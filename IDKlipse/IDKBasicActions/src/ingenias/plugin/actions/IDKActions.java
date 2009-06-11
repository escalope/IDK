//    IDKLipse is a plugin for the development of Multi-Agent Systems based 
//    on the INGENIAS Development Kit, which is distributed under the terms of 
//    the GPL.
//
//    Copyright (C) 2009  Jorge J. Gomez-Sanz & Ivan Garcia-Magarino
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

import java.io.PrintWriter;
import java.util.Properties;

import javax.swing.tree.TreePath;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.*;

import ingenias.codeproc.*;
import ingenias.editor.ProjectProperty;
import ingenias.generator.browser.BrowserImp;
import ingenias.module.*;
import ingenias.utils.applinker.*;


public class IDKActions {
	/** The default path of the specification **/
	public static final String SPEC_FILE="/spec/specification.xml";
	
	/** Generate the code with the IAF Framework **/
	public static void iaf(Shell shell){
		
		System.out.println("Generating Code...");

		//Obtaining the path of the project selected  
		IProject project = getSelectedProject(shell);
		if(project==null){
			return;
		}
		String pathProject=project.getLocation().toString();
		// Obtain the path of the specification
		String pathFile ="";// Global.getInstance().defaultSpec.get(pathProject);
		if(pathFile==null){
			pathFile=pathProject+SPEC_FILE;
		}
		
		// two arguments 
		final String args[]=new String[2];
		args[0]=pathFile;
		args[1]=pathProject;
		System.out.println("specfile:"+args[0]+"\njadeProject:"+args[1]);
		// one argument
		try {
			new Thread(){public void run(){
			try {
				//ingenias.codeproc.IAFGenerator.main(args); 
				// It does not call main to not let execute "System.exit(0)"
				ingenias.editor.Log.initInstance(new PrintWriter(System.out));
				IAFGenerator jadegen = new IAFGenerator(args[0]);
				Properties props = jadegen.getBrowser().getState().prop;
				jadegen.setProperty("jadeproject", args[1]); 
				for (Object key: props.keySet()){
					System.err.println(((ProjectProperty)props.get(key.toString())).key+":"+
							((ProjectProperty)props.get(key.toString())).value);
				};
				jadegen.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
			}}.run();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
	
	/** Set the selected specification as default **/
	public static void setDefault(Shell shell){
		//Obtaining the path of the project selected  		
		IProject project = getSelectedProject(shell);
		if(project==null){
			return;
		}		
		String pathProject=project.getLocation().toString();
		
		// Obtaining the path of the Spec File
		IFile file = getSelectedFile(shell);
		if(file==null){
			return;
		}		
		String pathFile=file.getLocation().toString();
		// Insert the default specification
		System.out.println("Setting as default specification:"+pathProject+","+
				pathFile);
		Global.getInstance().defaultSpec.put(pathProject, pathFile);	
	}

	
	/** Generate the HTML documentation of the selected project **/
	public static void html(Shell shell){
		
		System.out.println("Generating HTML...");

		//Obtaining the project 
		IProject project = getSelectedProject(shell);
		if(project==null){
			return;
		}
		String pathProject=project.getLocation().toString();
		
		// one argument
		final String[] args=new String[1];
		args[0]=pathProject+SPEC_FILE;
		try {
			new Thread(){public void run(){
			try {
				// It does not call main to not let execute "System.exit(0)"
				// ingenias.codeproc.HTMLDocumentGenerator.main(args);
				ingenias.editor.Log.initInstance(new PrintWriter(System.out));
				HTMLDocumentGenerator html = new HTMLDocumentGenerator(args[0]);
				html.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
			}}.run();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/** Generate the HTML documentation of the selected project **/
	public static void editor(Shell shell){
		
		System.out.println("Running the Editor ...");

		//Obtaining the project 
		IProject project = getSelectedProject(shell);
		if(project==null){
			return;
		}
		String pathProject=project.getLocation().toString();
		
		// one argument
		final String[] args=new String[1];
		args[0]=pathProject+SPEC_FILE;		
		try {
			new Thread(){public void run(){
			try {
				ingenias.editor.IDE.main(args);
			} catch (Exception e) {
				e.printStackTrace();
			}
			}}.run();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	
	
	
	/** Generate the HTML documentation of the selected project **/
	public static void uploader(Shell shell){
		
		System.out.println("Uploading the code ...");

		//Obtaining the project 
		IProject project = getSelectedProject(shell);
		if(project==null){
			return;
		}
		String pathProject=project.getLocation().toString();
		
		// one argument
		final String[] args=new String[1];
		args[0]=pathProject+SPEC_FILE;
		new Thread(){public void run(){
			try {
				ingenias.editor.Log.initInstance(new java.io.PrintWriter(System.err));
				CodeUploader uploader = new CodeUploader(args[0]);
				uploader.run();
				System.out.println("Code Uploaded");
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}}.run();
	}
	
	/** Generate the HTML documentation of the selected project **/
	public static void appLinker(Shell shell){
		
		System.out.println("Applinker ...");

		//Obtaining the project 
		IProject project = getSelectedProject(shell);
		if(project==null){
			return;
		}
		String pathProject=project.getLocation().toString();
		
		// one argument
		final String[] args=new String[1];
		args[0]=pathProject+SPEC_FILE;
		new Thread(){public void run(){
			try {
				AppLinker.main(args);
				System.out.println("AppLinker done.");
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}}.run();
	}
		
	
	
	
	    /** Return the selected project or null if no project is selected **/
		public static IProject getSelectedProject(Shell shell) {
	        ISelectionService service= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
	        ISelection selection= service.getSelection();
	        IProject project= getProject(selection);
	        if(project==null){
				MessageDialog.openInformation(
						shell,
						"Warning",
						"A project must be selected");
			}
	        return project;
	    }
		
		protected static IProject getProject(ISelection selection) {
	        if (selection instanceof IStructuredSelection && !selection.isEmpty()) {
	            IStructuredSelection ssel= (IStructuredSelection) selection;
	            if (ssel.size() > 1)
	                return null;
	            Object obj= ssel.getFirstElement();
	            if (obj.getClass().getName().equals("IJavaElement")) // instanceof IJavaElement
	                obj= ((IJavaElement) obj).getResource();
	            if (obj instanceof IResource) {
	                return ((IResource) obj).getProject();
	            }
	            if (obj instanceof JavaProject) {
	                return ((JavaProject) obj).getProject();
	            }
	        }
	        if (selection instanceof ITextSelection || selection == null) {
	            IEditorPart editorPart= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	            IEditorInput editorInput= editorPart.getEditorInput();

	            if (editorInput instanceof IFileEditorInput) {
	            	IFileEditorInput fileInput= (IFileEditorInput) editorInput;
	            	return fileInput.getFile().getProject();
	            }
	        }
	        return null;
	    }
		
		
		
		/** Return the selected File or null if no file is selected **/
		public static IFile getSelectedFile(Shell shell) {
	        ISelectionService service= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
	        ISelection selection= service.getSelection();
	        IFile file= getFile(selection);
	        if(file==null){
				MessageDialog.openInformation(
						shell,
						"Warning",
						"A File must be selected");
			}
	        return file;
	    }
		
		protected static IFile getFile(ISelection selection) {
	        
	        if(selection==null){
	        	//System.out.println("selction=null");	            
	        }
			if(selection instanceof ITreeSelection){
	        	//System.out.println("ITreeSelection");
	        	ITreeSelection treeSel = (ITreeSelection)selection;
	        	org.eclipse.jface.viewers.TreePath treePath=treeSel.getPaths()[treeSel.getPaths().length-1];
	        	Object obj=treePath.getLastSegment();
	        	//System.out.println(obj.getClass().getName());
	        	if(obj instanceof IFile){
	        		return ((IFile)obj);
	        	}else{
	        		return null;
	        	}
	        }else if (selection instanceof IStructuredSelection){
	        	//System.out.println("IStructuredSelection");
	        }else if (selection instanceof ITextSelection || selection == null) {
				//System.out.println("ITextSelection");
	            IEditorPart editorPart= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	            IEditorInput editorInput= editorPart.getEditorInput();
	            if (editorInput instanceof IFileEditorInput) {
	        	IFileEditorInput fileInput= (IFileEditorInput) editorInput;

	        	return fileInput.getFile();
	            }
	        }
	        return null;
	    }
		
}
