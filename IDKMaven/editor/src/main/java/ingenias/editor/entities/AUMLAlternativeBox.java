
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

public class AUMLAlternativeBox extends AUMLContainer {


  public java.lang.String NumberOfAlternatives;





  public AUMLAlternativeBox(String id) {
    super(id);
    this.setHelpDesc("It represents link of a column to another");
    this.setHelpRecom("");
  }


      public java.lang.String getNumberOfAlternatives(){
        return NumberOfAlternatives;
      }
       public void setNumberOfAlternatives(java.lang.String
					NumberOfAlternatives){
        this.NumberOfAlternatives=NumberOfAlternatives;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("NumberOfAlternatives")!=null && ht.get("NumberOfAlternatives").equals(""))
  this.setNumberOfAlternatives(null);
 else
  if (ht.get("NumberOfAlternatives")!=null)
   this.setNumberOfAlternatives(new java.lang.String(ht.get("NumberOfAlternatives").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getNumberOfAlternatives() instanceof String)
 if (this.getNumberOfAlternatives()!=null)
 	ht.put("NumberOfAlternatives",this.getNumberOfAlternatives().toString());
 else	
 	ht.put("NumberOfAlternatives","");


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
