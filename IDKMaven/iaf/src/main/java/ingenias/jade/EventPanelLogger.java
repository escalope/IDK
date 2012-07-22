package ingenias.jade;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sun.misc.Compare;
import sun.misc.Sort;

import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.jade.comm.ActiveConversation;
import ingenias.jade.components.Task;
import jade.lang.acl.ACLMessage;

public class EventPanelLogger extends JSplitPane {
	JTextArea logs=new JTextArea();
	
	JScrollPane logsScrollPane = new JScrollPane();
	JTextField logsFilter=new JTextField(40);
	private String currentAgentLogged="";
	private FileOutputStream logFile=null;
	Hashtable<String,Vector<String>> logsBuffer=new Hashtable<String,Vector<String>>();
	DefaultListModel agentsCombo=new DefaultListModel(); 

	private final static int MaxEntriesPerKey=100;

	JList agentSelector=new JList(agentsCombo);
	JPanel logsPanel=new JPanel();
	public EventPanelLogger(){
		this.setOrientation(JSplitPane.VERTICAL_SPLIT);
		logsPanel.setLayout(new BorderLayout());
		this.logsScrollPane.getViewport().add(this.logs,null); 
		this.logsScrollPane.setAutoscrolls(true);			
		this.logs.setAutoscrolls(true);
		logs.setEditable(false);
		logs.setWrapStyleWord(true);
		logs.setLineWrap(true);
		JButton logButtons=new JButton("Clear All");

		final Runnable runclear=new Runnable(){
			public void run(){
				logsBuffer.clear();
				logs.setText("");
				agentsCombo.clear();				
			};	
		};								

		logButtons.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(runclear);
			}});

		logsPanel.add(logsScrollPane, BorderLayout.CENTER);
		JPanel filterPanel=new JPanel(new FlowLayout());
		filterPanel.add(new JLabel("Filter:"));
		filterPanel.add(logsFilter);
		logsPanel.add(filterPanel,BorderLayout.NORTH);
		logsPanel.add(logButtons,BorderLayout.SOUTH);

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
					Runnable runFilter=new Runnable(){
						public void run(){
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
					};
					SwingUtilities.invokeLater(runFilter);
				}
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent arg0) {}
		});	

		this.setTopComponent(new JScrollPane(agentSelector));
		this.setBottomComponent(logsPanel);
		EventManager.getInstance().register(new EventPaneLogsListener(this));
	}

	public synchronized String findLogs(String cid) {
		Vector<String> result=new Vector<String>();
		Set<String> keys = logsBuffer.keySet();		
		for (String key:keys){
			if (key.indexOf("-"+cid)>=0){
				Vector<String> logsAgent = logsBuffer.get(key);
				Vector<String> lagents=new Vector<String>();				
				for (String alog:logsAgent){
					lagents.add(alog);
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


	private String getCurrentAgentLogged() {
		return this.currentAgentLogged;
	}


	public synchronized void log(final String message, final String agent){
		java.util.Date cdate = new java.util.Date();
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("HH:mm");
		java.text.SimpleDateFormat dflog = new java.text.SimpleDateFormat("HH:mm:ss:SS");
		String sb = df.format(cdate);
		String sbl = dflog.format(cdate);
		if (!logsBuffer.containsKey(agent)){
			logsBuffer.put(agent,new Vector<String>());
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					int k=0;
					while (k<agentsCombo.getSize() && 
							agentsCombo.getElementAt(k).toString().compareTo(agent)<0){
						k++;
					}

					agentsCombo.insertElementAt(agent,k);		

				}
			});

		}
		Vector<String> lastEntered = logsBuffer.get(agent);
		if (lastEntered.size()==0 || lastEntered.lastElement().indexOf(message)<0){
			if (lastEntered.size()>=MaxEntriesPerKey)
				lastEntered.removeElementAt(0);
			String _message=message;
			if (message.length()>400){
			 _message=message.substring(0,500)+"...";
			}
			if (getCurrentAgentLogged().equals(agent)){
				
				logs.append("["+sbl.toString()+"-"+agent+"]  "+_message+"\n");				
			}
			lastEntered.add("["+sbl.toString()+"-"+agent+"]  "+_message); // To avoid repeated messages
			

		}
	}


}
