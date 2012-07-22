
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

public class DeploymentPackage extends INGENIASObject {




  public TypedVector Parameters=new TypedVector(ingenias.editor.entities.Parameter.class);

  public TypedVector AgentsDeployed=new TypedVector(ingenias.editor.entities.DeploymentUnitByType.class);



  public DeploymentPackage(String id) {
    super(id);
    this.setHelpDesc("A deploy unit is an entity that defines how many instances of agents will exist in the final system");
    this.setHelpRecom("");
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


  public void setAgentsDeployed(TypedVector tv){
    this.AgentsDeployed=tv;
  }

  public String getAgentsDeployed(){
   return AgentsDeployed.toString();
  }

  public Class getAgentsDeployedType(){
   return AgentsDeployed.getType();
  }
  public void addAgentsDeployed(ingenias.editor.entities.DeploymentUnitByType element){
   this.AgentsDeployed.add(element);
  }

  public void insertAgentsDeployedAt(int pos,ingenias.editor.entities.DeploymentUnitByType element){
   this.AgentsDeployed.insert(element,pos);
  }

  public int containsAgentsDeployed(ingenias.editor.entities.DeploymentUnitByType element){
   return this.AgentsDeployed.indexOf(element);
  }


  public Enumeration getAgentsDeployedElements(){
   return this.AgentsDeployed.elements();
  }

  public void removeAgentsDeployedElement(String id){
    Enumeration enumeration=this.getAgentsDeployedElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.AgentsDeployed.remove(found);
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
