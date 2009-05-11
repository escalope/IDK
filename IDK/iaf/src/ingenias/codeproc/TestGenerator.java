/*
    Copyright (C) 2007 Jorge Gomez Sanz

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

package ingenias.codeproc;

import ingenias.editor.Log;
import ingenias.editor.extension.BasicCodeGenerator;
import ingenias.exception.NotFound;
import ingenias.exception.NotInitialised;
import ingenias.exception.NullEntity;
import ingenias.generator.browser.Browser;
import ingenias.generator.browser.GraphAttribute;
import ingenias.generator.browser.GraphCollection;
import ingenias.generator.browser.GraphEntity;
import ingenias.generator.datatemplate.Repeat;
import ingenias.generator.datatemplate.Sequences;
import ingenias.generator.datatemplate.Var;

public class TestGenerator {

	private Browser browser;

	public TestGenerator(Browser browser){
		this.browser=browser;
	}
	
	public void generateTests(Sequences p, BasicCodeGenerator generator){
		try {
			GraphEntity[] testEntities = Utils
			.generateEntitiesOfType("Test",browser);
			
			for (GraphEntity test:testEntities){
				Repeat testingDepl = new Repeat("testdefinition");
				p.addRepeat(testingDepl);
				testingDepl.add(new Var("test", test.getID()));
			}
			
		} catch (NotInitialised e) {			
			e.printStackTrace();
		}

	}


	/**
	 * 
	 * 
	 * @param p
	 * @throws NotInitialised
	 */
	public  void generateTestingDeployment(Sequences p, BasicCodeGenerator bcg,Browser browser)
	throws NotInitialised {
		GraphEntity[] deployPacks = Utils
		.generateEntitiesOfType("TestingPackage",browser);
		String port = "60000";
		if (deployPacks.length > 0) {
			for (int k = 0; k < deployPacks.length; k++) {
				try {
					Repeat testingDepl = new Repeat("testingnode");
					p.addRepeat(testingDepl);
					
					testingDepl.add(new Var("testingconfig",deployPacks[k].getID()));

					GraphAttribute deplPackageAttr = deployPacks[k]
					                                             .getAttributeByName("TestingDeployment");
					GraphAttribute testAttr = deployPacks[k].getAttributeByName("Tests");
					

					if (deplPackageAttr != null) {
						GraphEntity deplPackage = deplPackageAttr.getEntityValue();
						Repeat depl = new Repeat("deploynode");
						testingDepl.add(depl);
						DeploymentGenerator.generateDeploymentPack(deplPackage, depl,bcg); // for build.xml script generation
						if (deplPackage != null) {
							
							if (testAttr!=null && testAttr.getCollectionValue()!=null && testAttr.getCollectionValue().size()>0){
								GraphCollection tests = testAttr.getCollectionValue();
								
								for (int j=0;j<tests.size();j++){								
									Repeat testRepeat=new Repeat("testunit");									
									testingDepl.add(testRepeat);
									depl = new Repeat("deploynode");
									testRepeat.add(depl);
									DeploymentGenerator.generateDeploymentPack(deplPackage, depl,bcg); // for JUnit class initialization per test
									
									GraphEntity test=tests.getElementAt(j);																											
									testRepeat.add(new Var("test",test.getID()));
									GraphEntity[] relatedComponents = Utils.getRelatedElements(test, "UMLRealizes", "UMLRealizestarget");
									if (relatedComponents!=null && 
											relatedComponents.length>0){
										if (relatedComponents.length>1){
											bcg.fatalError();
											Log.getInstance().logERROR(
													"The test element can be associated only to one INGENIAS Component","", test.getID());
										} else {	
											//String className=permfolder+"/ingenias/tests/"+test.getID()+".java";
											//testingDepl.add(new Var("classname",className));
											//analyzeComponent(relatedComponents[0], testRepeat);
											//DeploymentGenerator.generateDeploymentPack(deplPackage,
											//		testRepeat);
										}
									} 
								}
								
							} else {
								bcg.fatalError();
								Log.getInstance()
								.logERROR(
										"The testing deployment has not defined a test","", deployPacks[k].getID());
							}
						} else {
							bcg.fatalError();
							Log.getInstance()
							.logERROR(
									"The testing deployment has not defined a deployment package",
									"", deployPacks[k].getID());
						};

					}					

				} catch (NullEntity e) {					
					e.printStackTrace();
				} catch (Exception e) {					
					e.printStackTrace();
				}


			}
		}


	}

	private  void analyzeComponent(GraphEntity component, Repeat testingDepl,BasicCodeGenerator bcg) throws Exception{
		GraphAttribute files;
		try {
			files = component.getAttributeByName("Files");
		} catch (NotFound e) {
			Log.getInstance().logERROR("Entity "+component.getID()+" did not have attribute Files","",component.getID());
			bcg.fatalError();
			throw e;
		}
		GraphCollection gc;
		try {
			gc = files.getCollectionValue();
		} catch (NullEntity e) {
			Log.getInstance().logERROR("Entity "+component.getID()+" do not have any collection of Application-File associated","",component.getID());
			bcg.fatalError();
			throw e;
		}
		if (gc.size()>1 || gc.size()==0){
			Log.getInstance().logERROR("Entity "+component.getID()+" must have only one do not have any collection of Application-File associated","",component.getID());
			bcg.fatalError();
			throw new Exception();

		}
		boolean itis=false;
		int j=0;
		while (j<gc.size()){
			GraphEntity filespec=null;
			try {
				filespec = gc.getElementAt(j);
				if (filespec.getType().equals("FileSpecMapping")){
					String fileEntity = filespec.getAttributeByName("Entity").getEntityValue().getID();
					if (filespec.getAttributeByName("File")==null ||filespec.getAttributeByName("File").getSimpleValue()==null ){
						Log.getInstance().logERROR("Component has a file spec mapping entity with empty file field","",component.getID());
						bcg.fatalError();
						throw new Exception();
					}
					String filePath = filespec.getAttributeByName("File").getSimpleValue();
					if (filePath==null){
						Log.getInstance().logERROR("Component has a file spec mapping entity with empty file field","",component.getID());
						bcg.fatalError();
						throw new Exception();
					}

					filePath=filePath.replace('\\', '/');
					String path=filePath.substring(filePath.indexOf('/'));
					path=path.substring(1,path.indexOf('.'));
					String className=path.replace("/", ".");
					testingDepl.add(new Var("classname",className));
				}
				j++;
			} catch (NullEntity e) {
				Log.getInstance().logERROR("Entity "+component.getID()+" hash an entry with empty entity","",component.getID());
				bcg.fatalError();
				throw e;
			} catch (NotFound e) {				
				e.printStackTrace();
				throw e;
			}

		}
	}
}
