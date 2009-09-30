

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

public  class SimulationKernelInit {

 private static SimulationKernelAppImp instance = null;


private static java.lang.String semaphore="SimulationKernel";
 

 


 public static void initialize(SimulationKernelAppImp app){
  
 }

 public static void shutdown(SimulationKernelAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {

   if (instance!=null){
	shutdown(instance);
   }


  }
}




  public static SimulationKernelApp getInstance(){
  	synchronized (semaphore) {
   if (instance==null){
	instance=new SimulationKernelAppImp();
    initialize(instance);
   }
   
   return instance;
   }
  }
    public static SimulationKernelApp getInstance(JADEAgent owner){
    	synchronized (semaphore) {
   if (instance==null){
	instance=new SimulationKernelAppImp();	
    initialize(instance);
   } 
	instance.registerMultipleOwners(owner);
	
   return instance;
   }
  }

}

 