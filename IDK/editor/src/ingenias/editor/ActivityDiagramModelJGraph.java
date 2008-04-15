
/*
    Copyright (C) 2002 Jorge Gomez Sanz, Ruben Fuentes, Juan Pavon

    Modifications over original code from jgraph.sourceforge.net

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
import ingenias.exception.InvalidEntity;

public class ActivityDiagramModelJGraph extends ModelJGraph {

  public ActivityDiagramModelJGraph(ActivityDiagramDataEntity mde, 
                               String nombre, ObjectManager om, Model
                               m, BasicMarqueeHandler mh) {
    super(mde, nombre, m, mh,om);
    
			 
				  ToolTipManager.sharedInstance().registerComponent(this);
				  this.getModel().addGraphModelListener(
				  new ChangeNARYEdgeLocation(this));
				  this.getModel().addGraphModelListener(
				  new ChangeEntityLocation(this));			  
				  
		
    this.getGraphLayoutCache().setFactory(new ingenias.editor.cellfactories.ActivityDiagramCellViewFactory());
  }


  //
  // Adding Tooltips
  //

  // Return Cell Label as a Tooltip
  public String getToolTipText(MouseEvent e) {
    if (e != null) {
      // Fetch Cell under Mousepointer
      Object c = getFirstCellForLocation(e.getX(), e.getY());
      if (c != null) {

        // Convert Cell to String and Return
        return convertValueToString(c);
      }
    }
    return null;
  }





  public JToolBar getPaleta() {
    return toolbar;

  }

  protected void creaToolBar() {
    toolbar = new JToolBar(JToolBar.VERTICAL);
    toolbar.setFloatable(false);
    ImageIcon undoIcon = null;
    JButton jb = null;


    Image img_InitialNode =
        ImageLoader.getImage("images/minitialnode.gif");
    undoIcon = new ImageIcon(img_InitialNode);
    Action InitialNode=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "InitialNode");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    InitialNode.setEnabled(true);
    jb = new JButton(InitialNode);
    jb.setToolTipText("InitialNode");
    toolbar.add(jb);

    Image img_DecisionNode =
        ImageLoader.getImage("images/mdecisionnode.gif");
    undoIcon = new ImageIcon(img_DecisionNode);
    Action DecisionNode=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "DecisionNode");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    DecisionNode.setEnabled(true);
    jb = new JButton(DecisionNode);
    jb.setToolTipText("DecisionNode");
    toolbar.add(jb);

    Image img_ForkNode =
        ImageLoader.getImage("images/mforknode.gif");
    undoIcon = new ImageIcon(img_ForkNode);
    Action ForkNode=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "ForkNode");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    ForkNode.setEnabled(true);
    jb = new JButton(ForkNode);
    jb.setToolTipText("ForkNode");
    toolbar.add(jb);

    Image img_EndNode =
        ImageLoader.getImage("images/mendnode.gif");
    undoIcon = new ImageIcon(img_EndNode);
    Action EndNode=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "EndNode");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    EndNode.setEnabled(true);
    jb = new JButton(EndNode);
    jb.setToolTipText("EndNode");
    toolbar.add(jb);

    Image img_Task =
        ImageLoader.getImage("images/mtask.gif");
    undoIcon = new ImageIcon(img_Task);
    Action Task=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Task");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Task.setEnabled(true);
    jb = new JButton(Task);
    jb.setToolTipText("Task");
    toolbar.add(jb);

    Image img_Role =
        ImageLoader.getImage("images/mrole.gif");
    undoIcon = new ImageIcon(img_Role);
    Action Role=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Role");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Role.setEnabled(true);
    jb = new JButton(Role);
    jb.setToolTipText("Role");
    toolbar.add(jb);

    Image img_WorkflowBox =
        ImageLoader.getImage("images/mworkflow.gif");
    undoIcon = new ImageIcon(img_WorkflowBox);
    Action WorkflowBox=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "WorkflowBox");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    WorkflowBox.setEnabled(true);
    jb = new JButton(WorkflowBox);
    jb.setToolTipText("WorkflowBox");
    toolbar.add(jb);

    Image img_UMLComment =
        ImageLoader.getImage("images/mumlcomment.gif");
    undoIcon = new ImageIcon(img_UMLComment);
    Action UMLComment=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "UMLComment");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    UMLComment.setEnabled(true);
    jb = new JButton(UMLComment);
    jb.setToolTipText("UMLComment");
    toolbar.add(jb);

    Image img_TextNote =
        ImageLoader.getImage("images/mtext.gif");
    undoIcon = new ImageIcon(img_TextNote);
    Action TextNote=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "TextNote");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    TextNote.setEnabled(true);
    jb = new JButton(TextNote);
    jb.setToolTipText("TextNote");
    toolbar.add(jb);


  }

  public static Vector<String> getAllowedRelationships(){
   Vector<String> relationships=new   Vector<String>();


          relationships.add("UMLAnnotatedElement");

          relationships.add("WFStarts");

          relationships.add("WFEnds");

          relationships.add("WFDecides");

          relationships.add("WFFollows");

          relationships.add("WFFollowsGuarded");

   return relationships;
  }

  public static Vector<String> getAllowedEntities(){
   Vector<String> entities=new   Vector<String>();


 entities.add("InitialNode");

 entities.add("DecisionNode");

 entities.add("ForkNode");

 entities.add("EndNode");

 entities.add("Task");

 entities.add("Role");

 entities.add("WorkflowBox");

 entities.add("UMLComment");

 entities.add("TextNote");

   return entities;
  }

  // Gets the name of the possible relationships for the selected GraphCells.
  // A relationship can be binary (DefaultEdge) or n-ary (NAryEdge).
  // The requested action is slightly different depending on selected items.
  // According to the number of Edges in selected, the action can be:
  // 0 => Propose a relationship between selected according included classes.
  // 1 and it is NAryEdge => The class of that NAryEdge if it is possible according implements java.io.Serializable
  //      current cardinality and included classes..
  // other cases => Error, no relationships are allowed.
  public Object[] getPossibleRelationships(GraphCell[] selected) {
    // Possible relationships initialization.
    Vector v = new Vector();

    // Search for NAryEdges in selected.
    int nAryEdgesNum = 0;
    int edgesNum = 0;
    NAryEdge selectedEdge = null;
    for (int i = 0; i < selected.length; i++) {
      if (selected[i] instanceof NAryEdge) {
        nAryEdgesNum++;
        selectedEdge = (NAryEdge) selected[i];
      }
      else if (selected[i] instanceof DefaultEdge) {
        edgesNum++;

        // Connections are only possible with two or more elements and without binary edges.
      }
    }
    if (selected.length >= 2 && edgesNum == 0) {

      // The number of NAryEdges is considered.
      if (nAryEdgesNum == 0) {
        // acceptConnection methods only admits vertex parameters.
        // Binary relationships.

        // N-ary relationships. Sometimes they can be also binary.
        if (UMLAnnotatedElementEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UMLAnnotatedElement");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFStartsEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFStarts");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFEndsEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFEnds");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFDecidesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFDecides");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFFollowsEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFFollows");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFFollowsGuardedEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFFollowsGuarded");
	  }

      }
      else if (nAryEdgesNum == 1) {

        if (selectedEdge instanceof UMLAnnotatedElementEdge &&
        (UMLAnnotatedElementEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UMLAnnotatedElement");
        }

        if (selectedEdge instanceof WFStartsEdge &&
        (WFStartsEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFStarts");
        }

        if (selectedEdge instanceof WFEndsEdge &&
        (WFEndsEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFEnds");
        }

        if (selectedEdge instanceof WFDecidesEdge &&
        (WFDecidesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFDecides");
        }

        if (selectedEdge instanceof WFFollowsEdge &&
        (WFFollowsEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFFollows");
        }

        if (selectedEdge instanceof WFFollowsGuardedEdge &&
        (WFFollowsGuardedEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFFollowsGuarded");
        }

      }
    }

    return v.toArray();
  }

  public DefaultGraphCell getInstanciaNRelacion(String relacion,
                                                GraphCell[] selected) {

    // Search for NAryEdges in selected.
    int nAryEdgesNum = 0;
    int edgesNum = 0;
    NAryEdge selectedEdge = null;
    for (int i = 0; i < selected.length; i++) {
      if (selected[i] instanceof NAryEdge) {
        nAryEdgesNum++;
        selectedEdge = (NAryEdge) selected[i];
      }
      else if (selected[i] instanceof DefaultEdge) {
        edgesNum++;

      }
    }
    if (nAryEdgesNum <= 1 && edgesNum == 0) {

      if (relacion.equalsIgnoreCase("UMLAnnotatedElement")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof UMLAnnotatedElementEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new UMLAnnotatedElementEdge(new UMLAnnotatedElement(Editor.getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFStarts")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFStartsEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFStartsEdge(new WFStarts(Editor.getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFEnds")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFEndsEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFEndsEdge(new WFEnds(Editor.getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFDecides")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFDecidesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFDecidesEdge(new WFDecides(Editor.getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFFollows")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFFollowsEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFFollowsEdge(new WFFollows(Editor.getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFFollowsGuarded")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFFollowsGuardedEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFFollowsGuardedEdge(new WFFollowsGuarded(Editor.getNewId()));
        }
      }

    }

    return null;
  }
  
  public DefaultGraphCell createCell(String entity) throws InvalidEntity{
  
    if (entity.equalsIgnoreCase("InitialNode")) {
    InitialNode nentity=getOM().createInitialNode( Editor.getNewId("InitialNode"));
      DefaultGraphCell vertex = new
          InitialNodeCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("DecisionNode")) {
    DecisionNode nentity=getOM().createDecisionNode( Editor.getNewId("DecisionNode"));
      DefaultGraphCell vertex = new
          DecisionNodeCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("ForkNode")) {
    ForkNode nentity=getOM().createForkNode( Editor.getNewId("ForkNode"));
      DefaultGraphCell vertex = new
          ForkNodeCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("EndNode")) {
    EndNode nentity=getOM().createEndNode( Editor.getNewId("EndNode"));
      DefaultGraphCell vertex = new
          EndNodeCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Task")) {
    Task nentity=getOM().createTask( Editor.getNewId("Task"));
      DefaultGraphCell vertex = new
          TaskCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Role")) {
    Role nentity=getOM().createRole( Editor.getNewId("Role"));
      DefaultGraphCell vertex = new
          RoleCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("WorkflowBox")) {
    WorkflowBox nentity=getOM().createWorkflowBox( Editor.getNewId("WorkflowBox"));
      DefaultGraphCell vertex = new
          WorkflowBoxCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("UMLComment")) {
    UMLComment nentity=getOM().createUMLComment( Editor.getNewId("UMLComment"));
      DefaultGraphCell vertex = new
          UMLCommentCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("TextNote")) {
    TextNote nentity=getOM().createTextNote( Editor.getNewId("TextNote"));
      DefaultGraphCell vertex = new
          TextNoteCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

	  throw new ingenias.exception.InvalidEntity("Entity type "+entity+" is not allowed in this diagram"); 
  }
  
  public Dimension getDefaultSize(Entity entity) throws InvalidEntity{
    
    if (entity.getType().equalsIgnoreCase("InitialNode")) {
      return InitialNodeView.getSize((InitialNode)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("DecisionNode")) {
      return DecisionNodeView.getSize((DecisionNode)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ForkNode")) {
      return ForkNodeView.getSize((ForkNode)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("EndNode")) {
      return EndNodeView.getSize((EndNode)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Task")) {
      return TaskView.getSize((Task)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Role")) {
      return RoleView.getSize((Role)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("WorkflowBox")) {
      return WorkflowBoxView.getSize((WorkflowBox)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("UMLComment")) {
      return UMLCommentView.getSize((UMLComment)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("TextNote")) {
      return TextNoteView.getSize((TextNote)entity);      
    }
    else

    throw new ingenias.exception.InvalidEntity("Entity type "+entity+" is not allowed in this diagram"); 
	    
  }

  public DefaultGraphCell insert(Point point, String entity) throws InvalidEntity {
  // CellView information is not available after creating the cell.

    // Create a Map that holds the attributes for the Vertex
    Map map = new Hashtable();
    // Snap the Point to the Grid
    point = convert(this.snap(new Point(point)));

    // Construct Vertex with no Label
    DefaultGraphCell vertex;
    Dimension size;

    vertex=this.createCell(entity);
    size=this.getDefaultSize((Entity)vertex.getUserObject());



    // Add a Bounds Attribute to the Map
    GraphConstants.setBounds(map, new Rectangle(point, size));

    // Construct a Map from cells to Maps (for insert)
    Hashtable attributes = new Hashtable();
    // Associate the Vertex with its Attributes
    attributes.put(vertex, map);
    // Insert the Vertex and its Attributes
    this.getModel().insert(new Object[] {vertex},attributes
                           , null, null, null);
    return vertex;
  }

  


public DefaultGraphCell insertDuplicated(Point point, ingenias.editor.entities.Entity
                               entity) {
    // CellView information is not available after creating the cell.

    // Create a Map that holds the attributes for the Vertex
    Map map =new Hashtable();
    // Snap the Point to the Grid
    point = convert(this.snap(new Point(point)));
   

    // Construct Vertex with no Label
    DefaultGraphCell vertex = null;
    Dimension size = null;


    if (entity.getClass().equals(InitialNode.class)) {
      vertex = new InitialNodeCell( (InitialNode) entity);
      // Default Size for the new Vertex with the new entity within
      size = InitialNodeView.getSize((InitialNode) entity);
    }
    else

    if (entity.getClass().equals(DecisionNode.class)) {
      vertex = new DecisionNodeCell( (DecisionNode) entity);
      // Default Size for the new Vertex with the new entity within
      size = DecisionNodeView.getSize((DecisionNode) entity);
    }
    else

    if (entity.getClass().equals(ForkNode.class)) {
      vertex = new ForkNodeCell( (ForkNode) entity);
      // Default Size for the new Vertex with the new entity within
      size = ForkNodeView.getSize((ForkNode) entity);
    }
    else

    if (entity.getClass().equals(EndNode.class)) {
      vertex = new EndNodeCell( (EndNode) entity);
      // Default Size for the new Vertex with the new entity within
      size = EndNodeView.getSize((EndNode) entity);
    }
    else

    if (entity.getClass().equals(Task.class)) {
      vertex = new TaskCell( (Task) entity);
      // Default Size for the new Vertex with the new entity within
      size = TaskView.getSize((Task) entity);
    }
    else

    if (entity.getClass().equals(Role.class)) {
      vertex = new RoleCell( (Role) entity);
      // Default Size for the new Vertex with the new entity within
      size = RoleView.getSize((Role) entity);
    }
    else

    if (entity.getClass().equals(WorkflowBox.class)) {
      vertex = new WorkflowBoxCell( (WorkflowBox) entity);
      // Default Size for the new Vertex with the new entity within
      size = WorkflowBoxView.getSize((WorkflowBox) entity);
    }
    else

    if (entity.getClass().equals(UMLComment.class)) {
      vertex = new UMLCommentCell( (UMLComment) entity);
      // Default Size for the new Vertex with the new entity within
      size = UMLCommentView.getSize((UMLComment) entity);
    }
    else

    if (entity.getClass().equals(TextNote.class)) {
      vertex = new TextNoteCell( (TextNote) entity);
      // Default Size for the new Vertex with the new entity within
      size = TextNoteView.getSize((TextNote) entity);
    }
    else

   {}; // Just in case there is no allowed entity in the diagram

    if (vertex == null) {
JOptionPane.showMessageDialog(IDE.ide,
		 "Object not allowed in this diagram "+this.getID()+":"+ 
		 entity.getId()+":"+entity.getClass().getName()+
		 this.getClass().getName(),"Warning", JOptionPane.WARNING_MESSAGE);    }
    else {

      // Add a Bounds Attribute to the Map
      GraphConstants.setBounds(map, new Rectangle(point, size));

      // Construct a Map from cells to Maps (for insert)
      Hashtable attributes = new Hashtable();
      // Associate the Vertex with its Attributes
      attributes.put(vertex, map);
      // Insert the Vertex and its Attributes
      this.getModel().insert(new Object[] {vertex},attributes
                             , null, null, null);
    }
   return vertex;

  }

  public String toString() {
    return this.getID();
  }

}
