

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

public  class VirtualTerminalInit {
 


 private static Vector<VirtualTerminalAppImp> appsinitialised=new Vector<VirtualTerminalAppImp>();
 


 public static void initialize(VirtualTerminalAppImp app){
  
 }

 public static void shutdown(VirtualTerminalAppImp app){
  
 }

public static void shutdown(){


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

}



 public static Vector<VirtualTerminalAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static VirtualTerminalApp createInstance(){
	VirtualTerminalAppImp app=new VirtualTerminalAppImp();
    initialize(app);
	appsinitialised.add(app);
   return app;
  }
  public static VirtualTerminalApp createInstance(JADEAgent owner){
	VirtualTerminalAppImp app=new VirtualTerminalAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
   return app;
  }


}

 