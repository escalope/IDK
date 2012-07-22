
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
import org.jgraph.graph.*;
import ingenias.editor.entities.*;
public class DeploymentUnitByTypeCell extends  DefaultGraphCell {
  // Empty Constructor
  public DeploymentUnitByTypeCell(DeploymentUnitByType userObject) {
    super(userObject); ////////// Por quç¯¯ne esto el ejemplo de JGraph.
    // Add a Port
    DefaultPort port = new DefaultPort(userObject);
    this.add(port);
  }
  public String toString(){
   return this.getUserObject().toString();
  }
}


