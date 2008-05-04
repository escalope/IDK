/*
 Copyright (C) 2002 Jorge Gomez Sanz, Ruben Fuentes
 Modifications over original code from jgraph.sourceforge.net
 This file is part of INGENIAS IDK, a support tool for the INGENIAS
 methodology, availabe at http://grasia.fdi.ucm.es/ingenias or
 http://ingenias.sourceforge.net
 INGENIAS IDK is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.
 INGENIAS IDK is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 You should have received a copy of the GNU General Public License
 along with INGENIAS IDK; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package ingenias.editor;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

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

import com.languageExplorer.widgets.ScrollableBar;

import ingenias.editor.entities.*;
import ingenias.editor.cell.*;
//import ingenias.editor.auml.*;
import ingenias.exception.*;
import ingenias.generator.browser.BrowserImp;

import java.io.*;
import java.awt.geom.*;

public class Editor
extends JPanel
implements GraphSelectionListener, java.io.Serializable, KeyListener {

	// JGraph instance
	/**
	 *  Description of the Field
	 */
	protected ModelJGraph graph;

	protected JTabbedPane graphPanel;

	// Undo Manager
	/**
	 *  Description of the Field
	 */
	protected GraphUndoManager undoManager;

	private JPanel gpan, jp;

	protected ObjectManager om = null;

