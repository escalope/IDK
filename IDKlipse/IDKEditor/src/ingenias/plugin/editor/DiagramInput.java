//    IDKLipse is a plugin for the development of Multi-Agent Systems based 
//    on the INGENIAS Development Kit, which is distributed under the terms of 
//    the GPL.
//
//   Copyright (C) 2009  Jorge J. Gomez-Sanz 
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package ingenias.plugin.editor;

import ingenias.codeproc.HTMLDocumentGenerator;
import ingenias.codeproc.IAFGenerator;
import ingenias.editor.GUIResources;
import ingenias.editor.GraphManager;
import ingenias.editor.IDE;
import ingenias.editor.IDEAbs;
import ingenias.editor.IDEState;
import ingenias.editor.IDEUpdater;
import ingenias.editor.Log;
import ingenias.editor.ModelJGraph;
import ingenias.editor.ObjectManager;
import ingenias.editor.ProjectProperty;
import ingenias.editor.filters.FilterManager;
import ingenias.editor.persistence.PersistenceManager;
import ingenias.editor.widget.DnDJTree;
import ingenias.generator.browser.BrowserImp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JWindow;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IPluginRegistry;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class DiagramInput implements IEditorInput {

	private ingenias.editor.IDEState ids=ingenias.editor.IDEState.emptyIDEState();		

	String specFile=null;

	private SWTGUIResources resources;

	public DiagramInput(String specFile) {
		super();		 
		this.specFile=specFile;
		Assert.isNotNull(specFile);		
		loadFile(new File(specFile));
	}


	
	public DiagramInput() {
		super();	
		this.specFile="";
		Assert.isNotNull(specFile);		
		ids=ingenias.editor.IDEState.emptyIDEState();
		
		// TODO Auto-generated constructor stub
	}

	public void loadFile(File file) {
		final File input = file;
		//final JWindow jw = showMessageWindow("LOADING...");
				
				try{
					resources=new SWTGUIResources(); 
					ids=ingenias.editor.IDEState.emptyIDEState();
					System.err.println("CArgvando");
					ingenias.editor.actions.LoadFileAction la=new ingenias.editor.actions.LoadFileAction(ids,resources);
					ids=la.loadFileAction(file);
					System.err.println(ids.editor.getGraphPanel().getTabCount());
										
					Log.getInstance().logSYS("Project loaded successfully");

					for (TreePath tp:ids.gm.toExpad){
						Vector<Object> npath=new Vector<Object>(); 
						for (Object path:tp.getPath()){
							npath.add(path);
						}
						npath.remove(0);
						npath.insertElementAt(ids.gm.arbolProyecto.getModel().getRoot(), 0);
						ids.gm.arbolProyecto.expandPath(new TreePath(npath.toArray()));				
					}
	
					
				}				
				catch (Throwable t){
					t.printStackTrace();
				}
	
	}
	
	public GUIResources getResources() {
		return resources;
	}

	public void setResources(SWTGUIResources resources) {
		this.resources = resources;
	}

	public void saveFile(String file) {
		final File input = new File(file);
		//final JWindow jw = showMessageWindow("LOADING...");

				try{
					// This code sets the html and jade project properties to paths adapted to 
					// the created eclipse project
					IAFGenerator jadegen = new IAFGenerator(new BrowserImp(ids));
					HTMLDocumentGenerator html = new HTMLDocumentGenerator(new BrowserImp(ids));
					
					Properties props = jadegen.getProperties();					
					ProjectProperty jadeProjectProperty = (ProjectProperty) props.get(jadegen.getName()+":jadeproject");
				
					jadeProjectProperty.value=input.getParentFile().getParentFile().getAbsolutePath();
					ids.prop.putAll(props);
					
					props = html.getProperties();

					jadeProjectProperty = (ProjectProperty) props.get(html.getName()+":htmldoc");
					jadeProjectProperty.value= input.getParentFile().getParentFile().getAbsolutePath()+"/html";
					ids.prop.putAll(props);
					
					PersistenceManager pm = new PersistenceManager();
					
					pm.save(input, ids);
					

				}
				catch (Throwable t){
					t.printStackTrace();
				}
	
	}
	
	

	@Override
	public boolean exists() {

		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {

		return null;
	}

	@Override
	public String getName() {

		return specFile;
	}

	@Override
	public IPersistableElement getPersistable() {

		return null;
	}

	@Override
	public String getToolTipText() {

		return specFile;
	}

	@Override
	public Object getAdapter(Class arg0) {

		return null;
	}

	public boolean equals(Object obj) {
		if (super.equals(obj))
			return true;
		if (!(obj instanceof DiagramInput))
			return false;
		DiagramInput other = (DiagramInput) obj;
		return specFile.equals(other.specFile);
	}
	public int hashCode() {
		return specFile.hashCode();
	}


	private void replaceTree(DefaultMutableTreeNode replaced,
			DefaultMutableTreeNode replacee) {

		replaced.removeAllChildren();
		while (replacee.getChildCount() > 0) {
			DefaultMutableTreeNode first = (DefaultMutableTreeNode) replacee.
			getChildAt(0);
			replacee.remove(first);
			first.removeFromParent();
			replaced.add(first);
			first.setParent(replaced);
		}

	}

	public DnDJTree getArbolProyectos() {
		return ids.gm.arbolProyecto;
	}

	public DefaultMutableTreeNode getRoot() {
		return (DefaultMutableTreeNode) ids.gm.arbolProyecto.getModel().getRoot();		
	}

	public IDEState getIDEState() {
		return ids;
	}

	public ModelJGraph getDiagram(String diagramName) {
		return ids.gm.getModel(diagramName);		
	}

	public String getSpecFile() {
		return specFile;
	}

	public void setSpecFile(String specFile) {
		this.specFile = specFile;
	}
}
