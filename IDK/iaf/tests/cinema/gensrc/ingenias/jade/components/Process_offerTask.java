

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



public class Process_offerTask extends Task{

 public Process_offerTask(String id){
  super(id,"Process_offer");
 }



 public void execute() throws TaskException{


        Offer  eiOffer=(Offer)this.getFirstInputOfType("Offer");             

        RequestedMovie  eiRequestedMovie=(RequestedMovie)this.getFirstInputOfType("RequestedMovie");             

        CinemaProfile  eiCinemaProfile=(CinemaProfile)this.getFirstInputOfType("CinemaProfile");             

        Movie  eiMovie=(Movie)this.getFirstInputOfType("Movie");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		SearchTimeExceeded outputsdefaultSearchTimeExceeded=
			(SearchTimeExceeded)
				outputsdefault.getEntityByType("SearchTimeExceeded");
		
		
		Pay_the_money outputsdefaultPay_the_money=
			(Pay_the_money)
				outputsdefault.getEntityByType("Pay_the_money");
		
		Not_interested outputsdefaultNot_interested=
			(Not_interested)
				outputsdefault.getEntityByType("Not_interested");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:Process offer code <--- DO NOT REMOVE THIS	
		int price=eiOffer.getprice();
		Hashtable<String, Integer> score = eiCinemaProfile.getscoreForLastCinemasVisited();
		if (price<=eiMovie.getpreferredPrice()){
			outputsdefault.remove(outputsdefaultNot_interested);
			score.put(eiRequestedMovie.getcinemaName(),
					score.get(eiRequestedMovie.getcinemaName())+1);
		} else {			
			outputsdefault.remove(outputsdefaultPay_the_money);
			score.put(eiRequestedMovie.getcinemaName(),
					score.get(eiRequestedMovie.getcinemaName())-1);
		}
//#end_node:Process offer code <--- DO NOT REMOVE THIS

 }
 
}

 