

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

public class UseCaseDiagramCellViewFactory implements CellViewFactory {

public UseCaseDiagramCellViewFactory() {}
 

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


   if (userObject.getClass().equals(TextUseCase.class)){
           return new ingenias.editor.cell.TextUseCaseView(v);
   }

   if (userObject.getClass().equals(INGENIASUseCase.class)){
           return new ingenias.editor.cell.INGENIASUseCaseView(v);
   }

   if (userObject.getClass().equals(Agent.class)){
           return new ingenias.editor.cell.AgentView(v);
   }

   if (userObject.getClass().equals(Role.class)){
           return new ingenias.editor.cell.RoleView(v);
   }

   if (userObject.getClass().equals(Interaction.class)){
           return new ingenias.editor.cell.InteractionView(v);
   }

   if (userObject.getClass().equals(TextNote.class)){
           return new ingenias.editor.cell.TextNoteView(v);
   }

   if (userObject.getClass().equals(UMLComment.class)){
           return new ingenias.editor.cell.UMLCommentView(v);
   }

   if (userObject.getClass().equals(Goal.class)){
           return new ingenias.editor.cell.GoalView(v);
   }

   if (userObject.getClass().equals(Organization.class)){
           return new ingenias.editor.cell.OrganizationView(v);
   }


   // Diagram Relationships start here

   if (v.getClass().equals(ExtendsEdge.class)){
           return new ingenias.editor.cell.ExtendsView(v);
   }

   if (v.getClass().equals(IncludesEdge.class)){
           return new ingenias.editor.cell.IncludesView(v);
   }

   if (v.getClass().equals(GeneralizesEdge.class)){
           return new ingenias.editor.cell.GeneralizesView(v);
   }

   if (v.getClass().equals(ParticipatesInUseCaseEdge.class)){
           return new ingenias.editor.cell.ParticipatesInUseCaseView(v);
   }

   if (v.getClass().equals(UMLDescribesUseCaseEdge.class)){
           return new ingenias.editor.cell.UMLDescribesUseCaseView(v);
   }

   if (v.getClass().equals(UMLAssociationEdge.class)){
           return new ingenias.editor.cell.UMLAssociationView(v);
   }

   if (v.getClass().equals(UMLAnnotatedElementEdge.class)){
           return new ingenias.editor.cell.UMLAnnotatedElementView(v);
   }

   if (v.getClass().equals(UseCasePursuesEdge.class)){
           return new ingenias.editor.cell.UseCasePursuesView(v);
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
