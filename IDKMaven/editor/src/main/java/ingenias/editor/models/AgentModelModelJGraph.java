

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

public class AgentModelModelJGraph extends ModelJGraph {

  private Preferences prefs;
  
 

  public AgentModelModelJGraph(AgentModelDataEntity mde, 
                               String nombre, ObjectManager om, Model
                               m, BasicMarqueeHandler mh, Preferences prefs) {
    super(mde, nombre, m, mh,om);
    this.prefs=prefs;
    
       
                                  ToolTipManager.sharedInstance().registerComponent(this);                
                                  

    this.getGraphLayoutCache().setFactory(new ingenias.editor.cellfactories.AgentModelCellViewFactory());

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
    toolbar = new FilteredJToolBar("AgentModel");
    toolbar.setFloatable(false);
    ImageIcon undoIcon = null;
    JButton jb = null;


   if (true){
    Image img_FAERIEContext =
        ImageLoader.getImage("images/mdepl.gif");
    undoIcon = new ImageIcon(img_FAERIEContext);
    Action FAERIEContext=
        new AbstractAction("FAERIEContext", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "FAERIEContext");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    FAERIEContext.setEnabled(true);
    jb = new JButton(FAERIEContext);
    jb.setText("");
    jb.setName("FAERIEContext");	
    jb.setToolTipText("FAERIEContext");
    toolbar.add(jb);
    }

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
    Image img_MentalStateProcessor =
        ImageLoader.getImage("images/mproc.gif");
    undoIcon = new ImageIcon(img_MentalStateProcessor);
    Action MentalStateProcessor=
        new AbstractAction("MentalStateProcessor", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "MentalStateProcessor");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    MentalStateProcessor.setEnabled(true);
    jb = new JButton(MentalStateProcessor);
    jb.setText("");
    jb.setName("MentalStateProcessor");	
    jb.setToolTipText("MentalStateProcessor");
    toolbar.add(jb);
    }

   if (true){
    Image img_MentalStateManager =
        ImageLoader.getImage("images/mman.gif");
    undoIcon = new ImageIcon(img_MentalStateManager);
    Action MentalStateManager=
        new AbstractAction("MentalStateManager", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "MentalStateManager");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    MentalStateManager.setEnabled(true);
    jb = new JButton(MentalStateManager);
    jb.setText("");
    jb.setName("MentalStateManager");	
    jb.setToolTipText("MentalStateManager");
    toolbar.add(jb);
    }

   if (true){
    Image img_MentalState =
        ImageLoader.getImage("images/mmstate.gif");
    undoIcon = new ImageIcon(img_MentalState);
    Action MentalState=
        new AbstractAction("MentalState", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "MentalState");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    MentalState.setEnabled(true);
    jb = new JButton(MentalState);
    jb.setText("");
    jb.setName("MentalState");	
    jb.setToolTipText("MentalState");
    toolbar.add(jb);
    }

   if (true){
    Image img_ConditionalMentalState =
        ImageLoader.getImage("images/mcmstate.gif");
    undoIcon = new ImageIcon(img_ConditionalMentalState);
    Action ConditionalMentalState=
        new AbstractAction("ConditionalMentalState", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "ConditionalMentalState");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    ConditionalMentalState.setEnabled(true);
    jb = new JButton(ConditionalMentalState);
    jb.setText("");
    jb.setName("ConditionalMentalState");	
    jb.setToolTipText("ConditionalMentalState");
    toolbar.add(jb);
    }

   if (true){
    Image img_AutonomousEntityQuery =
        ImageLoader.getImage("images/mquery.gif");
    undoIcon = new ImageIcon(img_AutonomousEntityQuery);
    Action AutonomousEntityQuery=
        new AbstractAction("AutonomousEntityQuery", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "AutonomousEntityQuery");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    AutonomousEntityQuery.setEnabled(true);
    jb = new JButton(AutonomousEntityQuery);
    jb.setText("");
    jb.setName("AutonomousEntityQuery");	
    jb.setToolTipText("AutonomousEntityQuery");
    toolbar.add(jb);
    }

   if (true){
    Image img_AgentRequirementsQuery =
        ImageLoader.getImage("images/mrquery.gif");
    undoIcon = new ImageIcon(img_AgentRequirementsQuery);
    Action AgentRequirementsQuery=
        new AbstractAction("AgentRequirementsQuery", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "AgentRequirementsQuery");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    AgentRequirementsQuery.setEnabled(true);
    jb = new JButton(AgentRequirementsQuery);
    jb.setText("");
    jb.setName("AgentRequirementsQuery");	
    jb.setToolTipText("AgentRequirementsQuery");
    toolbar.add(jb);
    }

