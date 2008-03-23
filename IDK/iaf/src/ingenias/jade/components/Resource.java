/*
    Copyright (C) 2005 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 

    INGENIAS Agent Framework is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS Agent Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS Agent Framework; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/


package ingenias.jade.components;

import java.util.*;
import ingenias.jade.exception.*;
/**
 * Not used yet
 * @author jj
 *
 */
public abstract class Resource {
  Vector inputs=new Vector();
  Vector outputs=new Vector();
  Hashtable resources=new Hashtable();
  String id;

  public Resource(String id){
    this.id=id;
  }

  public String getID(){
    return id;
  }

  public void addInput(Object input){
    inputs.add(input);
  }

  public void addResource(String name, Object resource){
    this.resources.put(name,resource);
  }

  protected Vector getInputs(){
    return outputs;
  }

  public Vector getOutputs(){
    return (Vector)(outputs.clone());
  }

  abstract public void execute() throws TaskException;

}