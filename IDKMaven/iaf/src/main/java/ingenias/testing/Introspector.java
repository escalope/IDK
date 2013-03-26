/**
 * This class reuses the introspection agent code to provide a synchronous interface with the
 * introspection services each JADE agent provides.  
 * @author Jorge J. Gomez-Sanz
 *
 */
package ingenias.testing;


import jade.content.AgentAction;
import jade.content.onto.basic.Action;
import jade.content.onto.basic.Done;
import jade.core.AID;
import jade.core.Agent;
import jade.core.BehaviourID;
import jade.core.ContainerID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SenderBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.FIPAService;
import jade.domain.FIPAAgentManagement.AlreadyRegistered;
import jade.domain.FIPAAgentManagement.NotRegistered;
import jade.domain.JADEAgentManagement.DebugOff;
import jade.domain.JADEAgentManagement.DebugOn;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.domain.introspection.AddedBehaviour;
import jade.domain.introspection.AddedContainer;
import jade.domain.introspection.BornAgent;
import jade.domain.introspection.ChangedAgentState;
import jade.domain.introspection.ChangedBehaviourState;
import jade.domain.introspection.DeadAgent;
import jade.domain.introspection.Event;
import jade.domain.introspection.EventRecord;
import jade.domain.introspection.IntrospectionOntology;
import jade.domain.introspection.IntrospectionVocabulary;
import jade.domain.introspection.MovedAgent;
import jade.domain.introspection.Occurred;
import jade.domain.introspection.PostedMessage;
import jade.domain.introspection.ReceivedMessage;
import jade.domain.introspection.RemovedBehaviour;
import jade.domain.introspection.RemovedContainer;
import jade.domain.introspection.ResetEvents;
import jade.domain.introspection.SentMessage;
import jade.domain.introspection.StartNotify;
import jade.domain.introspection.AMSSubscriber.EventHandler;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.SimpleAchieveREInitiator;
import jade.proto.SimpleAchieveREResponder;
import jade.tools.ToolAgent;
import jade.tools.introspector.Sensor;
import jade.tools.introspector.SensorManager;
import jade.tools.introspector.gui.MainWindow;
import jade.util.Logger;
import jade.util.leap.List;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class Introspector extends ToolAgent {





	private Set allAgents = null;
	private Hashtable preload = null;

	private class AMSRequester extends SimpleAchieveREInitiator {

		private String actionName;


		public AMSRequester(String an, ACLMessage request) {
			super(Introspector.this, request);
			actionName = an;
		}

		protected void handleNotUnderstood(ACLMessage reply) {
			SynchronousIntrospectionServiceForJADE.logError("NOT-UNDERSTOOD received during " + actionName);
		}

		protected void handleRefuse(ACLMessage reply) {
			SynchronousIntrospectionServiceForJADE.logError("REFUSE received during " + actionName);
		}

		protected void handleAgree(ACLMessage reply) {
			if(logger.isLoggable(Logger.FINEST))
				logger.log(Logger.FINEST,"AGREE received");
		}

		protected void handleFailure(ACLMessage reply) {
			SynchronousIntrospectionServiceForJADE.logError("FAILURE received during " + actionName);
		}

		protected void handleInform(ACLMessage reply) {
			if(logger.isLoggable(Logger.FINEST))
				logger.log(Logger.FINEST,"INFORM received");
		}

	} // End of AMSRequester class

	// GUI events
	public static final int STEP_EVENT = 1;
	public static final int BREAK_EVENT = 2;
	public static final int SLOW_EVENT = 3;
	public static final int GO_EVENT = 4;
	public static final int KILL_EVENT = 5;
	public static final int SUSPEND_EVENT = 6;


	private Sensor guiSensor = new Sensor();
	private String myContainerName;
	private Map windowMap = Collections.synchronizedMap(new TreeMap());

	// The set of agents that are observed in step-by-step mode
	private Set stepByStepAgents = new HashSet();
	// The set of agents that are observed in slow mode
	private Set slowAgents = new HashSet();
	// Maps an observed agent with the String used as reply-with in the
	// message that notified about an event that had to be observed synchronously
	private Map pendingReplies = new HashMap();
	// Maps an observed agent with the ToolNotifier that notifies events
	// about that agent to this Introspector
	private Map notifiers = new HashMap();

	private SequentialBehaviour AMSSubscribe = new SequentialBehaviour();

	class IntrospectorAMSListenerBehaviour extends AMSListenerBehaviour {

		protected void installHandlers(Map handlersTable) {

			handlersTable.put(IntrospectionVocabulary.META_RESETEVENTS, new EventHandler() {
				public void handle(Event ev) {
					ResetEvents re = (ResetEvents)ev;
					//myGUI.resetTree();
				}
			});

			handlersTable.put(IntrospectionVocabulary.ADDEDCONTAINER, new EventHandler() {
				public void handle(Event ev) {
					AddedContainer ac = (AddedContainer)ev;
					ContainerID cid = ac.getContainer();
					String name = cid.getName();
					String address = cid.getAddress();
					/*	try {
						InetAddress addr = InetAddress.getByName(address);
						myGUI.addContainer(name, addr);
					}
					catch(UnknownHostException uhe) {
						myGUI.addContainer(name, null);
					}*/
				}
			});

			handlersTable.put(IntrospectionVocabulary.REMOVEDCONTAINER, new EventHandler() {
				public void handle(Event ev) {
					RemovedContainer rc = (RemovedContainer)ev;
					ContainerID cid = rc.getContainer();
					String name = cid.getName();
					//	myGUI.removeContainer(name);
				}
			});

			handlersTable.put(IntrospectionVocabulary.BORNAGENT, new EventHandler() {
				public void handle(Event ev) {
					BornAgent ba = (BornAgent)ev;
					ContainerID cid = ba.getWhere();
					String container = cid.getName();
					AID agent = ba.getAgent();
					allAgents.add(agent);
					//myGUI.addAgent(container, agent);
					if( preloadContains( agent.getName() ) != null )
						Introspector.this.addAgent( agent );
					if(agent.equals(getAID()))
						myContainerName = container;
				}
			});

			handlersTable.put(IntrospectionVocabulary.DEADAGENT, new EventHandler() {
				public void handle(Event ev) {
					DeadAgent da = (DeadAgent)ev;
					ContainerID cid = da.getWhere();
					String container = cid.getName();
					AID agent = da.getAgent();
					allAgents.remove(agent);
					MainWindow m = (MainWindow)windowMap.get(agent);
					/*if(m != null) {
						myGUI.closeInternal(m);
						windowMap.remove(agent);
					}
					myGUI.removeAgent(container, agent);*/
				}
			});

			handlersTable.put(IntrospectionVocabulary.MOVEDAGENT, new EventHandler() {
				public void handle(Event ev) {
					MovedAgent ma = (MovedAgent)ev;
					AID agent = ma.getAgent();
					ContainerID from = ma.getFrom();
					/*myGUI.removeAgent(from.getName(), agent);
					ContainerID to = ma.getTo();
					myGUI.addAgent(to.getName(), agent);

					if (windowMap.containsKey(agent)) {
						MainWindow m = (MainWindow)windowMap.get(agent);
						// FIXME: We should clean behaviours and pending messages here
						requestDebugOn(agent);
					}*/
				}
			});

		} // End of installHandlers() method
	}

	public void toolSetup() {

		ACLMessage msg = getRequest();
		msg.setOntology(JADEManagementOntology.NAME);

		// Send 'subscribe' message to the AMS
		AMSSubscribe.addSubBehaviour(new SenderBehaviour(this, getSubscribe()));

		// Handle incoming 'inform' messages about Platform events from the AMS
		AMSSubscribe.addSubBehaviour(new IntrospectorAMSListenerBehaviour());

		addBehaviour(AMSSubscribe);

		// Handle incoming INFORM messages about Agent and Message events from the
		// ToolNotifiers
		addBehaviour(new IntrospectionListenerBehaviour());

		// Handle incoming INFORM messages about observation start/stop from
		// ToolNotifiers
		addBehaviour(new ControlListenerBehaviour(this));

		// Handle incoming REQUEST to start/stop debugging agents
		addBehaviour(new RequestListenerBehaviour());

		// Handle incoming REQUEST inform of current behaviors of an agent
		addBehaviour(new RequestListenerBehaviour());

		// Manages GUI events
		addBehaviour(new SensorManager(this, guiSensor) {
			public void onEvent(jade.util.Event ev) {
				AID id = ((MainWindow) ev.getSource()).getDebugged();
				switch (ev.getType()) {
				case STEP_EVENT:
					proceed(id);
					break;
				case BREAK_EVENT:
					stepByStepAgents.add(id);
					slowAgents.remove(id);
					break;
				case SLOW_EVENT:
					stepByStepAgents.remove(id);
					slowAgents.add(id);
					proceed(id);
					break;
				case GO_EVENT:
					stepByStepAgents.remove(id);
					slowAgents.remove(id);
					proceed(id);
				}
			}
		}	);

		allAgents = new HashSet();
		preload = new Hashtable();

		/*
		 * preload agents from argument array if arguments present
		 */
		Object[] arguments = getArguments();
		if ( arguments != null )
		{
			for( int i=0; i < arguments.length; ++i )
			{
				parsePreloadDescription( (String)arguments[i] );
			}
		}	
		try {
			SynchronousIntrospectionServiceForJADE.register(this);
		} catch (AlreadyRegistered e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 Adds an agent to the debugged agents map, and asks the AMS to
	 start debugging mode on that agent.
	 */
	public boolean addAgent(AID name) {
		if(!windowMap.containsKey(name)) {
			MainWindow m = new MainWindow(guiSensor, name);
			/*myGUI.addWindow(m);
			windowMap.put(name, m);*/

			// Enable the following instruction if you want STEP_BY_STEP to
			// be the default debug mode
			//stepByStepAgents.add(name);

			requestDebugOn(name);
			return true;
		}
		else {
			return false;
		}    
	}

	private void requestDebugOn(AID name) {
		try {
			ACLMessage msg = getRequest();
			msg.setOntology(JADEManagementOntology.NAME);
			DebugOn dbgOn = new DebugOn();
			dbgOn.setDebugger(getAID());
			dbgOn.addDebuggedAgents(name);
			Action a = new Action();
			a.setActor(getAMS());
			a.setAction(dbgOn);

			getContentManager().fillContent(msg, a);

			addBehaviour(new AMSRequester("DebugOn", msg));
		}
		catch(Exception fe) {
			fe.printStackTrace();
		}
	}

	/*
	 Removes an agent from the debugged agents map, and closes its
	 window. Moreover,it and asks the AMS to stop debugging mode on
	 that agent.
	 */
	public void removeAgent(final AID name) {
		if(windowMap.containsKey(name)) {
			try {
				/*final MainWindow m = (MainWindow)windowMap.get(name);
				myGUI.closeInternal(m);
				windowMap.remove(name);*/

				stepByStepAgents.remove(name);
				slowAgents.remove(name);
				proceed(name);

				ACLMessage msg = getRequest();
				msg.setOntology(JADEManagementOntology.NAME);
				DebugOff dbgOff = new DebugOff();
				dbgOff.setDebugger(getAID());
				dbgOff.addDebuggedAgents(name);
				Action a = new Action();
				a.setActor(getAMS());
				a.setAction(dbgOff);

				getContentManager().fillContent(msg, a);

				addBehaviour(new AMSRequester("DebugOff", msg));
			}
			catch(Exception fe) {
				fe.printStackTrace();
			}
		}
	}


	/**
	 Cleanup during agent shutdown. This method cleans things up when
	 <em>RMA</em> agent is destroyed, disconnecting from <em>AMS</em>
	 agent and closing down the platform administration <em>GUI</em>.
	 */
	public void toolTakeDown() {
		// Stop debugging all the agents
		if(!windowMap.isEmpty()) {
			ACLMessage msg = getRequest();
			msg.setOntology(JADEManagementOntology.NAME);
			DebugOff dbgOff = new DebugOff();
			dbgOff.setDebugger(getAID());
			Iterator it = windowMap.keySet().iterator();
			while(it.hasNext()) {
				AID id = (AID)it.next();
				dbgOff.addDebuggedAgents(id);
			}

			Action a = new Action();
			a.setActor(getAMS());
			a.setAction(dbgOff);

			try {
				getContentManager().fillContent(msg, a);
				FIPAService.doFipaRequestClient(this, msg);
			}
			catch(Exception fe) {
				// When the AMS replies the tool notifier is no longer registered.
				// But we don't care as we are exiting
				if(logger.isLoggable(Logger.WARNING))
					logger.log(Logger.WARNING,fe.getMessage());
			}
		}

		send(getCancel());
		try {
			SynchronousIntrospectionServiceForJADE.unregister(this);
		} 
		catch (NotRegistered e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 Callback method for platform management <em>GUI</em>.
	 */
	/*	public AgentTreeModel getModel() {
		return myGUI.getModel();
	}*/

	/*
	 Listens to introspective messages and dispatches them.
	 */
	private class IntrospectionListenerBehaviour extends CyclicBehaviour {

		private MessageTemplate template;
		private Map handlers = new TreeMap(String.CASE_INSENSITIVE_ORDER);

		IntrospectionListenerBehaviour() {
			template = MessageTemplate.and(MessageTemplate.MatchOntology(IntrospectionOntology.NAME),
					MessageTemplate.MatchConversationId(getName() + "-event"));

			// Fill handlers table ...
			handlers.put(IntrospectionVocabulary.CHANGEDAGENTSTATE, new EventHandler() {
				public void handle(Event ev) {

				}

			});

			handlers.put(IntrospectionVocabulary.ADDEDBEHAVIOUR, new EventHandler() {
				public void handle(Event ev) {
					AddedBehaviour ab = (AddedBehaviour)ev;
					AID agent = ab.getAgent();
					SynchronousIntrospectionServiceForJADE.addBehavior(agent,ab.getBehaviour());

					/*			MainWindow wnd = (MainWindow)windowMap.get(agent);
					if(wnd != null)
						myGUI.behaviourAdded(wnd, ab);*/
				}

			});

			handlers.put(IntrospectionVocabulary.REMOVEDBEHAVIOUR, new EventHandler() {
				public void handle(Event ev) {
					RemovedBehaviour rb = (RemovedBehaviour)ev;
					AID agent = rb.getAgent();
					SynchronousIntrospectionServiceForJADE.removeBehavior(agent,rb.getBehaviour());

				}

			});

			handlers.put(IntrospectionVocabulary.CHANGEDBEHAVIOURSTATE, new EventHandler() {
				public void handle(Event ev) {
					ChangedBehaviourState cs = (ChangedBehaviourState)ev;
					AID agent = cs.getAgent();		

					SynchronousIntrospectionServiceForJADE.modifyBehavior(agent,cs.getBehaviour());
					proceed(agent);
				}

			});

			handlers.put(IntrospectionVocabulary.SENTMESSAGE, new EventHandler() {
				public void handle(Event ev) {
					SentMessage sm = (SentMessage)ev;
					AID sender = sm.getSender();

					/*MainWindow wnd = (MainWindow)windowMap.get(sender);
					if(wnd != null)
						myGUI.messageSent(wnd, sm);*/
				}

			});

			handlers.put(IntrospectionVocabulary.RECEIVEDMESSAGE, new EventHandler() {
				public void handle(Event ev) {
					ReceivedMessage rm = (ReceivedMessage)ev;
					AID receiver = rm.getReceiver();

					/*MainWindow wnd = (MainWindow)windowMap.get(receiver);
					if(wnd != null)
						myGUI.messageReceived(wnd, rm);*/
				}

			});

			handlers.put(IntrospectionVocabulary.POSTEDMESSAGE, new EventHandler() {
				public void handle(Event ev) {
					PostedMessage pm = (PostedMessage)ev;
					AID receiver = pm.getReceiver();

					/*	MainWindow wnd = (MainWindow)windowMap.get(receiver);
					if(wnd != null)
						myGUI.messagePosted(wnd, pm);*/
				}

			});

			handlers.put(IntrospectionVocabulary.CHANGEDAGENTSTATE, new EventHandler() {
				public void handle(Event ev) {
					ChangedAgentState cas = (ChangedAgentState)ev;
					AID agent = cas.getAgent();

					/*MainWindow wnd = (MainWindow)windowMap.get(agent);
					if(wnd != null)
						myGUI.changedAgentState(wnd, cas);*/
				}

			});

		}

		public void action() {

			ACLMessage message = receive(template);
			if(message != null) {
				AID name = message.getSender();
				try{
					Occurred o = (Occurred)getContentManager().extractContent(message);
					EventRecord er = o.getWhat();
					Event ev = er.getWhat();
					// DEBUG
					if(logger.isLoggable(Logger.FINEST))
						logger.log(Logger.FINEST,"Received event "+ev);
					if (message.getReplyWith() != null) {
						// A reply is expected --> put relevant information into the
						// pendingReplies Map
						ChangedBehaviourState cs = (ChangedBehaviourState)ev;
						pendingReplies.put(cs.getAgent(), message.getReplyWith());
					}
					String eventName = ev.getName();
					EventHandler h = (EventHandler)handlers.get(eventName);
					if(h != null)
						h.handle(ev);
				}
				catch(Exception fe) {
					fe.printStackTrace();
				}
			}
			else
				block();
		}

	} // End of inner class IntrospectionListenerBehaviour


	/**
	 Inner class ControlListenerBehaviour.
	 This is a behaviour that listen for messages from ToolNotifiers
	 informing that they have started notifying events about a given
	 agent. These information are used to keep the map between observed
	 agents and ToolNotifiers up to date.
	 */
	private class ControlListenerBehaviour extends CyclicBehaviour {
		private MessageTemplate template;

		ControlListenerBehaviour(Agent a) {
			super(a);
			template = MessageTemplate.and(
					MessageTemplate.MatchOntology(IntrospectionOntology.NAME),
					MessageTemplate.MatchConversationId(getName() + "-control"));
		}

		public void action() {
			ACLMessage message = receive(template);
			if(message != null) {
				try{
					Done d = (Done)getContentManager().extractContent(message);
					Action a = (Action)d.getAction();
					AID tn = a.getActor();
					StartNotify sn = (StartNotify) a.getAction();
					AID observed = sn.getObserved();
					notifiers.put(observed, tn);
				}
				catch(Exception fe) {
					fe.printStackTrace();
				}
			}
			else {
				block();
			}
		}

	} // End of inner class ControlListenerBehaviour

	private void proceed(AID id) {
		String pendingReplyWith = (String) pendingReplies.remove(id);
		AID tn = (AID) notifiers.get(id);
		if (pendingReplyWith != null && tn != null) {
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.addReceiver(tn);
			msg.setInReplyTo(pendingReplyWith);
			send(msg);
		}
	}

	/**
	 The doDelete() method is re-defined because if the Introspector is
	 killed while it is debugging the AMS a deadlock occurs. In fact,
	 while exiting, the Introspector can't make the debugged agents
	 proceed. At the same time however the Introspector can't proceed as it
	 is waiting for the answer to its AMS deregistration
	 */
	public void doDelete() {
		AID amsId = getAMS();
		if (windowMap.containsKey(amsId)) {
			try {
				final MainWindow m = (MainWindow)windowMap.get(amsId);
				/*myGUI.closeInternal(m);
				windowMap.remove(amsId);*/

				stepByStepAgents.remove(amsId);
				slowAgents.remove(amsId);
				proceed(amsId);

				ACLMessage msg = getRequest();
				msg.setOntology(JADEManagementOntology.NAME);
				DebugOff dbgOff = new DebugOff();
				dbgOff.setDebugger(getAID());
				dbgOff.addDebuggedAgents(amsId);
				Action a = new Action();
				a.setActor(getAMS());
				a.setAction(dbgOff);

				getContentManager().fillContent(msg, a);

				addBehaviour(new AMSRequester("DebugOff", msg) {
					public int onEnd() {
						myAgent.doDelete();
						return 0;
					}
				} );
			}
			catch(Exception fe) {
				fe.printStackTrace();
			}
		}
		else {
			super.doDelete();
		}
	}

	/**
	 * Search keys in preload for a string which matches (using isMatch method)
	 * the agent name.
	 * @param agentName The agent name.
	 * @return String The key which matched.
	 */
	protected String preloadContains(String agentName) {
		for (Enumeration enumeration = preload.keys(); enumeration.hasMoreElements() ;) {
			String key = (String)enumeration.nextElement();
			if (isMatch(key, agentName)) {
				return key;
			}
		}
		return null;
	}

	/**
	 * Given two strings determine if they match. We iterate over the match expression
	 * string from left to right as follows:
	 * <ol>
	 * <li> If we encounter a '*' in the expression token they match.
	 * <li> If there aren't any more characters in the subject string token they don't match.
	 * <li> If we encounter a '?' in the expression token we ignore the subject string's
	 * character and move on to the next iteration.
	 * <li> If the character in the expression token isn't equal to the character in
	 * the subject string they don't match.
	 * </ol>
	 * If we complete the iteration they match only if there are the same number of
	 * characters in both strings.
	 * @param aMatchExpression An expression string with special significance to '?' and '*'.
	 * @param aString The subject string.
	 * @return True if they match, false otherwise.
	 */
	protected boolean isMatch(String aMatchExpression, String aString)
	{
		int expressionLength = aMatchExpression.length();
		for (int i = 0; i < expressionLength; i++)
		{
			char expChar = aMatchExpression.charAt(i);
			if (expChar == '*')
				return true;   // * matches the remainder of anything
			if (i == aString.length())
				return false;  // if we run out of characters they don't match
			if (expChar == '?')
				continue;      // ? matches any single character so keep going
			if (expChar != aString.charAt(i))
				return false;  // if non wild then must be exactly equal
		}
		return (expressionLength == aString.length());
	}

	private void parsePreloadDescription(String aDescription) {
		StringTokenizer st = new StringTokenizer(aDescription);
		String name = st.nextToken();
		if (!name.endsWith("*")) {
			int atPos = name.lastIndexOf('@');
			if(atPos == -1) {
				name = name + "@" + getHap();
			}
		}

		int performativeCount = ACLMessage.getAllPerformativeNames().length;
		boolean[] filter = new boolean[performativeCount];
		boolean initVal = (st.hasMoreTokens() ? false : true);
		for (int i=0; i<performativeCount; i++) {
			filter[i] = initVal;
		}
		while (st.hasMoreTokens())
		{
			int perfIndex = ACLMessage.getInteger(st.nextToken());
			if (perfIndex != -1) {
				filter[perfIndex] = true;
			}
		}
		preload.put(name, filter);
	}

	/**
	 Inner class RequestListenerBehaviour.
	 This behaviour serves requests to start debugging agents.
	 If an agent does not exist it is put into the 
	 preload table so that it will be debugged as soon as it starts.
	 */
	private class RequestListenerBehaviour extends SimpleAchieveREResponder {  	
		private Action requestAction;
		private AgentAction aa;

		RequestListenerBehaviour() {
			// We serve REQUEST messages refering to the JADE Management Ontology
			super(Introspector.this, MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),MessageTemplate.MatchOntology(JADEManagementOntology.NAME)));
		}

		protected ACLMessage prepareResponse (ACLMessage request) {
			ACLMessage response = request.createReply();
			try {			
				requestAction = (Action) getContentManager().extractContent(request);
				aa = (AgentAction) requestAction.getAction();
				if (aa instanceof DebugOn || aa instanceof DebugOff) {
					if (getAID().equals(requestAction.getActor())) {
						response.setPerformative(ACLMessage.AGREE);
						response.setContent(request.getContent());
					}
					else {
						response.setPerformative(ACLMessage.REFUSE);
						response.setContent("((unrecognised-parameter-value actor "+requestAction.getActor()+"))");
					}
				}
				else {
					response.setPerformative(ACLMessage.REFUSE);	
					response.setContent("((unsupported-act "+aa.getClass().getName()+"))");
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
				response.setPerformative(ACLMessage.NOT_UNDERSTOOD);	
			}
			return response;					    		    		 	
		}

		protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) {	
			if (aa instanceof DebugOn) { 
				// DEBUG ON
				DebugOn requestDebugOn = (DebugOn) aa;
				// Start debugging existing agents.
				// Put non existing agents in the preload map. We will start
				// debug them as soon as they start. 
				List agentsToDebug = requestDebugOn.getCloneOfDebuggedAgents();											
				for (int i=0;i<agentsToDebug.size();i++) {
					AID aid = (AID)agentsToDebug.get(i); 
					if (allAgents.contains(aid)) {
						addAgent(aid);
					} 
					else {
						//not alive -> put it into preload
						int performativeCount = ACLMessage.getAllPerformativeNames().length;
						boolean[] filter = new boolean[performativeCount];
						for (int j=0; j<performativeCount;j++) {
							filter[j] = true;
						}
						preload.put(aid.getName(), filter);									
					}
				}																		
			}
			else {
				// DEBUG OFF
				DebugOff requestDebugOff = (DebugOff) aa;
				List agentsToDebug = requestDebugOff.getCloneOfDebuggedAgents();											
				for (int i=0;i<agentsToDebug.size();i++) {
					AID aid = (AID)agentsToDebug.get(i); 
					removeAgent(aid);
				}
			}

			// Send back the notification
			ACLMessage result = request.createReply();
			result.setPerformative(ACLMessage.INFORM);
			Done d = new Done(requestAction);
			try {
				myAgent.getContentManager().fillContent(result, d);
			}
			catch (Exception e) {
				// Should never happen
				e.printStackTrace();
			}
			return result;
		}		
	}  // END of inner class RequestListenerBehaviour 

	/**
	 Inner class RequestListenerBehaviour.
	 This behaviour serves requests to start debugging agents.
	 If an agent does not exist it is put into the 
	 preload table so that it will be debugged as soon as it starts.
	 */
	private class BehaviorInformationRequestListenerBehaviour extends SimpleAchieveREResponder {  	
		private Action requestAction;
		private AgentAction aa;

		BehaviorInformationRequestListenerBehaviour() {
			// We serve REQUEST messages refering to the JADE Management Ontology
			super(Introspector.this, MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),MessageTemplate.MatchOntology(JADEManagementOntology.NAME)));
		}

		protected ACLMessage prepareResponse (ACLMessage request) {
			ACLMessage response = request.createReply();
			try {			
				requestAction = (Action) getContentManager().extractContent(request);
				aa = (AgentAction) requestAction.getAction();
				if (aa instanceof DebugOn) {
					if (getAID().equals(requestAction.getActor())) {
						response.setPerformative(ACLMessage.INFORM);					
						Object cbehaviors = getKnownBehaviors(requestAction.getActor().getName());
						response.setContent(cbehaviors.toString());
					}
					else {
						response.setPerformative(ACLMessage.REFUSE);
						response.setContent("((unrecognised-parameter-value actor "+requestAction.getActor()+"))");
					}
				}
				else {
					response.setPerformative(ACLMessage.REFUSE);	
					response.setContent("((unsupported-act "+aa.getClass().getName()+"))");
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
				response.setPerformative(ACLMessage.NOT_UNDERSTOOD);	
			}
			return response;					    		    		 	
		}

		protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) {	
			if (aa instanceof DebugOn) { 
				// DEBUG ON
				DebugOn requestDebugOn = (DebugOn) aa;
				// Start debugging existing agents.
				// Put non existing agents in the preload map. We will start
				// debug them as soon as they start. 
				List agentsToDebug = requestDebugOn.getCloneOfDebuggedAgents();											
				for (int i=0;i<agentsToDebug.size();i++) {
					AID aid = (AID)agentsToDebug.get(i); 
					if (allAgents.contains(aid)) {
						addAgent(aid);
					} 
					else {
						//not alive -> put it into preload
						int performativeCount = ACLMessage.getAllPerformativeNames().length;
						boolean[] filter = new boolean[performativeCount];
						for (int j=0; j<performativeCount;j++) {
							filter[j] = true;
						}
						preload.put(aid.getName(), filter);									
					}
				}																		
			}
			else {
				// DEBUG OFF
				DebugOff requestDebugOff = (DebugOff) aa;
				List agentsToDebug = requestDebugOff.getCloneOfDebuggedAgents();											
				for (int i=0;i<agentsToDebug.size();i++) {
					AID aid = (AID)agentsToDebug.get(i); 
					removeAgent(aid);
				}
			}

			// Send back the notification
			ACLMessage result = request.createReply();
			result.setPerformative(ACLMessage.INFORM);
			Done d = new Done(requestAction);
			try {
				myAgent.getContentManager().fillContent(result, d);
			}
			catch (Exception e) {
				// Should never happen
				e.printStackTrace();
			}
			return result;
		}		
	}  // END of inner class RequestListenerBehaviour 

	public Object getKnownBehaviors(String agentName) {
		// TODO Auto-generated method stub
		return null;
	}
}