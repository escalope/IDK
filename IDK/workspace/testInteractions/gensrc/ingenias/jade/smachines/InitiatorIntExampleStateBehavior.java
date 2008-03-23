

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

public class InitiatorIntExampleStateBehavior extends StateBehavior{
private MentalStateReader msr=null;
  public InitiatorIntExampleStateBehavior( String agentName,
   			MentalStateReader msr,
   			MentalStateUpdater msu,
  			RuntimeConversation conv, String playedRole, 
			DFAgentDescription[] actors, 
			//DFAgentDescription[] playedRoles,
			DefaultCommControl dcc, 
			String protocol){
    super(conv, playedRole,msu,actors,dcc,protocol,agentName);
    this.msr=msr;
    try {
			if (IAFProperties.getGraphicsOn()){
			    
			    
			      // States involved into interaction intiator    
			      smf.add("disabled", "Multiple_deliver");
			    
			    
			      // Receiving a message    
			      smf.add("Simple_answer", "waiting for Simple_answer");
			      
			      // Next states after receiving "Simple_answer"
			      
			       smf.add("waiting for Simple_answer","endSimple_answer");
			      
			    
			      // Receiving a message    
			      smf.add("Multiple_retrieval", "waiting for Multiple_retrieval");
			      
			      // Next states after receiving "Multiple_retrieval"
			      
			       smf.add("waiting for Multiple_retrieval","Simple_deliver");
			      
			    
			    
			     // States involved in message deliver
			     
			      smf.add("Simple_deliver", "Simple_answer");
			      
			    
			     // States involved in message deliver
			     
			      smf.add("Multiple_deliver", "Multiple_retrieval");
			      
			    
			
			    this.updateStates(agentName);
			}
    } catch (ingenias.exception.CycleInProtocol cip){
      cip.printStackTrace();
    }
    
    this.addState("disabled");

  }

