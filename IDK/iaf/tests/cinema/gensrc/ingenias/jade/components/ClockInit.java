

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

public  class ClockInit {

 private static ClockAppImp instance = null;


private static java.lang.String semaphore="Clock";
 

 


 public static void initialize(ClockAppImp app){
  
 }

 public static void shutdown(ClockAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {

   if (instance!=null){
	shutdown(instance);
   }


  }
}




  public static ClockApp getInstance(){
  	synchronized (semaphore) {
   if (instance==null){
	instance=new ClockAppImp();
    initialize(instance);
   }
   
   return instance;
   }
  }
    public static ClockApp getInstance(JADEAgent owner){
    	synchronized (semaphore) {
   if (instance==null){
	instance=new ClockAppImp();	
    initialize(instance);
   } 
	instance.registerMultipleOwners(owner);
	
   return instance;
   }
  }

}

 