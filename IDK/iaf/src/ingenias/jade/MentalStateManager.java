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
package ingenias.jade;

import ingenias.editor.IDEState;
import ingenias.editor.Model;
import ingenias.editor.ModelJGraph;
import ingenias.editor.cell.RenderComponentManager;
import ingenias.editor.entities.Entity;
import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.editor.entities.RuntimeEvent;
import ingenias.editor.entities.RuntimeFact;
import ingenias.editor.entities.StateGoal;
import ingenias.editor.entities.ViewPreferences.ViewType;
import ingenias.exception.InvalidEntity;
import ingenias.exception.NotFound;
import ingenias.jade.graphics.AgentModelPanelIAF;
import ingenias.testing.DebugUtils;
import ingenias.testing.MSMRepository;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelListener;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphModel;

/**
 * The mental state manager is responsible of adding/removing information from
 * the mental state. It uses elements from the INGENIAS Development Kit to
 * represent explicitely the internal information. Concretely, it uses an
 * AgentModelModelJGraph, a GUI that can show the entities hold in the internal
 * state. Operations applied to the GUI will be processed by this entity.
 * Relevant operations are:
 * <ul>
 * <li> the user edits one entity in the GUI
 * <li> the user adds an entity in the GUI
 * <li> the user removes an entity in the GUI
 * </ul>
 * On the other hand, if a new entity appears as a result of an internal
 * operation, it is inmediately asserted into this GUI.
 * 
 * @author jj
 * 
 */
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

