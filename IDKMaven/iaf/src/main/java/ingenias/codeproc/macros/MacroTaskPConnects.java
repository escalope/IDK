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
import ingenias.generator.browser.GraphRelationshipFactory;
import ingenias.generator.browser.GraphRole;

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
			GraphEntityFactory gef=new GraphEntityFactory(getBrowser().getState());
			Graph taskmacrosgraph;

			taskmacrosgraph = gf.createCompleteGraph("TasksAndGoalsModel", "processing_task_macross");
			for (GraphEntity task:vge){
				Vector<GraphRole> outputs = Utils.getRelatedElementsRolesVector(task,
						"Pconnects",
						"PConnectssource");
				Vector<GraphRole> inputs = Utils.getRelatedElementsRolesVector(task,
						"Pconnects",
						"PConnectstarget");	
				gef.reuseEntity(task.getID(), taskmacrosgraph);
				GraphRelationshipFactory rf=new GraphRelationshipFactory(getBrowser().getState()); 
				for(GraphRole outputRole:outputs){

					try {			
						if (outputRole.getPlayer().getID().equals(task.getID())){
							GraphCollection taskoutputs = outputRole.getAttributeByName("TaskOutput").getCollectionValue(); // A TaskOutputDefinition
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

					} catch (NullEntity e) {
						e.printStackTrace();
					} 
					catch (NotFound e) {
						e.printStackTrace();
					} catch (InvalidEntity e) {
						e.printStackTrace();
					}
				}


				for(GraphRole inputRole:inputs){
					try {					
						if (inputRole.getPlayer().getID().equals(task.getID())){
							GraphCollection taskinputs = inputRole.getAttributeByName("TaskInput").getCollectionValue(); // A TaskOutputDefinition
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