//	public static final ingenias.editor.events.ChangeNARYEdgeLocation relationshipLocationListener=new ingenias.editor.events.ChangeNARYEdgeLocation();

	protected JComponent modelToolBar = null;
	protected ButtonToolBar commonButtons = null;

	public static int idCounter = 0;

	// To make "cut" action not to delete until pasted
	public static final int COPIED = 0;
	public static final int PASTED = 1;
	public static final int NONE = 2;
	public static final int CUT = 3;

	private static int state = NONE;

	private AUMLInsertOperations auml=new AUMLInsertOperations();

	public static String getNewId() {
		idCounter=0;

		Vector<NAryEdgeEntity> rels;
		try {
			rels = RelationshipManager.getRelationshipsVector(BrowserImp.getInstance().getState().gm);
			HashSet<String> trels=new HashSet<String> ();
			for (NAryEdgeEntity nedge:rels){
				trels.add(nedge.getId());						
			}
			
			
			while (trels.contains(""+idCounter) || 
					BrowserImp.getInstance().getState().om.findUserObject(""+idCounter).size()>0 ||
					BrowserImp.getInstance().getState().gm.getModel(""+idCounter)!=null){
				idCounter++;
			}
		} catch (NotInitialised e) {
			
			e.printStackTrace();
		}
		

			return ""+idCounter;
	}
	
	public static String getNewId(String fromID) {
		idCounter=0;

		Vector<NAryEdgeEntity> rels = RelationshipManager.getRelationshipsVector(IDE.ide.ids.gm);
		Hashtable<String,NAryEdgeEntity> trels=new Hashtable<String,NAryEdgeEntity>();
		for (NAryEdgeEntity nedge:rels){
			if (nedge.getId().equals(fromID+idCounter))
				idCounter++;
		}

		while (IDE.ide.ids.om.findUserObject(fromID+idCounter).size()>0){
			idCounter++;
		}

		while (IDE.ide.ids.gm.getModel(fromID+idCounter)!=null){
			idCounter++;
		}

		return fromID+idCounter;
	}

	//
	// Editor Panel
	//

	// Construct an Editor Panel
	/**
	 *  Constructor for the Editor object
	 */
	public Editor(ObjectManager om) {
		this.om = om;
		// Use Border Layout
		setLayout(new BorderLayout());
		// Construct the Graph
//		graph = new JGraph(new Model(), new MarqueeHandler(this));

		// Construct Command History
		//
		// Create a GraphUndoManager which also Updates the ToolBar
		undoManager =
			new GraphUndoManager() {
			// Override Superclass
			/**
			 *  Description of the Method
			 *
			 *@param  e  Description of Parameter
			 */
			public void undoableEditHappened(UndoableEditEvent e) {
				// First Invoke Superclass
				super.undoableEditHappened(e);
				// Then Update Undo/Redo Buttons
				updateHistoryButtons();
			}
		};


		// Construct Panel
		//
		// Add a ToolBar
		jp = new JPanel();
		jp.setLayout(new GridLayout(1, 1));
		commonButtons = createToolBar();
		jp.add(commonButtons);
		add(jp, BorderLayout.NORTH);
		// Add the Graph as Center Component
		graphPanel = new JTabbedPaneWithCloseIcons();
		graphPanel.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		graphPanel.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent ce) {

				// Modifies the button bars when a new panel is selected
				if ( (JScrollPane) graphPanel.getSelectedComponent() != null &&
						( (JScrollPane) graphPanel.getSelectedComponent()).getViewport() != null &&
						( (JScrollPane) graphPanel.getSelectedComponent()).getViewport().
						getComponentCount() > 0) {
					ModelJGraph mjg = (ModelJGraph)
					( ( (JScrollPane) graphPanel.getSelectedComponent()).getViewport().
							getComponent(0));
					if (graphPanel.getTabCount() > 0) {
						graph = mjg;
						updateBars(mjg);
					}
				}
			}
		});
		KeyStroke copyshortcut=KeyStroke.getKeyStroke("control C");
		this.registerKeyboardAction(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				commonButtons.copy.actionPerformed(e);

			}
		},copyshortcut,JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		KeyStroke pasteshortcut=KeyStroke.getKeyStroke("control V");
		this.registerKeyboardAction(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				commonButtons.paste.actionPerformed(e);

			}
		},pasteshortcut,JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		add(graphPanel, BorderLayout.CENTER);


	}

	public void closeTab(String name) {
		int size = graphPanel.getTabCount();
		int k = 0;
		boolean found = false;
		String title = "";
		while (k < size && !title.equals(name)) {
			title = graphPanel.getTitleAt(k);
			if (!title.equals(name)) {
				k++;
			}
		}
		if (k < size) {
			graphPanel.remove(k);
		}
	}

	public JGraph getGraph() {
		return this.graph;
	}

	// This method can be invoked by pressing the project tree and the state
	// change listener (when the tab changes)
	public synchronized void changeGraph(ModelJGraph graph1) {
		this.graph = graph1;

		if (this.graphPanel.indexOfTab(graph.getID()) < 0) {
			this.graphPanel.addTab(graph.getID(), new JScrollPane(graph));
		}

		this.graphPanel.setSelectedIndex(this.graphPanel.indexOfTab(graph.getID()));
		updateBars(graph);
	}

	public synchronized Vector<String> getOpenedDiagrams(){
		Vector<String> result=new Vector<String>();
		for (int k=0;k<this.graphPanel.getTabCount();k++){
			result.add(this.graphPanel.getTitleAt(k));	
		}
		return result;
	}

	private void updateBars(ModelJGraph graph) {

		if (this.modelToolBar != null) {
			IDEGUI.buttonModelPanel.remove(modelToolBar);
		}

		if (this.commonButtons != null) {
			jp.remove(commonButtons);

		}
		ButtonToolBar oldButtons = commonButtons;

		modelToolBar = new ScrollableBar(creaPaleta(), ScrollableBar.VERTICAL);

		commonButtons = this.createToolBar();

		commonButtons.getJc().setSelectedIndex(oldButtons.getJc().getSelectedIndex());

		jp.add(commonButtons);
		IDEGUI.buttonModelPanel.add(modelToolBar, BorderLayout.CENTER);

		// Add Listeners to Graph
		//
		// Register UndoManager with the Model
		GraphModel gm = graph.getModel();
		gm.addUndoableEditListener(undoManager);
		// Update ToolBar based on Selection Changes
		graph.getSelectionModel().addGraphSelectionListener(this);

		// Listen for Delete Keystroke when the Graph has Focus
		graph.addKeyListener(this);

		// Construct Panel
		//
		// Add a ToolBar
//		gpan.setLayout(new GridLayout(1,1));
//		gpan.add(graph);
		jp.validate();
		if (this.getTopLevelAncestor()!=null){
			this.getTopLevelAncestor().repaint();
			this.getTopLevelAncestor().validate();
		}
		GraphLayoutCacheListener obs = new ingenias.editor.events.GraphViewChange( (Model) graph.
				getModel());
//		gpan.setLayout(new GridLayout(1,1));
//		gpan.add(graph);
		if (graph != null) {
			graph.getGraphLayoutCache().removeGraphLayoutCacheListener(obs);
			graph.getGraphLayoutCache().addGraphLayoutCacheListener(obs);
		}
		this.validate();
		repaint();
		System.gc();
	}

	// Determines if a Cell is a Group
	/**
	 *  Gets the group attribute of the Editor object
	 *
	 *@param  cell  Description of Parameter
	 *@return       The group value
	 */
	public boolean isGroup(Object cell) {
		// Map the Cell to its View
		CellView view = graph.getGraphLayoutCache().getMapping(cell, false);
		if (view != null) {
			return!view.isLeaf();
		}
		return false;
	}

	// Insert a new Vertex at point
	/**
	 *  Description of the Method
	 *
	 *@param  point   Description of Parameter
	 *@param  entity  Description of Parameter
	 * @throws InvalidEntity 
	 */
	public void insert(Point point, String entity) throws InvalidEntity {
		DefaultGraphCell newCell;
		if (graph instanceof AUMLInteractionDiagramModelJGraph){
			this.auml.insert(point, entity, graph);
		} else {
			newCell=graph.insert(point, entity);
			Entity newEntity=(Entity) newCell.getUserObject();
			if (IDE.ide!=null && IDE.ide.prefs.getModelingLanguage()==Preferences.ModelingLanguage.UML)
				newEntity.getPrefs().setView(ViewPreferences.ViewType.UML);
			if (IDE.ide!=null && IDE.ide.prefs.getModelingLanguage()==Preferences.ModelingLanguage.INGENIAS)
				newEntity.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
			System.err.println("----------------insertando ----------------");
		}

		IDE.setChanged();

	}

	public DefaultGraphCell insertDuplicated(Point point, ingenias.editor.entities.Entity
			entity) {

		IDE.setChanged();
		return graph.insertDuplicated(point, entity);

	}

	// Associate the NAryEdge Vertex with its Attributes.
	private Hashtable nEdgeAttributes(NAryEdge nEdge, Point pt) {
		// Create a Map that holds the attributes for the NAryEdge Vertex.
		Map map = new Hashtable();
		// Snap the Point to the Grid.
		Point2D point = graph.snap(pt);
//		GraphConstants.setFontSize(map, 12f);
//		GraphConstants.setFontName(map, "monospaced");
		// Default Size for the new Vertex.
		/*    Font f = GraphConstants.getFont(map);

		 Dimension size = new Dimension(
		 this.getFontMetrics(f).stringWidth(nEdge.getUserObject().toString())
		 , 20);

		 // Add a Bounds Attribute to the Map.*/
		GraphConstants.setMoveable(map, true);
		GraphConstants.setSizeable(map,true);
		// Construct a Map from cells to Maps (for insert).
		Hashtable attributes = new Hashtable();
		// Associate the NAryEdge Vertex with its Attributes.
		attributes.put(nEdge, map);
		return attributes;
	}




	// Create a Group that Contains the Cells
	/**
	 *  Description of the Method
	 *
	 *@param  cells  Description of Parameter
	 */
	/*public void group(Object[] cells) {
	 // Order Cells by View Layering
	  cells = graph.getGraphLayoutCache().order(cells);
	  // If Any Cells in View
	   if (cells != null && cells.length > 0) {
	   // Create Group Cell
	    int count = getCellCount(graph);
	    DefaultGraphCell group = new DefaultGraphCell(new Integer(count
	    - 1));
	    // Create Change Information
	     java.util.HashMap map = new HashMap();
	     // Insert Child Parent Entries
	      for (int i = 0; i > cells.length; i++) {
	      map.put(cells[i], group);
	      }
	      // Insert into model
	       graph.getModel().insert(new Object[] {group}
	       , map, null,null,null);
	       }
	       }*/

	// Ungroup the Groups in Cells and Select the Children
	/**
	 *  Description of the Method
	 *
	 *@param  cells  Description of Parameter
	 */
	public void ungroup(Object[] cells) {
		// If any Cells
		if (cells != null && cells.length > 0) {
			// List that Holds the Groups
			ArrayList groups = new ArrayList();
			// List that Holds the Children
			ArrayList children = new ArrayList();
			// Loop Cells
			for (int i = 0; i < cells.length; i++) {
				// If Cell is a Group
				if (isGroup(cells[i])) {
					// Add to List of Groups
					groups.add(cells[i]);
					// Loop Children of Cell
					for (int j = 0; j < graph.getModel().getChildCount(cells[i]); j++) {
						// Get Child from Model
						Object child = graph.getModel().getChild(cells[i], j);
						// If Not Port
						if (! (child instanceof Port)) {
							// Add to Children List
							children.add(child);
						}
					}
				}
			}
			// Remove Groups from Model (Without Children)
			//graph.getModel().remove(groups.toArray());
			// Select Children
			//graph.setSelectionCells(children.toArray());
		}
	}

	// Brings the Specified Cells to Front
	/**
	 *  Description of the Method
	 *
	 *@param  c  Description of Parameter
	 */
	public void toFront(Object[] c) {
		if (c != null && c.length > 0) {
			graph.getGraphLayoutCache().toFront(graph.getGraphLayoutCache().getMapping(c));
		}
	}

	// Sends the Specified Cells to Back
	/**
	 *  Description of the Method
	 *
	 *@param  c  Description of Parameter
	 */
	public void toBack(Object[] c) {
		if (c != null && c.length > 0) {
			graph.getGraphLayoutCache().toBack(graph.getGraphLayoutCache().getMapping(c));
		}
	}

	// Undo the last Change to the Model or the View
	/**
	 *  Description of the Method
	 */
	public void undo() {
		try {
			undoManager.undo(graph.getGraphLayoutCache());
		}
		catch (Exception ex) {
			System.err.println(ex);
		}
		finally {
			updateHistoryButtons();
		}
	}

	// Redo the last Change to the Model or the View
	/**
	 *  Description of the Method
	 */
	public void redo() {
		try {
			undoManager.redo(graph.getGraphLayoutCache());
		}
		catch (Exception ex) {
			System.err.println(ex);
		}
		finally {
			updateHistoryButtons();
		}
	}

	//
	// Listeners
	//

	// From GraphSelectionListener Interface
	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void valueChanged(GraphSelectionEvent e) {
		// Group Button only Enabled if more than One Cell Selected
//		group.setEnabled(graph.getSelectionCount() > 1);
		// Update Button States based on Current Selection
		boolean enabled = !graph.isSelectionEmpty();
		this.commonButtons.getRemove().setEnabled(enabled);
//		ungroup.setEnabled(enabled);
//		tofront.setEnabled(enabled);
//		toback.setEnabled(enabled);
		//copy.setEnabled(enabled);
		//cut.setEnabled(enabled);
	}

	//
	// KeyListener for Delete KeyStroke
	//
	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void keyReleased(KeyEvent e) {
	}

	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void keyTyped(KeyEvent e) {
	}

	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void keyPressed(KeyEvent e) {
		// Listen for Delete Key Press
		if (e.getKeyCode() == KeyEvent.VK_DELETE) {
			// Execute Remove Action on Delete Key Press
			this.commonButtons.getRemove().actionPerformed(null);
		}
	}

	// End of Editor.MyMarqueeHandler

	/**
	 *  ToolBar
	 *
	 *@return    Description of the Returned Value
	 */
	public ButtonToolBar createToolBar() {

		ButtonToolBar toolBar=new ButtonToolBar(this);
		return toolBar;
	}

	// Returns the total number of cells in a graph
	/**
	 *  Gets the cellCount attribute of the Editor object
	 *
	 *@param  graph  Description of Parameter
	 *@return        The cellCount value
	 */
	protected int getCellCount(JGraph graph) {
		Object[] cells = graph.getDescendants(graph.getRoots());
		return cells.length;
	}

	// Update Undo/Redo Button State based on Undo Manager
	/**
	 *  Description of the Method
	 */
	protected void updateHistoryButtons() {
		// The View Argument Defines the Context
		this.commonButtons.getUndo().setEnabled(undoManager.canUndo(graph.getGraphLayoutCache()));
		this.commonButtons.getRedo().setEnabled(undoManager.canRedo(graph.getGraphLayoutCache()));
	}

	//
	// Main
	//

	// Main Method
	/**
	 *  The main program for the Editor class
	 *
	 *@param  args  The command line arguments
	 */
	public static void main(String[] args) {
		// Construct Frame
		JFrame frame = new JFrame("GraphEd");
		// Set Close Operation to Exit
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Add an Editor Panel
		frame.getContentPane().add(new Editor(null));
		// Fetch URL to Icon Resource
		URL jgraphUrl =
			Editor.class.getClassLoader().getResource("images/jgraph.gif");
		// If Valid URL
		if (jgraphUrl != null) {
			// Load Icon
			ImageIcon jgraphIcon = new ImageIcon(jgraphUrl);
			// Use in Window
			frame.setIconImage(jgraphIcon.getImage());
		}
		// Set Default Size
		frame.setSize(520, 390);
		// Show Frame
		frame.show();
	}




	private void hideRoleLabels() {
		Object[] cells = this.graph.getSelectionCells();
		if (cells == null || cells.length == 0) {
			cells = this.graph.getRoots();
		}
		for (int k = 0; k < cells.length; k++) {
			Object gc = cells[k];

			if (gc instanceof DefaultEdge) {

				RoleEntity r = (RoleEntity) ( (org.jgraph.graph.DefaultEdge) gc).
				getUserObject();
				r.hide();
			}
		}
		this.graph.repaint();
	}

	private void showRoleLabels() {
		Object[] cells = this.graph.getSelectionCells();
		if (cells == null || cells.length == 0) {
			cells = this.graph.getRoots();
		}
		for (int k = 0; k < cells.length; k++) {
			Object gc = cells[k];
			if (gc instanceof DefaultEdge) {
				RoleEntity r = (RoleEntity) ( (org.jgraph.graph.DefaultEdge) gc).
				getUserObject();
				r.show(r.getAttributeToShow() + 1);

			}
		}

		this.graph.repaint();
	}

	public JComboBox getJC(){
		return this.commonButtons.getJc();
	}

	public void enableAutomaticLayout() {
		if (this.graph!=null){
			GraphModelListener[] gml = ( (DefaultGraphModel)this.graph.getModel()).
			getGraphModelListeners();
			for (int k = 0; k < gml.length; k++) {
				if (ingenias.editor.events.ChangeNARYEdgeLocation.class.isAssignableFrom(
						gml[k].getClass())) {
					( (ingenias.editor.events.ChangeNARYEdgeLocation) gml[k]).
					enableAutomaticAllocation();
				}
				if (ingenias.editor.events.ChangeEntityLocation.class.isAssignableFrom(
						gml[k].getClass())) {
					( (ingenias.editor.events.ChangeEntityLocation) gml[k]).
					enableAutomaticAllocation();
				}

			}
		}

	}

	public void disableAutomaticLayout() {
		if (this.graph!=null){
			GraphModelListener[] gml = ( (DefaultGraphModel)this.graph.getModel()).
			getGraphModelListeners();
			for (int k = 0; k < gml.length; k++) {
				if (ingenias.editor.events.ChangeNARYEdgeLocation.class.isAssignableFrom(
						gml[k].getClass())) {
					( (ingenias.editor.events.ChangeNARYEdgeLocation) gml[k]).
					disableAutomaticAllocation();
				}
				if (ingenias.editor.events.ChangeEntityLocation.class.isAssignableFrom(
						gml[k].getClass())) {
					( (ingenias.editor.events.ChangeEntityLocation) gml[k]).
					disableAutomaticAllocation();
				}


			}
		}
	}

