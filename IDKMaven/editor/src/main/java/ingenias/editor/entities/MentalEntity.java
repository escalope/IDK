
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

public class MentalEntity extends INGENIASObject {


  public java.lang.String ExecutionContext;





  public MentalEntity(String id) {
    super(id);
    this.setHelpDesc("It is any element that may form part of the mental state of an agent");
    this.setHelpRecom("");
  }


      public java.lang.String getExecutionContext(){
        return ExecutionContext;
      }
       public void setExecutionContext(java.lang.String
					ExecutionContext){
        this.ExecutionContext=ExecutionContext;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("ExecutionContext")!=null && ht.get("ExecutionContext").equals(""))
  this.setExecutionContext(null);
 else
  if (ht.get("ExecutionContext")!=null)
   this.setExecutionContext(new java.lang.String(ht.get("ExecutionContext").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getExecutionContext() instanceof String)
 if (this.getExecutionContext()!=null)
 	ht.put("ExecutionContext",this.getExecutionContext().toString());
 else	
 	ht.put("ExecutionContext","");


}

public String toString(){
if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();
}

}
