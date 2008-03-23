

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
 

 


 public static void initialize(ClockAppImp app){
  
 }

 public static void shutdown(ClockAppImp app){
  
 }

public static void shutdown(){

   if (instance!=null){
	shutdown(instance);
   }


}




  public static ClockApp getInstance(){
   if (instance==null){
	instance=new ClockAppImp();
    initialize(instance);
   }
   return instance;
  }
    public static ClockApp getInstance(JADEAgent owner){
   if (instance==null){
	instance=new ClockAppImp();
	instance.registerOwner(owner);
    initialize(instance);
   }
   if (instance.getOwner()==null)
	 instance.registerOwner(owner);
	 
   return instance;
  }

}

 