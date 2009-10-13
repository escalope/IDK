

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

public  class VisualizationAppInit {

 private static VisualizationAppAppImp instance = null;


private static java.lang.String semaphore="VisualizationApp";
 

 


 public static void initialize(VisualizationAppAppImp app){
  
 }

 public static void shutdown(VisualizationAppAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {

   if (instance!=null){
	shutdown(instance);
   }


  }
}




  public static VisualizationAppApp getInstance(){
  	synchronized (semaphore) {
   if (instance==null){
	instance=new VisualizationAppAppImp();
    initialize(instance);
   }
   
   return instance;
   }
  }
    public static VisualizationAppApp getInstance(JADEAgent owner){
    	synchronized (semaphore) {
   if (instance==null){
	instance=new VisualizationAppAppImp();	
    initialize(instance);
   } 
	instance.registerMultipleOwners(owner);
	
   return instance;
   }
  }

}

 