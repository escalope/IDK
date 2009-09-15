

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
import jade.wrapper.State;
import ingenias.editor.entities.MentalEntity;
import ingenias.exception.TimeOut;
import ingenias.jade.MentalStateManager;
import ingenias.jade.MentalStateProcessor;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.mental.*;
import ingenias.testing.MSMRepository;
import ingenias.testing.MSPRepository;
import ingenias.testing.TestUtils;
import ingenias.tests.*;



public class SingleInputTaskTest {
@Test
  public void testDemo() throws TimeOut{			
  			
  			// write here the agent id whose 
  			// mental state manager you want to get access to
			MentalStateManager msm = MSMRepository.getInstance().waitFor("A_0DeploymentUnitByTypeEnumInitMS1"); // provides access to the
			// mental state of the agent
			MentalStateProcessor msp = MSPRepository.getInstance().waitFor("A_0DeploymentUnitByTypeEnumInitMS1"); 
			TestUtils.waitForAgentInitialised(msm);

			Vector<MentalEntity> frameFactEntitiesBefore = msm
					.getMentalEntityByType("SingleInputTaskFact"); // Obtains entities of a given type
						
			assertTrue("There should be two SingleInputTaskFact entities, and this is the actual content "+frameFactEntitiesBefore,frameFactEntitiesBefore.size()==2);
						
			MainInteractionManager.goAutomatic(); // tells the agents to start working
			TestUtils.doNothing(500); // waits for 1 second
			Vector<MentalEntity> frameFactEntitiesAfter = msm
					.getMentalEntityByType("SingleInputTaskFact");
					
			assertTrue("There should be no any SingleInputTaskFact entities and there are "+frameFactEntitiesAfter.size(),
			  frameFactEntitiesAfter.size() == 0); // checks that all entities of type MY_TYPE
			  // do not exist 			
  }
}

 