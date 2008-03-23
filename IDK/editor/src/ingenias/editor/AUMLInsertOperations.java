package ingenias.editor;
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
import ingenias.editor.entities.*;
import ingenias.editor.cell.*;
import ingenias.editor.events.*;
import java.util.*;
public class AUMLInsertOperations {
	
	 private Lifeline locateLifeline(Column col, ModelJGraph m) {
		    Lifeline result = null;
		    for (int k = 0; k < m.getModel().getRootCount(); k++) {
		      if (m.getModel().getRootAt(k)instanceof LifelineCell) {
		        LifelineCell llc = (LifelineCell) m.getModel().getRootAt(k);
		        Lifeline ll = (Lifeline) llc.getUserObject();
		        Enumeration enume = ll.getChildrenElements();
		        while (enume.hasMoreElements()) {
		          Object current = enume.nextElement();
		          if (col.equals(current)) {
		            return ll;
		          }
		        }
		      }
		    }
		    return result;
		  }

		  public void insert(Point point, String entity, ModelJGraph model) {
		    boolean canIInsert = true;
		    // Create a Map that holds the attributes for the Vertex
		    Map map = new Hashtable();
		    // Snap the Point to the Grid
		    point = model.convert(model.snap(new Point(point)));

		    // Construct Vertex with no Label
		    DefaultGraphCell vertex = null;
		    Dimension size = null;

		    if (entity.equalsIgnoreCase("Protocol")) {
		      vertex = new
		          ProtocolCell(IDEAbs.ide.ids.om.createProtocol("Protocol" + Editor.getNewId()));
		      // Default Size for the new Vertex
		      size = new Dimension(300, 250);
		      // Add a Bounds Attribute to the Map
		      GraphConstants.setBounds(map, new Rectangle(point, size));
		      // Construct a Map from cells to Maps (for insert)
		      Hashtable attributes = new Hashtable();
		      // Associate the Vertex with its Attributes
		      attributes.put(vertex, map);
		      // Insert the Vertex and its Attributes
		      model.getModel().insert(new Object[] {vertex}
		                             , attributes
		                             , null, null, null);

		    }
		    else

		    if (entity.equalsIgnoreCase("Lifeline")) {
		      Object[] selectedCells = model.getSelectionCells();
		      canIInsert = (selectedCells.length == 1 && (
		          selectedCells[0] instanceof ingenias.editor.cell.ProtocolCell));
		      if (canIInsert) {
		        ProtocolCell protocolcell = (ingenias.editor.cell.
		                                     ProtocolCell) selectedCells[0];
		        Protocol selectedProtocol = ( (Protocol) protocolcell.getUserObject());
		        int portCount = 1;

		        if (selectedProtocol.getChildrenElements().hasMoreElements()) {
		          Lifeline previousLL = (Lifeline) selectedProtocol.getChildrenElements().
		              nextElement();
		          portCount = countPorts( (Column) previousLL.getChildrenElements().
		                                 nextElement());
		        }

		        Lifeline ll = IDEAbs.ide.ids.om.createLifeline("Lifeline" + Editor.getNewId());

		        ll.setParent(selectedProtocol);
		        selectedProtocol.addChildren(ll);

		        // constrained to a selected protocol
		        vertex = new
		            LifelineCell(ll);
		        Hashtable attributes = new Hashtable();



		        //Setting location and size attributes of lifeline, columns, and ports

		        size = new Dimension(100,50);
		        Rectangle protocolB = GraphConstants.getBounds(protocolcell.
		            getAttributes()).getBounds();
		        Point origPoint = new Point(protocolB.getLocation().x + 10,
		                                    protocolB.getLocation().y + 40);
		        this.createCol(portCount, ll,origPoint,size, attributes);
		        Object cell1 = model.getFirstCellForLocation(origPoint.x,
		            origPoint.y + 2);
		        Object cell2 = model.getFirstCellForLocation(origPoint.x + size.width,
		            origPoint.y + 2);
		        Object cell3 = model.getFirstCellForLocation(origPoint.x +
		            size.width / 2, origPoint.y + 2);

		        Object lastCell = null;
		        boolean found = false;
		        while (!found) {
		          found = (cell1 == null && cell2 == null && cell3 == null) ||
		              (cell1 instanceof ProtocolCell &&
		               cell2 instanceof ProtocolCell &&
		               cell3 instanceof ProtocolCell);

		          if (!found) {
		            origPoint.x = origPoint.x + 40;
		            cell1 = model.getNextCellForLocation(cell1, origPoint.x,
		                                                origPoint.y + 2);
		            cell2 = model.getNextCellForLocation(cell2, origPoint.x + size.width,
		                                                origPoint.y + 2);
		            cell3 = model.getNextCellForLocation(cell3,
		                                                origPoint.x + size.width / 2,
		                                                origPoint.y + 2);
		          }
		        }

		        GraphConstants.setBounds(map,
		                                 new Rectangle(origPoint, size));

		        /*
		         */
		        model.getModel().toBack(selectedCells);
		        // Construct a Map from cells to Maps (for insert)

		        // Associate the Vertex with its Attributes
		        attributes.put(vertex, map);

		        // Insert the Vertex and its Attributes
		        model.getModel().insert(attributes.keySet().toArray()
		                               , attributes
		                               , null, null, null);

		      }
		    }
		    else
		    if (entity.equalsIgnoreCase("Column")) {
		      // Cannot be inserted
		      vertex = new
		          ColumnCell(IDEAbs.ide.ids.om.createColumn("Column" + Editor.getNewId()));
		      // Default Size for the new Vertex
		      size = ColumnView.getSize();
		      // Add a Bounds Attribute to the Map
		      GraphConstants.setBounds(map, new Rectangle(point, size));

		    }
		    else
		    if (entity.equalsIgnoreCase("AUMLAlternativeBox")) {
		      vertex = new
		          AUMLAlternativeBoxCell(IDEAbs.ide.ids.om.createAUMLAlternativeBox(
		          "AUMLAlternativeBox" + Editor.getNewId()));
		      // Default Size for the new Vertex
		      size = AUMLAlternativeBoxView.getSize();
		      // Add a Bounds Attribute to the Map
		      GraphConstants.setBounds(map, new Rectangle(point, size));
		      // Construct a Map from cells to Maps (for insert)
		      Hashtable attributes = new Hashtable();
		      // Associate the Vertex with its Attributes
		      attributes.put(vertex, map);
		      // Insert the Vertex and its Attributes
		      model.getModel().insert(new Object[] {vertex}
		                             , attributes
		                             , null, null, null);

		    }
		    else

		    if (entity.equalsIgnoreCase("AUMLAlternativeRow")) {
		      Object[] selectedCells = model.getSelectionCells();
		      canIInsert = (selectedCells.length == 1 && (
		          selectedCells[0] instanceof ingenias.editor.cell.
		          AUMLAlternativeBoxCell));
		      if (canIInsert) {
		        AUMLAlternativeBoxCell aabc = (AUMLAlternativeBoxCell) selectedCells[0];
		        AUMLAlternativeBox aab = (AUMLAlternativeBox) aabc.getUserObject();

		        AUMLAlternativeRow aar = IDEAbs.ide.ids.om.createAUMLAlternativeRow(
		            "AUMLAlternativeRow" + Editor.getNewId());

		        AUMLAlternativeRowCell aarc = new AUMLAlternativeRowCell(aar);
		        aarc.removeAllChildren();

		        Enumeration enume = aab.getChildrenElements();
		        AUMLAlternativeRow current = (AUMLAlternativeRow) enume.nextElement();
		        enume = current.getChildrenElements();
		        Vector columns = new Vector();
		        Vector newColumns = new Vector();
		        Vector newColumnsCell = new Vector();
		        Hashtable newElements = new Hashtable();

		        while (enume.hasMoreElements()) {

		          Column existentColumn = (Column) enume.nextElement();
		          Column col = IDEAbs.ide.ids.om.createColumn("column" + Editor.getNewId());
		          ColumnCell cc = new ColumnCell(col);
		          cc.removeAllChildren();
		          Lifeline ll = this.locateLifeline(existentColumn,model);

		          int insertIndex = this.getInsertionIndex(ll, aab);

		          ll.insertChildrenAt(insertIndex, col);
		          col.setParent(ll);
		          aar.addChildren(col);

		          Point lastColPos = getLastColPos(aab, ll);

		          AUMLPort aumlp = IDEAbs.ide.ids.om.createAUMLPort("port" + Editor.getNewId());
		          AUMLPortCell aumlpc = new AUMLPortCell(aumlp);
		          col.addChildren(aumlp);
		          aumlp.setParent(col);

		          Map m = new Hashtable();
		          /*          GraphConstants.setBounds(m,
		                                             new Rectangle(lastColPos,
		               new Dimension(30, 15)));*/
		          newElements.put(cc, m);
		          m = new Hashtable();
		          /*          GraphConstants.setBounds(m,
		                                             new Rectangle(lastColPos,
		               new Dimension(30, 15)));*/
		          newElements.put(aumlpc, m);

		          newColumnsCell.add(cc);
		          newColumnsCell.add(aumlpc);
		        }

		        aab.addChildren(aar); // I add the row after the loop
		        // so that inside, the last row is the one containing
		        // columns
		        aar.setParent(aab);

		        Map m = new Hashtable();
		        GraphConstants.setBounds(m,
		                                 this.computeAlternativeRowSize(aar,
		            (ingenias.editor.Model)model.getModel()));
		        newElements.put(aarc, m);
		        newColumnsCell.add(aarc);

		        size = AUMLAlternativeRowView.getSize();
		        GraphConstants.setBounds(map, new Rectangle(point, size));

		        model.getModel().insert(newColumnsCell.toArray()
		                               , newElements
		                               , null, null, null);

		      }

		    }
		    else

		    if (entity.equalsIgnoreCase("TextNote")) {

		      vertex = new
		          TextNoteCell(IDEAbs.ide.ids.om.createTextNote("TextNote" + Editor.getNewId()));
		      // Default Size for the new Vertex
		      size = TextNoteView.getSize();
		      // Add a Bounds Attribute to the Map
		      GraphConstants.setBounds(map, new Rectangle(point, size));
		      // Construct a Map from cells to Maps (for insert)
		      Hashtable attributes = new Hashtable();
		      // Associate the Vertex with its Attributes
		      attributes.put(vertex, map);
		      // Insert the Vertex and its Attributes
		      model.getModel().insert(new Object[] {vertex}
		                             , attributes
		                             , null, null, null);

		    }
		    else

		    {
		      // Add a Bounds Attribute to the Map

		      vertex = new DefaultGraphCell();
		      // Default Size for the new Vertex
		      size = new Dimension(0, 0);
		      // Add a Bounds Attribute to the Map
		      GraphConstants.setBounds(map, new Rectangle(point, size));

		    }

		  }

