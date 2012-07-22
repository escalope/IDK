

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

public class CtxtUpdatesRenderer extends VertexRenderer implements CellViewRenderer{


private static ViewPreferences.ViewType current = ViewPreferences.ViewType.INGENIAS;


  static {
   try {

    	ViewPreferences.ViewType index=ViewPreferences.ViewType.INGENIAS;
	index=ViewPreferences.ViewType.INGENIAS;
	
	
	index=ViewPreferences.ViewType.NOICON;
	RenderComponentManager.loadRenderFile("CtxtUpdates",
	index,"/rendererxml/CtxtUpdatesNOICONPanel.xml");
	
	index=ViewPreferences.ViewType.INGENIAS;
	RenderComponentManager.loadRenderFile("CtxtUpdates",
	index,"/rendererxml/CtxtUpdatesINGENIASPanel.xml");
	
	index=ViewPreferences.ViewType.LABEL;
	RenderComponentManager.loadRenderFile("CtxtUpdates",
	index,"/rendererxml/CtxtUpdatesLABELPanel.xml");
	
	
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }


  }


    /**
     * Constructs a renderer that may be used to render vertices.
     */
     public CtxtUpdatesRenderer() {
    }

    public Dimension getSize() {
    return RenderComponentManager.getSize("CtxtUpdates",current);
    }

    public boolean supportsAttribute(Object key) {
    	return true;
        }

 public static void setEntity(ingenias.editor.entities.CtxtUpdates ent, Map attributes){
 Map currentMap=(Map)  RenderComponentManager.retrieveIDs("CtxtUpdates",ent.getPrefs(attributes).getView());
current=ent.getPrefs(attributes).getView();
	if (ent != null
				&& currentMap.get("_attributes_") != null
				&& currentMap.get("_attributes_") instanceof ingenias.editor.rendererxml.AttributesPanel) {

			((ingenias.editor.rendererxml.AttributesPanel) currentMap
					.get("_attributes_")).setEntity(ent);

		}

	

      
      if (currentMap.get("UpdateWith")!=null){
	   if (ent!=null && ent.getUpdateWith()!=null){
		if (currentMap.get("UpdateWith") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("UpdateWith")).setText(ent.getUpdateWith().toString());
		} else {
		 if (currentMap.get("UpdateWith") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("UpdateWith")).setText(ent.getUpdateWith().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("UpdateWith") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("UpdateWith")).setText("");
	     else {
		if (!(currentMap.get("UpdateWith") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("UpdateWith")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("Label")!=null){
	   if (ent!=null && ent.getLabel()!=null){
		if (currentMap.get("Label") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("Label")).setText(ent.getLabel().toString());
		} else {
		 if (currentMap.get("Label") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("Label")).setText(ent.getLabel().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("Label") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("Label")).setText("");
	     else {
		if (!(currentMap.get("Label") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("Label")).setText("");
	     }
         }
	  }
	   
      
    
  }
  	public Component getRendererComponent(JGraph graph,
			CellView view, boolean sel,
			boolean focus, boolean preview)
	{
	return RenderComponentManager.retrievePanel("CtxtUpdates",this.current);
		
	}
	
	public static JPanel setCurrent(ViewPreferences.ViewType c) {
	
		current = ViewPreferences.ViewType.INGENIAS;
	
		return (JPanel) RenderComponentManager.retrievePanel("CtxtUpdates",c);
	}



}


			