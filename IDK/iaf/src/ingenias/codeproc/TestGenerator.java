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

import java.util.Hashtable;
import java.util.Vector;

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
				testingDepl.add(new Var("test", Utils.replaceBadChars(test.getID())));
				addAgentIdsToBeLaunchedAtTest(testingDepl, test,generator,browser);


			}

		} catch (NotInitialised e) {			
			e.printStackTrace();
		}

	}


	private void addAgentIdsToBeLaunchedAtTest(Repeat testingDepl,
			GraphEntity test,BasicCodeGenerator bcg,Browser browser) throws NotInitialised {
		GraphEntity[] deployPacks = Utils
		.generateEntitiesOfType("TestingPackage",browser);
		if (deployPacks.length > 0) {
			for (int k = 0; k < deployPacks.length; k++) {
				try {

					GraphAttribute deplPackageAttr = deployPacks[k]
					                                             .getAttributeByName("TestingDeployment");
					GraphAttribute testAttr = deployPacks[k].getAttributeByName("Tests");


					if (deplPackageAttr != null) {
						GraphEntity deplPackage = deplPackageAttr.getEntityValue();
						if (deplPackage != null) {
							Repeat depl = new Repeat("deploynode");
							DeploymentGenerator.generateDeploymentPack(deplPackage, depl,bcg); // for build.xml script generation


							if (testAttr!=null && 
									testAttr.getCollectionValue()!=null && 
									testAttr.getCollectionValue().size()>0){
								GraphCollection tests = testAttr.getCollectionValue();

								for (int j=0;j<tests.size();j++){								
									GraphEntity localTest=tests.getElementAt(j);		
									if (localTest.equals(test)){
										testingDepl.add(depl);				
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

					testingDepl.add(new Var("testingconfig",Utils.replaceBadChars(deployPacks[k].getID())));

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
									Hashtable<String,String> agentIds=DeploymentGenerator.generateDeploymentPack(deplPackage, depl,bcg); // for JUnit class initialization per test

									GraphEntity test=tests.getElementAt(j);																											
									testRepeat.add(new Var("test", Utils.replaceBadChars(test.getID())));
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
									} else {
										if (relatedComponents!=null && 
												relatedComponents.length>0){
											bcg.fatalError();
											Log.getInstance().logERROR(
													"The test element must be associated to one INGENIAS Component","", test.getID());

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


	/**
	 * 
	 * 
	 * @param p
	 * @throws NotInitialised
	 */
	public  void generateSimulationDeployment(Sequences p, BasicCodeGenerator bcg,Browser browser)
	throws NotInitialised {
		GraphEntity[] deployPacks = Utils
		.generateEntitiesOfType("SimulationPackage",browser);
		String port = "60000";
		if (deployPacks.length > 0) {
			for (int k = 0; k < deployPacks.length; k++) {
				try {
					Repeat testingDepl = new Repeat("simulationnode");
					p.addRepeat(testingDepl);

					testingDepl.add(new Var("simulationconfig",Utils.replaceBadChars(deployPacks[k].getID())));

					GraphAttribute deplPackageAttr = deployPacks[k]
					                                             .getAttributeByName("SimulationDeployment");


					if (deplPackageAttr != null && deplPackageAttr.getEntityValue()!=null) {
						GraphEntity deplPackage = deplPackageAttr.getEntityValue();

						Repeat depl = new Repeat("deploynode");
						testingDepl.add(depl);
						Hashtable<String,String> agentIds=DeploymentGenerator.generateDeploymentPack(deplPackage, depl,bcg); 
						GraphAttribute injectedEventsAttr = deployPacks[k].getAttributeByName("InjectedEvents");

						GraphAttribute extractedInfoAttr = deployPacks[k].getAttributeByName("ExtractedInformation");
						GraphAttribute simLengthAttr = deployPacks[k].getAttributeByName("SimLength");
						GraphAttribute deltaTAttr = deployPacks[k].getAttributeByName("DeltaT");

						if (simLengthAttr!=null && simLengthAttr.getSimpleValue()!=null){
							try {
								int simLength=Integer.parseInt(simLengthAttr.getSimpleValue());
								testingDepl.add(new Var("simlength",simLengthAttr.getSimpleValue()));
								testingDepl.add(new Var("deltat",deltaTAttr.getSimpleValue()));
							} catch (NumberFormatException nfe){
								bcg.fatalError();
								Log.getInstance()
								.logERROR(
										"The simulation package has not defined a valid sim length. Its value is "+simLengthAttr.getSimpleValue()+" and it should be a " +
										"positive integer. Please fill in the SimLength field of entity "+deployPacks[k].getID(),
										"", deployPacks[k].getID());
							}

						} else {
							bcg.fatalError();
							Log.getInstance()
							.logERROR(
									"The simulation package has not defined a valid sim length and should be a " +
									"positive integer. Please fill in the SimLength field of entity "+deployPacks[k].getID(),
									"", deployPacks[k].getID());
						}

						// retrieve events to be inserted
						defineInjectedEvents(bcg, deployPacks, k, testingDepl,
								agentIds, injectedEventsAttr);
						// retrieve elements to be inspected
						defineObservedInformations(bcg, deployPacks, k,
								testingDepl, agentIds, extractedInfoAttr);


					}  else {
						bcg.fatalError();
						Log.getInstance()
						.logERROR(
								"The simulation package has not defined a deployment. Please fill in the SimulationDeployment field of entity "+deployPacks[k].getID(),"", deployPacks[k].getID());
					}




				} catch (NullEntity e) {					
					e.printStackTrace();
				} catch (Exception e) {					
					e.printStackTrace();
				}


			}
		}


	}

	private void defineObservedInformations(BasicCodeGenerator bcg,
			GraphEntity[] deployPacks, int k, Repeat testingDepl,
			Hashtable<String, String> agentIds, GraphAttribute extractedInfoAttr)
	throws NullEntity, NotFound {
		if (extractedInfoAttr!=null && extractedInfoAttr.getCollectionValue()!=null &&
				extractedInfoAttr.getCollectionValue().size()>0){
			for (int j=0;j<extractedInfoAttr.getCollectionValue().size();j++){
				Repeat extractedPiecesInformation=new Repeat("simextractedinformation");
				testingDepl.add(extractedPiecesInformation);
				GraphEntity extractedInfo = extractedInfoAttr.getCollectionValue().getElementAt(j);
				Vector<GraphEntity> associatedCode = Utils.getRelatedElementsVector(extractedInfo, "CDUsesCode", "CDUsesCodetarget");
				GraphAttribute pollEachSimTimeUnitsAttr = extractedInfo.getAttributeByName("PollEachSimTimeUnits");
				GraphAttribute pollAgentsInDeploymentAttr = extractedInfo.getAttributeByName("PollAgentsInDeployment");
				GraphAttribute EntitiesToExtractAttr = extractedInfo.getAttributeByName("EntitiesToExtract");
				if (pollEachSimTimeUnitsAttr!=null && pollEachSimTimeUnitsAttr.getSimpleValue()!=null){
					try {
						int pollingFrequence=Integer.parseInt(pollEachSimTimeUnitsAttr.getSimpleValue());
						extractedPiecesInformation.add(new Var("simtime", ""+pollingFrequence));
						if (pollAgentsInDeploymentAttr.getEntityValue()!=null){
							GraphEntity receivers = pollAgentsInDeploymentAttr.getEntityValue();
							Vector<String> aids=getAffectedAgents(receivers.getID(),agentIds);
							if (!aids.isEmpty()){
								for (String aid:aids){
									Repeat affectedAgent=new Repeat("surveyedagent");
									extractedPiecesInformation.add(affectedAgent);
									affectedAgent.add(new Var("agentid",aid));
									if (EntitiesToExtractAttr!=null &&EntitiesToExtractAttr.getCollectionValue()!=null ){
										for (int l=0;l<EntitiesToExtractAttr.getCollectionValue().size();l++){
											Repeat infoToAssert=new Repeat("extractedinfo");
											affectedAgent.add(infoToAssert);
											GraphEntity information=EntitiesToExtractAttr.getCollectionValue().getElementAt(l);
											infoToAssert.add(new Var("extractedinfotype",information.getID()));
											if (associatedCode.size()>0){
												if (associatedCode.size()==1){
													infoToAssert.add(new Var("extractioncode",associatedCode.elementAt(0).getAttributeByName("Code").getSimpleValue()));
												} else {
													bcg.fatalError();
													Log.getInstance()
													.logERROR(
															"There cannot be more than one code components associated to the  information extraction entity "+extractedInfo.getID()
															,"", extractedInfo.getID());
												}
											}
											
											
										}
									}
								}
							}  else {
								bcg.fatalError();
								Log.getInstance()
								.logERROR(
										"There are no agents belonging to the deploymentunittype "+receivers.getID()+"in the deploymentpackage "+deployPacks[k].getID()
										,"", deployPacks[k].getID());
							}
						}



					} catch (NumberFormatException nfe){
						bcg.fatalError();
						Log.getInstance()
						.logERROR(
								"ProducedAtSimTime field of entity "+deployPacks[k].getID()+ " defines the value "+pollEachSimTimeUnitsAttr.getSimpleValue()+" which is not a positive value","", deployPacks[k].getID());
					}
				}

			}

		}
	}

	private void defineInjectedEvents(BasicCodeGenerator bcg,
			GraphEntity[] deployPacks, int k, Repeat testingDepl,
			Hashtable<String, String> agentIds,
			GraphAttribute injectedEventsAttr) throws NullEntity, NotFound {
		if (injectedEventsAttr!=null && injectedEventsAttr.getCollectionValue()!=null &&
				injectedEventsAttr.getCollectionValue().size()>0){
			for (int j=0;j<injectedEventsAttr.getCollectionValue().size();j++){
				Repeat injectedEvents=new Repeat("injectedevents");
				testingDepl.add(injectedEvents);
				GraphEntity injectedEvent = injectedEventsAttr.getCollectionValue().getElementAt(j);
				GraphAttribute producedAtSimTimeAttr = injectedEvent.getAttributeByName("ProducedAtSimTime");
				GraphAttribute receivedByAgentsInDeploymentAttr = injectedEvent.getAttributeByName("ReceivedByAgentsInDeployment");
				GraphAttribute newInformationAttr = injectedEvent.getAttributeByName("NewInformation");
				if (producedAtSimTimeAttr!=null && producedAtSimTimeAttr.getSimpleValue()!=null){
					try {
						int simTime=Integer.parseInt(producedAtSimTimeAttr.getSimpleValue());
						injectedEvents.add(new Var("simtime", ""+simTime));
						if (receivedByAgentsInDeploymentAttr.getEntityValue()!=null){
							GraphEntity receivers = receivedByAgentsInDeploymentAttr.getEntityValue();
							Vector<String> aids=getAffectedAgents(receivers.getID(),agentIds);
							for (String aid:aids){
								Repeat affectedAgent=new Repeat("affectedagent");
								injectedEvents.add(affectedAgent);
								affectedAgent.add(new Var("agentid",aid));
							}
						}
						if (newInformationAttr!=null &&newInformationAttr.getEntityValue()!=null ){
							GraphEntity information=newInformationAttr.getEntityValue();
							injectedEvents.add(new Var("informationid",information.getID()));
						}


					} catch (NumberFormatException nfe){
						bcg.fatalError();
						Log.getInstance()
						.logERROR(
								"ProducedAtSimTime field of entity "+deployPacks[k].getID()+ " defines the value "+producedAtSimTimeAttr.getSimpleValue()+" which is not a positive value","", deployPacks[k].getID());
					}
				}

			}
		}
	}


	private Vector<String> getAffectedAgents(String deplid,
			Hashtable<String, String> agentIds) {
		Vector<String> ids=new Vector<String>();
		for (String iad:agentIds.keySet()){
			if (agentIds.get(iad).equalsIgnoreCase(deplid)){
				ids.add(iad);
			}
		}
		return ids;
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