		  private int getInsertionIndex(Lifeline ll, AUMLAlternativeBox aab) {
		    Enumeration rows = aab.getChildrenElements();
		    AUMLContainer last = null;
		    int insertionPoint = -1;
		    while (rows.hasMoreElements()) {
		      last = (AUMLContainer) rows.nextElement();
		    }
		    Enumeration cols = last.getChildrenElements();
		    boolean found = false;
		    Column col = null;
		    while (cols.hasMoreElements() && insertionPoint == -1) {
		      col = (Column) cols.nextElement();
		      insertionPoint = ll.containsChildren(col);
		    }

		    if (insertionPoint != -1) {
		      return insertionPoint + 1;
		    }
		    else {
		      return insertionPoint;
		    }

		  }

		  private Rectangle computeAlternativeRowSize(AUMLAlternativeRow ar,
		                                              ingenias.editor.Model model) {

		    Enumeration enume = ar.getChildrenElements();
		    Rectangle rect = new Rectangle();
		    rect.x = Integer.MAX_VALUE;
		    rect.y = Integer.MAX_VALUE;
		    rect.width = Integer.MIN_VALUE;
		    rect.height = Integer.MIN_VALUE;
		    while (enume.hasMoreElements()) {
		      Column col = (Column) enume.nextElement();
		      ColumnCell cc = (ColumnCell) AUMLDiagramChangesManager.
		          getCellFromUserObject(col);
		      if (cc != null && cc.getAttributes() != null &&
		          GraphConstants.getBounds(cc.getAttributes()) != null) {
		        Rectangle ccbounds =
		            GraphConstants.getBounds(cc.getAttributes()).getBounds();
		        rect.x = Math.min(ccbounds.x, rect.x);
		        rect.y = Math.min(ccbounds.y, rect.y);
		        rect.height = Math.max(ccbounds.height, rect.height);
		        rect.width = Math.max(ccbounds.x + ccbounds.width, rect.x + rect.width) -
		            rect.x;
		      }

		    }
		    return rect;
		  }

