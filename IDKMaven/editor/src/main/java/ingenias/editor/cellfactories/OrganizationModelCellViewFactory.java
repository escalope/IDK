

/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz over original code from Ruben Fuentes
 * 
 *   Modifications over original code from jgraph.sourceforge.net
 * 
 * This file is part of the INGENME tool. INGENME is an open source meta-editor
 * which produces customized editors for user-defined modeling languages
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3 of the License
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 **/
 
package ingenias.editor.cellfactories;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.Map;
import java.util.Hashtable;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Map;
import java.util.Hashtable;
import java.util.ArrayList;
import javax.swing.event.UndoableEditEvent;
import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.jgraph.event.*;
import java.util.Vector;
import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.jgraph.event.*;
import org.jgraph.plaf.basic.*;
import ingenias.editor.entities.*;
import ingenias.editor.cell.*;
import ingenias.editor.events.*;

public class OrganizationModelCellViewFactory implements CellViewFactory {

public OrganizationModelCellViewFactory() {}
 

 	public org.jgraph.graph.CellView createView(GraphModel model, Object cell) {
		
			org.jgraph.graph.CellView view = null;
			if (model.isPort(cell))
				view = new PortView(cell);
			else if (model.isEdge(cell))
				view = createEdgeView(cell);
			else
				view = createVertexView(cell);
			return view;
		}

  // Modificar agregando nuevas clases VIEW segun se vayan a?endo

