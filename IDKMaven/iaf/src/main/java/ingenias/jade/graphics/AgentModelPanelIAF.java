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

import ingenias.editor.Model;
import ingenias.editor.cell.RuntimeConversationCell;
import ingenias.editor.cell.RuntimeConversationView;
import ingenias.editor.cell.RuntimeEventCell;
import ingenias.editor.cell.RuntimeEventView;
import ingenias.editor.cell.RuntimeFactCell;
import ingenias.editor.cell.RuntimeFactView;
import ingenias.editor.entities.AgentModelDataEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.editor.entities.RuntimeEvent;
import ingenias.editor.entities.RuntimeFact;


import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Hashtable;
import java.util.Map;

import org.jgraph.graph.BasicMarqueeHandler;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;


public class AgentModelPanelIAF extends ingenias.editor.panels.AgentModelPanel {

	public AgentModelPanelIAF(AgentModelDataEntity mde, String nombre, Model m) {
		super(mde, nombre, m, new BasicMarqueeHandler());
		 this.getGraphLayoutCache().setFactory(new AgentModelCellViewFactoryIAF());
	}
	
    @Override
	public DefaultGraphCell insertDuplicated(Point point, ingenias.editor.entities.Entity
            entity) {
		
		 // CellView information is not available after creating the cell.

	    // Create a Map that holds the attributes for the Vertex
	    Map map =new Hashtable();
	    // Snap the Point to the Grid
	      

	    // Construct Vertex with no Label
	    DefaultGraphCell vertex = null;
	    Dimension size = null;

		if (RuntimeFact.class.isAssignableFrom(entity.getClass())) {
		      vertex = new RuntimeFactCell( (RuntimeFact) entity);
		      // Default Size for the new Vertex with the new entity within
		      size = RuntimeFactView.getSize((RuntimeFact) entity);
			  // Add a Bounds Attribute to the Map
		      GraphConstants.setBounds(map, new Rectangle(point, size));
		      GraphConstants.setSize(map, size);
		      // Construct a Map from cells to Maps (for insert)
		      Hashtable attributes = new Hashtable();
		      // Associate the Vertex with its Attributes
		      attributes.put(vertex, map);
		      // Insert the Vertex and its Attributes
		      this.getModel().insert(new Object[] {vertex},attributes
		                             , null, null, null);
		      

		      
		    }
		
		if (RuntimeEvent.class.isAssignableFrom(entity.getClass())) {
		      vertex = new RuntimeEventCell( (RuntimeEvent) entity);
		      // Default Size for the new Vertex with the new entity within
		      
		      size = RuntimeEventView.getSize((RuntimeEvent) entity);		      
			  // Add a Bounds Attribute to the Map
		      GraphConstants.setBounds(map, new Rectangle(point, size));
		      GraphConstants.setSize(map, size);		      
		      GraphConstants.setAutoSize(map, true);		      
		      
		      // Construct a Map from cells to Maps (for insert)
		      Hashtable attributes = new Hashtable();
		      // Associate the Vertex with its Attributes
		      attributes.put(vertex, map);
		      // Insert the Vertex and its Attributes
		      this.getModel().insert(new Object[] {vertex},attributes
		                             , null, null, null);

		      
		    }
		
		if (RuntimeConversation.class.isAssignableFrom(entity.getClass())) {
		      vertex = new RuntimeConversationCell( (RuntimeConversation) entity);
		      // Default Size for the new Vertex with the new entity within
		      size = RuntimeConversationView.getSize((RuntimeConversation) entity);
			  // Add a Bounds Attribute to the Map
		      GraphConstants.setBounds(map, new Rectangle(point, size));
		      GraphConstants.setSize(map, size);

		      // Construct a Map from cells to Maps (for insert)
		      Hashtable attributes = new Hashtable();
		      // Associate the Vertex with its Attributes
		      attributes.put(vertex, map);
		      // Insert the Vertex and its Attributes
		      this.getModel().insert(new Object[] {vertex},attributes
		                             , null, null, null);

		      
		    }
		
		
	    if (vertex==null){	    
	    	vertex=super.insertDuplicated(point,entity);
	    }
	    
	    
	    return vertex;
	}

}
