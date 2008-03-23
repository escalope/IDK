

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
import ingenias.jade.AgentStates;
import ingenias.jade.MentalStateManager;
import ingenias.jade.MentalStateProcessor;
import ingenias.jade.components.Task;
import ingenias.jade.exception.TaskException;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.mental.*;
import ingenias.testing.MSMRepository;
import ingenias.testing.MSPRepository;
import ingenias.testing.TestUtils;
import ingenias.tests.*;



public class MultipleInputTaskTest {
@Test
  public void testDemo() throws TimeOut{			
  			
	MentalStateManager msm = MSMRepository.getInstance().waitFor("A_0");
	MentalStateProcessor msp = MSPRepository.getInstance().waitFor("A_0");
	
	while (msp.getState()==AgentStates.PLANNING_STARTING){
		try {
			Thread.currentThread().sleep(100);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
	
	Vector<MentalEntity> goals = msm.getMentalEntityByType("StateGoal");
	assertTrue("There should be one goal and there were " +goals.size(),
			goals.size() == 1);
	Vector<MentalEntity> frameFactEntitiesBefore = msm
			.getMentalEntityByType("AFrameFact");
	Vector<MentalEntity> anotherFrameFactEntitiesBefore = msm
			.getMentalEntityByType("AnotherFrameFact");			
	assertTrue("There should be two AFrameFact entities before execution and there were " +frameFactEntitiesBefore.size(),
			frameFactEntitiesBefore.size() == 2);
	assertTrue("There should be none AnotherFrameFact entities after execution and there were "+anotherFrameFactEntitiesBefore.size(),
			anotherFrameFactEntitiesBefore.size() == 0);
	
	Vector<Task> tasks = msp.getScheduledTasks();
	try {
		Vector<Task> activeTasks=msp.activeTasks();
		assertTrue("There should be two scheduled tasks and there are the following " +tasks,
				tasks.size()==2);
	} catch (TaskException e) {
		
		e.printStackTrace();
	}

	MainInteractionManager.goAutomatic();
	
	TestUtils.doNothing(500);
	
	Vector<MentalEntity> frameFactEntitiesAfter = msm
			.getMentalEntityByType("AFrameFact");
	Vector<MentalEntity> anotherFrameFactEntitiesAfter = msm
			.getMentalEntityByType("AnotherFrameFact");

	assertTrue("There should be none AFrameFact entities after execution  and there were " +frameFactEntitiesAfter.size()+" "+tasks.size()+" "+tasks,
			frameFactEntitiesAfter.size() == 0);
	assertTrue("There should be none AnotherFrameFact entities after execution  and there were " +anotherFrameFactEntitiesAfter.size(),
			anotherFrameFactEntitiesAfter.size() == 0);	
  }
}

 