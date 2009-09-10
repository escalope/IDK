

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

import java.util.*;
import ingenias.jade.exception.*;
import ingenias.jade.JADEAgent;

public  class TimerCiclosAppInit {

 private static TimerCiclosAppAppImp instance = null;


private static java.lang.String semaphore="TimerCiclosApp";
 

 


 public static void initialize(TimerCiclosAppAppImp app){
  
 }

 public static void shutdown(TimerCiclosAppAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {

   if (instance!=null){
	shutdown(instance);
   }


  }
}




  public static TimerCiclosAppApp getInstance(){
  	synchronized (semaphore) {
   if (instance==null){
	instance=new TimerCiclosAppAppImp();
    initialize(instance);
   }
   
   return instance;
   }
  }
    public static TimerCiclosAppApp getInstance(JADEAgent owner){
    	synchronized (semaphore) {
   if (instance==null){
	instance=new TimerCiclosAppAppImp();	
    initialize(instance);
   } 
	instance.registerMultipleOwners(owner);
	
   return instance;
   }
  }

}

 