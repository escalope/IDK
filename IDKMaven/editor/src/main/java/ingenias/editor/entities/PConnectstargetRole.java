

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

public class PConnectstargetRole extends RoleEntity {



 static int idCounter=0;

 public PConnectstargetRole() {
	    super("PConnectstargetRole"+idCounter);
	    idCounter++;

	  }


  public TypedVector TaskInput=new TypedVector(ingenias.editor.entities.TaskInputDefinition.class);


  public PConnectstargetRole(String id) {
    super(id);

  }





  public Class getTaskInputType(){
   return TaskInput.getType();
  }
  public void addTaskInput(ingenias.editor.entities.TaskInputDefinition element){
   this.TaskInput.add(element);
  }

  public Enumeration getTaskInputElements(){
   return this.TaskInput.elements();
  }

  public void removeTaskInputElement(String id){
    Enumeration enumeration=this.getTaskInputElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.TaskInput.remove(found);
  }




public void fromMap(Map ht){
super.fromMap(ht);



}
public void toMap(Map ht){
super.toMap(ht);


}

}
			