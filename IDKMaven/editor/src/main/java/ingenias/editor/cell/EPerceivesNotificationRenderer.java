

/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz over original code from Ruben Fuentes
 * 
 * Modifications of original jgraph distribution code (jgraph.sourceforge.net)
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

public class EPerceivesNotificationRenderer extends VertexRenderer implements CellViewRenderer{


private static ViewPreferences.ViewType current = ViewPreferences.ViewType.INGENIAS;


  static {
   try {

    	ViewPreferences.ViewType index=ViewPreferences.ViewType.INGENIAS;
	index=ViewPreferences.ViewType.INGENIAS;
	
	
	index=ViewPreferences.ViewType.NOICON;
	RenderComponentManager.loadRenderFile("EPerceivesNotification",
	index,"/rendererxml/EPerceivesNotificationNOICONPanel.xml");
	
	index=ViewPreferences.ViewType.INGENIAS;
	RenderComponentManager.loadRenderFile("EPerceivesNotification",
	index,"/rendererxml/EPerceivesNotificationINGENIASPanel.xml");
	
	index=ViewPreferences.ViewType.LABEL;
	RenderComponentManager.loadRenderFile("EPerceivesNotification",
	index,"/rendererxml/EPerceivesNotificationLABELPanel.xml");
	
	
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }


  }


    /**
     * Constructs a renderer that may be used to render vertices.
     */
     public EPerceivesNotificationRenderer() {
    }

    public Dimension getSize() {
    return RenderComponentManager.getSize("EPerceivesNotification",current);
    }

    public boolean supportsAttribute(Object key) {
    	return true;
        }

 public static void setEntity(ingenias.editor.entities.EPerceivesNotification ent, Map attributes){
 Map currentMap=(Map)  RenderComponentManager.retrieveIDs("EPerceivesNotification",ent.getPrefs(attributes).getView());
current=ent.getPrefs(attributes).getView();
	if (ent != null
				&& currentMap.get("_attributes_") != null
				&& currentMap.get("_attributes_") instanceof ingenias.editor.rendererxml.AttributesPanel) {

			((ingenias.editor.rendererxml.AttributesPanel) currentMap
					.get("_attributes_")).setEntity(ent);

		}

	

      
      if (currentMap.get("Event")!=null){
	   if (ent!=null && ent.getEvent()!=null){
		if (currentMap.get("Event") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("Event")).setText(ent.getEvent().toString());
		} else {
		 if (currentMap.get("Event") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("Event")).setText(ent.getEvent().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("Event") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("Event")).setText("");
	     else {
		if (!(currentMap.get("Event") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("Event")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("ApplicationMethod")!=null){
	   if (ent!=null && ent.getApplicationMethod()!=null){
		if (currentMap.get("ApplicationMethod") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("ApplicationMethod")).setText(ent.getApplicationMethod().toString());
		} else {
		 if (currentMap.get("ApplicationMethod") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("ApplicationMethod")).setText(ent.getApplicationMethod().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("ApplicationMethod") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("ApplicationMethod")).setText("");
	     else {
		if (!(currentMap.get("ApplicationMethod") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("ApplicationMethod")).setText("");
	     }
         }
	  }
	   
      
    
  }
  	public Component getRendererComponent(JGraph graph,
			CellView view, boolean sel,
			boolean focus, boolean preview)
	{
	return RenderComponentManager.retrievePanel("EPerceivesNotification",this.current);
		
	}
	
	public static JPanel setCurrent(ViewPreferences.ViewType c) {
	
		current = ViewPreferences.ViewType.INGENIAS;
	
		return (JPanel) RenderComponentManager.retrievePanel("EPerceivesNotification",c);
	}



}


			