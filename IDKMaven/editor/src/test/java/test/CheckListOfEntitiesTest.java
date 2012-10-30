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

public class CheckListOfEntitiesTest {


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
		window.resizeWidthTo(1000);
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
	public void checkAddingOneMethodToApplication(){
		new File(anotherFileSpec).delete();// to delete the testing spec if it existed
		window.requireVisible();

		// pop up windows for editing properties are set
		window.menuItemWithPath("Preferences","Edit Properties Mode", "Edit Properties in a PopUp Window" ).click();

		// a new environment model is created
		window.tree("ProjectsTree").selectRow(0).showPopupMenu().menuItemWithPath("Add EnvironmentModel").click();
		window.optionPane(Timeout.timeout(3000)).requireVisible().requireTitle("New graph").textBox().setText(firstDiagram);
		window.optionPane(Timeout.timeout(3000)).requireVisible().requireTitle("New graph").okButton().click();

		// the diagram is opened
		window.tree("ProjectsTree").selectRow(1);
		window.tree("ProjectsTree").doubleClickRow(1);		

		// An application is created
		window.button("Application").click();		
		window.with(jgraphWithName(firstDiagram)).withDGC("Application0").selectDGC().moveAway();
		// the properties of the creation application are opened
		window.with(jgraphWithName(firstDiagram)).withDGC("Application0").selectDGC().doubleClick();

		// a method is added to the applicatoin
		addMethodToApplication("one");

		assertTrue("There should be one method defined in the list",
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==1);

		// the added method is removed
		window.dialog().list("valueListMethods").item(0).click().showPopupMenu().menuItemWithPath("Remove selected element").click();
		Pause.pause(200);

		assertTrue("There should be no method defined in the list",
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==0);

		// now I close the dialog accepting changes, which should be none
		window.dialog().button(matchButtonName("Accept")).click();


		// now I open again the same entity to check nothing has changed	
		window.with(jgraphWithName(firstDiagram)).withDGC("Application0").selectDGC().doubleClick();
		assertTrue("There should be no method defined in the list",
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==0);

		// I add again the same method
		addMethodToApplication("one");
		assertTrue("There should be one method defined in the list",
				window.dialog().requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==1);

		// And this time the change is cancelled
		window.dialog(Timeout.timeout(3000)).requireModal().button(matchButtonName("Cancel")).click();

		window.optionPane().yesButton().click(); // confirm the cancel

		// the properties of the creation application are opened
		window.with(jgraphWithName(firstDiagram)).withDGC("Application0").selectDGC().doubleClick();
		assertTrue("There should be no method defined in the list",
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==0);
		// the dialog is dimissed requesting no changes
		window.dialog().button(matchButtonName("Cancel")).click();		

		// the properties of the creation application are opened
		window.with(jgraphWithName(firstDiagram)).withDGC("Application0").selectDGC().doubleClick();
		// a method is added to the application entity
		addMethodToApplication("one");
		// the change is accepted
		window.dialog(Timeout.timeout(3000)).button(matchButtonName("Accept")).click();

		// the properties of the creation application are opened
		window.with(jgraphWithName(firstDiagram)).withDGC("Application0").selectDGC().doubleClick();
		assertTrue("There should be one method defined in the list",
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==1);
		// the dialog is dimissed requesting no changes
		window.dialog(Timeout.timeout(3000)).button(matchButtonName("Cancel")).click();	
		//	window.optionPane().yesButton().click(); // confirm the cancel

		// the properties of the creation application are opened
		window.with(jgraphWithName(firstDiagram)).withDGC("Application0").selectDGC().doubleClick();
		assertTrue("There should be one method defined in the list",
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==1);
		// the dialog is dimissed with an accept. There were no operations made, so it should remain the same
		window.dialog( Timeout.timeout(3000)).button(matchButtonName("Accept")).click();	

		// the properties of the creation application are opened
		window.with(jgraphWithName(firstDiagram)).withDGC("Application0").selectDGC().doubleClick();
		assertTrue("There should be one method defined in the list",
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==1);
		// the dialog is dimissed with an accept. There were no operations made, so it should remain the same
		window.dialog( Timeout.timeout(3000)).button(matchButtonName("Accept")).click();



		window.menuItemWithPath("File", "Save").requireEnabled();

		window.menuItemWithPath("File","Save").click();
		window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().textBox().setText(anotherFileSpec);
		window.dialog(Timeout.timeout(3000)).button(matchButtonName("Save")).click();		
		window.menuItemWithPath("File","Save").click(); // this is a bug
		window.optionPane(Timeout.timeout(3000)).yesButton().click();

		window.menuItemWithPath("File", "Save").requireDisabled();

		window.menuItemWithPath("File", "New").click();

		window.menuItemWithPath("File", "Load").click();
		window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().textBox().setText(this.anotherFileSpec);
		window.dialog(Timeout.timeout(3000)).requireModal().button(VisualDiagramCreationTest.matchButtonName("Open")).click();
		Pause.pause(1000);

		// the diagram is opened
		window.tree("ProjectsTree").selectRow(1);
		window.tree("ProjectsTree").doubleClickRow(1);		
		// the properties of the creation application are opened
		window.with(jgraphWithName(firstDiagram)).withDGC("Application0").selectDGC().doubleClick();
		assertTrue("There should be one method defined in the list",
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==1);
		// the dialog is dimissed requesting no changes
		window.dialog(Timeout.timeout(3000)).button(matchButtonName("Cancel")).click();	
	}

