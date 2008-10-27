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

import ingenias.editor.entities.*;
import ingenias.exception.InvalidEntity;
import ingenias.jade.JADEAgent;
import ingenias.jade.MentalStateReader;
import ingenias.jade.MentalStateUpdater;
import ingenias.jade.components.YellowPages;
import ingenias.jade.exception.NoAgentsFound;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.testing.DebugUtils;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;

import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 * It manages the ongoing conversations and allows to create new ones.
 * 
 * @author Jorge
 * 
 */
public class ConversationManagement {

	/**
	 * It associates a Conversation id with the played role. An agent can play
	 * different roles within the same conversation. Therefore, it is required
	 * to distinguish the state behaviors referring to the conversations
	 * associated to one role and others. This is achieved making the id of a
	 * conversation the combination of the conversation id and the role played
	 * 
	 * @author jj
	 * 
	 */
	private class Pair {
		String cid;
		String playedRole;

		public Pair(String cid, String playedRole) {
			this.cid = cid;
			this.playedRole = cid;
		}

		@Override
		public int hashCode() {
			return (cid.hashCode() + playedRole.hashCode()) % Integer.MAX_VALUE;
		}

		public boolean equals(Object obj) {
			if (obj instanceof Pair) {
				return ((Pair) obj).cid.equals(cid)
				&& ((Pair) obj).playedRole.equals(playedRole);
			} else
				return super.equals(obj);
		}
	}

	private int cid = 0; // a counter to store different conversation ids
	private Vector<Pair> conversationIDs = new Vector<Pair>(); // The different
	// conversations
	// maintaind up
	// to now
	private Vector pendingMessages = new Vector(); // a list of messages not
	// processed yet
	protected Vector knownProtocols = new Vector(); // a list of protocols known
	// by the agent
	protected Hashtable<String, String> initiatorRoles = new Hashtable<String, String>();
	private Vector<StateBehavior> smachines = new Vector<StateBehavior>();
	private Hashtable<Pair, StateBehavior> activeMachines = new Hashtable<Pair, StateBehavior>(); // a
	// list
	// of
	// ongoing
	// conversations
	private Vector<DefaultCommControl> defaultControl = new Vector<DefaultCommControl>();
	private JADEAgent agent = null; // the agent owning the conversations
	private AgentProtocols ap = null; // An interface to create new
	// conversations and perform interaction
	// specific operations

	public ConversationManagement(JADEAgent agent, AgentProtocols ap) {
		this.agent = agent;
		this.ap = ap;
	}

	public void add(DefaultCommControl dcc) {
		this.defaultControl.add(dcc);
	}

	public void add(StateBehavior sb) {
		this.smachines.add(sb);
	}

	public int getPendingStateBehaviors() {
		return this.smachines.size();
	}

	public void getParticipants(String cid) {

	}

	/**
	 * It adds a message to the pending messages queue.
	 */
	public void addPending(ACLMessage acl) {
		pendingMessages.add(acl);
	}

	public Vector getKnownProtocols() {
		return this.knownProtocols;
	}

	public void removePending(ACLMessage acl) {
		pendingMessages.remove(acl);
	}

	public Enumeration getPendingMessages() {
		return ((Vector) pendingMessages.clone()).elements();
	}

	public String getCID() {
		String completeName=this.agent.getAID().getName();
		String id = this.agent.getLocalName()+completeName.substring(completeName.indexOf("@")+1,completeName.indexOf("@")+4);
		id = cid + "." + id+new Date().getTime();
		cid = cid + 1;
		return id;
	}

	public boolean contains(String cid, String role) {
		return conversationIDs.contains(new Pair(cid, role));
	}

	/**
	 * Adds a new conversation identifier. In a conversation, the identifier is
	 * an id shared by all members of the conversation. Within an agent, to
	 * identify a conversation, it is required to combine this identifier with
	 * the played role. The reason is that an agent can play different roles in
	 * the same conversation
	 * 
	 * @param cid
	 *            The conversation id inserted in each message
	 * @param playedRole
	 *            The role played in the conversation
	 */
	public void addCID(String cid, String playedRole) {
		if (!conversationIDs.contains(new Pair(cid, playedRole))) {
			conversationIDs.add(new Pair(cid, playedRole));
		}
	}

	/**
	 * It removes a conversation
	 * 
	 * @param cid
	 */
	public void removeCID(String cid) {
		this.conversationIDs.remove(cid);
	}

