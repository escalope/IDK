

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

public class UseCaseDiagramModelJGraph extends ModelJGraph {

  private Preferences prefs;
  
 

  public UseCaseDiagramModelJGraph(UseCaseDiagramDataEntity mde, 
                               String nombre, ObjectManager om, Model
                               m, BasicMarqueeHandler mh, Preferences prefs) {
    super(mde, nombre, m, mh,om);
    this.prefs=prefs;
    
       
                                  ToolTipManager.sharedInstance().registerComponent(this);                        
                                  

    this.getGraphLayoutCache().setFactory(new ingenias.editor.cellfactories.UseCaseDiagramCellViewFactory());

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
    toolbar = new FilteredJToolBar("UseCaseDiagram");
    toolbar.setFloatable(false);
    ImageIcon undoIcon = null;
    JButton jb = null;


   if (true){
    Image img_TextUseCase =
        ImageLoader.getImage("images/musecase.gif");
    undoIcon = new ImageIcon(img_TextUseCase);
    Action TextUseCase=
        new AbstractAction("TextUseCase", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "TextUseCase");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    TextUseCase.setEnabled(true);
    jb = new JButton(TextUseCase){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("TextUseCase");	
    jb.setToolTipText("TextUseCase:"+new TextUseCase("").getHelpDesc()+"\n\n"+new TextUseCase("").getHelpRecom());
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
    Image img_INGENIASUseCase =
        ImageLoader.getImage("images/miusecase.gif");
    undoIcon = new ImageIcon(img_INGENIASUseCase);
    Action INGENIASUseCase=
        new AbstractAction("INGENIASUseCase", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "INGENIASUseCase");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    INGENIASUseCase.setEnabled(true);
    jb = new JButton(INGENIASUseCase){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("INGENIASUseCase");	
    jb.setToolTipText("INGENIASUseCase:"+new INGENIASUseCase("").getHelpDesc()+"\n\n"+new INGENIASUseCase("").getHelpRecom());
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
    jb = new JButton(Role){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("Role");	
    jb.setToolTipText("Role:"+new Role("").getHelpDesc()+"\n\n"+new Role("").getHelpRecom());
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
    jb = new JButton(Interaction){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("Interaction");	
    jb.setToolTipText("Interaction:"+new Interaction("").getHelpDesc()+"\n\n"+new Interaction("").getHelpRecom());
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
    jb = new JButton(TextNote){
					@Override
					public JToolTip createToolTip() {
						return new ingenias.editor.editiondialog.JMultiLineToolTip();
					}
				};
    jb.setText("");
    jb.setName("TextNote");	
    jb.setToolTipText("TextNote:"+new TextNote("").getHelpDesc()+"\n\n"+new TextNote("").getHelpRecom());
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
    Image img_Organization =
        ImageLoader.getImage("images/morg.gif");
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

    }

  }

  public Vector<String> getAllowedRelationships(){
   Vector<String> relationships=new   Vector<String>();


          relationships.add("Extends");

          relationships.add("Includes");

          relationships.add("Generalizes");

          relationships.add("ParticipatesInUseCase");

          relationships.add("UMLDescribesUseCase");

          relationships.add("UMLAssociation");

          relationships.add("UMLAnnotatedElement");

          relationships.add("UseCasePursues");

   return relationships;
  }

  public  Vector<String> getAllowedEntities(){
   Vector<String> entities=new   Vector<String>();


 entities.add("TextUseCase");

 entities.add("INGENIASUseCase");

 entities.add("Agent");

 entities.add("Role");

 entities.add("Interaction");

 entities.add("TextNote");

 entities.add("UMLComment");

 entities.add("Goal");

 entities.add("Organization");

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
        if (ExtendsEdge.acceptConnection(this.getModel(), selected)) {
          v.add("Extends");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (IncludesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("Includes");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (GeneralizesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("Generalizes");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (ParticipatesInUseCaseEdge.acceptConnection(this.getModel(), selected)) {
          v.add("ParticipatesInUseCase");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (UMLDescribesUseCaseEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UMLDescribesUseCase");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (UMLAssociationEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UMLAssociation");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (UMLAnnotatedElementEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UMLAnnotatedElement");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (UseCasePursuesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UseCasePursues");
	  }

      }
      else if (nAryEdgesNum == 1) {

        if (selectedEdge instanceof ExtendsEdge &&
        (ExtendsEdge.acceptConnection(this.getModel(), selected))) {
          v.add("Extends");
        }

        if (selectedEdge instanceof IncludesEdge &&
        (IncludesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("Includes");
        }

        if (selectedEdge instanceof GeneralizesEdge &&
        (GeneralizesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("Generalizes");
        }

        if (selectedEdge instanceof ParticipatesInUseCaseEdge &&
        (ParticipatesInUseCaseEdge.acceptConnection(this.getModel(), selected))) {
          v.add("ParticipatesInUseCase");
        }

        if (selectedEdge instanceof UMLDescribesUseCaseEdge &&
        (UMLDescribesUseCaseEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UMLDescribesUseCase");
        }

        if (selectedEdge instanceof UMLAssociationEdge &&
        (UMLAssociationEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UMLAssociation");
        }

        if (selectedEdge instanceof UMLAnnotatedElementEdge &&
        (UMLAnnotatedElementEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UMLAnnotatedElement");
        }

        if (selectedEdge instanceof UseCasePursuesEdge &&
        (UseCasePursuesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UseCasePursues");
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

      if (relacion.equalsIgnoreCase("Extends")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof ExtendsEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new ExtendsEdge(new ingenias.editor.entities.Extends(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("Includes")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof IncludesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new IncludesEdge(new ingenias.editor.entities.Includes(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("Generalizes")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof GeneralizesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new GeneralizesEdge(new ingenias.editor.entities.Generalizes(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("ParticipatesInUseCase")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof ParticipatesInUseCaseEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new ParticipatesInUseCaseEdge(new ingenias.editor.entities.ParticipatesInUseCase(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("UMLDescribesUseCase")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof UMLDescribesUseCaseEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new UMLDescribesUseCaseEdge(new ingenias.editor.entities.UMLDescribesUseCase(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("UMLAssociation")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof UMLAssociationEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new UMLAssociationEdge(new ingenias.editor.entities.UMLAssociation(getMJGraph().getNewId()));
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

      if (relacion.equalsIgnoreCase("UseCasePursues")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof UseCasePursuesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new UseCasePursuesEdge(new ingenias.editor.entities.UseCasePursues(getMJGraph().getNewId()));
        }
      }

    }

    return null;
  }
  
  public DefaultGraphCell createCell(String entity) throws InvalidEntity{
  
    if (entity.equalsIgnoreCase("TextUseCase")) {
    TextUseCase nentity=getOM().createTextUseCase(getMJGraph().getNewId("TextUseCase"));
      DefaultGraphCell vertex = new
          TextUseCaseCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("INGENIASUseCase")) {
    INGENIASUseCase nentity=getOM().createINGENIASUseCase(getMJGraph().getNewId("INGENIASUseCase"));
      DefaultGraphCell vertex = new
          INGENIASUseCaseCell(nentity);
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

    if (entity.equalsIgnoreCase("Interaction")) {
    Interaction nentity=getOM().createInteraction(getMJGraph().getNewId("Interaction"));
      DefaultGraphCell vertex = new
          InteractionCell(nentity);
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

    if (entity.equalsIgnoreCase("Goal")) {
    Goal nentity=getOM().createGoal(getMJGraph().getNewId("Goal"));
      DefaultGraphCell vertex = new
          GoalCell(nentity);
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

	  throw new ingenias.exception.InvalidEntity("Entity type "+entity+" is not allowed in this diagram"); 
  }
  
  public Dimension getDefaultSize(Entity entity) throws InvalidEntity{
    
    if (entity.getType().equalsIgnoreCase("TextUseCase")) {
      return TextUseCaseView.getSize((ingenias.editor.entities.TextUseCase)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("INGENIASUseCase")) {
      return INGENIASUseCaseView.getSize((ingenias.editor.entities.INGENIASUseCase)entity);      
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

    if (entity.getType().equalsIgnoreCase("Interaction")) {
      return InteractionView.getSize((ingenias.editor.entities.Interaction)entity);      
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

    if (entity.getType().equalsIgnoreCase("Goal")) {
      return GoalView.getSize((ingenias.editor.entities.Goal)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Organization")) {
      return OrganizationView.getSize((ingenias.editor.entities.Organization)entity);      
    }
    else


      if (entity.getType().equalsIgnoreCase("Extends")) {
      	return ExtendsView.getSize((ingenias.editor.entities.Extends)entity);
      }

      if (entity.getType().equalsIgnoreCase("Includes")) {
      	return IncludesView.getSize((ingenias.editor.entities.Includes)entity);
      }

      if (entity.getType().equalsIgnoreCase("Generalizes")) {
      	return GeneralizesView.getSize((ingenias.editor.entities.Generalizes)entity);
      }

      if (entity.getType().equalsIgnoreCase("ParticipatesInUseCase")) {
      	return ParticipatesInUseCaseView.getSize((ingenias.editor.entities.ParticipatesInUseCase)entity);
      }

      if (entity.getType().equalsIgnoreCase("UMLDescribesUseCase")) {
      	return UMLDescribesUseCaseView.getSize((ingenias.editor.entities.UMLDescribesUseCase)entity);
      }

      if (entity.getType().equalsIgnoreCase("UMLAssociation")) {
      	return UMLAssociationView.getSize((ingenias.editor.entities.UMLAssociation)entity);
      }

      if (entity.getType().equalsIgnoreCase("UMLAnnotatedElement")) {
      	return UMLAnnotatedElementView.getSize((ingenias.editor.entities.UMLAnnotatedElement)entity);
      }

      if (entity.getType().equalsIgnoreCase("UseCasePursues")) {
      	return UseCasePursuesView.getSize((ingenias.editor.entities.UseCasePursues)entity);
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


    if (entity.getClass().equals(TextUseCase.class)) {
      vertex = new TextUseCaseCell( (TextUseCase) entity);
      // Default Size for the new Vertex with the new entity within
      size = TextUseCaseView.getSize((TextUseCase) entity);
      
    }
    else

    if (entity.getClass().equals(INGENIASUseCase.class)) {
      vertex = new INGENIASUseCaseCell( (INGENIASUseCase) entity);
      // Default Size for the new Vertex with the new entity within
      size = INGENIASUseCaseView.getSize((INGENIASUseCase) entity);
      
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

    if (entity.getClass().equals(Interaction.class)) {
      vertex = new InteractionCell( (Interaction) entity);
      // Default Size for the new Vertex with the new entity within
      size = InteractionView.getSize((Interaction) entity);
      
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

    if (entity.getClass().equals(Goal.class)) {
      vertex = new GoalCell( (Goal) entity);
      // Default Size for the new Vertex with the new entity within
      size = GoalView.getSize((Goal) entity);
      
    }
    else

    if (entity.getClass().equals(Organization.class)) {
      vertex = new OrganizationCell( (Organization) entity);
      // Default Size for the new Vertex with the new entity within
      size = OrganizationView.getSize((Organization) entity);
      
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

		

		 UseCaseDiagramModelJGraph jg=new  UseCaseDiagramModelJGraph(
				(UseCaseDiagramDataEntity) this.mde,name, ids.om,
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