public class MentalStateManager implements MentalStateReader,
MentalStateUpdater {

	private static int idCounter = 0;
	//private IDEState ids = null;
	private AgentModelPanelIAF amm = null;
	private boolean modified = false;
	private Vector<GraphModelListener> changeListeners = new Vector<GraphModelListener>();
	private String agentName = "";
	private Hashtable<String, MentalEntity> state = new Hashtable<String, MentalEntity>();
	private Vector<String> conversationsInUse= new Vector<String>();
	private Vector<Boolean> graphModificationQueue=new Vector<Boolean>(); 
	private Thread graphModificationThread=new Thread(){
		public void run(){
			while (true){
				while (graphModificationQueue.isEmpty()){
					try {
						Thread.currentThread().sleep(100);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
				graphModificationQueue.remove(0);
				for (GraphModelListener gml:changeListeners){
					gml.graphChanged(null);
				}
			}
		}};
		private HashSet<String> conversationAlreadyProcessed= new HashSet<String>();
		private Vector<Runnable> executeLaterActions=new  Vector<Runnable>();

		private class ConversationTracker implements Runnable{
			private Hashtable<RuntimeConversation, Integer> timeout=new Hashtable<RuntimeConversation, Integer>();



			public void run() {			
				//				int seconds=IAFProperties.getGarbageCollectionInterval();
				while(true){
					Vector<RuntimeConversation> convs = getConversations();
					for (RuntimeConversation conv:convs){
						if ((conv.getState().equalsIgnoreCase("finished") 
								|| conv.getState().equalsIgnoreCase("aborted"))){
							if (
									!isConversationBeingUsedByATask(conv) 
									&& !hasAnOngoingParentConversation(conv)
									&&!hasAnOngoingChildConversation(conv)
							){
								if (timeout.containsKey(conv)){
									timeout.put(conv, timeout.get(conv)+1);
									/*if (agentName.startsWith("Mon"))
										System.err.println(agentName+":::::::::::: marking "+conv.getId() +" with "+timeout.get(conv));*/
								} else {
									timeout.put(conv, 1);
									/*	if (agentName.startsWith("Mon"))
										System.err.println(agentName+":::::::::::: marking "+conv.getId());*/
								}
							} else {
								if (timeout.containsKey(conv)){
									timeout.remove(conv);
								}
							}
						}
						/*if (agentName.startsWith("Mon")){
							System.err.println(conv.getConversationID()+" "+ isConversationBeingUsedByATask(conv)+" :"+hasAnOngoingParentConversation(conv)
									+":"+hasAnOngoingChildConversation(conv)+" "+conversationsInUse);
						}*/
					}

					Enumeration<RuntimeConversation> ks = timeout.keys();
					while (ks.hasMoreElements()){
						RuntimeConversation conv=ks.nextElement();
						if (timeout.get(conv)>IAFProperties.getGarbageCollectionInterval() 
								&& !isConversationBeingUsedByATask(conv) 
								&& !hasAnOngoingParentConversation(conv)
								&& !hasAnOngoingChildConversation(conv)
								&& IAFProperties.getGarbageCollectionEnabled()){

							remove(conv.getId());
							timeout.remove(conv);
							/*if (agentName.startsWith("Mon"))
								System.err.println(agentName+":::::::::::: removing "+conv.getId());*/
						}
					}

					if (amm!=null){
						amm.validate();
						amm.repaint();
					}

					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//System.err.println(timeout);

				}
			}
		}

		/*public synchronized void updateFinishedConversations(){
			Vector<RuntimeConversation> convs = getConversations();			
			for (RuntimeConversation conv:convs){
				if ((conv.getState().equalsIgnoreCase("finished") 
						|| conv.getState().equalsIgnoreCase("aborted")) &&
						!conversationAlreadyProcessed.contains(conv.getId()))
				{
					if (!isConversationBeingUsed(conv)){
						Enumeration elements = conv.getCurrentContentElements();
						while (elements.hasMoreElements()){
							Object celement=elements.nextElement();
							if (!(celement instanceof RuntimeConversation)){
								hierarchicalConversationUpdate((MentalEntity) celement,
										conv, conv.getId());
							}
						}
						conversationAlreadyProcessed.add(conv.getId());
					}
				}
			}
		}*/

		/*private void hierarchicalConversationUpdate(MentalEntity newEntity,
				RuntimeConversation rc, String originalConversationid){
			Enumeration<MentalEntity> content = rc.getCurrentContentElements();
			boolean exit=false;
			while (content.hasMoreElements() && !exit){
				MentalEntity next = content.nextElement();
				if (next instanceof RuntimeConversation){						
					hierarchicalConversationUpdate(newEntity,(RuntimeConversation)next,originalConversationid);
					exit=true;
				}
			}
			if (!exit && !rc.getId().equalsIgnoreCase(originalConversationid)){				
				rc.addCurrentContent(newEntity);
			}
		}*/



		/**
		 * It creates a new mental state manager. It requires an object IDEState to
		 * hold information needed to make the AgentModelModelJGraph work.
		 * 
		 * @param ids
		 *            It contains data required by AgentModelModelJGraph
		 * @param amm
		 *            The GUI used to represnet internal information and let users
		 *            inspect and modify
		 * @param agentName
		 */
		public  MentalStateManager(IDEState ids, AgentModelPanelIAF amm,
				String agentName) {
			//	this.ids = ids;
			this.amm = amm;
			this.agentName = agentName;

			amm.getModel().addGraphModelListener(new GraphModelListener() {
				public void graphChanged(GraphModelEvent arg0) {
					modified = true;
					// System.err.println("Graph modified .....");
				}
			});
			((Model) amm.getModel()).setAskMessages(false);
			ids.gm.addModel(new Object[] {}, "mentalstate", amm);
			MSMRepository.getInstance().register(agentName.substring(0,agentName.indexOf('@')), this);
			ConversationTracker ct=new ConversationTracker();
			new Thread(ct).start();
			graphModificationThread.setName("Graph modification "+this.agentName);
			graphModificationThread.start();
		}

		public synchronized void conversationAlreadyUsed(RuntimeConversation conv) {
			conversationsInUse.remove(conv.getId());
		}

		public synchronized void conversationIsInUse(RuntimeConversation conv) {
			conversationsInUse.add(conv.getId());
		}

		public synchronized boolean hasAnOngoingParentConversation(RuntimeConversation conv) {			

			boolean found=false;			
			if (conv.getParentConversation()!=null){
				found= (!
						(conv.getParentConversation().getState().equalsIgnoreCase("finished") ||
								conv.getParentConversation().getState().equalsIgnoreCase("aborted"))
				);
				if (!found){
					found=hasAnOngoingParentConversation(conv.getParentConversation());
				} /*else {
					System.err.println("encontrada "+conv.getParentConversation().getConversationID()+" "+conv.getParentConversation().getState());
				}*/
			}
			return found;
		}

		public synchronized boolean hasAnOngoingChildConversation(RuntimeConversation conv) {			
			Enumeration enumeration=conv.getChildConversationElements();
			boolean found=false;
			while (enumeration.hasMoreElements() && !found){
				RuntimeConversation current=(RuntimeConversation)enumeration.nextElement();
				if (current.getState().equalsIgnoreCase("finished") || 
						current.getState().equalsIgnoreCase("aborted")){
					found=hasAnOngoingChildConversation(current);	
				} else
					found=true;
			}
			return found;
		}		

		public synchronized boolean isConversationBeingUsedByATask(RuntimeConversation conv) {			
			return  conversationsInUse.contains(conv.getId());
		}

		public  MentalStateManager(IDEState ids2, String name) {
			//this.ids = ids;

			this.agentName = name;

			MSMRepository.getInstance().register(agentName.substring(0,agentName.indexOf('@')), this);
		}

		/**
		 * It tells if the mental state was modified since the last call to this
		 * method. Each time this method is called, it resets the "modified"
		 * attribute.
		 * 
		 * @return True if it was modified, and false in other case
		 */
		public synchronized boolean modifiedSinceLastLecture() {
			boolean result = modified;
			modified = false;
			return result;
		}

		/**
		 * It obtains current representations of running conversations. Each time an
		 * interaction is started, it is stored in the mental state a "conversation"
		 * entity. When the interaction is finished, the conversation entity still
		 * remains.
		 * 
		 */
		public synchronized Vector<RuntimeConversation> getConversations() {
			Vector<RuntimeConversation> conversations = new Vector<RuntimeConversation>();
			Collection<MentalEntity> values = state.values();
			for (MentalEntity me:values){
				if (me instanceof RuntimeConversation){
					conversations.add((RuntimeConversation)me);
				}
			}
			return conversations;
		}

		/**
		 * Adds a goal to the mental state. It is a state goal, so it can hold
		 * information about its current status (solved, pending, ...). The
		 * transition from one to another is not prefixed. It is expected that users
		 * define these transitions in their own terms. By default, when added, all
		 * goals are in "pending" state.
		 * 
		 * @param name
		 *            The name associated to the goal
		 * @param g
		 *            The goal entity
		 */
		/*public void addGoal(String name, ingenias.editor.entities.StateGoal g) {
		g.setState("pending");
		try {
			this.insert(g);
			ingenias.jade.graphics.MainInteractionManager
			.log("Added to mental state a goal " + g.getId(),
					this.agentName);
			this.setModified();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		} 
	}*/

		/**
		 * It returns the goal stored with the id "name".
		 * 
		 */
		public synchronized ingenias.editor.entities.StateGoal getGoal(String name) {
			return (StateGoal) state.get(name);
		}

		/**
		 * It returns all existing goals whose state is "pending". Goals has
		 * different states (pending, refining, solved, failed). The transition from
		 * one to another is not prefixed. It is expected that users define these
		 * transitions in their own terms
		 * 
		 * 
		 */
		public synchronized Vector getAllPendingGoals() {
			Vector pending = new Vector();
			Iterator vals = state.values().iterator();
			while (vals.hasNext()) {
				Object current = vals.next();
				if (current instanceof ingenias.editor.entities.StateGoal) {
					ingenias.editor.entities.StateGoal g = (ingenias.editor.entities.StateGoal) current;
					if (g.getState() != null && g.getState().equals("pending")) {
						pending.add(g);
					}
				}
			}
			return pending;
		}

		/**
		 * It tells whether an entity with a concrete id exists or not
		 * 
		 * @param id
		 *            The id of the entity to look for
		 * @return True if it exists and false i.o.c.
		 */
		public synchronized boolean contains(String id) {
			return state.containsKey(id);
			/*boolean found = false;
		Iterator vals = state.values()ects().iterator();
		while (vals.hasNext() && !found) {
			Entity current = (Entity) vals.next();
			found = current.getId().equals(id);
		}
		return found;*/
		}

		/**
		 * It finds an entity with a concrete id. If found, it returns this entity
		 * as result. If nothing was found, it returns a null
		 * 
		 * @param id
		 *            The id of the entity to be looked for
		 * @return The entity or null if not found
		 * @throws ingenias.exception.NotFound
		 */
		public synchronized Entity findEntity(String id) throws ingenias.exception.NotFound {
			if (state.containsKey(id))
				return state.get(id);
			else
				throw new ingenias.exception.NotFound("Entity " + id
						+ " was not found");
		}

		/**
		 * It removes the entity whose id matches the parameter. If the entity is
		 * not found, no errors are raised. This method may trigger calls to listeners. 
		 * If not done properly, this may end in an infinite loop call
		 * 
		 * @param id
		 *            The id of the entity to remove
		 */
		public synchronized void remove(String id) {
			try {

				final Entity ent = this.findEntity(id);
				//System.err.println(this.agentName+":Borrado de "+ent);
				state.remove(id);
				DebugUtils.logEvent("MERemovedFromMentalState", new String[]{this.agentName,id});
				this.setModified();
				//System.err.println("Current MS "+this.agentName+" "+this.state);
				if (amm!=null){
					/*new Thread(){
					public void run(){*/
					removeFromDiagram(ent);	
					/*}
				}.start();*/
				} 


			} catch (NotFound nf) {
				// Not finding the entity to be removed is not an error
			}
		}

		/**
		 * It adds a new mental entity to the mental state. It can be used with any
		 * object extending ingenias.editor.entities.Entity element. The entity will
		 * be added to the internal representation of the mental state and to the
		 * GUI.This method may trigger calls to listeners. 
		 * If not done properly, this may end in an infinite loop call
		 * 
		 * @param ent
		 *            The mental entity to be inserted
		 */
		private void insert(final MentalEntity ent) {
			state.put(ent.getId(), ent);
			this.setModified();
			if (amm!=null){
				/*new Thread(){
				public void run(){*/

				insertIntoDiagram(ent);		

				/*}
			}.start();*/
			} 
		}

		/**
		 * Inserts the entity into the diagram so that users can see it.
		 * 
		 * @param ent
		 */
		private void insertIntoDiagram(final Entity ent) {
			if (amm!=null){
				Runnable insert=new Runnable(){
					public void run(){
						String superclass=ent.getClass().getSuperclass().getName().substring(ent.getClass().getSuperclass().getName().lastIndexOf(".")+1);
						Dimension dim=RenderComponentManager.getSize(ent,superclass,ent.getPrefs().getView());
						java.awt.Point j = ModelJGraph.findEmptyPlacePoint(dim,amm);
						DefaultGraphCell dgc = amm.insertDuplicated(j, ent);
						resizeAllMentalEntities();
					}
				};
				javax.swing.SwingUtilities.invokeLater(insert);




			}
		}

		public void resizeAllMentalEntities() {			
			if (amm!=null){
				Runnable resize=new Runnable(){
					public void run(){
						GraphModel gm=amm.getModel();
						for (int k=0;k<gm.getRootCount();k++){
							DefaultGraphCell dgc1 = (DefaultGraphCell) gm.getRootAt(k);
							Object userobject=dgc1.getUserObject();
							CellView currentview=amm.getGraphLayoutCache().getMapping(dgc1,false);	
							Entity ent1=(Entity)dgc1.getUserObject();
							
							String type="";
							if (ent1 instanceof RuntimeFact){
								type="RuntimeFact";
							} else
								if (ent1 instanceof RuntimeEvent){
									type="RuntimeEvent";
								} else
									type=ent1.getType();

							if (ent1!=null &&
									RenderComponentManager.retrievePanel(type,ent1.getPrefs().getView())!=null){
								Dimension dim1=RenderComponentManager.getSize(ent1,type,ent1.getPrefs().getView());

								if (dim1!=null){
									Map attributes=dgc1.getAttributes();
									Rectangle2D loc=GraphConstants.getBounds(attributes);			  
									loc.setRect(loc.getX(),loc.getY(),(int) Math.min(400, dim1
											.getWidth()), (int) Math.min(300, dim1.getHeight()));						
									GraphConstants.setBounds(attributes,loc);			  
									Map nmap=new Hashtable();
									nmap.put(dgc1,attributes);
									amm.getModel().edit(nmap,null,null,null);
									amm.repaint();	
								}
							}
						}		
					}
				};
				javax.swing.SwingUtilities.invokeLater(resize);


			}
		}

		/**
		 * It removes the entity in the metnal state whose id matches the id of the
		 * entity passed as parameter. The entity will be removed from both the GUI
		 * and the internal representation of the mental state.
		 * 
		 * @param ent
		 *            The entity to remove
		 */
		private void removeFromDiagram(final Entity ent) {
			//System.err.println("removing "+ent);
			if (amm!=null){
				Runnable remove=new Runnable(){
					public void run(){
						Entity result = null;
						DefaultGraphCell cell = null;
						boolean found = false;

						int before=amm.getModel().getRootCount();
						for (int k = 0; k < amm.getModel().getRootCount() && !found; k++) {
							cell = (DefaultGraphCell) amm.getModel().getRootAt(k);
							Entity current = (Entity) cell.getUserObject();
							if (current.getId().equals(ent.getId())) {
								found = true;
								result = current;
							}
						}
						if (found){
							amm.getModel().remove(new Object[] { cell });
							//this.amm.getGraphLayoutCache().remove(new Object[] { cell },true,true);
							amm.validate();
							amm.repaint();
						}

						if (before<=amm.getModel().getRootCount()){
							throw new RuntimeException();
						}

					}
				};
				javax.swing.SwingUtilities.invokeLater(remove);
			}

		}
		
		public synchronized void processNewEntitiesInConversations(){
			for (Runnable action:executeLaterActions){
				action.run();
			}
			executeLaterActions.clear();
		}

		public synchronized void addMentalEntityToConversation(final RuntimeConversation conv,final Vector<MentalEntity> mes)
		throws ingenias.exception.InvalidEntity{
			Runnable action=new Runnable(){
				public void run(){
					Vector<MentalEntity> localmes = (Vector<MentalEntity>) mes.clone();
					for (MentalEntity me:mes){
						conv.addCurrentContent(me);
						DebugUtils.logEvent("MEAddedToConversation", new String[]{agentName,me.getType(),me.getId(),conv.getInteraction().getId(),conv.getConversationID()});
					}

					resizeAllMentalEntities();
		
				}
			};
			executeLaterActions.add(action);
						

		}

		public synchronized void addMentalEntity(Vector<MentalEntity> mes)
		throws ingenias.exception.InvalidEntity{
			Vector<GraphModelListener> tlisteners = changeListeners;
			changeListeners=new Vector<GraphModelListener>();
			mes=new Vector<MentalEntity>(mes); 
			MentalEntity firstEntity=mes.firstElement();
			mes.removeElementAt(0);
			for (MentalEntity me:mes){
				addMentalEntity(me);
				DebugUtils.logEvent("MEAddedToMentalState", new String[]{this.agentName,me.getType(),me.getId()});
			}
			changeListeners=tlisteners;
			addMentalEntity(firstEntity);		
		}

		/**
		 * It adds a new entity to the mental state of the agent. The insertion
		 * checks first if another entity with the same id did exist before. If such
		 * entity existed, nothing is done. Otherwise, the entity is inserted.
		 * 
		 * @param me
		 *            The mental entity to be added
		 */
		public synchronized void addMentalEntity(MentalEntity me)
		throws ingenias.exception.InvalidEntity {
			try {
				try {
					if (me instanceof RuntimeConversation)
						me.getPrefs().setView(ViewType.UML);
					this.findEntity(me.getId());
					// There cannot be two entities with the same id
					throw new InvalidEntity("Entity " + me.getId() + " of type "
							+ me.getType() + " could not be inserted "
							+ "because there was another with the same id");
				} catch (NotFound e) {

					this.insert(me);
					ingenias.jade.graphics.MainInteractionManager.logMSM(
							"Added to mental state entity " + me.getId()
							+ " of type " + me.getType(), this.agentName);
					DebugUtils.logEvent("MEAddedToMentalState", new String[]{this.agentName,me.getType(),me.getId()});
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}

		}

		/**
		 * It returns the mental entity stored in the mental state with id "name".
		 * This method is equivalent to the findEntity method, but adding a cast to
		 * the MentalEntity type.
		 */
		public synchronized MentalEntity getMentalEntityByID(String name) throws NotFound {
			return (MentalEntity) findEntity(name);
		}

		/**
		 * It returns the mental entity stored in the mental state with type "type".
		 * This method is equivalent to the findEntityTypeInstances method, but
		 * adding a cast to the MentalEntity type.
		 */
		public synchronized Vector<MentalEntity>  getMentalEntityByType(String type) {
			return findEntityTypeInstances(type);
		}

		public synchronized Vector<MentalEntity>  getAllMentalEntities() {
			return new Vector<MentalEntity> (state.values());
		}

		/**
		 * It sets the modified attribute of the mental stat to true. It is used to
		 * inform the agent that new entities where either inserted by means of
		 * information transfer among agents, or edited by the user through the GUI.
		 * 
		 */
		public synchronized void setModified() {
			modified = true;
			graphModificationQueue.add(true);


			/*new Thread() {
			public void run() {
				for (int k = 0; k < changeListeners.size(); k++) {
					changeListeners.elementAt(k).graphChanged(null);
				}
			}
		}.start();*/
		}

		/**
		 * It associates a listener to the GUI for mental entities modification and
		 * browsing GUI.
		 * 
		 * @param al
		 *            The listener to be assocaited
		 */
		public synchronized void registerChangeListener(GraphModelListener al) {
			if (amm!=null){
				//	this.amm.getModel().addGraphModelListener(al);			
			}
			this.changeListeners.add(al);
		};

		/**
		 * It provides an entity either stored in a conversation or in the mental
		 * state. First, it looks for a mental entity with the id matching the
		 * parameter. If the conversation is null, nothing else is done. Otherwise,
		 * it looks as well in the entities received and stored in the conversation.
		 * A conversation also stores the entities transmitted during the
		 * interaction.
		 */
		public synchronized MentalEntity obtainConversationalMentalEntity(RuntimeConversation conv,
				String id) throws NotFound {
			MentalEntity inMentalState = null;
			if (conv==null){
				inMentalState = (MentalEntity) findEntity(id);
				return inMentalState;
			}

			// If not found the conversation context has to be consulted.
			Enumeration enumeration = conv.getCurrentContentElements();
			boolean found = false;
			MentalEntity me = null;
			while (enumeration.hasMoreElements() && !found) {
				me = (MentalEntity) enumeration.nextElement();
				found = me.getId().equalsIgnoreCase(id);

			}
			if (!found && conv.getParentConversation()!=null){
				try {
					me=obtainConversationalMentalEntity(conv.getParentConversation(),
							id);
					found=true;
				} catch (NotFound nf){

				}
			}
			if (found)
				return me;
			else {
				return  (MentalEntity) findEntity(id);
			}


		}

		/*public synchronized Vector<MentalEntity> obtainAllConversationalMentalEntity(
			RuntimeConversation conv, String id) {

		Vector<MentalEntity> result = new Vector<MentalEntity>();
		MentalEntity inMentalState = null;
		try {
			result.add((MentalEntity) findEntity(id));
		} catch (NotFound nf) {
		}
		;
		if (conv == null)
			return result;

		Enumeration enumeration = conv.getCurrentContentElements();
		boolean found = false;
		MentalEntity me = null;
		while (enumeration.hasMoreElements()) {
			me = (MentalEntity) enumeration.nextElement();
			found = me.getId().equalsIgnoreCase(id);
			if (found)
				result.add(me);
		}

		return result;
	}*/

		public synchronized Vector<MentalEntity> findEntityTypeInstances(String type) {
			boolean found = false;
			Vector<MentalEntity> result = new Vector<MentalEntity>();
			Vector vals = new Vector(this.state.values());
			for (Object val : vals) {
				MentalEntity current = (MentalEntity) val;
				if (current.getType().equals(type))
					result.add(current);
			}
			return result;
		}

		public synchronized Vector<MentalEntity> obtainConversationalMentalEntityByType(
				RuntimeConversation conv, String type) {
			HashSet<MentalEntity> result = new HashSet<MentalEntity>();
			result.addAll(findEntityTypeInstances(type));
			if (conv == null) {

				return new Vector();
			}



			Enumeration enumeration = conv.getCurrentContentElements();
			boolean found = false;
			MentalEntity me = null;

			while (enumeration.hasMoreElements()) {
				me = (MentalEntity) enumeration.nextElement();
				if (me.getType().equalsIgnoreCase(type))
					result.add(me);				

			};

			if (conv.getParentConversation()!=null){
				result.addAll(obtainConversationalMentalEntityByType(conv.getParentConversation(), type));
			}

			return new Vector(result);
		}

		public synchronized static String generateMentalEntityID() {
			return "ME" + (idCounter++);
		}

		public synchronized void removeFromConversation(RuntimeConversation conversationContext,
				String id) {
			/*try {
				System.err.println(this.agentName+":Borrado de conv "+conversationContext.getConversationID()+" the entity "+obtainConversationalMentalEntity(conversationContext, id));
			} catch (NotFound e) {

				e.printStackTrace();
			}*/
			conversationContext.removeCurrentContentElement(id);
			DebugUtils.logEvent("MERemovedFromConversation", new String[]{this.agentName,id,conversationContext.getInteraction().getId(),conversationContext.getConversationID()});
			removeFromLowerConversation(conversationContext,id);
			removeFromUpperConversation(conversationContext,id);


		}

		private void removeFromUpperConversation(
				RuntimeConversation conversationContext, String id) {
			conversationContext.removeCurrentContentElement(id);
			if (conversationContext.getParentConversation()!=null){
				removeFromUpperConversation(conversationContext.getParentConversation(), id);
			}
		}

		private void removeFromLowerConversation(
				RuntimeConversation conversationContext, String id) {
			conversationContext.removeCurrentContentElement(id);
			Enumeration<RuntimeConversation> enumeration = conversationContext.getChildConversationElements();
			MentalEntity me = null;									
			while (enumeration.hasMoreElements()) {								
				removeFromLowerConversation(
						enumeration.nextElement(), id);				
			}
		}



}
