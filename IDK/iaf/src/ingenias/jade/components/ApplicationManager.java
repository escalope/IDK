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

import java.util.Hashtable;

/**
 * It controls all known applications to a JADEAgent. 
 * 
 * @author Jorge
 *
 */
public class ApplicationManager {
	private Hashtable knownApplications= new Hashtable(); 

	/**
	 * Obtains a concrete application from those available to the agent.
	 * 
	 * @param id The application id
	 * @return	The application object
	 */
	public Application getApplication(String id){	  
		//System.err.println(this+" "+this.knownApplications);
		return (Application)this.knownApplications.get(id);
	}
	/**
	 * Adds an application to the ones already known by the agent.
	 * 
	 * @param id The id associated to the application
	 * @param app The application object
	 */
	public void addApplication(String id, Application app){	  
		this.knownApplications.put(id,app);
	}
	
	/**
	 * A yellow pages service is an special application created by default to all
	 * agents. It is available for those tasks which produce an interaction, so that
	 * they can choose other collaborating agents 
	 *  
	 * @return A yellow pages application
	 */
	public YellowPages getYellowPages(){
		return (YellowPages)this.getApplication("YellowPages");
	}

}
