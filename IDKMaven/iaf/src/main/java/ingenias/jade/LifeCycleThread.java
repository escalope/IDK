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
