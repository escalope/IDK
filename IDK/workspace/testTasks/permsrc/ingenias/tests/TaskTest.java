
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

import static org.junit.Assert.assertTrue;
import ingenias.editor.entities.MentalEntity;
import ingenias.jade.MentalStateManager;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.testing.MSMRepository;
import ingenias.testing.TestUtils;

import java.util.Vector;

import org.junit.Test;

public class TaskTest {

	@Test
	public void testDemo() {
		try {
						
			MentalStateManager msm = MSMRepository.getInstance().get("A_0");
			Vector<MentalEntity> frameFactEntitiesBefore = msm
					.getMentalEntityByType("AFrameFact");
			Vector<MentalEntity> anotherFrameFactEntitiesBefore = msm
					.getMentalEntityByType("AnotherFrameFact");			
			assertTrue("There should be two AFrameFact entities before execution",
					frameFactEntitiesBefore.size() == 2);
			assertTrue("There should be none AnotherFrameFact entities after execution",
					anotherFrameFactEntitiesBefore.size() == 0);
			MainInteractionManager.goAutomatic();
			TestUtils.doNothing(2000);
			Vector<MentalEntity> frameFactEntitiesAfter = msm
					.getMentalEntityByType("AFrameFact");
			Vector<MentalEntity> anotherFrameFactEntitiesAfter = msm
					.getMentalEntityByType("AnotherFrameFact");
			assertTrue("There should be none AFrameFact entities after execution",
					frameFactEntitiesAfter.size() == 0);
			assertTrue("There should be exactly one AnotherFrameFact entities after execution",
					anotherFrameFactEntitiesAfter.size() == 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
