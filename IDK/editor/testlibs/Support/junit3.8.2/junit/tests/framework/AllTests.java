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
package junit.tests.framework;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * TestSuite that runs all the sample tests
 *
 */
public class AllTests {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
	
	public static Test suite() {
		TestSuite suite= new TestSuite("Framework Tests");
		suite.addTestSuite(TestCaseTest.class);
		suite.addTest(SuiteTest.suite()); // Tests suite building, so can't use automatic test extraction 
		suite.addTestSuite(TestListenerTest.class);
		suite.addTestSuite(AssertTest.class);
		suite.addTestSuite(TestImplementorTest.class);
		suite.addTestSuite(NoArgTestCaseTest.class);
		suite.addTestSuite(ComparisonCompactorTest.class);
		suite.addTestSuite(ComparisonFailureTest.class);
		suite.addTestSuite(DoublePrecisionAssertTest.class);
		return suite;
	}
	
}