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


import ingenias.editor.Log;
import ingenias.editor.ModelJGraph;
import ingenias.editor.ProjectProperty;
import ingenias.editor.persistence.PersistenceManager;
import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;
import ingenias.generator.util.FileUtils;

import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 *  This class uploads code from tasks back into the 
 *
 *@author     Jorge Gomez
 *@created    14 of December of 2003
 */
public class CodeUploader extends ingenias.editor.extension.BasicToolImp {

	boolean codeChanged=false; 
	
	public boolean isCodeChanged() {
		return codeChanged;
	}

	/**
	 *  Initialises the class with a file containing a INGENIAS specification
	 *
	 *@param  file           Path to file containing INGENIAS specification
	 *@exception  Exception  Error accessing any file or malformed XML exception
	 */

	public CodeUploader(String file) throws Exception {
		super(file);
	}

	/**
	 *  Initialises the class giving access to diagrams in run-time
	 **/

	public CodeUploader(Browser browser) throws Exception {
		super(browser);
	}

	@Override
	public String getVersion() {
		return "@modcodeuploader.ver@";
	}


	/**
	 *  Gets the description of this module
	 *
	 *@return    The description
	 */
	public String getDescription() {
		return "This module uploads code embedded in taks up into the model";
	}


	/**
	 *  Gets the name of this module
	 *
	 *@return    The name
	 */
	public String getName() {
		return "CodeUploader";
	}