  public synchronized void action() {
    boolean cond1 = true;
    boolean cond2 = true;

    

    
    if (this.isState("disabled")){
  	try {  	  
      String[] options=new String[]{"Multiple_deliver"};
      AID[] actors=null;
      Vector<AID> actorsv=new Vector<AID>();
      Vector<String> rolesv=new Vector<String>();
      
      actorsv.addAll(this.getActor("Participant1"));
      {      
       Vector<AID> receivers=this.getActor("Participant1");      
       for (AID aid:receivers){
        rolesv.add("Participant1");
       }
      }

      
      actorsv.addAll(this.getActor("Participant2"));
      {      
       Vector<AID> receivers=this.getActor("Participant2");      
       for (AID aid:receivers){
        rolesv.add("Participant2");
       }
      }

      
      actors=new AID[actorsv.size()];
      actorsv.toArray(actors);
      CommActCreator.generateSObject((JADEAgent)myAgent,rolesv,actors,this.getConversationID(),
       "enable","IntExample",this.getAgentExternalDescription());
      this.getDCC().notifyMessageSent("disabled",options,this);
      this.setRunning();   
      } catch (NoAgentsFound e) {
      e.printStackTrace();
  	}
    }
    


  
  
  // Receives a message and a synchronization command
  if (this.isState("Simple_answer") ) {  // State changed by other agent and upted
    //in the parent class
         
      Vector options=new Vector();
      
      options.add("endSimple_answer");
      
      String[] optionsA=new String[options.size()];
      options.toArray(optionsA);
      boolean allexist=true;
      
      int cardinality=1;
      if ("1".equals("n")){
         try{
          Vector<AID> receivers=this.getActor("Participant1");    
          cardinality=receivers.size();          
          } catch (NoAgentsFound ex) {
                    ex.printStackTrace();
                }   
       
      }
      
      if (allexist && true){     
		 CommActCreator.generateR((JADEAgent)myAgent,
                        this.getConversationID(),"Simple_answer",
                   "IntExample",optionsA,this,cardinality,0);
      }	 
      this.removeState("Simple_answer");
      this.addState("waiting for Simple_answer");
  } 
  
  // Receives a message and a synchronization command
  if (this.isState("Multiple_retrieval") ) {  // State changed by other agent and upted
    //in the parent class
         
      Vector options=new Vector();
      
      options.add("Simple_deliver");
      
      String[] optionsA=new String[options.size()];
      options.toArray(optionsA);
      boolean allexist=true;
      
      int cardinality=1;
      if ("n".equals("n")){
         try{
          Vector<AID> receivers=this.getActor("Participant2");    
          cardinality=receivers.size();          
          } catch (NoAgentsFound ex) {
                    ex.printStackTrace();
                }   
       
      }
      
      if (allexist && true){     
		 CommActCreator.generateR((JADEAgent)myAgent,
                        this.getConversationID(),"Multiple_retrieval",
                   "IntExample",optionsA,this,cardinality,1000);
      }	 
      this.removeState("Multiple_retrieval");
      this.addState("waiting for Multiple_retrieval");
  } 
  
  
  
  
  // Sends a message and synchronization commands
  if (this.isState("Simple_deliver")) {
     
     try {
      AID[] actors=null;
      Vector actorsv=new Vector();
      Vector<String> rolesv=new Vector<String>();
      
      {      
       Vector<AID> receivers=this.getActor("Participant1");      
       actorsv.addAll(receivers);
       for (AID aid:receivers){
        rolesv.add("Participant1");
       }
      }
      
      actors=new AID[actorsv.size()];
      actorsv.toArray(actors);
      Vector options=new Vector();
      
      options.add("Simple_answer");
      
      String[] optionsA=new String[options.size()];
      options.toArray(optionsA);
      if (this.getDCC().notifyMessageSent("Simple_deliver",optionsA,this)){
           //If mental state conditions are met, the message is send and state changed
            CommActCreator.generateSObject((JADEAgent)myAgent,rolesv,actors,this.getConversationID(),
           "Simple_deliver","IntExample",this.getContentForNextMessage());
           getTimeout().stop();
      } else {
    	  if (getTimeout().isStarted() && getTimeout().isFinished()){
    	    		 this.abortDueTimeout();   	        
    	  } else  {
    		  if (!getTimeout().isStarted())
    		  getTimeout().start(2000);
    	  }
      }

      } catch (NoAgentsFound e) {
      e.printStackTrace();
  	}
  } 
  
  // Sends a message and synchronization commands
  if (this.isState("Multiple_deliver")) {
     
     try {
      AID[] actors=null;
      Vector actorsv=new Vector();
      Vector<String> rolesv=new Vector<String>();
      
      {      
       Vector<AID> receivers=this.getActor("Participant2");      
       actorsv.addAll(receivers);
       for (AID aid:receivers){
        rolesv.add("Participant2");
       }
      }
      
      actors=new AID[actorsv.size()];
      actorsv.toArray(actors);
      Vector options=new Vector();
      
      options.add("Multiple_retrieval");
      
      String[] optionsA=new String[options.size()];
      options.toArray(optionsA);
      if (this.getDCC().notifyMessageSent("Multiple_deliver",optionsA,this)){
           //If mental state conditions are met, the message is send and state changed
            CommActCreator.generateSObject((JADEAgent)myAgent,rolesv,actors,this.getConversationID(),
           "Multiple_deliver","IntExample",this.getContentForNextMessage());
           getTimeout().stop();
      } else {
    	  if (getTimeout().isStarted() && getTimeout().isFinished()){
    	    		 this.abortDueTimeout();   	        
    	  } else  {
    		  if (!getTimeout().isStarted())
    		  getTimeout().start(0);
    	  }
      }

      } catch (NoAgentsFound e) {
      e.printStackTrace();
  	}
  } 
  
   

  
  // Finishes this state machine
  if (this.isState("endSimple_answer")) {
    this.setFinished(); // End of transitions
    this.getDCC().removeDefaultLocks();
  }
  
  
  if (this.isState("ABORTED")) {	    
	    this.getDCC().removeDefaultLocks();
  }

}


}

