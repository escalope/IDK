
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

public class AgentModelBelieve extends Believe {




  public TypedVector Believed=new TypedVector(ingenias.editor.entities.AgentModelModelEntity.class);



  public AgentModelBelieve(String id) {
    super(id);
    this.setHelpDesc("A believe expressed with an agent model. This entity is an encapsulation for agent models that express complex requirements");
    this.setHelpRecom("");
  }




  public void setBelieved(TypedVector tv){
    this.Believed=tv;
  }

  public String getBelieved(){
   return Believed.toString();
  }

  public Class getBelievedType(){
   return Believed.getType();
  }
  public void addBelieved(AgentModelModelEntity element){
   this.Believed.add(element);
  }

  public void insertBelievedAt(int pos,AgentModelModelEntity element){
   this.Believed.insert(element,pos);
  }

  public int containsBelieved(AgentModelModelEntity element){
   return this.Believed.indexOf(element);
  }


  public Enumeration getBelievedElements(){
   return this.Believed.elements();
  }

  public void removeBelievedElement(String id){
    Enumeration enumeration=this.getBelievedElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.Believed.remove(found);
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
