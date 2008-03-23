

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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ingenias.exception.InvalidEntity;
import ingenias.jade.exception.*;
import ingenias.jade.mental.UserPressedButton;



public  class UserGUIAppImp extends UserGUIApp{

 public UserGUIAppImp(){
  super();
  JFrame main=new JFrame ();
  JButton jb=new JButton("Press me");
  main.getContentPane().add(jb);
  jb.addActionListener(new ActionListener(){

	public void actionPerformed(ActionEvent arg0) {
		buttonPressed();		
	}
	  
  });
  main.pack();
  main.setVisible(true);
  
 }

public void buttonPressed() {
	try {
		this.getOwner().getMSM().addMentalEntity(new UserPressedButton());
	} catch (InvalidEntity e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void greetUser() {
	new Thread(){
		public void run(){
			JOptionPane.showMessageDialog(null, "Hello World!!!");
		}
	}.start();
	
}


}

 
