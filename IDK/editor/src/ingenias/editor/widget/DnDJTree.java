/*
    This code has been extracted and modified from an original
    work from Rob Kenworthy and an example from Sheetal Gupta
    (http://java.sun.com/docs/books/tutorial/dnd/sheetal.html)

 */

package ingenias.editor.widget;

import javax.swing.JTree;
import java.awt.dnd.*;
import java.awt.Point;
import java.awt.datatransfer.*;
import java.awt.Cursor;
import java.io.*;
import javax.swing.tree.*;
import java.awt.Component;
import org.jgraph.JGraph;
import javax.swing.JViewport;
import java.awt.*;

import java.awt.*;
import java.awt.dnd.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import javax.swing.*;
import java.util.*;


public class DnDJTree extends JTree implements java.io.Serializable,
DropTargetListener,DragSourceListener,DragGestureListener{

	DropTarget dropTarget=null;
	DragSource dragSource=null;
	JScrollPane jsp=null;
	Vector expansionPaths=new Vector();

	boolean dragOn=false;

	DefaultMutableTreeNode nodeInTransfer=null;

	public DnDJTree(JScrollPane jsp,TreeNode tn) {
		super(tn);
		this.setAutoscrolls(true);
		this.jsp=jsp;
		final JTree jt=this;
		dropTarget=new DropTarget(this,this);
		dragSource=new DragSource();
		dragSource.createDefaultDragGestureRecognizer(this,
				DnDConstants.ACTION_MOVE,this);
		this.addKeyListener(new KeyListener(){

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			// Code added to make nodes go up and down among siblings
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar()=='q' && jt.getSelectionPath()!=null){
					DefaultMutableTreeNode node =
						(DefaultMutableTreeNode)jt.getSelectionPath().getLastPathComponent();
					if (node.getParent()!=null){
						int index=node.getParent().getIndex(node);
						if (index>0){
							DefaultMutableTreeNode parent=(DefaultMutableTreeNode)node.getParent();
							node.removeFromParent();
							parent.insert(node, index-1);
							node.setParent(parent);
							storeTreeExpansionPaths();
							((DefaultTreeModel)getModel()).reload();
							validate();
							restoreTreeExpansionPath();
							jt.addSelectionPath(new TreePath(node.getPath()));
						}
					}
				}
				if (e.getKeyChar()=='z' && jt.getSelectionPath()!=null){
					DefaultMutableTreeNode node =
						(DefaultMutableTreeNode)jt.getSelectionPath().getLastPathComponent();
					if (node.getParent()!=null){
						int index=node.getParent().getIndex(node);
						if (index<node.getParent().getChildCount()-1){
							DefaultMutableTreeNode parent=(DefaultMutableTreeNode)node.getParent();
							node.removeFromParent();
							parent.insert(node, index+1);
							node.setParent(parent);
							storeTreeExpansionPaths();
							((DefaultTreeModel)getModel()).reload();
							validate();
							restoreTreeExpansionPath();
							jt.addSelectionPath(new TreePath(node.getPath()));
						}
					}
				}

			}
		});


	}

	public void storeTreeExpansionPaths(){
		TreePath[] tp=this.getPathBetweenRows(0,this.getRowCount()-1);
		expansionPaths=new Vector();
		for (int k=0;k<tp.length;k++){
			if (this.isExpanded(tp[k]))
				expansionPaths.add(tp[k]);
		}

	}

	public void restoreTreeExpansionPath(){
		Enumeration enumeration=this.expansionPaths.elements();
		while (enumeration.hasMoreElements()){
			TreePath tp1=(TreePath)enumeration.nextElement();
			this.expandPath(tp1);
		}
		this.expansionPaths=new Vector();

	}

	public Insets getAutoscrollInsets(){
		Rectangle r=this.getVisibleRect();
		Dimension s=this.getSize();

		return new Insets(r.y+15,r.x+15,s.height-r.y-r.height+15,s.width-r.x-r.width+15);
	}

	public DnDJTree() {
		super(new DefaultMutableTreeNode("Project"));
		dropTarget=new DropTarget(this,this);
		dragSource=new DragSource();
		dragSource.createDefaultDragGestureRecognizer(this,
				DnDConstants.ACTION_MOVE,this);
	}

	/**
	 * is invoked when you are dragging over the DropSite
	 *
	 */

	public void dragEnter (DropTargetDragEvent event) {

		// debug messages for diagnostics

		event.acceptDrag (DnDConstants.ACTION_MOVE);
	}

	/**
	 * is invoked when you are exit the DropSite without dropping
	 *
	 */

	public void dragExit (DropTargetEvent event) {


	}


	/**
	 * a drop has occurred
	 *
	 */


	public void drop (DropTargetDropEvent event) {

		try {
			Transferable transferable = event.getTransferable();
			DefaultMutableTreeNode target=this.findNode(event.getLocation());
			// we accept only Strings
			if (transferable.isDataFlavorSupported (DataFlavor.stringFlavor) &&
					target!=null &&
					!JGraph.class.isAssignableFrom(target.getUserObject().getClass()) &&
					!this.nodeInTransfer.isNodeDescendant(target)){

				event.acceptDrop(DnDConstants.ACTION_MOVE);
				String s = (String)transferable.getTransferData ( DataFlavor.stringFlavor);
				//           addElement( s );
				this.nodeInTransfer.removeFromParent();
				target.add( this.nodeInTransfer);
				this.nodeInTransfer.setParent(target);
				this.storeTreeExpansionPaths();
				((DefaultTreeModel)this.getModel()).reload();
				this.validate();
				this.restoreTreeExpansionPath();
				event.getDropTargetContext().dropComplete(true);
				System.err.println("***********transferido");
			}
			else{ 
				//Change the order

				if (target.getParent()!=null){
					event.acceptDrop(DnDConstants.ACTION_MOVE);
					int index=target.getParent().getIndex(target);
					event.getDropTargetContext().dropComplete(true);
					if (index!=-1){
						this.nodeInTransfer.removeFromParent();
						((DefaultMutableTreeNode)target.getParent()).insert( this.nodeInTransfer, index);
						this.nodeInTransfer.setParent((DefaultMutableTreeNode)(target.getParent()));
						this.storeTreeExpansionPaths();
						((DefaultTreeModel)this.getModel()).reload();
						this.validate();
						this.restoreTreeExpansionPath();
						event.getDropTargetContext().dropComplete(true);
					}
				} else
					event.rejectDrop();



				System.err.println("Rechazado");
				//
			}
//			this.setCursor(null);
		}
		catch (IOException exception) {
			exception.printStackTrace();

			event.rejectDrop();
		}
		catch (UnsupportedFlavorException ufException ) {
			ufException.printStackTrace();

			event.rejectDrop();
		}
	}

	/**
	 * is invoked if the use modifies the current drop gesture
	 *
	 */


	public void dropActionChanged ( DropTargetDragEvent event ) {
	}

	/**
	 * a drag gesture has been initiated
	 *
	 */

	public void dragGestureRecognized( DragGestureEvent event) {
		TreePath tp=this.getSelectionPath();
		if (tp!=null){
			DefaultMutableTreeNode selected =(DefaultMutableTreeNode) tp.getLastPathComponent();
			if ( selected != null ){
				StringSelection text = new StringSelection( "");
				this.nodeInTransfer=selected;
				// as the name suggests, starts the dragging
				dragSource.startDrag (event, DragSource.DefaultMoveDrop, text, this);
			} else {

			}
		}
	}

	/**
	 * this message goes to DragSourceListener, informing it that the dragging
	 * has ended
	 *
	 */

	public void dragDropEnd (DragSourceDropEvent event) {
		if ( event.getDropSuccess()){

		}
	}

	/**
	 * this message goes to DragSourceListener, informing it that the dragging
	 * has entered the DropSite
	 *
	 */

	public void dragEnter (DragSourceDragEvent event) {

	}

	/**
	 * this message goes to DragSourceListener, informing it that the dragging
	 * has exited the DropSite
	 *
	 */

	public void dragExit (DragSourceEvent event) {


	}

	/**
	 * this message goes to DragSourceListener, informing it that the dragging is currently
	 * ocurring over the DropSite
	 *
	 */

	public void dragOver (DragSourceDragEvent event) {
	}

	private DefaultMutableTreeNode findNode(Point p){
		TreePath tp=this.getPathForLocation(p.x,p.y);
		if (tp!=null){

			DefaultMutableTreeNode dmtn=(DefaultMutableTreeNode)tp.getLastPathComponent();
			return dmtn;
		} else
			return null;
	}

	private void moveMouseWithDrag(Point p){

		Rectangle visibleHeight=jsp.getViewport().getViewRect();

		DefaultMutableTreeNode dmtn=this.findNode(p);

		if (visibleHeight.height-p.y<40){
			jsp.validate();

			jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getValue()+10);
		}
		if ((p.y-visibleHeight.y<40) && (jsp.getVerticalScrollBar().getValue()>10)){
			jsp.validate();

			jsp.getVerticalScrollBar().setValue(jsp.getVerticalScrollBar().getValue()-10);
		}




		// this.getLocation()

		if (dmtn!=null){


			this.setSelectionPath(new TreePath(dmtn.getPath()));

		} else {
		}
	}

	public void dragOver (DropTargetDragEvent event) {
		this.moveMouseWithDrag(event.getLocation());


	}

	/**
	 * is invoked when the user changes the dropAction
	 *
	 */

	public void dropActionChanged ( DragSourceDragEvent event) {

	}


}





