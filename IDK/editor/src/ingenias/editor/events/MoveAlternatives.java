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
public class MoveAlternatives
    implements org.jgraph.event.GraphModelListener {
  public final int COL_SEPARATOR = 10;
  private Object workingObject = null;
  private boolean enabled = true;
  JGraph graph;

  public MoveAlternatives(JGraph graph) {

    this.graph = graph;

  }

  private LifelineCell locateLifelineCell(Lifeline ll,
                                          ingenias.editor.Model model) {
    LifelineCell result = null;
    return (LifelineCell) AUMLDiagramChangesManager.getCellFromUserObject(ll);
  }

  private AUMLPortCell locatePortCell(AUMLPort port,
                                      ingenias.editor.Model model) {
    AUMLPortCell result = null;
    return (AUMLPortCell) AUMLDiagramChangesManager.getCellFromUserObject(port);

  }

  private AUMLAlternativeRowCell locateAlternativeRowCell(AUMLAlternativeRow
      col,
      ingenias.editor.Model model) {
    AUMLAlternativeRowCell result = null;
    return (AUMLAlternativeRowCell) AUMLDiagramChangesManager.
        getCellFromUserObject(col);
  }

  private ColumnCell locateColumnCell(Column col,
                                      ingenias.editor.Model model) {
    ColumnCell result = null;
    return (ColumnCell) AUMLDiagramChangesManager.getCellFromUserObject(col);
  }

  private ProtocolCell locateProtocolCell(Lifeline ll,
                                          ingenias.editor.Model model) {
    ProtocolCell result = null;

    for (int k = 0; k < model.getRootCount() && result == null; k++) {
      if (model.getRootAt(k)instanceof ProtocolCell) {
        Protocol prot = (Protocol) ( (ProtocolCell) model.getRootAt(k)).
            getUserObject();
        Enumeration enumeration = prot.getChildrenElements();
        boolean found = false;
        while (enumeration.hasMoreElements() && !found) {
          found = enumeration.nextElement().equals(ll);
        }
        if (found) {
          result = (ProtocolCell) model.getRootAt(k);
        }
      }
    }
    return result;
  }

  private Rectangle computeAlternativeRowSize(AUMLAlternativeRow ar,
                                              ingenias.editor.Model model) {


    Rectangle rect = new Rectangle();
    rect.x = Integer.MAX_VALUE;
    rect.y = Integer.MAX_VALUE;
    rect.width = Integer.MIN_VALUE;
    rect.height = Integer.MIN_VALUE;
    for (int k=0;k<2;k++){
      Enumeration enumeration = ar.getChildrenElements();
      while (enumeration.hasMoreElements()) {
        Column col = (Column) enumeration.nextElement();
        ColumnCell cc = this.locateColumnCell(col, model);
        Rectangle ccbounds =
            GraphConstants.getBounds(cc.getAttributes()).getBounds();
        rect.x = Math.min(ccbounds.x, rect.x);
        rect.y = Math.min(ccbounds.y, rect.y);
        rect.height = Math.max(ccbounds.height, rect.height);
        rect.width = Math.max(ccbounds.x + ccbounds.width,
                              rect.x + rect.width) -
            rect.x;

      }
    }
    return rect;
  }

  private void moveAllAlternatives(ingenias.editor.Model model) {

    for (int k = 0; k < model.getRootCount(); k++) {
      Object current = model.getRootAt(k);
      if (current instanceof AUMLAlternativeBoxCell) {
        this.moveAlternative( (AUMLAlternativeBoxCell) current, model);
      }

    }
  }

  private void moveAlternative(AUMLAlternativeBoxCell lc,
                               ingenias.editor.Model model) {
    AUMLAlternativeBox ab = (AUMLAlternativeBox) lc.getUserObject();
    Enumeration enumeration = ab.getChildrenElements();
    Map elems = new Hashtable();
    Rectangle bounds = new Rectangle();
    bounds.x = Integer.MAX_VALUE;
    bounds.y = Integer.MAX_VALUE;
    bounds.width = Integer.MIN_VALUE;
    bounds.height = 0;

    while (enumeration.hasMoreElements()) {
      AUMLAlternativeRow row = (AUMLAlternativeRow) enumeration.nextElement();
      AUMLAlternativeRowCell rowcell = this.locateAlternativeRowCell(row, model);
      Map rowmap = rowcell.getAttributes();
      Rectangle npos = this.computeAlternativeRowSize(row, model);
      GraphConstants.setBounds(rowmap, npos);
      elems.put(rowcell, rowmap);
      bounds.x = Math.min(bounds.x, npos.x - 85);
      bounds.y = Math.min(bounds.y, npos.y - 5);
      bounds.width = Math.max(bounds.width, npos.width + 90);
      bounds.height = bounds.height + npos.height;
    }

    bounds.height = bounds.height + 10;
    Map abmap = lc.getAttributes();
    GraphConstants.setBounds(abmap, bounds);
    elems.put(lc, abmap);
    model.edit(elems, null, null, null);
  }

  public Vector getLifelines(ingenias.editor.Model model) {
    Vector result = new Vector();
    for (int k = 0; k < model.getRootCount(); k++) {
      if (model.getRootAt(k)instanceof LifelineCell) {
        result.add( ( (LifelineCell) model.getRootAt(k)).getUserObject());
      }
    }
    return result;
  }

  private Rectangle getMaxContainerSize(AUMLContainer cont) {
    Enumeration enumeration = cont.getChildrenElements();
    DefaultGraphCell dgc1 = AUMLDiagramChangesManager.getCellFromUserObject(
        cont);
    Rectangle finalSize = new Rectangle(0, 0, 0, 0);
    if (dgc1.getAttributes() != null &&
        GraphConstants.getBounds(dgc1.getAttributes()) != null) {
      finalSize = GraphConstants.getBounds(dgc1.getAttributes()).
          getBounds();
      /*     = new Rectangle(Integer.MAX_VALUE, Integer.MAX_VALUE,
           Integer.MIN_VALUE, Integer.MIN_VALUE);
       */while (enumeration.hasMoreElements()) {
        AUMLComponent ac = (AUMLComponent) enumeration.nextElement();
        DefaultGraphCell dgc = (DefaultGraphCell) AUMLDiagramChangesManager.
            getCellFromUserObject(ac);
        if (dgc != null && dgc.getAttributes() != null &&
            GraphConstants.getBounds(dgc.getAttributes()) != null) {
          Rectangle ccbounds = null;
          if (AUMLContainer.class.isAssignableFrom(ac.getClass())) {
            ccbounds = this.getMaxContainerSize( (AUMLContainer) ac);
          }
          else {
            ccbounds = GraphConstants.getBounds(dgc.
                                                getAttributes()).getBounds();
          }

          finalSize.x = Math.min(ccbounds.x, finalSize.x);
          finalSize.y = Math.min(ccbounds.y, finalSize.y);
          finalSize.height = Math.max(ccbounds.y + ccbounds.height,
                                      finalSize.y + finalSize.height) -
              finalSize.y;
          finalSize.width = Math.max(ccbounds.x + ccbounds.width,
                                     finalSize.x + finalSize.width) -
              finalSize.x;
        }
      }

      /*    finalSize.height = finalSize.height + 20;
          finalSize.width = finalSize.width + 20;
          finalSize.y = finalSize.y - 40;
          finalSize.x = finalSize.x - 20;*/

    }
    return finalSize;
  }

  private Object createCell(Column col, ingenias.editor.Model model) {
    AUMLPort port = ingenias.editor.ObjectManager.getInstance().
        createAUMLPort(ingenias.editor.Editor.getNewId("port"));
    AUMLPortCell aumlport = new AUMLPortCell(port);
    Map pmap = new Hashtable();
    col.addChildren(port);
    port.setParent(col);
    Hashtable ht = new Hashtable();
    ht.put(aumlport, pmap);
    model.insert(new Object[] {aumlport}
                 , ht, null, null, null);
    return aumlport;
  }

  private void updateProtocol(Protocol prot, ingenias.editor.Model model) {
    DefaultGraphCell dgc = AUMLDiagramChangesManager.getCellFromUserObject(prot);
    Rectangle rect = this.getMaxContainerSize(prot);
    Map map = dgc.getAttributes();
    Hashtable ht = new Hashtable();
    ht.put(dgc, map);
    model.edit(ht, null, null, null);
  }

  public void graphChanged(org.jgraph.event.GraphModelEvent gme) {

    ingenias.editor.IDE.setChanged();

    if (enabled && this.workingObject == null) {
      workingObject = "hello";
      this.moveAllAlternatives( (ingenias.editor.Model) gme.getSource());
      workingObject = null;
    }

  }

}