

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

public  class Cinema_directoryInit {
 


 private static Vector<Cinema_directoryAppImp> appsinitialised=new Vector<Cinema_directoryAppImp>();
 

 public static void initialize(Cinema_directoryAppImp app){
  
 }

 public static void shutdown(Cinema_directoryAppImp app){
  
 }

public static void shutdown(){


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

}



  public static Cinema_directoryApp createInstance(){
	Cinema_directoryAppImp app=new Cinema_directoryAppImp();
    initialize(app);
	appsinitialised.add(app);
   return app;
  }
  public static Cinema_directoryApp createInstance(JADEAgent owner){
	Cinema_directoryAppImp app=new Cinema_directoryAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
   return app;
  }


}

 