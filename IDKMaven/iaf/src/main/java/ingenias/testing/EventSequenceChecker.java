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

import ingenias.jade.EventManager;

import java.util.Collection;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

public class EventSequenceChecker {
	Vector<EventStateMachine> machines=new  Vector<EventStateMachine> ();

	Thread evaluator=null;

	ConcurrentLinkedQueue<Event> queue=new ConcurrentLinkedQueue<Event>(); // this class is threadsafe

	public EventSequenceChecker(){
		EventManager.getInstance().register(new EventSequenceCheckerListener(this));
		Thread evaluator=new Thread(){
			public void run(){
				evaluate();				
			}
		};
		evaluator.start();
	}
	public synchronized void registerEvaluator(EventStateMachine esm){
		machines.add(esm);
	}

	public void evaluate(){
		while (true){
			while (queue.isEmpty()){
				try {
					Thread.currentThread().sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Event event=queue.remove();
			for (EventStateMachine esm:machines){
				esm.evaluate(event);
			}	
		}
	}


	public void notify(Event event){
		
		queue.add(event);
	}

	public synchronized Vector<EventStateMachine> getMachinesInAcceptanceState(){
		Vector<EventStateMachine> result=new Vector<EventStateMachine>();
		for (EventStateMachine esm:machines){
			if (esm.isAccepted())
				result.add(esm);
		}
		return result;
	}


	public static void main(String args[]){
		State initial=new State("first");
		State acceptance=new State("acceptance");
		initial.addTransition(
				new EventHandler(new State[]{acceptance}){
					@Override
					public boolean evaluate(Event event,
							State fromState) {
						return false;
					}
				});

		EventStateMachine esm=new EventStateMachine(
				"test1",initial,new State[]{acceptance});
	}


}
