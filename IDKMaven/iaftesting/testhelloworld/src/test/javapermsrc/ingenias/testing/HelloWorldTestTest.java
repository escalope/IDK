

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


package ingenias.testing;



import static org.testng.AssertJUnit.assertTrue;
import java.util.Vector;

import org.fest.swing.core.EmergencyAbortListener;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.timing.Pause;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jade.core.*;
import jade.wrapper.StaleProxyException;
import ingenias.editor.entities.MentalEntity;
import ingenias.exception.TimeOut;
import ingenias.jade.AgentEventAdapter;
import ingenias.jade.EventManager;
import ingenias.jade.MentalStateManager;
import ingenias.jade.components.DeleteNonUsedEntitiesTask;
import ingenias.jade.components.GreetingsTaskTask;
import ingenias.jade.components.Task;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.mental.*;
import ingenias.jade.IAFProperties;
import ingenias.testing.BasicMASTest;
import ingenias.testing.fest.*;



public class HelloWorldTestTest extends BasicTestingDeploymentForHelloWorldTest{

	private class TaskExecutionLifecycle extends AgentEventAdapter{
		GenericAutomata ga=null;
		public TaskExecutionLifecycle(){
			ga=new GenericAutomata();
			ga.addInitialState("init");		
			ga.addStateTransition("init","evtscheduledtask","scheduledtask");
			ga.addStateTransition("scheduledtask","evtstartingtask","startedtask");
			ga.addStateTransition("startedtask","evtremovedentity","removedentity");
			ga.addStateTransition("removedentity","evtfinishedtask","finishedtask");		
			ga.addStateTransition("finishedtask","","errorstate");		
			ga.addFinalState("finishedtask");		
		}

		public GenericAutomata getAutomata(){
			return ga;
		}


		@Override
		public void startingTaskExecution(String agentid, String agentType,
				Task task) {
			if (task.getClass().equals(GreetingsTaskTask.class))
				ga.next("evtstartingtask");
		}

		@Override
		public void taskExecutionFinished(String agentid, String agentType,
				Task task) {
			if (task.getClass().equals(GreetingsTaskTask.class))
				ga.next("evtfinishedtask");
		}


		@Override
		public void scheduledTask(String agentid, String agentType, Task task) {
			if (task.getClass().equals(GreetingsTaskTask.class))
				ga.next("evtscheduledtask");
		}

		@Override
		public void removeEntityFromMSByTask(String agentid, String agentType,
				Task task, MentalEntity entity) {
			if (task.getClass().equals(DeleteNonUsedEntitiesTask.class) && 
					entity.getType().equalsIgnoreCase("DontForgetToGreet"))
				ga.next("evtremovedentity");
		}

		@Override
		public void removeEntityFromMS(String agentid, String agentType,
				MentalEntity entity) {
			if (entity.getType().equalsIgnoreCase("DontForgetToGreet"))
				ga.next("evtremovedentity");
		}

	}
	

	@BeforeMethod
	@Override
	public void agentSetup() throws StaleProxyException{
		IAFProperties.setGraphicsOn(false); // disable graphics
		super.agentSetup();
		// customize MAS Setup method herer
	}


	@AfterMethod
	@Override
	public void endTest() throws StaleProxyException{
		super.endTest();
		// customize MAS shutdown here. The default behavior is a kill signal sent to the main container
	};



	@Test
	public void testDemo() throws TimeOut{			

		MSMRepository.getInstance().waitFor("HelloWorldAgent_0HelloWorldAgentCreation");

		MentalStateManager msmHelloWorldAgent_0HelloWorldAgentCreation=
				MSMRepository.getInstance().get("HelloWorldAgent_0HelloWorldAgentCreation");

		Vector<MentalEntity> frameFactEntitiesBefore = msmHelloWorldAgent_0HelloWorldAgentCreation
				.getMentalEntityByType("DontForgetToGreet");

		assertTrue("There should be one  DontForgetToGreet entities and there are "+
				frameFactEntitiesBefore.size(),
				frameFactEntitiesBefore.size() == 1); 

		TaskExecutionLifecycle automata=new TaskExecutionLifecycle();		
		EventManager.getInstance().register(automata);

		MainInteractionManager.goAutomatic(); // tells the agents to start working

		TestUtils.doNothing(1000); // waits for 1 second

		assertTrue("The verification automata ought to have finished and its current state is "+
				automata.getAutomata().getCurrentStates(), automata.getAutomata().isFinal());

		Vector<MentalEntity> frameFactEntitiesAfter = msmHelloWorldAgent_0HelloWorldAgentCreation
				.getMentalEntityByType("DontForgetToGreet");

		assertTrue("There should be cero DontForgetToGreet entities and there are "+
				frameFactEntitiesAfter.size(),
				frameFactEntitiesAfter.isEmpty()); 		
	}
}

