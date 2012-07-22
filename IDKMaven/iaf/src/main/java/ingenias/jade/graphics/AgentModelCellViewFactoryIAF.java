/*
    Copyright (C) 2007 Jorge Gomez Sanz

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
package ingenias.jade.graphics;

import ingenias.editor.cell.RuntimeConversationView;
import ingenias.editor.cell.RuntimeEventView;
import ingenias.editor.cell.RuntimeFactView;
import ingenias.editor.cellfactories.AgentModelCellViewFactory;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.editor.entities.RuntimeEvent;
import ingenias.editor.entities.RuntimeFact;

import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.PortView;
import org.jgraph.graph.VertexView;

public class AgentModelCellViewFactoryIAF extends  AgentModelCellViewFactory {

	public AgentModelCellViewFactoryIAF() {}


	public CellView createView(GraphModel model, Object cell) {

		CellView view = null;
		if (model.isPort(cell))
			view = new PortView(cell);
		else if (model.isEdge(cell))
			view = createEdgeView(cell);
		else
			view = createVertexView(cell);

		if (view==null)
			view=super.createView(model, cell);


		return view;
	}


	protected VertexView createVertexView(Object v) {
		Object userObject = ( (DefaultGraphCell) v).getUserObject();

		// Diagram Objects start here


		if (RuntimeFact.class.isAssignableFrom(userObject.getClass())){
			return new RuntimeFactView(v);
		} else
			if (RuntimeEvent.class.isAssignableFrom(userObject.getClass()))
				return new RuntimeEventView(v);
			else
				if (RuntimeConversation.class.equals(userObject.getClass()))
					return new RuntimeConversationView(v);
				return super.createVertexView(v);

	}

	protected EdgeView createEdgeView(Object v) {
		return new EdgeView(v);

//		if (v instanceof ExecuteEdge){
//		return new ExecuteView(v,this,cm);
//		}

	}




}
