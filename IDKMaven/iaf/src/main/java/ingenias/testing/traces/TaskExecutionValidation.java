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
package ingenias.testing.traces;

import ingenias.editor.entities.MentalEntity;
import ingenias.testing.GenericAutomata;

import java.util.AbstractMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import edu.emory.mathcs.backport.java.util.Collections;

public class TaskExecutionValidation {
	private Vector<AbstractMap.SimpleEntry<String, String>> allowedTasks=new Vector<AbstractMap.SimpleEntry<String, String>>();
	private GenericAutomata ga;

	public TaskExecutionValidation(GenericAutomata ga){
		this.ga=ga;
	}

	public void registerTask(String aid, String ttype) {
		allowedTasks.add(new AbstractMap.SimpleEntry<String, String>(aid,ttype));		
	}

	private boolean taskBelongsToAgent(String aid, String ttype) {
		return allowedTasks.contains(new AbstractMap.SimpleEntry<String, String>(aid,ttype));		
	}

	public void reset(){

	}


	public  Vector<String> validatePartialTermination(
			RetrieveExecutionData cwfe, GenericAutomata ga, long millisPerCycle) {
		Hashtable<MentalEntity, Trace> traces = cwfe.getTraces();
		Vector<String> counterExamples=new Vector<String>();
		Vector<Long> finalStatesTimeStamps=new Vector<Long>();
		Hashtable<Long,Vector<TaskTrace>> finalStatesTimeStampsStackTraces=new Hashtable<Long,Vector<TaskTrace>>();
		
		long latestStateTimeStamp=Long.MIN_VALUE;
		for (MentalEntity men: traces.keySet()){
			Trace metraces = traces.get(men);
			for (Vector<TaskTrace> possibleTrace:metraces){
				ga.reset();
				boolean result=true;			
				Iterator<TaskTrace> taskSequence=possibleTrace.iterator();
				long lastTimeStampOfFinalState=Long.MAX_VALUE;
				while (result && taskSequence.hasNext()){
					TaskTrace tt=taskSequence.next();
					latestStateTimeStamp=Math.max(tt.getTimeStamp(),latestStateTimeStamp);
					if (taskBelongsToAgent(tt.getAid(),tt.getTask().getType())){
						result=ga.next(tt.getAid()+"-"+tt.getTask().getType());
						if (!result)
							counterExamples.add("Failed when processing transition "+tt.toString()+
									" because the task execution did not trigger any transition in the validation automata." +
									"The current state of the automata is  "+ga.getCurrentStates()+"\n " +
											"Full trace:\n"+Trace.getTraceVectorPrettyPrint(possibleTrace));
						else {
							if (ga.hasFinalStateBeenReachedInLastTransition()){
								finalStatesTimeStamps.add(tt.getTimeStamp());
								finalStatesTimeStampsStackTraces.put(tt.getTimeStamp(),possibleTrace); // it is not possible that two final states have the same timestamp. Timestamp creation is within a synchronized method.
								long timeBetweenFinalStates = tt.getTimeStamp()-lastTimeStampOfFinalState;
								if (timeBetweenFinalStates>millisPerCycle){
									// failure because the cycle took more than allowed millesPerCycle
									result=false;
									counterExamples.add("Failed at "+tt.toString()+" because the time between final states has exceeded the "+millisPerCycle+
											" millis limit .Current Time has been "+timeBetweenFinalStates+
											" millis:\n"+Trace.getTraceVectorPrettyPrint(possibleTrace));

								} else
									lastTimeStampOfFinalState=tt.getTimeStamp();
							}
						}
					}
				}
			}
		}
		if (finalStatesTimeStamps.isEmpty()){
			// No final state was reached
			counterExamples.add("No final state was reached. The complete traces follow \n"+getPrettyPrint(traces));
		} else {			
			Collections.sort(finalStatesTimeStamps);
			boolean timeExceeded=false;
			for (int k=0;k<finalStatesTimeStamps.size()-1 && !timeExceeded;k++){
				long timeBetweenFinalStates = finalStatesTimeStamps.elementAt(k+1)-finalStatesTimeStamps.elementAt(k);
				timeExceeded = timeBetweenFinalStates>millisPerCycle;
				if (timeExceeded){
					StringBuffer message=new StringBuffer();
					message.append("Time between final states reach has been "+timeBetweenFinalStates+" millis, which is longer than the timeout of "+millisPerCycle+" millis between the following traces");
					message.append("First trace\n");
					message.append(Trace.getTraceVectorPrettyPrint(finalStatesTimeStampsStackTraces.get(finalStatesTimeStamps.elementAt(k+1))));
					message.append("Second trace\n");
					message.append(Trace.getTraceVectorPrettyPrint(finalStatesTimeStampsStackTraces.get(finalStatesTimeStamps.elementAt(k))));
					counterExamples.add(message.toString());
				}
			}
			long ellapsedTimeBetweenLatestTransitionAndLatestFinalStateReach = latestStateTimeStamp-finalStatesTimeStamps.elementAt(finalStatesTimeStamps.size()-1);
			if (ellapsedTimeBetweenLatestTransitionAndLatestFinalStateReach>millisPerCycle){
				StringBuffer message=new StringBuffer();
				message.append("Time between the latest final state and the last operation is greater than "+millisPerCycle+" concretely it is "+ellapsedTimeBetweenLatestTransitionAndLatestFinalStateReach);				
				message.append("Latest state trace\n");
				message.append(Trace.getTraceVectorPrettyPrint(finalStatesTimeStampsStackTraces.get(finalStatesTimeStamps.elementAt(finalStatesTimeStamps.size()-1))));
				counterExamples.add(message.toString());
			}
		}
		return counterExamples;
	}

	public static String getPrettyPrint(Hashtable<MentalEntity, Trace> traces) {
		StringBuffer sb=new StringBuffer();
		for (MentalEntity me:traces.keySet()){			
			sb.append("ME: "+me.getId()+":"+me.getType()+"\n");
			sb.append(traces.get(me).getPrettyPrint()+"\n");
		}
		return sb.toString();

	}

	public static void prettyPrint(Hashtable<MentalEntity, Trace> traces) {
		System.out.println(getPrettyPrint(traces));

	}
}
