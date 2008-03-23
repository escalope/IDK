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
public class ResizeColumns
    implements org.jgraph.event.GraphModelListener {

  public final int COL_SEPARATOR = 10;
  public final int PORT_TO_PORT_DISTANCE=35;
  private Object workingObject = null;
  private boolean enabled = true;
  JGraph graph;

  public ResizeColumns(JGraph graph) {
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

  private Vector<Column> getMainLines(Vector<Lifeline> ll) {
    Vector<Column> result = new Vector<Column>();

    for (int k = 0; k < ll.size(); k++) {
      AUMLContainer current = (AUMLContainer) ll.elementAt(k);
      Enumeration en=current.getChildrenElements();
      while (en.hasMoreElements()) {
        result.add((Column)en.nextElement());
      }
    }

    return result;
  }

  private Vector getMainLinesFromRow(AUMLContainer cont) {
    Vector result = new Vector();
    Enumeration enumeration = cont.getChildrenElements();
    while (enumeration.hasMoreElements()) {
      Object col = enumeration.nextElement();
      if (col instanceof Column) {
        result.add(col);
      }
    }
    return result;
  }

  private void insertIntoMainLines(Vector mainll, int pos,
                                   Hashtable changes) {
    for (int k = 0; k < mainll.size(); k++) {
      Column col = (Column) mainll.elementAt(k);
      this.createPort(pos, col, changes);
    }
  }

  private Vector<Lifeline> getLifelines(ingenias.editor.Model model) {
	  Vector<Lifeline> result = new Vector<Lifeline>();
    for (int k = 0; k < model.getRootCount(); k++) {
      if (model.getRootAt(k)instanceof LifelineCell) {
        result.add( (Lifeline)( (LifelineCell) model.getRootAt(k)).getUserObject());
      }
    }
    return result;
  }

  private Vector<AUMLAlternativeRow> getAlternativeRows(ingenias.editor.Model model) {
    Vector<AUMLAlternativeRow> result = new Vector<AUMLAlternativeRow>();
    for (int k = 0; k < model.getRootCount(); k++) {
      if (model.getRootAt(k)instanceof ingenias.editor.cell.
          AUMLAlternativeRowCell) {
        result.add( (AUMLAlternativeRow)( (AUMLAlternativeRowCell) model.getRootAt(k)).
                   getUserObject());
      }
    }
    return result;
  }

  private void insertNewPorts(Vector mainlls, ingenias.editor.Model model) {
    Hashtable changes=new Hashtable();
    mainlls = (Vector) mainlls.clone();
    Enumeration colsenumeration = mainlls.elements();
    Vector insertionPoints = new Vector();
    Vector processedColumns = new Vector();
    while (colsenumeration.hasMoreElements() && processedColumns.size() != 1) {
      Object nextElement = colsenumeration.nextElement();
      if (nextElement instanceof Column) {
        Column col = (Column) nextElement;
        if (insertionPoints.size() == 0) {
          ColumnCell colcell = this.locateColumnCell(col, model);
          if (colcell!=null){
            Map colmap = colcell.getAttributes();
            Enumeration portsenumeration = col.getChildrenElements();
            Vector oldports = new Vector();
            while (portsenumeration.hasMoreElements()) {
              Object nextElement1 = portsenumeration.nextElement();
              if (nextElement1 instanceof AUMLPort) {
                AUMLPort port = (AUMLPort) nextElement1;
                AUMLPortCell portcell = this.locatePortCell(port, null);
                oldports.add(portcell);
              }
            }

            Vector busyports = getBusyPorts(oldports, model);

            if (busyports.size() > 0) {
              for (int k = 0; k < oldports.size(); k++) {
                if (busyports.contains(oldports.elementAt(k))) {
                  if (k == 0 || k == oldports.size() - 1) {
                    insertionPoints.add(new Integer(k));
                    insertionPoints.add(new Integer(k + 2));
                    createPort(k, col, changes);
                    createPort(k + 2, col, changes);
                    System.err.println("INSERTING PORTS 1");
                  }
                  else {
                    if (busyports.contains(oldports.elementAt(k - 1))) {
                      insertionPoints.add(new Integer(k));
                      createPort(k, col, changes);
                      System.err.println("INSERTING PORTS 2");
                    }
                  }
                }
              }
              processedColumns.add(col);
            }
          }
        }
      }
    }

    if (processedColumns.size() == 1) {
      mainlls.removeAll(processedColumns);
      for (int k = 0; k < insertionPoints.size(); k++) {
        Integer insertionp = (Integer) insertionPoints.elementAt(k);
        this.insertIntoMainLines(mainlls, insertionp.intValue(), changes);
      }
    }
    if (changes.size()!=0){
        model.insert(changes.keySet().toArray(),changes,null,null,null);
    }
  }

  private void matchRowsRelatedColumns(AUMLContainer row,
                                       ingenias.editor.Model model) {
    Enumeration colenumeration = row.getChildrenElements();
    int maxy = 0;
    while (colenumeration.hasMoreElements()) {
      ColumnCell col = (ColumnCell) AUMLDiagramChangesManager.
          getCellFromUserObject(colenumeration.nextElement());
      Map m = col.getAttributes();
      if (m != null) {
        Rectangle rect = GraphConstants.getBounds(m).getBounds();
        if (rect != null && rect.y > maxy) {
          maxy = rect.y;
        }
      }
    }
    colenumeration = row.getChildrenElements();
    Hashtable ht = new Hashtable();
    while (colenumeration.hasMoreElements()) {
      ColumnCell col = (ColumnCell) AUMLDiagramChangesManager.
          getCellFromUserObject(colenumeration.nextElement());
      Map m = col.getAttributes();
      if (m != null) {
        Rectangle rect = GraphConstants.getBounds(m).getBounds();
        if (rect.y != maxy) {
          rect.y = maxy;
          GraphConstants.setBounds(m, rect);
          ht.put(col, m);
          this.orderCellsAlongColumn( (Column) col.getUserObject(),
                                     model, rect.getLocation(), ht);
        }
      }
    }
    if (ht.size() > 0) {
      model.edit(ht, null, null, null);

    }
  }

  private void computeNewPositions(Vector lls, ingenias.editor.Model model) {
    Enumeration llsenumeration = lls.elements();
    Vector insertPoints = new Vector();
    while (llsenumeration.hasMoreElements()) {
      Lifeline ll = (Lifeline) llsenumeration.nextElement();
      LifelineCell llc = this.locateLifelineCell(ll, model);
      Rectangle rect=GraphConstants.getBounds(llc.getAttributes()).getBounds();

      Enumeration colsenumeration = ll.getChildrenElements();
      int lastY = rect.y+rect.height;
      int lastX=rect.x+rect.width/2-15;
      while (colsenumeration.hasMoreElements()) {
        Object nextElement = colsenumeration.nextElement();
        if (nextElement instanceof Column) {
          Column col = (Column) nextElement;
          ColumnCell colcell = this.locateColumnCell(col, model);
          Map colmap = colcell.getAttributes();
          Enumeration portsenumeration = col.getChildrenElements();
          Vector portcells = new Vector();
          while (portsenumeration.hasMoreElements()) {
            Object nextElement1 = portsenumeration.nextElement();
            if (nextElement1 instanceof AUMLPort) {
              AUMLPort port = (AUMLPort) nextElement1;
              AUMLPortCell portcell = this.locatePortCell(port, model);
              portcells.add(portcell);
            }
          }
          //if (busyports.size()>=portcells.size()){
          Rectangle colcellpos = GraphConstants.getBounds(colcell.
              getAttributes()).
              getBounds();

          // Size of the column must change, and new ports added.
          // New ports are as many as busy ports
          // Column size is increased proportionally with new ports
          // New ports are allocated above or below busy ports

          Hashtable newPortLocation = new Hashtable();
          Rectangle oldpos = GraphConstants.getBounds(colmap).getBounds();
          if (oldpos.y != lastY && lastY != -1) {
            oldpos.y = lastY + COL_SEPARATOR;
            lastY = lastY + COL_SEPARATOR + oldpos.height;
          }
          else {
            lastY = oldpos.y + COL_SEPARATOR + oldpos.height;
          }

          Dimension newColumnSize =
              orderCellsAlongColumn(col, model, oldpos.getLocation(),
                                    newPortLocation);
          ProtocolCell pc = this.locateProtocolCell(ll, model);
          Map mappc = pc.getAttributes();
          Rectangle rectpc = GraphConstants.getBounds(mappc).getBounds();
          Rectangle rectlc = GraphConstants.getBounds(llc.getAttributes()).
              getBounds();
          rectpc.height = Math.max(rectpc.height,
                                   newColumnSize.height + rectlc.height + 50);
          if (!oldpos.getSize().equals(newColumnSize)
              || (oldpos.y != lastY)) {
            oldpos.setSize(newColumnSize);
            oldpos.x=lastX;
            GraphConstants.setBounds(colmap, oldpos);
            newPortLocation.put(colcell, colmap);
            model.edit(newPortLocation, null, null, null);
            newPortLocation.clear();

            Rectangle procoldpos = GraphConstants.getBounds(mappc).getBounds();
            Rectangle procnpos = this.getMaxContainerSize( ( (Protocol) pc.
                getUserObject()));
            procnpos.x = procnpos.x - 5; // to separate the alternative from
            // the border of the protocol
            /*           if (!procoldpos.equals(procnpos)) {
                                     procnpos.x = procoldpos.x;
                                     procnpos.y = procoldpos.y;
                         GraphConstants.setBounds(mappc, procnpos);
                         newPortLocation.put(pc, mappc);
                         model.edit(newPortLocation, null, null, null);
                       }*/
          }
        }
      }
    }

  }

  private Rectangle getMaxContainerSize(AUMLContainer cont) {
    Enumeration enumeration = cont.getChildrenElements();
    DefaultGraphCell dgc1 = AUMLDiagramChangesManager.getCellFromUserObject(
        cont);
    Rectangle finalSize = new Rectangle(0, 0, 0, 0);
    if (dgc1 != null && dgc1.getAttributes() != null &&
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

  /*  private Vector orderCellsAlongColumn(Vector oldcells) {
      Vector result = new Vector();
      Comparator comp = new Comparator() {
        public int compare(Object o1,
                           Object o2) {
          DefaultGraphCell dgc1 = (DefaultGraphCell) o1;
       Rectangle b1 = GraphConstants.getBounds(dgc1.getAttributes()).getBounds();
          DefaultGraphCell dgc2 = (DefaultGraphCell) o2;
       Rectangle b2 = GraphConstants.getBounds(dgc2.getAttributes()).getBounds();
          if (b1.y == b2.y) {
            return 0;
          }
          if (b1.y < b2.y) {
            return -1;
          }
          return 1;
        }
      };
      Collections.sort(oldcells, comp);
      result = oldcells;
      return result;
    }
   */
  private Object createPort(int pos, Column col, Hashtable changes) {
    AUMLPort port = ingenias.editor.ObjectManager.getInstance().
        createAUMLPort(ingenias.editor.Editor.getNewId("port"));
    AUMLPortCell aumlport = new AUMLPortCell(port);
    Map pmap = new Hashtable();
    col.insertChildrenAt(pos, port);
    port.setParent(col);
    Hashtable ht = new Hashtable();
    changes.put(aumlport, pmap);
    return aumlport;
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

  private Dimension orderCellsAlongColumn(Column col,
                                          ingenias.editor.Model model,
                                          Point initial,
                                          Hashtable newlocation) {

    Dimension result = null;
//    oldcells = this.orderCellsAlongColumn(oldcells);
    Vector finalOrderedCells = new Vector();
    int j = 0;
    /*    Vector mll = this.getMainLines(this.getLifelines(model));
        for (int k = 0; k < oldcells.size(); k++) {
          if (busyCells.contains(oldcells.elementAt(k))) {
            if (k == 0 || k == oldcells.size() - 1) {
              insertPos.add(new Integer(k));
              insertPos.add(new Integer(k + 2));
              finalOrderedCells.add(createPort(k, col, model));
              finalOrderedCells.add(oldcells.elementAt(k));
              finalOrderedCells.add(createPort(k + 2, col, model));
            }
            else {
              if (busyCells.contains(oldcells.elementAt(k - 1))) {
                insertPos.add(new Integer(k));
                finalOrderedCells.add(createPort(k, col, model));
                finalOrderedCells.add(oldcells.elementAt(k));
              }
              else {
                finalOrderedCells.add(oldcells.elementAt(k));
              }
            }
          }
          else {
            finalOrderedCells.add(oldcells.elementAt(k));
          }
        }*/
    int initialY = initial.y;
    Enumeration ports = col.getChildrenElements();
//    for (int k = 0; k < finalOrderedCells.size(); k++) {
    while (ports.hasMoreElements()) {
      // DefaultGraphCell dgc = (DefaultGraphCell) finalOrderedCells.elementAt(k);
      DefaultGraphCell dgc = (DefaultGraphCell) ingenias.editor.events.
          AUMLDiagramChangesManager.getCellFromUserObject(ports.nextElement());
      if (dgc != null) {
        Map dgcatt = dgc.getAttributes();
        Rectangle newPos = new Rectangle(initial.x, initialY, 30, 15);
        GraphConstants.setBounds(dgcatt, newPos);
        newlocation.put(dgc, dgcatt);
        initialY = initialY + PORT_TO_PORT_DISTANCE;
      }
    }

    return new Dimension(30, initialY - initial.y);
  }

  private boolean isBusy(AUMLPortCell aumlportcell, ingenias.editor.Model model) {

    if (aumlportcell.getChildCount() == 1) {
      DefaultPort dport = (DefaultPort) aumlportcell.getChildAt(0);
      Set edges = model.getEdges(model, new Object[] {dport});
      if (edges != null && edges.size() > 0) {
        return true;
      }
    }
    return false;

  }

  private Vector getBusyPorts(Vector portscells, ingenias.editor.Model model) {
    Vector busy = new Vector();
    for (int k = 0; k < portscells.size(); k++) {
      AUMLPortCell aumlportcell = (AUMLPortCell) portscells.elementAt(k);
      if (aumlportcell != null && isBusy(aumlportcell, model)) {
        busy.add(aumlportcell);
      }
    }
    return busy;
  }

  private void updateProtocol(Protocol prot, ingenias.editor.Model model) {
    DefaultGraphCell dgc = AUMLDiagramChangesManager.getCellFromUserObject(prot);
    Rectangle rect = this.getMaxContainerSize(prot);
    Map map = dgc.getAttributes();
    Hashtable ht = new Hashtable();
    ht.put(dgc, map);
    model.edit(ht, null, null, null);
  }

  public void simplifyPorts(Vector mainlines, ingenias.editor.Model model) {
    Enumeration[] enumeration = new Enumeration[mainlines.size()];
    for (int k = 0; k < mainlines.size(); k++) {
      enumeration[k] = ( (AUMLContainer) mainlines.elementAt(k)).getChildrenElements();
    }
    boolean finished = false;
    Vector toRemove = new Vector();
    Vector candidates = new Vector();
    Vector foundsome = new Vector();
    int j = 0;
    while (!finished && enumeration.length > 0) {
      foundsome.clear();
      for (int k = 0; k < enumeration.length && !finished; k++) {
        finished = finished || !enumeration[k].hasMoreElements();
        if (!finished) {
          AUMLPort port = (AUMLPort) enumeration[k].nextElement();
          AUMLPortCell aumlportcell = (AUMLPortCell)
              AUMLDiagramChangesManager.
              getCellFromUserObject(port);
          if (aumlportcell != null &&
              !this.isBusy(aumlportcell, model)) {
            foundsome.add(aumlportcell);
          }
        }
      }
      if (foundsome.size() == enumeration.length) {
        candidates.addAll(foundsome);

        if (candidates.size() == 2 * enumeration.length) {
          toRemove.addAll(foundsome);
          candidates.clear();
        }
      }
      else {
        candidates.clear();
      }
      j = j + 1;

    }
    if (toRemove.size() > 0) {
      model.remove(toRemove.toArray());
    }
  }

  public void graphChanged(org.jgraph.event.GraphModelEvent gme) {
    if (enabled && this.workingObject == null) {
      workingObject = "hello";

      ingenias.editor.Model model = (ingenias.editor.Model) gme.getSource();
      Vector<Lifeline> lls = this.getLifelines(model);
      // By default the first column is the main line
      Vector<Column> mainlls = this.getMainLines(lls);
      this.insertNewPorts(mainlls, model);
      this.simplifyPorts(mainlls, model);
      // then, for every alternative, first columns are
      // also main life lines
      Vector alts = this.getAlternativeRows(model);
      /*for (int k = 0; k < alts.size(); k++) {
        AUMLContainer aumlcont = (AUMLContainer) alts.elementAt(k);
        Vector colsinrow = this.getMainLinesFromRow(aumlcont);
        this.insertNewPorts(colsinrow, model);
        this.simplifyPorts(colsinrow, model);
      }*/

      this.computeNewPositions(lls, model);
      for (int k = 0; k < alts.size(); k++) {
        AUMLContainer aumlcont = (AUMLContainer) alts.elementAt(k);
        this.matchRowsRelatedColumns(aumlcont, model);
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