	@Test
	@GUITest
	public void checkListAggregationTest(){
		new File(anotherFileSpec).delete();// to delete the testing spec if it existed
		window.requireVisible();

		// pop up windows for editing properties are set
		window.menuItemWithPath("Preferences","Edit Properties Mode", "Edit Properties in a PopUp Window" ).click();

		// a new environment model is created
		window.tree("ProjectsTree").selectRow(0).showPopupMenu().menuItemWithPath("Add EnvironmentModel").click();
		window.optionPane(Timeout.timeout(3000)).requireVisible().requireTitle("New graph").textBox().setText(firstDiagram);
		window.optionPane(Timeout.timeout(3000)).requireVisible().requireTitle("New graph").okButton().click();

		// the diagram is opened
		window.tree("ProjectsTree").selectRow(1);
		window.tree("ProjectsTree").doubleClickRow(1);		

		// An application is created
		window.button("Application").click();		
		window.with(jgraphWithName(firstDiagram)).withDGC("Application0").selectDGC().moveAway();
		// the properties of the creation application are opened
		window.with(jgraphWithName(firstDiagram)).withDGC("Application0").selectDGC().doubleClick();

		// a method is added to the applicatoin
		addMethodToApplication("one");

		assertTrue("There should be one method defined in the list",
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==1);

		// the added method is removed
		window.dialog().list("valueListMethods").item(0).click().showPopupMenu().menuItemWithPath("Remove selected element").click();
		Pause.pause(200);

		assertTrue("There should be no method defined in the list",
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==0);

		addMethodToApplication("one");
		addMethodToApplication("two");

		assertTrue("There should be two methods defined in the list",
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==2);

		// the added method is removed
		window.dialog().list("valueListMethods").item(1).click().showPopupMenu().menuItemWithPath("Remove selected element").click();
		Pause.pause(200);
		assertTrue("There should be one method defined in the list",
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==1);

		assertTrue("The remaining method should be \"one\" and it is "+window.dialog().list("valueListMethods").item(0).toString(),
				window.dialog().list("valueListMethods").target.getModel().getElementAt(0).toString().equals("one"));

		addMethodToApplication("two");
		addMethodToApplication("three");

		assertTrue("There should be three methods defined in the list",
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==3);

		window.dialog().list("valueListMethods").item(1).click().showPopupMenu().menuItemWithPath("Remove selected element").click();
		Pause.pause(200);

		// the dialog is dimissed with an accept. There were no operations made, so it should remain the same
		window.dialog( Timeout.timeout(3000)).button(matchButtonName("Accept")).click();
		// the properties of the creation application are opened
		window.with(jgraphWithName(firstDiagram)).withDGC("Application0").selectDGC().doubleClick();
		assertTrue("There should be two methods defined in the list and there are "+window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize(),
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==2);

		assertTrue("The methods should be one and three and they are "+window.dialog().list("valueListMethods").target.getModel().toString(),
				window.dialog().list("valueListMethods").target.getModel().getElementAt(0).toString().equals("one") && 
				window.dialog().list("valueListMethods").target.getModel().getElementAt(1).toString().equals("three"));

		addMethodToApplication("two");		
		// the added method is removed
		window.dialog().list("valueListMethods").item(2).click().showPopupMenu().menuItemWithPath("Remove selected element").click();
		assertTrue("There should be two methods defined in the list and there are "+window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize(),
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==2);

		assertTrue("The methods should be one and three and they are "+window.dialog().list("valueListMethods").target.getModel().toString(),
				window.dialog().list("valueListMethods").target.getModel().getElementAt(0).toString().equals("one") && 
				window.dialog().list("valueListMethods").target.getModel().getElementAt(1).toString().equals("three"));

		addMethodToApplication("two");	
		window.dialog().list("valueListMethods").item(0).click().showPopupMenu().menuItemWithPath("Remove selected element").click();
		assertTrue("There should be two methods defined in the list and there are "+window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize(),
				window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().
				list("valueListMethods").target.getModel().getSize()==2);
		assertTrue("The methods should be three and two and they are "+window.dialog().list("valueListMethods").target.getModel().toString(),
				window.dialog().list("valueListMethods").target.getModel().getElementAt(0).toString().equals("three") && 
				window.dialog().list("valueListMethods").target.getModel().getElementAt(1).toString().equals("two"));


	}


	private void addMethodToApplication(String name) {
		window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().list("valueListMethods").
		click(MouseButton.RIGHT_BUTTON).showPopupMenu().menuItemWithPath("Add new element").click();
		window.optionPane().comboBox().selectItem("Method");
		window.optionPane().yesButton().click();

		//window.dialog(matchWindowName("Editing"), Timeout.timeout(3000)).requireModal().textBox("Name").setText("one");
		// dialog5 may not be a regular name maintained across executions, but it is the one the traces in FEST show
		// it seems the number is given by swing depending on how many previously shown dialog windows were presented. It is 
		// not reliable.
		window.dialog(matchWindowName("Editing"), Timeout.timeout(3000)).requireModal().textBox("Name").setText(name);
		window.dialog(matchWindowName("Editing"), Timeout.timeout(3000)).requireModal().textBox("Result").setText(name);
		window.dialog(matchWindowName("Editing"), Timeout.timeout(3000)).button(matchButtonName("Accept")).click();

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
