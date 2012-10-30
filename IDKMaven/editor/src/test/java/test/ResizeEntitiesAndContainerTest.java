package test;

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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;
import javax.swing.text.JTextComponent;

import org.testng.annotations.Test;

import ingenias.editor.IDE;
import ingenias.editor.widget.CustomJTextField;
import ingenias.exception.CannotLoad;
import ingenias.exception.DamagedFormat;
import ingenias.exception.NotFound;
import ingenias.exception.UnknowFormat;
import ingenias.generator.browser.Browser;
import ingenias.generator.browser.BrowserImp;
import ingenias.generator.util.FileUtils;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.core.BasicComponentFinder;
import org.fest.swing.core.ComponentFinder;
import org.fest.swing.core.EmergencyAbortListener;
import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.core.MouseButton;
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

import static test.JGraphFixtureExtension.jgraphWithName;

public class ResizeEntitiesAndContainerTest {


	private Robot robot;
	private FrameFixture window;


	private EmergencyAbortListener listener;  

	private IDE frame;


	private String anotherFileSpec=System.getProperty("user.dir")+"/target/anothersamplespec.xml";

	public static final String firstDiagram="environment definition";

	@BeforeMethod 
	public void setUpOnce() {
		listener = EmergencyAbortListener.registerInToolkit();
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
		window.resizeWidthTo(1200);
		window.resizeHeightTo(700);		


	}


	@AfterMethod
	public void tearDown() {
		listener.unregister(); 
		window.close();
		window.cleanUp();
	}

