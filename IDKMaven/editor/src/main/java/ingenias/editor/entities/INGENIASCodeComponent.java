
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

public class INGENIASCodeComponent extends INGENIASObject {


  public java.lang.String Code;

  public java.lang.String Language;





  public INGENIASCodeComponent(String id) {
    super(id);
    this.setHelpDesc("This component encapsulates code associated to a component.");
    this.setHelpRecom("");
  }


      public java.lang.String getCode(){
        return Code;
      }
       public void setCode(java.lang.String
					Code){
        this.Code=Code;
      }


      public java.lang.String getLanguage(){
        return Language;
      }
       public void setLanguage(java.lang.String
					Language){
        this.Language=Language;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Code")!=null && ht.get("Code").equals(""))
  this.setCode(null);
 else
  if (ht.get("Code")!=null)
   this.setCode(new java.lang.String(ht.get("Code").toString()));

 if (ht.get("Language")!=null && ht.get("Language").equals(""))
  this.setLanguage(null);
 else
  if (ht.get("Language")!=null)
   this.setLanguage(new java.lang.String(ht.get("Language").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getCode() instanceof String)
 if (this.getCode()!=null)
 	ht.put("Code",this.getCode().toString());
 else	
 	ht.put("Code","");

//if (this.getLanguage() instanceof String)
 if (this.getLanguage()!=null)
 	ht.put("Language",this.getLanguage().toString());
 else	
 	ht.put("Language","");


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
