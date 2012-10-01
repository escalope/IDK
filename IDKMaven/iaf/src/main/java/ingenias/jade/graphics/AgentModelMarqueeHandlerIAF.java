/*
    Copyright (C) 2007 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 

    INGENIAS Agent Framework is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS Agent Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS Agent Framework; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/
package ingenias.jade.graphics;

import ingenias.editor.entities.RuntimeConversation;
import ingenias.editor.entities.RuntimeEvent;
import ingenias.editor.entities.RuntimeFact;
import ingenias.editor.entities.ViewPreferences;
import ingenias.testing.DebugUtils;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;

// MarqueeHandler that Connects Vertices and Displays PopupMenus

public class AgentModelMarqueeHandlerIAF extends
		BasicAgentModelMarqueeHandlerIAF implements java.io.Serializable {

	public AgentModelMarqueeHandlerIAF(JGraph graph) {
		super(graph);
	}
	
	protected Vector<AbstractAction> createDebugActions(
			final DefaultGraphCell cell) {
		
		Vector<AbstractAction> possibleViews = new Vector<AbstractAction>();
		final ingenias.editor.entities.Entity ent = ((ingenias.editor.entities.Entity) cell
				.getUserObject());
		if (RuntimeConversation.class.isAssignableFrom(ent.getClass())){			
			possibleViews.add(new AbstractAction("Stack") {
				public void actionPerformed(ActionEvent e) {					
					String trace=DebugUtils.getStackTrace((RuntimeConversation)ent);
					JFrame window=new JFrame();
					JTextArea jta=new JTextArea();					
					window.getContentPane().add(jta);
					jta.setText(trace);
					window.pack();
					window.setTitle("Trace for "+ent.getId()+":"+ent.getType());
					window.setVisible(true);					
				}
			});
                            possibleViews.add(new AbstractAction("List contained entities") {
				public void actionPerformed(ActionEvent e) {
                                    RuntimeConversation runEnt= (RuntimeConversation)ent;
					
					JFrame window=new JFrame();
					JTextArea jta=new JTextArea();
					window.getContentPane().add(jta);
                                        String content="";
                                        Enumeration<ingenias.editor.entities.MentalEntity> enumeration=runEnt.getCurrentContentElements();
                                        while (enumeration.hasMoreElements()){
                                            ingenias.editor.entities.MentalEntity current=enumeration.nextElement();
                                            content=content+"-"+current.getId()+":"+current.getType()+"\n";
                                        }
					jta.setText(content);
					window.pack();
					window.setTitle("List of entities");
					window.setVisible(true);
				}
			});
		}
		
		if (RuntimeFact.class.isAssignableFrom(ent.getClass())){			
			possibleViews.add(new AbstractAction("Stack") {
				public void actionPerformed(ActionEvent e) {					
					String trace=DebugUtils.getStackTrace((RuntimeFact)ent);
					JFrame window=new JFrame();
					JTextArea jta=new JTextArea();					
					window.getContentPane().add(jta);
					jta.setText(trace);
					window.pack();
					window.setTitle("Trace for "+ent.getId()+":"+ent.getType());
					window.setVisible(true);					
				}
			});
		}
		
		if (RuntimeEvent.class.isAssignableFrom(ent.getClass())){			
			possibleViews.add(new AbstractAction("Stack") {
				public void actionPerformed(ActionEvent e) {					
					String trace=DebugUtils.getStackTrace((RuntimeEvent)ent);
					JFrame window=new JFrame();
					JTextArea jta=new JTextArea();					
					window.getContentPane().add(jta);
					jta.setText(trace);
					window.pack();
					window.setTitle("Trace for "+ent.getId()+":"+ent.getType());
					window.setVisible(true);					
				}
			});

		}
		return possibleViews;

	}

	protected Vector<AbstractAction> createChangeViewActions(
			final DefaultGraphCell cell) {
		Vector<AbstractAction> possibleViews = new Vector<AbstractAction>();
		final ingenias.editor.entities.Entity ent = ((ingenias.editor.entities.Entity) cell
				.getUserObject());

		possibleViews.addAll(super.createChangeViewActions(cell));
		
		if (RuntimeEvent.class.isAssignableFrom(ent.getClass())) {
			final ViewPreferences.ViewType current1 = ViewPreferences.ViewType.UML;
			possibleViews.add(new AbstractAction("UML") {
				public void actionPerformed(ActionEvent e) {
					getGraph().getModel().getAttributes(cell).put("view", "UML"); ent.getPrefs(getGraph().getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
					getGraph().repaint();
				}
			});
		}
		

		if (RuntimeEvent.class.isAssignableFrom(ent.getClass())) {
			final ViewPreferences.ViewType current1 = ViewPreferences.ViewType.INGENIAS;
			possibleViews.add(new AbstractAction("INGENIAS") {
				public void actionPerformed(ActionEvent e) {
					getGraph().getModel().getAttributes(cell).put("view", "INGENIAS"); ent.getPrefs(getGraph().getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
					getGraph().repaint();
				}
			});
		}
		
		if (RuntimeFact.class.isAssignableFrom(ent.getClass())) {
			final ViewPreferences.ViewType current1 = ViewPreferences.ViewType.UML;
			possibleViews.add(new AbstractAction("INGENIAS") {
				public void actionPerformed(ActionEvent e) {
					getGraph().getModel().getAttributes(cell).put("view", "INGENIAS"); ent.getPrefs(getGraph().getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
					getGraph().repaint();
				}
			});
		}



		if (RuntimeFact.class.isAssignableFrom(ent.getClass())) {
			final ViewPreferences.ViewType current1 = ViewPreferences.ViewType.UML;
			possibleViews.add(new AbstractAction("UML") {
				public void actionPerformed(ActionEvent e) {
					getGraph().getModel().getAttributes(cell).put("view", "UML"); ent.getPrefs(getGraph().getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
					getGraph().repaint();
				}
			});
		}

		
		if (RuntimeConversation.class.isAssignableFrom(ent.getClass())) {
			final ViewPreferences.ViewType current1 = ViewPreferences.ViewType.UML;
			possibleViews.add(new AbstractAction("UML") {
				public void actionPerformed(ActionEvent e) {
					getGraph().getModel().getAttributes(cell).put("view", "UML"); ent.getPrefs(getGraph().getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.UML);
					getGraph().repaint();
				}
			});
		}

		if (RuntimeConversation.class.isAssignableFrom(ent.getClass())) {
			final ViewPreferences.ViewType current1 = ViewPreferences.ViewType.INGENIAS;
			possibleViews.add(new AbstractAction("INGENIAS") {
				public void actionPerformed(ActionEvent e) {
					getGraph().getModel().getAttributes(cell).put("view", "INGENIAS"); ent.getPrefs(getGraph().getModel().getAttributes(cell)).setView(ViewPreferences.ViewType.INGENIAS);
					getGraph().repaint();
				}
			});
		}
		
		
		
		
		

		return possibleViews;
	}

	protected Vector<AbstractAction> createDiagramSpecificInsertActions(
			final Point pt) {
		Vector<AbstractAction> nobjects = new Vector<AbstractAction>();

		return nobjects;
	}

}
