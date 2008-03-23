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
import java.util.Map;
import java.util.Hashtable;
import org.jgraph.graph.*;
import org.jgraph.*;
import org.jgraph.event.*;
import ingenias.editor.ObservableModel;
import java.util.*;

/**
 *
 * Avoids that an entity gets outta of the screen.
 * It does so by setting X to 1 when X<0 and Y to 1 when Y<0
 *
 */
public class RemoveDependent
    implements org.jgraph.event.GraphModelListener {
  public final int COL_SEPARATOR=5;
  private Object workingObject = null;
  private boolean enabled = true;
  JGraph graph;

  public RemoveDependent(JGraph graph) {

       this.graph = graph;


  }

  private LifelineCell locateLifelineCell(Lifeline ll,
                                          ingenias.editor.Model model) {
    LifelineCell result = null;
    return (LifelineCell)AUMLDiagramChangesManager.getCellFromUserObject(ll);
  }

  private AUMLPortCell locatePortCell(AUMLPort port,
                                      ingenias.editor.Model model) {
    AUMLPortCell result = null;
    return (AUMLPortCell)AUMLDiagramChangesManager.getCellFromUserObject(port);

  }

  private AUMLAlternativeRowCell locateAlternativeRowCell(AUMLAlternativeRow
      col,
      ingenias.editor.Model model) {
    AUMLAlternativeRowCell result = null;
    return (AUMLAlternativeRowCell)AUMLDiagramChangesManager.getCellFromUserObject(col);
  }

  private ColumnCell locateColumnCell(Column col,
                                      ingenias.editor.Model model) {
    ColumnCell result = null;
    return (ColumnCell)AUMLDiagramChangesManager.getCellFromUserObject(col);
  }



  private void removeContainer(AUMLContainer cont, HashSet moreToRemove) {
    Enumeration enumeration = cont.getChildrenElements();
    while (enumeration.hasMoreElements()) {
      Object next = enumeration.nextElement();
      if (AUMLContainer.class.isAssignableFrom(next.getClass()) &&
          !moreToRemove.contains(next)) {
        moreToRemove.add(next);
        this.removeContainer( (AUMLContainer) next, moreToRemove);
      }
      else {
        moreToRemove.add(next);
        ;

      }
    }
  }

  private void removeDependent(org.jgraph.event.GraphModelEvent gme) {
    Object[] removed = gme.getChange().getRemoved();
    ingenias.editor.Model model = (ingenias.editor.Model) gme.getSource();
    HashSet moreToRemove = new HashSet();
    for (int k = 0; k < removed.length; k++) {
      Object uo = ( (DefaultGraphCell) removed[k]).getUserObject();
      if (AUMLContainer.class.isAssignableFrom(uo.getClass())) {
        this.removeContainer( (AUMLContainer) uo, moreToRemove);
      }
    }
    for (int k = 0; k < removed.length; k++) {
      Object uo = ( (DefaultGraphCell) removed[k]).getUserObject();
      if (moreToRemove.contains(uo)) {
        moreToRemove.remove(uo);
      }
    }
    Enumeration enumeration = AUMLDiagramChangesManager.getUserObjects();
    while (enumeration.hasMoreElements()) {
      Object uo = enumeration.nextElement();
      if (AUMLContainer.class.isAssignableFrom(uo.getClass())) {
        AUMLContainer cont = (AUMLContainer) uo;
        for (int k = 0; k < removed.length; k++) {
          Entity contained = (Entity) ( (DefaultGraphCell) removed[k]).
              getUserObject();
          cont.removeChildrenElement(contained.getId());
        }
        Iterator it = moreToRemove.iterator();
        while (it.hasNext()) {
          Entity contained = (Entity) it.next();
          cont.removeChildrenElement(contained.getId());
        }
      }
    }
    final Vector finalToBeRemoved = new Vector();
    Iterator it = moreToRemove.iterator();
    while (it.hasNext()) {
      DefaultGraphCell dgc = AUMLDiagramChangesManager.getCellFromUserObject(it.next());
      if (dgc != null) {
        finalToBeRemoved.add(dgc);
      }
    }
    final ingenias.editor.Model model1 = model;
    new Thread() {
      public void run() {
        model1.remove(finalToBeRemoved.toArray());
      }
    }

    .run();

  }


  public void graphChanged(org.jgraph.event.GraphModelEvent gme) {

    ingenias.editor.IDE.setChanged();



      if (enabled && this.workingObject == null &&
          gme.getChange().getRemoved() != null &&
          gme.getChange().getRemoved().length > 0) {
           workingObject = "hello";
        removeDependent(gme);
              workingObject = null;
      }

  }




}