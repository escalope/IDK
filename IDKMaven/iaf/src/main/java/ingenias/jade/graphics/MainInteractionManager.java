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

import ingenias.editor.DraggableTabbedPane;
import ingenias.editor.JTabbedPaneWithCloseIcons;
import ingenias.jade.EventManager;
import ingenias.jade.ChartStatsManager;
import ingenias.jade.EventPanelLogger;
import ingenias.jade.MentalStateProcessor;
//import ingenias.jade.EventPanelLogger;
import ingenias.jade.IAFProperties;
import ingenias.testing.MSPRepository;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sun.misc.Compare;
import sun.misc.Sort;

public class MainInteractionManager
extends javax.swing.JFrame {
	static DraggableTabbedPane tabpaneMentalStateManager=new DraggableTabbedPane();
	private static MainInteractionManager mim = new MainInteractionManager();
	private static Hashtable intsPanel = new Hashtable();
	private JTabbedPane jtp = new DraggableTabbedPane();
	JPanel mainPanel = new JPanel(new BorderLayout());
	private JButton runStep;
	private JLabel jLabel1;
	private JTextField runDelay;
	private JButton runWithoutInterruption;
	private JPanel jPanel1;
	private String secondMonitor = "secondmonitor";
	JPanel mainInteractions = new JPanel();
	JSplitPane mainTasks = new JSplitPane();
	JSplitPane mainMentalState = new JSplitPane();
	
	JPanel mainApplications = new JPanel(new BorderLayout());
	JScrollPane imcontainerScrollPane = new JScrollPane();
	JScrollPane mstcontainerscrollpane = new JScrollPane();
	JScrollPane taskcontainerScrollPane = new JScrollPane();
	JScrollPane appcontainerScrollPane = new JScrollPane();
	//Hashtable<String,Vector<String>> logsBuffer=new Hashtable<String,Vector<String>>();
	private boolean automatic = true;
	private long delay = 0;
	private int currentCount = 0;
	private static int agentCounter = 0;
	Box imcontainer;
	Box taskcontainer;
	Box mstcontainer;
	Box appcontainer;
	JScrollPane interactionsScrollPane = new JScrollPane();
	Box interactions;
	public static double scalex = 1;
	public static double scaley = 1;
	private static boolean logsOn = false;

	static {
		if (IAFProperties.getGraphicsOn()) {
			mim.pack();
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

			/*mim.setLocation(
					d.width / 2 - mim.getSize().width / 2,
					d.height / 2 - 100);
*/
			mim.setLocationByPlatform(true);
			mim.setVisible(true);
		
			mim.setMainTask(new JPanel());
			//mim.setMainMS(new JScrollPane());
		}
	}

	public static MainInteractionManager getInstance() {
		return mim;
	}

	public static void addAgentInteraction(final JPanel jp, final String agent, final String cid, final String role, final String protocol) {
		if (IAFProperties.getGraphicsOn()) {
			Runnable run = new Runnable() {

				public void run() {
					getInstance().imcontainer.add(jp);
					agentCounter++;
					VertizalZoomableBox panel = null;
					if (intsPanel.containsKey(cid)) {
						panel = (VertizalZoomableBox) intsPanel.get(cid);
					} else {
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
								JFrame logwindow = new JFrame();

								JTextArea contentWidget = new JTextArea();
								contentWidget.setWrapStyleWord(true);
								contentWidget.setLineWrap(true);
								JScrollPane jscr = new JScrollPane(contentWidget);
								contentWidget.setText(getInstance().mainLogs.findLogs(cid));
								contentWidget.setEditable(false);
								logwindow.getContentPane().add(jscr);
								logwindow.pack();
								logwindow.setVisible(true);
							}
						});

						Border border1 = BorderFactory.createLineBorder(Color.black, 2);
						TitledBorder tb = new TitledBorder(border1, cid + "-" + role + ":" + protocol);
						panel.setBorder(tb);
						spanel.add(panel, BorderLayout.CENTER);
						Box wpanel = Box.createVerticalBox();
						wpanel.add(jb);
						wpanel.add(logInt);
						spanel.add(wpanel, BorderLayout.WEST);
						getInstance().interactions.add(spanel);
					}
					JPanel ncontainerPanel=new JPanel(new BorderLayout());
					ncontainerPanel.add(jp,BorderLayout.CENTER);
					JButton logInt = new JButton("LocalLog");
					logInt.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							JFrame logwindow = new JFrame();

							JTextArea contentWidget = new JTextArea();
							contentWidget.setWrapStyleWord(true);
							contentWidget.setLineWrap(true);
							JScrollPane jscr = new JScrollPane(contentWidget);
							contentWidget.setText(getInstance().mainLogs.findLogs(role+"-"+cid ));
							contentWidget.setEditable(false);
							logwindow.getContentPane().add(jscr);
							logwindow.pack();
							logwindow.setVisible(true);
						}
					});
					ncontainerPanel.add(logInt,BorderLayout.WEST);
					panel.add(ncontainerPanel);
					JFrame jf;
					getInstance().repaint();
				}
			};
			SwingUtilities.invokeLater(run);
		}


	}

	public static void setMainTask(final JPanel jp) {
		if (IAFProperties.getGraphicsOn()) {
			Runnable run = new Runnable() {

				public void run() {
					getInstance().mainTasks.add(jp, JSplitPane.RIGHT);
				}
			};

			SwingUtilities.invokeLater(run);
		}
	}

	public static void setMainMS(final String agentName,final JScrollPane jp) {
		if (IAFProperties.getGraphicsOn()) {
			synchronized (getInstance()) {
				Runnable run = new Runnable() {

					public void run() {
						if (tabpaneMentalStateManager.indexOfTab(agentName)>=0){
							tabpaneMentalStateManager.setSelectedIndex(tabpaneMentalStateManager.indexOfTab(agentName));
						} else
						 tabpaneMentalStateManager.addTabWithCloseIcon(agentName, jp);
						//getInstance().mainMentalState.add(jp, JSplitPane.RIGHT);
					}
				};
				SwingUtilities.invokeLater(run);
			}
		}
	}

	public static void addAgentInteractionManager(final JPanel jp, final String agent) {
		if (IAFProperties.getGraphicsOn()) {
			Runnable run = new Runnable() {

				public void run() {
					Border border1 = BorderFactory.createLineBorder(Color.black, 2);
					TitledBorder tb = new TitledBorder(border1, agent);
					TitledBorder tasks = new TitledBorder(border1, agent);
					jp.setBorder(tb);
					getInstance().imcontainer.add(jp);
					getInstance().validate();
					getInstance().interactionsScrollPane.validate();
					getInstance().repaint();
				}

				;
			};
			SwingUtilities.invokeLater(run);


		}
	}

	public static void addAgentProgrammedTask(final JPanel task1, final String agent1) {
		if (IAFProperties.getGraphicsOn()) {
			Runnable run = new Runnable() {

				public void run() {
					final JPanel task = task1;
					final String agent = agent1;


					Border border1 = BorderFactory.createLineBorder(Color.black, 2);
					TitledBorder tb = new TitledBorder(border1, agent);
					task.setBorder(tb);

					getInstance().taskcontainer.add(task);

					//getInstance().validate();

					getInstance().taskcontainerScrollPane.validate();

					getInstance().repaint();
				}

				;
			};
			SwingUtilities.invokeLater(run);
		}


	}

	public static void addAgentApplication(final Box apps, final String agent) {
		if (IAFProperties.getGraphicsOn()) {
			Runnable run = new Runnable() {

				public void run() {
					try {

						Border border1 = BorderFactory.createLineBorder(Color.black, 2);
						TitledBorder tb = new TitledBorder(border1, agent);
						apps.setBorder(tb);

						getInstance().appcontainer.add(apps);

						getInstance().validate();

						getInstance().appcontainerScrollPane.validate();

						getInstance().repaint();

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

				;
			};
			SwingUtilities.invokeLater(run);
		}

	}
	/*
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
    }*/

	public static void setLogsOn(boolean logsOn) {
		MainInteractionManager.logsOn = logsOn;
	}

	/*public static void log(final String message, final String agent){

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
    MainInteractionManager.getInstance().logFile.write((cdate.getTime()+"|").getBytes());
    MainInteractionManager.getInstance().logFile.write((agent+"|").getBytes());
    MainInteractionManager.getInstance().logFile.write((message+"|").getBytes());
    MainInteractionManager.getInstance().logFile.write(("\n").getBytes());
    } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    }
    }



    }

    }*/
	public static void addAgentMentalState(final JPanel mentalstate, final String agent) {
		if (IAFProperties.getGraphicsOn()) {
			//			synchronized(getInstance()){

			SwingUtilities.invokeLater(new Runnable() {

				public void run() {

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

	public static void refresh() {
		getInstance().repaint();
	}
	private EventPanelLogger mainLogs;


	public MainInteractionManager() {

		try {

			jbInit();

			this.setSize(553, 278);
			java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("MMddyyHHmm");
			Date d = new Date();
			/*		if (IAFProperties.getLogFileOn()){
            new File("logs").mkdir();
            this.logFile=new FileOutputStream("logs/log"+df.format(d)+".log");
            }*/

			setLocationByPlatform(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		if (IAFProperties.getGraphicsOn()) {
			tabpaneMentalStateManager=new DraggableTabbedPane();
			
			imcontainer = Box.createVerticalBox();
			JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			this.taskcontainer = Box.createVerticalBox();
			this.mstcontainer = Box.createVerticalBox();
			this.appcontainer = Box.createVerticalBox();
			interactions = Box.createVerticalBox();
			mainLogs = new EventPanelLogger();
			this.setTitle("Main Interaction Manager");
			this.getContentPane().setLayout(new BorderLayout());
			imcontainerScrollPane.setAutoscrolls(false);
			//mainInteractions.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			//mainInteractions.setDividerLocation(100);
			//this.mainApplications.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			//this.mainApplications.setDividerLocation(100);

			//mainPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
			mainPanel.add(jtp, BorderLayout.CENTER);

			//mainPanel.add(this.logsScrollPane,JSplitPane.BOTTOM);
			//mainPanel.setDividerLocation(200);



			this.getContentPane().add(mainPanel, BorderLayout.CENTER);

			jPanel1 = new JPanel();
			this.getContentPane().add(jPanel1, BorderLayout.NORTH);
			{
				runDelay = new JTextField();
				runDelay.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						try {
							delay = Long.parseLong(runDelay.getText());
						} catch (java.lang.NumberFormatException ne) {
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


			jtp.add("tasks", mainTasks);
			jtp.addTab("interactions", null, interactionsScrollPane, null);

			ChartStatsManager es = new ChartStatsManager("Stats");



			jtp.add("Mental State", mainMentalState);
			jtp.add("Applications", this.mainApplications);
			jtp.add("Logs", mainLogs);
			jtp.addTab("Statistics", es.getStatsPanel());
			

			JButton zoom = new JButton("Zoom -");
			zoom.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					scalex = scalex - 0.05;
					scaley = scaley - 0.025;
					interactions.repaint();
				}
			});
			jPanel1.add(zoom);
			JButton unzoom = new JButton("Zoom +");
			unzoom.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					scalex = scalex + 0.05;
					scaley = scaley + 0.025;
					interactions.repaint();
				}
			});
			jPanel1.add(unzoom);


			//mainInteractions.add(imcontainerScrollPane, JSplitPane.LEFT);
			//mainInteractions.add(interactionsScrollPane, JSplitPane.RIGHT);
			this.mainMentalState.add(this.mstcontainerscrollpane, JSplitPane.LEFT);
			this.mainMentalState.add(MainInteractionManager.tabpaneMentalStateManager, JSplitPane.RIGHT);			
			this.mainTasks.add(this.taskcontainerScrollPane, JSplitPane.LEFT);
			this.mainApplications.add(this.appcontainerScrollPane, BorderLayout.CENTER);
			interactionsScrollPane.getViewport().add(interactions, null);
			imcontainerScrollPane.getViewport().add(imcontainer, null);
			mstcontainerscrollpane.getViewport().add(mstcontainer, null);
			taskcontainerScrollPane.getViewport().add(taskcontainer, null);
			this.appcontainerScrollPane.getViewport().add(this.appcontainer, null);
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

	public boolean isAutomatic() {
		return this.automatic;
	}

	public long getDelay() {
		return delay;
		/*try {
        return Long.parseLong(this.runDelay.getText());
        } catch (java.lang.NumberFormatException ne){
        return 0;
        }*/
	}

	private void runWithoutInterruptionActionPerformed(ActionEvent evt) {
		automatic = true;
		System.out.println("waked up all1");

		Vector<MentalStateProcessor> msps =
			MSPRepository.getInstance().getAllRegisteredMSP();
		for (MentalStateProcessor msp : msps) {
			msp.doReplan();
		}
		System.out.println("waked up all");
	}

	private void runStepActionPerformed(ActionEvent evt) {
		automatic = false;
		Vector<MentalStateProcessor> msps =
			MSPRepository.getInstance().getAllRegisteredMSP();
		for (MentalStateProcessor msp : msps) {
			msp.doReplan();
		}
	}

	public static void goAutomatic() {
		getInstance().automatic = true;
		if (IAFProperties.getGraphicsOn()) {
			synchronized (getInstance()) {
				getInstance().runStep.setEnabled(true);
				getInstance().runWithoutInterruption.setEnabled(false);
			}
		}
		getInstance().runWithoutInterruptionActionPerformed(null);
	}

	public static void goManual() {
		getInstance().automatic = false;
		if (IAFProperties.getGraphicsOn()) {
			Runnable run=new Runnable(){
				public void run(){
					//synchronized (getInstance()) {
					getInstance().runStep.setEnabled(false);
					getInstance().runWithoutInterruption.setEnabled(true);
					//}		
				}
			};
			SwingUtilities.invokeLater(run);

		}
		// getInstance().runStepActionPerformed(null);
	}
}
