
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

public class FileSpecMapping extends UMLClassifier {


  public ingenias.editor.entities.INGENIASObject Entity;

  public java.lang.String File;





  public FileSpecMapping(String id) {
    super(id);
    this.setHelpDesc("It is a common slot used only in Frame Fact entity. Each slot could be interpreted in the implementation as an attribute in an object or as a CLIPS slot in a CLIPS fact.");
    this.setHelpRecom("");
  }


      public ingenias.editor.entities.INGENIASObject getEntity(){
        return Entity;
      }
       public void setEntity(ingenias.editor.entities.INGENIASObject
					Entity){
        this.Entity=Entity;
      }


      public java.lang.String getFile(){
        return File;
      }
       public void setFile(java.lang.String
					File){
        this.File=File;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("File")!=null && ht.get("File").equals(""))
  this.setFile(null);
 else
  if (ht.get("File")!=null)
   this.setFile(new java.lang.String(ht.get("File").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getFile() instanceof String)
 if (this.getFile()!=null)
 	ht.put("File",this.getFile().toString());
 else	
 	ht.put("File","");


}

public String toString(){
if (this.getEntity()==null ||
    this.getEntity().toString().equals(""))
 return "Please, define the value of field Entity";
else
 return this.getEntity().toString();
}

}
