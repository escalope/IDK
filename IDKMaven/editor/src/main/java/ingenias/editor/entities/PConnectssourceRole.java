

/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz over original code from Ruben Fuentes
 * 
 * 
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

package ingenias.editor.entities;


import java.util.*;
import ingenias.editor.TypedVector;

public class PConnectssourceRole extends RoleEntity {



 static int idCounter=0;

 public PConnectssourceRole() {
	    super("PConnectssourceRole"+idCounter);
	    idCounter++;

	  }


  public TypedVector TaskOutput=new TypedVector(ingenias.editor.entities.TaskOutputDefinition.class);


  public PConnectssourceRole(String id) {
    super(id);

  }





  public Class getTaskOutputType(){
   return TaskOutput.getType();
  }
  public void addTaskOutput(ingenias.editor.entities.TaskOutputDefinition element){
   this.TaskOutput.add(element);
  }

  public Enumeration getTaskOutputElements(){
   return this.TaskOutput.elements();
  }

  public void removeTaskOutputElement(String id){
    Enumeration enumeration=this.getTaskOutputElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.TaskOutput.remove(found);
  }




public void fromMap(Map ht){
super.fromMap(ht);



}
public void toMap(Map ht){
super.toMap(ht);


}

}
			