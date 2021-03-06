
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



  public class BuyerBuy_ticketDefaultCommControl extends DefaultCommControl{
  
  	  private Vector<String> previous=new Vector<String>();
  	  private com.thoughtworks.xstream.XStream xstream=new com.thoughtworks.xstream.XStream(new com.thoughtworks.xstream.io.xml.DomDriver()); 
  	  
  public BuyerBuy_ticketDefaultCommControl(String cid, MentalStateReader msr, ingenias.jade.comm.LocksRemover lr){
  super(msr, lr);
  
  };
  
  public static void addDefaultLocks(LocksWriter lw){
  
       // Facts that cannot be removed because they are part of guards
    
    // Facts that cannot be removed because they must be sent
     
     lw.addDeletionLockExpectedType("Not_interested");
     
    
       // Facts that cannot be removed because they are part of guards
    
    // Facts that cannot be removed because they must be sent
     
     lw.addDeletionLockExpectedType("Transaction_payment");
     
    
       // Facts that cannot be removed because they are part of guards
    
    // Facts that cannot be removed because they must be sent
     
     lw.addDeletionLockExpectedType("RequestedMovie");
     
     
    }
    
     public Vector<String> getDefaultLocks(){
      Vector<String> locks=new       Vector<String>();
  
       // Facts that cannot be removed because they are part of guards
    
    // Facts that cannot be removed because they must be sent
     
     locks.add("Not_interested");
     
    
       // Facts that cannot be removed because they are part of guards
    
    // Facts that cannot be removed because they must be sent
     
     locks.add("Transaction_payment");
     
    
       // Facts that cannot be removed because they are part of guards
    
    // Facts that cannot be removed because they must be sent
     
     locks.add("RequestedMovie");
     
     
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
      	 String content=mes.getContent();    	
    	 Vector actorlist = (Vector) xstream.fromXML(content);
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
  
  
   
    if (stateToEvaluate.equals("I_do_not_want_it") &&
    	 sb.isState("I_do_not_want_it")&& options.length>0) {         
         boolean allexist=true;
         Vector<MentalEntity> mfcontent=null;
         
		 
		 allexist=allexist && !getMSR().obtainConversationalMentalEntityByType(sb.getConversation(),"Not_interested").isEmpty();
		   
         if (allexist && true){
           sb.clearContentNextMessage();
           sb.removeState("I_do_not_want_it");
           
           
	   	   mfcontent=getMSR().obtainConversationalMentalEntityByType(sb.getConversation(),"Not_interested");
	   	   for (MentalEntity me:mfcontent)             
             sb.addContentForNextMessage(me);    
	       getLR().removeDeletionLockType("Not_interested");
	       lockProcessed("Not_interested");
           //MainInteractionManager.log("Removing lock Not_interested",this.getAgent().getLocalName()+"-"+sb.getConversationID());
            
             
           
  		   //sb.clearState();         
	              
		   futureStates.add("endI_do_not_want_it");
          
          processed = true;
      	 }
     
    } 
   
    if (stateToEvaluate.equals("I_want_it") &&
    	 sb.isState("I_want_it")&& options.length>0) {         
         boolean allexist=true;
         Vector<MentalEntity> mfcontent=null;
         
		 
		 allexist=allexist && !getMSR().obtainConversationalMentalEntityByType(sb.getConversation(),"Transaction_payment").isEmpty();
		   
         if (allexist && true){
           sb.clearContentNextMessage();
           sb.removeState("I_want_it");
           
           
	   	   mfcontent=getMSR().obtainConversationalMentalEntityByType(sb.getConversation(),"Transaction_payment");
	   	   for (MentalEntity me:mfcontent)             
             sb.addContentForNextMessage(me);    
	       getLR().removeDeletionLockType("Transaction_payment");
	       lockProcessed("Transaction_payment");
           //MainInteractionManager.log("Removing lock Transaction_payment",this.getAgent().getLocalName()+"-"+sb.getConversationID());
            
             
           
  		   //sb.clearState();         
	              
		   futureStates.add("SellTicket");
          
          processed = true;
      	 }
     
    } 
   
    if (stateToEvaluate.equals("ObtainTicket") &&
    	 sb.isState("ObtainTicket")&& options.length>0) {         
         boolean allexist=true;
         Vector<MentalEntity> mfcontent=null;
         
		 
		 allexist=allexist && !getMSR().obtainConversationalMentalEntityByType(sb.getConversation(),"RequestedMovie").isEmpty();
		   
         if (allexist && true){
           sb.clearContentNextMessage();
           sb.removeState("ObtainTicket");
           
           
	   	   mfcontent=getMSR().obtainConversationalMentalEntityByType(sb.getConversation(),"RequestedMovie");
	   	   for (MentalEntity me:mfcontent)             
             sb.addContentForNextMessage(me);    
	       getLR().removeDeletionLockType("RequestedMovie");
	       lockProcessed("RequestedMovie");
           //MainInteractionManager.log("Removing lock RequestedMovie",this.getAgent().getLocalName()+"-"+sb.getConversationID());
            
             
           
  		   //sb.clearState();         
	              
		   futureStates.add("TicketExists");
                  
		   futureStates.add("TicketRequestRefused");
          
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
  
   
   
    if (sb.isState("waiting for TicketRequestRefused")&& options.length>0 && mes!=null
    && mes.getUserDefinedParameter("sequence")!=null &&
    		mes.getUserDefinedParameter("sequence").equals("TicketRequestRefused")){
    	 boolean allexist=true;
         
         if (allexist && true){
     	   sb.removeState("waiting for TicketRequestRefused");           
    	   //try {
                    Vector toAdd=new Vector();
					for (ACLMessage singleMessage:multipleMessages){
						 String content=singleMessage.getContent();    	 				
    	 				Vector realContent = (Vector) xstream.fromXML(content);
						toAdd.addAll(realContent);						
					}
					sb.updateMentalState(toAdd);	
		   /*} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
 		   }*/

	              
		   futureStates.add("endTicketRequestRefused");
          
          processed = true;
      	 }      	 
    } 
   
    if (sb.isState("waiting for TicketExists")&& options.length>0 && mes!=null
    && mes.getUserDefinedParameter("sequence")!=null &&
    		mes.getUserDefinedParameter("sequence").equals("TicketExists")){
    	 boolean allexist=true;
         
         if (allexist && true){
     	   sb.removeState("waiting for TicketExists");           
    	   //try {
                    Vector toAdd=new Vector();
					for (ACLMessage singleMessage:multipleMessages){
						 String content=singleMessage.getContent();    	 				
    	 				Vector realContent = (Vector) xstream.fromXML(content);
						toAdd.addAll(realContent);						
					}
					sb.updateMentalState(toAdd);	
		   /*} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
 		   }*/

	              
		   futureStates.add("I_do_not_want_it");
                  
		   futureStates.add("I_want_it");
          
          processed = true;
      	 }      	 
    } 
   
    if (sb.isState("waiting for SellTicket")&& options.length>0 && mes!=null
    && mes.getUserDefinedParameter("sequence")!=null &&
    		mes.getUserDefinedParameter("sequence").equals("SellTicket")){
    	 boolean allexist=true;
         
         if (allexist && true){
     	   sb.removeState("waiting for SellTicket");           
    	   //try {
                    Vector toAdd=new Vector();
					for (ACLMessage singleMessage:multipleMessages){
						 String content=singleMessage.getContent();    	 				
    	 				Vector realContent = (Vector) xstream.fromXML(content);
						toAdd.addAll(realContent);						
					}
					sb.updateMentalState(toAdd);	
		   /*} catch (UnreadableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
 		   }*/

	              
		   futureStates.add("endSellTicket");
          
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



