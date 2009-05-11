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

import jade.core.AID;

/**
 * This class provides a summarized information of the agent. Concretely, it
 * provides the agent ID and the role name. It is used for indexing purposes of
 * services known. Here, the role would be used as service ide.
 * 
 * @author jj
 * 
 */
public class AgentExternalDescription implements java.io.Serializable {
	public AID id;

	public String role;

	public AgentExternalDescription(AID id, String role) {
		this.id = id;
		this.role = role;
	}

}