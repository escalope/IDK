

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



import ingenias.jade.MentalStateProcessor;
import org.junit.Test;

import ingenias.jade.MentalStateManager;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.mental.*;
import ingenias.testing.*;


public class LearnToTrustTest {
@Test
  public void testDemo() throws Exception{
  			// Involved agent local ids for this test are:
  			  			
        	// -AutonomousColaborator_0AutonomousColaboratorDU
        	  			
        	// -SourcesSupervisor_0SourcesSupervisorDU
        	  			
        	// -SourcesSupervisor_1SourcesSupervisorDU
        	  			
        	// -SourcesManager_0SourcesManagerDU
        	  			
        	// -ResearcherAssistant_0ResearcherAssistantDU
        	  			
        	// -SourcesInspector_0SourcesInspectorDU
        	  			
        	// -SourcesAlfaInspector_0SourcesAlfaInspectorDU
        	
  			// write here the agent id whose 
  			// mental state manager you want to get access to
			// MentalStateManager msm = MSMRepository.getInstance().get("MY_AGENT_ID"); // provides access to the
			// For current agents, these are the variables containing their mental states
			  			
        MentalStateManager msmA = MSMRepository.getInstance().waitFor("ResearcherAssistant_0ResearcherAssistantDU");
        MentalStateProcessor mspA = MSPRepository.getInstance().waitFor("ResearcherAssistant_0ResearcherAssistantDU");
        TestUtils.waitForAgentInitialised(mspA);
        
        MentalStateManager msmB = MSMRepository.getInstance().waitFor("SourcesSupervisor_0SourcesSupervisorDU");
        MentalStateProcessor mspB = MSPRepository.getInstance().waitFor("SourcesSupervisor_0SourcesSupervisorDU");
        TestUtils.waitForAgentInitialised(mspA);

        MainInteractionManager.goAutomatic();

        NuevaPropuestaIntroducidaEvento evento = new NuevaPropuestaIntroducidaEvento();
        evento.setdata("urn:fuente:3");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);




        evento = new NuevaPropuestaIntroducidaEvento();
        evento.setdata("urn:fuente:3");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);


        evento = new NuevaPropuestaIntroducidaEvento();
        evento.setdata("urn:fuente:3");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);


        evento = new NuevaPropuestaIntroducidaEvento();
        evento.setdata("urn:fuente:3");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);

			
//			// mental state of the agent
//			Vector<MentalEntity> frameFactEntitiesBefore = msm
//					.getMentalEntityByType("MY_TYPE"); // Obtains entities of a given type
//
//			assertTrue("There should be two MY_TYPE entities",frameFactEntitiesBefore.size()==2);
//
//			MainInteractionManager.goAutomatic(); // tells the agents to start working
//			TestUtils.doNothing(1000); // waits for 1 second
//			Vector<MentalEntity> frameFactEntitiesAfter = msm
//					.getMentalEntityByType("MY_TYPE");
//
//			assertTrue("There should be no any MY_TYPE entities",
//			  frameFactEntitiesAfter.size() == 0); // checks that all entities of type MY_TYPE
//			  // do not exist 		
  }
}

 