

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

public class ActivityDiagramCellViewFactory implements CellViewFactory {

public ActivityDiagramCellViewFactory() {}
 

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


   if (userObject.getClass().equals(InitialNode.class)){
           return new ingenias.editor.cell.InitialNodeView(v);
   }

   if (userObject.getClass().equals(DecisionNode.class)){
           return new ingenias.editor.cell.DecisionNodeView(v);
   }

   if (userObject.getClass().equals(ForkNode.class)){
           return new ingenias.editor.cell.ForkNodeView(v);
   }

   if (userObject.getClass().equals(EndNode.class)){
           return new ingenias.editor.cell.EndNodeView(v);
   }

   if (userObject.getClass().equals(Task.class)){
           return new ingenias.editor.cell.TaskView(v);
   }

   if (userObject.getClass().equals(Role.class)){
           return new ingenias.editor.cell.RoleView(v);
   }

   if (userObject.getClass().equals(WorkflowBox.class)){
           return new ingenias.editor.cell.WorkflowBoxView(v);
   }

   if (userObject.getClass().equals(UMLComment.class)){
           return new ingenias.editor.cell.UMLCommentView(v);
   }

   if (userObject.getClass().equals(TextNote.class)){
           return new ingenias.editor.cell.TextNoteView(v);
   }

   if (userObject.getClass().equals(TaskWS.class)){
           return new ingenias.editor.cell.TaskWSView(v);
   }

   if (userObject.getClass().equals(ContextUseTask.class)){
           return new ingenias.editor.cell.ContextUseTaskView(v);
   }

   if (userObject.getClass().equals(Workflow.class)){
           return new ingenias.editor.cell.WorkflowView(v);
   }

   if (userObject.getClass().equals(BoxedTask.class)){
           return new ingenias.editor.cell.BoxedTaskView(v);
   }

   if (userObject.getClass().equals(RoleWS.class)){
           return new ingenias.editor.cell.RoleWSView(v);
   }

   if (userObject.getClass().equals(ContextBindingTask.class)){
           return new ingenias.editor.cell.ContextBindingTaskView(v);
   }

   if (userObject.getClass().equals(ContextReleaseTask.class)){
           return new ingenias.editor.cell.ContextReleaseTaskView(v);
   }

   if (userObject.getClass().equals(Plan.class)){
           return new ingenias.editor.cell.PlanView(v);
   }


   // Diagram Relationships start here

   if (v.getClass().equals(UMLAnnotatedElementEdge.class)){
           return new ingenias.editor.cell.UMLAnnotatedElementView(v);
   }

   if (v.getClass().equals(WFStartsEdge.class)){
           return new ingenias.editor.cell.WFStartsView(v);
   }

   if (v.getClass().equals(WFEndsEdge.class)){
           return new ingenias.editor.cell.WFEndsView(v);
   }

   if (v.getClass().equals(WFDecidesEdge.class)){
           return new ingenias.editor.cell.WFDecidesView(v);
   }

   if (v.getClass().equals(WFFollowsEdge.class)){
           return new ingenias.editor.cell.WFFollowsView(v);
   }

   if (v.getClass().equals(WFFollowsGuardedEdge.class)){
           return new ingenias.editor.cell.WFFollowsGuardedView(v);
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
