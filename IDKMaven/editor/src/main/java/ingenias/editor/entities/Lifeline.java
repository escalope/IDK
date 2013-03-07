
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

public class Lifeline extends AUMLContainer {


  public ingenias.editor.entities.Agent Agent;

  public java.lang.String Name;

  public java.lang.String Cardinality;

  public ingenias.editor.entities.Role Role;





  public Lifeline(String id) {
    super(id);
    this.setHelpDesc("It represents a lifeline in a AUML protocol");
    this.setHelpRecom("");
  }


      public ingenias.editor.entities.Agent getAgent(){
        return Agent;
      }
       public void setAgent(ingenias.editor.entities.Agent
					Agent){
        this.Agent=Agent;
      }


      public java.lang.String getName(){
        return Name;
      }
       public void setName(java.lang.String
					Name){
        this.Name=Name;
      }


      public java.lang.String getCardinality(){
        return Cardinality;
      }
       public void setCardinality(java.lang.String
					Cardinality){
        this.Cardinality=Cardinality;
      }


      public ingenias.editor.entities.Role getRole(){
        return Role;
      }
       public void setRole(ingenias.editor.entities.Role
					Role){
        this.Role=Role;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Name")!=null && ht.get("Name").equals(""))
  this.setName(null);
 else
  if (ht.get("Name")!=null)
   this.setName(new java.lang.String(ht.get("Name").toString()));

 if (ht.get("Cardinality")!=null && ht.get("Cardinality").equals(""))
  this.setCardinality(null);
 else
  if (ht.get("Cardinality")!=null)
   this.setCardinality(new java.lang.String(ht.get("Cardinality").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getName() instanceof String)
 if (this.getName()!=null)
 	ht.put("Name",this.getName().toString());
 else	
 	ht.put("Name","");

//if (this.getCardinality() instanceof String)
 if (this.getCardinality()!=null)
 	ht.put("Cardinality",this.getCardinality().toString());
 else	
 	ht.put("Cardinality","");


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
