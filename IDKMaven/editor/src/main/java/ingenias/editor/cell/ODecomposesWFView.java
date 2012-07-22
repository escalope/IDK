

/*
    Copyright (C) 2002 Jorge Gomez Sanz, Ruben Fuentes

    Modifications of original jgraph distribution code (jgraph.sourceforge.ent)

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

package ingenias.editor.cell;


import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.util.Map;
import java.util.Hashtable;
import org.jgraph.graph.*;
import org.jgraph.*;
import java.awt.geom.Point2D;

public class ODecomposesWFView extends NAryView {

static ODecomposesWFRenderer renderer1 = new ODecomposesWFRenderer();
 public static ODecomposesWFRenderer renderer = new ODecomposesWFRenderer();
  // Constructor.
  public ODecomposesWFView(Object cell) {
    super(cell);
  }

  // Default ResponsibleView Size.
  static public Dimension getSize() {
    return new Dimension(80,120);
  }

  public CellViewRenderer getRenderer() {
    return renderer1;
  }

  public java.awt.Component getRendererComponent(JGraph jg, boolean b1,
                                                 boolean b2, boolean b3) {
    CellViewRenderer renderer=null;
   try {
         ingenias.editor.entities.ODecomposesWF ent=(ingenias.editor.entities.ODecomposesWF)((DefaultGraphCell)this.getCell()).getUserObject();
         this.renderer1.setEntity(ent,jg.getModel().getAttributes(this.getCell()));
         JPanel uop=(JPanel)this.renderer1.getRendererComponent(null,null,false,false,false);
        if (ent.getPrefs(jg.getModel().getAttributes(this.getCell())).getView()==ingenias.editor.entities.ViewPreferences.ViewType.LABEL){
        	 NAryEdge naryedge=(NAryEdge)this.getCell();
        	 DefaultEdge[] edge=naryedge.getRepresentation();
        	 AttributeMap am=edge[0].getAttributes();
        	 GraphConstants.setLabelAlongEdge(am,true);
        	 GraphConstants.setExtraLabels(am,new Object[]{ent.getLabel()});
        	 GraphConstants.setExtraLabelPositions(am,new Point2D[]{new Point2D.Double(GraphConstants.PERMILLE*7/8, -20)});
        	 edge[0].setAttributes(am);
         }
         return (Component)uop;
       }
       catch (Exception e) {
         e.printStackTrace();
         ingenias.editor.Log.getInstance().log("WARNING!!!"+e.getMessage());
       }
       return super.getRendererComponent(jg,b1,b2,b3);

  }
    // Default size with entity ent inside
  static public Dimension getSize(ingenias.editor.entities.ODecomposesWF ent) {
    renderer.setEntity(ent,null);
    return renderer.getSize();
  }

}


			