

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



public class Determine_availabilityTask extends Task{

 public Determine_availabilityTask(String id){
  super(id,"Determine_availability");
 }



 public void execute() throws TaskException{


        RequestedMovie  eiRequestedMovie=(RequestedMovie)this.getFirstInputOfType("RequestedMovie");             

        CurrentMovies  eiCurrentMovies=(CurrentMovies)this.getFirstInputOfType("CurrentMovies");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		Offer outputsdefaultOffer=
			(Offer)
				outputsdefault.getEntityByType("Offer");
		
		TicketNotAvailable outputsdefaultTicketNotAvailable=
			(TicketNotAvailable)
				outputsdefault.getEntityByType("TicketNotAvailable");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:Determine availability code <--- DO NOT REMOVE THIS	
        String movieName=eiRequestedMovie.getmovieName();
        
        if (eiCurrentMovies.getstartingTimeMovie().containsKey(movieName)){
        	Vector<String> extras = eiCurrentMovies.getavailableExtras();
        	Vector<String> aextras=eiRequestedMovie.getrequestedExtras();
        	if (extras.containsAll(aextras)){
        		Vector<String> preferredSeats=eiRequestedMovie.getrequestedSeats();
        		Hashtable<String, Vector<String>>  aseats=eiCurrentMovies.getavailableSeats().get(movieName);
        		Vector<String> sessions = eiRequestedMovie.getsessions();
        		boolean found=false;
        		int j=0;
        		int k=0;           		
        		while (!found && j<sessions.size()){
        			if (aseats.containsKey(sessions.elementAt(j))){	
        				k=0;        		
        				while (!found && k<preferredSeats.size()){
        					found=aseats.contains(preferredSeats.elementAt(k));
        					if (!found)
        						k++;
        				}
        			}
        			if (!found)
        				j++;
        		}
        		if (found){
        			outputsdefaultOffer.setofferedSeats(aseats.get(sessions.elementAt(j)).elementAt(k));
        			outputsdefaultOffer.setprice(eiCurrentMovies.getprice());
        			outputsdefaultOffer.setsession(sessions.elementAt(j));
        			outputsdefaultOffer.setmovieName(movieName);
        			outputsdefault.remove(outputsdefaultTicketNotAvailable);

        		} else {
        			outputsdefault.remove(outputsdefaultOffer);
        			outputsdefaultTicketNotAvailable.setreason(cinema.types.TicketRequestRejectedType.NO_SEATS);
        		}
        	} else {
        		outputsdefault.remove(outputsdefaultOffer);
        		outputsdefaultTicketNotAvailable.setreason(cinema.types.TicketRequestRejectedType.NO_EXTRAS);
        	}
        } else {
        	outputsdefault.remove(outputsdefaultOffer);
        	outputsdefaultTicketNotAvailable.setreason(cinema.types.TicketRequestRejectedType.NO_MOVIE);
        }
      //  System.err.println("seller:Procesada respuesta "+this.getConversationContext().getConversationID());



//#end_node:Determine availability code <--- DO NOT REMOVE THIS

 }
 
}

 