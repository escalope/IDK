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

import java.util.Vector;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;

/**
 * This application can perform searches in the Domain Facilitator. However, it can be invoked only within
 * an agent behavior, otherwise the operation will cause a hang. 
 * 
 * @author jj
 *
 */
public class YellowPages extends Application {

	Agent ja;



	public YellowPages(Agent ja){
		this.ja=ja;
	}

	/**
	 * It returns a list of JADE DF descriptors of agents playing the role passed as paremeter. The role is 
	 * supposed to be the type of a service the agent offers
	 * 
	 * @param rolename The role the agent is supposed to play
	 * @return An array of descriptors of agents playing that role
	 * @throws FIPAException
	 */
	public synchronized DFAgentDescription[] getAgents(String rolename) throws FIPAException {
		//System.err.println("searching "+rolename+ " "+this.getColDescription(rolename));
		SearchConstraints searchcons= new SearchConstraints();
		searchcons.setMaxDepth(5l); // To search within federated DFs
		searchcons.setMaxResults(100l); // To return 100 matches as much
		DFAgentDescription[] result=null;		
		int k=0;
		while (result==null && k<10){
			try {
				AID dfName;
				result= jade.domain.DFService.searchUntilFound(ja, ja.getDefaultDF(), this.getColDescription(rolename), searchcons, 20000);
				// Lower values than 20 seconds timeout lead to null values returned. See ingenias.testing.TestJadeDFSearch
			}catch (FIPAException fe){

			}
			k=k+1;
		} 
		return result;
	}

	/**
	 * It obtains an array of agents whose local id is the one passed as parameter. There can be several unique
	 * agent ids that share the same local id. They may differ only in the platform they live into
	 * 
	 * @param localid The name of the agent
	 * @return The JADE DF descriptor of all agents having the indicated local id
	 * @throws FIPAException
	 */
	public DFAgentDescription[] getAgentFromLocalID(String localid) throws FIPAException {
		
		SearchConstraints searchcons= new SearchConstraints();
		
		searchcons.setMaxDepth(2l); // To search within federated DFs
		searchcons.setMaxResults(100l); // To return 100 matches as much
		DFAgentDescription[] result=null;		
		int k=0;
		
		//while (result==null && k<500){
			try {
				AID dfName;
				System.err.println("Try "+k+" looking for agent with localid "+localid);
				result= jade.domain.DFService.search(ja, new DFAgentDescription(), 
						searchcons);
				if (result!=null){
				
				Vector<DFAgentDescription> descriptors=new Vector<DFAgentDescription>();
				for (DFAgentDescription desc:result){
					System.err.println("found "+desc.getName().getLocalName());
					if (desc.getName().getLocalName().equalsIgnoreCase(localid)){
						descriptors.add(desc);
					}
				}
				System.err.println("found "+descriptors);
				result=descriptors.toArray(new DFAgentDescription[descriptors.size()]);
				// Lower values than 20 seconds timeout lead to null values returned. See ingenias.testing.TestJadeDFSearch
				}
			}catch (FIPAException fe){
fe.printStackTrace();
			}
			k=k+1;
		//} 
		System.err.println("found "+result);
		return result;
	}

	/**
	 *  Given a role name, it obtains a JADE DF descriptor. The descriptor contains a service description
	 *  with the role name as type
	 *
	 *@param  rolename  The name of the role 
	 *@return       a JADE DF descriptor for that role
	 */
	public DFAgentDescription getColDescription(String rolename) {
		// Tiene sentido en iniciadores
		DFAgentDescription dfd;
		ServiceDescription sd;
		dfd = new DFAgentDescription();
		sd = new ServiceDescription();
		sd.setType(rolename);
		dfd.addServices(sd);
		return dfd;

	}

	/**
	 *  It obtains a descriptor for searching agents with the same local id. The local id is the name parameter
	 *  of the DF Agent descriptor
	 *
	 *@param  localid  The local id of the agent
	 *@return       The JADE DF descriptor for the local id
	 */
	public DFAgentDescription getColDescriptionFromLocalID(String localid) {
		// Tiene sentido en iniciadores
		DFAgentDescription dfd;
		dfd = new DFAgentDescription();
		dfd.setName(new AID(localid,false));
		return dfd;

	}

}
