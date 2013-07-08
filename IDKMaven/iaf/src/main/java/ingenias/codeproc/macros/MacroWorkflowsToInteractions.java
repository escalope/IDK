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

import ingenias.codeproc.Utils;
import ingenias.exception.GenerationException;
import ingenias.exception.InvalidAttribute;
import ingenias.exception.InvalidColection;
import ingenias.exception.InvalidEntity;
import ingenias.exception.InvalidGraph;
import ingenias.exception.NotFound;
import ingenias.exception.NotInitialised;
import ingenias.exception.NullEntity;
import ingenias.exception.TransformationException;
import ingenias.generator.browser.Browser;
import ingenias.generator.browser.Graph;
import ingenias.generator.browser.GraphAttribute;
import ingenias.generator.browser.GraphAttributeFactory;
import ingenias.generator.browser.GraphCollection;
import ingenias.generator.browser.GraphEntity;
import ingenias.generator.browser.GraphEntityFactory;
import ingenias.generator.browser.GraphFactory;
import ingenias.generator.browser.GraphRelationship;
import ingenias.generator.browser.GraphRelationshipFactory;
import ingenias.generator.browser.GraphRole;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

/**
 * 
 * @author ingenias
 * 
 * It converts a workflow of tasks into a sequence of information exchange messages and creates an interaction
 * accordingly. This macro has to be launched after MacroTaskPConnects to convert properly new WFProduces and WFConsumes
 * relationships into information transferred through InteractionUnits
 *
 */
public class MacroWorkflowsToInteractions extends Macro{

	public MacroWorkflowsToInteractions(Browser browser) {
		super(browser);
	}

