


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

public class OrganizationModelActionsFactory extends DiagramMenuEntriesActionsFactory  implements java.io.Serializable {

        public OrganizationModelActionsFactory(GUIResources resources, IDEState state){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Organization")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Organization")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.OrganizationGroup")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.OrganizationGroup")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.OrganizationNetwork")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.OrganizationNetwork")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AutonomousEntityQuery")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AutonomousEntityQuery")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AgentRequirementsQuery")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AgentRequirementsQuery")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ConcreteAgent")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ConcreteAgent")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WorkflowBox")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WorkflowBox")){
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
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EResourceBelongsTo")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EResourceBelongsTo")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EResourceBelongsTo")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.OHasGroup")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.OHasGroup")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.OHasGroup")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.OHasMember")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.OHasMember")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.OHasMember")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.OHasWF")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.OHasWF")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.OHasWF")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ODecomposesGroup")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ODecomposesGroup")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ODecomposesGroup")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ODecomposesWF")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ODecomposesWF")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ODecomposesWF")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
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
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFConnects")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFConnects")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFConnects")){
			
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
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFContainsTask")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFContainsTask")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFContainsTask")){
			
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
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGORelationshipGroup")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGORelationshipGroup")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGORelationshipGroup")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGORelationshipMember")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGORelationshipMember")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGORelationshipMember")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGORelationshipOrg")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGORelationshipOrg")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGORelationshipOrg")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOSubordinationRelationshipGroup")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOSubordinationRelationshipGroup")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOSubordinationRelationshipGroup")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOSubordinationRelationshipMember")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOSubordinationRelationshipMember")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOSubordinationRelationshipMember")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOSubordinationRelationshipOrg")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOSubordinationRelationshipOrg")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOSubordinationRelationshipOrg")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOCondSubordinationRelationshipGroup")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOCondSubordinationRelationshipGroup")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOCondSubordinationRelationshipGroup")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOCondSubordinationRelationshipMember")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOCondSubordinationRelationshipMember")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOCondSubordinationRelationshipMember")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOCondSubordinationRelationshipOrg")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOCondSubordinationRelationshipOrg")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOCondSubordinationRelationshipOrg")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOInconditionalSubordinationRelationshipGroup")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOInconditionalSubordinationRelationshipGroup")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOInconditionalSubordinationRelationshipGroup")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOInconditionalSubordinationRelationshipMember")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOInconditionalSubordinationRelationshipMember")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOInconditionalSubordinationRelationshipMember")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOInconditionalSubordinationRelationshipOrg")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOInconditionalSubordinationRelationshipOrg")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOInconditionalSubordinationRelationshipOrg")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOClientServerRelationshipGroup")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOClientServerRelationshipGroup")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOClientServerRelationshipGroup")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOClientServerRelationshipMember")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOClientServerRelationshipMember")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOClientServerRelationshipMember")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOClientServerRelationshipOrg")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOClientServerRelationshipOrg")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AGOClientServerRelationshipOrg")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFSpecifiesExecution")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFSpecifiesExecution")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFSpecifiesExecution")){
			
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
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.IInitiates")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.IInitiates")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.IInitiates")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.IColaborates")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.IColaborates")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.IColaborates")){
			
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
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFPlays")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFPlays")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFPlays")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFDecomposesWF")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFDecomposesWF")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFDecomposesWF")){
			
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
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WSConnects")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WSConnects")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WSConnects")){
			
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "Agent")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "Organization")){
		// Insert an object of type Organization
		nobjects.add(
			new AbstractAction("Insert Organization") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Organization");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Organization is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "OrganizationGroup")){
		// Insert an object of type OrganizationGroup
		nobjects.add(
			new AbstractAction("Insert OrganizationGroup") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "OrganizationGroup");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type OrganizationGroup is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "OrganizationNetwork")){
		// Insert an object of type OrganizationNetwork
		nobjects.add(
			new AbstractAction("Insert OrganizationNetwork") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "OrganizationNetwork");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type OrganizationNetwork is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "Role")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "Resource")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "Application")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "EnvironmentApplication")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "InternalApplication")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "Task")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "Plan")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "Workflow")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "Interaction")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "Goal")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "Fact")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "FrameFact")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "Believe")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "GeneralEvent")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "ApplicationEvent")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "ApplicationEventSlots")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "AutonomousEntityQuery")){
		// Insert an object of type AutonomousEntityQuery
		nobjects.add(
			new AbstractAction("Insert AutonomousEntityQuery") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "AutonomousEntityQuery");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type AutonomousEntityQuery is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "AgentRequirementsQuery")){
		// Insert an object of type AgentRequirementsQuery
		nobjects.add(
			new AbstractAction("Insert AgentRequirementsQuery") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "AgentRequirementsQuery");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type AgentRequirementsQuery is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "ConcreteAgent")){
		// Insert an object of type ConcreteAgent
		nobjects.add(
			new AbstractAction("Insert ConcreteAgent") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "ConcreteAgent");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type ConcreteAgent is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "TextNote")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "UMLComment")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "RoleWS")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "TaskWS")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "GoalStateWS")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "AgentWS")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "WorkflowBox")){
		// Insert an object of type WorkflowBox
		nobjects.add(
			new AbstractAction("Insert WorkflowBox") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "WorkflowBox");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type WorkflowBox is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "ApplicationWS")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "BoxedTask")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "ContextBindingTask")){
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

		if (this.getState().getDiagramFilter().isValidEntity("OrganizationModel", "ContextReleaseTask")){
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

			return nobjects;
	}
	
	




}

