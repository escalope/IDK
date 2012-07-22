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
package ingenias.codeproc;

import ingenias.codeproc.InteractionGeneration;
import ingenias.editor.GUIResources;
import ingenias.editor.IDEAbs;
import ingenias.editor.IDEState;
import ingenias.editor.Log;
import ingenias.editor.ModelJGraph;
import ingenias.editor.ProjectProperty;
import ingenias.editor.actions.HistoryManager;
import ingenias.editor.persistence.PersistenceManager;
import ingenias.editor.persistence.TextAreaOutputStream;
import ingenias.exception.CannotLoad;
import ingenias.exception.DamagedFormat;
import ingenias.exception.NotFound;
import ingenias.exception.NotInitialised;
import ingenias.exception.NullEntity;
import ingenias.exception.UnknowFormat;
import ingenias.generator.browser.Browser;
import ingenias.generator.browser.BrowserImp;
import ingenias.generator.browser.Graph;
import ingenias.generator.browser.GraphAttribute;
import ingenias.generator.browser.GraphCollection;
import ingenias.generator.browser.GraphEntity;
import ingenias.generator.browser.GraphRelationship;
import ingenias.generator.browser.GraphRole;
import ingenias.generator.datatemplate.Repeat;
import ingenias.generator.datatemplate.Sequences;
import ingenias.generator.datatemplate.Var;
import ingenias.generator.interpreter.SplitHandler;
import ingenias.generator.util.FileUtils;

import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bouncycastle.jce.provider.JDKMessageDigest.MD5;
import org.fest.swing.awt.AWT;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *  An XML merger using as guideline the following DSL
 *  
 *  <mergespec>
 *    <file id="myid1" src="pathtofile.xml">
 *    <file id="myid2" src="pathtofile.xml">
 *    <file id="myid3" src="pathtofile.xml">
 *    <file id="myid4" src="pathtofile.xml">
 *    <merge value="((myid1,myid2),myid4)" target="myid3"/>       
 *  </mergespec>
 *
 *@author     Jorge Gomez
 *@created    29 de marzo de 2003
 */
public class IAFMerger {

	public static File evaluateMergeExpression(String expression, Hashtable<String,File> files) throws IOException, UnknowFormat, DamagedFormat, CannotLoad{
		int leftmostLeftParethesis=expression.indexOf(")");
		int lastIndex=0;
		while (expression.indexOf("(",lastIndex)<leftmostLeftParethesis && expression.indexOf("(",lastIndex+1)>=0)
			lastIndex=expression.indexOf("(",lastIndex+1);
		String firstoperand=expression.substring(lastIndex+1,expression.indexOf(",",lastIndex));
		String second=expression.substring(expression.indexOf(",",lastIndex)+1,leftmostLeftParethesis);
		File temp=File.createTempFile("iafmerger", ".xml");
		files.put(temp.getName(), temp);
		merge(files.get(firstoperand),files.get(second),temp);
		expression=expression.substring(0,lastIndex)+temp.getName()+expression.substring(leftmostLeftParethesis+1,expression.length());
		if (expression.indexOf(",")>=0)
			return evaluateMergeExpression(expression, files);
		else
			return temp;
	}
	
	
	public static void merge(File file1, File file2, File target) throws UnknowFormat, DamagedFormat, CannotLoad, IOException{
		
		Browser browser=BrowserImp.initialise(file1.getAbsolutePath());
		GUIResources resources=null;
		IDEAbs ide=new IDEAbs();
		resources=ide.getResources();
		
		ide.updateIDEState(browser.getState());
		
		PersistenceManager pm = new PersistenceManager();
		// Merges data from the file	   
		
		pm.mergeFile(file2.getAbsolutePath(),
				browser.getState(),
				resources);
		
		pm.save(target,ide.getIds());
		
		Log.getInstance().logSYS("Project imported successfully");
	}
	
	

	public static void main(String args[]) throws SAXException, IOException, ParserConfigurationException, UnknowFormat, DamagedFormat, CannotLoad{
		if (args.length==0)
			System.err.println("You need to use as argument the path to a XML file containing the merge instructions");
		else {
			String mergeInstructions=args[0];
			if (!new File(mergeInstructions).exists())
				throw new IOException("Merge instructions are not available in "+mergeInstructions+". File not found");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new File(mergeInstructions));
			doc.getDocumentElement().normalize();
			NodeList files = doc.getElementsByTagName("file");
			Hashtable<String,File> filesTable=new Hashtable<String,File> ();
			for (int k=0;k<files.getLength();k++){
				String id=files.item(k).getAttributes().getNamedItem("id").getNodeValue();
				String src=files.item(k).getAttributes().getNamedItem("src").getNodeValue();
				if (!new File(src).exists()){
					throw new IOException("File id "+id+" associated with path "+src+" does not exist");
				} else{ 
					filesTable.put(id,new File(src));
				}
			}
			if (filesTable.isEmpty())
				throw new IOException("There are no defined files in "+mergeInstructions);				
			NodeList mergeTag = doc.getElementsByTagName("merge");
			if (mergeTag.getLength()==0)
				throw new IOException("A merge tag is required with format <merge value=\"(idfile,idfile)\" target=\"idfile\"/>");

			String mergeExpression=mergeTag.item(0).getAttributes().getNamedItem("value").getNodeValue();
			String targetFile=mergeTag.item(0).getAttributes().getNamedItem("target").getNodeValue();
			Log.initInstance(new PrintWriter(System.out));
			File result=evaluateMergeExpression(mergeExpression,filesTable);
			StringBuffer content=FileUtils.readFile(result.getAbsolutePath());
			FileOutputStream fos=new FileOutputStream(targetFile);
			fos.write(content.toString().getBytes());
			fos.close();
			
		}
	}
}
