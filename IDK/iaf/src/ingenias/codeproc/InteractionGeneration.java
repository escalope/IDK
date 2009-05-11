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
package ingenias.codeproc;


import ingenias.codeproc.jade.NoInitialIU;
import ingenias.editor.Log;
import ingenias.exception.CycleInProtocol;
import ingenias.exception.MultipleInitialPoints;
import ingenias.exception.NotFound;
import ingenias.exception.NotInitialised;
import ingenias.exception.NullEntity;
import ingenias.generator.browser.Browser;
import ingenias.generator.browser.Graph;
import ingenias.generator.browser.GraphAttribute;
import ingenias.generator.browser.GraphCollection;
import ingenias.generator.browser.GraphEntity;
import ingenias.generator.browser.GraphRelationship;
import ingenias.generator.browser.GraphRole;
import ingenias.generator.datatemplate.Repeat;
import ingenias.generator.datatemplate.Sequences;
import ingenias.generator.datatemplate.Var;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Vector;

import sun.nio.cs.Surrogate.Generator;
/**
 * The class is responsible of producing information related with the interaction implementation. For each interaction
 * identified in the specification, it locates the associated protocols, concretely a GRASIA specification.
 * Using this specification as starting point, it produces declarations of several state machines each
 * one representing the view of an actor of the interaction. Hence, there is no global representation of
 * the state.  Also, the processing includes information about the conditions required to send/receive
 * messages across agents. These conditions act as interaction guards.
 *
 * @author jj
 *
 */
public class InteractionGeneration {
    /**
     * It encapsulates a communicative act. It will be used to determine the state machines to be used by each
     * participant in a protocol
     *
     * @author jj
     *
     */
    class CommAction {
        final static int RECEIVE = 0;
        final static int SEND = 1;
        private int kind = RECEIVE;
        private GraphEntity iu;
        
        public CommAction(int kind, GraphEntity iu) {
            this.kind = kind;
            this.iu = iu;
        }
        
        int getKind() {
            return kind;
        }
        
        GraphEntity getIU() {
            return iu;
        }
    };
    IAFGenerator mygen;
	private Browser browser;
    
    public InteractionGeneration(IAFGenerator generator, Browser browser) {
        this.mygen=generator;
        this.browser=browser;
    }
    
