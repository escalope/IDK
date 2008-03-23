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
import ingenias.testing.DebugUtils;
import ingenias.exception.InvalidEntity;

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
					ent.getPrefs().setView(ViewPreferences.ViewType.UML);
					getGraph().repaint();
				}
			});
		}

		if (RuntimeEvent.class.isAssignableFrom(ent.getClass())) {
			final ViewPreferences.ViewType current1 = ViewPreferences.ViewType.INGENIAS;
			possibleViews.add(new AbstractAction("INGENIAS") {
				public void actionPerformed(ActionEvent e) {
					ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
					getGraph().repaint();
				}
			});
		}

		if (RuntimeFact.class.isAssignableFrom(ent.getClass())) {
			final ViewPreferences.ViewType current1 = ViewPreferences.ViewType.UML;
			possibleViews.add(new AbstractAction("UML") {
				public void actionPerformed(ActionEvent e) {
					ent.getPrefs().setView(ViewPreferences.ViewType.UML);
					getGraph().repaint();
				}
			});
		}

		if (RuntimeFact.class.isAssignableFrom(ent.getClass())) {
			final ViewPreferences.ViewType current1 = ViewPreferences.ViewType.INGENIAS;
			possibleViews.add(new AbstractAction("INGENIAS") {
				public void actionPerformed(ActionEvent e) {
					ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
					getGraph().repaint();
				}
			});
		}
		
		if (RuntimeConversation.class.isAssignableFrom(ent.getClass())) {
			final ViewPreferences.ViewType current1 = ViewPreferences.ViewType.UML;
			possibleViews.add(new AbstractAction("UML") {
				public void actionPerformed(ActionEvent e) {
					ent.getPrefs().setView(ViewPreferences.ViewType.UML);
					getGraph().repaint();
				}
			});
		}

		if (RuntimeConversation.class.isAssignableFrom(ent.getClass())) {
			final ViewPreferences.ViewType current1 = ViewPreferences.ViewType.INGENIAS;
			possibleViews.add(new AbstractAction("INGENIAS") {
				public void actionPerformed(ActionEvent e) {
					ent.getPrefs().setView(ViewPreferences.ViewType.INGENIAS);
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
