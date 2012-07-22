
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

public class INGENIASComponent extends UMLComponent {


  public java.lang.String Language;



  public TypedVector Files=new TypedVector(ingenias.editor.entities.FileSpecMapping.class);



  public INGENIASComponent(String id) {
    super(id);
    this.setHelpDesc("(UML Superstructure Specification, v2.0) It is a modular unit with well-defined interfaces that is replaceable within its environment.An important aspect of component-based development is the reuse of previously constructed components. A<br>    component can always be considered an autonomous unit within a system or subsystem. It has one or more provided and/or required interfaces (potentially exposed via ports), and its internals are hidden and inaccessible other than as provided by its<br>    interfaces. Although it may be dependent on other elements in terms of interfaces that are required, a component is encapsulated and its dependencies are designed such that it can be treated as independently as possible. As a result, components and<br>    subsystems can be flexibly reused and replaced by connecting (wiring) them together via their provided and required interfaces. The aspects of autonomy and reuse also extend to components at deployment time. The artifacts that implement component are<br>    intended to be capable of being deployed and re-deployed independently, for instance to update an existing system.");
    this.setHelpRecom("");
  }


      public java.lang.String getLanguage(){
        return Language;
      }
       public void setLanguage(java.lang.String
					Language){
        this.Language=Language;
      }




  public void setFiles(TypedVector tv){
    this.Files=tv;
  }

  public String getFiles(){
   return Files.toString();
  }

  public Class getFilesType(){
   return Files.getType();
  }
  public void addFiles(ingenias.editor.entities.FileSpecMapping element){
   this.Files.add(element);
  }

  public void insertFilesAt(int pos,ingenias.editor.entities.FileSpecMapping element){
   this.Files.insert(element,pos);
  }

  public int containsFiles(ingenias.editor.entities.FileSpecMapping element){
   return this.Files.indexOf(element);
  }


  public Enumeration getFilesElements(){
   return this.Files.elements();
  }

  public void removeFilesElement(String id){
    Enumeration enumeration=this.getFilesElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.Files.remove(found);
  }



public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Language")!=null && ht.get("Language").equals(""))
  this.setLanguage(null);
 else
  if (ht.get("Language")!=null)
   this.setLanguage(new java.lang.String(ht.get("Language").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getLanguage() instanceof String)
 if (this.getLanguage()!=null)
 	ht.put("Language",this.getLanguage().toString());
 else	
 	ht.put("Language","");


}

public String toString(){
if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();
}

}
