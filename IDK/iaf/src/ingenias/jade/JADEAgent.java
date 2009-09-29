/*
    Copyright (C) 2005 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 

    INGENIAS Agent Framework is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS Agent Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS Agent Framework; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 */

package ingenias.jade;

import ingenias.editor.IDEState;
import ingenias.editor.Model;
import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.exception.InvalidEntity;
import ingenias.exception.NotFound;
import ingenias.jade.comm.ActiveConversation;
import ingenias.jade.comm.AgentProtocols;
import ingenias.jade.comm.ConversationManagement;
import ingenias.jade.comm.CustomLocks;
import ingenias.jade.comm.LocksManager;
import ingenias.jade.comm.StateBehavior;
import ingenias.jade.comm.StateBehaviorChangesListener;
import ingenias.jade.components.ApplicationManager;
import ingenias.jade.components.OutputEntity;
import ingenias.jade.components.Task;
import ingenias.jade.components.TaskOperations;
import ingenias.jade.components.TaskOutput;
import ingenias.jade.components.YellowPages;
import ingenias.jade.exception.NoAgentsFound;
import ingenias.jade.graphics.AgentGraphics;
import ingenias.jade.graphics.AgentModelMarqueeHandlerIAF;
import ingenias.jade.graphics.AgentModelPanelIAF;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.mental.Agent_data;
import ingenias.testing.DebugUtils;
import ingenias.testing.MSMRepository;
import ingenias.testing.MSPRepository;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CompositeBehaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.messaging.MatchAllFilter;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.proto.states.MsgReceiver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import org.jfree.util.WaitingImageObserver;
import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelListener;

/**
 * <p>This class represents an agent with mental state, communication session control,
 * and task management facilities. There is a default application: YellowPages. 
 * This application is available only to tasks producing interactions.
 * 
 * <p>The class puts together several elements: control components for communications,
 * components encapsulating actions, and general purpose components (maybe used for
 * GUI, interfacing with external applications). These elements are used by the two
 * main elements of the agent, the Mental State Processor and the Mental State manager.
 * The first is responsible of implementing the decision procedures of the agent. The
 * second implements the memory of the agent. The internals of both elements can be
 * explored by means of the GUI implemented by the Interaction Manager, in the package
 * graphics of this module.
 * 
 * <p>The initialization of this class is rather tricky, since not everything can be
 * initialized in the constructor of the JADEAgent. Most of the initialization actions take
 * place in the setup method. This is a restriction imposed by the JADE platform.
 * 
 * <p>To customize a JADEAgent, it is necessary to provide a collection of elements:
 * <ul>
 * <li>A class extending JADEAgent and implementing its abstract methods
 * <li>A class implementing the interface CustomLocks to link the interactions with the information locks
 * <li>A class implementing the AgentProtocols interface to define specific management of the protocols of the agent
 * <li>Several classes extending the StateBehavior class. Each class will implement the specific protocol to be followed
 * during the interaction. For each StateBehavior class, it will be required another specialization of DefaultCommControl
 * <li>Classes extending the Task class, to encasulate agent actions
 * <li>Classes implementing applications defined in the specification. For each application, there will be
 * an interface declaration (classes whose name ends in App), an implementation of the interface (classes
 * whose name ends in AppImp), and classes responsible of the initialization of the applications (classes 
 * whose name ends in Init).
 * <li>Classes extending the FrameFact class and representing the information to be handled during the agent lifetime
 * </ul>
 * 
 * <p>There are other elements involved in the JADEAgent internals, but these are not domain independent as the 
 * previous ones
 *  
 * WARNING: if you search in the DF outside of a behavior, it hangs
 * 
 * 
 * @author original version from Giovanni Rimassa. Modified by Jorge Gomez
 * @version 1.0
 */


abstract public class JADEAgent extends Agent{	


	private MentalStateManager msm=null;
	private transient AgentGraphics graphics=null;
	private ingenias.jade.MentalStateProcessor msp=null;

	private boolean agentInitialised=false;
	private ConversationManagement cman=null;
	private ApplicationManager apman=new ApplicationManager();
	private LocksManager lman=null;
	private AgentProtocols ap=null;
	private CustomLocks cl=null;
	private IDEState ids = null;
	private boolean completedLast=false;
	private CommsManagementBehavior mainBehavior;

	private  String synRegister="sinchronization of registering process";


