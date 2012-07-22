

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

public class EnvironmentModelModelJGraph extends ModelJGraph {

  private Preferences prefs;
  
 

  public EnvironmentModelModelJGraph(EnvironmentModelDataEntity mde, 
                               String nombre, ObjectManager om, Model
                               m, BasicMarqueeHandler mh, Preferences prefs) {
    super(mde, nombre, m, mh,om);
    this.prefs=prefs;
    
       
                                  ToolTipManager.sharedInstance().registerComponent(this);                
                                  

    this.getGraphLayoutCache().setFactory(new ingenias.editor.cellfactories.EnvironmentModelCellViewFactory());

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
    toolbar = new FilteredJToolBar("EnvironmentModel");
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
    Image img_OrganizationGroup =
        ImageLoader.getImage("images/mgroup.gif");
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
    jb = new JButton(OrganizationGroup);
    jb.setText("");
    jb.setName("OrganizationGroup");	
    jb.setToolTipText("OrganizationGroup");
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
    jb = new JButton(Organization);
    jb.setText("");
    jb.setName("Organization");	
    jb.setToolTipText("Organization");
    toolbar.add(jb);
    }

   if (true){
    Image img_AMIContext =
        ImageLoader.getImage("images/mdepl.gif");
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
    Image img_FAERIECtxtModel =
        ImageLoader.getImage("images/mdepl.gif");
    undoIcon = new ImageIcon(img_FAERIECtxtModel);
    Action FAERIECtxtModel=
        new AbstractAction("FAERIECtxtModel", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "FAERIECtxtModel");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    FAERIECtxtModel.setEnabled(true);
    jb = new JButton(FAERIECtxtModel);
    jb.setText("");
    jb.setName("FAERIECtxtModel");	
    jb.setToolTipText("FAERIECtxtModel");
    toolbar.add(jb);
    }

