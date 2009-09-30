

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

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.core.behaviours.*;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

import jade.core.*;
import jade.core.behaviours.*;

import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.domain.DFService;
 import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import java.util.*;
import ingenias.jade.*;
import ingenias.editor.entities.*;
import ingenias.jade.comm.DefaultCommControl;
import ingenias.jade.comm.StateBehavior;
import ingenias.jade.comm.CommActCreator;
import ingenias.jade.exception.NoAgentsFound;

public class BuyerAsking_for_a_ticketStateBehavior extends StateBehavior{
private MentalStateReader msr=null;
  public BuyerAsking_for_a_ticketStateBehavior( String agentName,
   			MentalStateReader msr,
   			MentalStateUpdater msu,
  			RuntimeConversation conv, String playedRole, 
			AgentExternalDescription[] actors, 
			//DFAgentDescription[] playedRoles,
			DefaultCommControl dcc, 
			String protocol){
    super(conv, playedRole,msu,actors,dcc,protocol,agentName);
    this.msr=msr;
    try {
			if (IAFProperties.getGraphicsOn()){
			    
			      // States involved into colaborator initialization
			      smf.add("disabled", "waiting for enable");
			      smf.add("waiting for enable","RequestOrder");
			    
			    
			    
			      // Receiving a message    
			      smf.add("RequestOrder", "waiting for RequestOrder");
			      
			      // Next states after receiving "RequestOrder"
			      
			       smf.add("waiting for RequestOrder","GiveTicket");
			      
			       smf.add("waiting for RequestOrder","ImpossibleToGiveTicket");
			      
			    
			    
			     // States involved in message deliver
			     
			      smf.add("ImpossibleToGiveTicket", "endImpossibleToGiveTicket");
			      
			    
			     // States involved in message deliver
			     
			      smf.add("GiveTicket", "endGiveTicket");
			      
			    
			
			    this.updateStates(agentName);
			}
    } catch (ingenias.exception.CycleInProtocol cip){
      cip.printStackTrace();
    }
    
    this.addState("disabled");

  }

