

/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz over original code from Ruben Fuentes
 * 
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

public class OHasMembertargetRole extends RoleEntity {


  public java.lang.String Cardinality;


 static int idCounter=0;

 public OHasMembertargetRole() {
	    super("OHasMembertargetRole"+idCounter);
	    idCounter++;

	  }



  public OHasMembertargetRole(String id) {
    super(id);

  }



      public java.lang.String getCardinality(){
        return Cardinality;
      }
       public void setCardinality(java.lang.String
					Cardinality){
        this.Cardinality=Cardinality;
      }






public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Cardinality")!=null && ht.get("Cardinality").equals(""))
  this.setCardinality(null);
 else
  if (ht.get("Cardinality")!=null)
   this.setCardinality(new java.lang.String(ht.get("Cardinality").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getCardinality() instanceof String)
 if (this.getCardinality()!=null)
 	ht.put("Cardinality",this.getCardinality().toString());
 else	
 	ht.put("Cardinality","");


}

}
			