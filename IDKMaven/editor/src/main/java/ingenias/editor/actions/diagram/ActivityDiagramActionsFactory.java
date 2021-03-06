


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

public class ActivityDiagramActionsFactory extends DiagramMenuEntriesActionsFactory  implements java.io.Serializable {

        public ActivityDiagramActionsFactory(GUIResources resources, IDEState state){
	super(resources,state);
        }


	 public Vector<AbstractAction> createChangeViewActions(final DefaultGraphCell cell, final ModelJGraph graph) {
		 Vector<AbstractAction> possibleViews=new Vector<AbstractAction>();
		final ingenias.editor.entities.Entity ent=((ingenias.editor.entities.Entity)cell.getUserObject());
		
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.InitialNode")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.InitialNode")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.DecisionNode")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.DecisionNode")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ForkNode")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ForkNode")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EndNode")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EndNode")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ContextUseTask")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ContextUseTask")){
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
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFStarts")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFStarts")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFStarts")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFEnds")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFEnds")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFEnds")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFDecides")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFDecides")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFDecides")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFFollows")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFFollows")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFFollows")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFFollowsGuarded")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFFollowsGuarded")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.WFFollowsGuarded")){
			
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

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "InitialNode")){
		// Insert an object of type InitialNode
		AbstractAction aa=new AbstractAction("Insert InitialNode") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "InitialNode");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type InitialNode is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new InitialNode("").getHelpDesc()+"\n\n"+new InitialNode("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "DecisionNode")){
		// Insert an object of type DecisionNode
		AbstractAction aa=new AbstractAction("Insert DecisionNode") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "DecisionNode");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type DecisionNode is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new DecisionNode("").getHelpDesc()+"\n\n"+new DecisionNode("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "ForkNode")){
		// Insert an object of type ForkNode
		AbstractAction aa=new AbstractAction("Insert ForkNode") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "ForkNode");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type ForkNode is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new ForkNode("").getHelpDesc()+"\n\n"+new ForkNode("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "EndNode")){
		// Insert an object of type EndNode
		AbstractAction aa=new AbstractAction("Insert EndNode") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "EndNode");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type EndNode is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new EndNode("").getHelpDesc()+"\n\n"+new EndNode("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "Task")){
		// Insert an object of type Task
		AbstractAction aa=new AbstractAction("Insert Task") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Task");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Task is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new Task("").getHelpDesc()+"\n\n"+new Task("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "Role")){
		// Insert an object of type Role
		AbstractAction aa=new AbstractAction("Insert Role") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Role");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Role is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new Role("").getHelpDesc()+"\n\n"+new Role("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "WorkflowBox")){
		// Insert an object of type WorkflowBox
		AbstractAction aa=new AbstractAction("Insert WorkflowBox") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "WorkflowBox");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type WorkflowBox is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new WorkflowBox("").getHelpDesc()+"\n\n"+new WorkflowBox("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "UMLComment")){
		// Insert an object of type UMLComment
		AbstractAction aa=new AbstractAction("Insert UMLComment") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "UMLComment");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type UMLComment is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new UMLComment("").getHelpDesc()+"\n\n"+new UMLComment("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "TextNote")){
		// Insert an object of type TextNote
		AbstractAction aa=new AbstractAction("Insert TextNote") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "TextNote");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type TextNote is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new TextNote("").getHelpDesc()+"\n\n"+new TextNote("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "TaskWS")){
		// Insert an object of type TaskWS
		AbstractAction aa=new AbstractAction("Insert TaskWS") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "TaskWS");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type TaskWS is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new TaskWS("").getHelpDesc()+"\n\n"+new TaskWS("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "ContextUseTask")){
		// Insert an object of type ContextUseTask
		AbstractAction aa=new AbstractAction("Insert ContextUseTask") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "ContextUseTask");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type ContextUseTask is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new ContextUseTask("").getHelpDesc()+"\n\n"+new ContextUseTask("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "Workflow")){
		// Insert an object of type Workflow
		AbstractAction aa=new AbstractAction("Insert Workflow") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Workflow");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Workflow is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new Workflow("").getHelpDesc()+"\n\n"+new Workflow("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "BoxedTask")){
		// Insert an object of type BoxedTask
		AbstractAction aa=new AbstractAction("Insert BoxedTask") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "BoxedTask");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type BoxedTask is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new BoxedTask("").getHelpDesc()+"\n\n"+new BoxedTask("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "RoleWS")){
		// Insert an object of type RoleWS
		AbstractAction aa=new AbstractAction("Insert RoleWS") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "RoleWS");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type RoleWS is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new RoleWS("").getHelpDesc()+"\n\n"+new RoleWS("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "ContextBindingTask")){
		// Insert an object of type ContextBindingTask
		AbstractAction aa=new AbstractAction("Insert ContextBindingTask") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "ContextBindingTask");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type ContextBindingTask is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new ContextBindingTask("").getHelpDesc()+"\n\n"+new ContextBindingTask("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "ContextReleaseTask")){
		// Insert an object of type ContextReleaseTask
		AbstractAction aa=new AbstractAction("Insert ContextReleaseTask") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "ContextReleaseTask");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type ContextReleaseTask is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new ContextReleaseTask("").getHelpDesc()+"\n\n"+new ContextReleaseTask("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("ActivityDiagram", "Plan")){
		// Insert an object of type Plan
		AbstractAction aa=new AbstractAction("Insert Plan") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Plan");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Plan is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new Plan("").getHelpDesc()+"\n\n"+new Plan("").getHelpRecom());
		nobjects.add(aa);
		}

			return nobjects;
	}
	
	




}

