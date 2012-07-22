


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



public class SimulationEventRenderer extends CompositeRenderer implements CellViewRenderer, Serializable {

  
	private static ViewPreferences.ViewType current = ViewPreferences.ViewType.INGENIAS;

  static {

    try {

    	ViewPreferences.ViewType index=ViewPreferences.ViewType.INGENIAS;
	index=ViewPreferences.ViewType.INGENIAS;
	
	index=ViewPreferences.ViewType.UML;
	RenderComponentManager.loadRenderFile("SimulationEvent",
	index,"/rendererxml/SimulationEventUMLPanel.xml");
	
	index=ViewPreferences.ViewType.INGENIAS;
	RenderComponentManager.loadRenderFile("SimulationEvent",
	index,"/rendererxml/SimulationEventINGENIASPanel.xml");
	
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }

    /**
     * Constructs a renderer that may be used to render vertices.
     */
    public SimulationEventRenderer() {
    }
    
      public JComponent getConcreteSubComponent(String fieldname, Entity ent, Map map) {
	  Map<String,JComponent> currentMap=(Map)  RenderComponentManager.retrieveIDs("SimulationEvent",ent.getPrefs(map).getView());
	  return currentMap.get(fieldname);
  		}

    public Dimension getSize() {
    return RenderComponentManager.getSize("SimulationEvent",current);
    }

    public boolean supportsAttribute(Object key) {
    	return true;
        }

  public static void setEntity(SimulationEvent ent, Map attributes){
  Map currentMap=(Map)  RenderComponentManager.retrieveIDs("SimulationEvent",ent.getPrefs(attributes).getView());
  current=ent.getPrefs(attributes).getView();
		if (ent != null
				&& currentMap.get("_attributes_") != null
				&& currentMap.get("_attributes_") instanceof ingenias.editor.rendererxml.AttributesPanel) {

			((ingenias.editor.rendererxml.AttributesPanel) currentMap
					.get("_attributes_")).setEntity(ent);

		}

	

      
      if (currentMap.get("ProducedAtSimTime")!=null){
	   if (ent!=null && ent.getProducedAtSimTime()!=null){
		if (currentMap.get("ProducedAtSimTime") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("ProducedAtSimTime")).setText(ent.getProducedAtSimTime().toString());
		} else {
		 if (currentMap.get("ProducedAtSimTime") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("ProducedAtSimTime")).setText(ent.getProducedAtSimTime().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("ProducedAtSimTime") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("ProducedAtSimTime")).setText("");
	     else {
		if (!(currentMap.get("ProducedAtSimTime") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("ProducedAtSimTime")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("InsertionFrequency")!=null){
	   if (ent!=null && ent.getInsertionFrequency()!=null){
		if (currentMap.get("InsertionFrequency") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("InsertionFrequency")).setText(ent.getInsertionFrequency().toString());
		} else {
		 if (currentMap.get("InsertionFrequency") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("InsertionFrequency")).setText(ent.getInsertionFrequency().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("InsertionFrequency") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("InsertionFrequency")).setText("");
	     else {
		if (!(currentMap.get("InsertionFrequency") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("InsertionFrequency")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("FinishedAtSimTime")!=null){
	   if (ent!=null && ent.getFinishedAtSimTime()!=null){
		if (currentMap.get("FinishedAtSimTime") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("FinishedAtSimTime")).setText(ent.getFinishedAtSimTime().toString());
		} else {
		 if (currentMap.get("FinishedAtSimTime") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("FinishedAtSimTime")).setText(ent.getFinishedAtSimTime().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("FinishedAtSimTime") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("FinishedAtSimTime")).setText("");
	     else {
		if (!(currentMap.get("FinishedAtSimTime") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("FinishedAtSimTime")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("ReceivedByAgentsInDeployment")!=null){
	   if (ent!=null && ent.getReceivedByAgentsInDeployment()!=null){
		if (currentMap.get("ReceivedByAgentsInDeployment") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("ReceivedByAgentsInDeployment")).setText(ent.getReceivedByAgentsInDeployment().toString());
		} else {
		 if (currentMap.get("ReceivedByAgentsInDeployment") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("ReceivedByAgentsInDeployment")).setText(ent.getReceivedByAgentsInDeployment().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("ReceivedByAgentsInDeployment") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("ReceivedByAgentsInDeployment")).setText("");
	     else {
		if (!(currentMap.get("ReceivedByAgentsInDeployment") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("ReceivedByAgentsInDeployment")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("NewInformation")!=null){
	   if (ent!=null && ent.getNewInformation()!=null){
		if (currentMap.get("NewInformation") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("NewInformation")).setText(ent.getNewInformation().toString());
		} else {
		 if (currentMap.get("NewInformation") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("NewInformation")).setText(ent.getNewInformation().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("NewInformation") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("NewInformation")).setText("");
	     else {
		if (!(currentMap.get("NewInformation") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("NewInformation")).setText("");
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
	return RenderComponentManager.retrievePanel("SimulationEvent",
	((Entity)((DefaultGraphCell)(view.getCell())).getUserObject()).getPrefs(graph.getModel().getAttributes(view.getCell())).getView());
		
	}
	
	public static JPanel setCurrent(ViewPreferences.ViewType c) {
	
		current = ViewPreferences.ViewType.INGENIAS;
	
		return (JPanel) RenderComponentManager.retrievePanel("SimulationEvent",current);
	}
	

}
