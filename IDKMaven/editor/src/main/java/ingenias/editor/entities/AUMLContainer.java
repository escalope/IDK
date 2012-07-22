
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

public class AUMLContainer extends AUMLComponent {




  public TypedVector Children=new TypedVector(ingenias.editor.entities.AUMLComponent.class);



  public AUMLContainer(String id) {
    super(id);
    this.setHelpDesc("");
    this.setHelpRecom("");
  }




  public void setChildren(TypedVector tv){
    this.Children=tv;
  }

  public String getChildren(){
   return Children.toString();
  }

  public Class getChildrenType(){
   return Children.getType();
  }
  public void addChildren(ingenias.editor.entities.AUMLComponent element){
   this.Children.add(element);
  }

  public void insertChildrenAt(int pos,ingenias.editor.entities.AUMLComponent element){
   this.Children.insert(element,pos);
  }

  public int containsChildren(ingenias.editor.entities.AUMLComponent element){
   return this.Children.indexOf(element);
  }


  public Enumeration getChildrenElements(){
   return this.Children.elements();
  }

  public void removeChildrenElement(String id){
    Enumeration enumeration=this.getChildrenElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.Children.remove(found);
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