  private boolean additionalRound=false;
  public synchronized void action() {
    boolean cond1 = true;
    boolean cond2 = true;
    additionalRound=false;
    
    String initialStateToCompareAtTheEnd=this.getStates();

    
    if (this.isState("disabled")){     
     String[] options=new String[]{"RequestOrder"};
     CommActCreator.generateRWithoutCID((JADEAgent)myAgent,
      "Asking_for_a_ticket","enable",this.getPlayedRole(),options,this);
      this.removeState("disabled");
      
     this.addState("waiting for enable");
     
     this.setRunning();   
      this.notifyStateTransitionExecuted("disabled",options[0]);
    }
    

    


  
  
  // Receives a message and a synchronization command
  if (this.isState("RequestOrder") ) {  // State changed by other agent and upted
    //in the parent class
         
      Vector options=new Vector();
      
      options.add("GiveTicket");
      
      options.add("ImpossibleToGiveTicket");
      
      String[] optionsA=new String[options.size()];
      options.toArray(optionsA);
      boolean allexist=true;
      
      int cardinality=1;
      if ("1".equals("n")){
         try{
          Vector<AID> receivers=this.getActor("User_assistant");    
          cardinality=receivers.size();          
          } catch (NoAgentsFound ex) {
                    ex.printStackTrace();
                }   
       
      }
      
      if (allexist && true){     
		 addCreatedBehaviors(CommActCreator.generateR((JADEAgent)myAgent,
                        this.getConversationID(),"RequestOrder",
                   "Asking_for_a_ticket",this.getPlayedRole(),
                   optionsA,this,cardinality,0));
      }	 
      this.removeState("RequestOrder");
      this.addState("waiting for RequestOrder");
      this.notifyStateTransitionExecuted("RequestOrder", "waiting for RequestOrder");
  } 
  
  
  
  
  // Sends a message and synchronization commands
  if (this.isState("ImpossibleToGiveTicket")) {
     
     try {
      AID[] actors=null;
      Vector actorsv=new Vector();
      Vector<String> rolesv=new Vector<String>();
      
      {      
       Vector<AID> receivers=this.getActor("User_assistant");      
       actorsv.addAll(receivers);
       for (AID aid:receivers){
        rolesv.add("User_assistant");
       }
      }
      
      actors=new AID[actorsv.size()];
      actorsv.toArray(actors);
      Vector options=new Vector();      
      
      options.add("endImpossibleToGiveTicket");      
      
      String[] optionsA=new String[options.size()];
      options.toArray(optionsA);
      if (this.getDCC().notifyMessageSent("ImpossibleToGiveTicket",optionsA,this)){
           //If mental state conditions are met, the message is send and state changed
            CommActCreator.generateSObject((JADEAgent)myAgent,rolesv,actors,this.getConversationID(),
           "ImpossibleToGiveTicket","Asking_for_a_ticket",this.getContentForNextMessage());
           getTimeout().stop();
            this.notifyStateTransitionExecuted("ImpossibleToGiveTicket", options.firstElement().toString());
      } else {
    	  if (getTimeout().isStarted() && getTimeout().isFinished()){
    	    		 this.abortDueTimeout();   	        
    	    		  this.notifyStateTransitionExecuted("ImpossibleToGiveTicket", "ABORTED");
    	  } else  {
    		  if (!getTimeout().isStarted())
    		  getTimeout().start(0);
    	  }
      }
      additionalRound=true; // To enable a reevaluation of the state since this is a cyclicbehavior

      } catch (NoAgentsFound e) {
      e.printStackTrace();
  	}
  } 
  
  // Sends a message and synchronization commands
  if (this.isState("GiveTicket")) {
     
     try {
      AID[] actors=null;
      Vector actorsv=new Vector();
      Vector<String> rolesv=new Vector<String>();
      
      {      
       Vector<AID> receivers=this.getActor("User_assistant");      
       actorsv.addAll(receivers);
       for (AID aid:receivers){
        rolesv.add("User_assistant");
       }
      }
      
      actors=new AID[actorsv.size()];
      actorsv.toArray(actors);
      Vector options=new Vector();      
      
      options.add("endGiveTicket");      
      
      String[] optionsA=new String[options.size()];
      options.toArray(optionsA);
      if (this.getDCC().notifyMessageSent("GiveTicket",optionsA,this)){
           //If mental state conditions are met, the message is send and state changed
            CommActCreator.generateSObject((JADEAgent)myAgent,rolesv,actors,this.getConversationID(),
           "GiveTicket","Asking_for_a_ticket",this.getContentForNextMessage());
           getTimeout().stop();
            this.notifyStateTransitionExecuted("GiveTicket", options.firstElement().toString());
      } else {
    	  if (getTimeout().isStarted() && getTimeout().isFinished()){
    	    		 this.abortDueTimeout();   	        
    	    		  this.notifyStateTransitionExecuted("GiveTicket", "ABORTED");
    	  } else  {
    		  if (!getTimeout().isStarted())
    		  getTimeout().start(0);
    	  }
      }
      additionalRound=true; // To enable a reevaluation of the state since this is a cyclicbehavior

      } catch (NoAgentsFound e) {
      e.printStackTrace();
  	}
  } 
  
   

  
  // Finishes this state machine
  if (this.isState("endImpossibleToGiveTicket")) {
    this.setFinished(); // End of transitions
    this.notifyProtocolFinished();
    this.getDCC().removeDefaultLocks();
  }
  
  // Finishes this state machine
  if (this.isState("endGiveTicket")) {
    this.setFinished(); // End of transitions
    this.notifyProtocolFinished();
    this.getDCC().removeDefaultLocks();
  }
  
  
  if (this.isState("ABORTED")) {	    
	    this.getDCC().removeDefaultLocks();
  }
  
  if (initialStateToCompareAtTheEnd.equals(this.getStates()))
   if (additionalRound)
   	this.block(100); // To start a new round in 100 millis
   else
    this.block(); // Else wait for some external event

}


}

