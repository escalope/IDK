
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

public class FrameFact extends Fact {




  public TypedVector Slots=new TypedVector(ingenias.editor.entities.Slot.class);



  public FrameFact(String id) {
    super(id);
    this.setHelpDesc("It is a fact whose informatino is contained in its slots");
    this.setHelpRecom("");
  }




  public void setSlots(TypedVector tv){
    this.Slots=tv;
  }

  public String getSlots(){
   return Slots.toString();
  }

  public Class getSlotsType(){
   return Slots.getType();
  }
  public void addSlots(ingenias.editor.entities.Slot element){
   this.Slots.add(element);
  }

  public void insertSlotsAt(int pos,ingenias.editor.entities.Slot element){
   this.Slots.insert(element,pos);
  }

  public int containsSlots(ingenias.editor.entities.Slot element){
   return this.Slots.indexOf(element);
  }


  public Enumeration getSlotsElements(){
   return this.Slots.elements();
  }

  public void removeSlotsElement(String id){
    Enumeration enumeration=this.getSlotsElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.Slots.remove(found);
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
