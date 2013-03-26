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
package ingenias.automata;

import java.util.HashSet;

import ingenias.exception.InvalidTransition;
import ingenias.testing.DualAutomata;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DualAutomataTest {

	
	
	private boolean isInState(DualAutomata da,String [] state){
	 return	da.getCurrentStates().equals(
				new HashSet<String>(
						DualAutomata.toVector(state)));
	}
	
	
	@Test
	/**
	 * Simple test for this config
	 * 
	 * -->init-> first --> second --> end
	 * 
	 */
	public void testSimpleAutomata() throws InvalidTransition{		
		DualAutomata da=new DualAutomata();
		da.addInitialState("init");
		da.addFinalState("end");
		da.addStateTransition("init","event1", "first");
		da.addStateTransition("first","event2", "second");
		da.addStateTransition("second","event3", "end");
		da.next("event2");
		Assert.assertTrue(isInState(da, new String[]{"init"}));
		da.next("event1");
		Assert.assertTrue(isInState(da, new String[]{"first"}));
		da.next("event2");
		Assert.assertTrue(isInState(da, new String[]{"second"}));
		da.next("event3");
		Assert.assertTrue(isInState(da, new String[]{"end"}));
		Assert.assertTrue(da.isFinal());		
	}
	
	@Test
	/**
	 * Simple test for this config
	 * 
	 * -->init-> first --> second --> end
	 * 
	 */
	public void testContinuousProduction() throws InvalidTransition{		
		DualAutomata da=new DualAutomata();
		da.addInitialState("init");
		da.addFinalState("end");
		da.addStateTransition("init","event1", "first");
		da.addStateTransition("first","event2", "second");
		da.addStateTransition("second","event3", "end");
		da.next("event2");
		Assert.assertTrue(isInState(da, new String[]{"init"}));
		da.next("event1");
		Assert.assertTrue(isInState(da, new String[]{"first"}));
		da.next("event2");
		Assert.assertTrue(isInState(da, new String[]{"second"}));
		da.next("event3");
		Assert.assertTrue(isInState(da, new String[]{"end"}));
		Assert.assertTrue(da.isFinal());
		Assert.assertTrue(da.countTotalNumberOfTokens()==1);
		Assert.assertTrue(da.countTotalNumberOfTokensInFinalStates()==1);	
		da.next("event1");
		Assert.assertTrue(isInState(da, new String[]{"first","end"}));		
		da.next("event2");
		Assert.assertTrue(isInState(da, new String[]{"second","end"}));
		da.next("event3");
		Assert.assertTrue(isInState(da, new String[]{"end"}));
		Assert.assertTrue(da.isFinal());
		Assert.assertTrue(da.countTotalNumberOfTokens()==2);
		Assert.assertTrue(da.countTotalNumberOfTokensInFinalStates()==2);	
	}
	
	@Test
	/**
	 * Simple test for this config
	 * 
	 * -->init-> first --> second --> end
	 *                 <--
	 * 
	 */
	public void testLoops() throws InvalidTransition{		
		/*DualAutomata da=new DualAutomata();
		da.addInitialState("init");
		da.addFinalState("end");
		da.addStateTransition("init","event1", "first");
		da.addStateTransition("first","event2", "second");
		da.addStateTransition("second","event1", "first");
		da.addStateTransition("second","event3", "end");
		da.next("event2");
		Assert.assertTrue(isInState(da, new String[]{"init"}));
		da.next("event1");
		Assert.assertTrue(isInState(da, new String[]{"first"}));
		da.next("event2");
		Assert.assertTrue(isInState(da, new String[]{"second"}));
		da.next("event3");
		Assert.assertTrue(isInState(da, new String[]{"end"}));
		Assert.assertTrue(da.isFinal());
		Assert.assertTrue(da.countTotalNumberOfTokens()==1);
		Assert.assertTrue(da.countTotalNumberOfTokensInFinalStates()==1);	
		da.next("event1");
		Assert.assertTrue(isInState(da, new String[]{"first","end"}));		
		da.next("event2");
		Assert.assertTrue(isInState(da, new String[]{"second","end"}));
		da.next("event3");
		Assert.assertTrue(isInState(da, new String[]{"end"}));
		Assert.assertTrue(da.isFinal());
		Assert.assertTrue(da.countTotalNumberOfTokens()==2);
		Assert.assertTrue(da.countTotalNumberOfTokensInFinalStates()==2);	*/
	}

}
