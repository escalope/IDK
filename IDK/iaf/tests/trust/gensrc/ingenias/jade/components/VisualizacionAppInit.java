

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

public  class VisualizacionAppInit {

 private static VisualizacionAppAppImp instance = null;


private static java.lang.String semaphore="VisualizacionApp";
 

 


 public static void initialize(VisualizacionAppAppImp app){
  
 }

 public static void shutdown(VisualizacionAppAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {

   if (instance!=null){
	shutdown(instance);
   }


  }
}




  public static VisualizacionAppApp getInstance(){
  	synchronized (semaphore) {
   if (instance==null){
	instance=new VisualizacionAppAppImp();
    initialize(instance);
   }
   
   return instance;
   }
  }
    public static VisualizacionAppApp getInstance(JADEAgent owner){
    	synchronized (semaphore) {
   if (instance==null){
	instance=new VisualizacionAppAppImp();	
    initialize(instance);
   } 
	instance.registerMultipleOwners(owner);
	
   return instance;
   }
  }

}

 