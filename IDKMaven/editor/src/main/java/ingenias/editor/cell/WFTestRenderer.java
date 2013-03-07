


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



public class WFTestRenderer extends CompositeRenderer implements CellViewRenderer, Serializable {

  
	private static ViewPreferences.ViewType current = ViewPreferences.ViewType.INGENIAS;

  static {

    try {

    	ViewPreferences.ViewType index=ViewPreferences.ViewType.INGENIAS;
	index=ViewPreferences.ViewType.INGENIAS;
	
	index=ViewPreferences.ViewType.UML;
	RenderComponentManager.loadRenderFile("WFTest",
	index,"/rendererxml/WFTestUMLPanel.xml");
	
	index=ViewPreferences.ViewType.INGENIAS;
	RenderComponentManager.loadRenderFile("WFTest",
	index,"/rendererxml/WFTestINGENIASPanel.xml");
	
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }

    /**
     * Constructs a renderer that may be used to render vertices.
     */
    public WFTestRenderer() {
    }
    
      public JComponent getConcreteSubComponent(String fieldname, Entity ent, Map map) {
	  Map<String,JComponent> currentMap=(Map)  RenderComponentManager.retrieveIDs("WFTest",ent.getPrefs(map).getView());
	  return currentMap.get(fieldname);
  		}

    public Dimension getSize() {
    return RenderComponentManager.getSize("WFTest",current);
    }

    public boolean supportsAttribute(Object key) {
    	return true;
        }

  public static void setEntity(WFTest ent, Map attributes){
  Map currentMap=(Map)  RenderComponentManager.retrieveIDs("WFTest",ent.getPrefs(attributes).getView());
  current=ent.getPrefs(attributes).getView();
		if (ent != null
				&& currentMap.get("_attributes_") != null
				&& currentMap.get("_attributes_") instanceof ingenias.editor.rendererxml.AttributesPanel) {

			((ingenias.editor.rendererxml.AttributesPanel) currentMap
					.get("_attributes_")).setEntity(ent);

		}

	
        if (currentMap.get("TestStates")!=null && 
            currentMap.get("TestStates") instanceof ingenias.editor.rendererxml.CollectionPanel) {
          try {
            ( (ingenias.editor.rendererxml.CollectionPanel) currentMap.get(
                "TestStates")).setCollection("TestStates",ent.TestStates, ent.TestStates.getType());
          }
          catch (IllegalArgumentException ex) {
            ex.printStackTrace();
          }
          catch (IllegalAccessException ex) {
            ex.printStackTrace();
          }
        }
	
        if (currentMap.get("TestStates")!=null && 
            currentMap.get("TestStates") instanceof ingenias.editor.rendererxml.CollectionPanel) {
          try {
            ( (ingenias.editor.rendererxml.CollectionPanel) currentMap.get(
                "TestStates")).setCollection("TestStates",ent.TestStates, ent.TestStates.getType());
          }
          catch (IllegalArgumentException ex) {
            ex.printStackTrace();
          }
          catch (IllegalAccessException ex) {
            ex.printStackTrace();
          }
        }
	

      
      if (currentMap.get("NeverClaim")!=null){
	   if (ent!=null && ent.getNeverClaim()!=null){
		if (currentMap.get("NeverClaim") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("NeverClaim")).setText(ent.getNeverClaim().toString());
		} else {
		 if (currentMap.get("NeverClaim") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("NeverClaim")).setText(ent.getNeverClaim().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("NeverClaim") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("NeverClaim")).setText("");
	     else {
		if (!(currentMap.get("NeverClaim") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("NeverClaim")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("Repetition")!=null){
	   if (ent!=null && ent.getRepetition()!=null){
		if (currentMap.get("Repetition") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("Repetition")).setText(ent.getRepetition().toString());
		} else {
		 if (currentMap.get("Repetition") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("Repetition")).setText(ent.getRepetition().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("Repetition") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("Repetition")).setText("");
	     else {
		if (!(currentMap.get("Repetition") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("Repetition")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("MaxTimePerCycle")!=null){
	   if (ent!=null && ent.getMaxTimePerCycle()!=null){
		if (currentMap.get("MaxTimePerCycle") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("MaxTimePerCycle")).setText(ent.getMaxTimePerCycle().toString());
		} else {
		 if (currentMap.get("MaxTimePerCycle") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("MaxTimePerCycle")).setText(ent.getMaxTimePerCycle().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("MaxTimePerCycle") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("MaxTimePerCycle")).setText("");
	     else {
		if (!(currentMap.get("MaxTimePerCycle") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("MaxTimePerCycle")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("TestDuration")!=null){
	   if (ent!=null && ent.getTestDuration()!=null){
		if (currentMap.get("TestDuration") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("TestDuration")).setText(ent.getTestDuration().toString());
		} else {
		 if (currentMap.get("TestDuration") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("TestDuration")).setText(ent.getTestDuration().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("TestDuration") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("TestDuration")).setText("");
	     else {
		if (!(currentMap.get("TestDuration") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("TestDuration")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("TestStates")!=null){
	   if (ent!=null && ent.getTestStates()!=null){
		if (currentMap.get("TestStates") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("TestStates")).setText(ent.getTestStates().toString());
		} else {
		 if (currentMap.get("TestStates") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("TestStates")).setText(ent.getTestStates().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("TestStates") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("TestStates")).setText("");
	     else {
		if (!(currentMap.get("TestStates") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("TestStates")).setText("");
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
	return RenderComponentManager.retrievePanel("WFTest",
	((Entity)((DefaultGraphCell)(view.getCell())).getUserObject()).getPrefs(graph.getModel().getAttributes(view.getCell())).getView());
		
	}
	
	public static JPanel setCurrent(ViewPreferences.ViewType c) {
	
		current = ViewPreferences.ViewType.INGENIAS;
	
		return (JPanel) RenderComponentManager.retrievePanel("WFTest",current);
	}
	

}
