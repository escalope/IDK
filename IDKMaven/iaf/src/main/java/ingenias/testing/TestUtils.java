/*
    Copyright (C) 2007 Jorge Gomez Sanz

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
package ingenias.testing;

import static org.junit.Assert.*;
import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.jade.AgentStates;
import ingenias.jade.MentalStateManager;
import ingenias.jade.MentalStateProcessor;

import jade.util.leap.Serializable;

import java.lang.reflect.Field;
import java.security.Permission;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

public class TestUtils {

	private static Hashtable<String,Vector<MentalEntity>> snapshots=new Hashtable<String,Vector<MentalEntity>>();
	
	public static Thread assertUntil(final AssertExpression expression, final long timeout){
		
		Thread thread=new Thread(){
			public void run(){
				long localTimeout=timeout;
				while (!expression.canEvaluate() && localTimeout>0){
					double sleeptime = 1000*Math.random();
					localTimeout=(long) (localTimeout-sleeptime);
					try {
						Thread.currentThread().sleep((long) sleeptime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				new Exception().printStackTrace();
				if (!expression.canEvaluate()){
					fail("Timed out expression "+expression.getName()+". Could not evaluate its content before timeout "+timeout);
				} else 
				assertTrue(expression.getFailureMessage(),expression.evaluate());
			}
		};		
	    thread.setUncaughtExceptionHandler(Thread.currentThread().getUncaughtExceptionHandler());
	    thread.setDefaultUncaughtExceptionHandler(Thread.currentThread().getDefaultUncaughtExceptionHandler());

		thread.start();
		return thread;
	}

	// From Laurent Caillette's weblog
	public static void forbidSystemExitCall() {
		final SecurityManager securityManager = new SecurityManager() {
			public void checkPermission( Permission permission ) {
				if( "exitVM".equals( permission.getName() ) ) {
					throw new RuntimeException("System exit signal trapped") ;
				}
			}
		} ;
		System.setSecurityManager( securityManager ) ;
	}
	
	// From Laurent Caillette's weblog
	public static void enableSystemExitCall() {
		System.setSecurityManager( null ) ;
	}

	public static void doNothing(long sleepTime) {
		try {
			Thread.currentThread().sleep(sleepTime);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public static void waitForAgentInitialised(MentalStateProcessor msp) {
		while (!msp.agentInitialised()){
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}
		}
	}
	
	public static void waitForAgentInitialised(MentalStateManager msm) {
		while (msm.findEntityTypeInstances("Agent_data").isEmpty()){
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {			
				e.printStackTrace();
			}
		}
	}


	public static void printSnapshot(String id){
		System.out.println(snapshots.get(id));
	}

	public static String getSnapshotString(String id){
		return snapshots.get(id).toString();
	}



	public static void snapShot(MentalStateManager msm, String id) {
		snapshots.put(id,msm.getAllMentalEntities());
	}

	public static Vector<MentalEntity> compareCurrentStateAgainstSnapshot(
			MentalStateManager msm, String id) {
		HashSet<MentalEntity> oldMentalState = new HashSet<MentalEntity>(snapshots.get(id));
		Vector<MentalEntity> differentEntities=new Vector<MentalEntity>();
		HashSet<MentalEntity> currentMentalState=new HashSet<MentalEntity>(msm.getAllMentalEntities());
		for (MentalEntity me:currentMentalState){
			boolean found=false;
			for (MentalEntity old:oldMentalState){
				found = found || old.getId().equalsIgnoreCase(me.getId()); 
			}
			if (!found)
				differentEntities.add(me);

		}
		return differentEntities;

	}

	public static Vector<MentalEntity>  findMentalEntityInConversationByType(
			RuntimeConversation conv, String value) {
		Enumeration elementsContained=conv.getCurrentContentElements();
		Vector<MentalEntity> mes=new Vector<MentalEntity>(); 
		boolean found=false;
		while (!found && elementsContained.hasMoreElements()){
			MentalEntity next=(MentalEntity) elementsContained.nextElement();
			if (next.getType().equalsIgnoreCase(value))
				mes.add(next);
		}
		return mes;
	}

	public static void checkNOExistenceMEWithinMS(MentalStateManager msm,
			String type, String agent) {
		assertTrue("There should be no instance of "+type+" in the mental state of agent "+agent,msm.getMentalEntityByType(type).size()==0);
	}

	public static void checkExistenceMEWithinMS(MentalStateManager msm,
			String type, String agent, int instances) {
		assertTrue("There should be "+instances+" instance of "+type+" in the mental state of agent "+agent,msm.getMentalEntityByType(type).size()==instances);
	}

	public static void checkNOExistenceMEWithinConv(RuntimeConversation conv, String type, String agent) {
		Vector<MentalEntity> mes;
		mes=TestUtils.findMentalEntityInConversationByType(conv,type);
		assertTrue("There should no one instance of  "+type+" in agent "+agent+" within the conversation and there were "+mes,mes.size()==0);
	}

	public static void checkExistenceMEWithinConv(RuntimeConversation conv, String type, String agent, int instances) {
		Vector<MentalEntity> mes;
		mes=TestUtils.findMentalEntityInConversationByType(conv,type);
		assertTrue("There should "+instances+" instances of  "+type+" in agent "+agent+" within the conversation and there were "+mes,mes.size()==instances);
	}

	public static RuntimeConversation checkFirstConversation(
			MentalStateManager msmA, String agent, String state) {
		Vector<MentalEntity> conversations;
		RuntimeConversation conv;
		conversations = msmA.getMentalEntityByType("RuntimeConversation");
		assertTrue("There should be only one conversation in agent "+agent+" and there where "+conversations,(conversations.size()==1));
		assertTrue("There should be only one conversation in state "+state+"in agent "+agent+" and current one is "+((RuntimeConversation)conversations.elementAt(0)).State,
				((RuntimeConversation)conversations.elementAt(0)).State.equalsIgnoreCase(state));
		conv=(RuntimeConversation)conversations.elementAt(0);
		return conv;
	}

	public static void verifyFields(
			Class myClass,
			java.lang.Object content, Vector<Class> verifiedClasses) throws VerificationFailure{
		Field[] fields = myClass.getDeclaredFields();
		boolean serializableFields=true;
		if (!isSerializable(myClass))
			throw new VerificationFailure("Class "+myClass.getName()+" is not serializable");
		for (Field field:fields){
			try {
				field.setAccessible(true); // to eliminate private access restrictions
				if (field.get(content)==null){
					throw new VerificationFailure("Field "+field.getName()+" of class instance "+content.getClass().getName()+" does contain a null value. It cannot be serialized properly");
				}
				
				Class fieldClass=field.getDeclaringClass();
				verifiedClasses.add(fieldClass);
				boolean serializableExists = isSerializable(fieldClass);
				if (!serializableExists) 
					throw new VerificationFailure("Field "+field.getName()+" does not implement the serializable interface. It cannot be transmitted");			
				if (!verifiedClasses.contains(fieldClass))
					verifyFields(fieldClass,field.get(content), verifiedClasses);
				serializableFields=serializableFields && serializableExists;		
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static boolean isSerializable(Class fieldClass) {
		Class<?>[] interfaces = fieldClass.getInterfaces();
		boolean serializableExists=false;
		for (Class currentInterface:interfaces){
			serializableExists=serializableExists || currentInterface.equals(java.io.Serializable.class);
		}
		return serializableExists;
	}


}
