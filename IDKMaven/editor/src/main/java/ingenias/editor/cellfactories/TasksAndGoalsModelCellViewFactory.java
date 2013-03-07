

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

public class TasksAndGoalsModelCellViewFactory implements CellViewFactory {

public TasksAndGoalsModelCellViewFactory() {}
 

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


   if (userObject.getClass().equals(FAERIEContext.class)){
           return new ingenias.editor.cell.FAERIEContextView(v);
   }

   if (userObject.getClass().equals(Agent.class)){
           return new ingenias.editor.cell.AgentView(v);
   }

   if (userObject.getClass().equals(AgentModelBelieve.class)){
           return new ingenias.editor.cell.AgentModelBelieveView(v);
   }

   if (userObject.getClass().equals(ApplicationEvent.class)){
           return new ingenias.editor.cell.ApplicationEventView(v);
   }

   if (userObject.getClass().equals(ApplicationEventSlots.class)){
           return new ingenias.editor.cell.ApplicationEventSlotsView(v);
   }

   if (userObject.getClass().equals(Application.class)){
           return new ingenias.editor.cell.ApplicationView(v);
   }

   if (userObject.getClass().equals(AgentWS.class)){
           return new ingenias.editor.cell.AgentWSView(v);
   }

   if (userObject.getClass().equals(Role.class)){
           return new ingenias.editor.cell.RoleView(v);
   }

   if (userObject.getClass().equals(Goal.class)){
           return new ingenias.editor.cell.GoalView(v);
   }

   if (userObject.getClass().equals(StateGoal.class)){
           return new ingenias.editor.cell.StateGoalView(v);
   }

   if (userObject.getClass().equals(Task.class)){
           return new ingenias.editor.cell.TaskView(v);
   }

   if (userObject.getClass().equals(Resource.class)){
           return new ingenias.editor.cell.ResourceView(v);
   }

   if (userObject.getClass().equals(Fact.class)){
           return new ingenias.editor.cell.FactView(v);
   }

   if (userObject.getClass().equals(FrameFact.class)){
           return new ingenias.editor.cell.FrameFactView(v);
   }

   if (userObject.getClass().equals(RuntimeCommFailure.class)){
           return new ingenias.editor.cell.RuntimeCommFailureView(v);
   }

   if (userObject.getClass().equals(Believe.class)){
           return new ingenias.editor.cell.BelieveView(v);
   }

   if (userObject.getClass().equals(Compromise.class)){
           return new ingenias.editor.cell.CompromiseView(v);
   }

   if (userObject.getClass().equals(GeneralEvent.class)){
           return new ingenias.editor.cell.GeneralEventView(v);
   }

   if (userObject.getClass().equals(CommunicationEvent.class)){
           return new ingenias.editor.cell.CommunicationEventView(v);
   }

   if (userObject.getClass().equals(EnvironmentApplication.class)){
           return new ingenias.editor.cell.EnvironmentApplicationView(v);
   }

   if (userObject.getClass().equals(InternalApplication.class)){
           return new ingenias.editor.cell.InternalApplicationView(v);
   }

   if (userObject.getClass().equals(TextNote.class)){
           return new ingenias.editor.cell.TextNoteView(v);
   }

   if (userObject.getClass().equals(Plan.class)){
           return new ingenias.editor.cell.PlanView(v);
   }

   if (userObject.getClass().equals(BoxedTask.class)){
           return new ingenias.editor.cell.BoxedTaskView(v);
   }

   if (userObject.getClass().equals(MentalEntityInstanceCreation.class)){
           return new ingenias.editor.cell.MentalEntityInstanceCreationView(v);
   }

   if (userObject.getClass().equals(MentalEntityInstanceAccess.class)){
           return new ingenias.editor.cell.MentalEntityInstanceAccessView(v);
   }

   if (userObject.getClass().equals(Conversation.class)){
           return new ingenias.editor.cell.ConversationView(v);
   }

   if (userObject.getClass().equals(UMLComment.class)){
           return new ingenias.editor.cell.UMLCommentView(v);
   }

