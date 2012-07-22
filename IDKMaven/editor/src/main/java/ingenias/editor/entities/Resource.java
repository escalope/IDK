
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

public class Resource extends AgentComponent {


  public java.lang.String CurrentValue;

  public java.lang.String MinValue;

  public java.lang.String MaxValue;





  public Resource(String id) {
    super(id);
    this.setHelpDesc("Describes a resource according to TAEMS notation. Opposite to TAEMS, there is no distinction between consumable and non-consumable resources.");
    this.setHelpRecom("");
  }


      public java.lang.String getCurrentValue(){
        return CurrentValue;
      }
       public void setCurrentValue(java.lang.String
					CurrentValue){
        this.CurrentValue=CurrentValue;
      }


      public java.lang.String getMinValue(){
        return MinValue;
      }
       public void setMinValue(java.lang.String
					MinValue){
        this.MinValue=MinValue;
      }


      public java.lang.String getMaxValue(){
        return MaxValue;
      }
       public void setMaxValue(java.lang.String
					MaxValue){
        this.MaxValue=MaxValue;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("CurrentValue")!=null && ht.get("CurrentValue").equals(""))
  this.setCurrentValue(null);
 else
  if (ht.get("CurrentValue")!=null)
   this.setCurrentValue(new java.lang.String(ht.get("CurrentValue").toString()));

 if (ht.get("MinValue")!=null && ht.get("MinValue").equals(""))
  this.setMinValue(null);
 else
  if (ht.get("MinValue")!=null)
   this.setMinValue(new java.lang.String(ht.get("MinValue").toString()));

 if (ht.get("MaxValue")!=null && ht.get("MaxValue").equals(""))
  this.setMaxValue(null);
 else
  if (ht.get("MaxValue")!=null)
   this.setMaxValue(new java.lang.String(ht.get("MaxValue").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getCurrentValue() instanceof String)
 if (this.getCurrentValue()!=null)
 	ht.put("CurrentValue",this.getCurrentValue().toString());
 else	
 	ht.put("CurrentValue","");

//if (this.getMinValue() instanceof String)
 if (this.getMinValue()!=null)
 	ht.put("MinValue",this.getMinValue().toString());
 else	
 	ht.put("MinValue","");

//if (this.getMaxValue() instanceof String)
 if (this.getMaxValue()!=null)
 	ht.put("MaxValue",this.getMaxValue().toString());
 else	
 	ht.put("MaxValue","");


}

public String toString(){
if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();
}

}
