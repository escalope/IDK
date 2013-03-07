
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

public class MentalEntityInstanceCreation extends INGENIASObject {


  public java.lang.String MatchingPattern;

  public java.lang.String InstanceName;

  public java.lang.String Operation;

  public ingenias.editor.entities.MentalEntity MentalEntity;





  public MentalEntityInstanceCreation(String id) {
    super(id);
    this.setHelpDesc("");
    this.setHelpRecom("");
  }


      public java.lang.String getMatchingPattern(){
        return MatchingPattern;
      }
       public void setMatchingPattern(java.lang.String
					MatchingPattern){
        this.MatchingPattern=MatchingPattern;
      }


      public java.lang.String getInstanceName(){
        return InstanceName;
      }
       public void setInstanceName(java.lang.String
					InstanceName){
        this.InstanceName=InstanceName;
      }


      public java.lang.String getOperation(){
        return Operation;
      }
       public void setOperation(java.lang.String
					Operation){
        this.Operation=Operation;
      }


      public ingenias.editor.entities.MentalEntity getMentalEntity(){
        return MentalEntity;
      }
       public void setMentalEntity(ingenias.editor.entities.MentalEntity
					MentalEntity){
        this.MentalEntity=MentalEntity;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("MatchingPattern")!=null && ht.get("MatchingPattern").equals(""))
  this.setMatchingPattern(null);
 else
  if (ht.get("MatchingPattern")!=null)
   this.setMatchingPattern(new java.lang.String(ht.get("MatchingPattern").toString()));

 if (ht.get("InstanceName")!=null && ht.get("InstanceName").equals(""))
  this.setInstanceName(null);
 else
  if (ht.get("InstanceName")!=null)
   this.setInstanceName(new java.lang.String(ht.get("InstanceName").toString()));

 if (ht.get("Operation")!=null && ht.get("Operation").equals(""))
  this.setOperation(null);
 else
  if (ht.get("Operation")!=null)
   this.setOperation(new java.lang.String(ht.get("Operation").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getMatchingPattern() instanceof String)
 if (this.getMatchingPattern()!=null)
 	ht.put("MatchingPattern",this.getMatchingPattern().toString());
 else	
 	ht.put("MatchingPattern","");

//if (this.getInstanceName() instanceof String)
 if (this.getInstanceName()!=null)
 	ht.put("InstanceName",this.getInstanceName().toString());
 else	
 	ht.put("InstanceName","");

//if (this.getOperation() instanceof String)
 if (this.getOperation()!=null)
 	ht.put("Operation",this.getOperation().toString());
 else	
 	ht.put("Operation","");


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