   if (userObject.getClass().equals(Workflow.class)){
           return new ingenias.editor.cell.WorkflowView(v);
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

   if (userObject.getClass().equals(ApplicationWS.class)){
           return new ingenias.editor.cell.ApplicationWSView(v);
   }

   if (userObject.getClass().equals(Interaction.class)){
           return new ingenias.editor.cell.InteractionView(v);
   }

   if (userObject.getClass().equals(FAERIECtxtAttribute.class)){
           return new ingenias.editor.cell.FAERIECtxtAttributeView(v);
   }

   if (userObject.getClass().equals(FAERIECtxtEntity.class)){
           return new ingenias.editor.cell.FAERIECtxtEntityView(v);
   }

   if (userObject.getClass().equals(FAERIECtxtRelationship.class)){
           return new ingenias.editor.cell.FAERIECtxtRelationshipView(v);
   }

   if (userObject.getClass().equals(RemoteProcedureCall.class)){
           return new ingenias.editor.cell.RemoteProcedureCallView(v);
   }

   if (userObject.getClass().equals(RuntimeEvent.class)){
           return new ingenias.editor.cell.RuntimeEventView(v);
   }

   if (userObject.getClass().equals(IUIterate.class)){
           return new ingenias.editor.cell.IUIterateView(v);
   }

   if (userObject.getClass().equals(MessagePassing.class)){
           return new ingenias.editor.cell.MessagePassingView(v);
   }

   if (userObject.getClass().equals(ShareTouple.class)){
           return new ingenias.editor.cell.ShareToupleView(v);
   }

   if (userObject.getClass().equals(RuntimeConversation.class)){
           return new ingenias.editor.cell.RuntimeConversationView(v);
   }

   if (userObject.getClass().equals(AMIContext.class)){
           return new ingenias.editor.cell.AMIContextView(v);
   }

   if (userObject.getClass().equals(ContextBindingTask.class)){
           return new ingenias.editor.cell.ContextBindingTaskView(v);
   }

   if (userObject.getClass().equals(ContextReleaseTask.class)){
           return new ingenias.editor.cell.ContextReleaseTaskView(v);
   }

   if (userObject.getClass().equals(ContextUseTask.class)){
           return new ingenias.editor.cell.ContextUseTaskView(v);
   }

   if (userObject.getClass().equals(RuntimeFact.class)){
           return new ingenias.editor.cell.RuntimeFactView(v);
   }

   if (userObject.getClass().equals(IUConcurrence.class)){
           return new ingenias.editor.cell.IUConcurrenceView(v);
   }

   if (userObject.getClass().equals(InteractionUnit.class)){
           return new ingenias.editor.cell.InteractionUnitView(v);
   }


   // Diagram Relationships start here

   if (v.getClass().equals(GTPursuesEdge.class)){
           return new ingenias.editor.cell.GTPursuesView(v);
   }

   if (v.getClass().equals(GTCreatesEdge.class)){
           return new ingenias.editor.cell.GTCreatesView(v);
   }

   if (v.getClass().equals(GTAffectsEdge.class)){
           return new ingenias.editor.cell.GTAffectsView(v);
   }

   if (v.getClass().equals(GTDestroysEdge.class)){
           return new ingenias.editor.cell.GTDestroysView(v);
   }

   if (v.getClass().equals(GTModifiesEdge.class)){
           return new ingenias.editor.cell.GTModifiesView(v);
   }

   if (v.getClass().equals(GTFailsEdge.class)){
           return new ingenias.editor.cell.GTFailsView(v);
   }

   if (v.getClass().equals(GTSatisfiesEdge.class)){
           return new ingenias.editor.cell.GTSatisfiesView(v);
   }

   if (v.getClass().equals(WFResponsableEdge.class)){
           return new ingenias.editor.cell.WFResponsableView(v);
   }

   if (v.getClass().equals(WFConsumesEdge.class)){
           return new ingenias.editor.cell.WFConsumesView(v);
   }

   if (v.getClass().equals(WFPlaysEdge.class)){
           return new ingenias.editor.cell.WFPlaysView(v);
   }

   if (v.getClass().equals(ConsumesEdge.class)){
           return new ingenias.editor.cell.ConsumesView(v);
   }

   if (v.getClass().equals(WFUsesEdge.class)){
           return new ingenias.editor.cell.WFUsesView(v);
   }

   if (v.getClass().equals(WFUsesMethodEdge.class)){
           return new ingenias.editor.cell.WFUsesMethodView(v);
   }

   if (v.getClass().equals(PConnectsEdge.class)){
           return new ingenias.editor.cell.PConnectsView(v);
   }

   if (v.getClass().equals(WFProducesEdge.class)){
           return new ingenias.editor.cell.WFProducesView(v);
   }

   if (v.getClass().equals(GTDecomposesEdge.class)){
           return new ingenias.editor.cell.GTDecomposesView(v);
   }

   if (v.getClass().equals(GTDecomposesANDEdge.class)){
           return new ingenias.editor.cell.GTDecomposesANDView(v);
   }

   if (v.getClass().equals(GTDecomposesOREdge.class)){
           return new ingenias.editor.cell.GTDecomposesORView(v);
   }

   if (v.getClass().equals(GTDependsEdge.class)){
           return new ingenias.editor.cell.GTDependsView(v);
   }

   if (v.getClass().equals(GTInheritsEdge.class)){
           return new ingenias.editor.cell.GTInheritsView(v);
   }

   if (v.getClass().equals(GTAndDependsEdge.class)){
           return new ingenias.editor.cell.GTAndDependsView(v);
   }

   if (v.getClass().equals(GTOrDependsEdge.class)){
           return new ingenias.editor.cell.GTOrDependsView(v);
   }

   if (v.getClass().equals(WFDecomposesEdge.class)){
           return new ingenias.editor.cell.WFDecomposesView(v);
   }

   if (v.getClass().equals(ContributeEdge.class)){
           return new ingenias.editor.cell.ContributeView(v);
   }

   if (v.getClass().equals(ContributePositivelyEdge.class)){
           return new ingenias.editor.cell.ContributePositivelyView(v);
   }

   if (v.getClass().equals(ContributeNegativelyEdge.class)){
           return new ingenias.editor.cell.ContributeNegativelyView(v);
   }

   if (v.getClass().equals(UMLAnnotatedElementEdge.class)){
           return new ingenias.editor.cell.UMLAnnotatedElementView(v);
   }

   if (v.getClass().equals(WFParticipatesEdge.class)){
           return new ingenias.editor.cell.WFParticipatesView(v);
   }

   if (v.getClass().equals(WFCancelsEdge.class)){
           return new ingenias.editor.cell.WFCancelsView(v);
   }

   if (v.getClass().equals(IAccessesEdge.class)){
           return new ingenias.editor.cell.IAccessesView(v);
   }

   if (v.getClass().equals(TriggersFailureEdge.class)){
           return new ingenias.editor.cell.TriggersFailureView(v);
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
