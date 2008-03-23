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


package ingenias.jade.comm;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.core.behaviours.*;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.InetAddress;

import jade.core.*;
import jade.core.behaviours.*;

import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.domain.DFService;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import java.util.*;

import ingenias.editor.Log;
import ingenias.editor.TypedVector;
import ingenias.editor.entities.FrameFact;
import ingenias.editor.entities.MentalEntity;
import ingenias.jade.AgentExternalDescription;
import ingenias.jade.IAFProperties;
import ingenias.jade.JADEAgent;
import ingenias.jade.MentalStateUpdater;
import ingenias.jade.exception.*;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.graphics.StateMachineFrame;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.exception.InvalidEntity;

/**
 * It represents the state of a running interaction. This class will be specialised by inheritors which
 * will focus in concrete protocols. The state transitions are executed on a DefaultComm class specialization
 * associated to a concrete state behavior specialization.
 * When information is received, it is inserted in the mental entity representing this behavior in the mental state
 * of the agent. 
 * A state behavior can have multiple states in a certain moment. At some moments, the interaction has several
 * possibilities to follow. We choose to enable all of them let the DefaultComm class decide which one
 * should be continued or if all of them have to be continued. It all depends on the definition of the protocol
 * and the internal guards defined along it. 
 * 
 * The class extends a basic JADE behavior. As any JADE behavior, this class will execute the action method only once.
 * However, this behavior is created within a cyclic behavior. Therefore, periodic calls to action method should be
 * expected.
 * 
 * 
 * @author jj
 *
 */
