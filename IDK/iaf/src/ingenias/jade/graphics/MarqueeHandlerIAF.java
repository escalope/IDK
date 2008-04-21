/*
 Copyright (C) 2002 Jorge Gomez Sanz, Ruben Fuentes
 
 Modifications over JGRAPH original code
 
 This file is part of INGENIAS IDE, a support tool for the INGENIAS
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
 along with INGENIAS IDE; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 
 */

package ingenias.jade.graphics;
import ingenias.editor.CellHelpWindow;
import ingenias.editor.IDE;
import ingenias.editor.cell.NAryEdge;
import ingenias.editor.entities.Entity;
import ingenias.editor.entities.RoleEntity;
import ingenias.editor.widget.GraphicsUtils;
import ingenias.exception.NotInitialised;
import ingenias.exception.NullEntity;
import ingenias.generator.browser.BrowserImp;
import ingenias.generator.browser.Graph;
import ingenias.generator.browser.GraphEntity;
import ingenias.generator.browser.GraphRelationship;
import ingenias.generator.browser.GraphRelationshipImp;

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
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.event.UndoableEditEvent;
import javax.swing.tree.TreePath;

import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.jgraph.event.*;
import java.util.Vector;
import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.jgraph.event.*;
import org.jgraph.plaf.basic.*;

//MarqueeHandler that Connects Vertices and Displays PopupMenus


public abstract class MarqueeHandlerIAF extends BasicMarqueeHandler  implements java.io.Serializable {
	// Holds the Start and the Current Point
	/**
	 *  Description of the Field
	 */
	protected Point start, current;
	
	// Holds the First and the Current Port
	/**
	 *  Description of the Field
	 */
	protected PortView port, firstPort;
	
	private JGraph graph;
	
		
	public MarqueeHandlerIAF(JGraph graph){
	
		this.graph=graph;
	}
	
	
	protected Point convert(java.awt.geom.Point2D p2d){
		Point p=new Point((int)p2d.getX(),(int)p2d.getY());
		return p;
	}
	

	

	
	

	protected JGraph getGraph(){
		return graph;
	}
	
	// Override to Gain Control (for PopupMenu and ConnectMode)
	/**
	 *  Gets the forceMarqueeEvent attribute of the MarqueeHandler object
	 *
	 *@param  e  Description of Parameter
	 *@return    The forceMarqueeEvent value
	 */
	public boolean isForceMarqueeEvent(MouseEvent e) {
		// If Right Mouse Button we want to Display the PopupMenu
		if (SwingUtilities.isRightMouseButton(e)) {
			// Return Immediately
			return true;
		}
		// Find and Remember Port
		port = getSourcePortAt(e.getPoint());
		// If Port Found and in ConnectMode (=Ports Visible)
		if (port != null && getGraph().isPortsVisible()) {
			return true;
		}
		// Else Call Superclass
		return super.isForceMarqueeEvent(e);
	}
	
	
	/**
	 *  Gets the sourcePortAt attribute of the MarqueeHandler object
	 *
	 *@param  point  Description of Parameter
	 *@return        The sourcePortAt value
	 */
	public PortView getSourcePortAt(Point point) {
		// Scale from Screen to Model
		Point tmp = convert(getGraph().fromScreen(new Point(point)));
		// Find a Port View in Model Coordinates and Remember
		return getGraph().getPortViewAt(tmp.x, tmp.y);
	}
	
	
	// Display PopupMenu or Remember Start Location and First Port
	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mousePressed(final MouseEvent e) {
		// If Right Mouse Button
		if (SwingUtilities.isRightMouseButton(e)) {
			// Scale From Screen to Model
			Point loc = convert(getGraph().fromScreen(e.getPoint()));
			// Find Cell in Model Coordinates
			Object cell = getGraph().getFirstCellForLocation(loc.x, loc.y);
			
			JPopupMenu menu=new JPopupMenu();
			if (cell!=null)
				System.err.println(cell.getClass().getName());
			if (cell instanceof DefaultGraphCell){
						// Create PopupMenu for the Cell
						menu.add("Entity");
						menu.addSeparator();
						addActionsToPopupMenu(menu,this.createBasicActions((DefaultGraphCell)cell));				
						menu.addSeparator();
						menu.add(createMenu("Views",this.createChangeViewActions((DefaultGraphCell)cell)));
						menu.add(createMenu("Debug",this.createDebugActions((DefaultGraphCell)cell)));
					} else {
						if (getGraph().getSelectionCells()!=null && getGraph().getSelectionCells().length>1){
							GraphCell[] gc=new GraphCell[getGraph().getSelectionCells().length];
							System.arraycopy(getGraph().getSelectionCells(),0,gc,0,gc.length);
							addActionsToPopupMenu(menu,this.createDiagramIndependentActions(e.getPoint(),
									gc));
						}
						addActionsToPopupMenu(menu,this.createDiagramOperations());
						
						addActionsToPopupMenu(menu,this.createDiagramSpecificInsertActions(e.getPoint()));	
					}
				
			
			
			// Display PopupMenu
			menu.show(getGraph(), e.getX(), e.getY());
			
			// Else if in ConnectMode and Remembered Port is Valid
		}
		else if (port != null && !e.isConsumed() && getGraph().isPortsVisible()) {
			// Remember Start Location
			start = convert(getGraph().toScreen(port.getLocation(null)));
			// Remember First Port
			firstPort = port;
			// Consume Event
			e.consume();
		}
		else {
			// Call Superclass
			
		}
		super.mousePressed(e);		
	}
	

