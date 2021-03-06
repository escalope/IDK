

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



public class Process_ticket_obtainedTask extends Task{

 public Process_ticket_obtainedTask(String id){
  super(id,"Process_ticket_obtained");
 }



 public void execute() throws TaskException{


        Movie  eiMovie=(Movie)this.getFirstInputOfType("Movie");             

        Find_a_movie  eiFind_a_movie=(Find_a_movie)this.getFirstInputOfType("Find_a_movie");             

        Ticket_data  eiTicket_data=(Ticket_data)this.getFirstInputOfType("Ticket_data");             





			
        UserGUIApp eaUserGUI=(UserGUIApp)this.getApplication("UserGUI");




        // This means that the task participates in the interaction Asking_for_a_ticket
        RuntimeConversation  conversationContextAsking_for_a_ticket=this.getConversationContext();


  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:Process ticket obtained code <--- DO NOT REMOVE THIS	
        int seat=Integer.parseInt(eiTicket_data.getseat());
        
        int price=eiTicket_data.getprice();
					
		eaUserGUI.presentTicket(seat, price);















//#end_node:Process ticket obtained code <--- DO NOT REMOVE THIS

 }
 
}

 