		  private void createCol(int ports, Lifeline ll, Point origPoint,
		                         Dimension size,
		                         Hashtable modif) {
		    Column col = IDEAbs.ide.ids.om.createColumn("column" + Editor.getNewId());
		    col.setParent(ll);
		    ll.addChildren(col);
		    ColumnCell colcell = new ColumnCell(col);
		    // to avoid drawing the column port
		    colcell.removeAllChildren();

		    Map m1 = new Hashtable();
		    GraphConstants.setBounds(m1,
		                             new Rectangle(origPoint.x + 10 +
		                                           (size.width / 2) - 15,
		                                           origPoint.y + 10 +
		                                           (int) size.getHeight(),
		                                           30, 100));

		    modif.put(colcell, m1);
		    for (int k = 0; k < ports; k++) {

		      AUMLPort port = IDEAbs.ide.ids.om.createAUMLPort("port" + Editor.getNewId());
		      port.setParent(col);
		      col.addChildren(port);
		      AUMLPortCell aumlport = new AUMLPortCell(port);

		      Map m2 = new Hashtable();

		      modif.put(aumlport, m2);
		    }

		  }

		  private Vector getLifelineCols(Lifeline ll) {
		    Enumeration enume = ll.getChildrenElements();
		    Vector result = new Vector();
		    while (enume.hasMoreElements()) {
		      result.add(enume.nextElement());
		    }
		    return result;
		  }

