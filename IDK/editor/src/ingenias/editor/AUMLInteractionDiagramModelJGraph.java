
/*
    Copyright (C) 2002 Jorge Gomez Sanz, Ruben Fuentes, Juan Pavon

    Modifications over original code from jgraph.sourceforge.net

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
import ingenias.exception.InvalidEntity;

public class AUMLInteractionDiagramModelJGraph extends ModelJGraph {

  public AUMLInteractionDiagramModelJGraph(AUMLInteractionDiagramDataEntity mde, 
                               String nombre, ObjectManager om, Model
                               m, BasicMarqueeHandler mh) {
    super(mde, nombre, m, mh,om);
    
			 
				  this.getModel().addGraphModelListener(
        				new AUMLDiagramChangesManager(this));
				  ToolTipManager.sharedInstance().registerComponent(this);			  
				  
		
    this.getGraphLayoutCache().setFactory(new ingenias.editor.cellfactories.AUMLInteractionDiagramCellViewFactory());
  }


  //
  // Adding Tooltips
  //

  // Return Cell Label as a Tooltip
  public String getToolTipText(MouseEvent e) {
    if (e != null) {
      // Fetch Cell under Mousepointer
      Object c = getFirstCellForLocation(e.getX(), e.getY());
      if (c != null) {

        // Convert Cell to String and Return
        return convertValueToString(c);
      }
    }
    return null;
  }





  public JToolBar getPaleta() {
    return toolbar;

  }

  protected void creaToolBar() {
    toolbar = new JToolBar(JToolBar.VERTICAL);
    toolbar.setFloatable(false);
    ImageIcon undoIcon = null;
    JButton jb = null;


    Image img_Protocol =
        ImageLoader.getImage("images/prot.png");
    undoIcon = new ImageIcon(img_Protocol);
    Action Protocol=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Protocol");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Protocol.setEnabled(true);
    jb = new JButton(Protocol);
    jb.setToolTipText("Protocol");
    toolbar.add(jb);

    Image img_SubProtocol =
        ImageLoader.getImage("images/prot.png");
    undoIcon = new ImageIcon(img_SubProtocol);
    Action SubProtocol=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "SubProtocol");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    SubProtocol.setEnabled(true);
    jb = new JButton(SubProtocol);
    jb.setToolTipText("SubProtocol");
    toolbar.add(jb);

    Image img_Lifeline =
        ImageLoader.getImage("images/col.png");
    undoIcon = new ImageIcon(img_Lifeline);
    Action Lifeline=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Lifeline");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Lifeline.setEnabled(true);
    jb = new JButton(Lifeline);
    jb.setToolTipText("Lifeline");
    toolbar.add(jb);

    Image img_Column =
        ImageLoader.getImage("images/col.png");
    undoIcon = new ImageIcon(img_Column);
    Action Column=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "Column");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    Column.setEnabled(true);
    jb = new JButton(Column);
    jb.setToolTipText("Column");
    toolbar.add(jb);

    Image img_AUMLPort =
        ImageLoader.getImage("images/magent.gif");
    undoIcon = new ImageIcon(img_AUMLPort);
    Action AUMLPort=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "AUMLPort");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    AUMLPort.setEnabled(true);
    jb = new JButton(AUMLPort);
    jb.setToolTipText("AUMLPort");
    toolbar.add(jb);

    Image img_AUMLAlternativeBox =
        ImageLoader.getImage("images/altb.png");
    undoIcon = new ImageIcon(img_AUMLAlternativeBox);
    Action AUMLAlternativeBox=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "AUMLAlternativeBox");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    AUMLAlternativeBox.setEnabled(true);
    jb = new JButton(AUMLAlternativeBox);
    jb.setToolTipText("AUMLAlternativeBox");
    toolbar.add(jb);

    Image img_AUMLAlternativeRow =
        ImageLoader.getImage("images/altr.png");
    undoIcon = new ImageIcon(img_AUMLAlternativeRow);
    Action AUMLAlternativeRow=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "AUMLAlternativeRow");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    AUMLAlternativeRow.setEnabled(true);
    jb = new JButton(AUMLAlternativeRow);
    jb.setToolTipText("AUMLAlternativeRow");
    toolbar.add(jb);

    Image img_TextNote =
        ImageLoader.getImage("images/mtext.gif");
    undoIcon = new ImageIcon(img_TextNote);
    Action TextNote=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "TextNote");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    TextNote.setEnabled(true);
    jb = new JButton(TextNote);
    jb.setToolTipText("TextNote");
    toolbar.add(jb);

    Image img_UMLComment =
        ImageLoader.getImage("images/mumlcomment.gif");
    undoIcon = new ImageIcon(img_UMLComment);
    Action UMLComment=
        new AbstractAction("", undoIcon) {
      public void actionPerformed(ActionEvent e) {
       try{
        insert(new Point(0, 0), "UMLComment");
	} catch (InvalidEntity e1) {			
		e1.printStackTrace();
	}
      }
    };
    UMLComment.setEnabled(true);
    jb = new JButton(UMLComment);
    jb.setToolTipText("UMLComment");
    toolbar.add(jb);


  }

  public static Vector<String> getAllowedRelationships(){
   Vector<String> relationships=new   Vector<String>();


          relationships.add("AUMLSendSimple");

          relationships.add("AUMLSelection");

          relationships.add("AUMLUseProtocol");

   return relationships;
  }

  public static Vector<String> getAllowedEntities(){
   Vector<String> entities=new   Vector<String>();


 entities.add("Protocol");

 entities.add("SubProtocol");

 entities.add("Lifeline");

 entities.add("Column");

 entities.add("AUMLPort");

 entities.add("AUMLAlternativeBox");

 entities.add("AUMLAlternativeRow");

 entities.add("TextNote");

 entities.add("UMLComment");

   return entities;
  }

  // Gets the name of the possible relationships for the selected GraphCells.
  // A relationship can be binary (DefaultEdge) or n-ary (NAryEdge).
  // The requested action is slightly different depending on selected items.
  // According to the number of Edges in selected, the action can be:
  // 0 => Propose a relationship between selected according included classes.
  // 1 and it is NAryEdge => The class of that NAryEdge if it is possible according implements java.io.Serializable
  //      current cardinality and included classes..
  // other cases => Error, no relationships are allowed.
  public Object[] getPossibleRelationships(GraphCell[] selected) {
    // Possible relationships initialization.
    Vector v = new Vector();

    // Search for NAryEdges in selected.
    int nAryEdgesNum = 0;
    int edgesNum = 0;
    NAryEdge selectedEdge = null;
    for (int i = 0; i < selected.length; i++) {
      if (selected[i] instanceof NAryEdge) {
        nAryEdgesNum++;
        selectedEdge = (NAryEdge) selected[i];
      }
      else if (selected[i] instanceof DefaultEdge) {
        edgesNum++;

        // Connections are only possible with two or more elements and without binary edges.
      }
    }
    if (selected.length >= 2 && edgesNum == 0) {

      // The number of NAryEdges is considered.
      if (nAryEdgesNum == 0) {
        // acceptConnection methods only admits vertex parameters.
        // Binary relationships.

        // N-ary relationships. Sometimes they can be also binary.
        if (AUMLSendSimpleEdge.acceptConnection(this.getModel(), selected)) {
          v.add("AUMLSendSimple");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (AUMLSelectionEdge.acceptConnection(this.getModel(), selected)) {
          v.add("AUMLSelection");
	  }

        // N-ary relationships. Sometimes they can be also binary.
        if (AUMLUseProtocolEdge.acceptConnection(this.getModel(), selected)) {
          v.add("AUMLUseProtocol");
	  }

      }
      else if (nAryEdgesNum == 1) {

        if (selectedEdge instanceof AUMLSendSimpleEdge &&
        (AUMLSendSimpleEdge.acceptConnection(this.getModel(), selected))) {
          v.add("AUMLSendSimple");
        }

        if (selectedEdge instanceof AUMLSelectionEdge &&
        (AUMLSelectionEdge.acceptConnection(this.getModel(), selected))) {
          v.add("AUMLSelection");
        }

        if (selectedEdge instanceof AUMLUseProtocolEdge &&
        (AUMLUseProtocolEdge.acceptConnection(this.getModel(), selected))) {
          v.add("AUMLUseProtocol");
        }

      }
    }

    return v.toArray();
  }

  public DefaultGraphCell getInstanciaNRelacion(String relacion,
                                                GraphCell[] selected) {

    // Search for NAryEdges in selected.
    int nAryEdgesNum = 0;
    int edgesNum = 0;
    NAryEdge selectedEdge = null;
    for (int i = 0; i < selected.length; i++) {
      if (selected[i] instanceof NAryEdge) {
        nAryEdgesNum++;
        selectedEdge = (NAryEdge) selected[i];
      }
      else if (selected[i] instanceof DefaultEdge) {
        edgesNum++;

      }
    }
    if (nAryEdgesNum <= 1 && edgesNum == 0) {

      if (relacion.equalsIgnoreCase("AUMLSendSimple")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof AUMLSendSimpleEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new AUMLSendSimpleEdge(new AUMLSendSimple(Editor.getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("AUMLSelection")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof AUMLSelectionEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new AUMLSelectionEdge(new AUMLSelection(Editor.getNewId()));
        }
      }

      if (relacion.equalsIgnoreCase("AUMLUseProtocol")) {
        // ResponsibleNEdge already exists.
        if (nAryEdgesNum == 1 && selectedEdge instanceof AUMLUseProtocolEdge) {
          return selectedEdge;
        }
        // There is no NAryEdges in selected.
        else if (nAryEdgesNum == 0) {
          return new AUMLUseProtocolEdge(new AUMLUseProtocol(Editor.getNewId()));
        }
      }

    }

    return null;
  }
  
  public DefaultGraphCell createCell(String entity) throws InvalidEntity{
  
    if (entity.equalsIgnoreCase("Protocol")) {
    Protocol nentity=getOM().createProtocol( Editor.getNewId("Protocol"));
      DefaultGraphCell vertex = new
          ProtocolCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("SubProtocol")) {
    SubProtocol nentity=getOM().createSubProtocol( Editor.getNewId("SubProtocol"));
      DefaultGraphCell vertex = new
          SubProtocolCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Lifeline")) {
    Lifeline nentity=getOM().createLifeline( Editor.getNewId("Lifeline"));
      DefaultGraphCell vertex = new
          LifelineCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Column")) {
    Column nentity=getOM().createColumn( Editor.getNewId("Column"));
      DefaultGraphCell vertex = new
          ColumnCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("AUMLPort")) {
    AUMLPort nentity=getOM().createAUMLPort( Editor.getNewId("AUMLPort"));
      DefaultGraphCell vertex = new
          AUMLPortCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("AUMLAlternativeBox")) {
    AUMLAlternativeBox nentity=getOM().createAUMLAlternativeBox( Editor.getNewId("AUMLAlternativeBox"));
      DefaultGraphCell vertex = new
          AUMLAlternativeBoxCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("AUMLAlternativeRow")) {
    AUMLAlternativeRow nentity=getOM().createAUMLAlternativeRow( Editor.getNewId("AUMLAlternativeRow"));
      DefaultGraphCell vertex = new
          AUMLAlternativeRowCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("TextNote")) {
    TextNote nentity=getOM().createTextNote( Editor.getNewId("TextNote"));
      DefaultGraphCell vertex = new
          TextNoteCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("UMLComment")) {
    UMLComment nentity=getOM().createUMLComment( Editor.getNewId("UMLComment"));
      DefaultGraphCell vertex = new
          UMLCommentCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

	  throw new ingenias.exception.InvalidEntity("Entity type "+entity+" is not allowed in this diagram"); 
  }
  
  public Dimension getDefaultSize(Entity entity) throws InvalidEntity{
    
    if (entity.getType().equalsIgnoreCase("Protocol")) {
      return ProtocolView.getSize((Protocol)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("SubProtocol")) {
      return SubProtocolView.getSize((SubProtocol)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Lifeline")) {
      return LifelineView.getSize((Lifeline)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Column")) {
      return ColumnView.getSize((Column)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AUMLPort")) {
      return AUMLPortView.getSize((AUMLPort)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AUMLAlternativeBox")) {
      return AUMLAlternativeBoxView.getSize((AUMLAlternativeBox)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AUMLAlternativeRow")) {
      return AUMLAlternativeRowView.getSize((AUMLAlternativeRow)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("TextNote")) {
      return TextNoteView.getSize((TextNote)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("UMLComment")) {
      return UMLCommentView.getSize((UMLComment)entity);      
    }
    else

    throw new ingenias.exception.InvalidEntity("Entity type "+entity+" is not allowed in this diagram"); 
	    
  }

  public DefaultGraphCell insert(Point point, String entity) throws InvalidEntity {
  // CellView information is not available after creating the cell.
	  
	  return new AUMLInsertOperations().insert(point,entity,this);

  
  }

  


public DefaultGraphCell insertDuplicated(Point point, ingenias.editor.entities.Entity
                               entity) {
    // CellView information is not available after creating the cell.

    // Create a Map that holds the attributes for the Vertex
    Map map =new Hashtable();
    // Snap the Point to the Grid
    point = convert(this.snap(new Point(point)));
   

    // Construct Vertex with no Label
    DefaultGraphCell vertex = null;
    Dimension size = null;


    if (entity.getClass().equals(Protocol.class)) {
      vertex = new ProtocolCell( (Protocol) entity);
      // Default Size for the new Vertex with the new entity within
      size = ProtocolView.getSize((Protocol) entity);
    }
    else

    if (entity.getClass().equals(SubProtocol.class)) {
      vertex = new SubProtocolCell( (SubProtocol) entity);
      // Default Size for the new Vertex with the new entity within
      size = SubProtocolView.getSize((SubProtocol) entity);
    }
    else

    if (entity.getClass().equals(Lifeline.class)) {
      vertex = new LifelineCell( (Lifeline) entity);
      // Default Size for the new Vertex with the new entity within
      size = LifelineView.getSize((Lifeline) entity);
    }
    else

    if (entity.getClass().equals(Column.class)) {
      vertex = new ColumnCell( (Column) entity);
      // Default Size for the new Vertex with the new entity within
      size = ColumnView.getSize((Column) entity);
    }
    else

    if (entity.getClass().equals(AUMLPort.class)) {
      vertex = new AUMLPortCell( (AUMLPort) entity);
      // Default Size for the new Vertex with the new entity within
      size = AUMLPortView.getSize((AUMLPort) entity);
    }
    else

    if (entity.getClass().equals(AUMLAlternativeBox.class)) {
      vertex = new AUMLAlternativeBoxCell( (AUMLAlternativeBox) entity);
      // Default Size for the new Vertex with the new entity within
      size = AUMLAlternativeBoxView.getSize((AUMLAlternativeBox) entity);
    }
    else

    if (entity.getClass().equals(AUMLAlternativeRow.class)) {
      vertex = new AUMLAlternativeRowCell( (AUMLAlternativeRow) entity);
      // Default Size for the new Vertex with the new entity within
      size = AUMLAlternativeRowView.getSize((AUMLAlternativeRow) entity);
    }
    else

    if (entity.getClass().equals(TextNote.class)) {
      vertex = new TextNoteCell( (TextNote) entity);
      // Default Size for the new Vertex with the new entity within
      size = TextNoteView.getSize((TextNote) entity);
    }
    else

    if (entity.getClass().equals(UMLComment.class)) {
      vertex = new UMLCommentCell( (UMLComment) entity);
      // Default Size for the new Vertex with the new entity within
      size = UMLCommentView.getSize((UMLComment) entity);
    }
    else

   {}; // Just in case there is no allowed entity in the diagram

    if (vertex == null) {
JOptionPane.showMessageDialog(IDE.ide,
		 "Object not allowed in this diagram "+this.getID()+":"+ 
		 entity.getId()+":"+entity.getClass().getName()+
		 this.getClass().getName(),"Warning", JOptionPane.WARNING_MESSAGE);    }
    else {

      // Add a Bounds Attribute to the Map
      GraphConstants.setBounds(map, new Rectangle(point, size));

      // Construct a Map from cells to Maps (for insert)
      Hashtable attributes = new Hashtable();
      // Associate the Vertex with its Attributes
      attributes.put(vertex, map);
      // Insert the Vertex and its Attributes
      this.getModel().insert(new Object[] {vertex},attributes
                             , null, null, null);
    }
   return vertex;

  }

  public String toString() {
    return this.getID();
  }

}
