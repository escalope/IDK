

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

public  class TimerAppInit {

 private static TimerAppAppImp instance = null;


private static java.lang.String semaphore="TimerApp";
 

 


 public static void initialize(TimerAppAppImp app){
  
 }

 public static void shutdown(TimerAppAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {

   if (instance!=null){
	shutdown(instance);
   }


  }
}




  public static TimerAppApp getInstance(){
  	synchronized (semaphore) {
   if (instance==null){
	instance=new TimerAppAppImp();
    initialize(instance);
   }
   
   return instance;
   }
  }
    public static TimerAppApp getInstance(JADEAgent owner){
    	synchronized (semaphore) {
   if (instance==null){
	instance=new TimerAppAppImp();	
    initialize(instance);
   } 
	instance.registerMultipleOwners(owner);
	
   return instance;
   }
  }

}

 