	private Vector<AbstractAction> createDiagramOperations() {
		Vector<AbstractAction> actions=new Vector<AbstractAction>();

		return actions;	}


	private Vector<AbstractAction> createDiagramIndependentActions(final Point point, final GraphCell[] selected) {
		Vector<AbstractAction> actions=new Vector<AbstractAction>();

		return actions;
	}


	private void createRelationshipMenu(JPopupMenu menu, NAryEdge nary) {
		String typeOfRelationship=((ingenias.editor.entities.NAryEdgeEntity)nary.getUserObject()).getType();
		Vector<String> roles=new Vector<String>(nary.getRoles());
		addActionsToPopupMenu(menu,this.createBasicActions(nary));				
		menu.addSeparator();
		menu.add(createMenu("views",this.createChangeViewActions(nary)));
		
		for (int k=0;k<roles.size();k++){
			DefaultEdge[] edgesPerRole=nary.getRoleEdges(roles.elementAt(k));					
			if (edgesPerRole.length>1){
				for (int j=0;j<edgesPerRole.length;j++){
					Vector<AbstractAction> edgeMenuActions = createEdgeActions(nary.getRoleEdges(roles.elementAt(k))[j]);
					menu.add(createMenu("role:"+roles.elementAt(k),edgeMenuActions));
				}
			} else {
				if (edgesPerRole.length==1){
					Vector<AbstractAction> edgeMenuActions = createEdgeActions(nary.getRoleEdges(roles.elementAt(k))[0]);
					menu.add(createMenu("role:"+roles.elementAt(k),edgeMenuActions));
				} 
			}
			
			
		}
	}
	
	
	
	
	private JMenu createMenu(String name, Vector<AbstractAction> actions) {
		Iterator<AbstractAction> it=actions.iterator();
		JMenu menu=new JMenu(name);
		while (it.hasNext()){
			menu.add(it.next());
		}
		// TODO Auto-generated method stub
		return menu;
	}
	
	
	private void addActionsToPopupMenu(JPopupMenu menu, Vector<AbstractAction> actions) {
		Iterator<AbstractAction> it=actions.iterator();
		while (it.hasNext()){
			menu.add(it.next());
		}
	}
	
	
	private Vector<AbstractAction> createEdgeActions(final DefaultEdge cell){
		Vector<AbstractAction> actions=new Vector<AbstractAction>();
		final RoleEntity re=(RoleEntity)(cell).getUserObject();
		Field[] fs=re.getClass().getFields();
		if (fs.length>1)
			actions.add(
					new AbstractAction("Edit") {
						public void actionPerformed(ActionEvent e) {
							System.err.println("clase cell"+cell.getClass()+" "+cell.getUserObject());
							getGraph().startEditingAtCell(cell);
							
						}
					});
		if (fs.length>1)
			actions.add(
					new AbstractAction("Hide fields") {
						
						public void actionPerformed(ActionEvent e) {
							re.hide();
						}
					});
		for (int k=0;k<fs.length;k++){
			if (!fs[k].getName().equalsIgnoreCase("id")){
				final int j=k;
				// Edit
				actions.add(
						new AbstractAction("Show field "+fs[k].getName()) {
							
							public void actionPerformed(ActionEvent e) {
								re.show(j);
							}
						});
				
			}}
		return actions;
	}
	
	protected abstract Vector<AbstractAction> createDebugActions(
			final DefaultGraphCell cell);
	
	
	private Vector<AbstractAction> createBasicActions(final DefaultGraphCell cell){
		Vector<AbstractAction> actions=new Vector<AbstractAction>();
//		Help
		
		actions.add(
				new AbstractAction("Help") {
					public void actionPerformed(ActionEvent e) {
						CellHelpWindow chw= new CellHelpWindow();
						DefaultGraphCell dgc=(DefaultGraphCell)cell;
						ingenias.editor.entities.Entity ent=((ingenias.editor.entities.Entity)dgc.getUserObject());
						chw.setDescription(ent.getHelpDesc());
						chw.setRec(ent.getHelpRecom());
						chw.setSize(350,300);
						chw.setLocation(GraphicsUtils.getCenter(IDE.ide,chw.getSize()));
						chw.show();
					}
				});
		
		
		
		// Edit
		actions.add(
				new AbstractAction("Edit") {
					public void actionPerformed(ActionEvent e) {
						getGraph().startEditingAtCell(cell);
						
					}
				});
		
//		Remove
		
		/*actions.add(
				new AbstractAction("Remove") {
					public void actionPerformed(ActionEvent e) {
						getGraph().setSelectionCells(new Object[]{cell});
						editor.getCommonButtons().getRemove().actionPerformed(e);
					}
				});*/
		
		
		
		final ingenias.editor.entities.Entity ent=((ingenias.editor.entities.Entity)cell.getUserObject());
		
				
		return actions;
	}
	
	
	
	
	abstract protected Vector<AbstractAction> createChangeViewActions(final DefaultGraphCell cell);
	abstract protected Vector<AbstractAction> createDiagramSpecificInsertActions(Point pt);
	
	
	
	// Find Port under Mouse and Repaint Connector
	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseDragged(MouseEvent e) {
		// If remembered Start Point is Valid
		
		// Call Superclass
		super.mouseDragged(e);
	}
	
	
	// Connect the First Port and the Current Port in the Graph or Repaint
	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseReleased(MouseEvent e) {
		// If Valid Event, Current and First Port
		super.mouseReleased(e);
	}
	
	
	// Show Special Cursor if Over Port
	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseMoved(MouseEvent e) {
		// Check Mode and Find Port

		// Call Superclass
		super.mouseReleased(e);
	}
	
	
	
	
	
	
}