  protected org.jgraph.graph.VertexView createVertexView(Object v) {
    Object userObject = ( (DefaultGraphCell) v).getUserObject();

   // Diagram Objects start here


   if (userObject.getClass().equals(Agent.class)){
           return new ingenias.editor.cell.AgentView(v);
   }

   if (userObject.getClass().equals(Organization.class)){
           return new ingenias.editor.cell.OrganizationView(v);
   }

   if (userObject.getClass().equals(OrganizationGroup.class)){
           return new ingenias.editor.cell.OrganizationGroupView(v);
   }

   if (userObject.getClass().equals(OrganizationNetwork.class)){
           return new ingenias.editor.cell.OrganizationNetworkView(v);
   }

   if (userObject.getClass().equals(Role.class)){
           return new ingenias.editor.cell.RoleView(v);
   }

   if (userObject.getClass().equals(Resource.class)){
           return new ingenias.editor.cell.ResourceView(v);
   }

   if (userObject.getClass().equals(Application.class)){
           return new ingenias.editor.cell.ApplicationView(v);
   }

   if (userObject.getClass().equals(EnvironmentApplication.class)){
           return new ingenias.editor.cell.EnvironmentApplicationView(v);
   }

   if (userObject.getClass().equals(InternalApplication.class)){
           return new ingenias.editor.cell.InternalApplicationView(v);
   }

   if (userObject.getClass().equals(Task.class)){
           return new ingenias.editor.cell.TaskView(v);
   }

   if (userObject.getClass().equals(Plan.class)){
           return new ingenias.editor.cell.PlanView(v);
   }

   if (userObject.getClass().equals(Workflow.class)){
           return new ingenias.editor.cell.WorkflowView(v);
   }

   if (userObject.getClass().equals(Interaction.class)){
           return new ingenias.editor.cell.InteractionView(v);
   }

   if (userObject.getClass().equals(Goal.class)){
           return new ingenias.editor.cell.GoalView(v);
   }

   if (userObject.getClass().equals(Fact.class)){
           return new ingenias.editor.cell.FactView(v);
   }

   if (userObject.getClass().equals(FrameFact.class)){
           return new ingenias.editor.cell.FrameFactView(v);
   }

   if (userObject.getClass().equals(Believe.class)){
           return new ingenias.editor.cell.BelieveView(v);
   }

   if (userObject.getClass().equals(GeneralEvent.class)){
           return new ingenias.editor.cell.GeneralEventView(v);
   }

   if (userObject.getClass().equals(ApplicationEvent.class)){
           return new ingenias.editor.cell.ApplicationEventView(v);
   }

   if (userObject.getClass().equals(ApplicationEventSlots.class)){
           return new ingenias.editor.cell.ApplicationEventSlotsView(v);
   }

   if (userObject.getClass().equals(AutonomousEntityQuery.class)){
           return new ingenias.editor.cell.AutonomousEntityQueryView(v);
   }

   if (userObject.getClass().equals(AgentRequirementsQuery.class)){
           return new ingenias.editor.cell.AgentRequirementsQueryView(v);
   }

   if (userObject.getClass().equals(ConcreteAgent.class)){
           return new ingenias.editor.cell.ConcreteAgentView(v);
   }

   if (userObject.getClass().equals(TextNote.class)){
           return new ingenias.editor.cell.TextNoteView(v);
   }

   if (userObject.getClass().equals(UMLComment.class)){
           return new ingenias.editor.cell.UMLCommentView(v);
   }

   if (userObject.getClass().equals(RoleWS.class)){
           return new ingenias.editor.cell.RoleWSView(v);
   }

   if (userObject.getClass().equals(TaskWS.class)){
           return new ingenias.editor.cell.TaskWSView(v);
   }

   if (userObject.getClass().equals(GoalStateWS.class)){
           return new ingenias.editor.cell.GoalStateWSView(v);
   }

   if (userObject.getClass().equals(AgentWS.class)){
           return new ingenias.editor.cell.AgentWSView(v);
   }

   if (userObject.getClass().equals(WorkflowBox.class)){
           return new ingenias.editor.cell.WorkflowBoxView(v);
   }

   if (userObject.getClass().equals(ApplicationWS.class)){
           return new ingenias.editor.cell.ApplicationWSView(v);
   }

   if (userObject.getClass().equals(BoxedTask.class)){
           return new ingenias.editor.cell.BoxedTaskView(v);
   }

   if (userObject.getClass().equals(ContextBindingTask.class)){
           return new ingenias.editor.cell.ContextBindingTaskView(v);
   }

   if (userObject.getClass().equals(ContextReleaseTask.class)){
           return new ingenias.editor.cell.ContextReleaseTaskView(v);
   }


   // Diagram Relationships start here

   if (v.getClass().equals(UMLAnnotatedElementEdge.class)){
           return new ingenias.editor.cell.UMLAnnotatedElementView(v);
   }

   if (v.getClass().equals(EResourceBelongsToEdge.class)){
           return new ingenias.editor.cell.EResourceBelongsToView(v);
   }

   if (v.getClass().equals(OHasGroupEdge.class)){
           return new ingenias.editor.cell.OHasGroupView(v);
   }

   if (v.getClass().equals(OHasMemberEdge.class)){
           return new ingenias.editor.cell.OHasMemberView(v);
   }

   if (v.getClass().equals(OHasWFEdge.class)){
           return new ingenias.editor.cell.OHasWFView(v);
   }

   if (v.getClass().equals(ODecomposesGroupEdge.class)){
           return new ingenias.editor.cell.ODecomposesGroupView(v);
   }

   if (v.getClass().equals(ODecomposesWFEdge.class)){
           return new ingenias.editor.cell.ODecomposesWFView(v);
   }

   if (v.getClass().equals(GTPursuesEdge.class)){
           return new ingenias.editor.cell.GTPursuesView(v);
   }

   if (v.getClass().equals(WFConnectsEdge.class)){
           return new ingenias.editor.cell.WFConnectsView(v);
   }

   if (v.getClass().equals(WFUsesEdge.class)){
           return new ingenias.editor.cell.WFUsesView(v);
   }

   if (v.getClass().equals(WFContainsTaskEdge.class)){
           return new ingenias.editor.cell.WFContainsTaskView(v);
   }

   if (v.getClass().equals(WFConsumesEdge.class)){
           return new ingenias.editor.cell.WFConsumesView(v);
   }

   if (v.getClass().equals(ConsumesEdge.class)){
           return new ingenias.editor.cell.ConsumesView(v);
   }

   if (v.getClass().equals(WFDecomposesEdge.class)){
           return new ingenias.editor.cell.WFDecomposesView(v);
   }

   if (v.getClass().equals(WFProducesEdge.class)){
           return new ingenias.editor.cell.WFProducesView(v);
   }

   if (v.getClass().equals(AGORelationshipGroupEdge.class)){
           return new ingenias.editor.cell.AGORelationshipGroupView(v);
   }

   if (v.getClass().equals(AGORelationshipMemberEdge.class)){
           return new ingenias.editor.cell.AGORelationshipMemberView(v);
   }

   if (v.getClass().equals(AGORelationshipOrgEdge.class)){
           return new ingenias.editor.cell.AGORelationshipOrgView(v);
   }

   if (v.getClass().equals(AGOSubordinationRelationshipGroupEdge.class)){
           return new ingenias.editor.cell.AGOSubordinationRelationshipGroupView(v);
   }

   if (v.getClass().equals(AGOSubordinationRelationshipMemberEdge.class)){
           return new ingenias.editor.cell.AGOSubordinationRelationshipMemberView(v);
   }

   if (v.getClass().equals(AGOSubordinationRelationshipOrgEdge.class)){
           return new ingenias.editor.cell.AGOSubordinationRelationshipOrgView(v);
   }

   if (v.getClass().equals(AGOCondSubordinationRelationshipGroupEdge.class)){
           return new ingenias.editor.cell.AGOCondSubordinationRelationshipGroupView(v);
   }

   if (v.getClass().equals(AGOCondSubordinationRelationshipMemberEdge.class)){
           return new ingenias.editor.cell.AGOCondSubordinationRelationshipMemberView(v);
   }

   if (v.getClass().equals(AGOCondSubordinationRelationshipOrgEdge.class)){
           return new ingenias.editor.cell.AGOCondSubordinationRelationshipOrgView(v);
   }

   if (v.getClass().equals(AGOInconditionalSubordinationRelationshipGroupEdge.class)){
           return new ingenias.editor.cell.AGOInconditionalSubordinationRelationshipGroupView(v);
   }

   if (v.getClass().equals(AGOInconditionalSubordinationRelationshipMemberEdge.class)){
           return new ingenias.editor.cell.AGOInconditionalSubordinationRelationshipMemberView(v);
   }

   if (v.getClass().equals(AGOInconditionalSubordinationRelationshipOrgEdge.class)){
           return new ingenias.editor.cell.AGOInconditionalSubordinationRelationshipOrgView(v);
   }

   if (v.getClass().equals(AGOClientServerRelationshipGroupEdge.class)){
           return new ingenias.editor.cell.AGOClientServerRelationshipGroupView(v);
   }

   if (v.getClass().equals(AGOClientServerRelationshipMemberEdge.class)){
           return new ingenias.editor.cell.AGOClientServerRelationshipMemberView(v);
   }

   if (v.getClass().equals(AGOClientServerRelationshipOrgEdge.class)){
           return new ingenias.editor.cell.AGOClientServerRelationshipOrgView(v);
   }

   if (v.getClass().equals(WFSpecifiesExecutionEdge.class)){
           return new ingenias.editor.cell.WFSpecifiesExecutionView(v);
   }

   if (v.getClass().equals(WFResponsableEdge.class)){
           return new ingenias.editor.cell.WFResponsableView(v);
   }

   if (v.getClass().equals(IInitiatesEdge.class)){
           return new ingenias.editor.cell.IInitiatesView(v);
   }

   if (v.getClass().equals(IColaboratesEdge.class)){
           return new ingenias.editor.cell.IColaboratesView(v);
   }

   if (v.getClass().equals(WFParticipatesEdge.class)){
           return new ingenias.editor.cell.WFParticipatesView(v);
   }

   if (v.getClass().equals(WFPlaysEdge.class)){
           return new ingenias.editor.cell.WFPlaysView(v);
   }

   if (v.getClass().equals(WFDecomposesWFEdge.class)){
           return new ingenias.editor.cell.WFDecomposesWFView(v);
   }

   if (v.getClass().equals(UMLAnnotatedElementEdge.class)){
           return new ingenias.editor.cell.UMLAnnotatedElementView(v);
   }

   if (v.getClass().equals(WSConnectsEdge.class)){
           return new ingenias.editor.cell.WSConnectsView(v);
   }


    return null;
  }

  protected org.jgraph.graph.EdgeView createEdgeView(Object v) {
  return new org.jgraph.graph.EdgeView(v);

//         if (v instanceof ExecuteEdge){
//           return new ExecuteView(v,this,cm);
//         }

  }

 


}
