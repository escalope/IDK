

/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz over original code from Ruben Fuentes
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

public class PConnects extends NAryEdgeEntity {




  public TypedVector Inputs=new TypedVector(ingenias.editor.entities.MentalEntity.class);


  public PConnects(String id) {
    super(id);
    ModelEntity em=null;
  }





  public void setInputs(TypedVector tv){
    this.Inputs=tv;
  }

  public String getInputs(){
   return Inputs.toString();
  }

  public Class getInputsType(){
   return Inputs.getType();
  }
  public void addInputs(ingenias.editor.entities.MentalEntity element){
   this.Inputs.add(element);
  }

  public void insertInputsAt(int pos,ingenias.editor.entities.MentalEntity element){
   this.Inputs.insert(element,pos);
  }

  public int containsInputs(ingenias.editor.entities.MentalEntity element){
   return this.Inputs.indexOf(element);
  }


  public Enumeration getInputsElements(){
   return this.Inputs.elements();
  }

  public void removeInputsElement(String id){
    Enumeration enumeration=this.getInputsElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.Inputs.remove(found);
  }



  


public void fromMap(Map ht){
super.fromMap(ht);



}
public void toMap(Map ht){
super.toMap(ht);


}


public String toString(){
 return getId()+":"+getType();
}



}
