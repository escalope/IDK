

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

public  class GUIInit {


private static java.lang.String semaphore="GUI";
 


 private static Vector<GUIAppImp> appsinitialised=new Vector<GUIAppImp>();
 


 public static void initialize(GUIAppImp app){
   javax.swing.JFrame guiFrame=new javax.swing.JFrame();
					javax.swing.JButton pressHere=new javax.swing.JButton("Press here");
					javax.swing.JPanel elements=new javax.swing.JPanel(new java.awt.GridLayout(2,1));
					elements.add(new
					javax.swing.JLabel(app.getOwner().getAID().getLocalName()));
					elements.add(pressHere);
					guiFrame.getContentPane().add(elements);
					// This statement is valid only for multiple instances pattern
					guiFrame.setTitle(app.getOwner().getAID().getLocalName());
					guiFrame.toFront();
					guiFrame.setLocationByPlatform(true);

					// Singleton pattern would use app.getOwners(), which returns a
					// vector of current owners. Besides, a singleton pattern is not
					// initialized with all registered owners, since this value
					// can change during the applications live.
					final GUIAppImp application=app;
					pressHere.addActionListener(new java.awt.event.ActionListener(){
					public void actionPerformed(java.awt.event.ActionEvent e) {
					ingenias.jade.mental.SampleGUIEvent event=new
					ingenias.jade.mental.SampleGUIEvent();
					try {
					application.getOwner().getMSM().addMentalEntity(event);
					} catch (ingenias.exception.InvalidEntity e1) {
					e1.printStackTrace();
					}
					}
					});
					guiFrame.pack();
					guiFrame.setVisible(true);
				
 }

 public static void shutdown(GUIAppImp app){
  
 }

public static void shutdown(){
	synchronized (semaphore) {


  for (int k=0;k<appsinitialised.size();k++){
   shutdown(appsinitialised.elementAt(k));
  }

  }
}



 public static Vector<GUIAppImp>  getAppsInitialised(){
		return appsinitialised;
 }
  public static GUIApp createInstance(){
  	synchronized (semaphore) {
	GUIAppImp app=new GUIAppImp();
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }
  public static GUIApp createInstance(JADEAgent owner){
  	synchronized (semaphore) {
	GUIAppImp app=new GUIAppImp();
	app.registerOwner(owner);
    initialize(app);
	appsinitialised.add(app);
	
   return app;
   }
  }


}

 