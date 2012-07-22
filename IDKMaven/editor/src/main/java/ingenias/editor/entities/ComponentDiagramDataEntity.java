
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

public class ComponentDiagramDataEntity extends ModelDataEntity {


  public java.lang.String Description;




  public ComponentDiagramDataEntity(String id) {
    super(id);

  }



      public java.lang.String getDescription(){
        return Description;
      }
       public void setDescription(java.lang.String
					Description){
        this.Description=Description;
      }






public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Description")!=null && ht.get("Description").equals(""))
  this.setDescription(null);
 else
  if (ht.get("Description")!=null)
   this.setDescription(new java.lang.String(ht.get("Description").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getDescription() instanceof String)
 if (this.getDescription()!=null)
 	ht.put("Description",this.getDescription().toString());
 else	
 	ht.put("Description","");


}

}
			