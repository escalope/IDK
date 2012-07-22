
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

public class Method extends INGENIASObject {


  public java.lang.String Result;

  public java.lang.String Name;



  public TypedVector Parameter=new TypedVector(ingenias.editor.entities.MethodParameter.class);



  public Method(String id) {
    super(id);
    this.setHelpDesc("A conventional object method");
    this.setHelpRecom("");
  }


      public java.lang.String getResult(){
        return Result;
      }
       public void setResult(java.lang.String
					Result){
        this.Result=Result;
      }


      public java.lang.String getName(){
        return Name;
      }
       public void setName(java.lang.String
					Name){
        this.Name=Name;
      }




  public void setParameter(TypedVector tv){
    this.Parameter=tv;
  }

  public String getParameter(){
   return Parameter.toString();
  }

  public Class getParameterType(){
   return Parameter.getType();
  }
  public void addParameter(ingenias.editor.entities.MethodParameter element){
   this.Parameter.add(element);
  }

  public void insertParameterAt(int pos,ingenias.editor.entities.MethodParameter element){
   this.Parameter.insert(element,pos);
  }

  public int containsParameter(ingenias.editor.entities.MethodParameter element){
   return this.Parameter.indexOf(element);
  }


  public Enumeration getParameterElements(){
   return this.Parameter.elements();
  }

  public void removeParameterElement(String id){
    Enumeration enumeration=this.getParameterElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.Parameter.remove(found);
  }



public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Result")!=null && ht.get("Result").equals(""))
  this.setResult(null);
 else
  if (ht.get("Result")!=null)
   this.setResult(new java.lang.String(ht.get("Result").toString()));

 if (ht.get("Name")!=null && ht.get("Name").equals(""))
  this.setName(null);
 else
  if (ht.get("Name")!=null)
   this.setName(new java.lang.String(ht.get("Name").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getResult() instanceof String)
 if (this.getResult()!=null)
 	ht.put("Result",this.getResult().toString());
 else	
 	ht.put("Result","");

//if (this.getName() instanceof String)
 if (this.getName()!=null)
 	ht.put("Name",this.getName().toString());
 else	
 	ht.put("Name","");


}

public String toString(){
if (this.getName()==null ||
    this.getName().toString().equals(""))
 return "Please, define the value of field Name";
else
 return this.getName().toString();
}

}
