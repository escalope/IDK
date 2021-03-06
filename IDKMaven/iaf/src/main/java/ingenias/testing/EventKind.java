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

public enum EventKind {
  TaskAborted,TaskScheduled,TaskStarted,TaskFinished,
  MentalEntityAddedToMS, MentalEntityRemovedFromMS,
  removedDeletionLock,
  MentalEntityAddedToConv,MentalEntityRemovedFromConv, 
  TaskAborteddueToLockedItems, TaskAbortedDueToMissingItems, AddedNewEntityToConversation, AddedNewEntityToMS, ProducedNewEntityToMS, AlreadyHadAConversationCreatedForThatMesssage, AutomaticallyScheduledTask, ConsumedEntityDuringtaskExecutionFinished, ConsumedEntityDuringtaskExecutionFinishedFromConversation, ConversationalInitializationOfTaskFailed, CouldNotProcessMessageBecauseAgentDoesNotPlayRequestedRole, CurrentScheduledTasks, DontKnowHowToProcessReceivedMessage, FailedToFindColaboratorsWhenManuallyInitializingAConversation, ManuallyScheduledTask, MessageDelivered, NonConversationalInitializationOfTaskFailed, NotScheduledTaskDueToLockedItems, NotScheduledTaskDueToMissingItems, ProcessingReceivedMessage, ProducedAConversation, ReceivedMessageInSequence, ReceivedMessageToStartACollaboration, RemoveEntityFromConversation, RemoveEntityFromMS, StartingInteractionAsCollaborator, StartingInteractionAsInitiator, StartingTaskExecution, TaskExecutionFinished, WaitingForNextMessageInSequence, ScheduledTask, addedNewEntityToConversationFromCommBehavior, RemoveEntityFromMSByTask; 
}