
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

public class SimExtractedInformation extends INGENIASObject {


  public java.lang.String PollEachSimTimeUnits;

  public ingenias.editor.entities.DeploymentUnitByType PollAgentsInDeployment;



  public TypedVector EntitiesToExtract=new TypedVector(ingenias.editor.entities.MentalEntity.class);



  public SimExtractedInformation(String id) {
    super(id);
    this.setHelpDesc("Describes an action in an activity diagram");
    this.setHelpRecom("");
  }


      public java.lang.String getPollEachSimTimeUnits(){
        return PollEachSimTimeUnits;
      }
       public void setPollEachSimTimeUnits(java.lang.String
					PollEachSimTimeUnits){
        this.PollEachSimTimeUnits=PollEachSimTimeUnits;
      }


      public ingenias.editor.entities.DeploymentUnitByType getPollAgentsInDeployment(){
        return PollAgentsInDeployment;
      }
       public void setPollAgentsInDeployment(ingenias.editor.entities.DeploymentUnitByType
					PollAgentsInDeployment){
        this.PollAgentsInDeployment=PollAgentsInDeployment;
      }




  public void setEntitiesToExtract(TypedVector tv){
    this.EntitiesToExtract=tv;
  }

  public String getEntitiesToExtract(){
   return EntitiesToExtract.toString();
  }

  public Class getEntitiesToExtractType(){
   return EntitiesToExtract.getType();
  }
  public void addEntitiesToExtract(ingenias.editor.entities.MentalEntity element){
   this.EntitiesToExtract.add(element);
  }

  public void insertEntitiesToExtractAt(int pos,ingenias.editor.entities.MentalEntity element){
   this.EntitiesToExtract.insert(element,pos);
  }

  public int containsEntitiesToExtract(ingenias.editor.entities.MentalEntity element){
   return this.EntitiesToExtract.indexOf(element);
  }


  public Enumeration getEntitiesToExtractElements(){
   return this.EntitiesToExtract.elements();
  }

  public void removeEntitiesToExtractElement(String id){
    Enumeration enumeration=this.getEntitiesToExtractElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.EntitiesToExtract.remove(found);
  }



public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("PollEachSimTimeUnits")!=null && ht.get("PollEachSimTimeUnits").equals(""))
  this.setPollEachSimTimeUnits(null);
 else
  if (ht.get("PollEachSimTimeUnits")!=null)
   this.setPollEachSimTimeUnits(new java.lang.String(ht.get("PollEachSimTimeUnits").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getPollEachSimTimeUnits() instanceof String)
 if (this.getPollEachSimTimeUnits()!=null)
 	ht.put("PollEachSimTimeUnits",this.getPollEachSimTimeUnits().toString());
 else	
 	ht.put("PollEachSimTimeUnits","");


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
