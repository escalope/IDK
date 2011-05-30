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

package ingenias.plugin.wizard;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IPluginRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.ui.wizards.NewJavaProjectWizardPageOne;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.ide.IDE;




public class IDKProjectCreationWizard extends Wizard implements INewWizard {	
	private IDKProjectPageOne projectNamePage;
	//private org.eclipse.jdt.ui.wizards.NewJavaProjectWizardPageTwo foldersAndClassPathPage;
	private ISelection selection;
	private IWorkbench workbench;

	/**
	 * Constructor for SampleNewWizard.
	 */
	public IDKProjectCreationWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		projectNamePage = new IDKProjectPageOne();


		//foldersAndClassPathPage = new NewJavaProjectWizardPageTwo(projectNamePage);		

		addPage(projectNamePage);

		//		addPage(foldersAndClassPathPage);

	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	public boolean performFinish() {
		//final String prName = page.getContainerName();
		final String fileName = projectNamePage.getProjectName();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(fileName, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * The worker method. It will find the container, create the
	 * file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */

	private void doFinish(

			String projectName,
			IProgressMonitor monitor)
	throws CoreException, MalformedURLException, IOException {
		try   {
			if (projectNamePage.isPageComplete()){

				String pname=projectNamePage.getProjectName();

				IWorkspaceRoot root= ResourcesPlugin.getWorkspace().getRoot();
				IProject resourceProject= root.getProject(pname);
				if(resourceProject.exists()){
					return;
				}
				resourceProject.create(null);
				resourceProject.open(null);


				setJavaNature(resourceProject);

				IJavaProject project = JavaCore.create(resourceProject);

				IContainer container = (IContainer) project.getResource();

				IResource resource = root.findMember(new Path(projectName)); 
				
				project.save(monitor,true);

				// Create simple folders
				IFolder binFolder=createFolder(monitor, container, "bin");

				Set<IClasspathEntry> entries = new HashSet<IClasspathEntry>();
				//	entries.addAll(Arrays.asList(project.getRawClasspath()));	
				System.err.println("antes .............");
				try {
					entries.add(JavaRuntime.getDefaultJREContainerEntry());
				} catch (Throwable e) {
					e.printStackTrace();
				}
				System.err.println("despues .............");

				project.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), null);

				IClasspathEntry[] rcps = project.getRawClasspath();
				for (IClasspathEntry rcp:rcps){
					System.err.println("path anterior:"+rcp.getPath());
				}

				//project.setRawClasspath(new IClasspathEntry[0], null);

				project.setOutputLocation(binFolder.getFullPath(), monitor);


				createFolder(monitor, container, "lib");
				createFolder(monitor, container, "spec");
				createSourceFolder(project.getProject(),project, "permsrc");
				createSourceFolder(project.getProject(),project, "gensrc");
				createSourceFolder(project.getProject(),project, "src");
				createFolder(monitor, container, "config");
				createFolder(monitor, container, "html");
				createFolder(monitor, container, "jade");

				monitor.beginTask("Creating " + projectName, 3);


				// Create a Project		

				if(!project.exists()){
					project.getProject().create(monitor);
				}
				project.open(monitor);

				
				// create the spec file
				final IFile file = container.getFile(new Path("spec/specification.idk"));
				try {
					InputStream stream = openContentStream(projectName);
					if (file.exists()) {
						file.setContents(stream, true, true, monitor);
					} else {
						file.create(stream, true, monitor);
					}
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				monitor.worked(1);
				monitor.setTaskName("Opening file for editing...");
				getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						IWorkbenchPage page =
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
						try {
							IDE.openEditor(page, file, true);
						} catch (PartInitException e) {
							e.printStackTrace();
						}
					}
				});
				monitor.worked(1);
				
				String targetFileLocation=container.getLocation().toString();
				// Copy Files
				int selectedTemplate = projectNamePage.getAvailableTemplates();
				System.err.println("Seleccionado "+selectedTemplate);
				switch (selectedTemplate){
				case 0:
					FileCopy.copyFile(monitor, targetFileLocation,"lib/exampleHelloWorld.idk","/spec/specification.idk");
					break;
				case 1:
					FileCopy.copyFile(monitor, targetFileLocation,"lib/exampleInteraction.idk","/spec/specification.idk");
					break;
				case 2:
					FileCopy.copyFile(monitor, targetFileLocation,"lib/exampleGUIAgent.idk","/spec/specification.idk");
					break;
				default:
					break;
				}			

				


				FileCopy.copyFile(monitor, targetFileLocation,"lib/generate.xml","/generate.xml");		
				FileCopy.copyFile(monitor, targetFileLocation,"lib/Properties.prop" ,"/config/Properties.prop");

				IPath path = FileCopy.copyFile(monitor, targetFileLocation,"lib/modiaf.jar","/lib/modiaf.jar");
				addJarToClassPath(project,path);


				path=FileCopy.copyFile(monitor, targetFileLocation,"lib/Base64.jar" ,"/lib/Base64.ja");
				addJarToClassPath(project,path);

				path=FileCopy.copyFile(monitor, targetFileLocation,"lib/commons-codec-1.3.jar" ,"/lib/commons-codec-1.3.jar");
				addJarToClassPath(project,path);

				path=FileCopy.copyFile(monitor, targetFileLocation,"lib/ingeniaseditor.jar" ,"/lib/ingeniaseditor.jar");
				addJarToClassPath(project,path);

				path=FileCopy.copyFile(monitor, targetFileLocation,"lib/jdom.jar" ,"/lib/jdom.jar");

				addJarToClassPath(project,path);
				path=FileCopy.copyFile(monitor, targetFileLocation,"lib/swixml.jar" ,"/lib/swixml.jar");

				addJarToClassPath(project,path);
				path=FileCopy.copyFile(monitor, targetFileLocation,"lib/xercesImpl.jar" ,"/lib/xercesImpl.jar");

				addJarToClassPath(project,path);
				path=FileCopy.copyFile(monitor, targetFileLocation,"lib/http.jar" ,"/lib/http.jar");

				addJarToClassPath(project,path);
				path=FileCopy.copyFile(monitor, targetFileLocation,"lib/iiop.jar" ,"/lib/iiop.jar");

				addJarToClassPath(project,path);

				path=FileCopy.copyFile(monitor, targetFileLocation,"lib/jade.jar" ,"/lib/jade.jar");
				addJarToClassPath(project,path);

				path=FileCopy.copyFile(monitor, targetFileLocation,"lib/junit.jar" ,"/lib/junit.jar");

				addJarToClassPath(project,path);
				path=FileCopy.copyFile(monitor, targetFileLocation,"lib/jadeTools.jar" ,"/lib/jadeTools.jar");	

				addJarToClassPath(project,path);
				path=FileCopy.copyFile(monitor, targetFileLocation,"lib/jgraph.jar" ,"/lib/jgraph.jar");

				addJarToClassPath(project,path);





				project.save(monitor,true);
				Runnable changePerspective=new Runnable(){
					public void run(){

						IWorkbench workbench = PlatformUI.getWorkbench();
						try {
							workbench.showPerspective(
									"ingenias.plugin.perspective.IDKPerspective", 
									workbench.getActiveWorkbenchWindow());
							System.err.println("PErspectiva cambiada");
						} catch (WorkbenchException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				// code extracted from http://www.stateofflow.com/journal/66/creating-java-projects-programmatically
				// by Channing Walton 

				container.refreshLocal(IResource.DEPTH_INFINITE, monitor);
				getShell().getDisplay().asyncExec(changePerspective);
				


			}
		} catch (Throwable t){
			t.printStackTrace();
		}

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

	private void addSystemLibraries(IJavaProject javaProject) throws JavaModelException {
		IClasspathEntry[] oldEntries= javaProject.getRawClasspath();
		IClasspathEntry[] newEntries=
			new IClasspathEntry[oldEntries.length + 1];
		System.arraycopy(oldEntries, 0, newEntries, 0,
				oldEntries.length);
		newEntries[oldEntries.length]=
			org.eclipse.jdt.launching.JavaRuntime.getDefaultJREContainerEntry();
		javaProject.setRawClasspath(newEntries, null);
	}

	private void setJavaNature(IProject project) throws CoreException {
		IProjectDescription description= project.getDescription();
		description.setNatureIds(new String[] { JavaCore.NATURE_ID });
		project.setDescription(description, null);
	}


	private IPackageFragmentRoot createSourceFolder(IProject project, IJavaProject javaProject, String name)
	throws CoreException {
		IFolder folder= project.getFolder(name);
		folder.create(false, true, null);
		System.err.println("created");
		IPackageFragmentRoot root=
			javaProject.getPackageFragmentRoot(folder);

		IClasspathEntry[] oldEntries= javaProject.getRawClasspath();
		IClasspathEntry[] newEntries=
			new IClasspathEntry[oldEntries.length + 1];
		System.arraycopy(oldEntries, 0, newEntries, 0,
				oldEntries.length);

		newEntries[oldEntries.length]=
			JavaCore.newSourceEntry(root.getPath(),new IPath[]{root.getParent().getPath()});
		System.err.println("creating classpath "+root.getPath());
		javaProject.setRawClasspath(newEntries, null);
		return root;
	}

	/** Create a folder **/
	private IFolder createFolder(IProgressMonitor monitor, IContainer container,
			String folderName) throws CoreException {
		IFolder folder=container.getFolder(new Path(folderName));		
		if (!folder.exists()) {
			folder.create(true, true, monitor);
		}		
		monitor.worked(1);
		return folder;
	}

	



	/**
	 * We will initialize file contents with a sample text.
	 */

	private InputStream openContentStream(String projectName) {
		String contents =
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
			"<project cid=\"0\" version=\"1.1\">\n"+
			"<projectproperties>\n"+
			"<projectproperty id=\"jadeout\" module=\"Ingenias Agent Framework generator\"  name=\"JADE generated output folder\" value=\"gensrc\"  tooltip=\"The folder that will hold generated JADE agents\" />\n"+
			"<projectproperty id=\"proysrc\" module=\"Ingenias Agent Framework generator\"  name=\"Main source folder for the project\" value=\"src\"  tooltip=\"The folder containing the sources of the project\" />\n"+
			"<projectproperty id=\"jadeproject\" module=\"Ingenias Agent Framework generator\"  name=\"JADE main project folder\" value=\""+projectName+"\"  tooltip=\"The folder that will contain the project for this development\" />\n"+
			"<projectproperty id=\"jadeperm\" module=\"Ingenias Agent Framework generator\"  name=\"JADE generate only once folder\" value=\"permsrc\"  tooltip=\"The folder that will hold generated elements that should not be regenerated\" />\n"+
			" <projectproperty id=\"htmldoc\" module=\"HTML Document generator\"  name=\"HTML document folder\" value=\""+projectName+"/html\"  tooltip=\"The document folder that will contain HTML version of this specification\" />\n"+
			"</projectproperties>\n"+
			"<leafpackages>\n"+
			"</leafpackages>\n"+
			"<objects>\n"+
			"</objects>\n"+
			"<relationships>\n"+
			"</relationships>\n"+
			"<models>\n"+ 
			"</models>\n"+
			"</project>\n";	
		return new ByteArrayInputStream(contents.getBytes());
	}

	private void throwCoreException(String message) throws CoreException {
		new Exception().printStackTrace();
		IStatus status =
			new Status(IStatus.ERROR, "p1", IStatus.OK, message, null);
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
		this.workbench=workbench;
	}
}