	private HashSet<GraphEntity> getTaskRole(GraphEntity task){
		Vector<GraphEntity> relatedRoles;
		try {
			relatedRoles = Utils.getRelatedElementsVector(task, "WFResponsable", "WFResponsablesource");		
			return new HashSet<GraphEntity>(relatedRoles);
		} catch (NullEntity e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new HashSet<GraphEntity>();
	}


	public Vector<String> apply() throws TransformationException  {
		Vector<String> problems=new Vector<String>(); 
		try {
			GraphEntity[] vge = Utils.generateEntitiesOfType("Workflow", browser);;
			GraphFactory gf=new GraphFactory(getBrowser().getState());
			GraphEntityFactory gef=new GraphEntityFactory(getBrowser().getState());
			GraphAttributeFactory gaf=new GraphAttributeFactory(getBrowser().getState());
			GraphRelationshipFactory grf=new GraphRelationshipFactory(getBrowser().getState());

			int newGraphCounter=0;
			for (GraphEntity workflow:vge){
				Graph interactionmacrosgraph = gf.createCompleteGraph("InteractionModel", "processing_workflow_interactions_macross"+newGraphCounter);
				newGraphCounter++;


				GraphCollection workflowTasksAttribute = workflow.getAttributeByName("Tasks").getCollectionValue();								
				if (workflowTasksAttribute.size()>0){
					HashSet<GraphEntity> workflowTasksHS=new	HashSet<GraphEntity>();					
					for (int k=0;k<workflowTasksAttribute.size();k++){
						workflowTasksHS.add(workflowTasksAttribute.getElementAt(k));
					}
					GraphEntity firstTask=findFirstTask(workflowTasksHS);		

					GraphEntity newInteraction = createMainInteraction(gef,
							gaf, grf, interactionmacrosgraph);

					modifyTaskToCreateconverSation(gf,gef,
							gaf, grf,firstTask, newInteraction, workflow);
					//	colaboratingRoles.add(connectedTaskResponsibles.iterator().next());
					HashSet<GraphEntity> involvedRoles=new HashSet<GraphEntity>();
					Vector<GraphEntity> newInteractionUnits=createInteractionUnits(gef, gaf, grf,
							interactionmacrosgraph, workflowTasksHS);	
					GraphEntity firstInteractionUnit=findFirstInteractionUnit(newInteractionUnits);
					HashSet<GraphEntity> colRoles=getColaboratingRoles(newInteractionUnits,firstInteractionUnit);
					permitTaskAccesstoInteraction(
							gf,gef,gaf,grf,newInteraction,firstTask, workflowTasksHS,
							interactionmacrosgraph);
					associateColaboratingRoles(gef, gaf, grf,newInteraction,colRoles, interactionmacrosgraph);
					associateInitialisingRol(gef, gaf, grf, 
							newInteraction,							
							getTaskRole(firstTask).iterator().next(), 
							interactionmacrosgraph);

				}
			}
		} catch (NullEntity | InvalidEntity | NotFound | InvalidGraph | NotInitialised ne ){
			ne.printStackTrace();
			throw new TransformationException(ne);

		} catch (InvalidColection e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TransformationException(e);
		} catch (InvalidAttribute e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new TransformationException(e);
		}
		return problems;

	}

	private GraphEntity findFirstInteractionUnit(
			Vector<GraphEntity> newInteractionUnits) throws NullEntity, NotFound {
		HashSet<GraphEntity> firstTasks=new HashSet<GraphEntity>();
		for (GraphEntity currentTask:newInteractionUnits){
			Vector<GraphEntity> pconnectsRels = Utils.getRelatedElementsVector(currentTask,"UIPrecedes","UIPrecedestarget");
			firstTasks.addAll(pconnectsRels);			
		}
		HashSet<GraphEntity> candidateFirstinteractionUnits=new HashSet<GraphEntity>(newInteractionUnits);
		candidateFirstinteractionUnits.removeAll(firstTasks);
		if (candidateFirstinteractionUnits.size()==1)
			return candidateFirstinteractionUnits.iterator().next();

		throw new ingenias.exception.NotFound("I expected one Interaction Unnit and there are "+candidateFirstinteractionUnits);
	}

	private void associateInitialisingRol(GraphEntityFactory gef,
			GraphAttributeFactory gaf, GraphRelationshipFactory grf,
			GraphEntity newInteraction, GraphEntity rol,
			Graph interactionmacrosgraph) throws NotFound, InvalidEntity {

		Vector<Hashtable<String, String>> assignments = grf.getPossibleRoleAssignment(
				"IInitiates", 
				new String[]{rol.getID(),newInteraction.getID()}, 
				interactionmacrosgraph, browser);	
		grf.createRelationship("IInitiates", interactionmacrosgraph, assignments.firstElement());		
	}

	public GraphEntity createMainInteraction(GraphEntityFactory gef,
			GraphAttributeFactory gaf, GraphRelationshipFactory grf,
			Graph interactionmacrosgraph) throws InvalidEntity, NotFound,
			InvalidAttribute {
		GraphEntity newInteraction = gef.createEntity("Interaction", interactionmacrosgraph);
		GraphEntity newProtSpec = gef.createEntity("GRASIASpecification", interactionmacrosgraph);
		GraphEntity modelEntity = gef.createModelEntity(
				"InteractionModel", newInteraction.getID()+"IM", interactionmacrosgraph);	
		
		Vector<Hashtable<String, String>> assignment = grf.getPossibleRoleAssignment("IHasSpec", 
				new String[]{newProtSpec.getID(),newInteraction.getID()});
		grf.createRelationship("IHasSpec", interactionmacrosgraph, assignment.get(0));

		GraphAttribute modelAtt = gaf.createAttribute("ModelThatContainsSpecification",modelEntity, interactionmacrosgraph);
		gaf.setAttribute(newProtSpec,modelAtt);

		modelEntity.setAttributeValue("modelID",interactionmacrosgraph.getID());
		return newInteraction;
	}

	private void associateColaboratingRoles(GraphEntityFactory gef,
			GraphAttributeFactory gaf, GraphRelationshipFactory grf,
			GraphEntity newInteraction,
			HashSet<GraphEntity> colRoles, 
			Graph interactionmacrosgraph) throws NotFound, InvalidEntity {
		for (GraphEntity colrol:colRoles){
			Vector<Hashtable<String, String>> assignments = grf.getPossibleRoleAssignment(
					"IColaborates", 
					new String[]{colrol.getID(),newInteraction.getID()}, 
					interactionmacrosgraph, browser);	
			grf.createRelationship("IColaborates", interactionmacrosgraph, assignments.firstElement());
		}

	}

	private HashSet<GraphEntity> getColaboratingRoles(
			Vector<GraphEntity> newInteractionUnits, GraphEntity firstInteractionUnit) throws NullEntity {
		HashSet<GraphEntity> colRoles=new HashSet<GraphEntity> ();
		for (GraphEntity iu:newInteractionUnits){
			Vector<GraphEntity> colrolesiu = Utils.getRelatedElementsVector(iu, "UIColaborates", "UIColaboratestarget");
			colRoles.addAll(colrolesiu);
			if (!iu.equals(firstInteractionUnit)){
				Vector<GraphEntity> initRolesiu = Utils.getRelatedElementsVector(iu, "UIInitiates", "UIInitiatestarget");
				colRoles.addAll(initRolesiu);
			}
		}
		Vector<GraphEntity> initRolesiu = Utils.getRelatedElementsVector(firstInteractionUnit, "UIInitiates", "UIInitiatestarget");
		colRoles.removeAll(initRolesiu); // to remove the initiator of the interaction from the colaborators
		return colRoles;
	}

	public Vector<GraphEntity> createInteractionUnits(GraphEntityFactory gef,
			GraphAttributeFactory gaf, GraphRelationshipFactory grf,
			Graph interactionmacrosgraph, HashSet<GraphEntity> workflowTasksHS)
					throws InvalidEntity, NullEntity, NotFound, InvalidColection,
					InvalidAttribute {
		Vector<GraphEntity> newInteractionUnits=new Vector<GraphEntity>();
		Hashtable<GraphEntity, GraphEntity[]> interactionUnitOriginalTasks=
				new Hashtable<GraphEntity,GraphEntity[]>();

				for (GraphEntity workflowTask:workflowTasksHS){										
					Vector<GraphRole> relatedTasksRoles = 
							Utils.getRelatedElementsRolesVector(workflowTask, "Pconnects", "PConnectstarget");		
					HashSet<GraphEntity> taskResponsibles = getTaskRole(workflowTask);
					if (!taskResponsibles.isEmpty()){
						gef.reuseEntity(taskResponsibles.iterator().next().getID(), interactionmacrosgraph);
						int numberOfInteractions=newInteractionUnits.size();
						createDiagramInteractionUnits(gef,
								gaf, grf, interactionmacrosgraph,
								workflowTasksHS, newInteractionUnits,
								interactionUnitOriginalTasks, workflowTask,
								relatedTasksRoles, taskResponsibles);
					}
				}

				HashSet<GraphEntity> alreadyVisitedUI=new HashSet<GraphEntity>();
				for (GraphEntity iu:interactionUnitOriginalTasks.keySet()){
					GraphEntity[] tasks = interactionUnitOriginalTasks.get(iu);
					for (GraphEntity iunext:interactionUnitOriginalTasks.keySet()){
						GraphEntity[] nextTasks = interactionUnitOriginalTasks.get(iu);
						if (tasks[1].equals(nextTasks[0])){
							// it is immediately after
							Vector<Hashtable<String, String>> assignments = 
									grf.getPossibleRoleAssignment("UIPrecedes", 
											new String[]{iu.getID(),iunext.getID()}, 
											interactionmacrosgraph, browser);	
							grf.createRelationship("UIPrecedes", 
									interactionmacrosgraph, assignments.firstElement());
							alreadyVisitedUI.add(iu);
							alreadyVisitedUI.add(iunext);
						}
					}
				}

				if (alreadyVisitedUI.size()!=interactionUnitOriginalTasks.keySet().size()){
					// some ius have not direct relationship
					HashSet<GraphEntity> pending=new HashSet<GraphEntity>(interactionUnitOriginalTasks.keySet());
					pending.removeAll(alreadyVisitedUI);
					for (GraphEntity pendingIU:pending){
						GraphEntity[] tasks = interactionUnitOriginalTasks.get(pendingIU);
						for (GraphEntity otherIU:interactionUnitOriginalTasks.keySet()){
							GraphEntity[] nextTasks = interactionUnitOriginalTasks.get(otherIU);
							if (pconnectsOutsideWorkflow(tasks[1],nextTasks[0],workflowTasksHS, new HashSet<GraphEntity>())){
								// there is a path that leads from pendingiu to otheriu
								Vector<Hashtable<String, String>> assignments = 
										grf.getPossibleRoleAssignment("UIPrecedes", 
												new String[]{pendingIU.getID(),otherIU.getID()}, 
												interactionmacrosgraph, browser);	
								grf.createRelationship("UIPrecedes", 
										interactionmacrosgraph, assignments.firstElement());
							} else 
								if (pconnectsOutsideWorkflow(nextTasks[1], tasks[0],workflowTasksHS, new HashSet<GraphEntity>())){
									// there is a path that leads from pendingiu to otheriu
									Vector<Hashtable<String, String>> assignments = 
											grf.getPossibleRoleAssignment("UIPrecedes", 
													new String[]{otherIU.getID(),pendingIU.getID()}, 
													interactionmacrosgraph, browser);	
									grf.createRelationship("UIPrecedes", 
											interactionmacrosgraph, assignments.firstElement());
								}
						}
					}

				}

				return newInteractionUnits;
	}

	public void createDiagramInteractionUnits(
			GraphEntityFactory gef, GraphAttributeFactory gaf,
			GraphRelationshipFactory grf, Graph interactionmacrosgraph,
			HashSet<GraphEntity> workflowTasksHS,
			Vector<GraphEntity> newInteractionUnits,
			Hashtable<GraphEntity, GraphEntity[]> interactionUnitOriginalTasks,
			GraphEntity workflowTask, Vector<GraphRole> relatedTasksRoles,
			HashSet<GraphEntity> taskResponsibles) throws NullEntity,
			InvalidEntity, NotFound, InvalidColection, InvalidAttribute {
		// for each task of relatedTasks is an InteractionUnit
		for (GraphRole connectedTask:relatedTasksRoles){
			HashSet<GraphEntity> connectedTaskResponsibles = getTaskRole(connectedTask.getPlayer());
			GraphEntity connectedTaskResponsible=connectedTaskResponsibles.iterator().next();
			GraphEntity originalTaskResponsible=taskResponsibles.iterator().next();
			String connectedRoleTaskID = connectedTaskResponsibles.iterator().next().getID();

			if (workflowTasksHS.contains(connectedTask.getPlayer()) && 
					!workflowTask.getID().equals(connectedTask.getPlayer().getID()) &&
					!connectedTaskResponsible.equals(originalTaskResponsible)){
				// an interaction unit must be defined among two connected tasks only when they are launched by different roles/agents											
				createInteractionUnitAmongTasks(gef, gaf, grf,
						interactionmacrosgraph, newInteractionUnits,
						interactionUnitOriginalTasks, workflowTask,
						connectedTask, originalTaskResponsible, connectedRoleTaskID);
			} else {
				if (!workflowTasksHS.contains(connectedTask.getPlayer()) && 
						!workflowTask.getID().equals(connectedTask.getPlayer().getID()) &&
						connectedTaskResponsibles.iterator().next().equals(taskResponsibles.iterator().next())){
					for (GraphEntity internalTask:workflowTasksHS){
						if (!internalTask.getID().equals(connectedTask.getPlayer().getID())){
							// there is a path to a workflow task
							GraphEntity connectingTaskOutsideWorkflow = 
									taskWhichPconnectsOutsideWorkflow(connectedTask.getPlayer(), internalTask, workflowTasksHS, new HashSet<GraphEntity>());
							if (connectingTaskOutsideWorkflow!=null){
								GraphRole[] graphroles = Utils.getRelatedElementsRoles(connectingTaskOutsideWorkflow, "PConnects", "PConnectstarget");
								GraphRole connectingRole=null;
								for (GraphRole gr:graphroles){
									if (gr.getPlayer().equals(internalTask))
										connectingRole=gr;
								}
								createInteractionUnitAmongTasks(gef, gaf, grf,
										interactionmacrosgraph, newInteractionUnits,
										interactionUnitOriginalTasks, connectingTaskOutsideWorkflow,
										connectingRole,	getTaskRole(connectingTaskOutsideWorkflow).iterator().next(),										
										getTaskRole(internalTask).iterator().next().getID());
							}

						}
					}

				}

			}
		}
	}

	public void createInteractionUnitAmongTasks(GraphEntityFactory gef,
			GraphAttributeFactory gaf, GraphRelationshipFactory grf,
			Graph interactionmacrosgraph,
			Vector<GraphEntity> newInteractionUnits,
			Hashtable<GraphEntity, GraphEntity[]> interactionUnitOriginalTasks,
			GraphEntity workflowTask, GraphRole connectedTask,
			GraphEntity originalTaskResponsible, String connectedTaskRoleID)
					throws InvalidEntity, NullEntity, NotFound, InvalidColection,
					InvalidAttribute {
		gef.reuseEntity(connectedTaskRoleID, interactionmacrosgraph);
		GraphEntity newInteractionUnit = 
				gef.createEntity("InteractionUnit", interactionmacrosgraph);
		interactionUnitOriginalTasks.put(newInteractionUnit, 
				new GraphEntity[]{workflowTask,	connectedTask.getPlayer()});
		newInteractionUnits.add(newInteractionUnit);
		GraphCollection taskinputs = connectedTask.getAttributeByName("TaskInput").getCollectionValue(); // A TaskOutputDefinition
		Vector<GraphEntity> transferredInfo=new Vector<GraphEntity>();
		for (int k=0;k<taskinputs.size();k++){
			GraphEntity taskinput = taskinputs.getElementAt(k);
			String operation=taskinput.getAttributeByName("Operation").getSimpleValue(); //operation type
			GraphEntity mentalEntity=taskinput.getAttributeByName("AffectedElement").getEntityValue(); // a Mental entity
			transferredInfo.add(mentalEntity);
		}
		String fakeFactID="fake_"+workflowTask.getID()+"_output_for_task_"+connectedTask.getPlayer().getID(); // rel id is used to ensure the uniqueness of the fact id
		GraphEntity synchroFact=browser.findEntity(fakeFactID);
		transferredInfo.add(synchroFact);
		if (!transferredInfo.isEmpty()){
			GraphCollection transferrredInfoColAttr = gaf.createCollection(transferredInfo, interactionmacrosgraph);
			GraphAttribute transferrredInfoAttr=gaf.createAttribute("TransferredInfo", transferrredInfoColAttr, interactionmacrosgraph);
			gaf.setAttribute(newInteractionUnit, transferrredInfoAttr);			
			Vector<Hashtable<String, String>> assignments = grf.getPossibleRoleAssignment("UIInitiates", 
					new String[]{originalTaskResponsible.getID(),newInteractionUnit.getID()}, 
					interactionmacrosgraph, browser);	
			grf.createRelationship("UIInitiates", interactionmacrosgraph, assignments.firstElement());
			assignments = grf.getPossibleRoleAssignment("UIColaborates", 
					new String[]{connectedTaskRoleID,newInteractionUnit.getID()}, 
					interactionmacrosgraph, browser);	
			grf.createRelationship("UIColaborates", interactionmacrosgraph, assignments.firstElement());
		} else {
			// an error will be triggered
		}
	}

	private GraphEntity taskWhichPconnectsOutsideWorkflow(GraphEntity graphEntity,
			GraphEntity graphEntity2, HashSet<GraphEntity> workflowTasksHS, HashSet<GraphEntity> alreadyVisitedTask) throws NullEntity {

		Vector<GraphEntity> relatedTasks = Utils.getRelatedElementsVector(graphEntity, "PConnects", "PConnectstarget");
		relatedTasks.removeAll(alreadyVisitedTask);
		for (GraphEntity currentTask:relatedTasks){
			if (graphEntity2.equals(currentTask)){
				return graphEntity;
			} else{
				if (!workflowTasksHS.contains(currentTask)){
					alreadyVisitedTask.add(currentTask);
					GraphEntity result = taskWhichPconnectsOutsideWorkflow(currentTask,graphEntity2,workflowTasksHS,alreadyVisitedTask);
					if (result!=null)
						return result;
				}
			}
		}

		return null;
	}

	private boolean pconnectsOutsideWorkflow(GraphEntity graphEntity,
			GraphEntity graphEntity2, HashSet<GraphEntity> workflowTasksHS, HashSet<GraphEntity> alreadyVisitedTask) throws NullEntity {

		return taskWhichPconnectsOutsideWorkflow(graphEntity,graphEntity2,workflowTasksHS,alreadyVisitedTask)!=null;
	}

	private void modifyTaskToCreateconverSation(GraphFactory gf, GraphEntityFactory gef,
			GraphAttributeFactory gaf, GraphRelationshipFactory grf,
			GraphEntity firstTask,
			GraphEntity newInteraction,
			GraphEntity workflow) throws InvalidGraph, NotInitialised, InvalidEntity, NotFound {
		Graph taskmacrosgraph = gf.createCompleteGraph("TasksAndGoalsModel", "macros_"+workflow.getID()+" involved tasks");
		gef.reuseEntity(firstTask.getID(), taskmacrosgraph);
		GraphEntity conversation = gef.createEntity("Conversation", taskmacrosgraph);
		conversation.setAttributeValue("Interaction", newInteraction.getEntity());
		Vector<Hashtable<String, String>> assignments = 
				grf.getPossibleRoleAssignment("GTCreates", new String[]{conversation.getID(),firstTask.getID()});
		grf.createRelationship("GTCreates", taskmacrosgraph, assignments.get(0));


	}

	private void permitTaskAccesstoInteraction(GraphFactory gf, GraphEntityFactory gef,
			GraphAttributeFactory gaf, GraphRelationshipFactory grf,
			GraphEntity newInteraction, GraphEntity firstTask,
			HashSet<GraphEntity> workflowTasks, Graph interactionDiagram) throws InvalidEntity, NotFound{
		for (GraphEntity task:workflowTasks){
			if (!task.equals(firstTask)){
				gef.reuseEntity(task.getID(), interactionDiagram);
				Vector<Hashtable<String, String>> assignments = 
						grf.getPossibleRoleAssignment("IAccesses", 
								new String[]{task.getID(),newInteraction.getID()});				
				grf.createRelationship("IAccesses", 
						interactionDiagram, 
						assignments.get(0));
			}
		}

	}

	private GraphEntity findFirstTask(HashSet<GraphEntity> workflowTasksHS) throws NotFound, NullEntity {
		HashSet<GraphEntity> firstTasks=new HashSet<GraphEntity>();
		for (GraphEntity currentTask:workflowTasksHS){
			Vector<GraphRelationship> pconnectsRels = currentTask.getAllRelationships("PConnects");
			for (GraphRelationship rel:pconnectsRels){
				if (rel.getRoles("PConnectstarget").length>0 && 
						workflowTasksHS.contains(rel.getRoles("PConnectssource")[0].getPlayer())){
					for (GraphRole gr:rel.getRoles("PConnectstarget")){
						firstTasks.add(gr.getPlayer());	
					}												
				}
			}
		}
		workflowTasksHS=new HashSet<GraphEntity>(workflowTasksHS);
		workflowTasksHS.removeAll(firstTasks);
		HashSet<GraphEntity> connectedIndirectly=new HashSet<GraphEntity>();
		for (GraphEntity gr:workflowTasksHS){
			for (GraphEntity other:workflowTasksHS){
				if (!other.equals(gr))
					if (pconnectsOutsideWorkflow(gr,other,workflowTasksHS,new HashSet())){
						connectedIndirectly.add(other);
					}
			}
		}
		workflowTasksHS.removeAll(connectedIndirectly);		
		if (workflowTasksHS.size()==1)
			return workflowTasksHS.iterator().next();

		throw new ingenias.exception.NotFound("I expected one initial task within the workflow and there are "+workflowTasksHS);		

	}

}
