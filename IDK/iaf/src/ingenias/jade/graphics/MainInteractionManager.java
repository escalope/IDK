/*
    Copyright (C) 2005 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 

    INGENIAS Agent Framework is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS Agent Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS Agent Framework; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 */


package ingenias.jade.graphics;

import ingenias.jade.IAFProperties;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.core.behaviours.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.junit.runner.RunWith;

import sun.misc.Compare;
import sun.misc.Sort;



public class MainInteractionManager
extends javax.swing.JFrame {
	private static MainInteractionManager mim = new MainInteractionManager();
	private static Hashtable intsPanel = new Hashtable();
	private JTabbedPane jtp=new JTabbedPane();
	JPanel mainPanel = new JPanel(new BorderLayout());
	private JButton runStep;
	private JLabel jLabel1;
	private JTextField runDelay;
	private JButton runWithoutInterruption;
	private JPanel jPanel1;
	private final static int MaxEntriesPerKey=100;
	private String secondMonitor="secondmonitor";
	JSplitPane mainInteractions = new JSplitPane();
	JSplitPane mainTasks = new JSplitPane();
	JSplitPane mainMentalState = new JSplitPane();
	JPanel mainApplications = new JPanel(new BorderLayout());

	JScrollPane imcontainerScrollPane = new JScrollPane();
	JScrollPane mstcontainerscrollpane = new JScrollPane();
	JScrollPane taskcontainerScrollPane = new JScrollPane();
	JScrollPane appcontainerScrollPane = new JScrollPane();

	Hashtable<String,Vector<String>> logsBuffer=new Hashtable<String,Vector<String>>();

	DefaultListModel agentsCombo=new DefaultListModel(); 
	JTextArea logs=new JTextArea();
	JScrollPane logsScrollPane = new JScrollPane();
	JTextField logsFilter=new JTextField(40);

	private boolean automatic=true;
	private long delay=0;
	private int currentCount=0; 
	private static int agentCounter=0;
	private String currentAgentLogged="";
	private FileOutputStream logFile=null;

	Box imcontainer;
	Box taskcontainer;
	Box mstcontainer;
	Box appcontainer;
	JPanel mainLogs;
	JScrollPane interactionsScrollPane = new JScrollPane();
	Box interactions;

	public static double scalex=1;
	public static double scaley=1;
	private static boolean logsOn=false;

	static {		
		if (IAFProperties.getGraphicsOn()){
			mim.pack();
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

			mim.setLocation(
					d.width / 2 - mim.getSize().width / 2,
					d.height / 2 - 100);

			mim.show();
			mim.setMainTask(new JPanel());
			mim.setMainMS(new JScrollPane());
		}
	}

	public static MainInteractionManager getInstance() {
		return mim;
	}

	public static void addAgentInteraction(final JPanel jp, final String cid, final String role, final String protocol) {
		if (IAFProperties.getGraphicsOn()){
			Runnable run=new Runnable(){
				public void run(){
					getInstance().imcontainer.add(jp);
					agentCounter++;
					VertizalZoomableBox panel = null;
					if (intsPanel.containsKey(cid)) {
						panel = (VertizalZoomableBox) intsPanel.get(cid);
					}
					else {
						final JPanel spanel = new JPanel(new BorderLayout());
						panel = new VertizalZoomableBox(BoxLayout.Y_AXIS);
						intsPanel.put(cid, panel);
						JButton jb = new JButton("Close");
						final String _cid = cid;
						jb.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(java.awt.event.ActionEvent e) {
								getInstance().interactions.remove(spanel);
								getInstance().repaint();
							}
						});

						JButton logInt = new JButton("Log");
						logInt.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(java.awt.event.ActionEvent e) {
								JFrame logwindow=new JFrame();

								JTextArea contentWidget=new JTextArea();
								contentWidget.setWrapStyleWord(true);
								contentWidget.setLineWrap(true);
								JScrollPane jscr=new JScrollPane(contentWidget);
								contentWidget.setText(getInstance().findLogs(cid));
								contentWidget.setEditable(false);
								logwindow.getContentPane().add(jscr);
								logwindow.pack();
								logwindow.setVisible(true);
							}
						});

						Border border1 = BorderFactory.createLineBorder(Color.black, 2);
						TitledBorder tb = new TitledBorder(border1, cid+"-"+role+":"+protocol);
						panel.setBorder(tb);
						spanel.add(panel, BorderLayout.CENTER);
						Box wpanel=Box.createVerticalBox();				
						wpanel.add(jb);
						wpanel.add(logInt);
						spanel.add(wpanel, BorderLayout.WEST);
						getInstance().interactions.add(spanel);
					}
					panel.add(jp);
					JFrame jf;
					getInstance().repaint();
				}
			};
			SwingUtilities.invokeLater(run);
		}


	}

	protected synchronized String findLogs(String cid) {
		Vector<String> result=new Vector<String>();
		Set<String> keys = getInstance().logsBuffer.keySet();		
		for (String key:keys){
			if (key.indexOf("-"+cid)>=0){
				Vector<String> logsAgent = getInstance().logsBuffer.get(key);
				Vector<String> lagents=new Vector<String>();				
				for (String alog:logsAgent){
					lagents.add(key+":"+alog);
				}
				result.addAll(lagents);
			}
		}
		Object[] toSort = result.toArray();
		Sort.quicksort(toSort, new Compare(){

			public int doCompare(Object arg0, Object arg1) {				
				return arg0.toString().compareTo(arg1.toString());
			}});
		result.clear();
		StringBuffer output=new StringBuffer();
		for (Object obj:toSort){
			output.append(obj.toString()+"\n");
		}
		return output.toString();

	}

	public static void setMainTask(final JPanel jp){
		if (IAFProperties.getGraphicsOn()){
			Runnable run=new Runnable(){
				public void run(){
					getInstance().mainTasks.add(jp,JSplitPane.RIGHT);
				}};

				SwingUtilities.invokeLater(run);
		}
	}

	public static void setMainMS(JScrollPane jp){
		if (IAFProperties.getGraphicsOn()){
			synchronized(getInstance()){
				getInstance().mainMentalState.add(jp,JSplitPane.RIGHT);
			}
		}
	}


	public static void addAgentInteractionManager(final JPanel jp, final String agent) {
		if (IAFProperties.getGraphicsOn()){
			Runnable run=new Runnable(){
				public void run(){
					Border border1 = BorderFactory.createLineBorder(Color.black, 2);
					TitledBorder tb = new TitledBorder(border1, agent);
					TitledBorder tasks = new TitledBorder(border1, agent);
					jp.setBorder(tb);
					getInstance().imcontainer.add(jp);
					getInstance().validate();
					getInstance().interactionsScrollPane.validate();
					getInstance().repaint();
				};
			};
			SwingUtilities.invokeLater(run);


		}
	}

	public static void addAgentProgrammedTask(final JPanel task1, final String agent1) {
		if (IAFProperties.getGraphicsOn()){
			Runnable run=new Runnable(){
				public void run(){
					final JPanel task=task1;
					final String agent=agent1;


					Border border1 = BorderFactory.createLineBorder(Color.black, 2);
					TitledBorder tb = new TitledBorder(border1, agent);
					task.setBorder(tb);

					getInstance().taskcontainer.add(task);

					//getInstance().validate();

					getInstance().taskcontainerScrollPane.validate();

					getInstance().repaint();
				};
			};
			SwingUtilities.invokeLater(run);
		}


	}

	public static void addAgentApplication(final Box apps, final String agent) {
		if (IAFProperties.getGraphicsOn()){
			Runnable run=new Runnable(){
				public void run(){
					try {

						Border border1 = BorderFactory.createLineBorder(Color.black, 2);
						TitledBorder tb = new TitledBorder(border1, agent);
						apps.setBorder(tb);

						getInstance().appcontainer.add(apps);

						getInstance().validate();

						getInstance().appcontainerScrollPane.validate();

						getInstance().repaint();

					}catch (Exception ex){
						ex.printStackTrace();
					}
				};
			};
			SwingUtilities.invokeLater(run);
		}

	}

	public static void logInteraction(final String message, final String agent, String cid){
		log(message,agent+"-"+cid);
	}

	public static void logMSP(final String message, final String agent){
		log(message,agent+".MSP");
	}

	public static void logMSP(final String message, final String agent, String taskid, String taskType){
		log(message,agent+".MSP-"+taskid+":"+taskType);
	}

	public static void logMSM(final String message, final String agent){
		log(message,agent+".MSM");
	}


	public static void setLogsOn(boolean logsOn){
		MainInteractionManager.logsOn=logsOn;
	}

	public static void log(final String message, final String agent){

		synchronized (getInstance()) {

			java.util.Date cdate = new java.util.Date();
			java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("HH:mm");
			java.text.SimpleDateFormat dflog = new java.text.SimpleDateFormat("HH:mm:ss:SS");
			String sb = df.format(cdate);
			String sbl = dflog.format(cdate);
			if (IAFProperties.getGraphicsOn()){
				if (!getInstance().logsBuffer.containsKey(agent)){
					getInstance().logsBuffer.put(agent,new Vector<String>());
					SwingUtilities.invokeLater(new Runnable(){
						public void run(){
							synchronized (getInstance()){
								int k=0;
								while (k<getInstance().agentsCombo.getSize() && getInstance().agentsCombo.getElementAt(k).toString().compareTo(agent)<0){
									k++;
								}

								getInstance().agentsCombo.insertElementAt(agent,k);		
							}
						}
					});

				}
				Vector<String> lastEntered = getInstance().logsBuffer.get(agent);
				if (lastEntered.size()==0 || lastEntered.lastElement().indexOf(message)<0){
					if (lastEntered.size()>=MaxEntriesPerKey)
						lastEntered.removeElementAt(0);
					lastEntered.add("["+sbl.toString()+"]"+message); // To avoid repeated messages
					if (getInstance().getCurrentAgentLogged().equals(agent)){
						getInstance().logs.append("["+sbl.toString()+"-"+agent+"]  "+message+"\n");				
					}

				}
			}
			if (IAFProperties.getLogFileOn()){
				try {

					MainInteractionManager.getInstance().logFile.write((sbl.toString()+"|").getBytes());
					MainInteractionManager.getInstance().logFile.write((agent+"|").getBytes());
					MainInteractionManager.getInstance().logFile.write((message+"|").getBytes());
					MainInteractionManager.getInstance().logFile.write(("\n").getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}



		}

	}


	public void finalize(){
		if (logFile!=null)
			try {
				logFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private String getCurrentAgentLogged() {
		return this.currentAgentLogged;
	}

	public static void addAgentMentalState(final JPanel mentalstate, final String agent) {
		if (IAFProperties.getGraphicsOn()){
//			synchronized(getInstance()){
				
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){

						Border border1 = BorderFactory.createLineBorder(Color.black, 2);
						TitledBorder tb = new TitledBorder(border1, agent);
						mentalstate.setBorder(tb);

						getInstance().mstcontainer.add(mentalstate);

						getInstance().validate();

						getInstance().interactionsScrollPane.validate();

						getInstance().repaint();						
					}
				});
				
//			}
		}

	}

	public static void refresh(){
		getInstance().repaint();
	}

	public MainInteractionManager() {

		try {

			jbInit();

			this.setSize(553, 278);
			java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("MMddyyHHmm");
			Date d=new Date();
			if (IAFProperties.getLogFileOn()){
				new File("logs").mkdir();
				this.logFile=new FileOutputStream("logs/log"+df.format(d)+".log");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private void jbInit() throws Exception {

		if (IAFProperties.getGraphicsOn()){

			imcontainer = Box.createVerticalBox();
			JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			this.taskcontainer = Box.createVerticalBox();
			this.mstcontainer=Box.createVerticalBox();
			this.appcontainer=Box.createVerticalBox();
			interactions = Box.createVerticalBox();    
			mainLogs=new JPanel(new BorderLayout());
			this.setTitle("Main Interaction Manager");
			this.getContentPane().setLayout(new BorderLayout());
			imcontainerScrollPane.setAutoscrolls(false);
			mainInteractions.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			mainInteractions.setDividerLocation(100);
			//this.mainApplications.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			//this.mainApplications.setDividerLocation(100);

			//mainPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
			mainPanel.add(jtp,BorderLayout.CENTER);

			//mainPanel.add(this.logsScrollPane,JSplitPane.BOTTOM);
			//mainPanel.setDividerLocation(200);

			this.getContentPane().add(mainPanel,BorderLayout.CENTER);
			{
				jPanel1 = new JPanel();
				this.getContentPane().add(jPanel1, BorderLayout.NORTH);
				{
					runDelay = new JTextField();
					runDelay.addActionListener(new ActionListener(){

						public void actionPerformed(ActionEvent e) {
							try {
								delay= Long.parseLong(runDelay.getText());
							} catch (java.lang.NumberFormatException ne){

							}
						}

					});
					jPanel1.add(runDelay);
					runDelay.setText("0");
					runDelay.setColumns(7);
				}
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("Run delay");
				}

				runStep = new JButton();
				runWithoutInterruption = new JButton();
				runWithoutInterruption.setEnabled(false);
				jPanel1.add(runWithoutInterruption);
				runWithoutInterruption.setText("go");
				runWithoutInterruption.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						runWithoutInterruptionActionPerformed(evt);
						runWithoutInterruption.setEnabled(false);
						runStep.setEnabled(true);
					}
				});


				jPanel1.add(runStep);
				runStep.setText("Manual run");
				runStep.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						runStepActionPerformed(evt);
						runWithoutInterruption.setEnabled(true);
						runStep.setEnabled(false);
					}
				});

			}
			jtp.add("tasks",mainTasks);
			jtp.addTab("interactions", null, mainInteractions, null);
			jtp.add("Mental State",mainMentalState);
			jtp.add("Applications",this.mainApplications);
			jtp.add("Logs", jsp);

			JButton zoom=new JButton("Zoom -");
			zoom.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					scalex=scalex-0.05;
					scaley=scaley-0.025;
					interactions.repaint();
				}

			});
			jPanel1.add(zoom);
			JButton unzoom=new JButton("Zoom +");
			unzoom.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					scalex=scalex+0.05;
					scaley=scaley+0.025;
					interactions.repaint();
				}

			});
			jPanel1.add(unzoom);


			mainInteractions.add(imcontainerScrollPane, JSplitPane.LEFT);
			mainInteractions.add(interactionsScrollPane, JSplitPane.RIGHT);
			this.mainMentalState.add(this.mstcontainerscrollpane,JSplitPane.LEFT);
			this.mainTasks.add(this.taskcontainerScrollPane,JSplitPane.LEFT);
			this.mainApplications.add(this.appcontainerScrollPane,BorderLayout.CENTER);
			interactionsScrollPane.getViewport().add(interactions, null);
			imcontainerScrollPane.getViewport().add(imcontainer, null);
			mstcontainerscrollpane.getViewport().add(mstcontainer, null);
			taskcontainerScrollPane.getViewport().add(taskcontainer, null);
			this.appcontainerScrollPane.getViewport().add(this.appcontainer, null);
			this.logsScrollPane.getViewport().add(this.logs,null); 
			logs.setEditable(false);
			this.logsScrollPane.setAutoscrolls(true);			
			this.logs.setAutoscrolls(true);
			final JList agentSelector=new JList(agentsCombo);

			jsp.setTopComponent(new JScrollPane(agentSelector));
			jsp.setBottomComponent(mainLogs);

			JButton logButtons=new JButton("Clear All");

			final Runnable runclear=new Runnable(){
				public void run(){
					synchronized(getInstance()){
						logsBuffer.clear();
						logs.setText("");
						agentsCombo.clear();			
					};	
				};	
			};								

			logButtons.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {

					new Thread(runclear).start();
				}});

			mainLogs.add(logsScrollPane, BorderLayout.CENTER);
			JPanel filterPanel=new JPanel(new FlowLayout());
			filterPanel.add(new JLabel("Filter:"));
			filterPanel.add(logsFilter);
			mainLogs.add(filterPanel,BorderLayout.NORTH);
			mainLogs.add(logButtons,BorderLayout.SOUTH);

			agentSelector.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent arg0) {
					if (agentSelector.getSelectedIndex()!=-1){
						currentAgentLogged=agentSelector.getSelectedValue().toString();
						Vector<String> clogs=logsBuffer.get(currentAgentLogged);
						logs.setText("");
						for (String i: clogs){
							logs.append(i+"\n");
						}
					}					
				}
			});

			logsFilter.addKeyListener(new KeyListener(){

				public void actionPerformed(ActionEvent e) {	

				}

				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode()==KeyEvent.VK_ENTER){
						synchronized (getInstance()){
							logs.setText("");
							String filter=logsFilter.getText().toLowerCase();
							Set<String> keys = logsBuffer.keySet();
							Vector<String> tvalues=new Vector<String>(); 
							for (String key:keys){
								Vector<String> value = logsBuffer.get(key);
								boolean validLines=key.toLowerCase().indexOf(filter)>=0;
								for (String line:value){
									if (validLines || line.toLowerCase().indexOf(filter)>=0){
										tvalues.add(line+":"+key);
									}
								}				

							}
							Object[] toallocate=tvalues.toArray();
							Sort.quicksort(toallocate, new Compare(){

								public int doCompare(Object arg0, Object arg1) {
									return arg0.toString().compareTo(arg1.toString());
								}});
							for (Object line:toallocate){
								logs.append(line+"\n");
							}

						}
					}

				}

				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub

				}

				public void keyTyped(KeyEvent arg0) {

				}

			});
		}


	}

	public static void main(String args[]) {
		JPanel jp = new JPanel();
		jp.add(new JLabel("Funciona"));
		getInstance().addAgentInteractionManager(new JPanel(), "hola");
		getInstance().addAgentInteractionManager(new JPanel(), "adios");
		//getInstance().addAgentInteraction(new JPanel(), "i1","role1");
		//getInstance().addAgentInteraction(jp, "i2","role2");
	}

	public boolean isAutomatic(){
		return this.automatic;
	}

	public long getDelay(){
		return delay;
		/*try {
			return Long.parseLong(this.runDelay.getText());
		} catch (java.lang.NumberFormatException ne){
			return 0;
		}*/
	}


	private void runWithoutInterruptionActionPerformed(ActionEvent evt) {	
		automatic=true;
	}

	private void runStepActionPerformed(ActionEvent evt) {
		automatic=false;
	}

	public static void goAutomatic(){
		getInstance().automatic=true;
		if (IAFProperties.getGraphicsOn()){
			synchronized(getInstance()){
				getInstance().runStep.setEnabled(true);
				getInstance().runWithoutInterruption.setEnabled(false);
			}
		}
	}

	public static void goManual(){
		getInstance().automatic=false;		
		if (IAFProperties.getGraphicsOn()){
			synchronized(getInstance()){
				getInstance().runStep.setEnabled(false);
				getInstance().runWithoutInterruption.setEnabled(true);
			}
		}
	}



}