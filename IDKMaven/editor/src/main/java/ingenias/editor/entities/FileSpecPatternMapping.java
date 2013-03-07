
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

public class FileSpecPatternMapping extends FileSpecMapping {


  public java.lang.String Pattern;





  public FileSpecPatternMapping(String id) {
    super(id);
    this.setHelpDesc("It adds to FileSpecMapping the possibility of declaring a singleton pattern, meaning that the associated file appears as a singleton.");
    this.setHelpRecom("");
  }


      public java.lang.String getPattern(){
        return Pattern;
      }
       public void setPattern(java.lang.String
					Pattern){
        this.Pattern=Pattern;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Pattern")!=null && ht.get("Pattern").equals(""))
  this.setPattern(null);
 else
  if (ht.get("Pattern")!=null)
   this.setPattern(new java.lang.String(ht.get("Pattern").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getPattern() instanceof String)
 if (this.getPattern()!=null)
 	ht.put("Pattern",this.getPattern().toString());
 else	
 	ht.put("Pattern","");


}

public String toString(){
/*if (this.getEntity()==null ||
    this.getEntity().toString().equals(""))
 return "Please, define the value of field Entity";
else
 return this.getEntity().toString();*/
return ""+getEntity();
}

}
