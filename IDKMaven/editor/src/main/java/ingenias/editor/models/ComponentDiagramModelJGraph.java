

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
import java.util.concurrent.TimeUnit;

public class ComponentDiagramModelJGraph extends ModelJGraph {

  private Preferences prefs;
  
 

  public ComponentDiagramModelJGraph(ComponentDiagramDataEntity mde, 
                               String nombre, ObjectManager om, Model
                               m, BasicMarqueeHandler mh, Preferences prefs) {
    super(mde, nombre, m, mh,om);
    this.prefs=prefs;
    
       
                                  ToolTipManager.sharedInstance().registerComponent(this);                        
                                  

    this.getGraphLayoutCache().setFactory(new ingenias.editor.cellfactories.ComponentDiagramCellViewFactory());

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
    toolbar = new FilteredJToolBar("ComponentDiagram");
    toolbar.setFloatable(false);
    ImageIcon undoIcon = null;
    JButton jb = null;


   if (true){
    Image img_INGENIASComponent =
        ImageLoader.getImage("images/micomponent.gif");
    undoIcon = new ImageIcon(img_INGENIASComponent);
    Action INGENIASComponent=
        new AbstractAction("INGENIASComponent", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "INGENIASComponent");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    INGENIASComponent.setEnabled(true);
    jb = new JButton(INGENIASComponent){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("INGENIASComponent");	
    jb.setToolTipText("INGENIASComponent:"+new INGENIASComponent("").getHelpDesc()+"\n\n"+new INGENIASComponent("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
    toolbar.add(jb);
    }

   if (true){
    Image img_INGENIASCodeComponent =
        ImageLoader.getImage("images/miccomponent.gif");
    undoIcon = new ImageIcon(img_INGENIASCodeComponent);
    Action INGENIASCodeComponent=
        new AbstractAction("INGENIASCodeComponent", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "INGENIASCodeComponent");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    INGENIASCodeComponent.setEnabled(true);
    jb = new JButton(INGENIASCodeComponent){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("INGENIASCodeComponent");	
    jb.setToolTipText("INGENIASCodeComponent:"+new INGENIASCodeComponent("").getHelpDesc()+"\n\n"+new INGENIASCodeComponent("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
    toolbar.add(jb);
    }

   if (true){
    Image img_FileSpecPatternMapping =
        ImageLoader.getImage("images/mumlcomment.gif");
    undoIcon = new ImageIcon(img_FileSpecPatternMapping);
    Action FileSpecPatternMapping=
        new AbstractAction("FileSpecPatternMapping", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "FileSpecPatternMapping");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    FileSpecPatternMapping.setEnabled(true);
    jb = new JButton(FileSpecPatternMapping){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("FileSpecPatternMapping");	
    jb.setToolTipText("FileSpecPatternMapping:"+new FileSpecPatternMapping("").getHelpDesc()+"\n\n"+new FileSpecPatternMapping("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
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
    jb = new JButton(Application){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("Application");	
    jb.setToolTipText("Application:"+new Application("").getHelpDesc()+"\n\n"+new Application("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
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
    jb = new JButton(EnvironmentApplication){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("EnvironmentApplication");	
    jb.setToolTipText("EnvironmentApplication:"+new EnvironmentApplication("").getHelpDesc()+"\n\n"+new EnvironmentApplication("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
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
    jb = new JButton(InternalApplication){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("InternalApplication");	
    jb.setToolTipText("InternalApplication:"+new InternalApplication("").getHelpDesc()+"\n\n"+new InternalApplication("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
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
    jb = new JButton(Task){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("Task");	
    jb.setToolTipText("Task:"+new Task("").getHelpDesc()+"\n\n"+new Task("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
    toolbar.add(jb);
    }

   if (true){
    Image img_Test =
        ImageLoader.getImage("images/mdepl.gif");
    undoIcon = new ImageIcon(img_Test);
    Action Test=
        new AbstractAction("Test", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Test");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Test.setEnabled(true);
    jb = new JButton(Test){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("Test");	
    jb.setToolTipText("Test:"+new Test("").getHelpDesc()+"\n\n"+new Test("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
    toolbar.add(jb);
    }

   if (true){
    Image img_WFTest =
        ImageLoader.getImage("images/mdepl.gif");
    undoIcon = new ImageIcon(img_WFTest);
    Action WFTest=
        new AbstractAction("WFTest", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "WFTest");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    WFTest.setEnabled(true);
    jb = new JButton(WFTest){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("WFTest");	
    jb.setToolTipText("WFTest:"+new WFTest("").getHelpDesc()+"\n\n"+new WFTest("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
    toolbar.add(jb);
    }

   if (true){
    Image img_WFTestState =
        ImageLoader.getImage("images/mdepl.gif");
    undoIcon = new ImageIcon(img_WFTestState);
    Action WFTestState=
        new AbstractAction("WFTestState", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "WFTestState");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    WFTestState.setEnabled(true);
    jb = new JButton(WFTestState){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("WFTestState");	
    jb.setToolTipText("WFTestState:"+new WFTestState("").getHelpDesc()+"\n\n"+new WFTestState("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
    toolbar.add(jb);
    }

   if (true){
    Image img_WFTestFinalState =
        ImageLoader.getImage("images/mdepl.gif");
    undoIcon = new ImageIcon(img_WFTestFinalState);
    Action WFTestFinalState=
        new AbstractAction("WFTestFinalState", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "WFTestFinalState");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    WFTestFinalState.setEnabled(true);
    jb = new JButton(WFTestFinalState){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("WFTestFinalState");	
    jb.setToolTipText("WFTestFinalState:"+new WFTestFinalState("").getHelpDesc()+"\n\n"+new WFTestFinalState("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
    toolbar.add(jb);
    }

   if (true){
    Image img_WFTestInitialState =
        ImageLoader.getImage("images/mdepl.gif");
    undoIcon = new ImageIcon(img_WFTestInitialState);
    Action WFTestInitialState=
        new AbstractAction("WFTestInitialState", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "WFTestInitialState");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    WFTestInitialState.setEnabled(true);
    jb = new JButton(WFTestInitialState){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("WFTestInitialState");	
    jb.setToolTipText("WFTestInitialState:"+new WFTestInitialState("").getHelpDesc()+"\n\n"+new WFTestInitialState("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
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
    jb = new JButton(UMLComment){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("UMLComment");	
    jb.setToolTipText("UMLComment:"+new UMLComment("").getHelpDesc()+"\n\n"+new UMLComment("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
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
    jb = new JButton(TaskWS){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("TaskWS");	
    jb.setToolTipText("TaskWS:"+new TaskWS("").getHelpDesc()+"\n\n"+new TaskWS("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
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
    jb = new JButton(ContextUseTask){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("ContextUseTask");	
    jb.setToolTipText("ContextUseTask:"+new ContextUseTask("").getHelpDesc()+"\n\n"+new ContextUseTask("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
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
    jb = new JButton(BoxedTask){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("BoxedTask");	
    jb.setToolTipText("BoxedTask:"+new BoxedTask("").getHelpDesc()+"\n\n"+new BoxedTask("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
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
    jb = new JButton(ContextBindingTask){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("ContextBindingTask");	
    jb.setToolTipText("ContextBindingTask:"+new ContextBindingTask("").getHelpDesc()+"\n\n"+new ContextBindingTask("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
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
    jb = new JButton(ContextReleaseTask){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("ContextReleaseTask");	
    jb.setToolTipText("ContextReleaseTask:"+new ContextReleaseTask("").getHelpDesc()+"\n\n"+new ContextReleaseTask("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
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
    jb = new JButton(Plan){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("Plan");	
    jb.setToolTipText("Plan:"+new Plan("").getHelpDesc()+"\n\n"+new Plan("").getHelpRecom());
    jb.addMouseListener(new MouseAdapter() {
				    final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
				    final int dismissDelayMinutes = (int) TimeUnit.MINUTES.toMillis(10); // 10 minutes
				    @Override
				    public void mouseEntered(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(dismissDelayMinutes);
				    }
				 
				    @Override
				    public void mouseExited(MouseEvent me) {
				        ToolTipManager.sharedInstance().setDismissDelay(defaultDismissTimeout);
				    }
				});
    toolbar.add(jb);
    }

    }

  }

  public Vector<String> getAllowedRelationships(){
   Vector<String> relationships=new   Vector<String>();


          relationships.add("CDUsesCode");

          relationships.add("UMLRealizes");

          relationships.add("UMLAnnotatedElement");

          relationships.add("WFTestAfter");

   return relationships;
  }

  public  Vector<String> getAllowedEntities(){
   Vector<String> entities=new   Vector<String>();


 entities.add("INGENIASComponent");

 entities.add("INGENIASCodeComponent");

 entities.add("FileSpecPatternMapping");

 entities.add("Application");

 entities.add("EnvironmentApplication");

 entities.add("InternalApplication");

 entities.add("Task");

 entities.add("Test");

 entities.add("WFTest");

 entities.add("WFTestState");

 entities.add("WFTestFinalState");

 entities.add("WFTestInitialState");

 entities.add("UMLComment");

 entities.add("TaskWS");

 entities.add("ContextUseTask");

 entities.add("BoxedTask");

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
        if (CDUsesCodeEdge.acceptConnection(this.getModel(), selected)) {
          v.add("CDUsesCode");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (UMLRealizesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UMLRealizes");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (UMLAnnotatedElementEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UMLAnnotatedElement");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (WFTestAfterEdge.acceptConnection(this.getModel(), selected)) {
          v.add("WFTestAfter");
	  }

      }
      else if (nAryEdgesNum == 1) {

        if (selectedEdge instanceof CDUsesCodeEdge &&
        (CDUsesCodeEdge.acceptConnection(this.getModel(), selected))) {
          v.add("CDUsesCode");
        }

        if (selectedEdge instanceof UMLRealizesEdge &&
        (UMLRealizesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UMLRealizes");
        }

        if (selectedEdge instanceof UMLAnnotatedElementEdge &&
        (UMLAnnotatedElementEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UMLAnnotatedElement");
        }

        if (selectedEdge instanceof WFTestAfterEdge &&
        (WFTestAfterEdge.acceptConnection(this.getModel(), selected))) {
          v.add("WFTestAfter");
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

      if (relacion.equalsIgnoreCase("CDUsesCode")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof CDUsesCodeEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new CDUsesCodeEdge(new ingenias.editor.entities.CDUsesCode(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("UMLRealizes")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof UMLRealizesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new UMLRealizesEdge(new ingenias.editor.entities.UMLRealizes(getMJGraph().getNewId()));
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

      if (relacion.equalsIgnoreCase("WFTestAfter")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof WFTestAfterEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new WFTestAfterEdge(new ingenias.editor.entities.WFTestAfter(getMJGraph().getNewId()));
        }
      }

    }

    return null;
  }
  
  public DefaultGraphCell createCell(String entity) throws InvalidEntity{
  
    if (entity.equalsIgnoreCase("INGENIASComponent")) {
    INGENIASComponent nentity=getOM().createINGENIASComponent(getMJGraph().getNewId("INGENIASComponent"));
      DefaultGraphCell vertex = new
          INGENIASComponentCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("INGENIASCodeComponent")) {
    INGENIASCodeComponent nentity=getOM().createINGENIASCodeComponent(getMJGraph().getNewId("INGENIASCodeComponent"));
      DefaultGraphCell vertex = new
          INGENIASCodeComponentCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("FileSpecPatternMapping")) {
    FileSpecPatternMapping nentity=getOM().createFileSpecPatternMapping(getMJGraph().getNewId("FileSpecPatternMapping"));
      DefaultGraphCell vertex = new
          FileSpecPatternMappingCell(nentity);
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

    if (entity.equalsIgnoreCase("Task")) {
    Task nentity=getOM().createTask(getMJGraph().getNewId("Task"));
      DefaultGraphCell vertex = new
          TaskCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Test")) {
    Test nentity=getOM().createTest(getMJGraph().getNewId("Test"));
      DefaultGraphCell vertex = new
          TestCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("WFTest")) {
    WFTest nentity=getOM().createWFTest(getMJGraph().getNewId("WFTest"));
      DefaultGraphCell vertex = new
          WFTestCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("WFTestState")) {
    WFTestState nentity=getOM().createWFTestState(getMJGraph().getNewId("WFTestState"));
      DefaultGraphCell vertex = new
          WFTestStateCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("WFTestFinalState")) {
    WFTestFinalState nentity=getOM().createWFTestFinalState(getMJGraph().getNewId("WFTestFinalState"));
      DefaultGraphCell vertex = new
          WFTestFinalStateCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("WFTestInitialState")) {
    WFTestInitialState nentity=getOM().createWFTestInitialState(getMJGraph().getNewId("WFTestInitialState"));
      DefaultGraphCell vertex = new
          WFTestInitialStateCell(nentity);
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

    if (entity.equalsIgnoreCase("TaskWS")) {
    TaskWS nentity=getOM().createTaskWS(getMJGraph().getNewId("TaskWS"));
      DefaultGraphCell vertex = new
          TaskWSCell(nentity);
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

    if (entity.equalsIgnoreCase("BoxedTask")) {
    BoxedTask nentity=getOM().createBoxedTask(getMJGraph().getNewId("BoxedTask"));
      DefaultGraphCell vertex = new
          BoxedTaskCell(nentity);
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
    
    if (entity.getType().equalsIgnoreCase("INGENIASComponent")) {
      return INGENIASComponentView.getSize((ingenias.editor.entities.INGENIASComponent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("INGENIASCodeComponent")) {
      return INGENIASCodeComponentView.getSize((ingenias.editor.entities.INGENIASCodeComponent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FileSpecPatternMapping")) {
      return FileSpecPatternMappingView.getSize((ingenias.editor.entities.FileSpecPatternMapping)entity);      
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

    if (entity.getType().equalsIgnoreCase("Task")) {
      return TaskView.getSize((ingenias.editor.entities.Task)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Test")) {
      return TestView.getSize((ingenias.editor.entities.Test)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("WFTest")) {
      return WFTestView.getSize((ingenias.editor.entities.WFTest)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("WFTestState")) {
      return WFTestStateView.getSize((ingenias.editor.entities.WFTestState)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("WFTestFinalState")) {
      return WFTestFinalStateView.getSize((ingenias.editor.entities.WFTestFinalState)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("WFTestInitialState")) {
      return WFTestInitialStateView.getSize((ingenias.editor.entities.WFTestInitialState)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("UMLComment")) {
      return UMLCommentView.getSize((ingenias.editor.entities.UMLComment)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("TaskWS")) {
      return TaskWSView.getSize((ingenias.editor.entities.TaskWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ContextUseTask")) {
      return ContextUseTaskView.getSize((ingenias.editor.entities.ContextUseTask)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("BoxedTask")) {
      return BoxedTaskView.getSize((ingenias.editor.entities.BoxedTask)entity);      
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


      if (entity.getType().equalsIgnoreCase("CDUsesCode")) {
      	return CDUsesCodeView.getSize((ingenias.editor.entities.CDUsesCode)entity);
      }

      if (entity.getType().equalsIgnoreCase("UMLRealizes")) {
      	return UMLRealizesView.getSize((ingenias.editor.entities.UMLRealizes)entity);
      }

      if (entity.getType().equalsIgnoreCase("UMLAnnotatedElement")) {
      	return UMLAnnotatedElementView.getSize((ingenias.editor.entities.UMLAnnotatedElement)entity);
      }

      if (entity.getType().equalsIgnoreCase("WFTestAfter")) {
      	return WFTestAfterView.getSize((ingenias.editor.entities.WFTestAfter)entity);
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


    if (entity.getClass().equals(INGENIASComponent.class)) {
      vertex = new INGENIASComponentCell( (INGENIASComponent) entity);
      // Default Size for the new Vertex with the new entity within
      size = INGENIASComponentView.getSize((INGENIASComponent) entity);
      
    }
    else

    if (entity.getClass().equals(INGENIASCodeComponent.class)) {
      vertex = new INGENIASCodeComponentCell( (INGENIASCodeComponent) entity);
      // Default Size for the new Vertex with the new entity within
      size = INGENIASCodeComponentView.getSize((INGENIASCodeComponent) entity);
      
    }
    else

    if (entity.getClass().equals(FileSpecPatternMapping.class)) {
      vertex = new FileSpecPatternMappingCell( (FileSpecPatternMapping) entity);
      // Default Size for the new Vertex with the new entity within
      size = FileSpecPatternMappingView.getSize((FileSpecPatternMapping) entity);
      
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

    if (entity.getClass().equals(Task.class)) {
      vertex = new TaskCell( (Task) entity);
      // Default Size for the new Vertex with the new entity within
      size = TaskView.getSize((Task) entity);
      
    }
    else

    if (entity.getClass().equals(Test.class)) {
      vertex = new TestCell( (Test) entity);
      // Default Size for the new Vertex with the new entity within
      size = TestView.getSize((Test) entity);
      
    }
    else

    if (entity.getClass().equals(WFTest.class)) {
      vertex = new WFTestCell( (WFTest) entity);
      // Default Size for the new Vertex with the new entity within
      size = WFTestView.getSize((WFTest) entity);
      
    }
    else

    if (entity.getClass().equals(WFTestState.class)) {
      vertex = new WFTestStateCell( (WFTestState) entity);
      // Default Size for the new Vertex with the new entity within
      size = WFTestStateView.getSize((WFTestState) entity);
      
    }
    else

    if (entity.getClass().equals(WFTestFinalState.class)) {
      vertex = new WFTestFinalStateCell( (WFTestFinalState) entity);
      // Default Size for the new Vertex with the new entity within
      size = WFTestFinalStateView.getSize((WFTestFinalState) entity);
      
    }
    else

    if (entity.getClass().equals(WFTestInitialState.class)) {
      vertex = new WFTestInitialStateCell( (WFTestInitialState) entity);
      // Default Size for the new Vertex with the new entity within
      size = WFTestInitialStateView.getSize((WFTestInitialState) entity);
      
    }
    else

    if (entity.getClass().equals(UMLComment.class)) {
      vertex = new UMLCommentCell( (UMLComment) entity);
      // Default Size for the new Vertex with the new entity within
      size = UMLCommentView.getSize((UMLComment) entity);
      
    }
    else

    if (entity.getClass().equals(TaskWS.class)) {
      vertex = new TaskWSCell( (TaskWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = TaskWSView.getSize((TaskWS) entity);
      
    }
    else

    if (entity.getClass().equals(ContextUseTask.class)) {
      vertex = new ContextUseTaskCell( (ContextUseTask) entity);
      // Default Size for the new Vertex with the new entity within
      size = ContextUseTaskView.getSize((ContextUseTask) entity);
      
    }
    else

    if (entity.getClass().equals(BoxedTask.class)) {
      vertex = new BoxedTaskCell( (BoxedTask) entity);
      // Default Size for the new Vertex with the new entity within
      size = BoxedTaskView.getSize((BoxedTask) entity);
      
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

		

		 ComponentDiagramModelJGraph jg=new  ComponentDiagramModelJGraph(
				(ComponentDiagramDataEntity) this.mde,name, ids.om,
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
