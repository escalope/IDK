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

import ingenias.editor.entities.RuntimeFact;
import ingenias.editor.entities.StackEntry;
import ingenias.jade.JADEAgent;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.testing.DebugUtils;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.core.behaviours.*;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

import jade.core.*;
import jade.core.behaviours.*;

import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.domain.DFService;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import java.util.*;

/**
 * This is an utility class designed to create the different communicative acts an agent may
 * need in a conversation.
 *
 * @author jj
 *
 */
public class CommActCreator {
    
    /**
     * It produces an INFORM speech act to be recieved by a set of agents. The content
     * of the message is a string.
     *
     * @param ag The agent sending the message
     * @param receiver The IDs of the agents receiving the message
     * @param CID The conversation id
     * @param seqcode The state of the conversation this message refers to
     * @param protocol The name of the protocol
     * @param content The message content
     * @return
     */
    public static Vector generateSString(JADEAgent ag,
            AID[] receiver,
            String CID,
            String seqcode,
            String protocol,
            String content) {
        Vector acts = new Vector();
//    System.err.println(ag.getAID().getName()+" received "+seqcode);
        ACLMessage message2BSend = new ACLMessage(ACLMessage.INFORM);
        
        for (int k = 0; k < receiver.length; k++) {
            if (!ag.getAID().equals(receiver[k])) { // To avoid sending messages to itself
                message2BSend.addReceiver(receiver[k]);
                
//      System.err.println("Sending to "+receiver[k]);
            }
        }
        
        message2BSend.setContent(content);
        message2BSend.addUserDefinedParameter("sequence", seqcode);
        message2BSend.setProtocol(protocol);
        message2BSend.setConversationId(CID);
        
        jade.core.behaviours.SenderBehaviour send =
                new jade.core.behaviours.SenderBehaviour(ag, message2BSend);
        
        acts.add(send);
        
        ag.addBehaviour(send);
        
        MainInteractionManager.logInteraction("Sending "+seqcode+" "+message2BSend.toString(),ag.getLocalName(),CID);
        return acts;
    }
    
    /**
     * It generates an INFORM speech act. The content is a java object which can be serialized.
     * This method is used to supply the initial list of actors to all participants and to
     * encapsulate the different mental entities to be sent along the interaction.
     *
     * @param ag The agent sending the message
     * @param roles
     * @param receiver The IDs of the agents receiving the message
     * @param CID The conversation id
     * @param seqcode The state of the conversation this message refers to
     * @param protocol The name of the protocol
     * @param content The message content
     * @return
     */
    public static Vector generateSObject(final JADEAgent ag,
            Vector<String> roles, AID[] receiver,
            final String CID,
            final String seqcode,
            final String protocol,
            java.io.Serializable content) {
        Vector acts = new Vector();
        
//    new Exception().printStackTrace();
//  System.err.println(ag.getAID().getName()+" received "+seqcode);
        String receivers="";
        for (AID creceiver:receiver){
        	receivers=receivers+creceiver.getName()+",";
        }
        final String freceivers=receivers; 
        

        
        for (int k = 0; k < receiver.length; k++) {
            final ACLMessage message2BSend = new ACLMessage(ACLMessage.INFORM);
//      if (!ag.getAID().equals(receiver[k])) { // To avoid sending messages to itself
            message2BSend.addReceiver(receiver[k]);
            
            try {
                
                message2BSend.addUserDefinedParameter("sequence", seqcode);
                
                message2BSend.addUserDefinedParameter("requestedRole", roles.elementAt(k));
                message2BSend.setProtocol(protocol);
                message2BSend.setConversationId(CID);
                
                if (content instanceof Vector){
                	for (Object obj:(Vector)content){                        
                    		if (obj instanceof RuntimeFact){
                    			StackEntry se=new StackEntry("");
                    			se.setOperation("Transferred");
                    			se.setPlace(CID+":"+protocol);
                    			se.setResposible(ag.getLocalName());
                    			se.setTime(""+new java.util.Date().getTime());
                    			((RuntimeFact)obj).addStack(se);
                    		}
                	}
                }
                //System.err.println(ag.getLocalName()+"-"+CID+" enviando "+seqcode);
                message2BSend.setContentObject(content);
                jade.core.behaviours.SenderBehaviour send =
                        new jade.core.behaviours.SenderBehaviour(ag, message2BSend);           
                 
                jade.core.behaviours.OneShotBehaviour after= new jade.core.behaviours.OneShotBehaviour(ag){
					@Override
					public void action() {
						 MainInteractionManager.logInteraction("Sent (content omitted) "+seqcode+":"+message2BSend,ag.getLocalName(),CID);
						 DebugUtils.logEvent("MessageSent", new String[]{protocol,CID,seqcode,ag.getLocalName(),freceivers});
					}                	
                };
                jade.core.behaviours.SequentialBehaviour seb=new jade.core.behaviours.SequentialBehaviour(ag);
                seb.addSubBehaviour(send);
                seb.addSubBehaviour(after);
                
                acts.add(seb);
                
                ag.addBehaviour(seb);
                
            } catch (java.io.IOException ioe) {
                ioe.printStackTrace();
            }
        }
        ingenias.jade.graphics.MainInteractionManager.logInteraction("Sending message to "+receivers+" with content "+content,ag.getName(),CID);
        return acts;
    }
    
