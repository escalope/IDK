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
import ingenias.generator.browser.BrowserImp;
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
import java.util.Hashtable;
import java.util.Vector;
/**
 *
 * @author jj
 *
 */
public class AnaliserProtocolGenerator {
	
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
	
	IAMGenerator mygen;
	
		public AnaliserProtocolGenerator(IAMGenerator generator) {
		this.mygen=generator;
	}
		
		public void generateProtocolData(Sequences p) throws NotInitialised{
			GraphEntity[] entities = BrowserImp.getInstance().getAllEntities();
			for (GraphEntity entity:entities){
				if (entity.getType().equalsIgnoreCase("interaction")){
					
					Repeat newInteraction=new Repeat("interaction");
					newInteraction.add(new Var("interactionID",Utils.replaceBadChars(entity.getID())));					
					p.addRepeat(newInteraction);
					this.generateProtocolData(entity, newInteraction);		
				}
			}
		}

	public void generateProtocolData(GraphEntity interaction, Repeat interactionRepeat){
		try {			
			GraphEntity first=this.getFirstIU(interaction);	
			GraphEntity[] ius = getIUs(interaction);
			Vector iusv=new Vector();
			for (GraphEntity iu:ius){
				iusv.add(iu);
			}

			GraphEntity[] iuinit = Utils.getRelatedElements(first,
					"UIInitiates", "UIInitiatestarget");
			String initiatorID=Utils.replaceBadChars(iuinit[0].getID());
			Vector<Integer> generatedStates=new Vector<Integer>();
			generateInteractionUnitCode(first,iusv,interactionRepeat,0,"initial",1,initiatorID,generatedStates);
			for (Integer state:generatedStates){
				Repeat states=new Repeat("interactionState");
				interactionRepeat.add(states);
				states.add(new Var("stateid",""+state));	
			}
		} catch (NotInitialised e) {

			e.printStackTrace();
		} catch (CycleInProtocol e) {

			e.printStackTrace();
		} catch (MultipleInitialPoints e) {

			e.printStackTrace();
		} catch (NoInitialIU e) {

			e.printStackTrace();
		} catch (NullEntity e) {

			e.printStackTrace();
		}


	}

	private int generateInteractionUnitCode(GraphEntity currentIU,
			Vector graphEntities, Repeat interactionRepeat, int prevstate, String prevstateType, int cstate, String initiatorID,Vector<Integer> generatedStates) throws NullEntity {
		Repeat iu=new Repeat("transitions");
		interactionRepeat.add(iu);
		iu.add(new Var("iuid",Utils.replaceBadChars(currentIU.getID())));
		GraphEntity[] iuinit = Utils.getRelatedElements(currentIU,
				"UIInitiates", "UIInitiatestarget");
		boolean senderIsInitiator=(iuinit[0].getID().equalsIgnoreCase(initiatorID));
		GraphEntity[] iucol = Utils.getRelatedElements(currentIU,
				"UIColaborates", "UIColaboratestarget");
		boolean receiverIsInitiator=(iucol[0].getID().equalsIgnoreCase(initiatorID));

		Vector<GraphEntity> nextIUS=getNextIU(currentIU,graphEntities);

		try {
			iu.add(new Var("performative",Utils.replaceBadChars(currentIU.getAttributeByName("speechact").getSimpleValue().toUpperCase()).replace("-","_")));
			iu.add(new Var("prevstate",""+prevstate));
			iu.add(new Var("prevstatetype",prevstateType));

			if (nextIUS.isEmpty()){
				iu.add(new Var("nextstate","OK_STATE"));
				iu.add(new Var("nextstatetype","final"));
			} else{
				iu.add(new Var("nextstate",""+cstate));
				iu.add(new Var("nextstatetype","middle"));
				generatedStates.add(cstate);
			}			
			if (senderIsInitiator){
				iu.add(new Var("rolesender","INITIATOR"));

			} else {
				iu.add(new Var("rolesender","PARTICIPANT"));				 
			}

			if (receiverIsInitiator){
				iu.add(new Var("rolereceiver","INITIATOR"));
			} else {
				iu.add(new Var("rolereceiver","PARTICIPANT"));
			}

		} catch (NotFound e) {
			e.printStackTrace();
		}
		int nextstate=cstate+1;
		
		for (GraphEntity next:nextIUS){  
			
			int modifiedstate=generateInteractionUnitCode(next,graphEntities,interactionRepeat,cstate, "middle",nextstate,initiatorID,generatedStates);
			if (modifiedstate!=nextstate){				
				nextstate=modifiedstate;
			}
		}
		
		if (nextIUS.isEmpty()){
			return cstate;
		} else
		 return nextstate;

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
		Graph[] g = ingenias.generator.browser.BrowserImp.getInstance().getGraphs();
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
