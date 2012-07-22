
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

public class SlotValueSpecification extends INGENIASObject {


  public java.lang.String Value;

  public ingenias.editor.entities.Slot Slot;





  public SlotValueSpecification(String id) {
    super(id);
    this.setHelpDesc("It is a common slot used only in Frame Fact entity. Each slot could be interpreted in the implementation as an attribute in an object or as a CLIPS slot in a CLIPS fact.");
    this.setHelpRecom("");
  }


      public java.lang.String getValue(){
        return Value;
      }
       public void setValue(java.lang.String
					Value){
        this.Value=Value;
      }


      public ingenias.editor.entities.Slot getSlot(){
        return Slot;
      }
       public void setSlot(ingenias.editor.entities.Slot
					Slot){
        this.Slot=Slot;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Value")!=null && ht.get("Value").equals(""))
  this.setValue(null);
 else
  if (ht.get("Value")!=null)
   this.setValue(new java.lang.String(ht.get("Value").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getValue() instanceof String)
 if (this.getValue()!=null)
 	ht.put("Value",this.getValue().toString());
 else	
 	ht.put("Value","");


}

public String toString(){
if (this.getSlot()==null ||
    this.getSlot().toString().equals(""))
 return "Please, define the value of field Slot";
else
 return this.getSlot().toString();
}

}
