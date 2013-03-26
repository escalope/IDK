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
import ingenias.exception.InvalidTransition;

import java.util.AbstractMap.SimpleEntry;
import java.util.*;


public class DualAutomata {

	HashSet<String> currentStates=new HashSet<String>();
	HashSet<String> finalStates=new HashSet<String>();
	HashSet<String> initialStates=new HashSet<String>(); 
	Hashtable<Vector<String>,Vector<String>> tokensPerTransition=new Hashtable<Vector<String>,Vector<String>>(); // index is the transition, the value are the available tokens in the transition
	Hashtable<AbstractMap.SimpleEntry<String,String>, String> stateTransition=new Hashtable<AbstractMap.SimpleEntry<String,String>, String>();
	Hashtable<String,Vector<String>> tokensInState=new Hashtable<String,Vector<String>>(); // tokens stored in a state because there are no outgoing transitions
	Hashtable<String, String> stateLambdaTransition=new Hashtable<String, String>();
	private int counter=0;

	public void addStateTransition(String origin, String event,String target) throws InvalidTransition{
		if (initialStates.contains(target))
			throw new InvalidTransition(target+" state is an initial state. You cannot add transitions to the initial state");		
		if (event.equals(""))
			stateLambdaTransition.put(origin,target); 
		else
			stateTransition.put(new AbstractMap.SimpleEntry<String,String>(origin, event),target);		
	}

	public static Vector<String> toVector(String[] array){
		Vector<String> result=new Vector<String>();
		for (String string:array){
			result.add(string);
		}
		return result;
	}


	private boolean isTransitionEnabled(String fromstate,String event,String toState){		 
		return tokensPerTransition.containsKey(toVector(new String[]{fromstate,event,toState})) &&
				(	!tokensPerTransition.get(toVector(new String[]{fromstate,event,toState})).isEmpty()	 ||
						isInitialState(fromstate));
	}

	private boolean isInitialState(String fromstate) {		
		return initialStates.contains(fromstate);
	}

	private void transformToken(String state){
		HashSet<Vector<String>> transitionsIncoming=getIncomingTransitions(state);
		HashSet<Vector<String>> transitionsOutgoing=getOutgoingTransitions(state);
		Vector<String> selectedIncomingTransition=selectOneTransitionWithToken(transitionsIncoming);		
		String removedToken=tokensPerTransition.get(selectedIncomingTransition).remove(0); // remove first token
		if (transitionsOutgoing.isEmpty()){
			addTokenToState(removedToken, state);
		} else
			for (Vector<String> transition:transitionsOutgoing){
				addTokenToTransition(removedToken, transition);
			}		
	}

	private Vector<String> selectOneTransitionWithToken(
			HashSet<Vector<String>> transitionsIncoming) {
		Iterator<Vector<String>> it = transitionsIncoming.iterator();
		Vector<String> selected=null;
		while (selected==null && it.hasNext()){
			Vector<String> currentTrans = it.next();
			if (!tokensPerTransition.get(currentTrans).isEmpty()){
				selected=currentTrans;
			}
		}
		return selected;
	}

	private HashSet<Vector<String>> getOutgoingTransitions(String state) {
		HashSet<Vector<String>> result=new HashSet<Vector<String>>();
		for (SimpleEntry<String, String> key:stateTransition.keySet()){
			if (key.getKey().equals(state)){
				result.add(toVector(new String[]{state,key.getValue(),stateTransition.get(key)}));
			}
		}
		return result;
	}

	private HashSet<Vector<String>> getIncomingTransitions(String state) {
		HashSet<Vector<String>> result=new HashSet<Vector<String>>();
		for (SimpleEntry<String, String> key:stateTransition.keySet()){
			if (stateTransition.get(key).equals(state)){
				result.add(toVector(new String[]{key.getKey(),key.getValue(),state}));
			}
		}
		return result;
	}

	private void addTokenToState(String newToken,
			String state) {
		if (!tokensInState.containsKey(state))				
			tokensInState.put(state,new Vector<String>());
		tokensInState.get(state).add(newToken);
	}

	private void addTokenToTransition(String newToken,
			Vector<String> transition) {
		if (!tokensPerTransition.containsKey(transition))				
			tokensPerTransition.put(transition,new Vector<String>());
		tokensPerTransition.get(transition).add(newToken);
	}




	public void addInitialState(String state) throws InvalidTransition{
		currentStates.add(state);
		initialStates.add(state);		
		
		for ( SimpleEntry<String, String> transition:stateTransition.keySet()){
			String existingState=stateTransition.get(transition);
			if (existingState.equals(state)){
				throw new InvalidTransition("Adding "+state+" as initial state is not " +
						"possible because there is already a transition "+transition+" leading to this state and initial states cannot be the target of a transition ");
			}
		}
		
	}

	public void addFinalState(String state){
		finalStates.add(state);
	}


	public void next(String event){
		createTokenIfInitialStateIsTriggered(event);

		HashSet<String> nstates=new HashSet<String>();
		HashSet<String> processed=new HashSet<String>();
		for (String cstate:currentStates){
			SimpleEntry<String, String> key =null;			
			key = new AbstractMap.SimpleEntry<String,String>(cstate,event);
			if (stateTransition.containsKey(key) && 
					isTransitionEnabled(cstate,event,stateTransition.get(key))){
				nstates.add(stateTransition.get(key));
				processed.add(cstate);

			}	
		}
		currentStates.removeAll(processed);
		for (String state:nstates){
			transformToken(state); // moves tokens to next states
		}
		currentStates.addAll(nstates);	
	}
	private void createTokenIfInitialStateIsTriggered(String event) {
		for (String initState:initialStates){
			SimpleEntry<String, String> key = new AbstractMap.SimpleEntry<String,String>(initState,event);
			if (stateTransition.containsKey(key)){
				String nextState=stateTransition.get(key);
				currentStates.add(initState);
				Vector<String> transition=toVector(new String[]{initState,event,nextState});		
				addTokenToTransition(createToken(),transition);
			}
		}
	}

	private String createToken() {
		counter++;
		return ""+counter;
	}

	public HashSet<String> getCurrentStates(){
		return new HashSet<String>(currentStates);
	}

	public boolean isFinal(){
		return countTotalNumberOfTokens()==countTotalNumberOfTokensInFinalStates();
	}

	public int countTotalNumberOfTokens() {
		int counter=0;
		for (Vector<String> tokens:tokensPerTransition.values()){
			counter=counter+tokens.size();
		}
		for (Vector<String> tokens:tokensInState.values()){
			counter=counter+tokens.size();
		}
		return counter;
	}

	public int countTokensInState(String state) {
		return tokensInState.get(state).size();
	}

	public int countTotalNumberOfTokensInFinalStates() {
		int counter=0;
		for (String fstate:finalStates){
			if (tokensInState.containsKey(fstate))
				counter=counter+tokensInState.get(fstate).size();
		}

		return counter;
	}
}
