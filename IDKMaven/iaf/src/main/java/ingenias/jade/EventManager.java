package ingenias.jade;

import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.jade.comm.ActiveConversation;
import ingenias.jade.comm.StateBehavior;
import ingenias.jade.components.Task;
import ingenias.jade.graphics.MainInteractionManager;
import jade.lang.acl.ACLMessage;

import java.util.HashSet;
import java.util.Vector;

public class EventManager implements AgentEventListener{

	private static EventManager instance=new EventManager();
	private HashSet<AgentEventListener> listeners=new HashSet<AgentEventListener> ();
	private EventManager(){};

	public static EventManager getInstance(){
		return instance;
	}

	public void register(AgentEventListener listener){
		listeners.add(listener);
	}

	public void unregister(AgentEventListener listener){
		listeners.remove(listener);
	}

	@Override
	public void addedNewEntityToConversation(String agentid, String agentType,
			Task task, MentalEntity entity, RuntimeConversation conversation){
		for (AgentEventListener listener:listeners){
			listener.addedNewEntityToConversation(agentid, agentType,
					task,entity, conversation);
		}	
	}
	
	@Override
	public void addedNewEntityToConversationFromCommBehavior(String agentid, String agentType,
			 StateBehavior commBehavior, MentalEntity entity, RuntimeConversation conversation){
		for (AgentEventListener listener:listeners){
			listener.addedNewEntityToConversationFromCommBehavior(agentid, agentType,
					commBehavior,entity, conversation);
		}	
	}

	@Override
	public void addedNewEntityToMS(String agentid, String agentType,Task t, MentalEntity entityType ){
			
		for (AgentEventListener listener:listeners){
			listener.addedNewEntityToMS(agentid, agentType,t,entityType);
		}	
	}
	
	@Override
	public void addedNewEntityToMS(String agentid, String agentType, MentalEntity entityType ){
			
		for (AgentEventListener listener:listeners){
			listener.addedNewEntityToMS(agentid, agentType,entityType);
		}	
	}

	@Override
	public void taskExecutionFinished(String agentid, String agentType, Task task) {
		for (AgentEventListener listener:listeners){
			listener.taskExecutionFinished(agentid, agentType, task);
		}
	}

	@Override
	public void removeEntityFromConversation(String agentid, String agentType,
			Task task, MentalEntity entity, RuntimeConversation conversation) {
		for (AgentEventListener listener:listeners){
			listener.removeEntityFromConversation(agentid, agentType, task, entity, conversation);
		}
	}
	
	@Override
	public void removeEntityFromMSByTask(String agentid, String agentType,Task task, MentalEntity entity ){
		for (AgentEventListener listener:listeners){
			listener.removeEntityFromMSByTask(agentid, agentType, task, entity);
		}
	}

	@Override
	public void removeEntityFromMS(String agentid, String agentType, MentalEntity entity ){
		for (AgentEventListener listener:listeners){
			listener.removeEntityFromMS(agentid, agentType, entity);
		}
	}

	@Override
	public void abortedTaskDueToLockedItems(String agentid, String agentType,
			Task task, MentalEntity[] missingInputs) {
		for (AgentEventListener listener:listeners){
			listener.abortedTaskDueToLockedItems(agentid, agentType, task, missingInputs);
		}
	}

	@Override
	public void abortedTaskDueToMissingItems(String agentid, String agentType,
			Task task, MentalEntity[] missingInputs)  {
		for (AgentEventListener listener:listeners){
			listener.abortedTaskDueToMissingItems(agentid, agentType, task, missingInputs);
		}
	}

	@Override
	public void abortedTaskDueToMissingItemsInConversation(String agentid,
			String agentType, Task task, MentalEntity[] missingInputs)  {	
		for (AgentEventListener listener:listeners){
			listener.abortedTaskDueToMissingItemsInConversation(agentid, agentType, task, missingInputs);
		}

	}

	@Override
	public void automaticallyScheduledTask(String agentid, String agentType, Task task) {
		for (AgentEventListener listener:listeners){
			listener.automaticallyScheduledTask(agentid, agentType, task);
		}
	}

	@Override
	public void consumedEntityDuringtaskExecutionFinished(String agentid,
			String agentType, Task task, MentalEntity entity){
		for (AgentEventListener listener:listeners){
			listener.consumedEntityDuringtaskExecutionFinished(agentid, agentType, task,entity);
		}
	}
	@Override
	public void consumedEntityDuringtaskExecutionFinishedFromConversation(
			String agentid, String agentType,
			Task task, MentalEntity entity, 
			RuntimeConversation conversation){
		for (AgentEventListener listener:listeners){
			listener.consumedEntityDuringtaskExecutionFinishedFromConversation(agentid, agentType, task, entity,conversation);
		}
	}

	@Override
	public void conversationalInitializationOfTaskFailed(String agentid, String agentType, 
			Task task, String[] missingInputType){
		for (AgentEventListener listener:listeners){
			listener.conversationalInitializationOfTaskFailed(
					agentid, agentType, task, missingInputType);
		}
	}

	@Override
	public void currentScheduledTasks(String agentid, String agentType, Task[] tasks) {
		for (AgentEventListener listener:listeners){
			listener.currentScheduledTasks(agentid, agentType, tasks);
		}
	}



	@Override
	public void failedToFindColaboratorsWhenManuallyInitializingAConversation(
			String agentid, String agentType, Task task,
			String interactionType, String collaboratorNotFound) {

		for (AgentEventListener listener:listeners){
			listener.failedToFindColaboratorsWhenManuallyInitializingAConversation(
					agentid, agentType, task, 
					interactionType, collaboratorNotFound);
		}
	}

