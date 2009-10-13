/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tws.demo.testing;

import static org.junit.Assert.*;
/**
 *
 * @author carlos
 */
public class TestUtils2 {

    public static void waitAssertionUntil(Assertion assertion, long timeInSeconds){
        WaitAssertionUntil wau = new WaitAssertionUntil();
        wau.doIt(assertion, timeInSeconds);
    }

}
