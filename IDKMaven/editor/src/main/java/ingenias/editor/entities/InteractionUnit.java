
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

public class InteractionUnit extends INGENIASObject {


  public java.lang.String SpeechAct;

  public java.lang.String Timeout;



  public TypedVector TransferredInfo=new TypedVector(ingenias.editor.entities.MentalEntity.class);



  public InteractionUnit(String id) {
    super(id);
    this.setHelpDesc("Interaction among actors can be described in terms of units of interaction. A unit of interaction can be a message passing, a shared tuple read/write, a remote method invocation, an action over the environment,... This entity serves as<br>    an abstraction of all the existing ways an agent can interact with another. To characterize interaction units, the user can associate an speech act with each interaction.");
    this.setHelpRecom("");
  }


      public java.lang.String getSpeechAct(){
        return SpeechAct;
      }
       public void setSpeechAct(java.lang.String
					SpeechAct){
        this.SpeechAct=SpeechAct;
      }


      public java.lang.String getTimeout(){
        return Timeout;
      }
       public void setTimeout(java.lang.String
					Timeout){
        this.Timeout=Timeout;
      }




  public void setTransferredInfo(TypedVector tv){
    this.TransferredInfo=tv;
  }

  public String getTransferredInfo(){
   return TransferredInfo.toString();
  }

  public Class getTransferredInfoType(){
   return TransferredInfo.getType();
  }
  public void addTransferredInfo(ingenias.editor.entities.MentalEntity element){
   this.TransferredInfo.add(element);
  }

  public void insertTransferredInfoAt(int pos,ingenias.editor.entities.MentalEntity element){
   this.TransferredInfo.insert(element,pos);
  }

  public int containsTransferredInfo(ingenias.editor.entities.MentalEntity element){
   return this.TransferredInfo.indexOf(element);
  }


  public Enumeration getTransferredInfoElements(){
   return this.TransferredInfo.elements();
  }

  public void removeTransferredInfoElement(String id){
    Enumeration enumeration=this.getTransferredInfoElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.TransferredInfo.remove(found);
  }



public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("SpeechAct")!=null && ht.get("SpeechAct").equals(""))
  this.setSpeechAct(null);
 else
  if (ht.get("SpeechAct")!=null)
   this.setSpeechAct(new java.lang.String(ht.get("SpeechAct").toString()));

 if (ht.get("Timeout")!=null && ht.get("Timeout").equals(""))
  this.setTimeout(null);
 else
  if (ht.get("Timeout")!=null)
   this.setTimeout(new java.lang.String(ht.get("Timeout").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getSpeechAct() instanceof String)
 if (this.getSpeechAct()!=null)
 	ht.put("SpeechAct",this.getSpeechAct().toString());
 else	
 	ht.put("SpeechAct","");

//if (this.getTimeout() instanceof String)
 if (this.getTimeout()!=null)
 	ht.put("Timeout",this.getTimeout().toString());
 else	
 	ht.put("Timeout","");


}

public String toString(){
if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();
}

}
