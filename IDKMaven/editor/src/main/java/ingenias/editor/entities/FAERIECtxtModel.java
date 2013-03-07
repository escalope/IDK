
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

public class FAERIECtxtModel extends AMICtxtModel {




  public TypedVector CtxtValues=new TypedVector(ingenias.editor.entities.FAERIECtxtValue.class);

  public TypedVector CtxtElements=new TypedVector(ingenias.editor.entities.FAERIECtxtElement.class);



  public FAERIECtxtModel(String id) {
    super(id);
    this.setHelpDesc("A deploy unit is an entity that defines how many instances of agents will exist in the final system");
    this.setHelpRecom("");
  }




  public void setCtxtValues(TypedVector tv){
    this.CtxtValues=tv;
  }

  public String getCtxtValues(){
   return CtxtValues.toString();
  }

  public Class getCtxtValuesType(){
   return CtxtValues.getType();
  }
  public void addCtxtValues(ingenias.editor.entities.FAERIECtxtValue element){
   this.CtxtValues.add(element);
  }

  public void insertCtxtValuesAt(int pos,ingenias.editor.entities.FAERIECtxtValue element){
   this.CtxtValues.insert(element,pos);
  }

  public int containsCtxtValues(ingenias.editor.entities.FAERIECtxtValue element){
   return this.CtxtValues.indexOf(element);
  }


  public Enumeration getCtxtValuesElements(){
   return this.CtxtValues.elements();
  }

  public void removeCtxtValuesElement(String id){
    Enumeration enumeration=this.getCtxtValuesElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.CtxtValues.remove(found);
  }


  public void setCtxtElements(TypedVector tv){
    this.CtxtElements=tv;
  }

  public String getCtxtElements(){
   return CtxtElements.toString();
  }

  public Class getCtxtElementsType(){
   return CtxtElements.getType();
  }
  public void addCtxtElements(ingenias.editor.entities.FAERIECtxtElement element){
   this.CtxtElements.add(element);
  }

  public void insertCtxtElementsAt(int pos,ingenias.editor.entities.FAERIECtxtElement element){
   this.CtxtElements.insert(element,pos);
  }

  public int containsCtxtElements(ingenias.editor.entities.FAERIECtxtElement element){
   return this.CtxtElements.indexOf(element);
  }


  public Enumeration getCtxtElementsElements(){
   return this.CtxtElements.elements();
  }

  public void removeCtxtElementsElement(String id){
    Enumeration enumeration=this.getCtxtElementsElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.CtxtElements.remove(found);
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
