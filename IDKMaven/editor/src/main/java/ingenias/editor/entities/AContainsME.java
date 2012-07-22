

/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz over original code from Ruben Fuentes
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

public class AContainsME extends NAryEdgeEntity {


  public java.lang.String Label;




  public AContainsME(String id) {
    super(id);
    ModelEntity em=null;
  }






  
      public java.lang.String getLabel(){
        return Label;
      }
   public void setLabel(java.lang.String
					Label){
        this.Label=Label;
      }



public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Label")!=null && ht.get("Label").equals(""))
  this.setLabel(null);
 else
  if (ht.get("Label")!=null)
   this.setLabel(new java.lang.String(ht.get("Label").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getLabel() instanceof String)
 if (this.getLabel()!=null)
 	ht.put("Label",this.getLabel().toString());
 else	
 	ht.put("Label","");


}


public String toString(){
 return getId()+":"+getType();
}



}