	/**
	 * Initiates one of the known protocols of the agent. As a result, the
	 * conversation ID of the protocol is returned. This ID will be used
	 * internally to update a Conversation entity inserted in the agent mental
	 * state which contains the current state of the protocol.
	 * 
	 * @param protocol
	 *            the name of the protocol
	 * @return The conversation id.
	 * @throws NoAgentsFound
	 */
	public ActiveConversation launchProtocolAsInitiator(String protocol,
			YellowPages yp) throws NoAgentsFound {
		String role = this.initiatorRoles.get(protocol);
		// System.err.println(initiatorRoles);
		ActiveConversation actconv = this.launchProtocolAsInitiator(protocol,
				role, yp);
		//System.err.println("actconv " + actconv + " " + this.agent.getMSM());
		try {
			this.agent.getMSM().addMentalEntity(actconv.getConv());
		} catch (InvalidEntity e) {

			e.printStackTrace();
		}
		this.add(actconv.getSb());

		return actconv;

	}

	/**
	 * Initiates one of the known protocols of the agent. As a result, the
	 * conversation ID of the protocol is returned. This ID will be used
	 * internally to update a Conversation entity inserted in the agent mental
	 * state which contains the current state of the protocol.
	 * 
	 * @param protocol
	 *            the name of the protocol
	 * @return The conversation id.
	 * @throws NoAgentsFound
	 */
	public ActiveConversation launchProtocolAsInitiator(String protocol,
			DFAgentDescription[] actors) throws NoAgentsFound {
		String role = this.initiatorRoles.get(protocol);
		ActiveConversation actconv = this.launchProtocolAsInitiator(protocol,
				role, actors, this.agent.getMSM(), this.agent.getMSM(),
				this.agent.getLM());
		try {
			this.agent.getMSM().addMentalEntity(actconv.getConv());
		} catch (InvalidEntity e) {

			e.printStackTrace();
		}
		this.add(actconv.getSb());
		DebugUtils.logEvent("StartingCollaboration", new String[]{protocol,actconv.getCid(),agent.getLocalName()});
		return actconv;

	}

	/**
	 * It starts the requested protocol as collaborator. The agent will play the
	 * role requested by the initiator. The other collaborators of the
	 * interaction are not set initially. This list of actors is determined in
	 * runtime inside of the DefaultCommControl specialization associated to
	 * each state behavior class. Concretely, it is the transition to enabled
	 * where this information is extracted, in the method updateActorList
	 * invoked from each DefaultCommControl
	 * 
	 * @param protocol
	 *            The protocol name to initiate
	 * @param requestedRole
	 *            The role that the agent has to play
	 * @return An object representing the conversation being constructed.
	 * @throws NoAgentsFound
	 */
	public ActiveConversation launchAsCollaborator(String protocol,
			String requestedRole, String cid) throws NoAgentsFound {
		ActiveConversation actconv = this.launchProtocolAsCollaborator(
				protocol, requestedRole, cid, null, this.agent.getMSM(),
				this.agent.getMSM(), this.agent.getLM());
		this.agent.getLM().addInteractionLocks(protocol);
		this.add(actconv.getSb());		
		try {
			this.agent.getMSM().addMentalEntity(actconv.getConv());
		} catch (InvalidEntity e) {
			e.printStackTrace();
		}
		DebugUtils.logEvent("CollaborationAccepted", new String[]{protocol,cid,agent.getLocalName()});
		return actconv;
	}

	/**
	 * It obtains the role of the initiator of the procotol id passed as
	 * parameter. If the agent does not play any initiator role, it would return
	 * null.
	 * 
	 * @param protocol
	 *            The id of a protocol
	 * @return The id of the role played
	 */
	public String getInitiatorRole(String protocol) {
		return this.initiatorRoles.get(protocol);
	}

