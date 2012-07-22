
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

public class SimulationEvent extends INGENIASObject {


  public java.lang.String ProducedAtSimTime;

  public java.lang.String InsertionFrequency;

  public java.lang.String FinishedAtSimTime;

  public ingenias.editor.entities.DeploymentUnitByType ReceivedByAgentsInDeployment;

  public ingenias.editor.entities.MentalEntity NewInformation;





  public SimulationEvent(String id) {
    super(id);
    this.setHelpDesc("Describes an action in an activity diagram");
    this.setHelpRecom("");
  }


      public java.lang.String getProducedAtSimTime(){
        return ProducedAtSimTime;
      }
       public void setProducedAtSimTime(java.lang.String
					ProducedAtSimTime){
        this.ProducedAtSimTime=ProducedAtSimTime;
      }


      public java.lang.String getInsertionFrequency(){
        return InsertionFrequency;
      }
       public void setInsertionFrequency(java.lang.String
					InsertionFrequency){
        this.InsertionFrequency=InsertionFrequency;
      }


      public java.lang.String getFinishedAtSimTime(){
        return FinishedAtSimTime;
      }
       public void setFinishedAtSimTime(java.lang.String
					FinishedAtSimTime){
        this.FinishedAtSimTime=FinishedAtSimTime;
      }


      public ingenias.editor.entities.DeploymentUnitByType getReceivedByAgentsInDeployment(){
        return ReceivedByAgentsInDeployment;
      }
       public void setReceivedByAgentsInDeployment(ingenias.editor.entities.DeploymentUnitByType
					ReceivedByAgentsInDeployment){
        this.ReceivedByAgentsInDeployment=ReceivedByAgentsInDeployment;
      }


      public ingenias.editor.entities.MentalEntity getNewInformation(){
        return NewInformation;
      }
       public void setNewInformation(ingenias.editor.entities.MentalEntity
					NewInformation){
        this.NewInformation=NewInformation;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("ProducedAtSimTime")!=null && ht.get("ProducedAtSimTime").equals(""))
  this.setProducedAtSimTime(null);
 else
  if (ht.get("ProducedAtSimTime")!=null)
   this.setProducedAtSimTime(new java.lang.String(ht.get("ProducedAtSimTime").toString()));

 if (ht.get("InsertionFrequency")!=null && ht.get("InsertionFrequency").equals(""))
  this.setInsertionFrequency(null);
 else
  if (ht.get("InsertionFrequency")!=null)
   this.setInsertionFrequency(new java.lang.String(ht.get("InsertionFrequency").toString()));

 if (ht.get("FinishedAtSimTime")!=null && ht.get("FinishedAtSimTime").equals(""))
  this.setFinishedAtSimTime(null);
 else
  if (ht.get("FinishedAtSimTime")!=null)
   this.setFinishedAtSimTime(new java.lang.String(ht.get("FinishedAtSimTime").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getProducedAtSimTime() instanceof String)
 if (this.getProducedAtSimTime()!=null)
 	ht.put("ProducedAtSimTime",this.getProducedAtSimTime().toString());
 else	
 	ht.put("ProducedAtSimTime","");

//if (this.getInsertionFrequency() instanceof String)
 if (this.getInsertionFrequency()!=null)
 	ht.put("InsertionFrequency",this.getInsertionFrequency().toString());
 else	
 	ht.put("InsertionFrequency","");

//if (this.getFinishedAtSimTime() instanceof String)
 if (this.getFinishedAtSimTime()!=null)
 	ht.put("FinishedAtSimTime",this.getFinishedAtSimTime().toString());
 else	
 	ht.put("FinishedAtSimTime","");


}

public String toString(){
if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();
}

}
