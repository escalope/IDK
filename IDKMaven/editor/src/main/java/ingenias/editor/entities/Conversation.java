
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

public class Conversation extends InformationMentalEntity {


  public java.lang.String PlayedRole;

  public java.lang.String State;

  public ingenias.editor.entities.Interaction Interaction;

  public java.lang.String Initiator;

  public java.lang.String ParticipantsSearch;

  public java.lang.String ConversationID;



  public TypedVector Collaborators=new TypedVector(java.lang.Object.class);

  public TypedVector CurrentContent=new TypedVector(ingenias.editor.entities.MentalEntity.class);



  public Conversation(String id) {
    super(id);
    this.setHelpDesc("A conversation is an instance of an interaction with concrete actors");
    this.setHelpRecom("");
  }


      public java.lang.String getPlayedRole(){
        return PlayedRole;
      }
       public void setPlayedRole(java.lang.String
					PlayedRole){
        this.PlayedRole=PlayedRole;
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


      public java.lang.String getInitiator(){
        return Initiator;
      }
       public void setInitiator(java.lang.String
					Initiator){
        this.Initiator=Initiator;
      }


      public java.lang.String getParticipantsSearch(){
        return ParticipantsSearch;
      }
       public void setParticipantsSearch(java.lang.String
					ParticipantsSearch){
        this.ParticipantsSearch=ParticipantsSearch;
      }


      public java.lang.String getConversationID(){
        return ConversationID;
      }
       public void setConversationID(java.lang.String
					ConversationID){
        this.ConversationID=ConversationID;
      }




  public void setCollaborators(TypedVector tv){
    this.Collaborators=tv;
  }

  public String getCollaborators(){
   return Collaborators.toString();
  }

  public Class getCollaboratorsType(){
   return Collaborators.getType();
  }
  public void addCollaborators(java.lang.Object element){
   this.Collaborators.add(element);
  }

  public void insertCollaboratorsAt(int pos,java.lang.Object element){
   this.Collaborators.insert(element,pos);
  }

  public int containsCollaborators(java.lang.Object element){
   return this.Collaborators.indexOf(element);
  }


  public Enumeration getCollaboratorsElements(){
   return this.Collaborators.elements();
  }

  public void removeCollaboratorsElement(String id){
    Enumeration enumeration=this.getCollaboratorsElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.Collaborators.remove(found);
  }


  public void setCurrentContent(TypedVector tv){
    this.CurrentContent=tv;
  }

  public String getCurrentContent(){
   return CurrentContent.toString();
  }

  public Class getCurrentContentType(){
   return CurrentContent.getType();
  }
  public void addCurrentContent(ingenias.editor.entities.MentalEntity element){
   this.CurrentContent.add(element);
  }

  public void insertCurrentContentAt(int pos,ingenias.editor.entities.MentalEntity element){
   this.CurrentContent.insert(element,pos);
  }

  public int containsCurrentContent(ingenias.editor.entities.MentalEntity element){
   return this.CurrentContent.indexOf(element);
  }


  public Enumeration getCurrentContentElements(){
   return this.CurrentContent.elements();
  }

  public void removeCurrentContentElement(String id){
    Enumeration enumeration=this.getCurrentContentElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.CurrentContent.remove(found);
  }



public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("PlayedRole")!=null && ht.get("PlayedRole").equals(""))
  this.setPlayedRole(null);
 else
  if (ht.get("PlayedRole")!=null)
   this.setPlayedRole(new java.lang.String(ht.get("PlayedRole").toString()));

 if (ht.get("State")!=null && ht.get("State").equals(""))
  this.setState(null);
 else
  if (ht.get("State")!=null)
   this.setState(new java.lang.String(ht.get("State").toString()));

 if (ht.get("Initiator")!=null && ht.get("Initiator").equals(""))
  this.setInitiator(null);
 else
  if (ht.get("Initiator")!=null)
   this.setInitiator(new java.lang.String(ht.get("Initiator").toString()));

 if (ht.get("ParticipantsSearch")!=null && ht.get("ParticipantsSearch").equals(""))
  this.setParticipantsSearch(null);
 else
  if (ht.get("ParticipantsSearch")!=null)
   this.setParticipantsSearch(new java.lang.String(ht.get("ParticipantsSearch").toString()));

 if (ht.get("ConversationID")!=null && ht.get("ConversationID").equals(""))
  this.setConversationID(null);
 else
  if (ht.get("ConversationID")!=null)
   this.setConversationID(new java.lang.String(ht.get("ConversationID").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getPlayedRole() instanceof String)
 if (this.getPlayedRole()!=null)
 	ht.put("PlayedRole",this.getPlayedRole().toString());
 else	
 	ht.put("PlayedRole","");

//if (this.getState() instanceof String)
 if (this.getState()!=null)
 	ht.put("State",this.getState().toString());
 else	
 	ht.put("State","");

//if (this.getInitiator() instanceof String)
 if (this.getInitiator()!=null)
 	ht.put("Initiator",this.getInitiator().toString());
 else	
 	ht.put("Initiator","");

//if (this.getParticipantsSearch() instanceof String)
 if (this.getParticipantsSearch()!=null)
 	ht.put("ParticipantsSearch",this.getParticipantsSearch().toString());
 else	
 	ht.put("ParticipantsSearch","");

//if (this.getConversationID() instanceof String)
 if (this.getConversationID()!=null)
 	ht.put("ConversationID",this.getConversationID().toString());
 else	
 	ht.put("ConversationID","");


}

public String toString(){
if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();
}

}