		  private Point getLastColPos(AUMLAlternativeBox ab, Lifeline ll) {
		    Enumeration enume = ab.getChildrenElements();
		    Vector llcols = this.getLifelineCols(ll);
		    Point result = new Point(0, 0);
		    while (enume.hasMoreElements()) {
		      AUMLContainer auml = (AUMLContainer) enume.nextElement();
		      Enumeration colsenum = auml.getChildrenElements();
		      while (colsenum.hasMoreElements()) {
		        AUMLComponent component = (AUMLComponent) colsenum.nextElement();
		        if (llcols.contains(component)) {
		          DefaultGraphCell dgc = (DefaultGraphCell) ingenias.editor.events.
		              AUMLDiagramChangesManager.getCellFromUserObject(component);
		          if (dgc != null) {
		            Rectangle rect = GraphConstants.getBounds(dgc.getAttributes()).
		                getBounds();
		            result.y = Math.max(result.y, rect.y + rect.height);
		            result.x = Math.max(result.x, rect.x);
		          }
		        }
		      }
		    }
		    return result;
		  }

		  private int countPorts(Column col) {
		    int result = 0;
		    Enumeration enume = col.getChildrenElements();
		    while (enume.hasMoreElements()) {
		      enume.nextElement();
		      result++;
		    }
		    return result;
		  }
}
