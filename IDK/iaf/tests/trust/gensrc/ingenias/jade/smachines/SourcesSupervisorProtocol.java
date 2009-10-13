
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
			
package ingenias.jade.smachines;		
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
import ingenias.jade.*;
import java.util.*;

import ingenias.jade.mental.*;
import ingenias.jade.comm.ActiveConversation;
import ingenias.jade.comm.ConversationManagement;
import ingenias.jade.comm.StateBehavior;
import ingenias.jade.components.*;
import ingenias.jade.exception.*;
import ingenias.jade.graphics.AgentGraphics;

import javax.swing.*;

import ingenias.jade.components.*;
import java.awt.event.*;

import java.awt.*;


import ingenias.editor.entities.Entity;
import ingenias.editor.entities.FrameFact;
import ingenias.editor.entities.Interaction;
import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.MentalStateProcessor;
import ingenias.editor.entities.Slot;
import ingenias.editor.entities.StateGoal;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.editor.*;
import ingenias.jade.comm.DefaultCommControl;

import javax.swing.border.*;

import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelListener;
import org.jgraph.graph.DefaultGraphCell;
import ingenias.jade.comm.AgentProtocols;
import ingenias.jade.comm.LocksWriter;


public class SourcesSupervisorProtocol implements AgentProtocols{

public SourcesSupervisorProtocol(){};		
			
	public ActiveConversation initialiseProtocols(String agentName,RuntimeConversation conv, 
			MentalStateReader msr, MentalStateUpdater msu,
			ingenias.jade.comm.LocksRemover lr,  
			AgentExternalDescription[] actors) throws NoAgentsFound,WrongInteraction{
        // Adds a protocol to the protocol queue
            StateBehavior   sb=null;
		    // Interactions in which this agent appears as collaborator
                             
		   if (conv.getInteraction().getId().equals("PerformProposal") && conv.getPlayedRole().equals("SupervisorRole")){
		   	DefaultCommControl dcc=new SupervisorRolePerformProposalDefaultCommControl(
                        		   conv.getConversationID(),msr,lr);
			sb = new SupervisorRolePerformProposalStateBehavior(agentName,
				msr, msu,
				conv,                           		
				conv.getPlayedRole(),
			   	actors,
				dcc,conv.getInteraction().getId());
                        ActiveConversation aconv=new ActiveConversation(sb,dcc,conv);
			return aconv;
                   }
                                    
		   if (conv.getInteraction().getId().equals("ReputationQuery") && conv.getPlayedRole().equals("SupervisorReputationInfoProviderRole")){
		   	DefaultCommControl dcc=new SupervisorReputationInfoProviderRoleReputationQueryDefaultCommControl(
                        		   conv.getConversationID(),msr,lr);
			sb = new SupervisorReputationInfoProviderRoleReputationQueryStateBehavior(agentName,
				msr, msu,
				conv,                           		
				conv.getPlayedRole(),
			   	actors,
				dcc,conv.getInteraction().getId());
                        ActiveConversation aconv=new ActiveConversation(sb,dcc,conv);
			return aconv;
                   }
                  
		           // Interactions in which this agent appears as initiator                  
                  
		   if (conv.getInteraction().getId().equals("AlphaQualityInspectionRequest") && conv.getPlayedRole().equals("SupervisorRole")){
		   DefaultCommControl dcc=new SupervisorRoleAlphaQualityInspectionRequestDefaultCommControl(
                        		   conv.getConversationID(),msr,lr);
			sb = new SupervisorRoleAlphaQualityInspectionRequestStateBehavior(
			agentName,
				msr,msu,
					conv,                           		
				   conv.getPlayedRole(),
				   actors,
				dcc,conv.getInteraction().getId());
                           ActiveConversation aconv=new ActiveConversation(sb,dcc,conv);
			   return aconv;
                   }
		
		   if (conv.getInteraction().getId().equals("RequestToAddSource") && conv.getPlayedRole().equals("SupervisorRole")){
		   DefaultCommControl dcc=new SupervisorRoleRequestToAddSourceDefaultCommControl(
                        		   conv.getConversationID(),msr,lr);
			sb = new SupervisorRoleRequestToAddSourceStateBehavior(
			agentName,
				msr,msu,
					conv,                           		
				   conv.getPlayedRole(),
				   actors,
				dcc,conv.getInteraction().getId());
                           ActiveConversation aconv=new ActiveConversation(sb,dcc,conv);
			   return aconv;
                   }
		
		   if (conv.getInteraction().getId().equals("QualityInspectionRequest") && conv.getPlayedRole().equals("SupervisorRole")){
		   DefaultCommControl dcc=new SupervisorRoleQualityInspectionRequestDefaultCommControl(
                        		   conv.getConversationID(),msr,lr);
			sb = new SupervisorRoleQualityInspectionRequestStateBehavior(
			agentName,
				msr,msu,
					conv,                           		
				   conv.getPlayedRole(),
				   actors,
				dcc,conv.getInteraction().getId());
                           ActiveConversation aconv=new ActiveConversation(sb,dcc,conv);
			   return aconv;
                   }
		
		   if (conv.getInteraction().getId().equals("ReputationQuery") && conv.getPlayedRole().equals("SupervisorInterestedInReputationRole")){
		   DefaultCommControl dcc=new SupervisorInterestedInReputationRoleReputationQueryDefaultCommControl(
                        		   conv.getConversationID(),msr,lr);
			sb = new SupervisorInterestedInReputationRoleReputationQueryStateBehavior(
			agentName,
				msr,msu,
					conv,                           		
				   conv.getPlayedRole(),
				   actors,
				dcc,conv.getInteraction().getId());
                           ActiveConversation aconv=new ActiveConversation(sb,dcc,conv);
			   return aconv;
                   }
		

         throw new WrongInteraction("Agent "+agentName+" does not know any interaction protocol of type "+conv.getInteraction().getId()+" where the " +
		   		" agent knows how to play the protocol "+conv.getPlayedRole());
        
        
        
        }

 
	
