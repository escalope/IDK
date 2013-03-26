/*
    Copyright (C) 2013 Jorge Gomez Sanz

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

package ingenias.testing;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

public class BasicMASTest {
	private Vector<String> startedAgents=new Vector<String>();
	private Hashtable<String,HashSet<String>> agentRoles=new Hashtable<String,HashSet<String>>(); 

	public void addStartedAgent(String agentid){
		this.startedAgents.add(agentid);
	}
	
	public void addAgentRole(String agentid, String role){
		HashSet<String> agents=new HashSet<String>();
		if (agentRoles.containsKey(role)){
			agents=agentRoles.get(role);
		}
		agents.add(agentid);
	}
	
	public HashSet<String> getAgentsForRole(String roleid){
		return agentRoles.get(roleid);
	}
	
	public HashSet<String> getRolesForAgent(String agentid){
		HashSet<String> roles=new HashSet<String>();
		for (String role:agentRoles.keySet()){
			HashSet<String> agents=agentRoles.get(role);
			for (String agent:agents){
				if (agent.equalsIgnoreCase(agentid)){
					roles.add(role);
				}
			}
		}
		return roles;
	}
	
	public Vector<String> getAgents(){
		return startedAgents;
	}
}
