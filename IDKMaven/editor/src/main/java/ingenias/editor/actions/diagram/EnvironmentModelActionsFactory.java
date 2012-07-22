

/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz over original code from Ruben Fuentes and Juan Pavon
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

public class EnvironmentModelActionsFactory extends DiagramMenuEntriesActionsFactory  implements java.io.Serializable {

        public EnvironmentModelActionsFactory(GUIResources resources, IDEState state){
	super(resources,state);
        }


	 protected Vector<AbstractAction> createChangeViewActions(final DefaultGraphCell cell, final ModelJGraph graph) {
		 Vector<AbstractAction> possibleViews=new Vector<AbstractAction>();
		final ingenias.editor.entities.Entity ent=((ingenias.editor.entities.Entity)cell.getUserObject());
		
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.Agent")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                     graph.getListenerContainer().storeContraints(cell);      
                     graph.getModel().getAttributes(cell).put("view", "UML");               				
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIEContext")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                     graph.getListenerContainer().storeContraints(cell);      
                     graph.getModel().getAttributes(cell).put("view", "UML");               				
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIECtxtModel")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                     graph.getListenerContainer().storeContraints(cell);      
                     graph.getModel().getAttributes(cell).put("view", "UML");               				
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIECtxtModel")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                     graph.getListenerContainer().storeContraints(cell);      
                     graph.getModel().getAttributes(cell).put("view", "INGENIAS");               				
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
			
			
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIECtxtValue")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.UML;
                   possibleViews.add(new AbstractAction("UML") {
                     public void actionPerformed(ActionEvent e) {
                     graph.getListenerContainer().storeContraints(cell);      
                     graph.getModel().getAttributes(cell).put("view", "UML");               				
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIECtxtValue")){
			 final ViewPreferences.ViewType current1=ViewPreferences.ViewType.INGENIAS;
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                     graph.getListenerContainer().storeContraints(cell);      
                     graph.getModel().getAttributes(cell).put("view", "INGENIAS");               				
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.UML);
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
			    	 //ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
				 graph.getListenerContainer().restoreContraints(cell);
				graph.invalidate();
				graph.refresh();
                     }
                   });
			 }
			
                  
			
    		      
			
		  
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EPerceives")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "NOICON");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EPerceives")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EPerceives")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "LABEL");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EPerceivesNotification")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "NOICON");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EPerceivesNotification")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EPerceivesNotification")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "LABEL");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EPerceivesPolling")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "NOICON");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EPerceivesPolling")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EPerceivesPolling")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "LABEL");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EResourceBelongsTo")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "NOICON");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EResourceBelongsTo")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.EResourceBelongsTo")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "LABEL");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ApplicationBelongsTo")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "NOICON");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ApplicationBelongsTo")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ApplicationBelongsTo")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "LABEL");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.UMLAnnotatedElement")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "NOICON");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.UMLAnnotatedElement")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.UMLAnnotatedElement")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "LABEL");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.OHasGroup")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "NOICON");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.OHasGroup")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.OHasGroup")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "LABEL");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ODecomposesGroup")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "NOICON");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ODecomposesGroup")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.ODecomposesGroup")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "LABEL");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.CtxtNotifies")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "NOICON");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.CtxtNotifies")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.CtxtNotifies")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "LABEL");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.CtxtUpdates")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "NOICON");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.CtxtUpdates")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.CtxtUpdates")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "LABEL");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIEAppliedTo")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "NOICON");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIEAppliedTo")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIEAppliedTo")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "LABEL");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIEHasValue")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "NOICON");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIEHasValue")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIEHasValue")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "LABEL");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIEGeneratedBy")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "NOICON");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIEGeneratedBy")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIEGeneratedBy")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "LABEL");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIESrcEntity")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "NOICON");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIESrcEntity")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIESrcEntity")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "LABEL");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIETrgtEntity")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "NOICON");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIETrgtEntity")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.FAERIETrgtEntity")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "LABEL");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
			
		    
			 if (ent.getClass().getName().equals("ingenias.editor.entities.HasCtxModel")){
			
                   possibleViews.add(new AbstractAction("NOICON") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "NOICON");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.NOICON,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.HasCtxModel")){
			
                   possibleViews.add(new AbstractAction("INGENIAS") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "INGENIAS");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.INGENIAS,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
			 if (ent.getClass().getName().equals("ingenias.editor.entities.HasCtxModel")){
			
                   possibleViews.add(new AbstractAction("LABEL") {
                     public void actionPerformed(ActionEvent e) {
                      graph.getModel().getAttributes(cell).put("view", "LABEL");     
		  /*   ingenias.editor.cell.RenderComponentManager.setRelationshipView(ViewPreferences.ViewType.LABEL,
                    			 ent, cell, graph);*/
	
				graph.refresh();
                     }
                   });
			 }
			
                     
		 
		 return possibleViews;
		 }
		 
		 

		 protected Vector<AbstractAction> createDiagramSpecificInsertActions(final Point pt, final ModelJGraph graph) {
			 Vector<AbstractAction> nobjects=new Vector<AbstractAction>();

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

		// Insert an object of type AMIContext
		nobjects.add(
			new AbstractAction("Insert AMIContext") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "AMIContext");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type AMIContext is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});

		// Insert an object of type FAERIEContext
		nobjects.add(
			new AbstractAction("Insert FAERIEContext") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "FAERIEContext");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type FAERIEContext is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});

		// Insert an object of type FAERIECtxtModel
		nobjects.add(
			new AbstractAction("Insert FAERIECtxtModel") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "FAERIECtxtModel");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type FAERIECtxtModel is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});

		// Insert an object of type FAERIECtxtAttribute
		nobjects.add(
			new AbstractAction("Insert FAERIECtxtAttribute") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "FAERIECtxtAttribute");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type FAERIECtxtAttribute is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});

		// Insert an object of type FAERIECtxtEntity
		nobjects.add(
			new AbstractAction("Insert FAERIECtxtEntity") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "FAERIECtxtEntity");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type FAERIECtxtEntity is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});

		// Insert an object of type FAERIECtxtRelationship
		nobjects.add(
			new AbstractAction("Insert FAERIECtxtRelationship") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "FAERIECtxtRelationship");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type FAERIECtxtRelationship is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});

		// Insert an object of type FAERIECtxtValue
		nobjects.add(
			new AbstractAction("Insert FAERIECtxtValue") {
				public void actionPerformed(ActionEvent ev) {
					try {
						graph.insert(pt, "FAERIECtxtValue");
					} catch (InvalidEntity e) {						
						e.printStackTrace();
						JOptionPane.showMessageDialog(graph, "Object type FAERIECtxtValue is not allowed in this diagram",
                                "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});

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

			return nobjects;
	}
	
	




}