    /**
     * A sender state has information about the current state and which states should
     * go next (information already supplied by the method parameter). Also, the state
     * includes information about the mental state required
     * to allow initiating the interaction unit. The information to be sent is determined
     * by studying associated tasks to the �IInitates and UIColaborates relationship that links the actor
     * with the interaction unit. This information has to be present in the sender mental state, otherwise
     * the message will not be sent.
     *
     * @param r The data structure where information will be inserted
     * @param actor The actor considered
     * @param interaction The interaction being transformed into state machines
     * @param iu The current interaction unit under study
     * @param nextIUs The interaction units following this one and where this actor appears (this may
     * require ignoring intermediate interaction units)
     * @param iucols The colaborators that should receive the message
     */
    public void generateSenderStates(Repeat r, GraphEntity actor,
            GraphEntity interaction, GraphEntity iu,
            Vector nextIUs, GraphEntity[] iucols) {
        // It is initiator
        Repeat r1 = new Repeat("sendaction");
        r.add(r1);
        r1.add(new Var("iud", Utils.replaceBadChars(iu.getID())));
        GraphRole[] initiatessource=Utils.getRelatedElementsRoles(iu, "UIInitiates", "UIInitiatessource");
        try {
        	if (initiatessource[0].getAttributeByName("Timeout").getSimpleValue()==null ||
        			initiatessource[0].getAttributeByName("Timeout").getSimpleValue().equals("")){
        		r1.add(new Var("timeout", "0"));
        	} else
			 r1.add(new Var("timeout", Utils.replaceBadChars(initiatessource[0].getAttributeByName("Timeout").getSimpleValue())));
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Actors that will receive the message
        for (int k = 0; k < iucols.length; k++) {
            Repeat r2 = new Repeat("receiver");
            r1.add(r2);
            r2.add(new Var("receiver", Utils.replaceBadChars(iucols[k].getID())));
        }
        
                /*        if (nextIUs.size() != 0) {
                 // Adds different options to the state machine
                  r1.add(new Var("nextstateid",
                  ( (GraphEntity) nextIUs.elementAt(0)).getID()));
                  }
                  else */
        this.associateMentalConditionIU(actor, interaction,
                iu,r1);
        this.generateContentMessage(actor,iu,r1);
        if (nextIUs.size() > 0) {
            // all of them are senders
            for (int l = 0; l < nextIUs.size(); l++) {
                Repeat possibleOptions = new Repeat("nextstates");
                r1.add(possibleOptions);
                possibleOptions.add(new Var("possibleiu",
                        Utils.replaceBadChars(( (GraphEntity) nextIUs.elementAt(l)).
                        getID())));
            }
            
        } else {
            // Nothing more to do but finishing the protocol
            Repeat possibleOptions = new Repeat("nextstates");
            r1.add(possibleOptions);
            possibleOptions.add(new Var("possibleiu",
                    Utils.replaceBadChars("end" + iu.getID())));
            //r1.add(new Var("condition", "true"));
        }
    }
    
    /**
     * For each interaction unit, the model can associate a mental condition, i.e., an agent diagram
     * expressing which mental entities should exist and which requirements they should satisfy. When
     * the actor collaborates, this means that it will not attend the message until the conditions are
     * met. When the actor initiates the message, this means the initiator will not send the message until
     * teh conditions are met.
     *
     * @param actor The actor being studied
     * @param interaction The interaction in which it participates
     * @param iu The concrete interaction unit where this actor intitates or collaborates
     * @param r1 The data structure where extracted information will be put
     */
    private void associateMentalConditionIU(GraphEntity actor,
            GraphEntity interaction,
            GraphEntity iu, Repeat r1) {
        
        String graphid="";
        String relid="";
        try {
            
            GraphRelationship[] iuinitrels;
            
            GraphEntity[] iuinit1 = Utils.getRelatedElementsAux(
                    iu, "UIInitiates",
                    "UIInitiatestarget");
            
            boolean isinit = iuinit1[0].equals(actor);
            ////System.err.println( "init"+iuinit1[0]);
            GraphEntity[] iucol1 = Utils.getRelatedElementsAux(
                    iu, "UIColaborates",
                    "UIColaboratestarget");
            
            boolean iscol = iucol1[0].equals(actor);
            ////System.err.println("col"+ iucol1[0]);
            if (isinit) {
                iuinitrels = Utils.getRelatedElementsRels(
                        iu, "UIInitiates",
                        "UIInitiatestarget");
            } else {
                iuinitrels = Utils.getRelatedElementsRels(
                        iu, "UIColaborates",
                        "UIColaboratestarget");
                
            }
            ////System.err.println("Mental condition for "+iu+" and actor "+actor);
            graphid=iuinitrels[0].getGraph().getName();
            GraphAttribute ga = iuinitrels[0].getAttributeByName("Condition");
            relid=iuinitrels[0].getID();
            if (ga != null) {
                GraphEntity ge = ga.getEntityValue();
                if (ge.getType().equals("GRASIAMentalStatePattern")) {
                    try {
                        GraphAttribute model = ge.getAttributeByName(
                                "DescriptionWithAgentModel");
                        try {
                            GraphAttribute modelid = model.getEntityValue().
                                    getAttributeByName("ModelID");
                            if (modelid.getSimpleValue() != null) {
                                try {
                                    //System.err.println(iu.getID() + ":" + modelid.getSimpleValue());
                                    this.generateConditionsXML(r1, modelid.getSimpleValue(),
                                            interaction, iu);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        } catch (ingenias.exception.NullEntity ne) {
                            r1.add(new Var("condition", "true"));
                            Log.getInstance().logWARNING(" In Interaction unit " +
                                    iu.getID() +
                                    " the collaboration relationship " +
                                    " does not define any mental condition.",graphid,relid);
                            
                        }
                        
                    } catch (ingenias.exception.NotFound nf1) {
                        Log.getInstance().logERROR(" In Interaction unit " + iu.getID() +
                                " the collaboration relationship " +
                                " has no associated description. " +
                                " Please, use Grasia Mental State Pattern",graphid,relid);
                        mygen.fatalError();
                        
                    }
                } else {
                    Log.getInstance().logERROR(" In Interaction unit " + iu.getID() +
                            " the collaboration relationship " +
                            " describes the condition with an unexpected type " +
                            " Please, use Grasia Mental State Pattern", graphid,relid);
                    mygen.fatalError();
                }
            }
        } catch (ingenias.exception.NotFound nf) {
            r1.add(new Var("condition", "true"));
            Log.getInstance().logWARNING(" In Interaction unit " + iu.getID() +
                    " the colloboration relationship does" +
                    " not remark a mental condition. If you" +
                    " feel that this collaboration requires" +
                    " a mental condition, just double click" +
                    " on the relationship",graphid, relid);
        } catch (ingenias.exception.NullEntity ne) {
            r1.add(new Var("condition", "true"));
            Log.getInstance().logWARNING(" In Interaction unit " + iu.getID() +
                    " the colloboration relationship does" +
                    " not remark a mental condition. If you" +
                    " feel that this collaboration requires" +
                    " a mental condition, just double click" +
                    " on the relationship",graphid,relid);
            
        }
        
    }
    
    /**
     * Generate the instantiation sequence for mental conditions. Mental conditions are stored in a
     * agent diagram whose name is "conditionName". The condition it adds is in general: requires
     * the existence of all framefact appearing in the diagram "conditionName" and the boolean expression
     * contained in the "Condition" attribute of the conditional mental state entity appearing in the
     * diagram "conditionName".
     *
     * @param instSeq Contains the instantiation sequence
     * @param conditionName It is the name of the diagram with the mental condition representation
     *
     * @throws java.lang.Exception
     */
    
    private void generateConditionsXML(Repeat instSeq, String conditionName,
            GraphEntity interaction, GraphEntity iu) throws
            Exception {
        Graph mcondition = this.mygen.getBrowser().getGraph(conditionName);
        if (mcondition != null) {
            Vector mcsp = Utils.getEntities(mcondition, "ConditionalMentalState");
            Vector facts = Utils.getEntities(mcondition, "FrameFact");
            
            for (int k=0;k<facts.size();k++){
                //System.err.println(facts.elementAt(k));
            }
            if (facts.size() == 0) {
                Log.getInstance().logWARNING(
                        "There are no FRAME FACTS defined in diagram " +
                        "diagram " + mcondition.getName() + ". " +
                        "Interaction " + interaction.getID() + " in interaction unit " +
                        iu.getID() +
                        "says that this diagram defines a mental state condition and facts may be required. " +
                        "Please, check that you are using FrameFacts. To do so, select a fact that you are using and right click on it, and check that there is no any refine option. If there is, select in the refine submenu the FrameFact entry");
                
            }
            if (mcsp.size() > 1) {
                Log.getInstance().logERROR(
                        "There is more than one instances of ConditionalMentalState in " +
                        "diagram " + mcondition.getName() + ". " +
                        "Interaction " + interaction.getID() + " in interaction unit " +
                        iu.getID() +
                        "says that this diagram defines a mental state condition. " +
                        "Please, use only one mental state pattern per diagram",mcondition.getName(),iu.getID());
                mygen.fatalError();
            } else {
                if (mcsp.size() == 0) {
                    Log.getInstance().logERROR(
                            "There are no instances of ConditionalMentalState in " +
                            "diagram " + mcondition.getName() + ". " +
                            "Interaction " + interaction.getID() + " in interaction unit " +
                            iu.getID() +
                            "says that this diagram defines a mental state condition. " +
                            "Please, use one mental state pattern in the diagram",mcondition.getName(),iu.getID());
                    mygen.fatalError();
                } else {
                    GraphEntity condstate = (GraphEntity) mcsp.elementAt(0);
                    GraphAttribute cond = condstate.getAttributeByName("Condition");
                    String value = cond.getSimpleValue();
                    //System.err.println("Facts " + facts.size());
                    for (int k = 0; k < facts.size(); k++) {
                        GraphEntity fact = (GraphEntity) facts.elementAt(k);
                        if (!fact.getType().equalsIgnoreCase("conversation")){
                            Repeat factr = new Repeat("condfacts");
                            factr.add(new Var("type", Utils.replaceBadChars(fact.getID())));
                            GraphRole[] roles = Utils.getRelatedElementsRoles(fact,
                                    "AContainsME", "AContainsMEtarget");
                            if (roles.length != 0) {
                                String label = roles[0].getAttributeByName("Label").
                                        getSimpleValue();
                                if (label == null || label.equals("")) {
                                    Log.getInstance().logERROR("Fact " + fact.getID() +
                                            " appears in diagram " +
                                            conditionName +
                                            " without any label associated." +
                                            " Double click in the extreme of AContainsME that connects with the fact",conditionName,fact.getID());
                                    mygen.fatalError();
                                    
                                }
                                factr.add(new Var("label",
                                        Utils.replaceBadChars(label)));
                                instSeq.add(factr);
                            } else {
                                Log.getInstance().logERROR("FAct " + fact.getID() +
                                        " appears in diagram " +
                                        conditionName + " without any association "+
                                        "to a mental state. Please associate it with AContainsME relationship",conditionName,fact.getID());
                                mygen.fatalError();
                            }
                        }
                        
                    }
                    instSeq.add(new Var("condition", value));
                    
                    //System.err.println("Todo bien " + value);
                }
            }
        } else {
            Log.getInstance().logERROR(
                    "There is no diagram named " + conditionName +
                    "Interaction " + interaction.getID() + " in interaction unit " +
                    iu.getID() +
                    "says that this diagram defines a mental state condition. " +
                    "Please, use one mental state pattern in the diagram",conditionName,iu.getID());
            mygen.fatalError();
            
        }
        
    }
    
    /**
     * It obtains the interactions that are associated to every role in the specification.
     * For each entity role, it locates instances of IInitiates and IColaborates where the role
     * participates. These relationships connect roles with interaction entities, which are the ones
     * we are interested into. Once obtained, for each interaction, it produces the set of
     * deliver and receive actions involved.
     * @throws NotInitialised
     *
     */
    
    void generateActorActions(Sequences seq) throws NullEntity, NotInitialised {
        GraphEntity[] interactions = Utils.generateEntitiesOfType("Interaction",browser);
        GraphEntity[] roles = Utils.generateEntitiesOfType("Role",browser);
        for (int k = 0; k < interactions.length; k++) {
            GraphEntity interaction = interactions[k];
            if (this.getIUs(interaction)==null ||this.getIUs(interaction).length==0 ){
                Log.getInstance().logERROR("The interaction "+interaction.getID()+" has no interaction units"+
                        " associated. Please, define an interaction diagram with interaction units and create "+
                        " an interaction specification that points to it","",interaction.getID());
                mygen.fatalError();
            } else {
                HashVector iusgroupedbyactor = this.generateSendRec(this.getIUs(
                        interaction));
                Vector cols = Utils.getRelatedElementsVector(interactions[k], "IInitiates",
                        "IInitiatestarget");
                Vector inits = Utils.getRelatedElementsVector(interactions[k],
                        "IColaborates", "IColaboratestarget");
                inits.addAll(cols);
                Enumeration participants = inits.elements();
                while (participants.hasMoreElements()) {
                    GraphEntity actor = (GraphEntity) participants.nextElement();
                    this.generateActorActions(seq, actor, interaction, iusgroupedbyactor);
                }
            }
        }
    }
    
    /**
     * It produces individual state machines for a single actor taking into account its
     * participation in the interaction.  It distinguishes between the initiator of the
     * interaction and collaborators.
     *
     * @param seq The data tree where information will be inserted
     * @param actor The actor participating in the interaction
     * @param interaction The interaction being analized
     * @param ius The interaction units of the interaction indexed by actor
     * @throws NullEntity
     */
    private void generateActorActions(Sequences seq, GraphEntity actor,
            GraphEntity interaction, HashVector ius) throws NullEntity {
        Repeat r1 = new Repeat("agentinteractionscols");
        seq.addRepeat(r1);
        r1.add(new Var("interactionid", Utils.replaceBadChars(interaction.getID())));
        // Obtain initiators of this interaction
        r1.add(new Var("roleid", Utils.replaceBadChars(actor.getID())));
        GraphEntity firstIU = null;
        try {
            firstIU = this.getFirstIU(interaction);
        } catch (Exception niu) {
            niu.printStackTrace();
        }
        Vector initiator = Utils.getRelatedElementsVector(firstIU, "UIInitiates",
                "UIInitiatestarget");
        if (initiator.contains(actor)) {
            // It is the interaction initiator
            Repeat r2 = new Repeat("initialiseInititator");
            r1.add(r2);
            this.associateMentalConditionIU(actor, interaction, firstIU, r1);
            r2.add(new Var("firstiu", Utils.replaceBadChars(firstIU.getID())));
            Vector cols = Utils.getRelatedElementsVector(interaction, "IColaborates",
                    "IColaboratestarget");
            Enumeration enumeration = cols.elements();
            while (enumeration.hasMoreElements()) {
                GraphEntity col = (GraphEntity) enumeration.nextElement();
                Repeat r3 = new Repeat("receivers");
                r3.add(new Var("receiver", Utils.replaceBadChars(col.getID())));
                r2.add(r3);
                
            }
            
        } else {
            try {
                firstIU = this.getFirstIU(interaction, actor);
            } catch (Exception niu) {
                niu.printStackTrace();
            }
            
            // It is an interaction collaborator
            Repeat r2 = new Repeat("initialiseColaborator");
            r1.add(r2);
            r2.add(new Var("firstiu", Utils.replaceBadChars(firstIU.getID())));
            this.associateMentalConditionIU(actor, interaction, firstIU, r1);
        }
        
        Vector rius = ius.get(actor);
        generateStates(r1, actor, rius, interaction);
    }
    
    /**
     * It obtains all the interaction units associated to the interaction. First we obtain the specification entity
     * with IHasSpec. Then, the linked interaction diagram by reading the attributes of the specification. Then we go to the
     * interaction diagram and obtain all the interaction units.
     * @param interaction The interaction being studied
     * @return An array of the interaction units associated to the interaction
     * @throws NotInitialised
     */
    public GraphEntity[] getIUs(GraphEntity interaction) throws NotInitialised{
        Graph[] g = this.mygen.getBrowser().getGraphs();
        Vector selected = new Vector();
        try {
            Vector<GraphEntity> specs=Utils.getRelatedElementsVector(interaction,"IHasSpec" ,"IHasSpectarget");
            Vector<GraphEntity> notRecognisedSpecs=new Vector<GraphEntity>();
            for (GraphEntity ge:specs){
                if (!ge.getType().equalsIgnoreCase("grasiaspecification")){
                    notRecognisedSpecs.add(ge);
                }
            }
            specs.removeAll(notRecognisedSpecs);
            //System.err.println("Remanining size "+specs.size());
            if (specs.size()==0){
                Log.getInstance().logERROR("Interaction "+interaction+
                        " has no associated protocol specification. "+
                        "Please, use a GRASIASpecification entity and link it with the interaction",
                        "",interaction.getID());
                mygen.fatalError();
            } else {
                if (specs.size()>1)
                    Log.getInstance().logERROR("Interaction "+interaction+" has more than one associated protocol specification. Please, use only one","",interaction.getID());
                else {
                    GraphEntity spec=(GraphEntity)specs.elementAt(0);
                    Graph interactionprotocol=this.locateInteractionDiagramFromSpecification(spec);
                    if (!mygen.getError()){
                        GraphEntity[] ges = interactionprotocol.getEntities();
                        for (int i = 0; i < ges.length; i++) {
                            if (ges[i].getType().toLowerCase().equals("interactionunit") ||
                                    ges[i].getType().toLowerCase().equals("messagepassing")) {
                                selected.add(ges[i]);
                            }
                        }
                        if (ges.length==0){
                            Log.getInstance().logERROR("Interaction "+interaction+
                                    " has no associated interaction units in diagram "+interactionprotocol.getName()+ " which is the "+
                                    " one pointed out by its specification "+spec.getID()+".",
                                    interactionprotocol.getName(),"");
                            mygen.fatalError();
                        }
                    } else {
                        System.err.println("Error indetectado en "+interaction);
                    }
                }
                
            }
        } catch (NullEntity e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (selected.size()==0){
            System.err.println("SIZE 0 con "+interaction.getID());
        }
        return Utils.toGEArray(selected.toArray());
        
    }
    
    /**
     * Similar to getIUs(GraphEntity interaction) but limiting to the interaction units where the "actor"
     * participates with either IUColaborates or IUInitiates
     *
     * @param interaction The interaction being considered
     * @param actor The actor being studied
     * @return An array of interaction units where the actor is involved
     */
    private GraphEntity[] getIUs(GraphEntity interaction, GraphEntity actor) {
        Graph[] g = mygen.getBrowser().getGraphs();
        Vector selected = new Vector();
        for (int k = 0; k < g.length; k++) {
            try {
                GraphEntity candidate = g[k].getAttributeByName("interactionConsidered").
                        getEntityValue();
                if (candidate.getID().equals(interaction.getID())) {
//					candidate.get
                    GraphEntity[] ges = g[k].getEntities();
                    for (int i = 0; i < ges.length; i++) {
                        if (ges[i].getType().toLowerCase().equals("interactionunit") ||
                                ges[i].getType().toLowerCase().equals("CustomerRequestsABook")) {
                            selected.add(ges[i]);
                        }
                    }
                }
            } catch (ingenias.exception.NullEntity ne) {
                // The attribute was set to null
            } catch (ingenias.exception.NotFound nf) {
            }
        }
        
        return Utils.toGEArray(selected.toArray());
        
    }
    
    /**
     * It produces the different states an actor may go through when interacting with others. Every actor
     * has a different state machine representing its view of the protocol and its evolution. Hence, there
     * is no shared global state. It takes as input the different interaction units this actor participates
     * into. Then, it generates reception or sending states for each interaction unit, depending on the
     * role of the actor (UIColaboratestarget= receives, UIInitiatestarget= sends). To generate these states,
     * it is required to produce as well the transition function, i.e., from the current state, which state should
     * go next? This information is obtained by getNextIU method.
     *
     * @param r	The data structure where information will be inserted
     * @param actor The actor considered
     * @param ius The interaction units this actors participates into
     * @param interaction	The interaction under study
     * @throws NullEntity
     */
    private void generateStates(Repeat r, GraphEntity actor, Vector ius,
            GraphEntity interaction) throws NullEntity {
        Enumeration enumeration = ius.elements();
        while (enumeration.hasMoreElements()) {
            CommAction ca = (CommAction) enumeration.nextElement();
            GraphEntity iu = ca.getIU();
            Vector nextIUs = this.getNextIU(iu, this.obtainIUFromCommAction(ius));
            GraphEntity[] iucols = Utils.getRelatedElements(iu, "UIColaborates",
                    "UIColaboratestarget");
            GraphEntity[] iuinit = Utils.getRelatedElements(iu, "UIInitiates",
                    "UIInitiatestarget");
            GraphRelationship[] iuinitrels = Utils.getRelatedElementsRels(iu,
                    "UIInitiates",
                    "UIInitiatestarget");
            
            if (ca.getKind() == CommAction.RECEIVE) {
                generateReceiverStates(r, actor, interaction, iu, nextIUs, iuinit);
            } else {
                generateSenderStates(r, actor, interaction, iu, nextIUs, iucols);
            }
            if (nextIUs.size() == 0) {
                Repeat r1 = new Repeat("endstate");
                r.add(r1);
                r1.add(new Var("stateid", Utils.replaceBadChars(iu.getID())));
            } else {
                
            }
        }
    }
    
    
    /**
     * Given an interactio unit, it determines which information should be sent. The information
     * is obtained from the involved tasks on one side of the interaction unit (colaborator side)
     * and on the other one (initiator side). It is sent any information produced by the task in
     * the initiator side and any other information required as input by the corresponding task in
     * the colaborator side. This information will have to be present in the sender, otherwise the
     * communicative act will not happen.
     *
     * @param actor The actor considered
     * @param iu The current interaction unit under study
     * @param r1 The data structure where information will be inserted
     */
    private void generateContentMessage(GraphEntity actor, GraphEntity iu, Repeat r1) {
        GraphCollection collection;
        HashSet<String> toAdd=new HashSet<String>();
        try {
            GraphEntity[] tasks = Utils.getRelatedElementsAux(
                    iu, "UIInitiates",
                    "UIExecutestarget");
            java.util.Hashtable<String,GraphEntity> elementsToTransfer=new java.util.Hashtable<String,GraphEntity>();
            
            // This is the information the tasks can supply on the sender side
            for (int k=0;k<tasks.length;k++){
                Vector v=mygen.getExpectedOutput(tasks[k]);
                for (int j=0;j<v.size();j++){
                    GraphEntity eo=(GraphEntity)v.elementAt(j);
                    if (!eo.getType().equalsIgnoreCase("conversation")){
                        elementsToTransfer.put(eo.getID(),eo);
                        
                    }
                    
                }
            }
            try {
                // Information to be transferred according to the interaction unit
                if (iu.getAttributeByName("TransferredInfo")!=null){
                    collection=iu.getAttributeByName("TransferredInfo").getCollectionValue();
                    for (int k=0;k<collection.size();k++){
                        toAdd.add(Utils.replaceBadChars(collection.getElementAt(k).getID()));
                        
                    }
                };
            } catch (NotFound ex) {
                ex.printStackTrace();
            }
            
            // This is the information requested on the client side
            tasks = Utils.getRelatedElementsAux(
                    iu, "UIColaborates",
                    "UIExecutestarget");
            
                        /*for (int k=0;k<tasks.length;k++){
                                System.err.println("considerando tarea "+tasks[k].getID()+ " para "+iu.getID());
                                Vector<GraphEntity> v=mygen.getConsumedInputsInWorkflow(tasks[k]);
                                for (int j=0;j<v.size();j++){
                                        System.err.println("considerando "+v.elementAt(j).getID());
                                        GraphEntity eo=(GraphEntity)v.elementAt(j);
                                        //if (elementsToTransfer.containsKey(eo.getID())) {
                                        // All information consumed on the collaborator side is supposed to come from
                                        // the initiator side.
                                        System.err.println("Transmitiendo colaborador "+Utils.replaceBadChars(eo.getID()));
                                        toAdd.add(Utils.replaceBadChars(eo.getID()));
                         
                                        //}
                                }
                        }*/
            for (String type:toAdd){
                Repeat r=new Repeat("mescontent");
                r1.add(r);
                r.add(new Var("type",type));
            }
            
            
        } catch (NullEntity e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * It produces states required for a collaborator to process a message. As in the sender states,
     * receiver states come from iucollaborates relationships. It also attachs a condition
     * expressed as an agent diagram to determine when the collaborator should pay attention to
     * the message sent. Finally, it incorporates in the data structure which states are next to this one.
     *
     * @param r The data structure where information will be inserted
     * @param actor The actor considered
     * @param interaction The interaction being transformed into state machines
     * @param iu The current interaction unit under study
     * @param nextIUs The interaction units following this one and where this actor appears (this may
     * require ignoring intermediate interaction units)
     * @param iuinit The sender of the message
     */
    private void generateReceiverStates(Repeat r, GraphEntity actor, GraphEntity interaction,
            GraphEntity iu, Vector nextIUs, GraphEntity[] iuinit) {
        Vector<GraphEntity> initiator;
        // It is a collaborator
        Repeat r1 = new Repeat("receiveaction");
        r1.add(new Var("iud", Utils.replaceBadChars(iu.getID())));
        r1.add(new Var("sender", Utils.replaceBadChars(iuinit[0].getID())));
        
       
        try {
            initiator=Utils.getRelatedElementsVector(iu,
                        "UIInitiates", "UIInitiatestarget");            
            GraphRole[] colaboratessource=Utils.getRelatedElementsRoles(iu, "UIColaborates", "UIColaboratessource");
            r1.add(new Var("senderactor", Utils.replaceBadChars(initiator.firstElement().getID())));
            
            if (colaboratessource[0].getAttributeByName("Timeout").getSimpleValue()==null ||
        			colaboratessource[0].getAttributeByName("Timeout").getSimpleValue().equals("")){
        		r1.add(new Var("timeout", "0"));
            } else
            	r1.add(new Var("timeout", Utils.replaceBadChars(colaboratessource[0].getAttributeByName("Timeout").getSimpleValue())));
            
        } catch (NullEntity ex) {
            ex.printStackTrace();
        } catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        String cardinality=getCardinalitySender(iu,interaction, actor);
        r1.add(new Var("cardinality", cardinality));
        
        r.add(r1);
        this.associateMentalConditionIU(actor, interaction,
                iu,
                r1);
        if (nextIUs.size() > 0) {
            for (int l = 0; l < nextIUs.size(); l++) {
                Repeat possibleOptions = new Repeat("nextstates");
                
                r1.add(possibleOptions);
                
                possibleOptions.add(new Var("possibleiu",
                        Utils.replaceBadChars(( (GraphEntity) nextIUs.elementAt(l)).
                        getID())));
            }
        } else {
//			//System.err.println("No next action For " + iu.getID());
            Repeat possibleOptions = new Repeat("nextstates");
            r1.add(possibleOptions);
            possibleOptions.add(new Var("possibleiu",
                    Utils.replaceBadChars("end" + iu.getID())));
            r1.add(new Var("condition", "true"));
            
        }
    }
    
    /**
     * It produces a hastable where indexes are the actors of a single interaction. With the
     * interaction units passed as parameters, it is studied for each actor whether the
     * actor is a sender or a receiver of information. As a result, a CommAction is created
     * to markup the interaction unit as sender or receiver. This information is added to the
     * hashtable to the index associated to the involved actors.
     * @param ius The interaction units to be studied. These come from a single interaction
     * @return A hashtable indexed with the actors of the interaction and with the interaction units associated to each one
     * @throws NullEntity
     */
    private HashVector generateSendRec(GraphEntity[] ius) throws NullEntity {
        HashVector participants = new HashVector();
        for (int k = 0; k < ius.length; k++) {
            GraphEntity[] iucols = Utils.getRelatedElements(ius[k],
                    "UIColaborates", "UIColaboratestarget");
            GraphEntity[] iuinit = Utils.getRelatedElements(ius[k],
                    "UIInitiates", "UIInitiatestarget");
            
            if (iuinit.length==0){
                Log.getInstance().logERROR("The interaction unit "+ius[k].getID()+" has no initiator","",ius[k].getID());
                mygen.fatalError();
            } else {
                if (iucols.length==0){
                    Log.getInstance().logERROR("The interaction unit "+ius[k].getID()+" has no collaborator","",ius[k].getID());
                    mygen.fatalError();
                } else {
                    participants.put(iuinit[0], new CommAction(CommAction.SEND, ius[k]));
                    for (int j = 0; j < iucols.length; j++) {
                        participants.put(iucols[j], new CommAction(CommAction.RECEIVE, ius[k]));
                    }
                }
            }
        }
        return participants;
    }
    
    /**
     * It obtains the first Interaction unit in a protocol. The scope of the search
     * is the interaction units given as parameter. The order is induced by the "precedes" relationship
     * among interaction units. We consider the first that interaction unit such that no other interaction
     * unit "precedes" it.
     *
     * @param ius The interaction units being inspected
     * @return The first interaction unit
     * @throws MultipleInitialPoints
     * @throws ingenias.codeproc.jade.NoInitialIU
     */
    private GraphEntity getFirst(Vector ius) throws
            MultipleInitialPoints, ingenias.codeproc.jade.NoInitialIU {
        Vector candidates = new Vector(ius);
        
        Vector selected = new Vector();
        for (int k = 0; k < ius.size(); k++) {
            GraphEntity ge = (GraphEntity) ius.elementAt(k);
            Vector next = this.getNextIU(ge, ius);
            candidates.removeAll(next);
        }
        selected = candidates;
        
        if (selected.size() > 1) {
            Log.getInstance().logERROR("When considering these interaction units "+ius
                    +"and precedence relationship, I found multiple initial points "+selected,
                    "",((GraphEntity)selected.firstElement()).getID());
            mygen.fatalError();
            throw new MultipleInitialPoints("Found the following initial points "+selected+" when inspecting this collection "+ius);
        }
        
        if (selected.size() == 0) {
            throw new ingenias.codeproc.jade.NoInitialIU();
        }
        
        return (GraphEntity) selected.firstElement();
        
    }
    
    /**
     * It obtains the first interaction unit associated with an interaction. The interaction will
     * be linked with a GRASIASpecification entity. That entity will contain a link to the
     * protocol diagram, which is the place where the interaction units will be defined. The first interaction
     * unit will be naturally linked with the initiator of the protocol
     *
     * @param interaction The interaction being studied.
     * @return The first interaction unit in the protocol associated with the interaction
     * @throws CycleInProtocol
     * @throws MultipleInitialPoints
     * @throws ingenias.codeproc.jade.NoInitialIU
     * @throws NotInitialised
     */
    GraphEntity getFirstIU(GraphEntity interaction) throws
            CycleInProtocol, MultipleInitialPoints,
            ingenias.codeproc.jade.NoInitialIU, NotInitialised {
        GraphEntity result = null;
        GraphEntity[] ius = this.getIUs(interaction);
        if (ius.length == 0) {
            Log.getInstance().logERROR("Interaction " + interaction.getID() +
                    " has no " +
                    " interaction units associated","",interaction.getID());
            mygen.fatalError();
            //throw new ingenias.codeproc.jade.NoInitialIU();
        } else {
            Vector candidates = new Vector();
            for (int k = 0; k < ius.length; k++) {
                candidates.add(ius[k]);
            }
            
            result = this.getFirst(candidates);
        }
        return result;
    }
    
    /**
     * Similar to getFirstIU(GraphEntity interaction), but limiting the results to the first interaction
     * unit a single actor will execute in the protocol. There can be only one initial interaction units in a
     * collaborator.
     *
     * @param interaction The interaction being studied
     * @param actor The actor being considered
     * @return The first interaction unit of the actor in the protocol of the interaction
     * @throws CycleInProtocol
     * @throws MultipleInitialPoints
     * @throws ingenias.codeproc.jade.NoInitialIU
     * @throws NullEntity
     * @throws NotInitialised
     */
    private GraphEntity getFirstIU(GraphEntity interaction, GraphEntity actor) throws
            CycleInProtocol, MultipleInitialPoints,
            ingenias.codeproc.jade.NoInitialIU, NullEntity, NotInitialised {
        //System.err.println("--------- Getting the first interaction unit for actor "+actor+" and interaction "+interaction);
        GraphEntity result = null;
        GraphEntity[] ius = this.getIUs(interaction);
        if (ius.length == 0) {
            Log.getInstance().logERROR("Interaction " + interaction.getID() +
                    " has no " +
                    " interaction units associated","",interaction.getID());
            mygen.fatalError();
            throw new ingenias.codeproc.jade.NoInitialIU();
        } else {
            Vector candidates = new Vector();
            for (int k = 0; k < ius.length; k++) {
                
                Vector cols = Utils.getRelatedElementsVector(ius[k], "UIColaborates",
                        "UIColaboratesTarget");
                Vector inits = Utils.getRelatedElementsVector(ius[k], "UIInitiates",
                        "UIInitiatesTarget");
                if (cols.contains(actor) || inits.contains(actor)) {
                    candidates.add(ius[k]);
                }
            }
            try {
                result = this.getFirst(candidates);
            } catch (NoInitialIU noiu){
                Log.getInstance().logERROR("Interaction " + interaction.getID() +
                        " when considering role, " +actor+" finds multiple initial points "+
                        ". Please, redefine the interaction so that"+
                        "there is only one","",interaction.getID());
                mygen.fatalError();
            }
            
            if (mygen.getError()){
                Log.getInstance().logERROR("Interaction " + interaction.getID() +
                        " when considering role, " +actor+" finds multiple initial points "+
                        ". Please, redefine the interaction so that"+
                        "there is only one","",interaction.getID());
                mygen.fatalError();
            }
        }
        return result;
    }
    
    private Vector obtainIUFromCommAction(Vector ca) {
        Enumeration enumeration = ca.elements();
        Vector result = new Vector();
        while (enumeration.hasMoreElements()) {
            CommAction ge = (CommAction) enumeration.nextElement();
            result.add(ge.getIU());
        }
        return result;
    }
    
    /**
     * Given a specification entity of a protocol it determines the diagram that contains the actual
     * protocol definition.
     *
     * @param spec The specification being studied
     * @return A Graph entity containing the diagram pointed out by the "spec" object
     */
    private Graph locateInteractionDiagramFromSpecification(GraphEntity spec) {
        Vector<GraphEntity> iunits;
        Graph result=null;
        
        GraphEntity model=null;
        try {
            try {
                model = spec.getAttributeByName("ModelThatContainsSpecification").getEntityValue();
            } catch (NullEntity e) {
                Log.getInstance().logERROR("GRASIASpecification "+
                        spec.getID()+" has an undefined attribute ModelThatContainsSpecification."+
                        " Please, open the properties of the entity and define this attribute","",spec.getID());
                mygen.fatalError();
            }
            
            if (!mygen.getError()){
                GraphEntity[] interaction=Utils.getRelatedElements(spec,"IHasSpec","IHasSpecsource");
                if (interaction.length==0){
                    Log.getInstance().logERROR("GRASIASpecification "+
                            spec.getID()+" is not linked to any interaction","",spec.getID());
                    mygen.fatalError();
                } else {
                    if (model!=null){
                        
                        String interactionID=model.getAttributeByName("ModelID").getSimpleValue();
                        //System.err.println("looking for "+interactionID+" "+model.getType());
                        Graph g=mygen.getBrowser().getGraph(interactionID);
                        
                        if (g==null){
                            Log.getInstance().logERROR("GRASIASpecification "+spec.getID()+" referred to a non existent protocol diagram "+interactionID,"",spec.getID());
                            mygen.fatalError();
                            
                        } else {
                            return g;
                        }
                    } else {
                        Log.getInstance().logERROR("GRASIASpecification "+
                                spec.getID()+" has an undefined attribute ModelThatContainsSpecification."+
                                " Please, open the properties of the entity and define this attribute","",spec.getID());
                        mygen.fatalError();
                    }
                }
            } else {
                
                System.err.println("Error detectado con "+spec);
            }
        } catch (NotFound e) {
            e.printStackTrace();
        } catch (NullEntity e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    /**
     * Obtains the next interaction unit in the protocol from a concrete one. There can be more than
     * one possible next interaction unit. Besides, the search must limit to ius. The intention is to
     * separate the interaction units in which a concrete actor participates.
     * @param iu Starting point to look for following interaction units
     * @param ius Interaction units to consider
     * @return The interaction units which follows iu in the protocol
     */
    private Vector getNextIU(GraphEntity iu, Vector ius) {
        
        Vector nextius=this.traverse(iu, ius, new Vector());
        //System.err.println("next for "+iu+" are "+nextius+" constrained for "+ius);
        return nextius;
    }
    
    /**
     * Using the instances of the "UIPrecedes" relationship, we construct different state machines
     * attending to the point of view of individual actors. To do so, we require traversing the
     * interaction units using the precedes relationship and ignoring those
     *
     * @param origin
     * @param targets
     * @param traversed
     * @return
     */
    private Vector traverse(GraphEntity origin, Vector targets, Vector traversed) {
        HashSet result = new HashSet();
        
        Vector connected;
        try {
            connected = Utils.getRelatedElementsVectorAux(origin, "UIPrecedes",
                    "UIPrecedestarget"); // Obtains all connected interaction units to origin
            
            
            Vector connectedCloned = (Vector) connected.clone();
            
            connectedCloned.retainAll(targets); // Removes all interaction units
            // not included in the current scope
            //connectedCloned.remove(origin);
            if (!connectedCloned.contains(origin)) { // To prevent loops
                if (connectedCloned.size() > 0) {
                    // next was found
                    result.addAll(connectedCloned);
                    traversed.addAll(connectedCloned);
                }
                
                // In case the obtained interaction units are not within
                // the interaction units scope, we search for those interaction units
                // connected to the ones obtained.
                Enumeration enumeration = connected.elements();
                while (enumeration.hasMoreElements()) {
                    GraphEntity next = (GraphEntity) enumeration.nextElement();
                    if (!traversed.contains(next)) {
                        traversed.add(next);
                        //System.err.println("traversing "+next);
                        result.addAll(this.traverse(next, targets, traversed));
                    } else {
                        
                    }
                }
                
                
            }
        } catch (NullEntity e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new Vector(result);
        
    }
    
    private String getCardinalitySender(GraphEntity iu, GraphEntity interaction,GraphEntity actor) {
        
        GraphRole[] actorColaborator;
        Vector<GraphEntity> actorInitiator;
        Vector<GraphEntity> initiator;
        String cardinality="1";
        try {
            
            initiator=Utils.getRelatedElementsVector(iu,
                    "UIInitiates", "UIInitiatestarget"); // must have only one element
            
            
/*        actorInitiator=Utils.getRelatedElementsRoles(interaction,
                    "IInitiates", "IInitiatestarget");        */
            
            
            actorColaborator=Utils.getRelatedElementsRoles(interaction,
                    "IColaborates", "IColaboratestarget");
            
            
            for (GraphRole gr:actorColaborator){
                if (gr.getPlayer().getID().equals(initiator.firstElement().getID())){
                    String cardString=gr.getAttributeByName("Cardinality").getSimpleValue();
                    System.err.println("Cardinality for "+iu.getID()+" is "+cardString);
                    if (cardString.equals("1..*")|| cardString.equals("n") ||cardString.equals("0..*") || cardString.equals("*")){
                        cardinality="n";
                    }
                }
                
            }
        } catch (NotFound ex) {
            ex.printStackTrace();
        } catch (NullEntity ex) {
            ex.printStackTrace();
        }
        
        return cardinality;
        
        
    }
    
    
    
    
}