   if (true){
    Image img_ConcreteAgent =
        ImageLoader.getImage("images/mcaquery.gif");
    undoIcon = new ImageIcon(img_ConcreteAgent);
    Action ConcreteAgent=
        new AbstractAction("ConcreteAgent", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "ConcreteAgent");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    ConcreteAgent.setEnabled(true);
    jb = new JButton(ConcreteAgent);
    jb.setText("");
    jb.setName("ConcreteAgent");	
    jb.setToolTipText("ConcreteAgent");
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
    Image img_MentalInstanceSpecification =
        ImageLoader.getImage("images/mmispec.gif");
    undoIcon = new ImageIcon(img_MentalInstanceSpecification);
    Action MentalInstanceSpecification=
        new AbstractAction("MentalInstanceSpecification", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "MentalInstanceSpecification");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    MentalInstanceSpecification.setEnabled(true);
    jb = new JButton(MentalInstanceSpecification);
    jb.setText("");
    jb.setName("MentalInstanceSpecification");	
    jb.setToolTipText("MentalInstanceSpecification");
    toolbar.add(jb);
    }

   if (false){
    Image img_ApplicationWS =
        ImageLoader.getImage("");
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

   if (false){
    Image img_RuntimeCommFailure =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_RuntimeCommFailure);
    Action RuntimeCommFailure=
        new AbstractAction("RuntimeCommFailure", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "RuntimeCommFailure");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    RuntimeCommFailure.setEnabled(true);
    jb = new JButton(RuntimeCommFailure);
    jb.setText("");
    jb.setName("RuntimeCommFailure");	
    jb.setToolTipText("RuntimeCommFailure");
    toolbar.add(jb);
    }

