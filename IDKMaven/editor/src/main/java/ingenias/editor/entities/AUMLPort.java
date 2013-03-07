
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

public class AUMLPort extends AUMLComponent {


  public java.lang.String Dest;





  public AUMLPort(String id) {
    super(id);
    this.setHelpDesc("It represents link of a column to another");
    this.setHelpRecom("");
  }


      public java.lang.String getDest(){
        return Dest;
      }
       public void setDest(java.lang.String
					Dest){
        this.Dest=Dest;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Dest")!=null && ht.get("Dest").equals(""))
  this.setDest(null);
 else
  if (ht.get("Dest")!=null)
   this.setDest(new java.lang.String(ht.get("Dest").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getDest() instanceof String)
 if (this.getDest()!=null)
 	ht.put("Dest",this.getDest().toString());
 else	
 	ht.put("Dest","");


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
