

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


package ingenias.jade.components;

import java.util.*;
import ingenias.jade.exception.*;
import ingenias.jade.comm.*;
import ingenias.jade.mental.*;
import ingenias.editor.entities.*;



public class Task0Task extends Task{

 public Task0Task(String id){
  super(id,"Task0");
 }



 public void execute() throws TaskException{


        MyFrameFact  eiMyFrameFact=(MyFrameFact)this.getFirstInputOfType("MyFrameFact");             

        fake_ProcessAGUIEvent_output_for_task_Task0  eifake_ProcessAGUIEvent_output_for_task_Task0=(fake_ProcessAGUIEvent_output_for_task_Task0)this.getFirstInputOfType("fake_ProcessAGUIEvent_output_for_task_Task0");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		RuntimeConversation outputsdefaultInteraction0=
			(RuntimeConversation)
				outputsdefault.getEntityByType("Interaction0");				
		
			
		
		
		
		fake_Task0_output_for_task_Task1 outputsdefaultfake_Task0_output_for_task_Task1=
			(fake_Task0_output_for_task_Task1)
				outputsdefault.getEntityByType("fake_Task0_output_for_task_Task1");								
		
		AnotherFact outputsdefaultAnotherFact=
			(AnotherFact)
				outputsdefault.getEntityByType("AnotherFact");								
		
		
		
        YellowPages yp=null; // only available for initiators of interactions
	
        
	
		// This task can produce an interaction of type Interaction0 by working with its conversation object
        
        // To define manually who are the collaborator involved. Your selection will be verified
        // in runtime. Pay attention to log messages to detect errors. You can use the yello pages
        // service to locate other agents
        yp=(YellowPages)this.getApplication("YellowPages");

        //  Uncomment the following and write down a proper local id of the agent
        // Find an agent playing the role "Role1"
      	//eoInteraction0.addCollaborators("Local ID of the collaborator");
       	
        //  Uncomment the following and write down a proper local id of the agent
        // Find an agent playing the role "Role2"
      	//eoInteraction0.addCollaborators("Local ID of the collaborator");
       	

        
	
		// This task can produce an interaction of type Interaction0 by working with its conversation object
        
        // To define manually who are the collaborator involved. Your selection will be verified
        // in runtime. Pay attention to log messages to detect errors. You can use the yello pages
        // service to locate other agents
        yp=(YellowPages)this.getApplication("YellowPages");

        //  Uncomment the following and write down a proper local id of the agent
        // Find an agent playing the role "Role1"
      	//eoInteraction0.addCollaborators("Local ID of the collaborator");
       	
        //  Uncomment the following and write down a proper local id of the agent
        // Find an agent playing the role "Role2"
      	//eoInteraction0.addCollaborators("Local ID of the collaborator");
       	


//#start_node:INGENIASCodeComponent2 <--- DO NOT REMOVE THIS	
System.out.println("Task0");
//#end_node:INGENIASCodeComponent2 <--- DO NOT REMOVE THIS

 }
 
}

 