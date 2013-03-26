/*
Copyright (C) 2012 Jorge Gomez Sanz

This file is part of INGENIAS Agent Framework, an agent infrastructure linked
to the INGENIAS Development Kit, and availabe at http://ingenias.sourceforge.net. 

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

import javax.swing.SwingUtilities;

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
	public void abortedTaskDueToLockedItems(final String agentid, String agentType,
			final Task task, final MentalEntity[] lockedInputEntities) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				StringBuffer lockedEntitiesSummary=new StringBuffer();
				for (int j=0;j<lockedInputEntities.length;j++){
					lockedEntitiesSummary.append(lockedInputEntities[j].toString()+",");
				}
				logMSP("Aborted task due to locked items:"+lockedEntitiesSummary,agentid,task.getID(),task.getType());
			}
		});
	}

	@Override
	public void abortedTaskDueToMissingItems(final String agentid, String agentType,
			final Task task, final MentalEntity[] missingInputType)  {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				StringBuffer missingEntitiesSummary=new StringBuffer();
				for (int j=0;j<missingInputType.length;j++){
					missingEntitiesSummary.append(missingInputType[j].toString()+",");
				}
				logMSP("Aborted task due to missing items:"+missingEntitiesSummary,agentid,task.getID(),task.getType());
			}
		});

	}

	@Override
	public void abortedTaskDueToMissingItemsInConversation(final String agentid,
			String agentType, final Task task, final MentalEntity[] missingInputType) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				StringBuffer missingEntitiesSummary=new StringBuffer();
				for (int j=0;j<missingInputType.length;j++){
					missingEntitiesSummary.append(missingInputType[j].toString()+",");
				}
				logMSP("Aborted task due to missing items:"+missingEntitiesSummary+ " in conversation "+task.getConversationContext().ConversationID+":"+task.getConversationContext().getType(),agentid,task.getID(),task.getType());
			}});
	}

	@Override
	public void addedNewEntityToConversation(final String agentid, String agentType,
			final Task t, final MentalEntity newEntity, final RuntimeConversation conversation){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSP("Produced entity "+newEntity.getId()+":"+newEntity.getType()+" and stored within conversation "+
						conversation.getPlayedRole()+"-"+conversation.getConversationID(),agentid,t.getID(),t.getType());
			}});

	}

	@Override
	public void addedNewEntityToMS(final String agentid, String agentType,
			final MentalEntity me) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSM(
						"Added to mental state entity " + me.getId()
						+ " of type " + me.getType(), agentid);
			}});
	}

	@Override
	public void addedNewEntityToMS(final String agentid, String agentType,
			final Task t,final MentalEntity me) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSP("Produced entity "+me.getId()+":"+me.getType(),agentid,t.getID(),t.getType());
			}});
	}


	@Override
	public void automaticallyScheduledTask(final String agentid, String agentType,
			final Task task) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSP("Scheduled task "+task.getID()+":"+task.getType(),
						agentid,task.getID(),task.getType());
			}});
	}

	@Override
	public void consumedEntityDuringtaskExecutionFinishedFromConversation(
			final String agentid, String agentType, 
			final Task task, 
			final MentalEntity consumedFact, 
			final RuntimeConversation conversation){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSP("Consumed fact "+consumedFact.getId()+
						":"+consumedFact.getType()+" from conversation "+
						conversation.getPlayedRole()+"-"+conversation.getId(),
						agentid,
						task.getID(),
						task.getType());
			}});

	}

	@Override
	public void consumedEntityDuringtaskExecutionFinished(
			final String agentid, String agentType,
			final Task task, 
			final MentalEntity consumedFact){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSP("Consumed information of type "+
						consumedFact.getId()+":"+consumedFact.getType()+" from mental state",agentid,task.getID(),task.getType());
			}});
	}


	@Override
	public void conversationalInitializationOfTaskFailed(final String agentid,
			String agentType, final Task task,
			final String[] missingInputType) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				StringBuffer nonexisting=new StringBuffer();
				for (int j=0;j<missingInputType.length;j++){
					nonexisting.append(missingInputType[j].toString()+",");
				}
				logMSP("Conversational intialisation discarded task "+task.getType()+
						" to achieve goal Collaborate because did not have all preconditions. " +
						"Missing elements are:"+nonexisting+"\n",agentid);
			}});

	}

	@Override
	public void currentScheduledTasks(final String agentid, String agentType,
			final Task[] tasks) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				StringBuffer taskList=new StringBuffer();
				for (int j=0;j<tasks.length;j++){
					taskList.append(tasks[j].getID()+":"+tasks[j].getType()+",");
				}
				logMSP("Currently scheduled tasks: "+taskList,agentid);
			}});
	}

	@Override
	public void dontKnowHowToProcessReceivedMessage(final String agentid,
			String agentType,final ACLMessage acl) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				String contentToString=acl.getContent();
				logMSP("Cannot process  message submitted by " + acl.getSender().getLocalName() + 
						" with content " + contentToString.substring(0, Math.min(contentToString.length(), 100)), 
						agentid);
			}});

	}

	@Override
	public void failedToFindColaboratorsWhenManuallyInitializingAConversation(
			final String agentid, String agentType,final Task task,
			final String interactionType, final String collaboratorNotFound) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSP("ERROR intializing manually protocol "+
						interactionType+". Not all required collaborators" +
						" where defined. Colaborators not found "+collaboratorNotFound+
						". Please, review code for task "+task.getID(),agentid);
				logMSP("ERROR intializing manually protocol "+
						interactionType+". Not all required collaborators" +
						" where defined. Colaborators not found "+collaboratorNotFound+
						". Please, review code for task "+task.getID(),agentid,task.getID(),task.getType());
			}});

	}

	@Override
	public void manuallyScheduledTask(final String agentid, String agentType,
			final Task task) {	
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSP("Queuing task "+task.getID()+" in the manual queue",agentid,task.getID(), task.getType());
			}});
	}

	@Override
	public void nonConversationalInitializationOfTaskFailed(final String agentid,
			String agentType, final Task task,
			final String[] missingInputType) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				StringBuffer nonexisting=new StringBuffer();
				for (int j=0;j<missingInputType.length;j++){
					nonexisting.append(missingInputType[j]+",");
				}
				logger.log("Non conversational initialisation discarded task "+task.getType()+
						" because did not have all preconditions. " +
						"Missing elements are:"+nonexisting,agentid+"-"+task.getType());
			}
		});
	}

	@Override
	public void notScheduledTaskDueToLockedItems(final String agentid, String agentType, 
			final Task task, final MentalEntity[] lockedInputEntities) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				StringBuffer lockedItems=new StringBuffer();
				for (int j=0;j<lockedInputEntities.length;j++){
					lockedItems.append(lockedInputEntities[j].getId()+":"+lockedInputEntities[j].getType()+",");
				}
				logMSP("Not considering task "+task.getID()+" because there are locks on required inputs, concretely "+lockedItems+"  are locked. Involved interaction must finish first.",
						agentid,task.getID(),task.getType());
				logMSP("Not considering task "+task.getID()+
						" because there are locks on required inputs, concretely "+lockedItems+"  are locked. Involved interaction must finish first.",
						agentid);
			}});
	}

	@Override
	public void notScheduledTaskDueToMissingItems(final String agentid,
			String agentType, final Task task, final MentalEntity[] lockedInputEntities) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				StringBuffer missingItems=new StringBuffer();
				for (int j=0;j<lockedInputEntities.length;j++){
					missingItems.append(lockedInputEntities[j].getId()+":"+lockedInputEntities[j].getType()+",");
				}
				logMSP("Not considering task "+task.getID()+" because there are missing inputs, concretely "+missingItems+"  are locked. Involved interaction must finish first.",
						agentid,task.getID(),task.getType());
				logMSP("Not considering task "+task.getID()+
						" because there are missing inputs, concretely "+missingItems+"  are locked. Involved interaction must finish first.",
						agentid);
			}});

	}


	@Override
	public void producedAConversation(final String agentid, String agentType,
			final Task task,final RuntimeConversation conversation) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSP("Produced conversation "+conversation.getInteraction().getId(),agentid,
						task.getID(),task.getType());
			}});

	}

	@Override
	public void removeEntityFromConversation(final String agentid, String agentType,
			final Task task,MentalEntity entity, final RuntimeConversation conversation) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSP("Produced conversation "+conversation.getInteraction().getId(),agentid,
						task.getID(),task.getType());
			}});

	}

	@Override
	public void removeEntityFromMSByTask(final String agentid, String agentType,
			final Task task, final MentalEntity entity){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSP("Requested removal of entity "+entity.getId()+":"+entity.getType(),agentid,
						task.getID(),task.getType());
			}});
	}

	@Override
	public void removeEntityFromMS(final String agentid, String agentType,
			final MentalEntity entity){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSM("Requested removal of entity "+entity.getId()+":"+entity.getType(),agentid);
			}});
	}

	@Override
	public void startingTaskExecution(final String agentid, String agentType,
			final Task task) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				// TODO Auto-generated method stub
				logMSP("Executing task "+task.getID(),agentid,task.getID(),task.getType());
				logMSP("Executing task "+task.getID(),agentid);			
				logMSP("Executing task "+task.getID(),agentid, task.getID(), task.getType());
				if (task.getConversationContext()!=null){		
					Conversation conversation=task.getConversationContext();
					logInteraction("Starting task "+task.getID()+":"+task.getType()+" in the context of this conversation "+conversation.getConversationID()
							,agentid,conversation.getPlayedRole()+"-"+conversation.getConversationID());
				}
			}});
	}

	@Override
	public void taskExecutionFinished(final String agentid, String agentType,
			final Task task) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSP("Task completed "+task.getID(),agentid,task.getID(),task.getType());
				if (task.getConversationContext()!=null){		
					Conversation conversation=task.getConversationContext();
					logInteraction("Finished task "+task.getID()+":"+task.getType()+" in the context of this conversation "+conversation.getConversationID()
							,agentid,conversation.getPlayedRole()+"-"+conversation.getConversationID());
				}
			}});
	}

	@Override
	public void processingReceivedMessage(final String agentid, String agentType,
			final jade.lang.acl.ACLMessage acl) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logInteraction("Processing message with content " + acl.getContent().toString().substring(0, Math.min(acl.getContent().toString().length(), 100)), agentid, acl.getConversationId());
			}});

	}

	@Override
	public void alreadyHadAConversationCreatedForThatMesssage(final String agentid,
			String agentType, jade.lang.acl.ACLMessage acl) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logInteraction("Already had that conversation. " +
						"Error in processing message. ",agentid,"IgnoredMessages");
			}});
	}

	@Override
	public void startingInteractionAsCollaborator(final String agentid,
			String agentType, final jade.lang.acl.ACLMessage acl) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logInteraction("Starting as collaborator interaction playing role "+acl.getUserDefinedParameter("requestedRole"),agentid,

						acl.getUserDefinedParameter("requestedRole")+"-"+acl.getConversationId());
			}});
	}

	@Override
	public void couldNotProcessMessageBecauseAgentDoesNotPlayRequestedRole(
			final String agentid, String agentType, final ACLMessage acl,
			final String requestedRole) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSP("Could not process a message because this agent does not play role "+requestedRole+
						". The message is "+acl,agentid);
			}});

	}

	@Override
	public void receivedMessageToStartACollaboration(final String agentid,
			String agenttype, final ACLMessage message, final String protocol) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logInteraction("Received messages to start a collaboration of type "+protocol+ " playing the role "+
						message.getUserDefinedParameter("requestedRole")+ " from "+
						message.getSender().getLocalName(),agentid,protocol);
			}});


	}

	@Override
	public void receivedMessageInSequence(final String agentid, String agentType,
			final ACLMessage message) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logInteraction("Received valid message for "+message.getConversationId()+
						" from "+
						message.getSender().getLocalName(),agentid,

						message.getUserDefinedParameter("requestedRole")+"-"+message.getConversationId());
			}});
	}

	@Override
	public void waitingForNextMessageInSequence(final String agentid,
			String agentType, final String CID, final String seqcode) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logInteraction("Waiting for "+CID+"("+seqcode+")",agentid,CID);
			}});
	}

	@Override
	public void messageDelivered(final String agentid, String agenttype,
			final ACLMessage message2BSend) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logInteraction("Sending "+message2BSend.getUserDefinedParameter("sequence")+" and asking for a role "
						+message2BSend.getUserDefinedParameter("requestedRole")+
						message2BSend.toString(),agentid,message2BSend.getConversationId());
			}});

	}

	@Override
	public void startingInteractionAsInitiator(final String agentid, String agenttype,
			final ActiveConversation aconv) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logInteraction("Protocol "+aconv.getConv().getInteraction().getId()+
						" initialised", agentid, aconv.getCid());
			}});

	}


	@Override
	public void scheduledTask(final String agentid, String agentType, final Task task) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSP("Scheduled task  "+task.getID()+":"+task.getType(),agentid);
			}});

	}

	@Override
	public void addedNewEntityToConversationFromCommBehavior(final String agentid,
			String agentType, final StateBehavior commBehavior, final MentalEntity entity,
			final RuntimeConversation conversation) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSM("Entity "+entity.getId()+":"+entity.getType()+" added to conversation "+commBehavior.getPlayedRole()+"-"+conversation.getConversationID()+" from com behavior "+commBehavior.getBehaviourName(),agentid);
				logInteraction("Entity "+entity.getId()+":"+entity.getType()+" added to conversation "+conversation.getConversationID()+" from com behavior "+commBehavior.getBehaviourName()
						,agentid,conversation.getPlayedRole()+"-"+conversation.getConversationID());
			}});
	}

	@Override
	public void removedDeletionLock(final MentalEntity element, final Vector<MentalEntity> locked, final String aname, String atype) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSM("Removed "+element+". Current lokcs:"+locked.toString(),aname);
			}});
	}

	@Override
	public void addDeletionLock(final MentalEntity entity, final Vector<MentalEntity> cannotBeDeleted, final String aname, String atype) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSM("Added "+entity+".  Current locks:"+cannotBeDeleted.toString(),
						aname);
			}});
	}

	@Override
	public void addDeletionLockToType(final String type, final Vector<String> cannotBeDeleted, final String aname, String atype) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				logMSM("Added lock to all entities of type "+type+".  Current locked types:"+cannotBeDeleted.toString(),
						aname);
			}});
	}

}
