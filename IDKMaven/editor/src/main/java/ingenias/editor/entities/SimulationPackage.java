
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

public class SimulationPackage extends INGENIASObject {


  public java.lang.String DeltaT;

  public java.lang.String SimLength;

  public ingenias.editor.entities.DeploymentPackage SimulationDeployment;



  public TypedVector InjectedEvents=new TypedVector(ingenias.editor.entities.SimulationEvent.class);

  public TypedVector Parameters=new TypedVector(ingenias.editor.entities.Parameter.class);

  public TypedVector ExtractedInformation=new TypedVector(ingenias.editor.entities.SimExtractedInformation.class);



  public SimulationPackage(String id) {
    super(id);
    this.setHelpDesc("A deploy unit is an entity that defines how many instances of agents will exist in the final system");
    this.setHelpRecom("");
  }


      public java.lang.String getDeltaT(){
        return DeltaT;
      }
       public void setDeltaT(java.lang.String
					DeltaT){
        this.DeltaT=DeltaT;
      }


      public java.lang.String getSimLength(){
        return SimLength;
      }
       public void setSimLength(java.lang.String
					SimLength){
        this.SimLength=SimLength;
      }


      public ingenias.editor.entities.DeploymentPackage getSimulationDeployment(){
        return SimulationDeployment;
      }
       public void setSimulationDeployment(ingenias.editor.entities.DeploymentPackage
					SimulationDeployment){
        this.SimulationDeployment=SimulationDeployment;
      }




  public void setInjectedEvents(TypedVector tv){
    this.InjectedEvents=tv;
  }

  public String getInjectedEvents(){
   return InjectedEvents.toString();
  }

  public Class getInjectedEventsType(){
   return InjectedEvents.getType();
  }
  public void addInjectedEvents(ingenias.editor.entities.SimulationEvent element){
   this.InjectedEvents.add(element);
  }

  public void insertInjectedEventsAt(int pos,ingenias.editor.entities.SimulationEvent element){
   this.InjectedEvents.insert(element,pos);
  }

  public int containsInjectedEvents(ingenias.editor.entities.SimulationEvent element){
   return this.InjectedEvents.indexOf(element);
  }


  public Enumeration getInjectedEventsElements(){
   return this.InjectedEvents.elements();
  }

  public void removeInjectedEventsElement(String id){
    Enumeration enumeration=this.getInjectedEventsElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.InjectedEvents.remove(found);
  }


  public void setParameters(TypedVector tv){
    this.Parameters=tv;
  }

  public String getParameters(){
   return Parameters.toString();
  }

  public Class getParametersType(){
   return Parameters.getType();
  }
  public void addParameters(ingenias.editor.entities.Parameter element){
   this.Parameters.add(element);
  }

  public void insertParametersAt(int pos,ingenias.editor.entities.Parameter element){
   this.Parameters.insert(element,pos);
  }

  public int containsParameters(ingenias.editor.entities.Parameter element){
   return this.Parameters.indexOf(element);
  }


  public Enumeration getParametersElements(){
   return this.Parameters.elements();
  }

  public void removeParametersElement(String id){
    Enumeration enumeration=this.getParametersElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.Parameters.remove(found);
  }


  public void setExtractedInformation(TypedVector tv){
    this.ExtractedInformation=tv;
  }

  public String getExtractedInformation(){
   return ExtractedInformation.toString();
  }

  public Class getExtractedInformationType(){
   return ExtractedInformation.getType();
  }
  public void addExtractedInformation(ingenias.editor.entities.SimExtractedInformation element){
   this.ExtractedInformation.add(element);
  }

  public void insertExtractedInformationAt(int pos,ingenias.editor.entities.SimExtractedInformation element){
   this.ExtractedInformation.insert(element,pos);
  }

  public int containsExtractedInformation(ingenias.editor.entities.SimExtractedInformation element){
   return this.ExtractedInformation.indexOf(element);
  }


  public Enumeration getExtractedInformationElements(){
   return this.ExtractedInformation.elements();
  }

  public void removeExtractedInformationElement(String id){
    Enumeration enumeration=this.getExtractedInformationElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.ExtractedInformation.remove(found);
  }



public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("DeltaT")!=null && ht.get("DeltaT").equals(""))
  this.setDeltaT(null);
 else
  if (ht.get("DeltaT")!=null)
   this.setDeltaT(new java.lang.String(ht.get("DeltaT").toString()));

 if (ht.get("SimLength")!=null && ht.get("SimLength").equals(""))
  this.setSimLength(null);
 else
  if (ht.get("SimLength")!=null)
   this.setSimLength(new java.lang.String(ht.get("SimLength").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getDeltaT() instanceof String)
 if (this.getDeltaT()!=null)
 	ht.put("DeltaT",this.getDeltaT().toString());
 else	
 	ht.put("DeltaT","");

//if (this.getSimLength() instanceof String)
 if (this.getSimLength()!=null)
 	ht.put("SimLength",this.getSimLength().toString());
 else	
 	ht.put("SimLength","");


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