//	Funciones especificas del modelo

	private JToolBar creaPaleta() {
		if (graph != null) {
			return graph.getPaleta();
		}
		else {
			return new JToolBar();
		}
	}

//	******************************************************************
//	NUEVOS
//	******************************************************************

	/*  Description of the Method
	 *
	 *@param  nEdge               Description of Parameter
	 *@param  selected            Description of Parameter
	 *@param  currentAssignation  Description of Parameter
	 */
	/*	private void insertRelationshipInManager(NAryEdge nEdge, GraphCell[] selected, java.util.List currentAssignation) {
	 // The NAryEdgeEntity of the relationship is built.
	  NAryEdgeEntity nae = (NAryEdgeEntity) nEdge.getUserObject();
	  for (int i = 0; i < currentAssignation.size(); i++) {
	  if (!(((DefaultGraphCell) selected[i]).getUserObject() instanceof NAryEdgeEntity)) {
	  nae.addObject( ((Entity)( (DefaultGraphCell) selected[i] ).getUserObject()),
	  ((RoleEntity)edges[i].getUserObject()),
	  (String) currentAssignation.get(i),
	  ( ( (DefaultGraphCell) selected[i] ).getUserObject().getClass().getName() ));
	  nae.addObject((Entity) ((DefaultGraphCell) selected[i]).getUserObject(),
	  (String) currentAssignation.get(i),
	  (((DefaultGraphCell) selected[i]).getUserObject().getClass().getName()));
	  }
	  }
	  // Insert the Edge in the relationship manager.
	   // this.rm.addRelationship((Entity) nEdge.getUserObject());
	    }*/

	/**
	 *  Gets the ports attribute of the Editor object
	 *
	 *@param  vertexList  Description of Parameter
	 *@param  portsList   Description of Parameter
	 *@return             The ports value
	 */
	public Port[] getPorts(Object[] vertexList, Map portsList) {

		// Ports of argument vertexs.
		Port[] ports = new Port[vertexList.length];
		// Obtain the model.
		GraphModel model = graph.getModel();

		// Iterate over all Objects.
		for (int i = 0; i < vertexList.length; i++) {
			Port objectPort = null;
			if (portsList.get(vertexList[i]) != null &&
					portsList.get(vertexList[i])instanceof Port) {
				objectPort = (Port) portsList.get(vertexList[i]);
			}
			else {
				// Iterate over all Children
				for (int j = 0; j < model.getChildCount(vertexList[i]); j++) {
					// Fetch the Child of Vertex at Index i
					Object child = model.getChild(vertexList[i], j);
					// Check if Child is a Port
					if (child instanceof Port) {
						// Return the Child as a Port
						objectPort = (Port) child;
					}
				}
			}

			ports[i] = objectPort;
		}

		return ports;
	}

	/**
	 *  Description of the Method
	 *
	 *@param  pt             Description of Parameter
	 *@param  selected       Description of Parameter
	 *@param  selectedPorts  Description of Parameter
	 */
	/*  public NAryEdge connectSequence(Point pt, GraphCell[] selected,
	 Map selectedPorts) {
	 // Possible edges.
	  Object[] ops = this.getRelacionesPosibles(selected);
	  SequenceModelModelJGraph graph = (SequenceModelModelJGraph)this.graph;
	  NAryEdge nEdge = null;
	  if (ops.length > 0) {
	  // sel can be:
	   // >= 0   If the user selects a relationship and accepts it.
	    // < 0    In other case.
	     int sel = getSelectedRelationship(ops).intValue();
	     if (sel >= 0) {
	     // N-ary relationship.
	      nEdge = (NAryEdge)this.getInstanciaNRelacion(ops[sel].toString(),
	      selected);
	      if (nEdge != null) {
	      // All role assignations to classes are obtained.
	       // Remove nEdge from selected if it is present.
	        GraphCell[] selectedWithNoNAryEdge = nEdge.prepareSelected(selected);
	        // The user selects a role assignation (List of Strings).
	         java.util.List currentAssignation = this.selectAssignation(
	         selectedWithNoNAryEdge, nEdge);
	         if (currentAssignation != null) {
	         // Connections that will be inserted into the Model.
	          String[] selectedAssignation = new String[currentAssignation.size()];
	          for (int i = 0; i < currentAssignation.size(); i++) {
	          selectedAssignation[i] = (String) currentAssignation.get(i);
	          }
	          GraphCell[] newInserted = null;
	          Port[] portsToConnect = this.getPorts(selectedWithNoNAryEdge,
	          selectedPorts);
	          // Revisar y actualizar barras.
	           // Revisar newSelected.
	            // Revisar Ports.
	             if (this.graph instanceof SequenceModelModelJGraph) {
	             newInserted = ( (SequenceModelModelJGraph)this.graph).updateBars(
	             nEdge, selectedWithNoNAryEdge, portsToConnect);
	             }
	             try {
	             // Auxiliary edges that will be inserted in the Model.
	              DefaultEdge[] auxiliaryEdges = nEdge.connectionsEdges(
	              selectedWithNoNAryEdge, selectedAssignation);
	              ConnectionSet cs = nEdge.connections(selectedAssignation,
	              auxiliaryEdges, portsToConnect);
	              // Create a Map that holds the attributes for the NAryEdge Vertex.
	               // Associate the NAryEdge Vertex with its Attributes.
	                Hashtable attributes = this.nEdgeAttributes(nEdge, pt);
	                // Atributes for the binary edges of this NAryEdge according to
	                 // targets and sources.
	                  Hashtable edgesAttributes = this.edgesAttributes(auxiliaryEdges,
	                  selectedAssignation);
	                  // Insert the Edge and its Attributes. The order matters.
	                   graph.insert(new Object[] {nEdge}
	                   , null, null, attributes);
	                   ///////// Quiz�s haya que corregir los selectedWithNoNAryEdge con un graph.getModel().edit().
	                    graph.insert( (Object[]) newInserted, null, null, null);
	                    graph.insert( (Object[]) auxiliaryEdges, cs, null,
	                    edgesAttributes);
	                    // Update bars positions ///////////////////////////////////////
	                     this.insertRelationshipInManager(nEdge, auxiliaryEdges,
	                     selectedWithNoNAryEdge,
	                     currentAssignation);
	                     if (nEdge instanceof ingenias.editor.cell.SDivideActivationEdge) {
	                     ingenias.editor.auml.cell.ActivationCell sourceac = (ingenias.
	                     editor.auml.cell.ActivationCell) selected[0];
	                     ingenias.editor.auml.cell.ActivationCell destac = (ingenias.
	                     editor.auml.cell.ActivationCell) selected[1];
	                     ingenias.editor.auml.cell.ActivationCell.divideBar(destac,
	                     (Port) selectedPorts.get(sourceac), this.graph);
	                     graph.setBarsPositions(newInserted, pt);
	                     graph.updateBarsPositions();
	                     //                                                 graph.updateObjectsAndPorts();
	                      System.err.println("divides");
	                      }
	                      else {
	                      if (nEdge instanceof ingenias.editor.cell.SCommunicatesEdge) {
	                      graph.setBarsPositions(newInserted, pt);
	                      graph.updateBarsPositions();
	                      System.err.println("communicates");
	                      }
	                      }
	                      ////////////////////////////
	                       // New relationship is inserted also in the relationship manager.
	                        //this.insertRelationshipInManager(nEdge, auxiliaryEdges, selected,currentAssignation);
	                         ingenias.editor.events.ReallocateSequenceColumns.reallocatePorts(this.
	                         graph);
	                         } catch (WrongParameters wp){
	                         Log.getInstance().logSYS("WARNING!!! INTERNAL ERROR. Cannot produce edges to"+
	                         " connect these entities");
	                         }
	                         }
	                         else {
	                         JOptionPane.showMessageDialog(this, "Assignation not allowed",
	                         "Warning",
	                         JOptionPane.WARNING_MESSAGE);
	                         }
	                         }
	                         }
	                         }
	                         else {
	                         JOptionPane.showMessageDialog(this, "Relationship not allowed", "Warning",
	                         JOptionPane.WARNING_MESSAGE);
	                         }
	                         return nEdge;
	                         }*/

	public void writeObject(ObjectOutputStream s) throws IOException {

	}

	public ButtonToolBar getCommonButtons() {
		return commonButtons;
	}

}
