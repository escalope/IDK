

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

public class InteractionModelModelJGraph extends ModelJGraph {

  private Preferences prefs;
  
 

  public InteractionModelModelJGraph(InteractionModelDataEntity mde, 
                               String nombre, ObjectManager om, Model
                               m, BasicMarqueeHandler mh, Preferences prefs) {
    super(mde, nombre, m, mh,om);
    this.prefs=prefs;
    
       
                                  ToolTipManager.sharedInstance().registerComponent(this);                
                                  

    this.getGraphLayoutCache().setFactory(new ingenias.editor.cellfactories.InteractionModelCellViewFactory());

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
    toolbar = new FilteredJToolBar("InteractionModel");
    toolbar.setFloatable(false);
    ImageIcon undoIcon = null;
    JButton jb = null;


   if (true){
    Image img_Agent =
        ImageLoader.getImage("images/magent.gif");
    undoIcon = new ImageIcon(img_Agent);
    Action Agent=
        new AbstractAction("Agent", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Agent");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Agent.setEnabled(true);
    jb = new JButton(Agent);
    jb.setText("");
    jb.setName("Agent");	
    jb.setToolTipText("Agent");
    toolbar.add(jb);
    }

   if (true){
    Image img_AgentWS =
        ImageLoader.getImage("images/magentws.gif");
    undoIcon = new ImageIcon(img_AgentWS);
    Action AgentWS=
        new AbstractAction("AgentWS", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "AgentWS");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    AgentWS.setEnabled(true);
    jb = new JButton(AgentWS);
    jb.setText("");
    jb.setName("AgentWS");	
    jb.setToolTipText("AgentWS");
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
    Image img_RoleWS =
        ImageLoader.getImage("images/mrolews.gif");
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
    Image img_TaskWS =
        ImageLoader.getImage("images/mtaskws.gif");
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

   if (true){
    Image img_Goal =
        ImageLoader.getImage("images/mgoal.gif");
    undoIcon = new ImageIcon(img_Goal);
    Action Goal=
        new AbstractAction("Goal", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Goal");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Goal.setEnabled(true);
    jb = new JButton(Goal);
    jb.setText("");
    jb.setName("Goal");	
    jb.setToolTipText("Goal");
    toolbar.add(jb);
    }

   if (true){
    Image img_Interaction =
        ImageLoader.getImage("images/minter.gif");
    undoIcon = new ImageIcon(img_Interaction);
    Action Interaction=
        new AbstractAction("Interaction", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Interaction");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Interaction.setEnabled(true);
    jb = new JButton(Interaction);
    jb.setText("");
    jb.setName("Interaction");	
    jb.setToolTipText("Interaction");
    toolbar.add(jb);
    }

   if (true){
    Image img_IUIterate =
        ImageLoader.getImage("images/mit.gif");
    undoIcon = new ImageIcon(img_IUIterate);
    Action IUIterate=
        new AbstractAction("IUIterate", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "IUIterate");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    IUIterate.setEnabled(true);
    jb = new JButton(IUIterate);
    jb.setText("");
    jb.setName("IUIterate");	
    jb.setToolTipText("IUIterate");
    toolbar.add(jb);
    }

   if (true){
    Image img_IUConcurrence =
        ImageLoader.getImage("images/mconc.gif");
    undoIcon = new ImageIcon(img_IUConcurrence);
    Action IUConcurrence=
        new AbstractAction("IUConcurrence", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "IUConcurrence");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    IUConcurrence.setEnabled(true);
    jb = new JButton(IUConcurrence);
    jb.setText("");
    jb.setName("IUConcurrence");	
    jb.setToolTipText("IUConcurrence");
    toolbar.add(jb);
    }

   if (true){
    Image img_InteractionUnit =
        ImageLoader.getImage("images/miu.gif");
    undoIcon = new ImageIcon(img_InteractionUnit);
    Action InteractionUnit=
        new AbstractAction("InteractionUnit", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "InteractionUnit");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    InteractionUnit.setEnabled(true);
    jb = new JButton(InteractionUnit);
    jb.setText("");
    jb.setName("InteractionUnit");	
    jb.setToolTipText("InteractionUnit");
    toolbar.add(jb);
    }

   if (true){
    Image img_UMLSpecification =
        ImageLoader.getImage("images/mumlspec.gif");
    undoIcon = new ImageIcon(img_UMLSpecification);
    Action UMLSpecification=
        new AbstractAction("UMLSpecification", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "UMLSpecification");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    UMLSpecification.setEnabled(true);
    jb = new JButton(UMLSpecification);
    jb.setText("");
    jb.setName("UMLSpecification");	
    jb.setToolTipText("UMLSpecification");
    toolbar.add(jb);
    }

   if (true){
    Image img_GRASIASpecification =
        ImageLoader.getImage("images/mgspec.gif");
    undoIcon = new ImageIcon(img_GRASIASpecification);
    Action GRASIASpecification=
        new AbstractAction("GRASIASpecification", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "GRASIASpecification");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    GRASIASpecification.setEnabled(true);
    jb = new JButton(GRASIASpecification);
    jb.setText("");
    jb.setName("GRASIASpecification");	
    jb.setToolTipText("GRASIASpecification");
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

   if (false){
    Image img_Conversation =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_Conversation);
    Action Conversation=
        new AbstractAction("Conversation", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Conversation");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Conversation.setEnabled(true);
    jb = new JButton(Conversation);
    jb.setText("");
    jb.setName("Conversation");	
    jb.setToolTipText("Conversation");
    toolbar.add(jb);
    }

   if (false){
    Image img_Fact =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_Fact);
    Action Fact=
        new AbstractAction("Fact", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Fact");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Fact.setEnabled(true);
    jb = new JButton(Fact);
    jb.setText("");
    jb.setName("Fact");	
    jb.setToolTipText("Fact");
    toolbar.add(jb);
    }

   if (false){
    Image img_RuntimeConversation =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_RuntimeConversation);
    Action RuntimeConversation=
        new AbstractAction("RuntimeConversation", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "RuntimeConversation");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    RuntimeConversation.setEnabled(true);
    jb = new JButton(RuntimeConversation);
    jb.setText("");
    jb.setName("RuntimeConversation");	
    jb.setToolTipText("RuntimeConversation");
    toolbar.add(jb);
    }

   if (false){
    Image img_CommunicationEvent =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_CommunicationEvent);
    Action CommunicationEvent=
        new AbstractAction("CommunicationEvent", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "CommunicationEvent");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    CommunicationEvent.setEnabled(true);
    jb = new JButton(CommunicationEvent);
    jb.setText("");
    jb.setName("CommunicationEvent");	
    jb.setToolTipText("CommunicationEvent");
    toolbar.add(jb);
    }

   if (false){
    Image img_ApplicationEvent =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_ApplicationEvent);
    Action ApplicationEvent=
        new AbstractAction("ApplicationEvent", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "ApplicationEvent");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    ApplicationEvent.setEnabled(true);
    jb = new JButton(ApplicationEvent);
    jb.setText("");
    jb.setName("ApplicationEvent");	
    jb.setToolTipText("ApplicationEvent");
    toolbar.add(jb);
    }

   if (false){
    Image img_RemoteProcedureCall =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_RemoteProcedureCall);
    Action RemoteProcedureCall=
        new AbstractAction("RemoteProcedureCall", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "RemoteProcedureCall");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    RemoteProcedureCall.setEnabled(true);
    jb = new JButton(RemoteProcedureCall);
    jb.setText("");
    jb.setName("RemoteProcedureCall");	
    jb.setToolTipText("RemoteProcedureCall");
    toolbar.add(jb);
    }

   if (false){
    Image img_GoalStateWS =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_GoalStateWS);
    Action GoalStateWS=
        new AbstractAction("GoalStateWS", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "GoalStateWS");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    GoalStateWS.setEnabled(true);
    jb = new JButton(GoalStateWS);
    jb.setText("");
    jb.setName("GoalStateWS");	
    jb.setToolTipText("GoalStateWS");
    toolbar.add(jb);
    }

   if (false){
    Image img_FrameFact =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_FrameFact);
    Action FrameFact=
        new AbstractAction("FrameFact", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "FrameFact");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    FrameFact.setEnabled(true);
    jb = new JButton(FrameFact);
    jb.setText("");
    jb.setName("FrameFact");	
    jb.setToolTipText("FrameFact");
    toolbar.add(jb);
    }