	public JADEAgent(AgentProtocols ap, CustomLocks cl){
		super();

		try{
			this.apman.addApplication("YellowPages",new YellowPages(this)); // it creates
			// a default yellow pages application and makes it available to the agent
			this.setEnabledO2ACommunication(true,5);
			this.ap=ap;
			cman=new ConversationManagement(this,ap);
			this.cl=cl;
		}catch (Throwable e){
			e.printStackTrace();
		}

	}

	protected void addExpectedInputs(Task tobject, String type, String cardinality, MentalEntity expectedInput) {
		tobject.addInput(expectedInput);


	}

	protected void addExpectedInputs(Task tobject, String type, String cardinality, Vector<MentalEntity> expectedInput) {
		if (cardinality.equals("1")){            
			tobject.addInput(expectedInput.firstElement());
		} else {
			for (MentalEntity me:expectedInput)
				tobject.addInput(me);		           
		}

	}

	protected void addConsumedInput(TaskOutput to, String cardinality, Vector<MentalEntity> expectedInput) {
		if (cardinality.equals("1")){            
			to.add(new OutputEntity(expectedInput.firstElement(),TaskOperations.Consume));
		} else {
			for (MentalEntity me:expectedInput)
				to.add(new OutputEntity(me,TaskOperations.Consume));
		}

	}


	/**
	 * Obtain a reference to the Mental State Manager of this agent
	 * 
	 * @return A mental state manager
	 */
	public MentalStateManager getMSM() {
		return msm;
	}

	/**
	 * Obtain a reference to the Mental State Processor of this agent
	 * 
	 * @return A mental state manager
	 */
	public ingenias.jade.MentalStateProcessor getMSP() {
		return msp;
	}

	/**
	 * Obtain a reference to the graphics component of this agent
	 * 
	 * @return A graphics component
	 */
	public AgentGraphics getGraphics(){
		return this.graphics;
	}

	/**
	 * Obtains the conversation management component of the agent
	 * @return The conversations management component
	 */
	public ConversationManagement getCM(){
		return this.cman;
	}

	/**
	 * Obtains the Locks Manager component of the agent
	 * 
	 * @return The Locks manager
	 */
	public LocksManager getLM(){
		return this.lman;
	}

	/**
	 * It obtains the application manager component of the agent 
	 * 
	 * @return The application manager
	 */
	public ApplicationManager getAM(){
		return this.apman;
	}

	/**
	 * Informs if the agent has finished its initialization process. The initialization finishes
	 * when the setup method finishes. Due to overload of this method in inheritors, it was required
	 * to inform explicitely when the setup call chain was finished.
	 * 
	 * @return true if it was initialized and false in other case
	 */
	public boolean isAgentInitialised(){
		return this.agentInitialised;
	}


	/**
	 * It returns the possible descriptions associated to this agent. There are as many
	 * descriptions as role played. These descriptors will be entered into the JADE
	 * yellow pages so that other agents can access them
	 * 
	 * @return an array of JADE descriptors for this agent
	 */
	abstract public DFAgentDescription[] getDescription();

	/**
	 * Given the id of a goal, it informs which of the tasks known by the agent can help
	 * in achieving the goal. This information is later used by the mental state processor
	 * to decide which one execute.
	 *  
	 * @param id The id of the goal
	 * @return A vector of tasks
	 */
	protected abstract Vector<Task> tasksThatSatisfyGoal(String id);

	/**
	 * This method is invoked once the agent is initialized. It is used to add a default
	 * mental state to all agents. The main default information is the JADE id, which is asserted as
	 * "Agent_data" fact with a field containing the agent name.
	 *
	 */
	protected void agentInitialised(){
		this.agentInitialised=true;
		Agent_data ff=new Agent_data();
		ff.setAgentID(getLocalName());
		try {
			this.msm.addMentalEntity(ff);
		} catch (InvalidEntity e) {			
			e.printStackTrace();
		}
		DebugUtils.logEvent("AgentInitialised", new String[]{getLocalName()});
	}



	/**
	 * It registers this agent into JADE yellow pages services. It uses the  getDescription method
	 * to obtain the descriptors associated to this agent.
	 * 
	 * @throws FIPAException
	 */
	/*private void register() throws FIPAException {
		boolean registered = false;
		DFAgentDescription[] roles = this.getDescription();
		for (int k = 0; k < roles.length; k++) {
			while (!registered) {
				try {
					jade.domain.DFService.register(this, roles[k]);
					registered = true;
				}
				catch (FIPAException fe) {
					if (! (fe.getMessage().toLowerCase().indexOf("already") >= 0)) {
						System.out.println(fe.getACLMessage().getPerformative(fe.
								getACLMessage().
								getPerformative()));
						throw fe;
					}
				}
				if (!registered) {
					try {
						jade.domain.DFService.deregister(this, this.getAID());
					}
					catch (FIPAException fe) {
						fe.printStackTrace();
					}
				}
			}
		}
	}*/


