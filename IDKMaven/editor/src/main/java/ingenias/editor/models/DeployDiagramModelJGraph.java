

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

public class DeployDiagramModelJGraph extends ModelJGraph {

  private Preferences prefs;
  
 

  public DeployDiagramModelJGraph(DeployDiagramDataEntity mde, 
                               String nombre, ObjectManager om, Model
                               m, BasicMarqueeHandler mh, Preferences prefs) {
    super(mde, nombre, m, mh,om);
    this.prefs=prefs;
    
       
                                  ToolTipManager.sharedInstance().registerComponent(this);        
                                  

    this.getGraphLayoutCache().setFactory(new ingenias.editor.cellfactories.DeployDiagramCellViewFactory());

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
    toolbar = new FilteredJToolBar("DeployDiagram");
    toolbar.setFloatable(false);
    ImageIcon undoIcon = null;
    JButton jb = null;


   if (true){
    Image img_TestingPackage =
        ImageLoader.getImage("images/mdepl.gif");
    undoIcon = new ImageIcon(img_TestingPackage);
    Action TestingPackage=
        new AbstractAction("TestingPackage", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "TestingPackage");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    TestingPackage.setEnabled(true);
    jb = new JButton(TestingPackage){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("TestingPackage");	
    jb.setToolTipText("TestingPackage:"+new TestingPackage("").getHelpDesc()+"\n\n"+new TestingPackage("").getHelpRecom());
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
    Image img_DeploymentPackage =
        ImageLoader.getImage("images/mdepl.gif");
    undoIcon = new ImageIcon(img_DeploymentPackage);
    Action DeploymentPackage=
        new AbstractAction("DeploymentPackage", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "DeploymentPackage");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    DeploymentPackage.setEnabled(true);
    jb = new JButton(DeploymentPackage){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("DeploymentPackage");	
    jb.setToolTipText("DeploymentPackage:"+new DeploymentPackage("").getHelpDesc()+"\n\n"+new DeploymentPackage("").getHelpRecom());
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
    Image img_DeploymentPackageWithContext =
        ImageLoader.getImage("images/mdepl.gif");
    undoIcon = new ImageIcon(img_DeploymentPackageWithContext);
    Action DeploymentPackageWithContext=
        new AbstractAction("DeploymentPackageWithContext", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "DeploymentPackageWithContext");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    DeploymentPackageWithContext.setEnabled(true);
    jb = new JButton(DeploymentPackageWithContext){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("DeploymentPackageWithContext");	
    jb.setToolTipText("DeploymentPackageWithContext:"+new DeploymentPackageWithContext("").getHelpDesc()+"\n\n"+new DeploymentPackageWithContext("").getHelpRecom());
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
    Image img_SimulationPackage =
        ImageLoader.getImage("images/mdepl.gif");
    undoIcon = new ImageIcon(img_SimulationPackage);
    Action SimulationPackage=
        new AbstractAction("SimulationPackage", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "SimulationPackage");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    SimulationPackage.setEnabled(true);
    jb = new JButton(SimulationPackage){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("SimulationPackage");	
    jb.setToolTipText("SimulationPackage:"+new SimulationPackage("").getHelpDesc()+"\n\n"+new SimulationPackage("").getHelpRecom());
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
    Image img_SimulationEvent =
        ImageLoader.getImage("images/miusecase.gif");
    undoIcon = new ImageIcon(img_SimulationEvent);
    Action SimulationEvent=
        new AbstractAction("SimulationEvent", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "SimulationEvent");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    SimulationEvent.setEnabled(true);
    jb = new JButton(SimulationEvent){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("SimulationEvent");	
    jb.setToolTipText("SimulationEvent:"+new SimulationEvent("").getHelpDesc()+"\n\n"+new SimulationEvent("").getHelpRecom());
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
    Image img_SimExtractedInformation =
        ImageLoader.getImage("images/miusecase.gif");
    undoIcon = new ImageIcon(img_SimExtractedInformation);
    Action SimExtractedInformation=
        new AbstractAction("SimExtractedInformation", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "SimExtractedInformation");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    SimExtractedInformation.setEnabled(true);
    jb = new JButton(SimExtractedInformation){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("SimExtractedInformation");	
    jb.setToolTipText("SimExtractedInformation:"+new SimExtractedInformation("").getHelpDesc()+"\n\n"+new SimExtractedInformation("").getHelpRecom());
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
    jb = new JButton(Goal){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("Goal");	
    jb.setToolTipText("Goal:"+new Goal("").getHelpDesc()+"\n\n"+new Goal("").getHelpRecom());
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
    Image img_DeploymentUnitByType =
        ImageLoader.getImage("images/mtypedepl.gif");
    undoIcon = new ImageIcon(img_DeploymentUnitByType);
    Action DeploymentUnitByType=
        new AbstractAction("DeploymentUnitByType", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "DeploymentUnitByType");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    DeploymentUnitByType.setEnabled(true);
    jb = new JButton(DeploymentUnitByType){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("DeploymentUnitByType");	
    jb.setToolTipText("DeploymentUnitByType:"+new DeploymentUnitByType("").getHelpDesc()+"\n\n"+new DeploymentUnitByType("").getHelpRecom());
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
    Image img_DeploymentUnitByTypeEnumInitMS =
        ImageLoader.getImage("images/mimtypedepl.gif");
    undoIcon = new ImageIcon(img_DeploymentUnitByTypeEnumInitMS);
    Action DeploymentUnitByTypeEnumInitMS=
        new AbstractAction("DeploymentUnitByTypeEnumInitMS", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "DeploymentUnitByTypeEnumInitMS");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    DeploymentUnitByTypeEnumInitMS.setEnabled(true);
    jb = new JButton(DeploymentUnitByTypeEnumInitMS){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("DeploymentUnitByTypeEnumInitMS");	
    jb.setToolTipText("DeploymentUnitByTypeEnumInitMS:"+new DeploymentUnitByTypeEnumInitMS("").getHelpDesc()+"\n\n"+new DeploymentUnitByTypeEnumInitMS("").getHelpRecom());
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
    Image img_DeploymentUnitByTypeMSEntity =
        ImageLoader.getImage("images/mimtypedepl.gif");
    undoIcon = new ImageIcon(img_DeploymentUnitByTypeMSEntity);
    Action DeploymentUnitByTypeMSEntity=
        new AbstractAction("DeploymentUnitByTypeMSEntity", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "DeploymentUnitByTypeMSEntity");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    DeploymentUnitByTypeMSEntity.setEnabled(true);
    jb = new JButton(DeploymentUnitByTypeMSEntity){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("DeploymentUnitByTypeMSEntity");	
    jb.setToolTipText("DeploymentUnitByTypeMSEntity:"+new DeploymentUnitByTypeMSEntity("").getHelpDesc()+"\n\n"+new DeploymentUnitByTypeMSEntity("").getHelpRecom());
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
    Image img_FAERIECtxtModelInst =
        ImageLoader.getImage("images/mdepl.gif");
    undoIcon = new ImageIcon(img_FAERIECtxtModelInst);
    Action FAERIECtxtModelInst=
        new AbstractAction("FAERIECtxtModelInst", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "FAERIECtxtModelInst");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    FAERIECtxtModelInst.setEnabled(true);
    jb = new JButton(FAERIECtxtModelInst){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("FAERIECtxtModelInst");	
    jb.setToolTipText("FAERIECtxtModelInst:"+new FAERIECtxtModelInst("").getHelpDesc()+"\n\n"+new FAERIECtxtModelInst("").getHelpRecom());
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
    jb = new JButton(FAERIEContext){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("FAERIEContext");	
    jb.setToolTipText("FAERIEContext:"+new FAERIEContext("").getHelpDesc()+"\n\n"+new FAERIEContext("").getHelpRecom());
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
    Image img_OrgDeploymentUnit =
        ImageLoader.getImage("images/mtypedepl.gif");
    undoIcon = new ImageIcon(img_OrgDeploymentUnit);
    Action OrgDeploymentUnit=
        new AbstractAction("OrgDeploymentUnit", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "OrgDeploymentUnit");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    OrgDeploymentUnit.setEnabled(true);
    jb = new JButton(OrgDeploymentUnit){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("OrgDeploymentUnit");	
    jb.setToolTipText("OrgDeploymentUnit:"+new OrgDeploymentUnit("").getHelpDesc()+"\n\n"+new OrgDeploymentUnit("").getHelpRecom());
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
    Image img_GroupDeploymentUnit =
        ImageLoader.getImage("images/mtypedepl.gif");
    undoIcon = new ImageIcon(img_GroupDeploymentUnit);
    Action GroupDeploymentUnit=
        new AbstractAction("GroupDeploymentUnit", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "GroupDeploymentUnit");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    GroupDeploymentUnit.setEnabled(true);
    jb = new JButton(GroupDeploymentUnit){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("GroupDeploymentUnit");	
    jb.setToolTipText("GroupDeploymentUnit:"+new GroupDeploymentUnit("").getHelpDesc()+"\n\n"+new GroupDeploymentUnit("").getHelpRecom());
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
    jb = new JButton(Fact){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("Fact");	
    jb.setToolTipText("Fact:"+new Fact("").getHelpDesc()+"\n\n"+new Fact("").getHelpRecom());
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
    Image img_OrganizationGroup =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_OrganizationGroup);
    Action OrganizationGroup=
        new AbstractAction("OrganizationGroup", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "OrganizationGroup");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    OrganizationGroup.setEnabled(true);
    jb = new JButton(OrganizationGroup){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("OrganizationGroup");	
    jb.setToolTipText("OrganizationGroup:"+new OrganizationGroup("").getHelpDesc()+"\n\n"+new OrganizationGroup("").getHelpRecom());
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
    jb = new JButton(RuntimeCommFailure){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("RuntimeCommFailure");	
    jb.setToolTipText("RuntimeCommFailure:"+new RuntimeCommFailure("").getHelpDesc()+"\n\n"+new RuntimeCommFailure("").getHelpRecom());
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
    jb = new JButton(FAERIECtxtRelationship){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("FAERIECtxtRelationship");	
    jb.setToolTipText("FAERIECtxtRelationship:"+new FAERIECtxtRelationship("").getHelpDesc()+"\n\n"+new FAERIECtxtRelationship("").getHelpRecom());
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
    Image img_Organization =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_Organization);
    Action Organization=
        new AbstractAction("Organization", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Organization");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Organization.setEnabled(true);
    jb = new JButton(Organization){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("Organization");	
    jb.setToolTipText("Organization:"+new Organization("").getHelpDesc()+"\n\n"+new Organization("").getHelpRecom());
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
    jb = new JButton(ApplicationEvent){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("ApplicationEvent");	
    jb.setToolTipText("ApplicationEvent:"+new ApplicationEvent("").getHelpDesc()+"\n\n"+new ApplicationEvent("").getHelpRecom());
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
    Image img_MentalState =
        ImageLoader.getImage("");
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
    jb = new JButton(MentalState){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("MentalState");	
    jb.setToolTipText("MentalState:"+new MentalState("").getHelpDesc()+"\n\n"+new MentalState("").getHelpRecom());
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
    jb = new JButton(GoalStateWS){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("GoalStateWS");	
    jb.setToolTipText("GoalStateWS:"+new GoalStateWS("").getHelpDesc()+"\n\n"+new GoalStateWS("").getHelpRecom());
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
    Image img_Test =
        ImageLoader.getImage("");
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
    jb = new JButton(RuntimeEvent){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("RuntimeEvent");	
    jb.setToolTipText("RuntimeEvent:"+new RuntimeEvent("").getHelpDesc()+"\n\n"+new RuntimeEvent("").getHelpRecom());
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
    jb = new JButton(FAERIECtxtEntity){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("FAERIECtxtEntity");	
    jb.setToolTipText("FAERIECtxtEntity:"+new FAERIECtxtEntity("").getHelpDesc()+"\n\n"+new FAERIECtxtEntity("").getHelpRecom());
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
    jb = new JButton(ApplicationEventSlots){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("ApplicationEventSlots");	
    jb.setToolTipText("ApplicationEventSlots:"+new ApplicationEventSlots("").getHelpDesc()+"\n\n"+new ApplicationEventSlots("").getHelpRecom());
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
    jb = new JButton(FAERIECtxtAttribute){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("FAERIECtxtAttribute");	
    jb.setToolTipText("FAERIECtxtAttribute:"+new FAERIECtxtAttribute("").getHelpDesc()+"\n\n"+new FAERIECtxtAttribute("").getHelpRecom());
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
    Image img_FileSpecPatternMapping =
        ImageLoader.getImage("");
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
    jb = new JButton(RuntimeConversation){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("RuntimeConversation");	
    jb.setToolTipText("RuntimeConversation:"+new RuntimeConversation("").getHelpDesc()+"\n\n"+new RuntimeConversation("").getHelpRecom());
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
    Image img_Agent =
        ImageLoader.getImage("");
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
    jb = new JButton(Agent){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("Agent");	
    jb.setToolTipText("Agent:"+new Agent("").getHelpDesc()+"\n\n"+new Agent("").getHelpRecom());
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
    jb = new JButton(CommunicationEvent){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("CommunicationEvent");	
    jb.setToolTipText("CommunicationEvent:"+new CommunicationEvent("").getHelpDesc()+"\n\n"+new CommunicationEvent("").getHelpRecom());
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
    jb = new JButton(AMIContext){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("AMIContext");	
    jb.setToolTipText("AMIContext:"+new AMIContext("").getHelpDesc()+"\n\n"+new AMIContext("").getHelpRecom());
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
    Image img_AMIContextInstantiation =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_AMIContextInstantiation);
    Action AMIContextInstantiation=
        new AbstractAction("AMIContextInstantiation", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "AMIContextInstantiation");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    AMIContextInstantiation.setEnabled(true);
    jb = new JButton(AMIContextInstantiation){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("AMIContextInstantiation");	
    jb.setToolTipText("AMIContextInstantiation:"+new AMIContextInstantiation("").getHelpDesc()+"\n\n"+new AMIContextInstantiation("").getHelpRecom());
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
    jb = new JButton(GeneralEvent){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("GeneralEvent");	
    jb.setToolTipText("GeneralEvent:"+new GeneralEvent("").getHelpDesc()+"\n\n"+new GeneralEvent("").getHelpRecom());
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
    Image img_ConditionalMentalState =
        ImageLoader.getImage("");
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
    jb = new JButton(ConditionalMentalState){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("ConditionalMentalState");	
    jb.setToolTipText("ConditionalMentalState:"+new ConditionalMentalState("").getHelpDesc()+"\n\n"+new ConditionalMentalState("").getHelpRecom());
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
    jb = new JButton(StateGoal){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("StateGoal");	
    jb.setToolTipText("StateGoal:"+new StateGoal("").getHelpDesc()+"\n\n"+new StateGoal("").getHelpRecom());
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
    jb = new JButton(Believe){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("Believe");	
    jb.setToolTipText("Believe:"+new Believe("").getHelpDesc()+"\n\n"+new Believe("").getHelpRecom());
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
    Image img_Task =
        ImageLoader.getImage("");
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

   if (false){
    Image img_WFTest =
        ImageLoader.getImage("");
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

   if (false){
    Image img_DeploymentUnitByTypeWithInitMS =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_DeploymentUnitByTypeWithInitMS);
    Action DeploymentUnitByTypeWithInitMS=
        new AbstractAction("DeploymentUnitByTypeWithInitMS", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "DeploymentUnitByTypeWithInitMS");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    DeploymentUnitByTypeWithInitMS.setEnabled(true);
    jb = new JButton(DeploymentUnitByTypeWithInitMS){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("DeploymentUnitByTypeWithInitMS");	
    jb.setToolTipText("DeploymentUnitByTypeWithInitMS:"+new DeploymentUnitByTypeWithInitMS("").getHelpDesc()+"\n\n"+new DeploymentUnitByTypeWithInitMS("").getHelpRecom());
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
    Image img_OrganizationNetwork =
        ImageLoader.getImage("");
    undoIcon = new ImageIcon(img_OrganizationNetwork);
    Action OrganizationNetwork=
        new AbstractAction("OrganizationNetwork", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "OrganizationNetwork");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    OrganizationNetwork.setEnabled(true);
    jb = new JButton(OrganizationNetwork){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("OrganizationNetwork");	
    jb.setToolTipText("OrganizationNetwork:"+new OrganizationNetwork("").getHelpDesc()+"\n\n"+new OrganizationNetwork("").getHelpRecom());
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
    jb = new JButton(FrameFact){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("FrameFact");	
    jb.setToolTipText("FrameFact:"+new FrameFact("").getHelpDesc()+"\n\n"+new FrameFact("").getHelpRecom());
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
    Image img_AgentWS =
        ImageLoader.getImage("");
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
    jb = new JButton(AgentWS){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("AgentWS");	
    jb.setToolTipText("AgentWS:"+new AgentWS("").getHelpDesc()+"\n\n"+new AgentWS("").getHelpRecom());
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
    jb = new JButton(AgentModelBelieve){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("AgentModelBelieve");	
    jb.setToolTipText("AgentModelBelieve:"+new AgentModelBelieve("").getHelpDesc()+"\n\n"+new AgentModelBelieve("").getHelpRecom());
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
    jb = new JButton(Compromise){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("Compromise");	
    jb.setToolTipText("Compromise:"+new Compromise("").getHelpDesc()+"\n\n"+new Compromise("").getHelpRecom());
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
    jb = new JButton(Conversation){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("Conversation");	
    jb.setToolTipText("Conversation:"+new Conversation("").getHelpDesc()+"\n\n"+new Conversation("").getHelpRecom());
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
    Image img_MentalInstanceSpecification =
        ImageLoader.getImage("");
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
    jb = new JButton(MentalInstanceSpecification){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("MentalInstanceSpecification");	
    jb.setToolTipText("MentalInstanceSpecification:"+new MentalInstanceSpecification("").getHelpDesc()+"\n\n"+new MentalInstanceSpecification("").getHelpRecom());
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
    jb = new JButton(RuntimeFact){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("RuntimeFact");	
    jb.setToolTipText("RuntimeFact:"+new RuntimeFact("").getHelpDesc()+"\n\n"+new RuntimeFact("").getHelpRecom());
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


          relationships.add("UMLAnnotatedElement");

          relationships.add("DefinesDeployment");

          relationships.add("CDUsesCode");

          relationships.add("SimulationPursues");

          relationships.add("TestEventInjection");

          relationships.add("OrgDplmnt");

          relationships.add("GroupDplmnt");

          relationships.add("MemberDplmnt");

   return relationships;
  }

  public  Vector<String> getAllowedEntities(){
   Vector<String> entities=new   Vector<String>();


 entities.add("TestingPackage");

 entities.add("DeploymentPackage");

 entities.add("DeploymentPackageWithContext");

 entities.add("INGENIASComponent");

 entities.add("Application");

 entities.add("SimulationPackage");

 entities.add("SimulationEvent");

 entities.add("SimExtractedInformation");

 entities.add("INGENIASCodeComponent");

 entities.add("EnvironmentApplication");

 entities.add("InternalApplication");

 entities.add("UMLComment");

 entities.add("Goal");

 entities.add("DeploymentUnitByType");

 entities.add("DeploymentUnitByTypeEnumInitMS");

 entities.add("DeploymentUnitByTypeMSEntity");

 entities.add("FAERIECtxtModelInst");

 entities.add("FAERIEContext");

 entities.add("WFTestInitialState");

 entities.add("WFTestFinalState");

 entities.add("WFTestState");

 entities.add("OrgDeploymentUnit");

 entities.add("GroupDeploymentUnit");

 entities.add("TaskWS");

 entities.add("Fact");

 entities.add("OrganizationGroup");

 entities.add("RuntimeCommFailure");

 entities.add("FAERIECtxtRelationship");

 entities.add("Organization");

 entities.add("ApplicationEvent");

 entities.add("MentalState");

 entities.add("GoalStateWS");

 entities.add("Test");

 entities.add("RuntimeEvent");

 entities.add("FAERIECtxtEntity");

 entities.add("ApplicationEventSlots");

 entities.add("FAERIECtxtAttribute");

 entities.add("FileSpecPatternMapping");

 entities.add("BoxedTask");

 entities.add("RuntimeConversation");

 entities.add("Agent");

 entities.add("CommunicationEvent");

 entities.add("AMIContext");

 entities.add("AMIContextInstantiation");

 entities.add("ContextReleaseTask");

 entities.add("GeneralEvent");

 entities.add("ContextUseTask");

 entities.add("ConditionalMentalState");

 entities.add("StateGoal");

 entities.add("Believe");

 entities.add("Task");

 entities.add("Plan");

 entities.add("WFTest");

 entities.add("DeploymentUnitByTypeWithInitMS");

 entities.add("OrganizationNetwork");

 entities.add("FrameFact");

 entities.add("AgentWS");

 entities.add("AgentModelBelieve");

 entities.add("Compromise");

 entities.add("Conversation");

 entities.add("MentalInstanceSpecification");

 entities.add("ContextBindingTask");

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
        if (UMLAnnotatedElementEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UMLAnnotatedElement");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (DefinesDeploymentEdge.acceptConnection(this.getModel(), selected)) {
          v.add("DefinesDeployment");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (CDUsesCodeEdge.acceptConnection(this.getModel(), selected)) {
          v.add("CDUsesCode");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (SimulationPursuesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("SimulationPursues");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (TestEventInjectionEdge.acceptConnection(this.getModel(), selected)) {
          v.add("TestEventInjection");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (OrgDplmntEdge.acceptConnection(this.getModel(), selected)) {
          v.add("OrgDplmnt");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (GroupDplmntEdge.acceptConnection(this.getModel(), selected)) {
          v.add("GroupDplmnt");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (MemberDplmntEdge.acceptConnection(this.getModel(), selected)) {
          v.add("MemberDplmnt");
	  }

      }
      else if (nAryEdgesNum == 1) {

        if (selectedEdge instanceof UMLAnnotatedElementEdge &&
        (UMLAnnotatedElementEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UMLAnnotatedElement");
        }

        if (selectedEdge instanceof DefinesDeploymentEdge &&
        (DefinesDeploymentEdge.acceptConnection(this.getModel(), selected))) {
          v.add("DefinesDeployment");
        }

        if (selectedEdge instanceof CDUsesCodeEdge &&
        (CDUsesCodeEdge.acceptConnection(this.getModel(), selected))) {
          v.add("CDUsesCode");
        }

        if (selectedEdge instanceof SimulationPursuesEdge &&
        (SimulationPursuesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("SimulationPursues");
        }

        if (selectedEdge instanceof TestEventInjectionEdge &&
        (TestEventInjectionEdge.acceptConnection(this.getModel(), selected))) {
          v.add("TestEventInjection");
        }

        if (selectedEdge instanceof OrgDplmntEdge &&
        (OrgDplmntEdge.acceptConnection(this.getModel(), selected))) {
          v.add("OrgDplmnt");
        }

        if (selectedEdge instanceof GroupDplmntEdge &&
        (GroupDplmntEdge.acceptConnection(this.getModel(), selected))) {
          v.add("GroupDplmnt");
        }

        if (selectedEdge instanceof MemberDplmntEdge &&
        (MemberDplmntEdge.acceptConnection(this.getModel(), selected))) {
          v.add("MemberDplmnt");
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

      if (relacion.equalsIgnoreCase("DefinesDeployment")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof DefinesDeploymentEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new DefinesDeploymentEdge(new ingenias.editor.entities.DefinesDeployment(getMJGraph().getNewId()));
        }
      }

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

      if (relacion.equalsIgnoreCase("SimulationPursues")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof SimulationPursuesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new SimulationPursuesEdge(new ingenias.editor.entities.SimulationPursues(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("TestEventInjection")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof TestEventInjectionEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new TestEventInjectionEdge(new ingenias.editor.entities.TestEventInjection(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("OrgDplmnt")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof OrgDplmntEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new OrgDplmntEdge(new ingenias.editor.entities.OrgDplmnt(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("GroupDplmnt")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof GroupDplmntEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new GroupDplmntEdge(new ingenias.editor.entities.GroupDplmnt(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("MemberDplmnt")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof MemberDplmntEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new MemberDplmntEdge(new ingenias.editor.entities.MemberDplmnt(getMJGraph().getNewId()));
        }
      }

    }

    return null;
  }
  
  public DefaultGraphCell createCell(String entity) throws InvalidEntity{
  
    if (entity.equalsIgnoreCase("TestingPackage")) {
    TestingPackage nentity=getOM().createTestingPackage(getMJGraph().getNewId("TestingPackage"));
      DefaultGraphCell vertex = new
          TestingPackageCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("DeploymentPackage")) {
    DeploymentPackage nentity=getOM().createDeploymentPackage(getMJGraph().getNewId("DeploymentPackage"));
      DefaultGraphCell vertex = new
          DeploymentPackageCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("DeploymentPackageWithContext")) {
    DeploymentPackageWithContext nentity=getOM().createDeploymentPackageWithContext(getMJGraph().getNewId("DeploymentPackageWithContext"));
      DefaultGraphCell vertex = new
          DeploymentPackageWithContextCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("INGENIASComponent")) {
    INGENIASComponent nentity=getOM().createINGENIASComponent(getMJGraph().getNewId("INGENIASComponent"));
      DefaultGraphCell vertex = new
          INGENIASComponentCell(nentity);
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

    if (entity.equalsIgnoreCase("SimulationPackage")) {
    SimulationPackage nentity=getOM().createSimulationPackage(getMJGraph().getNewId("SimulationPackage"));
      DefaultGraphCell vertex = new
          SimulationPackageCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("SimulationEvent")) {
    SimulationEvent nentity=getOM().createSimulationEvent(getMJGraph().getNewId("SimulationEvent"));
      DefaultGraphCell vertex = new
          SimulationEventCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("SimExtractedInformation")) {
    SimExtractedInformation nentity=getOM().createSimExtractedInformation(getMJGraph().getNewId("SimExtractedInformation"));
      DefaultGraphCell vertex = new
          SimExtractedInformationCell(nentity);
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

    if (entity.equalsIgnoreCase("UMLComment")) {
    UMLComment nentity=getOM().createUMLComment(getMJGraph().getNewId("UMLComment"));
      DefaultGraphCell vertex = new
          UMLCommentCell(nentity);
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

    if (entity.equalsIgnoreCase("DeploymentUnitByType")) {
    DeploymentUnitByType nentity=getOM().createDeploymentUnitByType(getMJGraph().getNewId("DeploymentUnitByType"));
      DefaultGraphCell vertex = new
          DeploymentUnitByTypeCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("DeploymentUnitByTypeEnumInitMS")) {
    DeploymentUnitByTypeEnumInitMS nentity=getOM().createDeploymentUnitByTypeEnumInitMS(getMJGraph().getNewId("DeploymentUnitByTypeEnumInitMS"));
      DefaultGraphCell vertex = new
          DeploymentUnitByTypeEnumInitMSCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("DeploymentUnitByTypeMSEntity")) {
    DeploymentUnitByTypeMSEntity nentity=getOM().createDeploymentUnitByTypeMSEntity(getMJGraph().getNewId("DeploymentUnitByTypeMSEntity"));
      DefaultGraphCell vertex = new
          DeploymentUnitByTypeMSEntityCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("FAERIECtxtModelInst")) {
    FAERIECtxtModelInst nentity=getOM().createFAERIECtxtModelInst(getMJGraph().getNewId("FAERIECtxtModelInst"));
      DefaultGraphCell vertex = new
          FAERIECtxtModelInstCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("FAERIEContext")) {
    FAERIEContext nentity=getOM().createFAERIEContext(getMJGraph().getNewId("FAERIEContext"));
      DefaultGraphCell vertex = new
          FAERIEContextCell(nentity);
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

    if (entity.equalsIgnoreCase("WFTestFinalState")) {
    WFTestFinalState nentity=getOM().createWFTestFinalState(getMJGraph().getNewId("WFTestFinalState"));
      DefaultGraphCell vertex = new
          WFTestFinalStateCell(nentity);
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

    if (entity.equalsIgnoreCase("OrgDeploymentUnit")) {
    OrgDeploymentUnit nentity=getOM().createOrgDeploymentUnit(getMJGraph().getNewId("OrgDeploymentUnit"));
      DefaultGraphCell vertex = new
          OrgDeploymentUnitCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("GroupDeploymentUnit")) {
    GroupDeploymentUnit nentity=getOM().createGroupDeploymentUnit(getMJGraph().getNewId("GroupDeploymentUnit"));
      DefaultGraphCell vertex = new
          GroupDeploymentUnitCell(nentity);
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

    if (entity.equalsIgnoreCase("Fact")) {
    Fact nentity=getOM().createFact(getMJGraph().getNewId("Fact"));
      DefaultGraphCell vertex = new
          FactCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("OrganizationGroup")) {
    OrganizationGroup nentity=getOM().createOrganizationGroup(getMJGraph().getNewId("OrganizationGroup"));
      DefaultGraphCell vertex = new
          OrganizationGroupCell(nentity);
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

    if (entity.equalsIgnoreCase("Organization")) {
    Organization nentity=getOM().createOrganization(getMJGraph().getNewId("Organization"));
      DefaultGraphCell vertex = new
          OrganizationCell(nentity);
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

    if (entity.equalsIgnoreCase("MentalState")) {
    MentalState nentity=getOM().createMentalState(getMJGraph().getNewId("MentalState"));
      DefaultGraphCell vertex = new
          MentalStateCell(nentity);
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

    if (entity.equalsIgnoreCase("Test")) {
    Test nentity=getOM().createTest(getMJGraph().getNewId("Test"));
      DefaultGraphCell vertex = new
          TestCell(nentity);
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

    if (entity.equalsIgnoreCase("ApplicationEventSlots")) {
    ApplicationEventSlots nentity=getOM().createApplicationEventSlots(getMJGraph().getNewId("ApplicationEventSlots"));
      DefaultGraphCell vertex = new
          ApplicationEventSlotsCell(nentity);
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

    if (entity.equalsIgnoreCase("FileSpecPatternMapping")) {
    FileSpecPatternMapping nentity=getOM().createFileSpecPatternMapping(getMJGraph().getNewId("FileSpecPatternMapping"));
      DefaultGraphCell vertex = new
          FileSpecPatternMappingCell(nentity);
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

    if (entity.equalsIgnoreCase("Agent")) {
    Agent nentity=getOM().createAgent(getMJGraph().getNewId("Agent"));
      DefaultGraphCell vertex = new
          AgentCell(nentity);
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

    if (entity.equalsIgnoreCase("AMIContextInstantiation")) {
    AMIContextInstantiation nentity=getOM().createAMIContextInstantiation(getMJGraph().getNewId("AMIContextInstantiation"));
      DefaultGraphCell vertex = new
          AMIContextInstantiationCell(nentity);
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

    if (entity.equalsIgnoreCase("GeneralEvent")) {
    GeneralEvent nentity=getOM().createGeneralEvent(getMJGraph().getNewId("GeneralEvent"));
      DefaultGraphCell vertex = new
          GeneralEventCell(nentity);
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

    if (entity.equalsIgnoreCase("ConditionalMentalState")) {
    ConditionalMentalState nentity=getOM().createConditionalMentalState(getMJGraph().getNewId("ConditionalMentalState"));
      DefaultGraphCell vertex = new
          ConditionalMentalStateCell(nentity);
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

    if (entity.equalsIgnoreCase("Believe")) {
    Believe nentity=getOM().createBelieve(getMJGraph().getNewId("Believe"));
      DefaultGraphCell vertex = new
          BelieveCell(nentity);
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

    if (entity.equalsIgnoreCase("Plan")) {
    Plan nentity=getOM().createPlan(getMJGraph().getNewId("Plan"));
      DefaultGraphCell vertex = new
          PlanCell(nentity);
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

    if (entity.equalsIgnoreCase("DeploymentUnitByTypeWithInitMS")) {
    DeploymentUnitByTypeWithInitMS nentity=getOM().createDeploymentUnitByTypeWithInitMS(getMJGraph().getNewId("DeploymentUnitByTypeWithInitMS"));
      DefaultGraphCell vertex = new
          DeploymentUnitByTypeWithInitMSCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("OrganizationNetwork")) {
    OrganizationNetwork nentity=getOM().createOrganizationNetwork(getMJGraph().getNewId("OrganizationNetwork"));
      DefaultGraphCell vertex = new
          OrganizationNetworkCell(nentity);
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

    if (entity.equalsIgnoreCase("AgentWS")) {
    AgentWS nentity=getOM().createAgentWS(getMJGraph().getNewId("AgentWS"));
      DefaultGraphCell vertex = new
          AgentWSCell(nentity);
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

    if (entity.equalsIgnoreCase("Compromise")) {
    Compromise nentity=getOM().createCompromise(getMJGraph().getNewId("Compromise"));
      DefaultGraphCell vertex = new
          CompromiseCell(nentity);
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

    if (entity.equalsIgnoreCase("MentalInstanceSpecification")) {
    MentalInstanceSpecification nentity=getOM().createMentalInstanceSpecification(getMJGraph().getNewId("MentalInstanceSpecification"));
      DefaultGraphCell vertex = new
          MentalInstanceSpecificationCell(nentity);
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
    
    if (entity.getType().equalsIgnoreCase("TestingPackage")) {
      return TestingPackageView.getSize((ingenias.editor.entities.TestingPackage)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("DeploymentPackage")) {
      return DeploymentPackageView.getSize((ingenias.editor.entities.DeploymentPackage)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("DeploymentPackageWithContext")) {
      return DeploymentPackageWithContextView.getSize((ingenias.editor.entities.DeploymentPackageWithContext)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("INGENIASComponent")) {
      return INGENIASComponentView.getSize((ingenias.editor.entities.INGENIASComponent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Application")) {
      return ApplicationView.getSize((ingenias.editor.entities.Application)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("SimulationPackage")) {
      return SimulationPackageView.getSize((ingenias.editor.entities.SimulationPackage)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("SimulationEvent")) {
      return SimulationEventView.getSize((ingenias.editor.entities.SimulationEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("SimExtractedInformation")) {
      return SimExtractedInformationView.getSize((ingenias.editor.entities.SimExtractedInformation)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("INGENIASCodeComponent")) {
      return INGENIASCodeComponentView.getSize((ingenias.editor.entities.INGENIASCodeComponent)entity);      
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

    if (entity.getType().equalsIgnoreCase("UMLComment")) {
      return UMLCommentView.getSize((ingenias.editor.entities.UMLComment)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Goal")) {
      return GoalView.getSize((ingenias.editor.entities.Goal)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("DeploymentUnitByType")) {
      return DeploymentUnitByTypeView.getSize((ingenias.editor.entities.DeploymentUnitByType)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("DeploymentUnitByTypeEnumInitMS")) {
      return DeploymentUnitByTypeEnumInitMSView.getSize((ingenias.editor.entities.DeploymentUnitByTypeEnumInitMS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("DeploymentUnitByTypeMSEntity")) {
      return DeploymentUnitByTypeMSEntityView.getSize((ingenias.editor.entities.DeploymentUnitByTypeMSEntity)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIECtxtModelInst")) {
      return FAERIECtxtModelInstView.getSize((ingenias.editor.entities.FAERIECtxtModelInst)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIEContext")) {
      return FAERIEContextView.getSize((ingenias.editor.entities.FAERIEContext)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("WFTestInitialState")) {
      return WFTestInitialStateView.getSize((ingenias.editor.entities.WFTestInitialState)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("WFTestFinalState")) {
      return WFTestFinalStateView.getSize((ingenias.editor.entities.WFTestFinalState)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("WFTestState")) {
      return WFTestStateView.getSize((ingenias.editor.entities.WFTestState)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("OrgDeploymentUnit")) {
      return OrgDeploymentUnitView.getSize((ingenias.editor.entities.OrgDeploymentUnit)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("GroupDeploymentUnit")) {
      return GroupDeploymentUnitView.getSize((ingenias.editor.entities.GroupDeploymentUnit)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("TaskWS")) {
      return TaskWSView.getSize((ingenias.editor.entities.TaskWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Fact")) {
      return FactView.getSize((ingenias.editor.entities.Fact)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("OrganizationGroup")) {
      return OrganizationGroupView.getSize((ingenias.editor.entities.OrganizationGroup)entity);      
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

    if (entity.getType().equalsIgnoreCase("Organization")) {
      return OrganizationView.getSize((ingenias.editor.entities.Organization)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ApplicationEvent")) {
      return ApplicationEventView.getSize((ingenias.editor.entities.ApplicationEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("MentalState")) {
      return MentalStateView.getSize((ingenias.editor.entities.MentalState)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("GoalStateWS")) {
      return GoalStateWSView.getSize((ingenias.editor.entities.GoalStateWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Test")) {
      return TestView.getSize((ingenias.editor.entities.Test)entity);      
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

    if (entity.getType().equalsIgnoreCase("ApplicationEventSlots")) {
      return ApplicationEventSlotsView.getSize((ingenias.editor.entities.ApplicationEventSlots)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIECtxtAttribute")) {
      return FAERIECtxtAttributeView.getSize((ingenias.editor.entities.FAERIECtxtAttribute)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FileSpecPatternMapping")) {
      return FileSpecPatternMappingView.getSize((ingenias.editor.entities.FileSpecPatternMapping)entity);      
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

    if (entity.getType().equalsIgnoreCase("Agent")) {
      return AgentView.getSize((ingenias.editor.entities.Agent)entity);      
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

    if (entity.getType().equalsIgnoreCase("AMIContextInstantiation")) {
      return AMIContextInstantiationView.getSize((ingenias.editor.entities.AMIContextInstantiation)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ContextReleaseTask")) {
      return ContextReleaseTaskView.getSize((ingenias.editor.entities.ContextReleaseTask)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("GeneralEvent")) {
      return GeneralEventView.getSize((ingenias.editor.entities.GeneralEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ContextUseTask")) {
      return ContextUseTaskView.getSize((ingenias.editor.entities.ContextUseTask)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ConditionalMentalState")) {
      return ConditionalMentalStateView.getSize((ingenias.editor.entities.ConditionalMentalState)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("StateGoal")) {
      return StateGoalView.getSize((ingenias.editor.entities.StateGoal)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Believe")) {
      return BelieveView.getSize((ingenias.editor.entities.Believe)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Task")) {
      return TaskView.getSize((ingenias.editor.entities.Task)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Plan")) {
      return PlanView.getSize((ingenias.editor.entities.Plan)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("WFTest")) {
      return WFTestView.getSize((ingenias.editor.entities.WFTest)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("DeploymentUnitByTypeWithInitMS")) {
      return DeploymentUnitByTypeWithInitMSView.getSize((ingenias.editor.entities.DeploymentUnitByTypeWithInitMS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("OrganizationNetwork")) {
      return OrganizationNetworkView.getSize((ingenias.editor.entities.OrganizationNetwork)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FrameFact")) {
      return FrameFactView.getSize((ingenias.editor.entities.FrameFact)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AgentWS")) {
      return AgentWSView.getSize((ingenias.editor.entities.AgentWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AgentModelBelieve")) {
      return AgentModelBelieveView.getSize((ingenias.editor.entities.AgentModelBelieve)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Compromise")) {
      return CompromiseView.getSize((ingenias.editor.entities.Compromise)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Conversation")) {
      return ConversationView.getSize((ingenias.editor.entities.Conversation)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("MentalInstanceSpecification")) {
      return MentalInstanceSpecificationView.getSize((ingenias.editor.entities.MentalInstanceSpecification)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ContextBindingTask")) {
      return ContextBindingTaskView.getSize((ingenias.editor.entities.ContextBindingTask)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RuntimeFact")) {
      return RuntimeFactView.getSize((ingenias.editor.entities.RuntimeFact)entity);      
    }
    else


      if (entity.getType().equalsIgnoreCase("UMLAnnotatedElement")) {
      	return UMLAnnotatedElementView.getSize((ingenias.editor.entities.UMLAnnotatedElement)entity);
      }

      if (entity.getType().equalsIgnoreCase("DefinesDeployment")) {
      	return DefinesDeploymentView.getSize((ingenias.editor.entities.DefinesDeployment)entity);
      }

      if (entity.getType().equalsIgnoreCase("CDUsesCode")) {
      	return CDUsesCodeView.getSize((ingenias.editor.entities.CDUsesCode)entity);
      }

      if (entity.getType().equalsIgnoreCase("SimulationPursues")) {
      	return SimulationPursuesView.getSize((ingenias.editor.entities.SimulationPursues)entity);
      }

      if (entity.getType().equalsIgnoreCase("TestEventInjection")) {
      	return TestEventInjectionView.getSize((ingenias.editor.entities.TestEventInjection)entity);
      }

      if (entity.getType().equalsIgnoreCase("OrgDplmnt")) {
      	return OrgDplmntView.getSize((ingenias.editor.entities.OrgDplmnt)entity);
      }

      if (entity.getType().equalsIgnoreCase("GroupDplmnt")) {
      	return GroupDplmntView.getSize((ingenias.editor.entities.GroupDplmnt)entity);
      }

      if (entity.getType().equalsIgnoreCase("MemberDplmnt")) {
      	return MemberDplmntView.getSize((ingenias.editor.entities.MemberDplmnt)entity);
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


    if (entity.getClass().equals(TestingPackage.class)) {
      vertex = new TestingPackageCell( (TestingPackage) entity);
      // Default Size for the new Vertex with the new entity within
      size = TestingPackageView.getSize((TestingPackage) entity);
      
    }
    else

    if (entity.getClass().equals(DeploymentPackage.class)) {
      vertex = new DeploymentPackageCell( (DeploymentPackage) entity);
      // Default Size for the new Vertex with the new entity within
      size = DeploymentPackageView.getSize((DeploymentPackage) entity);
      
    }
    else

    if (entity.getClass().equals(DeploymentPackageWithContext.class)) {
      vertex = new DeploymentPackageWithContextCell( (DeploymentPackageWithContext) entity);
      // Default Size for the new Vertex with the new entity within
      size = DeploymentPackageWithContextView.getSize((DeploymentPackageWithContext) entity);
      
    }
    else

    if (entity.getClass().equals(INGENIASComponent.class)) {
      vertex = new INGENIASComponentCell( (INGENIASComponent) entity);
      // Default Size for the new Vertex with the new entity within
      size = INGENIASComponentView.getSize((INGENIASComponent) entity);
      
    }
    else

    if (entity.getClass().equals(Application.class)) {
      vertex = new ApplicationCell( (Application) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationView.getSize((Application) entity);
      
    }
    else

    if (entity.getClass().equals(SimulationPackage.class)) {
      vertex = new SimulationPackageCell( (SimulationPackage) entity);
      // Default Size for the new Vertex with the new entity within
      size = SimulationPackageView.getSize((SimulationPackage) entity);
      
    }
    else

    if (entity.getClass().equals(SimulationEvent.class)) {
      vertex = new SimulationEventCell( (SimulationEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = SimulationEventView.getSize((SimulationEvent) entity);
      
    }
    else

    if (entity.getClass().equals(SimExtractedInformation.class)) {
      vertex = new SimExtractedInformationCell( (SimExtractedInformation) entity);
      // Default Size for the new Vertex with the new entity within
      size = SimExtractedInformationView.getSize((SimExtractedInformation) entity);
      
    }
    else

    if (entity.getClass().equals(INGENIASCodeComponent.class)) {
      vertex = new INGENIASCodeComponentCell( (INGENIASCodeComponent) entity);
      // Default Size for the new Vertex with the new entity within
      size = INGENIASCodeComponentView.getSize((INGENIASCodeComponent) entity);
      
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

    if (entity.getClass().equals(UMLComment.class)) {
      vertex = new UMLCommentCell( (UMLComment) entity);
      // Default Size for the new Vertex with the new entity within
      size = UMLCommentView.getSize((UMLComment) entity);
      
    }
    else

    if (entity.getClass().equals(Goal.class)) {
      vertex = new GoalCell( (Goal) entity);
      // Default Size for the new Vertex with the new entity within
      size = GoalView.getSize((Goal) entity);
      
    }
    else

    if (entity.getClass().equals(DeploymentUnitByType.class)) {
      vertex = new DeploymentUnitByTypeCell( (DeploymentUnitByType) entity);
      // Default Size for the new Vertex with the new entity within
      size = DeploymentUnitByTypeView.getSize((DeploymentUnitByType) entity);
      
    }
    else

    if (entity.getClass().equals(DeploymentUnitByTypeEnumInitMS.class)) {
      vertex = new DeploymentUnitByTypeEnumInitMSCell( (DeploymentUnitByTypeEnumInitMS) entity);
      // Default Size for the new Vertex with the new entity within
      size = DeploymentUnitByTypeEnumInitMSView.getSize((DeploymentUnitByTypeEnumInitMS) entity);
      
    }
    else

    if (entity.getClass().equals(DeploymentUnitByTypeMSEntity.class)) {
      vertex = new DeploymentUnitByTypeMSEntityCell( (DeploymentUnitByTypeMSEntity) entity);
      // Default Size for the new Vertex with the new entity within
      size = DeploymentUnitByTypeMSEntityView.getSize((DeploymentUnitByTypeMSEntity) entity);
      
    }
    else

    if (entity.getClass().equals(FAERIECtxtModelInst.class)) {
      vertex = new FAERIECtxtModelInstCell( (FAERIECtxtModelInst) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIECtxtModelInstView.getSize((FAERIECtxtModelInst) entity);
      
    }
    else

    if (entity.getClass().equals(FAERIEContext.class)) {
      vertex = new FAERIEContextCell( (FAERIEContext) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIEContextView.getSize((FAERIEContext) entity);
      
    }
    else

    if (entity.getClass().equals(WFTestInitialState.class)) {
      vertex = new WFTestInitialStateCell( (WFTestInitialState) entity);
      // Default Size for the new Vertex with the new entity within
      size = WFTestInitialStateView.getSize((WFTestInitialState) entity);
      
    }
    else

    if (entity.getClass().equals(WFTestFinalState.class)) {
      vertex = new WFTestFinalStateCell( (WFTestFinalState) entity);
      // Default Size for the new Vertex with the new entity within
      size = WFTestFinalStateView.getSize((WFTestFinalState) entity);
      
    }
    else

    if (entity.getClass().equals(WFTestState.class)) {
      vertex = new WFTestStateCell( (WFTestState) entity);
      // Default Size for the new Vertex with the new entity within
      size = WFTestStateView.getSize((WFTestState) entity);
      
    }
    else

    if (entity.getClass().equals(OrgDeploymentUnit.class)) {
      vertex = new OrgDeploymentUnitCell( (OrgDeploymentUnit) entity);
      // Default Size for the new Vertex with the new entity within
      size = OrgDeploymentUnitView.getSize((OrgDeploymentUnit) entity);
      
    }
    else

    if (entity.getClass().equals(GroupDeploymentUnit.class)) {
      vertex = new GroupDeploymentUnitCell( (GroupDeploymentUnit) entity);
      // Default Size for the new Vertex with the new entity within
      size = GroupDeploymentUnitView.getSize((GroupDeploymentUnit) entity);
      
    }
    else

    if (entity.getClass().equals(TaskWS.class)) {
      vertex = new TaskWSCell( (TaskWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = TaskWSView.getSize((TaskWS) entity);
      
    }
    else

    if (entity.getClass().equals(Fact.class)) {
      vertex = new FactCell( (Fact) entity);
      // Default Size for the new Vertex with the new entity within
      size = FactView.getSize((Fact) entity);
      
    }
    else

    if (entity.getClass().equals(OrganizationGroup.class)) {
      vertex = new OrganizationGroupCell( (OrganizationGroup) entity);
      // Default Size for the new Vertex with the new entity within
      size = OrganizationGroupView.getSize((OrganizationGroup) entity);
      
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

    if (entity.getClass().equals(Organization.class)) {
      vertex = new OrganizationCell( (Organization) entity);
      // Default Size for the new Vertex with the new entity within
      size = OrganizationView.getSize((Organization) entity);
      
    }
    else

    if (entity.getClass().equals(ApplicationEvent.class)) {
      vertex = new ApplicationEventCell( (ApplicationEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationEventView.getSize((ApplicationEvent) entity);
      
    }
    else

    if (entity.getClass().equals(MentalState.class)) {
      vertex = new MentalStateCell( (MentalState) entity);
      // Default Size for the new Vertex with the new entity within
      size = MentalStateView.getSize((MentalState) entity);
      
    }
    else

    if (entity.getClass().equals(GoalStateWS.class)) {
      vertex = new GoalStateWSCell( (GoalStateWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = GoalStateWSView.getSize((GoalStateWS) entity);
      
    }
    else

    if (entity.getClass().equals(Test.class)) {
      vertex = new TestCell( (Test) entity);
      // Default Size for the new Vertex with the new entity within
      size = TestView.getSize((Test) entity);
      
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

    if (entity.getClass().equals(ApplicationEventSlots.class)) {
      vertex = new ApplicationEventSlotsCell( (ApplicationEventSlots) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationEventSlotsView.getSize((ApplicationEventSlots) entity);
      
    }
    else

    if (entity.getClass().equals(FAERIECtxtAttribute.class)) {
      vertex = new FAERIECtxtAttributeCell( (FAERIECtxtAttribute) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIECtxtAttributeView.getSize((FAERIECtxtAttribute) entity);
      
    }
    else

    if (entity.getClass().equals(FileSpecPatternMapping.class)) {
      vertex = new FileSpecPatternMappingCell( (FileSpecPatternMapping) entity);
      // Default Size for the new Vertex with the new entity within
      size = FileSpecPatternMappingView.getSize((FileSpecPatternMapping) entity);
      
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

    if (entity.getClass().equals(Agent.class)) {
      vertex = new AgentCell( (Agent) entity);
      // Default Size for the new Vertex with the new entity within
      size = AgentView.getSize((Agent) entity);
      
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

    if (entity.getClass().equals(AMIContextInstantiation.class)) {
      vertex = new AMIContextInstantiationCell( (AMIContextInstantiation) entity);
      // Default Size for the new Vertex with the new entity within
      size = AMIContextInstantiationView.getSize((AMIContextInstantiation) entity);
      
    }
    else

    if (entity.getClass().equals(ContextReleaseTask.class)) {
      vertex = new ContextReleaseTaskCell( (ContextReleaseTask) entity);
      // Default Size for the new Vertex with the new entity within
      size = ContextReleaseTaskView.getSize((ContextReleaseTask) entity);
      
    }
    else

    if (entity.getClass().equals(GeneralEvent.class)) {
      vertex = new GeneralEventCell( (GeneralEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = GeneralEventView.getSize((GeneralEvent) entity);
      
    }
    else

    if (entity.getClass().equals(ContextUseTask.class)) {
      vertex = new ContextUseTaskCell( (ContextUseTask) entity);
      // Default Size for the new Vertex with the new entity within
      size = ContextUseTaskView.getSize((ContextUseTask) entity);
      
    }
    else

    if (entity.getClass().equals(ConditionalMentalState.class)) {
      vertex = new ConditionalMentalStateCell( (ConditionalMentalState) entity);
      // Default Size for the new Vertex with the new entity within
      size = ConditionalMentalStateView.getSize((ConditionalMentalState) entity);
      
    }
    else

    if (entity.getClass().equals(StateGoal.class)) {
      vertex = new StateGoalCell( (StateGoal) entity);
      // Default Size for the new Vertex with the new entity within
      size = StateGoalView.getSize((StateGoal) entity);
      
    }
    else

    if (entity.getClass().equals(Believe.class)) {
      vertex = new BelieveCell( (Believe) entity);
      // Default Size for the new Vertex with the new entity within
      size = BelieveView.getSize((Believe) entity);
      
    }
    else

    if (entity.getClass().equals(Task.class)) {
      vertex = new TaskCell( (Task) entity);
      // Default Size for the new Vertex with the new entity within
      size = TaskView.getSize((Task) entity);
      
    }
    else

    if (entity.getClass().equals(Plan.class)) {
      vertex = new PlanCell( (Plan) entity);
      // Default Size for the new Vertex with the new entity within
      size = PlanView.getSize((Plan) entity);
      
    }
    else

    if (entity.getClass().equals(WFTest.class)) {
      vertex = new WFTestCell( (WFTest) entity);
      // Default Size for the new Vertex with the new entity within
      size = WFTestView.getSize((WFTest) entity);
      
    }
    else

    if (entity.getClass().equals(DeploymentUnitByTypeWithInitMS.class)) {
      vertex = new DeploymentUnitByTypeWithInitMSCell( (DeploymentUnitByTypeWithInitMS) entity);
      // Default Size for the new Vertex with the new entity within
      size = DeploymentUnitByTypeWithInitMSView.getSize((DeploymentUnitByTypeWithInitMS) entity);
      
    }
    else

    if (entity.getClass().equals(OrganizationNetwork.class)) {
      vertex = new OrganizationNetworkCell( (OrganizationNetwork) entity);
      // Default Size for the new Vertex with the new entity within
      size = OrganizationNetworkView.getSize((OrganizationNetwork) entity);
      
    }
    else

    if (entity.getClass().equals(FrameFact.class)) {
      vertex = new FrameFactCell( (FrameFact) entity);
      // Default Size for the new Vertex with the new entity within
      size = FrameFactView.getSize((FrameFact) entity);
      
    }
    else

    if (entity.getClass().equals(AgentWS.class)) {
      vertex = new AgentWSCell( (AgentWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = AgentWSView.getSize((AgentWS) entity);
      
    }
    else

    if (entity.getClass().equals(AgentModelBelieve.class)) {
      vertex = new AgentModelBelieveCell( (AgentModelBelieve) entity);
      // Default Size for the new Vertex with the new entity within
      size = AgentModelBelieveView.getSize((AgentModelBelieve) entity);
      
    }
    else

    if (entity.getClass().equals(Compromise.class)) {
      vertex = new CompromiseCell( (Compromise) entity);
      // Default Size for the new Vertex with the new entity within
      size = CompromiseView.getSize((Compromise) entity);
      
    }
    else

    if (entity.getClass().equals(Conversation.class)) {
      vertex = new ConversationCell( (Conversation) entity);
      // Default Size for the new Vertex with the new entity within
      size = ConversationView.getSize((Conversation) entity);
      
    }
    else

    if (entity.getClass().equals(MentalInstanceSpecification.class)) {
      vertex = new MentalInstanceSpecificationCell( (MentalInstanceSpecification) entity);
      // Default Size for the new Vertex with the new entity within
      size = MentalInstanceSpecificationView.getSize((MentalInstanceSpecification) entity);
      
    }
    else

    if (entity.getClass().equals(ContextBindingTask.class)) {
      vertex = new ContextBindingTaskCell( (ContextBindingTask) entity);
      // Default Size for the new Vertex with the new entity within
      size = ContextBindingTaskView.getSize((ContextBindingTask) entity);
      
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

		

		 DeployDiagramModelJGraph jg=new  DeployDiagramModelJGraph(
				(DeployDiagramDataEntity) this.mde,name, ids.om,
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
