


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



public class RuntimeConversationRenderer extends CompositeRenderer implements CellViewRenderer, Serializable {

  
	private static ViewPreferences.ViewType current = ViewPreferences.ViewType.INGENIAS;

  static {

    try {

    	ViewPreferences.ViewType index=ViewPreferences.ViewType.INGENIAS;
	index=ViewPreferences.ViewType.INGENIAS;
	
	index=ViewPreferences.ViewType.UML;
	RenderComponentManager.loadRenderFile("RuntimeConversation",
	index,"/rendererxml/RuntimeConversationUMLPanel.xml");
	
	index=ViewPreferences.ViewType.INGENIAS;
	RenderComponentManager.loadRenderFile("RuntimeConversation",
	index,"/rendererxml/RuntimeConversationINGENIASPanel.xml");
	
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }

    /**
     * Constructs a renderer that may be used to render vertices.
     */
    public RuntimeConversationRenderer() {
    }
    
      public JComponent getConcreteSubComponent(String fieldname, Entity ent, Map map) {
	  Map<String,JComponent> currentMap=(Map)  RenderComponentManager.retrieveIDs("RuntimeConversation",ent.getPrefs(map).getView());
	  return currentMap.get(fieldname);
  		}

    public Dimension getSize() {
    return RenderComponentManager.getSize("RuntimeConversation",current);
    }

    public boolean supportsAttribute(Object key) {
    	return true;
        }

  public static void setEntity(RuntimeConversation ent, Map attributes){
  Map currentMap=(Map)  RenderComponentManager.retrieveIDs("RuntimeConversation",ent.getPrefs(attributes).getView());
  current=ent.getPrefs(attributes).getView();
		if (ent != null
				&& currentMap.get("_attributes_") != null
				&& currentMap.get("_attributes_") instanceof ingenias.editor.rendererxml.AttributesPanel) {

			((ingenias.editor.rendererxml.AttributesPanel) currentMap
					.get("_attributes_")).setEntity(ent);

		}

	
        if (currentMap.get("Stack")!=null && 
            currentMap.get("Stack") instanceof ingenias.editor.rendererxml.CollectionPanel) {
          try {
            ( (ingenias.editor.rendererxml.CollectionPanel) currentMap.get(
                "Stack")).setCollection("Stack",ent.Stack, ent.Stack.getType());
          }
          catch (IllegalArgumentException ex) {
            ex.printStackTrace();
          }
          catch (IllegalAccessException ex) {
            ex.printStackTrace();
          }
        }
	
        if (currentMap.get("ChildConversation")!=null && 
            currentMap.get("ChildConversation") instanceof ingenias.editor.rendererxml.CollectionPanel) {
          try {
            ( (ingenias.editor.rendererxml.CollectionPanel) currentMap.get(
                "ChildConversation")).setCollection("ChildConversation",ent.ChildConversation, ent.ChildConversation.getType());
          }
          catch (IllegalArgumentException ex) {
            ex.printStackTrace();
          }
          catch (IllegalAccessException ex) {
            ex.printStackTrace();
          }
        }
	
        if (currentMap.get("Collaborators")!=null && 
            currentMap.get("Collaborators") instanceof ingenias.editor.rendererxml.CollectionPanel) {
          try {
            ( (ingenias.editor.rendererxml.CollectionPanel) currentMap.get(
                "Collaborators")).setCollection("Collaborators",ent.Collaborators, ent.Collaborators.getType());
          }
          catch (IllegalArgumentException ex) {
            ex.printStackTrace();
          }
          catch (IllegalAccessException ex) {
            ex.printStackTrace();
          }
        }
	
        if (currentMap.get("CurrentContent")!=null && 
            currentMap.get("CurrentContent") instanceof ingenias.editor.rendererxml.CollectionPanel) {
          try {
            ( (ingenias.editor.rendererxml.CollectionPanel) currentMap.get(
                "CurrentContent")).setCollection("CurrentContent",ent.CurrentContent, ent.CurrentContent.getType());
          }
          catch (IllegalArgumentException ex) {
            ex.printStackTrace();
          }
          catch (IllegalAccessException ex) {
            ex.printStackTrace();
          }
        }
	

      
      if (currentMap.get("Description")!=null){
	   if (ent!=null && ent.getDescription()!=null){
		if (currentMap.get("Description") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("Description")).setText(ent.getDescription().toString());
		} else {
		 if (currentMap.get("Description") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("Description")).setText(ent.getDescription().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("Description") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("Description")).setText("");
	     else {
		if (!(currentMap.get("Description") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("Description")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("AbortCode")!=null){
	   if (ent!=null && ent.getAbortCode()!=null){
		if (currentMap.get("AbortCode") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("AbortCode")).setText(ent.getAbortCode().toString());
		} else {
		 if (currentMap.get("AbortCode") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("AbortCode")).setText(ent.getAbortCode().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("AbortCode") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("AbortCode")).setText("");
	     else {
		if (!(currentMap.get("AbortCode") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("AbortCode")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("Interaction")!=null){
	   if (ent!=null && ent.getInteraction()!=null){
		if (currentMap.get("Interaction") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("Interaction")).setText(ent.getInteraction().toString());
		} else {
		 if (currentMap.get("Interaction") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("Interaction")).setText(ent.getInteraction().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("Interaction") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("Interaction")).setText("");
	     else {
		if (!(currentMap.get("Interaction") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("Interaction")).setText("");
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
	   
      if (currentMap.get("ParticipantsSearch")!=null){
	   if (ent!=null && ent.getParticipantsSearch()!=null){
		if (currentMap.get("ParticipantsSearch") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("ParticipantsSearch")).setText(ent.getParticipantsSearch().toString());
		} else {
		 if (currentMap.get("ParticipantsSearch") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("ParticipantsSearch")).setText(ent.getParticipantsSearch().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("ParticipantsSearch") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("ParticipantsSearch")).setText("");
	     else {
		if (!(currentMap.get("ParticipantsSearch") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("ParticipantsSearch")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("Collaborators")!=null){
	   if (ent!=null && ent.getCollaborators()!=null){
		if (currentMap.get("Collaborators") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("Collaborators")).setText(ent.getCollaborators().toString());
		} else {
		 if (currentMap.get("Collaborators") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("Collaborators")).setText(ent.getCollaborators().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("Collaborators") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("Collaborators")).setText("");
	     else {
		if (!(currentMap.get("Collaborators") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("Collaborators")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("Initiator")!=null){
	   if (ent!=null && ent.getInitiator()!=null){
		if (currentMap.get("Initiator") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("Initiator")).setText(ent.getInitiator().toString());
		} else {
		 if (currentMap.get("Initiator") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("Initiator")).setText(ent.getInitiator().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("Initiator") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("Initiator")).setText("");
	     else {
		if (!(currentMap.get("Initiator") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("Initiator")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("CurrentContent")!=null){
	   if (ent!=null && ent.getCurrentContent()!=null){
		if (currentMap.get("CurrentContent") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("CurrentContent")).setText(ent.getCurrentContent().toString());
		} else {
		 if (currentMap.get("CurrentContent") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("CurrentContent")).setText(ent.getCurrentContent().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("CurrentContent") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("CurrentContent")).setText("");
	     else {
		if (!(currentMap.get("CurrentContent") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("CurrentContent")).setText("");
	     }
         }
	  }
	   
      if (currentMap.get("State")!=null){
	   if (ent!=null && ent.getState()!=null){
		if (currentMap.get("State") instanceof javax.swing.JLabel){
		((javax.swing.JLabel)( currentMap).get("State")).setText(ent.getState().toString());
		} else {
		 if (currentMap.get("State") instanceof javax.swing.text.JTextComponent)
		 ((javax.swing.text.JTextComponent)( currentMap).get("State")).setText(ent.getState().toString());
		
	      } 
	   } else  {
	     if (currentMap.get("State") instanceof javax.swing.JLabel)
	     ((javax.swing.JLabel)( currentMap).get("State")).setText("");
	     else {
		if (!(currentMap.get("State") instanceof ingenias.editor.rendererxml.CollectionPanel)) 
		((javax.swing.text.JTextComponent)( currentMap).get("State")).setText("");
	     }
         }
	  }
	   
      
    
  }


	public Component getRendererComponent(JGraph graph,
			CellView view, boolean sel,
			boolean focus, boolean preview)
	{
	return RenderComponentManager.retrievePanel("RuntimeConversation",
	((Entity)((DefaultGraphCell)(view.getCell())).getUserObject()).getPrefs(graph.getModel().getAttributes(view.getCell())).getView());
		
	}
	
	public static JPanel setCurrent(ViewPreferences.ViewType c) {
	
		current = ViewPreferences.ViewType.INGENIAS;
	
		return (JPanel) RenderComponentManager.retrievePanel("RuntimeConversation",current);
	}
	

}
