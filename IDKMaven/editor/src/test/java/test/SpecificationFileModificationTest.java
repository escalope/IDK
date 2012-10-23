package test;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.Collection;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.testng.annotations.Test;

import ingenias.editor.IDE;
import ingenias.editor.persistence.PersistenceManager;
import ingenias.exception.CannotLoad;
import ingenias.exception.DamagedFormat;
import ingenias.exception.InvalidEntity;
import ingenias.exception.InvalidGraph;
import ingenias.exception.NotFound;
import ingenias.exception.NotInitialised;
import ingenias.exception.UnknowFormat;
import ingenias.generator.browser.Browser;
import ingenias.generator.browser.BrowserImp;
import ingenias.generator.browser.Graph;
import ingenias.generator.browser.GraphAttributeFactory;
import ingenias.generator.browser.GraphEntity;
import ingenias.generator.browser.GraphEntityFactory;
import ingenias.generator.browser.GraphFactory;
import ingenias.generator.browser.GraphRelationshipFactory;
import ingenias.generator.util.FileUtils;

import org.fest.assertions.Condition;
import org.fest.swing.annotation.GUITest;
import org.fest.swing.core.BasicComponentFinder;
import org.fest.swing.core.ComponentFinder;
import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.exception.WaitTimedOutError;
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

public class SpecificationFileModificationTest {


	private Robot robot;
	private FrameFixture window;

	private IDE frame;

	private String fileSpec=System.getProperty("user.dir")+"/target/samplespec.xml";

	String firstDiagram="Hello world agent definition";
	String secondDiagram="Task description";
	String thirdDiagram="Task Code";