	/**
	 * It starts a protocol assuming that the agent is a collaborator. When a
	 * collaborator starts an interaction, it is assumed that there is a pending
	 * message in the JADE message queue. The conversation id is already
	 * predetermined, which is the cid of the corresponding pending message. The
	 * initilization of the state behaviors require knowing a locks remover
	 * instance as well as a locks remover. The agent will play a concrete role
	 * within the interaction, and the same agent can play different roles
	 * within the interaction. The concrete role of this agent in the
	 * interaction is determined by the enabling message. The first message to a
	 * collaborator determines which role is expected this agent to play. This
	 * first message contains as well the list of agents which will participate
	 * in the interaction. This is necessary to continue the interactino when
	 * this agent has to send messages to other agents along the interaction,
	 * i.e., it has to do something different from receiving messages
	 * 
	 * @param protocol
	 *            The id of the protocol
	 * @param role
	 *            The role id played by the agent
	 * @param cid
	 *            The conversation id of the pending message
	 * @param actors
	 *            The actors involved in the interaction
	 * @param msr
	 *            The mental state reader needed to inspect the internal state
	 *            of the agent
	 * @param lr
	 *            The component responsible to remove locks on information
	 *            stored in the mental state
	 * @return An initialized Active Conversation object
	 * @throws NoAgentsFound
	 */
	protected ActiveConversation launchProtocolAsCollaborator(String protocol,
			String role, String cid, DFAgentDescription[] actors,
			MentalStateReader msr, MentalStateUpdater msu, LocksRemover lr)
	throws NoAgentsFound {
		int tries = 10;
		ActiveConversation aconv = null;
		boolean continueInit = false;
		RuntimeConversation conv = new RuntimeConversation(role
				+ "-" + cid);
		conv.setInteraction(new Interaction(protocol));
		conv.setConversationID(cid);
		conv.setPlayedRole(role);
		aconv = launchProtocol(actors, msr, msu, lr, tries, aconv,
				continueInit, conv);
		DebugUtils.logEvent("CollaborationAccepted", new String[]{protocol,cid,agent.getLocalName()});
		return aconv;
	}