	/**
	 * The intialization, first, starts the GUI components that will show internal agent
	 * information to the user. Later on, it starts the locks manager which is responsible
	 * of preventing some information to be removed before transmited to other agents.
	 * Following, the agent is registered into JADE yellow pages service with the descriptors
	 * extracted from method getDescription. Next, an agent model diagram is created to show
	 * the mental state of the agent. It also register action listener to react accordingly
	 * to user actions on the diagram. 
	 * Next, it uses the transfer object utility functions to assert information passed to the
	 * agent at construction time. Some operations cannot be performed until the agent is fully
	 * connected to JADE. For those operations, they require data that can only be passed in the
	 * constructor of the agent. To make these data available in the setup method, we use the method 
	 * setEnabledO2ACommunication and getO2AObject.
	 * After these initializations, the agent is ready to initialize its internal behaviors to handle
	 * incoming messages. For more details, look at the comments in the method body.
	 * 
	 * 
	 *  
	 */
	public void setup() {
		try {
			super.setup();
			if (IAFProperties.getGraphicsOn()){
				this.graphics=new AgentGraphics(this.getName());			
			}

			this.lman=new LocksManager(this.getAID().getLocalName(),cl);


			/**
			 * Registers the agent in the yellow pages services. It uses the getDescription
			 * to obtain a list of services the agent can provide
			 */
			DFAgentDescription[] roles = this.getDescription();
			for (int k = 0; k < roles.length; k++) {
				try {
					synchronized(synRegister){
						//System.err.println("iniciando registro");
						jade.domain.DFService.register(this,
								roles[k]);		
						//System.err.println("registrado");
					}

				}
				catch (FIPAException fe) {
					if (! (fe.getMessage().toLowerCase().indexOf("already") >= 0)) {
						System.out.println(fe.getACLMessage().getPerformative(fe.
								getACLMessage().
								getPerformative()));
					}
					else {
						try {
							synchronized(synRegister){
								//	System.err.println("iniciando registro 2");
								jade.domain.DFService.register(this,
										roles[k]);		
								//	System.err.println("registrado 2");
							}
						}
						catch (FIPAException fe1) {
							// No more exception should be produced
						}
					}
				}
			}

			/**
			 * Creates the GUI for the Mental State Manager
			 */

			AgentModelPanelIAF amm =null;
			if (IAFProperties.getGraphicsOn()){
				ids = IDEState.emptyIDEState();
				amm = new AgentModelPanelIAF(
						new ingenias.editor.entities.AgentModelDataEntity("1"), 
						"1", new Model(ids));
				amm.setMarqueeHandler(new AgentModelMarqueeHandlerIAF(amm));
			}
			/**
			 * Initializes the Mental State Manager and the Mental State Processor. The mental 
			 * state processor is associated to the GUI of the agents.
			 */
			if (IAFProperties.getGraphicsOn())
				this.msm=new MentalStateManager(ids,amm, this.getAID().getLocalName());
			else
				this.msm=new MentalStateManager(ids, this.getAID().getLocalName());
		//	this.addBehaviour(this.msm.getConvTracker());
			if (IAFProperties.getGraphicsOn())
				this.graphics.setMentalStatePanel(this.getLocalName(),amm);
			if (IAFProperties.getGraphicsOn())
				this.msp=new ingenias.jade.MentalStateProcessor(msm,this,this.getGraphics());
			else
				this.msp=new ingenias.jade.MentalStateProcessor(msm,this);
			this.lman.register(msp); // To get notifications of locks removal/addition
			/**
			 * Creates handlers to deal with operations made on the GUI representing the
			 * Mental State Manager
			 */
			/*this.msm.registerChangeListener(new GraphModelListener(){

			public void graphChanged(GraphModelEvent arg0) {
				MainInteractionManager.logMSM("Evaluating changes in mental state",getLocalName());
				if (arg0!=null){
					if (arg0.getChange().getInserted()!=null)
						for (int k=0;k<arg0.getChange().getInserted().length;k++){
							MainInteractionManager.logMSM("Inserted "+arg0.getChange().getInserted()[k],getLocalName());
						}
					if (arg0.getChange().getRemoved()!=null){

						for (int k=0;k<arg0.getChange().getRemoved().length;k++){
							getMSM().remove(arg0.getChange().getRemoved()[k].toString()); // removes the entity if removed from the panel
							MainInteractionManager.logMSM("Removed "+arg0.getChange().getRemoved()[k],getLocalName());
						}
					}
					if (arg0.getChange().getChanged()!=null)
						for (int k=0;k<arg0.getChange().getChanged().length;k++){
							MainInteractionManager.logMSM("Changed "+arg0.getChange().getChanged()[k],getLocalName());
						}
				}
				msp.doReplan();


			}

		});*/

			/**
			 * It creates the initial mental state of the agent. It retrieves elements from the
			 * getO2AObject method, a data transfer method.
			 */
			MentalEntity queue=null;
			do {
				queue=(MentalEntity)this.getO2AObject();
				if (queue!=null){
					try {
						this.msm.addMentalEntity(queue);
					} catch (InvalidEntity e) {

						e.printStackTrace();
					}
				} 
			} while (queue!=null);



			/**
			 * This behavior takes incoming acl messages and distributes them into two 
			 * groups: messages being processed and messages not being processed.
			 * Messages being processed are added again to the messages queue so that
			 * they can be processed by a specialized behavior
			 * @return 
			 */
			final LifeCycleThread vthread = new LifeCycleThread(this) ;

			getMSM().registerChangeListener(vthread);

			vthread.setName("Lifecycle "+this.getAID().getLocalName());
			vthread.start(); // The thread is constantly running so that no new
			// threads are needed;
			StateBehaviorChangesListener behaviorChangesListener=null;



			behaviorChangesListener=new StateBehaviorChangesListener(){		
				private void wakeUpCommsManagementBehavior(){
					/*while (!mainBehavior.getExecutionState().equalsIgnoreCase(CyclicBehaviour.STATE_BLOCKED)){
					//System.out.println(mainBehavior.getExecutionState());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}*/
					if (mainBehavior.getExecutionState().equalsIgnoreCase(CyclicBehaviour.STATE_BLOCKED))
						mainBehavior.restart();
				}

				@Override
				public void protocolFinished() {
					new Thread("waking up comms from "+getAID().getLocalName()){
						public void run(){
							wakeUpCommsManagementBehavior();		
						}
					}.start();

				}

				@Override
				public void protocolStarted() {
					new Thread("waking up comms from "+getAID().getLocalName()){
						public void run(){
							wakeUpCommsManagementBehavior();		
						}
					}.start();
				}


				@Override
				public void stateTransitionExecuted(String fromState, String toState) {
					new Thread("waking up comms from "+getAID().getLocalName()){
						public void run(){
							wakeUpCommsManagementBehavior();		
						}
					}.start();	
				}

			};

			mainBehavior=new CommsManagementBehavior(this,behaviorChangesListener) ;	
			mainBehavior.setBehaviourName("LifeCycle");
			this.addBehaviour(mainBehavior);
			this.getMainBehavior();
			


		} catch (Throwable t){
			t.printStackTrace();
		}
	}


