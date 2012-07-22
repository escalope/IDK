
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

public class RuntimeConversation extends Conversation {


  public java.lang.String State;

  public ingenias.editor.entities.Interaction Interaction;

  public java.lang.Integer AbortCode;

  public ingenias.editor.entities.RuntimeConversation ParentConversation;



  public TypedVector Stack=new TypedVector(ingenias.editor.entities.StackEntry.class);

  public TypedVector ChildConversation=new TypedVector(ingenias.editor.entities.RuntimeConversation.class);



  public RuntimeConversation(String id) {
    super(id);
    this.setHelpDesc("A conversation is an instance of an interaction with concrete actors");
    this.setHelpRecom("");
  }


      public java.lang.String getState(){
        return State;
      }
       public void setState(java.lang.String
					State){
        this.State=State;
      }


      public ingenias.editor.entities.Interaction getInteraction(){
        return Interaction;
      }
       public void setInteraction(ingenias.editor.entities.Interaction
					Interaction){
        this.Interaction=Interaction;
      }


      public java.lang.Integer getAbortCode(){
        return AbortCode;
      }
       public void setAbortCode(java.lang.Integer
					AbortCode){
        this.AbortCode=AbortCode;
      }


      public ingenias.editor.entities.RuntimeConversation getParentConversation(){
        return ParentConversation;
      }
       public void setParentConversation(ingenias.editor.entities.RuntimeConversation
					ParentConversation){
        this.ParentConversation=ParentConversation;
      }




  public void setStack(TypedVector tv){
    this.Stack=tv;
  }

  public String getStack(){
   return Stack.toString();
  }

  public Class getStackType(){
   return Stack.getType();
  }
  public void addStack(ingenias.editor.entities.StackEntry element){
   this.Stack.add(element);
  }

  public void insertStackAt(int pos,ingenias.editor.entities.StackEntry element){
   this.Stack.insert(element,pos);
  }

  public int containsStack(ingenias.editor.entities.StackEntry element){
   return this.Stack.indexOf(element);
  }


  public Enumeration getStackElements(){
   return this.Stack.elements();
  }

  public void removeStackElement(String id){
    Enumeration enumeration=this.getStackElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.Stack.remove(found);
  }


  public void setChildConversation(TypedVector tv){
    this.ChildConversation=tv;
  }

  public String getChildConversation(){
   return ChildConversation.toString();
  }

  public Class getChildConversationType(){
   return ChildConversation.getType();
  }
  public void addChildConversation(ingenias.editor.entities.RuntimeConversation element){
   this.ChildConversation.add(element);
  }

  public void insertChildConversationAt(int pos,ingenias.editor.entities.RuntimeConversation element){
   this.ChildConversation.insert(element,pos);
  }

  public int containsChildConversation(ingenias.editor.entities.RuntimeConversation element){
   return this.ChildConversation.indexOf(element);
  }


  public Enumeration getChildConversationElements(){
   return this.ChildConversation.elements();
  }

  public void removeChildConversationElement(String id){
    Enumeration enumeration=this.getChildConversationElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.ChildConversation.remove(found);
  }



public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("State")!=null && ht.get("State").equals(""))
  this.setState(null);
 else
  if (ht.get("State")!=null)
   this.setState(new java.lang.String(ht.get("State").toString()));

 if (ht.get("AbortCode")!=null && ht.get("AbortCode").equals(""))
  this.setAbortCode(null);
 else
  if (ht.get("AbortCode")!=null)
   this.setAbortCode(new java.lang.Integer(ht.get("AbortCode").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getState() instanceof String)
 if (this.getState()!=null)
 	ht.put("State",this.getState().toString());
 else	
 	ht.put("State","");

//if (this.getAbortCode() instanceof String)
 if (this.getAbortCode()!=null)
 	ht.put("AbortCode",this.getAbortCode().toString());
 else	
 	ht.put("AbortCode","");


}

public String toString(){
if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();
}

}
