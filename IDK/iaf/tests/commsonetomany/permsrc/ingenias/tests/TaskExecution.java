

/*
    Copyright (C) 2005 Jorge Gomez Sanz

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


package ingenias.tests;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import junit.extensions.*;
import jade.core.*;
import jade.wrapper.StaleProxyException;
import ingenias.editor.entities.MentalEntity;
import ingenias.jade.MentalStateManager;
import ingenias.jade.components.InteractionLaunchingTaskTask;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.mental.*;
import ingenias.testing.*;
import ingenias.tests.BasicMASTest;
import ingenias.jade.IAFProperties;


public class TaskExecution extends BasicMASTest {



	@Test
	public void testDemo(){			
		// Involved agent local ids for this test are:


		// -InitiatorAgent_0DeploymentUnitByType2

		// -ColAgent2_0DeploymentUnitByType1

		// -ColAgent1_0DeploymentUnitByType0

		// write here the agent id whose 
		// mental state manager you want to get access to

		EventStateMachine esm = createAutomataForVerifyingInteractionLaunchingTaskExecution();
		EventStateMachine esm1 = createAutomataForVerifyingTaskProcessingTheAnswerExecution();
		EventStateMachine esm2 = createAutomataForVerifyingTaskProcessingTheRequestExecution();
		EventStateMachine esm3 = createAutomataForVerifyingCustomizedTaskProcessingTheRequestExecution();

		EventStateMachine esm4 = createAutomataForVerifyingInitiatorAgentExecution();
		EventStateMachine esm5 = createAutomataForVerifyingColAgent1Execution();
		EventStateMachine esm6 = createAutomataForVerifyingColAgent2Execution();

		EventSequenceChecker esc=new EventSequenceChecker();
		esc.registerEvaluator(esm);
		esc.registerEvaluator(esm1);
		esc.registerEvaluator(esm2);
		esc.registerEvaluator(esm3);
		esc.registerEvaluator(esm4);
		esc.registerEvaluator(esm5);
		esc.registerEvaluator(esm6);

		// mental state of the agent
		System.out.println("sigo");
		MainInteractionManager.goAutomatic(); // tells the agents to start working
		TestUtils.doNothing(5000); // waits for 2 second

		assertTrue("The task InteractionLaunchingTask should have been executed and it is in state "+
				esm.getCurrentStates()+". The trace is \n"+esm.getTracesAsString(),esm.isAccepted()); 		
		assertTrue("The task TaskProcessingTheAnswer should have been executed and it is in state "+
				esm1.getCurrentStates()+". The trace is \n"+esm1.getTracesAsString(),esm1.isAccepted()); 		
		assertTrue("The task  TaskProcessingTheRequest should have been executed and it is in state "+
				esm2.getCurrentStates()+". The trace is \n"+esm2.getTracesAsString(),esm2.isAccepted()); 		
		assertTrue("The task CustomizedTaskProcessingTheRequest should have been executed and it is in state "+
				esm3.getCurrentStates()+". The trace is \n"+esm3.getTracesAsString(),esm3.isAccepted()); 		
		assertTrue("Task execution sequence for agent InitiatorAgent is not corrent and fails in state "+
				esm4.getCurrentStates()+". The trace is \n"+esm4.getTracesAsString(),esm4.isAccepted()); 		
		assertTrue("Task execution sequence for agent ColAgent1 is not corrent and fails in state "+
				esm5.getCurrentStates()+". The trace is \n"+esm5.getTracesAsString(),esm5.isAccepted()); 		
		assertTrue("Task execution sequence for agent ColAgent2 is not corrent and fails in state "+
				esm6.getCurrentStates()+". The trace is \n"+esm6.getTracesAsString(),esm6.isAccepted()); 						
	}

	private EventStateMachine createAutomataForVerifyingColAgent2Execution() {
		State initial=new State("first");
		State failure= new State("failure");
		State taskExecuted=new State("Processing the request task executed");
		initial.addTransition(// task "InteractionLaunchingTaskTask" is executed
				new EventHandler(new State[]{taskExecuted}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
								event.getAgentId().indexOf("ColAgent2_0DeploymentUnitByType1")>=0){						
							return event.getInvolvedTasks()[0].getType().equals("CustomizedTaskProcessingTheRequest");								
						}
						return false;
					}
				});
		initial.addTransition(// the execution of other task different from 
				// "CustomizedTaskProcessingTheRequest" leads to a failure
				new EventHandler(new State[]{failure}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
								event.getAgentId().indexOf("ColAgent2_0DeploymentUnitByType1")>=0){
							System.err.println("colaborador2 "+event.getInvolvedTasks()[0].getType());
							return 
							!event.getInvolvedTasks()[0].getType().equals("CustomizedTaskProcessingTheRequest")&& 
							!event.getInvolvedTasks()[0].getType().equals("DeleteNonUsedEntitiesTask");								
						}
						return false;
					}
				});
		taskExecuted.addTransition(// The task is executed only once
				new EventHandler(new State[]{failure}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished)){
							return 	event.getAgentId().equalsIgnoreCase("ColAgent2_0DeploymentUnitByType1")
							&& 
							!event.getInvolvedTasks()[0].getType().equals("DeleteNonUsedEntitiesTask");
						}
						return false;
					}
				});

		EventStateMachine esm=new EventStateMachine(
				"TaskProcessingTheRequest task execution",initial,new State[]{taskExecuted});
		return esm;
	}

	private EventStateMachine createAutomataForVerifyingColAgent1Execution() {
		State initial=new State("first");
		State failure= new State("failure");
		State taskExecuted=new State("Processing the request task executed");
		initial.addTransition(// task "InteractionLaunchingTaskTask" is executed
				new EventHandler(new State[]{taskExecuted}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
								event.getAgentId().indexOf("ColAgent1_0DeploymentUnitByType0")>=0){						
							return event.getInvolvedTasks()[0].getType().equals("TaskProcessingTheRequest");								
						}
						return false;
					}
				});
		initial.addTransition(// the execution of other task different from 
				// "TaskProcessingTheRequest" leads to a failure
				new EventHandler(new State[]{failure}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
								event.getAgentId().indexOf("ColAgent1_0DeploymentUnitByType0")>=0){						
							return !event.getInvolvedTasks()[0].getType().equals("TaskProcessingTheRequest")&& 
							!event.getInvolvedTasks()[0].getType().equals("DeleteNonUsedEntitiesTask");								
						}
						return false;
					}
				});

		taskExecuted.addTransition(// The task is executed only once
				new EventHandler(new State[]{failure}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished)){
							return 	event.getAgentId().equalsIgnoreCase("ColAgent1_0DeploymentUnitByType0")
							&& 
							!event.getInvolvedTasks()[0].getType().equals("DeleteNonUsedEntitiesTask");
						}
						return false;
					}
				});

		EventStateMachine esm=new EventStateMachine(
				"TaskProcessingTheRequest task execution",initial,new State[]{taskExecuted});
		return esm;

	}

	private EventStateMachine createAutomataForVerifyingInitiatorAgentExecution() {
		State initial=new State("first");
		State failure= new State("failure");
		State waitingForAnswers=new State("waitingForAnswers");
		State answersProcessed=new State("answersProcessed");
		State answersProcessedAgain=new State("answersProcessedAgain");

		initial.addTransition(// task "InteractionLaunchingTaskTask" is executed
				new EventHandler(new State[]{waitingForAnswers}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
								event.getAgentId().indexOf("InitiatorAgent_0DeploymentUnitByType2")>=0){						
							return event.getInvolvedTasks()[0].getType().equals("InteractionLaunchingTask");								
						}
						return false;
					}
				});
		initial.addTransition(// the execution of other task different from 
				// "InteractionLaunchingTaskTask" leads to a failure
				new EventHandler(new State[]{failure}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
								event.getAgentId().indexOf("InitiatorAgent_0DeploymentUnitByType2")>=0){						
							return !event.getInvolvedTasks()[0].getType().equals("InteractionLaunchingTask");								
						}
						return false;
					}
				});

		waitingForAnswers.addTransition(// task "InteractionLaunchingTaskTask" is executed
				new EventHandler(new State[]{answersProcessed}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
								event.getAgentId().indexOf("InitiatorAgent_0DeploymentUnitByType2")>=0){						
							return event.getInvolvedTasks()[0].getType().equals("TaskProcessingTheAnswer");								
						}
						return false;
					}
				});
		
		waitingForAnswers.addTransition(// the execution of other task different from 
				// "InteractionLaunchingTaskTask" leads to a failure
				new EventHandler(new State[]{failure}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
								event.getAgentId().indexOf("InitiatorAgent_0DeploymentUnitByType2")>=0){	
							System.err.println("Se quiere ejecutar "+event.getInvolvedTasks()[0].getType());
							return !event.getInvolvedTasks()[0].getType().equals("TaskProcessingTheAnswer") && 
							!event.getInvolvedTasks()[0].getType().equals("DeleteNonUsedEntitiesTask") ;								
						}
						return false;
					}
				});
		answersProcessed.addTransition(// Any further task execution leads to a failure
				new EventHandler(new State[]{answersProcessedAgain}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
								event.getAgentId().indexOf("InitiatorAgent_0DeploymentUnitByType2")>=0){						
							return event.getInvolvedTasks()[0].getType().equals("TaskProcessingTheAnswer");								
						}
						return false;
					}
				});

		
		answersProcessed.addTransition(// Any further task execution leads to a failure
				new EventHandler(new State[]{failure}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
								event.getAgentId().indexOf("InitiatorAgent_0DeploymentUnitByType2")>=0){						
							return !event.getInvolvedTasks()[0].getType().equals("TaskProcessingTheAnswer")
							&& 
							!event.getInvolvedTasks()[0].getType().equals("DeleteNonUsedEntitiesTask");								
						}
						return false;
					}
				});


		answersProcessedAgain.addTransition(// Any further task execution leads to a failure
				new EventHandler(new State[]{failure}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished) ){
							return 	event.getAgentId().equalsIgnoreCase("InitiatorAgent_0DeploymentUnitByType2");
						}
						return false;
					}
				});

		EventStateMachine esm=new EventStateMachine(
				"TaskProcessingTheRequest task execution",initial,new State[]{answersProcessedAgain});
		return esm;

	}

	private EventStateMachine createAutomataForVerifyingTaskProcessingTheRequestExecution() {
		State initial=new State("first");
		State failure= new State("failure");
		State taskExecuted=new State("Processing the request task executed");
		initial.addTransition(// task "InteractionLaunchingTaskTask" is executed
				new EventHandler(new State[]{taskExecuted}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
								event.getAgentId().indexOf("ColAgent1_0DeploymentUnitByType0")>=0){						
							return event.getInvolvedTasks()[0].getType().equals("TaskProcessingTheRequest");								
						}
						return false;
					}
				});
		taskExecuted.addTransition(// The task is executed only once
				new EventHandler(new State[]{failure}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished)){
							return event.getInvolvedTasks()[0].getType().equals("TaskProcessingTheRequest") &&
							event.getAgentId().equalsIgnoreCase("ColAgent1_0DeploymentUnitByType0");
						}
						return false;
					}
				});
		EventStateMachine esm=new EventStateMachine(
				"TaskProcessingTheRequest task execution",initial,new State[]{taskExecuted});
		return esm;
	}

	private EventStateMachine createAutomataForVerifyingCustomizedTaskProcessingTheRequestExecution() {
		State initial=new State("first");
		State failure= new State("failure");
		State taskExecuted=new State("Customized Processing the request task executed");

		initial.addTransition(// task "CustomizedTaskProcessingTheRequest" is executed
				new EventHandler(new State[]{taskExecuted}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
								event.getAgentId().indexOf("ColAgent2_0DeploymentUnitByType1")>=0){						
							return event.getInvolvedTasks()[0].getType().equals("CustomizedTaskProcessingTheRequest");								
						}
						return false;
					}
				});
		taskExecuted.addTransition(// The task is executed only once
				new EventHandler(new State[]{failure}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished)){
							return event.getInvolvedTasks()[0].getType().equals("CustomizedTaskProcessingTheRequest") &&
							event.getAgentId().equalsIgnoreCase("ColAgent2_0DeploymentUnitByType1");
						}
						return false;
					}
				});
		EventStateMachine esm=new EventStateMachine(
				"Verifying CustomizedTaskProcessingTheRequest",initial,new State[]{taskExecuted});
		return esm;
	}

	private EventStateMachine createAutomataForVerifyingTaskProcessingTheAnswerExecution() {
		State initial=new State("first");
		State failure= new State("failure");
		State firstTaskExecution=new State("TaskProcessingTheAnswer task executed once");
		State secondTaskExecution=new State("TaskProcessingTheAnswer task executed twice");
		initial.addTransition(// task "TaskProcessingTheAnswer" is executed
				new EventHandler(new State[]{firstTaskExecution}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished)){
							System.out.println( event.getInvolvedTasks()[0].getType()+" terminada "+event.getAgentId());
						}
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
								event.getAgentId().indexOf("InitiatorAgent_0DeploymentUnitByType2")>=0){
							System.out.println("resultadoa "+event.getInvolvedTasks()[0].getType().equals("InteractionLaunchingTask"));
							return event.getInvolvedTasks()[0].getType().equals("TaskProcessingTheAnswer");								
						}
						return false;
					}
				});
		firstTaskExecution.addTransition(// task "TaskProcessingTheAnswer" is executed
				new EventHandler(new State[]{secondTaskExecution}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished)){
							System.out.println( event.getInvolvedTasks()[0].getType()+" terminada "+event.getAgentId());
						}
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
								event.getAgentId().indexOf("InitiatorAgent_0DeploymentUnitByType2")>=0){
							System.out.println("resultadoa "+event.getInvolvedTasks()[0].getType().equals("InteractionLaunchingTask"));
							return event.getInvolvedTasks()[0].getType().equals("TaskProcessingTheAnswer");								
						}
						return false;
					}
				});
		secondTaskExecution.addTransition(// The task is executed only once
				new EventHandler(new State[]{failure}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished)){
							return event.getInvolvedTasks()[0].getType().equals("TaskProcessingTheAnswer") &&
							event.getAgentId().equalsIgnoreCase("InitiatorAgent_0DeploymentUnitByType2");
						}
						return false;
					}
				});
		EventStateMachine esm=new EventStateMachine(
				"TaskProcessingTheAnswer verification",initial,new State[]{secondTaskExecution});
		return esm;
	}

	private EventStateMachine createAutomataForVerifyingInteractionLaunchingTaskExecution() {
		State initial=new State("first");
		State failure= new State("failure");
		State taskExecuted=new State("Interaction launching task executed");
		initial.addTransition(// task "InteractionLaunchingTaskTask" is executed
				new EventHandler(new State[]{taskExecuted}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished)){
							System.out.println( event.getInvolvedTasks()[0].getType()+" terminada "+event.getAgentId());
						}
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
								event.getAgentId().indexOf("InitiatorAgent_0DeploymentUnitByType2")>=0){
							System.out.println("resultadoa "+event.getInvolvedTasks()[0].getType().equals("InteractionLaunchingTask"));
							return event.getInvolvedTasks()[0].getType().equals("InteractionLaunchingTask");								
						}
						return false;
					}
				});
		taskExecuted.addTransition(// The task is executed only once
				new EventHandler(new State[]{failure}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						if (event.getEventKind().equals(EventKind.TaskExecutionFinished)){
							return event.getInvolvedTasks()[0].getType().equals("InteractionLaunchingTask") &&
							event.getAgentId().equalsIgnoreCase("InitiatorAgent_0DeploymentUnitByType2");
						}
						return false;
					}
				});
		EventStateMachine esm=new EventStateMachine(
				"Initiator task execution",initial,new State[]{taskExecuted});
		return esm;
	}
}

