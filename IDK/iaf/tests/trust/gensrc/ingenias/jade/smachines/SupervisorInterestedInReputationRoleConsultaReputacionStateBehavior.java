

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

public class SupervisorInterestedInReputationRoleConsultaReputacionStateBehavior extends StateBehavior{
private MentalStateReader msr=null;
  public SupervisorInterestedInReputationRoleConsultaReputacionStateBehavior( String agentName,
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
			    
			    
			      // States involved into interaction intiator    
			      smf.add("disabled", "ConsultaReputacionUI");
			    
			    
			      // Receiving a message    
			      smf.add("AgenteDesconocidoUI", "waiting for AgenteDesconocidoUI");
			      
			      // Next states after receiving "AgenteDesconocidoUI"
			      
			       smf.add("waiting for AgenteDesconocidoUI","endAgenteDesconocidoUI");
			      
			    
			      // Receiving a message    
			      smf.add("RespuetaReputacion", "waiting for RespuetaReputacion");
			      
			      // Next states after receiving "RespuetaReputacion"
			      
			       smf.add("waiting for RespuetaReputacion","endRespuetaReputacion");
			      
			    
			    
			     // States involved in message deliver
			     
			      smf.add("ConsultaReputacionUI", "AgenteDesconocidoUI");
			      
			      smf.add("ConsultaReputacionUI", "RespuetaReputacion");
			      
			    
			
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
    
    String initialStateToCompareAtTheEnd=this.getStates();

    

    
    if (this.isState("disabled")){
  	try {  	  
      String[] options=new String[]{"ConsultaReputacionUI"};
      AID[] actors=null;
      Vector<AID> actorsv=new Vector<AID>();
      Vector<String> rolesv=new Vector<String>();
      
      actorsv.addAll(this.getActor("SupervisorReputationInfoProviderRole"));
      {      
       Vector<AID> receivers=this.getActor("SupervisorReputationInfoProviderRole");      
       for (AID aid:receivers){
        rolesv.add("SupervisorReputationInfoProviderRole");
       }
      }
     

      
      
      Vector<AgentExternalDescription> completelistInvolvedActors = this.getAgentExternalDescription();      
      completelistInvolvedActors.add(new AgentExternalDescription(myAgent.getAID(),getPlayedRole()));
      
      actors=new AID[actorsv.size()];
      actorsv.toArray(actors);
      CommActCreator.generateSObject((JADEAgent)myAgent,rolesv,actors,this.getConversationID(),
       "enable","ConsultaReputacion",completelistInvolvedActors);
      this.getDCC().notifyMessageSent("disabled",options,this);
     
      this.setRunning();   
      this.notifyStateTransitionExecuted("disabled",options[0]);
      } catch (NoAgentsFound e) {
      e.printStackTrace();
  	}
    }
    


  
  
  // Receives a message and a synchronization command
  if (this.isState("AgenteDesconocidoUI") ) {  // State changed by other agent and upted
    //in the parent class
         
      Vector options=new Vector();
      
      options.add("endAgenteDesconocidoUI");
      
      String[] optionsA=new String[options.size()];
      options.toArray(optionsA);
      boolean allexist=true;
      
      int cardinality=1;
      if ("n".equals("n")){
         try{
          Vector<AID> receivers=this.getActor("SupervisorReputationInfoProviderRole");    
          cardinality=receivers.size();          
          } catch (NoAgentsFound ex) {
                    ex.printStackTrace();
                }   
       
      }
      
      if (allexist && true){     
		 addCreatedBehaviors(CommActCreator.generateR((JADEAgent)myAgent,
                        this.getConversationID(),"AgenteDesconocidoUI",
                   "ConsultaReputacion",this.getPlayedRole(),
                   optionsA,this,cardinality,0));
      }	 
      this.removeState("AgenteDesconocidoUI");
      this.addState("waiting for AgenteDesconocidoUI");
      this.notifyStateTransitionExecuted("AgenteDesconocidoUI", "waiting for AgenteDesconocidoUI");
  } 
  
  // Receives a message and a synchronization command
  if (this.isState("RespuetaReputacion") ) {  // State changed by other agent and upted
    //in the parent class
         
      Vector options=new Vector();
      
      options.add("endRespuetaReputacion");
      
      String[] optionsA=new String[options.size()];
      options.toArray(optionsA);
      boolean allexist=true;
      
      int cardinality=1;
      if ("n".equals("n")){
         try{
          Vector<AID> receivers=this.getActor("SupervisorReputationInfoProviderRole");    
          cardinality=receivers.size();          
          } catch (NoAgentsFound ex) {
                    ex.printStackTrace();
                }   
       
      }
      
      if (allexist && true){     
		 addCreatedBehaviors(CommActCreator.generateR((JADEAgent)myAgent,
                        this.getConversationID(),"RespuetaReputacion",
                   "ConsultaReputacion",this.getPlayedRole(),
                   optionsA,this,cardinality,0));
      }	 
      this.removeState("RespuetaReputacion");
      this.addState("waiting for RespuetaReputacion");
      this.notifyStateTransitionExecuted("RespuetaReputacion", "waiting for RespuetaReputacion");
  } 
  
  
  
  
  // Sends a message and synchronization commands
  if (this.isState("ConsultaReputacionUI")) {
     
     try {
      AID[] actors=null;
      Vector actorsv=new Vector();
      Vector<String> rolesv=new Vector<String>();
      
      {      
       Vector<AID> receivers=this.getActor("SupervisorReputationInfoProviderRole");      
       actorsv.addAll(receivers);
       for (AID aid:receivers){
        rolesv.add("SupervisorReputationInfoProviderRole");
       }
      }
      
      actors=new AID[actorsv.size()];
      actorsv.toArray(actors);
      Vector options=new Vector();      
      
      options.add("AgenteDesconocidoUI");      
      
      options.add("RespuetaReputacion");      
      
      String[] optionsA=new String[options.size()];
      options.toArray(optionsA);
      if (this.getDCC().notifyMessageSent("ConsultaReputacionUI",optionsA,this)){
           //If mental state conditions are met, the message is send and state changed
            CommActCreator.generateSObject((JADEAgent)myAgent,rolesv,actors,this.getConversationID(),
           "ConsultaReputacionUI","ConsultaReputacion",this.getContentForNextMessage());
           getTimeout().stop();
            this.notifyStateTransitionExecuted("ConsultaReputacionUI", options.firstElement().toString());
      } else {
    	  if (getTimeout().isStarted() && getTimeout().isFinished()){
    	    		 this.abortDueTimeout();   	        
    	    		  this.notifyStateTransitionExecuted("ConsultaReputacionUI", "ABORTED");
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
  if (this.isState("endAgenteDesconocidoUI")) {
    this.setFinished(); // End of transitions
    this.notifyProtocolFinished();
    this.getDCC().removeDefaultLocks();
  }
  
  // Finishes this state machine
  if (this.isState("endRespuetaReputacion")) {
    this.setFinished(); // End of transitions
    this.notifyProtocolFinished();
    this.getDCC().removeDefaultLocks();
  }
  
  
  if (this.isState("ABORTED")) {	    
	    this.getDCC().removeDefaultLocks();
  }
  
  if (initialStateToCompareAtTheEnd.equals(this.getStates()))
   this.block();

}


}

