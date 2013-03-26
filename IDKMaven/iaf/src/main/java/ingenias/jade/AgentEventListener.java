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

import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.jade.comm.ActiveConversation;
import ingenias.jade.comm.StateBehavior;
import ingenias.jade.components.Task;
import jade.lang.acl.ACLMessage;
import java.util.Vector;

public interface AgentEventListener {

	public void startingTaskExecution(String agentid, String agentType, Task task);
	public void taskExecutionFinished(String agentid, String agentType, Task task);
	
	public void consumedEntityDuringtaskExecutionFinished(String agentid, String agentType, Task task, MentalEntity entity);
	public void consumedEntityDuringtaskExecutionFinishedFromConversation(String agentid, String agentType, Task task, MentalEntity entity, RuntimeConversation conversation);

	public void abortedTaskDueToMissingItems(String agentid,  String agentType,
			Task task, MentalEntity[] missingInputType);
	public void abortedTaskDueToLockedItems(String agentid, String agentType,
			Task task, MentalEntity[] missingInputType);
	public void abortedTaskDueToMissingItemsInConversation(String agentid, String agentType, 
			Task task, MentalEntity[] missingInputType);
	
	public void processingReceivedMessage(String agentid, String agentType, ACLMessage acl);
	public void receivedMessageToStartACollaboration(String agentid,
			String agenttype, ACLMessage message, String protocol);
	public void receivedMessageInSequence(String agentid, String agentType,
			ACLMessage message);
	public void dontKnowHowToProcessReceivedMessage(String agentid, String agentType, ACLMessage acl);
	public void alreadyHadAConversationCreatedForThatMesssage(String agentid, String agentType, ACLMessage acl); 
	public void startingInteractionAsCollaborator(String agentid, String agentType, ACLMessage acl);
	public void couldNotProcessMessageBecauseAgentDoesNotPlayRequestedRole(
			String agentid, String agenttype, ACLMessage acl,
			String requestedRole) ;

	public void messageDelivered(String agentid, String agenttype,
			ACLMessage message2BSend);
	public void waitingForNextMessageInSequence(String agentid,
			String agentype, String conversationID, String expectedNextSequenceState); 
	
	public void currentScheduledTasks(String agentid, String agentType, Task[] task);
	
	public void automaticallyScheduledTask(String agentid, String agentType, Task task);
	public void manuallyScheduledTask(String agentid, String agentType, Task task);
	public void scheduledTask(String agentid, String agentType, Task task);
	
	
	public void addedNewEntityToMS(String agentid, String agentType,MentalEntity entityType );
	public void addedNewEntityToMS(String agentid, String agentType,Task t,MentalEntity entityType );

	public void producedAConversation(String agentid, String agentType,Task task, RuntimeConversation conv );

	public void failedToFindColaboratorsWhenManuallyInitializingAConversation(
			String agentid, String agentType, Task task,
			String interactionType, String collaboratorNotFound);
	
	public void removeEntityFromMS(String agentid, String agentType,
			 MentalEntity entity);
	public void removeEntityFromMSByTask(String agentid, String agentType,
			Task task, MentalEntity entity);
	
	public void addedNewEntityToConversation(String agentid, String agentType,Task task, MentalEntity entity, RuntimeConversation conversation);
	public void removeEntityFromConversation(String agentid, String agentType,
			Task task,MentalEntity entity, RuntimeConversation conversation);
	
	public void conversationalInitializationOfTaskFailed(String agentid, String agentType, Task task, String[] missingInputType);
	public void nonConversationalInitializationOfTaskFailed(String agentid, String agentType, Task task, String[] missingInputType);
	
	public void notScheduledTaskDueToMissingItems(String agentid, String agentType, Task task, MentalEntity[] missingInputEntities);
	public void notScheduledTaskDueToLockedItems(String agentid, String agentType, Task task, MentalEntity[] lockedInputEntityIds);
	public void startingInteractionAsInitiator(String localName,String substring, ActiveConversation aconv);
	public void addedNewEntityToConversationFromCommBehavior(String agentid, String agentType,
			StateBehavior commBehavior,MentalEntity entity, RuntimeConversation conversation);

    public void removedDeletionLock(MentalEntity element, Vector<MentalEntity> locked, String aname, String atype);

    public void addDeletionLock(MentalEntity entity, Vector<MentalEntity> cannotBeDeleted, String aname, String atype);

    public void addDeletionLockToType(String type, Vector<String> cannotBeDeleted, String aname, String atype);
	
}
