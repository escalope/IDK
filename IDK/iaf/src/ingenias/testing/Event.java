package ingenias.testing;

import jade.lang.acl.ACLMessage;
import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.editor.entities.StateGoal;
import ingenias.jade.components.Task;

public class Event {
	public Event(String agentId,String agentType,
			EventKind eventKind, Task[] tasks,
			MentalEntity[] missingInputType, StateGoal[] stateGoals,
			RuntimeConversation[] runtimeConversations,
			ACLMessage involvedMessage) {
		this.eventKind=eventKind;
		this.involvedTasks=tasks;
		this.involvedGoals=stateGoals;
		this.involvedConversations=runtimeConversations;
		this.involvedMentalEntities=missingInputType;
		this.involvedMessage=involvedMessage;
		this.agentId=agentId;
		this.agentType=agentType;	
		
		if (getEventKind()==null){
			throw new RuntimeException("Field eventKind of the event must contain a value");
		}
	}
	
	private EventKind eventKind;
	
	public EventKind getEventKind() {
		return eventKind;
	}
	public void setEventKind(EventKind eventKind) {
		this.eventKind = eventKind;
	}
	public Task[] getInvolvedTasks() {
		return involvedTasks;
	}
	public void setInvolvedTasks(Task[] involvedTasks) {
		this.involvedTasks = involvedTasks;
	}
	public MentalEntity[] getInvolvedMentalEntities() {
		return involvedMentalEntities;
	}
	public void setInvolvedMentalEntities(MentalEntity[] involvedMentalEntities) {
		this.involvedMentalEntities = involvedMentalEntities;
	}
	public StateGoal[] getInvolvedGoals() {
		return involvedGoals;
	}
	public void setInvolvedGoals(StateGoal[] involvedGoals) {
		this.involvedGoals = involvedGoals;
	}
	public RuntimeConversation[] getInvolvedConversations() {
		return involvedConversations;
	}
	public void setInvolvedConversations(RuntimeConversation[] involvedConversations) {
		this.involvedConversations = involvedConversations;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getAgentId() {
		return agentId;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}
	public String getAgentType() {
		return agentType;
	}

	public void setInvolvedMessage(ACLMessage involvedMessage) {
		this.involvedMessage = involvedMessage;
	}
	public ACLMessage getInvolvedMessage() {
		return involvedMessage;
	}

	private Task[] involvedTasks;
	private MentalEntity[] involvedMentalEntities;
	private StateGoal[] involvedGoals;
	private RuntimeConversation[] involvedConversations;
	private String agentId;
	private String agentType;
	private ACLMessage involvedMessage;
}