	@Override
	public void manuallyScheduledTask(String agentid, String agentType, Task task) {

		for (AgentEventListener listener:listeners){
			listener.manuallyScheduledTask(agentid, agentType, task);
		}
	}

	@Override
	public void nonConversationalInitializationOfTaskFailed(String agentid, String agentType, Task task, 
			String[] missingInputType)
	 {
		for (AgentEventListener listener:listeners){
			listener.nonConversationalInitializationOfTaskFailed(agentid, 
					agentType, task, missingInputType);
		}

	}

	@Override
	public void notScheduledTaskDueToLockedItems(String agentid, 
			String agentType, Task task, 
			MentalEntity[] lockedInputEntities)
 {
		for (AgentEventListener listener:listeners){
			listener.notScheduledTaskDueToLockedItems(agentid, agentType, task,
					lockedInputEntities);
		}
	}

	@Override
	public void notScheduledTaskDueToMissingItems(String agentid, String agentType, Task task, 
			MentalEntity[] missingInputEntities) {
		for (AgentEventListener listener:listeners){
			listener.notScheduledTaskDueToMissingItems(agentid, agentType, task, 
					missingInputEntities);
		}
	}

	@Override
	public void processingReceivedMessage(String agentid, String agentType,
			ACLMessage acl) {
		for (AgentEventListener listener:listeners){
			listener.processingReceivedMessage(agentid, agentType, acl);
		}
	}

	@Override
	public void producedAConversation(String agentid, String agentType,
			Task task,RuntimeConversation conv) {
		for (AgentEventListener listener:listeners){
			listener.producedAConversation(agentid, agentType, task,conv);
		}
	}

	@Override
	public void startingTaskExecution(String agentid, String agentType, Task task){
		for (AgentEventListener listener:listeners){			
			listener.startingTaskExecution(agentid, agentType, task);
		}

	}

	@Override
	public void dontKnowHowToProcessReceivedMessage(String agentid,
			String agentType, ACLMessage acl) {
		for (AgentEventListener listener:listeners){
			listener.dontKnowHowToProcessReceivedMessage(agentid, agentType, acl);
		}

	}

	public void alreadyHadAConversationCreatedForThatMesssage(String agentid,
			String agenttype, ACLMessage acl) {
		for (AgentEventListener listener:listeners){
			listener.alreadyHadAConversationCreatedForThatMesssage(agentid, agenttype, acl);
		}

	}

	public void startingInteractionAsCollaborator(String agentid,
			String agenttype, ACLMessage acl) {
		for (AgentEventListener listener:listeners){
			listener.startingInteractionAsCollaborator(agentid, agenttype, acl);
		}
		
		
		
	}

	public void couldNotProcessMessageBecauseAgentDoesNotPlayRequestedRole(
			String agentid, String agenttype, ACLMessage acl,
			String requestedRole) {
		for (AgentEventListener listener:listeners){
			listener.couldNotProcessMessageBecauseAgentDoesNotPlayRequestedRole(agentid,agenttype, acl, requestedRole);
			}
	}

	public void messageDelivered(String agentid, String agenttype,
			ACLMessage message2BSend) {
		for (AgentEventListener listener:listeners){
			listener.messageDelivered(agentid, agenttype, message2BSend);
			}  
		
	}

	public void waitingForNextMessageInSequence(String agentid,
			String agenttype, String CID, String seqcode) {
		for (AgentEventListener listener:listeners){
			listener.waitingForNextMessageInSequence(agentid,agenttype,CID,seqcode);
			}
        }

	public void receivedMessageInSequence(String agentid, String agentType,
			ACLMessage message) {
		for (AgentEventListener listener:listeners){
			listener.receivedMessageInSequence(agentid, agentType, message);
		}
		
	}

	public void receivedMessageToStartACollaboration(String agentid,
			String agenttype, ACLMessage message, String protocol) {
		for (AgentEventListener listener:listeners){
			listener.receivedMessageToStartACollaboration(agentid, agenttype, message, protocol);
		}
	}

	public void startingInteractionAsInitiator(String agentid,
			String agenttype, ActiveConversation aconv) {
		for (AgentEventListener listener:listeners){
		 listener.startingInteractionAsInitiator(agentid, agenttype, aconv);	
		}
		
	}

	@Override
	public void scheduledTask(String agentid, String agentType, Task task) {
		for (AgentEventListener listener:listeners){
			 listener.scheduledTask(agentid, agentType, task);	
			}
			
	}

    public void removedDeletionLock(MentalEntity element,Vector<MentalEntity> locked, String aname, String atype) {
        for (AgentEventListener listener:listeners){
			 listener.removedDeletionLock(element,locked,aname,atype);
			}
        //throw new UnsupportedOperationException("Not yet implemented");
        //
    }

    public void addDeletionLock(MentalEntity entity, Vector<MentalEntity> cannotBeDeleted, String aname, String atype) {
        for (AgentEventListener listener:listeners){
			 listener.addDeletionLock(entity,cannotBeDeleted,aname,atype);
			}
    }

    public void addDeletionLockToType(String type, Vector<String> cannotBeDeleted, String aname, String atype) {
      for (AgentEventListener listener:listeners){
			 listener.addDeletionLockToType(type,cannotBeDeleted,aname,atype);
			}
    }

}
