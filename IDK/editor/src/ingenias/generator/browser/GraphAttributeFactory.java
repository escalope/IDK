package ingenias.generator.browser;
/*
 Copyright (C) 2005 Jorge Gomez Sanz
 
 This file is part of INGENIAS Development Kit (IDK), a support tool for the INGENIAS
 methodology, availabe at http://grasia.fdi.ucm.es/ingenias or
 http://ingenias.sourceforge.net
 
 INGENIAS IDE is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.
 
 INGENIAS IDE is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public License
 along with IDK; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 
 */
import ingenias.editor.Editor;
import ingenias.editor.IDE;
import ingenias.editor.IDEState;
import ingenias.editor.Log;
import ingenias.editor.Model;
import ingenias.editor.ModelJGraph;
import ingenias.editor.ObjectManager;
import ingenias.editor.TypedVector;
import ingenias.editor.cell.NAryEdge;
import ingenias.editor.cell.RenderComponentManager;
import ingenias.editor.entities.Entity;
import ingenias.editor.entities.NAryEdgeEntity;
import ingenias.editor.entities.RoleEntity;
import ingenias.exception.InvalidAttribute;
import ingenias.exception.InvalidColection;
import ingenias.exception.InvalidEntity;
import ingenias.exception.InvalidGraph;
import ingenias.exception.NotInitialised;
import ingenias.exception.NullEntity;
import ingenias.exception.WrongParameters;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.jgraph.JGraph;
import org.jgraph.graph.BasicMarqueeHandler;
import org.jgraph.graph.ConnectionSet;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphModel;
import org.jgraph.graph.Port;

public class GraphAttributeFactory {
	private IDEState ids;
	
	public static GraphAttributeFactory createDefaultEmptyGraphFactory(){
		return new GraphAttributeFactory(IDEState.emptyIDEState());
	}
	
	public static GraphAttributeFactory createDefaultGraphFactory() throws NotInitialised{
		return new GraphAttributeFactory(BrowserImp.getInstance().getState());
	}
	
	public GraphAttributeFactory(IDEState ids){
		this.ids=ids;	
	}
	

	
	public  GraphAttribute createAttribute(String name, Object value, Graph graph ){
		GraphAttribute ga=new GraphAttributeImp(name,value,((GraphImp)graph).getGraph());
		return ga;  
	}
	
	public  GraphCollection createCollection(Vector<GraphEntity> elements, Graph g) throws InvalidColection{
		if (elements.size()>0){		  
			TypedVector tv=new TypedVector(((GraphEntityImp)elements.firstElement()).getEntity().getClass());
			for (int k=0;k<elements.size();k++){
				tv.add(((GraphEntityImp)elements.elementAt(k)).getEntity());
			}
			return new GraphCollectionImp(tv,((GraphImp)g).getGraph());
		}
		throw new InvalidColection("Collection used is empty");
		
	}
	
	public static void setAttribute(GraphEntity ge, GraphAttribute ga) throws InvalidAttribute{
		((GraphEntityImp)ge).setAttribute(ga);
	}
	
	
	
	
}
