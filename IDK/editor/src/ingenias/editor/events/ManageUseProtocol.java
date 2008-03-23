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
public class ManageUseProtocol
    implements org.jgraph.event.GraphModelListener {
  private Object workingObject = null;
  private boolean enabled = true;

  JGraph graph;


  public ManageUseProtocol(JGraph graph) {
    this.graph = graph;
  }

  private LifelineCell locateLifelineCell(ColumnCell cc,
                                          ingenias.editor.Model model) {
    LifelineCell result = null;
    for (int k = 0; k < model.getRootCount() && result == null; k++) {
      if (model.getRootAt(k)instanceof LifelineCell) {
        Lifeline ll = (Lifeline) ( (LifelineCell) (model.getRootAt(k))).
            getUserObject();
        Enumeration enumeration = ll.getChildrenElements();
        boolean found = false;
        while (enumeration.hasMoreElements() && !found) {
          Column col = (Column) enumeration.nextElement();
          found = col.equals(cc.getUserObject());
          if (found) {
            result = (LifelineCell) (model.getRootAt(k));
          }
        }
      }
    }
    return result;
  }

  private LifelineCell locateLifelineCell(Lifeline ll,
                                          ingenias.editor.Model model) {
    LifelineCell result = null;
    for (int k = 0; k < model.getRootCount() && result == null; k++) {
      if (model.getRootAt(k)instanceof LifelineCell &&
          ( ( (LifelineCell) model.getRootAt(k)).getUserObject().equals(ll))) {
        result = (LifelineCell) (model.getRootAt(k));
      }
    }
    return result;
  }

  private ColumnCell locateColumnCell(AUMLPort port,
                                      ingenias.editor.Model model) {
    ColumnCell result = null;
    for (int k = 0; k < model.getRootCount() && result == null; k++) {
      if (model.getRootAt(k)instanceof ColumnCell) {
        Column col = (Column) ( (ColumnCell) model.getRootAt(k)).getUserObject();
        Enumeration enumeration = col.getChildrenElements();
        boolean found = false;
        while (enumeration.hasMoreElements() && !found) {
          AUMLPort nextcol = (AUMLPort) enumeration.nextElement();
          found = nextcol.equals(port);
        }
        if (found) {
          result = (ColumnCell) (model.getRootAt(k));
        }
      }
    }
    return result;
  }

  private AUMLPortCell locatePortCell(AUMLPort port,
                                      ingenias.editor.Model model) {
    AUMLPortCell result = null;
    for (int k = 0; k < model.getRootCount() && result == null; k++) {
      if (model.getRootAt(k)instanceof AUMLPortCell &&
          ( ( (AUMLPortCell) model.getRootAt(k)).getUserObject().equals(port))) {
        result = (AUMLPortCell) (model.getRootAt(k));
      }
    }
    return result;
  }

  private ColumnCell locateColumnCell(Column col,
                                      ingenias.editor.Model model) {
    ColumnCell result = null;
    for (int k = 0; k < model.getRootCount() && result == null; k++) {
      if (model.getRootAt(k)instanceof ColumnCell &&
          ( ( (ColumnCell) model.getRootAt(k)).getUserObject().equals(col))) {
        result = (ColumnCell) (model.getRootAt(k));
      }
    }
    return result;
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


  private AUMLPortCell createCell(Column col, ingenias.editor.Model model) {
    AUMLPort port = ingenias.editor.ObjectManager.getInstance().
        createAUMLPort(ingenias.editor.Editor.getNewId("port"));
    AUMLPortCell aumlport = new AUMLPortCell(port);
    port.setParent(col);
    Map pmap = new Hashtable();
    col.addChildren(port);

    Hashtable ht = new Hashtable();
    ht.put(aumlport, pmap);
    model.insert(new Object[] {aumlport}
                 , ht, null, null, null);
    return aumlport;
  }

  private SubProtocolCell createSubProtocol(ingenias.editor.Model model) {
    SubProtocol prot =
        ingenias.editor.ObjectManager.getInstance().createSubProtocol(
        ingenias.editor.Editor.getNewId("box"));

    SubProtocolCell protcell =
        new SubProtocolCell(prot);
    Map pmap = new Hashtable();
    // col.addPorts(port);
    Hashtable ht = new Hashtable();
    ht.put(protcell, pmap);
    model.insert(new Object[] {protcell}
                 , ht, null, null, null);
    return protcell;
  }

  private ColumnCell createColumnCell(ingenias.editor.Model model) {
    Column col =
        ingenias.editor.ObjectManager.getInstance().createColumn(
        ingenias.editor.Editor.getNewId("col"));

    AUMLPort port=ingenias.editor.ObjectManager.getInstance().createAUMLPort(
        ingenias.editor.Editor.getNewId("port"));

    ingenias.editor.cell.ColumnCell colcell =
        new ingenias.editor.cell.ColumnCell(col);
    colcell.removeAllChildren();

    ingenias.editor.cell.AUMLPortCell portcell =
        new ingenias.editor.cell.AUMLPortCell(port);

    Map cmap = new Hashtable();
    Map pmap = new Hashtable();

    col.addChildren(port);
    port.setParent(col);

    Hashtable ht = new Hashtable();
    ht.put(colcell, cmap);
    ht.put(portcell, pmap);

    model.insert(new Object[] {colcell, portcell}
                 , ht, null, null, null);
    return colcell;
  }

  private AUMLAlternativeRowCell createAUMLRowCell(ingenias.editor.Model model) {
    AUMLAlternativeRow box =
        ingenias.editor.ObjectManager.getInstance().createAUMLAlternativeRow(
       ingenias.editor.Editor.getNewId( "row"));

    ingenias.editor.cell.AUMLAlternativeRowCell aumlbox =
        new ingenias.editor.cell.AUMLAlternativeRowCell(box);
    Map pmap = new Hashtable();
// col.addPorts(port);
    Hashtable ht = new Hashtable();
    ht.put(aumlbox, pmap);
    model.insert(new Object[] {aumlbox}
                 , ht, null, null, null);
    return aumlbox;
  }


  private Rectangle computeSelectionSize(LifelineCell ll1,
                                         LifelineCell ll2,
                                         ingenias.editor.Model model){

    Rectangle ll1r=GraphConstants.getBounds(ll1.getAttributes()).getBounds();
    Rectangle ll2r=GraphConstants.getBounds(ll2.getAttributes()).getBounds();
    int x=Math.min(ll1r.x,ll2r.x);
    int width=Math.max(ll1r.x,ll2r.x)-x;
    int y=this.getLastColY((Lifeline)ll1.getUserObject(),model);
    return new Rectangle(x-80,y,width+100+80, 80);
  }

  private int getLastColY(Lifeline ll, ingenias.editor.Model model) {
    Enumeration enumeration = ll.getChildrenElements();
    int lastY1 = 0;
    int lastX1 = 0;
    int lastY2 = 0;
    int lastX2 = 0;

    while (enumeration.hasMoreElements()) {
      Column col = (Column) enumeration.nextElement();
      ColumnCell colcell = this.locateColumnCell(col, model);
      if (colcell != null) {
        Map m = colcell.getAttributes();
        if (m != null && GraphConstants.getBounds(m) != null) {
          Rectangle rect = GraphConstants.getBounds(m).getBounds();
          if (rect.y + rect.height > lastY2) {
            lastY2=lastY1;
            lastX2=lastX1;
            lastY1 = rect.y + rect.height;
            lastX1 = rect.x;
          }
        }
      }
    }
    return lastY2;
  }



  public void processEdge(ingenias.editor.cell.AUMLUseProtocolEdge edge,
                          ingenias.editor.Model model) {

    Object[] edges = model.getEdges(model, new Object[] {edge.getChildAt(0)}).
        toArray();
    if (edges!=null && edges.length>0){

      DefaultGraphCell dgc1 = this.locateColumnCell( (AUMLPort)this.
          getCellExtreme(edges[0],
                         model).getUserObject(), model);

      LifelineCell llc1 = this.locateLifelineCell( (ColumnCell) dgc1, model);
      Lifeline ll1 = (Lifeline) llc1.getUserObject();

      edges = model.getEdges(model, new Object[] {edge.getChildAt(1)}).
          toArray();
      DefaultGraphCell dgc2 = this.locateColumnCell( (AUMLPort)
          this.getCellExtreme(edges[0],
                              model).getUserObject(), model);
      LifelineCell llc2 = this.locateLifelineCell( (ColumnCell) dgc2, model);
      Lifeline ll2 = (Lifeline) llc2.getUserObject();

      ingenias.editor.cell.SubProtocolCell protcell =
          (SubProtocolCell)this.createSubProtocol(model);
      SubProtocol prot =
          (SubProtocol) protcell.getUserObject();

      ColumnCell ncc1 = this.createColumnCell(model);
      Column col1 = (Column) ncc1.getUserObject();
      ColumnCell ncc2 = this.createColumnCell(model);
      Column col2 = (Column) ncc2.getUserObject();

      ColumnCell ncc11 = this.createColumnCell(model);
      Column col11 = (Column) ncc11.getUserObject();
      ColumnCell ncc21 = this.createColumnCell(model);
      Column col21 = (Column) ncc21.getUserObject();


/*      AUMLPort port1 = (AUMLPort) col1.getChildrenElements().nextElement();
      AUMLPortCell portc1 = (AUMLPortCell) AUMLDiagramChangesManager.
          getCellFromUserObject(port1);


      AUMLPort port2 = (AUMLPort) col2.getChildrenElements().nextElement();
      AUMLPortCell portc2 = (AUMLPortCell) AUMLDiagramChangesManager.
          getCellFromUserObject(port2);
*/


      prot.addChildren(col1);
      prot.addChildren(col2);

      prot.setParent(ll1.getParent());
      ( (AUMLContainer) ll1.getParent()).addChildren(prot);

      ll1.addChildren(col1);
      ll1.addChildren(col11);
      ll2.addChildren(col2);
      ll2.addChildren(col21);

      Rectangle selectionrect = this.computeSelectionSize(llc1, llc2, model);
      Rectangle llcr1 = GraphConstants.getBounds(llc1.getAttributes()).
          getBounds();
      Rectangle llcr2 = GraphConstants.getBounds(llc2.getAttributes()).
          getBounds();
      int y = Math.max(this.getLastColY(ll1, model),this.getLastColY(ll2, model));

//      Map m1 = ncc1.getAttributes();
/*      GraphConstants.setBounds(m1,
                               new Rectangle(llcr1.x + (llcr1.width / 2) - 5,
                                             y + 10, 30, 50));*/

//      Map m2 = ncc2.getAttributes();
/*      GraphConstants.setBounds(m2,
                               new Rectangle( (int) (llcr2.x + (llcr2.width / 2) -
          5), y + 10, 30, 50));*/

      Map m3 = protcell.getAttributes();
      GraphConstants.setBounds(m3, selectionrect);

//      Map m5 = portc1.getAttributes();
/*      GraphConstants.setBounds(m5,
                               new Rectangle(llcr1.x + (llcr1.width / 2) - 5,
                                             y + 10, 30, 50));*/

//      Map m6 = portc2.getAttributes();
/*      GraphConstants.setBounds(m6,
                               new Rectangle( (int) (llcr2.x + (llcr2.width / 2) -
          5), y + 10, 30, 50));*/

      Hashtable ht = new Hashtable();
//      ht.put(ncc1, m1);
//      ht.put(ncc2, m2);
//      ht.put(portc1, m5);
//      ht.put(portc2, m6);
      ht.put(prot, m3);

      model.edit(ht, null, null, null);
      model.remove(new Object[] {edge});
      model.toFront(new Object[]{prot});
    }

  }

  public void graphChanged(org.jgraph.event.GraphModelEvent gme) {

    ingenias.editor.IDE.setChanged();
    if (enabled && this.workingObject == null &&
        (gme.getChange().getInserted() == null ||
         gme.getChange().getInserted().length == 0)) {
      workingObject = "hello";
      boolean foundEdge = false;
      ingenias.editor.cell.AUMLUseProtocolEdge asel = null;
      ingenias.editor.Model model = (ingenias.editor.Model) gme.getSource();
      for (int k = 0; k < model.getRootCount() && !foundEdge; k++) {
        foundEdge = model.getRootAt(k) instanceof ingenias.editor.cell.AUMLUseProtocolEdge;
        if (foundEdge) {
          asel = (ingenias.editor.cell.AUMLUseProtocolEdge) model.getRootAt(k);
        }
      }
      if (foundEdge) {
        processEdge(asel, (ingenias.editor.Model) gme.getSource());
      }
      workingObject = null;
    }
  }


  private DefaultGraphCell getCellExtreme(Object edge, ingenias.editor.Model m) {
    DefaultEdge de = (DefaultEdge) edge;
    DefaultGraphCell target = (DefaultGraphCell) ( (DefaultPort) m.getTarget(de)).
        getParent();
    DefaultGraphCell source = (DefaultGraphCell) ( (DefaultPort) m.getSource(de)).
        getParent();
    DefaultGraphCell nary = null;
    if (NAryEdge.class.isAssignableFrom(target.getClass())) {
      nary = source;
    }
    if (NAryEdge.class.isAssignableFrom(source.getClass())) {
      nary = target;
    }
    return nary;
  }


}