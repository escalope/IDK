

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

public class InteractionModelCellViewFactory implements CellViewFactory {

public InteractionModelCellViewFactory() {}
 

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

   if (userObject.getClass().equals(AgentWS.class)){
           return new ingenias.editor.cell.AgentWSView(v);
   }

   if (userObject.getClass().equals(Role.class)){
           return new ingenias.editor.cell.RoleView(v);
   }

   if (userObject.getClass().equals(RoleWS.class)){
           return new ingenias.editor.cell.RoleWSView(v);
   }

   if (userObject.getClass().equals(Task.class)){
           return new ingenias.editor.cell.TaskView(v);
   }

   if (userObject.getClass().equals(TaskWS.class)){
           return new ingenias.editor.cell.TaskWSView(v);
   }

   if (userObject.getClass().equals(Goal.class)){
           return new ingenias.editor.cell.GoalView(v);
   }

   if (userObject.getClass().equals(Interaction.class)){
           return new ingenias.editor.cell.InteractionView(v);
   }

   if (userObject.getClass().equals(IUIterate.class)){
           return new ingenias.editor.cell.IUIterateView(v);
   }

   if (userObject.getClass().equals(IUConcurrence.class)){
           return new ingenias.editor.cell.IUConcurrenceView(v);
   }

   if (userObject.getClass().equals(InteractionUnit.class)){
           return new ingenias.editor.cell.InteractionUnitView(v);
   }

   if (userObject.getClass().equals(UMLSpecification.class)){
           return new ingenias.editor.cell.UMLSpecificationView(v);
   }

   if (userObject.getClass().equals(GRASIASpecification.class)){
           return new ingenias.editor.cell.GRASIASpecificationView(v);
   }

   if (userObject.getClass().equals(TextNote.class)){
           return new ingenias.editor.cell.TextNoteView(v);
   }

   if (userObject.getClass().equals(UMLComment.class)){
           return new ingenias.editor.cell.UMLCommentView(v);
   }

   if (userObject.getClass().equals(RuntimeCommFailure.class)){
           return new ingenias.editor.cell.RuntimeCommFailureView(v);
   }

   if (userObject.getClass().equals(Conversation.class)){
           return new ingenias.editor.cell.ConversationView(v);
   }

   if (userObject.getClass().equals(Fact.class)){
           return new ingenias.editor.cell.FactView(v);
   }

   if (userObject.getClass().equals(RuntimeConversation.class)){
           return new ingenias.editor.cell.RuntimeConversationView(v);
   }

   if (userObject.getClass().equals(CommunicationEvent.class)){
           return new ingenias.editor.cell.CommunicationEventView(v);
   }

   if (userObject.getClass().equals(ApplicationEvent.class)){
           return new ingenias.editor.cell.ApplicationEventView(v);
   }

   if (userObject.getClass().equals(RemoteProcedureCall.class)){
           return new ingenias.editor.cell.RemoteProcedureCallView(v);
   }

   if (userObject.getClass().equals(GoalStateWS.class)){
           return new ingenias.editor.cell.GoalStateWSView(v);
   }

   if (userObject.getClass().equals(GeneralEvent.class)){
           return new ingenias.editor.cell.GeneralEventView(v);
   }

   if (userObject.getClass().equals(FrameFact.class)){
           return new ingenias.editor.cell.FrameFactView(v);
   }

   if (userObject.getClass().equals(RuntimeEvent.class)){
           return new ingenias.editor.cell.RuntimeEventView(v);
   }

   if (userObject.getClass().equals(StateGoal.class)){
           return new ingenias.editor.cell.StateGoalView(v);
   }

   if (userObject.getClass().equals(RuntimeFact.class)){
           return new ingenias.editor.cell.RuntimeFactView(v);
   }

   if (userObject.getClass().equals(MessagePassing.class)){
           return new ingenias.editor.cell.MessagePassingView(v);
   }

   if (userObject.getClass().equals(ShareTouple.class)){
           return new ingenias.editor.cell.ShareToupleView(v);
   }

   if (userObject.getClass().equals(Believe.class)){
           return new ingenias.editor.cell.BelieveView(v);
   }

   if (userObject.getClass().equals(AgentModelBelieve.class)){
           return new ingenias.editor.cell.AgentModelBelieveView(v);
   }

   if (userObject.getClass().equals(ApplicationEventSlots.class)){
           return new ingenias.editor.cell.ApplicationEventSlotsView(v);
   }

   if (userObject.getClass().equals(Compromise.class)){
           return new ingenias.editor.cell.CompromiseView(v);
   }


   // Diagram Relationships start here

   if (v.getClass().equals(IInitiatesEdge.class)){
           return new ingenias.editor.cell.IInitiatesView(v);
   }

   if (v.getClass().equals(IColaboratesEdge.class)){
           return new ingenias.editor.cell.IColaboratesView(v);
   }

   if (v.getClass().equals(UIInitiatesEdge.class)){
           return new ingenias.editor.cell.UIInitiatesView(v);
   }

   if (v.getClass().equals(UIColaboratesEdge.class)){
           return new ingenias.editor.cell.UIColaboratesView(v);
   }

   if (v.getClass().equals(UISelectionEdge.class)){
           return new ingenias.editor.cell.UISelectionView(v);
   }

   if (v.getClass().equals(IPursuesEdge.class)){
           return new ingenias.editor.cell.IPursuesView(v);
   }

   if (v.getClass().equals(GTPursuesEdge.class)){
           return new ingenias.editor.cell.GTPursuesView(v);
   }

   if (v.getClass().equals(IHasSpecEdge.class)){
           return new ingenias.editor.cell.IHasSpecView(v);
   }

   if (v.getClass().equals(UIPrecedesEdge.class)){
           return new ingenias.editor.cell.UIPrecedesView(v);
   }

   if (v.getClass().equals(UMLSendsMessageEdge.class)){
           return new ingenias.editor.cell.UMLSendsMessageView(v);
   }

   if (v.getClass().equals(UMLAnnotatedElementEdge.class)){
           return new ingenias.editor.cell.UMLAnnotatedElementView(v);
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
