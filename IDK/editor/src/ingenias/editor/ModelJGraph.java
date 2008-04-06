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

package ingenias.editor;

import java.awt.*;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.util.Map;
import java.util.Hashtable;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.Map;
import java.util.Hashtable;
import java.util.ArrayList;
import javax.swing.event.UndoableEditEvent;
import javax.swing.plaf.ComponentUI;

import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.jgraph.event.*;
import java.util.Vector;
import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.jgraph.event.*;
import org.jgraph.plaf.GraphUI;
import org.jgraph.plaf.basic.*;
import org.jgraph.plaf.basic.BasicGraphUI.MouseInputHandler;

import ingenias.editor.editiondialog.GeneralEditionPanel;
import ingenias.editor.entities.*;
import ingenias.editor.cell.*;
import ingenias.editor.events.*;
import ingenias.exception.InvalidEntity;


public abstract class ModelJGraph extends JGraph {
	protected Action undo, redo, remove, group, ungroup, tofront, toback,
	cut, copy, paste;
	protected JToolBar toolbar;
	private ObjectManager om;
	String name;
	
	ModelDataEntity mde=null;
	
	static{ 
		// To enable SVG. These definitions are needed to let the UIManager
		// know for this look and feel which ComponentUI are needed
		UIManager.getDefaults().put(new org.swixml.XVBox().getUIClassID(),"org.swixml.XVBox");
		UIManager.getDefaults().put(new JGraph().getUIClassID(),"ingenias.editor.ModelJGraph");
	}
	
	
	public ModelJGraph(ModelDataEntity mde,String nombre,Model m,BasicMarqueeHandler mh, ObjectManager om){
		super(m,mh);
		this.mde=mde;
		this.name=nombre;		
		this.om=om;
		if (om==null)
			throw new RuntimeException("OM is null  in Model JGraph");
		creaToolBar();
		
		// this.setTransferHandler(new RelationshipEntityTransferHandler());
	}
	
	//
	// Customizing In-Place Editing
	//
	
	public void updateUI() {
		//super.updateUI();
		// Install a new UI
		setUI(new DialogUI());
		invalidate();
	}
	
	public class DialogUI  extends BasicGraphUI implements java.io.Serializable {
		
		protected CellEditorListener cellEditorListener;
		
		protected JDialog editDialog = null;
		
		
		public DialogUI(){  }
		
