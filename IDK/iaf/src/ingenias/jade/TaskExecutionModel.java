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

import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.editor.entities.RuntimeFact;
import ingenias.editor.entities.StackEntry;
import ingenias.exception.InvalidEntity;
import ingenias.jade.comm.ActiveConversation;
import ingenias.jade.comm.StateBehavior;
import ingenias.jade.components.Task;
import ingenias.jade.components.TaskOutput;
import ingenias.jade.exception.NoAgentsFound;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.testing.DebugUtils;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Vector;

public class TaskExecutionModel {

    Vector<Boolean> markConversationAsUsed = new Vector<Boolean>();
    Task ctask = null;
    MentalStateManager msm = null;
    String agentName = "";
    Thread conversationModificationAndNotificationThread = new Thread() {

        public void run() {
            while (true) {
                while (markConversationAsUsed.isEmpty()) {
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
                //System.err.println("Executing task "+ctask.getID()+ " "+ctask.getType()+ " "+markConversationAsUsed.size());
                RuntimeConversation rc = null;

                removeMark();

                msm.setModified();
            }
        //	wakeup();
        }
    };
    private Vector<String> interactionsProcessed = new Vector<String>();

    public boolean isInteractionsProcessed() {
        return interactionsProcessed.isEmpty();
    }

    public TaskExecutionModel(String agentName, MentalStateManager msm) {
        conversationModificationAndNotificationThread.setName("TaskExecutionModel " + agentName);
        conversationModificationAndNotificationThread.start();
        this.agentName = agentName;
        this.msm = msm;
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
    public synchronized void executeTask(final JADEAgent ja, final TaskQueue queues, final MentalStateManager cmsm, final Task t) {
     	msm.lockChanges();
        EventManager.getInstance().startingTaskExecution(ja.getLocalName(),
                ja.getClass().getName().substring(0,
                ja.getClass().getName().indexOf("JADE")),
                t);
        if (queues.reviewTaskToBeExecuted(t)) {
            try {
                t.setAgentID(ja.getLocalName());
                t.execute();
            //DebugUtils.logEvent("TaskExecuted", new String[]{ja.getAID().getLocalName(),t.getType(),t.getID()});
            //MainInteractionManager.logMSP("Execution finished ",ja.getLocalName(),t.getID(),t.getType());
            //System.err.println(agentName+": Execution finished "+t.getID()+t.getType());
            } catch (Throwable ex) {
                Vector<MentalEntity> inputs = t.getInputs();
                for (MentalEntity input : inputs) {
                    if (input instanceof RuntimeFact) {
                        ingenias.testing.DebugUtils.printStackTrace((RuntimeFact) input);
                    }
                }
                ex.printStackTrace();
            }

            if (t.getFinalOutput() == null) {
                Vector<TaskOutput> outputs = t.getOutputs();
                t.setFinalOutput(outputs.firstElement());
            }

            //MainInteractionManager.logMSP("Execution results ",ja.getLocalName(),t.getID(),t.getType());

            StateBehavior associatedStateMachine = null;
            Vector<RuntimeConversation> newInteractions = new Vector<RuntimeConversation>();


            TaskOutput generatedOutput = t.getFinalOutput();

            removeConsumedInputs(ja, cmsm, t, generatedOutput);

            createNewEntities(ja, cmsm, t, t.getConversationContext(), generatedOutput, newInteractions);

            // Interactions are launched at the end to ensure that they check
            // the latest mental state

            msm.unlockChanges(); // Changes are permitted again so that new conversations can access to the mental state manager
            // from another thread

            createNewInteractions(ja, t, newInteractions);

            /*if (t.getConversationContext()!=null){
            StateBehavior sb=ja.getCM().getStateMachine(t.getConversationContext());
            if (sb!=null && sb.getFinished()){
            ja.getMSM().remove(t.getConversationContext().getId());
            }
            }*/
            ja.getMSM().resizeAllMentalEntities();

            ctask = t;
            msm = cmsm;
            markMentalStateAsChanged();
            EventManager.getInstance().taskExecutionFinished(ja.getLocalName(),
                    ja.getClass().getName().substring(0, ja.getClass().getName().indexOf("JADE")),
                    t);
        } else {
              msm.unlockChanges();
        }
        


    }

    private synchronized void markMentalStateAsChanged() {
        markConversationAsUsed.add(true);
    }

    private synchronized void removeMark() {

        markConversationAsUsed.remove(0);

    }

    private void createNewInteractions(final JADEAgent ja, final Task t, Vector<RuntimeConversation> newInteractions) {
        for (int k = 0; k < newInteractions.size(); k++) {
            final RuntimeConversation current = newInteractions.elementAt(k);
            //ja.getLM().addInteractionLocks(current.getInteraction().getId());
            this.interactionsProcessed.add("OneToGo");
            SimpleBehaviour oneShotBehaviour = new SimpleBehaviour() {

                private boolean done = false;

                @Override
                public void action() {
                    if (msm.isLocked()) {
                        done = false;
                    } else {
                        try {
                            ActiveConversation newConversation = null;
                            Enumeration collaborators = current.getCollaboratorsElements();
                            if (collaborators != null && collaborators.hasMoreElements()) {
                                newConversation = createNewInteractionWithCollaboratorsSetByUser(
                                        ja, t, current, newConversation, collaborators);
                            } else {
                                newConversation = createNewInteractionWithCollaboratorsAutomaticallySet(
                                        ja, t, current);
                            }

                            if (newConversation != null) {
                                if (t.getConversationContext() != null) {
                                    Enumeration enumEnt;
                                    /*copyConversationContextContentToNewConversation(
                                    t, newConversation);*/ // not necessary since the information is already
                                    // accesible from child conversations
                                    copyContentFromTempConversationToNewConversation(
                                            current, newConversation);
                                    newConversation.getConv().setParentConversation(t.getConversationContext());
                                    t.getConversationContext().addChildConversation(newConversation.getConv());
                                //updateAncestorConversationOfDifferentType(newConversation.getConv(),t.getConversationContext());
                                } else {
                                    copyContentFromTempConversationToNewConversation(
                                            current, newConversation);
                                }
                                Enumeration<ingenias.editor.entities.Entity> ents = newConversation.getConv().getCurrentContentElements();
                                // after creating the new conversations, the conversation locks managers are properly initialised. Then
                                // the concrete locks to be added to these new conversations are processed.
                                while (ents.hasMoreElements()) {
                                    ja.getLM().getCLM(newConversation.getConv()).addDeletionLock((MentalEntity) ents.nextElement());
                                }
                            } else {
                                //System.err.println("Un error "+cid+" in "+t.getID());
                            }
                        } catch (NoAgentsFound e) {
                            e.printStackTrace();
                            current.setState("ABORTED");
                            current.setAbortCode(IAFProperties.NO_AGENTS_FOUND);
                        }
                        interactionsProcessed.remove(0); // to indicate one interaction was already processed
                        done = true;
                        

                    }
                }

                @Override
                public boolean done() {
                    return done;
                }

                private void copyContentFromTempConversationToNewConversation(
                        final RuntimeConversation current,
                        ActiveConversation newConversation) {
                    Enumeration enumEnt;
                    enumEnt = current.getCurrentContentElements();
                    while (enumEnt.hasMoreElements()) {
                        MentalEntity nelement = (MentalEntity) enumEnt.nextElement();
                        if (!contains(newConversation.getConv(), nelement) && !(nelement instanceof RuntimeConversation)) {
                            newConversation.getConv().addCurrentContent(nelement);
                        }
                    }
                }

                private void copyConversationContextContentToNewConversation(
                        final Task t, ActiveConversation newConversation) {
                    Enumeration enumEnt = t.getConversationContext().getCurrentContentElements();
                    while (enumEnt.hasMoreElements()) {
                        MentalEntity nelement = (MentalEntity) enumEnt.nextElement();
                        if (!contains(newConversation.getConv(), nelement) && !(nelement instanceof RuntimeConversation)) {
                            newConversation.getConv().addCurrentContent(nelement);
                        }
                    }
                }

                private ActiveConversation createNewInteractionWithCollaboratorsAutomaticallySet(
                        final JADEAgent ja, final Task t,
                        final RuntimeConversation current) throws NoAgentsFound {
                    ActiveConversation actconv;
                    // automatic selection of participants
                    actconv = ja.getCM().launchProtocolAsInitiator(current.getInteraction().getId(),
                            ja.getAM().getYellowPages());
                    StackEntry se = new StackEntry("");
                    se.setOperation("Creation");
                    se.setPlace(t.getID() + ":" + t.getType());
                    se.setResposible(ja.getLocalName());
                    se.setTime("" + new java.util.Date().getTime());


                    actconv.getConv().addStack(se);
                    return actconv;
                }

                private ActiveConversation createNewInteractionWithCollaboratorsSetByUser(
                        final JADEAgent ja, final Task t,
                        final RuntimeConversation current,
                        ActiveConversation actconv, Enumeration<AgentExternalDescription> collaborators)
                        throws NoAgentsFound {
                    //Collaborators have been set
                    AgentExternalDescription[] actors;
                    Vector<AgentExternalDescription> descV = new Vector<AgentExternalDescription>();
                    String colaboratorsFound = "";
                    String colaboratorNotFound = "";
                    while (collaborators.hasMoreElements() && 
                            ((current.getAbortCode() !=null && current.getAbortCode() != IAFProperties.INTERNAL_FAILURE)
                            || current.getAbortCode()==null)) {
                    	AgentExternalDescription colLocalID = collaborators.nextElement();
                            //descr = ja.getAM().getYellowPages().getAgentFromLocalID(colLocalID);
                        //if (descr.length > 0) {
							descV.add(colLocalID);
							//colaboratorsFound = colaboratorsFound + descr[0].getName().getLocalName();
                        //}                       
                    }
                    actors = descV.toArray(new AgentExternalDescription[descV.size()]);
                    if (ja.getCM().verifyActors(current.getInteraction().getId(), actors)) {
                        actconv = ja.getCM().launchProtocolAsInitiator(
                                current.getInteraction().getId(), actors);
                        StackEntry se = new StackEntry("");
                        se.setOperation("Creation");
                        se.setPlace(t.getID() + ":" + t.getType());
                        se.setResposible(ja.getLocalName());
                        se.setTime("" + new java.util.Date().getTime());
                        actconv.getConv().addStack(se);

                    } else {
                        EventManager.getInstance().failedToFindColaboratorsWhenManuallyInitializingAConversation(
                                ja.getLocalName(), ja.getClass().getName().substring(0, ja.getClass().getName().indexOf("JADE")),
                                t,
                                current.getInteraction().getType(),
                                colaboratorNotFound);


                        current.setState("ABORTED");
                        current.setAbortCode(IAFProperties.NO_AGENTS_FOUND);
                    }
                    return actconv;
                }

                private void updateAncestorConversationOfDifferentType(
                        RuntimeConversation current,
                        RuntimeConversation parent) {
                    if (!parent.getInteraction().getId().equalsIgnoreCase(current.getInteraction().getId())) {
                        if (!contains(current, parent)) {
                            current.addCurrentContent(parent);
                        }
                    } else {
                        Enumeration<MentalEntity> content = parent.getCurrentContentElements();
                        while (content.hasMoreElements()) {
                            MentalEntity next = content.nextElement();
                            if (next instanceof RuntimeConversation) {
                                updateAncestorConversationOfDifferentType(current, (RuntimeConversation) next);
                            }
                        }
                    }
                }

                private boolean contains(RuntimeConversation conv,
                        MentalEntity newME) {
                    Enumeration elements = conv.getCurrentContentElements();
                    boolean found = false;
                    while (elements.hasMoreElements() && !found) {
                        MentalEntity nelement = (MentalEntity) elements.nextElement();
                        if (nelement.getId().equals(newME.getId())) {
                            found = true;
                        }
                    }

                    return found;
                }
            };
            oneShotBehaviour.setBehaviourName("New interactions creation for task " + t.getID() + ":" + t.getType());
            ja.addBehaviour(oneShotBehaviour);


        }
    }

    private void createNewEntities(final JADEAgent ja, MentalStateManager msm, final Task t, RuntimeConversation currentConv,
            TaskOutput generatedOutput, Vector<RuntimeConversation> newInteractions) {



        createNewEntitiesInAgentMentalState(ja, msm, t, generatedOutput,
                newInteractions);



        createNewEntitiesInConversationContext(ja, msm, t, currentConv,
                generatedOutput, newInteractions);


        if (currentConv != null) {
            HashSet<MentalEntity> newEntities = generatedOutput.getNewEntitiesWF();

            for (MentalEntity newEntity : newEntities) {
                hierarchicalConversationUpdate(newEntity, currentConv, currentConv.getInteraction().getId());
            }
        }


    }

    private void createNewEntitiesInConversationContext(final JADEAgent ja,
            MentalStateManager msm, final Task t,
            RuntimeConversation currentConv, TaskOutput generatedOutput,
            Vector<RuntimeConversation> newInteractions) {
        HashSet<MentalEntity> newEntities;
        newEntities = generatedOutput.getNewEntitiesWF();

        for (MentalEntity newEntity : newEntities) {

            if (newEntity instanceof RuntimeFact) { // Insert debugging stack data
                insertDebuggingInformationIntoRuntimeFact(ja, t, newEntity);
            }

            if (currentConv != null && !(newEntity instanceof RuntimeConversation)) {
                // insert new entity in created conversations and the current conversation
                // child conversations can access the local information by chaining calls
                // to references stored within their conversations
				/*if (!newInteractions.isEmpty()){
                for (RuntimeConversation conv:newInteractions){
                conv.addCurrentContent(newEntity);
                }
                } */
                currentConv.addCurrentContent(newEntity);
                EventManager.getInstance().addedNewEntityToConversation(
                        ja.getLocalName(),
                        ja.getClass().getName().substring(ja.getClass().getName().indexOf("JADE")),
                        t,
                        newEntity,
                        currentConv);


                ja.getLM().getCLM(currentConv).addDeletionLock((MentalEntity) newEntity);// A lock is set on that entity
            } else {
                if (currentConv == null && !(newEntity instanceof RuntimeConversation)) {
                    // if there is no current conversation just add to created conversations
                    // or to the mental state

                    //	MainInteractionManager.logMSP("Produced entity "+newEntity.getId()+":"+newEntity.getType(),ja.getLocalName(),t.getID(),t.getType());
                    try {
                        if (!newInteractions.isEmpty()) {
                            for (RuntimeConversation conv : newInteractions) {
                                conv.addCurrentContent(newEntity);
                            // A lock is set on that entity
                            // provided that the concrete conversation locks manager is set to recognise that kind of entity
                            // this will be made on launching the task
                            }
                        } else {
                            msm.addMentalEntity((MentalEntity) newEntity);
                        }
                    } catch (InvalidEntity e) {
                        e.printStackTrace();
                    } //Adds a new fact to the mental state
                }
            }
        }
    }

    private void insertDebuggingInformationIntoRuntimeFact(final JADEAgent ja,
            final Task t, MentalEntity newEntity) {
        StackEntry se = new StackEntry("");
        se.setOperation("Creation");
        se.setPlace(t.getID() + ":" + t.getType());
        se.setResposible(ja.getLocalName());
        se.setTime("" + new java.util.Date().getTime());
        ((RuntimeFact) newEntity).addStack(se);
    }

    private void createNewEntitiesInAgentMentalState(final JADEAgent ja,
            MentalStateManager msm, final Task t, TaskOutput generatedOutput,
            Vector<RuntimeConversation> newInteractions) {
        HashSet<MentalEntity> newEntities = generatedOutput.getNewEntitiesMS();
        for (MentalEntity newEntity : newEntities) {
            if (newEntity instanceof RuntimeFact) {
                insertDebuggingInformationIntoRuntimeFact(ja, t, newEntity);
            }
            if (newEntity instanceof RuntimeConversation) {
                RuntimeConversation conv = (RuntimeConversation) newEntity;
                newInteractions.add(conv);
                EventManager.getInstance().producedAConversation(ja.getLocalName(), ja.getClass().getName().substring(0, ja.getClass().getName().indexOf("JADE")),
                        t, conv);

            } else if (newEntity instanceof MentalEntity) {
                EventManager.getInstance().addedNewEntityToMS(
                        ja.getLocalName(), ja.getClass().getName().substring(0, ja.getClass().getName().indexOf("JADE")),
                        t, (MentalEntity) newEntity);

                try {
                    msm.addMentalEntity((MentalEntity) newEntity);
                    //ja.getLM().getGlm().addDeletionLock((MentalEntity)newEntity); // in case there are entities to be sent
                    if (t.getConversationContext() != null) {
                        ja.getLM().getCLM(t.getConversationContext()).addDeletionLock((MentalEntity) newEntity); // in case there are entities to be sent
                    }

                } catch (InvalidEntity e) {
                    e.printStackTrace();
                } //Adds a new fact to the mental state
            }
        }
    }

    /**
     * Updates the hierarchy of conversations inserting copies of a produced mental entity.
     * If a sequence of several conversations of the same protocol are found, only the first one is
     * updated. This way, the last one will have access to the new information and, according to the
     * mental management schema, to the information of parent conversations.
     * @param newEntity
     * @param rc
     * @param lastType
     */
    private void hierarchicalConversationUpdate(MentalEntity newEntity,
            RuntimeConversation rc, String lastType) {

        if (rc.getParentConversation() != null) {

            if (!(rc.getParentConversation().getInteraction().getId().equalsIgnoreCase(lastType))) {
                (rc.getParentConversation()).addCurrentContent(newEntity);
            }
            hierarchicalConversationUpdate(newEntity, rc.getParentConversation(),
                    rc.getParentConversation().getInteraction().getId());

        }
    }

    private void removeConsumedInputs(final JADEAgent ja, MentalStateManager msm, final Task t, TaskOutput generatedOutput) {
        HashSet<MentalEntity> consumedInputs = generatedOutput.getConsumedEntities();

        for (MentalEntity consumedFact : consumedInputs) {
            if (t.getConversationContext() == null) {
                msm.remove(consumedFact.getId());
                EventManager.getInstance().consumedEntityDuringtaskExecutionFinished(ja.getLocalName(),
                        ja.getClass().getName().substring(0, ja.getClass().getName().indexOf("JADE")),
                        t,
                        (MentalEntity) consumedFact);


            //System.err.println("Consumed fact "+consumedFact.getName()+" from mental state "+t.getID());
            } else {
                msm.removeFromConversation(t.getConversationContext(), consumedFact.getId());
                msm.remove(consumedFact.getId());
                EventManager.getInstance().consumedEntityDuringtaskExecutionFinishedFromConversation(ja.getLocalName(),
                        ja.getClass().getName().substring(0,
                        ja.getClass().getName().indexOf("JADE")),
                        t,
                        (MentalEntity) consumedFact, t.getConversationContext());
            //System.err.println("Consumed fact "+consumedFact.getName()+" from conversation by "+t.getID());
            }
        }
    }
}
