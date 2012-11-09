
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

public class AMIContext extends EnvironmentApplication {




  public TypedVector Models=new TypedVector(ingenias.editor.entities.AMICtxtModel.class);



  public AMIContext(String id) {
    super(id);
    this.setHelpDesc("A deploy unit is an entity that defines how many instances of agents will exist in the final system");
    this.setHelpRecom("");
  }




  public void setModels(TypedVector tv){
    this.Models=tv;
  }

  public String getModels(){
   return Models.toString();
  }

  public Class getModelsType(){
   return Models.getType();
  }
  public void addModels(ingenias.editor.entities.AMICtxtModel element){
   this.Models.add(element);
  }

  public void insertModelsAt(int pos,ingenias.editor.entities.AMICtxtModel element){
   this.Models.insert(element,pos);
  }

  public int containsModels(ingenias.editor.entities.AMICtxtModel element){
   return this.Models.indexOf(element);
  }


  public Enumeration getModelsElements(){
   return this.Models.elements();
  }

  public void removeModelsElement(String id){
    Enumeration enumeration=this.getModelsElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.Models.remove(found);
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
