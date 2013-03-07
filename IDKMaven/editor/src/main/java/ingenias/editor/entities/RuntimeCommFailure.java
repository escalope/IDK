
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

public class RuntimeCommFailure extends RuntimeEvent {


  public java.lang.String FailureType;

  public ingenias.editor.entities.RuntimeConversation Conversation;

  public java.lang.String Message;

  public java.lang.String Stage;

  public java.lang.String ConversationID;



  public TypedVector Stack=new TypedVector(ingenias.editor.entities.StackEntry.class);



  public RuntimeCommFailure(String id) {
    super(id);
    this.setHelpDesc("A failure occurring in runtime");
    this.setHelpRecom("");
  }


      public java.lang.String getFailureType(){
        return FailureType;
      }
       public void setFailureType(java.lang.String
					FailureType){
        this.FailureType=FailureType;
      }


      public ingenias.editor.entities.RuntimeConversation getConversation(){
        return Conversation;
      }
       public void setConversation(ingenias.editor.entities.RuntimeConversation
					Conversation){
        this.Conversation=Conversation;
      }


      public java.lang.String getMessage(){
        return Message;
      }
       public void setMessage(java.lang.String
					Message){
        this.Message=Message;
      }


      public java.lang.String getStage(){
        return Stage;
      }
       public void setStage(java.lang.String
					Stage){
        this.Stage=Stage;
      }


      public java.lang.String getConversationID(){
        return ConversationID;
      }
       public void setConversationID(java.lang.String
					ConversationID){
        this.ConversationID=ConversationID;
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



public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("FailureType")!=null && ht.get("FailureType").equals(""))
  this.setFailureType(null);
 else
  if (ht.get("FailureType")!=null)
   this.setFailureType(new java.lang.String(ht.get("FailureType").toString()));

 if (ht.get("Message")!=null && ht.get("Message").equals(""))
  this.setMessage(null);
 else
  if (ht.get("Message")!=null)
   this.setMessage(new java.lang.String(ht.get("Message").toString()));

 if (ht.get("Stage")!=null && ht.get("Stage").equals(""))
  this.setStage(null);
 else
  if (ht.get("Stage")!=null)
   this.setStage(new java.lang.String(ht.get("Stage").toString()));

 if (ht.get("ConversationID")!=null && ht.get("ConversationID").equals(""))
  this.setConversationID(null);
 else
  if (ht.get("ConversationID")!=null)
   this.setConversationID(new java.lang.String(ht.get("ConversationID").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getFailureType() instanceof String)
 if (this.getFailureType()!=null)
 	ht.put("FailureType",this.getFailureType().toString());
 else	
 	ht.put("FailureType","");

//if (this.getMessage() instanceof String)
 if (this.getMessage()!=null)
 	ht.put("Message",this.getMessage().toString());
 else	
 	ht.put("Message","");

//if (this.getStage() instanceof String)
 if (this.getStage()!=null)
 	ht.put("Stage",this.getStage().toString());
 else	
 	ht.put("Stage","");

//if (this.getConversationID() instanceof String)
 if (this.getConversationID()!=null)
 	ht.put("ConversationID",this.getConversationID().toString());
 else	
 	ht.put("ConversationID","");


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
