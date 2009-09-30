

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



public class ChooseMovieTask extends Task{

 public ChooseMovieTask(String id){
  super(id,"ChooseMovie");
 }



 public void execute() throws TaskException{


        User_wants_to_watch_a_movie  eiUser_wants_to_watch_a_movie=(User_wants_to_watch_a_movie)this.getFirstInputOfType("User_wants_to_watch_a_movie");             

        AssignedBuyer  eiAssignedBuyer=(AssignedBuyer)this.getFirstInputOfType("AssignedBuyer");             

        MoviesProfile  eiMoviesProfile=(MoviesProfile)this.getFirstInputOfType("MoviesProfile");             





			
        UserGUIApp eaUserGUI=(UserGUIApp)this.getApplication("UserGUI");





  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		RuntimeConversation outputsdefaultAsking_for_a_ticket=
			(RuntimeConversation)
				outputsdefault.getEntityByType("Asking_for_a_ticket");
		
		
		Find_a_movie outputsdefaultFind_a_movie=
			(Find_a_movie)
				outputsdefault.getEntityByType("Find_a_movie");
		
		Movie outputsdefaultMovie=
			(Movie)
				outputsdefault.getEntityByType("Movie");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions

		// This task can produce an interaction of type Asking_for_a_ticket by working with its conversation object
        
        // To define manually who are the collaborator involved. Your selection will be verified
        // in runtime. Pay attention to log messages to detect errors. You can use the yello pages
        // service to locate other agents
        yp=(YellowPages)this.getApplication("YellowPages");

        //  Uncomment the following and write down a proper local id of the agent
        // Find an agent playing the role "Buyer"
      	//eoAsking_for_a_ticket.addCollaborators("Local ID of the collaborator");
       	


//#start_node:ChooseMovie code <--- DO NOT REMOVE THIS	
        outputsdefaultMovie.setpreferredPrice(eiMoviesProfile.getpreferredPrice());        
        outputsdefaultMovie.setmovieName(eiMoviesProfile.getmoviesILike().elementAt(0));
        outputsdefaultMovie.setpreferredExtras(eiMoviesProfile.getpreferredExtras());
        outputsdefaultMovie.setpreferredSeats(eiMoviesProfile.getpreferredSeats());        
        outputsdefaultMovie.setpreferredSessions(eiMoviesProfile.getpreferredSessions());
        String agentid=this.getAgentID();
        int index=agentid.lastIndexOf("_");
        outputsdefaultAsking_for_a_ticket.addCollaborators(new ingenias.jade.AgentExternalDescription(
        		new jade.core.AID(eiAssignedBuyer.getbuyerName(),false),"Buyer"));   

//#end_node:ChooseMovie code <--- DO NOT REMOVE THIS

 }
 
}

 