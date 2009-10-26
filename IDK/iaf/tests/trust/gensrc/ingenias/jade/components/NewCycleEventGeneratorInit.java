

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

public  class NewCycleEventGeneratorInit {


private static java.lang.String semaphore="NewCycleEventGenerator";
 


 private static Vector<NewCycleEventGeneratorAppImp> appsinitialised=new Vector<NewCycleEventGeneratorAppImp>();
 


 public static void initialize(NewCycleEventGeneratorAppImp app){
  
 }

 public static void shutdown(NewCycleEventGeneratorAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

  }
}



 public static Vector<NewCycleEventGeneratorAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static NewCycleEventGeneratorApp createInstance(){
  	synchronized (semaphore) {
	NewCycleEventGeneratorAppImp app=new NewCycleEventGeneratorAppImp();
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }
  public static NewCycleEventGeneratorApp createInstance(JADEAgent owner){
  	synchronized (semaphore) {
	NewCycleEventGeneratorAppImp app=new NewCycleEventGeneratorAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }


}

 