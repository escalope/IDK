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

import org.testng.annotations.Test;

import ingenias.editor.IDE;
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


public class VisualDiagramCreationTest {


	private FrameFixture window;

	private IDE frame;

	private String fileSpec=System.getProperty("user.dir")+"/target/samplespec.xml";
	private String anotherFileSpec=System.getProperty("user.dir")+"/target/anothersamplespec.xml";
	
	public static final String firstDiagram="Hello world agent definition";
	public static final String secondDiagram="Task description";
	public static final String thirdDiagram="Task Code";

	@BeforeMethod (alwaysRun=true)
	public void setUp() {
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


	@AfterMethod (alwaysRun=true)
	public void tearDown() {
		window.close();
		Pause.pause(1000);
		window.requireNotVisible();
		window.cleanUp();
	}
	
	@Test(groups={"diagramcreation"})
	@GUITest
	public void createHelloWorldExample(){
		
		

		
		new File(fileSpec).delete();// to delete the testing spec if it existed
		
		window.menuItemWithPath("Preferences","Edit Properties Mode", "Edit Properties in a PopUp Window" ).click();
		

		window.tree("ProjectsTree").selectRow(0).showPopupMenu().menuItemWithPath("Add AgentModel").click();
		window.optionPane(Timeout.timeout(3000)).requireVisible().requireTitle("New graph").textBox().setText(firstDiagram);
		window.optionPane(Timeout.timeout(3000)).requireVisible().requireTitle("New graph").okButton().click();

		window.tree("ProjectsTree").selectRow(1);
		window.tree("ProjectsTree").doubleClickRow(1);
		window.button("Agent").click();		
		window.with(jgraphWithName(firstDiagram)).withDGC("Agent0").selectDGC().moveAway();
		window.button("Goal").click(); 		
		window.with(jgraphWithName(firstDiagram)).withDGC("Goal0").selectDGC().moveAway();
		window.with(jgraphWithName(firstDiagram)).withDGC("Agent0").selectOrigin().withDGC("Goal0").selectTarget().drag();
		window.button("MentalState").click();
		window.with(jgraphWithName(firstDiagram)).withDGC("MentalState0").selectDGC().moveAway();
		window.with(jgraphWithName(firstDiagram)).withDGC("Agent0").selectOrigin().withDGC("MentalState0").selectTarget().drag();
		window.button("FrameFact").click();
		window.with(jgraphWithName(firstDiagram)).withDGC("FrameFact0").selectDGC().moveAway();
		window.with(jgraphWithName(firstDiagram)).withDGC("MentalState0").selectOrigin().withDGC("FrameFact0").selectTarget().drag();
		window.button("Task").click();
		window.with(jgraphWithName(firstDiagram)).withDGC("Task0").selectDGC().moveAway();
		window.with(jgraphWithName(firstDiagram)).withDGC("Agent0").selectOrigin().withDGC("Task0").selectTarget().drag();		
		window.with(jgraphWithName(firstDiagram)).withDGC("Task0").selectDGC().withDGC("Goal0").addToSelectionDGC().withDGC("FrameFact0").addToSelectionDGC().copySelectedEntities();
		
		
				
		window.tree("ProjectsTree").selectRow(0).showPopupMenu().menuItemWithPath("Add TasksAndGoalsModel").click();
		window.optionPane(Timeout.timeout(3000)).requireVisible().requireTitle("New graph").textBox().setText(secondDiagram);
		window.optionPane(Timeout.timeout(3000)).requireVisible().requireTitle("New graph").okButton().click();
		window.tree("ProjectsTree").selectRow(2);
		window.tree("ProjectsTree").doubleClickRow(2);
		window.with(jgraphWithName(secondDiagram)).pasteSelectedEntities();
		window.with(jgraphWithName(secondDiagram)).pasteSelectedEntities();		
		window.with(jgraphWithName(secondDiagram)).withDGC("Task0").withDGC("Goal0").withDGC("FrameFact0");		
		window.with(jgraphWithName(secondDiagram)).withDGC("Task0").selectOrigin().withDGC("Goal0").selectTarget().drag();
		window.optionPane(Timeout.timeout(3000)).comboBox().selectItem("GTSatisfies");
		window.optionPane(Timeout.timeout(3000)).okButton().click();
		window.with(jgraphWithName(secondDiagram)).withDGC("Task0").selectOrigin().withDGC("FrameFact0").selectTarget().drag();
		window.optionPane(Timeout.timeout(3000)).comboBox().selectItem("WFConsumes");
		window.optionPane(Timeout.timeout(3000)).okButton().click();
		window.with(jgraphWithName(secondDiagram)).withDGC("Task0").selectDGC().copySelectedEntities();
		
		window.tree("ProjectsTree").selectRow(0).showPopupMenu().menuItemWithPath("Add ComponentDiagram").click();
		window.optionPane(Timeout.timeout(3000)).requireVisible().requireTitle("New graph").textBox().setText(thirdDiagram);
		window.optionPane(Timeout.timeout(3000)).requireVisible().requireTitle("New graph").okButton().click();
		window.tree("ProjectsTree").selectRow(3);
		window.tree("ProjectsTree").doubleClickRow(3);
		window.with(jgraphWithName(thirdDiagram)).pasteSelectedEntities();
		window.button("INGENIASCodeComponent").click();
		window.with(jgraphWithName(thirdDiagram)).withDGC("INGENIASCodeComponent0").moveAway();
		window.with(jgraphWithName(thirdDiagram)).withDGC("Task0").selectOrigin().withDGC("INGENIASCodeComponent0").selectTarget().drag();
		window.with(jgraphWithName(thirdDiagram)).withDGC("INGENIASCodeComponent0").doubleClick();
		
		window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().panel("Code").textBox().setText("System.out.println(\"hello\");");
		window.dialog(Timeout.timeout(3000)).button("accept").click();
		
		window.menuItemWithPath("File","Save").click();
		window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().textBox().setText(fileSpec);
		window.dialog(Timeout.timeout(3000)).button(matchButtonName("Save")).click();
		window.menuItemWithPath("File","Save").click(); // this is a bug
		window.optionPane(Timeout.timeout(3000)).yesButton().click();
	
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


	private void changeLastModification() throws FileNotFoundException,
	IOException {
		StringBuffer specContent = FileUtils.readFile(fileSpec);		
		FileOutputStream fos= new FileOutputStream(fileSpec);
		fos.write(specContent.toString().getBytes());
		fos.close();
	}


}
