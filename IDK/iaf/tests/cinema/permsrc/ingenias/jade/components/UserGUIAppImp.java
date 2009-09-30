

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

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.Kernel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import ingenias.editor.entities.ApplicationEventSlots;
import ingenias.jade.exception.*;
import ingenias.jade.mental.User_wants_to_watch_a_movie;



public  class UserGUIAppImp extends UserGUIApp{

	JFrame userGUI=new JFrame();
	JButton requestMovie=new JButton("Ask for a movie");
	JLabel result=new JLabel("");
	private static Hashtable<String,UserGUIAppImp> instances=new Hashtable<String,UserGUIAppImp>();
	private static Vector<String>[] monthActivity=null;
	private static Vector<String> expectedDayActivity=new  Vector<String>();
	private static String syncObject="UserGUIApp";	

	static {
		
		
		
	}

	public UserGUIAppImp(){
		super();		
	}

	protected void issueRequest() {
		System.err.println("agent :"+this.getOwner().getAID().getLocalName()+" requesting...");
		requestMovie.setEnabled(false);
		result.setText("Finding a cinema");
		User_wants_to_watch_a_movie nevent= new User_wants_to_watch_a_movie();	        
		try {
			getOwner().getMSM().addMentalEntity(nevent);
		} catch (ingenias.exception.InvalidEntity ex){
			ex.printStackTrace();
		}
	}

	public void showGUI(){
		Dimension dim=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		userGUI.setLocation(dim.width/2,dim.height/2);
		userGUI.setTitle(getOwner().getName()+" - User GUI");
		Box box=javax.swing.Box.createVerticalBox();
		userGUI.getContentPane().add(box);
		box.add(new JLabel("agent: "+this.getOwner().getLocalName()));
		box.add(requestMovie);
		box.add(result);

		requestMovie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				issueRequest();
			}
		});
		userGUI.setSize(new Dimension(400,100));
		userGUI.doLayout();
		//userGUI.setVisible(true);
	}

	public  void presentTicket(int seat, int cost){
		synchronized(syncObject){
			requestMovie.setEnabled(true);
			result.setText("Received a movie ticket at cinema "+" for seat "+seat+" and cost "+cost);
			userGUI.doLayout();
			SimulationKernelInit.getInstance().ticketObtained(getOwner());									
		}
	}

	public synchronized void apologizeForFailure() {
		synchronized(syncObject){
			requestMovie.setEnabled(true);
			result.setText("Sorry, but could not get a ticket.");
			userGUI.doLayout();
			SimulationKernelInit.getInstance().ticketNotObtained(getOwner());			
		}
	}

}

