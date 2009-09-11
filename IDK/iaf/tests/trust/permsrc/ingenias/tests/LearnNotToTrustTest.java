

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
import ingenias.jade.MentalStateManager;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.mental.*;
import ingenias.testing.MSMRepository;
import ingenias.testing.TestUtils;
import ingenias.testing.*;
import ingenias.jade.IAFProperties;


public class LearnNotToTrustTest {
@Test
  public void testDemo(){			
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
			  			
        	MentalStateManager msmAutonomousColaborator_0AutonomousColaboratorDU=MSMRepository.getInstance().get("AutonomousColaborator_0AutonomousColaboratorDU");
        	  			
        	MentalStateManager msmSourcesSupervisor_0SourcesSupervisorDU=MSMRepository.getInstance().get("SourcesSupervisor_0SourcesSupervisorDU");
        	  			
        	MentalStateManager msmSourcesSupervisor_1SourcesSupervisorDU=MSMRepository.getInstance().get("SourcesSupervisor_1SourcesSupervisorDU");
        	  			
        	MentalStateManager msmSourcesManager_0SourcesManagerDU=MSMRepository.getInstance().get("SourcesManager_0SourcesManagerDU");
        	  			
        	MentalStateManager msmResearcherAssistant_0ResearcherAssistantDU=MSMRepository.getInstance().get("ResearcherAssistant_0ResearcherAssistantDU");
        	  			
        	MentalStateManager msmSourcesInspector_0SourcesInspectorDU=MSMRepository.getInstance().get("SourcesInspector_0SourcesInspectorDU");
        	  			
        	MentalStateManager msmSourcesAlfaInspector_0SourcesAlfaInspectorDU=MSMRepository.getInstance().get("SourcesAlfaInspector_0SourcesAlfaInspectorDU");
        	
			
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

 