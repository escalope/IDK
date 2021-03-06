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

import ingenias.jade.mental.OrganizationDescription;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.FIPAManagementVocabulary;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * This application can perform searches in the Domain Facilitator. However, it can be invoked only within
 * an agent behavior, otherwise the operation will cause a hang. 
 * 
 * @author jj
 *
 */
public class YellowPages extends Application {

	Agent ja;
	private DFAgentDescription[]  searchResult;
	private boolean searchFinished;
	private Hashtable<String, OrganizationDescription> agentOrganizations=new Hashtable<String, OrganizationDescription>();

	/**
	 * It tells which organizations this agent is registered to
	 * @return
	 */
	public Set<String> getAgentOrganizations(){
		return agentOrganizations.keySet();
	}

	/**
	 * It returns the groups within the organization to which this agent belongs
	 * @param orgid
	 * @return
	 */
	public Vector<String> getAgentGroups(String orgid){
		Vector<String> groups=new Vector<String>();
		OrganizationDescription orgd=agentOrganizations.get(orgid);
		if (orgd.getOrgID().equalsIgnoreCase(orgid)){
			for ( String cgroups:orgd.getGroups()){
				for ( String member:orgd.getMembers(cgroups)){
					if (member.equalsIgnoreCase(ja.getLocalName()))
						groups.add(cgroups);
				}			
			}
		}
		return groups;
	}

	public Vector<String> getAgentIDsInOrganization(String o){
		Vector<String> aids=new Vector<String>();
		for (OrganizationDescription orgd:agentOrganizations.values())			
			for (String group:orgd.getGroups()){
				aids.addAll(orgd.getMembers(group));						
			}
		return aids;
	}

	public Vector<String> getAgentIDsInGroup(String groupid){
		Vector<String> aids=new Vector<String>();
		for (OrganizationDescription orgd:agentOrganizations.values())			
			for (String group:orgd.getGroups()){
				if (orgd.getMembers(group).contains(ja.getLocalName())){
					aids.addAll(orgd.getMembers(group));				
					HashSet<String> subgroups=orgd.getSubgroups(group);
					for (String subgroup:subgroups){
						aids.addAll(orgd.getMembers(subgroup));		
					}
				}
			}

		return aids;
	}

	public YellowPages(Agent ja){
		this.ja=ja;
	}

	public synchronized DFAgentDescription[] getAgents(String orgid, String rolename) throws FIPAException {
		DFAgentDescription[] agentsfound = getAgents(rolename); 
		Vector<DFAgentDescription> filteredAgents = new Vector<DFAgentDescription>();
		Vector<String> agentids=new Vector<String>();
		agentids.addAll(getAgentIDsInOrganization(orgid));
		
		for (DFAgentDescription agent:agentsfound){
			if (agentids.contains(agent.getName().getLocalName()))
				filteredAgents.add(agent);
		}
		return filteredAgents.toArray(new DFAgentDescription[filteredAgents.size()]);
	}

	public synchronized DFAgentDescription[] getAgents(String orgid, String groupid, String rolename) throws FIPAException {
		DFAgentDescription[] agentsfound = getAgents(rolename); 
		Vector<DFAgentDescription> filteredAgents = new Vector<DFAgentDescription>();
		Vector<String> groups = getAgentGroups(orgid);
		Vector<String> agentids=new Vector<String>();
		for (String gid:groups){
			if (gid.equalsIgnoreCase(groupid))
				agentids.addAll(getAgentIDsInGroup(gid));
		}
		for (DFAgentDescription agent:agentsfound){
			if (agentids.contains(agent.getName().getLocalName()))
				filteredAgents.add(agent);
		}
		return filteredAgents.toArray(new DFAgentDescription[filteredAgents.size()]);
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
		searchResult=null;
		searchFinished=false;
		ACLMessage requestMessage=jade.domain.DFService.createRequestMessage(ja,ja.getDefaultDF(),  FIPAManagementVocabulary.SEARCH,
				this.getColDescription(rolename), searchcons);
		requestMessage.setReplyByDate(new Date(new Date().getTime()+10000)); // set timeout to 10 seconds
		AchieveREInitiator request=new AchieveREInitiator(ja,
				requestMessage){
			@Override
			protected void handleAgree(ACLMessage agree) {
				// TODO Auto-generated method stub
				super.handleAgree(agree);
				searchFinished=true;
			}

			@Override
			protected void handleRefuse(ACLMessage refuse) {
				// TODO Auto-generated method stub
				super.handleRefuse(refuse);
				searchFinished=true;
			}
			@Override
			public void handleInform(ACLMessage inform){
				try {
					searchResult=DFService.decodeResult(inform.getContent());
				} catch (FIPAException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				searchFinished=true;
			}
		};
		ja.addBehaviour(request);
		long timeout=30000;
		while (!searchFinished && timeout>0){
			try {
				long currentSleep=(long) (100*Math.random());
				Thread.currentThread().sleep(currentSleep);
				timeout=timeout-currentSleep;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (searchResult==null)
			new Exception("Null returned by DFService").printStackTrace();
		List<DFAgentDescription> list = Arrays.asList(searchResult);
		Collections.shuffle(list);
		return list.toArray(new DFAgentDescription[list.size()]);


		/*int k=0;
		while (result==null && k<10){
			try {
				AID dfName;
				result= jade.domain.DFService.searchUntilFound(ja, ja.getDefaultDF(), this.getColDescription(rolename), searchcons, 5000);
				// Lower values than 20 seconds timeout lead to null values returned. See ingenias.testing.TestJadeDFSearch
			}catch (FIPAException fe){
				fe.printStackTrace();
			}
			k=k+1;
		} 
		return result;*/
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
			//System.err.println("Try "+k+" looking for agent with localid "+localid);
			result= jade.domain.DFService.search(ja, new DFAgentDescription(), 
					searchcons);
			if (result!=null){

				Vector<DFAgentDescription> descriptors=new Vector<DFAgentDescription>();
				for (DFAgentDescription desc:result){
					//System.err.println("found "+desc.getName().getLocalName());
					if (desc.getName().getLocalName().equalsIgnoreCase(localid)){
						descriptors.add(desc);
					}
				}
				//System.err.println("found "+descriptors);
				result=descriptors.toArray(new DFAgentDescription[descriptors.size()]);
				// Lower values than 20 seconds timeout lead to null values returned. See ingenias.testing.TestJadeDFSearch
			}
		}catch (FIPAException fe){
			fe.printStackTrace();
		}
		k=k+1;
		//} 
		//System.err.println("found "+result);
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

	public Vector<String> getRegisteredOrganizationsWithType(String orgtype) {
		Vector<String> orgs=new Vector<String> ();
		for (OrganizationDescription orgdescs:agentOrganizations.values()){
			if (orgdescs.getOrgType().equalsIgnoreCase(orgtype))
				orgs.add(orgdescs.getOrgID());
		}
		return orgs;
	}

	public Vector<String>  getRegisteredGroupWithType(String grouptype) {
		Vector<String> groups=new Vector<String> ();		
		for (OrganizationDescription orgdescs:agentOrganizations.values()){
			for (String groupid:orgdescs.getGroups()){
				if (orgdescs.getGroupType(groupid).equalsIgnoreCase(grouptype))
					groups.add(groupid);	
			}

		}
		return groups;
	}

	public void processOrganization(OrganizationDescription orgdesc) {
		agentOrganizations.put(orgdesc.getOrgID(),orgdesc);


	}

}
