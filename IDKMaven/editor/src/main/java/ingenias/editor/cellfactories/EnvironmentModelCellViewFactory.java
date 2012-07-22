

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

public class EnvironmentModelCellViewFactory implements CellViewFactory {

public EnvironmentModelCellViewFactory() {}
 

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

   if (userObject.getClass().equals(OrganizationGroup.class)){
           return new ingenias.editor.cell.OrganizationGroupView(v);
   }

   if (userObject.getClass().equals(Resource.class)){
           return new ingenias.editor.cell.ResourceView(v);
   }

   if (userObject.getClass().equals(Application.class)){
           return new ingenias.editor.cell.ApplicationView(v);
   }

   if (userObject.getClass().equals(InternalApplication.class)){
           return new ingenias.editor.cell.InternalApplicationView(v);
   }

   if (userObject.getClass().equals(EnvironmentApplication.class)){
           return new ingenias.editor.cell.EnvironmentApplicationView(v);
   }

   if (userObject.getClass().equals(ApplicationWS.class)){
           return new ingenias.editor.cell.ApplicationWSView(v);
   }

   if (userObject.getClass().equals(TextNote.class)){
           return new ingenias.editor.cell.TextNoteView(v);
   }

   if (userObject.getClass().equals(UMLComment.class)){
           return new ingenias.editor.cell.UMLCommentView(v);
   }

   if (userObject.getClass().equals(Organization.class)){
           return new ingenias.editor.cell.OrganizationView(v);
   }

   if (userObject.getClass().equals(AMIContext.class)){
           return new ingenias.editor.cell.AMIContextView(v);
   }

   if (userObject.getClass().equals(FAERIEContext.class)){
           return new ingenias.editor.cell.FAERIEContextView(v);
   }

   if (userObject.getClass().equals(FAERIECtxtModel.class)){
           return new ingenias.editor.cell.FAERIECtxtModelView(v);
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

   if (userObject.getClass().equals(FAERIECtxtValue.class)){
           return new ingenias.editor.cell.FAERIECtxtValueView(v);
   }

   if (userObject.getClass().equals(RoleWS.class)){
           return new ingenias.editor.cell.RoleWSView(v);
   }


   // Diagram Relationships start here

   if (v.getClass().equals(EPerceivesEdge.class)){
           return new ingenias.editor.cell.EPerceivesView(v);
   }

   if (v.getClass().equals(EPerceivesNotificationEdge.class)){
           return new ingenias.editor.cell.EPerceivesNotificationView(v);
   }

   if (v.getClass().equals(EPerceivesPollingEdge.class)){
           return new ingenias.editor.cell.EPerceivesPollingView(v);
   }

   if (v.getClass().equals(EResourceBelongsToEdge.class)){
           return new ingenias.editor.cell.EResourceBelongsToView(v);
   }

   if (v.getClass().equals(ApplicationBelongsToEdge.class)){
           return new ingenias.editor.cell.ApplicationBelongsToView(v);
   }

   if (v.getClass().equals(UMLAnnotatedElementEdge.class)){
           return new ingenias.editor.cell.UMLAnnotatedElementView(v);
   }

   if (v.getClass().equals(OHasGroupEdge.class)){
           return new ingenias.editor.cell.OHasGroupView(v);
   }

   if (v.getClass().equals(ODecomposesGroupEdge.class)){
           return new ingenias.editor.cell.ODecomposesGroupView(v);
   }

   if (v.getClass().equals(CtxtNotifiesEdge.class)){
           return new ingenias.editor.cell.CtxtNotifiesView(v);
   }

   if (v.getClass().equals(CtxtUpdatesEdge.class)){
           return new ingenias.editor.cell.CtxtUpdatesView(v);
   }

   if (v.getClass().equals(FAERIEAppliedToEdge.class)){
           return new ingenias.editor.cell.FAERIEAppliedToView(v);
   }

   if (v.getClass().equals(FAERIEHasValueEdge.class)){
           return new ingenias.editor.cell.FAERIEHasValueView(v);
   }

   if (v.getClass().equals(FAERIEGeneratedByEdge.class)){
           return new ingenias.editor.cell.FAERIEGeneratedByView(v);
   }

   if (v.getClass().equals(FAERIESrcEntityEdge.class)){
           return new ingenias.editor.cell.FAERIESrcEntityView(v);
   }

   if (v.getClass().equals(FAERIETrgtEntityEdge.class)){
           return new ingenias.editor.cell.FAERIETrgtEntityView(v);
   }

   if (v.getClass().equals(HasCtxModelEdge.class)){
           return new ingenias.editor.cell.HasCtxModelView(v);
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
