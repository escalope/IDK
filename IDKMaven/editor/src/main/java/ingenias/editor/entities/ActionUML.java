
/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz
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

public class ActionUML extends UMLObject {




  public TypedVector Tasks=new TypedVector(ingenias.editor.entities.Task.class);



  public ActionUML(String id) {
    super(id);
    this.setHelpDesc("Describes an action in an activity diagram");
    this.setHelpRecom("");
  }




  public void setTasks(TypedVector tv){
    this.Tasks=tv;
  }

  public String getTasks(){
   return Tasks.toString();
  }

  public Class getTasksType(){
   return Tasks.getType();
  }
  public void addTasks(ingenias.editor.entities.Task element){
   this.Tasks.add(element);
  }

  public void insertTasksAt(int pos,ingenias.editor.entities.Task element){
   this.Tasks.insert(element,pos);
  }

  public int containsTasks(ingenias.editor.entities.Task element){
   return this.Tasks.indexOf(element);
  }


  public Enumeration getTasksElements(){
   return this.Tasks.elements();
  }

  public void removeTasksElement(String id){
    Enumeration enumeration=this.getTasksElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.Tasks.remove(found);
  }



public void fromMap(Map ht){
super.fromMap(ht);



}
public void toMap(Map ht){
super.toMap(ht);


}

public String toString(){
/*if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();*/
return ""+getId();
}

}
