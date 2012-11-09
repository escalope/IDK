


/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz over original code from Ruben Fuentes
 * 
 *   Modifications over original code from Jon Barrileaux.
 *   It has been significantly modified to enhance drawing speed.
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


package ingenias.editor.cell;


import org.jgraph.JGraph;
import org.jgraph.graph.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.util.*;
import ingenias.editor.entities.*;
import ingenias.editor.entities.*;
import org.swixml.SwingEngine;
import ingenias.editor.entities.Entity;



public class DeploymentPackageWithContextRenderer extends CompositeRenderer implements CellViewRenderer, Serializable {

  
	private static ViewPreferences.ViewType current = ViewPreferences.ViewType.INGENIAS;

  static {

    try {

    	ViewPreferences.ViewType index=ViewPreferences.ViewType.INGENIAS;
	index=ViewPreferences.ViewType.INGENIAS;
	
	index=ViewPreferences.ViewType.UML;
	RenderComponentManager.loadRenderFile("DeploymentPackageWithContext",
	index,"/rendererxml/DeploymentPackageWithContextUMLPanel.xml");
	
	index=ViewPreferences.ViewType.INGENIAS;
	RenderComponentManager.loadRenderFile("DeploymentPackageWithContext",
	index,"/rendererxml/DeploymentPackageWithContextINGENIASPanel.xml");
	
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }

    /**
     * Constructs a renderer that may be used to render vertices.
     */
    public DeploymentPackageWithContextRenderer() {
    }
    
      public JComponent getConcreteSubComponent(String fieldname, Entity ent, Map map) {
	  Map<String,JComponent> currentMap=(Map)  RenderComponentManager.retrieveIDs("DeploymentPackageWithContext",ent.getPrefs(map).getView());
	  return currentMap.get(fieldname);
  		}

    public Dimension getSize() {
    return RenderComponentManager.getSize("DeploymentPackageWithContext",current);
    }

    public boolean supportsAttribute(Object key) {
    	return true;
        }

  public static void setEntity(DeploymentPackageWithContext ent, Map attributes){
  Map currentMap=(Map)  RenderComponentManager.retrieveIDs("DeploymentPackageWithContext",ent.getPrefs(attributes).getView());
  current=ent.getPrefs(attributes).getView();
		if (ent != null
				&& currentMap.get("_attributes_") != null
				&& currentMap.get("_attributes_") instanceof ingenias.editor.rendererxml.AttributesPanel) {

			((ingenias.editor.rendererxml.AttributesPanel) currentMap
					.get("_attributes_")).setEntity(ent);

		}

	
        if (currentMap.get("Context")!=null && 
            currentMap.get("Context") instanceof ingenias.editor.rendererxml.CollectionPanel) {
          try {
            ( (ingenias.editor.rendererxml.CollectionPanel) currentMap.get(
                "Context")).setCollection("Context",ent.Context, ent.Context.getType());
          }
          catch (IllegalArgumentException ex) {
            ex.printStackTrace();
          }
          catch (IllegalAccessException ex) {
            ex.printStackTrace();
          }
        }
	
        if (currentMap.get("ContextModelInstantiation")!=null && 
            currentMap.get("ContextModelInstantiation") instanceof ingenias.editor.rendererxml.CollectionPanel) {
          try {
            ( (ingenias.editor.rendererxml.CollectionPanel) currentMap.get(
                "ContextModelInstantiation")).setCollection("ContextModelInstantiation",ent.ContextModelInstantiation, ent.ContextModelInstantiation.getType());
          }
          catch (IllegalArgumentException ex) {
            ex.printStackTrace();
          }
          catch (IllegalAccessException ex) {
            ex.printStackTrace();
          }
        }
	
        if (currentMap.get("Parameters")!=null && 
            currentMap.get("Parameters") instanceof ingenias.editor.rendererxml.CollectionPanel) {
          try {
            ( (ingenias.editor.rendererxml.CollectionPanel) currentMap.get(
                "Parameters")).setCollection("Parameters",ent.Parameters, ent.Parameters.getType());
          }
          catch (IllegalArgumentException ex) {
            ex.printStackTrace();
          }
          catch (IllegalAccessException ex) {
            ex.printStackTrace();
          }
        }
	
        if (currentMap.get("Context")!=null && 
            currentMap.get("Context") instanceof ingenias.editor.rendererxml.CollectionPanel) {
          try {
            ( (ingenias.editor.rendererxml.CollectionPanel) currentMap.get(
                "Context")).setCollection("Context",ent.Context, ent.Context.getType());
          }
          catch (IllegalArgumentException ex) {
            ex.printStackTrace();
          }
          catch (IllegalAccessException ex) {
            ex.printStackTrace();
          }
        }
	
        if (currentMap.get("ContextModelInstantiation")!=null && 
            currentMap.get("ContextModelInstantiation") instanceof ingenias.editor.rendererxml.CollectionPanel) {
          try {
            ( (ingenias.editor.rendererxml.CollectionPanel) currentMap.get(
                "ContextModelInstantiation")).setCollection("ContextModelInstantiation",ent.ContextModelInstantiation, ent.ContextModelInstantiation.getType());
          }
          catch (IllegalArgumentException ex) {
            ex.printStackTrace();
          }
          catch (IllegalAccessException ex) {
            ex.printStackTrace();
          }
        }
	

      
      if (currentMap.get("Parameters")!=null){
	   if (ent!=null && ent.getParameters()!=null){
		if (currentMap.get("Parameters") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("Parameters")).setText(ent.getParameters().toString());
		} else {
		 if (currentMap.get("Parameters") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("Parameters")).setText(ent.getParameters().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("Parameters") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("Parameters")).setText("");
	     else {
		if (!(currentMap.get("Parameters") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("Parameters")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("AgentsDeployed")!=null){
	   if (ent!=null && ent.getAgentsDeployed()!=null){
		if (currentMap.get("AgentsDeployed") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("AgentsDeployed")).setText(ent.getAgentsDeployed().toString());
		} else {
		 if (currentMap.get("AgentsDeployed") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("AgentsDeployed")).setText(ent.getAgentsDeployed().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("AgentsDeployed") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("AgentsDeployed")).setText("");
	     else {
		if (!(currentMap.get("AgentsDeployed") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("AgentsDeployed")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("Context")!=null){
	   if (ent!=null && ent.getContext()!=null){
		if (currentMap.get("Context") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("Context")).setText(ent.getContext().toString());
		} else {
		 if (currentMap.get("Context") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("Context")).setText(ent.getContext().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("Context") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("Context")).setText("");
	     else {
		if (!(currentMap.get("Context") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("Context")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("ContextModelInstantiation")!=null){
	   if (ent!=null && ent.getContextModelInstantiation()!=null){
		if (currentMap.get("ContextModelInstantiation") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("ContextModelInstantiation")).setText(ent.getContextModelInstantiation().toString());
		} else {
		 if (currentMap.get("ContextModelInstantiation") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("ContextModelInstantiation")).setText(ent.getContextModelInstantiation().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("ContextModelInstantiation") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("ContextModelInstantiation")).setText("");
	     else {
		if (!(currentMap.get("ContextModelInstantiation") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("ContextModelInstantiation")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("Id")!=null){
	   if (ent!=null && ent.getId()!=null){
		if (currentMap.get("Id") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("Id")).setText(ent.getId().toString());
		} else {
		 if (currentMap.get("Id") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("Id")).setText(ent.getId().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("Id") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("Id")).setText("");
	     else {
		if (!(currentMap.get("Id") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("Id")).setText("");
	     }
         }
	  }
	   
      
    
  }


	public Component getRendererComponent(JGraph graph,
			CellView view, boolean sel,
			boolean focus, boolean preview)
	{
	return RenderComponentManager.retrievePanel("DeploymentPackageWithContext",
	((Entity)((DefaultGraphCell)(view.getCell())).getUserObject()).getPrefs(graph.getModel().getAttributes(view.getCell())).getView());
		
	}
	
	public static JPanel setCurrent(ViewPreferences.ViewType c) {
	
		current = ViewPreferences.ViewType.INGENIAS;
	
		return (JPanel) RenderComponentManager.retrievePanel("DeploymentPackageWithContext",current);
	}
	

}