    /**
     * It produces a receiver behavior specialised to attend only to messages with a concrete
     * conversation ID, following a determined protocol, and referring to a specific state.
     * New behaviors are added automatically to the agent and returned as result.
     *
     * @param ag The agent which will add the receiver behavior
     * @param CID The conversation id
     * @param seqcode The state of the interaction to be considered
     * @param protocol The name of the protocol followed
     * @param options The states that will follow seqcode if the receiver behavior is executed succesfully
     * @param sb The state behavior encapsulating the message interchange behavior for this converstaion
     * @return The new behaviors added.
     */
    public static Vector<jade.core.behaviours.SequentialBehaviour>  generateR(final Agent ag, final String CID, final String seqcode,
            final String protocol,
            String[] options,
            StateBehavior sb,
            int multiple,
            long timeout
            ) {
    	
        final String _protocol = protocol;
        final String _CID = CID;
        final String _seqcode = seqcode;
        final String[] _options = options;
        final DefaultCommControl _dcc = sb.getDCC();
        final StateBehavior _sb = sb;

        if (timeout<=0){
        	timeout=-1;
        }
        
        Vector acts = new Vector();
        
        
        // Receive a message to start interaction
        // First message contains the actor list
        MessageTemplate.MatchExpression me = new MessageTemplate.MatchExpression() {
            public boolean match(ACLMessage mes) {
                // Be careful with reception of sync messages as conventional messages
                
                return mes.getProtocol() != null && mes.getProtocol().equals(_protocol) &&
                        mes.getConversationId() != null &&
                        mes.getConversationId().equals(_CID) &&
                        mes.getContent() != null &&
                        mes.getUserDefinedParameter("sequence") != null &&
                        mes.getUserDefinedParameter("sequence").equals(_seqcode);
            };
        };
        
        MessageTemplate mt = new MessageTemplate(me);
        MainInteractionManager.logInteraction("Waiting for "+CID+"("+seqcode+")",ag.getLocalName(),CID);
        
        jade.core.behaviours.SequentialBehaviour seqBehavior =
                new jade.core.behaviours.SequentialBehaviour(ag);
        acts.add(seqBehavior);
        seqBehavior.setBehaviourName("Receive "+CID+" state "+seqcode);
        jade.core.behaviours.ParallelBehaviour parBehavior=
        	new jade.core.behaviours.ParallelBehaviour(ag, jade.core.behaviours.ParallelBehaviour.WHEN_ALL);
        parBehavior.setBehaviourName("Multiple reception");
        seqBehavior.addSubBehaviour(parBehavior);
        
        final Vector<jade.core.behaviours.ReceiverBehaviour> receiverBehaviors=new Vector<jade.core.behaviours.ReceiverBehaviour>();
        
        for (int k=0;k<multiple;k++){
            
            jade.core.behaviours.ReceiverBehaviour rec = new jade.core.behaviours.
                    ReceiverBehaviour(ag, timeout, mt);
            rec.setBehaviourName("Reception act");
            parBehavior.addSubBehaviour(rec);
            receiverBehaviors.add(rec);         
        }
        
        jade.core.behaviours.SimpleBehaviour sbeh = new jade.core.behaviours.
                SimpleBehaviour(ag) {
            private boolean done = false;
            public void action() {
                Vector<ACLMessage> received=new Vector<ACLMessage>();
                
                for (jade.core.behaviours.ReceiverBehaviour recB:receiverBehaviors){
                    try {                    	
                        received.add(recB.getMessage());
                        DebugUtils.logEvent("MessageReceived", new String[]{protocol,CID,seqcode,recB.getMessage().getSender().getLocalName(),ag.getLocalName()});
                        MainInteractionManager.logInteraction("Received messages for "+CID+"("+seqcode+") from "+recB.getMessage().getSender().getLocalName(),ag.getLocalName(),CID);        
                        // OK. Message received within timeout.
                    } catch(ReceiverBehaviour.TimedOut rbte) {
                    	// No message received so far from that behavior
                    	rbte.printStackTrace();
                    }catch (ReceiverBehaviour.NotYetReady ex) {
                        ex.printStackTrace();
                    }                                        
                }
                
                if (received.size()==0){
                	_sb.abortDueTimeout();	
                } else{                
                _dcc.notifyNewMessage(received, _options, _sb);
                }
                
//           System.err.println("notified ");
                done = true;
                
            }
            
            public boolean done() {
                return done;
            }
        };
        
        sbeh.setBehaviourName("Process received messages");
        
        seqBehavior.addSubBehaviour(sbeh);
        ag.addBehaviour(seqBehavior);
        
        return acts;
    }
    
    
    
