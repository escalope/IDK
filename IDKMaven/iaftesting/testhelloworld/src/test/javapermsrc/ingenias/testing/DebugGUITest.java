

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

import javax.swing.JFrame;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.core.EmergencyAbortListener;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.timing.Pause;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jade.core.*;
import jade.wrapper.StaleProxyException;
import ingenias.editor.IDE;
import ingenias.editor.entities.MentalEntity;
import ingenias.exception.TimeOut;
import ingenias.jade.EventManager;
import ingenias.jade.MentalStateManager;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.mental.*;
import ingenias.jade.IAFProperties;
import ingenias.testing.BasicMASTest;
import ingenias.testing.fest.*;

public class DebugGUITest extends DebugGUITestingDeploymentForDebugGUI{
	private Robot robot;
	private FrameFixture window;
	private EmergencyAbortListener listener;  
	private MainInteractionManager frame;


	@BeforeMethod
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


	@AfterMethod
	@Override
	public void endTest() throws StaleProxyException{
		super.endTest();
		listener.unregister(); 
		window.close();
		window.cleanUp();
		// customize MAS shutdown here. The default behavior is a kill signal sent to the main container
		
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};

	@Test
	@GUITest
	public void testDemo() throws TimeOut{			
		
		MSMRepository.getInstance().waitFor("HelloWorldAgent_0HelloWorldAgentCreation");
		
		MentalStateManager msmHelloWorldAgent_0HelloWorldAgentCreation=
				MSMRepository.getInstance().get("HelloWorldAgent_0HelloWorldAgentCreation");
		
		window.button("automaticgo").click();
		
		Pause.pause(1000);
		
		Vector<MentalEntity> frameFactEntitiesAfter = msmHelloWorldAgent_0HelloWorldAgentCreation
				.getMentalEntityByType("DontForgetToGreet");

		assertTrue("There should be cero DontForgetToGreet entities and there are "+
				frameFactEntitiesAfter.size(),
				frameFactEntitiesAfter.isEmpty()); 		
 		
	}
}

