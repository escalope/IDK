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

import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.jade.graphics.MainInteractionManager;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;
/**
 * It handles locks on information entities stored in the Mental State Manager. When an entity is locked,
 * it cannot be removed from the mental state, what prevents tasks from consuming them. When an entity 
 * is locked and a task requires to consume it, the task is aborted. Locks are created because interactions
 * require certain entities to exist in the mental state of the agent. When a task produces such information,
 * any deletion is forbidden until the interaction has made use of it. Making use means sending the information
 * to another participant or having received it. Locks are removed as soon as the information is used.
 * Whem multiple interactions require certain entity, several locks are created. As interactions use the information,
 * locks are removed. When no more interactions require the information, the information becomes lock free.
 * 
 * To define all the locks an interaction may need, this uses objects implementing the CustomLocks interface.
 * 
 * @author jj
 *
 */
public class GeneralLocksManager implements LocksRemover, LocksWriter {

	private Vector<ingenias.editor.entities.MentalEntity> cannotBeDeleted=new Vector<ingenias.editor.entities.MentalEntity>();
	private Vector<String> cannotBeDeletedExpectedTypes=new Vector<String>();	
	private String aname="";
	private CustomLocks cl=null;
	private Vector<LocksListener> listeners=new Vector<LocksListener>(); 
	
	/**
	 * It creates a locks manager
	 * @param agentName The local name of the agent
	 * @param cl The customLocks informing of locks to be added per interaction an agent knows
	 */
	public GeneralLocksManager(String agentName, CustomLocks cl){
		this.aname=agentName;
		this.cl=cl;
	}
	
	
	/**
	 * It tells if there is a lock over the element.
	 * 
	 */
	public  synchronized boolean canBeDeleted(ingenias.editor.entities.MentalEntity element){		
		return !this.cannotBeDeleted.contains(element);
	}
	
	public  synchronized boolean canBeDeleted(Vector<ingenias.editor.entities.MentalEntity> elements){
		//MainInteractionManager.log(this.cannotBeDeleted.toString(),
		//		aname+"- checking deletion");
		return !this.cannotBeDeleted.containsAll(elements);
	}
	
	/**
	 * It removes one lock associated to the element. There can be as many as needed.
	 * An entity has as many locks as times the addDeletionLock has been invoked
	 */
	public  synchronized  void removeDeletionLock(ingenias.editor.entities.MentalEntity element){		
		this.cannotBeDeleted.remove(element);
		notifyChangeLocks();
		MainInteractionManager.logMSM("Removed "+element+". Current lokcs:"+this.cannotBeDeleted.toString(),aname);
	}
	
	/**
	 * It adds a deletion lock over an entity. There can be several locks over the same
	 * entity
	 */
	
	public synchronized void addDeletionLockExpectedType(String type){
		this.cannotBeDeletedExpectedTypes.add(type);		
		MainInteractionManager.logMSM("Added "+type+".  Current locks:"+this.cannotBeDeleted.toString(),
				aname);
		
	}
	
	public synchronized void addDeletionLock(ingenias.editor.entities.MentalEntity entity){
		if (this.cannotBeDeletedExpectedTypes.contains(entity.getType())){
			this.cannotBeDeleted.add(entity);
			notifyChangeLocks();
			MainInteractionManager.logMSM("Added "+entity+".  Current locks:"+this.cannotBeDeleted.toString(),
					aname);
		}		
	}

	
	/**
	 * It returns the list of locks currently known
	 * @return
	 */
	public synchronized Vector<ingenias.editor.entities.Entity> getLocks(){
		return new Vector<ingenias.editor.entities.Entity>(this.cannotBeDeleted);
	}

	/**
	 * It adds all the locks an interaction requires. The number of locks is determined
	 * by implementors of the interface CustomLocks with which this class is initialized.
	 * 
	 * @param interactionType The interaction whose locks has to be added
	 */
	public void addInteractionLocks(String interactionType) {
		new Exception("Method deprecated").printStackTrace();
	}
	
	public void register(LocksListener ll){
		this.listeners.add(ll);
	}
	
	private void notifyChangeLocks(){
	 for (LocksListener ll:listeners){
		 ll.locksChanged();
	 }
	}


	public void removeDeletionLock(Vector<MentalEntity> elements) {
		for (MentalEntity me:elements){
			removeDeletionLock(me);
		}
	}
	
	public void removeAllDeletionLocks() {
		cannotBeDeleted.removeAllElements();
		
	}

}
