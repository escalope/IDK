package test;

import static ingenias.testing.fest.JGraphFixtureExtension.jgraphWithName;
import static org.testng.AssertJUnit.assertTrue;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import org.testng.annotations.Test;

import ingenias.editor.IDE;
import ingenias.editor.widget.CustomJTextField;
import ingenias.exception.CannotLoad;
import ingenias.exception.DamagedFormat;
import ingenias.exception.UnknowFormat;
import ingenias.generator.browser.Browser;
import ingenias.generator.browser.BrowserImp;
import ingenias.generator.util.FileUtils;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.core.BasicComponentFinder;
import org.fest.swing.core.ComponentFinder;
import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.finder.ComponentFinderTemplate;
import org.fest.swing.fixture.ComponentFixture;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.timing.Pause;
import org.fest.swing.timing.Timeout;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class SaveEnableTest {


	private Robot robot;
	private FrameFixture window;

	private IDE frame;


	private String anotherFileSpec=System.getProperty("user.dir")+"/target/anothersamplespec.xml";
	
	public static final String firstDiagram="Hello world agent definition";
	public static final String secondDiagram="Task description";
	public static final String thirdDiagram="Task Code";

	@BeforeMethod (alwaysRun=true)
	public void setUpOnce() {
		//FailOnThreadViolationRepaintManager.install();

		frame = GuiActionRunner.execute(new GuiQuery<IDE>() {
			protected IDE executeInEDT() {
				IDE ide=new IDE();
				ide.launchIDE(new String[]{});
				ide.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				ide.removeExitAction();
				return ide; 
			}
		});		
		window = new FrameFixture(frame);		
		window.show(); // it shows the frame to test
		window.resizeWidthTo(1000);
		window.resizeHeightTo(700);		


	}


	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		window.close();
		window.cleanUp();
	}
	
	@Test
	@GUITest
	public void checkSaveEnabledMenu(){
		new File(anotherFileSpec).delete();// to delete the testing spec if it existed
		window.requireVisible();
		window.menuItemWithPath("File", "New").click();
		
		try {
			window.dialog(Timeout.timeout(2000)).requireModal().requireVisible(); 
			Assert.fail("No dialog should have appeared");
		}catch (org.fest.swing.exception.WaitTimedOutError t){
			// it is correct because no dialog should appear. It is assumed
			// the editor starts always with an unmodified spec with respect to disk
		}
		
		window.menuItemWithPath("File", "Save").requireDisabled();
		
		window.menuItemWithPath("Preferences","Edit Properties Mode", "Edit Properties in a PopUp Window" ).click();
		window.tree("ProjectsTree").selectRow(0).showPopupMenu().menuItemWithPath("Add AgentModel").click();
		window.optionPane(Timeout.timeout(3000)).requireVisible().requireTitle("New graph").textBox().setText(firstDiagram);
		window.optionPane(Timeout.timeout(3000)).requireVisible().requireTitle("New graph").okButton().click();
		
		window.menuItemWithPath("File", "Save").requireEnabled();
		
		window.menuItemWithPath("File","Save").click();
		window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().textBox().setText(anotherFileSpec);
		window.dialog(Timeout.timeout(3000)).button(matchButtonName("Save")).click();		
		window.menuItemWithPath("File","Save").click(); // this is a bug
		window.optionPane(Timeout.timeout(3000)).yesButton().click();
		
		window.menuItemWithPath("File", "Save").requireDisabled();
		
		window.tree("ProjectsTree").selectRow(1);
		window.tree("ProjectsTree").doubleClickRow(1);
		
		window.menuItemWithPath("File", "Save").requireDisabled();
		
		window.button("Agent").click();		
		window.with(jgraphWithName(firstDiagram)).withDGC("Agent0").selectDGC().moveAway();
		
		window.menuItemWithPath("File", "Save").requireEnabled();	
		
		window.menuItemWithPath("File", "Save").click();
		window.dialog(Timeout.timeout(2000)).requireModal().requireVisible(); // it will ask to confirm overwrite
		window.optionPane().yesButton().click();
		
		window.menuItemWithPath("File", "Save").requireDisabled();
		
		window.with(jgraphWithName(firstDiagram)).withDGC("Agent0").selectDGC().doubleClick();
		window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().textBox("id").setText("Agent1");
		window.dialog(Timeout.timeout(3000)).button("accept").click();
		window.menuItemWithPath("File", "Save").requireEnabled();	
		
		window.menuItemWithPath("File", "Save").click();
		window.dialog(Timeout.timeout(2000)).requireModal().requireVisible(); // it will ask to confirm overwrite
		window.optionPane().yesButton().click();
		
		window.menuItemWithPath("File", "Save").requireDisabled();
		
	}




	public static GenericTypeMatcher<JButton> matchButtonName(final String text) {
		return new GenericTypeMatcher<JButton>(JButton.class) {
			@Override
			protected boolean isMatching(JButton component) {
				if (component.getText()!=null)
				 return component.getText().equals(text);
				else
					return false;
			}
			public String toString(){
				return "matching buttons with text "+text;
			}
		};
	}



}