	@Test
	@GUITest
	public void changeSizeTest(){
		new File(anotherFileSpec).delete();// to delete the testing spec if it existed
		window.requireVisible();

		// pop up windows for editing properties are set
		window.menuItemWithPath("Preferences","Edit Properties Mode", "Edit Properties in a PopUp Window" ).click();

		// a new environment model is created
		window.tree("ProjectsTree").selectRow(0).showPopupMenu().menuItemWithPath("Add TasksAndGoalsModel").click();
		window.optionPane(Timeout.timeout(3000)).requireVisible().requireTitle("New graph").textBox().setText(firstDiagram);
		window.optionPane(Timeout.timeout(3000)).requireVisible().requireTitle("New graph").okButton().click();

		// the diagram is opened
		window.tree("ProjectsTree").selectRow(1);
		window.tree("ProjectsTree").doubleClickRow(1);		

		// A plan is created
		window.button("Plan").click();		
		window.with(jgraphWithName(firstDiagram)).withDGC("Plan0").selectDGC().moveAway();
		// the properties of the creation application are opened
		window.with(jgraphWithName(firstDiagram)).withDGC("Plan0").selectDGC().changeWidthBy(200).changeHeightBy(100).moveToCenter();

		// A task is created and dragged into the plan
		window.button("Task").click();
		window.with(jgraphWithName(firstDiagram)).withDGC("Task0").selectOrigin().withDGC("Plan0").selectTarget().moveWithin();

		window.with(jgraphWithName(firstDiagram)).withDGC("Plan0").moveToCenter().doubleClick();

		assertTrue("There should be a task defined in the list",
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListTasks").target.getModel().getSize()==1);

		// the added task is removed
		window.dialog().list("valueListTasks").item(0).click().showPopupMenu().menuItemWithPath("Remove selected element").click();
		Pause.pause(200);
		// the dialog is dimissed with an accept. There were no operations made, so it should remain the same
		window.dialog( Timeout.timeout(3000)).button(matchButtonName("Accept")).click();
		
		window.optionPane().okButton().click(); // confirm the deletion of the entity
		
		// now there is no Task0		
		window.with(jgraphWithName(firstDiagram)).withOutDGC("Task0");
		
		// the task is created again
		window.button("Task").click();
		window.with(jgraphWithName(firstDiagram)).withDGC("Task0").selectOrigin().withDGC("Plan0").selectTarget().moveWithin();
		
		window.with(jgraphWithName(firstDiagram)).withDGC("Plan0").moveToCenter().doubleClick();
		assertTrue("There should be a task defined in the list",
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListTasks").target.getModel().getSize()==1);
		window.dialog( Timeout.timeout(3000)).button(matchButtonName("Accept")).click();		
		
		window.with(jgraphWithName(firstDiagram)).withDGC("Task0");
		// change the kind of view
		window.with(jgraphWithName(firstDiagram)).withDGC("Plan0").rightClick().menuItemWithPath("Views","UML").click();
		Pause.pause(500); // give some time for refreshing
		window.with(jgraphWithName(firstDiagram)).withDGC("Plan0").moveAway(); // to force refresh
		window.with(jgraphWithName(firstDiagram)).withDGC("Task0").requireNotVisible(); // task0 dissapeared
		window.with(jgraphWithName(firstDiagram)).withDGC("Plan0").rightClick().menuItemWithPath("Views","INGENIAS").click();
		window.with(jgraphWithName(firstDiagram)).withDGC("Task0").requireVisible(); // task0 appeared
		
		window.menuItemWithPath("File","Save").click();
		window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().textBox().setText(this.anotherFileSpec);
		window.dialog(Timeout.timeout(3000)).button(matchButtonName("Save")).click();
		window.menuItemWithPath("File","Save").click(); // this is a bug
		window.optionPane(Timeout.timeout(3000)).yesButton().click();
		
		window.menuItemWithPath("File", "Load").click();
		window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().textBox().setText(this.anotherFileSpec);
		window.dialog(Timeout.timeout(3000)).requireModal().button(VisualDiagramCreationTest.matchButtonName("Open")).click();
		
		Pause.pause(2000);
		// the diagram is opened
		window.tree("ProjectsTree").selectRow(1);
		window.tree("ProjectsTree").doubleClickRow(1);		

		window.with(jgraphWithName(firstDiagram)).withDGC("Plan0").doubleClick();
		assertTrue("There should be a task defined in the list",
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListTasks").target.getModel().getSize()==1);
		// the dialog is dimissed with an accept. There were no operations made, so it should remain the same
		window.dialog( Timeout.timeout(3000)).button(matchButtonName("Accept")).click();
		window.with(jgraphWithName(firstDiagram)).withDGC("Task0").requireVisible(); // task0 appeared		
		
		window.with(jgraphWithName(firstDiagram)).withDGC("Task0").selectOrigin().withDGC("Plan0").selectTarget().isWithin();
		window.with(jgraphWithName(firstDiagram)).withDGC("Plan0").moveAway();
		window.with(jgraphWithName(firstDiagram)).withDGC("Task0").selectOrigin().withDGC("Plan0").selectTarget().isWithin();
	}
	
	
	@Test
	@GUITest
	public void testingContainer(){
		new File(anotherFileSpec).delete();// to delete the testing spec if it existed
		window.requireVisible();

		// pop up windows for editing properties are set
		window.menuItemWithPath("Preferences","Edit Properties Mode", "Edit Properties in a PopUp Window" ).click();

		// a new environment model is created
		window.tree("ProjectsTree").selectRow(0).showPopupMenu().menuItemWithPath("Add TasksAndGoalsModel").click();
		window.optionPane(Timeout.timeout(3000)).requireVisible().requireTitle("New graph").textBox().setText(firstDiagram);
		window.optionPane(Timeout.timeout(3000)).requireVisible().requireTitle("New graph").okButton().click();

		// the diagram is opened
		window.tree("ProjectsTree").selectRow(1);
		window.tree("ProjectsTree").doubleClickRow(1);		

		// A plan is created
		window.button("Plan").click();		
		window.with(jgraphWithName(firstDiagram)).withDGC("Plan0").selectDGC().moveAway();
		// the properties of the creation application are opened
		window.with(jgraphWithName(firstDiagram)).withDGC("Plan0").selectDGC().changeWidthBy(200).changeHeightBy(100).moveToCenter();

		// Two tasks are created and dragged into the plan. The tasks are connected then with a relationship
		window.button("Task").click();				
		window.with(jgraphWithName(firstDiagram)).withDGC("Task0").selectOrigin().withDGC("Plan0").selectTarget().moveWithin();		
		window.button("Task").click();		
		window.with(jgraphWithName(firstDiagram)).withDGC("Task1").selectOrigin().withDGC("Plan0").selectTarget().moveWithin();
		window.with(jgraphWithName(firstDiagram)).withDGC("Task1").moveAway();
		
		window.with(jgraphWithName(firstDiagram)).withDGC("Task0").selectOrigin().withDGC("Task1").selectTarget().drag();
		window.optionPane(Timeout.timeout(3000)).comboBox().selectItem("PConnects");
		window.optionPane(Timeout.timeout(3000)).okButton().click(); // accept this type
		window.optionPane(Timeout.timeout(3000)).okButton().click(); // ok with current assignment
		
		window.with(jgraphWithName(firstDiagram)).withDGC("Plan0").rightClick().menuItemWithPath("Views","UML").click();		
		window.with(jgraphWithName(firstDiagram)).withDGC("Plan0").moveToCenter(); // to permit things to refresh
		
		window.with(jgraphWithName(firstDiagram)).withDGC("Task0").requireNotVisible();
		window.with(jgraphWithName(firstDiagram)).withDGC("Task1").requireNotVisible();
		window.with(jgraphWithName(firstDiagram)).withDGC("0").requireNotVisible();
		
		window.with(jgraphWithName(firstDiagram)).withDGC("Plan0").rightClick().menuItemWithPath("Views","INGENIAS").click();
		
		window.with(jgraphWithName(firstDiagram)).withDGC("Task0").requireVisible();
		window.with(jgraphWithName(firstDiagram)).withDGC("Task1").requireVisible();
		window.with(jgraphWithName(firstDiagram)).withDGC("0").requireVisible();
		
				
	}



	public static GenericTypeMatcher<JDialog> matchWindowName(final String text) {
		return new GenericTypeMatcher<JDialog>(JDialog.class) {
			@Override
			protected boolean isMatching(JDialog component) {
				if (component.getTitle()!=null)
					return component.getTitle().equals(text) && component.isVisible();
				else
					return false;
			}
			public String toString(){
				return "matching buttons with text "+text;
			}
		};
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
