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

import java.util.Date;

import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.jade.comm.ActiveConversation;
import ingenias.jade.comm.StateBehavior;
import ingenias.jade.components.Task;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.util.leap.Iterator;
import java.util.Vector;

public class AgentEventChartsHandler implements AgentEventListener {
	private ChartStatsManager charts=null;
	
	public AgentEventChartsHandler(ChartStatsManager charts){
		this.charts=charts;
	}
	
	@Override
	public void abortedTaskDueToLockedItems(String agentid, String agentType,
			Task task, MentalEntity[] missingInputType) { 
		this.charts.addEvent(agentid+":"+agentType+", aborted task "+task.getType(),new Date().getTime(),1);
		this.charts.addEvent("Aborted task "+task.getType(),new Date().getTime(),1);
	}

	@Override
	public void abortedTaskDueToMissingItems(String agentid, String agentType,
			Task task, MentalEntity[] missingInputType) {
		this.charts.addEvent(agentid+":"+agentType+", aborted task "+task.getType(),new Date().getTime(),1);
		this.charts.addEvent("Aborted task "+task.getType(),new Date().getTime(),1);		
	}

	@Override
	public void abortedTaskDueToMissingItemsInConversation(String agentid,
			String agentType, Task task, MentalEntity[] missingInputType) {
		this.charts.addEvent(agentid+":"+agentType+", aborted task "+task.getType(),new Date().getTime(),1);
		this.charts.addEvent("Aborted task "+task.getType(),new Date().getTime(),1);		
	}

	@Override
	public void addedNewEntityToConversation(String agentid, String agentType,
			Task task,MentalEntity entity, RuntimeConversation conversation) {
		this.charts.addEvent(agentid+":"+agentType+", created entity "+entity.getType(),new Date().getTime(),1);
		this.charts.addEvent("Created entity "+entity.getType(),new Date().getTime(),1);	
	}

	@Override
	public void addedNewEntityToMS(String agentid, String agentType,
			MentalEntity entity) {
		this.charts.addEvent(agentid+":"+agentType+", created entity "+entity.getType(),new Date().getTime(),1);
		this.charts.addEvent("Created entity "+entity.getType(),new Date().getTime(),1);			
	}

	@Override
	public void alreadyHadAConversationCreatedForThatMesssage(String localName,
			String substring, ACLMessage acl) {
	}

	@Override
	public void automaticallyScheduledTask(String agentid, String agentType,
			Task task) {
	}

	@Override
	public void consumedEntityDuringtaskExecutionFinished(String agentid,
			String agentType, Task task, MentalEntity entity) {
		this.charts.addEvent(agentid+":"+agentType+", removed entity "+entity.getType(),new Date().getTime(),1);
		this.charts.addEvent("Removed entity "+entity.getType(),new Date().getTime(),1);	
	}

	@Override
	public void consumedEntityDuringtaskExecutionFinishedFromConversation(
			String agentid, String agentType, Task task, MentalEntity entity,
			RuntimeConversation conversation) {
		this.charts.addEvent(agentid+":"+agentType+", removed entity "+entity.getType(),new Date().getTime(),1);
		this.charts.addEvent("Removed entity "+entity.getType(),new Date().getTime(),1);		}

	@Override
	public void conversationalInitializationOfTaskFailed(String agentid,
			String agentType, Task task, String[] missingInputType) {

	}

	@Override
	public void couldNotProcessMessageBecauseAgentDoesNotPlayRequestedRole(
			String agentid, String agenttype, ACLMessage acl,
			String requestedRole) {
		this.charts.addEvent(agentid+":"+agenttype+", not playing role "+requestedRole+" so cannot participate",new Date().getTime(),1);
	}

	@Override
	public void currentScheduledTasks(String agentid, String agentType,
			Task[] tasks) {
		
	}

	@Override
	public void dontKnowHowToProcessReceivedMessage(String agentid,
			String agentType, ACLMessage acl) {
		this.charts.addEvent(agentid+":"+agentType+", dontKnowHowToProcessReceivedMessage",new Date().getTime(),1);		
	}

	@Override
	public void failedToFindColaboratorsWhenManuallyInitializingAConversation(
			String agentid, String agentType, Task task,
			String interactionType, String collaboratorNotFound) {
		this.charts.addEvent(agentid+":"+agentType+", missing at least one colaborator",new Date().getTime(),1);	
		this.charts.addEvent(agentid+":"+agentType+", cannot start "+interactionType+", missing at least one colaborator",new Date().getTime(),1);	

	}

	@Override
	public void manuallyScheduledTask(String agentid, String agentType,
			Task task) {
		
	}

