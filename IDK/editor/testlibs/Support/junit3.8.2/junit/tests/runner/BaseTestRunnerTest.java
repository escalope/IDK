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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.runner.BaseTestRunner;

public class BaseTestRunnerTest extends TestCase {
	
	public static class MockRunner extends BaseTestRunner {
		protected void runFailed(String message) {
		}

		public void testEnded(String testName) {
		}

		public void testFailed(int status, Test test, Throwable t) {
		}

		public void testStarted(String testName) {
		}
	}
	
	public static class NonStatic {
		public Test suite() {
			return null;
		}
	}

	
	public void testInvokeNonStaticSuite() {
		BaseTestRunner runner= new MockRunner();
		runner.getTest("junit.tests.runner.BaseTestRunnerTest$NonStatic"); // Used to throw NullPointerException
	}
}
