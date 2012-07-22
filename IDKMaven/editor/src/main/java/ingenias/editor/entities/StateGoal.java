
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

public class StateGoal extends Goal {


  public java.lang.String State;

  public ingenias.editor.entities.Goal LinkedGoal;





  public StateGoal(String id) {
    super(id);
    this.setHelpDesc("This specialization of Goal is associated with a state that represents the lifecycle of a goal according to INGENIAS. - Satisfied: means that this goal has been achieved - Failed: means that this goal could not be achieved - Pending: the<br>    goal has been activated and is waiting for possible refinements - Refined: the goal has been refined into different subgoals. SubGoals are determined with - Solving: there is a task that can satisfy this goal and it has been executed Field Goal<br>    refers to the goal that is being described.");
    this.setHelpRecom("");
  }


      public java.lang.String getState(){
        return State;
      }
       public void setState(java.lang.String
					State){
        this.State=State;
      }


      public ingenias.editor.entities.Goal getLinkedGoal(){
        return LinkedGoal;
      }
       public void setLinkedGoal(ingenias.editor.entities.Goal
					LinkedGoal){
        this.LinkedGoal=LinkedGoal;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("State")!=null && ht.get("State").equals(""))
  this.setState(null);
 else
  if (ht.get("State")!=null)
   this.setState(new java.lang.String(ht.get("State").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getState() instanceof String)
 if (this.getState()!=null)
 	ht.put("State",this.getState().toString());
 else	
 	ht.put("State","");


}

public String toString(){
if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();
}

}
