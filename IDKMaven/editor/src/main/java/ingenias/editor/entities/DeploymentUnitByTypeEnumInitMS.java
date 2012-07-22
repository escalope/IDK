
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

public class DeploymentUnitByTypeEnumInitMS extends DeploymentUnitByType {




  public TypedVector InitialState=new TypedVector(ingenias.editor.entities.MentalInstanceSpecification.class);



  public DeploymentUnitByTypeEnumInitMS(String id) {
    super(id);
    this.setHelpDesc("A deploy unit is an entity that defines how many instances of agents will exist in the final system");
    this.setHelpRecom("");
  }




  public void setInitialState(TypedVector tv){
    this.InitialState=tv;
  }

  public String getInitialState(){
   return InitialState.toString();
  }

  public Class getInitialStateType(){
   return InitialState.getType();
  }
  public void addInitialState(ingenias.editor.entities.MentalInstanceSpecification element){
   this.InitialState.add(element);
  }

  public void insertInitialStateAt(int pos,ingenias.editor.entities.MentalInstanceSpecification element){
   this.InitialState.insert(element,pos);
  }

  public int containsInitialState(ingenias.editor.entities.MentalInstanceSpecification element){
   return this.InitialState.indexOf(element);
  }


  public Enumeration getInitialStateElements(){
   return this.InitialState.elements();
  }

  public void removeInitialStateElement(String id){
    Enumeration enumeration=this.getInitialStateElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.InitialState.remove(found);
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
