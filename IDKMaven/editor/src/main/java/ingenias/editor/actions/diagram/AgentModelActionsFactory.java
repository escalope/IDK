


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

public class AgentModelActionsFactory extends DiagramMenuEntriesActionsFactory  implements java.io.Serializable {

        public AgentModelActionsFactory(GUIResources resources, IDEState state){
	super(resources,state);
        }


	 public Vector<AbstractAction> createChangeViewActions(final DefaultGraphCell cell, final ModelJGraph graph) {
		 Vector<AbstractAction> possibleViews=new Vector<AbstractAction>();
		final ingenias.editor.entities.Entity ent=((ingenias.editor.entities.Entity)cell.getUserObject());
		
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIEContext")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIEContext")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.MentalStateProcessor")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.MentalStateProcessor")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.MentalStateManager")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.MentalStateManager")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.MentalState")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.MentalState")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ConditionalMentalState")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ConditionalMentalState")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.MentalInstanceSpecification")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.MentalInstanceSpecification")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIECtxtRelationship")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIECtxtRelationship")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIECtxtEntity")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIECtxtEntity")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIECtxtAttribute")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIECtxtAttribute")){
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
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AMIContext")){
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
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AMIContext")){
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
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AInherits")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AInherits")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AInherits")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AHasMS")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AHasMS")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AHasMS")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AHasMSManager")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AHasMSManager")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AHasMSManager")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AHasMSProcessor")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AHasMSProcessor")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AHasMSProcessor")){
			
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
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AInstanceOf")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AInstanceOf")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AInstanceOf")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AContainsME")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AContainsME")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.AContainsME")){
			
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
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ARoleInheritance")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ARoleInheritance")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ARoleInheritance")){
			
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
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ApplicationBelongsTo")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ApplicationBelongsTo")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
		     ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ApplicationBelongsTo")){
			
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

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "FAERIEContext")){
		// Insert an object of type FAERIEContext
		AbstractAction aa=new AbstractAction("Insert FAERIEContext") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "FAERIEContext");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type FAERIEContext is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new FAERIEContext("").getHelpDesc()+"\n\n"+new FAERIEContext("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "Agent")){
		// Insert an object of type Agent
		AbstractAction aa=new AbstractAction("Insert Agent") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Agent");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Agent is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new Agent("").getHelpDesc()+"\n\n"+new Agent("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "Role")){
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

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "Goal")){
		// Insert an object of type Goal
		AbstractAction aa=new AbstractAction("Insert Goal") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Goal");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Goal is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new Goal("").getHelpDesc()+"\n\n"+new Goal("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "Plan")){
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

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "StateGoal")){
		// Insert an object of type StateGoal
		AbstractAction aa=new AbstractAction("Insert StateGoal") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "StateGoal");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type StateGoal is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new StateGoal("").getHelpDesc()+"\n\n"+new StateGoal("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "Fact")){
		// Insert an object of type Fact
		AbstractAction aa=new AbstractAction("Insert Fact") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Fact");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Fact is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new Fact("").getHelpDesc()+"\n\n"+new Fact("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "Task")){
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

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "FrameFact")){
		// Insert an object of type FrameFact
		AbstractAction aa=new AbstractAction("Insert FrameFact") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "FrameFact");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type FrameFact is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new FrameFact("").getHelpDesc()+"\n\n"+new FrameFact("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "Believe")){
		// Insert an object of type Believe
		AbstractAction aa=new AbstractAction("Insert Believe") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Believe");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Believe is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new Believe("").getHelpDesc()+"\n\n"+new Believe("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "Compromise")){
		// Insert an object of type Compromise
		AbstractAction aa=new AbstractAction("Insert Compromise") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Compromise");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Compromise is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new Compromise("").getHelpDesc()+"\n\n"+new Compromise("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "GeneralEvent")){
		// Insert an object of type GeneralEvent
		AbstractAction aa=new AbstractAction("Insert GeneralEvent") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "GeneralEvent");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type GeneralEvent is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new GeneralEvent("").getHelpDesc()+"\n\n"+new GeneralEvent("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "ApplicationEvent")){
		// Insert an object of type ApplicationEvent
		AbstractAction aa=new AbstractAction("Insert ApplicationEvent") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "ApplicationEvent");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type ApplicationEvent is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new ApplicationEvent("").getHelpDesc()+"\n\n"+new ApplicationEvent("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "ApplicationEventSlots")){
		// Insert an object of type ApplicationEventSlots
		AbstractAction aa=new AbstractAction("Insert ApplicationEventSlots") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "ApplicationEventSlots");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type ApplicationEventSlots is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new ApplicationEventSlots("").getHelpDesc()+"\n\n"+new ApplicationEventSlots("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "MentalStateProcessor")){
		// Insert an object of type MentalStateProcessor
		AbstractAction aa=new AbstractAction("Insert MentalStateProcessor") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "MentalStateProcessor");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type MentalStateProcessor is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new MentalStateProcessor("").getHelpDesc()+"\n\n"+new MentalStateProcessor("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "MentalStateManager")){
		// Insert an object of type MentalStateManager
		AbstractAction aa=new AbstractAction("Insert MentalStateManager") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "MentalStateManager");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type MentalStateManager is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new MentalStateManager("").getHelpDesc()+"\n\n"+new MentalStateManager("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "MentalState")){
		// Insert an object of type MentalState
		AbstractAction aa=new AbstractAction("Insert MentalState") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "MentalState");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type MentalState is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new MentalState("").getHelpDesc()+"\n\n"+new MentalState("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "ConditionalMentalState")){
		// Insert an object of type ConditionalMentalState
		AbstractAction aa=new AbstractAction("Insert ConditionalMentalState") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "ConditionalMentalState");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type ConditionalMentalState is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new ConditionalMentalState("").getHelpDesc()+"\n\n"+new ConditionalMentalState("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "AutonomousEntityQuery")){
		// Insert an object of type AutonomousEntityQuery
		AbstractAction aa=new AbstractAction("Insert AutonomousEntityQuery") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "AutonomousEntityQuery");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type AutonomousEntityQuery is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new AutonomousEntityQuery("").getHelpDesc()+"\n\n"+new AutonomousEntityQuery("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "AgentRequirementsQuery")){
		// Insert an object of type AgentRequirementsQuery
		AbstractAction aa=new AbstractAction("Insert AgentRequirementsQuery") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "AgentRequirementsQuery");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type AgentRequirementsQuery is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new AgentRequirementsQuery("").getHelpDesc()+"\n\n"+new AgentRequirementsQuery("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "ConcreteAgent")){
		// Insert an object of type ConcreteAgent
		AbstractAction aa=new AbstractAction("Insert ConcreteAgent") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "ConcreteAgent");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type ConcreteAgent is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new ConcreteAgent("").getHelpDesc()+"\n\n"+new ConcreteAgent("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "AgentModelBelieve")){
		// Insert an object of type AgentModelBelieve
		AbstractAction aa=new AbstractAction("Insert AgentModelBelieve") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "AgentModelBelieve");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type AgentModelBelieve is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new AgentModelBelieve("").getHelpDesc()+"\n\n"+new AgentModelBelieve("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "Conversation")){
		// Insert an object of type Conversation
		AbstractAction aa=new AbstractAction("Insert Conversation") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Conversation");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Conversation is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new Conversation("").getHelpDesc()+"\n\n"+new Conversation("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "UMLComment")){
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

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "TextNote")){
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

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "Application")){
		// Insert an object of type Application
		AbstractAction aa=new AbstractAction("Insert Application") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Application");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Application is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new Application("").getHelpDesc()+"\n\n"+new Application("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "EnvironmentApplication")){
		// Insert an object of type EnvironmentApplication
		AbstractAction aa=new AbstractAction("Insert EnvironmentApplication") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "EnvironmentApplication");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type EnvironmentApplication is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new EnvironmentApplication("").getHelpDesc()+"\n\n"+new EnvironmentApplication("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "InternalApplication")){
		// Insert an object of type InternalApplication
		AbstractAction aa=new AbstractAction("Insert InternalApplication") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "InternalApplication");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type InternalApplication is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new InternalApplication("").getHelpDesc()+"\n\n"+new InternalApplication("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "RoleWS")){
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

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "TaskWS")){
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

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "GoalStateWS")){
		// Insert an object of type GoalStateWS
		AbstractAction aa=new AbstractAction("Insert GoalStateWS") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "GoalStateWS");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type GoalStateWS is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new GoalStateWS("").getHelpDesc()+"\n\n"+new GoalStateWS("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "AgentWS")){
		// Insert an object of type AgentWS
		AbstractAction aa=new AbstractAction("Insert AgentWS") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "AgentWS");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type AgentWS is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new AgentWS("").getHelpDesc()+"\n\n"+new AgentWS("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "MentalInstanceSpecification")){
		// Insert an object of type MentalInstanceSpecification
		AbstractAction aa=new AbstractAction("Insert MentalInstanceSpecification") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "MentalInstanceSpecification");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type MentalInstanceSpecification is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new MentalInstanceSpecification("").getHelpDesc()+"\n\n"+new MentalInstanceSpecification("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "ApplicationWS")){
		// Insert an object of type ApplicationWS
		AbstractAction aa=new AbstractAction("Insert ApplicationWS") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "ApplicationWS");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type ApplicationWS is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new ApplicationWS("").getHelpDesc()+"\n\n"+new ApplicationWS("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "RuntimeCommFailure")){
		// Insert an object of type RuntimeCommFailure
		AbstractAction aa=new AbstractAction("Insert RuntimeCommFailure") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "RuntimeCommFailure");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type RuntimeCommFailure is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new RuntimeCommFailure("").getHelpDesc()+"\n\n"+new RuntimeCommFailure("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "FAERIECtxtRelationship")){
		// Insert an object of type FAERIECtxtRelationship
		AbstractAction aa=new AbstractAction("Insert FAERIECtxtRelationship") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "FAERIECtxtRelationship");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type FAERIECtxtRelationship is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new FAERIECtxtRelationship("").getHelpDesc()+"\n\n"+new FAERIECtxtRelationship("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "RuntimeEvent")){
		// Insert an object of type RuntimeEvent
		AbstractAction aa=new AbstractAction("Insert RuntimeEvent") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "RuntimeEvent");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type RuntimeEvent is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new RuntimeEvent("").getHelpDesc()+"\n\n"+new RuntimeEvent("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "FAERIECtxtEntity")){
		// Insert an object of type FAERIECtxtEntity
		AbstractAction aa=new AbstractAction("Insert FAERIECtxtEntity") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "FAERIECtxtEntity");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type FAERIECtxtEntity is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new FAERIECtxtEntity("").getHelpDesc()+"\n\n"+new FAERIECtxtEntity("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "FAERIECtxtAttribute")){
		// Insert an object of type FAERIECtxtAttribute
		AbstractAction aa=new AbstractAction("Insert FAERIECtxtAttribute") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "FAERIECtxtAttribute");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type FAERIECtxtAttribute is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new FAERIECtxtAttribute("").getHelpDesc()+"\n\n"+new FAERIECtxtAttribute("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "Interaction")){
		// Insert an object of type Interaction
		AbstractAction aa=new AbstractAction("Insert Interaction") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "Interaction");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type Interaction is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new Interaction("").getHelpDesc()+"\n\n"+new Interaction("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "BoxedTask")){
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

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "RuntimeConversation")){
		// Insert an object of type RuntimeConversation
		AbstractAction aa=new AbstractAction("Insert RuntimeConversation") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "RuntimeConversation");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type RuntimeConversation is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new RuntimeConversation("").getHelpDesc()+"\n\n"+new RuntimeConversation("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "CommunicationEvent")){
		// Insert an object of type CommunicationEvent
		AbstractAction aa=new AbstractAction("Insert CommunicationEvent") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "CommunicationEvent");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type CommunicationEvent is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new CommunicationEvent("").getHelpDesc()+"\n\n"+new CommunicationEvent("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "AMIContext")){
		// Insert an object of type AMIContext
		AbstractAction aa=new AbstractAction("Insert AMIContext") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "AMIContext");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type AMIContext is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new AMIContext("").getHelpDesc()+"\n\n"+new AMIContext("").getHelpRecom());
		nobjects.add(aa);
		}

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "ContextBindingTask")){
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

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "ContextReleaseTask")){
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

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "ContextUseTask")){
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

		if (this.getState().getDiagramFilter().isValidEntity("AgentModel", "RuntimeFact")){
		// Insert an object of type RuntimeFact
		AbstractAction aa=new AbstractAction("Insert RuntimeFact") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "RuntimeFact");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type RuntimeFact is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			};
		aa.putValue("tooltip", new RuntimeFact("").getHelpDesc()+"\n\n"+new RuntimeFact("").getHelpRecom());
		nobjects.add(aa);
		}

			return nobjects;
	}
	
	




}

