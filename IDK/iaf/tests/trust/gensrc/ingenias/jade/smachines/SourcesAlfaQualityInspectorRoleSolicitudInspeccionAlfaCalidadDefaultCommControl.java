
/*
    Copyright (C) 2005 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 

    INGENIAS Agent Framework is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS Agent Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS Agent Framework; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/


package ingenias.jade.smachines;
import ingenias.jade.*;
import jade.lang.acl.ACLMessage;
import java.util.*;
import ingenias.jade.mental.*;
import ingenias.editor.entities.*;
import jade.lang.acl.UnreadableException;
import ingenias.jade.comm.DefaultCommControl;
import ingenias.jade.comm.StateBehavior;
import ingenias.jade.comm.LocksWriter;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.exception.NotFound;



  public class SourcesAlfaQualityInspectorRoleSolicitudInspeccionAlfaCalidadDefaultCommControl extends DefaultCommControl{
  
  	  private Vector<String> previous=new Vector<String>();
  	  
  public SourcesAlfaQualityInspectorRoleSolicitudInspeccionAlfaCalidadDefaultCommControl(String cid, MentalStateReader msr, ingenias.jade.comm.LocksRemover lr){
  super(msr, lr);
  };
  
  public static void addDefaultLocks(LocksWriter lw){
  
       // Facts that cannot be removed because they are part of guards
    
    // Facts that cannot be removed because they must be sent
     
     lw.addDeletionLockExpectedType("GradoCalidadFuenteCuarentena");
     
     
    }
    
     public Vector<String> getDefaultLocks(){
      Vector<String> locks=new       Vector<String>();
  
       // Facts that cannot be removed because they are part of guards
    
    // Facts that cannot be removed because they must be sent
     
     locks.add("GradoCalidadFuenteCuarentena");
     
     
    return locks;
    }
    
      

 /**
  * This method is executed on receiving a new message. 
  *
  */
 public boolean notifyNewMessage(Vector<ACLMessage> multipleMessages,String[] options,
                               StateBehavior sb){
    boolean processed = false;
     ACLMessage mes=multipleMessages.firstElement();
   if (options.length>0){
    String sequence=mes.getUserDefinedParameter("sequence");

    if (sequence.equals("enable")){
     try {
       Vector actorlist = (Vector) mes.getContentObject();
       sb.updateActorList(actorlist);
     } catch (Exception e){
       e.printStackTrace();
     }
     sb.addState(options[0]);
     sb.removeState("waiting for enable");
     processed=true;
    }
    if (!processed)
     processed=continueProcess( multipleMessages, options,sb);
   }
   return processed;
 }
 
 public boolean continueProcessSend(String stateToEvaluate,String[] options,
                               StateBehavior sb){

   boolean processed = false;
    Vector<String> futureStates=new Vector<String>();
  
  
   
    if (stateToEvaluate.equals("GradoCalidadCuarentenaListoUI") &&
    	 sb.isState("GradoCalidadCuarentenaListoUI")&& options.length>0) {         
         boolean allexist=true;
         Vector<MentalEntity> mfcontent=null;
         
		 
		 allexist=allexist && !getMSR().obtainConversationalMentalEntityByType(sb.getConversation(),"GradoCalidadFuenteCuarentena").isEmpty();
		   
         if (allexist && true){
           sb.clearContentNextMessage();
           sb.removeState("GradoCalidadCuarentenaListoUI");
           
           
	   	   mfcontent=getMSR().obtainConversationalMentalEntityByType(sb.getConversation(),"GradoCalidadFuenteCuarentena");
	   	   for (MentalEntity me:mfcontent)             
             sb.addContentForNextMessage(me);    
	       getLR().removeDeletionLock(mfcontent);
	       lockProcessed("GradoCalidadFuenteCuarentena");
           //MainInteractionManager.log("Removing lock GradoCalidadFuenteCuarentena",this.getAgent().getLocalName()+"-"+sb.getConversationID());
            
             
           
  		   //sb.clearState();         
	              
		   futureStates.add("endGradoCalidadCuarentenaListoUI");
          
          processed = true;
      	 }
     
    } 
   
   
  if (futureStates.size()>1){
	  previous.addAll(futureStates);
  } else {
	  if (futureStates.size()==1){
	   for (int k=0;k<previous.size();k++){
	 	  sb.removeState(previous.elementAt(k));
	   }
	   previous.clear();
	  }
  }
   
   for (int k=0;k<futureStates.size();k++){
	   sb.addState(futureStates.elementAt(k));
   }

    return processed;
  }


public boolean continueProcess(Vector<ACLMessage> multipleMessages,String[] options,
                               StateBehavior sb){

   boolean processed = false;
    ACLMessage mes=multipleMessages.firstElement();
  // String sequence= sb.getState();
    Vector<String> futureStates=new Vector<String>();
  
   
   
    if (sb.isState("waiting for SolicitudInspeccionAlfaCalidadUI")&& options.length>0 && mes!=null
    && mes.getUserDefinedParameter("sequence")!=null &&
    		mes.getUserDefinedParameter("sequence").equals("SolicitudInspeccionAlfaCalidadUI")){
    	 boolean allexist=true;
         
         if (allexist && true){
     	   sb.removeState("waiting for SolicitudInspeccionAlfaCalidadUI");           
    	   try {
                    Vector toAdd=new Vector();
					for (ACLMessage singleMessage:multipleMessages){
						toAdd.addAll((Vector)singleMessage.getContentObject());						
					}
					sb.updateMentalState(toAdd);	
		   } catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
 		   }

	              
		   futureStates.add("GradoCalidadCuarentenaListoUI");
          
          processed = true;
      	 }      	 
    } 
   
 
   
  if (futureStates.size()>1){
	  previous.addAll(futureStates);
  } else {
	  if (futureStates.size()==1){
	   for (int k=0;k<previous.size();k++){
	 	  sb.removeState(previous.elementAt(k));
	   }
	   previous.clear();
	  }
  }
   
   for (int k=0;k<futureStates.size();k++){
	   sb.addState(futureStates.elementAt(k));
   }

    return processed;
  }


  public boolean notifyMessageSent(String sequence,String[] options, StateBehavior sb){

   boolean processed=false;
   if (options.length>0){

    if (sequence.equals("disabled")){      
         sb.removeState("disabled");
         sb.addState(options[0]);     
      processed = true;
    }
    if (!processed)
     processed=continueProcessSend(sequence, options,sb);
  }
   return processed;
 }
}


