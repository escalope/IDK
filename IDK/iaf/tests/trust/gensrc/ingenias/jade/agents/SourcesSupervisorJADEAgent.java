
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

public class SourcesSupervisorJADEAgent
		 extends JADEAgent {         
 
		 public SourcesSupervisorJADEAgent(){
		 super(new SourcesSupervisorProtocol(),new SourcesSupervisorInteractionLocks());
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
  
		
		expectedInput=this.getMSM().getMentalEntityByType("NewSource");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("NewSource");
			 } else {
			    addExpectedInputs(tobject, "NewSource","1",expectedInput);
             	addConsumedInput(to,"NewSource",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("InspectQualitySource");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("InspectQualitySource");
			 } else {
			    addExpectedInputs(tobject, "InspectQualitySource","1",expectedInput);
             	addConsumedInput(to,"InspectQualitySource",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("PropuestaRechazada");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("PropuestaRechazada");
			 } else {
			    addExpectedInputs(tobject, "PropuestaRechazada","1",expectedInput);
             	addConsumedInput(to,"PropuestaRechazada",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("InspeccionarCalidadFuenteCuarentena");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("InspeccionarCalidadFuenteCuarentena");
			 } else {
			    addExpectedInputs(tobject, "InspeccionarCalidadFuenteCuarentena","1",expectedInput);
             	addConsumedInput(to,"InspeccionarCalidadFuenteCuarentena",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist || expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().getMentalEntityByType("AcceptedProposal");
		if (this.getLM().canBeDeleted(expectedInput)){             
             if (expectedInput.size()==0){
				nonExistingInputs.add("AcceptedProposal");
			 } else {
			    addExpectedInputs(tobject, "AcceptedProposal","1",expectedInput);
             	addConsumedInput(to,"AcceptedProposal",expectedInput);
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
	
   	    
    	         
                  
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("RealizarPreguntaReputacionTask") ){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
	      
		
		
             expectedInput=this.getMSM().getMentalEntityByType("ReputacionAgenteNecesaria");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("ReputacionAgenteNecesaria");
			 } else {
			    addExpectedInputs(tobject, "ReputacionAgenteNecesaria","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
			 expectedInt=new RuntimeConversation(MentalStateManager.generateMentalEntityID());
			 expectedInt.setInteraction(new Interaction("ConsultaReputacion"));
             to.add(new OutputEntity(expectedInt,TaskOperations.CreateMS));
	      
	      */	  
	      
	      /**/	      
	      
 
	     
 			{
 			RuntimeConversation expectedOutputRuntimeConversation=
 				new RuntimeConversation(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputRuntimeConversation.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputRuntimeConversation.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputRuntimeConversation, new Interaction("ConsultaReputacion")) ;	  
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
	     
	     
		    {ConsultaReputacionAgente expectedOutputConsultaReputacionAgente=		    
		     new ConsultaReputacionAgente(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputConsultaReputacionAgente,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;

		if (!allEntitiesExist){
		   String[] nonexisting=new String[nonExistingInputs.size()];
		   for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting[j]=nonExistingInputs.elementAt(j).toString();
			}
			EventManager.getInstance().conversationalInitializationOfTaskFailed(getLocalName(), 
												"SourcesSupervisor", 
												tobject, nonexisting);
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
  
		
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"NewSource");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("NewSource");
			 } else {
			    addExpectedInputs(tobject, "NewSource","1",expectedInput);
             	addConsumedInput(to,"NewSource",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"InspectQualitySource");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("InspectQualitySource");
			 } else {
			    addExpectedInputs(tobject, "InspectQualitySource","1",expectedInput);
             	addConsumedInput(to,"InspectQualitySource",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"PropuestaRechazada");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("PropuestaRechazada");
			 } else {
			    addExpectedInputs(tobject, "PropuestaRechazada","1",expectedInput);
             	addConsumedInput(to,"PropuestaRechazada",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"InspeccionarCalidadFuenteCuarentena");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("InspeccionarCalidadFuenteCuarentena");
			 } else {
			    addExpectedInputs(tobject, "InspeccionarCalidadFuenteCuarentena","1",expectedInput);
             	addConsumedInput(to,"InspeccionarCalidadFuenteCuarentena",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist|| expectedInput.size()!=0;
		} 
	      
		expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"AcceptedProposal");
		if (this.getLM().canBeDeleted(expectedInput)){                          
             if (expectedInput.size()==0){
				nonExistingInputs.add("AcceptedProposal");
			 } else {
			    addExpectedInputs(tobject, "AcceptedProposal","1",expectedInput);
             	addConsumedInput(to,"AcceptedProposal",expectedInput);
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
				conversation.getInteraction().getId().equalsIgnoreCase("ConsultaReputacion");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("SupervisorInterestedInReputationRole");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("");
  	   	
       	if (tobject.getType().equals("AceptarNegacionPreguntaReputacionTask") && (false ||
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
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"AgenteDesconocido");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("AgenteDesconocido");
			else {
			    addExpectedInputs(tobject, "AgenteDesconocido","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "SourcesSupervisor", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("ConsultaReputacion");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("SupervisorInterestedInReputationRole");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("");
  	   	
       	if (tobject.getType().equals("ActualizarValorReputacionTask") && (false ||
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
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"ValoresConfianzaReputacion");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("ValoresConfianzaReputacion");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"ReputacionAgente");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("ReputacionAgente");
			else {
			    addExpectedInputs(tobject, "ReputacionAgente","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "SourcesSupervisor", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("PerformProposal");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("SupervisorRole");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("");
  	   	
       	if (tobject.getType().equals("AddSourceIntoSystem") && (false ||
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
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"SourceToProcess");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("SourceToProcess");
			else {
			    addExpectedInputs(tobject, "SourceToProcess","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
             expectedInt=new RuntimeConversation(MentalStateManager.generateMentalEntityID());
			 expectedInt.setInteraction(new Interaction("QualityInspectionRequest"));
             to.add(new OutputEntity(expectedInt,TaskOperations.CreateMS));
	      
             expectedInt=new RuntimeConversation(MentalStateManager.generateMentalEntityID());
			 expectedInt.setInteraction(new Interaction("RequestToAddSource"));
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
					m.invoke(expectedOutputRuntimeConversation, new Interaction("QualityInspectionRequest")) ;	  
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
 			RuntimeConversation expectedOutputRuntimeConversation=
 				new RuntimeConversation(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputRuntimeConversation.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputRuntimeConversation.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputRuntimeConversation, new Interaction("RequestToAddSource")) ;	  
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
	     
	     
		    {NewSource expectedOutputNewSource=		    
		     new NewSource(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputNewSource,TaskOperations.CreateWF));
            }
	     
		    {InspectQualitySource expectedOutputInspectQualitySource=		    
		     new InspectQualitySource(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputInspectQualitySource,TaskOperations.CreateWF));
            }
	     
		    {AcceptedProposal expectedOutputAcceptedProposal=		    
		     new AcceptedProposal(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputAcceptedProposal,TaskOperations.CreateWF));
            }
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "SourcesSupervisor", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("SolicitudInspeccionAlfaCalidad");
	 	    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("PerformProposal");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("SupervisorRole");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("");
  	   	
       	if (tobject.getType().equals("ProcessResultAlfaQualityInspection") && (false ||
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
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"ValoresConfianzaReputacion");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("ValoresConfianzaReputacion");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"GradoCalidadFuenteCuarentena");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("GradoCalidadFuenteCuarentena");
			else {
			    addExpectedInputs(tobject, "GradoCalidadFuenteCuarentena","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
	     
		    {PropuestaRechazada expectedOutputPropuestaRechazada=		    
		     new PropuestaRechazada(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputPropuestaRechazada,TaskOperations.CreateWF));
            }
	     
		    {SourceToProcess expectedOutputSourceToProcess=		    
		     new SourceToProcess(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputSourceToProcess,TaskOperations.CreateWF));
            }
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "SourcesSupervisor", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("QualityInspectionRequest");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("SupervisorRole");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("");
  	   	
       	if (tobject.getType().equals("ProcessInspectionResult") && (false ||
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
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"ValoresConfianzaReputacion");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("ValoresConfianzaReputacion");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"GradoCalidadFuente");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("GradoCalidadFuente");
			else {
			    addExpectedInputs(tobject, "GradoCalidadFuente","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "SourcesSupervisor", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("PerformProposal");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("SupervisorRole");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("");
  	   	
       	if (tobject.getType().equals("RequestAlfaQualityInspection") && (false ||
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
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"FuenteParaCuarentena");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("FuenteParaCuarentena");
			else {
			    addExpectedInputs(tobject, "FuenteParaCuarentena","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
             expectedInt=new RuntimeConversation(MentalStateManager.generateMentalEntityID());
			 expectedInt.setInteraction(new Interaction("SolicitudInspeccionAlfaCalidad"));
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
					m.invoke(expectedOutputRuntimeConversation, new Interaction("SolicitudInspeccionAlfaCalidad")) ;	  
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
	     
	     
		    {InspeccionarCalidadFuenteCuarentena expectedOutputInspeccionarCalidadFuenteCuarentena=		    
		     new InspeccionarCalidadFuenteCuarentena(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputInspeccionarCalidadFuenteCuarentena,TaskOperations.CreateWF));
            }
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "SourcesSupervisor", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("PerformProposal");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("SupervisorRole");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("");
  	   	
       	if (tobject.getType().equals("ProcessReceivedProposalTask") && (false ||
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
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"ValoresConfianzaReputacion");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("ValoresConfianzaReputacion");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"PropuestaFuente");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("PropuestaFuente");
			else {
			    addExpectedInputs(tobject, "PropuestaFuente","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
 			{
 			ReputacionAgenteNecesaria expectedOutputReputacionAgenteNecesaria=
 				new ReputacionAgenteNecesaria(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputReputacionAgenteNecesaria.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputReputacionAgenteNecesaria.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputReputacionAgenteNecesaria, new Interaction("")) ;	  
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
            to.add(new OutputEntity(expectedOutputReputacionAgenteNecesaria,TaskOperations.CreateMS));
            }
	     
	     
		    {FuenteParaCuarentena expectedOutputFuenteParaCuarentena=		    
		     new FuenteParaCuarentena(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputFuenteParaCuarentena,TaskOperations.CreateWF));
            }
	     
		    {PropuestaRechazada expectedOutputPropuestaRechazada=		    
		     new PropuestaRechazada(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputPropuestaRechazada,TaskOperations.CreateWF));
            }
	     
		    {SourceToProcess expectedOutputSourceToProcess=		    
		     new SourceToProcess(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputSourceToProcess,TaskOperations.CreateWF));
            }
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "SourcesSupervisor", 
												tobject, nonexisting);
     	     			
			   }
	        	       
 	      initialised= allEntitiesExist;
 	       return initialised;
	      }
         
         
         }
         validConversationType=false;
             

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("ConsultaReputacion");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
  	   	boolean correctRole=conversation.getPlayedRole().equals ("SupervisorReputationInfoProviderRole");
  	   	// Now all ascendant roles are verified, to enable tasks belonging to roles specializing a more
  	   	// generic one involved in an interaction
  	   	
  	   	correctRole=correctRole|| 
  	   	 conversation.getPlayedRole().equals ("");
  	   	
       	if (tobject.getType().equals("ProcesarConsultaReputacionTask") && (false ||
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
	     
	     	expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"ValoresConfianzaReputacion");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("ValoresConfianzaReputacion");
			else
				addExpectedInputs(tobject, "", 
				"1", expectedInput);
		    allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	     
            
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"ConsultaReputacionAgente");
			if (expectedInput.size()==0 && !("1".equals("0..n")))
				nonExistingInputs.add("ConsultaReputacionAgente");
			else {
			    addExpectedInputs(tobject, "ConsultaReputacionAgente","1",expectedInput);
			    addConsumedInput(to, "1", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
	     
		    {ReputacionAgente expectedOutputReputacionAgente=		    
		     new ReputacionAgente(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputReputacionAgente,TaskOperations.CreateWF));
            }
	     
		    {AgenteDesconocido expectedOutputAgenteDesconocido=		    
		     new AgenteDesconocido(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputAgenteDesconocido,TaskOperations.CreateWF));
            }
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
     	         String[] nonexisting=new String[nonExistingInputs.size()];
		   		 for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting[j]=nonExistingInputs.elementAt(j).toString();
				 }
				 EventManager.getInstance().conversationalInitializationOfTaskFailed(
				 			getLocalName(), "SourcesSupervisor", 
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
	     
	     typesOfConversation.add("ConsultaReputacion");
		 
         
         if (goalname.equals("MantenerActualizadoInfoReputacion")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new AceptarNegacionPreguntaReputacionTaskTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal MantenerActualizadoInfoReputacion",getLocalName()+"-"+tobject.getType());
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
	     
	     typesOfConversation.add("ConsultaReputacion");
		 
         
         if (goalname.equals("MantenerActualizadoInfoReputacion")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new ActualizarValorReputacionTaskTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal MantenerActualizadoInfoReputacion",getLocalName()+"-"+tobject.getType());
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
	     
	     typesOfConversation.add("PerformProposal");
		 
         
         if (goalname.equals("SystemWithQualitySources")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new AddSourceIntoSystemTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal SystemWithQualitySources",getLocalName()+"-"+tobject.getType());
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
	     
	     typesOfConversation.add("SolicitudInspeccionAlfaCalidad");
		 
	     typesOfConversation.add("PerformProposal");
		 
         
         if (goalname.equals("SystemWithQualitySources")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new ProcessResultAlfaQualityInspectionTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal SystemWithQualitySources",getLocalName()+"-"+tobject.getType());
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
	     
	     typesOfConversation.add("QualityInspectionRequest");
		 
         
         if (goalname.equals("SystemWithQualitySources")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new ProcessInspectionResultTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal SystemWithQualitySources",getLocalName()+"-"+tobject.getType());
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
	     
	     typesOfConversation.add("PerformProposal");
		 
         
         if (goalname.equals("SystemWithQualitySources")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new RequestAlfaQualityInspectionTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal SystemWithQualitySources",getLocalName()+"-"+tobject.getType());
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
	     
	     typesOfConversation.add("PerformProposal");
		 
         
         if (goalname.equals("SystemWithQualitySources")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new ProcessReceivedProposalTaskTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal SystemWithQualitySources",getLocalName()+"-"+tobject.getType());
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
	     
	     typesOfConversation.add("ConsultaReputacion");
		 
         
         if (goalname.equals("ProveerInformacionReputacion")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new ProcesarConsultaReputacionTaskTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
					//	MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal ProveerInformacionReputacion",getLocalName()+"-"+tobject.getType());
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
         
         if (goalname.equals("MantenerActualizadoInfoReputacion")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new RealizarPreguntaReputacionTaskTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					//MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal MantenerActualizadoInfoReputacion",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
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
		          
         
                   
         ttypes.add("AceptarNegacionPreguntaReputacionTask");					
         
         
                  
         
                   
         ttypes.add("ActualizarValorReputacionTask");					
         
         
                  
         
                   
         ttypes.add("AddSourceIntoSystem");					
         
         
                  
         
                   
         ttypes.add("ProcessResultAlfaQualityInspection");					
         
         
                  
         
                   
         ttypes.add("ProcessInspectionResult");					
         
         
                  
         
                   
         ttypes.add("RequestAlfaQualityInspection");					
         
         
                  
         
                   
         ttypes.add("ProcessReceivedProposalTask");					
         
         
                  
         
                   
         ttypes.add("ProcesarConsultaReputacionTask");					
         
         
         
         
         
         ttypes.add("RealizarPreguntaReputacionTask");		         
                
         
         if (IAFProperties.getGraphicsOn())
          this.getGraphics().setKnownTasks(ttypes);

   // Interactions started by this agent		
   
   getCM().addKnownProtocol("SolicitudInspeccionAlfaCalidad");
   
   getCM().addKnownProtocol("RequestToAddSource");
   
   getCM().addKnownProtocol("QualityInspectionRequest");
   
   getCM().addKnownProtocol("ConsultaReputacion");
   
   boolean continueInit=false;
   // Interactions where this agent acts as collaborator
   
   getCM().addKnownProtocol("PerformProposal");
   
   getCM().addKnownProtocol("ConsultaReputacion");
   

   // These are the initial goals of the agent. Goals determine
   // which task to execute first    
   ingenias.editor.entities.StateGoal sg=null;
   ingenias.editor.entities.RuntimeFact ff=null;
   Slot slot=null;	  
   ObjectSlot oslot=null;
   ingenias.jade.components.Application app=null;	  
   
   sg= new ingenias.editor.entities.StateGoal("MantenerActualizadoInfoReputacion");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("SystemWithQualitySources");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   sg= new ingenias.editor.entities.StateGoal("ProveerInformacionReputacion");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   
   
   ff= new ValoresConfianzaReputacion();      
   
   /* */
   		try {
			this.getMSM().addMentalEntity(ff);
		} catch (InvalidEntity e) {

			e.printStackTrace();
		}
     

		//Initializing the applications panel in the manager
			
		Vector events=null;		
		RuntimeEvent event=null;
		
	//Initial applications assigned to the agent	  
	Vector actions=null;
	Vector evetns=null;


   



   // Panel creation for interaction control
   // This panel shows a button for each interaction it starts.
   // If this agent does not start any interaction, a label showin
   // a message "DOES NOT START ANY INTERACTION" will appear
   java.awt.event.ActionListener ifPressed=null;
   
     final JADEAgent _jaSupervisorRoleSolicitudInspeccionAlfaCalidad=this;
     ifPressed=new java.awt.event.ActionListener() {
       public void actionPerformed(ActionEvent e) {
         _jaSupervisorRoleSolicitudInspeccionAlfaCalidad.addBehaviour(
             new jade.core.behaviours.OneShotBehaviour() {
           public void action() {
			 // If mental conditions are meet then the protocol is started
			 RuntimeFact expectedInput=null;
			 boolean allexist=true;
			 
			 if (allexist){
			  try {
			  getCM().launchProtocolAsInitiator("SolicitudInspeccionAlfaCalidad",getAM().getYellowPages());
              } catch (NoAgentsFound naf){
              }
             } else {
              if (getGraphics()!=null)
                getGraphics().runtimeWarning(" Mental conditions required for starting SupervisorRoleSolicitudInspeccionAlfaCalidad "+
        				 " are not satisfied yet ");
             }

           }
         });
       } };
     if (getGraphics()!=null)
      getGraphics().addInteraction(this.getName(), "Role: SupervisorRole - Int: SolicitudInspeccionAlfaCalidad", ifPressed);
     getCM().addInitiatorRoles("SolicitudInspeccionAlfaCalidad","SupervisorRole");
     
     final JADEAgent _jaSupervisorRoleRequestToAddSource=this;
     ifPressed=new java.awt.event.ActionListener() {
       public void actionPerformed(ActionEvent e) {
         _jaSupervisorRoleRequestToAddSource.addBehaviour(
             new jade.core.behaviours.OneShotBehaviour() {
           public void action() {
			 // If mental conditions are meet then the protocol is started
			 RuntimeFact expectedInput=null;
			 boolean allexist=true;
			 
			 if (allexist){
			  try {
			  getCM().launchProtocolAsInitiator("RequestToAddSource",getAM().getYellowPages());
              } catch (NoAgentsFound naf){
              }
             } else {
              if (getGraphics()!=null)
                getGraphics().runtimeWarning(" Mental conditions required for starting SupervisorRoleRequestToAddSource "+
        				 " are not satisfied yet ");
             }

           }
         });
       } };
     if (getGraphics()!=null)
      getGraphics().addInteraction(this.getName(), "Role: SupervisorRole - Int: RequestToAddSource", ifPressed);
     getCM().addInitiatorRoles("RequestToAddSource","SupervisorRole");
     
     final JADEAgent _jaSupervisorRoleQualityInspectionRequest=this;
     ifPressed=new java.awt.event.ActionListener() {
       public void actionPerformed(ActionEvent e) {
         _jaSupervisorRoleQualityInspectionRequest.addBehaviour(
             new jade.core.behaviours.OneShotBehaviour() {
           public void action() {
			 // If mental conditions are meet then the protocol is started
			 RuntimeFact expectedInput=null;
			 boolean allexist=true;
			 
			 if (allexist){
			  try {
			  getCM().launchProtocolAsInitiator("QualityInspectionRequest",getAM().getYellowPages());
              } catch (NoAgentsFound naf){
              }
             } else {
              if (getGraphics()!=null)
                getGraphics().runtimeWarning(" Mental conditions required for starting SupervisorRoleQualityInspectionRequest "+
        				 " are not satisfied yet ");
             }

           }
         });
       } };
     if (getGraphics()!=null)
      getGraphics().addInteraction(this.getName(), "Role: SupervisorRole - Int: QualityInspectionRequest", ifPressed);
     getCM().addInitiatorRoles("QualityInspectionRequest","SupervisorRole");
     
     final JADEAgent _jaSupervisorInterestedInReputationRoleConsultaReputacion=this;
     ifPressed=new java.awt.event.ActionListener() {
       public void actionPerformed(ActionEvent e) {
         _jaSupervisorInterestedInReputationRoleConsultaReputacion.addBehaviour(
             new jade.core.behaviours.OneShotBehaviour() {
           public void action() {
			 // If mental conditions are meet then the protocol is started
			 RuntimeFact expectedInput=null;
			 boolean allexist=true;
			 
			 if (allexist){
			  try {
			  getCM().launchProtocolAsInitiator("ConsultaReputacion",getAM().getYellowPages());
              } catch (NoAgentsFound naf){
              }
             } else {
              if (getGraphics()!=null)
                getGraphics().runtimeWarning(" Mental conditions required for starting SupervisorInterestedInReputationRoleConsultaReputacion "+
        				 " are not satisfied yet ");
             }

           }
         });
       } };
     if (getGraphics()!=null)
      getGraphics().addInteraction(this.getName(), "Role: SupervisorInterestedInReputationRole - Int: ConsultaReputacion", ifPressed);
     getCM().addInitiatorRoles("ConsultaReputacion","SupervisorInterestedInReputationRole");
        

    
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
		sd.setType("SupervisorRole");
		sd.setOwnership("JADE");
		dfd.addServices(sd);
                playedRoles.add(dfd);
                
		dfd.setName(getAID());
		sd = new ServiceDescription();
		sd.setName(getLocalName() + "-sub-df");
		sd.setType("SupervisorInterestedInReputationRole");
		sd.setOwnership("JADE");
		dfd.addServices(sd);
                playedRoles.add(dfd);
                
		dfd.setName(getAID());
		sd = new ServiceDescription();
		sd.setName(getLocalName() + "-sub-df");
		sd.setType("SupervisorReputationInfoProviderRole");
		sd.setOwnership("JADE");
		dfd.addServices(sd);
                playedRoles.add(dfd);
                
                
                result=new  DFAgentDescription[playedRoles.size()];
                playedRoles.toArray(result);
		return result;

	}


}

