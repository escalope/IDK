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