   if (true){
    Image img_FAERIECtxtAttribute =
        ImageLoader.getImage("images/mdepl.gif");
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

   if (true){
    Image img_FAERIECtxtEntity =
        ImageLoader.getImage("images/mdepl.gif");
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

   if (true){
    Image img_FAERIECtxtRelationship =
        ImageLoader.getImage("images/mdepl.gif");
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

   if (true){
    Image img_FAERIECtxtValue =
        ImageLoader.getImage("images/mdepl.gif");
    undoIcon = new ImageIcon(img_FAERIECtxtValue);
    Action FAERIECtxtValue=
        new AbstractAction("FAERIECtxtValue", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "FAERIECtxtValue");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    FAERIECtxtValue.setEnabled(true);
    jb = new JButton(FAERIECtxtValue);
    jb.setText("");
    jb.setName("FAERIECtxtValue");	
    jb.setToolTipText("FAERIECtxtValue");
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

    }

  }

  public Vector<String> getAllowedRelationships(){
   Vector<String> relationships=new   Vector<String>();


          relationships.add("EPerceives");

          relationships.add("EPerceivesNotification");

          relationships.add("EPerceivesPolling");

          relationships.add("EResourceBelongsTo");

          relationships.add("ApplicationBelongsTo");

          relationships.add("UMLAnnotatedElement");

          relationships.add("OHasGroup");

          relationships.add("ODecomposesGroup");

          relationships.add("CtxtNotifies");

          relationships.add("CtxtUpdates");

          relationships.add("FAERIEAppliedTo");

          relationships.add("FAERIEHasValue");

          relationships.add("FAERIEGeneratedBy");

          relationships.add("FAERIESrcEntity");

          relationships.add("FAERIETrgtEntity");

          relationships.add("HasCtxModel");

   return relationships;
  }

  public  Vector<String> getAllowedEntities(){
   Vector<String> entities=new   Vector<String>();


 entities.add("Agent");

 entities.add("OrganizationGroup");

 entities.add("Resource");

 entities.add("Application");

 entities.add("InternalApplication");

 entities.add("EnvironmentApplication");

 entities.add("ApplicationWS");

 entities.add("TextNote");

 entities.add("UMLComment");

 entities.add("Organization");

 entities.add("AMIContext");

 entities.add("FAERIEContext");

 entities.add("FAERIECtxtModel");

 entities.add("FAERIECtxtAttribute");

 entities.add("FAERIECtxtEntity");

 entities.add("FAERIECtxtRelationship");

 entities.add("FAERIECtxtValue");

 entities.add("RoleWS");

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
        if (EPerceivesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("EPerceives");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (EPerceivesNotificationEdge.acceptConnection(this.getModel(), selected)) {
          v.add("EPerceivesNotification");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (EPerceivesPollingEdge.acceptConnection(this.getModel(), selected)) {
          v.add("EPerceivesPolling");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (EResourceBelongsToEdge.acceptConnection(this.getModel(), selected)) {
          v.add("EResourceBelongsTo");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (ApplicationBelongsToEdge.acceptConnection(this.getModel(), selected)) {
          v.add("ApplicationBelongsTo");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (UMLAnnotatedElementEdge.acceptConnection(this.getModel(), selected)) {
          v.add("UMLAnnotatedElement");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (OHasGroupEdge.acceptConnection(this.getModel(), selected)) {
          v.add("OHasGroup");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (ODecomposesGroupEdge.acceptConnection(this.getModel(), selected)) {
          v.add("ODecomposesGroup");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (CtxtNotifiesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("CtxtNotifies");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (CtxtUpdatesEdge.acceptConnection(this.getModel(), selected)) {
          v.add("CtxtUpdates");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (FAERIEAppliedToEdge.acceptConnection(this.getModel(), selected)) {
          v.add("FAERIEAppliedTo");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (FAERIEHasValueEdge.acceptConnection(this.getModel(), selected)) {
          v.add("FAERIEHasValue");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (FAERIEGeneratedByEdge.acceptConnection(this.getModel(), selected)) {
          v.add("FAERIEGeneratedBy");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (FAERIESrcEntityEdge.acceptConnection(this.getModel(), selected)) {
          v.add("FAERIESrcEntity");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (FAERIETrgtEntityEdge.acceptConnection(this.getModel(), selected)) {
          v.add("FAERIETrgtEntity");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (HasCtxModelEdge.acceptConnection(this.getModel(), selected)) {
          v.add("HasCtxModel");
	  }

      }
      else if (nAryEdgesNum == 1) {

        if (selectedEdge instanceof EPerceivesEdge &&
        (EPerceivesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("EPerceives");
        }

        if (selectedEdge instanceof EPerceivesNotificationEdge &&
        (EPerceivesNotificationEdge.acceptConnection(this.getModel(), selected))) {
          v.add("EPerceivesNotification");
        }

        if (selectedEdge instanceof EPerceivesPollingEdge &&
        (EPerceivesPollingEdge.acceptConnection(this.getModel(), selected))) {
          v.add("EPerceivesPolling");
        }

        if (selectedEdge instanceof EResourceBelongsToEdge &&
        (EResourceBelongsToEdge.acceptConnection(this.getModel(), selected))) {
          v.add("EResourceBelongsTo");
        }

        if (selectedEdge instanceof ApplicationBelongsToEdge &&
        (ApplicationBelongsToEdge.acceptConnection(this.getModel(), selected))) {
          v.add("ApplicationBelongsTo");
        }

        if (selectedEdge instanceof UMLAnnotatedElementEdge &&
        (UMLAnnotatedElementEdge.acceptConnection(this.getModel(), selected))) {
          v.add("UMLAnnotatedElement");
        }

        if (selectedEdge instanceof OHasGroupEdge &&
        (OHasGroupEdge.acceptConnection(this.getModel(), selected))) {
          v.add("OHasGroup");
        }

        if (selectedEdge instanceof ODecomposesGroupEdge &&
        (ODecomposesGroupEdge.acceptConnection(this.getModel(), selected))) {
          v.add("ODecomposesGroup");
        }

        if (selectedEdge instanceof CtxtNotifiesEdge &&
        (CtxtNotifiesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("CtxtNotifies");
        }

        if (selectedEdge instanceof CtxtUpdatesEdge &&
        (CtxtUpdatesEdge.acceptConnection(this.getModel(), selected))) {
          v.add("CtxtUpdates");
        }

        if (selectedEdge instanceof FAERIEAppliedToEdge &&
        (FAERIEAppliedToEdge.acceptConnection(this.getModel(), selected))) {
          v.add("FAERIEAppliedTo");
        }

        if (selectedEdge instanceof FAERIEHasValueEdge &&
        (FAERIEHasValueEdge.acceptConnection(this.getModel(), selected))) {
          v.add("FAERIEHasValue");
        }

        if (selectedEdge instanceof FAERIEGeneratedByEdge &&
        (FAERIEGeneratedByEdge.acceptConnection(this.getModel(), selected))) {
          v.add("FAERIEGeneratedBy");
        }

        if (selectedEdge instanceof FAERIESrcEntityEdge &&
        (FAERIESrcEntityEdge.acceptConnection(this.getModel(), selected))) {
          v.add("FAERIESrcEntity");
        }

        if (selectedEdge instanceof FAERIETrgtEntityEdge &&
        (FAERIETrgtEntityEdge.acceptConnection(this.getModel(), selected))) {
          v.add("FAERIETrgtEntity");
        }

        if (selectedEdge instanceof HasCtxModelEdge &&
        (HasCtxModelEdge.acceptConnection(this.getModel(), selected))) {
          v.add("HasCtxModel");
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

      if (relacion.equalsIgnoreCase("EPerceives")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof EPerceivesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new EPerceivesEdge(new ingenias.editor.entities.EPerceives(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("EPerceivesNotification")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof EPerceivesNotificationEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new EPerceivesNotificationEdge(new ingenias.editor.entities.EPerceivesNotification(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("EPerceivesPolling")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof EPerceivesPollingEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new EPerceivesPollingEdge(new ingenias.editor.entities.EPerceivesPolling(getMJGraph().getNewId()));
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

      if (relacion.equalsIgnoreCase("OHasGroup")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof OHasGroupEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new OHasGroupEdge(new ingenias.editor.entities.OHasGroup(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("ODecomposesGroup")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof ODecomposesGroupEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new ODecomposesGroupEdge(new ingenias.editor.entities.ODecomposesGroup(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("CtxtNotifies")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof CtxtNotifiesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new CtxtNotifiesEdge(new ingenias.editor.entities.CtxtNotifies(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("CtxtUpdates")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof CtxtUpdatesEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new CtxtUpdatesEdge(new ingenias.editor.entities.CtxtUpdates(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("FAERIEAppliedTo")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof FAERIEAppliedToEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new FAERIEAppliedToEdge(new ingenias.editor.entities.FAERIEAppliedTo(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("FAERIEHasValue")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof FAERIEHasValueEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new FAERIEHasValueEdge(new ingenias.editor.entities.FAERIEHasValue(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("FAERIEGeneratedBy")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof FAERIEGeneratedByEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new FAERIEGeneratedByEdge(new ingenias.editor.entities.FAERIEGeneratedBy(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("FAERIESrcEntity")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof FAERIESrcEntityEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new FAERIESrcEntityEdge(new ingenias.editor.entities.FAERIESrcEntity(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("FAERIETrgtEntity")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof FAERIETrgtEntityEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new FAERIETrgtEntityEdge(new ingenias.editor.entities.FAERIETrgtEntity(getMJGraph().getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("HasCtxModel")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof HasCtxModelEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new HasCtxModelEdge(new ingenias.editor.entities.HasCtxModel(getMJGraph().getNewId()));
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

    if (entity.equalsIgnoreCase("OrganizationGroup")) {
    OrganizationGroup nentity=getOM().createOrganizationGroup(getMJGraph().getNewId("OrganizationGroup"));
      DefaultGraphCell vertex = new
          OrganizationGroupCell(nentity);
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

    if (entity.equalsIgnoreCase("Application")) {
    Application nentity=getOM().createApplication(getMJGraph().getNewId("Application"));
      DefaultGraphCell vertex = new
          ApplicationCell(nentity);
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

    if (entity.equalsIgnoreCase("EnvironmentApplication")) {
    EnvironmentApplication nentity=getOM().createEnvironmentApplication(getMJGraph().getNewId("EnvironmentApplication"));
      DefaultGraphCell vertex = new
          EnvironmentApplicationCell(nentity);
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

    if (entity.equalsIgnoreCase("Organization")) {
    Organization nentity=getOM().createOrganization(getMJGraph().getNewId("Organization"));
      DefaultGraphCell vertex = new
          OrganizationCell(nentity);
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

    if (entity.equalsIgnoreCase("FAERIEContext")) {
    FAERIEContext nentity=getOM().createFAERIEContext(getMJGraph().getNewId("FAERIEContext"));
      DefaultGraphCell vertex = new
          FAERIEContextCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("FAERIECtxtModel")) {
    FAERIECtxtModel nentity=getOM().createFAERIECtxtModel(getMJGraph().getNewId("FAERIECtxtModel"));
      DefaultGraphCell vertex = new
          FAERIECtxtModelCell(nentity);
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

    if (entity.equalsIgnoreCase("FAERIECtxtEntity")) {
    FAERIECtxtEntity nentity=getOM().createFAERIECtxtEntity(getMJGraph().getNewId("FAERIECtxtEntity"));
      DefaultGraphCell vertex = new
          FAERIECtxtEntityCell(nentity);
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

    if (entity.equalsIgnoreCase("FAERIECtxtValue")) {
    FAERIECtxtValue nentity=getOM().createFAERIECtxtValue(getMJGraph().getNewId("FAERIECtxtValue"));
      DefaultGraphCell vertex = new
          FAERIECtxtValueCell(nentity);
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

	  throw new ingenias.exception.InvalidEntity("Entity type "+entity+" is not allowed in this diagram"); 
  }
  
  public Dimension getDefaultSize(Entity entity) throws InvalidEntity{
    
    if (entity.getType().equalsIgnoreCase("Agent")) {
      return AgentView.getSize((ingenias.editor.entities.Agent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("OrganizationGroup")) {
      return OrganizationGroupView.getSize((ingenias.editor.entities.OrganizationGroup)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Resource")) {
      return ResourceView.getSize((ingenias.editor.entities.Resource)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Application")) {
      return ApplicationView.getSize((ingenias.editor.entities.Application)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("InternalApplication")) {
      return InternalApplicationView.getSize((ingenias.editor.entities.InternalApplication)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("EnvironmentApplication")) {
      return EnvironmentApplicationView.getSize((ingenias.editor.entities.EnvironmentApplication)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ApplicationWS")) {
      return ApplicationWSView.getSize((ingenias.editor.entities.ApplicationWS)entity);      
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

    if (entity.getType().equalsIgnoreCase("Organization")) {
      return OrganizationView.getSize((ingenias.editor.entities.Organization)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AMIContext")) {
      return AMIContextView.getSize((ingenias.editor.entities.AMIContext)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIEContext")) {
      return FAERIEContextView.getSize((ingenias.editor.entities.FAERIEContext)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIECtxtModel")) {
      return FAERIECtxtModelView.getSize((ingenias.editor.entities.FAERIECtxtModel)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIECtxtAttribute")) {
      return FAERIECtxtAttributeView.getSize((ingenias.editor.entities.FAERIECtxtAttribute)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIECtxtEntity")) {
      return FAERIECtxtEntityView.getSize((ingenias.editor.entities.FAERIECtxtEntity)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIECtxtRelationship")) {
      return FAERIECtxtRelationshipView.getSize((ingenias.editor.entities.FAERIECtxtRelationship)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIECtxtValue")) {
      return FAERIECtxtValueView.getSize((ingenias.editor.entities.FAERIECtxtValue)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RoleWS")) {
      return RoleWSView.getSize((ingenias.editor.entities.RoleWS)entity);      
    }
    else


      if (entity.getType().equalsIgnoreCase("EPerceives")) {
      	return EPerceivesView.getSize((ingenias.editor.entities.EPerceives)entity);
      }

      if (entity.getType().equalsIgnoreCase("EPerceivesNotification")) {
      	return EPerceivesNotificationView.getSize((ingenias.editor.entities.EPerceivesNotification)entity);
      }

      if (entity.getType().equalsIgnoreCase("EPerceivesPolling")) {
      	return EPerceivesPollingView.getSize((ingenias.editor.entities.EPerceivesPolling)entity);
      }

      if (entity.getType().equalsIgnoreCase("EResourceBelongsTo")) {
      	return EResourceBelongsToView.getSize((ingenias.editor.entities.EResourceBelongsTo)entity);
      }

      if (entity.getType().equalsIgnoreCase("ApplicationBelongsTo")) {
      	return ApplicationBelongsToView.getSize((ingenias.editor.entities.ApplicationBelongsTo)entity);
      }

      if (entity.getType().equalsIgnoreCase("UMLAnnotatedElement")) {
      	return UMLAnnotatedElementView.getSize((ingenias.editor.entities.UMLAnnotatedElement)entity);
      }

      if (entity.getType().equalsIgnoreCase("OHasGroup")) {
      	return OHasGroupView.getSize((ingenias.editor.entities.OHasGroup)entity);
      }

      if (entity.getType().equalsIgnoreCase("ODecomposesGroup")) {
      	return ODecomposesGroupView.getSize((ingenias.editor.entities.ODecomposesGroup)entity);
      }

      if (entity.getType().equalsIgnoreCase("CtxtNotifies")) {
      	return CtxtNotifiesView.getSize((ingenias.editor.entities.CtxtNotifies)entity);
      }

      if (entity.getType().equalsIgnoreCase("CtxtUpdates")) {
      	return CtxtUpdatesView.getSize((ingenias.editor.entities.CtxtUpdates)entity);
      }

      if (entity.getType().equalsIgnoreCase("FAERIEAppliedTo")) {
      	return FAERIEAppliedToView.getSize((ingenias.editor.entities.FAERIEAppliedTo)entity);
      }

      if (entity.getType().equalsIgnoreCase("FAERIEHasValue")) {
      	return FAERIEHasValueView.getSize((ingenias.editor.entities.FAERIEHasValue)entity);
      }

      if (entity.getType().equalsIgnoreCase("FAERIEGeneratedBy")) {
      	return FAERIEGeneratedByView.getSize((ingenias.editor.entities.FAERIEGeneratedBy)entity);
      }

      if (entity.getType().equalsIgnoreCase("FAERIESrcEntity")) {
      	return FAERIESrcEntityView.getSize((ingenias.editor.entities.FAERIESrcEntity)entity);
      }

      if (entity.getType().equalsIgnoreCase("FAERIETrgtEntity")) {
      	return FAERIETrgtEntityView.getSize((ingenias.editor.entities.FAERIETrgtEntity)entity);
      }

      if (entity.getType().equalsIgnoreCase("HasCtxModel")) {
      	return HasCtxModelView.getSize((ingenias.editor.entities.HasCtxModel)entity);
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

    if (entity.getClass().equals(OrganizationGroup.class)) {
      vertex = new OrganizationGroupCell( (OrganizationGroup) entity);
      // Default Size for the new Vertex with the new entity within
      size = OrganizationGroupView.getSize((OrganizationGroup) entity);
      
    }
    else

    if (entity.getClass().equals(Resource.class)) {
      vertex = new ResourceCell( (Resource) entity);
      // Default Size for the new Vertex with the new entity within
      size = ResourceView.getSize((Resource) entity);
      
    }
    else

    if (entity.getClass().equals(Application.class)) {
      vertex = new ApplicationCell( (Application) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationView.getSize((Application) entity);
      
    }
    else

    if (entity.getClass().equals(InternalApplication.class)) {
      vertex = new InternalApplicationCell( (InternalApplication) entity);
      // Default Size for the new Vertex with the new entity within
      size = InternalApplicationView.getSize((InternalApplication) entity);
      
    }
    else

    if (entity.getClass().equals(EnvironmentApplication.class)) {
      vertex = new EnvironmentApplicationCell( (EnvironmentApplication) entity);
      // Default Size for the new Vertex with the new entity within
      size = EnvironmentApplicationView.getSize((EnvironmentApplication) entity);
      
    }
    else

    if (entity.getClass().equals(ApplicationWS.class)) {
      vertex = new ApplicationWSCell( (ApplicationWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationWSView.getSize((ApplicationWS) entity);
      
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

    if (entity.getClass().equals(Organization.class)) {
      vertex = new OrganizationCell( (Organization) entity);
      // Default Size for the new Vertex with the new entity within
      size = OrganizationView.getSize((Organization) entity);
      
    }
    else

    if (entity.getClass().equals(AMIContext.class)) {
      vertex = new AMIContextCell( (AMIContext) entity);
      // Default Size for the new Vertex with the new entity within
      size = AMIContextView.getSize((AMIContext) entity);
      
    }
    else

    if (entity.getClass().equals(FAERIEContext.class)) {
      vertex = new FAERIEContextCell( (FAERIEContext) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIEContextView.getSize((FAERIEContext) entity);
      
    }
    else

    if (entity.getClass().equals(FAERIECtxtModel.class)) {
      vertex = new FAERIECtxtModelCell( (FAERIECtxtModel) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIECtxtModelView.getSize((FAERIECtxtModel) entity);
      
    }
    else

    if (entity.getClass().equals(FAERIECtxtAttribute.class)) {
      vertex = new FAERIECtxtAttributeCell( (FAERIECtxtAttribute) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIECtxtAttributeView.getSize((FAERIECtxtAttribute) entity);
      
    }
    else

    if (entity.getClass().equals(FAERIECtxtEntity.class)) {
      vertex = new FAERIECtxtEntityCell( (FAERIECtxtEntity) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIECtxtEntityView.getSize((FAERIECtxtEntity) entity);
      
    }
    else

    if (entity.getClass().equals(FAERIECtxtRelationship.class)) {
      vertex = new FAERIECtxtRelationshipCell( (FAERIECtxtRelationship) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIECtxtRelationshipView.getSize((FAERIECtxtRelationship) entity);
      
    }
    else

    if (entity.getClass().equals(FAERIECtxtValue.class)) {
      vertex = new FAERIECtxtValueCell( (FAERIECtxtValue) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIECtxtValueView.getSize((FAERIECtxtValue) entity);
      
    }
    else

    if (entity.getClass().equals(RoleWS.class)) {
      vertex = new RoleWSCell( (RoleWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = RoleWSView.getSize((RoleWS) entity);
      
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

		

		 EnvironmentModelModelJGraph jg=new  EnvironmentModelModelJGraph(
				(EnvironmentModelDataEntity) this.mde,name, ids.om,
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
