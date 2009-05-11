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


import ingenias.editor.IDEState;
import ingenias.editor.Model;
import ingenias.editor.ModelJGraph;
import ingenias.editor.entities.Entity;
import ingenias.editor.entities.MentalEntity;
import ingenias.editor.panels.AgentModelPanel;
import ingenias.jade.components.Task;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.jgraph.graph.GraphConstants;

public class AgentGraphics {
	public static javax.swing.ButtonGroup bg=new javax.swing.ButtonGroup();
	private Box taskBox=null; // This box contains current active tasks
	private Box appBox=null; // This box contains current known apps
	Vector taskButtons = new Vector();	
	private Hashtable taskpanels=new Hashtable();	
	APanel eventDiagram=null;
	Vector events=null;
	JPanel generateEventPanel=null;
	Box epb=null; 
	Box epanel=null;
	private Box interationbox;
	private JPanel interactionPanel;
	private JPanel taskPanel;
	private JList knownTasks;
	private DefaultListModel dlm=new DefaultListModel();
	private JCheckBox enableBreakpoint;

	protected class APanel extends JPanel{
		private  IDEState ids;
		AgentModelPanelIAF diagram = null;
		public APanel(MentalEntity event) {
			IDEState ids = IDEState.emptyIDEState();
			diagram = new AgentModelPanelIAF(
					new ingenias.editor.entities.AgentModelDataEntity("1"), 
					"1", new Model(ids));

			this.insert(diagram, event);
			this.add(diagram);		
		}

		private void insert(AgentModelPanelIAF ed, MentalEntity event) {
			Entity ent = event;
			int k=ModelJGraph.findEmptyPlace(ed);

			if (ent==null)
				throw new RuntimeException();
			org.jgraph.graph.DefaultGraphCell cell=ed.insertDuplicated(new Point(k, 30), ent);

			/*Enumeration enumeration = expectedInputs.elements();
			while (enumeration.hasMoreElements()) {
				Entity ent = (Entity) enumeration.nextElement();
				int k=ModelJGraph.findEmptyPlace(ed.getGraph());
				System.err.println("inserting " + ent);
				if (ent==null)
					throw new RuntimeException();
				org.jgraph.graph.DefaultGraphCell cell=ed.insertDuplicated(new Point(k, 30), ent);

			}*/
		}
	}

	protected class TPanel
	implements java.awt.event.ActionListener {
		private  IDEState ids;

		AgentModelPanelIAF input = null;

		AgentModelPanelIAF output = null;

		JPanel tpanel = new JPanel(new GridLayout(2, 1, 5, 5));

		public TPanel(Task t) {

			input = new AgentModelPanelIAF(
					new ingenias.editor.entities.AgentModelDataEntity("1"), 					
					"1",  new Model(ids));

			input.setMarqueeHandler(new AgentModelMarqueeHandlerIAF(input));

			Vector inputs=t.getInputs();			
			this.insert(input, inputs);
			tpanel.add(input);
			ids = IDEState.emptyIDEState();
			output = new AgentModelPanelIAF(
					new ingenias.editor.entities.AgentModelDataEntity("1"), "1", new Model(ids));
			output.setMarqueeHandler(new AgentModelMarqueeHandlerIAF(output));
			HashSet<MentalEntity> entitiesForMS = t.getOutputs().firstElement().getNewEntitiesMS();
			HashSet<MentalEntity> entitiesForWF = t.getOutputs().firstElement().getNewEntitiesWF();
			Vector entitiesOutput=new Vector();
			entitiesOutput.addAll(entitiesForMS);
			entitiesOutput.addAll(entitiesForWF);
			this.insert(output,entitiesOutput);
			Border border1 = BorderFactory.createLineBorder(Color.black, 2);
			TitledBorder tb = new TitledBorder(border1, "Inputs");
			input.setBorder(tb);
			border1 = BorderFactory.createLineBorder(Color.black, 2);
			tb = new TitledBorder(border1, "Expected Output");
			output.setBorder(tb);
			tpanel.add(output);
			border1= BorderFactory.createLineBorder(Color.black, 2);
			TitledBorder nametask = new TitledBorder(border1,t.getID());
			tpanel.setBorder(nametask);

		}

		private void insert(AgentModelPanelIAF input2, Vector expectedInputs) {
			Enumeration enumeration = expectedInputs.elements();			
			while (enumeration.hasMoreElements()) {

				ingenias.editor.entities.Entity ent =
					(ingenias.editor.entities.Entity) enumeration.nextElement();			
				int k=ModelJGraph.findEmptyPlace(input2);

				if (ent==null)
					throw new RuntimeException();
				org.jgraph.graph.DefaultGraphCell dgc=input2.insertDuplicated(new Point(k, 30), ent);
				Map attrs=dgc.getAttributes();

				Rectangle2D bounds=GraphConstants.getBounds(attrs);
				if (bounds==null)
					bounds=new Rectangle(k,30,200,100);
				else
					bounds=
						new Rectangle((int)bounds.getX(),(int)bounds.getY(),
								(int)Math.min(200,bounds.getWidth()),
								(int)Math.min(100,bounds.getHeight()));
				GraphConstants.setBounds(attrs,bounds);
				Hashtable changes =new Hashtable();
				changes.put(dgc,attrs);
				//dgc.setAttributes(attrs);
				input2.getModel().edit(changes,null,null,null);

			}
		}

		public void actionPerformed(ActionEvent e) {
			MainInteractionManager.setMainTask(tpanel);
		}
	};