	@BeforeMethod (alwaysRun=true)
	public void setUpOnce() {
		//FailOnThreadViolationRepaintManager.install();

		frame = GuiActionRunner.execute(new GuiQuery<IDE>() {
			protected IDE executeInEDT() {
				IDE ide=new IDE();
				ide.launchIDE(new String[]{});		
				ide.removeExitAction();
				ide.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);	
				System.err.println("Creada nueva ventana");
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


	@Test//(dependsOnGroups= {"diagramcreation"})
	@GUITest
	public void testIndenpendencyOfIDEAndBrowser() throws UnknowFormat, DamagedFormat, CannotLoad{
		Browser originalSpec=BrowserImp.initialise(fileSpec); // loads the original spec
		window.requireVisible();
		window.menuItemWithPath("Preferences","Edit Properties Mode", "Edit Properties in a PopUp Window" ).click();
		window.menuItemWithPath("File", "Load").click();
		window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().textBox().setText(fileSpec);
		window.dialog(Timeout.timeout(3000)).requireModal().button(VisualDiagramCreationTest.matchButtonName("Open")).click();
		Pause.pause(2000);
		window.tree("ProjectsTree").selectRow(3);
		window.tree("ProjectsTree").doubleClickRow(3);
		window.with(jgraphWithName(thirdDiagram)).withDGC("INGENIASCodeComponent0").doubleClick();
		String randomtext="//"+new Float(Math.random());
		window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().panel("Code").textBox().setText(randomtext);
		window.dialog(Timeout.timeout(3000)).button("accept").click();
		Browser browser = new BrowserImp(frame.getIds());
		GraphEntity ent = browser.findEntity("INGENIASCodeComponent0");
		try {
			assertTrue("The entity INGENIASCodeComponent0 should contain the text "+randomtext,
					randomtext.equals(ent.getAttributeByName("Code").getSimpleValue()));
		} catch (NotFound e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Assert.fail("FAiled code attribute inspection in entity INGENIASCodeComponent0");
		}

		window.menuItem("Savefilemenu").requireEnabled();
		window.menuItem("Savefilemenu").click();
		window.dialog(Timeout.timeout(2000)).requireModal().requireVisible(); // it will ask to confirm overwrite
		window.optionPane().yesButton().click();

		Browser browser2 = BrowserImp.initialise(fileSpec);
		
		try {
			
			ent = browser2.findEntity("INGENIASCodeComponent0");
			
			assertTrue("The entity INGENIASCodeComponent0 should contain the text "+randomtext,
					randomtext.equals(ent.getAttributeByName("Code").getSimpleValue()));						
		
			assertTrue("There should be no differences between current and stored file and there are "+BrowserImp.findAllDifferences(browser, browser2), 
					BrowserImp.findAllDifferences(browser, browser2).isEmpty());	
		} catch (NotFound e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Assert.fail("Failed code attribute inspection in entity INGENIASCodeComponent0");
		}


		try {
			window.dialog(Timeout.timeout(3000)).requireModal().requireVisible(); 
			Assert.fail("No further dialogs should appear because stored file and current spec are the same");
		} catch (WaitTimedOutError e){
			// no further dialogs should appear
			Browser storedSpec=BrowserImp.initialise(fileSpec); // loads the original spec
			assertTrue("There has been changes in the original spec and it is not the same anymore", 
					!BrowserImp.compare(originalSpec, storedSpec));	
			
		}
		
		
	}


	
	
	/**
	 * This method validates the dialogs when a change occurs in the file being edited 
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws UnknowFormat
	 * @throws DamagedFormat
	 * @throws CannotLoad
	 * @throws InvalidGraph
	 * @throws InvalidEntity
	 */
	@Test//(dependsOnGroups= {"diagramcreation"})
	@GUITest
	public void testModificationDialog() throws FileNotFoundException, IOException, UnknowFormat, DamagedFormat, CannotLoad, InvalidGraph, InvalidEntity{

		Browser originalSpec=BrowserImp.initialise(fileSpec); // loads the original spec

		window.requireVisible();
		window.menuItemWithPath("Preferences","Edit Properties Mode", "Edit Properties in a PopUp Window" ).click();
		
		window.menuItemWithPath("File", "Load").click();
		window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().textBox().setText(fileSpec);
		window.dialog(Timeout.timeout(3000)).requireModal().button(VisualDiagramCreationTest.matchButtonName("Open")).click();

		changeLastModificationAttributeOnly(); // the spec is copied over itself	

		try {
			window.dialog(Timeout.timeout(2000)).requireModal().requireVisible(); 
			Assert.fail("No dialog should have appeared");
		}catch (org.fest.swing.exception.WaitTimedOutError t){
			// it is correct because no dialog should appear
		}

		// there are no changes, so the save option should remain disabled
		window.menuItemWithPath("File", "Save").requireDisabled();

		window.tree("ProjectsTree").selectRow(3);
		window.tree("ProjectsTree").doubleClickRow(3);
		String randomtext="//"+new Float(Math.random());
		window.with(jgraphWithName(thirdDiagram)).withDGC("INGENIASCodeComponent0").doubleClick();
		window.dialog(Timeout.timeout(3000)).requireModal().requireVisible().panel("Code").textBox().setText(randomtext);
		window.dialog(Timeout.timeout(3000)).button("accept").click();


		//assertTrue("The editor should have been modified ",frame.getIds().isChanged());
		window.menuItem("Savefilemenu").requireEnabled();
		window.menuItem("Savefilemenu").click();
		window.dialog(Timeout.timeout(2000)).requireModal().requireVisible(); // it will ask to confirm overwrite
		window.optionPane().yesButton().click();
		try {
			window.dialog(Timeout.timeout(3000)).requireModal().requireVisible(); 
			Assert.fail("No further dialogs should appear because stored file and current spec are the same");
		} catch (WaitTimedOutError e){
			// no further dialogs should appear
			Browser storedSpec=BrowserImp.initialise(fileSpec); // loads the original spec
			assertTrue("There has been changes in the original spec and it is not the same anymore", 
					!BrowserImp.compare(originalSpec, storedSpec));	
		}

		changeSpecificationContent(); 

		window.dialog(Timeout.timeout(2000)).requireModal().requireVisible(); // change is detected
		window.optionPane(Timeout.timeout(2000)).okButton().click();
		// now a new spec should be loaded
		window.menuItemWithPath("File", "Save").requireDisabled();
		// and no additional warnings issued
		try {
			window.dialog(Timeout.timeout(3000)).requireModal().requireVisible(); 
			Assert.fail("No further dialogs should appear because stored file and current spec are the same");
		} catch (Exception e){

		}		
		
		window.tree("ProjectsTree").selectRow(4);
		window.tree("ProjectsTree").doubleClickRow(4);
		window.with(jgraphWithName("myDiag")).withDGC("miid").selectDGC().deleteSelectedDGC();
		window.optionPane(Timeout.timeout(2000)).okButton().click();
		
		window.tree("ProjectsTree").selectRow(4).showPopupMenu().menuItemWithPath("remove package/model").click();
		window.optionPane(Timeout.timeout(2000)).yesButton().click();		
		
		window.menuItem("Savefilemenu").requireEnabled();
		window.menuItem("Savefilemenu").click();
		window.dialog(Timeout.timeout(2000)).requireModal().requireVisible(); // it will ask to confirm overwrite
		window.optionPane().yesButton().click();

	}

	private void changeSpecificationContent() throws UnknowFormat, DamagedFormat, CannotLoad, InvalidGraph, InvalidEntity, IOException {
		Browser browser=BrowserImp.initialise(fileSpec);
		GraphFactory gf=null;
		GraphEntityFactory gef=null;
		GraphRelationshipFactory grf=null;
		GraphAttributeFactory gaf=null;
		try {
			gf = GraphFactory.createDefaultGraphFactory(browser);
			gef= GraphEntityFactory.createDefaultGraphFactory(browser);
			grf= GraphRelationshipFactory.createDefaultGraphFactory(browser);
			gaf= GraphAttributeFactory.createDefaultGraphFactory(browser);

		} catch (NotInitialised e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Graph g=null;
		try {
			g = gf.createCompleteGraph("AgentModel","myDiag");
		} catch (NotInitialised e) {
			e.printStackTrace();
		}
		GraphEntity ge=gef.createEntity("Agent","miid",g);		
		PersistenceManager p=new PersistenceManager();
		p.save(new File(fileSpec), browser.getState());

	}


	private void changeLastModificationAttributeOnly() throws FileNotFoundException,
	IOException {
		StringBuffer specContent = FileUtils.readFile(fileSpec);		
		FileOutputStream fos= new FileOutputStream(fileSpec);
		fos.write(specContent.toString().getBytes());
		fos.close();
	}

}
