

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

public class IDE
extends ingenias.editor.IDEAbs {


	/**
	 *  Constructor for the IDE object
	 */
	public IDE() {
		super();
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
		IDEAbs ide=new IDEAbs();
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
			/*ids=new LoadFileAction(ide.getIds(),ide.getResources()).loadNewFile(updater)(new File(args[0]));
			ide.updateIDEState(ids);*/
		}


	}
}



