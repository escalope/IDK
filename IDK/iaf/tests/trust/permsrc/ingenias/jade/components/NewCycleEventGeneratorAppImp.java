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
import ingenias.jade.MyMath;
import ingenias.jade.exception.*;
import ingenias.jade.mental.NewCycleEvent;
import ingenias.testing.TestUtils;

public class NewCycleEventGeneratorAppImp extends NewCycleEventGeneratorApp {

    private Random randomQuality = new Random();
    private Random randomTime = new Random();
    private SecureRandom random = new SecureRandom();
    private Hashtable<String, Double> documentsTable = new Hashtable<String, Double>();

    public NewCycleEventGeneratorAppImp() {
        super();
    }

    @Override
    public void setNewCycleEventSequence(final Double[] documentQuality) {
        new Thread() {

            public void run() {
                for (int k = 0; k < documentQuality.length; k++) {
                    NewCycleEvent nevent = new NewCycleEvent();
                    nevent.setdeclaredQuality(filter(documentQuality[k]));
                    System.out.println("DQ:" + nevent.getdeclaredQuality());
                    BigInteger nvalue = new BigInteger(32, random);
                    while (documentsTable.containsKey(nvalue.toString())) {
                        nvalue = new BigInteger(32, random);
                    }
                    nevent.setdata(nvalue.toString()); // Idea from http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string-in-java
                    documentsTable.put(nvalue.toString(), nevent.getdeclaredQuality());
                    try {
                        getOwner().getMSM().addMentalEntity(nevent);
                    } catch (InvalidEntity e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.err.println(getOwner().getLocalName() + " agent inserting event with quality " + documentQuality[k]);
                    long wait = (long) (4000 + 2000 * (randomQuality.nextDouble()));

                    TestUtils.doNothing(wait);
                    //TestUtils.doNothing(4000);

                }
            }
        }.start();

    }

    @Override
    public void setNewCycleEventSequence(final Integer size, final String distribution) {
        new Thread() {

            public void run() {
                if (getOwner().getAID().getLocalName().contains("0") ||
                        getOwner().getAID().getLocalName().contains("1") ||
                        getOwner().getAID().getLocalName().contains("2")) {
                    for (int k = 0; k < 25; k++) {
                        NewCycleEvent nevent = new NewCycleEvent();
                        nevent.setdeclaredQuality(filter(MyMath.gaussian(0.5, 0.3)));
                        BigInteger nvalue = new BigInteger(32, random);
                        while (documentsTable.containsKey(nvalue.toString())) {
                            nvalue = new BigInteger(32, random);
                        }
                        nevent.setdata(nvalue.toString()); // Idea from http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string-in-java
                        documentsTable.put(nvalue.toString(), nevent.getdeclaredQuality());
                        try {
                            getOwner().getMSM().addMentalEntity(nevent);
                        } catch (InvalidEntity e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        long wait = (long) (6000 + 2000 * (randomQuality.nextDouble()));

                        TestUtils.doNothing(wait);


                    }
                } else {
                    for (int k = 0; k < 40; k++) {
                        NewCycleEvent nevent = new NewCycleEvent();
                        nevent.setdeclaredQuality(filter(MyMath.gaussian(0.25 + k * 0.5 / 40.0, 0.1)));
                        BigInteger nvalue = new BigInteger(32, random);
                        while (documentsTable.containsKey(nvalue.toString())) {
                            nvalue = new BigInteger(32, random);
                        }
                        nevent.setdata(nvalue.toString()); // Idea from http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string-in-java
                        documentsTable.put(nvalue.toString(), nevent.getdeclaredQuality());
                        try {
                            getOwner().getMSM().addMentalEntity(nevent);
                        } catch (InvalidEntity e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        long wait = (long) (5000 + 2000 * (randomQuality.nextDouble()));

                        TestUtils.doNothing(wait);


                    }
                }

            }
        }.start();

    }

    private static double filter(double value) {
        if (value > 1) {
            value = 1;
        }
        if (value < -1) {
            value = -1;
        }
        return value;
    }
}

