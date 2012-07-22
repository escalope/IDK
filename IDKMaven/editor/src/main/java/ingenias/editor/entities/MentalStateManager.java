
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

public class MentalStateManager extends AgentComponent {




  public TypedVector ManagerDescription=new TypedVector(ingenias.editor.entities.TasksAndGoalsModelModelEntity.class);



  public MentalStateManager(String id) {
    super(id);
    this.setHelpDesc("An agent has a mental state upon which the agent takes decisions. This mental state is an aggregate of mental entities (believes, facts, events, etc.). As an aggregate, the mental state must be managed. Say that management of mental<br>    state consists of determining how new mental entities are added, how to maintain consistency, and how to remove entities. To specify these elements you can use description field of the entity or also task and goal models. If you use task and goals<br>    models, you can detail which management tasks exist and how they act. In these diagrams, tasks can be associated to mental entities by other relationships different from consumes or produces. For instance, you can say that a task create mental<br>    entities or removes mental entities depending on certain conditions. To express these conditions, we use MentalStatePatterns.");
    this.setHelpRecom("");
  }




  public void setManagerDescription(TypedVector tv){
    this.ManagerDescription=tv;
  }

  public String getManagerDescription(){
   return ManagerDescription.toString();
  }

  public Class getManagerDescriptionType(){
   return ManagerDescription.getType();
  }
  public void addManagerDescription(TasksAndGoalsModelModelEntity element){
   this.ManagerDescription.add(element);
  }

  public void insertManagerDescriptionAt(int pos,TasksAndGoalsModelModelEntity element){
   this.ManagerDescription.insert(element,pos);
  }

  public int containsManagerDescription(TasksAndGoalsModelModelEntity element){
   return this.ManagerDescription.indexOf(element);
  }


  public Enumeration getManagerDescriptionElements(){
   return this.ManagerDescription.elements();
  }

  public void removeManagerDescriptionElement(String id){
    Enumeration enumeration=this.getManagerDescriptionElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.ManagerDescription.remove(found);
  }



public void fromMap(Map ht){
super.fromMap(ht);



}
public void toMap(Map ht){
super.toMap(ht);


}

public String toString(){
if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();
}

}
