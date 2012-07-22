
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

public class ObjectSlot extends Slot {


  public java.lang.String ObjectValue;





  public ObjectSlot(String id) {
    super(id);
    this.setHelpDesc("It is a common slot used only in Frame Fact entity. Each slot could be interpreted in the implementation as an attribute in an object or as a CLIPS slot in a CLIPS fact.");
    this.setHelpRecom("");
  }


      public java.lang.String getObjectValue(){
        return ObjectValue;
      }
       public void setObjectValue(java.lang.String
					ObjectValue){
        this.ObjectValue=ObjectValue;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("ObjectValue")!=null && ht.get("ObjectValue").equals(""))
  this.setObjectValue(null);
 else
  if (ht.get("ObjectValue")!=null)
   this.setObjectValue(new java.lang.String(ht.get("ObjectValue").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getObjectValue() instanceof String)
 if (this.getObjectValue()!=null)
 	ht.put("ObjectValue",this.getObjectValue().toString());
 else	
 	ht.put("ObjectValue","");


}

public String toString(){
if (this.getName()==null ||
    this.getName().toString().equals(""))
 return "Please, define the value of field Name";
else
 return this.getName().toString();
}

}
