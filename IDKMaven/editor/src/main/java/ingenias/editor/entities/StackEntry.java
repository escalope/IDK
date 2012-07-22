
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

public class StackEntry extends INGENIASObject {


  public java.lang.String Operation;

  public java.lang.String Time;

  public java.lang.String Resposible;

  public java.lang.String Place;





  public StackEntry(String id) {
    super(id);
    this.setHelpDesc("Stack information");
    this.setHelpRecom("");
  }


      public java.lang.String getOperation(){
        return Operation;
      }
       public void setOperation(java.lang.String
					Operation){
        this.Operation=Operation;
      }


      public java.lang.String getTime(){
        return Time;
      }
       public void setTime(java.lang.String
					Time){
        this.Time=Time;
      }


      public java.lang.String getResposible(){
        return Resposible;
      }
       public void setResposible(java.lang.String
					Resposible){
        this.Resposible=Resposible;
      }


      public java.lang.String getPlace(){
        return Place;
      }
       public void setPlace(java.lang.String
					Place){
        this.Place=Place;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Operation")!=null && ht.get("Operation").equals(""))
  this.setOperation(null);
 else
  if (ht.get("Operation")!=null)
   this.setOperation(new java.lang.String(ht.get("Operation").toString()));

 if (ht.get("Time")!=null && ht.get("Time").equals(""))
  this.setTime(null);
 else
  if (ht.get("Time")!=null)
   this.setTime(new java.lang.String(ht.get("Time").toString()));

 if (ht.get("Resposible")!=null && ht.get("Resposible").equals(""))
  this.setResposible(null);
 else
  if (ht.get("Resposible")!=null)
   this.setResposible(new java.lang.String(ht.get("Resposible").toString()));

 if (ht.get("Place")!=null && ht.get("Place").equals(""))
  this.setPlace(null);
 else
  if (ht.get("Place")!=null)
   this.setPlace(new java.lang.String(ht.get("Place").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getOperation() instanceof String)
 if (this.getOperation()!=null)
 	ht.put("Operation",this.getOperation().toString());
 else	
 	ht.put("Operation","");

//if (this.getTime() instanceof String)
 if (this.getTime()!=null)
 	ht.put("Time",this.getTime().toString());
 else	
 	ht.put("Time","");

//if (this.getResposible() instanceof String)
 if (this.getResposible()!=null)
 	ht.put("Resposible",this.getResposible().toString());
 else	
 	ht.put("Resposible","");

//if (this.getPlace() instanceof String)
 if (this.getPlace()!=null)
 	ht.put("Place",this.getPlace().toString());
 else	
 	ht.put("Place","");


}

public String toString(){
if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();
}

}
