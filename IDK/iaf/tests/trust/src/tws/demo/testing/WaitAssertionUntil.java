/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tws.demo.testing;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;

/**
 *
 * @author carlos
 */
public class WaitAssertionUntil {

    class Timer extends Thread{
        private WaitAssertionUntil parent;
        long timeInSeconds;
        Timer(WaitAssertionUntil parent, long timeInSeconds){
            this.parent = parent;
            this.timeInSeconds =timeInSeconds;
            this.setDaemon(true);
        }
        public void run(){
            try {
                Thread.sleep(timeInSeconds * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(WaitAssertionUntil.class.getName()).log(Level.SEVERE, null, ex);
            }
            parent.finalize.set(true);
        }
    }


    public WaitAssertionUntil(){
    }


    private AtomicBoolean finalize=new AtomicBoolean(false);
    public void doIt(Assertion assertion, long timeInSeconds){
        finalize.set(false);
        Timer timer = new Timer(this,timeInSeconds);
        timer.start();
        while(!finalize.get()&!assertion.eval()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(WaitAssertionUntil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(!assertion.eval()) fail(assertion.failMessage());
    }
}

