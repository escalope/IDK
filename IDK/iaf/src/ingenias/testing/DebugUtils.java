/*
    Copyright (C) 2007 Jorge Gomez Sanz

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

import java.util.Enumeration;

import ingenias.editor.entities.RuntimeConversation;
import ingenias.editor.entities.RuntimeEvent;
import ingenias.editor.entities.RuntimeFact;
import ingenias.editor.entities.StackEntry;

public class DebugUtils {

	public static void printStackTrace(RuntimeFact rf){
		Enumeration enumeration=rf.getStackElements();
		System.err.println("Stack print for fact "+rf.getId()+":"+rf.getType());
		while (enumeration.hasMoreElements()){
			StackEntry se=(StackEntry)enumeration.nextElement();
			java.util.Date time=new java.util.Date(Long.parseLong(se.getTime()));
			String line= time.getHours()+":"+time.getMinutes()+":"+time.getSeconds()+" "+
			se.getOperation()+" by "+se.getResposible()+ " in "+se.getPlace();
			System.err.println(line);							
		}
		System.err.println("****************************");
	}
	
	private static String getLine(int length){
		StringBuffer sb=new StringBuffer();
		for (int k=0;k<length;k++){
			sb.append("*");
		}
		return sb.toString();
	}
	
	public static String getStackTrace(RuntimeFact rf){
		Enumeration enumeration=rf.getStackElements();
		String trace="";
		int maxLength=0;
		trace=trace+"Stack print for fact "+rf.getId()+":"+rf.getType()+"\n";
		maxLength=Math.max(trace.length(),maxLength);
		while (enumeration.hasMoreElements()){
			StackEntry se=(StackEntry)enumeration.nextElement();
			java.util.Date time=new java.util.Date(Long.parseLong(se.getTime()));
			String line= time.getHours()+":"+time.getMinutes()+":"+time.getSeconds()+" "+
			se.getOperation()+" by "+se.getResposible()+ " in "+se.getPlace();
			maxLength=Math.max(line.length(),maxLength);
			trace=trace+line+"\n";							
		}
		trace=trace+getLine(maxLength)+"\n";
		return trace;
	}
	
	public static String getStackTrace(RuntimeEvent rf){
		Enumeration enumeration=rf.getStackElements();
		String trace="";
		int maxLength=0;
		trace=trace+"Stack print for fact "+rf.getId()+":"+rf.getType()+"\n";
		maxLength=Math.max(trace.length(),maxLength);
		while (enumeration.hasMoreElements()){
			StackEntry se=(StackEntry)enumeration.nextElement();
			java.util.Date time=new java.util.Date(Long.parseLong(se.getTime()));
			String line= time.getHours()+":"+time.getMinutes()+":"+time.getSeconds()+" "+
			se.getOperation()+" by "+se.getResposible()+ " in "+se.getPlace();
			maxLength=Math.max(line.length(),maxLength);
			trace=trace+line+"\n";							
		}
		trace=trace+getLine(maxLength)+"\n";
		return trace;
	}
	
	public static String getStackTrace(RuntimeConversation rf){
		Enumeration enumeration=rf.getStackElements();
		String trace="";
		int maxLength=0;
		trace=trace+"Stack print for fact "+rf.getId()+":"+rf.getType()+"\n";
		maxLength=Math.max(trace.length(),maxLength);
		while (enumeration.hasMoreElements()){
			StackEntry se=(StackEntry)enumeration.nextElement();
			java.util.Date time=new java.util.Date(Long.parseLong(se.getTime()));
			String line= time.getHours()+":"+time.getMinutes()+":"+time.getSeconds()+" "+
			se.getOperation()+" by "+se.getResposible()+ " in "+se.getPlace();
			maxLength=Math.max(line.length(),maxLength);
			trace=trace+line+"\n";							
		}
		trace=trace+getLine(maxLength)+"\n";
		return trace;
	}
}
