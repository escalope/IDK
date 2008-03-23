

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
		com.thoughtworks.xstream.XStream xs=new XStream(new DomDriver());
		FileInputStream fis;
		try {
			fis = new FileInputStream("config/monthlyActivity.xml");
			monthActivity=(Vector<String>[]) xs.fromXML(fis);
			JFrame startMonthlyActivityDialog=new JFrame();
			final JButton start=new JButton("Start monthly activity");
			startMonthlyActivityDialog.getContentPane().add(start);
			startMonthlyActivityDialog.pack();
			startMonthlyActivityDialog.setVisible(true);
			start.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					new Thread("Daily activity"){
						public void run(){
							start.setEnabled(false);
							Vector<UserGUIAppImp> apps = UserGUIInit.getAppsInitialised();
							for (UserGUIAppImp app:apps){
								instances.put(app.getOwner().getAID().getLocalName(), app);
							}
							for (Vector<String> day:monthActivity){
								expectedDayActivity=new Vector(day);
								System.err.println("expected activity day: "+expectedDayActivity);
								for (String agentid:day){
									instances.get(agentid).issueRequest();
								}
								while (expectedDayActivity.size()!=0){
									try {
										Thread.currentThread().sleep(1000);
									} catch (InterruptedException e) {
										
										e.printStackTrace();
									}	
									System.err.println("Remaining "+expectedDayActivity	);
								}
							}
							javax.swing.JOptionPane.showMessageDialog(null,"Monthly activity finished");
							start.setEnabled(true);
						}
					}.start();
				}

			});

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
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

	public void presentTicket(int seat, int cost){
		synchronized(syncObject){
			requestMovie.setEnabled(true);
			result.setText("Received a movie ticket at cinema "+" for seat "+seat+" and cost "+cost);
			userGUI.doLayout();
			expectedDayActivity.remove(this.getOwner().getAID().getLocalName());
			userGUI.setVisible(true);
		}
	}

	public void apologizeForFailure() {
		synchronized(syncObject){
			requestMovie.setEnabled(true);
			result.setText("Sorry, but could not get a ticket.");
			userGUI.doLayout();
			expectedDayActivity.remove(this.getOwner().getAID().getLocalName());
		}
	}

}

