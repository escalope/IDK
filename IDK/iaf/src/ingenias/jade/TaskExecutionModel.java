/*
    Copyright (C) 2007 Jorge Gomez Sanz

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
package ingenias.jade;

import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Vector;

import ingenias.editor.entities.Entity;
import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.editor.entities.RuntimeFact;
import ingenias.editor.entities.StackEntry;
import ingenias.exception.InvalidEntity;
import ingenias.jade.comm.ActiveConversation;
import ingenias.jade.comm.StateBehavior;
import ingenias.jade.components.Task;
import ingenias.jade.components.TaskInput;
import ingenias.jade.components.TaskOutput;
import ingenias.jade.exception.NoAgentsFound;
import ingenias.jade.graphics.MainInteractionManager;

public class TaskExecutionModel {
	Vector<Boolean> markConversationAsUsed=new Vector<Boolean>();
	Task ctask=null;
	MentalStateManager msm=null;
	String agentName="";

	Thread conversationModificationAndNotificationThread=new Thread(){
		public void run(){
			while(true){						
				while (markConversationAsUsed.isEmpty()){
					try {
						Thread.currentThread().sleep(100);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
				//System.err.println("Executing task "+ctask.getID()+ " "+ctask.getType()+ " "+markConversationAsUsed.size());
				markConversationAsUsed.remove(0);
				if (ctask.getConversationContext()!=null){
					msm.conversationAlreadyUsed(ctask.getConversationContext());
				}
				msm.setModified();
			}
			//	wakeup();
		}
	};

	public TaskExecutionModel(String agentName){
		conversationModificationAndNotificationThread.setName("TaskExecutionModel "+agentName);
		conversationModificationAndNotificationThread.start();
		this.agentName=agentName;
	}

	/**
	 * This method performs a task execution. Basically, it uses inputs to produce outputs.
	 *  Inputs can be deleted or not, depending on how the inputs are related to 
	 * the task. Inputs associated with "consumes" relationship will be deleted. Inputs associated with
	 * "modifies" will no. Resulting outcomes will be added locally to the mental state of the agent. If the
	 * task is executed in the context of an interaction, the information is transmitted to the collaborating
	 * agent by the defaultCommControl specific of the protocol (it will take the information from the mental state).
	 * If the outcome is a conversation, a new state behavior will be created and actors located. The actors can be
	 * determined by the conversation object or resolved in runtime using the expected role names
	 * @param queues 
	 * @param t
	 */
	public synchronized  void executeTask(final JADEAgent ja, TaskQueue queues, final MentalStateManager msm, final Task t){
		if (queues.reviewTaskToBeExecuted(t)){
			try {
				t.setAgentID(ja.getLocalName());				
				t.execute();
				MainInteractionManager.logMSP("Execution finished ",ja.getLocalName(),t.getID(),t.getType());
				//System.err.println(agentName+": Execution finished "+t.getID()+t.getType());
			} catch (Throwable ex){
				Vector<MentalEntity> inputs = t.getInputs();
				for (MentalEntity input:inputs){
					if (input instanceof RuntimeFact)
						ingenias.testing.DebugUtils.printStackTrace((RuntimeFact)input);
				}
				ex.printStackTrace();
			} 

			if (t.getFinalOutput()==null){
				Vector<TaskOutput> outputs = t.getOutputs();
				t.setFinalOutput(outputs.firstElement());
			}

			MainInteractionManager.logMSP("Execution results ",ja.getLocalName(),t.getID(),t.getType());

			StateBehavior associatedStateMachine=null;
			Vector<RuntimeConversation> newInteractions=new Vector<RuntimeConversation>();


			TaskOutput generatedOutput=t.getFinalOutput();

			removeConsumedInputs(ja, msm, t, generatedOutput);

			createNewEntities(ja, msm, t, t.getConversationContext(), generatedOutput, newInteractions);


			/*Vector<TaskInput> cancelledConversations=t.getCancelledConversations();
				for (TaskInput ei:cancelledConversations){
					Conversation conv=(Conversation)ei.getValue();
					Vector<StateBehavior> activeByType=ja.getCM().getActiveConversationsByType(conv.getInteraction().getId());
					ja.getCM().abortConversationByType(conv.getInteraction().getId());
					for (StateBehavior sb:activeByType){
					 Vector<MentalEntity> entities = sb.getProducedEntities();
					 for (MentalEntity me:entities)
					  ja.getMSM().remove(me);
					}

				}*/

			// Interactions are launched at the end to ensure that they check
			// the latest mental state

			createNewInteractions(ja, t, newInteractions);

			if (t.getConversationContext()!=null){
				StateBehavior sb=ja.getCM().getStateMachine(t.getConversationContext());
				if (sb!=null && sb.getFinished()){
					ja.getMSM().remove(t.getConversationContext().getId());
				}
			}
			ja.getMSM().resizeAllMentalEntities();
			MainInteractionManager.logMSP("Finished execution",ja.getLocalName(),t.getID(),t.getType());

			ctask=t;
			this.msm=msm;

			markConversationAsUsed.add(true);
		}
	}

	private void createNewInteractions(final JADEAgent ja, final Task t, Vector<RuntimeConversation> newInteractions) {
		for (int k=0;k<newInteractions.size();k++){
			final RuntimeConversation current=newInteractions.elementAt(k);
			ja.getLM().addInteractionLocks(current.getInteraction().getId());
			ja.addBehaviour(new OneShotBehaviour(){
				@Override
				public void action() {
					try {
						ActiveConversation actconv=null;
						Enumeration collaborators=current.getCollaboratorsElements();
						if (collaborators!=null && collaborators.hasMoreElements()){
							//Collaborators have been set
							DFAgentDescription[] actors;
							Vector<DFAgentDescription> descV=new Vector<DFAgentDescription>();
							while (collaborators.hasMoreElements()){
								String colLocalID=collaborators.nextElement().toString();
								DFAgentDescription[] descr;
								try {
									descr = ja.getAM().getYellowPages().getAgentFromLocalID(colLocalID);
									if (descr.length>0){
										descV.add(descr[0]);											
									}
								} catch (FIPAException e) {
									e.printStackTrace();
									current.setAbortCode(IAFProperties.INTERNAL_FAILURE);
									current.setState("ABORTED");
								}

							}
							actors=descV.toArray(new DFAgentDescription[descV.size()]);
							if (ja.getCM().verifyActors(current.getInteraction().getId(),actors)) {
								actconv=ja.getCM().launchProtocolAsInitiator(
										current.getInteraction().getId(),actors);
								StackEntry se=new StackEntry("");
								se.setOperation("Creation");
								se.setPlace(t.getID()+":"+t.getType());
								se.setResposible(ja.getLocalName());
								se.setTime(""+new java.util.Date().getTime());
								actconv.getConv().addStack(se);

							} else {

								MainInteractionManager.logMSP("ERROR intializing manually protocol "+
										current.getInteraction().getId()+". Not all required collaborators" +
										" where defined. Please, review code for task "+t.getID(),ja.getLocalName());
								MainInteractionManager.logMSP("ERROR intializing manually protocol "+
										current.getInteraction().getId()+". Not all required collaborators" +
										" where defined. Please, review code for task "+t.getID(),ja.getLocalName(),t.getID(),t.getType());
								current.setState("ABORTED");
								current.setAbortCode(IAFProperties.NO_AGENTS_FOUND);
							}
						}
						else {
							// automatic selection of participants
							actconv=ja.getCM().launchProtocolAsInitiator(current.getInteraction().getId(),
									ja.getAM().getYellowPages());
							StackEntry se=new StackEntry("");
							se.setOperation("Creation");
							se.setPlace(t.getID()+":"+t.getType());
							se.setResposible(ja.getLocalName());
							se.setTime(""+new java.util.Date().getTime());


							actconv.getConv().addStack(se);
						}
						if (actconv!=null){								
							if (t.getConversationContext()!=null){
								Enumeration enumEnt=t.getConversationContext().getCurrentContentElements();
								while (enumEnt.hasMoreElements()){
									MentalEntity nelement = (MentalEntity)enumEnt.nextElement();
									if (!contains(actconv.getConv(),nelement))
										actconv.getConv().addCurrentContent(nelement);        
								}
								enumEnt=current.getCurrentContentElements();
								while (enumEnt.hasMoreElements()){
									MentalEntity nelement = (MentalEntity)enumEnt.nextElement();
									if (!contains(actconv.getConv(),nelement))
										actconv.getConv().addCurrentContent(nelement);        
								}
								updateAncestorConversationOfDifferentType(actconv.getConv(),t.getConversationContext());
							} else {
								Enumeration enumEnt=current.getCurrentContentElements();
								while (enumEnt.hasMoreElements()){
									MentalEntity nelement = (MentalEntity)enumEnt.nextElement();
									if (!contains(actconv.getConv(),nelement))
										actconv.getConv().addCurrentContent(nelement);        
								}
							}
							/*Conversation conv=(Conversation)ja.getMSM().getMentalEntity(cid);
							 for (MentalEntity s : assertedEntities){
							 conv.addCurrentContent(s);
							 }*/
						} else {
							//System.err.println("Un error "+cid+" in "+t.getID());
						}
					} catch (NoAgentsFound e) {
						e.printStackTrace();
						current.setState("ABORTED");
						current.setAbortCode(IAFProperties.NO_AGENTS_FOUND);
					}
				}

				private void updateAncestorConversationOfDifferentType(
						RuntimeConversation current,
						RuntimeConversation parent				) {
					if (!parent.getInteraction().getId().equalsIgnoreCase(current.getInteraction().getId())){
						if (!contains(current,parent))
						 current.addCurrentContent(parent);
					} else {
						Enumeration<MentalEntity> content = parent.getCurrentContentElements();
						while (content.hasMoreElements()){
							MentalEntity next = content.nextElement();
							if (next instanceof RuntimeConversation){
								updateAncestorConversationOfDifferentType(current, (RuntimeConversation)next);
							}
						}
					}
				}

				private boolean contains(RuntimeConversation conv,
						MentalEntity newME) {
					Enumeration elements=conv.getCurrentContentElements();
					boolean found=false;
					while (elements.hasMoreElements() && !found){
						MentalEntity nelement=(MentalEntity) elements.nextElement();
						if (nelement.getId().equals(newME.getId())){
							found=true;
						}
					}

					return found;
				}
			});	

		}
	}

	private void createNewEntities(final JADEAgent ja, MentalStateManager msm, final Task t, RuntimeConversation currentConv, 
			TaskOutput generatedOutput, Vector<RuntimeConversation> newInteractions) {
		HashSet<MentalEntity> newEntities = generatedOutput.getNewEntitiesMS();

		final Vector<MentalEntity> assertedEntities=new Vector<MentalEntity>();
		for (MentalEntity newEntity:newEntities){		
			if (newEntity instanceof RuntimeFact){
				StackEntry se=new StackEntry("");
				se.setOperation("Creation");
				se.setPlace(t.getID()+":"+t.getType());
				se.setResposible(ja.getLocalName());
				se.setTime(""+new java.util.Date().getTime());
				((RuntimeFact)newEntity).addStack(se);
			}
			if (newEntity instanceof RuntimeConversation){
				RuntimeConversation conv=(RuntimeConversation) newEntity;
				newInteractions.add(conv);	
				MainInteractionManager.logMSP("Produced conversation "+conv.getInteraction().getId(),ja.getLocalName(),
						t.getID(),t.getType());
			} else
				if (newEntity instanceof MentalEntity){
					MainInteractionManager.logMSP("Produced entity "+newEntity.getId()+":"+newEntity.getType(),ja.getLocalName(),t.getID(),t.getType());
					try {
						msm.addMentalEntity((MentalEntity)newEntity);
					} catch (InvalidEntity e) {							
						e.printStackTrace();
					} //Adds a new fact to the mental state
					assertedEntities.add((MentalEntity)newEntity); // and to the produced conversations
				}
		}

		newEntities = generatedOutput.getNewEntitiesWF();


		for (MentalEntity newEntity:newEntities){


			if (newEntity instanceof RuntimeFact){
				StackEntry se=new StackEntry("");
				se.setOperation("Creation");
				se.setPlace(t.getID()+":"+t.getType());
				se.setResposible(ja.getLocalName());
				se.setTime(""+new java.util.Date().getTime());
				((RuntimeFact)newEntity).addStack(se);

				if (t.getConversationContext()!=null){
					RuntimeConversation rc=t.getConversationContext();
					hierarchicalConversationUpdate(newEntity, rc);
				}
			}



			if (currentConv!=null && !(newEntity instanceof RuntimeConversation)){ 
				if (!newInteractions.isEmpty()){
					for (RuntimeConversation conv:newInteractions){
						conv.addCurrentContent(newEntity);
					}
				} 
				currentConv.addCurrentContent(newEntity);
				assertedEntities.add((MentalEntity)newEntity); // and to the produced conversations
				//System.err.println("Produced entity "+newEntity.getId()+":"+newEntity.getType()+" and stored within conversation "+
				//		currentConv.getConversationID());
				MainInteractionManager.logMSP("Produced entity "+newEntity.getId()+":"+newEntity.getType()+" and stored within conversation "+
						currentConv.getConversationID(),ja.getLocalName(),t.getID(),t.getType());
			} else {
				if (currentConv==null && !(newEntity instanceof RuntimeConversation)){
					MainInteractionManager.logMSP("Produced entity "+newEntity.getId()+":"+newEntity.getType(),ja.getLocalName(),t.getID(),t.getType());
					try {
						if (!newInteractions.isEmpty()){
							for (RuntimeConversation conv:newInteractions){
								conv.addCurrentContent(newEntity);
							}
						} else
							msm.addMentalEntity((MentalEntity)newEntity);
					} catch (InvalidEntity e) {							
						e.printStackTrace();
					} //Adds a new fact to the mental state
					assertedEntities.add((MentalEntity)newEntity); // and to the produced conversations
				}
			}				
		}


	}

	private void hierarchicalConversationUpdate(MentalEntity newEntity,
			RuntimeConversation rc) {
		Enumeration<MentalEntity> content = rc.getCurrentContentElements();
		while (content.hasMoreElements()){
			MentalEntity next = content.nextElement();
			if (next instanceof RuntimeConversation){
				((RuntimeConversation)next).addCurrentContent(newEntity);
				hierarchicalConversationUpdate(newEntity,(RuntimeConversation)next);
			}
		}
	}

	private void removeConsumedInputs(final JADEAgent ja, MentalStateManager msm, final Task t, TaskOutput generatedOutput) {
		HashSet<MentalEntity> consumedInputs=generatedOutput.getConsumedEntities();

		for (MentalEntity consumedFact:consumedInputs){				
			if (t.getConversationContext()==null){
				msm.remove(consumedFact.getId());
				MainInteractionManager.logMSP("Consumed information of type "+consumedFact.getId()+":"+consumedFact.getType()+" from mental state",ja.getLocalName(),t.getID(),t.getType());
				//System.err.println("Consumed fact "+consumedFact.getName()+" from mental state "+t.getID());
			}

			else {
				msm.removeFromConversation(t.getConversationContext(),consumedFact.getId());
				msm.remove(consumedFact.getId());
				MainInteractionManager.logMSP("Consumed fact "+consumedFact.getId()+":"+consumedFact.getType()+" from conversation "+
						t.getConversationContext().getId(),ja.getLocalName(),t.getID(),t.getType());
				//System.err.println("Consumed fact "+consumedFact.getName()+" from conversation by "+t.getID());
			}
		}
	}


}
