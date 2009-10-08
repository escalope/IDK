/*
    Copyright (C) 2005 Jorge Gomez Sanz

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
package ingenias.jade.mental;

import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeFact;
import ingenias.editor.entities.StackEntry;
import ingenias.jade.JADEAgent;

import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

/**
 * An utility class to operate over FrameFacts to inspect and modify slot values.  
 *  
 * @author jj
 *
 */
public class MentalUtils {
	public static String getCreator(RuntimeFact rf){
		Vector<StackEntry> stack = getVectorStack(rf);
		StackEntry creatorStack = stack.firstElement();
		return creatorStack.getResposible();
									
		}

	/**
	 * @param rf
	 */
	private static Vector<StackEntry> getVectorStack(RuntimeFact rf) {
		Vector<StackEntry> stack=new Vector<StackEntry>(); 
		Enumeration enumeration=rf.getStackElements();		
		while (enumeration.hasMoreElements()){
			StackEntry se=(StackEntry)enumeration.nextElement();
			stack.add(se);
		}
		return stack;
	}
		
	
	public static String getLastSender(RuntimeFact rf){
		Vector<StackEntry> stack = getVectorStack(rf);
		int k=stack.size()-1;
		boolean found=false;
		String sender="";
		while (k>=0 && !found){
			StackEntry current=stack.elementAt(k);
			found=current.Operation.equalsIgnoreCase("transferred");
			if (found)
				sender=current.Resposible;
			k++;
		
		}				
		return sender;
	}

	public static void addStackEntry(MentalEntity consumedFact, String operation, String place, String responsible) {
		if (consumedFact instanceof RuntimeFact){
			RuntimeFact rf=(RuntimeFact)consumedFact;
		StackEntry se=new StackEntry("");
		se.setOperation(operation);
		se.setPlace(place);
		se.setResposible(responsible);
		se.setTime(""+new java.util.Date().getTime());
		rf.addStack(se);
		}
		
	}

	public static void addCommStackEntry(final JADEAgent ag, final String CID,
			final String protocol, Object obj) {
		StackEntry se=new StackEntry("");
		se.setOperation("Transferred");
		se.setPlace(CID+":"+protocol);
		se.setResposible(ag.getLocalName());
		se.setTime(""+new java.util.Date().getTime());
		((RuntimeFact)obj).addStack(se);
	}
	

}
