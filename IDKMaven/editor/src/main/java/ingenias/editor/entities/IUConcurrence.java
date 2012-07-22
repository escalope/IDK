
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

public class IUConcurrence extends InteractionUnit {




  public TypedVector InteractionUnits=new TypedVector(ingenias.editor.entities.InteractionUnit.class);



  public IUConcurrence(String id) {
    super(id);
    this.setHelpDesc("It is a container of interaction units. It represents a non-deterministic execution order of a set of interaction units.");
    this.setHelpRecom("");
  }




  public void setInteractionUnits(TypedVector tv){
    this.InteractionUnits=tv;
  }

  public String getInteractionUnits(){
   return InteractionUnits.toString();
  }

  public Class getInteractionUnitsType(){
   return InteractionUnits.getType();
  }
  public void addInteractionUnits(ingenias.editor.entities.InteractionUnit element){
   this.InteractionUnits.add(element);
  }

  public void insertInteractionUnitsAt(int pos,ingenias.editor.entities.InteractionUnit element){
   this.InteractionUnits.insert(element,pos);
  }

  public int containsInteractionUnits(ingenias.editor.entities.InteractionUnit element){
   return this.InteractionUnits.indexOf(element);
  }


  public Enumeration getInteractionUnitsElements(){
   return this.InteractionUnits.elements();
  }

  public void removeInteractionUnitsElement(String id){
    Enumeration enumeration=this.getInteractionUnitsElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.InteractionUnits.remove(found);
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