		protected void completeEditing(boolean messageStop,
				boolean messageCancel,
				boolean messageGraph) {
			if (stopEditingInCompleteEditing && editingComponent != null &&
					editDialog != null) {
				Component oldComponent = editingComponent;
				Object oldCell = editingCell;
				GraphCellEditor oldEditor = cellEditor;
				Object newValue = oldEditor.getCellEditorValue();
				Rectangle editingBounds = graph.getCellBounds(editingCell).getBounds();
				boolean requestFocus = (graph != null &&
						(graph.hasFocus() || editingComponent.hasFocus()));
				editingCell = null;
				editingComponent = null;
				if (messageStop) {
					oldEditor.stopCellEditing();
				}
				else if (messageCancel) {
					oldEditor.cancelCellEditing();
				}
				editDialog.dispose();
				if (requestFocus) {
					graph.requestFocus();
				}
				if (messageGraph) {
					Map map =new Hashtable();
					GraphConstants.setValue(map, newValue);
					Map insert = new Hashtable();
					insert.put(oldCell, map);
					graphModel.edit(insert,null,  null, null);
				}
				updateSize();
				// Remove Editor Listener
				if (oldEditor != null && cellEditorListener != null) {
					oldEditor.removeCellEditorListener(cellEditorListener);
				}
				cellEditor = null;
				editDialog = null;
			}
		}
		protected boolean startEditing(Object cell, MouseEvent event) {
			
			completeEditing();
			/*if (cell instanceof DefaultEdge){
				cell=MarqueeHandler.getNAryEdge(ed.getGraph().getModel(),(DefaultEdge)cell);
			}*/
			
			if (graph.isCellEditable(cell) && editDialog == null) {      	
				CellView tmp = graphLayoutCache.getMapping(cell, false);
				cellEditor = tmp.getEditor();
				editingComponent = cellEditor.getGraphCellEditorComponent(graph, cell,
						graph.isCellSelected(cell));
				if (cellEditor.isCellEditable(event)) {
					JScrollPane jsp=new JScrollPane();
					
					JPanel south=new JPanel();
					JButton accept=new JButton("Accept");					
					JButton cancel=new JButton("Cancel");
					south.add(accept);
					south.add(cancel);
					JPanel main=new JPanel();
					main.setLayout(new BorderLayout());
					main.add(south, BorderLayout.SOUTH);
					final Entity ent=(Entity) ((DefaultGraphCell)cell).getUserObject();
					final GeneralEditionPanel gep=new GeneralEditionPanel(null, IDE.ide, getOM(), ent);
					jsp.getViewport().add(gep,null);
					main.add(jsp, BorderLayout.CENTER);
					
					if (IDE.ide==null || IDE.ide.isEditPropertiesPopUp()){
						editingCell = cell;
						Dimension editorSize = editingComponent.getPreferredSize();
						final JDialog editDialog=new JDialog(IDE.ide,true);
						editDialog.getContentPane().setLayout(new BorderLayout());
						editDialog.getContentPane().add(main,BorderLayout.CENTER);						
						editDialog.pack();						
						String oldid=ent.getId();
						editDialog.setLocation(ingenias.editor.widget.GraphicsUtils.getCenter(IDE.ide,editDialog.getSize()));
						cancel.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								if (gep.isModified()){
									int result = JOptionPane.showConfirmDialog(IDE.ide,
											"You will loose current changes. Do you want to continue (y/n)?",
											"Warning", JOptionPane.YES_NO_OPTION,
											JOptionPane.WARNING_MESSAGE);
									if (result == JOptionPane.OK_OPTION){
										gep.undo();		
										editDialog.setVisible(false);
									}
								} else {								
									editDialog.setVisible(false);
								}
								graph.repaint();
							};
						});
						accept.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								editDialog.setVisible(false);
								gep.confirmActions();
								graph.repaint();
							};
						});
						
						
						editDialog.setVisible(true);
						if (cell instanceof NAryEdge){
							RenderComponentManager.setRelationshipView(ent.getPrefs().getView(),ent,(DefaultGraphCell)cell,graph);
						}
						Vector result=getOM().findUserObject(ent.getId());
						if (result.size()>1 ){							
							JOptionPane.showMessageDialog(null,"There is already another entity with name "+ent.getId()+". I will restore old id","ERROR",JOptionPane.ERROR_MESSAGE);
							ent.setId(oldid);
						}
						getOM().reload();
						System.err.println("Terminada edicion");
					} else {
						final String proppaneltitle=ent.getType()+":"+ent.getId();
						IDE.ide.addPropertiesPanel(proppaneltitle,main);   
						IDE.ide.focusPropertiesPane(proppaneltitle);
						cancel.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								if (gep.isModified()){
									int result = JOptionPane.showConfirmDialog(IDE.ide,
											"You will loose current changes. Do you want to continue (y/n)?",
											"Warning", JOptionPane.YES_NO_OPTION,
											JOptionPane.WARNING_MESSAGE);
									if (result == JOptionPane.OK_OPTION){
										gep.undo();												
										IDE.ide.removePropertiesPane(proppaneltitle);
									}
								} else {
									IDE.ide.removePropertiesPane(proppaneltitle);
								}
								graph.repaint();
							};				
						});
						accept.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								graph.repaint();
								gep.confirmActions();
								IDE.ide.removePropertiesPane(proppaneltitle);								
							};
						});
						
						
						
					}
					
					
					// Add Editor Listener
					if (cellEditorListener == null) {
						cellEditorListener = createCellEditorListener();
					}
					if (cellEditor != null && cellEditorListener != null) {
						cellEditor.addCellEditorListener(cellEditorListener);
						
					}
					if (cellEditor.shouldSelectCell(event)) {
						stopEditingInCompleteEditing = false;
						try {
							graph.setSelectionCell(cell);
						}
						catch (Exception e) {
							System.err.println("Editing exception: " + e);
						}
						stopEditingInCompleteEditing = true;
					}
					
					if (event instanceof MouseEvent) {
						/* Find the component that will get forwarded all the
						 mouse events until mouseReleased. */
						Point componentPoint = SwingUtilities.convertPoint
						(graph, new Point(event.getX(), event.getY()),
								editingComponent);
						/* Create an instance of BasicTreeMouseListener to handle
						 passing the mouse/motion events to the necessary
						 component. */
						// We really want similiar behavior to getMouseEventTarget,
						// but it is package private.
						Component activeComponent = SwingUtilities.
						getDeepestComponentAt(editingComponent,
								componentPoint.x, componentPoint.y);
						if (activeComponent != null) {
							new MouseInputHandler(graph, activeComponent, event);
						}
					}
					return true;
				}
				else {
					editingComponent = null;
				}
			}
			return false;
		}
		
	}
	
	
	public static ComponentUI createUI(JComponent jc){
		return new BasicGraphUI();
	}
	
	protected abstract void creaToolBar();
	
	public JToolBar getPaleta(){ return toolbar;}
	
