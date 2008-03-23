

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

public  class TimeOut_generatorInit {

 private static TimeOut_generatorAppImp instance = null;
 

 

 public static void initialize(TimeOut_generatorAppImp app){
  
 }

 public static void shutdown(TimeOut_generatorAppImp app){
  
 }

public static void shutdown(){

   if (instance!=null){
	shutdown(instance);
   }


}




  public static TimeOut_generatorApp getInstance(){
   if (instance==null){
	instance=new TimeOut_generatorAppImp();
    initialize(instance);
   }
   return instance;
  }
    public static TimeOut_generatorApp getInstance(JADEAgent owner){
   if (instance==null){
	instance=new TimeOut_generatorAppImp();
	instance.registerOwner(owner);
    initialize(instance);
   }
   return instance;
  }

}

 