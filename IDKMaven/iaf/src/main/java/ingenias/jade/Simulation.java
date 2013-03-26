/*
Copyright (C) 2012 Jorge Gomez Sanz

This file is part of INGENIAS Agent Framework, an agent infrastructure linked
to the INGENIAS Development Kit, and availabe at http://ingenias.sourceforge.net. 

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

public class Simulation {
	
	private static Simulation instance=new Simulation();
	private Vector<String> waitingAgents=new Vector<String>();
	
	private boolean simulationmode=false;
	private Simulation(){};
	
	public static Simulation getInstance(){
		return instance;
	}
	
	public boolean isSimulationModeEnabled(){
		return simulationmode;
	}
	
	public void setSimulationMode(boolean modeon){
		simulationmode=modeon;
	}
	
	/**
	 * It waits for the next simulation cycle. The id of the agent is 
	 * registered to account the 
	 * @param agentid
	 */
	public synchronized void waitNextCycle(String agentid) {
		waitingAgents.add(agentid);
		try {
			wait();
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
	}
	
	/**
	 * It allows another simulation cycle and informs which agents did make
	 * use of it
	 * @return a vector of strings with the id of each agent which was waiting for
	 * a simulation cycle
	 */
	public synchronized Vector<String> proceedNextCycle(){
		Vector<String> agentids=new Vector<String>(waitingAgents);
		waitingAgents.clear();
		notifyAll();
		return agentids;
	}

}
