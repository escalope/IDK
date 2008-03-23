
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

public class ATimeOutJADEAgent
		 extends JADEAgent {         
 
		 public ATimeOutJADEAgent(){
		 super(new ATimeOutProtocol(),new ATimeOutInteractionLocks());
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
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
  
		
             expectedInput=this.getMSM().getMentalEntityByType("SingleQuestion");
             if (expectedInput.size()==0){
				nonExistingInputs.add("SingleQuestion");
			 } else {
			    addExpectedInputs(tobject, "SingleQuestion","1",expectedInput);
             	addConsumedInput(to,"SingleQuestion",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
             expectedInput=this.getMSM().getMentalEntityByType("InitialQuestion");
             if (expectedInput.size()==0){
				nonExistingInputs.add("InitialQuestion");
			 } else {
			    addExpectedInputs(tobject, "InitialQuestion","1",expectedInput);
             	addConsumedInput(to,"InitialQuestion",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
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
         if (tobject.getType().equals("Launch_Conversation_and_Enable_all_initiator_timeout")){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
	      
		
             expectedInput=this.getMSM().getMentalEntityByType("SenderFailure");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("SenderFailure");
			 } else {
			    addExpectedInputs(tobject, "SenderFailure","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("TimeOut_generator");
             tobject.addApplication("TimeOut_generator",expectedApp);
	      
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
 			{
 			RuntimeConversation expectedOutputRuntimeConversation=
 				new RuntimeConversation(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputRuntimeConversation.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputRuntimeConversation.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputRuntimeConversation, new Interaction("IntExample")) ;	  
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
	     
	     
		    {InitialQuestion expectedOutputInitialQuestion=		    
		     new InitialQuestion(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputInitialQuestion,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;
	      /*if (alreadyExists && repeatedOutputs.size()!=0){ 
	      StringBuffer poutcomes=new StringBuffer();
	      for (int j=0;j<repeatedOutputs.size();j++){
			poutcomes.append(repeatedOutputs.elementAt(j).toString()+",");
	      }
	      MainInteractionManager.log("Non conversational initialisation discarded task "+tobject.getType()+
		 " to achieve goal DemoGoal because a previous outcome exists:"+poutcomes,getLocalName()+"-"+tobject.getType());
	      return false;
		} else */
		if (!allEntitiesExist){
			StringBuffer nonexisting=new StringBuffer();
			for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting.append(nonExistingInputs.elementAt(j).toString()+",");
			}
			
			MainInteractionManager.log("Non conversational initialisation discarded task "+tobject.getType()+
				" to achieve goal DemoGoal because did not have all preconditions. Missing elements are:"+nonexisting+". Currently, the MS has the following elements:"+this.getMSM().getAllMentalEntities(),getLocalName()+"-"+tobject.getType());
		}
		return initialised;	       
	      }
                 
                  
         
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("Launch_Conversation_and_Enable_all_participant2_timeouts")){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
	      
		
             expectedInput=this.getMSM().getMentalEntityByType("FailAllParticipant2");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("FailAllParticipant2");
			 } else {
			    addExpectedInputs(tobject, "FailAllParticipant2","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("TimeOut_generator");
             tobject.addApplication("TimeOut_generator",expectedApp);
	      
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
 			{
 			RuntimeConversation expectedOutputRuntimeConversation=
 				new RuntimeConversation(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputRuntimeConversation.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputRuntimeConversation.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputRuntimeConversation, new Interaction("IntExample")) ;	  
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
	     
	     
		    {InitialQuestion expectedOutputInitialQuestion=		    
		     new InitialQuestion(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputInitialQuestion,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;
	      /*if (alreadyExists && repeatedOutputs.size()!=0){ 
	      StringBuffer poutcomes=new StringBuffer();
	      for (int j=0;j<repeatedOutputs.size();j++){
			poutcomes.append(repeatedOutputs.elementAt(j).toString()+",");
	      }
	      MainInteractionManager.log("Non conversational initialisation discarded task "+tobject.getType()+
		 " to achieve goal DemoGoal because a previous outcome exists:"+poutcomes,getLocalName()+"-"+tobject.getType());
	      return false;
		} else */
		if (!allEntitiesExist){
			StringBuffer nonexisting=new StringBuffer();
			for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting.append(nonExistingInputs.elementAt(j).toString()+",");
			}
			
			MainInteractionManager.log("Non conversational initialisation discarded task "+tobject.getType()+
				" to achieve goal DemoGoal because did not have all preconditions. Missing elements are:"+nonexisting+". Currently, the MS has the following elements:"+this.getMSM().getAllMentalEntities(),getLocalName()+"-"+tobject.getType());
		}
		return initialised;	       
	      }
                 
                  
         
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("Launch_Conversation_and_Enable_Participant2_timeouts")){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
	      
		
             expectedInput=this.getMSM().getMentalEntityByType("FailOneParticipan2");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("FailOneParticipan2");
			 } else {
			    addExpectedInputs(tobject, "FailOneParticipan2","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("TimeOut_generator");
             tobject.addApplication("TimeOut_generator",expectedApp);
	      
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
 			{
 			RuntimeConversation expectedOutputRuntimeConversation=
 				new RuntimeConversation(MentalStateManager.generateMentalEntityID());
 			if (RuntimeConversation.class.isAssignableFrom(expectedOutputRuntimeConversation.getClass())){
 			    java.lang.reflect.Method m;
				try {
					m = expectedOutputRuntimeConversation.getClass().getMethod("setInteraction", new Class[]{Interaction.class});
					m.invoke(expectedOutputRuntimeConversation, new Interaction("IntExample")) ;	  
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
	     
	     
		    {InitialQuestion expectedOutputInitialQuestion=		    
		     new InitialQuestion(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputInitialQuestion,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;
	      /*if (alreadyExists && repeatedOutputs.size()!=0){ 
	      StringBuffer poutcomes=new StringBuffer();
	      for (int j=0;j<repeatedOutputs.size();j++){
			poutcomes.append(repeatedOutputs.elementAt(j).toString()+",");
	      }
	      MainInteractionManager.log("Non conversational initialisation discarded task "+tobject.getType()+
		 " to achieve goal DemoGoal because a previous outcome exists:"+poutcomes,getLocalName()+"-"+tobject.getType());
	      return false;
		} else */
		if (!allEntitiesExist){
			StringBuffer nonexisting=new StringBuffer();
			for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting.append(nonExistingInputs.elementAt(j).toString()+",");
			}
			
			MainInteractionManager.log("Non conversational initialisation discarded task "+tobject.getType()+
				" to achieve goal DemoGoal because did not have all preconditions. Missing elements are:"+nonexisting+". Currently, the MS has the following elements:"+this.getMSM().getAllMentalEntities(),getLocalName()+"-"+tobject.getType());
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
		    

		    
		validConversationType=validConversationType||
				conversation.getInteraction().getId().equalsIgnoreCase("IntExample");
	 	
				
		if (validConversationType){
    	         
         
	   	nonExistingInputs.clear();
  	   	repeatedOutputs.clear();
       	if (tobject.getType().equals("GeneratingForSingleReceiver")){
	        Vector<MentalEntity> expectedInput=null;
            
       	RuntimeFact expectedOutput=null;
	   	RuntimeConversation expectedInt=null;
       	ingenias.jade.components.Resource expectedResource=null;
	   	ingenias.jade.components.Application expectedApp=null;        	
	   	TaskOutput to=null;
	   	to=new TaskOutput("default");

		tobject.setConversationContext(conversation);
		boolean allEntitiesExist=true;
	     
             
		
            expectedInput=this.getMSM().obtainConversationalMentalEntityByType(conversation,"MultipleAnswer");
			if (expectedInput.size()==0 && !("n".equals("0..n")))
				nonExistingInputs.add("MultipleAnswer");
			else {
			    addExpectedInputs(tobject, "MultipleAnswer","n",expectedInput);
			    addConsumedInput(to, "n", expectedInput);
			}
	      allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;
	      
		
             expectedApp=(ingenias.jade.components.Application)getAM().getApplication("TimeOut_generator");
             tobject.addApplication("TimeOut_generator",expectedApp);
	      
	      expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
        /*     
		
	      */	      
	     boolean alreadyExists=true;
	 
	     
	     
		    {SingleQuestion expectedOutputSingleQuestion=		    
		     new SingleQuestion(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputSingleQuestion,TaskOperations.CreateWF));
            }
	     
     
	     tobject.addOutput(to);
	     
	     
     	      if (!allEntitiesExist){
				StringBuffer nonexisting=new StringBuffer();
				for (int j=0;j<nonExistingInputs.size();j++){
					nonexisting.append(nonExistingInputs.elementAt(j).toString()+",");
				}
       	        MainInteractionManager.log("Conversational intialisation discarded task "+tobject.getType()+
	        	 " to achieve goal DemoGoal because did not have all preconditions. Missing elements are:"+nonexisting+
                        ". Currently, the MS has the following elements:"+this.getMSM().getAllMentalEntities(),getLocalName()+"-"+tobject.getType());
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
	     
	     typesOfConversation.add("IntExample");
		 
         
         if (goalname.equals("DemoGoal")){
         
          {
		    Task tobject=null;
			Vector<RuntimeConversation>  conversations=getCM().getCurrentActiveConversations(typesOfConversation);
				boolean canbescheduled=false;
				for (int k=0;k<conversations.size();k++){
					tobject=new GeneratingForSingleReceiverTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
					canbescheduled=initialiseConversationalTask(conversations.elementAt(k),tobject);
					if (canbescheduled){
						MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal DemoGoal",getLocalName()+"-"+tobject.getType());
						tasks.add(tobject);
					}
				}
				// If a conversational initialization fails, a conventional one is tried
	      }
         
          }        
         
         
         //************************************
         // Non conversational tasks evaluation
         //************************************
         
         if (goalname.equals("DemoGoal")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new Launch_Conversation_and_Enable_all_initiator_timeoutTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal DemoGoal",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("DemoGoal")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new Launch_Conversation_and_Enable_all_participant2_timeoutsTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal DemoGoal",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("DemoGoal")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new Launch_Conversation_and_Enable_Participant2_timeoutsTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal DemoGoal",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         Task tobject=new DeleteNonUsedEntitiesTask("DeleteNonUsedEntitiesTask","DeleteNonUsedEntitiesTask");
         boolean canbescheduled=initialiseNonConversationalTask(tobject);
		 if (canbescheduled){			
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
		          
         
                   
         ttypes.add("GeneratingForSingleReceiver");					
         
         
         
         
         
         ttypes.add("Launch_Conversation_and_Enable_all_initiator_timeout");		         
                
         
         
         ttypes.add("Launch_Conversation_and_Enable_all_participant2_timeouts");		         
                
         
         
         ttypes.add("Launch_Conversation_and_Enable_Participant2_timeouts");		         
                
         
         if (IAFProperties.getGraphicsOn())
          this.getGraphics().setKnownTasks(ttypes);

   // Interactions started by this agent		
   
   getCM().addKnownProtocol("IntExample");
   
   boolean continueInit=false;
   // Interactions where this agent acts as collaborator
   

   // These are the initial goals of the agent. Goals determine
   // which task to execute first    
   ingenias.editor.entities.StateGoal sg=null;
   ingenias.editor.entities.RuntimeFact ff=null;
   Slot slot=null;	  
   ObjectSlot oslot=null;
   ingenias.jade.components.Application app=null;	  
   
   sg= new ingenias.editor.entities.StateGoal("DemoGoal");
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
   
     app=TimeOut_generatorInit.getInstance(this);
     //app.registerOwner(this);
	
     	    
     this.getAM().addApplication("TimeOut_generator",app);        
	 events=new Vector();
	 actions=new Vector();

	 if (getGraphics()!=null)
	  getGraphics().addApplication("TimeOut_generator", events,actions);    


   



   // Panel creation for interaction control
   // This panel shows a button for each interaction it starts.
   // If this agent does not start any interaction, a label showin
   // a message "DOES NOT START ANY INTERACTION" will appear
   java.awt.event.ActionListener ifPressed=null;
   
     final JADEAgent _jaInitiatorIntExample=this;
     ifPressed=new java.awt.event.ActionListener() {
       public void actionPerformed(ActionEvent e) {
         _jaInitiatorIntExample.addBehaviour(
             new jade.core.behaviours.OneShotBehaviour() {
           public void action() {
			 // If mental conditions are meet then the protocol is started
			 RuntimeFact expectedInput=null;
			 boolean allexist=true;
			 
			 if (allexist){
			  try {
			  getCM().launchProtocolAsInitiator("IntExample",getAM().getYellowPages());
              } catch (NoAgentsFound naf){
              }
             } else {
              if (getGraphics()!=null)
                getGraphics().runtimeWarning(" Mental conditions required for starting InitiatorIntExample "+
        				 " are not satisfied yet ");
             }

           }
         });
       } };
     if (getGraphics()!=null)
      getGraphics().addInteraction(this.getName(), "Role: Initiator - Int: IntExample", ifPressed);
     getCM().addInitiatorRoles("IntExample","Initiator");
        

    
    // Final Graphics initialization
    if (getGraphics()!=null)
     getGraphics().startAgentDebug();
    
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
		sd.setType("Initiator");
		sd.setOwnership("JADE");
		dfd.addServices(sd);
                playedRoles.add(dfd);
                
                
                result=new  DFAgentDescription[playedRoles.size()];
                playedRoles.toArray(result);
		return result;

	}


}