	/**
	 *  It opens the different files generated under the ingenias/jade/components folder looking
	 *  for specific tags. These tags mark the beginning and the end of the modification
	 */
	public void run() {
		try {
			String folderForTasks=((ProjectProperty) this.getProperties().get("Ingenias Agent Framework generator:jadeproject")).value+"/"+
					((ProjectProperty) this.getProperties().get("Ingenias Agent Framework generator:jadeout")).value;

			if (this.getBrowser().getState().prefs!=null && this.getBrowser().getState().prefs.getWorkspacePath()!=null)
				folderForTasks=folderForTasks.replace("{workspace}",this.getBrowser().getState().prefs.getWorkspacePath());
			File folderDir=new File(folderForTasks);
			Vector<File> taskFiles=obtainAllFiles(folderDir);
			for (File task:taskFiles){
				if (task.isFile()){
					String content=getContent(task);
					int indexStart=content.indexOf("//#start_node:");
					int indexEnd=content.indexOf("//#end_node")-1;
					if (indexStart>-1 && indexEnd>-1){// && content.indexOf("<--",indexStart)>-1){
						String nodeid=content.substring(indexStart+"//#start_node:".length(), content.indexOf(" <--",indexStart));
						GraphEntity entity=browser.findEntity(nodeid);
						if (entity!=null){
							int indexStartCode=content.indexOf("\n",indexStart)+1;							
							String code=content.substring(indexStartCode,indexEnd);
							String othercode=entity.getAttributeByName("Code").getSimpleValue();
							if (!code.equals(othercode)){
								Graph g=browser.findFirstEntityOccurrence(nodeid);
								/*System.err.println("node:"+nodeid);
								System.err.println("old:'"+othercode+"'");
								System.err.println("new:'"+code+"'");*/
								//System.err.println(g+":"+entity);
								GraphAttributeFactory.createDefaultGraphFactory(browser);
								GraphAttributeFactory gaf = GraphAttributeFactory.createDefaultGraphFactory(browser);
								entity.setAttribute(gaf.createAttribute("Code", code,g));
								//System.err.println(entity.getAttributeByName("Code").getSimpleValue());
								browser.getState().setChanged(true);	
								codeChanged=true;
								//System.err.println("Cambiado .............");
							}
						}
					}
				}
			}			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	private Vector<File> obtainAllFiles(File folderDir) {
		Vector<File> currentFiles=new Vector<File>();
		for (File file:folderDir.listFiles()){
			if (file.isDirectory()){
				Vector<File> obtainedFiles=obtainAllFiles(file);
				currentFiles.addAll(obtainedFiles);
			} else {
				currentFiles.add(file);
			}
		}
		return currentFiles;
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

	public static byte[] getCheckSum(String content) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		return  MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
	}


	public static byte[] getLastCheckSum(String checksumprefix) throws FileNotFoundException, IOException {
		File homeidkfolder=new File(System.getProperty("user.home")+"/.idk");
		if (!homeidkfolder.exists())
			homeidkfolder.mkdirs();
		File lastCheckSum=new File(System.getProperty("user.home")+"/.idk/"+checksumprefix.replace("/", "").replace("\\","")+"lastchecksum");
		if (lastCheckSum.exists()){
			return FileUtils.readFileAsBytes(lastCheckSum.getAbsolutePath());

		}			
		return new byte[0];
	}

	public static void storeChecksum(String checksumprefix, byte[] checksum) throws FileNotFoundException, IOException {
		File homeidkfolder=new File(System.getProperty("user.home")+"/.idk");
		if (!homeidkfolder.exists())
			homeidkfolder.mkdirs();
		File lastCheckSum=new File(System.getProperty("user.home")+"/.idk/"+checksumprefix.replace("/", "").replace("\\","")+"lastchecksum");
		FileOutputStream fos=new FileOutputStream(lastCheckSum);
		fos.write(checksum);
		fos.close();

	}

	/**
	 *  Generates an stats report from a INGENIAS specification file (1st param)
	 *
	 *@param  args           Arguments typed in the command line. Only first one is attended
	 *@exception  Exception  Sth went wrong
	 */
	public static void main(String args[])  {
		System.out.println("INGENIAS Code Uploader  (C) 2012 Jorge Gomez");
		System.out.println("This program comes with ABSOLUTELY NO WARRANTY; for details check www.gnu.org/copyleft/gpl.html.");
		System.out.println("This is free software, and you are welcome to redistribute it under certain conditions;; for details check www.gnu.org/copyleft/gpl.html.");

		if (args.length==3){ 
			try {
				ingenias.editor.Log.initInstance(new PrintWriter(System.out));

				ModelJGraph.disableAllListeners(); // this disable layout listeners that slow down code generation
				// it is a bug of the platform which will be addressed in the future

				CodeUploader jadegen = new CodeUploader(args[0]);
				Properties props = jadegen.getBrowser().getState().prop;
				Properties oldprops=new Properties(props);


				//					jadegen.setProperty("jadeproject", args[1]);
				props.put("Ingenias Agent Framework generator:jadeproject",
						new ProjectProperty("Ingenias Agent Framework generator",
								"Ingenias Agent Framework generator:jadeproject","jadeproject",
								".",""));
				props.put("Ingenias Agent Framework generator:jadeout",
						new ProjectProperty("Ingenias Agent Framework generator",
								"Ingenias Agent Framework generator:jadeout","jadeout",
								args[1],"")
						);



				/*for (Object key: props.keySet()){
					System.err.println(((ProjectProperty)props.get(key.toString())).key+":"+
							((ProjectProperty)props.get(key.toString())).value);
				};*/

				// creates a backup first
				PersistenceManager pm=new PersistenceManager();
				int counter=0;
				new File("target/backupspec").mkdirs();
				while (new File("target/backupspec/specback"+args[0]+counter+".xml").exists()) counter++;				
				pm.save(new File("target/backupspec/specback"+args[0]+counter+".xml"), jadegen.getBrowser().getState());

				jadegen.run();
				jadegen.getBrowser().getState().prop=oldprops;
				if (jadegen.isCodeChanged()){
					pm=new PersistenceManager();
					pm.save(new File(args[2]), jadegen.getBrowser().getState());
				}
			} catch (Throwable t){
				Log.getInstance().logERROR(t);
			}
			for (Frame f:Frame.getFrames()){
				f.dispose();
			}
		} else {
			Log.getInstance().logERROR("The first argument (mandatory) has to be the specification file, the second (optional) " +
					"the project folder, and the third the file where the new specification will be stored");
		}




	}



}

