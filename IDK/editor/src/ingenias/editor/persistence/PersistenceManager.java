package ingenias.editor.persistence;

/*
 *  Copyright (C) 2002 Jorge Gomez Sanz, Ruben Fuentes
 *  This file is part of INGENIAS IDE, a support tool for the INGENIAS
 *  methodology, availabe at http://grasia.fdi.ucm.es/ingenias or
 *  http://ingenias.sourceforge.net
 *  INGENIAS IDE is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *  INGENIAS IDE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License
 *  along with INGENIAS IDE; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
import ingenias.exception.*;
import ingenias.editor.IDEAbs;
import ingenias.editor.IDEState;
import java.lang.reflect.*;
import javax.swing.tree.*;

import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.xni.parser.XMLInputSource;
import org.xml.sax.InputSource;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.*;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;

import javax.xml.parsers.*;
import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.w3c.dom.*;

import ingenias.editor.entities.*;
import ingenias.editor.widget.DnDJTree;
import ingenias.exception.*;
import ingenias.editor.cell.*;
import ingenias.editor.*;
import javax.swing.*;
import javax.swing.tree.*;
import ingenias.exception.*;
import ingenias.generator.browser.BrowserImp;


/**
 *  Description of the Class
 *
 * @author     developer
 * @created    7 de agosto de 2003
 */
public class PersistenceManager {

	private static GraphLoad gl;
	private static ObjectLoad ol;
	private static RelationshipLoad rl;
	private static PropertyLoad pl;


	/**
	 *  Constructor for the PersistenceManager object
	 */
	public PersistenceManager() { }

	/**
	 *  Description of the Method
	 *
	 * @param  abs               Description of the Parameter
	 * @exception  UnknowFormat  Description of the Exception
	 * @exception  CannotLoad    Description of the Exception
	 */
	public void restorePreferences(IDEAbs abs) throws UnknowFormat, CannotLoad {
		JFileChooser jfc = new JFileChooser();
		File homedir = jfc.getCurrentDirectory();
		String filename = homedir.getPath() + "/.idk/idkproperties.xml";
		if (new File(filename).exists()){
			try {

				DOMParser parser = new DOMParser();


//				Parse the Document
//				and traverse the DOM
				parser.parse("file:"+filename);
				Document doc = parser.getDocument();
				NodeList nl = doc.getElementsByTagName("preferences");
				Preferences prefs=Preferences.fromXML(nl.item(0));
				abs.prefs=prefs;
				nl = nl.item(0).getChildNodes();
				for (int k = 0; k < nl.getLength(); k++) {
					Node n = nl.item(k);
					if (n.getNodeName().equals("lastfile")) {
						if (n.getChildNodes().getLength() > 0) {
							Node file = n.getChildNodes().item(0);
							if (file.getNodeType() == Node.TEXT_NODE) {
								String path = file.getNodeValue();
								abs.updateHistory(new File(path));
							} else
								System.err.println(file.getNodeName());
						}
					}
					if (n.getNodeName().equals("lastimage")) {
						if (n.getChildNodes().getLength() > 0) {
							Node file = n.getChildNodes().item(0);
							if (file.getNodeType() == Node.TEXT_NODE) {
								String path = file.getNodeValue();
								abs.updateHistory(new File(path));
							}
						}
					}

				}
//				deleteBadRelationships(gm);
			}
			/*
			 *  catch (java.io.FileNotFoundException fnf) {
			 *  throw new CannotLoad("File " + filename + " not found");
			 *  }
			 */catch (java.io.IOException ioe) {

				 throw new CannotLoad("File " + filename + " could not be loaded. " +
						 ioe.getMessage());
			 } catch (org.xml.sax.SAXException se) {
				 se.printStackTrace();
				 throw new UnknowFormat("File " + filename + " is not valid xml." +
						 se.getMessage());
			 }
		}

	}


