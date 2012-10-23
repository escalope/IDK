package test;

import static org.testng.AssertJUnit.assertTrue;
import ingenias.editor.IDEState;
import ingenias.editor.persistence.PersistenceManager;
import ingenias.exception.InvalidAttribute;
import ingenias.exception.InvalidColection;
import ingenias.exception.InvalidEntity;
import ingenias.exception.InvalidGraph;
import ingenias.exception.NotFound;
import ingenias.exception.NotInitialised;
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

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CompareSpecTest {

	@Test
	public void testDiffTwoEqualSpecs() throws NotFound{
		Browser spec2=null;
		Browser spec1=simpleDiagram();
		assertTrue("A spec has to be equal to itself. And I found these "+BrowserImp.findAllDifferences(spec1, spec1), 
				BrowserImp.findAllDifferences(spec1, spec1).isEmpty());
		spec2=simpleDiagram();
		assertTrue("A spec has to be equal to itself. And I found these "+BrowserImp.findAllDifferences(spec1, spec2), 
				BrowserImp.findAllDifferences(spec1, spec2).isEmpty());
		spec2=simpleDiagramAlteredWithOneEntityIdDifferent();
		assertTrue("There is only one difference in the name of one entity and I found these "+BrowserImp.findAllDifferences(spec1, spec2), 
				BrowserImp.findAllDifferences(spec1, spec2).size()==1);
		spec2=simpleDiagramWithDescOfOneEntityDifferent();
		assertTrue("There is only one difference in the name of one entity and I found these "+BrowserImp.findAllDifferences(spec1, spec2), 
				BrowserImp.findAllDifferences(spec1, spec2).size()==1);
		spec2=simpleDiagramWithOneCollectionModified();
		assertTrue("There is only one difference in the name of one entity and I found these "+BrowserImp.findAllDifferences(spec1, spec2), 
				BrowserImp.findAllDifferences(spec1, spec2).size()==1);
		spec2=simpleDiagramWithOneCollectionModifiedJustName();
		assertTrue("There is only one difference in the name of one entity and I found these "+BrowserImp.findAllDifferences(spec1, spec2), 
				BrowserImp.findAllDifferences(spec1, spec2).size()==1);
	}
	
	public Browser simpleDiagramWithOneCollectionModifiedJustName() {
		try {
			Browser browser=new BrowserImp(IDEState.emptyIDEState());

			GraphFactory gf=null;
			GraphEntityFactory gef=null;
			GraphRelationshipFactory grf=null;
			GraphAttributeFactory gaf=null;
			try {
				gf = GraphFactory.createDefaultGraphFactory(browser);
				gef= GraphEntityFactory.createDefaultGraphFactory(browser);
				grf= GraphRelationshipFactory.createDefaultGraphFactory(browser);
				gaf= GraphAttributeFactory.createDefaultGraphFactory(browser);

				Graph g=null;
				try {
					g = gf.createCompleteGraph("AgentModel","myDiag");
				} catch (NotInitialised e) {
					e.printStackTrace();
				}
				GraphEntity ge=gef.createEntity("Agent","miid",g);	
				GraphAttribute decAt=gaf.createAttribute("Description", "Sample Description", g);
				ge.setAttribute(decAt);

				try {
					g = gf.createCompleteGraph("EnvironmentModel","envTest");
				} catch (NotInitialised e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ge=gef.createEntity("Application","iap",g);
				GraphEntity nmethod=gef.createEntity("Method","m2",g);
				Vector<GraphEntity> nv=new Vector<GraphEntity>(); 		
				GraphAttribute att1=gaf.createAttribute("Name","mymethod",g);
				GraphAttribute att2=gaf.createAttribute("Result","myresult",g);				
				nmethod.setAttribute(att1);
				nmethod.setAttribute(att2);
				nv.add(nmethod);		
				GraphCollection gc=gaf.createCollection(nv,g);
				GraphAttribute methods=gaf.createAttribute("Methods",gc,g);	
				ge.setAttribute(methods);
			} catch (NotInitialised e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidColection e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			return browser;
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
		} 
		//		catch (InvalidColection e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} 
		return null;
	}
	
	public Browser simpleDiagramWithOneCollectionModified() {
		try {
			Browser browser=new BrowserImp(IDEState.emptyIDEState());

			GraphFactory gf=null;
			GraphEntityFactory gef=null;
			GraphRelationshipFactory grf=null;
			GraphAttributeFactory gaf=null;
			try {
				gf = GraphFactory.createDefaultGraphFactory(browser);
				gef= GraphEntityFactory.createDefaultGraphFactory(browser);
				grf= GraphRelationshipFactory.createDefaultGraphFactory(browser);
				gaf= GraphAttributeFactory.createDefaultGraphFactory(browser);

				Graph g=null;
				try {
					g = gf.createCompleteGraph("AgentModel","myDiag");
				} catch (NotInitialised e) {
					e.printStackTrace();
				}
				GraphEntity ge=gef.createEntity("Agent","miid",g);	
				GraphAttribute decAt=gaf.createAttribute("Description", "Sample Description", g);
				ge.setAttribute(decAt);

				try {
					g = gf.createCompleteGraph("EnvironmentModel","envTest");
				} catch (NotInitialised e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ge=gef.createEntity("Application","iap",g);
				GraphEntity nmethod=gef.createEntity("Method","m1",g);
				Vector<GraphEntity> nv=new Vector<GraphEntity>(); 		
				GraphAttribute att1=gaf.createAttribute("Name","mymethod",g);
				GraphAttribute att2=gaf.createAttribute("Result","myresult",g);				
				nmethod.setAttribute(att1);
				nmethod.setAttribute(att2);
				nv.add(nmethod);		
				nv.add(nmethod);	
				GraphCollection gc=gaf.createCollection(nv,g);
				GraphAttribute methods=gaf.createAttribute("Methods",gc,g);	
				ge.setAttribute(methods);
			} catch (NotInitialised e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidColection e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			return browser;
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
		} 
		//		catch (InvalidColection e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} 
		return null;
	}
	
	

	public Browser simpleDiagramWithDescOfOneEntityDifferent() {
		try {
			Browser browser=new BrowserImp(IDEState.emptyIDEState());

			GraphFactory gf=null;
			GraphEntityFactory gef=null;
			GraphRelationshipFactory grf=null;
			GraphAttributeFactory gaf=null;
			try {
				gf = GraphFactory.createDefaultGraphFactory(browser);
				gef= GraphEntityFactory.createDefaultGraphFactory(browser);
				grf= GraphRelationshipFactory.createDefaultGraphFactory(browser);
				gaf= GraphAttributeFactory.createDefaultGraphFactory(browser);

				Graph g=null;
				try {
					g = gf.createCompleteGraph("AgentModel","myDiag");
				} catch (NotInitialised e) {
					e.printStackTrace();
				}
				GraphEntity ge=gef.createEntity("Agent","miid",g);
				GraphAttribute decAt=gaf.createAttribute("Description", "Another Description", g);
				ge.setAttribute(decAt);

				try {
					g = gf.createCompleteGraph("EnvironmentModel","envTest");
				} catch (NotInitialised e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ge=gef.createEntity("Application","iap",g);
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
			} catch (NotInitialised e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			return browser;
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
		} 
		catch (InvalidColection e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	public Browser simpleDiagramAlteredWithOneEntityIdDifferent() {
		try {
			Browser browser=new BrowserImp(IDEState.emptyIDEState());

			GraphFactory gf=null;
			GraphEntityFactory gef=null;
			GraphRelationshipFactory grf=null;
			GraphAttributeFactory gaf=null;
			try {
				gf = GraphFactory.createDefaultGraphFactory(browser);
				gef= GraphEntityFactory.createDefaultGraphFactory(browser);
				grf= GraphRelationshipFactory.createDefaultGraphFactory(browser);
				gaf= GraphAttributeFactory.createDefaultGraphFactory(browser);

				Graph g=null;
				try {
					g = gf.createCompleteGraph("AgentModel","myDiag");
				} catch (NotInitialised e) {
					e.printStackTrace();
				}
				GraphEntity ge=gef.createEntity("Agent","diferentId",g);
				GraphAttribute decAt=gaf.createAttribute("Description", "Sample Description", g);
				ge.setAttribute(decAt);			

				try {
					g = gf.createCompleteGraph("EnvironmentModel","envTest");
				} catch (NotInitialised e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ge=gef.createEntity("Application","iap",g);
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
			} catch (NotInitialised e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			return browser;
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
		} 
		return null;
	}


	public Browser simpleDiagram() {
		try {
			Browser browser=new BrowserImp(IDEState.emptyIDEState());

			GraphFactory gf=null;
			GraphEntityFactory gef=null;
			GraphRelationshipFactory grf=null;
			GraphAttributeFactory gaf=null;
			try {
				gf = GraphFactory.createDefaultGraphFactory(browser);
				gef= GraphEntityFactory.createDefaultGraphFactory(browser);
				grf= GraphRelationshipFactory.createDefaultGraphFactory(browser);
				gaf= GraphAttributeFactory.createDefaultGraphFactory(browser);

				Graph g=null;
				try {
					g = gf.createCompleteGraph("AgentModel","myDiag");
				} catch (NotInitialised e) {
					e.printStackTrace();
				}
				GraphEntity ge=gef.createEntity("Agent","miid",g);	
				GraphAttribute decAt=gaf.createAttribute("Description", "Sample Description", g);
				ge.setAttribute(decAt);

				try {
					g = gf.createCompleteGraph("EnvironmentModel","envTest");
				} catch (NotInitialised e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ge=gef.createEntity("Application","iap",g);
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
			} catch (NotInitialised e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidColection e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			return browser;
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
		} 
		//		catch (InvalidColection e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} 
		return null;
	}

}
