/*
    Copyright (C) 2013 Jorge Gomez Sanz

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
package ingenias.testing;

import jade.lang.acl.ACLMessage;
import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.editor.entities.StateGoal;
import ingenias.jade.AgentEventListener;
import ingenias.jade.comm.ActiveConversation;
import ingenias.jade.comm.StateBehavior;
import ingenias.jade.components.Task;
import java.util.Vector;

public class EventSequenceCheckerListener implements AgentEventListener {

	private EventSequenceChecker checker;

	public EventSequenceCheckerListener(
			EventSequenceChecker eventSequenceChecker) {
		this.checker=eventSequenceChecker;
	}

	@Override
	public void abortedTaskDueToLockedItems(String agentid, String agentType,
			Task task, MentalEntity[] missingInputType) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.TaskAborteddueToLockedItems,
				new Task[]{task},missingInputType,
				new StateGoal[]{},new RuntimeConversation[]{},
				null));
	}

	@Override// TODO Auto-generated method stub
	public void abortedTaskDueToMissingItems(String agentid, String agentType,
			Task task, MentalEntity[] missingInputType) {
		checker.notify(new Event(agentid,agentType,
				EventKind.TaskAbortedDueToMissingItems,
				new Task[]{task},missingInputType,
				new StateGoal[]{},new RuntimeConversation[]{},
				null));
	}

	@Override
	public void abortedTaskDueToMissingItemsInConversation(String agentid,
			String agentType, Task task, MentalEntity[] missingInputType) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.TaskAbortedDueToMissingItems,
				new Task[]{task},missingInputType,
				new StateGoal[]{},new RuntimeConversation[]{},
				null));
	}

	@Override
	public void addedNewEntityToConversation(String agentid, String agentType,
			Task task, MentalEntity entity, RuntimeConversation conversation) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.AddedNewEntityToConversation,
				new Task[]{task},new MentalEntity[]{entity},
				new StateGoal[]{},
				new RuntimeConversation[]{conversation},
				null));
	}

	@Override
	public void addedNewEntityToMS(String agentid, String agentType,
			MentalEntity entity) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.AddedNewEntityToMS,
				new Task[]{},new MentalEntity[]{entity},
				new StateGoal[]{},
				new RuntimeConversation[]{},
				null));
	}

	@Override
	public void addedNewEntityToMS(String agentid, String agentType, Task t,
			MentalEntity entity) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.ProducedNewEntityToMS,
				new Task[]{t},new MentalEntity[]{entity},
				new StateGoal[]{},new RuntimeConversation[]{},null));

	}

	@Override
	public void alreadyHadAConversationCreatedForThatMesssage(String agentid,
			String agentType, ACLMessage acl) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.AlreadyHadAConversationCreatedForThatMesssage,
				new Task[]{},new MentalEntity[]{},
				new StateGoal[]{},new RuntimeConversation[]{},
				null));

	}

	@Override
	public void automaticallyScheduledTask(String agentid, String agentType,
			Task task) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.AutomaticallyScheduledTask,
				new Task[]{task},new MentalEntity[]{},
				new StateGoal[]{},new RuntimeConversation[]{},
				null));

	}

	@Override
	public void consumedEntityDuringtaskExecutionFinished(String agentid,
			String agentType, Task task, MentalEntity entity) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.ConsumedEntityDuringtaskExecutionFinished,
				new Task[]{task},new MentalEntity[]{entity},
				new StateGoal[]{},new RuntimeConversation[]{},
				null));

	}

	@Override
	public void consumedEntityDuringtaskExecutionFinishedFromConversation(
			String agentid, String agentType, Task task, MentalEntity entity,
			RuntimeConversation conversation) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.ConsumedEntityDuringtaskExecutionFinishedFromConversation,
				new Task[]{task},new MentalEntity[]{entity},
				new StateGoal[]{},new RuntimeConversation[]{},
				null));
	}

	@Override
	public void conversationalInitializationOfTaskFailed(String agentid,
			String agentType, Task task, String[] missingInputType) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.ConversationalInitializationOfTaskFailed,
				new Task[]{task},new MentalEntity[]{},
				new StateGoal[]{},new RuntimeConversation[]{},
				null));
	}

	@Override
	public void couldNotProcessMessageBecauseAgentDoesNotPlayRequestedRole(
			String agentid, String agenttype, ACLMessage acl,
			String requestedRole) {
		checker.notify(new Event(
				agentid,agenttype,
				EventKind.CouldNotProcessMessageBecauseAgentDoesNotPlayRequestedRole,
				new Task[]{},new MentalEntity[]{},
				new StateGoal[]{},new RuntimeConversation[]{},
				acl));

	}

	@Override
	public void currentScheduledTasks(String agentid, String agentType,
			Task[] tasks) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.CurrentScheduledTasks,
				tasks,new MentalEntity[]{},
				new StateGoal[]{},new RuntimeConversation[]{},
				null));

	}

	@Override
	public void dontKnowHowToProcessReceivedMessage(String agentid,
			String agentType, ACLMessage acl) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.DontKnowHowToProcessReceivedMessage,
				new Task[]{},new MentalEntity[]{},
				new StateGoal[]{},new RuntimeConversation[]{},
				acl));

	}

	@Override
	public void failedToFindColaboratorsWhenManuallyInitializingAConversation(
			String agentid, String agentType, Task task,
			String interactionType, String collaboratorNotFound) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.FailedToFindColaboratorsWhenManuallyInitializingAConversation,
				new Task[]{task},new MentalEntity[]{},
				new StateGoal[]{},new RuntimeConversation[]{},
				null));
	}

	@Override
	public void manuallyScheduledTask(String agentid, String agentType,
			Task task) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.ManuallyScheduledTask,
				new Task[]{task},new MentalEntity[]{},
				new StateGoal[]{},new RuntimeConversation[]{},
				null));

	}

	@Override
	public void messageDelivered(String agentid, String agentType,
			ACLMessage message2BSend) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.MessageDelivered,
				new Task[]{},new MentalEntity[]{},
				new StateGoal[]{},new RuntimeConversation[]{},
				message2BSend));
	}

	@Override
	public void nonConversationalInitializationOfTaskFailed(String agentid,
			String agentType, Task task, String[] missingInputType) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.NonConversationalInitializationOfTaskFailed,
				new Task[]{task},new MentalEntity[]{},
				new StateGoal[]{},new RuntimeConversation[]{},
				null));

	}

	@Override
	public void notScheduledTaskDueToLockedItems(String agentid,
			String agentType, Task task, MentalEntity[] lockedInputEntityIds) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.NotScheduledTaskDueToLockedItems,
				new Task[]{task},lockedInputEntityIds,
				new StateGoal[]{},new RuntimeConversation[]{},
				null));
	}

	@Override
	public void notScheduledTaskDueToMissingItems(String agentid,
			String agentType, Task task, MentalEntity[] missingInputEntities) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.NotScheduledTaskDueToMissingItems,
				new Task[]{task},missingInputEntities,
				new StateGoal[]{},new RuntimeConversation[]{},
				null));

	}

	@Override
	public void processingReceivedMessage(String agentid, String agentType,
			ACLMessage acl) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.ProcessingReceivedMessage,
				new Task[]{},new MentalEntity[]{},
				new StateGoal[]{},new RuntimeConversation[]{},
				acl));
	}

	@Override
	public void producedAConversation(String agentid, String agentType,
			Task task, RuntimeConversation conv) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.ProducedAConversation,
				new Task[]{task},new MentalEntity[]{},
				new StateGoal[]{},new RuntimeConversation[]{conv},
				null));

	}

	@Override
	public void receivedMessageInSequence(String agentid, String agentType,
			ACLMessage message) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.ReceivedMessageInSequence,
				new Task[]{},new MentalEntity[]{},
				new StateGoal[]{},new RuntimeConversation[]{},
				message));

	}

	@Override
	public void receivedMessageToStartACollaboration(String agentid,
			String agentType, ACLMessage message, String protocol) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.ReceivedMessageToStartACollaboration,
				new Task[]{},new MentalEntity[]{},
				new StateGoal[]{},new RuntimeConversation[]{},
				message));
	}

	@Override
	public void removeEntityFromConversation(String agentid, String agentType,
			Task task, MentalEntity entity, RuntimeConversation conversation) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.RemoveEntityFromConversation,
				new Task[]{task},new MentalEntity[]{entity},
				new StateGoal[]{},
				new RuntimeConversation[]{conversation},
				null));
	}
	
	@Override
	public void removeEntityFromMSByTask(String agentid, String agentType, Task task,
			MentalEntity entity) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.RemoveEntityFromMSByTask,
				new Task[]{task},new MentalEntity[]{entity},
				new StateGoal[]{},
				new RuntimeConversation[]{},
				null));

	}


	@Override
	public void removeEntityFromMS(String agentid, String agentType, 
			MentalEntity entity) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.RemoveEntityFromMS,
				new Task[]{},new MentalEntity[]{entity},
				new StateGoal[]{},
				new RuntimeConversation[]{},
				null));

	}

	@Override
	public void startingInteractionAsCollaborator(String agentid,
			String agentType, ACLMessage acl) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.StartingInteractionAsCollaborator,
				new Task[]{},new MentalEntity[]{},
				new StateGoal[]{},
				new RuntimeConversation[]{},
				acl));
	}

	@Override
	public void startingInteractionAsInitiator(String agentid,
			String agentType, ActiveConversation aconv) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.StartingInteractionAsInitiator,
				new Task[]{},new MentalEntity[]{},
				new StateGoal[]{},
				new RuntimeConversation[]{aconv.getConv()},
				null));

	}

	@Override
	public void startingTaskExecution(String agentid, String agentType,
			Task task) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.StartingTaskExecution,
				new Task[]{task},new MentalEntity[]{},
				new StateGoal[]{},
				new RuntimeConversation[]{},
				null));

	}

	@Override
	public void taskExecutionFinished(String agentid, String agentType,
			Task task) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.TaskExecutionFinished,
				new Task[]{task},new MentalEntity[]{},
				new StateGoal[]{},
				new RuntimeConversation[]{},
				null));

	}

	@Override
	public void waitingForNextMessageInSequence(String agentid,
			String agenType, String conversationID,
			String expectedNextSequenceState) {
		checker.notify(new Event(
				agentid,agenType,
				EventKind.WaitingForNextMessageInSequence,
				new Task[]{},new MentalEntity[]{},
				new StateGoal[]{},
				new RuntimeConversation[]{},
				null));

	}

	@Override
	public void scheduledTask(String agentid, String agentType, Task task) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.ScheduledTask,
				new Task[]{task},new MentalEntity[]{},
				new StateGoal[]{},
				new RuntimeConversation[]{},
				null));
		
	}

	@Override
	public void addedNewEntityToConversationFromCommBehavior(String agentid,
			String agentType, StateBehavior commBehavior, MentalEntity entity,
			RuntimeConversation conversation) {
		checker.notify(new Event(
				agentid,agentType,
				EventKind.addedNewEntityToConversationFromCommBehavior,
				new Task[]{},new MentalEntity[]{entity},
				new StateGoal[]{},
				new RuntimeConversation[]{conversation},
				null));
		
	}

    @Override
    public void removedDeletionLock(MentalEntity element, Vector<MentalEntity> locked, String aname, String atype) {
       checker.notify(new Event(
				aname,atype,
				EventKind.removedDeletionLock,
				new Task[]{},new MentalEntity[]{element},
				new StateGoal[]{},
				new RuntimeConversation[]{},
				null));
    }

    @Override
    public void addDeletionLock(MentalEntity entity, Vector<MentalEntity> cannotBeDeleted, String aname, String atype) {
        
    }

    @Override
    public void addDeletionLockToType(String type, Vector<String> cannotBeDeleted, String aname, String atype) {
        
    }

	@Override
	public void addedNewEntityToMSFromApp(String agentid, String agentType,
			Task t, MentalEntity entityType) {
	
		checker.notify(new Event(
				agentid,agentType,
				EventKind.AddedNewEntityToMS,
				new Task[]{},new MentalEntity[]{entityType},
				new StateGoal[]{},
				new RuntimeConversation[]{},
				null));
	}

}
