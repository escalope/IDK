
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


package ingenias.jade.agents;


import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.core.behaviours.*;
import ingenias.jade.*;
import ingenias.jade.smachines.*;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;

import jade.core.*;
import jade.core.behaviours.*;

import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.domain.DFService;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import ingenias.jade.*;
import java.util.*;
import ingenias.jade.exception.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import ingenias.editor.entities.RuntimeFact;
import ingenias.jade.components.*;
import ingenias.jade.mental.*;
import ingenias.editor.entities.ApplicationEventSlots;
import ingenias.editor.entities.Interaction;
import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.ObjectSlot;
import ingenias.editor.entities.RuntimeEvent;
import ingenias.editor.entities.RuntimeFact;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.editor.entities.Slot;

import ingenias.jade.components.Task;
import ingenias.jade.graphics.*;
import ingenias.jade.MentalStateManager;
import ingenias.exception.InvalidEntity;

public class BuyerAgentJADEAgent
		 extends JADEAgent {         
 
		 public BuyerAgentJADEAgent(){
		 super(new BuyerAgentProtocol(),new BuyerAgentInteractionLocks());
		 }

	private boolean initialiseNonConversationalTask(Task tobject) {
	   boolean initialised=false;
	   Vector<String> repeatedOutputs=new Vector<String>();
	    Vector<String> nonExistingInputs=new Vector<String>();
	    
	    if (tobject.getType().equals("DeleteNonUsedEntitiesTask")){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=false;		
			TaskOutput to=null;
			to=new TaskOutput("default");
  
		
		expectedInput=this.getMSM().getMentalEntityByType("Reject_assistency_proposal");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("Reject_assistency_proposal");
			 } else {
			    addExpectedInputs(tobject, "Reject_assistency_proposal","1",expectedInput);
             	addConsumedInput(to,"Reject_assistency_proposal",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("Accept_assistency_proposal");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("Accept_assistency_proposal");
			 } else {
			    addExpectedInputs(tobject, "Accept_assistency_proposal","1",expectedInput);
             	addConsumedInput(to,"Accept_assistency_proposal",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("CouldNotGetTicket");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("CouldNotGetTicket");
			 } else {
			    addExpectedInputs(tobject, "CouldNotGetTicket","1",expectedInput);
             	addConsumedInput(to,"CouldNotGetTicket",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("Ticket_data");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("Ticket_data");
			 } else {
			    addExpectedInputs(tobject, "Ticket_data","1",expectedInput);
             	addConsumedInput(to,"Ticket_data",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		 tobject.addOutput(to);
     		   	
	      initialised= allEntitiesExist;

		  if (!allEntitiesExist){
			StringBuffer nonexisting=new StringBuffer();
			for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting.append(nonExistingInputs.elementAt(j).toString()+",");
			}
   		  }
		  return initialised;
	    }
	
   	    
    	
	      return false;
	}

	private boolean initialiseConversationalTask(RuntimeConversation conversation, Task tobject) {
	   boolean initialised=false;
	   Vector<String> nonExistingInputs=new Vector<String>();
	   Vector<String> repeatedOutputs=new Vector<String>();
	   boolean validConversationType=false;
	   
	   if (tobject.getType().equals("DeleteNonUsedEntitiesTask")){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=false;		
			TaskOutput to=null;
			to=new TaskOutput("default");
			tobject.setConversationContext(conversation);
  
		
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Reject_assistency_proposal");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("Reject_assistency_proposal");
			 } else {
			    addExpectedInputs(tobject, "Reject_assistency_proposal","1",expectedInput);
             	addConsumedInput(to,"Reject_assistency_proposal",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Accept_assistency_proposal");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("Accept_assistency_proposal");
			 } else {
			    addExpectedInputs(tobject, "Accept_assistency_proposal","1",expectedInput);
             	addConsumedInput(to,"Accept_assistency_proposal",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"CouldNotGetTicket");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("CouldNotGetTicket");
			 } else {
			    addExpectedInputs(tobject, "CouldNotGetTicket","1",expectedInput);
             	addConsumedInput(to,"CouldNotGetTicket",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Ticket_data");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("Ticket_data");
			 } else {
			    addExpectedInputs(tobject, "Ticket_data","1",expectedInput);
             	addConsumedInput(to,"Ticket_data",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		 tobject.addOutput(to);
     		   	
	      initialised= allEntitiesExist;

		  if (!allEntitiesExist){
			StringBuffer nonexisting=new StringBuffer();
			for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting.append(nonExistingInputs.elementAt(j).toString()+",");
			}
   		  }
		  return initialised;
	    }
	    
		    

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("BuyerAssignment");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("Buyer");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("");
  	   	
       	if (tobject.getType().equals("Evaluate_Assitency_Proposal") && (false ||
       		correctRole)){
	        Vector<MentalEntity> expectedInput=null;
            
       	RuntimeFact expectedOutput=null;
	   	RuntimeConversation expectedInt=null;
       	ingenias.jade.components.Resource expectedResource=null;
	   	ingenias.jade.components.Application expectedApp=null;        	
	   	TaskOutput to=null;
	   	to=new TaskOutput("default");

		tobject.setConversationContext(conversation);
		boolean allEntitiesExist=true;
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"CurrentAssistedAgent");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("CurrentAssistedAgent");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"BuyerAssignmentProposal");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("BuyerAssignmentProposal");
			else {
			    addExpectedInputs(tobject, "BuyerAssignmentProposal","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
	     
		    {Reject_assistency_proposal expectedOutputReject_assistency_proposal=		    
		     new Reject_assistency_proposal(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputReject_assistency_proposal,TaskOperations.CreateWF));
            }
	     
		    {Accept_assistency_proposal expectedOutputAccept_assistency_proposal=		    
		     new Accept_assistency_proposal(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputAccept_assistency_proposal,TaskOperations.CreateWF));
            }
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "BuyerAgent", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("Buy_ticket");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("Buyer");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("");
  	   	
       	if (tobject.getType().equals("PayTicket") && (false ||
       		correctRole)){
	        Vector<MentalEntity> expectedInput=null;
            
       	RuntimeFact expectedOutput=null;
	   	RuntimeConversation expectedInt=null;
       	ingenias.jade.components.Resource expectedResource=null;
	   	ingenias.jade.components.Application expectedApp=null;        	
	   	TaskOutput to=null;
	   	to=new TaskOutput("default");

		tobject.setConversationContext(conversation);
		boolean allEntitiesExist=true;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Pay_the_money");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("Pay_the_money");
			else {
			    addExpectedInputs(tobject, "Pay_the_money","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
             expectedApp=(ingenias.jade.components.Application)getAM().getApplication("VirtualTerminal");
             tobject.addApplication("VirtualTerminal",expectedApp);
	      
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
	     
		    {Transaction_payment expectedOutputTransaction_payment=		    
		     new Transaction_payment(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputTransaction_payment,TaskOperations.CreateWF));
            }
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "BuyerAgent", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("Buy_ticket");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("Buyer");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("");
  	   	
       	if (tobject.getType().equals("Process_offer") && (false ||
       		correctRole)){
	        Vector<MentalEntity> expectedInput=null;
            
       	RuntimeFact expectedOutput=null;
	   	RuntimeConversation expectedInt=null;
       	ingenias.jade.components.Resource expectedResource=null;
	   	ingenias.jade.components.Application expectedApp=null;        	
	   	TaskOutput to=null;
	   	to=new TaskOutput("default");

		tobject.setConversationContext(conversation);
		boolean allEntitiesExist=true;
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"RequestedMovie");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("RequestedMovie");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"CinemaProfile");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("CinemaProfile");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Movie");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("Movie");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Offer");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("Offer");
			else {
			    addExpectedInputs(tobject, "Offer","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
 			{
 			SearchTimeExceeded expectedOutputSearchTimeExceeded=
 				new SearchTimeExceeded(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputSearchTimeExceeded.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputSearchTimeExceeded.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputSearchTimeExceeded, new Interaction("")) ;	  
				} catch (SecurityException e) {
					
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
 			}	 			
            to.add(new OutputEntity(expectedOutputSearchTimeExceeded,TaskOperations.CreateMS));
            }
	     
	     
		    {Pay_the_money expectedOutputPay_the_money=		    
		     new Pay_the_money(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputPay_the_money,TaskOperations.CreateWF));
            }
	     
		    {Not_interested expectedOutputNot_interested=		    
		     new Not_interested(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputNot_interested,TaskOperations.CreateWF));
            }
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "BuyerAgent", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("Buy_ticket");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("Buyer");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("");
  	   	
       	if (tobject.getType().equals("Process_ticket") && (false ||
       		correctRole)){
	        Vector<MentalEntity> expectedInput=null;
            
       	RuntimeFact expectedOutput=null;
	   	RuntimeConversation expectedInt=null;
       	ingenias.jade.components.Resource expectedResource=null;
	   	ingenias.jade.components.Application expectedApp=null;        	
	   	TaskOutput to=null;
	   	to=new TaskOutput("default");

		tobject.setConversationContext(conversation);
		boolean allEntitiesExist=true;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Transaction_payment");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("Transaction_payment");
			else {
			    addExpectedInputs(tobject, "Transaction_payment","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Ticket");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("Ticket");
			else {
			    addExpectedInputs(tobject, "Ticket","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
	     
		    {Ticket_data expectedOutputTicket_data=		    
		     new Ticket_data(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputTicket_data,TaskOperations.CreateWF));
            }
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "BuyerAgent", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("Asking_for_a_ticket");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("Buyer");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("");
  	   	
       	if (tobject.getType().equals("AbortTicketSearch") && (false ||
       		correctRole)){
	        Vector<MentalEntity> expectedInput=null;
            
       	RuntimeFact expectedOutput=null;
	   	RuntimeConversation expectedInt=null;
       	ingenias.jade.components.Resource expectedResource=null;
	   	ingenias.jade.components.Application expectedApp=null;        	
	   	TaskOutput to=null;
	   	to=new TaskOutput("default");

		tobject.setConversationContext(conversation);
		boolean allEntitiesExist=true;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"SearchTimeExceeded");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("SearchTimeExceeded");
			else {
			    addExpectedInputs(tobject, "SearchTimeExceeded","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
	     
		    {CouldNotGetTicket expectedOutputCouldNotGetTicket=		    
		     new CouldNotGetTicket(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputCouldNotGetTicket,TaskOperations.CreateWF));
            }
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "BuyerAgent", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("Asking_for_a_ticket");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("Buyer");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("");
  	   	
       	if (tobject.getType().equals("ChooseCinema") && (false ||
       		correctRole)){
	        Vector<MentalEntity> expectedInput=null;
            
       	RuntimeFact expectedOutput=null;
	   	RuntimeConversation expectedInt=null;
       	ingenias.jade.components.Resource expectedResource=null;
	   	ingenias.jade.components.Application expectedApp=null;        	
	   	TaskOutput to=null;
	   	to=new TaskOutput("default");

		tobject.setConversationContext(conversation);
		boolean allEntitiesExist=true;
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Movie");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("Movie");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"CinemaProfile");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("CinemaProfile");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Find_a_movie");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("Find_a_movie");
			else {
			    addExpectedInputs(tobject, "Find_a_movie","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
             expectedInt=new RuntimeConversation(MentalStateManager.generateMentalEntityID());
			 expectedInt.setInteraction(new Interaction("Buy_ticket"));
             to.add(new OutputEntity(expectedInt,TaskOperations.CreateMS));
	      
	      */	      
	     boolean alreadyExists=true;
	 
	     
 			{
 			RuntimeConversation expectedOutputRuntimeConversation=
 				new RuntimeConversation(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputRuntimeConversation.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputRuntimeConversation.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputRuntimeConversation, new Interaction("Buy_ticket")) ;	  
				} catch (SecurityException e) {
					
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
 			}	 			
            to.add(new OutputEntity(expectedOutputRuntimeConversation,TaskOperations.CreateMS));
            }
	     
	     
		    {RequestedMovie expectedOutputRequestedMovie=		    
		     new RequestedMovie(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputRequestedMovie,TaskOperations.CreateWF));
            }
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "BuyerAgent", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("Buy_ticket");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("Buyer");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("");
  	   	
       	if (tobject.getType().equals("Process_no_tickets_available") && (false ||
       		correctRole)){
	        Vector<MentalEntity> expectedInput=null;
            
       	RuntimeFact expectedOutput=null;
	   	RuntimeConversation expectedInt=null;
       	ingenias.jade.components.Resource expectedResource=null;
	   	ingenias.jade.components.Application expectedApp=null;        	
	   	TaskOutput to=null;
	   	to=new TaskOutput("default");

		tobject.setConversationContext(conversation);
		boolean allEntitiesExist=true;
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"RequestedMovie");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("RequestedMovie");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"CinemaProfile");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("CinemaProfile");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"TicketNotAvailable");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("TicketNotAvailable");
			else {
			    addExpectedInputs(tobject, "TicketNotAvailable","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
             expectedInt=new RuntimeConversation(MentalStateManager.generateMentalEntityID());
			 expectedInt.setInteraction(new Interaction("Buy_ticket"));
             to.add(new OutputEntity(expectedInt,TaskOperations.CreateMS));
	      
	      */	      
	     boolean alreadyExists=true;
	 
	     
 			{
 			RuntimeConversation expectedOutputRuntimeConversation=
 				new RuntimeConversation(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputRuntimeConversation.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputRuntimeConversation.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputRuntimeConversation, new Interaction("Buy_ticket")) ;	  
				} catch (SecurityException e) {
					
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
 			}	 			
            to.add(new OutputEntity(expectedOutputRuntimeConversation,TaskOperations.CreateMS));
            }
	     
	     
		    {SearchTimeExceeded expectedOutputSearchTimeExceeded=		    
		     new SearchTimeExceeded(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputSearchTimeExceeded,TaskOperations.CreateWF));
            }
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "BuyerAgent", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("Buy_ticket");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("Buyer");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("");
  	   	
       	if (tobject.getType().equals("Look_for_another_seller") && (false ||
       		correctRole)){
	        Vector<MentalEntity> expectedInput=null;
            
       	RuntimeFact expectedOutput=null;
	   	RuntimeConversation expectedInt=null;
       	ingenias.jade.components.Resource expectedResource=null;
	   	ingenias.jade.components.Application expectedApp=null;        	
	   	TaskOutput to=null;
	   	to=new TaskOutput("default");

		tobject.setConversationContext(conversation);
		boolean allEntitiesExist=true;
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"RequestedMovie");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("RequestedMovie");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"CinemaProfile");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("CinemaProfile");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"Not_interested");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("Not_interested");
			else {
			    addExpectedInputs(tobject, "Not_interested","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
             expectedInt=new RuntimeConversation(MentalStateManager.generateMentalEntityID());
			 expectedInt.setInteraction(new Interaction("Buy_ticket"));
             to.add(new OutputEntity(expectedInt,TaskOperations.CreateMS));
	      
	      */	      
	     boolean alreadyExists=true;
	 
	     
 			{
 			RuntimeConversation expectedOutputRuntimeConversation=
 				new RuntimeConversation(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputRuntimeConversation.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputRuntimeConversation.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputRuntimeConversation, new Interaction("Buy_ticket")) ;	  
				} catch (SecurityException e) {
					
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
 			}	 			
            to.add(new OutputEntity(expectedOutputRuntimeConversation,TaskOperations.CreateMS));
            }
	     
 			{
 			SearchTimeExceeded expectedOutputSearchTimeExceeded=
 				new SearchTimeExceeded(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputSearchTimeExceeded.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputSearchTimeExceeded.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputSearchTimeExceeded, new Interaction("")) ;	  
				} catch (SecurityException e) {
					
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					
					e.printStackTrace();
				}
 			}	 			
            to.add(new OutputEntity(expectedOutputSearchTimeExceeded,TaskOperations.CreateMS));
            }
	     
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "BuyerAgent", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
         
		return false;
	}       

		
 	// This method returns the tasks this agent can perform in
	// order to satisfy the goal
	public Vector tasksThatSatisfyGoal(String goalname){
         Vector tasks=new Vector();
         Vector<String> typesOfConversation=null;
         //************************************
         // Conversational tasks evaluation
         //************************************
         
         typesOfConversation=new Vector<String>();
	     
	     typesOfConversation.add("BuyerAssignment");
		 
         
         if (goalname.equals("GetAnAssistant")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new Evaluate_Assitency_ProposalTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal GetAnAssistant",getLocalName()+"-"+tobject.getType());
						tasks.add(tobject);
					}
					tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					 if (canbescheduled && IAFProperties.getGarbageCollectionEnabled()){			
							tasks.add(tobject);
					 }
				}
				// If a conversational initialization fails, a conventional one is tried
	      }
         
          }        
         
         
         typesOfConversation=new Vector<String>();
	     
	     typesOfConversation.add("Buy_ticket");
		 
         
         if (goalname.equals("GetTicket")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new PayTicketTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal GetTicket",getLocalName()+"-"+tobject.getType());
						tasks.add(tobject);
					}
					tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					 if (canbescheduled && IAFProperties.getGarbageCollectionEnabled()){			
							tasks.add(tobject);
					 }
				}
				// If a conversational initialization fails, a conventional one is tried
	      }
         
          }        
         
         
         typesOfConversation=new Vector<String>();
	     
	     typesOfConversation.add("Buy_ticket");
		 
         
         if (goalname.equals("GetTicket")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new Process_offerTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal GetTicket",getLocalName()+"-"+tobject.getType());
						tasks.add(tobject);
					}
					tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					 if (canbescheduled && IAFProperties.getGarbageCollectionEnabled()){			
							tasks.add(tobject);
					 }
				}
				// If a conversational initialization fails, a conventional one is tried
	      }
         
          }        
         
         
         typesOfConversation=new Vector<String>();
	     
	     typesOfConversation.add("Buy_ticket");
		 
         
         if (goalname.equals("GetTicket")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new Process_ticketTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal GetTicket",getLocalName()+"-"+tobject.getType());
						tasks.add(tobject);
					}
					tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					 if (canbescheduled && IAFProperties.getGarbageCollectionEnabled()){			
							tasks.add(tobject);
					 }
				}
				// If a conversational initialization fails, a conventional one is tried
	      }
         
          }        
         
         
         typesOfConversation=new Vector<String>();
	     
	     typesOfConversation.add("Asking_for_a_ticket");
		 
         
         if (goalname.equals("FindCinema")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new AbortTicketSearchTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal FindCinema",getLocalName()+"-"+tobject.getType());
						tasks.add(tobject);
					}
					tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					 if (canbescheduled && IAFProperties.getGarbageCollectionEnabled()){			
							tasks.add(tobject);
					 }
				}
				// If a conversational initialization fails, a conventional one is tried
	      }
         
          }        
         
         
         typesOfConversation=new Vector<String>();
	     
	     typesOfConversation.add("Asking_for_a_ticket");
		 
         
         if (goalname.equals("FindCinema")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new ChooseCinemaTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal FindCinema",getLocalName()+"-"+tobject.getType());
						tasks.add(tobject);
					}
					tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					 if (canbescheduled && IAFProperties.getGarbageCollectionEnabled()){			
							tasks.add(tobject);
					 }
				}
				// If a conversational initialization fails, a conventional one is tried
	      }
         
          }        
         
         
         typesOfConversation=new Vector<String>();
	     
	     typesOfConversation.add("Buy_ticket");
		 
         
         if (goalname.equals("FindCinema")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new Process_no_tickets_availableTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal FindCinema",getLocalName()+"-"+tobject.getType());
						tasks.add(tobject);
					}
					tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					 if (canbescheduled && IAFProperties.getGarbageCollectionEnabled()){			
							tasks.add(tobject);
					 }
				}
				// If a conversational initialization fails, a conventional one is tried
	      }
         
          }        
         
         
         typesOfConversation=new Vector<String>();
	     
	     typesOfConversation.add("Buy_ticket");
		 
         
         if (goalname.equals("FindCinema")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new Look_for_another_sellerTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal FindCinema",getLocalName()+"-"+tobject.getType());
						tasks.add(tobject);
					}
					tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					 if (canbescheduled && IAFProperties.getGarbageCollectionEnabled()){			
							tasks.add(tobject);
					 }
				}
				// If a conversational initialization fails, a conventional one is tried
	      }
         
          }        
         
         
         //************************************
         // Non conversational tasks evaluation
         //************************************
         
         Task tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
         boolean canbescheduled=initialiseNonConversationalTask(tobject);
		 if (canbescheduled && IAFProperties.getGarbageCollectionEnabled()){			
				tasks.add(tobject);
		 }
        return tasks;
	}


	/**
	 *  Initializes the agent
	 */
	public void setup() {
		super.setup();
		Vector<String> ttypes=new Vector<String>(); 
		          
         
                   
         ttypes.add("Evaluate_Assitency_Proposal");					
         
         
                  
         
                   
         ttypes.add("PayTicket");					
         
         
                  
         
                   
         ttypes.add("Process_offer");					
         
         
                  
         
                   
         ttypes.add("Process_ticket");					
         
         
                  
         
                   
         ttypes.add("AbortTicketSearch");					
         
         
                  
         
                   
         ttypes.add("ChooseCinema");					
         
         
                  
         
                   
         ttypes.add("Process_no_tickets_available");					
         
         
                  
         
                   
         ttypes.add("Look_for_another_seller");					
         
         
         
         
         if (IAFProperties.getGraphicsOn())
          this.getGraphics().setKnownTasks(ttypes);

   // Interactions started by this agent		
   
   getCM().addKnownProtocol("Buy_ticket");
   
   boolean continueInit=false;
   // Interactions where this agent acts as collaborator
   
   getCM().addKnownProtocol("Asking_for_a_ticket");
   
   getCM().addKnownProtocol("BuyerAssignment");
   

   // These are the initial goals of the agent. Goals determine
   // which task to execute first    
   ingenias.editor.entities.StateGoal sg=null;
   ingenias.editor.entities.RuntimeFact ff=null;
   Slot slot=null;	  
   ObjectSlot oslot=null;
   ingenias.jade.components.Application app=null;	  
   
   sg= new ingenias.editor.entities.StateGoal("GetAnAssistant");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("GetTicket");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("AssistUser");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("FindCinema");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("Clean_mental_state");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   
     

		//Initializing the applications panel in the manager
			
		Vector events=null;		
		RuntimeEvent event=null;
		
	//Initial applications assigned to the agent	  
	Vector actions=null;
	Vector evetns=null;

     //Initial applications assigned to the agent	  
   
     app=SimulationKernelInit.getInstance(this);
     //app.registerOwner(this);
	
     	    
     this.getAM().addApplication("SimulationKernel",app);        
	 events=new Vector();
	 actions=new Vector();

	 if (getGraphics()!=null)
	  getGraphics().addApplication("SimulationKernel", events,actions);    

     //Initial applications assigned to the agent	  
   
     
     app=VirtualTerminalInit.createInstance(this);
	 //app.registerOwner(this);
	 	    
     this.getAM().addApplication("VirtualTerminal",app);        
	 events=new Vector();
	 actions=new Vector();

	 if (getGraphics()!=null)
	  getGraphics().addApplication("VirtualTerminal", events,actions);    


   



   // Panel creation for interaction control
   // This panel shows a button for each interaction it starts.
   // If this agent does not start any interaction, a label showin
   // a message "DOES NOT START ANY INTERACTION" will appear
   java.awt.event.ActionListener ifPressed=null;
   
     final JADEAgent _jaBuyerBuy_ticket=this;
     ifPressed=new java.awt.event.ActionListener() {
       public void actionPerformed(ActionEvent e) {
         _jaBuyerBuy_ticket.addBehaviour(
             new jade.core.behaviours.OneShotBehaviour() {
           public void action() {
			 // If mental conditions are meet then the protocol is started
			 Vector<MentalEntity> expectedInput=null;
			 boolean allexist=true;
			 
			 if (allexist){
			  try {
			  getCM().launchProtocolAsInitiator("Buy_ticket",getAM().getYellowPages());
              } catch (NoAgentsFound naf){
              } catch (WrongInteraction wi){
              	wi.printStackTrace();
              }
             } else {
              if (getGraphics()!=null)
                getGraphics().runtimeWarning(" Mental conditions required for starting BuyerBuy_ticket "+
        				 " are not satisfied yet ");
             }

           }
         });
       } };
     if (getGraphics()!=null)
      getGraphics().addInteraction(this.getName(), "Role: Buyer - Int: Buy_ticket", ifPressed);
     getCM().addInitiatorRoles("Buy_ticket","Buyer");
        

    
    // Final Graphics initialization
    if (getGraphics()!=null)
     getGraphics().startAgentDebug();
    
    getMSM().setModified(); // to trigger a first planning round
    // To indicate that the MSP can start
    this.agentInitialised();

	}




	/**
	 *  Obtains a DFAgentDescription array that describes the different roles an
       *  agent can play
	 *
	 *@return    Roles played
	 */
	public DFAgentDescription[ ] getDescription() {
               DFAgentDescription[] result=null;
	       Vector playedRoles=new Vector();
	        DFAgentDescription dfd=null;
                dfd = new DFAgentDescription();
                ServiceDescription sd=null;
                
		dfd.setName(getAID());
		sd = new ServiceDescription();
		sd.setName(getLocalName() + "-sub-df");
		sd.setType("Buyer");
		sd.setOwnership("JADE");
		dfd.addServices(sd);
                playedRoles.add(dfd);
                
                
                result=new  DFAgentDescription[playedRoles.size()];
                playedRoles.toArray(result);
		return result;

	}


}