	        public AgentExternalDescription[] getInteractionActors(String interaction, YellowPages yp) throws ingenias.jade.exception.NoAgentsFound{
              AgentExternalDescription[] result=null;
             
               if (interaction.equals("AlphaQualityInspectionRequest")){
                Vector<AgentExternalDescription> cols=new Vector<AgentExternalDescription>();
                 
                 try {
                 String cardinality="";
		 DFAgentDescription[] agents=yp.getAgents("SourcesAlphaQualityInspectorRole");
                   if (agents==null || agents.length<=0)
                      throw new ingenias.jade.exception.NoAgentsFound("Could not find an agent playing the role SourcesAlphaQualityInspectorRole");
                   if (cardinality.equals("1") || cardinality.equals(""))
                    cols.add(new AgentExternalDescription(agents[0].getName(),"SourcesAlphaQualityInspectorRole"));
                    else
                	   if (cardinality.equals("1__*"))
						   for (int k=0;k<agents.length;k++)
                			  cols.add(new AgentExternalDescription(agents[k].getName(),"SourcesAlphaQualityInspectorRole"));
                 } catch (FIPAException fe){
                   fe.printStackTrace();
                   throw new NoAgentsFound();
                 }
                 
                result=new AgentExternalDescription[cols.size()];
                cols.toArray(result);
               }
             
               if (interaction.equals("RequestToAddSource")){
                Vector<AgentExternalDescription> cols=new Vector<AgentExternalDescription>();
                 
                 try {
                 String cardinality="";
		 DFAgentDescription[] agents=yp.getAgents("SourcesManagerRole");
                   if (agents==null || agents.length<=0)
                      throw new ingenias.jade.exception.NoAgentsFound("Could not find an agent playing the role SourcesManagerRole");
                   if (cardinality.equals("1") || cardinality.equals(""))
                    cols.add(new AgentExternalDescription(agents[0].getName(),"SourcesManagerRole"));
                    else
                	   if (cardinality.equals("1__*"))
						   for (int k=0;k<agents.length;k++)
                			  cols.add(new AgentExternalDescription(agents[k].getName(),"SourcesManagerRole"));
                 } catch (FIPAException fe){
                   fe.printStackTrace();
                   throw new NoAgentsFound();
                 }
                 
                result=new AgentExternalDescription[cols.size()];
                cols.toArray(result);
               }
             
               if (interaction.equals("QualityInspectionRequest")){
                Vector<AgentExternalDescription> cols=new Vector<AgentExternalDescription>();
                 
                 try {
                 String cardinality="";
		 DFAgentDescription[] agents=yp.getAgents("SourceQualityInspectorRole");
                   if (agents==null || agents.length<=0)
                      throw new ingenias.jade.exception.NoAgentsFound("Could not find an agent playing the role SourceQualityInspectorRole");
                   if (cardinality.equals("1") || cardinality.equals(""))
                    cols.add(new AgentExternalDescription(agents[0].getName(),"SourceQualityInspectorRole"));
                    else
                	   if (cardinality.equals("1__*"))
						   for (int k=0;k<agents.length;k++)
                			  cols.add(new AgentExternalDescription(agents[k].getName(),"SourceQualityInspectorRole"));
                 } catch (FIPAException fe){
                   fe.printStackTrace();
                   throw new NoAgentsFound();
                 }
                 
                result=new AgentExternalDescription[cols.size()];
                cols.toArray(result);
               }
             
               if (interaction.equals("ReputationQuery")){
                Vector<AgentExternalDescription> cols=new Vector<AgentExternalDescription>();
                 
                 try {
                 String cardinality="1__*";
		 DFAgentDescription[] agents=yp.getAgents("SupervisorReputationInfoProviderRole");
                   if (agents==null || agents.length<=0)
                      throw new ingenias.jade.exception.NoAgentsFound("Could not find an agent playing the role SupervisorReputationInfoProviderRole");
                   if (cardinality.equals("1") || cardinality.equals(""))
                    cols.add(new AgentExternalDescription(agents[0].getName(),"SupervisorReputationInfoProviderRole"));
                    else
                	   if (cardinality.equals("1__*"))
						   for (int k=0;k<agents.length;k++)
                			  cols.add(new AgentExternalDescription(agents[k].getName(),"SupervisorReputationInfoProviderRole"));
                 } catch (FIPAException fe){
                   fe.printStackTrace();
                   throw new NoAgentsFound();
                 }
                 
                result=new AgentExternalDescription[cols.size()];
                cols.toArray(result);
               }
             
                return result;
        }
	