//	abstract public DefaultEdge getInstanciaRelacion(String relacion);
	
	public abstract DefaultGraphCell getInstanciaNRelacion(String relacion, GraphCell[] selected);
	
	public abstract DefaultGraphCell createCell(String entity) throws InvalidEntity;
	 public abstract Dimension getDefaultSize(Entity entity) throws InvalidEntity;
	
	
	public Object[] getRelacionesPosibles(Port source, Port target) {
		// The general getRelacionesPosibles method only admits a GraphCell[] parameter.
		GraphCell sourceGraphCell = (GraphCell) this.getModel().getParent(source);
		GraphCell targetGraphCell = (GraphCell) this.getModel().getParent(target);
		// The general getRelacionesPosibles method is invoked.
		return this.getPossibleRelationships(new GraphCell[]{sourceGraphCell, targetGraphCell});
	}
	
	abstract public Object[] getPossibleRelationships(GraphCell[] selected);
	
	abstract public DefaultGraphCell insert(Point point, String entity) throws InvalidEntity;
	
	abstract public DefaultGraphCell insertDuplicated(Point point, ingenias.editor.entities.Entity entity);

	
	
	
	public String getID(){
		return this.mde.getId();
	}
	
	public void setId(String id){
		this.mde.setId(id);
	}
	
	public ModelDataEntity getProperties(){
		return mde;
	}
	protected Point convert(java.awt.geom.Point2D p2d){
		Point p=new Point((int)p2d.getX(),(int)p2d.getY());
		return p;
	}
	
	
	public VertexView createExternalVertexView(Object v, CellMapper cm){
		return null;
	}
	
	public static java.awt.Point findEmptyPlacePoint(Dimension dim, org.jgraph.JGraph model){
		int j=0;
		int y=30;
		boolean occupied=true;
		while (occupied){
			Rectangle2D clip=new Rectangle(new Point(j,y), dim);
			occupied=model.getGraphLayoutCache().getRoots(clip)!=null  && model.getGraphLayoutCache().getRoots(clip).length!=0;
			
			/*FirstCellForLocation(j, y)!=null ||
			model.getFirstCellForLocation(j+20, y)!=null ||
			model.getFirstCellForLocation(j, y+20)!=null ||
			model.getFirstCellForLocation(j+20, y+20)!=null;*/
			if (occupied){
				j=(int) (j+dim.getWidth());
			}
			if (j>(model.getVisibleRect().x+model.getVisibleRect().width)){
				j=model.getVisibleRect().x;
				y=(int) (y+dim.getHeight());
			}
			
		}
		return new  java.awt.Point(j,y);
		
	}
	
	public static int findEmptyPlace(org.jgraph.JGraph model){
		int j=0;
		int y=30;
		boolean occupied=true;
		while (occupied){
			occupied=model.getFirstCellForLocation(j, y)!=null ||
			model.getFirstCellForLocation(j+20, y)!=null ||
			model.getFirstCellForLocation(j, y+20)!=null ||
			model.getFirstCellForLocation(j+20, y+20)!=null;
			if (occupied){
				j=j+10;
			}
			if (j>(model.getVisibleRect().x+model.getVisibleRect().width)){
				j=model.getVisibleRect().x;
				y=y+20;
			}
			
		}
		return j;
		
	}
	
	protected ObjectManager getOM() {
		return om;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	
}