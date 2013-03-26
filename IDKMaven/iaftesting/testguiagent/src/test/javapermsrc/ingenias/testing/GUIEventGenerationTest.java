

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

import static org.junit.Assert.*;

import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.core.EmergencyAbortListener;
import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.finder.FrameFinder;
import org.fest.swing.finder.WindowFinder;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.timing.Pause;
import org.fest.swing.timing.Timeout;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jade.core.*;
import jade.wrapper.StaleProxyException;
import ingenias.editor.entities.MentalEntity;
import ingenias.exception.TimeOut;
import ingenias.jade.MentalStateManager;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.mental.*;
import ingenias.jade.IAFProperties;
import ingenias.testing.BasicMASTest;
import ingenias.testing.fest.*;

import static ingenias.testing.fest.DraggableTabbedPaneFixtureExtension.draggableTabbedPaneWithName;
import static ingenias.testing.fest.JGraphFixtureExtension.jgraphWithName;

public class GUIEventGenerationTest extends TryingSingleAgentGUIDebuggingForGUIEventGeneration {

	private static final int _NUMBER_OF_EVENT_GENERATION_INTENTS = 30;
	private FrameFixture window;
	private EmergencyAbortListener listener;  
	private MainInteractionManager frame;


	@BeforeTest
	@Override
	public void agentSetup() throws StaleProxyException{		
		IAFProperties.setGraphicsOn(true); // disable graphics	
		listener = EmergencyAbortListener.registerInToolkit();
		//FailOnThreadViolationRepaintManager.install();

		frame = GuiActionRunner.execute(new GuiQuery<MainInteractionManager>() {
			protected MainInteractionManager executeInEDT() {				
				return MainInteractionManager.getInstance();
			}
		});		
		window = new FrameFixture(frame);		
		window.show(); // it shows the frame to test
		window.resizeWidthTo(1000);
		window.resizeHeightTo(700);		
		super.agentSetup();
		// customize MAS Setup method herer
	}


	@AfterTest
	public void endTest() throws StaleProxyException{
		super.endTest();
		listener.unregister(); 
		window.close();
		window.cleanUp();
		// customize MAS shutdown here. The default behavior is a kill signal sent to the main container
	};

	@Test
	@GUITest
	public void testDemo() throws TimeOut{			
		
		MSMRepository.getInstance().waitFor("GUIAgent_0SingleGUIAgent");
		
		MentalStateManager msmHelloWorldAgent_0HelloWorldAgentCreation=
				MSMRepository.getInstance().get("GUIAgent_0SingleGUIAgent");
		
		checkSimulatedEventGeneration(msmHelloWorldAgent_0HelloWorldAgentCreation); 
		
		window.tabbedPane("debuggingtabs").selectTab("Mental State").click();
		window.button("GUIAgent_0SingleGUIAgent-showmentalstate").click();
		
		window.tabbedPane("debuggingtabs").selectTab("Mental State");
		window.with(draggableTabbedPaneWithName("debuggingtabs")).withDraggableTabPane("Mental State").moveAway();
		WindowFinder.findFrame(matchWindowName("Mental State")).using(window.robot).resizeHeightTo(200).resizeWidthTo(400);
		
		
		window.button("manualgo").click(); // we start the test with the system in manual-execution-mode
		
		// now with an opened GUI showing the mental state		
		checkSimulatedEventGeneration(msmHelloWorldAgent_0HelloWorldAgentCreation);


		
	}
	

	public static GenericTypeMatcher<JFrame> matchWindowName(final String text) {
		return new GenericTypeMatcher<JFrame>(JFrame.class) {
			@Override
			protected boolean isMatching(JFrame component) {
				if (component.getTitle()!=null)
					return component.getTitle().equalsIgnoreCase(text) && component.isVisible();
				else
					return false;
			}
			public String toString(){
				return "matching buttons with text "+text;
			}
		};
	}



	private void checkSimulatedEventGeneration(
			MentalStateManager msmHelloWorldAgent_0HelloWorldAgentCreation) {
		
		
		window.tabbedPane("debuggingtabs").selectTab("Applications").click();

		window.button("GUI-generate-SampleGUIEvent").click();
		
		Pause.pause(500);
		
		Vector<MentalEntity> frameFactEntitiesAfter = msmHelloWorldAgent_0HelloWorldAgentCreation
				.getMentalEntityByType("SampleGUIEvent");
		
		assertTrue("There should be one SampleGUIEvent entities and there are "+
				frameFactEntitiesAfter.size(),
				frameFactEntitiesAfter.size()==1); 
		
		window.button("automaticgo").click();
		
		Pause.pause(500);
		
		frameFactEntitiesAfter = msmHelloWorldAgent_0HelloWorldAgentCreation
				.getMentalEntityByType("SampleGUIEvent");
		
		assertTrue("There should be zero SampleGUIEvent entities and there are "+
				frameFactEntitiesAfter.size(),
				frameFactEntitiesAfter.isEmpty()); 
		
		for (int k=0;k<_NUMBER_OF_EVENT_GENERATION_INTENTS;k++) // click one hundred times
		 window.button("GUI-generate-SampleGUIEvent").click();
		
		Pause.pause(5000);
		
		frameFactEntitiesAfter = msmHelloWorldAgent_0HelloWorldAgentCreation
				.getMentalEntityByType("SampleGUIEvent");
		
		assertTrue("There should be zero SampleGUIEvent entities and there are "+
				frameFactEntitiesAfter.size(),
				frameFactEntitiesAfter.isEmpty()); 
		
		window.button("manualgo").click();
		
		for (int k=0;k<_NUMBER_OF_EVENT_GENERATION_INTENTS;k++) // click one hundred times
			 window.button("GUI-generate-SampleGUIEvent").click();
		
		window.button("automaticgo").click();
		
		Pause.pause(5000);
		
		frameFactEntitiesAfter = msmHelloWorldAgent_0HelloWorldAgentCreation
				.getMentalEntityByType("SampleGUIEvent");
		
		assertTrue("There should be zero SampleGUIEvent entities and there are "+
				frameFactEntitiesAfter.size(),
				frameFactEntitiesAfter.isEmpty());
	}			

}


 
