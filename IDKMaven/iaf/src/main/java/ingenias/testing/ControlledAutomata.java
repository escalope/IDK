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
import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.Task;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

public class ControlledAutomata {

	HashSet<String> currentStates=new HashSet<String>();
	HashSet<String> finalStates=new HashSet<String>(); 
	Hashtable<AbstractMap.SimpleEntry<String,String>, String> stateTransition=new Hashtable<AbstractMap.SimpleEntry<String,String>, String>();
	Hashtable<String, String> stateLambdaTransition=new Hashtable<String, String>();
	Hashtable<String,Vector<String>> tokensInState=new Hashtable<String,Vector<String>>(); // tokens stored in a state because there are no outgoing transitions

	public void addStateTransition(String origin, String event,String target){
		if (event.equals(""))
			stateLambdaTransition.put(origin,target); 
		else
			stateTransition.put(new AbstractMap.SimpleEntry<String,String>(origin, event),target);
	}
	
	private void addTokenToState(String newToken,
			String state) {
		if (!tokensInState.containsKey(state))				
			tokensInState.put(state,new Vector<String>());
		tokensInState.get(state).add(newToken);
	}
	
	private void removeTokenFromState(String newToken,
			String state) {
		if (tokensInState.containsKey(state))				
			tokensInState.get(state).remove(newToken);
	}


	public void addInitialState(String state){
		currentStates.add(state);
	}

	public void addFinalState(String state){
		finalStates.add(state);
	}
	
	public void addNew(MentalEntity me, Task t, String agentid){
		addTokenToState(me.getId(),agentid+"-"+t.getId());
	}
	
	public void remove(MentalEntity me, Task t, String agentid){
		removeTokenFromState(me.getId(),agentid+"-"+t.getId());
	}
	

	public void next(String event, Vector<MentalEntity> inputs){
	/*	HashSet<String> candidateSourceStates=locateWhichCurrentStateHaveProduced(inputs);		
		HashSet<String> nstates=new HashSet<String>();
		HashSet<String> processed=new HashSet<String>();
		for (String cstate:candidateSourceStates){
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
		currentStates.addAll(nstates);			*/
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
