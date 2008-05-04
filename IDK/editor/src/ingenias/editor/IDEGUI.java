/*
 Copyright (C) 2002 Jorge Gomez Sanz, Ruben Fuentes, Juan Pavon

 This file is part of INGENIAS IDE, a support tool for the INGENIAS
 methodology, availabe at http://grasia.fdi.ucm.es/ingenias or
 http://ingenias.sourceforge.net

 INGENIAS IDE is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 INGENIAS IDE is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with INGENIAS IDE; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 */

package ingenias.editor;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import java.awt.dnd.*;
import ingenias.editor.widget.DnDJTree;



public class IDEGUI extends javax.swing.JFrame  {

	JMenuBar jMenuBar1 = new JMenuBar();
	JMenu file = new JMenu();
	JMenuItem save = new JMenuItem();
	JMenuItem load = new JMenuItem();
	ButtonGroup viewSelection= new ButtonGroup();
	ButtonGroup relationshipSelection= new ButtonGroup();
	ButtonGroup propertiesEditModeSelection= new ButtonGroup();	

	BorderLayout borderLayout2 = new BorderLayout();
	JSplitPane jSplitPane1 = new JSplitPane();
	JSplitPane jSplitPane2 = new JSplitPane();
	JScrollPane jScrollPane2 = new JScrollPane();
	javax.swing.tree.DefaultMutableTreeNode rootProject=
		new javax.swing.tree.DefaultMutableTreeNode("Project");

	JScrollPane jScrollPane1 = new JScrollPane();
	JPanel jPanel1 = new JPanel();
	javax.swing.tree.DefaultMutableTreeNode rootObjetos=
		new javax.swing.tree.DefaultMutableTreeNode("System Objects");
	JTree arbolObjetos = new JTree(rootObjetos);

	BorderLayout gridLayout1 = new BorderLayout();
	Border border1;
	TitledBorder titledBorder1;
	Border border2;
	TitledBorder titledBorder2;
	DnDJTree arbolProyectos = new   DnDJTree(jScrollPane2,rootProject);//rootProject);
	private JRadioButtonMenuItem fullinforelats;
	private JRadioButtonMenuItem labelsonly;
	private JMenu jMenu3;
	private JPanel jPanel2;
	private JButton Search;
	protected JTextField searchField;
	private JPanel searchPanel;
	protected JCheckBoxMenuItem editPopUpProperties;
	protected JRadioButtonMenuItem editOnMessages;
	private JMenu jMenu2;
	private JMenu jMenu1;
	private JMenuItem elimOverlap;
	private JMenuItem importFile;
	// GraphManager gm=GraphManager.initInstance(rootProject,arbolProyectos);
	private JMenu edit = new JMenu();
	private JMenuItem copyImage = new JMenuItem();
	JMenuItem saveas = new JMenuItem();
	JMenu help = new JMenu();
	JMenuItem manual = new JMenuItem();
	JMenuItem about = new JMenuItem();
	JMenu project = new JMenu();
	JMenuItem copy = new JMenuItem();
	JMenuItem paste = new JMenuItem();
	JMenuItem exit = new JMenuItem();
	JSplitPane jSplitPane3 = new JSplitPane();
	public static JPanel rightPanel=new JPanel();
	JPanel pprin = new JPanel();
	JScrollPane scrollLogs = new JScrollPane();
	TitledBorder titledBorder3;
	JPopupMenu messagesMenu = new JPopupMenu();
	JMenuItem clearMessages = new JMenuItem();
	JMenuItem forcegc = new JMenuItem();
	public static JPanel buttonModelPanel=new JPanel(new java.awt.BorderLayout());
	GridLayout gridLayout2 = new GridLayout();
	JRadioButtonMenuItem noinformationrelats = new JRadioButtonMenuItem();
	JMenu tools = new JMenu();
	JMenu codeGenerator = new JMenu();
	JMenu modules = new JMenu();
	JMenuItem properties = new JMenuItem();
	JTabbedPaneWithCloseIcons messagespane = new JTabbedPaneWithCloseIcons();
	JScrollPane outputpane = new JScrollPane();
	JTextPane moduleOutput = new JTextPane();
	JScrollPane scrolloutput = new JScrollPane();
	JTextPane moutput = new JTextPane();
	JTextPane logs = new JTextPane();
	JMenuItem newProject = new JMenuItem();
	JMenuItem undo = new JMenuItem();
	JMenuItem redo = new JMenuItem();
	JMenuItem delete = new JMenuItem();
	JMenuItem selectall = new JMenuItem();
	JMenuItem cpClipboard = new JMenuItem();
	JMenu preferences = new JMenu();

