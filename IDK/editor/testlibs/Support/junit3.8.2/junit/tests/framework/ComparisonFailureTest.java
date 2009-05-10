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

import junit.framework.ComparisonFailure;
import junit.framework.TestCase;

public class ComparisonFailureTest extends TestCase {
	
	// Most of the tests are in ComparisonCompactorTest
	public void testConnection() {
		ComparisonFailure failure= new ComparisonFailure("warning", "Mary had a little lamb", "Mary had the little lamb");
		assertEquals("warning expected:<Mary had [a] little lamb> but was:<Mary had [the] little lamb>", failure.getMessage());
	}
	
	// This is like an instanceof test.
	public void testThrowing() {
		try {
			assertEquals("a", "b");
		} catch (ComparisonFailure e) {
			return;
		}
		fail();
	}
}
