


/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz 
 * 
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

package ingenias.editor.actions.diagram;
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
import java.lang.reflect.*;
import ingenias.editor.entities.*;
import ingenias.editor.widget.*;
import ingenias.exception.NullEntity;
import ingenias.generator.browser.BrowserImp;
import ingenias.generator.browser.Graph;
import ingenias.generator.browser.GraphEntity;
import ingenias.exception.InvalidEntity;
import ingenias.editor.*;

// MarqueeHandler that Connects Vertices and Displays PopupMenus

public class TasksAndGoalsModelActionsFactory extends DiagramMenuEntriesActionsFactory  implements java.io.Serializable {

        public TasksAndGoalsModelActionsFactory(GUIResources resources, IDEState state){
	super(resources,state);
        }


	 public Vector<AbstractAction> createChangeViewActions(final DefaultGraphCell cell, final ModelJGraph graph) {
		 Vector<AbstractAction> possibleViews=new Vector<AbstractAction>();
		final ingenias.editor.entities.Entity ent=((ingenias.editor.entities.Entity)cell.getUserObject());
		
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Agent")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Agent")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AgentModelBelieve")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AgentModelBelieve")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ApplicationEvent")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ApplicationEvent")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ApplicationEventSlots")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ApplicationEventSlots")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Application")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Application")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AgentWS")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AgentWS")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Role")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Role")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Goal")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Goal")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.StateGoal")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.StateGoal")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Task")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Task")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Resource")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Resource")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Fact")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Fact")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FrameFact")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FrameFact")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.RuntimeCommFailure")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.RuntimeCommFailure")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Believe")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Believe")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Compromise")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Compromise")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GeneralEvent")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GeneralEvent")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.CommunicationEvent")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.CommunicationEvent")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EnvironmentApplication")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EnvironmentApplication")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.InternalApplication")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.InternalApplication")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.TextNote")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.TextNote")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Plan")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Plan")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.BoxedTask")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.BoxedTask")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.MentalEntityInstanceCreation")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.MentalEntityInstanceCreation")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.MentalEntityInstanceAccess")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.MentalEntityInstanceAccess")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Conversation")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Conversation")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.UMLComment")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.UMLComment")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Workflow")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Workflow")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.RoleWS")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.RoleWS")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.TaskWS")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.TaskWS")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GoalStateWS")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GoalStateWS")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ApplicationWS")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ApplicationWS")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Interaction")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Interaction")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.RemoteProcedureCall")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.RemoteProcedureCall")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.RuntimeEvent")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.RuntimeEvent")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.IUIterate")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.IUIterate")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.MessagePassing")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.MessagePassing")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ShareTouple")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ShareTouple")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.RuntimeConversation")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.RuntimeConversation")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ContextBindingTask")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ContextBindingTask")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ContextReleaseTask")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ContextReleaseTask")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.RuntimeFact")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.RuntimeFact")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.IUConcurrence")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.IUConcurrence")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.InteractionUnit")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "UML");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.InteractionUnit")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getListenerContainer().storeContraints(cell);         
		      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     				
		     ent.getPrefs(graph.getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
    		      
			
		  
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTPursues")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTPursues")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTPursues")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTCreates")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTCreates")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTCreates")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTAffects")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTAffects")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTAffects")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTDestroys")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTDestroys")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTDestroys")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTModifies")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTModifies")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTModifies")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTFails")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTFails")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTFails")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTSatisfies")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTSatisfies")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTSatisfies")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFResponsable")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFResponsable")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFResponsable")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFConsumes")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFConsumes")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFConsumes")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Consumes")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Consumes")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Consumes")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFUses")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFUses")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFUses")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFUsesMethod")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFUsesMethod")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFUsesMethod")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.PConnects")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.PConnects")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.PConnects")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFProduces")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFProduces")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFProduces")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTDecomposes")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTDecomposes")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTDecomposes")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTDecomposesAND")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTDecomposesAND")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTDecomposesAND")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTDecomposesOR")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTDecomposesOR")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTDecomposesOR")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTDepends")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTDepends")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTDepends")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTInherits")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTInherits")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTInherits")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTAndDepends")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTAndDepends")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTAndDepends")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTOrDepends")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTOrDepends")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.GTOrDepends")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFDecomposes")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFDecomposes")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFDecomposes")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Contribute")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Contribute")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Contribute")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ContributePositively")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ContributePositively")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ContributePositively")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ContributeNegatively")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ContributeNegatively")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ContributeNegatively")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.UMLAnnotatedElement")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.UMLAnnotatedElement")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.UMLAnnotatedElement")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFParticipates")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFParticipates")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFParticipates")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFCancels")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFCancels")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFCancels")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.IAccesses")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.IAccesses")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.IAccesses")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.TriggersFailure")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.TriggersFailure")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.TriggersFailure")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
		 return possibleViews;
		 }
		 
		 

		 protected Vector<AbstractAction> createDiagramSpecificInsertActions(final Point pt, final ModelJGraph graph) {
			 Vector<AbstractAction> nobjects=new Vector<AbstractAction>();

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "Agent")){
		// Insert an object of type Agent
		nobjects.add(
			new AbstractAction("Insert Agent") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Agent");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Agent is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "AgentModelBelieve")){
		// Insert an object of type AgentModelBelieve
		nobjects.add(
			new AbstractAction("Insert AgentModelBelieve") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "AgentModelBelieve");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type AgentModelBelieve is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "ApplicationEvent")){
		// Insert an object of type ApplicationEvent
		nobjects.add(
			new AbstractAction("Insert ApplicationEvent") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "ApplicationEvent");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type ApplicationEvent is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "ApplicationEventSlots")){
		// Insert an object of type ApplicationEventSlots
		nobjects.add(
			new AbstractAction("Insert ApplicationEventSlots") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "ApplicationEventSlots");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type ApplicationEventSlots is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "Application")){
		// Insert an object of type Application
		nobjects.add(
			new AbstractAction("Insert Application") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Application");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Application is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "AgentWS")){
		// Insert an object of type AgentWS
		nobjects.add(
			new AbstractAction("Insert AgentWS") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "AgentWS");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type AgentWS is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "Role")){
		// Insert an object of type Role
		nobjects.add(
			new AbstractAction("Insert Role") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Role");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Role is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "Goal")){
		// Insert an object of type Goal
		nobjects.add(
			new AbstractAction("Insert Goal") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Goal");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Goal is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "StateGoal")){
		// Insert an object of type StateGoal
		nobjects.add(
			new AbstractAction("Insert StateGoal") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "StateGoal");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type StateGoal is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "Task")){
		// Insert an object of type Task
		nobjects.add(
			new AbstractAction("Insert Task") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Task");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Task is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "Resource")){
		// Insert an object of type Resource
		nobjects.add(
			new AbstractAction("Insert Resource") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Resource");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Resource is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "Fact")){
		// Insert an object of type Fact
		nobjects.add(
			new AbstractAction("Insert Fact") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Fact");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Fact is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "FrameFact")){
		// Insert an object of type FrameFact
		nobjects.add(
			new AbstractAction("Insert FrameFact") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "FrameFact");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type FrameFact is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "RuntimeCommFailure")){
		// Insert an object of type RuntimeCommFailure
		nobjects.add(
			new AbstractAction("Insert RuntimeCommFailure") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "RuntimeCommFailure");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type RuntimeCommFailure is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "Believe")){
		// Insert an object of type Believe
		nobjects.add(
			new AbstractAction("Insert Believe") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Believe");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Believe is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "Compromise")){
		// Insert an object of type Compromise
		nobjects.add(
			new AbstractAction("Insert Compromise") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Compromise");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Compromise is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "GeneralEvent")){
		// Insert an object of type GeneralEvent
		nobjects.add(
			new AbstractAction("Insert GeneralEvent") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "GeneralEvent");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type GeneralEvent is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "CommunicationEvent")){
		// Insert an object of type CommunicationEvent
		nobjects.add(
			new AbstractAction("Insert CommunicationEvent") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "CommunicationEvent");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type CommunicationEvent is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "EnvironmentApplication")){
		// Insert an object of type EnvironmentApplication
		nobjects.add(
			new AbstractAction("Insert EnvironmentApplication") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "EnvironmentApplication");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type EnvironmentApplication is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "InternalApplication")){
		// Insert an object of type InternalApplication
		nobjects.add(
			new AbstractAction("Insert InternalApplication") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "InternalApplication");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type InternalApplication is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "TextNote")){
		// Insert an object of type TextNote
		nobjects.add(
			new AbstractAction("Insert TextNote") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "TextNote");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type TextNote is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "Plan")){
		// Insert an object of type Plan
		nobjects.add(
			new AbstractAction("Insert Plan") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Plan");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Plan is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "BoxedTask")){
		// Insert an object of type BoxedTask
		nobjects.add(
			new AbstractAction("Insert BoxedTask") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "BoxedTask");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type BoxedTask is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "MentalEntityInstanceCreation")){
		// Insert an object of type MentalEntityInstanceCreation
		nobjects.add(
			new AbstractAction("Insert MentalEntityInstanceCreation") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "MentalEntityInstanceCreation");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type MentalEntityInstanceCreation is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "MentalEntityInstanceAccess")){
		// Insert an object of type MentalEntityInstanceAccess
		nobjects.add(
			new AbstractAction("Insert MentalEntityInstanceAccess") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "MentalEntityInstanceAccess");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type MentalEntityInstanceAccess is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "Conversation")){
		// Insert an object of type Conversation
		nobjects.add(
			new AbstractAction("Insert Conversation") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Conversation");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Conversation is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "UMLComment")){
		// Insert an object of type UMLComment
		nobjects.add(
			new AbstractAction("Insert UMLComment") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "UMLComment");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type UMLComment is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "Workflow")){
		// Insert an object of type Workflow
		nobjects.add(
			new AbstractAction("Insert Workflow") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Workflow");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Workflow is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "RoleWS")){
		// Insert an object of type RoleWS
		nobjects.add(
			new AbstractAction("Insert RoleWS") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "RoleWS");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type RoleWS is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "TaskWS")){
		// Insert an object of type TaskWS
		nobjects.add(
			new AbstractAction("Insert TaskWS") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "TaskWS");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type TaskWS is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "GoalStateWS")){
		// Insert an object of type GoalStateWS
		nobjects.add(
			new AbstractAction("Insert GoalStateWS") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "GoalStateWS");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type GoalStateWS is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "ApplicationWS")){
		// Insert an object of type ApplicationWS
		nobjects.add(
			new AbstractAction("Insert ApplicationWS") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "ApplicationWS");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type ApplicationWS is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "Interaction")){
		// Insert an object of type Interaction
		nobjects.add(
			new AbstractAction("Insert Interaction") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Interaction");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Interaction is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "RemoteProcedureCall")){
		// Insert an object of type RemoteProcedureCall
		nobjects.add(
			new AbstractAction("Insert RemoteProcedureCall") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "RemoteProcedureCall");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type RemoteProcedureCall is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "RuntimeEvent")){
		// Insert an object of type RuntimeEvent
		nobjects.add(
			new AbstractAction("Insert RuntimeEvent") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "RuntimeEvent");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type RuntimeEvent is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "IUIterate")){
		// Insert an object of type IUIterate
		nobjects.add(
			new AbstractAction("Insert IUIterate") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "IUIterate");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type IUIterate is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "MessagePassing")){
		// Insert an object of type MessagePassing
		nobjects.add(
			new AbstractAction("Insert MessagePassing") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "MessagePassing");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type MessagePassing is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "ShareTouple")){
		// Insert an object of type ShareTouple
		nobjects.add(
			new AbstractAction("Insert ShareTouple") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "ShareTouple");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type ShareTouple is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "RuntimeConversation")){
		// Insert an object of type RuntimeConversation
		nobjects.add(
			new AbstractAction("Insert RuntimeConversation") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "RuntimeConversation");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type RuntimeConversation is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "ContextBindingTask")){
		// Insert an object of type ContextBindingTask
		nobjects.add(
			new AbstractAction("Insert ContextBindingTask") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "ContextBindingTask");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type ContextBindingTask is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "ContextReleaseTask")){
		// Insert an object of type ContextReleaseTask
		nobjects.add(
			new AbstractAction("Insert ContextReleaseTask") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "ContextReleaseTask");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type ContextReleaseTask is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "RuntimeFact")){
		// Insert an object of type RuntimeFact
		nobjects.add(
			new AbstractAction("Insert RuntimeFact") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "RuntimeFact");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type RuntimeFact is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "IUConcurrence")){
		// Insert an object of type IUConcurrence
		nobjects.add(
			new AbstractAction("Insert IUConcurrence") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "IUConcurrence");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type IUConcurrence is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("TasksAndGoalsModel", "InteractionUnit")){
		// Insert an object of type InteractionUnit
		nobjects.add(
			new AbstractAction("Insert InteractionUnit") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "InteractionUnit");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type InteractionUnit is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

			return nobjects;
	}
	
	




}

