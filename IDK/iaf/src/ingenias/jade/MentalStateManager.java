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

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;

import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelListener;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphModel;

import ingenias.editor.AgentModelPanel;
import ingenias.editor.IDEState;
import ingenias.editor.Model;
import ingenias.editor.ModelJGraph;
import ingenias.editor.cell.RenderComponentManager;
import ingenias.editor.entities.Entity;
import ingenias.editor.entities.FrameFact;
import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.StateGoal;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.editor.entities.ViewPreferences.ViewType;
import ingenias.exception.InvalidEntity;
import ingenias.exception.NotFound;
import ingenias.jade.graphics.AgentModelPanelIAF;
import ingenias.testing.MSMRepository;

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
	private Vector<RuntimeConversation> conversationsInUse= new Vector<RuntimeConversation>();
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

		private class ConversationTracker implements Runnable{
			private Hashtable<RuntimeConversation, Integer> timeout=new Hashtable<RuntimeConversation, Integer>(); 
			public void run() {			
//				int seconds=IAFProperties.getGarbageCollectionInterval();
				while(true){
					Vector<RuntimeConversation> convs = getConversations();
					for (RuntimeConversation conv:convs){
						if (conv.getState().equalsIgnoreCase("finished") || conv.getState().equalsIgnoreCase("aborted")){
							if (timeout.containsKey(conv)){
								timeout.put(conv, timeout.get(conv)+1);
							} else {
								timeout.put(conv, 1);
							}
						}
					}
					Enumeration<RuntimeConversation> ks = timeout.keys();
					while (ks.hasMoreElements()){
						RuntimeConversation conv=ks.nextElement();
						if (timeout.get(conv)>IAFProperties.getGarbageCollectionInterval() 
								&& !isConversationBeingUsed(conv) 
								&& IAFProperties.getGarbageCollectionEnabled()){						
							remove(conv.getId());
							timeout.remove(conv);
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

				}
			}
		}

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
			conversationsInUse.remove(conv);
		}

		public synchronized void conversationIsInUse(RuntimeConversation conv) {
			conversationsInUse.add(conv);
		}

		public synchronized boolean isConversationBeingUsed(RuntimeConversation conv) {
			// TODO Auto-generated method stub		
			return conversationsInUse.contains(conv);
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
		private void insertIntoDiagram(Entity ent) {
			if (amm!=null){
				String superclass=ent.getClass().getSuperclass().getName().substring(ent.getClass().getSuperclass().getName().lastIndexOf(".")+1);
				Dimension dim=RenderComponentManager.getSize(ent,superclass,ent.getPrefs().getView());
				java.awt.Point j = ModelJGraph.findEmptyPlacePoint(dim,this.amm);
				DefaultGraphCell dgc = this.amm.insertDuplicated(j, ent);
				/*Map attrs = dgc.getAttributes();

			String superclass=ent.getClass().getSuperclass().getName().substring(ent.getClass().getSuperclass().getName().lastIndexOf(".")+1);
			Dimension dim=null;

			if (superclass.equalsIgnoreCase("runtimeevent") || superclass.equalsIgnoreCase("runtimefact"))
				dim=RenderComponentManager.getSize(ent,superclass,ent.getPrefs().getView());
			else
				dim=RenderComponentManager.getSize(ent,ent.getType(),ent.getPrefs().getView());

			//System.err.println("Insertado ...."+superclass);

			Rectangle2D bounds = GraphConstants.getBounds(attrs);
			if (bounds == null)
				bounds = new Rectangle(j.x, j.y, 200, 100);
			else
				bounds = new Rectangle(j.x, j.y, (int) Math.min(400, dim
						.getWidth()), (int) Math.min(300, dim.getHeight()));



			GraphConstants.setBounds(attrs, bounds);
			Hashtable changes = new Hashtable();
			changes.put(dgc, attrs);
			// dgc.setAttributes(attrs);
			this.amm.getModel().edit(changes, null, null, null);*/

				resizeAllMentalEntities();
			}
		}

		public void resizeAllMentalEntities() {
			if (amm!=null){
				GraphModel gm=this.amm.getModel();
				for (int k=0;k<gm.getRootCount();k++){
					DefaultGraphCell dgc1 = (DefaultGraphCell) gm.getRootAt(k);
					Object userobject=dgc1.getUserObject();
					CellView currentview=amm.getGraphLayoutCache().getMapping(dgc1,false);	
					Entity ent1=(Entity)dgc1.getUserObject();
					if (ent1!=null &&
							RenderComponentManager.retrievePanel(ent1.getType(),ent1.getPrefs().getView())!=null){
						/*					Dimension dim1=RenderComponentManager.getSize(ent1,ent1.getType(),ent1.getPrefs().getView());

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
					}*/
					}
				}

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
		private void removeFromDiagram(Entity ent) {
			//System.err.println("removing "+ent);
			Entity result = null;
			DefaultGraphCell cell = null;
			boolean found = false;
			if (amm!=null){
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
					this.amm.getModel().remove(new Object[] { cell });
					//this.amm.getGraphLayoutCache().remove(new Object[] { cell },true,true);
					this.amm.validate();
					this.amm.repaint();
				}else
					System.err.println("Not found");

				if (before<=amm.getModel().getRootCount()){
					throw new RuntimeException();
				}
			}	

		}

		public synchronized void addMentalEntityToConversation(RuntimeConversation conv,Vector<MentalEntity> mes)
		throws ingenias.exception.InvalidEntity{
			for (MentalEntity me:mes){
				conv.addCurrentContent(me);
			}

			setModified();

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
			if (found)
				return me;
			else
				return  (MentalEntity) findEntity(id);


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
			Vector<MentalEntity> result = new Vector<MentalEntity>();
			result.addAll(findEntityTypeInstances(type));
			if (conv == null) {

				return result;
			}

			Enumeration enumeration = conv.getCurrentContentElements();
			boolean found = false;
			MentalEntity me = null;
			while (enumeration.hasMoreElements()) {
				me = (MentalEntity) enumeration.nextElement();
				if (me.getType().equalsIgnoreCase(type))
					result.add(me);
			}

			return result;
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


		}

}
