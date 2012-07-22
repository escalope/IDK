
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

public class Interaction extends InteractionUnit {


  public java.lang.String Nature;





  public Interaction(String id) {
    super(id);
    this.setHelpDesc("Represents an interaction between two or more agents or roles. There can be only one initiator and at least one collaborator. An interaction also details the goal that pursues. This goal should be related with the goals of the<br>    participants.");
    this.setHelpRecom("");
  }


      public java.lang.String getNature(){
        return Nature;
      }
       public void setNature(java.lang.String
					Nature){
        this.Nature=Nature;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Nature")!=null && ht.get("Nature").equals(""))
  this.setNature(null);
 else
  if (ht.get("Nature")!=null)
   this.setNature(new java.lang.String(ht.get("Nature").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getNature() instanceof String)
 if (this.getNature()!=null)
 	ht.put("Nature",this.getNature().toString());
 else	
 	ht.put("Nature","");


}

public String toString(){
if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();
}

}
