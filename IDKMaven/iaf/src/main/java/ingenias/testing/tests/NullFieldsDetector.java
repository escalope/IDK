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
package ingenias.testing.tests;

import static org.junit.Assert.*;

import ingenias.testing.TestUtils;
import ingenias.testing.VerificationFailure;

import java.io.Serializable;
import java.util.Vector;

import javax.swing.JPanel;

import org.junit.Test;

public class NullFieldsDetector {

	private class NotSerializableClass {

	};
	private class SerializableClass implements Serializable {
		private Object field1=null;
	};

	@Test
	public void testIsSerializable() {
		NotSerializableClass nsc=new NotSerializableClass();
		SerializableClass sc=new SerializableClass();
		
		assertTrue("SerializableClass class should appear as serializable", TestUtils.isSerializable(SerializableClass.class));
		assertFalse("NotSerializableClass class should appear as not serializable", TestUtils.isSerializable(NotSerializableClass.class));
		


	}

	@Test
	public void testVerifyFields() {
		SerializableClass sc=new SerializableClass();
		try {
			TestUtils.verifyFields(SerializableClass.class, sc,new Vector<Class>());		
			fail("SerializableClass has a null field that should have triggered a failure");
		} catch(VerificationFailure vf){

		}
		sc.field1="";
		try {
			TestUtils.verifyFields(SerializableClass.class, sc,new Vector<Class>());					
		} catch(VerificationFailure vf){
			vf.printStackTrace();
			fail("SerializableClass has no null fields now");	
		}
	}


}
