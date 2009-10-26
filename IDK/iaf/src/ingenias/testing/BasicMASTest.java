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
