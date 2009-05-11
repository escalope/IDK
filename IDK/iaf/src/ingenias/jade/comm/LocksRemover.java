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

import java.util.Vector;

/**
 * It gives access to operations for removing and quering locks. It is used inside the DefaultComm inheritors
 * to remove locks and inside the Mental State Processor to guess if a lock can be removed or not.
 * 
 * @author jj
 *
 */
public interface LocksRemover {
	/**
	 * It removes a lock over an entity. Until all locks have been removed, the element will not be free
	 * @param element The element to remove. Usually the id of the entity
	 */
	public    void removeDeletionLock(ingenias.editor.entities.MentalEntity element);
	public    void removeDeletionLock(Vector<ingenias.editor.entities.MentalEntity> element);
	
	/**
	 * It tells whether the element can be removed or not
	 * 
	 * @param element The id of the element to remove. Usually the id of the entity
	 * @return true if it can be deleted and false i.o.c
	 */
	public   boolean canBeDeleted(ingenias.editor.entities.MentalEntity element);
	public void removeAllDeletionLocks();
}