	public AgentGraphics(String aname) {
		this.taskBox=Box.createVerticalBox();
		this.appBox=Box.createVerticalBox();
		this.interationbox = Box.createVerticalBox();
		taskPanel = new JPanel(new BorderLayout());
		taskPanel.add(taskBox,BorderLayout.CENTER);
		interactionPanel = new JPanel(new BorderLayout());
		interactionPanel.add(interationbox,BorderLayout.CENTER);
		epanel=Box.createVerticalBox();

		JPanel breapPointsPanel=new JPanel(new BorderLayout()); 
		knownTasks=new JList(dlm);		
		knownTasks.setVisibleRowCount(5);
		knownTasks.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		breapPointsPanel.add(knownTasks,BorderLayout.CENTER);
		enableBreakpoint=new JCheckBox();
		JLabel enableBreakpointL=new JLabel("Enable Breakpoint:");
		JPanel topPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(enableBreakpointL);
		topPanel.add(enableBreakpoint);
		breapPointsPanel.add(topPanel,BorderLayout.NORTH);
		
		JScrollPane jsp=new JScrollPane(breapPointsPanel); 

		taskBox.add(jsp);
		MainInteractionManager.addAgentInteractionManager(interactionPanel,aname);
		MainInteractionManager.addAgentApplication(epanel,aname);   
		MainInteractionManager.addAgentProgrammedTask(taskPanel,aname);
		epanel.add(new JLabel("Agent perceptions"));

	}

	protected Box getTaskBox() {
		return taskBox;
	}

	protected void setTaskBox(Box taskBox) {
		this.taskBox = taskBox;
	}

	public synchronized void removeTaskPanel(Task t){
		if ((Component)this.taskpanels.get(t)!=null){
			this.getTaskBox().remove((Component)this.taskpanels.get(t));
			MainInteractionManager.refresh();
		}
	}

	public void addTask(final Task t, String name, ActionListener al) {				
		final JButton jb = new JButton("Execute " + name+":"+t.getType());
		final javax.swing.JRadioButton rb=new javax.swing.JRadioButton();
		final JPanel tpanel=new JPanel();
		tpanel.add(jb);
		tpanel.add(rb);
		bg.add(rb); // to avoid two radio buttons active at the same time
		rb.addActionListener(new TPanel(t));
		this.taskpanels.put(t,tpanel);
		jb.addActionListener(al);
		this.taskBox.add(tpanel);
		MainInteractionManager.refresh();

	}

