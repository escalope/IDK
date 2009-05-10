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

import java.lang.reflect.Method;
import java.net.URL;

import junit.framework.TestCase;
import junit.runner.TestCaseClassLoader;

/**
 * A TestCase for testing the TestCaseClassLoader
 *
 */
public class TestCaseClassLoaderTest extends TestCase {

	public void testClassLoading() throws Exception {
		TestCaseClassLoader loader= new TestCaseClassLoader();
		Class loadedClass= loader.loadClass("junit.tests.runner.ClassLoaderTest", true);
		Object o= loadedClass.newInstance();
		//
		// Invoke the assertClassLoaders method via reflection.
		// We use reflection since the class is loaded by
		// another class loader and we can't do a successful downcast to
		// ClassLoaderTestCase.
		//
		Method method= loadedClass.getDeclaredMethod("verify", new Class[0]);
		method.invoke(o, (Object[])new Class[0]);
	}

	public void testJarClassLoading() throws Exception {
		URL url= getClass().getResource("test.jar");
		if (url == null) 
			return; // This test only makes sense when run from Ant, so silently ignore
		String path= url.getFile();
		TestCaseClassLoader loader= new TestCaseClassLoader(path);
		Class loadedClass= loader.loadClass("junit.tests.runner.LoadedFromJar", true);
		Object o= loadedClass.newInstance();
		//
		// Invoke the assertClassLoaders method via reflection.
		// We use reflection since the class is loaded by
		// another class loader and we can't do a successfull downcast to
		// ClassLoaderTestCase.
		//
		Method method= loadedClass.getDeclaredMethod("verify", new Class[0]);
		method.invoke(o, (Object[])new Class[0]);
	}
}