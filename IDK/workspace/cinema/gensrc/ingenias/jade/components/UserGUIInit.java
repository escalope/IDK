

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

public  class UserGUIInit {
 


 private static Vector<UserGUIAppImp> appsinitialised=new Vector<UserGUIAppImp>();
 


 public static void initialize(UserGUIAppImp app){
  	 final UserGUIAppImp appF=app; 
					new Thread(){
					public void run(){
					appF.showGUI();			 
					}
					}.start();
 }

 public static void shutdown(UserGUIAppImp app){
  
 }

public static void shutdown(){


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

}



 public static Vector<UserGUIAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static UserGUIApp createInstance(){
	UserGUIAppImp app=new UserGUIAppImp();
    initialize(app);
	appsinitialised.add(app);
   return app;
  }
  public static UserGUIApp createInstance(JADEAgent owner){
	UserGUIAppImp app=new UserGUIAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
   return app;
  }


}

 