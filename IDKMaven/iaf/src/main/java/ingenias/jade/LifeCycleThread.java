package ingenias.jade;

import java.util.Vector;

import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelListener;

public class LifeCycleThread extends Thread implements GraphModelListener{
	JADEAgent agent;
	Vector<Boolean> wakeUpQueue=new Vector<Boolean>();
	public LifeCycleThread(JADEAgent agent) {
		this.agent=agent;
	}
	public synchronized void waitForNextCycle(){
		if (wakeUpQueue.isEmpty()){
			try {
				wait();
			} catch (InterruptedException e) {
			}
		} else {
			wakeUpQueue.remove(0);
		}
	}
	public synchronized void wakeup(){
		wakeUpQueue.add(true);
		notifyAll();

	}
	@Override
	public void graphChanged(GraphModelEvent arg0) {
		//System.err.println("waked up");
		wakeup();	

	}

	public void run() {
		//System.err.println("trying to wake up "+getLocalName());
		while (true) {			
			//msp.wakeup(); // It tells the MSP to take a decision about task execution
			//while (agent.getMSP().getScheduledTasks().size()>0){
			//System.err.println("Requesting wakeup "+agent.getAID().getLocalName());			
			 agent.getMSP().lifeCycle();
			/*try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}*/
			//}
			// information is uploaded to top level conversations
			/*completedLast = true;
			while (completedLast) {
				try {
					Thread.currentThread().sleep(100);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			}*/
			waitForNextCycle();
			//		System.err.println("Wakened up "+getLocalName());
		}
	}

}
