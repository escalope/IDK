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

public interface CustomLocks {
	/**
	 * it creates specific locks on mental entities so that they cannot
	 * be consumed by other tasks during an interaction and removed. When 
	 * an interaction is being executed, it transmits mental entities from
	 * one agent to another. If other task is executed and it consumes the
	 * mental entity to be transfered, it will not allow the interaction
	 * to progress, since the interaction will wait to an entity already
	 * produced and deleted before its tranfer.
	 * So locks help in preventing this situations. When an information produced 
	 * by a task is known to be transfered to another agent, a lock is 
	 * created. This method is responsible of defining which entities should
	 * be locked on being produced. Once created, the Lock manager will be
	 * respondible of deleting them and monitoring, if required 
	 *  
	 * @param interaction The interaction whose locks have to be created
	 */
	public void addInteractionLocks(String interaction, LocksWriter lw,String role);

}
