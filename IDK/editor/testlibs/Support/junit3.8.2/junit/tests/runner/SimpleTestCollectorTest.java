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

import junit.framework.TestCase;
import junit.runner.SimpleTestCollector;

public class SimpleTestCollectorTest extends TestCase {
	
	public void testMissingDirectory() {
		SimpleTestCollector collector= new SimpleTestCollector();
		assertFalse(collector.collectFilesInPath("foobar").elements().hasMoreElements());
	}

}
