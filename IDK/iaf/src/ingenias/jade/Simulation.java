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
			e.printStackTrace();
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
