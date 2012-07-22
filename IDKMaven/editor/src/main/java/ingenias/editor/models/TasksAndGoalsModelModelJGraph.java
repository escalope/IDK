

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

public class TasksAndGoalsModelModelJGraph extends ModelJGraph {

  private Preferences prefs;
  
 

  public TasksAndGoalsModelModelJGraph(TasksAndGoalsModelDataEntity mde, 
                               String nombre, ObjectManager om, Model
                               m, BasicMarqueeHandler mh, Preferences prefs) {
    super(mde, nombre, m, mh,om);
    this.prefs=prefs;
    
       
                                  ToolTipManager.sharedInstance().registerComponent(this);                        
                                  

    this.getGraphLayoutCache().setFactory(new ingenias.editor.cellfactories.TasksAndGoalsModelCellViewFactory());

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
    toolbar = new FilteredJToolBar("TasksAndGoalsModel");
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
    Image img_AgentModelBelieve =
        ImageLoader.getImage("images/mabel.gif");
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

   if (true){
    Image img_ApplicationEvent =
        ImageLoader.getImage("images/meventa.gif");
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

   if (true){
    Image img_ApplicationEventSlots =
        ImageLoader.getImage("images/meventas.gif");
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

   if (true){
    Image img_Application =
        ImageLoader.getImage("images/mapp.gif");
    undoIcon = new ImageIcon(img_Application);
    Action Application=
        new AbstractAction("Application", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Application");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Application.setEnabled(true);
    jb = new JButton(Application);
    jb.setText("");
    jb.setName("Application");	
    jb.setToolTipText("Application");
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
    Image img_StateGoal =
        ImageLoader.getImage("images/msgoal.gif");
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
    Image img_Resource =
        ImageLoader.getImage("images/mresource.gif");
    undoIcon = new ImageIcon(img_Resource);
    Action Resource=
        new AbstractAction("Resource", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Resource");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Resource.setEnabled(true);
    jb = new JButton(Resource);
    jb.setText("");
    jb.setName("Resource");	
    jb.setToolTipText("Resource");
    toolbar.add(jb);
    }

   if (true){
    Image img_Fact =
        ImageLoader.getImage("images/mfact.gif");
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

   if (true){
    Image img_FrameFact =
        ImageLoader.getImage("images/mffact.gif");
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

   if (true){
    Image img_Believe =
        ImageLoader.getImage("images/mbel.gif");
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

   if (true){
    Image img_Compromise =
        ImageLoader.getImage("images/mcomp.gif");
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

   if (true){
    Image img_GeneralEvent =
        ImageLoader.getImage("images/mevent.gif");
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

   if (true){
    Image img_CommunicationEvent =
        ImageLoader.getImage("images/meventa.gif");
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

   if (true){
    Image img_EnvironmentApplication =
        ImageLoader.getImage("images/mappe.gif");
    undoIcon = new ImageIcon(img_EnvironmentApplication);
    Action EnvironmentApplication=
        new AbstractAction("EnvironmentApplication", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "EnvironmentApplication");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    EnvironmentApplication.setEnabled(true);
    jb = new JButton(EnvironmentApplication);
    jb.setText("");
    jb.setName("EnvironmentApplication");	
    jb.setToolTipText("EnvironmentApplication");
    toolbar.add(jb);
    }

   if (true){
    Image img_InternalApplication =
        ImageLoader.getImage("images/mappi.gif");
    undoIcon = new ImageIcon(img_InternalApplication);
    Action InternalApplication=
        new AbstractAction("InternalApplication", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "InternalApplication");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    InternalApplication.setEnabled(true);
    jb = new JButton(InternalApplication);
    jb.setText("");
    jb.setName("InternalApplication");	
    jb.setToolTipText("InternalApplication");
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
    Image img_Plan =
        ImageLoader.getImage("images/mplan.gif");
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

   if (true){
    Image img_BoxedTask =
        ImageLoader.getImage("images/mtask.gif");
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

   if (true){
    Image img_MentalEntityInstanceCreation =
        ImageLoader.getImage("images/mtask.gif");
    undoIcon = new ImageIcon(img_MentalEntityInstanceCreation);
    Action MentalEntityInstanceCreation=
        new AbstractAction("MentalEntityInstanceCreation", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "MentalEntityInstanceCreation");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    MentalEntityInstanceCreation.setEnabled(true);
    jb = new JButton(MentalEntityInstanceCreation);
    jb.setText("");
    jb.setName("MentalEntityInstanceCreation");	
    jb.setToolTipText("MentalEntityInstanceCreation");
    toolbar.add(jb);
    }

   if (true){
    Image img_MentalEntityInstanceAccess =
        ImageLoader.getImage("images/mtask.gif");
    undoIcon = new ImageIcon(img_MentalEntityInstanceAccess);
    Action MentalEntityInstanceAccess=
        new AbstractAction("MentalEntityInstanceAccess", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "MentalEntityInstanceAccess");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    MentalEntityInstanceAccess.setEnabled(true);
    jb = new JButton(MentalEntityInstanceAccess);
    jb.setText("");
    jb.setName("MentalEntityInstanceAccess");	
    jb.setToolTipText("MentalEntityInstanceAccess");
    toolbar.add(jb);
    }

   if (true){
    Image img_Conversation =
        ImageLoader.getImage("images/mconv.png");
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
    Image img_Workflow =
        ImageLoader.getImage("images/mworkflow.gif");
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
    Image img_GoalStateWS =
        ImageLoader.getImage("images/mgoalstatews.png");
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

   if (true){
    Image img_ApplicationWS =
        ImageLoader.getImage("images/mapp.gif");
    undoIcon = new ImageIcon(img_ApplicationWS);
    Action ApplicationWS=
        new AbstractAction("ApplicationWS", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "ApplicationWS");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    ApplicationWS.setEnabled(true);
    jb = new JButton(ApplicationWS);
    jb.setText("");
    jb.setName("ApplicationWS");	
    jb.setToolTipText("ApplicationWS");
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
    Image img_IUIterate =
        ImageLoader.getImage("");
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
    Image img_IUConcurrence =
        ImageLoader.getImage("");
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

   if (false){
    Image img_InteractionUnit =
        ImageLoader.getImage("");
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

    }

  }

  public Vector<String> getAllowedRelationships(){
   Vector<String> relationships=new   Vector<String>();


          relationships.add("GTPursues");

          relationships.add("GTCreates");

          relationships.add("GTAffects");

          relationships.add("GTDestroys");

          relationships.add("GTModifies");

          relationships.add("GTFails");

          relationships.add("GTSatisfies");

          relationships.add("WFResponsable");

          relationships.add("WFConsumes");

          relationships.add("Consumes");

          relationships.add("WFUses");

          relationships.add("WFUsesMethod");

          relationships.add("PConnects");

          relationships.add("WFProduces");

          relationships.add("GTDecomposes");

          relationships.add("GTDecomposesAND");

          relationships.add("GTDecomposesOR");

          relationships.add("GTDepends");

          relationships.add("GTInherits");

          relationships.add("GTAndDepends");

          relationships.add("GTOrDepends");

          relationships.add("WFDecomposes");

          relationships.add("Contribute");

          relationships.add("ContributePositively");

          relationships.add("ContributeNegatively");

          relationships.add("UMLAnnotatedElement");

          relationships.add("WFParticipates");

          relationships.add("WFCancels");

          relationships.add("IAccesses");

   return relationships;
  }

  public  Vector<String> getAllowedEntities(){
   Vector<String> entities=new   Vector<String>();


 entities.add("Agent");

 entities.add("AgentModelBelieve");

 entities.add("ApplicationEvent");

 entities.add("ApplicationEventSlots");

 entities.add("Application");

 entities.add("AgentWS");

 entities.add("Role");

 entities.add("Goal");

 entities.add("StateGoal");

 entities.add("Task");

 entities.add("Resource");

 entities.add("Fact");

 entities.add("FrameFact");

 entities.add("Believe");

 entities.add("Compromise");

 entities.add("GeneralEvent");

 entities.add("CommunicationEvent");

 entities.add("EnvironmentApplication");

 entities.add("InternalApplication");

 entities.add("TextNote");

 entities.add("Plan");

 entities.add("BoxedTask");

 entities.add("MentalEntityInstanceCreation");

 entities.add("MentalEntityInstanceAccess");

 entities.add("Conversation");

 entities.add("UMLComment");

 entities.add("Workflow");

 entities.add("RoleWS");

 entities.add("TaskWS");

 entities.add("GoalStateWS");

 entities.add("ApplicationWS");

 entities.add("Interaction");

 entities.add("RemoteProcedureCall");

 entities.add("RuntimeEvent");

 entities.add("IUIterate");

 entities.add("MessagePassing");

 entities.add("ShareTouple");

 entities.add("RuntimeConversation");

 entities.add("ContextBindingTask");

 entities.add("ContextReleaseTask");

 entities.add("RuntimeFact");

 entities.add("IUConcurrence");

 entities.add("InteractionUnit");

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
        if (GTPursuesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("GTPursues");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (GTCreatesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("GTCreates");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (GTAffectsEdge.acceptConnection(this.getModel(), selected)) {
          v.add("GTAffects");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (GTDestroysEdge.acceptConnection(this.getModel(), selected)) {
          v.add("GTDestroys");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (GTModifiesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("GTModifies");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (GTFailsEdge.acceptConnection(this.getModel(), selected)) {
          v.add("GTFails");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (GTSatisfiesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("GTSatisfies");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFResponsableEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFResponsable");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFConsumesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFConsumes");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (ConsumesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("Consumes");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFUsesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFUses");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFUsesMethodEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFUsesMethod");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (PConnectsEdge.acceptConnection(this.getModel(), selected)) {
          v.add("PConnects");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFProducesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFProduces");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (GTDecomposesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("GTDecomposes");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (GTDecomposesANDEdge.acceptConnection(this.getModel(), selected)) {
          v.add("GTDecomposesAND");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (GTDecomposesOREdge.acceptConnection(this.getModel(), selected)) {
          v.add("GTDecomposesOR");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (GTDependsEdge.acceptConnection(this.getModel(), selected)) {
          v.add("GTDepends");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (GTInheritsEdge.acceptConnection(this.getModel(), selected)) {
          v.add("GTInherits");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (GTAndDependsEdge.acceptConnection(this.getModel(), selected)) {
          v.add("GTAndDepends");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (GTOrDependsEdge.acceptConnection(this.getModel(), selected)) {
          v.add("GTOrDepends");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFDecomposesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFDecomposes");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (ContributeEdge.acceptConnection(this.getModel(), selected)) {
          v.add("Contribute");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (ContributePositivelyEdge.acceptConnection(this.getModel(), selected)) {
          v.add("ContributePositively");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (ContributeNegativelyEdge.acceptConnection(this.getModel(), selected)) {
          v.add("ContributeNegatively");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (UMLAnnotatedElementEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UMLAnnotatedElement");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFParticipatesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFParticipates");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFCancelsEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFCancels");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (IAccessesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("IAccesses");
	  }

      }
      else if (nAryEdgesNum == 1) {

        if (selectedEdge instanceof GTPursuesEdge &&
        (GTPursuesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("GTPursues");
        }

        if (selectedEdge instanceof GTCreatesEdge &&
        (GTCreatesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("GTCreates");
        }

        if (selectedEdge instanceof GTAffectsEdge &&
        (GTAffectsEdge.acceptConnection(this.getModel(), selected))) {
          v.add("GTAffects");
        }

        if (selectedEdge instanceof GTDestroysEdge &&
        (GTDestroysEdge.acceptConnection(this.getModel(), selected))) {
          v.add("GTDestroys");
        }

        if (selectedEdge instanceof GTModifiesEdge &&
        (GTModifiesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("GTModifies");
        }

        if (selectedEdge instanceof GTFailsEdge &&
        (GTFailsEdge.acceptConnection(this.getModel(), selected))) {
          v.add("GTFails");
        }

        if (selectedEdge instanceof GTSatisfiesEdge &&
        (GTSatisfiesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("GTSatisfies");
        }

        if (selectedEdge instanceof WFResponsableEdge &&
        (WFResponsableEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFResponsable");
        }

        if (selectedEdge instanceof WFConsumesEdge &&
        (WFConsumesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFConsumes");
        }

        if (selectedEdge instanceof ConsumesEdge &&
        (ConsumesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("Consumes");
        }

        if (selectedEdge instanceof WFUsesEdge &&
        (WFUsesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFUses");
        }

        if (selectedEdge instanceof WFUsesMethodEdge &&
        (WFUsesMethodEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFUsesMethod");
        }

        if (selectedEdge instanceof PConnectsEdge &&
        (PConnectsEdge.acceptConnection(this.getModel(), selected))) {
          v.add("PConnects");
        }

        if (selectedEdge instanceof WFProducesEdge &&
        (WFProducesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFProduces");
        }

        if (selectedEdge instanceof GTDecomposesEdge &&
        (GTDecomposesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("GTDecomposes");
        }

        if (selectedEdge instanceof GTDecomposesANDEdge &&
        (GTDecomposesANDEdge.acceptConnection(this.getModel(), selected))) {
          v.add("GTDecomposesAND");
        }

        if (selectedEdge instanceof GTDecomposesOREdge &&
        (GTDecomposesOREdge.acceptConnection(this.getModel(), selected))) {
          v.add("GTDecomposesOR");
        }

        if (selectedEdge instanceof GTDependsEdge &&
        (GTDependsEdge.acceptConnection(this.getModel(), selected))) {
          v.add("GTDepends");
        }

        if (selectedEdge instanceof GTInheritsEdge &&
        (GTInheritsEdge.acceptConnection(this.getModel(), selected))) {
          v.add("GTInherits");
        }

        if (selectedEdge instanceof GTAndDependsEdge &&
        (GTAndDependsEdge.acceptConnection(this.getModel(), selected))) {
          v.add("GTAndDepends");
        }

        if (selectedEdge instanceof GTOrDependsEdge &&
        (GTOrDependsEdge.acceptConnection(this.getModel(), selected))) {
          v.add("GTOrDepends");
        }

        if (selectedEdge instanceof WFDecomposesEdge &&
        (WFDecomposesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFDecomposes");
        }

        if (selectedEdge instanceof ContributeEdge &&
        (ContributeEdge.acceptConnection(this.getModel(), selected))) {
          v.add("Contribute");
        }

        if (selectedEdge instanceof ContributePositivelyEdge &&
        (ContributePositivelyEdge.acceptConnection(this.getModel(), selected))) {
          v.add("ContributePositively");
        }

        if (selectedEdge instanceof ContributeNegativelyEdge &&
        (ContributeNegativelyEdge.acceptConnection(this.getModel(), selected))) {
          v.add("ContributeNegatively");
        }

        if (selectedEdge instanceof UMLAnnotatedElementEdge &&
        (UMLAnnotatedElementEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UMLAnnotatedElement");
        }

        if (selectedEdge instanceof WFParticipatesEdge &&
        (WFParticipatesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFParticipates");
        }

        if (selectedEdge instanceof WFCancelsEdge &&
        (WFCancelsEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFCancels");
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

      if (relacion.equalsIgnoreCase("GTCreates")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof GTCreatesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new GTCreatesEdge(new ingenias.editor.entities.GTCreates(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("GTAffects")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof GTAffectsEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new GTAffectsEdge(new ingenias.editor.entities.GTAffects(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("GTDestroys")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof GTDestroysEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new GTDestroysEdge(new ingenias.editor.entities.GTDestroys(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("GTModifies")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof GTModifiesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new GTModifiesEdge(new ingenias.editor.entities.GTModifies(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("GTFails")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof GTFailsEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new GTFailsEdge(new ingenias.editor.entities.GTFails(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("GTSatisfies")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof GTSatisfiesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new GTSatisfiesEdge(new ingenias.editor.entities.GTSatisfies(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFResponsable")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFResponsableEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFResponsableEdge(new ingenias.editor.entities.WFResponsable(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFConsumes")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFConsumesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFConsumesEdge(new ingenias.editor.entities.WFConsumes(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("Consumes")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof ConsumesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new ConsumesEdge(new ingenias.editor.entities.Consumes(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFUses")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFUsesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFUsesEdge(new ingenias.editor.entities.WFUses(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFUsesMethod")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFUsesMethodEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFUsesMethodEdge(new ingenias.editor.entities.WFUsesMethod(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("PConnects")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof PConnectsEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new PConnectsEdge(new ingenias.editor.entities.PConnects(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFProduces")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFProducesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFProducesEdge(new ingenias.editor.entities.WFProduces(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("GTDecomposes")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof GTDecomposesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new GTDecomposesEdge(new ingenias.editor.entities.GTDecomposes(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("GTDecomposesAND")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof GTDecomposesANDEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new GTDecomposesANDEdge(new ingenias.editor.entities.GTDecomposesAND(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("GTDecomposesOR")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof GTDecomposesOREdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new GTDecomposesOREdge(new ingenias.editor.entities.GTDecomposesOR(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("GTDepends")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof GTDependsEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new GTDependsEdge(new ingenias.editor.entities.GTDepends(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("GTInherits")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof GTInheritsEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new GTInheritsEdge(new ingenias.editor.entities.GTInherits(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("GTAndDepends")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof GTAndDependsEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new GTAndDependsEdge(new ingenias.editor.entities.GTAndDepends(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("GTOrDepends")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof GTOrDependsEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new GTOrDependsEdge(new ingenias.editor.entities.GTOrDepends(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFDecomposes")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFDecomposesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFDecomposesEdge(new ingenias.editor.entities.WFDecomposes(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("Contribute")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof ContributeEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new ContributeEdge(new ingenias.editor.entities.Contribute(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("ContributePositively")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof ContributePositivelyEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new ContributePositivelyEdge(new ingenias.editor.entities.ContributePositively(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("ContributeNegatively")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof ContributeNegativelyEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new ContributeNegativelyEdge(new ingenias.editor.entities.ContributeNegatively(getMJGraph().getNewId()));
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

      if (relacion.equalsIgnoreCase("WFParticipates")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFParticipatesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFParticipatesEdge(new ingenias.editor.entities.WFParticipates(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFCancels")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFCancelsEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFCancelsEdge(new ingenias.editor.entities.WFCancels(getMJGraph().getNewId()));
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

    if (entity.equalsIgnoreCase("AgentModelBelieve")) {
    AgentModelBelieve nentity=getOM().createAgentModelBelieve(getMJGraph().getNewId("AgentModelBelieve"));
      DefaultGraphCell vertex = new
          AgentModelBelieveCell(nentity);
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

    if (entity.equalsIgnoreCase("ApplicationEventSlots")) {
    ApplicationEventSlots nentity=getOM().createApplicationEventSlots(getMJGraph().getNewId("ApplicationEventSlots"));
      DefaultGraphCell vertex = new
          ApplicationEventSlotsCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Application")) {
    Application nentity=getOM().createApplication(getMJGraph().getNewId("Application"));
      DefaultGraphCell vertex = new
          ApplicationCell(nentity);
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

    if (entity.equalsIgnoreCase("Goal")) {
    Goal nentity=getOM().createGoal(getMJGraph().getNewId("Goal"));
      DefaultGraphCell vertex = new
          GoalCell(nentity);
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

    if (entity.equalsIgnoreCase("Task")) {
    Task nentity=getOM().createTask(getMJGraph().getNewId("Task"));
      DefaultGraphCell vertex = new
          TaskCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Resource")) {
    Resource nentity=getOM().createResource(getMJGraph().getNewId("Resource"));
      DefaultGraphCell vertex = new
          ResourceCell(nentity);
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

    if (entity.equalsIgnoreCase("FrameFact")) {
    FrameFact nentity=getOM().createFrameFact(getMJGraph().getNewId("FrameFact"));
      DefaultGraphCell vertex = new
          FrameFactCell(nentity);
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

    if (entity.equalsIgnoreCase("Compromise")) {
    Compromise nentity=getOM().createCompromise(getMJGraph().getNewId("Compromise"));
      DefaultGraphCell vertex = new
          CompromiseCell(nentity);
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

    if (entity.equalsIgnoreCase("CommunicationEvent")) {
    CommunicationEvent nentity=getOM().createCommunicationEvent(getMJGraph().getNewId("CommunicationEvent"));
      DefaultGraphCell vertex = new
          CommunicationEventCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("EnvironmentApplication")) {
    EnvironmentApplication nentity=getOM().createEnvironmentApplication(getMJGraph().getNewId("EnvironmentApplication"));
      DefaultGraphCell vertex = new
          EnvironmentApplicationCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("InternalApplication")) {
    InternalApplication nentity=getOM().createInternalApplication(getMJGraph().getNewId("InternalApplication"));
      DefaultGraphCell vertex = new
          InternalApplicationCell(nentity);
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

    if (entity.equalsIgnoreCase("Plan")) {
    Plan nentity=getOM().createPlan(getMJGraph().getNewId("Plan"));
      DefaultGraphCell vertex = new
          PlanCell(nentity);
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

    if (entity.equalsIgnoreCase("MentalEntityInstanceCreation")) {
    MentalEntityInstanceCreation nentity=getOM().createMentalEntityInstanceCreation(getMJGraph().getNewId("MentalEntityInstanceCreation"));
      DefaultGraphCell vertex = new
          MentalEntityInstanceCreationCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("MentalEntityInstanceAccess")) {
    MentalEntityInstanceAccess nentity=getOM().createMentalEntityInstanceAccess(getMJGraph().getNewId("MentalEntityInstanceAccess"));
      DefaultGraphCell vertex = new
          MentalEntityInstanceAccessCell(nentity);
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

    if (entity.equalsIgnoreCase("UMLComment")) {
    UMLComment nentity=getOM().createUMLComment(getMJGraph().getNewId("UMLComment"));
      DefaultGraphCell vertex = new
          UMLCommentCell(nentity);
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

    if (entity.equalsIgnoreCase("RoleWS")) {
    RoleWS nentity=getOM().createRoleWS(getMJGraph().getNewId("RoleWS"));
      DefaultGraphCell vertex = new
          RoleWSCell(nentity);
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

    if (entity.equalsIgnoreCase("GoalStateWS")) {
    GoalStateWS nentity=getOM().createGoalStateWS(getMJGraph().getNewId("GoalStateWS"));
      DefaultGraphCell vertex = new
          GoalStateWSCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("ApplicationWS")) {
    ApplicationWS nentity=getOM().createApplicationWS(getMJGraph().getNewId("ApplicationWS"));
      DefaultGraphCell vertex = new
          ApplicationWSCell(nentity);
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

    if (entity.equalsIgnoreCase("RemoteProcedureCall")) {
    RemoteProcedureCall nentity=getOM().createRemoteProcedureCall(getMJGraph().getNewId("RemoteProcedureCall"));
      DefaultGraphCell vertex = new
          RemoteProcedureCallCell(nentity);
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

    if (entity.equalsIgnoreCase("IUIterate")) {
    IUIterate nentity=getOM().createIUIterate(getMJGraph().getNewId("IUIterate"));
      DefaultGraphCell vertex = new
          IUIterateCell(nentity);
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

    if (entity.equalsIgnoreCase("RuntimeConversation")) {
    RuntimeConversation nentity=getOM().createRuntimeConversation(getMJGraph().getNewId("RuntimeConversation"));
      DefaultGraphCell vertex = new
          RuntimeConversationCell(nentity);
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

    if (entity.equalsIgnoreCase("RuntimeFact")) {
    RuntimeFact nentity=getOM().createRuntimeFact(getMJGraph().getNewId("RuntimeFact"));
      DefaultGraphCell vertex = new
          RuntimeFactCell(nentity);
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

	  throw new ingenias.exception.InvalidEntity("Entity type "+entity+" is not allowed in this diagram"); 
  }
  
  public Dimension getDefaultSize(Entity entity) throws InvalidEntity{
    
    if (entity.getType().equalsIgnoreCase("Agent")) {
      return AgentView.getSize((ingenias.editor.entities.Agent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AgentModelBelieve")) {
      return AgentModelBelieveView.getSize((ingenias.editor.entities.AgentModelBelieve)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ApplicationEvent")) {
      return ApplicationEventView.getSize((ingenias.editor.entities.ApplicationEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ApplicationEventSlots")) {
      return ApplicationEventSlotsView.getSize((ingenias.editor.entities.ApplicationEventSlots)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Application")) {
      return ApplicationView.getSize((ingenias.editor.entities.Application)entity);      
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

    if (entity.getType().equalsIgnoreCase("Goal")) {
      return GoalView.getSize((ingenias.editor.entities.Goal)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("StateGoal")) {
      return StateGoalView.getSize((ingenias.editor.entities.StateGoal)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Task")) {
      return TaskView.getSize((ingenias.editor.entities.Task)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Resource")) {
      return ResourceView.getSize((ingenias.editor.entities.Resource)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Fact")) {
      return FactView.getSize((ingenias.editor.entities.Fact)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FrameFact")) {
      return FrameFactView.getSize((ingenias.editor.entities.FrameFact)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Believe")) {
      return BelieveView.getSize((ingenias.editor.entities.Believe)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Compromise")) {
      return CompromiseView.getSize((ingenias.editor.entities.Compromise)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("GeneralEvent")) {
      return GeneralEventView.getSize((ingenias.editor.entities.GeneralEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("CommunicationEvent")) {
      return CommunicationEventView.getSize((ingenias.editor.entities.CommunicationEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("EnvironmentApplication")) {
      return EnvironmentApplicationView.getSize((ingenias.editor.entities.EnvironmentApplication)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("InternalApplication")) {
      return InternalApplicationView.getSize((ingenias.editor.entities.InternalApplication)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("TextNote")) {
      return TextNoteView.getSize((ingenias.editor.entities.TextNote)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Plan")) {
      return PlanView.getSize((ingenias.editor.entities.Plan)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("BoxedTask")) {
      return BoxedTaskView.getSize((ingenias.editor.entities.BoxedTask)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("MentalEntityInstanceCreation")) {
      return MentalEntityInstanceCreationView.getSize((ingenias.editor.entities.MentalEntityInstanceCreation)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("MentalEntityInstanceAccess")) {
      return MentalEntityInstanceAccessView.getSize((ingenias.editor.entities.MentalEntityInstanceAccess)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Conversation")) {
      return ConversationView.getSize((ingenias.editor.entities.Conversation)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("UMLComment")) {
      return UMLCommentView.getSize((ingenias.editor.entities.UMLComment)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Workflow")) {
      return WorkflowView.getSize((ingenias.editor.entities.Workflow)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RoleWS")) {
      return RoleWSView.getSize((ingenias.editor.entities.RoleWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("TaskWS")) {
      return TaskWSView.getSize((ingenias.editor.entities.TaskWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("GoalStateWS")) {
      return GoalStateWSView.getSize((ingenias.editor.entities.GoalStateWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ApplicationWS")) {
      return ApplicationWSView.getSize((ingenias.editor.entities.ApplicationWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Interaction")) {
      return InteractionView.getSize((ingenias.editor.entities.Interaction)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RemoteProcedureCall")) {
      return RemoteProcedureCallView.getSize((ingenias.editor.entities.RemoteProcedureCall)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RuntimeEvent")) {
      return RuntimeEventView.getSize((ingenias.editor.entities.RuntimeEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("IUIterate")) {
      return IUIterateView.getSize((ingenias.editor.entities.IUIterate)entity);      
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

    if (entity.getType().equalsIgnoreCase("RuntimeConversation")) {
      return RuntimeConversationView.getSize((ingenias.editor.entities.RuntimeConversation)entity);      
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

    if (entity.getType().equalsIgnoreCase("RuntimeFact")) {
      return RuntimeFactView.getSize((ingenias.editor.entities.RuntimeFact)entity);      
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


      if (entity.getType().equalsIgnoreCase("GTPursues")) {
      	return GTPursuesView.getSize((ingenias.editor.entities.GTPursues)entity);
      }

      if (entity.getType().equalsIgnoreCase("GTCreates")) {
      	return GTCreatesView.getSize((ingenias.editor.entities.GTCreates)entity);
      }

      if (entity.getType().equalsIgnoreCase("GTAffects")) {
      	return GTAffectsView.getSize((ingenias.editor.entities.GTAffects)entity);
      }

      if (entity.getType().equalsIgnoreCase("GTDestroys")) {
      	return GTDestroysView.getSize((ingenias.editor.entities.GTDestroys)entity);
      }

      if (entity.getType().equalsIgnoreCase("GTModifies")) {
      	return GTModifiesView.getSize((ingenias.editor.entities.GTModifies)entity);
      }

      if (entity.getType().equalsIgnoreCase("GTFails")) {
      	return GTFailsView.getSize((ingenias.editor.entities.GTFails)entity);
      }

      if (entity.getType().equalsIgnoreCase("GTSatisfies")) {
      	return GTSatisfiesView.getSize((ingenias.editor.entities.GTSatisfies)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFResponsable")) {
      	return WFResponsableView.getSize((ingenias.editor.entities.WFResponsable)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFConsumes")) {
      	return WFConsumesView.getSize((ingenias.editor.entities.WFConsumes)entity);
      }

      if (entity.getType().equalsIgnoreCase("Consumes")) {
      	return ConsumesView.getSize((ingenias.editor.entities.Consumes)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFUses")) {
      	return WFUsesView.getSize((ingenias.editor.entities.WFUses)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFUsesMethod")) {
      	return WFUsesMethodView.getSize((ingenias.editor.entities.WFUsesMethod)entity);
      }

      if (entity.getType().equalsIgnoreCase("PConnects")) {
      	return PConnectsView.getSize((ingenias.editor.entities.PConnects)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFProduces")) {
      	return WFProducesView.getSize((ingenias.editor.entities.WFProduces)entity);
      }

      if (entity.getType().equalsIgnoreCase("GTDecomposes")) {
      	return GTDecomposesView.getSize((ingenias.editor.entities.GTDecomposes)entity);
      }

      if (entity.getType().equalsIgnoreCase("GTDecomposesAND")) {
      	return GTDecomposesANDView.getSize((ingenias.editor.entities.GTDecomposesAND)entity);
      }

      if (entity.getType().equalsIgnoreCase("GTDecomposesOR")) {
      	return GTDecomposesORView.getSize((ingenias.editor.entities.GTDecomposesOR)entity);
      }

      if (entity.getType().equalsIgnoreCase("GTDepends")) {
      	return GTDependsView.getSize((ingenias.editor.entities.GTDepends)entity);
      }

      if (entity.getType().equalsIgnoreCase("GTInherits")) {
      	return GTInheritsView.getSize((ingenias.editor.entities.GTInherits)entity);
      }

      if (entity.getType().equalsIgnoreCase("GTAndDepends")) {
      	return GTAndDependsView.getSize((ingenias.editor.entities.GTAndDepends)entity);
      }

      if (entity.getType().equalsIgnoreCase("GTOrDepends")) {
      	return GTOrDependsView.getSize((ingenias.editor.entities.GTOrDepends)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFDecomposes")) {
      	return WFDecomposesView.getSize((ingenias.editor.entities.WFDecomposes)entity);
      }

      if (entity.getType().equalsIgnoreCase("Contribute")) {
      	return ContributeView.getSize((ingenias.editor.entities.Contribute)entity);
      }

      if (entity.getType().equalsIgnoreCase("ContributePositively")) {
      	return ContributePositivelyView.getSize((ingenias.editor.entities.ContributePositively)entity);
      }

      if (entity.getType().equalsIgnoreCase("ContributeNegatively")) {
      	return ContributeNegativelyView.getSize((ingenias.editor.entities.ContributeNegatively)entity);
      }

      if (entity.getType().equalsIgnoreCase("UMLAnnotatedElement")) {
      	return UMLAnnotatedElementView.getSize((ingenias.editor.entities.UMLAnnotatedElement)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFParticipates")) {
      	return WFParticipatesView.getSize((ingenias.editor.entities.WFParticipates)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFCancels")) {
      	return WFCancelsView.getSize((ingenias.editor.entities.WFCancels)entity);
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

    if (entity.getClass().equals(AgentModelBelieve.class)) {
      vertex = new AgentModelBelieveCell( (AgentModelBelieve) entity);
      // Default Size for the new Vertex with the new entity within
      size = AgentModelBelieveView.getSize((AgentModelBelieve) entity);
      
    }
    else

    if (entity.getClass().equals(ApplicationEvent.class)) {
      vertex = new ApplicationEventCell( (ApplicationEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationEventView.getSize((ApplicationEvent) entity);
      
    }
    else

    if (entity.getClass().equals(ApplicationEventSlots.class)) {
      vertex = new ApplicationEventSlotsCell( (ApplicationEventSlots) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationEventSlotsView.getSize((ApplicationEventSlots) entity);
      
    }
    else

    if (entity.getClass().equals(Application.class)) {
      vertex = new ApplicationCell( (Application) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationView.getSize((Application) entity);
      
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

    if (entity.getClass().equals(Goal.class)) {
      vertex = new GoalCell( (Goal) entity);
      // Default Size for the new Vertex with the new entity within
      size = GoalView.getSize((Goal) entity);
      
    }
    else

    if (entity.getClass().equals(StateGoal.class)) {
      vertex = new StateGoalCell( (StateGoal) entity);
      // Default Size for the new Vertex with the new entity within
      size = StateGoalView.getSize((StateGoal) entity);
      
    }
    else

    if (entity.getClass().equals(Task.class)) {
      vertex = new TaskCell( (Task) entity);
      // Default Size for the new Vertex with the new entity within
      size = TaskView.getSize((Task) entity);
      
    }
    else

    if (entity.getClass().equals(Resource.class)) {
      vertex = new ResourceCell( (Resource) entity);
      // Default Size for the new Vertex with the new entity within
      size = ResourceView.getSize((Resource) entity);
      
    }
    else

    if (entity.getClass().equals(Fact.class)) {
      vertex = new FactCell( (Fact) entity);
      // Default Size for the new Vertex with the new entity within
      size = FactView.getSize((Fact) entity);
      
    }
    else

    if (entity.getClass().equals(FrameFact.class)) {
      vertex = new FrameFactCell( (FrameFact) entity);
      // Default Size for the new Vertex with the new entity within
      size = FrameFactView.getSize((FrameFact) entity);
      
    }
    else

    if (entity.getClass().equals(Believe.class)) {
      vertex = new BelieveCell( (Believe) entity);
      // Default Size for the new Vertex with the new entity within
      size = BelieveView.getSize((Believe) entity);
      
    }
    else

    if (entity.getClass().equals(Compromise.class)) {
      vertex = new CompromiseCell( (Compromise) entity);
      // Default Size for the new Vertex with the new entity within
      size = CompromiseView.getSize((Compromise) entity);
      
    }
    else

    if (entity.getClass().equals(GeneralEvent.class)) {
      vertex = new GeneralEventCell( (GeneralEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = GeneralEventView.getSize((GeneralEvent) entity);
      
    }
    else

    if (entity.getClass().equals(CommunicationEvent.class)) {
      vertex = new CommunicationEventCell( (CommunicationEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = CommunicationEventView.getSize((CommunicationEvent) entity);
      
    }
    else

    if (entity.getClass().equals(EnvironmentApplication.class)) {
      vertex = new EnvironmentApplicationCell( (EnvironmentApplication) entity);
      // Default Size for the new Vertex with the new entity within
      size = EnvironmentApplicationView.getSize((EnvironmentApplication) entity);
      
    }
    else

    if (entity.getClass().equals(InternalApplication.class)) {
      vertex = new InternalApplicationCell( (InternalApplication) entity);
      // Default Size for the new Vertex with the new entity within
      size = InternalApplicationView.getSize((InternalApplication) entity);
      
    }
    else

    if (entity.getClass().equals(TextNote.class)) {
      vertex = new TextNoteCell( (TextNote) entity);
      // Default Size for the new Vertex with the new entity within
      size = TextNoteView.getSize((TextNote) entity);
      
    }
    else

    if (entity.getClass().equals(Plan.class)) {
      vertex = new PlanCell( (Plan) entity);
      // Default Size for the new Vertex with the new entity within
      size = PlanView.getSize((Plan) entity);
      
    }
    else

    if (entity.getClass().equals(BoxedTask.class)) {
      vertex = new BoxedTaskCell( (BoxedTask) entity);
      // Default Size for the new Vertex with the new entity within
      size = BoxedTaskView.getSize((BoxedTask) entity);
      
    }
    else

    if (entity.getClass().equals(MentalEntityInstanceCreation.class)) {
      vertex = new MentalEntityInstanceCreationCell( (MentalEntityInstanceCreation) entity);
      // Default Size for the new Vertex with the new entity within
      size = MentalEntityInstanceCreationView.getSize((MentalEntityInstanceCreation) entity);
      
    }
    else

    if (entity.getClass().equals(MentalEntityInstanceAccess.class)) {
      vertex = new MentalEntityInstanceAccessCell( (MentalEntityInstanceAccess) entity);
      // Default Size for the new Vertex with the new entity within
      size = MentalEntityInstanceAccessView.getSize((MentalEntityInstanceAccess) entity);
      
    }
    else

    if (entity.getClass().equals(Conversation.class)) {
      vertex = new ConversationCell( (Conversation) entity);
      // Default Size for the new Vertex with the new entity within
      size = ConversationView.getSize((Conversation) entity);
      
    }
    else

    if (entity.getClass().equals(UMLComment.class)) {
      vertex = new UMLCommentCell( (UMLComment) entity);
      // Default Size for the new Vertex with the new entity within
      size = UMLCommentView.getSize((UMLComment) entity);
      
    }
    else

    if (entity.getClass().equals(Workflow.class)) {
      vertex = new WorkflowCell( (Workflow) entity);
      // Default Size for the new Vertex with the new entity within
      size = WorkflowView.getSize((Workflow) entity);
      
    }
    else

    if (entity.getClass().equals(RoleWS.class)) {
      vertex = new RoleWSCell( (RoleWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = RoleWSView.getSize((RoleWS) entity);
      
    }
    else

    if (entity.getClass().equals(TaskWS.class)) {
      vertex = new TaskWSCell( (TaskWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = TaskWSView.getSize((TaskWS) entity);
      
    }
    else

    if (entity.getClass().equals(GoalStateWS.class)) {
      vertex = new GoalStateWSCell( (GoalStateWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = GoalStateWSView.getSize((GoalStateWS) entity);
      
    }
    else

    if (entity.getClass().equals(ApplicationWS.class)) {
      vertex = new ApplicationWSCell( (ApplicationWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationWSView.getSize((ApplicationWS) entity);
      
    }
    else

    if (entity.getClass().equals(Interaction.class)) {
      vertex = new InteractionCell( (Interaction) entity);
      // Default Size for the new Vertex with the new entity within
      size = InteractionView.getSize((Interaction) entity);
      
    }
    else

    if (entity.getClass().equals(RemoteProcedureCall.class)) {
      vertex = new RemoteProcedureCallCell( (RemoteProcedureCall) entity);
      // Default Size for the new Vertex with the new entity within
      size = RemoteProcedureCallView.getSize((RemoteProcedureCall) entity);
      
    }
    else

    if (entity.getClass().equals(RuntimeEvent.class)) {
      vertex = new RuntimeEventCell( (RuntimeEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = RuntimeEventView.getSize((RuntimeEvent) entity);
      
    }
    else

    if (entity.getClass().equals(IUIterate.class)) {
      vertex = new IUIterateCell( (IUIterate) entity);
      // Default Size for the new Vertex with the new entity within
      size = IUIterateView.getSize((IUIterate) entity);
      
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

    if (entity.getClass().equals(RuntimeConversation.class)) {
      vertex = new RuntimeConversationCell( (RuntimeConversation) entity);
      // Default Size for the new Vertex with the new entity within
      size = RuntimeConversationView.getSize((RuntimeConversation) entity);
      
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

    if (entity.getClass().equals(RuntimeFact.class)) {
      vertex = new RuntimeFactCell( (RuntimeFact) entity);
      // Default Size for the new Vertex with the new entity within
      size = RuntimeFactView.getSize((RuntimeFact) entity);
      
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

		

		 TasksAndGoalsModelModelJGraph jg=new  TasksAndGoalsModelModelJGraph(
				(TasksAndGoalsModelDataEntity) this.mde,name, ids.om,
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
