
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

public class TaskInputDefinition extends INGENIASObject {


  public java.lang.String Operation;

  public ingenias.editor.entities.MentalEntity AffectedElement;





  public TaskInputDefinition(String id) {
    super(id);
    this.setHelpDesc("Describes an action in an activity diagram");
    this.setHelpRecom("");
  }


      public java.lang.String getOperation(){
        return Operation;
      }
       public void setOperation(java.lang.String
					Operation){
        this.Operation=Operation;
      }


      public ingenias.editor.entities.MentalEntity getAffectedElement(){
        return AffectedElement;
      }
       public void setAffectedElement(ingenias.editor.entities.MentalEntity
					AffectedElement){
        this.AffectedElement=AffectedElement;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Operation")!=null && ht.get("Operation").equals(""))
  this.setOperation(null);
 else
  if (ht.get("Operation")!=null)
   this.setOperation(new java.lang.String(ht.get("Operation").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getOperation() instanceof String)
 if (this.getOperation()!=null)
 	ht.put("Operation",this.getOperation().toString());
 else	
 	ht.put("Operation","");


}

public String toString(){
/*if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();*/
return ""+Operation + AffectedElement;
}

}
