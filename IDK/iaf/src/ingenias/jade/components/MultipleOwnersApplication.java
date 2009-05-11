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

package ingenias.jade.components;

import ingenias.jade.JADEAgent;

import java.util.Vector;

/**
 * This class is a wrapper for classes to be accessed from tasks of the agent. 
 * 
 */
public abstract class MultipleOwnersApplication extends Application{
	private Vector<JADEAgent> owners=new  Vector<JADEAgent>();
	
	/**
	 * It tells which agent owns this class.
	 * 
	 * @param owner the jadeagent owner
	 */
	public void registerMultipleOwners(JADEAgent owner){
		this.owners.add(owner);
	}
	
	/**
	 * It tells who owns this class
	 * @return
	 */
	public Vector<JADEAgent> getMultipleOwners(){
		return owners;
	}
	
}
