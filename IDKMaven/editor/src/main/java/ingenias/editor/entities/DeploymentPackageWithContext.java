
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

public class DeploymentPackageWithContext extends DeploymentPackage {




  public TypedVector Context=new TypedVector(ingenias.editor.entities.AMIContext.class);

  public TypedVector ContextModelInstantiation=new TypedVector(ingenias.editor.entities.AMIContextInstantiation.class);



  public DeploymentPackageWithContext(String id) {
    super(id);
    this.setHelpDesc("A deploy unit is an entity that defines how many instances of agents will exist in the final system");
    this.setHelpRecom("");
  }




  public void setContext(TypedVector tv){
    this.Context=tv;
  }

  public String getContext(){
   return Context.toString();
  }

  public Class getContextType(){
   return Context.getType();
  }
  public void addContext(ingenias.editor.entities.AMIContext element){
   this.Context.add(element);
  }

  public void insertContextAt(int pos,ingenias.editor.entities.AMIContext element){
   this.Context.insert(element,pos);
  }

  public int containsContext(ingenias.editor.entities.AMIContext element){
   return this.Context.indexOf(element);
  }


  public Enumeration getContextElements(){
   return this.Context.elements();
  }

  public void removeContextElement(String id){
    Enumeration enumeration=this.getContextElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.Context.remove(found);
  }


  public void setContextModelInstantiation(TypedVector tv){
    this.ContextModelInstantiation=tv;
  }

  public String getContextModelInstantiation(){
   return ContextModelInstantiation.toString();
  }

  public Class getContextModelInstantiationType(){
   return ContextModelInstantiation.getType();
  }
  public void addContextModelInstantiation(ingenias.editor.entities.AMIContextInstantiation element){
   this.ContextModelInstantiation.add(element);
  }

  public void insertContextModelInstantiationAt(int pos,ingenias.editor.entities.AMIContextInstantiation element){
   this.ContextModelInstantiation.insert(element,pos);
  }

  public int containsContextModelInstantiation(ingenias.editor.entities.AMIContextInstantiation element){
   return this.ContextModelInstantiation.indexOf(element);
  }


  public Enumeration getContextModelInstantiationElements(){
   return this.ContextModelInstantiation.elements();
  }

  public void removeContextModelInstantiationElement(String id){
    Enumeration enumeration=this.getContextModelInstantiationElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.ContextModelInstantiation.remove(found);
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
