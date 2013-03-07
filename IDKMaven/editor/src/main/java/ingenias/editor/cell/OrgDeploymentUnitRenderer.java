


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



public class OrgDeploymentUnitRenderer extends CompositeRenderer implements CellViewRenderer, Serializable {

  
	private static ViewPreferences.ViewType current = ViewPreferences.ViewType.INGENIAS;

  static {

    try {

    	ViewPreferences.ViewType index=ViewPreferences.ViewType.INGENIAS;
	index=ViewPreferences.ViewType.INGENIAS;
	
	index=ViewPreferences.ViewType.UML;
	RenderComponentManager.loadRenderFile("OrgDeploymentUnit",
	index,"/rendererxml/OrgDeploymentUnitUMLPanel.xml");
	
	index=ViewPreferences.ViewType.INGENIAS;
	RenderComponentManager.loadRenderFile("OrgDeploymentUnit",
	index,"/rendererxml/OrgDeploymentUnitINGENIASPanel.xml");
	
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }

    /**
     * Constructs a renderer that may be used to render vertices.
     */
    public OrgDeploymentUnitRenderer() {
    }
    
      public JComponent getConcreteSubComponent(String fieldname, Entity ent, Map map) {
	  Map<String,JComponent> currentMap=(Map)  RenderComponentManager.retrieveIDs("OrgDeploymentUnit",ent.getPrefs(map).getView());
	  return currentMap.get(fieldname);
  		}

    public Dimension getSize() {
    return RenderComponentManager.getSize("OrgDeploymentUnit",current);
    }

    public boolean supportsAttribute(Object key) {
    	return true;
        }

  public static void setEntity(OrgDeploymentUnit ent, Map attributes){
  Map currentMap=(Map)  RenderComponentManager.retrieveIDs("OrgDeploymentUnit",ent.getPrefs(attributes).getView());
  current=ent.getPrefs(attributes).getView();
		if (ent != null
				&& currentMap.get("_attributes_") != null
				&& currentMap.get("_attributes_") instanceof ingenias.editor.rendererxml.AttributesPanel) {

			((ingenias.editor.rendererxml.AttributesPanel) currentMap
					.get("_attributes_")).setEntity(ent);

		}

	

      
      if (currentMap.get("InstanceName")!=null){
	   if (ent!=null && ent.getInstanceName()!=null){
		if (currentMap.get("InstanceName") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("InstanceName")).setText(ent.getInstanceName().toString());
		} else {
		 if (currentMap.get("InstanceName") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("InstanceName")).setText(ent.getInstanceName().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("InstanceName") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("InstanceName")).setText("");
	     else {
		if (!(currentMap.get("InstanceName") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("InstanceName")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("InstOrganization")!=null){
	   if (ent!=null && ent.getInstOrganization()!=null){
		if (currentMap.get("InstOrganization") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("InstOrganization")).setText(ent.getInstOrganization().toString());
		} else {
		 if (currentMap.get("InstOrganization") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("InstOrganization")).setText(ent.getInstOrganization().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("InstOrganization") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("InstOrganization")).setText("");
	     else {
		if (!(currentMap.get("InstOrganization") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("InstOrganization")).setText("");
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
	return RenderComponentManager.retrievePanel("OrgDeploymentUnit",
	((Entity)((DefaultGraphCell)(view.getCell())).getUserObject()).getPrefs(graph.getModel().getAttributes(view.getCell())).getView());
		
	}
	
	public static JPanel setCurrent(ViewPreferences.ViewType c) {
	
		current = ViewPreferences.ViewType.INGENIAS;
	
		return (JPanel) RenderComponentManager.retrievePanel("OrgDeploymentUnit",current);
	}
	

}
