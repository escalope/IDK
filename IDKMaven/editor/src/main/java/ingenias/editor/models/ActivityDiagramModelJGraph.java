

/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz over original code from Ruben Fuentes and Juan Pavon
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
 

package ingenias.editor.models;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.Map;
import java.util.Hashtable;
import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;


import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;
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
import ingenias.editor.rendererxml.*;
import ingenias.editor.events.*;
import ingenias.exception.InvalidEntity;
import ingenias.editor.*;

public class ActivityDiagramModelJGraph extends ModelJGraph {

  private Preferences prefs;
  
 

  public ActivityDiagramModelJGraph(ActivityDiagramDataEntity mde, 
                               String nombre, ObjectManager om, Model
                               m, BasicMarqueeHandler mh, Preferences prefs) {
    super(mde, nombre, m, mh,om);
    this.prefs=prefs;
    
       
                                  ToolTipManager.sharedInstance().registerComponent(this);                
                                  

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
    if (toolbar==null){
    toolbar = new FilteredJToolBar("ActivityDiagram");
    toolbar.setFloatable(false);
    ImageIcon undoIcon = null;
    JButton jb = null;


   if (true){
    Image img_InitialNode =
        ImageLoader.getImage("images/minitialnode.gif");
    undoIcon = new ImageIcon(img_InitialNode);
    Action InitialNode=
        new AbstractAction("InitialNode", undoIcon) {
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
    jb.setText("");
    jb.setName("InitialNode");	
    jb.setToolTipText("InitialNode");
    toolbar.add(jb);
    }

   if (true){
    Image img_DecisionNode =
        ImageLoader.getImage("images/mdecisionnode.gif");
    undoIcon = new ImageIcon(img_DecisionNode);
    Action DecisionNode=
        new AbstractAction("DecisionNode", undoIcon) {
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
    jb.setText("");
    jb.setName("DecisionNode");	
    jb.setToolTipText("DecisionNode");
    toolbar.add(jb);
    }

   if (true){
    Image img_ForkNode =
        ImageLoader.getImage("images/mforknode.gif");
    undoIcon = new ImageIcon(img_ForkNode);
    Action ForkNode=
        new AbstractAction("ForkNode", undoIcon) {
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
    jb.setText("");
    jb.setName("ForkNode");	
    jb.setToolTipText("ForkNode");
    toolbar.add(jb);
    }

   if (true){
    Image img_EndNode =
        ImageLoader.getImage("images/mendnode.gif");
    undoIcon = new ImageIcon(img_EndNode);
    Action EndNode=
        new AbstractAction("EndNode", undoIcon) {
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
    jb.setText("");
    jb.setName("EndNode");	
    jb.setToolTipText("EndNode");
    toolbar.add(jb);
    }

   if (true){
    Image img_Task =
        ImageLoader.getImage("images/mtask.gif");
    undoIcon = new ImageIcon(img_Task);
    Action Task=
        new AbstractAction("Task", undoIcon) {
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
    jb.setText("");
    jb.setName("Task");	
    jb.setToolTipText("Task");
    toolbar.add(jb);
    }

   if (true){
    Image img_Role =
        ImageLoader.getImage("images/mrole.gif");
    undoIcon = new ImageIcon(img_Role);
    Action Role=
        new AbstractAction("Role", undoIcon) {
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
    jb.setText("");
    jb.setName("Role");	
    jb.setToolTipText("Role");
    toolbar.add(jb);
    }

   if (true){
    Image img_WorkflowBox =
        ImageLoader.getImage("images/mworkflow.gif");
    undoIcon = new ImageIcon(img_WorkflowBox);
    Action WorkflowBox=
        new AbstractAction("WorkflowBox", undoIcon) {
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
    jb.setText("");
    jb.setName("WorkflowBox");	
    jb.setToolTipText("WorkflowBox");
    toolbar.add(jb);
    }

   if (true){
    Image img_UMLComment =
        ImageLoader.getImage("images/mumlcomment.gif");
    undoIcon = new ImageIcon(img_UMLComment);
    Action UMLComment=
        new AbstractAction("UMLComment", undoIcon) {
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
    jb.setText("");
    jb.setName("UMLComment");	
    jb.setToolTipText("UMLComment");
    toolbar.add(jb);
    }

   if (true){
    Image img_TextNote =
        ImageLoader.getImage("images/mtext.gif");
    undoIcon = new ImageIcon(img_TextNote);
    Action TextNote=
        new AbstractAction("TextNote", undoIcon) {
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
    jb.setText("");
    jb.setName("TextNote");	
    jb.setToolTipText("TextNote");
    toolbar.add(jb);
    }

   if (false){
    Image img_TaskWS =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_TaskWS);
    Action TaskWS=
        new AbstractAction("TaskWS", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "TaskWS");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    TaskWS.setEnabled(true);
    jb = new JButton(TaskWS);
    jb.setText("");
    jb.setName("TaskWS");	
    jb.setToolTipText("TaskWS");
    toolbar.add(jb);
    }

   if (false){
    Image img_Workflow =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_Workflow);
    Action Workflow=
        new AbstractAction("Workflow", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Workflow");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Workflow.setEnabled(true);
    jb = new JButton(Workflow);
    jb.setText("");
    jb.setName("Workflow");	
    jb.setToolTipText("Workflow");
    toolbar.add(jb);
    }

   if (false){
    Image img_BoxedTask =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_BoxedTask);
    Action BoxedTask=
        new AbstractAction("BoxedTask", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "BoxedTask");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    BoxedTask.setEnabled(true);
    jb = new JButton(BoxedTask);
    jb.setText("");
    jb.setName("BoxedTask");	
    jb.setToolTipText("BoxedTask");
    toolbar.add(jb);
    }

   if (false){
    Image img_RoleWS =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_RoleWS);
    Action RoleWS=
        new AbstractAction("RoleWS", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "RoleWS");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    RoleWS.setEnabled(true);
    jb = new JButton(RoleWS);
    jb.setText("");
    jb.setName("RoleWS");	
    jb.setToolTipText("RoleWS");
    toolbar.add(jb);
    }

   if (false){
    Image img_ContextBindingTask =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_ContextBindingTask);
    Action ContextBindingTask=
        new AbstractAction("ContextBindingTask", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "ContextBindingTask");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    ContextBindingTask.setEnabled(true);
    jb = new JButton(ContextBindingTask);
    jb.setText("");
    jb.setName("ContextBindingTask");	
    jb.setToolTipText("ContextBindingTask");
    toolbar.add(jb);
    }

   if (false){
    Image img_ContextReleaseTask =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_ContextReleaseTask);
    Action ContextReleaseTask=
        new AbstractAction("ContextReleaseTask", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "ContextReleaseTask");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    ContextReleaseTask.setEnabled(true);
    jb = new JButton(ContextReleaseTask);
    jb.setText("");
    jb.setName("ContextReleaseTask");	
    jb.setToolTipText("ContextReleaseTask");
    toolbar.add(jb);
    }

   if (false){
    Image img_Plan =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_Plan);
    Action Plan=
        new AbstractAction("Plan", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Plan");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Plan.setEnabled(true);
    jb = new JButton(Plan);
    jb.setText("");
    jb.setName("Plan");	
    jb.setToolTipText("Plan");
    toolbar.add(jb);
    }

    }

  }

  public Vector<String> getAllowedRelationships(){
   Vector<String> relationships=new   Vector<String>();


          relationships.add("UMLAnnotatedElement");

          relationships.add("WFStarts");

          relationships.add("WFEnds");

          relationships.add("WFDecides");

          relationships.add("WFFollows");

          relationships.add("WFFollowsGuarded");

   return relationships;
  }

  public  Vector<String> getAllowedEntities(){
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

 entities.add("TaskWS");

 entities.add("Workflow");

 entities.add("BoxedTask");

 entities.add("RoleWS");

 entities.add("ContextBindingTask");

 entities.add("ContextReleaseTask");

 entities.add("Plan");

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
          return new UMLAnnotatedElementEdge(new ingenias.editor.entities.UMLAnnotatedElement(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFStarts")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFStartsEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFStartsEdge(new ingenias.editor.entities.WFStarts(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFEnds")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFEndsEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFEndsEdge(new ingenias.editor.entities.WFEnds(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFDecides")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFDecidesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFDecidesEdge(new ingenias.editor.entities.WFDecides(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFFollows")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFFollowsEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFFollowsEdge(new ingenias.editor.entities.WFFollows(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFFollowsGuarded")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFFollowsGuardedEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFFollowsGuardedEdge(new ingenias.editor.entities.WFFollowsGuarded(getMJGraph().getNewId()));
        }
      }

    }

    return null;
  }
  
  public DefaultGraphCell createCell(String entity) throws InvalidEntity{
  
    if (entity.equalsIgnoreCase("InitialNode")) {
    InitialNode nentity=getOM().createInitialNode(getMJGraph().getNewId("InitialNode"));
      DefaultGraphCell vertex = new
          InitialNodeCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("DecisionNode")) {
    DecisionNode nentity=getOM().createDecisionNode(getMJGraph().getNewId("DecisionNode"));
      DefaultGraphCell vertex = new
          DecisionNodeCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("ForkNode")) {
    ForkNode nentity=getOM().createForkNode(getMJGraph().getNewId("ForkNode"));
      DefaultGraphCell vertex = new
          ForkNodeCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("EndNode")) {
    EndNode nentity=getOM().createEndNode(getMJGraph().getNewId("EndNode"));
      DefaultGraphCell vertex = new
          EndNodeCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Task")) {
    Task nentity=getOM().createTask(getMJGraph().getNewId("Task"));
      DefaultGraphCell vertex = new
          TaskCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Role")) {
    Role nentity=getOM().createRole(getMJGraph().getNewId("Role"));
      DefaultGraphCell vertex = new
          RoleCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("WorkflowBox")) {
    WorkflowBox nentity=getOM().createWorkflowBox(getMJGraph().getNewId("WorkflowBox"));
      DefaultGraphCell vertex = new
          WorkflowBoxCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("UMLComment")) {
    UMLComment nentity=getOM().createUMLComment(getMJGraph().getNewId("UMLComment"));
      DefaultGraphCell vertex = new
          UMLCommentCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("TextNote")) {
    TextNote nentity=getOM().createTextNote(getMJGraph().getNewId("TextNote"));
      DefaultGraphCell vertex = new
          TextNoteCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("TaskWS")) {
    TaskWS nentity=getOM().createTaskWS(getMJGraph().getNewId("TaskWS"));
      DefaultGraphCell vertex = new
          TaskWSCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Workflow")) {
    Workflow nentity=getOM().createWorkflow(getMJGraph().getNewId("Workflow"));
      DefaultGraphCell vertex = new
          WorkflowCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("BoxedTask")) {
    BoxedTask nentity=getOM().createBoxedTask(getMJGraph().getNewId("BoxedTask"));
      DefaultGraphCell vertex = new
          BoxedTaskCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("RoleWS")) {
    RoleWS nentity=getOM().createRoleWS(getMJGraph().getNewId("RoleWS"));
      DefaultGraphCell vertex = new
          RoleWSCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("ContextBindingTask")) {
    ContextBindingTask nentity=getOM().createContextBindingTask(getMJGraph().getNewId("ContextBindingTask"));
      DefaultGraphCell vertex = new
          ContextBindingTaskCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("ContextReleaseTask")) {
    ContextReleaseTask nentity=getOM().createContextReleaseTask(getMJGraph().getNewId("ContextReleaseTask"));
      DefaultGraphCell vertex = new
          ContextReleaseTaskCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Plan")) {
    Plan nentity=getOM().createPlan(getMJGraph().getNewId("Plan"));
      DefaultGraphCell vertex = new
          PlanCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

	  throw new ingenias.exception.InvalidEntity("Entity type "+entity+" is not allowed in this diagram"); 
  }
  
  public Dimension getDefaultSize(Entity entity) throws InvalidEntity{
    
    if (entity.getType().equalsIgnoreCase("InitialNode")) {
      return InitialNodeView.getSize((ingenias.editor.entities.InitialNode)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("DecisionNode")) {
      return DecisionNodeView.getSize((ingenias.editor.entities.DecisionNode)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ForkNode")) {
      return ForkNodeView.getSize((ingenias.editor.entities.ForkNode)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("EndNode")) {
      return EndNodeView.getSize((ingenias.editor.entities.EndNode)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Task")) {
      return TaskView.getSize((ingenias.editor.entities.Task)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Role")) {
      return RoleView.getSize((ingenias.editor.entities.Role)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("WorkflowBox")) {
      return WorkflowBoxView.getSize((ingenias.editor.entities.WorkflowBox)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("UMLComment")) {
      return UMLCommentView.getSize((ingenias.editor.entities.UMLComment)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("TextNote")) {
      return TextNoteView.getSize((ingenias.editor.entities.TextNote)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("TaskWS")) {
      return TaskWSView.getSize((ingenias.editor.entities.TaskWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Workflow")) {
      return WorkflowView.getSize((ingenias.editor.entities.Workflow)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("BoxedTask")) {
      return BoxedTaskView.getSize((ingenias.editor.entities.BoxedTask)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RoleWS")) {
      return RoleWSView.getSize((ingenias.editor.entities.RoleWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ContextBindingTask")) {
      return ContextBindingTaskView.getSize((ingenias.editor.entities.ContextBindingTask)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ContextReleaseTask")) {
      return ContextReleaseTaskView.getSize((ingenias.editor.entities.ContextReleaseTask)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Plan")) {
      return PlanView.getSize((ingenias.editor.entities.Plan)entity);      
    }
    else


      if (entity.getType().equalsIgnoreCase("UMLAnnotatedElement")) {
      	return UMLAnnotatedElementView.getSize((ingenias.editor.entities.UMLAnnotatedElement)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFStarts")) {
      	return WFStartsView.getSize((ingenias.editor.entities.WFStarts)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFEnds")) {
      	return WFEndsView.getSize((ingenias.editor.entities.WFEnds)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFDecides")) {
      	return WFDecidesView.getSize((ingenias.editor.entities.WFDecides)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFFollows")) {
      	return WFFollowsView.getSize((ingenias.editor.entities.WFFollows)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFFollowsGuarded")) {
      	return WFFollowsGuardedView.getSize((ingenias.editor.entities.WFFollowsGuarded)entity);
      }

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

	Entity newEntity=(Entity) vertex.getUserObject();
	if (prefs.getModelingLanguage()==Preferences.ModelingLanguage.UML)
		newEntity.getPrefs(null).setView(ViewPreferences.ViewType.UML);
	if (prefs.getModelingLanguage()==Preferences.ModelingLanguage.INGENIAS)
		newEntity.getPrefs(null).setView(ViewPreferences.ViewType.INGENIAS);

	getGraphLayoutCache().setVisible(vertex,true);// makes the cell visible because
      // the graphlayoutcache has partial set to true

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

    if (entity.getClass().equals(TaskWS.class)) {
      vertex = new TaskWSCell( (TaskWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = TaskWSView.getSize((TaskWS) entity);
      
    }
    else

    if (entity.getClass().equals(Workflow.class)) {
      vertex = new WorkflowCell( (Workflow) entity);
      // Default Size for the new Vertex with the new entity within
      size = WorkflowView.getSize((Workflow) entity);
      
    }
    else

    if (entity.getClass().equals(BoxedTask.class)) {
      vertex = new BoxedTaskCell( (BoxedTask) entity);
      // Default Size for the new Vertex with the new entity within
      size = BoxedTaskView.getSize((BoxedTask) entity);
      
    }
    else

    if (entity.getClass().equals(RoleWS.class)) {
      vertex = new RoleWSCell( (RoleWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = RoleWSView.getSize((RoleWS) entity);
      
    }
    else

    if (entity.getClass().equals(ContextBindingTask.class)) {
      vertex = new ContextBindingTaskCell( (ContextBindingTask) entity);
      // Default Size for the new Vertex with the new entity within
      size = ContextBindingTaskView.getSize((ContextBindingTask) entity);
      
    }
    else

    if (entity.getClass().equals(ContextReleaseTask.class)) {
      vertex = new ContextReleaseTaskCell( (ContextReleaseTask) entity);
      // Default Size for the new Vertex with the new entity within
      size = ContextReleaseTaskView.getSize((ContextReleaseTask) entity);
      
    }
    else

    if (entity.getClass().equals(Plan.class)) {
      vertex = new PlanCell( (Plan) entity);
      // Default Size for the new Vertex with the new entity within
      size = PlanView.getSize((Plan) entity);
      
    }
    else

   {}; // Just in case there is no allowed entity in the diagram

    if (vertex == null) {
JOptionPane.showMessageDialog(this,
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
      getGraphLayoutCache().setVisible(vertex,true);// makes the cell visible because
      // the graphlayoutcache has partial set to true  
        // waits for the cellview to be created
		boolean created=false;
		VertexView vv=null;
		while (!created){
			CellView[] cellviews = this.getGraphLayoutCache().getCellViews();
			for (CellView cv:cellviews){
				if (cv.getCell()==vertex){
					// created!
					created=true;
					vv=(VertexView)cv;
				}
			}
			System.err.println("waiting");
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// This should be used when the corresponding view for the entity has containers!
		// It can be known by inspecting its renderer. To get it, a cellview is needed,
		// but the cellview is created after a while via succesive callbacks to
		// the view factories

		if (!ListenerContainer.evaluate((CompositeRenderer) vv.getRenderer(),entity,null).isEmpty()){
			// there are container renderers that need new cells corresponding to children to
			// be inserted

			Hashtable<String, CollectionPanel> renderers = ListenerContainer.evaluate((CompositeRenderer) vv.getRenderer(),entity,null);
			for (String field:renderers.keySet()){
				Method obtainenumeration;
				try {
					obtainenumeration = entity.getClass().getMethod("get"+field+"Elements");
					Enumeration<ingenias.editor.entities.Entity>  enom=(Enumeration<ingenias.editor.entities.Entity>) obtainenumeration.invoke(entity,new Object[]{});
					while (enom.hasMoreElements()){
						DefaultGraphCell child=this.insertDuplicated(new Point(40,10), enom.nextElement());
						getListenerContainer().setParent(child,vertex);
					}
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
                             
    }
   return vertex;

  }


 public synchronized JGraph cloneJGraph(IDEState ids){

		

		 ActivityDiagramModelJGraph jg=new  ActivityDiagramModelJGraph(
				(ActivityDiagramDataEntity) this.mde,name, ids.om,
				new Model(ids),new BasicMarqueeHandler(),ids.prefs); 

		this.setSelectionCells(getGraphLayoutCache().getCells(false,true,false,false));
		Action copyaction =new EventRedirectorForGraphCopy(this,this.getTransferHandler().getCopyAction(),null	); 			
		Action pasteaction =new EventRedirectorPasteForGraphCopy(jg,jg.getTransferHandler().getPasteAction(),null	);
		copyaction.actionPerformed(new ActionEvent(this,0,"hello"));		
		pasteaction.actionPerformed(new ActionEvent(this,0,"hello"));
		jg.invalidate();
		jg.doLayout();
		
		return jg;

	}


  public String toString() {
    return this.getID();
  }

}
