
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

public class Slot extends INGENIASObject {


  public java.lang.String Name;

  public java.lang.String Type;

  public java.lang.String Value;





  public Slot(String id) {
    super(id);
    this.setHelpDesc("It is a common slot used only in Frame Fact entity. Each slot could be interpreted in the implementation as an attribute in an object or as a CLIPS slot in a CLIPS fact.");
    this.setHelpRecom("");
  }


      public java.lang.String getName(){
        return Name;
      }
       public void setName(java.lang.String
					Name){
        this.Name=Name;
      }


      public java.lang.String getType(){
        return Type;
      }
       public void setType(java.lang.String
					Type){
        this.Type=Type;
      }


      public java.lang.String getValue(){
        return Value;
      }
       public void setValue(java.lang.String
					Value){
        this.Value=Value;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Name")!=null && ht.get("Name").equals(""))
  this.setName(null);
 else
  if (ht.get("Name")!=null)
   this.setName(new java.lang.String(ht.get("Name").toString()));

 if (ht.get("Type")!=null && ht.get("Type").equals(""))
  this.setType(null);
 else
  if (ht.get("Type")!=null)
   this.setType(new java.lang.String(ht.get("Type").toString()));

 if (ht.get("Value")!=null && ht.get("Value").equals(""))
  this.setValue(null);
 else
  if (ht.get("Value")!=null)
   this.setValue(new java.lang.String(ht.get("Value").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getName() instanceof String)
 if (this.getName()!=null)
 	ht.put("Name",this.getName().toString());
 else	
 	ht.put("Name","");

//if (this.getType() instanceof String)
 if (this.getType()!=null)
 	ht.put("Type",this.getType().toString());
 else	
 	ht.put("Type","");

//if (this.getValue() instanceof String)
 if (this.getValue()!=null)
 	ht.put("Value",this.getValue().toString());
 else	
 	ht.put("Value","");


}

public String toString(){
if (this.getName()==null ||
    this.getName().toString().equals(""))
 return "Please, define the value of field Name";
else
 return this.getName().toString();
}

}