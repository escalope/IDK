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

/**
 * Test class used in TestTestCaseClassLoader
 */
import junit.framework.Assert;
import junit.framework.TestCase;

public class ClassLoaderTest extends Assert {
	public ClassLoaderTest() {
	}
	public void verify() {
		verifyApplicationClassLoadedByTestLoader();
		verifySystemClassNotLoadedByTestLoader();
	}
	private boolean isTestCaseClassLoader(ClassLoader cl) {
		return (cl != null && cl.getClass().getName().equals(junit.runner.TestCaseClassLoader.class.getName()));
	}
	private void verifyApplicationClassLoadedByTestLoader() {
		assertTrue(isTestCaseClassLoader(getClass().getClassLoader()));
	} 
	private void verifySystemClassNotLoadedByTestLoader() {
		assertTrue(!isTestCaseClassLoader(Object.class.getClassLoader()));
		assertTrue(!isTestCaseClassLoader(TestCase.class.getClassLoader()));
	}
}