   if (false){
    Image img_GeneralEvent =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_GeneralEvent);
    Action GeneralEvent=
        new AbstractAction("GeneralEvent", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "GeneralEvent");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    GeneralEvent.setEnabled(true);
    jb = new JButton(GeneralEvent);
    jb.setText("");
    jb.setName("GeneralEvent");	
    jb.setToolTipText("GeneralEvent");
    toolbar.add(jb);
    }

   if (false){
    Image img_RuntimeEvent =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_RuntimeEvent);
    Action RuntimeEvent=
        new AbstractAction("RuntimeEvent", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "RuntimeEvent");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    RuntimeEvent.setEnabled(true);
    jb = new JButton(RuntimeEvent);
    jb.setText("");
    jb.setName("RuntimeEvent");	
    jb.setToolTipText("RuntimeEvent");
    toolbar.add(jb);
    }

   if (false){
    Image img_StateGoal =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_StateGoal);
    Action StateGoal=
        new AbstractAction("StateGoal", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "StateGoal");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    StateGoal.setEnabled(true);
    jb = new JButton(StateGoal);
    jb.setText("");
    jb.setName("StateGoal");	
    jb.setToolTipText("StateGoal");
    toolbar.add(jb);
    }

   if (false){
    Image img_RuntimeFact =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_RuntimeFact);
    Action RuntimeFact=
        new AbstractAction("RuntimeFact", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "RuntimeFact");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    RuntimeFact.setEnabled(true);
    jb = new JButton(RuntimeFact);
    jb.setText("");
    jb.setName("RuntimeFact");	
    jb.setToolTipText("RuntimeFact");
    toolbar.add(jb);
    }

   if (false){
    Image img_MessagePassing =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_MessagePassing);
    Action MessagePassing=
        new AbstractAction("MessagePassing", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "MessagePassing");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    MessagePassing.setEnabled(true);
    jb = new JButton(MessagePassing);
    jb.setText("");
    jb.setName("MessagePassing");	
    jb.setToolTipText("MessagePassing");
    toolbar.add(jb);
    }

   if (false){
    Image img_ShareTouple =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_ShareTouple);
    Action ShareTouple=
        new AbstractAction("ShareTouple", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "ShareTouple");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    ShareTouple.setEnabled(true);
    jb = new JButton(ShareTouple);
    jb.setText("");
    jb.setName("ShareTouple");	
    jb.setToolTipText("ShareTouple");
    toolbar.add(jb);
    }

   if (false){
    Image img_Believe =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_Believe);
    Action Believe=
        new AbstractAction("Believe", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Believe");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Believe.setEnabled(true);
    jb = new JButton(Believe);
    jb.setText("");
    jb.setName("Believe");	
    jb.setToolTipText("Believe");
    toolbar.add(jb);
    }

   if (false){
    Image img_AgentModelBelieve =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_AgentModelBelieve);
    Action AgentModelBelieve=
        new AbstractAction("AgentModelBelieve", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "AgentModelBelieve");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    AgentModelBelieve.setEnabled(true);
    jb = new JButton(AgentModelBelieve);
    jb.setText("");
    jb.setName("AgentModelBelieve");	
    jb.setToolTipText("AgentModelBelieve");
    toolbar.add(jb);
    }

   if (false){
    Image img_ApplicationEventSlots =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_ApplicationEventSlots);
    Action ApplicationEventSlots=
        new AbstractAction("ApplicationEventSlots", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "ApplicationEventSlots");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    ApplicationEventSlots.setEnabled(true);
    jb = new JButton(ApplicationEventSlots);
    jb.setText("");
    jb.setName("ApplicationEventSlots");	
    jb.setToolTipText("ApplicationEventSlots");
    toolbar.add(jb);
    }

   if (false){
    Image img_Compromise =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_Compromise);
    Action Compromise=
        new AbstractAction("Compromise", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Compromise");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Compromise.setEnabled(true);
    jb = new JButton(Compromise);
    jb.setText("");
    jb.setName("Compromise");	
    jb.setToolTipText("Compromise");
    toolbar.add(jb);
    }

    }

  }

  public Vector<String> getAllowedRelationships(){
   Vector<String> relationships=new   Vector<String>();


          relationships.add("IInitiates");

          relationships.add("IColaborates");

          relationships.add("UIInitiates");

          relationships.add("UIColaborates");

          relationships.add("UISelection");

          relationships.add("IPursues");

          relationships.add("GTPursues");

          relationships.add("IHasSpec");

          relationships.add("UIPrecedes");

          relationships.add("UMLSendsMessage");

          relationships.add("UMLAnnotatedElement");

          relationships.add("IAccesses");

   return relationships;
  }

  public  Vector<String> getAllowedEntities(){
   Vector<String> entities=new   Vector<String>();


 entities.add("Agent");

 entities.add("AgentWS");

 entities.add("Role");

 entities.add("RoleWS");

 entities.add("Task");

 entities.add("TaskWS");

 entities.add("Goal");

 entities.add("Interaction");

 entities.add("IUIterate");

 entities.add("IUConcurrence");

 entities.add("InteractionUnit");

 entities.add("UMLSpecification");

 entities.add("GRASIASpecification");

 entities.add("TextNote");

 entities.add("UMLComment");

 entities.add("Conversation");

 entities.add("Fact");

 entities.add("RuntimeConversation");

 entities.add("CommunicationEvent");

 entities.add("ApplicationEvent");

 entities.add("RemoteProcedureCall");

 entities.add("GoalStateWS");

 entities.add("FrameFact");

 entities.add("GeneralEvent");

 entities.add("RuntimeEvent");

 entities.add("StateGoal");

 entities.add("RuntimeFact");

 entities.add("MessagePassing");

 entities.add("ShareTouple");

 entities.add("Believe");

 entities.add("AgentModelBelieve");

 entities.add("ApplicationEventSlots");

 entities.add("Compromise");

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
        if (IInitiatesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("IInitiates");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (IColaboratesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("IColaborates");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (UIInitiatesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UIInitiates");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (UIColaboratesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UIColaborates");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (UISelectionEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UISelection");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (IPursuesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("IPursues");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (GTPursuesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("GTPursues");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (IHasSpecEdge.acceptConnection(this.getModel(), selected)) {
          v.add("IHasSpec");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (UIPrecedesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UIPrecedes");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (UMLSendsMessageEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UMLSendsMessage");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (UMLAnnotatedElementEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UMLAnnotatedElement");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (IAccessesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("IAccesses");
	  }

      }
      else if (nAryEdgesNum == 1) {

        if (selectedEdge instanceof IInitiatesEdge &&
        (IInitiatesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("IInitiates");
        }

        if (selectedEdge instanceof IColaboratesEdge &&
        (IColaboratesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("IColaborates");
        }

        if (selectedEdge instanceof UIInitiatesEdge &&
        (UIInitiatesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UIInitiates");
        }

        if (selectedEdge instanceof UIColaboratesEdge &&
        (UIColaboratesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UIColaborates");
        }

        if (selectedEdge instanceof UISelectionEdge &&
        (UISelectionEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UISelection");
        }

        if (selectedEdge instanceof IPursuesEdge &&
        (IPursuesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("IPursues");
        }

        if (selectedEdge instanceof GTPursuesEdge &&
        (GTPursuesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("GTPursues");
        }

        if (selectedEdge instanceof IHasSpecEdge &&
        (IHasSpecEdge.acceptConnection(this.getModel(), selected))) {
          v.add("IHasSpec");
        }

        if (selectedEdge instanceof UIPrecedesEdge &&
        (UIPrecedesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UIPrecedes");
        }

        if (selectedEdge instanceof UMLSendsMessageEdge &&
        (UMLSendsMessageEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UMLSendsMessage");
        }

        if (selectedEdge instanceof UMLAnnotatedElementEdge &&
        (UMLAnnotatedElementEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UMLAnnotatedElement");
        }

        if (selectedEdge instanceof IAccessesEdge &&
        (IAccessesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("IAccesses");
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

      if (relacion.equalsIgnoreCase("IInitiates")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof IInitiatesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new IInitiatesEdge(new ingenias.editor.entities.IInitiates(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("IColaborates")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof IColaboratesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new IColaboratesEdge(new ingenias.editor.entities.IColaborates(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("UIInitiates")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof UIInitiatesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new UIInitiatesEdge(new ingenias.editor.entities.UIInitiates(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("UIColaborates")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof UIColaboratesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new UIColaboratesEdge(new ingenias.editor.entities.UIColaborates(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("UISelection")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof UISelectionEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new UISelectionEdge(new ingenias.editor.entities.UISelection(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("IPursues")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof IPursuesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new IPursuesEdge(new ingenias.editor.entities.IPursues(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("GTPursues")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof GTPursuesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new GTPursuesEdge(new ingenias.editor.entities.GTPursues(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("IHasSpec")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof IHasSpecEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new IHasSpecEdge(new ingenias.editor.entities.IHasSpec(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("UIPrecedes")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof UIPrecedesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new UIPrecedesEdge(new ingenias.editor.entities.UIPrecedes(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("UMLSendsMessage")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof UMLSendsMessageEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new UMLSendsMessageEdge(new ingenias.editor.entities.UMLSendsMessage(getMJGraph().getNewId()));
        }
      }

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

      if (relacion.equalsIgnoreCase("IAccesses")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof IAccessesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new IAccessesEdge(new ingenias.editor.entities.IAccesses(getMJGraph().getNewId()));
        }
      }

    }

    return null;
  }
  
  public DefaultGraphCell createCell(String entity) throws InvalidEntity{
  
    if (entity.equalsIgnoreCase("Agent")) {
    Agent nentity=getOM().createAgent(getMJGraph().getNewId("Agent"));
      DefaultGraphCell vertex = new
          AgentCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("AgentWS")) {
    AgentWS nentity=getOM().createAgentWS(getMJGraph().getNewId("AgentWS"));
      DefaultGraphCell vertex = new
          AgentWSCell(nentity);
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

    if (entity.equalsIgnoreCase("RoleWS")) {
    RoleWS nentity=getOM().createRoleWS(getMJGraph().getNewId("RoleWS"));
      DefaultGraphCell vertex = new
          RoleWSCell(nentity);
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

    if (entity.equalsIgnoreCase("TaskWS")) {
    TaskWS nentity=getOM().createTaskWS(getMJGraph().getNewId("TaskWS"));
      DefaultGraphCell vertex = new
          TaskWSCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Goal")) {
    Goal nentity=getOM().createGoal(getMJGraph().getNewId("Goal"));
      DefaultGraphCell vertex = new
          GoalCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Interaction")) {
    Interaction nentity=getOM().createInteraction(getMJGraph().getNewId("Interaction"));
      DefaultGraphCell vertex = new
          InteractionCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("IUIterate")) {
    IUIterate nentity=getOM().createIUIterate(getMJGraph().getNewId("IUIterate"));
      DefaultGraphCell vertex = new
          IUIterateCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("IUConcurrence")) {
    IUConcurrence nentity=getOM().createIUConcurrence(getMJGraph().getNewId("IUConcurrence"));
      DefaultGraphCell vertex = new
          IUConcurrenceCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("InteractionUnit")) {
    InteractionUnit nentity=getOM().createInteractionUnit(getMJGraph().getNewId("InteractionUnit"));
      DefaultGraphCell vertex = new
          InteractionUnitCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("UMLSpecification")) {
    UMLSpecification nentity=getOM().createUMLSpecification(getMJGraph().getNewId("UMLSpecification"));
      DefaultGraphCell vertex = new
          UMLSpecificationCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("GRASIASpecification")) {
    GRASIASpecification nentity=getOM().createGRASIASpecification(getMJGraph().getNewId("GRASIASpecification"));
      DefaultGraphCell vertex = new
          GRASIASpecificationCell(nentity);
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

    if (entity.equalsIgnoreCase("UMLComment")) {
    UMLComment nentity=getOM().createUMLComment(getMJGraph().getNewId("UMLComment"));
      DefaultGraphCell vertex = new
          UMLCommentCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Conversation")) {
    Conversation nentity=getOM().createConversation(getMJGraph().getNewId("Conversation"));
      DefaultGraphCell vertex = new
          ConversationCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Fact")) {
    Fact nentity=getOM().createFact(getMJGraph().getNewId("Fact"));
      DefaultGraphCell vertex = new
          FactCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("RuntimeConversation")) {
    RuntimeConversation nentity=getOM().createRuntimeConversation(getMJGraph().getNewId("RuntimeConversation"));
      DefaultGraphCell vertex = new
          RuntimeConversationCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("CommunicationEvent")) {
    CommunicationEvent nentity=getOM().createCommunicationEvent(getMJGraph().getNewId("CommunicationEvent"));
      DefaultGraphCell vertex = new
          CommunicationEventCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("ApplicationEvent")) {
    ApplicationEvent nentity=getOM().createApplicationEvent(getMJGraph().getNewId("ApplicationEvent"));
      DefaultGraphCell vertex = new
          ApplicationEventCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("RemoteProcedureCall")) {
    RemoteProcedureCall nentity=getOM().createRemoteProcedureCall(getMJGraph().getNewId("RemoteProcedureCall"));
      DefaultGraphCell vertex = new
          RemoteProcedureCallCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("GoalStateWS")) {
    GoalStateWS nentity=getOM().createGoalStateWS(getMJGraph().getNewId("GoalStateWS"));
      DefaultGraphCell vertex = new
          GoalStateWSCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("FrameFact")) {
    FrameFact nentity=getOM().createFrameFact(getMJGraph().getNewId("FrameFact"));
      DefaultGraphCell vertex = new
          FrameFactCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("GeneralEvent")) {
    GeneralEvent nentity=getOM().createGeneralEvent(getMJGraph().getNewId("GeneralEvent"));
      DefaultGraphCell vertex = new
          GeneralEventCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("RuntimeEvent")) {
    RuntimeEvent nentity=getOM().createRuntimeEvent(getMJGraph().getNewId("RuntimeEvent"));
      DefaultGraphCell vertex = new
          RuntimeEventCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("StateGoal")) {
    StateGoal nentity=getOM().createStateGoal(getMJGraph().getNewId("StateGoal"));
      DefaultGraphCell vertex = new
          StateGoalCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("RuntimeFact")) {
    RuntimeFact nentity=getOM().createRuntimeFact(getMJGraph().getNewId("RuntimeFact"));
      DefaultGraphCell vertex = new
          RuntimeFactCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("MessagePassing")) {
    MessagePassing nentity=getOM().createMessagePassing(getMJGraph().getNewId("MessagePassing"));
      DefaultGraphCell vertex = new
          MessagePassingCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("ShareTouple")) {
    ShareTouple nentity=getOM().createShareTouple(getMJGraph().getNewId("ShareTouple"));
      DefaultGraphCell vertex = new
          ShareToupleCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Believe")) {
    Believe nentity=getOM().createBelieve(getMJGraph().getNewId("Believe"));
      DefaultGraphCell vertex = new
          BelieveCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("AgentModelBelieve")) {
    AgentModelBelieve nentity=getOM().createAgentModelBelieve(getMJGraph().getNewId("AgentModelBelieve"));
      DefaultGraphCell vertex = new
          AgentModelBelieveCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("ApplicationEventSlots")) {
    ApplicationEventSlots nentity=getOM().createApplicationEventSlots(getMJGraph().getNewId("ApplicationEventSlots"));
      DefaultGraphCell vertex = new
          ApplicationEventSlotsCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Compromise")) {
    Compromise nentity=getOM().createCompromise(getMJGraph().getNewId("Compromise"));
      DefaultGraphCell vertex = new
          CompromiseCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

	  throw new ingenias.exception.InvalidEntity("Entity type "+entity+" is not allowed in this diagram"); 
  }
  
  public Dimension getDefaultSize(Entity entity) throws InvalidEntity{
    
    if (entity.getType().equalsIgnoreCase("Agent")) {
      return AgentView.getSize((ingenias.editor.entities.Agent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AgentWS")) {
      return AgentWSView.getSize((ingenias.editor.entities.AgentWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Role")) {
      return RoleView.getSize((ingenias.editor.entities.Role)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RoleWS")) {
      return RoleWSView.getSize((ingenias.editor.entities.RoleWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Task")) {
      return TaskView.getSize((ingenias.editor.entities.Task)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("TaskWS")) {
      return TaskWSView.getSize((ingenias.editor.entities.TaskWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Goal")) {
      return GoalView.getSize((ingenias.editor.entities.Goal)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Interaction")) {
      return InteractionView.getSize((ingenias.editor.entities.Interaction)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("IUIterate")) {
      return IUIterateView.getSize((ingenias.editor.entities.IUIterate)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("IUConcurrence")) {
      return IUConcurrenceView.getSize((ingenias.editor.entities.IUConcurrence)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("InteractionUnit")) {
      return InteractionUnitView.getSize((ingenias.editor.entities.InteractionUnit)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("UMLSpecification")) {
      return UMLSpecificationView.getSize((ingenias.editor.entities.UMLSpecification)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("GRASIASpecification")) {
      return GRASIASpecificationView.getSize((ingenias.editor.entities.GRASIASpecification)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("TextNote")) {
      return TextNoteView.getSize((ingenias.editor.entities.TextNote)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("UMLComment")) {
      return UMLCommentView.getSize((ingenias.editor.entities.UMLComment)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Conversation")) {
      return ConversationView.getSize((ingenias.editor.entities.Conversation)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Fact")) {
      return FactView.getSize((ingenias.editor.entities.Fact)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RuntimeConversation")) {
      return RuntimeConversationView.getSize((ingenias.editor.entities.RuntimeConversation)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("CommunicationEvent")) {
      return CommunicationEventView.getSize((ingenias.editor.entities.CommunicationEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ApplicationEvent")) {
      return ApplicationEventView.getSize((ingenias.editor.entities.ApplicationEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RemoteProcedureCall")) {
      return RemoteProcedureCallView.getSize((ingenias.editor.entities.RemoteProcedureCall)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("GoalStateWS")) {
      return GoalStateWSView.getSize((ingenias.editor.entities.GoalStateWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FrameFact")) {
      return FrameFactView.getSize((ingenias.editor.entities.FrameFact)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("GeneralEvent")) {
      return GeneralEventView.getSize((ingenias.editor.entities.GeneralEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RuntimeEvent")) {
      return RuntimeEventView.getSize((ingenias.editor.entities.RuntimeEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("StateGoal")) {
      return StateGoalView.getSize((ingenias.editor.entities.StateGoal)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RuntimeFact")) {
      return RuntimeFactView.getSize((ingenias.editor.entities.RuntimeFact)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("MessagePassing")) {
      return MessagePassingView.getSize((ingenias.editor.entities.MessagePassing)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ShareTouple")) {
      return ShareToupleView.getSize((ingenias.editor.entities.ShareTouple)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Believe")) {
      return BelieveView.getSize((ingenias.editor.entities.Believe)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AgentModelBelieve")) {
      return AgentModelBelieveView.getSize((ingenias.editor.entities.AgentModelBelieve)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ApplicationEventSlots")) {
      return ApplicationEventSlotsView.getSize((ingenias.editor.entities.ApplicationEventSlots)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Compromise")) {
      return CompromiseView.getSize((ingenias.editor.entities.Compromise)entity);      
    }
    else


      if (entity.getType().equalsIgnoreCase("IInitiates")) {
      	return IInitiatesView.getSize((ingenias.editor.entities.IInitiates)entity);
      }

      if (entity.getType().equalsIgnoreCase("IColaborates")) {
      	return IColaboratesView.getSize((ingenias.editor.entities.IColaborates)entity);
      }

      if (entity.getType().equalsIgnoreCase("UIInitiates")) {
      	return UIInitiatesView.getSize((ingenias.editor.entities.UIInitiates)entity);
      }

      if (entity.getType().equalsIgnoreCase("UIColaborates")) {
      	return UIColaboratesView.getSize((ingenias.editor.entities.UIColaborates)entity);
      }

      if (entity.getType().equalsIgnoreCase("UISelection")) {
      	return UISelectionView.getSize((ingenias.editor.entities.UISelection)entity);
      }

      if (entity.getType().equalsIgnoreCase("IPursues")) {
      	return IPursuesView.getSize((ingenias.editor.entities.IPursues)entity);
      }

      if (entity.getType().equalsIgnoreCase("GTPursues")) {
      	return GTPursuesView.getSize((ingenias.editor.entities.GTPursues)entity);
      }

      if (entity.getType().equalsIgnoreCase("IHasSpec")) {
      	return IHasSpecView.getSize((ingenias.editor.entities.IHasSpec)entity);
      }

      if (entity.getType().equalsIgnoreCase("UIPrecedes")) {
      	return UIPrecedesView.getSize((ingenias.editor.entities.UIPrecedes)entity);
      }

      if (entity.getType().equalsIgnoreCase("UMLSendsMessage")) {
      	return UMLSendsMessageView.getSize((ingenias.editor.entities.UMLSendsMessage)entity);
      }

      if (entity.getType().equalsIgnoreCase("UMLAnnotatedElement")) {
      	return UMLAnnotatedElementView.getSize((ingenias.editor.entities.UMLAnnotatedElement)entity);
      }

      if (entity.getType().equalsIgnoreCase("IAccesses")) {
      	return IAccessesView.getSize((ingenias.editor.entities.IAccesses)entity);
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


    if (entity.getClass().equals(Agent.class)) {
      vertex = new AgentCell( (Agent) entity);
      // Default Size for the new Vertex with the new entity within
      size = AgentView.getSize((Agent) entity);
      
    }
    else

    if (entity.getClass().equals(AgentWS.class)) {
      vertex = new AgentWSCell( (AgentWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = AgentWSView.getSize((AgentWS) entity);
      
    }
    else

    if (entity.getClass().equals(Role.class)) {
      vertex = new RoleCell( (Role) entity);
      // Default Size for the new Vertex with the new entity within
      size = RoleView.getSize((Role) entity);
      
    }
    else

    if (entity.getClass().equals(RoleWS.class)) {
      vertex = new RoleWSCell( (RoleWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = RoleWSView.getSize((RoleWS) entity);
      
    }
    else

    if (entity.getClass().equals(Task.class)) {
      vertex = new TaskCell( (Task) entity);
      // Default Size for the new Vertex with the new entity within
      size = TaskView.getSize((Task) entity);
      
    }
    else

    if (entity.getClass().equals(TaskWS.class)) {
      vertex = new TaskWSCell( (TaskWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = TaskWSView.getSize((TaskWS) entity);
      
    }
    else

    if (entity.getClass().equals(Goal.class)) {
      vertex = new GoalCell( (Goal) entity);
      // Default Size for the new Vertex with the new entity within
      size = GoalView.getSize((Goal) entity);
      
    }
    else

    if (entity.getClass().equals(Interaction.class)) {
      vertex = new InteractionCell( (Interaction) entity);
      // Default Size for the new Vertex with the new entity within
      size = InteractionView.getSize((Interaction) entity);
      
    }
    else

    if (entity.getClass().equals(IUIterate.class)) {
      vertex = new IUIterateCell( (IUIterate) entity);
      // Default Size for the new Vertex with the new entity within
      size = IUIterateView.getSize((IUIterate) entity);
      
    }
    else

    if (entity.getClass().equals(IUConcurrence.class)) {
      vertex = new IUConcurrenceCell( (IUConcurrence) entity);
      // Default Size for the new Vertex with the new entity within
      size = IUConcurrenceView.getSize((IUConcurrence) entity);
      
    }
    else

    if (entity.getClass().equals(InteractionUnit.class)) {
      vertex = new InteractionUnitCell( (InteractionUnit) entity);
      // Default Size for the new Vertex with the new entity within
      size = InteractionUnitView.getSize((InteractionUnit) entity);
      
    }
    else

    if (entity.getClass().equals(UMLSpecification.class)) {
      vertex = new UMLSpecificationCell( (UMLSpecification) entity);
      // Default Size for the new Vertex with the new entity within
      size = UMLSpecificationView.getSize((UMLSpecification) entity);
      
    }
    else

    if (entity.getClass().equals(GRASIASpecification.class)) {
      vertex = new GRASIASpecificationCell( (GRASIASpecification) entity);
      // Default Size for the new Vertex with the new entity within
      size = GRASIASpecificationView.getSize((GRASIASpecification) entity);
      
    }
    else

    if (entity.getClass().equals(TextNote.class)) {
      vertex = new TextNoteCell( (TextNote) entity);
      // Default Size for the new Vertex with the new entity within
      size = TextNoteView.getSize((TextNote) entity);
      
    }
    else

    if (entity.getClass().equals(UMLComment.class)) {
      vertex = new UMLCommentCell( (UMLComment) entity);
      // Default Size for the new Vertex with the new entity within
      size = UMLCommentView.getSize((UMLComment) entity);
      
    }
    else

    if (entity.getClass().equals(Conversation.class)) {
      vertex = new ConversationCell( (Conversation) entity);
      // Default Size for the new Vertex with the new entity within
      size = ConversationView.getSize((Conversation) entity);
      
    }
    else

    if (entity.getClass().equals(Fact.class)) {
      vertex = new FactCell( (Fact) entity);
      // Default Size for the new Vertex with the new entity within
      size = FactView.getSize((Fact) entity);
      
    }
    else

    if (entity.getClass().equals(RuntimeConversation.class)) {
      vertex = new RuntimeConversationCell( (RuntimeConversation) entity);
      // Default Size for the new Vertex with the new entity within
      size = RuntimeConversationView.getSize((RuntimeConversation) entity);
      
    }
    else

    if (entity.getClass().equals(CommunicationEvent.class)) {
      vertex = new CommunicationEventCell( (CommunicationEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = CommunicationEventView.getSize((CommunicationEvent) entity);
      
    }
    else

    if (entity.getClass().equals(ApplicationEvent.class)) {
      vertex = new ApplicationEventCell( (ApplicationEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationEventView.getSize((ApplicationEvent) entity);
      
    }
    else

    if (entity.getClass().equals(RemoteProcedureCall.class)) {
      vertex = new RemoteProcedureCallCell( (RemoteProcedureCall) entity);
      // Default Size for the new Vertex with the new entity within
      size = RemoteProcedureCallView.getSize((RemoteProcedureCall) entity);
      
    }
    else

    if (entity.getClass().equals(GoalStateWS.class)) {
      vertex = new GoalStateWSCell( (GoalStateWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = GoalStateWSView.getSize((GoalStateWS) entity);
      
    }
    else

    if (entity.getClass().equals(FrameFact.class)) {
      vertex = new FrameFactCell( (FrameFact) entity);
      // Default Size for the new Vertex with the new entity within
      size = FrameFactView.getSize((FrameFact) entity);
      
    }
    else

    if (entity.getClass().equals(GeneralEvent.class)) {
      vertex = new GeneralEventCell( (GeneralEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = GeneralEventView.getSize((GeneralEvent) entity);
      
    }
    else

    if (entity.getClass().equals(RuntimeEvent.class)) {
      vertex = new RuntimeEventCell( (RuntimeEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = RuntimeEventView.getSize((RuntimeEvent) entity);
      
    }
    else

    if (entity.getClass().equals(StateGoal.class)) {
      vertex = new StateGoalCell( (StateGoal) entity);
      // Default Size for the new Vertex with the new entity within
      size = StateGoalView.getSize((StateGoal) entity);
      
    }
    else

    if (entity.getClass().equals(RuntimeFact.class)) {
      vertex = new RuntimeFactCell( (RuntimeFact) entity);
      // Default Size for the new Vertex with the new entity within
      size = RuntimeFactView.getSize((RuntimeFact) entity);
      
    }
    else

    if (entity.getClass().equals(MessagePassing.class)) {
      vertex = new MessagePassingCell( (MessagePassing) entity);
      // Default Size for the new Vertex with the new entity within
      size = MessagePassingView.getSize((MessagePassing) entity);
      
    }
    else

    if (entity.getClass().equals(ShareTouple.class)) {
      vertex = new ShareToupleCell( (ShareTouple) entity);
      // Default Size for the new Vertex with the new entity within
      size = ShareToupleView.getSize((ShareTouple) entity);
      
    }
    else

    if (entity.getClass().equals(Believe.class)) {
      vertex = new BelieveCell( (Believe) entity);
      // Default Size for the new Vertex with the new entity within
      size = BelieveView.getSize((Believe) entity);
      
    }
    else

    if (entity.getClass().equals(AgentModelBelieve.class)) {
      vertex = new AgentModelBelieveCell( (AgentModelBelieve) entity);
      // Default Size for the new Vertex with the new entity within
      size = AgentModelBelieveView.getSize((AgentModelBelieve) entity);
      
    }
    else

    if (entity.getClass().equals(ApplicationEventSlots.class)) {
      vertex = new ApplicationEventSlotsCell( (ApplicationEventSlots) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationEventSlotsView.getSize((ApplicationEventSlots) entity);
      
    }
    else

    if (entity.getClass().equals(Compromise.class)) {
      vertex = new CompromiseCell( (Compromise) entity);
      // Default Size for the new Vertex with the new entity within
      size = CompromiseView.getSize((Compromise) entity);
      
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

		

		 InteractionModelModelJGraph jg=new  InteractionModelModelJGraph(
				(InteractionModelDataEntity) this.mde,name, ids.om,
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
