/*
    Copyright (C) 2013 Jorge Gomez Sanz

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
package ingenias.codeproc.macros;

import java.util.Hashtable;
import java.util.Vector;

import ingenias.codeproc.Utils;
import ingenias.exception.InvalidEntity;
import ingenias.exception.InvalidGraph;
import ingenias.exception.NotFound;
import ingenias.exception.NotInitialised;
import ingenias.exception.NullEntity;
import ingenias.exception.TransformationException;
import ingenias.generator.browser.Browser;
import ingenias.generator.browser.Graph;
import ingenias.generator.browser.GraphCollection;
import ingenias.generator.browser.GraphEntity;
import ingenias.generator.browser.GraphEntityFactory;
import ingenias.generator.browser.GraphFactory;
import ingenias.generator.browser.GraphRelationship;
import ingenias.generator.browser.GraphRelationshipFactory;
import ingenias.generator.browser.GraphRole;
import ingenias.generator.browser.RelationshipFactory;
/**
 * 
 * @author Jorge Gomez Sanz
 * 
 * Transform 1: when the pconnects source/target contains attributes, 
 * then create wfproduces and wfconsumes in each connected task
 * 
 * Transform 2: when the  pconnects source and target contains nothing, create fake 
 * facts that serve to connect both tasks
 * 
 * Transform 3: when the  pconnects source has something an target has nothing, or the opposite, create fake
 * facts that serve to connect both tasks 
 *
 */
public class MacroTaskPConnects extends Macro{

	public MacroTaskPConnects(Browser browser) {
		super(browser);
	}

