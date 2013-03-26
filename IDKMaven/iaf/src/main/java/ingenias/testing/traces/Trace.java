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

import ingenias.jade.components.Task;

import java.util.HashSet;
import java.util.Vector;

public class Trace extends HashSet<Vector<TaskTrace>> {


	public Trace() {}

	public Trace(Trace currentEvent) {
		for (Vector<TaskTrace> existingTraces:currentEvent){
			add((Vector<TaskTrace>) existingTraces.clone());
		}
	}

	public static Trace combine(TaskTrace tt, Trace currentEvent){
		Trace ntrace=new Trace(currentEvent);		
		if (ntrace.isEmpty()){
			Vector<TaskTrace> ttv=new Vector<TaskTrace>();
			ttv.add(tt);
			ntrace.add(ttv);

		} else {
			for (Vector<TaskTrace> existingTraces:ntrace){
				existingTraces.add(tt);
			}
		}
		return ntrace;
	}

	public static Trace emptyTrace(){
		Trace t=new Trace();
		return t;
	}

	public static Trace createTrace(String agentid, Task task,
			HashSet<Trace> traces) {
		TaskTrace tt=new TaskTrace(agentid,task);
		Trace resultingTrace=emptyTrace();
		if (traces.isEmpty())
			resultingTrace=Trace.combine(tt, resultingTrace);
		else
			for (Trace trace:traces){
				resultingTrace.addAll(Trace.combine(tt,trace));
			}
		return resultingTrace;
	}
	public StringBuffer getPrettyPrint(){
		StringBuffer sb=new StringBuffer();
		sb.append("***** begin trace *****"+"\n");
		for (Vector<TaskTrace> traces:this){
			sb.append(getTraceVectorPrettyPrint(traces));
		}
		sb.append("***** end trace *****\n");
		return sb;
	}

	public static String getTraceVectorPrettyPrint(
			Vector<TaskTrace> traces) {
		StringBuffer sb=new StringBuffer();
		int k=0;
		for (TaskTrace tt:traces){
			k++;
			sb.append(k+":"+tt+"\n");
		}
		sb.append("-----------\n");
		return sb.toString();
	}

	public void prettyPrint(){
		System.out.println(getPrettyPrint());
	}
}