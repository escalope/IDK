/*
    Copyright (C) 2013 Jorge Gomez Sanz

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
package ingenias.macros;

import static org.testng.AssertJUnit.assertTrue;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;


import org.testng.annotations.Test;

import ingenias.codeproc.macros.MacroTaskPConnects;
import ingenias.codeproc.macros.MacroWorkflowsToInteractions;
import ingenias.editor.Log;
import ingenias.editor.persistence.PersistenceManager;
import ingenias.exception.CannotLoad;
import ingenias.exception.DamagedFormat;
import ingenias.exception.NotFound;
import ingenias.exception.NullEntity;
import ingenias.exception.TransformationException;
import ingenias.exception.UnknowFormat;
import ingenias.generator.browser.Browser;
import ingenias.generator.browser.BrowserImp;

public class MacrosTest {


	@Test
	public void testTaskPConnects() throws UnknowFormat, DamagedFormat, CannotLoad, IOException, NullEntity, NotFound, TransformationException{
		Log.initInstance(new PrintWriter(System.out));
		Browser browser=BrowserImp.initialise("src/test/resources/macrostest/testpconnects.xml");
		assertTrue(browser.findEntity("Task0").getAllRelationships("WFConsumes").isEmpty());
		new MacroTaskPConnects(browser).apply();
		assertTrue("Task0 entity ought to exist", browser.findEntity("Task0")!=null);
		assertTrue("Task0 entity ought to exist and be a task", browser.findEntity("Task0").getType().equals("Task"));
		assertTrue(browser.findEntity("Task0").getAllRelationships("PConnects").get(0).getRoles("PConnectssource")[0].
				getPlayer().getID().equalsIgnoreCase("ProcessAGUIEvent"));
		assertTrue(browser.findEntity("Task0").getAllRelationships("PConnects").get(0).
		getRoles("PConnectssource")[0].getAttributeByName("TaskOutput").
		getCollectionValue().getElementAt(0).getAttributeByName("AffectedElement").getEntityValue().
		getID().equalsIgnoreCase("MyFrameFact"));
		assertTrue(browser.findEntity("Task0").getAllRelationships("PConnects").get(0).
				getRoles("PConnectssource")[0].getAttributeByName("TaskOutput").
				getCollectionValue().getElementAt(0).getAttributeByName("Operation").getSimpleValue().
				equalsIgnoreCase("+"));
		assertTrue(browser.findEntity("Task0").getAllRelationships("PConnects").get(0).
				getRoles("PConnectstarget")[0].getAttributeByName("TaskInput").
				getCollectionValue().getElementAt(0).getAttributeByName("Operation").getSimpleValue().
				equalsIgnoreCase("-"));
		assertTrue(browser.findEntity("Task0").getAllRelationships("PConnects").get(0).
				getRoles("PConnectstarget")[0].getAttributeByName("TaskInput").
				getCollectionValue().getElementAt(0).getAttributeByName("AffectedElement").getEntityValue().
				getID().equalsIgnoreCase("MyFrameFact"));
		assertTrue(browser.findEntity("Task0").getAllRelationships("WFConsumes")!=null);
		assertTrue("There should be a WFConsumes relationship and there are "+
					browser.findEntity("Task0").getAllRelationships("WFConsumes").size(),
					browser.findEntity("Task0").getAllRelationships("WFConsumes").size()==1);
		assertTrue(browser.findEntity("Task0").getAllRelationships("WFConsumes").get(0).
				getRoles("WFConsumestarget")[0].getPlayer().getID().equalsIgnoreCase("MyFrameFact"));
		PersistenceManager pm=new PersistenceManager();
		pm.save(new File("target/transformed_testpconnects.xml"), browser.getState());
	}
	
	@Test 
	public void testWorkflow() throws UnknowFormat, DamagedFormat, CannotLoad, IOException, TransformationException{
		Log.initInstance(new PrintWriter(System.out));
		Browser browser=BrowserImp.initialise("src/test/resources/macrostest/testpconnectsworkflow.xml");
		Vector<String> errors=new MacroWorkflowsToInteractions(browser).apply();
		PersistenceManager pm=new PersistenceManager();
		pm.save(new File("target/transformed_testpconnectsworkflow.xml"), browser.getState());
	}
	
	@Test 
	public void testWorkflowOutsideReferences() throws UnknowFormat, DamagedFormat, CannotLoad, IOException, TransformationException{
		Log.initInstance(new PrintWriter(System.out));
		Browser browser=BrowserImp.initialise("src/test/resources/macrostest/testpconnectsworkflow_outsidereferences.xml");
		Vector<String> errors=new MacroWorkflowsToInteractions(browser).apply();
		PersistenceManager pm=new PersistenceManager();
		pm.save(new File("target/transformed_testpconnectsworkflow.xml"), browser.getState());
	}

}
