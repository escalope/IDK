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

import java.io.Serializable;
/**
 * Interface for operating over the state behavior. This interface is used within the defaultcommcontrol.
 * @author jj
 *
 */
public interface StateUpdater {
	
	/**
	 * It tells if one of the current states matches a specific one. A state behavior can have multiple states
	 * @param state the state id
	 * @return true if the current state contains such state id
	 */
	public boolean isState(String state);
	
	/**
	 * It obtains the conversation id stored in the state behavior
	 */
	public String getConversationID();
	/**
	 * It establishes the content for the next message to be sent by the defaultcomm instance. It has to be
	 * a serializable object and it is supposed to be a vector of mental entities. This method can be executed
	 * several times. Each added object is accumulated in a list of waiting-to-be-sent entities.
	 * @param obj A serializable object to be sent
	 */
	public void addContentForNextMessage(Serializable obj);
	/**
	 * It allows the defaultcomm instance to know which information has to be sent.
	 * @return The content to be sent in a message
	 */
	public Serializable getContentForNextMessage();
	/**
	 * It cleans the waiting-to-be-sent messages queue. 
	 */
	public void clearContentNextMessage();
	
}