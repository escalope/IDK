
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

public class ConditionalMentalState extends MentalState {


  public java.lang.String Condition;





  public ConditionalMentalState(String id) {
    super(id);
    this.setHelpDesc("A mental state that adds extra information about what conditions must satisfy the entities aggregated in a mental state. Entities in a mental state can be labeled. These labels are used inside the mental state condition.");
    this.setHelpRecom("");
  }


      public java.lang.String getCondition(){
        return Condition;
      }
       public void setCondition(java.lang.String
					Condition){
        this.Condition=Condition;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Condition")!=null && ht.get("Condition").equals(""))
  this.setCondition(null);
 else
  if (ht.get("Condition")!=null)
   this.setCondition(new java.lang.String(ht.get("Condition").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getCondition() instanceof String)
 if (this.getCondition()!=null)
 	ht.put("Condition",this.getCondition().toString());
 else	
 	ht.put("Condition","");


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
