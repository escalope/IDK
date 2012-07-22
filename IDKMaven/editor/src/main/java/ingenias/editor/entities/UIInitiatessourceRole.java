

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

public class UIInitiatessourceRole extends RoleEntity {


  public java.lang.String Timeout;


 static int idCounter=0;

 public UIInitiatessourceRole() {
	    super("UIInitiatessourceRole"+idCounter);
	    idCounter++;

	  }



  public UIInitiatessourceRole(String id) {
    super(id);

  }



      public java.lang.String getTimeout(){
        return Timeout;
      }
       public void setTimeout(java.lang.String
					Timeout){
        this.Timeout=Timeout;
      }






public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Timeout")!=null && ht.get("Timeout").equals(""))
  this.setTimeout(null);
 else
  if (ht.get("Timeout")!=null)
   this.setTimeout(new java.lang.String(ht.get("Timeout").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getTimeout() instanceof String)
 if (this.getTimeout()!=null)
 	ht.put("Timeout",this.getTimeout().toString());
 else	
 	ht.put("Timeout","");


}

}
			