    /**
     * It produces a generic receiver behavior. It does not consider a concrete
     * conversation id. It is used to start conversations with
     * other agents when the agent is a collaborator. As soon as the state behavior
     * of a collaborator is initialised, it invokes this method to catch the message
     * sent by the initiator.
     * <p>
     * New behaviors are added automatically to the agent and returned as result.
     *
     *
     * @param ag The agent receiving the message
     * @param protocol The protocol id
     * @param seqcode The sequence to be expecting to. In general it is "enable"
     * @param options The states to be followed by this one
     * @param sb The state behavior that contains the protocol implementation
     * @return The receiving actions added to the agent behavior
     */
    public static Vector generateRWithoutCID(
            final JADEAgent ag,
            final String protocol,
            final String seqcode,
            String[] options,
            StateBehavior sb) {
        final String _protocol = protocol;
        final String _seqcode = "enable";
        final String[] _options = options;
        final DefaultCommControl _dcc = sb.getDCC();
        final StateBehavior _sb = sb;
        
        Vector acts = new Vector();
        // Receive a message to start interaction
        // First message contains the actor list
        MessageTemplate.MatchExpression me = new MessageTemplate.MatchExpression() {
            public boolean match(ACLMessage mes) {
                // Be careful with reception of sync messages as conventional messages
                return mes.getProtocol() != null && mes.getProtocol().equals(_protocol) &&
                        mes.getContent() != null &&
                        mes.getUserDefinedParameter("sequence") != null &&
                        mes.getUserDefinedParameter("sequence").equals(_seqcode);
            };
        };
        
        MessageTemplate mt = new MessageTemplate(me);
        
        jade.core.behaviours.SequentialBehaviour seqBehavior =
                new jade.core.behaviours.SequentialBehaviour(ag);
        
        final jade.core.behaviours.ReceiverBehaviour rec = new jade.core.behaviours.
                ReceiverBehaviour(ag, -1, mt);
        
        jade.core.behaviours.SimpleBehaviour sbeh = new jade.core.behaviours.
                SimpleBehaviour(ag) {
            private boolean done = false;
            public void action() {
                try {
                	DebugUtils.logEvent("CollaborationRequestReceived", new String[]{protocol,seqcode,rec.getMessage().getSender().getLocalName(),ag.getLocalName()});
                	MainInteractionManager.logInteraction("Received messages for protocol "+protocol+"("+seqcode+") from "+rec.getMessage().getSender().getLocalName(),ag.getLocalName(),protocol);
                    Vector<ACLMessage> messages=new Vector<ACLMessage>();
                    messages.add(rec.getMessage());
                    _dcc.notifyNewMessage(messages, _options, _sb);
                    _sb.setConversationID(rec.getMessage().getConversationId());
                    done = true;
                } catch (jade.core.behaviours.ReceiverBehaviour.NotYetReady nyr) {
                    nyr.printStackTrace();
                } catch (jade.core.behaviours.ReceiverBehaviour.TimedOut to) {
                    to.printStackTrace();
                }
                
            }
            
            public boolean done() {
                return done;
            }
            
        };
        
        seqBehavior.addSubBehaviour(rec);
        seqBehavior.addSubBehaviour(sbeh);
        acts.add(rec);
        ag.addBehaviour(seqBehavior);
        return acts;
    }
    
}