	public void setMentalStatePanel(String agentName, AgentModelPanel amm ) {
		Border border1 = BorderFactory.createLineBorder(Color.black, 2);
		TitledBorder nametask = new TitledBorder(border1,agentName);
		amm.setBorder(nametask);
		//ids.editor.changeGraph(amm);
		final JScrollPane ammpanel = new JScrollPane();
		ammpanel.getViewport().add(amm, null);
		final JPanel mspanel = new JPanel();
		JButton jb = new JButton("Show mental state");
		jb.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainInteractionManager.setMainMS(ammpanel);
			}
		});
		mspanel.add(jb);
		MainInteractionManager.addAgentMentalState(mspanel, agentName);		
	}

	public void addInteraction(String aname,String intName,ActionListener ifpressed){		   

		JButton jb=null;		   
		jb=new JButton(intName);
		interationbox.add(jb);		     
		jb.addActionListener(ifpressed);		     
		// To inform the user that this agent will not start any interaction
		if ( interationbox.getComponentCount()==0){
			JLabel jl=new JLabel("DOES NOT START ANY INTERACTION");
			jl.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			interationbox.add(jl);
		}
		// once the panel is initialized, it is added to the main panel where
		// other agents show their interactions as well

		// The rest of the code contains the behaviors responsible of interaction
		// management

		// This behavior takes incoming acl messages and distributes them into two 
		// groups: messages being processed and messages not being processed.
		// Messages being processed are added again to the messages queue so that
		// they can be processed by a specialized behavior

	}


	public void addApplication(String idapp, 
			Vector<MentalEntity> events,
			Vector<ActionListener> actions){
		JLabel alabel = new JLabel(" "+idapp+" ");
		// One horizontal panel per application   
		Box epblabelanddiag = Box.createHorizontalBox();
		epblabelanddiag.add(alabel);
		epanel.add(epblabelanddiag);
		// One vertical panel per perceives relationship
		// Each perception relationship provides info about
		// a single event
		epb=Box.createVerticalBox();
		epblabelanddiag.add(epb);

		for (int k=0;k<events.size();k++){

			MentalEntity event=events.elementAt(k);
			ActionListener al=actions.elementAt(k);
			// One vertical panel per perceives relationship
			// Each perception relationship provides info about
			// a single event
			epb=Box.createVerticalBox();
			epblabelanddiag.add(epb);
			// Events perceived per application
			JButton generateEvent=new JButton("Generate");
			generateEvent.addActionListener(al);
			generateEventPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
			generateEventPanel.add(generateEvent);  
			eventDiagram=new APanel(event);		
			epb.add(eventDiagram);   
			epb.add(generateEventPanel);


		}

	}


	public void runtimeWarning(String warning){
		JOptionPane.showMessageDialog(null,warning,"warning",JOptionPane.WARNING_MESSAGE);
	}

	public void startAgentDebug(){
//		if (epanel!=null) {
//		MainInteractionManager.addAgentApplication(epanel,aname);   
//		}

		JButton tbutton=null;		  
		// once the panel is initialized, it is added to the main panel where
		// other agents show their interactions as well

		// To inform the user that this agent will not start any interaction
		if ( interationbox.getComponentCount()==0){
			JLabel jl=new JLabel("DOES NOT START ANY INTERACTION");
			jl.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			interationbox.add(jl);
		}

	}

	public void setKnownTasks(Vector<String> tasks){
		for (String task:tasks)
		 dlm.addElement(task);
		knownTasks.validate();
	}
	
	public Vector<String> getSelectedTaskBreakpoints() {
		// TODO Auto-generated method stub
		
		Object[] values=this.knownTasks.getSelectedValues();
		Vector<String> result=new Vector<String>();
		
		if (values!=null && enableBreakpoint.getModel().isSelected()){
			for (int k=0;k<values.length;k++){
				result.add(values[k].toString());
			}
		}
		return result;
	}

}
