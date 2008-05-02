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

import java.util.Vector;

import ingenias.editor.entities.Entity;
import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.exception.NotFound;


/**
 * This interface is provided to internal components of the agent to provide a read access
 * to the mental state.
 * 
 * @author jj
 *
 */
public interface MentalStateReader {
	public MentalEntity getMentalEntityByID(String name) throws NotFound;
	public Vector<MentalEntity> getMentalEntityByType(String type);
	public MentalEntity obtainConversationalMentalEntity(RuntimeConversation conv, String id) throws NotFound;
	public Vector<MentalEntity> obtainConversationalMentalEntityByType(RuntimeConversation conv, String type);
	public Vector getAllPendingGoals();
	public Vector<RuntimeConversation> getConversations() ;
	public ingenias.editor.entities.StateGoal getGoal(String name);
	public Entity findEntity(String id) throws NotFound;
	public Vector<MentalEntity> findEntityTypeInstances(String type);
	public void conversationAlreadyUsed(RuntimeConversation conv);
	public void conversationIsInUse(RuntimeConversation conversationContext); 
}
