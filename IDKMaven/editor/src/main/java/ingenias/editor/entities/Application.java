
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

public class Application extends AgentComponent {




  public TypedVector Methods=new TypedVector(ingenias.editor.entities.Method.class);



  public Application(String id) {
    super(id);
    this.setHelpDesc("An application is wraper to computational system entities. By 'computational', we mean 'having an interface and a concrete behavior'.");
    this.setHelpRecom("");
  }




  public void setMethods(TypedVector tv){
    this.Methods=tv;
  }

  public String getMethods(){
   return Methods.toString();
  }

  public Class getMethodsType(){
   return Methods.getType();
  }
  public void addMethods(ingenias.editor.entities.Method element){
   this.Methods.add(element);
  }

  public void insertMethodsAt(int pos,ingenias.editor.entities.Method element){
   this.Methods.insert(element,pos);
  }

  public int containsMethods(ingenias.editor.entities.Method element){
   return this.Methods.indexOf(element);
  }


  public Enumeration getMethodsElements(){
   return this.Methods.elements();
  }

  public void removeMethodsElement(String id){
    Enumeration enumeration=this.getMethodsElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.Methods.remove(found);
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