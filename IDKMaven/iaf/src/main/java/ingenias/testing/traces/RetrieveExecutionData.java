/*
    Copyright (C) 2013 Jorge Gomez Sanz

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
package ingenias.testing.traces;

import jade.lang.acl.ACLMessage;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.jade.AgentEventAdapter;
import ingenias.jade.comm.StateBehavior;
import ingenias.jade.components.Task;
import ingenias.jade.components.TaskOutput;

public class RetrieveExecutionData extends AgentEventAdapter{

		private Hashtable<MentalEntity, Trace> traces=new Hashtable<MentalEntity, Trace>();

		public Hashtable<MentalEntity, Trace> getTraces(){
			return traces;
		}

		public Trace getTrace(MentalEntity me){
			if (traces.containsKey(me))
				return traces.get(me);
			else
				return Trace.emptyTrace();
		}




		private void putTrace(MentalEntity me, Trace ntrace) {
			traces.put(me,ntrace);			
		}



		private void removeTrace(MentalEntity me) {
			traces.remove(me);
		}



	/*	@Override
		public void messageDelivered(String agentid, String agenttype,
				ACLMessage message2bSend) {
			// TODO Auto-generated method stub
			super.messageDelivered(agentid, agenttype, message2bSend);
			if (message2bSend.toString().indexOf("AnotherGlobalFact")>-1){
				System.out.println(message2bSend.toString());
			}
		}

		@Override
		public void addedNewEntityToConversationFromCommBehavior(
				String agentid, String agentType, StateBehavior commBehavior,
				MentalEntity entity, RuntimeConversation conversation) {
			// TODO Auto-generated method stub
			super.addedNewEntityToConversationFromCommBehavior(agentid, agentType,
					commBehavior, entity, conversation);
			if (agentid.equalsIgnoreCase("Agent1_0DeploymentUnitByType2") && entity.getType().equalsIgnoreCase(				
					"AnotherGlobalFact")){
				System.out.println(entity+" added in "+agentid+ " - "+commBehavior.getConversationID());
			}
		}

		@Override
		public void consumedEntityDuringtaskExecutionFinished(String agentid,
				String agentType, Task task, MentalEntity entity) {
			// TODO Auto-generated method stub
			super.consumedEntityDuringtaskExecutionFinished(agentid, agentType, task,
					entity);
			super.removeEntityFromMSByTask(agentid, agentType, task, entity);
			if (agentid.equalsIgnoreCase("Agent1_0DeploymentUnitByType2") && entity.getType().equalsIgnoreCase(				
					"AnotherGlobalFact")){
				System.out.println(entity+" removed from "+agentid);
			}
		}

		@Override
		public void consumedEntityDuringtaskExecutionFinishedFromConversation(
				String agentid, String agentType, Task task,
				MentalEntity entity, RuntimeConversation conversation) {
			// TODO Auto-generated method stub
			super.consumedEntityDuringtaskExecutionFinishedFromConversation(agentid,
					agentType, task, entity, conversation);
			super.removeEntityFromMSByTask(agentid, agentType, task, entity);
			if (agentid.equalsIgnoreCase("Agent1_0DeploymentUnitByType2") && entity.getType().equalsIgnoreCase(				
					"AnotherGlobalFact")){
				System.out.println(entity+" removed from "+agentid);
			}
		}

		@Override
		public void removeEntityFromMSByTask(String agentid, String agentType,
				Task task, MentalEntity entity) {
			// TODO Auto-generated method stub
			super.removeEntityFromMSByTask(agentid, agentType, task, entity);
			if (agentid.equalsIgnoreCase("Agent1_0DeploymentUnitByType2") ){
				System.out.println(entity+" removed from "+agentid);
			}
		}

		@Override
		public void removeEntityFromMS(String agentid, String agentType,
				MentalEntity entity) {
			// TODO Auto-generated method stub
			super.removeEntityFromMS(agentid, agentType, entity);
			if (agentid.equalsIgnoreCase("Agent1_0DeploymentUnitByType2") ){
				System.out.println(entity+" removed from "+agentid);
			}
		}

		@Override
		public void removeEntityFromConversation(String agentid,
				String agentType, Task task, MentalEntity entity,
				RuntimeConversation conversation) {
			// TODO Auto-generated method stub
			super.removeEntityFromConversation(agentid, agentType, task, entity,
					conversation);
			if (agentid.equalsIgnoreCase("Agent1_0DeploymentUnitByType2") ){
				System.out.println(entity+" removed from "+agentid);
			}
		}*/

		@Override
		public synchronized void taskExecutionFinished(String agentid, String agentType,
				Task task) {
			Vector<TaskOutput> outputs = task.getOutputs();
			Vector<MentalEntity> inputs = task.getInputs();
			HashSet<Trace> traces=new HashSet<Trace>();
			for (MentalEntity me:inputs){
				Trace currentTrace = getTrace(me);
				if (!currentTrace.isEmpty())
					traces.add(currentTrace);				
			}

			for (TaskOutput to:outputs){
				HashSet<MentalEntity> consumedEntities = to.getConsumedEntities();
				HashSet<MentalEntity> newEntities1 = to.getNewEntitiesMS();
				newEntities1.addAll(to.getNewEntitiesWF());
				for (MentalEntity me:newEntities1){		
					Trace ntrace=Trace.createTrace(agentid,task,traces);
					putTrace(me,ntrace);					
				}
				for (MentalEntity me:consumedEntities){
					HashSet<Trace> metraces=new HashSet<Trace>();
					metraces.add(getTrace(me));
					Trace ntrace=Trace.createTrace(agentid,task,metraces);
					putTrace(me,ntrace);								
				}				
			}	
		}
	}