	@Override
	public Vector<String>  apply() throws TransformationException{
		Vector<String> problems=new Vector<String>(); 
		try {
			GraphEntity[] vge = Utils.generateEntitiesOfType("Task", browser);;
			GraphFactory gf=new GraphFactory(getBrowser().getState());
			GraphEntityFactory ef=new GraphEntityFactory(getBrowser().getState());
			GraphEntityFactory gef=new GraphEntityFactory(getBrowser().getState());
			GraphRelationshipFactory rf=new GraphRelationshipFactory(getBrowser().getState()); 
			Graph taskmacrosgraph;

			taskmacrosgraph = gf.createCompleteGraph("TasksAndGoalsModel", "processing_task_macross");
			for (GraphEntity task:vge){			
				GraphRelationship[] rels=Utils.getRelatedElementsRels(task,
						"Pconnects");

				for (GraphRelationship rel:rels){

					if (taskmacrosgraph.findEntity(task.getID())==null)
						gef.reuseEntity(task.getID(), taskmacrosgraph);
					Vector<GraphRole> outputsFromTheSource=Utils.getRolesFromRelationship(rel,"PConnectssource");
					Vector<GraphRole> inputsFromTheTarget=Utils.getRolesFromRelationship(rel,"PConnectstarget");


					for(GraphRole inputRole:inputsFromTheTarget){
						try {	
							// the user intended to connect two tasks but did not create the information linking both entities
							// the user did not define either inputs or outputs
							// create new fake fact to use as output
							String targetTaskID=inputRole.getPlayer().getID();
							String sourceTaskID=outputsFromTheSource.firstElement().getPlayer().getID(); // there can be only one source
							String fakeFactID="fake_"+sourceTaskID+"_output_for_task_"+targetTaskID; // rel id is used to ensure the uniqueness of the fact id
							if (browser.findEntity(fakeFactID)==null){
								if (taskmacrosgraph.findEntity(targetTaskID)==null)
									gef.reuseEntity(targetTaskID, taskmacrosgraph);
								if (taskmacrosgraph.findEntity(sourceTaskID)==null)
									gef.reuseEntity(sourceTaskID, taskmacrosgraph);

								GraphEntity newFakeFactEntity=ef.createEntity("FrameFact",fakeFactID, taskmacrosgraph);
								Vector<Hashtable<String, String>> assignments = null;
								assignments = 
										rf.getPossibleRoleAssignment("WFProduces", 
												new String[]{fakeFactID,sourceTaskID}, taskmacrosgraph, browser);		
								rf.createRelationship("WFProduces", taskmacrosgraph, assignments.firstElement());
								// the user intended to connect two tasks but did not create the information linking both entities
								// the user did not define either inputs or outputs
								// create new fake fact to use as output
								assignments = 
										rf.getPossibleRoleAssignment("WFConsumes", 
												new String[]{fakeFactID,targetTaskID}, taskmacrosgraph, browser);		
								rf.createRelationship("WFConsumes", taskmacrosgraph, assignments.firstElement());
							}

						} catch (NullEntity e) {
							e.printStackTrace();
						} 
						catch (NotFound e) {
							e.printStackTrace();
						} catch (InvalidEntity e) {
							e.printStackTrace();
						}
					}


					for(GraphRole outputRole:outputsFromTheSource){


						try {																		
							if (outputRole.getPlayer().getID().equals(task.getID())){
								GraphCollection taskoutputs = outputRole.getAttributeByName("TaskOutput").getCollectionValue();								
								if (taskoutputs.size()>0){
									for (int k=0;k<taskoutputs.size();k++){
										GraphEntity taskoutput = taskoutputs.getElementAt(k);
										String operation=taskoutput.getAttributeByName("Operation").getSimpleValue(); //operation type
										GraphEntity mentalEntity=taskoutput.getAttributeByName("AffectedElement").getEntityValue(); // a Mental entity
										gef.reuseEntity(mentalEntity.getID(), taskmacrosgraph);
										Vector<Hashtable<String, String>> assignments = null;
										switch (operation){
										case "+": 							 
											assignments = 
											rf.getPossibleRoleAssignment("WFProduces", 
													new String[]{mentalEntity.getID(),task.getID()}, taskmacrosgraph, browser);							 
											rf.createRelationship("WFProduces", taskmacrosgraph, assignments.firstElement());
											break; // a a WFProduces
										case "++":
											assignments = 
											rf.getPossibleRoleAssignment("GTCreates", 
													new String[]{mentalEntity.getID(),task.getID()}, taskmacrosgraph, browser);							 
											rf.createRelationship("GTCreates", taskmacrosgraph, assignments.firstElement());
											break; // a GTCreates									
										}
									}
								} 

							}

						} catch (NullEntity e) {
							e.printStackTrace();
						} 
						catch (NotFound e) {
							e.printStackTrace();
						} catch (InvalidEntity e) {
							e.printStackTrace();
						}
					}


					for(GraphRole inputRole:inputsFromTheTarget){
						try {					
							if (inputRole.getPlayer().getID().equals(task.getID())){
								GraphCollection taskinputs = inputRole.getAttributeByName("TaskInput").getCollectionValue(); // A TaskOutputDefinition
								String targetTaskID=inputRole.getPlayer().getID();
								if (taskinputs.size()>0){
									for (int k=0;k<taskinputs.size();k++){
										GraphEntity taskinput = taskinputs.getElementAt(k);
										String operation=taskinput.getAttributeByName("Operation").getSimpleValue(); //operation type
										GraphEntity mentalEntity=taskinput.getAttributeByName("AffectedElement").getEntityValue(); // a Mental entity
										gef.reuseEntity(mentalEntity.getID(), taskmacrosgraph);
										Vector<Hashtable<String, String>> assignments = null;
										switch (operation){
										case "-": 							 
											assignments = 
											rf.getPossibleRoleAssignment("WFConsumes", 
													new String[]{mentalEntity.getID(),task.getID()}, taskmacrosgraph, browser);							 
											rf.createRelationship("WFConsumes", taskmacrosgraph, assignments.firstElement());
											break; // a a WFProduces
										case "--":
											assignments = 
											rf.getPossibleRoleAssignment("Consumes", 
													new String[]{mentalEntity.getID(),task.getID()}, taskmacrosgraph, browser);							 
											rf.createRelationship("Consumes", taskmacrosgraph, assignments.firstElement());
											break; // a GTCreates									
										case "?":
											assignments = 
											rf.getPossibleRoleAssignment("GTModifies", 
													new String[]{mentalEntity.getID(),task.getID()}, taskmacrosgraph, browser);							 
											rf.createRelationship("GTModifies", taskmacrosgraph, assignments.firstElement());
											break; // a GTCreates
										}
									}
								} 
							}

						} catch (NullEntity e) {
							e.printStackTrace();
						} 
						catch (NotFound e) {
							e.printStackTrace();
						} catch (InvalidEntity e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (InvalidGraph | NotInitialised e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidEntity e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return problems;


	}

}
