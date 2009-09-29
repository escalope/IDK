

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

public  class MyApplicationInit {

 private static MyApplicationAppImp instance = null;


private static java.lang.String semaphore="MyApplication";
 

 


 public static void initialize(MyApplicationAppImp app){
  
 }

 public static void shutdown(MyApplicationAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {

   if (instance!=null){
	shutdown(instance);
   }


  }
}




  public static MyApplicationApp getInstance(){
  	synchronized (semaphore) {
   if (instance==null){
	instance=new MyApplicationAppImp();
    initialize(instance);
   }
   
   return instance;
   }
  }
    public static MyApplicationApp getInstance(JADEAgent owner){
    	synchronized (semaphore) {
   if (instance==null){
	instance=new MyApplicationAppImp();	
    initialize(instance);
   } 
	instance.registerMultipleOwners(owner);
	
   return instance;
   }
  }

}

 