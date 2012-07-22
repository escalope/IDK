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
import ingenias.jade.MentalStateReader;
import jade.lang.acl.ACLMessage;

import java.util.Enumeration;
import java.util.Vector;
/**
 * It handles the reception and deliver of messages. According to the semantics of the protocols, a 
 * message should not be sent until certain conditions are met. Possible conditions refer to the
 * existence of certain pieces of information in the mental state and that they satisfy certain boolean
 * expressions defined in the specification of the system. Reception of messages behaves the same way.
 * Messages not satisfying the defined guards in a concrete moment are ignored and lost.
 * 
 * In case the message is received/sent successfully, then a transition of state occurs. Different
 * transitions are defined into inheritors of this class. 
 * 
 * @author jj
 *
 */
public abstract class DefaultCommControl {
	MentalStateReader msr=null;
	LocksRemover lr;
	Vector<String> locksAccount=new Vector<String>(); 

	public DefaultCommControl(MentalStateReader msr, LocksRemover lr){
		this.msr=msr;
		this.lr=lr;
		locksAccount=this.getDefaultLocks();
	}
	
	/**
	 * It obtains a slot value of a FrameFact entity of the mental state. If there is no such
	 * slot in the framefact, then a null is returned.
	 * 
	 * @param ff	The framefact entity
	 * @param slotname	A slot name
	 * @return	A string with the value of the slot
	 */
	public static String getSlotValue(ingenias.editor.entities.FrameFact ff, String slotname){
		Enumeration enumeration=ff.getSlotsElements();
		String result=null;
		boolean found=false;
		while (enumeration.hasMoreElements() && !found){
			ingenias.editor.entities.Slot slot= (ingenias.editor.entities.Slot)enumeration.nextElement();
			found=slot.Name.equals(slotname);
			if (found)
				result=slot.getValue();
		}
		return result;
	}
	
	/**
	 * It is invoked when a new message has been received. The method has to decide if the state behavior
	 * should change its current state to another valid one. Next states are passed as parameter.
	 * The message is considered processed if it led to a state transition. Current valid states from which
	 * the transition is allowed are hardcoded into the inheritors of this class.  
	 * 
	 * @param mes The message received
	 * @param options The possible states to which transit from here
	 * @param sb A state behavior
	 * @return true if the message led to a state transition. False i.o.c.
	 */
	public abstract boolean notifyNewMessage(Vector<ACLMessage> mes,String[] options,
			StateBehavior sb);
	
	/**
	 * It tells that a new message has been sent. As in the reception of messages, a message is sent only
	 * if conditions established in the specification hold. These conditions are codified in the inheritors
	 * of this class. When the message is sent, it leds to a state transition and makes the function to
	 * return a true. In other case, it returns a false. When an information is sent, any existing lock
	 * in the Locks Manager referred to this information has to be removed. This is achieved with a
	 * Locks Remover instance. To obtain information about the mental state, a mental state reader is used.
	 * 
	 * @param sequence Current state
	 * @param options Possible states after this
	 * @param sb The state behavior containing the interaction
	 * @return True if the message was sent and false i.o.c.
	 */
	public abstract boolean notifyMessageSent(String sequence,String[] options, StateBehavior sb);
	
	/**
	 * It obtains a Mental State Reader reference stored in this object
	 * @return a mental state reader
	 */
	public MentalStateReader getMSR() {
		return msr;
	}
	
	/**
	 * It obtains the Locks remover reference stored in this object
	 * @return a locks remover
	 */
	public LocksRemover getLR() {
		return lr;
	}
	
	
	/**
	 * It removes pending locks associated to this interaction.
	 * It is invoked when the interaction has finished. In this case, all
	 * non-removed locks must be removed. This is necessary in interactions
	 * involving several possible branches. At the end, branches not having
	 * been executed keep their locks, what implies that other tasks may not
	 * be triggered because of the pending locks. 
	 *
	 */
	public void removeDefaultLocks(){
		getLR().removeAllDeletionLocks();
	}
	
	public void lockProcessed(String lock){
		locksAccount.remove(lock);
	}
	
	public abstract Vector<String> getDefaultLocks();
		
	
	
}