   if (false){
    Image img_FAERIECtxtRelationship =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_FAERIECtxtRelationship);
    Action FAERIECtxtRelationship=
        new AbstractAction("FAERIECtxtRelationship", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "FAERIECtxtRelationship");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    FAERIECtxtRelationship.setEnabled(true);
    jb = new JButton(FAERIECtxtRelationship);
    jb.setText("");
    jb.setName("FAERIECtxtRelationship");	
    jb.setToolTipText("FAERIECtxtRelationship");
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
    Image img_FAERIECtxtEntity =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_FAERIECtxtEntity);
    Action FAERIECtxtEntity=
        new AbstractAction("FAERIECtxtEntity", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "FAERIECtxtEntity");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    FAERIECtxtEntity.setEnabled(true);
    jb = new JButton(FAERIECtxtEntity);
    jb.setText("");
    jb.setName("FAERIECtxtEntity");	
    jb.setToolTipText("FAERIECtxtEntity");
    toolbar.add(jb);
    }

   if (false){
    Image img_FAERIECtxtAttribute =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_FAERIECtxtAttribute);
    Action FAERIECtxtAttribute=
        new AbstractAction("FAERIECtxtAttribute", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "FAERIECtxtAttribute");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    FAERIECtxtAttribute.setEnabled(true);
    jb = new JButton(FAERIECtxtAttribute);
    jb.setText("");
    jb.setName("FAERIECtxtAttribute");	
    jb.setToolTipText("FAERIECtxtAttribute");
    toolbar.add(jb);
    }

   if (false){
    Image img_Interaction =
        ImageLoader.getImage("");
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
    Image img_AMIContext =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_AMIContext);
    Action AMIContext=
        new AbstractAction("AMIContext", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "AMIContext");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    AMIContext.setEnabled(true);
    jb = new JButton(AMIContext);
    jb.setText("");
    jb.setName("AMIContext");	
    jb.setToolTipText("AMIContext");
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
    Image img_ContextUseTask =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_ContextUseTask);
    Action ContextUseTask=
        new AbstractAction("ContextUseTask", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "ContextUseTask");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    ContextUseTask.setEnabled(true);
    jb = new JButton(ContextUseTask);
    jb.setText("");
    jb.setName("ContextUseTask");	
    jb.setToolTipText("ContextUseTask");
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

    }

  }

  public Vector<String> getAllowedRelationships(){
   Vector<String> relationships=new   Vector<String>();


          relationships.add("GTPursues");

          relationships.add("AInherits");

          relationships.add("AHasMS");

          relationships.add("AHasMSManager");

          relationships.add("AHasMSProcessor");

          relationships.add("WFResponsable");

          relationships.add("AInstanceOf");

          relationships.add("AContainsME");

          relationships.add("WFPlays");

          relationships.add("ARoleInheritance");

          relationships.add("UMLAnnotatedElement");

          relationships.add("WFUses");

          relationships.add("EResourceBelongsTo");

          relationships.add("ApplicationBelongsTo");

   return relationships;
  }

  public  Vector<String> getAllowedEntities(){
   Vector<String> entities=new   Vector<String>();


 entities.add("FAERIEContext");

 entities.add("Agent");

 entities.add("Role");

 entities.add("Goal");

 entities.add("Plan");

 entities.add("StateGoal");

 entities.add("Fact");

 entities.add("Task");

 entities.add("FrameFact");

 entities.add("Believe");

 entities.add("Compromise");

 entities.add("GeneralEvent");

 entities.add("ApplicationEvent");

 entities.add("ApplicationEventSlots");

 entities.add("MentalStateProcessor");

 entities.add("MentalStateManager");

 entities.add("MentalState");

 entities.add("ConditionalMentalState");

 entities.add("AutonomousEntityQuery");

 entities.add("AgentRequirementsQuery");

 entities.add("ConcreteAgent");

 entities.add("AgentModelBelieve");

 entities.add("Conversation");

 entities.add("UMLComment");

 entities.add("TextNote");

 entities.add("Application");

 entities.add("EnvironmentApplication");

 entities.add("InternalApplication");

 entities.add("RoleWS");

 entities.add("TaskWS");

 entities.add("GoalStateWS");

 entities.add("AgentWS");

 entities.add("MentalInstanceSpecification");

 entities.add("ApplicationWS");

 entities.add("RuntimeCommFailure");

 entities.add("FAERIECtxtRelationship");

 entities.add("RuntimeEvent");

 entities.add("FAERIECtxtEntity");

 entities.add("FAERIECtxtAttribute");

 entities.add("Interaction");

 entities.add("BoxedTask");

 entities.add("RuntimeConversation");

 entities.add("CommunicationEvent");

 entities.add("AMIContext");

 entities.add("ContextBindingTask");

 entities.add("ContextReleaseTask");

 entities.add("ContextUseTask");

 entities.add("RuntimeFact");

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
        if (AInheritsEdge.acceptConnection(this.getModel(), selected)) {
          v.add("AInherits");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (AHasMSEdge.acceptConnection(this.getModel(), selected)) {
          v.add("AHasMS");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (AHasMSManagerEdge.acceptConnection(this.getModel(), selected)) {
          v.add("AHasMSManager");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (AHasMSProcessorEdge.acceptConnection(this.getModel(), selected)) {
          v.add("AHasMSProcessor");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFResponsableEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFResponsable");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (AInstanceOfEdge.acceptConnection(this.getModel(), selected)) {
          v.add("AInstanceOf");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (AContainsMEEdge.acceptConnection(this.getModel(), selected)) {
          v.add("AContainsME");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFPlaysEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFPlays");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (ARoleInheritanceEdge.acceptConnection(this.getModel(), selected)) {
          v.add("ARoleInheritance");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (UMLAnnotatedElementEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UMLAnnotatedElement");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFUsesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFUses");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (EResourceBelongsToEdge.acceptConnection(this.getModel(), selected)) {
          v.add("EResourceBelongsTo");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (ApplicationBelongsToEdge.acceptConnection(this.getModel(), selected)) {
          v.add("ApplicationBelongsTo");
	  }

      }
      else if (nAryEdgesNum == 1) {

        if (selectedEdge instanceof GTPursuesEdge &&
        (GTPursuesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("GTPursues");
        }

        if (selectedEdge instanceof AInheritsEdge &&
        (AInheritsEdge.acceptConnection(this.getModel(), selected))) {
          v.add("AInherits");
        }

        if (selectedEdge instanceof AHasMSEdge &&
        (AHasMSEdge.acceptConnection(this.getModel(), selected))) {
          v.add("AHasMS");
        }

        if (selectedEdge instanceof AHasMSManagerEdge &&
        (AHasMSManagerEdge.acceptConnection(this.getModel(), selected))) {
          v.add("AHasMSManager");
        }

        if (selectedEdge instanceof AHasMSProcessorEdge &&
        (AHasMSProcessorEdge.acceptConnection(this.getModel(), selected))) {
          v.add("AHasMSProcessor");
        }

        if (selectedEdge instanceof WFResponsableEdge &&
        (WFResponsableEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFResponsable");
        }

        if (selectedEdge instanceof AInstanceOfEdge &&
        (AInstanceOfEdge.acceptConnection(this.getModel(), selected))) {
          v.add("AInstanceOf");
        }

        if (selectedEdge instanceof AContainsMEEdge &&
        (AContainsMEEdge.acceptConnection(this.getModel(), selected))) {
          v.add("AContainsME");
        }

        if (selectedEdge instanceof WFPlaysEdge &&
        (WFPlaysEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFPlays");
        }

        if (selectedEdge instanceof ARoleInheritanceEdge &&
        (ARoleInheritanceEdge.acceptConnection(this.getModel(), selected))) {
          v.add("ARoleInheritance");
        }

        if (selectedEdge instanceof UMLAnnotatedElementEdge &&
        (UMLAnnotatedElementEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UMLAnnotatedElement");
        }

        if (selectedEdge instanceof WFUsesEdge &&
        (WFUsesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFUses");
        }

        if (selectedEdge instanceof EResourceBelongsToEdge &&
        (EResourceBelongsToEdge.acceptConnection(this.getModel(), selected))) {
          v.add("EResourceBelongsTo");
        }

        if (selectedEdge instanceof ApplicationBelongsToEdge &&
        (ApplicationBelongsToEdge.acceptConnection(this.getModel(), selected))) {
          v.add("ApplicationBelongsTo");
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

      if (relacion.equalsIgnoreCase("AInherits")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof AInheritsEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new AInheritsEdge(new ingenias.editor.entities.AInherits(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("AHasMS")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof AHasMSEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new AHasMSEdge(new ingenias.editor.entities.AHasMS(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("AHasMSManager")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof AHasMSManagerEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new AHasMSManagerEdge(new ingenias.editor.entities.AHasMSManager(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("AHasMSProcessor")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof AHasMSProcessorEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new AHasMSProcessorEdge(new ingenias.editor.entities.AHasMSProcessor(getMJGraph().getNewId()));
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

      if (relacion.equalsIgnoreCase("AInstanceOf")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof AInstanceOfEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new AInstanceOfEdge(new ingenias.editor.entities.AInstanceOf(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("AContainsME")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof AContainsMEEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new AContainsMEEdge(new ingenias.editor.entities.AContainsME(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("WFPlays")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFPlaysEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFPlaysEdge(new ingenias.editor.entities.WFPlays(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("ARoleInheritance")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof ARoleInheritanceEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new ARoleInheritanceEdge(new ingenias.editor.entities.ARoleInheritance(getMJGraph().getNewId()));
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

      if (relacion.equalsIgnoreCase("EResourceBelongsTo")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof EResourceBelongsToEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new EResourceBelongsToEdge(new ingenias.editor.entities.EResourceBelongsTo(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("ApplicationBelongsTo")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof ApplicationBelongsToEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new ApplicationBelongsToEdge(new ingenias.editor.entities.ApplicationBelongsTo(getMJGraph().getNewId()));
        }
      }

    }

    return null;
  }
  
  public DefaultGraphCell createCell(String entity) throws InvalidEntity{
  
    if (entity.equalsIgnoreCase("FAERIEContext")) {
    FAERIEContext nentity=getOM().createFAERIEContext(getMJGraph().getNewId("FAERIEContext"));
      DefaultGraphCell vertex = new
          FAERIEContextCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Agent")) {
    Agent nentity=getOM().createAgent(getMJGraph().getNewId("Agent"));
      DefaultGraphCell vertex = new
          AgentCell(nentity);
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

    if (entity.equalsIgnoreCase("Plan")) {
    Plan nentity=getOM().createPlan(getMJGraph().getNewId("Plan"));
      DefaultGraphCell vertex = new
          PlanCell(nentity);
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

    if (entity.equalsIgnoreCase("Fact")) {
    Fact nentity=getOM().createFact(getMJGraph().getNewId("Fact"));
      DefaultGraphCell vertex = new
          FactCell(nentity);
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

    if (entity.equalsIgnoreCase("MentalStateProcessor")) {
    MentalStateProcessor nentity=getOM().createMentalStateProcessor(getMJGraph().getNewId("MentalStateProcessor"));
      DefaultGraphCell vertex = new
          MentalStateProcessorCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("MentalStateManager")) {
    MentalStateManager nentity=getOM().createMentalStateManager(getMJGraph().getNewId("MentalStateManager"));
      DefaultGraphCell vertex = new
          MentalStateManagerCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("MentalState")) {
    MentalState nentity=getOM().createMentalState(getMJGraph().getNewId("MentalState"));
      DefaultGraphCell vertex = new
          MentalStateCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("ConditionalMentalState")) {
    ConditionalMentalState nentity=getOM().createConditionalMentalState(getMJGraph().getNewId("ConditionalMentalState"));
      DefaultGraphCell vertex = new
          ConditionalMentalStateCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("AutonomousEntityQuery")) {
    AutonomousEntityQuery nentity=getOM().createAutonomousEntityQuery(getMJGraph().getNewId("AutonomousEntityQuery"));
      DefaultGraphCell vertex = new
          AutonomousEntityQueryCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("AgentRequirementsQuery")) {
    AgentRequirementsQuery nentity=getOM().createAgentRequirementsQuery(getMJGraph().getNewId("AgentRequirementsQuery"));
      DefaultGraphCell vertex = new
          AgentRequirementsQueryCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("ConcreteAgent")) {
    ConcreteAgent nentity=getOM().createConcreteAgent(getMJGraph().getNewId("ConcreteAgent"));
      DefaultGraphCell vertex = new
          ConcreteAgentCell(nentity);
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

    if (entity.equalsIgnoreCase("TextNote")) {
    TextNote nentity=getOM().createTextNote(getMJGraph().getNewId("TextNote"));
      DefaultGraphCell vertex = new
          TextNoteCell(nentity);
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

    if (entity.equalsIgnoreCase("AgentWS")) {
    AgentWS nentity=getOM().createAgentWS(getMJGraph().getNewId("AgentWS"));
      DefaultGraphCell vertex = new
          AgentWSCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("MentalInstanceSpecification")) {
    MentalInstanceSpecification nentity=getOM().createMentalInstanceSpecification(getMJGraph().getNewId("MentalInstanceSpecification"));
      DefaultGraphCell vertex = new
          MentalInstanceSpecificationCell(nentity);
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

    if (entity.equalsIgnoreCase("RuntimeCommFailure")) {
    RuntimeCommFailure nentity=getOM().createRuntimeCommFailure(getMJGraph().getNewId("RuntimeCommFailure"));
      DefaultGraphCell vertex = new
          RuntimeCommFailureCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("FAERIECtxtRelationship")) {
    FAERIECtxtRelationship nentity=getOM().createFAERIECtxtRelationship(getMJGraph().getNewId("FAERIECtxtRelationship"));
      DefaultGraphCell vertex = new
          FAERIECtxtRelationshipCell(nentity);
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

    if (entity.equalsIgnoreCase("FAERIECtxtEntity")) {
    FAERIECtxtEntity nentity=getOM().createFAERIECtxtEntity(getMJGraph().getNewId("FAERIECtxtEntity"));
      DefaultGraphCell vertex = new
          FAERIECtxtEntityCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("FAERIECtxtAttribute")) {
    FAERIECtxtAttribute nentity=getOM().createFAERIECtxtAttribute(getMJGraph().getNewId("FAERIECtxtAttribute"));
      DefaultGraphCell vertex = new
          FAERIECtxtAttributeCell(nentity);
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

    if (entity.equalsIgnoreCase("BoxedTask")) {
    BoxedTask nentity=getOM().createBoxedTask(getMJGraph().getNewId("BoxedTask"));
      DefaultGraphCell vertex = new
          BoxedTaskCell(nentity);
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

    if (entity.equalsIgnoreCase("AMIContext")) {
    AMIContext nentity=getOM().createAMIContext(getMJGraph().getNewId("AMIContext"));
      DefaultGraphCell vertex = new
          AMIContextCell(nentity);
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

    if (entity.equalsIgnoreCase("ContextUseTask")) {
    ContextUseTask nentity=getOM().createContextUseTask(getMJGraph().getNewId("ContextUseTask"));
      DefaultGraphCell vertex = new
          ContextUseTaskCell(nentity);
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

	  throw new ingenias.exception.InvalidEntity("Entity type "+entity+" is not allowed in this diagram"); 
  }
  
  public Dimension getDefaultSize(Entity entity) throws InvalidEntity{
    
    if (entity.getType().equalsIgnoreCase("FAERIEContext")) {
      return FAERIEContextView.getSize((ingenias.editor.entities.FAERIEContext)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Agent")) {
      return AgentView.getSize((ingenias.editor.entities.Agent)entity);      
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

    if (entity.getType().equalsIgnoreCase("Plan")) {
      return PlanView.getSize((ingenias.editor.entities.Plan)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("StateGoal")) {
      return StateGoalView.getSize((ingenias.editor.entities.StateGoal)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Fact")) {
      return FactView.getSize((ingenias.editor.entities.Fact)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Task")) {
      return TaskView.getSize((ingenias.editor.entities.Task)entity);      
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

    if (entity.getType().equalsIgnoreCase("ApplicationEvent")) {
      return ApplicationEventView.getSize((ingenias.editor.entities.ApplicationEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ApplicationEventSlots")) {
      return ApplicationEventSlotsView.getSize((ingenias.editor.entities.ApplicationEventSlots)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("MentalStateProcessor")) {
      return MentalStateProcessorView.getSize((ingenias.editor.entities.MentalStateProcessor)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("MentalStateManager")) {
      return MentalStateManagerView.getSize((ingenias.editor.entities.MentalStateManager)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("MentalState")) {
      return MentalStateView.getSize((ingenias.editor.entities.MentalState)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ConditionalMentalState")) {
      return ConditionalMentalStateView.getSize((ingenias.editor.entities.ConditionalMentalState)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AutonomousEntityQuery")) {
      return AutonomousEntityQueryView.getSize((ingenias.editor.entities.AutonomousEntityQuery)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AgentRequirementsQuery")) {
      return AgentRequirementsQueryView.getSize((ingenias.editor.entities.AgentRequirementsQuery)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ConcreteAgent")) {
      return ConcreteAgentView.getSize((ingenias.editor.entities.ConcreteAgent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AgentModelBelieve")) {
      return AgentModelBelieveView.getSize((ingenias.editor.entities.AgentModelBelieve)entity);      
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

    if (entity.getType().equalsIgnoreCase("TextNote")) {
      return TextNoteView.getSize((ingenias.editor.entities.TextNote)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Application")) {
      return ApplicationView.getSize((ingenias.editor.entities.Application)entity);      
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

    if (entity.getType().equalsIgnoreCase("AgentWS")) {
      return AgentWSView.getSize((ingenias.editor.entities.AgentWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("MentalInstanceSpecification")) {
      return MentalInstanceSpecificationView.getSize((ingenias.editor.entities.MentalInstanceSpecification)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ApplicationWS")) {
      return ApplicationWSView.getSize((ingenias.editor.entities.ApplicationWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RuntimeCommFailure")) {
      return RuntimeCommFailureView.getSize((ingenias.editor.entities.RuntimeCommFailure)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIECtxtRelationship")) {
      return FAERIECtxtRelationshipView.getSize((ingenias.editor.entities.FAERIECtxtRelationship)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RuntimeEvent")) {
      return RuntimeEventView.getSize((ingenias.editor.entities.RuntimeEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIECtxtEntity")) {
      return FAERIECtxtEntityView.getSize((ingenias.editor.entities.FAERIECtxtEntity)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIECtxtAttribute")) {
      return FAERIECtxtAttributeView.getSize((ingenias.editor.entities.FAERIECtxtAttribute)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Interaction")) {
      return InteractionView.getSize((ingenias.editor.entities.Interaction)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("BoxedTask")) {
      return BoxedTaskView.getSize((ingenias.editor.entities.BoxedTask)entity);      
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

    if (entity.getType().equalsIgnoreCase("AMIContext")) {
      return AMIContextView.getSize((ingenias.editor.entities.AMIContext)entity);      
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

    if (entity.getType().equalsIgnoreCase("ContextUseTask")) {
      return ContextUseTaskView.getSize((ingenias.editor.entities.ContextUseTask)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RuntimeFact")) {
      return RuntimeFactView.getSize((ingenias.editor.entities.RuntimeFact)entity);      
    }
    else


      if (entity.getType().equalsIgnoreCase("GTPursues")) {
      	return GTPursuesView.getSize((ingenias.editor.entities.GTPursues)entity);
      }

      if (entity.getType().equalsIgnoreCase("AInherits")) {
      	return AInheritsView.getSize((ingenias.editor.entities.AInherits)entity);
      }

      if (entity.getType().equalsIgnoreCase("AHasMS")) {
      	return AHasMSView.getSize((ingenias.editor.entities.AHasMS)entity);
      }

      if (entity.getType().equalsIgnoreCase("AHasMSManager")) {
      	return AHasMSManagerView.getSize((ingenias.editor.entities.AHasMSManager)entity);
      }

      if (entity.getType().equalsIgnoreCase("AHasMSProcessor")) {
      	return AHasMSProcessorView.getSize((ingenias.editor.entities.AHasMSProcessor)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFResponsable")) {
      	return WFResponsableView.getSize((ingenias.editor.entities.WFResponsable)entity);
      }

      if (entity.getType().equalsIgnoreCase("AInstanceOf")) {
      	return AInstanceOfView.getSize((ingenias.editor.entities.AInstanceOf)entity);
      }

      if (entity.getType().equalsIgnoreCase("AContainsME")) {
      	return AContainsMEView.getSize((ingenias.editor.entities.AContainsME)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFPlays")) {
      	return WFPlaysView.getSize((ingenias.editor.entities.WFPlays)entity);
      }

      if (entity.getType().equalsIgnoreCase("ARoleInheritance")) {
      	return ARoleInheritanceView.getSize((ingenias.editor.entities.ARoleInheritance)entity);
      }

      if (entity.getType().equalsIgnoreCase("UMLAnnotatedElement")) {
      	return UMLAnnotatedElementView.getSize((ingenias.editor.entities.UMLAnnotatedElement)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFUses")) {
      	return WFUsesView.getSize((ingenias.editor.entities.WFUses)entity);
      }

      if (entity.getType().equalsIgnoreCase("EResourceBelongsTo")) {
      	return EResourceBelongsToView.getSize((ingenias.editor.entities.EResourceBelongsTo)entity);
      }

      if (entity.getType().equalsIgnoreCase("ApplicationBelongsTo")) {
      	return ApplicationBelongsToView.getSize((ingenias.editor.entities.ApplicationBelongsTo)entity);
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


    if (entity.getClass().equals(FAERIEContext.class)) {
      vertex = new FAERIEContextCell( (FAERIEContext) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIEContextView.getSize((FAERIEContext) entity);
      
    }
    else

    if (entity.getClass().equals(Agent.class)) {
      vertex = new AgentCell( (Agent) entity);
      // Default Size for the new Vertex with the new entity within
      size = AgentView.getSize((Agent) entity);
      
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

    if (entity.getClass().equals(Plan.class)) {
      vertex = new PlanCell( (Plan) entity);
      // Default Size for the new Vertex with the new entity within
      size = PlanView.getSize((Plan) entity);
      
    }
    else

    if (entity.getClass().equals(StateGoal.class)) {
      vertex = new StateGoalCell( (StateGoal) entity);
      // Default Size for the new Vertex with the new entity within
      size = StateGoalView.getSize((StateGoal) entity);
      
    }
    else

    if (entity.getClass().equals(Fact.class)) {
      vertex = new FactCell( (Fact) entity);
      // Default Size for the new Vertex with the new entity within
      size = FactView.getSize((Fact) entity);
      
    }
    else

    if (entity.getClass().equals(Task.class)) {
      vertex = new TaskCell( (Task) entity);
      // Default Size for the new Vertex with the new entity within
      size = TaskView.getSize((Task) entity);
      
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

    if (entity.getClass().equals(MentalStateProcessor.class)) {
      vertex = new MentalStateProcessorCell( (MentalStateProcessor) entity);
      // Default Size for the new Vertex with the new entity within
      size = MentalStateProcessorView.getSize((MentalStateProcessor) entity);
      
    }
    else

    if (entity.getClass().equals(MentalStateManager.class)) {
      vertex = new MentalStateManagerCell( (MentalStateManager) entity);
      // Default Size for the new Vertex with the new entity within
      size = MentalStateManagerView.getSize((MentalStateManager) entity);
      
    }
    else

    if (entity.getClass().equals(MentalState.class)) {
      vertex = new MentalStateCell( (MentalState) entity);
      // Default Size for the new Vertex with the new entity within
      size = MentalStateView.getSize((MentalState) entity);
      
    }
    else

    if (entity.getClass().equals(ConditionalMentalState.class)) {
      vertex = new ConditionalMentalStateCell( (ConditionalMentalState) entity);
      // Default Size for the new Vertex with the new entity within
      size = ConditionalMentalStateView.getSize((ConditionalMentalState) entity);
      
    }
    else

    if (entity.getClass().equals(AutonomousEntityQuery.class)) {
      vertex = new AutonomousEntityQueryCell( (AutonomousEntityQuery) entity);
      // Default Size for the new Vertex with the new entity within
      size = AutonomousEntityQueryView.getSize((AutonomousEntityQuery) entity);
      
    }
    else

    if (entity.getClass().equals(AgentRequirementsQuery.class)) {
      vertex = new AgentRequirementsQueryCell( (AgentRequirementsQuery) entity);
      // Default Size for the new Vertex with the new entity within
      size = AgentRequirementsQueryView.getSize((AgentRequirementsQuery) entity);
      
    }
    else

    if (entity.getClass().equals(ConcreteAgent.class)) {
      vertex = new ConcreteAgentCell( (ConcreteAgent) entity);
      // Default Size for the new Vertex with the new entity within
      size = ConcreteAgentView.getSize((ConcreteAgent) entity);
      
    }
    else

    if (entity.getClass().equals(AgentModelBelieve.class)) {
      vertex = new AgentModelBelieveCell( (AgentModelBelieve) entity);
      // Default Size for the new Vertex with the new entity within
      size = AgentModelBelieveView.getSize((AgentModelBelieve) entity);
      
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

    if (entity.getClass().equals(TextNote.class)) {
      vertex = new TextNoteCell( (TextNote) entity);
      // Default Size for the new Vertex with the new entity within
      size = TextNoteView.getSize((TextNote) entity);
      
    }
    else

    if (entity.getClass().equals(Application.class)) {
      vertex = new ApplicationCell( (Application) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationView.getSize((Application) entity);
      
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

    if (entity.getClass().equals(AgentWS.class)) {
      vertex = new AgentWSCell( (AgentWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = AgentWSView.getSize((AgentWS) entity);
      
    }
    else

    if (entity.getClass().equals(MentalInstanceSpecification.class)) {
      vertex = new MentalInstanceSpecificationCell( (MentalInstanceSpecification) entity);
      // Default Size for the new Vertex with the new entity within
      size = MentalInstanceSpecificationView.getSize((MentalInstanceSpecification) entity);
      
    }
    else

    if (entity.getClass().equals(ApplicationWS.class)) {
      vertex = new ApplicationWSCell( (ApplicationWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationWSView.getSize((ApplicationWS) entity);
      
    }
    else

    if (entity.getClass().equals(RuntimeCommFailure.class)) {
      vertex = new RuntimeCommFailureCell( (RuntimeCommFailure) entity);
      // Default Size for the new Vertex with the new entity within
      size = RuntimeCommFailureView.getSize((RuntimeCommFailure) entity);
      
    }
    else

    if (entity.getClass().equals(FAERIECtxtRelationship.class)) {
      vertex = new FAERIECtxtRelationshipCell( (FAERIECtxtRelationship) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIECtxtRelationshipView.getSize((FAERIECtxtRelationship) entity);
      
    }
    else

    if (entity.getClass().equals(RuntimeEvent.class)) {
      vertex = new RuntimeEventCell( (RuntimeEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = RuntimeEventView.getSize((RuntimeEvent) entity);
      
    }
    else

    if (entity.getClass().equals(FAERIECtxtEntity.class)) {
      vertex = new FAERIECtxtEntityCell( (FAERIECtxtEntity) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIECtxtEntityView.getSize((FAERIECtxtEntity) entity);
      
    }
    else

    if (entity.getClass().equals(FAERIECtxtAttribute.class)) {
      vertex = new FAERIECtxtAttributeCell( (FAERIECtxtAttribute) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIECtxtAttributeView.getSize((FAERIECtxtAttribute) entity);
      
    }
    else

    if (entity.getClass().equals(Interaction.class)) {
      vertex = new InteractionCell( (Interaction) entity);
      // Default Size for the new Vertex with the new entity within
      size = InteractionView.getSize((Interaction) entity);
      
    }
    else

    if (entity.getClass().equals(BoxedTask.class)) {
      vertex = new BoxedTaskCell( (BoxedTask) entity);
      // Default Size for the new Vertex with the new entity within
      size = BoxedTaskView.getSize((BoxedTask) entity);
      
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

    if (entity.getClass().equals(AMIContext.class)) {
      vertex = new AMIContextCell( (AMIContext) entity);
      // Default Size for the new Vertex with the new entity within
      size = AMIContextView.getSize((AMIContext) entity);
      
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

    if (entity.getClass().equals(ContextUseTask.class)) {
      vertex = new ContextUseTaskCell( (ContextUseTask) entity);
      // Default Size for the new Vertex with the new entity within
      size = ContextUseTaskView.getSize((ContextUseTask) entity);
      
    }
    else

    if (entity.getClass().equals(RuntimeFact.class)) {
      vertex = new RuntimeFactCell( (RuntimeFact) entity);
      // Default Size for the new Vertex with the new entity within
      size = RuntimeFactView.getSize((RuntimeFact) entity);
      
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

		

		 AgentModelModelJGraph jg=new  AgentModelModelJGraph(
				(AgentModelDataEntity) this.mde,name, ids.om,
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
