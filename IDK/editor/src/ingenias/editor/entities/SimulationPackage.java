
/*
    Copyright (C) 2002 Jorge Gomez Sanz, Ruben Fuentes

    This file is part of INGENIAS IDE, a support tool for the INGENIAS
    methodology, availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net

    INGENIAS IDE is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS IDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS IDE; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/

package ingenias.editor.entities;

import java.util.*;
import ingenias.editor.TypedVector;

public class SimulationPackage extends INGENIASObject {


  public ingenias.editor.entities.Test Test;

  public ingenias.editor.entities.DeploymentPackage SimulationDeployment;

  public java.lang.Integer Duration;



  public TypedVector Parameters=new TypedVector(ingenias.editor.entities.Parameter.class);



  public SimulationPackage(String id) {
    super(id);
    this.setHelpDesc("<br>A deploy unit is an entity that defines how many instances of agents will exist in the final system<br>");
    this.setHelpRecom("");
  }


      public ingenias.editor.entities.Test getTest(){
        return Test;
      }
       public void setTest(ingenias.editor.entities.Test
					Test){
        this.Test=Test;
      }


      public ingenias.editor.entities.DeploymentPackage getSimulationDeployment(){
        return SimulationDeployment;
      }
       public void setSimulationDeployment(ingenias.editor.entities.DeploymentPackage
					SimulationDeployment){
        this.SimulationDeployment=SimulationDeployment;
      }


      public java.lang.Integer getDuration(){
        return Duration;
      }
       public void setDuration(java.lang.Integer
					Duration){
        this.Duration=Duration;
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



public void fromMap(Map ht){
super.fromMap(ht);



}
public void toMap(Map ht){
super.toMap(ht);

}

public String toString(){
if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();
}

}
