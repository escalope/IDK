

/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz
 * 
 * This file is part of the INGENME tool. INGENME is an open source meta-editor
 * which produces customized editors for user-defined modeling languages
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3 of the License
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 **/


package ingenias.editor;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import javax.swing.tree.*;
import javax.swing.event.*;

import java.awt.event.*;
import java.util.Map;
import java.util.Hashtable;
import ingenias.editor.extension.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;
import javax.swing.event.UndoableEditEvent;
import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.jgraph.event.*;
import java.util.Vector;
import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.jgraph.event.*;
import org.jgraph.plaf.basic.*;
import ingenias.editor.entities.*;
import java.io.*;

import ingenias.editor.persistence.*;

import java.awt.image.*;
import java.awt.datatransfer.*;
import java.awt.geom.Rectangle2D;

import ingenias.editor.cell.*;
import ingenias.exception.NullEntity;
import ingenias.generator.browser.BrowserImp;
import ingenias.generator.browser.Graph;
import ingenias.generator.browser.GraphEntity;
import ingenias.editor.models.*;
import ingenias.editor.actions.*;
import ingenias.editor.actions.diagram.*;

class CheckChangesInFile extends Thread{
	boolean stop=false;
	File watchedFile=null;
	private long lastModified;
	IDE ide;

	CheckChangesInFile(IDE ide){				
		this.ide=ide;
	}

	public static int showConfirmDialog(Component parentComponent,
			Object message, String title,
			int optionType)
	{
		JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE, optionType);
		JDialog dialog = pane.createDialog(parentComponent, title);
		dialog.setLocationByPlatform(true);
		dialog.setVisible(true);
		if (pane.getValue() instanceof Integer)
			return ((Integer) pane.getValue()).intValue();
		return -1;
	}

	public void run(){
		while (!stop){
			try {
				Thread.currentThread().sleep(500);
				if (watchedFile!=null &&
						ide.getIds().getCurrentFile()!=null 
						&& ide.getIds().getCurrentFile().getCanonicalPath().equals(watchedFile.getCanonicalPath())){
					if (ide.hasFocus()){
						if (new File(watchedFile.getCanonicalPath()).lastModified()>lastModified){
							if (!ide.getIds().isChanged()){										
								int result = showConfirmDialog(ide, 
										"The specification has changed. \n Press OK to " +
												"load the new one. " +
												" If you cancel, no further action will be taken.","Specification file changed in disk",
												JOptionPane.OK_CANCEL_OPTION);
								if (result==JOptionPane.OK_OPTION){
									lastModified=new File(watchedFile.getCanonicalPath()).lastModified();
									new LoadFileSwingTask(watchedFile,ide,ide.getIds(),ide.getResources()).execute();
								} else {
									// update data to match the new file
									watchedFile=ide.getIds().getCurrentFile();
									lastModified=this.watchedFile.lastModified();
								}

							} else {
								int result = showConfirmDialog(ide, "The specification has changed in disk." +
										" Opened one is modified and changes can be lost. Do you want continue?",
										"Specification file changed in disk", 
										JOptionPane.OK_CANCEL_OPTION);

								lastModified=new File(watchedFile.getCanonicalPath()).lastModified();
								if (result==JOptionPane.OK_OPTION)
									new LoadFileSwingTask(watchedFile,ide,ide.getIds(),ide.getResources()).execute();
								else {
									// update data to match the new file
									watchedFile=ide.getIds().getCurrentFile();
									lastModified=this.watchedFile.lastModified();
								}
							}

						}
					} 
				} else {
					if (watchedFile==null && ide.getIds().getCurrentFile()!=null ){
						// nothing is being watched, but a file is opened
						watchedFile=ide.getIds().getCurrentFile();
						lastModified=this.watchedFile.lastModified();
					} else {
						if (watchedFile!=null &&
								ide.getIds().getCurrentFile()!=null 
								&& !ide.getIds().getCurrentFile().getCanonicalPath().equals(watchedFile.getCanonicalPath())){
							// files have changed and it is required to watch a new one
							watchedFile=ide.getIds().getCurrentFile();
							lastModified=this.watchedFile.lastModified();
						}
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void stopUpdate(){
		stop=true;
	}

}
public class IDE
extends ingenias.editor.IDEAbs {


	/**
	 *  Constructor for the IDE object
	 */
	public IDE() {
		super();
		try {
			System.getProperties().load(getClass().getResourceAsStream("/editor.properties"));
		} catch (IOException e) {			
		}
	}
	public static void setUIFont (javax.swing.plaf.FontUIResource f){
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get (key);
			if (value instanceof javax.swing.plaf.FontUIResource)
				UIManager.put (key, f);
		}
	}    


	public static void main(String args[]) throws Exception {
		System.out.println("INGENIAS Development Kit (C) 2012 Jorge Gomez");
		System.out.println("This program comes with ABSOLUTELY NO WARRANTY; for details check www.gnu.org/copyleft/gpl.html.");
		System.out.println("This is free software, and you are welcome to redistribute it under certain conditions;; for details check www.gnu.org/copyleft/gpl.html.");
		setUIFont(new javax.swing.plaf.FontUIResource(FontConfiguration.getConfiguration().getGUIFont()));   
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		}catch(Exception ex){
		}

		if (args.length != 0) {			
			if (args[0].equalsIgnoreCase("testing")){
				StaticPreferences.setTesting(true);
			}
		}

		IDEState ids=IDEState.emptyIDEState();
		try {
			ingenias.generator.browser.BrowserImp.initialise(ids);
		}	
		catch (Exception e) {
			e.printStackTrace();
		}	;	
		GUIResources resources=null;
		IDE ide=new IDE();
		resources=ide.getResources();
		Log.initInstance(new PrintWriter(new TextAreaOutputStream(resources.getModuleOutput())),
				new PrintWriter(new TextAreaOutputStream(resources.getLogs())));
		ide.updateIDEState(ids);



		//ide.initialiseActionHandlers();
		ide.validate();
		ide.pack();
		ide.setVisible(true);
		ide.setTitle("IDK-INGENME");

		if (args.length != 0 && !args[0].equalsIgnoreCase("testing")) {		
			new LoadFileSwingTask(new File(args[0]),ide,ide.getIds(),resources).execute();	
			CheckChangesInFile ccif=new CheckChangesInFile(ide);
			ccif.start();
			/*ids=new LoadFileAction(ide.getIds(),ide.getResources()).loadNewFile(updater)(new File(args[0]));
			ide.updateIDEState(ids);*/
		}


	}
}



