package cinema.helpers;

import ingenias.editor.entities.RuntimeConversation;
import ingenias.jade.AgentExternalDescription;
import ingenias.jade.components.TaskOutput;
import ingenias.jade.components.YellowPages;
import ingenias.jade.mental.CinemaProfile;
import ingenias.jade.mental.Movie;
import ingenias.jade.mental.RequestedMovie;
import ingenias.jade.mental.SearchTimeExceeded;

import jade.core.AID;

import java.util.Hashtable;
import java.util.Vector;

public class CinemaSelectionHelper {
	public static void firstCinemaSelection(String aid, Movie eiMovie, CinemaProfile eiCinemaProfile,
			RuntimeConversation outputsdefaultBuy_ticket,
			RequestedMovie outputsdefaultRequestedMovie, YellowPages yp) {
		jade.domain.FIPAAgentManagement.DFAgentDescription[] agents;        

		try {
			agents = yp.getAgents("Seller");
			if (agents==null){
				// it was not possible to consult the yellow pages after a long time. Hence, we keep on trying again
				// When there are no agents playing role "seller" the getAgents returns an empty array, instead.
				while (agents==null){
					agents = yp.getAgents("Seller");
				}
			} 
			Hashtable<String, Integer> scores = eiCinemaProfile.getscoreForLastCinemasVisited();
			Vector<String> candidates=new Vector<String>();
			int max=Integer.MIN_VALUE;
			String selected="";
			for ( jade.domain.FIPAAgentManagement.DFAgentDescription agent:agents){
				String cinemaName=agent.getName().getLocalName();
				if (scores.contains(cinemaName)){
					if (scores.get(cinemaName)>max){
						selected=cinemaName;
						max=scores.get(cinemaName);
					}
				} else {
					scores.put(cinemaName, 0);
					if (0>max){
						selected=cinemaName;
						max=0;
					}
				}            	
			}

			System.out.println("First try of "+aid+", deller agents found ............................."+agents.length);
			System.out.println("First try of "+aid+", Choosing  ............................."+selected);
			outputsdefaultBuy_ticket.addCollaborators(new AgentExternalDescription(new AID(selected,false),"Seller"));
			outputsdefaultRequestedMovie.setmovieName(eiMovie.getmovieName());
			outputsdefaultRequestedMovie.setcinemaName(selected);
			outputsdefaultRequestedMovie.setsessions(eiMovie.getpreferredSessions());
			outputsdefaultRequestedMovie.setrequestedSeats(eiMovie.getpreferredSeats());
			outputsdefaultRequestedMovie.settriedCinemas(new Vector());
			outputsdefaultRequestedMovie.gettriedCinemas().add(selected);
			outputsdefaultRequestedMovie.setnumberIntents(1);
			Vector<String> extras=new Vector<String>();
			for (String extra:eiMovie.getpreferredExtras()){
				extras.add(extra);
			}
			outputsdefaultRequestedMovie.setrequestedExtras(extras);

		} catch (jade.domain.FIPAException e) {
			e.printStackTrace();
		}
	}
	public static void selectCinemaBasedOnScore(String aid, RequestedMovie eiRequestedMovie,
			TaskOutput outputsdefault,
			RuntimeConversation outputsdefaultBuy_ticket,
			SearchTimeExceeded outputsdefaultSearchTimeExceeded,
			YellowPages yp,			
			Hashtable<String, Integer> scores) {
		jade.domain.FIPAAgentManagement.DFAgentDescription[] agents;
		try {
			eiRequestedMovie.setnumberIntents(eiRequestedMovie.getnumberIntents()+1);
			agents = yp.getAgents("Seller");
			if (agents !=null && eiRequestedMovie.getnumberIntents()<agents.length){			
				// sometimes agents==null due to failures in the JADE platform
				Vector<String> candidates=new Vector<String>();
				int max=Integer.MIN_VALUE;
				String selected="";
				for ( jade.domain.FIPAAgentManagement.DFAgentDescription agent:agents){
					String cinemaName=agent.getName().getLocalName();
					if (!eiRequestedMovie.gettriedCinemas().contains(cinemaName)){
						if (scores.contains(cinemaName)){
							if (scores.get(cinemaName)>max){
								selected=cinemaName;
								max=scores.get(cinemaName);
							}
						} else {
							scores.put(cinemaName, 0);
							if (0>max){
								selected=cinemaName;
								max=0;
							}        				
						}    
					}
				}
				System.out.println("Next try agent for "+aid+". Seller agents found "+agents.length+" trying now with "+selected);
				
				if (!selected.equals("")){
					outputsdefaultBuy_ticket.addCollaborators(new AgentExternalDescription(new AID(selected,false),"Seller"));
					eiRequestedMovie.setcinemaName(selected);
					eiRequestedMovie.gettriedCinemas().add(selected);
					outputsdefault.remove(outputsdefaultSearchTimeExceeded);				
				} else {
					outputsdefault.remove(outputsdefaultBuy_ticket);
				//	System.err.println("No more tries "+aid);
				}
			} else {
				outputsdefault.remove(outputsdefaultBuy_ticket);
				System.err.println("No more tries "+aid);
			}
		} catch (jade.domain.FIPAException e) {
			e.printStackTrace();
		}
	}
}
