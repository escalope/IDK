

/*
    Copyright (C) 2002 Jorge Gomez Sanz
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
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import javax.swing.tree.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.util.Map;
import java.util.Hashtable;
import ingenias.editor.extension.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;
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
import java.io.*;
import ingenias.editor.persistence.*;
import java.awt.image.*;
import java.awt.datatransfer.*;
import java.awt.geom.Rectangle2D;
import ingenias.editor.cell.*;
import ingenias.exception.NullEntity;
import ingenias.generator.browser.BrowserImp;
import ingenias.generator.browser.Graph;
import ingenias.generator.browser.GraphEntity;

public class IDE
    extends ingenias.editor.IDEAbs {


  /**
   *  Constructor for the IDE object
   */
  public IDE() {
    super();
  }

  

  

public void updateProjectsMenu(JMenu menu){
	final IDE self=this;
   
   // Menu to add a EnvironmentModel model instance

    menu.add(
        new AbstractAction("Add EnvironmentModel") {

      public void actionPerformed(ActionEvent e) {
    TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
    if (tp==null){
      tp=ids.gm.arbolProyecto.getPathForRow(0);
    }
     TreePath _tp=tp;

        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) _tp.
            getLastPathComponent();
        if (_tp!= null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
            EnvironmentModelModelJGraph mjg =
                new EnvironmentModelModelJGraph(new
                                                  EnvironmentModelDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   EnvironmentModelMarqueeHandler marquee=new
                EnvironmentModelMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(_tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(_tp);

            ids.gm.arbolProyecto.scrollPathToVisible(_tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();
          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a ComponentDiagram model instance

    menu.add(
        new AbstractAction("Add ComponentDiagram") {

      public void actionPerformed(ActionEvent e) {
    TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
    if (tp==null){
      tp=ids.gm.arbolProyecto.getPathForRow(0);
    }
     TreePath _tp=tp;

        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) _tp.
            getLastPathComponent();
        if (_tp!= null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
            ComponentDiagramModelJGraph mjg =
                new ComponentDiagramModelJGraph(new
                                                  ComponentDiagramDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   ComponentDiagramMarqueeHandler marquee=new
                ComponentDiagramMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(_tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(_tp);

            ids.gm.arbolProyecto.scrollPathToVisible(_tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();
          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a OrganizationModel model instance

    menu.add(
        new AbstractAction("Add OrganizationModel") {

      public void actionPerformed(ActionEvent e) {
    TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
    if (tp==null){
      tp=ids.gm.arbolProyecto.getPathForRow(0);
    }
     TreePath _tp=tp;

        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) _tp.
            getLastPathComponent();
        if (_tp!= null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
            OrganizationModelModelJGraph mjg =
                new OrganizationModelModelJGraph(new
                                                  OrganizationModelDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   OrganizationModelMarqueeHandler marquee=new
                OrganizationModelMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(_tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(_tp);

            ids.gm.arbolProyecto.scrollPathToVisible(_tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();
          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a TasksAndGoalsModel model instance

    menu.add(
        new AbstractAction("Add TasksAndGoalsModel") {

      public void actionPerformed(ActionEvent e) {
    TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
    if (tp==null){
      tp=ids.gm.arbolProyecto.getPathForRow(0);
    }
     TreePath _tp=tp;

        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) _tp.
            getLastPathComponent();
        if (_tp!= null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
            TasksAndGoalsModelModelJGraph mjg =
                new TasksAndGoalsModelModelJGraph(new
                                                  TasksAndGoalsModelDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   TasksAndGoalsModelMarqueeHandler marquee=new
                TasksAndGoalsModelMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(_tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(_tp);

            ids.gm.arbolProyecto.scrollPathToVisible(_tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();
          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a InteractionModel model instance

    menu.add(
        new AbstractAction("Add InteractionModel") {

      public void actionPerformed(ActionEvent e) {
    TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
    if (tp==null){
      tp=ids.gm.arbolProyecto.getPathForRow(0);
    }
     TreePath _tp=tp;

        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) _tp.
            getLastPathComponent();
        if (_tp!= null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
            InteractionModelModelJGraph mjg =
                new InteractionModelModelJGraph(new
                                                  InteractionModelDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   InteractionModelMarqueeHandler marquee=new
                InteractionModelMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(_tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(_tp);

            ids.gm.arbolProyecto.scrollPathToVisible(_tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();
          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a ActivityDiagram model instance

    menu.add(
        new AbstractAction("Add ActivityDiagram") {

      public void actionPerformed(ActionEvent e) {
    TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
    if (tp==null){
      tp=ids.gm.arbolProyecto.getPathForRow(0);
    }
     TreePath _tp=tp;

        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) _tp.
            getLastPathComponent();
        if (_tp!= null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
            ActivityDiagramModelJGraph mjg =
                new ActivityDiagramModelJGraph(new
                                                  ActivityDiagramDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   ActivityDiagramMarqueeHandler marquee=new
                ActivityDiagramMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(_tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(_tp);

            ids.gm.arbolProyecto.scrollPathToVisible(_tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();
          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a AgentModel model instance

    menu.add(
        new AbstractAction("Add AgentModel") {

      public void actionPerformed(ActionEvent e) {
    TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
    if (tp==null){
      tp=ids.gm.arbolProyecto.getPathForRow(0);
    }
     TreePath _tp=tp;

        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) _tp.
            getLastPathComponent();
        if (_tp!= null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
            AgentModelModelJGraph mjg =
                new AgentModelModelJGraph(new
                                                  AgentModelDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   AgentModelMarqueeHandler marquee=new
                AgentModelMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(_tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(_tp);

            ids.gm.arbolProyecto.scrollPathToVisible(_tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();
          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a UseCaseDiagram model instance

    menu.add(
        new AbstractAction("Add UseCaseDiagram") {

      public void actionPerformed(ActionEvent e) {
    TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
    if (tp==null){
      tp=ids.gm.arbolProyecto.getPathForRow(0);
    }
     TreePath _tp=tp;

        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) _tp.
            getLastPathComponent();
        if (_tp!= null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
            UseCaseDiagramModelJGraph mjg =
                new UseCaseDiagramModelJGraph(new
                                                  UseCaseDiagramDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   UseCaseDiagramMarqueeHandler marquee=new
                UseCaseDiagramMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(_tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(_tp);

            ids.gm.arbolProyecto.scrollPathToVisible(_tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();
          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a AUMLInteractionDiagram model instance

    menu.add(
        new AbstractAction("Add AUMLInteractionDiagram") {

      public void actionPerformed(ActionEvent e) {
    TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
    if (tp==null){
      tp=ids.gm.arbolProyecto.getPathForRow(0);
    }
     TreePath _tp=tp;

        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) _tp.
            getLastPathComponent();
        if (_tp!= null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
            AUMLInteractionDiagramModelJGraph mjg =
                new AUMLInteractionDiagramModelJGraph(new
                                                  AUMLInteractionDiagramDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   AUMLInteractionDiagramMarqueeHandler marquee=new
                AUMLInteractionDiagramMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(_tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(_tp);

            ids.gm.arbolProyecto.scrollPathToVisible(_tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();
          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a DeployDiagram model instance

    menu.add(
        new AbstractAction("Add DeployDiagram") {

      public void actionPerformed(ActionEvent e) {
    TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
    if (tp==null){
      tp=ids.gm.arbolProyecto.getPathForRow(0);
    }
     TreePath _tp=tp;

        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) _tp.
            getLastPathComponent();
        if (_tp!= null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
            DeployDiagramModelJGraph mjg =
                new DeployDiagramModelJGraph(new
                                                  DeployDiagramDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   DeployDiagramMarqueeHandler marquee=new
                DeployDiagramMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(_tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(_tp);

            ids.gm.arbolProyecto.scrollPathToVisible(_tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();
          }
        }
        //gm.addModel( );
      }
    });



  menu.addSeparator();
  menu.add(
        new AbstractAction("Add package") {

      public void actionPerformed(ActionEvent e) {
    TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
    if (tp==null){
      tp=ids.gm.arbolProyecto.getPathForRow(0);
    }
     TreePath _tp=tp;

          String nombre =
              JOptionPane.showInputDialog(IDE.ide,
                                          "Type a new package's name",
                                          "New package",
                                          JOptionPane.QUESTION_MESSAGE);
          if (nombre != null) {
            ids.gm.addPackage(_tp.getPath(), nombre);

            ids.gm.arbolProyecto.repaint();
            ids.gm.arbolProyecto.expandPath(_tp);

            ids.gm.arbolProyecto.scrollPathToVisible(_tp);
          setChanged();
          }
      }
    });


}


  public JPopupMenu menuProjectTree(MouseEvent me1) {
    JPopupMenu menu = new JPopupMenu();
    final MouseEvent me = me1;

    TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
     if (tp != null) {   
    DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) 
    tp.getLastPathComponent();
    final IDE self=this;
    if (tp != null && dmtn.getUserObject()instanceof String) {

   
   // Menu to add a EnvironmentModel model instance

    menu.add(
        new AbstractAction("Add EnvironmentModel") {

      public void actionPerformed(ActionEvent e) {
        TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) tp.
            getLastPathComponent();
        if (tp != null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
	  
            EnvironmentModelModelJGraph mjg =
                new EnvironmentModelModelJGraph(new
                                                  EnvironmentModelDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   EnvironmentModelMarqueeHandler marquee=new
                EnvironmentModelMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(tp);

            ids.gm.arbolProyecto.scrollPathToVisible(tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();

          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a ComponentDiagram model instance

    menu.add(
        new AbstractAction("Add ComponentDiagram") {

      public void actionPerformed(ActionEvent e) {
        TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) tp.
            getLastPathComponent();
        if (tp != null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
	  
            ComponentDiagramModelJGraph mjg =
                new ComponentDiagramModelJGraph(new
                                                  ComponentDiagramDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   ComponentDiagramMarqueeHandler marquee=new
                ComponentDiagramMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(tp);

            ids.gm.arbolProyecto.scrollPathToVisible(tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();

          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a OrganizationModel model instance

    menu.add(
        new AbstractAction("Add OrganizationModel") {

      public void actionPerformed(ActionEvent e) {
        TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) tp.
            getLastPathComponent();
        if (tp != null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
	  
            OrganizationModelModelJGraph mjg =
                new OrganizationModelModelJGraph(new
                                                  OrganizationModelDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   OrganizationModelMarqueeHandler marquee=new
                OrganizationModelMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(tp);

            ids.gm.arbolProyecto.scrollPathToVisible(tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();

          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a TasksAndGoalsModel model instance

    menu.add(
        new AbstractAction("Add TasksAndGoalsModel") {

      public void actionPerformed(ActionEvent e) {
        TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) tp.
            getLastPathComponent();
        if (tp != null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
	  
            TasksAndGoalsModelModelJGraph mjg =
                new TasksAndGoalsModelModelJGraph(new
                                                  TasksAndGoalsModelDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   TasksAndGoalsModelMarqueeHandler marquee=new
                TasksAndGoalsModelMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(tp);

            ids.gm.arbolProyecto.scrollPathToVisible(tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();

          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a InteractionModel model instance

    menu.add(
        new AbstractAction("Add InteractionModel") {

      public void actionPerformed(ActionEvent e) {
        TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) tp.
            getLastPathComponent();
        if (tp != null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
	  
            InteractionModelModelJGraph mjg =
                new InteractionModelModelJGraph(new
                                                  InteractionModelDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   InteractionModelMarqueeHandler marquee=new
                InteractionModelMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(tp);

            ids.gm.arbolProyecto.scrollPathToVisible(tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();

          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a ActivityDiagram model instance

    menu.add(
        new AbstractAction("Add ActivityDiagram") {

      public void actionPerformed(ActionEvent e) {
        TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) tp.
            getLastPathComponent();
        if (tp != null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
	  
            ActivityDiagramModelJGraph mjg =
                new ActivityDiagramModelJGraph(new
                                                  ActivityDiagramDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   ActivityDiagramMarqueeHandler marquee=new
                ActivityDiagramMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(tp);

            ids.gm.arbolProyecto.scrollPathToVisible(tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();

          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a AgentModel model instance

    menu.add(
        new AbstractAction("Add AgentModel") {

      public void actionPerformed(ActionEvent e) {
        TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) tp.
            getLastPathComponent();
        if (tp != null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
	  
            AgentModelModelJGraph mjg =
                new AgentModelModelJGraph(new
                                                  AgentModelDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   AgentModelMarqueeHandler marquee=new
                AgentModelMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(tp);

            ids.gm.arbolProyecto.scrollPathToVisible(tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();

          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a UseCaseDiagram model instance

    menu.add(
        new AbstractAction("Add UseCaseDiagram") {

      public void actionPerformed(ActionEvent e) {
        TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) tp.
            getLastPathComponent();
        if (tp != null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
	  
            UseCaseDiagramModelJGraph mjg =
                new UseCaseDiagramModelJGraph(new
                                                  UseCaseDiagramDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   UseCaseDiagramMarqueeHandler marquee=new
                UseCaseDiagramMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(tp);

            ids.gm.arbolProyecto.scrollPathToVisible(tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();

          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a AUMLInteractionDiagram model instance

    menu.add(
        new AbstractAction("Add AUMLInteractionDiagram") {

      public void actionPerformed(ActionEvent e) {
        TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) tp.
            getLastPathComponent();
        if (tp != null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
	  
            AUMLInteractionDiagramModelJGraph mjg =
                new AUMLInteractionDiagramModelJGraph(new
                                                  AUMLInteractionDiagramDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   AUMLInteractionDiagramMarqueeHandler marquee=new
                AUMLInteractionDiagramMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(tp);

            ids.gm.arbolProyecto.scrollPathToVisible(tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();

          }
        }
        //gm.addModel( );
      }
    });


   // Menu to add a DeployDiagram model instance

    menu.add(
        new AbstractAction("Add DeployDiagram") {

      public void actionPerformed(ActionEvent e) {
        TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) tp.
            getLastPathComponent();
        if (tp != null && dmtn.getUserObject()instanceof String) {
          String nombre = JOptionPane.showInputDialog(IDE.ide,
              "Type graph name",
              "New graph",
              JOptionPane.QUESTION_MESSAGE);
          if (nombre != null && ids.gm.existsModel(nombre)) {

            JOptionPane.showMessageDialog(self,
                "There exists a model with the same name. Please, select another",
                "Warning",
                JOptionPane.WARNING_MESSAGE);
          }
          else
          if (nombre != null) {
	  
            DeployDiagramModelJGraph mjg =
                new DeployDiagramModelJGraph(new
                                                  DeployDiagramDataEntity(
                nombre), nombre, ids.om, new Model(ids),
                                                new BasicMarqueeHandler()  );
	   DeployDiagramMarqueeHandler marquee=new
                DeployDiagramMarqueeHandler(mjg);
	    mjg.setMarqueeHandler(marquee);
            ids.gm.addModel(tp.getPath(), nombre, mjg);
            ids.gm.setCurrent(mjg);
            ids.editor.changeGraph(mjg);
            ids.gm.arbolProyecto.expandPath(tp);

            ids.gm.arbolProyecto.scrollPathToVisible(tp);
            ids.gm.arbolProyecto.repaint();
            ids.editor.setEnabled(true);
            validate();
            setChanged();

          }
        }
        //gm.addModel( );
      }
    });




  menu.add(
        new AbstractAction("Add package") {

      public void actionPerformed(ActionEvent e) {
        TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
        if (tp != null) {
          String nombre =
              JOptionPane.showInputDialog(self,
                                          "Type a new package's name",
                                          "New package",
                                          JOptionPane.QUESTION_MESSAGE);
          if (nombre != null) {
            ids.gm.addPackage(tp.getPath(), nombre);

            ids.gm.arbolProyecto.repaint();
            ids.gm.arbolProyecto.expandPath(tp);

            ids.gm.arbolProyecto.scrollPathToVisible(tp);
            setChanged();
          }
        }
      }
    });
    } 
    else {

    // Edit
    menu.add(
        new AbstractAction("Edit diagram properties") {
      public void actionPerformed(ActionEvent e) {
        TreePath tp = arbolProyectos.getSelectionPath();
        if (tp != null) {

          DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) tp.
              getLastPathComponent();

          Object uo = dmtn.getUserObject();

          if (!String.class.isAssignableFrom(uo.getClass())) {
            boolean duplicated = true;
            while (duplicated) {
              ModelDataEntity mde = ( (ModelJGraph) uo).getProperties();

              ingenias.editor.editiondialog.GeneralEditionFrame gef = new ingenias.
	      editor.editiondialog.GeneralEditionFrame(ids.editor, ids.om, self,
                                                  "Edit diagram properties",
                                                  mde);
              ModelJGraph mjg =
                  GraphManager.getInstance().getModel(mde.getId());
//              Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            gef.setLocation(getCenter(gef.getSize()));
            gef.pack();
              gef.show();
              duplicated =
                  GraphManager.getInstance().isDuplicated(mde.getId());
              if (duplicated) {
                JOptionPane.showMessageDialog(self,
                    "There exists a model with the same name. Please, select another",
                    "Warning", JOptionPane.WARNING_MESSAGE);
              }
            }
            ids.gm.arbolProyecto.storeTreeExpansionPaths();
            setChanged();
            ( (DefaultTreeModel) arbolProyectos.getModel()).reload();
            ids.gm.arbolProyecto.restoreTreeExpansionPath();

          }
        }
      }
    });
    }
    if (tp.getPathCount()>1){
    menu.add(
        new AbstractAction("rename") {

      public void actionPerformed(ActionEvent e) {
        TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
        if (tp != null) {
          DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode) tp.
              getLastPathComponent();
          Object uo = dmtn.getUserObject();
          String result = JOptionPane.showInputDialog(self,
              "Type in the new name", "New name", JOptionPane.QUESTION_MESSAGE);
          if (result != null && !result.equals("")) {
            if (String.class.isAssignableFrom(uo.getClass())) {
              dmtn.setUserObject(result);
	      setChanged();
            }
            else {
              if (ids.gm.existsModel(result)) {
                JOptionPane.showMessageDialog(self,
                    "There exists a model with the same name. Please, select another",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
              }
              else {

                ModelJGraph mjg = (ModelJGraph) uo;
                mjg.setId(result);
		setChanged();
              }
            }
          }
        }
      }
    });

  menu.add(
        new AbstractAction("remove package/model") {

      public void actionPerformed(ActionEvent e) {
        TreePath tp = ids.gm.arbolProyecto.getSelectionPath();
        if (tp != null) {
          int result = JOptionPane.showConfirmDialog(self,
              "This will remove permanently " + tp.getLastPathComponent() +
              ". Are you sure?",
              "removing package", JOptionPane.YES_NO_OPTION);
          if (result == JOptionPane.OK_OPTION) {
            ModelJGraph mj = ids.gm.getModel(tp.getPath());
            if (mj != null) {
              ids.editor.closeTab(mj.getID());
            }
            ids.gm.arbolProyecto.storeTreeExpansionPaths();
            ids.gm.removePackage(tp.getPath());

            ( (DefaultTreeModel) ids.gm.arbolProyecto.getModel()).reload();
            ids.gm.arbolProyecto.repaint();

            ids.gm.arbolProyecto.restoreTreeExpansionPath();
            setChanged();	    
          }
        }
      }
    });
    }
    }
    return menu;
  }

  
 
  


	
	
	

  public static void main(String args[]) throws Exception {
 
    // from jefflessard
    //http://forum.java.sun.com/profile.jsp?user=404141

    IDE ide = new IDE();

    if (args.length != 0) {

      ide.loadFile(new File(args[0]));

    }

    ingenias.generator.browser.BrowserImp.initialise();

  }
}