	        /* public DFAgentDescription[ ] getDescription(String protocol, AID agentID) {
               DFAgentDescription[] result=null;
	       Vector playedRoles=new Vector();
	        DFAgentDescription dfd=null;
                dfd = new DFAgentDescription();
                
                if (protocol.equals("AlphaQualityInspectionRequest")){
		dfd.setName(agentID);
		 ServiceDescription sd = new ServiceDescription();
		 sd.setName(agentID.getLocalName()  + "-sub-df");
		 sd.setType("SupervisorRole");
		 sd.setOwnership("JADE");
		 dfd.addServices(sd);
                 playedRoles.add(dfd);
                }
                
                if (protocol.equals("RequestToAddSource")){
		dfd.setName(agentID);
		 ServiceDescription sd = new ServiceDescription();
		 sd.setName(agentID.getLocalName()  + "-sub-df");
		 sd.setType("SupervisorRole");
		 sd.setOwnership("JADE");
		 dfd.addServices(sd);
                 playedRoles.add(dfd);
                }
                
                if (protocol.equals("QualityInspectionRequest")){
		dfd.setName(agentID);
		 ServiceDescription sd = new ServiceDescription();
		 sd.setName(agentID.getLocalName()  + "-sub-df");
		 sd.setType("SupervisorRole");
		 sd.setOwnership("JADE");
		 dfd.addServices(sd);
                 playedRoles.add(dfd);
                }
                
                if (protocol.equals("ReputationQuery")){
		dfd.setName(agentID);
		 ServiceDescription sd = new ServiceDescription();
		 sd.setName(agentID.getLocalName()  + "-sub-df");
		 sd.setType("SupervisorInterestedInReputationRole");
		 sd.setOwnership("JADE");
		 dfd.addServices(sd);
                 playedRoles.add(dfd);
                }
                
                                           
                if (protocol.equals("PerformProposal")){
		dfd.setName(agentID);
		 ServiceDescription sd = new ServiceDescription();
		 sd.setName(agentID.getLocalName()  + "-sub-df");
		 sd.setType("SupervisorRole");
		 sd.setOwnership("JADE");
		 dfd.addServices(sd);
                 playedRoles.add(dfd);
                }
                                           
                if (protocol.equals("ReputationQuery")){
		dfd.setName(agentID);
		 ServiceDescription sd = new ServiceDescription();
		 sd.setName(agentID.getLocalName()  + "-sub-df");
		 sd.setType("SupervisorReputationInfoProviderRole");
		 sd.setOwnership("JADE");
		 dfd.addServices(sd);
                 playedRoles.add(dfd);
                }
                
                result=new  DFAgentDescription[playedRoles.size()];
                playedRoles.toArray(result);
		return result;

	}*/
	
	
	public boolean verifyActors(String protocol, AgentExternalDescription[ ] actors) {
          
        
        
        if (protocol.equals("AlphaQualityInspectionRequest")){
            Vector<String> toVerify=new Vector<String>();
            HashSet<String> rolesFound=new HashSet<String>();
            
         	toVerify.add("SourcesAlphaQualityInspectorRole");
         	
        	if (actors.length<toVerify.size())
        		return false;
        	boolean found=false;
        	for (int k=0;k<actors.length ;k++){
        		if (toVerify.contains(actors[k].role)){
    				rolesFound.add(actors[k].role);    			
    			}
        	}
        	return toVerify.size()==rolesFound.size();
        }
        
        if (protocol.equals("RequestToAddSource")){
            Vector<String> toVerify=new Vector<String>();
            HashSet<String> rolesFound=new HashSet<String>();
            
         	toVerify.add("SourcesManagerRole");
         	
        	if (actors.length<toVerify.size())
        		return false;
        	boolean found=false;
        	for (int k=0;k<actors.length ;k++){
        		if (toVerify.contains(actors[k].role)){
    				rolesFound.add(actors[k].role);    			
    			}
        	}
        	return toVerify.size()==rolesFound.size();
        }
        
        if (protocol.equals("QualityInspectionRequest")){
            Vector<String> toVerify=new Vector<String>();
            HashSet<String> rolesFound=new HashSet<String>();
            
         	toVerify.add("SourceQualityInspectorRole");
         	
        	if (actors.length<toVerify.size())
        		return false;
        	boolean found=false;
        	for (int k=0;k<actors.length ;k++){
        		if (toVerify.contains(actors[k].role)){
    				rolesFound.add(actors[k].role);    			
    			}
        	}
        	return toVerify.size()==rolesFound.size();
        }
        
        if (protocol.equals("ReputationQuery")){
            Vector<String> toVerify=new Vector<String>();
            HashSet<String> rolesFound=new HashSet<String>();
            
         	toVerify.add("SupervisorReputationInfoProviderRole");
         	
        	if (actors.length<toVerify.size())
        		return false;
        	boolean found=false;
        	for (int k=0;k<actors.length ;k++){
        		if (toVerify.contains(actors[k].role)){
    				rolesFound.add(actors[k].role);    			
    			}
        	}
        	return toVerify.size()==rolesFound.size();
        }
        
     
		return false;

	}
	
	
			}
			