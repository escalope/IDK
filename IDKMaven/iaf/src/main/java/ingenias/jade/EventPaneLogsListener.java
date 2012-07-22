package ingenias.jade;

import ingenias.editor.entities.Conversation;
import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.jade.comm.ActiveConversation;
import ingenias.jade.comm.StateBehavior;
import ingenias.jade.components.Task;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventPaneLogsListener implements AgentEventListener {

	private EventPanelLogger logger;

	public EventPaneLogsListener(EventPanelLogger logger) {
		this.logger=logger;
	}
	

	public void logInteraction(final String message, final String agent, String cid){
		logger.log(message,agent+"-"+cid);
	}

	public void logMSP(final String message, final String agent){
		logger.log(message,agent+".MSP");
	}

	public void logMSP(final String message, final String agent, String taskid, String taskType){
		logger.log(message,agent+".MSP-"+taskid+":"+taskType);
	}

	public void logMSM(final String message, final String agent){
		logger.log(message,agent+".MSM");
	}


	@Override
	public void abortedTaskDueToLockedItems(String agentid, String agentType,
			Task task, MentalEntity[] lockedInputEntities) {
		StringBuffer lockedEntitiesSummary=new StringBuffer();
		for (int j=0;j<lockedInputEntities.length;j++){
			lockedEntitiesSummary.append(lockedInputEntities[j].toString()+",");
		}
		logMSP("Aborted task due to locked items:"+lockedEntitiesSummary,agentid,task.getID(),task.getType());
	}

	@Override
	public void abortedTaskDueToMissingItems(String agentid, String agentType,
			Task task, MentalEntity[] missingInputType)  {
		StringBuffer missingEntitiesSummary=new StringBuffer();
		for (int j=0;j<missingInputType.length;j++){
			missingEntitiesSummary.append(missingInputType[j].toString()+",");
		}
		logMSP("Aborted task due to missing items:"+missingEntitiesSummary,agentid,task.getID(),task.getType());

	}

	@Override
	public void abortedTaskDueToMissingItemsInConversation(String agentid,
			String agentType, Task task, MentalEntity[] missingInputType) {
		StringBuffer missingEntitiesSummary=new StringBuffer();
		for (int j=0;j<missingInputType.length;j++){
			missingEntitiesSummary.append(missingInputType[j].toString()+",");
		}
		logMSP("Aborted task due to missing items:"+missingEntitiesSummary+ " in conversation "+task.getConversationContext().ConversationID+":"+task.getConversationContext().getType(),agentid,task.getID(),task.getType());

	}

	@Override
	public void addedNewEntityToConversation(String agentid, String agentType,
			Task t, MentalEntity newEntity, RuntimeConversation conversation){
		logMSP("Produced entity "+newEntity.getId()+":"+newEntity.getType()+" and stored within conversation "+
                        conversation.getPlayedRole()+"-"+conversation.getConversationID(),agentid,t.getID(),t.getType());
		
	}

	@Override
	public void addedNewEntityToMS(String agentid, String agentType,
			MentalEntity me) {
		logMSM(
				"Added to mental state entity " + me.getId()
				+ " of type " + me.getType(), agentid);
	}

	@Override
	public void addedNewEntityToMS(String agentid, String agentType,
			Task t,MentalEntity me) {
		logMSP("Produced entity "+me.getId()+":"+me.getType(),agentid,t.getID(),t.getType());		
	}


	@Override
	public void automaticallyScheduledTask(String agentid, String agentType,
			Task task) {
		logMSP("Scheduled task "+task.getID()+":"+task.getType(),
				agentid,task.getID(),task.getType());
	}

	@Override
	public void consumedEntityDuringtaskExecutionFinishedFromConversation(
			String agentid, String agentType, 
			Task task, 
			MentalEntity consumedFact, 
			RuntimeConversation conversation){
		logMSP("Consumed fact "+consumedFact.getId()+
				":"+consumedFact.getType()+" from conversation "+
				conversation.getPlayedRole()+"-"+conversation.getId(),
				agentid,
				task.getID(),
				task.getType());
		
	}

	@Override
	public void consumedEntityDuringtaskExecutionFinished(
			String agentid, String agentType,
			Task task, 
			MentalEntity consumedFact){
		logMSP("Consumed information of type "+
				consumedFact.getId()+":"+consumedFact.getType()+" from mental state",agentid,task.getID(),task.getType());
	}

	@Override
	public void conversationalInitializationOfTaskFailed(String agentid,
			String agentType, Task task,
			String[] missingInputType) {
		StringBuffer nonexisting=new StringBuffer();
		for (int j=0;j<missingInputType.length;j++){
			nonexisting.append(missingInputType[j].toString()+",");
		}
		logMSP("Conversational intialisation discarded task "+task.getType()+
				" to achieve goal Collaborate because did not have all preconditions. " +
				"Missing elements are:"+nonexisting+"\n",agentid);

	}

	@Override
	public void currentScheduledTasks(String agentid, String agentType,
			Task[] tasks) {
		StringBuffer taskList=new StringBuffer();
		for (int j=0;j<tasks.length;j++){
			taskList.append(tasks[j].getID()+":"+tasks[j].getType()+",");
		}
		logMSP("Currently scheduled tasks: "+taskList,agentid);
	}

	@Override
	public void dontKnowHowToProcessReceivedMessage(String agentid,
			String agentType,ACLMessage acl) {
       
        	String contentToString=acl.getContent();
            logMSP("Cannot process  message submitted by " + acl.getSender().getLocalName() + " with content " + contentToString.substring(0, Math.min(contentToString.length(), 100)), agentid);
        
	}

	@Override
	public void failedToFindColaboratorsWhenManuallyInitializingAConversation(
			String agentid, String agentType,Task task,
			String interactionType, String collaboratorNotFound) {
		logMSP("ERROR intializing manually protocol "+
				interactionType+". Not all required collaborators" +
				" where defined. Colaborators not found "+collaboratorNotFound+
				". Please, review code for task "+task.getID(),agentid);
		logMSP("ERROR intializing manually protocol "+
				interactionType+". Not all required collaborators" +
				" where defined. Colaborators not found "+collaboratorNotFound+
				". Please, review code for task "+task.getID(),agentid,task.getID(),task.getType());

	}

	@Override
	public void manuallyScheduledTask(String agentid, String agentType,
			Task task) {	
		logMSP("Queuing task "+task.getID()+" in the manual queue",agentid,task.getID(), task.getType());
	}

	@Override
	public void nonConversationalInitializationOfTaskFailed(String agentid,
			String agentType, Task task,
			String[] missingInputType) {

		StringBuffer nonexisting=new StringBuffer();
		for (int j=0;j<missingInputType.length;j++){
			nonexisting.append(missingInputType[j]+",");
		}
		logger.log("Non conversational initialisation discarded task "+task.getType()+
				" because did not have all preconditions. " +
				"Missing elements are:"+nonexisting,agentid+"-"+task.getType());
	}

	@Override
	public void notScheduledTaskDueToLockedItems(String agentid, String agentType, 
			Task task, MentalEntity[] lockedInputEntities) {
		StringBuffer lockedItems=new StringBuffer();
		for (int j=0;j<lockedInputEntities.length;j++){
			lockedItems.append(lockedInputEntities[j].getId()+":"+lockedInputEntities[j].getType()+",");
		}
		logMSP("Not considering task "+task.getID()+" because there are locks on required inputs, concretely "+lockedItems+"  are locked. Involved interaction must finish first.",
				agentid,task.getID(),task.getType());
		logMSP("Not considering task "+task.getID()+
				" because there are locks on required inputs, concretely "+lockedItems+"  are locked. Involved interaction must finish first.",
				agentid);
	}

	@Override
	public void notScheduledTaskDueToMissingItems(String agentid,
			String agentType, Task task, MentalEntity[] lockedInputEntities) {
		StringBuffer missingItems=new StringBuffer();
		for (int j=0;j<lockedInputEntities.length;j++){
			missingItems.append(lockedInputEntities[j].getId()+":"+lockedInputEntities[j].getType()+",");
		}
		logMSP("Not considering task "+task.getID()+" because there are missing inputs, concretely "+missingItems+"  are locked. Involved interaction must finish first.",
				agentid,task.getID(),task.getType());
		logMSP("Not considering task "+task.getID()+
				" because there are missing inputs, concretely "+missingItems+"  are locked. Involved interaction must finish first.",
				agentid);

	}


	@Override
	public void producedAConversation(String agentid, String agentType,
			Task task,RuntimeConversation conversation) {
		logMSP("Produced conversation "+conversation.getInteraction().getId(),agentid,
				task.getID(),task.getType());

	}

	@Override
	public void removeEntityFromConversation(String agentid, String agentType,
			Task task,MentalEntity entity, RuntimeConversation conversation) {
		logMSP("Produced conversation "+conversation.getInteraction().getId(),agentid,
				task.getID(),task.getType());

	}

	@Override
	public void removeEntityFromMSByTask(String agentid, String agentType,
			Task task, MentalEntity entity){
		logMSP("Requested removal of entity "+entity.getId()+":"+entity.getType(),agentid,
				task.getID(),task.getType());
	}
	
	@Override
	public void removeEntityFromMS(String agentid, String agentType,
			 MentalEntity entity){
		logMSM("Requested removal of entity "+entity.getId()+":"+entity.getType(),agentid);
	}

	@Override
	public void startingTaskExecution(String agentid, String agentType,
			Task task) {
		// TODO Auto-generated method stub
		logMSP("Executing task "+task.getID(),agentid,task.getID(),task.getType());
		logMSP("Executing task "+task.getID(),agentid);			
		logMSP("Executing task "+task.getID(),agentid, task.getID(), task.getType());
		if (task.getConversationContext()!=null){		
		Conversation conversation=task.getConversationContext();
		logInteraction("Starting task "+task.getID()+":"+task.getType()+" in the context of this conversation "+conversation.getConversationID()
				,agentid,conversation.getPlayedRole()+"-"+conversation.getConversationID());
		}
	}

	@Override
	public void taskExecutionFinished(String agentid, String agentType,
			Task task) {
		logMSP("Task completed "+task.getID(),agentid,task.getID(),task.getType());
		if (task.getConversationContext()!=null){		
			Conversation conversation=task.getConversationContext();
			logInteraction("Finished task "+task.getID()+":"+task.getType()+" in the context of this conversation "+conversation.getConversationID()
					,agentid,conversation.getPlayedRole()+"-"+conversation.getConversationID());
			}
	}

	@Override
	public void processingReceivedMessage(String agentid, String agentType,
			jade.lang.acl.ACLMessage acl) {
     
            logInteraction("Processing message with content " + acl.getContent().toString().substring(0, Math.min(acl.getContent().toString().length(), 100)), agentid, acl.getConversationId());
     
	}

	@Override
	public void alreadyHadAConversationCreatedForThatMesssage(String agentid,
			String agentType, jade.lang.acl.ACLMessage acl) {
		logInteraction("Already had that conversation. " +
				"Error in processing message. ",agentid,"IgnoredMessages");
	}

	@Override
	public void startingInteractionAsCollaborator(String agentid,
			String agentType, jade.lang.acl.ACLMessage acl) {
		logInteraction("Starting as collaborator interaction playing role "+acl.getUserDefinedParameter("requestedRole"),agentid,

                        acl.getUserDefinedParameter("requestedRole")+"-"+acl.getConversationId());
	}

	@Override
	public void couldNotProcessMessageBecauseAgentDoesNotPlayRequestedRole(
			String agentid, String agentType, ACLMessage acl,
			String requestedRole) {
		logMSP("Could not process a message because this agent does not play role "+requestedRole+
				". The message is "+acl,agentid);

	}
	
	@Override
	public void receivedMessageToStartACollaboration(String agentid,
			String agenttype, ACLMessage message, String protocol) {
		logInteraction("Received messages to start a collaboration of type "+protocol+ " playing the role "+
                        message.getUserDefinedParameter("requestedRole")+ " from "+
				message.getSender().getLocalName(),agentid,protocol);


	}
	
	@Override
	public void receivedMessageInSequence(String agentid, String agentType,
			ACLMessage message) {
		logInteraction("Received valid message for "+message.getConversationId()+
				" from "+
				message.getSender().getLocalName(),agentid,

                                 message.getUserDefinedParameter("requestedRole")+"-"+message.getConversationId());
	}

	@Override
	public void waitingForNextMessageInSequence(String agentid,
			String agentType, String CID, String seqcode) {
		logInteraction("Waiting for "+CID+"("+seqcode+")",agentid,CID);
	}

	@Override
	public void messageDelivered(String agentid, String agenttype,
			ACLMessage message2BSend) {
		logInteraction("Sending "+message2BSend.getUserDefinedParameter("sequence")+" and asking for a role "
				+message2BSend.getUserDefinedParameter("requestedRole")+
				message2BSend.toString(),agentid,message2BSend.getConversationId());

	}
	
	@Override
	public void startingInteractionAsInitiator(String agentid, String agenttype,
			ActiveConversation aconv) {
		logInteraction("Protocol "+aconv.getConv().getInteraction().getId()+
				" initialised", agentid, aconv.getCid());

	}


	@Override
	public void scheduledTask(String agentid, String agentType, Task task) {
		logMSP("Scheduled task  "+task.getID()+":"+task.getType(),agentid);
	
	}

	@Override
	public void addedNewEntityToConversationFromCommBehavior(String agentid,
			String agentType, StateBehavior commBehavior, MentalEntity entity,
			RuntimeConversation conversation) {
		logMSM("Entity "+entity.getId()+":"+entity.getType()+" added to conversation "+commBehavior.getPlayedRole()+"-"+conversation.getConversationID()+" from com behavior "+commBehavior.getBehaviourName(),agentid);
		logInteraction("Entity "+entity.getId()+":"+entity.getType()+" added to conversation "+conversation.getConversationID()+" from com behavior "+commBehavior.getBehaviourName()
				,agentid,conversation.getPlayedRole()+"-"+conversation.getConversationID());
	}

    @Override
    public void removedDeletionLock(MentalEntity element, Vector<MentalEntity> locked, String aname, String atype) {
     logMSM("Removed "+element+". Current lokcs:"+locked.toString(),aname);
    }

    @Override
    public void addDeletionLock(MentalEntity entity, Vector<MentalEntity> cannotBeDeleted, String aname, String atype) {
      logMSM("Added "+entity+".  Current locks:"+cannotBeDeleted.toString(),
					aname);
    }

    @Override
    public void addDeletionLockToType(String type, Vector<String> cannotBeDeleted, String aname, String atype) {
      logMSM("Added lock to all entities of type "+type+".  Current locked types:"+cannotBeDeleted.toString(),
					aname);
    }

}
