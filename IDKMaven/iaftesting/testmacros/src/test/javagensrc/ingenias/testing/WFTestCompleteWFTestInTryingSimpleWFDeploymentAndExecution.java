

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
import ingenias.exception.InvalidEntity;
import ingenias.exception.TimeOut;
import ingenias.jade.EventManager;
import ingenias.jade.IAFProperties;
import ingenias.jade.MentalStateManager;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.testing.traces.RetrieveExecutionData;
import ingenias.testing.traces.TaskExecutionValidation;
import jade.wrapper.StaleProxyException;
import ingenias.jade.mental.*;

import java.util.Vector;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WFTestCompleteWFTestInTryingSimpleWFDeploymentAndExecution extends TryingSimpleWFDeploymentAndExecutionForWFTestCompleteWF {

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

  
  
  @Test(invocationCount=1,skipFailedInvocations=false)
  public void testDemo() throws TimeOut{		
  			  			
        	// wait for Agent1_0DeploymentUnitByType2 to initialise
        	MentalStateManager msmAgent1_0DeploymentUnitByType2=MSMRepository.getInstance().waitFor("Agent1_0DeploymentUnitByType2");
        	  			
        	// wait for Agent0_0DeploymentUnitByType1 to initialise
        	MentalStateManager msmAgent0_0DeploymentUnitByType1=MSMRepository.getInstance().waitFor("Agent0_0DeploymentUnitByType1");
        	  			
        	// wait for GUIAgent_0SingleGUIAgentWithInstanceOfGUIEvent to initialise
        	MentalStateManager msmGUIAgent_0SingleGUIAgentWithInstanceOfGUIEvent=MSMRepository.getInstance().waitFor("GUIAgent_0SingleGUIAgentWithInstanceOfGUIEvent");
        	
        	
        	GenericAutomata ga=null;
        	ga=new GenericAutomata();
			
			ga.addInitialState("default_WFTestInitialState1");		
			
			
			
			ga.addFinalState("WFTestFinalState1");		
			
			
			
			ga.addStateTransition("default_WFTestInitialState1","GUIAgent_0SingleGUIAgentWithInstanceOfGUIEvent-ProcessAGUIEvent","WFTestInitialState1");
			
			ga.addStateTransition("WFTestInitialState1","GUIAgent_0SingleGUIAgentWithInstanceOfGUIEvent-Task0","WFTestState1");
			
			ga.addStateTransition("WFTestState1","Agent0_0DeploymentUnitByType1-Task1","WFTestState2");
			
			ga.addStateTransition("WFTestState2","Agent0_0DeploymentUnitByType1-Task3","WFTestState3");
			
			ga.addStateTransition("WFTestState3","Agent1_0DeploymentUnitByType2-Task2","WFTestState4");
			
			ga.addStateTransition("WFTestState4","GUIAgent_0SingleGUIAgentWithInstanceOfGUIEvent-Task4","WFTestFinalState1");
				
			
			TaskExecutionValidation tev=new TaskExecutionValidation(ga);
        	
        	tev.registerTask("GUIAgent_0SingleGUIAgentWithInstanceOfGUIEvent","ProcessAGUIEvent");
        	
        	tev.registerTask("GUIAgent_0SingleGUIAgentWithInstanceOfGUIEvent","Task0");
        	
        	tev.registerTask("Agent0_0DeploymentUnitByType1","Task1");
        	
        	tev.registerTask("Agent0_0DeploymentUnitByType1","Task3");
        	
        	tev.registerTask("Agent1_0DeploymentUnitByType2","Task2");
        	
        	tev.registerTask("GUIAgent_0SingleGUIAgentWithInstanceOfGUIEvent","Task4");
        		
        		
        	RetrieveExecutionData cwfe=new 	RetrieveExecutionData();
        	EventManager.getInstance().register(cwfe);
		long step=100;
		long currentTime=0;
		long finishedTime=0;
		long duration=4000;
		long maxtimepercycle=4000;
		
		finishedTime=Math.max(finishedTime,2000);
		 
		MainInteractionManager.goAutomatic(); // tells the agents to start working			
		while (currentTime<finishedTime){
				try {
					Thread.currentThread().sleep(step);
					
				} catch (InterruptedException e) {					
					e.printStackTrace();
				}
				
				if (currentTime%1000 ==0 && currentTime<2000)
					try{
					 msmGUIAgent_0SingleGUIAgentWithInstanceOfGUIEvent.addMentalEntity(new SampleGUIEvent(MentalStateManager.generateMentalEntityID()));
					}  catch (InvalidEntity e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				
				currentTime=currentTime+step;
			}
			
			if (currentTime<duration){
				TestUtils.doNothing(duration-currentTime); // waits for tasks to execute	
			}
					
			
			MainInteractionManager.goManual(); // now it commands to not execute more tasks		
			EventManager.getInstance().unregister(cwfe);
			
		    Vector<String> counterExamples=tev.validatePartialTermination(cwfe,ga,maxtimepercycle);
		    assertTrue("The execution does not match the expected sequences. I found the following counter examples "+counterExamples,counterExamples.isEmpty());		
							
  }
}


 