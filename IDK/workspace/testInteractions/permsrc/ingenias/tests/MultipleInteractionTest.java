

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

import java.util.Enumeration;
import java.util.Vector;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import jade.core.*;
import jade.wrapper.StaleProxyException;
import jade.wrapper.State;
import ingenias.editor.entities.MentalEntity;
import ingenias.exception.TimeOut;
import ingenias.jade.MentalStateManager;
import ingenias.jade.MentalStateProcessor;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.mental.*;
import ingenias.testing.*;
import ingenias.jade.IAFProperties;


public class MultipleInteractionTest {
	@Test
	public void testDemo() throws TimeOut{			

		IAFProperties.setGarbageCollectionInterval(100);
		
		
		// initiator
		MentalStateManager msmA = MSMRepository.getInstance().waitFor("A_0"); 
		MentalStateProcessor mspA = MSPRepository.getInstance().waitFor("A_0"); 
		TestUtils.waitForAgentInitialised(mspA);

		// participant 1
		MentalStateManager msmB0 = MSMRepository.getInstance().waitFor("B_0"); 
		MentalStateProcessor mspB0 = MSPRepository.getInstance().waitFor("B_0");		
		TestUtils.waitForAgentInitialised(mspB0);

		// participant 2			
		MentalStateManager msmC0 = MSMRepository.getInstance().waitFor("C_0"); 			
		MentalStateProcessor mspC0 = MSPRepository.getInstance().waitFor("C_0"); 						
		TestUtils.waitForAgentInitialised(mspC0);

		MentalStateManager msmC1 = MSMRepository.getInstance().waitFor("C_1"); 			
		MentalStateProcessor mspC1 = MSPRepository.getInstance().waitFor("C_1"); 						
		TestUtils.waitForAgentInitialised(mspC1);

		MentalStateManager msmC2 = MSMRepository.getInstance().waitFor("C_2"); 			
		MentalStateProcessor mspC2 = MSPRepository.getInstance().waitFor("C_2"); 						
		TestUtils.waitForAgentInitialised(mspC2);

		MentalStateManager msmC3 = MSMRepository.getInstance().waitFor("C_3"); 			
		MentalStateProcessor mspC3 = MSPRepository.getInstance().waitFor("C_3"); 						
		TestUtils.waitForAgentInitialised(mspC2);

		MentalStateManager msmC4 = MSMRepository.getInstance().waitFor("C_4"); 			
		MentalStateProcessor mspC4 = MSPRepository.getInstance().waitFor("C_4"); 						
		TestUtils.waitForAgentInitialised(mspC2);


		assertTrue("There should be once instance of  InitialFact",msmA
				.getMentalEntityByType("InitialFact").size()==1);

		MainInteractionManager.goAutomatic(); // tells the agents to start working
		TestUtils.doNothing(4000); // waits for 2 second and the interaction should be finished by then

		/*System.err.println("ALL B_0:"+msmB0.getAllMentalEntities());
		System.err.println("ALL C_4:"+msmC4.getAllMentalEntities());
		System.err.println("ALL C_0:"+msmC0.getAllMentalEntities());
		System.err.println("ALL A_0:"+msmA.getAllMentalEntities());*/

		//*************************************************
		// Agent B0
		//*************************************************
		
					
		ingenias.editor.entities.RuntimeConversation conv= TestUtils.checkFirstConversation(msmB0,"B_0","FINISHED");						
		TestUtils.checkExistenceMEWithinConv(conv, "SingleAnswer", "B_0",1);			
		TestUtils.checkNOExistenceMEWithinMS(msmB0, "SingleAnswer", "B_0");			
		TestUtils.checkNOExistenceMEWithinConv(conv, "SingleQuestion","B_0");
		TestUtils.checkNOExistenceMEWithinMS(msmB0, "SingleQuestion","B_0");

		//*************************************************
		// Agent A0
		//*************************************************
		
		conv = TestUtils.checkFirstConversation(msmA,"A_0","FINISHED");

					
		TestUtils.checkNOExistenceMEWithinMS(msmA, "InitialQuestion", "A_0");
		TestUtils.checkExistenceMEWithinConv(conv, "InitialQuestion", "A_0",1);

		TestUtils.checkNOExistenceMEWithinMS(msmA, "MultipleAnswer", "A_0");
		TestUtils.checkNOExistenceMEWithinConv(conv, "MultipleAnswer", "A_0");

		TestUtils.checkNOExistenceMEWithinMS(msmA, "SingleQuestion", "A_0");
		TestUtils.checkExistenceMEWithinConv(conv, "SingleQuestion", "A_0",1);

		TestUtils.checkNOExistenceMEWithinMS(msmA, "InitialFact", "A_0");
		TestUtils.checkNOExistenceMEWithinConv(conv, "InitialFact", "A_0");


		//*************************************************
		// Agent C0
		//*************************************************
		
		System.err.println("ALL C_0:"+msmC0.getAllMentalEntities());
		
		conv = TestUtils.checkFirstConversation(msmC0,"C_0","FINISHED");

		checkAgent(msmC0, conv,"C_0");

		//*************************************************
		// Agent C1
		//*************************************************
		
		conv = TestUtils.checkFirstConversation(msmC1,"C_1","FINISHED");
		checkAgent(msmC1, conv,"C_1");
		
		//*************************************************
		// Agent C2
		//*************************************************
		
		conv = TestUtils.checkFirstConversation(msmC2,"C_2","FINISHED");
		checkAgent(msmC2, conv,"C_2");
		
		//*************************************************
		// Agent C3
		//*************************************************
		
		conv = TestUtils.checkFirstConversation(msmC3,"C_3","FINISHED");
		checkAgent(msmC3, conv,"C_3");
		
		//*************************************************
		// Agent C4
		//*************************************************
		
		conv = TestUtils.checkFirstConversation(msmC4,"C_4","FINISHED");
		checkAgent(msmC4, conv,"C_4");
		

	}

	private void checkAgent(MentalStateManager msmC0,
			ingenias.editor.entities.RuntimeConversation conv,String agentName) {
		Vector<MentalEntity> conversations;
		conversations = msmC0.getMentalEntityByType("RuntimeConversation");
		assertTrue("There should be only one conversation in agent "+agentName,(conversations.size()==1));
		assertTrue("There should be only one aborted conversation in agent "+agentName+" and current one is "+((ingenias.editor.entities.RuntimeConversation)conversations.elementAt(0)).State,
				((ingenias.editor.entities.RuntimeConversation)conversations.elementAt(0)).State.equalsIgnoreCase("FINISHED"));

		TestUtils.checkNOExistenceMEWithinMS(msmC0, "MultipleAnswer", agentName);
		TestUtils.checkExistenceMEWithinConv(conv, "MultipleAnswer", agentName,1);

		TestUtils.checkNOExistenceMEWithinMS(msmC0, "InitialQuestion", agentName);
		TestUtils.checkNOExistenceMEWithinConv(conv, "InitialQuestion", agentName);
	}



}

