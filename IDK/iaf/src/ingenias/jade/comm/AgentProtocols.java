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

package ingenias.jade.comm;

import ingenias.editor.entities.RuntimeConversation;
import ingenias.jade.AgentExternalDescription;
import ingenias.jade.MentalStateReader;
import ingenias.jade.MentalStateUpdater;
import ingenias.jade.WrongInteraction;
import ingenias.jade.components.YellowPages;
import ingenias.jade.exception.NoAgentsFound;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

public interface AgentProtocols {
	/**
	 * It returns a descriptor of the agents involved into an interaction. Each
	 * descriptor points at specific agents that satisfy interaction roles. Inheritors
	 * of this class must override this method to declare which actors are desired.
	 * IMPORTANT: the agent owner of this class will not be returned as interaction actor.
	 * @param interaction The name of the interaction
	 * @return An array of agent descriptors
	 * @throws ingenias.jade.exception.NoAgentsFound
	 */
	public AgentExternalDescription[] getInteractionActors(String interaction, YellowPages yp) 
	 throws ingenias.jade.exception.NoAgentsFound;
	
	
	/**
	 * It certifies that agents involved in a protocol provide the requested service. Each agent
	 * participates within interactions with, maybe, several agents. These agents are supposed
	 * to play certain roles according to the needs of this agent. The method ensures that, given
	 * a protocol and a set of agents to collaborate with, those agents play the roles this 
	 * interaction needs.
	 * 
	 * @param protocol The name of the protocol to verify
	 * @param actors The actors to be studied
	 * @return true if the actors play the roles required
	 */
	public  boolean verifyActors(String protocol, AgentExternalDescription[ ] actors);
	 // This method tells for each interaction type, the roles played. The description is
	 // an instance of DFAgentDescription. An agent can play several roles within the same
	 // interaction. Therefore, when this agent is requested to participate
	 
	//public DFAgentDescription[ ] getDescription(String protocol, AID agentID) ;
	
	/**
	 * The method creates specific state behaviors and default comm controls to deal 
	 * with a concrete interaction type. These classes require as input the list of
	 * actors that will participate, the mental entity representing the interaction,
	 * an interface to read the mental state of the agent (to determine what information
	 * to send, for instance), and an interace to the Locks manager in order to remove
	 * information locks on mental state entities.
	 *  
	 * 
	 * @param conv The mental entity representing the interaction. It will be used as information
	 * source for other internal initializations
	 * @param msr The mental state reader whose purpose is to provide an interface with the mental 
	 * state of the agent
	 * @param lr The locks remover which will allow to remove information locks
	 * @param actors The list of participating actors
	 * @return An object containing the state behavior, the new default comm control, and other related
	 * information
	 * @throws NoAgentsFound
	 */
	public ActiveConversation initialiseProtocols(String agentName,RuntimeConversation conv, 
			MentalStateReader msr, MentalStateUpdater msu,
			ingenias.jade.comm.LocksRemover lr,  
			AgentExternalDescription[] actors) throws NoAgentsFound,WrongInteraction;
	 
	
}