	JRadioButtonMenuItem  enableUMLView= new JRadioButtonMenuItem();
	JRadioButtonMenuItem  enableINGENIASView= new JRadioButtonMenuItem();
	JMenuItem  switchINGENIASView= new JMenuItem();
	JMenuItem  switchUMLView= new JMenuItem();
	JMenuItem  resizeAll= new JMenuItem();
	JMenuItem  resizeAllDiagrams= new JMenuItem();

	JEditorPane searchDiagramPanel= new JEditorPane();	

	public IDEGUI() {
		// To enable changes in cursor's shape
		this.getGlassPane().addMouseListener(new MouseAdapter(){});

		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void jbInit() throws Exception {
		border1 = BorderFactory.createLineBorder(Color.black,2);
		titledBorder1 = new TitledBorder(border1,"Project view");
		border2 = BorderFactory.createLineBorder(Color.black,2);
		titledBorder2 = new TitledBorder(border2,"Entities view");
		titledBorder3 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(148, 145, 140)),"Messages");
		this.getContentPane().setLayout(borderLayout2);
		file.setText("File");
		save.setEnabled(false);
		save.setText("Save");
		save.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save_actionPerformed(e);
			}
		});
		load.setText("Load");
		load.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				load_actionPerformed(e);
			}
		});
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setJMenuBar(jMenuBar1);
		this.setTitle("INGENIAS Development Kit");
		this.setSize(625, 470);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				this_windowClosed(e);
			}
			public void windowClosing(WindowEvent e) {
				this_windowClosing(e);
			}
		});
		jSplitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		jSplitPane2.setBottomComponent(jScrollPane1);
		jSplitPane2.setTopComponent(jScrollPane2);
		jPanel1.setLayout(gridLayout1);
		arbolObjetos.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				arbolObjetos_mouseClicked(e);
			}
		});
		jScrollPane2.setAutoscrolls(true);
		jScrollPane2.setBorder(titledBorder1);
		jScrollPane1.setBorder(titledBorder2);
		edit.setText("Edit");
		copyImage.setText("Copy diagram as a file");
		copyImage.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				capture_actionPerformed(e);
			}
		});
		saveas.setText("Save as");
		saveas.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				saveas_actionPerformed(e);
			}
		});
		help.setText("Help");
		manual.setText("Tool manual");
		manual.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				manual_actionPerformed(e);
			}
		});
		about.setText("About");
		about.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				about_actionPerformed(e);
			}
		});
		project.setText("Project");
		copy.setText("Copy");
		copy.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				copy_actionPerformed(e);
			}
		});
		paste.setText("Paste");
		paste.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				paste_actionPerformed(e);
			}
		});
		exit.setText("Exit");
		exit.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				exit_actionPerformed(e);
			}
		});
		jSplitPane3.setOrientation(JSplitPane.VERTICAL_SPLIT);
		jSplitPane3.setLastDividerLocation(150);
		pprin.setLayout(new BorderLayout());
		pprin.setPreferredSize(new Dimension(400, 300));
		jSplitPane1.setOrientation(JSplitPane.HORIZONTAL_SPLIT);

		scrollLogs.setBorder(titledBorder3);
		scrollLogs.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				jScrollPane3_keyPressed(e);
			}
		});
		this.clearMessages.setText("Clear");
		clearMessages.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearMessages_actionPerformed(e, (JTextPane)messagesMenu.getInvoker());
			}
		});
		forcegc.setText("Force GC");
		forcegc.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				forcegc_actionPerformed(e);
			}
		});

		tools.setText("Tools");
		codeGenerator.setText("Code Generator");
		modules.setText("Modules");
		this.properties.setText("Properties");
		properties.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				properties_actionPerformed(e);
			}
		});
		moutput.setEditable(false);
		moutput.setSelectionStart(0);
		moutput.setText("");
		moduleOutput.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				moduleOutput_mouseClicked(e);
			}
		});
		moduleOutput.setFont(new java.awt.Font("Monospaced", 0, 11));
		logs.setContentType("text/html");
		logs.setEditable(false);
		logs.setText("");
		logs.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				logs_mouseClicked(e);
			}
		});
		logs.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				logs_componentResized(e);
			}
		});
		newProject.setText("New");
		newProject.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newProject_actionPerformed(e);
			}
		});
		undo.setText("Undo");
		undo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				undo_actionPerformed(e);
			}
		});
		redo.setText("Redo");
		redo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				redo_actionPerformed(e);
			}
		});
		delete.setText("Delete");
		delete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete_actionPerformed(e);
			}
		});
		selectall.setText("Select all");
		selectall.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectall_actionPerformed(e);
			}
		});
		cpClipboard.setText("Copy diagram to clipboard");
		cpClipboard.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cpClipboard_actionPerformed(e);
			}
		});
		preferences.setText("Preferences");

		enableUMLView.setToolTipText("UML view" +
		"instead of its type");
		enableUMLView.setText("Enable UML view from now on");
		enableUMLView.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableUMLView_actionPerformed(e);
			}
		});
		enableINGENIASView.setToolTipText("INGENIAS view");
		enableINGENIASView.setText("Enable INGENIAS view from now on");
		enableINGENIASView.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableINGENIASView_actionPerformed(e);
			}
		});
		
		switchINGENIASView.setToolTipText("Switch to INGENIAS view");
		switchINGENIASView.setText("Switch to INGENIAS view");
		switchINGENIASView.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchINGENIASView_actionPerformed(e);
			}			
		});
		
		switchUMLView.setToolTipText("Switch to UML view");
		switchUMLView.setText("Switch to UML view");
		switchUMLView.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchUMLView_actionPerformed(e);
			}			
		});

		resizeAll.setToolTipText("Resize all");
		resizeAll.setText("Resize all entities within current diagram");
		resizeAll.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resizeAll_actionPerformed(e);
			}
		});

		resizeAllDiagrams.setToolTipText("Resize all diagrams");
		resizeAllDiagrams.setText("Resize all entities within all defined diagram");
		resizeAllDiagrams.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resizeAllDiagrams_actionPerformed(e);
			}
		});

		preferences.add( resizeAll);
		preferences.add( resizeAllDiagrams);
		{
			elimOverlap = new JMenuItem();
			preferences.add(elimOverlap);
			elimOverlap.setText("Eliminate overlap");
			elimOverlap.setAccelerator(KeyStroke.getKeyStroke("F3"));
			elimOverlap.addMenuKeyListener(new MenuKeyListener() {
				public void menuKeyTyped(MenuKeyEvent evt) {
					elimOverlapMenuKeyTyped(evt);
				}
				public void menuKeyReleased(MenuKeyEvent evt) {
					System.out.println("elimOverlap.menuKeyReleased, event="
							+ evt);
					//TODO add your code for elimOverlap.menuKeyReleased
				}
				public void menuKeyPressed(MenuKeyEvent evt) {
					System.out.println("elimOverlap.menuKeyPressed, event="
							+ evt);
					//TODO add your code for elimOverlap.menuKeyPressed
				}
			});
			elimOverlap.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent evt) {
					elimOverlapKeyPressed(evt);
				}
			});
			elimOverlap.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					elimOverlapActionPerformed(evt);
				}
			});
		}
		{
			jMenu1 = new JMenu();
			preferences.add(jMenu1);
			jMenu1.setText("Modelling language");
			jMenu1.add(enableINGENIASView);
			
			viewSelection.add(enableINGENIASView);
			jMenu1.add(enableUMLView);
			viewSelection.add(enableUMLView);
			
			enableINGENIASView.setSelected(true);
			jMenu1.add(switchUMLView);
			jMenu1.add(switchINGENIASView);
		}
		{
			jMenu2 = new JMenu();
			preferences.add(jMenu2);
			jMenu2.setText("Edit Properties Mode");
			{
				editPopUpProperties = new JCheckBoxMenuItem();
				jMenu2.add(editPopUpProperties);
				editPopUpProperties
				.setText("Edit Properties in a PopUp Window");
				editPopUpProperties.setSelected(true);
				editPopUpProperties.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						editPopUpProperties_selected();						
					}
				}	);
				propertiesEditModeSelection.add(editPopUpProperties);
			}
			{
				editOnMessages = new JRadioButtonMenuItem();
				jMenu2.add(editOnMessages);
				editOnMessages.setText("Edit Properties in Messages Panel");
				propertiesEditModeSelection.add(editOnMessages);
				editOnMessages.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						editOnMessages_selected();						
					}
				}	);
			}
		}
		{
		/*	jMenu3 = new JMenu();
			preferences.add(jMenu3);
			jMenu3.setText("Relationships look and feel");
			{
				labelsonly = new JRadioButtonMenuItem();				
				jMenu3.add(labelsonly);
				labelsonly.setText("Only labels");
				relationshipSelection.add(labelsonly);
				labelsonly.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						labelsonlyActionPerformed(evt);
					}
				});
			}
			{
				noinformationrelats = new JRadioButtonMenuItem();
				jMenu3.add(noinformationrelats);
				noinformationrelats.setText("No information");
				relationshipSelection.add(noinformationrelats);
				noinformationrelats.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						noinformationrelatsActionPerformed(evt);
					}
				});
			}
			{
				fullinforelats = new JRadioButtonMenuItem();
				jMenu3.add(fullinforelats);				
				fullinforelats.setText("Full information");
				relationshipSelection.add(fullinforelats);
				fullinforelats.setSelected(true);
				fullinforelats.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						fullinforelatsActionPerformed(evt);
					}
				});
			}*/
		}
		jMenuBar1.add(file);
		jMenuBar1.add(edit);
		jMenuBar1.add(project);
		jMenuBar1.add(modules);
		jMenuBar1.add(preferences);
		jMenuBar1.add(help);
		file.add(newProject);
		file.add(load);
		{
			importFile = new JMenuItem();
			file.add(importFile);
			importFile.setText("Import file");
			importFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					importFileActionPerformed(evt);
				}
			});
		}
		file.add(save);
		file.add(saveas);
		file.addSeparator();
		file.add(exit);
		file.addSeparator();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(buttonModelPanel, BorderLayout.WEST);
		this.getContentPane().add(jSplitPane1, BorderLayout.CENTER);
		rightPanel.add(jSplitPane3,BorderLayout.CENTER);
		jSplitPane1.add(jSplitPane2, JSplitPane.LEFT);
		jSplitPane2.add(jScrollPane2, JSplitPane.TOP);
		{
			jPanel2 = new JPanel();
			BorderLayout jPanel2Layout = new BorderLayout();
			jPanel2.setLayout(jPanel2Layout);
			jSplitPane2.add(jPanel2, JSplitPane.BOTTOM);
			jPanel2.add(jPanel1, BorderLayout.SOUTH);
			jPanel2.add(jScrollPane1, BorderLayout.CENTER);
		}
		jSplitPane1.add(rightPanel, JSplitPane.RIGHT);
		jSplitPane3.add(pprin, JSplitPane.TOP);
		jSplitPane3.add(messagespane, JSplitPane.BOTTOM);
		JScrollPane scrollSearchDiagram=new JScrollPane();
		scrollSearchDiagram.getViewport().add(searchDiagramPanel,null);
		searchDiagramPanel.setContentType("text/html");
		searchDiagramPanel.setEditable(false);


		messagespane.addConventionalTab(scrollLogs,   "Logs");			
		scrollLogs.getViewport().add(logs, null);
		scrolloutput.getViewport().add(this.moduleOutput, null);
		messagespane.addConventionalTab(scrolloutput,  "Module Output");
		messagespane.addConventionalTab(scrollSearchDiagram, "Search");
		scrolloutput.getViewport().add(moduleOutput, null);
		{
			searchPanel = new JPanel();
			FlowLayout searchPanelLayout = new FlowLayout();
			searchPanelLayout.setVgap(1);
			searchPanel.setLayout(searchPanelLayout);
			jPanel1.add(searchPanel, BorderLayout.SOUTH);
			searchPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
			{
				searchField = new JTextField();
				searchPanel.add(searchField);
				searchField.setColumns(15);
				searchField.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent evt) {
						searchFieldKeyTyped(evt);
					}
				});
			}
			{
				Search = new JButton();
				jScrollPane2.setViewportView(arbolProyectos);
				jScrollPane1.setViewportView(arbolObjetos);
				searchPanel.add(Search);

				Search.setIcon(new ImageIcon("images/lense.png"));
				Search.setPreferredSize(new java.awt.Dimension(20, 18));
				Search.setIconTextGap(0);
				Search.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						SearchActionPerformed(evt);
						System.err.println("invocando");
					}
				});
			}
		}
		edit.add(undo);
		edit.add(redo);
		edit.addSeparator();
		edit.add(copy);
		edit.add(paste);
		edit.add(delete);
		edit.add(selectall);
		edit.addSeparator();
		edit.add(copyImage);
		edit.add(cpClipboard);
		help.add(manual);
		help.add(about);
		help.add(forcegc);

		modules.add(tools);
		modules.add(codeGenerator);
		messagesMenu.add(this.clearMessages);
		project.add(    this.properties);

		project.addSeparator();
		jSplitPane1.setDividerLocation(250);
		jSplitPane2.setDividerLocation(250);
		arbolProyectos.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				arbolProyectos_mouseClicked(e);
			}
		});
		jSplitPane3.setDividerLocation(400);
	}
	public void switchUMLView_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void switchINGENIASView_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.err.println("ejec1");
		
	}
	void arbolProyectos_mousePressed(MouseEvent e) {

	}
	void arbolProyectos_mouseReleased(MouseEvent e) {

	}
	void arbolProyectos_mouseEntered(MouseEvent e) {

	}
	void arbolProyectos_mouseExited(MouseEvent e) {

	}
	void arbolProyectos_mouseClicked(MouseEvent e) {

	}

	void arbolObjetos_mouseClicked(MouseEvent e) {

	}

	void save_actionPerformed(ActionEvent e) {

	}

	void load_actionPerformed(ActionEvent e) {

	}


	void capture_actionPerformed(ActionEvent e) {
	};

	void exit_actionPerformed(ActionEvent e) {

	}

	void saveas_actionPerformed(ActionEvent e) {

	}

	void this_windowClosed(WindowEvent e) {

	}

	void this_windowClosing(WindowEvent e) {

	}

	void manual_actionPerformed(ActionEvent e) {

	}



	void copy_actionPerformed(ActionEvent e) {

	}


	void paste_actionPerformed(ActionEvent e) {

	}

	void about_actionPerformed(ActionEvent e) {

	}


	void jScrollPane3_keyPressed(KeyEvent e) {
		/*   System.err.println("pulsado");
		 */
	}

	void logs_componentResized(ComponentEvent e) {
//		System.err.println("pulsado");
		Point p=new Point(0,(int)logs.getSize().getHeight());
		scrollLogs.getViewport().setViewPosition(p);
		/*   Point p=scrollLogs.getViewport().getGraphLayoutCachePosition();
		 p.y=p.y+10;
		 scrollLogs.getViewport().setViewPosition(p);*/
	}

	void clearMessages_actionPerformed(ActionEvent e, JTextPane pane) {

	}



	void properties_actionPerformed(ActionEvent e) {

	}
	void logs_componentMoved(ComponentEvent e) {

	}
	void logs_componentShown(ComponentEvent e) {

	}
	void logs_componentHidden(ComponentEvent e) {

	}
	void logs_mousePressed(MouseEvent e) {

	}
	void logs_mouseReleased(MouseEvent e) {

	}
	void logs_mouseEntered(MouseEvent e) {

	}
	void logs_mouseExited(MouseEvent e) {

	}

	void moduleOutput_mouseClicked(MouseEvent e) {

	}
	void logs_mouseClicked(MouseEvent e) {

	}

	void newProject_actionPerformed(ActionEvent e) {

	}

	void undo_actionPerformed(ActionEvent e) {

	}

	void redo_actionPerformed(ActionEvent e) {

	}

	void delete_actionPerformed(ActionEvent e) {

	}

	void selectall_actionPerformed(ActionEvent e) {

	}

	void cpClipboard_actionPerformed(ActionEvent e) {

	}

	void forcegc_actionPerformed(ActionEvent e) {

	}

	void enableRelatinshipLabels_actionPerformed(ActionEvent e) {

	}
	void enableUMLView_actionPerformed(ActionEvent e) {

	}

	void enableINGENIASView_actionPerformed(ActionEvent e) {

	}

	void resizeAll_actionPerformed(ActionEvent e) {

	};
	void resizeAllDiagrams_actionPerformed(ActionEvent e) {

	}

	void importFileActionPerformed(ActionEvent evt) {
		System.out.println("importFile.actionPerformed, event=" + evt);
		//TODO add your code for importFile.actionPerformed
	}

	void elimOverlapActionPerformed(ActionEvent evt) {
		System.out.println("elimOverlap.actionPerformed, event=" + evt);
		//TODO add your code for elimOverlap.actionPerformed
	}

	void elimOverlapKeyPressed(KeyEvent evt) {
		System.out.println("elimOverlap.keyPressed, event=" + evt);
		//TODO add your code for elimOverlap.keyPressed
	}

	void elimOverlapMenuKeyTyped(MenuKeyEvent evt) {
		System.out.println("elimOverlap.menuKeyTyped, event=" + evt);
		//TODO add your code for elimOverlap.menuKeyTyped
	}



	public void addPropertiesPanel(String name,JPanel jp){
		if (this.messagespane.indexOfTab(name) < 0) {
			//JScrollPane scrolledit=new JScrollPane();		 
			//scrolledit.getViewport().removeAll();
			//scrolledit.getViewport().add(jp,null);
			messagespane.addTab(name,jp);
		} else
			this.messagespane.setSelectedIndex(this.messagespane.indexOfTab(name));
	}
	
	public void changePropertiesPanel(String oldName,String nname){
	 for (int k=0;k<messagespane.getTabCount();k++){
		 if (messagespane.getTitleAt(k).equalsIgnoreCase(oldName)){
			 messagespane.setTitleAt(k,nname);
		 }
	 }
	}

	public boolean isEditPropertiesPopUp(){		 
		return this.editPopUpProperties.isSelected();
	}

	public void focusPropertiesPane(String name){
		if (this.messagespane.indexOfTab(name) >= 0) 			  
			this.messagespane.setSelectedIndex(this.messagespane.indexOfTab(name));
	}

	public void focusSearchPane(){		  			  
		this.messagespane.setSelectedIndex(2);
	}

	public void removePropertiesPane(String name){
		if (this.messagespane.indexOfTab(name) >= 0)
			this.messagespane.removeTabAt(this.messagespane.indexOfTab(name));
	}

	public void SearchActionPerformed(ActionEvent evt) {
		System.out.println("Search.actionPerformed, event=" + evt);
		//TODO add your code for Search.actionPerformed
	}

	public void searchFieldKeyTyped(KeyEvent evt) {
		System.out.println("searchField.keyTyped, event=" + evt);
		//TODO add your code for searchField.keyTyped
	}

	/*public void labelsonlyActionPerformed(ActionEvent evt) {
		System.out.println("labelsonly.actionPerformed, event=" + evt);
		//TODO add your code for labelsonly.actionPerformed
	}

	public void noinformationrelatsActionPerformed(ActionEvent evt) {
		System.out.println("noinformationrelats.actionPerformed, event=" + evt);
		//TODO add your code for noinformationrelats.actionPerformed
	}

	public void fullinforelatsActionPerformed(ActionEvent evt) {
		System.out.println("fullinforelats.actionPerformed, event=" + evt);

	}*/
	
	public void editPopUpProperties_selected() {
		// TODO Auto-generated method stub
		
	};
	public void editOnMessages_selected() {
		// TODO Auto-generated method stub
		
	};

}