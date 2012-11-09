

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

public class AgentModelCellViewFactory implements CellViewFactory {

public AgentModelCellViewFactory() {}
 

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

   if (userObject.getClass().equals(Role.class)){
           return new ingenias.editor.cell.RoleView(v);
   }

   if (userObject.getClass().equals(Goal.class)){
           return new ingenias.editor.cell.GoalView(v);
   }

   if (userObject.getClass().equals(Plan.class)){
           return new ingenias.editor.cell.PlanView(v);
   }

   if (userObject.getClass().equals(StateGoal.class)){
           return new ingenias.editor.cell.StateGoalView(v);
   }

   if (userObject.getClass().equals(Fact.class)){
           return new ingenias.editor.cell.FactView(v);
   }

   if (userObject.getClass().equals(Task.class)){
           return new ingenias.editor.cell.TaskView(v);
   }

   if (userObject.getClass().equals(FrameFact.class)){
           return new ingenias.editor.cell.FrameFactView(v);
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

   if (userObject.getClass().equals(ApplicationEvent.class)){
           return new ingenias.editor.cell.ApplicationEventView(v);
   }

   if (userObject.getClass().equals(ApplicationEventSlots.class)){
           return new ingenias.editor.cell.ApplicationEventSlotsView(v);
   }

   if (userObject.getClass().equals(MentalStateProcessor.class)){
           return new ingenias.editor.cell.MentalStateProcessorView(v);
   }

   if (userObject.getClass().equals(MentalStateManager.class)){
           return new ingenias.editor.cell.MentalStateManagerView(v);
   }

   if (userObject.getClass().equals(MentalState.class)){
           return new ingenias.editor.cell.MentalStateView(v);
   }

   if (userObject.getClass().equals(ConditionalMentalState.class)){
           return new ingenias.editor.cell.ConditionalMentalStateView(v);
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

   if (userObject.getClass().equals(AgentModelBelieve.class)){
           return new ingenias.editor.cell.AgentModelBelieveView(v);
   }

   if (userObject.getClass().equals(Conversation.class)){
           return new ingenias.editor.cell.ConversationView(v);
   }

   if (userObject.getClass().equals(UMLComment.class)){
           return new ingenias.editor.cell.UMLCommentView(v);
   }

   if (userObject.getClass().equals(TextNote.class)){
           return new ingenias.editor.cell.TextNoteView(v);
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

   if (userObject.getClass().equals(MentalInstanceSpecification.class)){
           return new ingenias.editor.cell.MentalInstanceSpecificationView(v);
   }

   if (userObject.getClass().equals(ApplicationWS.class)){
           return new ingenias.editor.cell.ApplicationWSView(v);
   }

   if (userObject.getClass().equals(RuntimeCommFailure.class)){
           return new ingenias.editor.cell.RuntimeCommFailureView(v);
   }

   if (userObject.getClass().equals(FAERIECtxtRelationship.class)){
           return new ingenias.editor.cell.FAERIECtxtRelationshipView(v);
   }

   if (userObject.getClass().equals(RuntimeEvent.class)){
           return new ingenias.editor.cell.RuntimeEventView(v);
   }

   if (userObject.getClass().equals(FAERIECtxtEntity.class)){
           return new ingenias.editor.cell.FAERIECtxtEntityView(v);
   }

   if (userObject.getClass().equals(FAERIECtxtAttribute.class)){
           return new ingenias.editor.cell.FAERIECtxtAttributeView(v);
   }

   if (userObject.getClass().equals(Interaction.class)){
           return new ingenias.editor.cell.InteractionView(v);
   }

   if (userObject.getClass().equals(BoxedTask.class)){
           return new ingenias.editor.cell.BoxedTaskView(v);
   }

   if (userObject.getClass().equals(RuntimeConversation.class)){
           return new ingenias.editor.cell.RuntimeConversationView(v);
   }

   if (userObject.getClass().equals(CommunicationEvent.class)){
           return new ingenias.editor.cell.CommunicationEventView(v);
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


   // Diagram Relationships start here

   if (v.getClass().equals(GTPursuesEdge.class)){
           return new ingenias.editor.cell.GTPursuesView(v);
   }

   if (v.getClass().equals(AInheritsEdge.class)){
           return new ingenias.editor.cell.AInheritsView(v);
   }

   if (v.getClass().equals(AHasMSEdge.class)){
           return new ingenias.editor.cell.AHasMSView(v);
   }

   if (v.getClass().equals(AHasMSManagerEdge.class)){
           return new ingenias.editor.cell.AHasMSManagerView(v);
   }

   if (v.getClass().equals(AHasMSProcessorEdge.class)){
           return new ingenias.editor.cell.AHasMSProcessorView(v);
   }

   if (v.getClass().equals(WFResponsableEdge.class)){
           return new ingenias.editor.cell.WFResponsableView(v);
   }

   if (v.getClass().equals(AInstanceOfEdge.class)){
           return new ingenias.editor.cell.AInstanceOfView(v);
   }

   if (v.getClass().equals(AContainsMEEdge.class)){
           return new ingenias.editor.cell.AContainsMEView(v);
   }

   if (v.getClass().equals(WFPlaysEdge.class)){
           return new ingenias.editor.cell.WFPlaysView(v);
   }

   if (v.getClass().equals(ARoleInheritanceEdge.class)){
           return new ingenias.editor.cell.ARoleInheritanceView(v);
   }

   if (v.getClass().equals(UMLAnnotatedElementEdge.class)){
           return new ingenias.editor.cell.UMLAnnotatedElementView(v);
   }

   if (v.getClass().equals(WFUsesEdge.class)){
           return new ingenias.editor.cell.WFUsesView(v);
   }

   if (v.getClass().equals(EResourceBelongsToEdge.class)){
           return new ingenias.editor.cell.EResourceBelongsToView(v);
   }

   if (v.getClass().equals(ApplicationBelongsToEdge.class)){
           return new ingenias.editor.cell.ApplicationBelongsToView(v);
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
