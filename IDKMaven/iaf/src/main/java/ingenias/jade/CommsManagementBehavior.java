package ingenias.jade;

import ingenias.editor.entities.FrameFact;
import ingenias.editor.entities.Interaction;
import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.exception.InvalidEntity;
import ingenias.exception.NotFound;
import ingenias.jade.comm.ActiveConversation;
import ingenias.jade.comm.StateBehavior;
import ingenias.jade.comm.StateBehaviorChangesListener;
import ingenias.jade.exception.NoAgentsFound;
import ingenias.jade.graphics.MainInteractionManager;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class CommsManagementBehavior extends CyclicBehaviour {


	int cycledTimes=0;

	JADEAgent ja=null;



	private StateBehaviorChangesListener behaviorChangesListener;

	public CommsManagementBehavior(JADEAgent a, StateBehaviorChangesListener behaviorChangesListener) {
		super(a);
		ja=(JADEAgent)myAgent;
		this.behaviorChangesListener=behaviorChangesListener;
	}

	Date last=new Date();
	private boolean doSomething=false;
	public void action() {
		/*cycledTimes=(cycledTimes+1)%maxCyclesWithoutGarbageCollection;
		if (cycledTimes==0)
			System.gc();*/
		//System.err.println("----------------Execution------------"+myAgent.getCurQueueSize());

		// If there are finished protocols, then those are removed
		//System.out.println(ja.getAID().getLocalName()+" starting another protocol handling round");

		ja.getCM().removedFinishedProtocols();
		determineMessagesWhichCanBeProcessed();
		processNotProcessedMessages();
		ja.getCM().launchScheduledProtocols();
		addActiveMachinesAListener();

		/*	if (completedLast
				&& 
				(ja.getMSP().getScheduledTasks().size()!=0 
						|| ja.getMSM().modifiedSinceLastLecture())){
			completedLast=false; // This forces a new planning

		}*/				
		this.block();
		//System.out.println(ja.getAID().getLocalName()+" finishing");
		//System.err.println("----------------End Execution------------"+myAgent.getCurQueueSize());
	}



	private synchronized void addActiveMachinesAListener() {
		Hashtable<ingenias.jade.comm.Pair, StateBehavior> machines = ja.getCM().getActiveMachines();
		for (StateBehavior sb:machines.values()){
			sb.addListener(behaviorChangesListener);
		}
	}



	/**
	 * It removes message processing behaviors that have already
	 * finished. A message is processed if there is an existing conversation
	 * with the same id of the message. These kind of messages are posted again
	 * to the JADE message queue so that they can be received by the different
	 * existing state behaviors instances. When the message has not been processed, it 
	 * means that the agent should initiate an state behavior to further process it.
	 * 
	 */  

	private void determineMessagesWhichCanBeProcessed() {

		if (myAgent.getCurQueueSize() > 0) {
			HashSet<ACLMessage> processed=new HashSet<ACLMessage>(); // to store processed acl
			JADEAgent ja = (JADEAgent) myAgent;
			int size=myAgent.getCurQueueSize();
			for (int k = 0; k < size; k++) {
				final ACLMessage acl = myAgent.receive();
				if (acl!=null){// the message can have been processed by other behavior
					String requestedRole=acl.getUserDefinedParameter("requestedRole");
					String aclSummary=aclSummary(acl);

					if ((ja.getCM().contains(acl.getConversationId(),requestedRole) &&
							ja.getCM().isKnownProtocol(acl.getProtocol()))) {
						// if the message can be processed by some existing interaction
						// and the protocol is known, the message will be send back to 
						// the message queue

						//System.err.println("Added a processed message "+acl);
						processed.add(acl);
						EventManager.getInstance().processingReceivedMessage(ja.getLocalName(), ja.getClass().getName().substring(0, ja.getClass().getName().indexOf("JADE")),acl);

					}
					else {	
						//System.err.println("Not processed message1 "+acl);	
						// if the message  cannot be processed by any existing interaction
						// but there is an interaction that could understand it, a new conversation
						// would be launched
						if (ja.getCM().isKnownProtocol(acl.getProtocol()))
							ja.getCM().addPending(acl);
						else{
							EventManager.getInstance().dontKnowHowToProcessReceivedMessage(ja.getLocalName(), ja.getClass().getName().substring(0, ja.getClass().getName().indexOf("JADE")),acl);
							if (acl.getSender().getLocalName().equalsIgnoreCase("df")||
									acl.getSender().getLocalName().equalsIgnoreCase("ams")||
									acl.getSender().getLocalName().equalsIgnoreCase("rma")){
								ja.putBack(acl);// Some messages are to be processed by internal
								// components, like the DF service. Search consults to the DF requires
								// admiting this kind of messages.
								//	System.err.println("Putting back "+acl);
							} else
								System.err.println("Rejected "+acl);
						}
					}
				}
			}	          

			// To put back messages that can be processed by other behaviors
			Iterator it=processed.iterator();
			while (it.hasNext()){
				ACLMessage nextM=(ACLMessage) it.next();
				//     System.out.println("posting "+aclSummary(nextM));
				ja.postMessage(nextM);// It has to be posted because, that way, all behaviors are waked up
			}

			//ja.getMSP().wakeup();
		}
	}

	public String aclSummary(ACLMessage acl) {
		String cobj="";
		try {
			cobj = acl.getContentObject().toString();
			cobj=cobj.substring(0,Math.min(cobj.length(),1000));
		} catch (UnreadableException e) {

		}

		return "convid:"+acl.getConversationId()+ " protocol:"+
		acl.getProtocol()+ " sender:"+acl.getSender()+
		" state:"+acl.getUserDefinedParameter("sequence")
		+" requestedRole:"+acl.getUserDefinedParameter("requestedRole")
		+" content:"+cobj+" attrs:";
	}

	/**
	 * This behavior checks unprocessed messages and starts new behaviors
	 * able to process them. Non-processed messages are those not having
	 * a running state behavior instance to process them. We know if they
	 * are processed or not by their id. Should there be a state behavior
	 * instance representing a conversation with the same id of the agent,
	 * then we consider the message processed. 
	 * To start a new state behavior, first we need to determine if the agent
	 * can handle it. This is done with isKnownProtocol method. If it is known,
	 * we proceed to start a new state machine playing a collaborator role
	 * with the method launchAsCollaborator. When the method finishes, we create
	 * a new mental state entity, a conversation, so that tasks can access
	 * to run time information of the interaction. Once create the new state behavior,
	 * the message is no more un-processed, so we return the message to the message
	 * queue.
	 *  
	 * 
	 */                                                                                                                                                                                                                    

	private void processNotProcessedMessages() {
		Enumeration enumeration=ja.getCM().getPendingMessages();                                                                                                                                                                                                   
		Vector processed=new Vector();      
		JADEAgent ja = (JADEAgent) myAgent;
		while (enumeration.hasMoreElements()){                                                                                                                                                                                                          
			ACLMessage acl=(ACLMessage)enumeration.nextElement();
			//System.err.println("PRocesando .... "+acl);
			String requestedRole=acl.getUserDefinedParameter("requestedRole");
			String playedRole=acl.getUserDefinedParameter("senderPlayedRole");
			String sequence=acl.getUserDefinedParameter("sequence");
			if (ja.getCM().isKnownProtocol(acl.getProtocol()) 
					&& 
					!ja.getCM().contains(acl.getConversationId(),requestedRole) ){      
				if (acl.getSender().getLocalName().equalsIgnoreCase("ams")){
					// it is a failure message sent by the AMS
					try {
						ingenias.editor.entities.RuntimeCommFailure failure=null;						
						RuntimeConversation conv=ja.getCM().getConversation(
								acl.getConversationId(), playedRole);
						StateBehavior machine = ja.getCM().getStateMachine(conv);

						String failureid=MentalStateManager.generateMentalEntityID()+"ErrorComm";
						if (machine!=null){ // ams is referring to a unknown conversation following a known protocol
						 failure=machine.createFailure(failureid);
						}	
						if (failure==null) {// no predefined failures 
							failure=
							new ingenias.editor.entities.RuntimeCommFailure(failureid);
							failure.setStage("any");
						}
						if (acl.getContent().toLowerCase().contains("agent not found"))
							failure.setFailureType("agentdoesnotexist");
						else
							failure.setFailureType("unknown");
						failure.setStage(acl.getUserDefinedParameter("sequence"));
						failure.setMessage(acl.toString());
						Vector<MentalEntity> failures=new Vector<MentalEntity>();
						failures.add(failure);
						ja.getMSM().addMentalEntityToConversation(conv, failures);
						ja.getMSM().setModified();
						ja.getCM().removePending(acl);   
						System.err.println(acl);
						// the message is not reposted because there are no other protocols
						// responsible of processing it, like the other messages.
					} catch (InvalidEntity e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

				} else {
					boolean initialised=false;                                                                                                                                                                                                                  
					while (!initialised){                                                                                                                                                                                                                       
						try {                                                                                                                                                                                                                                       
							try {			
								ja.getMSM().findEntity(requestedRole+"-"+acl.getConversationId());
								EventManager.getInstance().alreadyHadAConversationCreatedForThatMesssage(ja.getLocalName(), ja.getClass().getName().substring(0, ja.getClass().getName().indexOf("JADE")),acl);

							}  catch (NotFound e) {			
								EventManager.getInstance().startingInteractionAsCollaborator(ja.getLocalName(), ja.getClass().getName().substring(0, ja.getClass().getName().indexOf("JADE")),acl);

								try {
									ActiveConversation actconv=ja.getCM().launchAsCollaborator(
											acl.getProtocol(),
											requestedRole,
											acl.getConversationId(),
											null);
									actconv.getSb().addListener(behaviorChangesListener);

									RuntimeConversation conv=actconv.getConv();
									ja.getCM().addCID(acl.getConversationId(),requestedRole);   
								} catch (WrongInteraction wi){
									System.err.println("The message cannot be processed "+acl);
									wi.printStackTrace();
								}
							}
							ja.getCM().removePending(acl);   
							processed.add(acl);  // to make the message available for the target protocol
							initialised=true;                                                                                                                                                                                                                      

						} catch (NoAgentsFound nf) {                                                                                                                                                                                                                
							try {
								Thread.sleep((long)(200*Math.random()));                                                                                                                                                                                                                 
							} catch (Exception e) {} ;
						}
					}
				}
			} else {
				if (ja.getCM().contains(acl.getConversationId(),requestedRole)){
					ja.getCM().removePending(acl);
					processed.add(acl);  
				} else{
					EventManager.getInstance().couldNotProcessMessageBecauseAgentDoesNotPlayRequestedRole(
							ja.getLocalName(), ja.getClass().getName().substring(0, ja.getClass().getName().indexOf("JADE")),
							acl,requestedRole);

				}

			}
		}                                                                                                                                                                                                                                               
		for (int k=0;k<processed.size();k++){
			//  System.out.println("posting1 "+aclSummary(((ACLMessage)processed.elementAt(k))));
			myAgent.postMessage(((ACLMessage)processed.elementAt(k)));                                                                                                                                                                                      
		}
	}   

}
