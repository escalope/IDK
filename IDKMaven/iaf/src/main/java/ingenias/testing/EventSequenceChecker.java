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
