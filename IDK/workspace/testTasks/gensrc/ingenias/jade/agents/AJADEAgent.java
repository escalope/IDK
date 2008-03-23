
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

public class AJADEAgent
		 extends JADEAgent {         
 
		 public AJADEAgent(){
		 super(new AProtocol(),new AInteractionLocks());
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
  
		
             expectedInput=this.getMSM().getMentalEntityByType("SingleInputTaskOutput");
             if (expectedInput.size()==0){
				nonExistingInputs.add("SingleInputTaskOutput");
			 } else {
			    addExpectedInputs(tobject, "SingleInputTaskOutput","1",expectedInput);
             	addConsumedInput(to,"SingleInputTaskOutput",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
             expectedInput=this.getMSM().getMentalEntityByType("AnotherFrameFact");
             if (expectedInput.size()==0){
				nonExistingInputs.add("AnotherFrameFact");
			 } else {
			    addExpectedInputs(tobject, "AnotherFrameFact","1",expectedInput);
             	addConsumedInput(to,"AnotherFrameFact",expectedInput);
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
         if (tobject.getType().equals("SingleInputTask")){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
	      
		
             expectedInput=this.getMSM().getMentalEntityByType("SingleInputTaskFact");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("SingleInputTaskFact");
			 } else {
			    addExpectedInputs(tobject, "SingleInputTaskFact","1",expectedInput);
             	addConsumedInput(to,"1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
	     
		    {SingleInputTaskOutput expectedOutputSingleInputTaskOutput=		    
		     new SingleInputTaskOutput(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputSingleInputTaskOutput,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;
	      /*if (alreadyExists && repeatedOutputs.size()!=0){ 
	      StringBuffer poutcomes=new StringBuffer();
	      for (int j=0;j<repeatedOutputs.size();j++){
			poutcomes.append(repeatedOutputs.elementAt(j).toString()+",");
	      }
	      MainInteractionManager.log("Non conversational initialisation discarded task "+tobject.getType()+
		 " to achieve goal AGoal because a previous outcome exists:"+poutcomes,getLocalName()+"-"+tobject.getType());
	      return false;
		} else */
		if (!allEntitiesExist){
			StringBuffer nonexisting=new StringBuffer();
			for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting.append(nonExistingInputs.elementAt(j).toString()+",");
			}
			
			MainInteractionManager.log("Non conversational initialisation discarded task "+tobject.getType()+
				" to achieve goal AGoal because did not have all preconditions. Missing elements are:"+nonexisting+". Currently, the MS has the following elements:"+this.getMSM().getAllMentalEntities(),getLocalName()+"-"+tobject.getType());
		}
		return initialised;	       
	      }
                 
                  
         
         nonExistingInputs.clear();
  		 repeatedOutputs.clear();
         if (tobject.getType().equals("MultipleInputTask")){
            Vector<MentalEntity> expectedInput=null;
            RuntimeFact expectedOutput=null;
            RuntimeEvent expectedOutputEvent=null;
			RuntimeConversation expectedInt=null;
            ingenias.jade.components.Resource expectedResource=null;
			ingenias.jade.components.Application expectedApp=null;        
			boolean allEntitiesExist=true;		
			TaskOutput to=null;
			to=new TaskOutput("default");
		
             expectedInput=this.getMSM().getMentalEntityByType("OtherFrameFact");
             if (expectedInput.size()==0 && !("1".equals("0..n"))){
				nonExistingInputs.add("OtherFrameFact");
			 } else {
			  addExpectedInputs(tobject, "OtherFrameFact","1",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;             
             
	      
	      
		
             expectedInput=this.getMSM().getMentalEntityByType("AFrameFact");
             if (expectedInput.size()==0 && !("n".equals("0..n"))){
				nonExistingInputs.add("AFrameFact");
			 } else {
			    addExpectedInputs(tobject, "AFrameFact","n",expectedInput);
             	addConsumedInput(to,"n",expectedInput);
			 }
             allEntitiesExist=allEntitiesExist&& expectedInput.size()!=0;

	      
	      
	     
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("MyApplication");
             tobject.addApplication("MyApplication",expectedApp);
	      
	      
	     // Default application for all tasks executed within a conversation
	     expectedApp=(ingenias.jade.components.Application)getAM().getApplication("YellowPages");
             tobject.addApplication("YellowPages",expectedApp);
	 /*    
	     
	      */	  
	      
	      /**/	      
	      
 
	     
	     
		    {AnotherFrameFact expectedOutputAnotherFrameFact=		    
		     new AnotherFrameFact(MentalStateManager.generateMentalEntityID());			
             to.add(new OutputEntity(expectedOutputAnotherFrameFact,TaskOperations.CreateWF));
            }
	     
     
     		      
	      tobject.addOutput(to);
	      initialised= allEntitiesExist;
	      /*if (alreadyExists && repeatedOutputs.size()!=0){ 
	      StringBuffer poutcomes=new StringBuffer();
	      for (int j=0;j<repeatedOutputs.size();j++){
			poutcomes.append(repeatedOutputs.elementAt(j).toString()+",");
	      }
	      MainInteractionManager.log("Non conversational initialisation discarded task "+tobject.getType()+
		 " to achieve goal AGoal because a previous outcome exists:"+poutcomes,getLocalName()+"-"+tobject.getType());
	      return false;
		} else */
		if (!allEntitiesExist){
			StringBuffer nonexisting=new StringBuffer();
			for (int j=0;j<nonExistingInputs.size();j++){
				nonexisting.append(nonExistingInputs.elementAt(j).toString()+",");
			}
			
			MainInteractionManager.log("Non conversational initialisation discarded task "+tobject.getType()+
				" to achieve goal AGoal because did not have all preconditions. Missing elements are:"+nonexisting+". Currently, the MS has the following elements:"+this.getMSM().getAllMentalEntities(),getLocalName()+"-"+tobject.getType());
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
         
         //************************************
         // Non conversational tasks evaluation
         //************************************
         
         if (goalname.equals("AGoal")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new SingleInputTaskTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal AGoal",getLocalName()+"-"+tobject.getType());
					tasks.add(tobject);
				 } 			
	     }
         
          }
                  
         
         if (goalname.equals("AGoal")){
         
         {
         boolean canbescheduled=false;
		 Task tobject=null;		 
				// If a conversational initialization fails, a conventional one is tried
				 tobject=new MultipleInputTaskTask(ingenias.jade.MentalStateManager.generateMentalEntityID());
				 canbescheduled=initialiseNonConversationalTask(tobject);
			 	 if (canbescheduled){
					MainInteractionManager.log("Scheduled task "+tobject.getType()+" to achieve goal AGoal",getLocalName()+"-"+tobject.getType());
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
		 
         
         
         ttypes.add("SingleInputTask");		         
                
         
         
         ttypes.add("MultipleInputTask");		         
                
         
         if (IAFProperties.getGraphicsOn())
          this.getGraphics().setKnownTasks(ttypes);

   // Interactions started by this agent		
   
   boolean continueInit=false;
   // Interactions where this agent acts as collaborator
   

   // These are the initial goals of the agent. Goals determine
   // which task to execute first    
   ingenias.editor.entities.StateGoal sg=null;
   ingenias.editor.entities.RuntimeFact ff=null;
   Slot slot=null;	  
   ObjectSlot oslot=null;
   ingenias.jade.components.Application app=null;	  
   
   sg= new ingenias.editor.entities.StateGoal("AGoal");
   sg.setState("pending");
      try {
	   this.getMSM().addMentalEntity(sg);
   } catch (InvalidEntity e1) {

	   e1.printStackTrace();
   }
   
   
   
   ff= new OtherFrameFact();      
   
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

     //Initial applications assigned to the agent	  
   
     app=MyApplicationInit.getInstance(this);
     //app.registerOwner(this);
	
     	    
     this.getAM().addApplication("MyApplication",app);        
	 events=new Vector();
	 actions=new Vector();

	 if (getGraphics()!=null)
	  getGraphics().addApplication("MyApplication", events,actions);    


   



   // Panel creation for interaction control
   // This panel shows a button for each interaction it starts.
   // If this agent does not start any interaction, a label showin
   // a message "DOES NOT START ANY INTERACTION" will appear
   java.awt.event.ActionListener ifPressed=null;
      

    
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
                
                
                result=new  DFAgentDescription[playedRoles.size()];
                playedRoles.toArray(result);
		return result;

	}


}