	@Override
	public void messageDelivered(String agentid, String agenttype,
			ACLMessage message2BSend) {
		this.charts.addEvent(agentid+":"+agenttype+", a message was sent",new Date().getTime(),1);
		Iterator receivers = message2BSend.getAllReceiver();
		while (receivers.hasNext()){
		 AID receiver=(AID)receivers.next();
		 this.charts.addEvent(agentid+":"+agenttype+", a message was sent to "+receiver.getLocalName(),new Date().getTime(),1);	
		}
	}

	@Override
	public void nonConversationalInitializationOfTaskFailed(String agentid,
			String agentType, Task task, String[] missingInputType) {
		
	}

	@Override
	public void notScheduledTaskDueToLockedItems(String agentid,
			String agentType, Task task, MentalEntity[] lockedInputEntities) {
		
	}

	@Override
	public void notScheduledTaskDueToMissingItems(String agentid,
			String agentType, Task task, MentalEntity[] missingInputEntities) {
		
	}

	@Override
	public void processingReceivedMessage(String agentid, String agentType,
			ACLMessage acl) {
		this.charts.addEvent(agentid+":"+agentType+", a message was received",new Date().getTime(),1);
		this.charts.addEvent(agentid+":"+agentType+", a message was received from "+acl.getSender().getLocalName(),new Date().getTime(),1);
	}

	@Override
	public void producedAConversation(String agentid, String agentType,
			Task task, RuntimeConversation conv) {
			
	}

	@Override
	public void receivedMessageInSequence(String agentid, String agentType,
			ACLMessage message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receivedMessageToStartACollaboration(String agentid,
			String agenttype, ACLMessage message, String protocol) {
		this.charts.addEvent(agentid+":"+agenttype+", a colaboration was requested",new Date().getTime(),1);
		this.charts.addEvent(agentid+":"+agenttype+",  a colaboration was requested by "+message.getSender().getLocalName(),new Date().getTime(),1);
	}

	@Override
	public void removeEntityFromConversation(String agentid, String agentType,
			Task task, MentalEntity entity, RuntimeConversation conversation) {
		this.charts.addEvent(agentid+":"+agentType+", entity "+entity.getType()+" was removed",new Date().getTime(),1);
		this.charts.addEvent(agentid+":"+agentType+",  an entity was removed",new Date().getTime(),1);		
	}

	@Override
	public void removeEntityFromMS(String agentid, String agentType,
			 MentalEntity entity) {
		this.charts.addEvent(agentid+":"+agentType+", entity "+entity.getType()+" was removed",new Date().getTime(),1);
		this.charts.addEvent(agentid+":"+agentType+",  an entity was removed",new Date().getTime(),1);		
	}
	
	@Override
	public void removeEntityFromMSByTask(String agentid, String agentType,
			Task task, MentalEntity entity) {
		this.charts.addEvent(agentid+":"+agentType+", entity "+entity.getType()+" was removed by task "+task.getType(),new Date().getTime(),1);
		this.charts.addEvent(agentid+":"+agentType+",  an entity was removed by task "+task.getType(),new Date().getTime(),1);		
	}

	@Override
	public void startingInteractionAsCollaborator(String agentid,
			String agenttype, ACLMessage acl) {
		this.charts.addEvent(agentid+":"+agenttype+", a colaboration was started",new Date().getTime(),1);
	}

	@Override
	public void startingInteractionAsInitiator(String agentid,
			String agenttype, ActiveConversation aconv) {
		this.charts.addEvent(agentid+":"+agenttype+", requesting a colaboration",new Date().getTime(),1);
		this.charts.addEvent(agentid+":"+agenttype+", requesting a colaboration of type "+aconv.getConv().getInteraction().getType(),new Date().getTime(),1);
	}

	@Override
	public void startingTaskExecution(String agentid, String agentType,
			Task task) {
		this.charts.addEvent(agentid+":"+agentType+", starting task "+task.getType(),new Date().getTime(),1);
		this.charts.addEvent(agentid+":"+agentType+", starting a task",new Date().getTime(),1);			
	}

	@Override
	public void taskExecutionFinished(String agentid, String agentType,
			Task task) {
		this.charts.addEvent(agentid+":"+agentType+", finishing task "+task.getType(),new Date().getTime(),1);
		this.charts.addEvent(agentid+":"+agentType+", finishing a task",new Date().getTime(),1);			
	}

	@Override
	public void waitingForNextMessageInSequence(String agentid,
			String agentype, String conversationID,
			String expectedNextSequenceState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addedNewEntityToMS(String agentid, String agentType, Task t,
			MentalEntity entityType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scheduledTask(String agentid, String agentType, Task task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addedNewEntityToConversationFromCommBehavior(String agentid, String agentType,
			StateBehavior commBehavior,MentalEntity entity, RuntimeConversation conversation) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void removedDeletionLock(MentalEntity element, Vector<MentalEntity> locked, String aname, String atype) {
        
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

		
	}

}
