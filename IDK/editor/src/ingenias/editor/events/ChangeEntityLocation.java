/*
 Copyright (C) 2002 Jorge Gomez Sanz
 This file is part of INGENIAS IDE, a support tool for the INGENIAS
 methodology, availabe at http://grasia.fdi.ucm.es/ingenias or
 http://ingenias.sourceforge.net
 INGENIAS IDE is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.
 INGENIAS IDE is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 You should have received a copy of the GNU General Public License
 along with INGENIAS IDE; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package ingenias.editor.events;

import ingenias.editor.cell.*;
import ingenias.editor.entities.*;

import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Map;
import java.util.Hashtable;
import org.jgraph.graph.*;
import org.jgraph.*;
import org.jgraph.event.*;

import ingenias.editor.Model;
import ingenias.editor.ObservableModel;
import java.util.*;

/**
 *
 * Avoids that an entity gets outta of the screen.
 * It does so by setting X to 1 when X<0 and Y to 1 when Y<0
 *
 */
public class ChangeEntityLocation
implements org.jgraph.event.GraphModelListener {
	private Object workingObject = null;
	private boolean enabled = true;
	private JGraph mjg;

	public ChangeEntityLocation(JGraph graph) {
		this.mjg=graph;
	}

	public void graphChanged(org.jgraph.event.GraphModelEvent gme) {
		ingenias.editor.IDE.setChanged();
		if (( gme.getChange().getInserted() == null
				|| gme.getChange().getInserted().length == 0)) {
			Map old = gme.getChange().getPreviousAttributes();
			Map newAt = gme.getChange().getAttributes();
			if (old != null) {
				Iterator keys = old.keySet().iterator();
				while (keys.hasNext()) {
					Object current = keys.next();
					if (current != null && ! 
							(NAryEdge.class.isAssignableFrom(current.getClass()))) {
						Map oluomap = (Map) old.get(current);
						Map newuomap = (Map) newAt.get(current);

						if (newuomap != null && GraphConstants.getBounds(newuomap) != null &&
								oluomap!=null &&	GraphConstants.getBounds(oluomap)!=null) {
							Rectangle rect1 = GraphConstants.getBounds(oluomap).getBounds();
							Rectangle rect2 = GraphConstants.getBounds(newuomap).getBounds();							
							if (rect1 == null || rect2 == null || !rect1.equals(rect2)) {								
								this.processChange(current,mjg);
							}
						}
					}
					if (current != null &&
							! (NAryEdge.class.isAssignableFrom(current.getClass()))) {
						Map oluomap = (Map) old.get(current);
						Map newuomap = (Map) newAt.get(current);

						if (newuomap != null && GraphConstants.getBounds(newuomap) != null &&
								oluomap!=null &&	GraphConstants.getBounds(oluomap)!=null) {
							Rectangle rect1 = GraphConstants.getBounds(oluomap).getBounds();
							Rectangle rect2 = GraphConstants.getBounds(newuomap).getBounds();

							if (rect1 == null || rect2 == null || !rect1.equals(rect2)) {
								this.processChange(current,mjg);
							}
						}
					}

				}
			}
		}
	}

	

	

	

	public void processChange(Object cell, JGraph mjg) {

		DefaultGraphCell dgc = (DefaultGraphCell) cell;
		ingenias.editor.Model m=(Model) mjg.getModel();

		Iterator it = m.getEdges(m, new Object[] {cell}).iterator();

		Hashtable changes = new Hashtable();
		while (it.hasNext()) {
			DefaultEdge de = (DefaultEdge) it.next();
			DefaultGraphCell nary = LocationChange.getNAryEdgeExtreme(de, m);
			if (enabled || ((Entity)(nary.getUserObject())).getPrefs().getView().equals(ViewPreferences.ViewType.NOICON)){
				LocationChange.centerNAryEdge(mjg, m, changes, nary);
			}
		}
		if (changes.size()>0)
			m.edit(changes, null, null, null);
	}

	

	

	public void disableAutomaticAllocation() {
		enabled = false;
	}

	public void enableAutomaticAllocation() {
		enabled = true;
	}
}