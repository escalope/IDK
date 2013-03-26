

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

import java.util.Vector;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import jade.core.*;
import jade.wrapper.StaleProxyException;
import ingenias.editor.entities.MentalEntity;
import ingenias.exception.TimeOut;
import ingenias.jade.AgentStates;
import ingenias.jade.MentalStateManager;
import ingenias.jade.MentalStateProcessor;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.mental.*;
import ingenias.testing.*;
import ingenias.jade.IAFProperties;
import ingenias.testing.BasicMASTest;

public class TestSimpleWFDeploymentAndExecutionTest extends TryingSimpleWFDeploymentAndExecutionForWFTestCompleteWF {


	@BeforeMethod
	@Override
	public void agentSetup() throws StaleProxyException{
		IAFProperties.setGraphicsOn(false); // disable graphics
		super.agentSetup();
		// customize MAS Setup method here
	}


	@AfterMethod
	@Override
	public void endTest() throws StaleProxyException{
		super.endTest();
		// customize MAS shutdown here. The default behavior is a kill signal sent to the main container
	};

  
  
  @Test
  public void testDemo() throws TimeOut{			
  			// Involved agent local ids for this test are:
  			  			
        	// -GUIAgent_0SingleGUIAgent        	
        	  			
        	// -Agent1_0DeploymentUnitByType2        	
        	  			
        	// -Agent0_0DeploymentUnitByType1        	
        	
  			// write here the agent id whose 
  			// mental state manager you want to get access to
			// MentalStateManager msm = MSMRepository.getInstance().get("MY_AGENT_ID"); // provides access to the
			// For current agents, these are the variables containing their mental states
			  			
        	// -GUIAgent_0SingleGUIAgent
        	MentalStateManager msmGUIAgent_0SingleGUIAgent=MSMRepository.getInstance().waitFor("GUIAgent_0SingleGUIAgentWithInstanceOfGUIEvent");
        	  			
        	// -Agent1_0DeploymentUnitByType2
        	MentalStateManager msmAgent1_0DeploymentUnitByType2=MSMRepository.getInstance().waitFor("Agent1_0DeploymentUnitByType2");
        	  			
        	// -Agent0_0DeploymentUnitByType1
        	MentalStateManager msmAgent0_0DeploymentUnitByType1=MSMRepository.getInstance().waitFor("Agent0_0DeploymentUnitByType1");
        	
        	
			// mental state of the agent
			  			
        	// -GUIAgent_0SingleGUIAgent
			Vector<MentalEntity> frameFactEntitiesBeforeGUIAgent_0SingleGUIAgent = msmGUIAgent_0SingleGUIAgent
					.getMentalEntityByType("SampleGUIEvent"); // Obtains entities of a given type
									
			assertTrue("There should be one SampleGUIEvent entities and there are "+frameFactEntitiesBeforeGUIAgent_0SingleGUIAgent.size(),
			   	frameFactEntitiesBeforeGUIAgent_0SingleGUIAgent.size()==1);
			assertTrue("There should be zero MyFrameFact entities and there are "+msmGUIAgent_0SingleGUIAgent
					.getMentalEntityByType("MyFrameFact").size(),
					msmGUIAgent_0SingleGUIAgent
					.getMentalEntityByType("MyFrameFact").size()==0);
			assertTrue("There should be zero FinalFact entities and there are "+msmGUIAgent_0SingleGUIAgent
					.getMentalEntityByType("FinalFact").size(),
					msmGUIAgent_0SingleGUIAgent
					.getMentalEntityByType("FinalFact").size()==0);
			  			
        	// -Agent1_0DeploymentUnitByType2
			Vector<MentalEntity> frameFactEntitiesBeforeAgent1_0DeploymentUnitByType2 = msmAgent1_0DeploymentUnitByType2
					.getMentalEntityByType("AnotherFact"); // Obtains entities of a given type
									
			assertTrue("There should be zero AnotherFact entities ",
					msmAgent1_0DeploymentUnitByType2
					.getMentalEntityByType("AnotherFAct").size()==0);
			
			assertTrue("There should be zero FinalFact entities",
					msmAgent1_0DeploymentUnitByType2
					.getMentalEntityByType("FinalFact").size()==0);
			  			
        	// -Agent0_0DeploymentUnitByType1
			Vector<MentalEntity> frameFactEntitiesBeforeAgent0_0DeploymentUnitByType1 = msmAgent0_0DeploymentUnitByType1
					.getMentalEntityByType("AnotherFAct"); // Obtains entities of a given type
									
			assertTrue("There should be zero AnotherFAct entities",
			   	frameFactEntitiesBeforeAgent0_0DeploymentUnitByType1.size()==0);
			assertTrue("There should be zero GlobalFact entities",
					msmAgent0_0DeploymentUnitByType1
					.getMentalEntityByType("AnotherGlobalFact").size()==0);
			assertTrue("There should be zero AnotherGlobalFact entities",
					msmAgent0_0DeploymentUnitByType1
					.getMentalEntityByType("AnotherGlobalFact").size()==0);
			
						
			MainInteractionManager.goAutomatic(); // tells the agents to start working
			TestUtils.doNothing(2000); // waits for 1 second
			MainInteractionManager.goManual(); // task execution is frozen
			// wait agents to reach an idle state where no more tasks can be triggered once in the manual mode
			waitForState(MSPRepository.getInstance().get("GUIAgent_0SingleGUIAgentWithInstanceOfGUIEvent"),
						AgentStates.WAIT_FOR_CHANGE_IN_MENTAL_STATE,
						1000);
			waitForState(MSPRepository.getInstance().get("Agent1_0DeploymentUnitByType2"),
					AgentStates.WAIT_FOR_CHANGE_IN_MENTAL_STATE,
					1000);
			waitForState(MSPRepository.getInstance().get("Agent1_0DeploymentUnitByType2"),
					AgentStates.WAIT_FOR_CHANGE_IN_MENTAL_STATE,
					1000);
			
			// GUI Agent final state			
			assertTrue("There should be zero MyFrameFact entities",
					msmGUIAgent_0SingleGUIAgent.getMentalEntityByType("MyFrameFact").size()==0);			
			assertTrue("There should be zero AnotherFact entities",
					msmGUIAgent_0SingleGUIAgent.getMentalEntityByType("AnotherFact").size()==0);
			
			
			// Agent0  final state
			assertTrue("There should be zero AnotherGlobalFact entities and there are "+
			msmAgent1_0DeploymentUnitByType2.getMentalEntityByType("AnotherGlobalFact").size(),
					msmAgent1_0DeploymentUnitByType2.getMentalEntityByType("AnotherGlobalFact").size()==0);
			
			assertTrue("There should be zero GlobalFact entities and there are "+msmAgent1_0DeploymentUnitByType2.getMentalEntityByType("GlobalFact").size(),
					msmAgent1_0DeploymentUnitByType2.getMentalEntityByType("GlobalFact").size()==0);
			
			// Agent 1 final state
	
			assertTrue("There should be zero AnotherGlobalFact entities and there are "+
			msmAgent1_0DeploymentUnitByType2.getMentalEntityByType("AnotherGlobalFact").size(),
					msmAgent1_0DeploymentUnitByType2.getMentalEntityByType("AnotherGlobalFact").size()==0);
			
			assertTrue("There should be zero FinalFact entities and there are "+
					msmAgent1_0DeploymentUnitByType2.getMentalEntityByType("FinalFact").size(),
					msmAgent1_0DeploymentUnitByType2.getMentalEntityByType("FinalFact").size()==0);
						
					
  }


private void waitForState(MentalStateProcessor mentalStateProcessor,
		int waitForChangeInMentalState, int i) throws TimeOut {
	long sleepingTime=0;
	while (mentalStateProcessor.getState()!=waitForChangeInMentalState && i<sleepingTime){
		try {
			Thread.currentThread().sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sleepingTime=sleepingTime+100;
	}
	if (sleepingTime>i && mentalStateProcessor.getState()!=waitForChangeInMentalState)
		throw new TimeOut("Exceeded "+i+" millis threshold for waiting "+i+" state. Current state in the agent is "+mentalStateProcessor.getState());
	
}
}


 