	public CommsManagementBehavior getMainBehavior() {
		return mainBehavior;
		
	}

	public CustomLocks getCl() {
		return cl;
	}

	/**
	 * To shutdown the agent, it deregisters itself from the address services
	 * of the platform
	 */
	protected void takeDown() {
		System.out.println("Killing agent " + this.getName());
		DFAgentDescription[] roles = this.getDescription();
		for (int k = 0; k < roles.length; k++) {
			try {
				jade.domain.DFService.deregister(this,
						roles[k]);
			}
			catch (FIPAException fe) {
				//			fe.printStackTrace();
			}
		}
		MSMRepository.getInstance().remove(this.getLocalName());
		MSPRepository.getInstance().remove(this.getLocalName());
	}




	/**
	 * A helper action to create action listeners able to add mental entities 
	 * in runtime. It is used frequently by GUIs.
	 * 
	 * @param event The entity to add 
	 * @return	An action object that can be associated to a GUI element
	 */
	protected ActionListener generateActionListener(final Class event) {
		return new ActionListener(){			
			public void actionPerformed(ActionEvent e) {		
				new Thread(){
					public void run(){
						try {
							Constructor constructor = event.getConstructor(String.class);
							MentalEntity instance = (MentalEntity) constructor.newInstance(MentalStateManager.generateMentalEntityID());

							getMSM().addMentalEntity(instance);
						} catch (InvalidEntity e) {
							e.printStackTrace();
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}.start();
			}
		};
	}

}
