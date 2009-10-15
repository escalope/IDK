/*
 *  Copyright (C) 2007 Jorge Gomez Sanz
 *  This file is part of INGENIAS IDE, a support tool for the INGENIAS
 *  methodology, availabe at http://grasia.fdi.ucm.es/ingenias or
 *  http://ingenias.sourceforge.net
 *  INGENIAS Development Kit is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *  INGENIAS Development Kit is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License
 *  along with INGENIAS Development Kit; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package ingenias.module;

import ingenias.editor.ProjectProperty;
import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;
import ingenias.genproject.ProjectGenerator;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.jgraph.JGraph;



import sun.awt.VerticalBagLayout;

/**
 *  This class generates a project folder
 *@author     Jorge Gomez
 *@created    13 of October of 2009
 */
public class IAFProjectCreator extends ingenias.editor.extension.BasicToolImp {

	private JFrame dialog=null;
	private JRadioButton rad1;
	private JRadioButton rad2;
	private JRadioButton rad3;
	private JRadioButton rad4;

	/**
	 *  Initialises the class with a file containing a INGENIAS specification
	 *
	 *@param  file           Path to file containing INGENIAS specification
	 *@exception  Exception  Error accessing any file or malformed XML exception
	 */

	public IAFProjectCreator(String file) throws Exception {
		super(file);
	}

	/**
	 *  Initialises the class giving access to diagrams in run-time
	 **/

	public IAFProjectCreator(Browser browser) throws Exception {
		super(browser);
	}



	/**
	 *  Gets the description of this module
	 *
	 *@return    The description
	 */
	public String getDescription() {
		return "It creates a full project";
	}


	/**
	 *  Gets the name of this module
	 *
	 *@return    The name
	 */
	public String getName() {
		return "IAFProjectCreator";
	}


