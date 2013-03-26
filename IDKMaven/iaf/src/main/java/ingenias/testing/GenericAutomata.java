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

import ingenias.editor.entities.Entity;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Hashtable;

public class GenericAutomata {

	HashSet<String> currentStates=new HashSet<String>();
	HashSet<String> initialStates=new HashSet<String>();
	HashSet<String> finalStates=new HashSet<String>(); 
	Hashtable<AbstractMap.SimpleEntry<String,String>, String> stateTransition=new Hashtable<AbstractMap.SimpleEntry<String,String>, String>();
	Hashtable<String, String> stateLambdaTransition=new Hashtable<String, String>();
	private boolean finalReached;

	public void addStateTransition(String origin, String event,String target){
		if (event.equals(""))
			stateLambdaTransition.put(origin,target); 
		else
			stateTransition.put(new AbstractMap.SimpleEntry<String,String>(origin, event),target);
	}

	public void addInitialState(String state){
		currentStates.add(state);
		initialStates.add(state);
	}

	public void addFinalState(String state){
		finalStates.add(state);
	}
	
	public void reset(){
		currentStates.clear();
		currentStates.addAll(initialStates);
	}


	public boolean next(String event){
		HashSet<String> nstates=new HashSet<String>();
		HashSet<String> processed=new HashSet<String>();
		for (String cstate:currentStates){
			SimpleEntry<String, String> key =null;			
			key = new AbstractMap.SimpleEntry<String,String>(cstate,event);
			if (stateTransition.containsKey(key)){
				nstates.add(stateTransition.get(key));
				processed.add(cstate);
			}
			if (stateLambdaTransition.containsKey(cstate)){
				processed.add(cstate);
				nstates.add(stateLambdaTransition.get(cstate));
			}			
		}	

		currentStates.removeAll(processed);
		currentStates.addAll(nstates);
		HashSet<String> guessingIfNextStateisFinal = new HashSet<String>(nstates);
		guessingIfNextStateisFinal.removeAll(finalStates);
		finalReached=guessingIfNextStateisFinal.size()!=nstates.size();
		return !nstates.isEmpty(); // this implies some transition was recognised
	}
	
	public boolean hasFinalStateBeenReachedInLastTransition(){
		return finalReached;
	}
	
	public HashSet<String> getCurrentStates(){
		return new HashSet<String>(currentStates);
	}

	public boolean isFinal(){
		HashSet<String> newSet = new HashSet<String>(currentStates);
		newSet.removeAll(finalStates);
		return newSet.size()!=currentStates.size();
	}


}
