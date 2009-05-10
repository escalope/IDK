/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package junit.tests.runner;

import java.util.Vector;

import junit.framework.TestCase;
import junit.runner.Sorter;

public class SorterTest extends TestCase {
	
	static class Swapper implements Sorter.Swapper {
		public void swap(Vector values, int left, int right) {
			Object tmp= values.elementAt(left); 
			values.setElementAt(values.elementAt(right), left); 
			values.setElementAt(tmp, right);
		}
	}
	
	public void testSort() throws Exception {
		Vector v= new Vector();
		v.addElement("c");
		v.addElement("b");
		v.addElement("a");
		Sorter.sortStrings(v, 0, v.size()-1, new Swapper());
		assertEquals(v.elementAt(0), "a");
		assertEquals(v.elementAt(1), "b");
		assertEquals(v.elementAt(2), "c");
	}
}