	/**
	 *  Description of the Method
	 *
	 * @param  ide  Description of the Parameter
	 */
	public void savePreferences(IDEAbs ide) {
		try {
			JFileChooser jfc = new JFileChooser();
			File homedir = jfc.getCurrentDirectory();
			String filename = homedir.getPath() + "/.idk/idkproperties.xml";
			java.io.FileOutputStream fos =
				new java.io.FileOutputStream(filename);
			fos.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<preferences>\n".getBytes());
			Vector v = ide.getLastFiles();
			for (int k = 0; k < v.size(); k++) {
				File current = (File) v.elementAt(k);
				fos.write(("<lastfile>" +
						ingenias.generator.util.Conversor.replaceInvalidChar(current.getPath()) + "</lastfile>\n").getBytes());

			}
			if (ide.currentImageFolder != null) {
				fos.write(("<lastimage>" +
						ingenias.generator.util.Conversor.replaceInvalidChar(
								ide.currentImageFolder.getPath()
						)+"</lastimage>\n").getBytes());
			}
			fos.write(ide.prefs.toXML().getBytes());


			fos.write("</preferences>".getBytes());

			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}


	/**
	 *  Description of the Method
	 *
	 * @param  input                                 Description of Parameter
	 * @return                                       Description of the Returned
	 *      Value
	 * @exception  ingenias.exception.UnknowFormat   Description of Exception
	 * @exception  ingenias.exception.DamagedFormat  Description of Exception
	 * @exception  CannotLoad                        Description of Exception
	 */
	public IDEState mergeFile(String input, IDEState ids) throws
	ingenias.exception.UnknowFormat,
	ingenias.exception.DamagedFormat, CannotLoad {

		try {

			DOMParser parser = new DOMParser();
//			Parse the Document
//			and traverse the DOM
			parser.parse(new InputSource(new FileInputStream(input)));

			Document doc = parser.getDocument();
			NodeList reltags = doc.getElementsByTagName("relationship");
			NodeList objects = doc.getElementsByTagName("object");
			NodeList nodesGLX = doc.getElementsByTagName("node");
			NodeList models = doc.getElementsByTagName("model");

			Vector<NAryEdgeEntity> rels = RelationshipManager.getRelationshipsVector(ids.gm);
			Hashtable<String,NAryEdgeEntity> trels=new Hashtable<String,NAryEdgeEntity>();
			for (NAryEdgeEntity nedge:rels){
				trels.put(nedge.getId(), nedge);
			}
			for (int k=0;k<reltags.getLength();k++){
				String id=reltags.item(k).getAttributes().getNamedItem("id").getNodeValue();				
				String nid=id;
				while (trels.containsKey(nid) || ids.om.findUserObject(nid).size()>0){
					nid=nid+"_";
				}
				if (!id.equals(nid)){
					reltags.item(k).getAttributes().getNamedItem("id").setNodeValue(nid);
					renameNodes(nodesGLX,id,nid);
					renameNodes(objects,id,nid);
				}
			}
			for (int k=0;k<models.getLength();k++){
				String id=models.item(k).getAttributes().getNamedItem("id").getNodeValue();				
				String nid=id;
				while (ids.gm.getModel(nid)!=null){
					nid=nid+"_";
				}
				if (!id.equals(nid)){
					reltags.item(k).getAttributes().getNamedItem("id").setNodeValue(nid);
					renameNodes(models,id,nid);
					renameNodes(objects,id,nid);
				}
			}

			String version = "1.0";
			try {
				version = this.getVersion(doc);
			} catch (VersionNotFound vnf) {
				//	vnf.printStackTrace();
			}

			this.setVersion(version);
//			ol.restoreObject(ids.om, ids.gm, doc);
			restoreObjects(ids.om, ids.gm, doc);
			rl.restoreRelationships(ids.om, ids.gm, doc);
			try {
				gl.restoreModels(ids, doc);
			} catch (ingenias.exception.CannotLoadDiagram cld) {
				throw new ingenias.exception.DamagedFormat(cld.getMessage());
			}

			Vector<NAryEdgeEntity> rels1 = RelationshipManager.getRelationshipsVector(ids.gm);
			HashSet<String> trels1=new HashSet<String>();
			for (NAryEdgeEntity nedge:rels){
				trels1.add(nedge.getId());
			}
			int n=1;
//			deleteBadRelationships(gm);

		} catch (java.io.FileNotFoundException fnf) {
			throw new CannotLoad("File " + input + " not found");
		} catch (java.io.IOException ioe) {
			throw new CannotLoad("File " + input + " could not be loaded. " +
					ioe.getMessage());
		} catch (org.xml.sax.SAXException se) {
			throw new UnknowFormat("File " + input + " is not valid xml." +
					se.getMessage());
		} catch (UnknownVersion uv) {
			throw new ingenias.exception.CannotLoad("File " + input + " version is not recognised. Try downloading a new version of the IDE in http://ingenias.sourceforge.net");
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();

		} catch (NoSuchMethodException nsme) {
			nsme.printStackTrace();
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		} catch (InstantiationException ie) {
			ie.printStackTrace();
		} catch (InvocationTargetException ite) {
			ite.printStackTrace();
		}

		return ids;
	}

	private void renameNodes(NodeList nodesGLX, String id, String nid) {
		for (int k=0;k<nodesGLX.getLength();k++){
			if (nodesGLX.item(k).getAttributes()!=null && nodesGLX.item(k).getAttributes().getNamedItem("id")!=null){
				String atid=nodesGLX.item(k).getAttributes().getNamedItem("id").getNodeValue();
				if (atid.equals(id)){
					nodesGLX.item(k).getAttributes().getNamedItem("id").setNodeValue(nid);
				}
			}

		}

	}

	/**
	 *  Description of the Method
	 *
	 * @param  input                                 Description of Parameter
	 * @return                                       Description of the Returned
	 *      Value
	 * @exception  ingenias.exception.UnknowFormat   Description of Exception
	 * @exception  ingenias.exception.DamagedFormat  Description of Exception
	 * @exception  CannotLoad                        Description of Exception
	 */
	public IDEState load(String input) throws
	ingenias.exception.UnknowFormat,
	ingenias.exception.DamagedFormat, CannotLoad {

		IDEState ids = IDEState.emptyIDEState();
		RelationshipManager.clearRelationships();

		try {

			DOMParser parser = new DOMParser();
//			Parse the Document
//			and traverse the DOM
			InputSource is=new InputSource(new FileInputStream(input));
			is.setEncoding("UTF-8");
			parser.parse(is);

			Document doc = parser.getDocument();

			String version = "1.0";
			try {
				version = this.getVersion(doc);
			} catch (VersionNotFound vnf) {
				//	vnf.printStackTrace();
			}

			this.setVersion(version);

			this.defaultProperties(ids.prop);

//			ol.restoreObject(ids.om, ids.gm, doc);
			restoreObjects(ids.om, ids.gm, doc);
			rl.restoreRelationships(ids.om, ids.gm, doc);

			try {
				gl.restoreModels(ids, doc);
				Vector<ModelJGraph> mjg=ids.gm.getUOModels();
				for (int k=0;k<mjg.size();k++){
					mjg.elementAt(k).setSelectionCells(new Object[0]);
				}
			} catch (ingenias.exception.CannotLoadDiagram cld) {
				throw new ingenias.exception.DamagedFormat(cld.getMessage());
			}

			NodeList leafpackages = doc.getElementsByTagName("leafpackages");
			if (leafpackages!=null && leafpackages.getLength()>0){
				NodeList paths = leafpackages.item(0).getChildNodes();
				ids.gm.toExpad=new Vector<TreePath>();
				for (int k=0;k<paths.getLength();k++){					
					if (paths.item(k).getNodeName().equalsIgnoreCase("path")){
						NodeList pathToAdd=paths.item(k).getChildNodes();
						Vector<String> pathlist=new Vector<String>();
						for (int j=0;j<pathToAdd.getLength();j++){							
							if (pathToAdd.item(j).getNodeName().equalsIgnoreCase("package")){
								String pname=pathToAdd.item(j).getAttributes().getNamedItem("id").getNodeValue();
								ids.gm.addPackage(pathlist.toArray(),pname);
								pathlist.add(pname);
							}
						}
						TreePath tp=getPath(pathlist,ids.gm.arbolProyecto);
						ids.gm.toExpad.add(tp);
					}

				}
			}
			ids.gm.arbolProyecto.validate();
			this.restoreProjectProperties(doc, ids); //It has to be done at this moment to open the different diagram tabs
//			deleteBadRelationships(gm);

		} catch (java.io.FileNotFoundException fnf) {
			throw new CannotLoad("File " + input + " not found");
		} catch (java.io.IOException ioe) {
			throw new CannotLoad("File " + input + " could not be loaded. " +
					ioe.getMessage());
		} catch (org.xml.sax.SAXException se) {
			throw new UnknowFormat("File " + input + " is not valid xml." +
					se.getMessage());
		} catch (UnknownVersion uv) {
			throw new ingenias.exception.CannotLoad("File " + input + " version is not recognised. Try downloading a new version of the IDE in http://ingenias.sourceforge.net");
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();

		} catch (NoSuchMethodException nsme) {
			nsme.printStackTrace();
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		} catch (InstantiationException ie) {
			ie.printStackTrace();
		} catch (InvocationTargetException ite) {
			ite.printStackTrace();
		}

		return ids;
	}


	private TreePath getPath(Vector<String> pathlist, DnDJTree arbolProyecto) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) arbolProyecto.getModel().getRoot();
		pathlist.remove(0);
		Vector<TreeNode> tn=new Vector<TreeNode>();
		tn.add(node);
		for (String name:pathlist){
			boolean found=false;
			for (int k=0;k<node.getChildCount() && !found;k++){
				DefaultMutableTreeNode child=(DefaultMutableTreeNode) node.getChildAt(k);
				found=child.getUserObject().toString().equalsIgnoreCase(name);
				if (found){
					node=child;
					tn.add(node);
				}
			}
		}
		return new TreePath(tn.toArray());
	}

	/**
	 *  Description of the Method
	 *
	 * @param  output                   Description of the Parameter
	 * @param  ids                      Description of the Parameter
	 * @exception  java.io.IOException  Description of Exception
	 */
	public void save(File output, IDEState ids) throws java.io.IOException {
		this.saveAllModels(ids.prop, ids.om, new RelationshipManager(), ids.gm,
				output);
	}


	/**
	 *  Sets the version attribute of the PersistenceManager object
	 *
	 * @param  ver                 The new version value
	 * @exception  UnknownVersion  Description of Exception
	 */
	private void setVersion(String ver) throws UnknownVersion {
		if (ver.equals("1.0")) {
			gl = new GraphLoadImp1();
			rl = new RelationshipLoadImp1();
			ol = new ObjectLoadImp1();
			pl = new PropertyLoadImp1();
		} else {
			if (ver.equals("1.1")) {
				gl = new GraphLoadImp2(); 
				rl = new RelationshipLoadImp2();
				ol = new ObjectLoadImp1();
				pl = new PropertyLoadImp1();
			} else {
				gl = new GraphLoadImp1();
				rl = new RelationshipLoadImp1();
				ol = new ObjectLoadImp1();
				pl = new PropertyLoadImp1();
			}
		}

//		throw new UnknownVersion();
	}


	/**
	 *  Gets the version attribute of the PersistenceManager object
	 *
	 * @param  doc                                     Description of Parameter
	 * @return                                         The version value
	 * @exception  ingenias.exception.VersionNotFound  Description of Exception
	 */
	private String getVersion(org.w3c.dom.Document doc) throws
	ingenias.exception.VersionNotFound {
		try {
			String version = "";
			NodeList nl = doc.getElementsByTagName("project");
			version = nl.item(0).getAttributes().getNamedItem("version").getNodeValue();
			return version;
		} catch (Exception e) {
			throw new VersionNotFound(e.getMessage());
		}
	}


	/**
	 *  Description of the Method
	 *
	 * @param  doc   Description of Parameter
	 * @param  ids  Description of Parameter
	 */
	private void restoreProjectProperties(Document doc, IDEState ids) {
		NodeList nl = doc.getElementsByTagName("project");
		for (int k = 0; k < nl.getLength(); k++) {
			Node current = nl.item(k);
			if (current.getNodeName().equalsIgnoreCase("project")) {
				String cid = current.getAttributes().getNamedItem("cid").getNodeValue();
				Editor.idCounter = Integer.parseInt(cid);
				NodeList nll = current.getChildNodes();
				for (int l = 0; l < nll.getLength(); l++) {
					current = nll.item(l);
					if (current.getNodeName().equalsIgnoreCase("projectproperties")) {
						NodeList nl1 = current.getChildNodes();
						for (int j = 0; j < nl1.getLength(); j++) {
							try {
								if (nl1.item(j).getNodeName().equalsIgnoreCase(
								"projectproperty")) {
									ProjectProperty pp = ProjectProperty.fromXML(nl1.item(j));
									ids.prop.put(pp.module+":"+pp.key, pp);		
								}
							} catch (InvalidProjectProperty ipp) {
								//	ipp.printStackTrace();
							}
							if (nl1.item(j).getNodeName().equals("openeddiagram")) {
								if (nl1.item(j).getChildNodes().getLength() > 0) {
									Node diagram = nl1.item(j).getChildNodes().item(0);
									if (diagram.getNodeType() == Node.TEXT_NODE) {
										String diagramT = diagram.getNodeValue();
										if (ids.gm.getModel(diagramT)!=null)
											ids.editor.changeGraph(ids.gm.getModel(diagramT));
									}
								}
							}
						}
					}
				}
			}
		}
		

	}


	/**
	 *  Description of the Method
	 *
	 * @param  om                             Description of Parameter
	 * @param  gm                             Description of Parameter
	 * @param  doc                            Description of Parameter
	 * @exception  ClassNotFoundException     Description of Exception
	 * @exception  NoSuchMethodException      Description of Exception
	 * @exception  IllegalAccessException     Description of Exception
	 * @exception  InstantiationException     Description of Exception
	 * @exception  InvocationTargetException  Description of Exception
	 */
	private void restoreObjects(ObjectManager om,
			GraphManager gm, Document doc) throws
			ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
		NodeList nl = doc.getElementsByTagName("objects");
		NodeList objects = nl.item(0).getChildNodes();
		for (int k = 0; k < objects.getLength(); k++) {
			Node n = objects.item(k);
			if (n.getNodeName().equalsIgnoreCase("object")) {
				ol.restoreObject(om, gm, n);
			}
		}
	}


	/**
	 *  Description of the Method
	 *
	 * @param  prop                     Description of Parameter
	 * @param  fos                      Description of Parameter
	 * @exception  java.io.IOException  Description of Exception
	 */
	private void saveProjectProperties(Properties prop, OutputStreamWriter fos) throws
	java.io.IOException {
		fos.write(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<project cid=\"" +
				Editor.idCounter + 
		"\" version=\"1.1\">\n");
		Enumeration ppenumeration = prop.elements();
		fos.write(("<projectproperties>\n"));
		while (ppenumeration.hasMoreElements()) {
			ProjectProperty pp = (ProjectProperty) ppenumeration.nextElement();
			fos.write(pp.toXML());
		}
		Vector<String> diagrams = IDE.ide.ids.editor.getOpenedDiagrams();

		for (String diagram:diagrams){
			fos.write(" <openeddiagram>"+ingenias.generator.util.Conversor.replaceInvalidChar(diagram)+"</openeddiagram>");
		}

		fos.write(("</projectproperties>\n"));

	}


	/**
	 *  Description of the Method
	 *
	 * @param  prop                     Description of Parameter
	 * @param  leafpackages             Description of Parameter
	 * @param  fos                      Description of Parameter
	 * @exception  java.io.IOException  Description of Exception
	 */
	private void saveProjectTree(Properties prop, Vector leafpackages,
			OutputStreamWriter fos) throws java.io.IOException {
		TreePath parent=new TreePath(IDE.ide.ids.gm.root.getPath());
		Enumeration<TreePath> leaf = IDE.ide.ids.gm.arbolProyecto.getExpandedDescendants(parent);
		Enumeration enumerationPack = leafpackages.elements();

		this.saveProjectProperties(prop, fos);

		fos.write("<leafpackages>\n");
		while (leaf !=null && leaf.hasMoreElements()) {
			TreePath currentLeaf = leaf.nextElement();

			fos.write("   <path>\n");
			Object[] packPath =  currentLeaf.getPath();
			for (int k = 0; k < packPath.length; k++) {
				String packageName = packPath[k].toString();						
				fos.write(("    <package id=\"" + packageName + "\"/>\n"));
			}
			fos.write("   </path>\n");

		}
		fos.write("</leafpackages>\n");

	}


	/**
	 *  Description of the Method
	 *
	 * @param  prop                       Description of Parameter
	 * @param  om                         Description of Parameter
	 * @param  rm                         Description of Parameter
	 * @param  gm                         Description of Parameter
	 * @param  output                     Description of Parameter
	 * @exception  IOException            Description of Exception
	 * @exception  FileNotFoundException  Description of Exception
	 */
	private void saveAllModels(Properties prop, ObjectManager om,
			RelationshipManager rm, GraphManager gm,
			File output) throws IOException,
			FileNotFoundException {
		try {
			RelationshipSave rs=new RelationshipSave();
			ObjectSave objsave=new ObjectSave();
			Vector models = gm.getModels();
			//  Vector objects=om.getObjects();
			Enumeration enumeration = models.elements();
			FileOutputStream os = new FileOutputStream(output);
			OutputStreamWriter fos=new OutputStreamWriter(os,"UTF-8");



			this.saveProjectTree(prop, new Vector(), fos);

			objsave.saveObjects(om, gm, fos);

			rs.saveRelationships(rm, gm, fos);

			fos.write("<models> \n");
			while (enumeration.hasMoreElements()) {
				TreeNode[] modelPath = (TreeNode[]) enumeration.nextElement();
				ModelJGraph model = (ModelJGraph) ((DefaultMutableTreeNode) modelPath[
				                                                                      modelPath.length - 1]).getUserObject();

				GraphSave.saveModel(model, modelPath, fos); 

			}

			fos.write("</models>\n");
			fos.write("</project>\n");
			fos.close();
		} catch (Throwable th) {
			th.printStackTrace();

		}

	}


	/**
	 *  Gets the gL attribute of the PersistenceManager class
	 *
	 * @return    The gL value
	 */
	public static GraphLoad getGL() {
		return gl;
	}


	/**
	 *  Gets the oL attribute of the PersistenceManager class
	 *
	 * @return    The oL value
	 */
	public static ObjectLoad getOL() {
		return ol;
	}


	/**
	 *  Gets the rL attribute of the PersistenceManager class
	 *
	 * @return    The rL value
	 */
	public static RelationshipLoad getRL() {
		return rl;
	}


	/**
	 *  Gets the pL attribute of the PersistenceManager class
	 *
	 * @return    The pL value
	 */
	public static PropertyLoad getPL() {
		return pl;
	}


	/**
	 *  Description of the Method
	 *
	 * @param  prop  Description of Parameter
	 */
	public static void defaultProperties(Properties prop) {
		if (IDE.ide !=null && IDE.ide.ids!=null && IDE.ide.ids.prop!=null)
			prop.putAll(IDE.ide.ids.prop);
	}


	/**
	 *  The main program for the PersistenceManager class
	 *
	 * @param  args  The command line arguments
	 */
	public static void main(String[] args) {
		PersistenceManager persistenceManager1 = new PersistenceManager();
	}

}

