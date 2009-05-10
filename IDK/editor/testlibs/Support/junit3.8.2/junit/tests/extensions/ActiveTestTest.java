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
package junit.tests.extensions;
 
import junit.extensions.ActiveTestSuite;
import junit.extensions.RepeatedTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
 
/**
 * Testing the ActiveTest support
 */

public class ActiveTestTest extends TestCase {

	public static class SuccessTest extends TestCase {		
		public void runTest() {
		}
	}
		
	public void testActiveTest() {		
		Test test= createActiveTestSuite(); 
		TestResult result= new TestResult();
		test.run(result);
		assertEquals(100, result.runCount());
		assertEquals(0, result.failureCount());
		assertEquals(0, result.errorCount());
	}
	
	public void testActiveRepeatedTest() {		
		Test test= new RepeatedTest(createActiveTestSuite(), 5);
		TestResult result= new TestResult();
		test.run(result);
		assertEquals(500, result.runCount());
		assertEquals(0, result.failureCount());
		assertEquals(0, result.errorCount());
	}
	
	public void testActiveRepeatedTest0() {		
		Test test= new RepeatedTest(createActiveTestSuite(), 0);
		TestResult result= new TestResult();
		test.run(result);
		assertEquals(0, result.runCount());
		assertEquals(0, result.failureCount());
		assertEquals(0, result.errorCount());
	}

	public void testActiveRepeatedTest1() {		
		Test test= new RepeatedTest(createActiveTestSuite(), 1);
		TestResult result= new TestResult();
		test.run(result);
		assertEquals(100, result.runCount());
		assertEquals(0, result.failureCount());
		assertEquals(0, result.errorCount());
	}

	ActiveTestSuite createActiveTestSuite() {
		ActiveTestSuite suite= new ActiveTestSuite();
		for (int i= 0; i < 100; i++) 
			suite.addTest(new SuccessTest());
		return suite;
	}

}