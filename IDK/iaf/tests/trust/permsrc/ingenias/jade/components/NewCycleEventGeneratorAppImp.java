

/**
 *
 *
 *  Description of the task /
 *
 * 
 *@author     Jorge J. Gomez
 *@version    1.0
 */

package ingenias.jade.components;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

import ingenias.exception.InvalidEntity;
import ingenias.jade.exception.*;
import ingenias.jade.mental.NewCycleEvent;
import ingenias.testing.TestUtils;



public  class NewCycleEventGeneratorAppImp extends NewCycleEventGeneratorApp{
	private Random randomQuality=new Random();
	private Random randomTime=new Random();
	private SecureRandom random = new SecureRandom();
	private Hashtable<String,Double> documentsTable=new Hashtable<String,Double>();
	public NewCycleEventGeneratorAppImp(){
		super();
	}

	@Override
	public void setNewCycleEventSequence(final Double[] documentQuality) {
		new Thread(){
			public void run(){
				for (int k=0;k<documentQuality.length;k++){
					NewCycleEvent nevent = new NewCycleEvent();				
					nevent.setdeclaredQuality(documentQuality[k]);		
					BigInteger nvalue=new BigInteger(32, random);
					while (documentsTable.containsKey(nvalue.toString())){
						nvalue=new BigInteger(32, random);
					}
					nevent.setdata(nvalue.toString()); // Idea from http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string-in-java
					documentsTable.put(nvalue.toString(), nevent.getdeclaredQuality());
					try {
						getOwner().getMSM().addMentalEntity(nevent);
					} catch (InvalidEntity e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
					System.err.println(getOwner().getLocalName()+" agent inserting event with quality "+documentQuality[k]);
					long wait=(long) (3000*randomQuality.nextGaussian());
					
					TestUtils.doNothing(Math.abs(wait));		


				}
			}
		}.start();

	}

	@Override
	public void setNewCycleEventSequence(final Integer size, final String distribution) {
		new Thread(){
			public void run(){
				for (int k=0;k<size;k++){
					NewCycleEvent nevent = new NewCycleEvent();
					if (distribution.equalsIgnoreCase("good")){
						nevent.setdeclaredQuality(0.5+Math.abs(randomQuality.nextGaussian()));					
					}
					if (distribution.equalsIgnoreCase("bad")){
						nevent.setdeclaredQuality(Math.abs(randomQuality.nextGaussian())-0.5);					
					}
					if (distribution.equalsIgnoreCase("mixture")){
						nevent.setdeclaredQuality(Math.abs(randomQuality.nextGaussian()));					
					}

					BigInteger nvalue=new BigInteger(32, random);
					while (documentsTable.containsKey(nvalue.toString())){
						nvalue=new BigInteger(32, random);
					}
					nevent.setdata(nvalue.toString()); // Idea from http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string-in-java
					documentsTable.put(nvalue.toString(), nevent.getdeclaredQuality());
					try {
						getOwner().getMSM().addMentalEntity(nevent);
					} catch (InvalidEntity e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					TestUtils.doNothing((long) (3000*Math.abs(randomQuality.nextGaussian())));		


				}
			}
		}.start();

	}


}

