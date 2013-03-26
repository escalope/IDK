

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

import org.fest.assertions.Fail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import jade.core.*;
import jade.lang.acl.ACLMessage;
import jade.wrapper.StaleProxyException;
import ingenias.editor.entities.MentalEntity;
import ingenias.exception.TimeOut;
import ingenias.jade.AgentEventAdapter;
import ingenias.jade.EventManager;
import ingenias.jade.MentalStateManager;
import ingenias.jade.comm.ActiveConversation;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.mental.*;
import ingenias.testing.*;
import ingenias.jade.IAFProperties;
import ingenias.testing.BasicMASTest;

/**
 * It is assumed the following organization
 * org0 <- group0 <- group10 <- Agent0_0DeploymentUnitByType0
 *     +<- group01 <- group11 <- Agent0_0DeploymentUnitByType1, Agent1_0DeploymentUnitByType2
 * 	   +<- group2 <- Agent1_0DeploymentUnitByType2
 * 
 * org1 <- group3 <- Agent1_0DeploymentUnitByType3
 * 
 *  
 * @author superjj
 *
 */
public class TestingOrgInteractionTest extends TestingPackage0ForTestingOrgInteraction {
	
	private class InteracionStateMachine extends AgentEventAdapter {
		int startingInteractionCounter=0;
		public int getStartingInteractionCounter() {
			return startingInteractionCounter;
		}

		@Override
		public  synchronized void startingInteractionAsCollaborator(String agentid,
				String agentType, ACLMessage acl) {
			super.startingInteractionAsCollaborator(agentid, agentType, acl);
			if (acl.getProtocol().equalsIgnoreCase("Interaction0")){
				// started by Agent0_0DeploymentUnitByType0 will connect with Agent1_0DeploymentUnitByType2, but not with Agent1_0DeploymentUnitByType3
				if (agentid.equals("Agent1_0DeploymentUnitByType3")){
					// this agent should not be chosen despise having a compatible role, because it belongs to another org
					Fail.fail("Agent "+agentid+ " should not be chosen " +
							"despite having a compatible role, because it belongs to another org");
				} else
					startingInteractionCounter++;
			}
		}

		@Override
		public void startingInteractionAsInitiator(String localName,
				String substring, ActiveConversation aconv) {
			super.startingInteractionAsInitiator(localName, substring, aconv);
			if (aconv.getConv().getType().equalsIgnoreCase("Interaction0")){
				// started by Agent0_0DeploymentUnitByType0 or Agent0_0DeploymentUnitByType3
				// Agent0_0DeploymentUnitByType0 will succeed, but Agent0_0DeploymentUnitByType3 will fail to find collaborators
				
			}
			
		}
		
	}

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
		
			  			
        	// -Agent0_0DeploymentUnitByType3
        	MentalStateManager msmAgent0_0DeploymentUnitByType3=MSMRepository.getInstance().waitFor("Agent1_0DeploymentUnitByType3");
        	  			
        	// -Agent1_0DeploymentUnitByType2
        	MentalStateManager msmAgent1_0DeploymentUnitByType2=MSMRepository.getInstance().waitFor("Agent1_0DeploymentUnitByType2");
        	  			
        	// -Agent0_0DeploymentUnitByType1
        	MentalStateManager msmAgent0_0DeploymentUnitByType1=MSMRepository.getInstance().waitFor("Agent0_0DeploymentUnitByType1");
        	  			
        	// -Agent0_0DeploymentUnitByType0
        	MentalStateManager msmAgent0_0DeploymentUnitByType0=MSMRepository.getInstance().waitFor("Agent0_0DeploymentUnitByType0");
        	
        	InteracionStateMachine listener = new InteracionStateMachine();
			EventManager.getInstance().register(listener);
						
			MainInteractionManager.goAutomatic(); // tells the agents to start working
			TestUtils.doNothing(2000); // waits for 1 second
			// if there are no failures, then, it is ok.
			assertTrue("There ought to have been 2 starting-interaction events, and there were "+listener.getStartingInteractionCounter(), 
					listener.getStartingInteractionCounter()==2);
  }
}


 