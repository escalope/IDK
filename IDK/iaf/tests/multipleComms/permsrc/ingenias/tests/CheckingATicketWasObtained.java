

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

import jade.core.*;
import jade.wrapper.StaleProxyException;
import ingenias.editor.entities.MentalEntity;
import ingenias.exception.InvalidEntity;
import ingenias.jade.MentalStateProcessor;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.mental.*;
import ingenias.jade.*;
import ingenias.testing.*;
import ingenias.tests.BasicMASTest;
import ingenias.jade.IAFProperties;


public class CheckingATicketWasObtained extends BasicMASTest {


	@Test
	public void testBuyerAssignment(){			
		// Involved agent local ids for this test are:

		// -BuyerAgent_0multipleBuyers

		// -BuyerAgent_1multipleBuyers

		// -BuyerAgent_2multipleBuyers

		// -BuyerAgent_3multipleBuyers

		// -BuyerAgent_4multipleBuyers

		// -BuyerAgent_5multipleBuyers

		// -BuyerAgent_6multipleBuyers

		// -BuyerAgent_7multipleBuyers

		// -BuyerAgent_8multipleBuyers

		// -BuyerAgent_9multipleBuyers

		// -InterfaceAgent_0multipleInterfaceAgents

		// -InterfaceAgent_1multipleInterfaceAgents

		// -InterfaceAgent_2multipleInterfaceAgents

		// -InterfaceAgent_3multipleInterfaceAgents

		// -InterfaceAgent_4multipleInterfaceAgents

		// -InterfaceAgent_5multipleInterfaceAgents

		// -InterfaceAgent_6multipleInterfaceAgents

		// -InterfaceAgent_7multipleInterfaceAgents

		// -InterfaceAgent_8multipleInterfaceAgents

		// -InterfaceAgent_9multipleInterfaceAgents

		// -SellerAgent1_0multipleSellers

		// -SellerAgent1_1multipleSellers

		// -SellerAgent1_2multipleSellers

		// -SellerAgent1_3multipleSellers

		// -SellerAgent1_4multipleSellers

		// write here the agent id whose 
		// mental state manager you want to get access to
		// MentalStateManager msm = MSMRepository.getInstance().get("MY_AGENT_ID"); // provides access to the
		// For current agents, these are the variables containing their mental states


		String[] buyerNames=new String[]{"BuyerAgent_0multipleBuyers", "BuyerAgent_1multipleBuyers", "BuyerAgent_2multipleBuyers", 
				"BuyerAgent_3multipleBuyers", "BuyerAgent_4multipleBuyers", "BuyerAgent_5multipleBuyers", "BuyerAgent_6multipleBuyers"
				,"BuyerAgent_7multipleBuyers", "BuyerAgent_8multipleBuyers", "BuyerAgent_9multipleBuyers"};

		String[] interfaceNames=new String[]{"InterfaceAgent_0multipleInterfaceAgents", "InterfaceAgent_1multipleInterfaceAgents", "InterfaceAgent_2multipleInterfaceAgents",
				"InterfaceAgent_3multipleInterfaceAgents","InterfaceAgent_4multipleInterfaceAgents","InterfaceAgent_5multipleInterfaceAgents",
				"InterfaceAgent_6multipleInterfaceAgents","InterfaceAgent_7multipleInterfaceAgents",
				"InterfaceAgent_8multipleInterfaceAgents", "InterfaceAgent_9multipleInterfaceAgents"};

		for (String bname:buyerNames){
			System.err.println("Trying "+bname);
			while (MSMRepository.getInstance().get(bname)==null){
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while (MSPRepository.getInstance().get(bname)==null){
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			System.err.println("Gained access to "+bname);
		}

		for (String iname:interfaceNames){

			System.err.println("Trying "+iname);
			while (MSMRepository.getInstance().get(iname)==null){
				try {
					Thread.currentThread().sleep((long)(200*Math.random()));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			while (MSPRepository.getInstance().get(iname)==null){
				try {
					Thread.currentThread().sleep((long)(200*Math.random()));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.err.println("Gained access to "+iname);
		}
		//TestUtils.doNothing(10000);

		MentalStateManager msmBuyerAgent_0multipleBuyers=MSMRepository.getInstance().get("BuyerAgent_0multipleBuyers");


		MentalStateManager msmBuyerAgent_1multipleBuyers=MSMRepository.getInstance().get("BuyerAgent_1multipleBuyers");

		MentalStateManager msmBuyerAgent_2multipleBuyers=MSMRepository.getInstance().get("BuyerAgent_2multipleBuyers");

		MentalStateManager msmBuyerAgent_3multipleBuyers=MSMRepository.getInstance().get("BuyerAgent_3multipleBuyers");

		MentalStateManager msmBuyerAgent_4multipleBuyers=MSMRepository.getInstance().get("BuyerAgent_4multipleBuyers");

		MentalStateManager msmBuyerAgent_5multipleBuyers=MSMRepository.getInstance().get("BuyerAgent_5multipleBuyers");

		MentalStateManager msmBuyerAgent_6multipleBuyers=MSMRepository.getInstance().get("BuyerAgent_6multipleBuyers");

		MentalStateManager msmBuyerAgent_7multipleBuyers=MSMRepository.getInstance().get("BuyerAgent_7multipleBuyers");

		MentalStateManager msmBuyerAgent_8multipleBuyers=MSMRepository.getInstance().get("BuyerAgent_8multipleBuyers");

		MentalStateManager msmBuyerAgent_9multipleBuyers=MSMRepository.getInstance().get("BuyerAgent_9multipleBuyers");

		MentalStateManager msmInterfaceAgent_0multipleInterfaceAgents=MSMRepository.getInstance().get("InterfaceAgent_0multipleInterfaceAgents");

		MentalStateManager msmInterfaceAgent_1multipleInterfaceAgents=MSMRepository.getInstance().get("InterfaceAgent_1multipleInterfaceAgents");

		MentalStateManager msmInterfaceAgent_2multipleInterfaceAgents=MSMRepository.getInstance().get("InterfaceAgent_2multipleInterfaceAgents");

		MentalStateManager msmInterfaceAgent_3multipleInterfaceAgents=MSMRepository.getInstance().get("InterfaceAgent_3multipleInterfaceAgents");

		MentalStateManager msmInterfaceAgent_4multipleInterfaceAgents=MSMRepository.getInstance().get("InterfaceAgent_4multipleInterfaceAgents");

		MentalStateManager msmInterfaceAgent_5multipleInterfaceAgents=MSMRepository.getInstance().get("InterfaceAgent_5multipleInterfaceAgents");

		MentalStateManager msmInterfaceAgent_6multipleInterfaceAgents=MSMRepository.getInstance().get("InterfaceAgent_6multipleInterfaceAgents");

		MentalStateManager msmInterfaceAgent_7multipleInterfaceAgents=MSMRepository.getInstance().get("InterfaceAgent_7multipleInterfaceAgents");

		MentalStateManager msmInterfaceAgent_8multipleInterfaceAgents=MSMRepository.getInstance().get("InterfaceAgent_8multipleInterfaceAgents");

		MentalStateManager msmInterfaceAgent_9multipleInterfaceAgents=MSMRepository.getInstance().get("InterfaceAgent_9multipleInterfaceAgents");



		MentalStateProcessor mspInterfaceAgent_0multipleInterfaceAgents=MSPRepository.getInstance().get("InterfaceAgent_0multipleInterfaceAgents");

		MentalStateProcessor mspInterfaceAgent_1multipleInterfaceAgents=MSPRepository.getInstance().get("InterfaceAgent_1multipleInterfaceAgents");

		MentalStateProcessor mspInterfaceAgent_2multipleInterfaceAgents=MSPRepository.getInstance().get("InterfaceAgent_2multipleInterfaceAgents");

		MentalStateProcessor mspInterfaceAgent_3multipleInterfaceAgents=MSPRepository.getInstance().get("InterfaceAgent_3multipleInterfaceAgents");

		MentalStateProcessor mspInterfaceAgent_4multipleInterfaceAgents=MSPRepository.getInstance().get("InterfaceAgent_4multipleInterfaceAgents");

		MentalStateProcessor mspInterfaceAgent_5multipleInterfaceAgents=MSPRepository.getInstance().get("InterfaceAgent_5multipleInterfaceAgents");

		MentalStateProcessor mspInterfaceAgent_6multipleInterfaceAgents=MSPRepository.getInstance().get("InterfaceAgent_6multipleInterfaceAgents");

		MentalStateProcessor mspInterfaceAgent_7multipleInterfaceAgents=MSPRepository.getInstance().get("InterfaceAgent_7multipleInterfaceAgents");

		MentalStateProcessor mspInterfaceAgent_8multipleInterfaceAgents=MSPRepository.getInstance().get("InterfaceAgent_8multipleInterfaceAgents");

		MentalStateProcessor mspInterfaceAgent_9multipleInterfaceAgents=MSPRepository.getInstance().get("InterfaceAgent_9multipleInterfaceAgents");

		MentalStateProcessor mspBuyerAgent_0multipleBuyers=MSPRepository.getInstance().get("BuyerAgent_0multipleBuyers");

		MentalStateProcessor mspBuyerAgent_1multipleBuyers=MSPRepository.getInstance().get("BuyerAgent_1multipleBuyers");

		MentalStateProcessor mspBuyerAgent_2multipleBuyers=MSPRepository.getInstance().get("BuyerAgent_2multipleBuyers");

		MentalStateProcessor mspBuyerAgent_3multipleBuyers=MSPRepository.getInstance().get("BuyerAgent_3multipleBuyers");

		MentalStateProcessor mspBuyerAgent_4multipleBuyers=MSPRepository.getInstance().get("BuyerAgent_4multipleBuyers");

		MentalStateProcessor mspBuyerAgent_5multipleBuyers=MSPRepository.getInstance().get("BuyerAgent_5multipleBuyers");

		MentalStateProcessor mspBuyerAgent_6multipleBuyers=MSPRepository.getInstance().get("BuyerAgent_6multipleBuyers");

		MentalStateProcessor mspBuyerAgent_7multipleBuyers=MSPRepository.getInstance().get("BuyerAgent_7multipleBuyers");

		MentalStateProcessor mspBuyerAgent_8multipleBuyers=MSPRepository.getInstance().get("BuyerAgent_8multipleBuyers");

		MentalStateProcessor mspBuyerAgent_9multipleBuyers=MSPRepository.getInstance().get("BuyerAgent_9multipleBuyers");



		TestUtils.waitForAgentInitialised(mspInterfaceAgent_0multipleInterfaceAgents);
		TestUtils.waitForAgentInitialised(mspInterfaceAgent_1multipleInterfaceAgents);
		TestUtils.waitForAgentInitialised(mspInterfaceAgent_2multipleInterfaceAgents);
		TestUtils.waitForAgentInitialised(mspInterfaceAgent_3multipleInterfaceAgents);
		TestUtils.waitForAgentInitialised(mspInterfaceAgent_4multipleInterfaceAgents);
		TestUtils.waitForAgentInitialised(mspInterfaceAgent_5multipleInterfaceAgents);
		TestUtils.waitForAgentInitialised(mspInterfaceAgent_6multipleInterfaceAgents);
		TestUtils.waitForAgentInitialised(mspInterfaceAgent_7multipleInterfaceAgents);
		TestUtils.waitForAgentInitialised(mspInterfaceAgent_8multipleInterfaceAgents);
		TestUtils.waitForAgentInitialised(mspInterfaceAgent_9multipleInterfaceAgents);


		TestUtils.waitForAgentInitialised(mspBuyerAgent_0multipleBuyers);
		TestUtils.waitForAgentInitialised(mspBuyerAgent_1multipleBuyers);
		TestUtils.waitForAgentInitialised(mspBuyerAgent_2multipleBuyers);
		TestUtils.waitForAgentInitialised(mspBuyerAgent_3multipleBuyers);
		TestUtils.waitForAgentInitialised(mspBuyerAgent_4multipleBuyers);
		TestUtils.waitForAgentInitialised(mspBuyerAgent_5multipleBuyers);
		TestUtils.waitForAgentInitialised(mspBuyerAgent_6multipleBuyers);
		TestUtils.waitForAgentInitialised(mspBuyerAgent_7multipleBuyers);
		TestUtils.waitForAgentInitialised(mspBuyerAgent_8multipleBuyers);
		TestUtils.waitForAgentInitialised(mspBuyerAgent_9multipleBuyers);



		MainInteractionManager.goAutomatic(); 

		TestUtils.doNothing(20000); // waits for 20 seconds

		printCurrentAssignment(msmBuyerAgent_0multipleBuyers);
		printCurrentAssignment(msmBuyerAgent_1multipleBuyers);
		printCurrentAssignment(msmBuyerAgent_2multipleBuyers);
		printCurrentAssignment(msmBuyerAgent_3multipleBuyers);
		printCurrentAssignment(msmBuyerAgent_4multipleBuyers);
		printCurrentAssignment(msmBuyerAgent_5multipleBuyers);
		printCurrentAssignment(msmBuyerAgent_6multipleBuyers);
		printCurrentAssignment(msmBuyerAgent_7multipleBuyers);
		printCurrentAssignment(msmBuyerAgent_8multipleBuyers);
		printCurrentAssignment(msmBuyerAgent_9multipleBuyers);      			



		checkAssignmentMade(msmBuyerAgent_0multipleBuyers);
		checkAssignmentMade(msmBuyerAgent_1multipleBuyers);
		checkAssignmentMade(msmBuyerAgent_2multipleBuyers);
		checkAssignmentMade(msmBuyerAgent_3multipleBuyers);
		checkAssignmentMade(msmBuyerAgent_4multipleBuyers);
		checkAssignmentMade(msmBuyerAgent_5multipleBuyers);
		checkAssignmentMade(msmBuyerAgent_6multipleBuyers);
		checkAssignmentMade(msmBuyerAgent_7multipleBuyers);
		checkAssignmentMade(msmBuyerAgent_8multipleBuyers);
		checkAssignmentMade(msmBuyerAgent_9multipleBuyers);      			



	}

	private void checkAssignmentMade(
			MentalStateManager msmBuyerAgent_0multipleBuyers) {

		Vector<MentalEntity> assistendagent = msmBuyerAgent_0multipleBuyers.getMentalEntityByType("CurrentAssistedAgent");
		assertTrue(msmBuyerAgent_0multipleBuyers.getAgentName()+ " should have already a piece of information containing the name of the assited agent and there is none",
				!assistendagent.isEmpty()); 
		CurrentAssistedAgent caa=(CurrentAssistedAgent) assistendagent.elementAt(0);

		assertTrue(msmBuyerAgent_0multipleBuyers.getAgentName()+ " should have already an assigned buyer and there was none",
				caa.getinterfaceAgent()!=null && !caa.getinterfaceAgent().equals("")); 
	}

	private void printCurrentAssignment(
			MentalStateManager msmBuyerAgent_0multipleBuyers) {

		Vector<MentalEntity> assistendagent = msmBuyerAgent_0multipleBuyers.getMentalEntityByType("CurrentAssistedAgent");
		CurrentAssistedAgent caa=(CurrentAssistedAgent) assistendagent.elementAt(0);

		System.out.println(msmBuyerAgent_0multipleBuyers.getAgentName()+ " is assigned to "+caa.getinterfaceAgent()); 
	}

}

