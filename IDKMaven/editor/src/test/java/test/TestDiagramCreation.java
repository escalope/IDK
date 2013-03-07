package test;

import org.fest.swing.annotation.GUITest;
import org.testng.annotations.Test;

import ingenias.editor.IDEState;
import ingenias.editor.ProjectProperty;
import ingenias.editor.extension.BasicToolImp;
import ingenias.editor.persistence.PersistenceManager;
import ingenias.exception.CannotLoad;
import ingenias.exception.DamagedFormat;
import ingenias.exception.InvalidAttribute;
import ingenias.exception.InvalidColection;
import ingenias.exception.InvalidEntity;
import ingenias.exception.InvalidGraph;
import ingenias.exception.NotFound;
import ingenias.exception.NotInitialised;
import ingenias.exception.UnknowFormat;
import ingenias.generator.browser.Browser;
import ingenias.generator.browser.BrowserImp;
import ingenias.generator.browser.Graph;
import ingenias.generator.browser.GraphAttribute;
import ingenias.generator.browser.GraphAttributeFactory;
import ingenias.generator.browser.GraphCollection;
import ingenias.generator.browser.GraphEntity;
import ingenias.generator.browser.GraphEntityFactory;
import ingenias.generator.browser.GraphFactory;
import ingenias.generator.browser.GraphRelationshipFactory;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Vector;

public class TestDiagramCreation  {


	private BrowserImp browser;

	@Test
	@GUITest
	public void diagramCreationTest() {
		try {
			 browser=new BrowserImp(IDEState.emptyIDEState());
			
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
			test1(gf,gef,grf,gaf);
			test2(gf,gef,grf,gaf);
			test3(gf,gef,grf,gaf);
			test4(gf,gef,grf,gaf);
		} catch (InvalidGraph e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 catch (InvalidEntity e) {
    	  // TODO Auto-generated catch block
    	  e.printStackTrace();
      } catch (InvalidAttribute e) {
    	  // TODO Auto-generated catch block
    	  e.printStackTrace();
      } catch (InvalidColection e) {
    	  // TODO Auto-generated catch block
    	  e.printStackTrace();
      } catch (NotFound e) {
    	  // TODO Auto-generated catch block
    	  e.printStackTrace();
      }
	}

	/**
	 *  This test creates a diagram of type AgentModel and inserts
	 *  into it an agent named "miid"
	 *  
	 * @param gf The graph factory created to manage instances  
	 * @param grf 
	 * @param gef 
	 * @param gaf 
	 * @throws InvalidGraph
	 * @throws InvalidEntity
	 */
	
	private void test1(GraphFactory gf, GraphEntityFactory gef, GraphRelationshipFactory grf, GraphAttributeFactory gaf) throws InvalidGraph, InvalidEntity {

		Graph g=null;
		try {
			g = gf.createCompleteGraph("AgentModel","myDiag");
		} catch (NotInitialised e) {
			e.printStackTrace();
		}
		GraphEntity ge=gef.createEntity("Agent","miid",g);

	}

	/**
	 * It creates 10 agent instances. This test checks that they are 
	 * inserted into different positions of the screen. The diagram
	 * where these agent instances are inserted is the diagram from
	 * test1
	 * 
	 * @param gf The graph factory created to manage instances
	 * @param grf 
	 * @param gef 
	 * @param gaf 
	 * @throws InvalidGraph
	 * @throws InvalidEntity
	 */
	
	private void test2(GraphFactory gf, GraphEntityFactory gef, GraphRelationshipFactory grf, GraphAttributeFactory gaf) throws InvalidGraph, InvalidEntity {
		Graph g=browser.getGraph("myDiag");				
		for (int k=0;k<10;k++)
			gef.createEntity("Agent","miid"+k,g);

	}


	/**
	 * This test creates an entity with collection attributes
	 * and initializes them
	 * 
	 * @param gf The graph factory created to manage instances
	 * @param grf 
	 * @param gef 
	 * @param gaf 
	 * @throws InvalidGraph
	 * @throws InvalidEntity
	 * @throws InvalidAttribute
	 * @throws InvalidColection
	 */
	
	private void test3(GraphFactory gf, GraphEntityFactory gef, GraphRelationshipFactory grf, GraphAttributeFactory gaf) throws InvalidGraph, InvalidEntity, InvalidAttribute, InvalidColection {
		Graph g=null;
		try {
			g = gf.createCompleteGraph("EnvironmentModel","envTest");
		} catch (NotInitialised e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GraphEntity ge=gef.createEntity("Application","iap",g);
		GraphEntity nmethod=gef.createEntity("Method","m1",g);
		Vector<GraphEntity> nv=new Vector<GraphEntity>(); 		
		GraphAttribute att1=gaf.createAttribute("Name","mymethod",g);
		GraphAttribute att2=gaf.createAttribute("Result","myresult",g);				
		nmethod.setAttribute(att1);
		nmethod.setAttribute(att2);
		nv.add(nmethod);		
		GraphCollection gc=gaf.createCollection(nv,g);
		GraphAttribute methods=gaf.createAttribute("Methods",gc,g);	
		ge.setAttribute(methods);
	}

	/**
	 * This test creates a relationship between two or more entities. The relationship type is the first of the valid
	 * ones connecting the two entities. The assignment of extremes to the relationship is the first from the 
	 * lis of valid orderings of the selected entities for the chosen kind of relationship. 
	 * 
	 * @param gf The graph factory created to manage instances
	 * @param grf 
	 * @param gef 
	 * @param gaf 
	 * @throws InvalidGraph
	 * @throws InvalidEntity
	 * @throws InvalidAttribute
	 * @throws InvalidColection
	 * @throws NotFound 
	 */
	
	private void test4(GraphFactory gf, 
			GraphEntityFactory gef, 
			GraphRelationshipFactory grf, 
			GraphAttributeFactory gaf) throws InvalidGraph, InvalidEntity, InvalidAttribute, InvalidColection, NotFound {
		Graph g=browser.getGraph("myDiag");		
		Vector<String> validRels = grf.getPossibleRelationships( new String[]{"miid1","miid2"}, g);
		Vector<Hashtable<String, String>> assignments = 
				grf.getPossibleRoleAssignment(validRels.firstElement(), new String[]{"miid1","miid2"}, g, browser);
		grf.createRelationship("AInherits",g, assignments.firstElement());
	}
	
	@Test

	public void saveSpecTest() throws IOException{
		ingenias.editor.Log.initInstance(new PrintWriter(System.out));
		diagramCreationTest();		
		PersistenceManager pm=new PersistenceManager();
		pm.save(new File("target/result.xml"), browser.getState());		
	}


}
