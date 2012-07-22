
/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz over original code from Ruben Fuentes
 * 
 *   Modifications over original code from jgraph.sourceforge.net
 * 
 * This file is part of the INGENME tool. INGENME is an open source meta-editor
 * which produces customized editors for user-defined modeling languages
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3 of the License
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 **/

package ingenias.editor.cell;

import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;
import java.util.Map;
import org.jgraph.graph.*;
import org.jgraph.*;
import ingenias.editor.entities.*;

// Define the View for an AgentCell
public class AgentView extends VertexView {

  public static AgentRenderer renderer = new AgentRenderer();

  // Constructor for Superclass
  // cell es un AgentCell
  public AgentView(Object cell) {
    super(cell);
  }


  public CellViewRenderer getRenderer() {
    try {
          return this.renderer;
        }
        catch (Exception e) {
          e.printStackTrace();
          ingenias.editor.Log.getInstance().log(e.getMessage());
        }

    return renderer;
  }

  public java.awt.Component getRendererComponent(JGraph jg,boolean b1, boolean b2, boolean b3){
   CellViewRenderer renderer=null;
    try {
           ingenias.editor.entities.Agent ent=(ingenias.editor.entities.Agent)((DefaultGraphCell)this.getCell()).getUserObject();
          this.renderer.setEntity(ent, jg.getModel().getAttributes(this.getCell()));
          JPanel uop=(JPanel)this.renderer.getRendererComponent(jg,this,false,false,false);
          return (Component)uop;
        }
        catch (Exception e) {
          e.printStackTrace();
          ingenias.editor.Log.getInstance().log("WARNING!!!"+e.getMessage());
        }
        return super.getRendererComponent(jg,b1,b2,b3);

 }

  // Default AgentView Size.
  static public Dimension getSize() {
    return renderer.getSize();
  }

  // Default size with entity ent inside
  static public Dimension getSize(Agent ent) {
    renderer.setEntity(ent,null);
    return renderer.getSize();
  }



}