	/**
	 *  It opens the different files generated under the ingenias/jade/components folder looking
	 *  for specific tags. These tags mark the beginning and the end of the modification
	 */
	public void run() {
		if (dialog==null){


			final JFileChooser chooser=new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // To choose only directories
			dialog=new JFrame();

			dialog.setTitle("Project creation wizard");
			final JTextField directory=new JTextField(50);
			directory.addKeyListener(new KeyListener(){

				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode()==KeyEvent.VK_ENTER){

						createProjectInLocation(directory);	
					}
					
				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyTyped(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			directory.setText(chooser.getCurrentDirectory().toString()+"/");
			JButton browse=new JButton("Browse");
			browse.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					chooser.setDialogType(chooser.OPEN_DIALOG);
					chooser.showOpenDialog(null);
					if (chooser.getSelectedFile()==null)
						JOptionPane.showMessageDialog(dialog, "A directory has to be chosen","No directory selected",JOptionPane.ERROR_MESSAGE);
					else
						directory.setText(chooser.getSelectedFile().toString());
				}

			});
			JButton cancel=new JButton("Cancel");
			cancel.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dialog.setVisible(false);	
					dialog=null;
				}

			});
			
			JPanel buttonPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));			
			ButtonGroup exampleSelGroup=new ButtonGroup();			
			
			JPanel exampleSelection = new JPanel(new GridLayout(5,1));
			GridBagConstraints gbc=new GridBagConstraints();
			JLabel exampleSelectionLabel=new JLabel("Choose a template for the specification:");
			gbc=new GridBagConstraints();
			gbc.gridx=0;
			gbc.gridy=0;
			exampleSelection.add(exampleSelectionLabel);
			
			JPanel option1=new JPanel(new FlowLayout(FlowLayout.LEFT));	
			gbc=new GridBagConstraints();
			gbc.gridx=0;
			gbc.gridy=1;
			rad1 = new javax.swing.JRadioButton();			
			exampleSelGroup.add(rad1);
			option1.add(rad1);
			option1.add(new javax.swing.JLabel("Empty specification"));
			exampleSelection.add(option1);
			
			JPanel option2=new JPanel(new FlowLayout(FlowLayout.LEFT));
			gbc=new GridBagConstraints();
			gbc.gridx=0;
			gbc.gridy=2;
			rad2 = new javax.swing.JRadioButton();
			exampleSelGroup.add(rad2);
			option2.add(rad2);			
			option2.add(new javax.swing.JLabel("Hello world"));
			exampleSelection.add(option2);
			
			JPanel option3=new JPanel(new FlowLayout(FlowLayout.LEFT));
			gbc=new GridBagConstraints();
			gbc.gridx=0;
			gbc.gridy=3;
			rad3 = new javax.swing.JRadioButton();
			exampleSelGroup.add(rad3);
			option3.add(rad3);
			option3.add(new javax.swing.JLabel("Example GUI agent"));
			exampleSelection.add(option3);
			
			JPanel option4=new JPanel(new FlowLayout(FlowLayout.LEFT));
			gbc=new GridBagConstraints();
			gbc.gridx=0;
			gbc.gridy=4;
			rad4 = new javax.swing.JRadioButton();
			exampleSelGroup.add(rad4);
			option4.add(rad4);
			option4.add(new javax.swing.JLabel("Example Interaction"));
			exampleSelection.add(option4);
			
			rad1.setSelected(true);
			
			buttonPanel.add(cancel);
			JButton create=new JButton("Create project in selected location");
			create.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {

					createProjectInLocation(directory);
				}

				

			});
			Box mainPanel = Box.createVerticalBox();
			JPanel folderSelectionPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel directoryLabel=new JLabel("Project folder:");
			folderSelectionPanel.add(directoryLabel);
			folderSelectionPanel.add(directory);
			folderSelectionPanel.add(browse);
			mainPanel.add(folderSelectionPanel);
			buttonPanel.add(create);
			buttonPanel.add(cancel);
			mainPanel.add(exampleSelection);
			mainPanel.add(buttonPanel);
			dialog.add(mainPanel);
			dialog.pack();
			dialog.setVisible(true);



			/*JFileChooser chooser=new JFileChooser();		
							JFrame dialog=new JFrame();
							dialog.setTitle("Project creation wizard");
							JTextField directory=new JTextField(100);
							JButton browse=new JButton("Browse");
							JButton create=new JButton("Create project in selected location");
							Box mainPanel = Box.createHorizontalBox();
							JPanel folderSelectionPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
							JLabel directoryLabel=new JLabel("Project folder:");
							folderSelectionPanel.add(directoryLabel);
							folderSelectionPanel.add(directory);
							mainPanel.add(folderSelectionPanel);
							mainPanel.add(create);
							dialog.add(mainPanel);*/
		}
	}
	private void copyResourceFromTo(
			String from, String to)
			throws FileNotFoundException, IOException {
		
		InputStream streamToModiaf =this.getClass().getClassLoader().getResourceAsStream(from);
		if (streamToModiaf==null)
			throw new FileNotFoundException(from+" resource not found");
		File destination = new File(to);
		if (destination.isDirectory() && !destination.exists())
			destination.mkdirs();
		FileOutputStream target=new FileOutputStream(destination);
		byte[] bytes=new byte[8000];
		int read=0;
		do {
			read=streamToModiaf.read(bytes);
			if (read>0){
				target.write(bytes,0,read);
			}
		} while (read!=-1);
		target.close();
		streamToModiaf.close();
	}
	
	private void createProjectInLocation(final JTextField directory) {
		if (directory.getText()!=null && !directory.getText().equals("")){
			File newFolder=new File(directory.getText());
			if (newFolder.exists()){
				if (!newFolder.isDirectory()){
					JOptionPane.showMessageDialog(dialog, "A directory has to be chosen and "+
							directory.getText()+" is not a folder","No directory selected",JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					if (JOptionPane.showConfirmDialog(dialog, 
							"The folder already exists. Do you want to reuse it?",
							"Confirm overwrite",
							JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE)!=JOptionPane.OK_OPTION){
						return;
					}
				}
			} else {
				newFolder.mkdirs(); // Creates new folders and subfolders if required
			}
			Runnable antinvocation=new Runnable(){
				public void run(){
					try {
						// creating basic folders
						new File(directory.getText()+"/lib").mkdir();
						new File(directory.getText()+"/spec").mkdir();
						new File(directory.getText()+"/src").mkdir();
						new File(directory.getText()+"/gensrc").mkdir();
						new File(directory.getText()+"/permsrc").mkdir();
						new File(directory.getText()+"/bin").mkdir();
						new File(directory.getText()+"/jade").mkdir();
						new File(directory.getText()+"/config").mkdir();
						
						if (rad1.isSelected())
							ProjectGenerator.main(new String[]{directory.getText()}); // generates empty spec
						if (rad2.isSelected())
							copyResourceFromTo("examples/exampleHelloWorld.idk",directory.getText()+"/spec/specification.xml");
						if (rad3.isSelected())
							copyResourceFromTo("examples/exampleGUIAgent.idk",directory.getText()+"/spec/specification.xml");
						if (rad4.isSelected())
							copyResourceFromTo("examples/exampleInteraction.idk",directory.getText()+"/spec/specification.xml");
						Enumeration<URL> resources = this.getClass().getClassLoader().getResources("lib/*.jar");
						while (resources.hasMoreElements()){									
							System.err.println(resources.nextElement());
						}
						copyResourceFromTo("generate.xml",directory.getText()+"/generate.xml");
						copyResourceFromTo("lib/modiaf.jar",directory.getText()+"/lib/modiaf.jar");
						copyResourceFromTo("config/Properties.prop",directory.getText()+"/config/Properties.prop");
						copyResourceFromTo("lib/Base64.jar",directory.getText()+"/lib/Base64.jar");
						copyResourceFromTo("lib/commons-codec-1.3.jar",directory.getText()+"/lib/commons-codec-1.3.jar");
						copyResourceFromTo("lib/ingeniaseditor.jar",directory.getText()+"/lib/ingeniaseditor.jar");
						copyResourceFromTo("lib/jdom.jar",directory.getText()+"/lib/jdom.jar");
						copyResourceFromTo("lib/swixml.jar",directory.getText()+"/lib/swixml.jar");
						copyResourceFromTo("lib/xercesImpl.jar",directory.getText()+"/lib/xercesImpl.jar");
						copyResourceFromTo("lib/http.jar",directory.getText()+"/lib/http.jar");
						copyResourceFromTo("lib/iiop.jar",directory.getText()+"/lib/iiop.jar");
						copyResourceFromTo("lib/jade.jar",directory.getText()+"/lib/jade.jar");
						copyResourceFromTo("lib/junit.jar",directory.getText()+"/lib/junit.jar");
						copyResourceFromTo("lib/jadeTools.jar",directory.getText()+"/lib/jadeTools.jar");
						copyResourceFromTo("lib/jgraph.jar",directory.getText()+"/lib/jgraph.jar");
						copyResourceFromTo("lib/xstream-1.2.jar",directory.getText()+"/lib/xstream-1.2.jar");
						copyResourceFromTo("lib/gnujaxp.jar",directory.getText()+"/lib/gnujaxp.jar");
						copyResourceFromTo("lib/iText-2.1.5.jar",directory.getText()+"/lib/iText-2.1.5.jar");
						copyResourceFromTo("lib/jcommon-1.0.16.jar",directory.getText()+"/lib/jcommon-1.0.16.jar");
						copyResourceFromTo("lib/jfreechart-1.0.13-experimental.jar",directory.getText()+"/lib/jfreechart-1.0.13-experimental.jar");
						copyResourceFromTo("lib/jfreechart-1.0.13.jar",directory.getText()+"/lib/jfreechart-1.0.13.jar");
						copyResourceFromTo("lib/swtgraphics2d.jar",directory.getText()+"/lib/swtgraphics2d.jar");

						
						File buildFile = new File(directory.getText()+"/generate.xml");
						Project p = new Project();
						p.setUserProperty("ant.file", buildFile.getAbsolutePath());
						p.init();
						ProjectHelper helper = ProjectHelper.getProjectHelper();
						p.addReference("ant.projectHelper", helper);
						p.setProperty("specfile", directory.getText()+"/spec/specification.xml");
						p.setProperty("mainP", directory.getText());
						helper.parse(p, buildFile);
						p.executeTarget(p.getDefaultTarget());

						JOptionPane.showMessageDialog(dialog, "Project created successfully ","Project created",JOptionPane.INFORMATION_MESSAGE);
						dialog.setVisible(false);
						dialog=null;
					}
					catch (Exception ex) {
						ex.printStackTrace();
					}	
				}

				
			};
			SwingUtilities.invokeLater(antinvocation);

		} else {
			JOptionPane.showMessageDialog(dialog, "No directory has been chosen. Please, press on the browse button to select one","No directory selected",JOptionPane.ERROR_MESSAGE);
		}
	}

	private String getContent(File task) {

		FileInputStream fis;
		try {
			fis = new FileInputStream(task);
			int read=0;
			byte[] bytesRead=new byte[100];
			StringBuffer sb=new StringBuffer(); 
			while (read>-1){
				try {
					read=fis.read(bytesRead);
					for (int k=0;k<read;k++){
						sb.append((char)bytesRead[k]);
					}
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
			return sb.toString();
		} catch (FileNotFoundException e1) {			
			e1.printStackTrace();
		}

		return "";
	}

	/**
	 *  This module defines no properties
	 *
	 *@return    Empty properties
	 */
	public Vector<ProjectProperty> defaultProperties() {
		Vector<ProjectProperty> result=new Vector<ProjectProperty>();

		return result;
	}


	/**
	 *  Generates an stats report from a INGENIAS specification file (1st param)
	 *
	 *@param  args           Arguments typed in the command line. Only first one is attended
	 *@exception  Exception  Sth went wrong
	 */
	public static void main(String args[]) throws Exception {
		ingenias.editor.Log.initInstance(new java.io.PrintWriter(System.err));


		// Prints the result
		System.exit(0);
	}



}
