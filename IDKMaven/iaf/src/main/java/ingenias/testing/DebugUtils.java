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

import ingenias.editor.entities.RuntimeConversation;
import ingenias.editor.entities.RuntimeEvent;
import ingenias.editor.entities.RuntimeFact;
import ingenias.editor.entities.StackEntry;
import ingenias.jade.IAFProperties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

public class DebugUtils {
	
	private static FileOutputStream fos=null;
	
	public static void logEvent(String name,String[] fields){
	 	if (fos==null && IAFProperties.getIntrospection()){
	 		new File("logs").mkdir();
	 		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("MMddyyHHmm");
	 		Date d=new Date();	 		
	 		try {
				fos=new FileOutputStream("logs/logEvent"+df.format(d)+".log");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}	 		
	 	}
	 	
	 	if (fos!=null){
	 		java.util.Date cdate = new java.util.Date();			
			java.text.SimpleDateFormat dflog = new java.text.SimpleDateFormat("HH:mm:ss:SS");			
			String sbl = dflog.format(cdate);
			String stringFields="";
			for (int k=0;k<fields.length-1;k++){
				stringFields=stringFields+fields[k].substring(0,Math.min(100,fields[k].length()))+"!";
			}
			stringFields=stringFields+fields[fields.length-1].substring(0,Math.min(100,fields[fields.length-1].length()));
	 		String output=sbl+";"+name+";"+stringFields+"\n";
	 		try {
				fos.write(output.getBytes());
				fos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		
	 	}
		
	}

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