abstract public class StateBehavior
extends jade.core.behaviours.SimpleBehaviour
implements StateUpdater {

	private boolean finished = false; // Whether the interaction has finished
	private String conversationID = ""; // The id of the conversation
	protected Vector contentForNextMessage = new Vector(); // Contains entities to hold
	protected StateMachineFrame smf=null; // A graphic representation of this state machine
	DFAgentDescription[] actors; // actors involved in the interaction
	private Vector<String> state = new Vector<String>(); // Current state. It can have multiple states.
//	private Exception syncFailed;
	private DefaultCommControl dcc; // The transition control associated to this class
	private String protocol = ""; // The name of the protocol
	private String playedRole=""; // The role played by owner of this class
	private RuntimeConversation conv; // the mental entity representing this protocol
	private MentalStateUpdater msu=null;// an interface to modify the mental state
	private String agentName=""; // The name of the agent owning this state machine
	public static final String ABORTED_STATE="_aborted_";
	private JADEAgent securityCopy=null;

	private Vector<MentalEntity> producedEntities=new Vector<MentalEntity>();

	private TimeoutController timeout=new TimeoutController();
	private Vector<jade.core.behaviours.Behaviour> extrabehaviors=new Vector<jade.core.behaviours.Behaviour>();

	/**
	 * It creates a new instance of a protocol
	 * @param conv The mental entity representing the protocol
	 * @param playedRole The role played by the owner of this class
	 * @param actors Other actors involved in the interaction
	 * @param dcc The control class responsible of executing state transitions
	 * @param protocol The name of the protocol
	 */
	public StateBehavior( RuntimeConversation conv, 
			String playedRole, 
			ingenias.jade.MentalStateUpdater msu,
			DFAgentDescription[] actors, 
			DefaultCommControl dcc,
			String protocol, String agentName) {
		super();

		this.actors = actors;
		this.conversationID = conv.getConversationID();
		this.dcc = dcc;
		this.protocol = protocol;
		if (IAFProperties.getGraphicsOn())
			this.smf=new StateMachineFrame(conversationID,protocol,playedRole);
		this.playedRole=playedRole;
		this.conv=conv;
		this.msu=msu;
		this.agentName=agentName;
		if (this.playedRole==null){
			throw new RuntimeException("In StateBehavior for interaction "+protocol+" found a null role ");
		}
		conv.setState("INITIATED");
		securityCopy=(JADEAgent) myAgent;
	}
	
	@Override
	public int onEnd() {
		removeExtraBehaviors();
		return super.onEnd();
	}

	protected void addCreatedBehaviors(Vector<jade.core.behaviours.SequentialBehaviour> behaviors) {
		this.extrabehaviors.addAll(behaviors);
	}
	
	private void removeExtraBehaviors(){
		for (jade.core.behaviours.Behaviour behavior:extrabehaviors){
		this.securityCopy.removeBehaviour(behavior);
		}
	}

	/**
	 * It obtains the role played by the owner of this class in this interaction
	 * @return The name of the role
	 */
	public String getPlayedRole(){
		return this.playedRole;
	}

	/**
	 * It returns the graphical representation of the protocol 
	 * @return The graphical representation
	 */
	protected StateMachineFrame getSMF(){
		return this.smf;
	}

	/**
	 * It creates a vector of AgentExternalDescription instances to be sent in the first messages
	 * of the interaction. It is used to inform other agents collaborating in the interaction who
	 * else will be in. 
	 * @return A vector of external descriptions of the agent
	 * 
	 */
	public Vector<AgentExternalDescription> getAgentExternalDescription() {
		Vector<AgentExternalDescription> participants = new Vector<AgentExternalDescription>();
		DFAgentDescription[] agents = actors;		

		int j = 0;
		for (int k = 0; k < agents.length; k++) {
			Iterator it = agents[k].getAllServices();
			while (it.hasNext()) {
				ServiceDescription sd = (ServiceDescription) it.next();
				participants.add(new AgentExternalDescription(agents[k].getName(),
						sd.getType()));
			}
		}
		participants.add(new AgentExternalDescription(myAgent.getAID(),
				playedRole));

		return participants;
	}

	/**
	 * it tells the name of the protocol followed by this state behavior 
	 * @return A string with the name of the protocol
	 */
	public String getProtocol(){
		return this.protocol;
	}

	/**
	 * It obtains the control of this state behavior
	 * @return the contorl
	 */
	public DefaultCommControl getDCC() {
		return this.dcc;
	}

	/**
	 * It returns a list of AID participating in this interaction
	 * @return The list of collaborators
	 */
	public AID[] getActors() {
		AID[] result = new AID[actors.length];
		for (int k = 0; k < result.length; k++) {
			result[k] = actors[k].getName();
		}
		return result;
	}

	/**
	 * It finds the agent playing the role "role" in the collection of initial actors
	 * supplied at the beginning of the interaction. If no agent is found, it produces
	 * a NoAgentsFound exception
	 * 
	 * @param role The name of the role to be looked up
	 * @return	The AID of the agent playing the requested role
	 * @throws NoAgentsFound
	 */
	protected Vector<AID> getActor(String role) throws NoAgentsFound{
		int k = 0;
		Vector<AID> result = new Vector<AID>();
		boolean found = false;
		while (k < this.actors.length) {
			Iterator it = actors[k].getAllServices();
			found=false;
			while (it.hasNext() && !found) {
				ServiceDescription serv = (ServiceDescription) it.next();
				found = serv.getType().equals(role);
			}
			if (found) {
				result.add(actors[k].getName());
			}
			k++;
		}

		/*if (result == null) {
		 try {
		 DFAgentDescription[] dfad = this.ja.getAM().getYellowPages().getAgents(role);
		 }
		 catch (FIPAException fe) {
		 fe.printStackTrace();
		 }
		 }*/
		if (result==null){
			throw new NoAgentsFound("No agent playing role "+role+" was found in the collection of initial "+
					"actors supplied to the state machine "+this.getProtocol()+ " in agent "
					+myAgent.getLocalName());
		}
		return result;
	}

	/**
	 * It executes the action associated to this behavior. This method is redefined by inheritors of this class.
	 * The method will include if-then sentences to determine if either a receive or a send action should be
	 * executed into each state. Transitions of state will be determined by the defaultcomm instance.
	 * This method will be executed periodically by JADE platform.
	 */
	public abstract void action();

	/**
	 * It establishes the content for the next message to be sent by the defaultcomm instance. It has to be
	 * a serializable object and it is supposed to be a vector of mental entities. This method can be executed
	 * several times. Each added object is accumulated in a list of waiting-to-be-sent entities.
	 * @param obj A serializable object to be sent
	 */
	public void addContentForNextMessage(Serializable obj) {
		this.contentForNextMessage.add(obj);
	}

	/**
	 * It allows the defaultcomm instance to know which information has to be sent.
	 * @return The content to be sent in a message
	 */
	public Serializable getContentForNextMessage() {
		return this.contentForNextMessage;
	}
	/**
	 * It cleans the waiting-to-be-sent messages queue. 
	 */
	public void clearContentNextMessage() {
		this.contentForNextMessage.clear();
	}

	/**
	 * It tells if one of the current states matches a specific one. A state behavior can have multiple states
	 * @param state the state id
	 * @return true if the current state contains such state id
	 */
	public boolean isState(String state) {
		return this.state.contains(state);
	}


	/**
	 * It tells if this state behavior has finished. It finishes when the specification protocol
	 * has achieved one end state
	 * 
	 * @return true if it finished, and false i.o.c.
	 */
	public boolean getFinished(){
		return this.finished;
	}

	/**
	 * It removes a state id from the list of current states
	 *  
	 * @param state The state id
	 */
	public synchronized void removeState(String state){
		this.state.remove(state);
		if (IAFProperties.getGraphicsOn())
			this.smf.clearRed(state);
	}

	/** 
	 * It adds a new state to the list of current states 
	 * @param state
	 */
	public synchronized void addState(String state) {
		this.state.add(state);
		if (IAFProperties.getGraphicsOn())
			this.smf.enable(state);
	}


	/**
	 * It sets the conversation id of this object. It matches the conversation id stored in the
	 * received messages. This method is invoked from the JADEAgent class to initialise a collaborator
	 * state behavior
	 * 
	 * @param cid The conversation id
	 */
	public void setConversationID(String cid) {
		this.conversationID = cid;
	}

	/**
	 * It informs the state behavior that the protocol has finished.
	 *
	 */
	public synchronized void setFinished() {		
		this.finished = true;
		if (conv!=null)
			conv.setState("FINISHED");
		else
			System.err.println("WARNING: conversation "+this.conversationID+" of protocol "+
					this.protocol+" not found in "+this.myAgent.getLocalName());
		//ja.getMSM().remove(conv.getId());
	}

	private synchronized void setAborted() {		
		this.finished = true;
		if (conv!=null)
			conv.setState("ABORTED");
		else
			System.err.println("WARNING: conversation "+this.conversationID+" of protocol "+
					this.protocol+" not found in "+this.myAgent.getLocalName());
		//ja.getMSM().remove(conv.getId());
	}

	public synchronized void setRunning() {				
		if (conv!=null)
			conv.setState("RUNNING");
		else
			System.err.println("WARNING: conversation "+this.conversationID+" of protocol "+
					this.protocol+" not found in "+this.myAgent.getLocalName());
		//ja.getMSM().remove(conv.getId());
	}


	/**
	 * JADE method. It tells the cyclic behavior that this behavior has finished or not. If it was not
	 * finished it has to be executed more times.
	 */
	public boolean done() {
		return finished;
	}

	/**
	 * It returns the list of participants of this protocol 
	 */
	public DFAgentDescription[] getParticipants() {
		return this.actors;
	}


	/**
	 * It updates the mental state of the agent owning this object. It takes as input
	 * a vector of mental entities received from another agent. These entities are
	 * inserted indirectly in the mental state of the agent. They appear as part of the
	 * contextual information exchanged along the interaction. Therefore the new information
	 * is not asserted directly, but added inside of a special field of the conversation
	 * object. At the end, the mental state manager is notified that a change has occurred.
	 * It has to be done this way because we change the content of a conversation object,
	 * without informing the mental state manager.  
	 * 
	 * @param ent The vector of new entities received
	 */
	public synchronized void updateMentalState(Vector convAssert){
		if (securityCopy==null)
			securityCopy=(JADEAgent) myAgent;
		Enumeration elements;		
		Vector<MentalEntity> tv=new Vector<MentalEntity>();
		if (conv!=null){
			for (int k=0;k<convAssert.size();k++){
				MentalEntity ff=(MentalEntity)convAssert.elementAt(k);
				//this.ja.getMSM().addMentalEntity(ff);
				tv.add(ff);
				ingenias.jade.graphics.MainInteractionManager.logInteraction("Interaction added "+ff+
						" to its context",agentName,conversationID);
			}                 
			// It aggregates previous existing elements
			String content="";


			for (Object me:tv){							
				content=content+me.toString()+"\n";
			}
			ingenias.jade.graphics.MainInteractionManager.logInteraction("Current interaction content "+content,agentName,conversationID);					

			try {
							
				securityCopy.getMSM().addMentalEntityToConversation(conv, tv);// myAgent attribute may have been 
				// set to null;
			} catch (InvalidEntity e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}  else {
			MainInteractionManager.logInteraction("Failure at interaction "+
					this.getConversationID()+
					". Conversation removed from mental state",
					this.myAgent.getLocalName(),this.getConversationID());
		}
	}


	/**
	 * In collaborators, this updates the list of actors participating in a interaction. In a collaborator,
	 * the list of actors is not known in the state behavior creation time. Only when the first message is
	 * received, this information is available. The object is initialised in the defaultcommcontrol associated
	 * to this class. 
	 * 
	 * @param actors The list of actors participating in the interaction.
	 */
	public void updateActorList(Vector actors) {
		// al has the format "actor1,{actor,}*"
		// Each actor ID is global unique
		/*  Vector actors=new Vector();
		 StringTokenizer st=new StringTokenizer(al,",");
		 while (st.hasMoreTokens()){
		 String aid=st.nextToken();
		 if (aid!=null && aid.length()>0)
		 actors.add(new AID(aid));
		 }*/
		Enumeration enumeration = actors.elements();
		Vector descriptions = new Vector();
		while (enumeration.hasMoreElements()) {
			AgentExternalDescription aed = (AgentExternalDescription) enumeration.
			nextElement();
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(aed.id);
			ServiceDescription sd = new ServiceDescription();
			//					sd.setName(getLocalName() + "-sub-df");
			sd.setType(aed.role);
			//					sd.setOwnership("JADE");
			dfd.addServices(sd);
			descriptions.add(dfd);

		}
		DFAgentDescription[] result = new DFAgentDescription[descriptions.size()];
		System.arraycopy(descriptions.toArray(), 0, result, 0, result.length);
		this.actors = result;
		
	}


	/**
	 * It obtains the conversation id stored in the state behavior
	 */
	public String getConversationID() {
		return this.conversationID;
	}
	public String getStates(){
		String result=null;
		for (String s:this.state){
			result=result+s+";";
		}
		return result;
	}

	public void updateStates(String agentName){
		if (IAFProperties.getGraphicsOn())
			smf.updateStates(agentName);
	}


	public RuntimeConversation getConversation() {
		return this.conv;
	}

	public void addProducedEntity(ingenias.editor.entities.MentalEntity ent){    	
		this.producedEntities.add(ent);
	}

	public Vector<ingenias.editor.entities.MentalEntity> getProducedEntities(){    	
		return (Vector<MentalEntity>) this.producedEntities.clone();
	}

	public synchronized void abortDueTimeout() {
		//System.err.println("ABORTED "+this.getPlayedRole()+"-"+this.conversationID+"!!!!!!!!!!!!!!!!!!!!");
		if (IAFProperties.getGraphicsOn()){
			for (String cstate:this.state){
				smf.clearRed(cstate);
			}
			smf.setAbortColor();			
		}
		this.state.clear();	
		timeout.stop();
		setAborted();
		if (conv!=null)
			conv.setAbortCode(IAFProperties.TIMEOUT);
		else
			System.err.println("WARNING: conversation "+this.conversationID+" of protocol "+
					this.protocol+" not found in "+this.myAgent.getLocalName());
	}



	public TimeoutController getTimeout() {
		return timeout;
	}

}