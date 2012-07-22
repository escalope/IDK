
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

public class BoxedTask extends Task {




  public TypedVector Inputs=new TypedVector(ingenias.editor.entities.MentalEntityInstanceAccess.class);

  public TypedVector Outputs=new TypedVector(ingenias.editor.entities.MentalEntityInstanceCreation.class);



  public BoxedTask(String id) {
    super(id);
    this.setHelpDesc("");
    this.setHelpRecom("");
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
  public void addInputs(ingenias.editor.entities.MentalEntityInstanceAccess element){
   this.Inputs.add(element);
  }

  public void insertInputsAt(int pos,ingenias.editor.entities.MentalEntityInstanceAccess element){
   this.Inputs.insert(element,pos);
  }

  public int containsInputs(ingenias.editor.entities.MentalEntityInstanceAccess element){
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


  public void setOutputs(TypedVector tv){
    this.Outputs=tv;
  }

  public String getOutputs(){
   return Outputs.toString();
  }

  public Class getOutputsType(){
   return Outputs.getType();
  }
  public void addOutputs(ingenias.editor.entities.MentalEntityInstanceCreation element){
   this.Outputs.add(element);
  }

  public void insertOutputsAt(int pos,ingenias.editor.entities.MentalEntityInstanceCreation element){
   this.Outputs.insert(element,pos);
  }

  public int containsOutputs(ingenias.editor.entities.MentalEntityInstanceCreation element){
   return this.Outputs.indexOf(element);
  }


  public Enumeration getOutputsElements(){
   return this.Outputs.elements();
  }

  public void removeOutputsElement(String id){
    Enumeration enumeration=this.getOutputsElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.Outputs.remove(found);
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