	/**
	 * It launchs a protocol. This method is reused by initiator and
	 * collaborator interaction launchers. It adds a conversation object to the
	 * initial parameters, so that running interactions can use the conversation
	 * as information source for the conversation id and information sent along
	 * an interaction. In this method, actors are determined and this implies
	 * consulting the yellow pages service. Agents, or the network, can be down
	 * temporally, so the location of collaborators can fail. There is a timeout
	 * for such issue, and a number of times the connection can be tried. The
	 * number is determined by the parameter "tries".
	 * 
	 * @param actors
	 *            Actors participating in the interaction
	 * @param msr
	 *            The accessor to the mental state of the agent
	 * @param lr
	 *            A component to remove locks on information stored in the
	 *            mental state
	 * @param tries
	 *            Number of times the conversation is started
	 * @param aconv
	 *            The Active Conversation so far.
	 * @param continueInit
	 * @param conv
	 *            The conversation being
	 * @return The initialized active conversation
	 * @throws NoAgentsFound
	 */
	private ActiveConversation launchProtocol(DFAgentDescription[] actors,
			MentalStateReader msr, MentalStateUpdater msu, LocksRemover lr,
			int tries, ActiveConversation aconv, boolean continueInit,
			RuntimeConversation conv) throws NoAgentsFound {

		while (!continueInit) {
			try {

				aconv = this.ap.initialiseProtocols(agent.getName(), conv, msr,
						msu, lr, actors);
				continueInit = true;
			}

			catch (ingenias.jade.exception.NoAgentsFound nf) {
				tries--;
				if (tries <= 0)
					throw nf;
				try {
					Thread.sleep(1000);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				;

			}
		}
		if (aconv!=null)
			MainInteractionManager.logInteraction("Protocol "+conv.getInteraction().getId()+" initialised", agent.getLocalName(), conv.getId());
		else
			MainInteractionManager.logInteraction("Protocol "+conv.getInteraction().getId()+" initialisation failed", agent.getLocalName(), conv.getId());
		return aconv;
	};

	/**
	 * Similar to launchProtocolAsCollaborator but assuming that this agent is
	 * the initiator of the interaction. Refer to the launchProtocolAsInitiator
	 * method for more information
	 * 
	 * @param protocol
	 *            The id of the protocol
	 * @param role
	 *            The id of the role played as initiator
	 * @param actors
	 *            A list of actors that will participate
	 * @param msr
	 *            The accessor to the mental state of the agent
	 * @param lr
	 *            A component to remove locks on information stored in the
	 *            mental state
	 * @return The initialized active conversation
	 * @throws NoAgentsFound
	 */
	protected ActiveConversation launchProtocolAsInitiator(String protocol,
			String role, DFAgentDescription[] actors, MentalStateReader msr,
			MentalStateUpdater msu, LocksRemover lr) throws NoAgentsFound {
		int tries = 10;
		ActiveConversation aconv = null;
		boolean continueInit = false;
		String cid = this.getCID();
		RuntimeConversation conv = new RuntimeConversation(role
				+ "-" + cid);
		conv.setInteraction(new Interaction(protocol));
		conv.setConversationID(cid);
		conv.setPlayedRole(role);

		aconv = launchProtocol(actors, msr, msu, lr, tries, aconv,
				continueInit, conv);
		return aconv;
	};

	/**
	 * It obtains the state behavior associated to a conversation object
	 * contained in the mental state reader.
	 * 
	 * @param conv
	 *            A mental state entity representing the ongoing interaction
	 * @return A state behavior or null if none exist
	 */
	public StateBehavior getStateMachine(RuntimeConversation conv) {
		boolean found = false;
		Enumeration enumeration = this.smachines.elements();
		StateBehavior result = null;
		while (!found && enumeration.hasMoreElements()) {
			StateBehavior sm = (StateBehavior) enumeration.nextElement();			
			found = sm.getConversationID().equals(conv.getId());
			if (found) {
				result = sm;
			}
		}
		if (!found) {
			enumeration = this.activeMachines.elements();
			result = null;
			while (!found && enumeration.hasMoreElements()) {
				StateBehavior sm = (StateBehavior) enumeration.nextElement();
				found = (conv.getPlayedRole()+"-"+sm.getConversationID()).equals(conv.getId());
				if (found) {
					result = sm;
				}
			}
		}
		return result;

	}

	/**
	 * It obtains a list of active conversations. It consults a list of strings
	 * representing id of protocols.
	 * 
	 * @param typesOfConversation
	 *            A list of types of strings representing interaction types
	 * @return A list of conversation entities representing ongoing interactions
	 */
	public Vector<RuntimeConversation> getCurrentActiveConversations(
			Vector<String> typesOfConversation) {
		Vector<RuntimeConversation> result = new Vector<RuntimeConversation>();
		Vector<RuntimeConversation> conversations = agent.getMSM().getConversations();
		for (int k = 0; k < conversations.size(); k++) {
			if (typesOfConversation.contains(conversations.elementAt(k)
					.getInteraction().getId())){ 

				// inserts finished/aborted conversations at the end so they are considered
				// the last
				if	(conversations.elementAt(k).getState().equalsIgnoreCase("finished") || 
						conversations.elementAt(k).getState().equalsIgnoreCase("aborted"))
					result.add(result.size(),conversations.elementAt(k));
				else
					result.add(0,conversations.elementAt(k));
			}

		}
		return result;
	}

	/**
	 * For a concrete conversation id and a role name, it returns the associated
	 * conversation. An agent can participate in several interactions at the
	 * same time. Therefore, the agent will have one conversation entity per
	 * ongoing interaction.
	 * 
	 * @param conversationID
	 *            The conversation id
	 * @param rolePlayed
	 *            The id of the role
	 * @return A conversation object or null if none is found
	 */
	public RuntimeConversation getConversation(String conversationID, String rolePlayed) {
		Vector<RuntimeConversation> convs = this.agent.getMSM().getConversations();
		// System.err.println("Looking for "+conversationID+" "+rolePlayed+"
		// "+convs);
		RuntimeConversation result = null;
		for (int k = 0; k < convs.size() && result == null; k++) {
			// System.err.println("examining
			// "+convs.elementAt(k).getConversationID()+"
			// "+convs.elementAt(k).getPlayedRole());
			if (convs.elementAt(k).getConversationID() != null
					&& convs.elementAt(k).getConversationID().equals(
							conversationID)
							&& convs.elementAt(k).getPlayedRole().equals(rolePlayed))
				result = convs.elementAt(k);
		}
		return result;
	}

	/**
	 * It returns the list of active machines indexed by role played and
	 * conversation id
	 * 
	 * @return An indexed hashtable
	 */
	public Hashtable<Pair, StateBehavior> getActiveMachines() {
		return activeMachines;
	}

	/**
	 * It obtains a list of the indexes used to organize active conversations
	 * 
	 * @return A list of indexes
	 */
	public Vector<Pair> getConversationIDs() {
		return conversationIDs;
	}

	/**
	 * It removes a reference to a state behavior from the list of active
	 * conversations.
	 * 
	 * @param cid
	 *            The conversation id
	 * @param role
	 *            The role played during the interaction
	 */
	public void removeActiveMachine(String cid, String role) {
		this.getActiveMachines().remove(new Pair(cid, role));
	}

	/**
	 * It removes those state behaviors whose state is finished.
	 * 
	 */
	public void removedFinishedProtocols() {
		if (getActiveMachines().size() > 0) {
			Hashtable<String, StateBehavior> v = (Hashtable<String, StateBehavior>) activeMachines
			.clone();
			Enumeration enumeration = v.elements();
			while (enumeration.hasMoreElements()) {
				StateBehavior sb = (StateBehavior) enumeration.nextElement();
				if (sb.getFinished()) {
					removeCID(sb.getConversationID());
					this.agent.removeBehaviour(sb);
					removeActiveMachine(sb.getConversationID(), sb
							.getPlayedRole());
					// MainInteractionManager.log(sb.getConversationID()+"
					// conversation finished",getLocalName());
				}
			}
		}
	}

	/**
	 * It traverses a list of
	 * 
	 */
	public void launchScheduledProtocols() {

		if (smachines.size() > 0) {
			StateBehavior toAdd = smachines.firstElement();
			//System.err.println("Launching " + toAdd.getProtocol());
			this.agent.addBehaviour(toAdd);
			addCID(toAdd.getConversationID(), toAdd.getPlayedRole());
			activeMachines.put(new Pair(toAdd.getConversationID(), toAdd
					.getPlayedRole()), toAdd);
			smachines.removeElementAt(0);
		}
	}

	/**
	 * it searches the list of known protocols
	 * 
	 * @param protocol
	 *            The protocol id
	 * @return true if the protocol is known and false i.o.c
	 */
	public boolean isKnownProtocol(String protocol) {
		return this.knownProtocols.contains(protocol);
	}

	/**
	 * Initiates one of the known protocols of the agent assuming that no
	 * previous preferences are set about which agent should participate. Agents
	 * are found using a yellow pages service and omitting in the search the
	 * agent owning this class. The algorithm consists in looking for the first
	 * agent registered in the platform able to fullfill one of the roles
	 * requested. One agent may play one or several roles.
	 * 
	 * As a result, an active conversation object is returned. This object
	 * contains references to the state machine representing the protocol, the
	 * conversation mental entity, and the control of the state machine.
	 * 
	 * @param protocol
	 *            the name of the protocol
	 * @return The conversation id.
	 * @throws NoAgentsFound
	 */
	protected ActiveConversation launchProtocolAsInitiator(String protocol,
			String role, YellowPages yp) throws NoAgentsFound {
		DFAgentDescription[] actors = this.ap
		.getInteractionActors(protocol, yp);
		return this.launchProtocolAsInitiator(protocol, role, actors,
				this.agent.getMSM(), this.agent.getMSM(), this.agent.getLM());
	};

	/*
	 * public ActiveConversation initialiseProtocols(String name, String
	 * playedRole,String cid) throws ingenias.jade.exception.NoAgentsFound{
	 * ActiveConversation
	 * aconv=this.ap.initialiseProtocols(name,playedRole,cid);
	 * add(aconv.getDcc()); addCID(cid,playedRole); return aconv; }
	 */

	/**
	 * It verifies that in the interaction, a candidate list of actors satisfies
	 * the interaction requirements. Each interaction requires having certain
	 * actors able to play concrete roles.
	 * 
	 * @param interactionType
	 *            The type of the interaction
	 * @param actors
	 *            The candidate list of actors
	 * @return true if the actor list is satisfactory, and false i.o.c.
	 */
	public boolean verifyActors(String interactionType,
			DFAgentDescription[] actors) {

		return this.ap.verifyActors(interactionType, actors);
	}

	// /*public DFAgentDescription[] getDescription(String protocol) {
	//		
	// return this.ap.getDescription(protocol, agent.getAID());
	// }*/

	/**
	 * It declares that this agent knows how to handle the protocol
	 * 
	 * @param protocolname
	 *            The protocol to be known
	 */
	public void addKnownProtocol(String protocolname) {
		this.knownProtocols.add(protocolname);
	}

	/**
	 * It declares that this agent can act as initiator in a concrete
	 * interaction type playing a specific role
	 * 
	 * @param interaction
	 *            The type of the interaction
	 * @param role
	 *            The role played
	 */
	public void addInitiatorRoles(String interaction, String role) {
		this.initiatorRoles.put(interaction, role);
	}

	public void abortConversationByType(String protocolType) {
		Collection<StateBehavior> elements = activeMachines.values();
		for (StateBehavior sb : elements) {
			if (sb.getProtocol().equalsIgnoreCase(protocolType)) {
				sb.abortDueTimeout();
			}
		}
	}

	public void abortConversationById(String convId) {
		throw new UnsupportedOperationException();
	}

	public Vector<StateBehavior> getActiveConversationsByType(String id) {
		Vector<StateBehavior> result = new Vector<StateBehavior>();
		Collection<StateBehavior> elements = activeMachines.values();
		for (StateBehavior sb : elements) {
			if (sb.getProtocol().equalsIgnoreCase(id) && !sb.getStates().equals("FINISHED") && !sb.getStates().equals("ABORTED")) {
				result.add(sb);
			}
		}
		return result;
	}

}
