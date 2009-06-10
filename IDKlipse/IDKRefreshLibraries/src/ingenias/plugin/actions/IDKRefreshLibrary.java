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

package ingenias.plugin.actions;

import ingenias.plugin.wizard.FileCopy;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IPluginRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.internal.ui.packageview.PackageExplorerPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.jdt.internal.core.*;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.launching.*;
import org.eclipse.swt.widgets.*;


public class IDKRefreshLibrary implements IObjectActionDelegate {

	private Shell shell;
	@SuppressWarnings("restriction")
	private PackageExplorerPart part;

	/**
	 * Constructor for Action1.
	 */
	public IDKRefreshLibrary() {
		super();

	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	@SuppressWarnings("restriction")
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		
		if (! (targetPart instanceof org.eclipse.jdt.internal.ui.packageview.PackageExplorerPart)){
			shell=null;

		} else {
			shell = targetPart.getSite().getShell();		
			part=(org.eclipse.jdt.internal.ui.packageview.PackageExplorerPart) targetPart;
		}


	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		System.err.println("running action");
		if (shell!=null && part.getSite().getSelectionProvider().getSelection() instanceof org.eclipse.jface.viewers.TreeSelection){
			org.eclipse.jface.viewers.TreeSelection selectedObject=(TreeSelection) part.getSite().getSelectionProvider().getSelection();
			System.err.println(selectedObject.getFirstElement().getClass().getName());
			String targetProjectFileSystemFullPath="";
			if (selectedObject.getFirstElement() instanceof org.eclipse.jdt.internal.core.JavaProject){
				org.eclipse.jdt.internal.core.JavaProject project=(org.eclipse.jdt.internal.core.JavaProject)selectedObject.getFirstElement();
				System.err.println(project.getPath());
				
				try {
					
					targetProjectFileSystemFullPath=project.getCorrespondingResource().getLocation().toString();
				} catch (JavaModelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				System.err.println("Es un "+targetProjectFileSystemFullPath);
			
			//System.err.println(selectedObject.getClass().getName());
			boolean result = MessageDialog.openQuestion(
					shell,
					"Updating libraries",
			"This action will replace your current IDK libraries with the latest installed in the editor. Do you want to proceed?");
			if (result){
				System.err.println(part.getSite().getSelectionProvider().getSelection());
				
				NullProgressMonitor monitor =new NullProgressMonitor();

				System.err.println("copiando...........");

				try {
					updateFile(targetProjectFileSystemFullPath,	project, monitor,"lib/modiaf.jar","/lib/modiaf.jar");
					updateFile(targetProjectFileSystemFullPath,	project, monitor,"lib/Base64.jar" ,"/lib/Base64.ja");
					updateFile(targetProjectFileSystemFullPath,	project, monitor,"lib/commons-codec-1.3.jar" ,"/lib/commons-codec-1.3.jar");
					updateFile(targetProjectFileSystemFullPath,	project, monitor,"lib/ingeniaseditor.jar" ,"/lib/ingeniaseditor.jar");
					updateFile(targetProjectFileSystemFullPath,	project, monitor,"lib/jdom.jar" ,"/lib/jdom.jar");
					updateFile(targetProjectFileSystemFullPath,	project, monitor,"lib/swixml.jar" ,"/lib/swixml.jar");
					updateFile(targetProjectFileSystemFullPath,	project, monitor,"lib/xercesImpl.jar" ,"/lib/xercesImpl.jar");
					updateFile(targetProjectFileSystemFullPath,	project, monitor,"lib/http.jar" ,"/lib/http.jar");
					updateFile(targetProjectFileSystemFullPath,	project, monitor,"lib/iiop.jar" ,"/lib/iiop.jar");
					updateFile(targetProjectFileSystemFullPath,	project, monitor,"lib/jade.jar" ,"/lib/jade.jar");
					updateFile(targetProjectFileSystemFullPath,	project, monitor,"lib/junit.jar" ,"/lib/junit.jar");
					updateFile(targetProjectFileSystemFullPath,	project, monitor,"lib/jadeTools.jar" ,"/lib/jadeTools.jar");
					updateFile(targetProjectFileSystemFullPath,	project, monitor,"lib/jgraph.jar" ,"/lib/jgraph.jar");
					
					IContainer container = (IContainer) project.getResource();
					try {
						container.refreshLocal(IResource.DEPTH_INFINITE, monitor);
					} catch (CoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				/*	Set<IClasspathEntry> entries = new HashSet<IClasspathEntry>();
					//	entries.addAll(Arrays.asList(project.getRawClasspath()));	
					System.err.println("antes .............");
					try {
						entries.add(JavaRuntime.getDefaultJREContainerEntry());
					} catch (Throwable e) {
						e.printStackTrace();
					}
					System.err.println("despues .............");
					
					try {
						project.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), null);
					} catch (JavaModelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // forces project refresh*/

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JavaModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.err.println("finalizado...........");
			}
			}
		}
	}

	private IPath updateFile(String targetProjectFileSystemFullPath,
			org.eclipse.jdt.internal.core.JavaProject project,
			NullProgressMonitor monitor, String relPathInPlugin, String relPathInTarget) throws MalformedURLException,
			IOException, JavaModelException {
		IPath path = FileCopy.copyFile(monitor, targetProjectFileSystemFullPath,"lib/modiaf.jar","/lib/modiaf.jar");
		return path;
	}
	
	public void addJarToClassPath(IJavaProject javaProject, IPath result)
	throws MalformedURLException, IOException, JavaModelException {
		IClasspathEntry[] oldEntries= javaProject.getRawClasspath();
		IClasspathEntry[] newEntries=
			new IClasspathEntry[oldEntries.length + 1];
		System.arraycopy(oldEntries, 0, newEntries, 0,
				oldEntries.length);
		System.err.println("creating jar library "+result.toString());
		newEntries[oldEntries.length]=
			JavaCore.newLibraryEntry(result, null, null);
		System.err.println("adding jar "+result.toString());
		javaProject.setRawClasspath(newEntries, null);
		System.err.println("added "+result.toString());
	}

	

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
