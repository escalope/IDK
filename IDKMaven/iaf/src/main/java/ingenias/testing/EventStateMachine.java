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

import java.util.Iterator;
import java.util.Vector;

public class EventStateMachine {
	private String name;
	private State initialState;
	private State[] acceptanceStates=new State[0];
	private Vector<State> currentStates=new Vector<State>();
	private Vector<String> traces=new Vector<String>();
	
	public EventStateMachine(String name, State initialState, State[] states){
		this.name=name;
		this.initialState=initialState;
		currentStates.add(initialState);
		this.acceptanceStates=states;
		traces.add(initialState.toString());
	}
	public synchronized boolean isAccepted(){
		boolean containsAny=false;
		Iterator<State> iterator=currentStates.iterator();
		while (!containsAny && iterator.hasNext()){
			int k=0;
			State nextState=iterator.next();
			while (!containsAny && k<acceptanceStates.length){
				containsAny=acceptanceStates[k].equals(nextState);
				k++;
			}
			
		}
		return containsAny;
	}

	public synchronized void evaluate(Event event){
		Vector<State> nextStates=new Vector<State>();
		Vector<State> toRemove=new Vector<State>();
		for (State current:currentStates){
			Vector<State> following=current.evaluate(event);
			if (!following.isEmpty()){
			 toRemove.add(current);
			 nextStates.addAll(following);
			}
		}
		currentStates.removeAll(toRemove);
		currentStates.addAll(nextStates);
		traces.add(currentStates.toString()+" <- "+event.getEventKind().name());
	}
	
	public Vector<State> getCurrentStates() {		
		return currentStates;
	}
	
	public synchronized Vector<String> getTraces() {		
		return traces;
	}
	
	public synchronized String getTracesAsString() {
		int k=0;
		String result="";
		for (String line:traces){
			result=result+k+":"+line+"\n";
		}
		return result;
	}

}
