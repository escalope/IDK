
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

public class TestingPackage extends INGENIASObject {


  public ingenias.editor.entities.DeploymentPackage TestingDeployment;



  public TypedVector Tests=new TypedVector(ingenias.editor.entities.Test.class);

  public TypedVector Parameters=new TypedVector(ingenias.editor.entities.Parameter.class);



  public TestingPackage(String id) {
    super(id);
    this.setHelpDesc("A deploy unit is an entity that defines how many instances of agents will exist in the final system");
    this.setHelpRecom("");
  }


      public ingenias.editor.entities.DeploymentPackage getTestingDeployment(){
        return TestingDeployment;
      }
       public void setTestingDeployment(ingenias.editor.entities.DeploymentPackage
					TestingDeployment){
        this.TestingDeployment=TestingDeployment;
      }




  public void setTests(TypedVector tv){
    this.Tests=tv;
  }

  public String getTests(){
   return Tests.toString();
  }

  public Class getTestsType(){
   return Tests.getType();
  }
  public void addTests(ingenias.editor.entities.Test element){
   this.Tests.add(element);
  }

  public void insertTestsAt(int pos,ingenias.editor.entities.Test element){
   this.Tests.insert(element,pos);
  }

  public int containsTests(ingenias.editor.entities.Test element){
   return this.Tests.indexOf(element);
  }


  public Enumeration getTestsElements(){
   return this.Tests.elements();
  }

  public void removeTestsElement(String id){
    Enumeration enumeration=this.getTestsElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.Tests.remove(found);
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
/*if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();*/
return ""+getId();
}

}
