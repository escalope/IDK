
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

public class TextUseCase extends UseCase {


  public java.lang.String Postcondition;

  public java.lang.String Precondition;





  public TextUseCase(String id) {
    super(id);
    this.setHelpDesc("A text use case is an UML use case whose description is supplied as natural language text");
    this.setHelpRecom("");
  }


      public java.lang.String getPostcondition(){
        return Postcondition;
      }
       public void setPostcondition(java.lang.String
					Postcondition){
        this.Postcondition=Postcondition;
      }


      public java.lang.String getPrecondition(){
        return Precondition;
      }
       public void setPrecondition(java.lang.String
					Precondition){
        this.Precondition=Precondition;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Postcondition")!=null && ht.get("Postcondition").equals(""))
  this.setPostcondition(null);
 else
  if (ht.get("Postcondition")!=null)
   this.setPostcondition(new java.lang.String(ht.get("Postcondition").toString()));

 if (ht.get("Precondition")!=null && ht.get("Precondition").equals(""))
  this.setPrecondition(null);
 else
  if (ht.get("Precondition")!=null)
   this.setPrecondition(new java.lang.String(ht.get("Precondition").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getPostcondition() instanceof String)
 if (this.getPostcondition()!=null)
 	ht.put("Postcondition",this.getPostcondition().toString());
 else	
 	ht.put("Postcondition","");

//if (this.getPrecondition() instanceof String)
 if (this.getPrecondition()!=null)
 	ht.put("Precondition",this.getPrecondition().toString());
 else	
 	ht.put("Precondition","");


}

public String toString(){
if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();
}

}
