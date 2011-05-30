//    IDKLipse is a plugin for the development of Multi-Agent Systems based 
//    on the INGENIAS Development Kit, which is distributed under the terms of 
//    the GPL.
//
//    Copyright (C) 2009  Jorge J. Gomez-Sanz
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


package ingenias.plugin.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;

import ingenias.editor.DiagramPaneInitialization;
import ingenias.editor.GUIResources;
import ingenias.editor.GraphManager;
import ingenias.editor.IDEState;
import ingenias.editor.ImageLoader;
import ingenias.editor.ModelJGraph;
import ingenias.editor.ProjectMenuCreator;
import ingenias.editor.ProjectTreeRenderer;
import ingenias.editor.events.DiagramChangeHandler;
import ingenias.editor.events.DiagramCreationAction;
import ingenias.editor.widget.DnDJTree;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.core.JavaProject;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.*;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IPluginRegistry;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.Launch;
import org.eclipse.debug.internal.core.LaunchManager;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.ant.core.*;
import org.eclipse.ant.internal.ui.launchConfigurations.*;
import org.eclipse.ui.externaltools.internal.model.*;
import org.eclipse.debug.ui.*;
import org.eclipse.ant.core.*;


import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.*;
import org.eclipse.jdt.launching.*;

import ingenias.plugin.editor.Activator;
import ingenias.plugin.editor.DiagramEditor;
import ingenias.plugin.editor.DiagramInput;
import ingenias.plugin.editor.X11Patch;
import org.eclipse.jface.action.*;
import  org.eclipse.jdt.core.*;



/**
 * This file was produced from a eclipse plugin template 
 * 
 * @author jj
 *
 */

public class IDKAntRunView extends ViewPart {
	protected String CONSOLE_NAME = "IAF ant";
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();


	}

	private Action doubleClickAction;
	private IPartListener partListener;

	private DefaultMutableTreeNode root;

	private JPanel mainPanel;
	private Hashtable<String,TargetInfo> targetTable=new Hashtable<String,TargetInfo>();

	private Frame frame; 

	private Composite composite;

	private Vector<Action> actions=new Vector<Action>();


	ResourceBundle resourceBundle;
	private DefaultTreeModel dtm;
	private JTree runTasks;
	private AntRunner runner;
	private boolean x11ErrorHandlerFixInstalled;
	private String pathProject;
	private Composite parent;
	private IProject project;
	private Label titleLabel;
	private ImageIcon rootIcon;
	private ImageIcon execIcon;


	/**
	 * The constructor.
	 */
	public IDKAntRunView() {
		final IDKAntRunView self=this;

		partListener = new IPartListener() {
			private void trackOpenChatEditors(IWorkbenchPart part) {


				Runnable modifyGUI=new Runnable(){
					public void run(){
						System.err.println("agregando controles");
						initializeWithModel();
					}
				};


				getSite().getShell().getDisplay().asyncExec(modifyGUI);
			}


			@Override
			public void partActivated(IWorkbenchPart part) {
				trackOpenChatEditors(part);
			}

			@Override
			public void partBroughtToTop(IWorkbenchPart part) {
				trackOpenChatEditors(part);
			}

			@Override
			public void partClosed(IWorkbenchPart part) {				
				System.err.println("CLOSED....");
				trackOpenChatEditors(part);
			}

			@Override
			public void partDeactivated(IWorkbenchPart arg0) { // Lost focus
				/*vcp.clear();
				currentEditor=null;*/
				System.err.println("DEACTIVATED....");
			}

			@Override
			public void partOpened(IWorkbenchPart part) {
				trackOpenChatEditors(part);
			}		  
		};
	}


	private boolean fillInTreeWithTasks(){
		targetTable.clear();
		runner = new AntRunner();

		pathProject=project.getLocation().toString();
		if (new File(pathProject+"/build.xml").exists()){
			runner.setBuildFileLocation(pathProject+"/build.xml");
			titleLabel.setText("Project "+project.getName());
			TargetInfo[] targets;

			try {
				targets = runner.getAvailableTargets();
				for (TargetInfo target:targets){
					if (target.getName().startsWith("run")||target.getName().startsWith("debug")){
						DefaultMutableTreeNode node=new DefaultMutableTreeNode();
						node.setUserObject(target.getName());
						root.add(node);
						node.setParent(root); 
						targetTable.put(target.getName(),target);
					}

				}
				return true;
			} catch (CoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;


	}

	private void initializeWithModel(){
		IProject newProject = getSelectedProject(this.getSite().getShell());
		if (	((newProject!=null && project==null) || 
				(newProject!=null && !newProject.equals(project))) &&
				new File(newProject.getLocation().toString()+"/build.xml").exists()){
			project=newProject;
			mainPanel.removeAll();
			root=new DefaultMutableTreeNode("Run targets");
			fillInTreeWithTasks();

			dtm=new DefaultTreeModel(root);		
			runTasks=new JTree();
			//runTasks.setSelectionModel(new DefaultTreeSelectionModel());
			runTasks.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent arg0) {
					runTargetTree_mouseClicked(arg0);				
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {	}

				@Override
				public void mouseExited(MouseEvent arg0) {}

				@Override
				public void mousePressed(MouseEvent arg0) {	}

				@Override
				public void mouseReleased(MouseEvent arg0) {}

			});

			runTasks.setModel(dtm);

			JScrollPane panel = new JScrollPane(runTasks);


			if (rootIcon==null)
				try {
					rootIcon=new ImageIcon(ImageLoader.getImage(							
							findFileInPlugin(getSite().getPluginId(),"icons/folder.png").toFile().toString()));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (execIcon==null)
					try {
						execIcon=new ImageIcon(ImageLoader.getImage(

								findFileInPlugin(getSite().getPluginId(),"icons/exectask.png").toFile().toString()));
					} catch (MalformedURLException e) {

						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					runTasks.setCellRenderer(new javax.swing.tree.DefaultTreeCellRenderer(){

						@Override
						public Component getTreeCellRendererComponent(JTree tree,
								Object value, boolean selected, boolean expanded,
								boolean leaf, int row, boolean hasFocus) {
							ImageIcon img=null;
							super.getTreeCellRendererComponent(
									tree, value, selected,
									expanded, leaf, row,
									hasFocus); // to enable special rendering for showing selection to appear;
							if (row==0){
								img=rootIcon;

							} else
								img= execIcon;

							if (img!=null) {
								setIcon(img);
								setText(value.toString());
								return this;
							} else
								return super.getTreeCellRendererComponent(tree,value,selected,expanded,leaf,row,hasFocus);
						}

					});

					mainPanel.add(panel, BorderLayout.CENTER);		
					frame.pack();
		}
	}

	public void createPartControl(Composite parent) {
		this.parent=parent;		
		GridData gd=new GridData(GridData.FILL_VERTICAL|GridData.FILL_HORIZONTAL);		
		GridLayout gl=new GridLayout(1,true);		
		parent.setLayout(gl);
		titleLabel=new Label(parent, SWT.CENTER);
		titleLabel.setText("No project selected");
		System.err.println("creando control tree");
		composite = new Composite(parent, SWT.EMBEDDED| SWT.NO_BACKGROUND| SWT.FILL);
		composite.setLayoutData(gd);
		frame = SWT_AWT.new_Frame(composite);
		X11Patch.getInstance().applyPatch();
		mainPanel=new JPanel(new BorderLayout());		
		frame.add(mainPanel);		
		mainPanel.setBackground(Color.white);

		getSite().getWorkbenchWindow().getPartService().addPartListener(partListener);
	}




	private void contributeToActionBars() {

		IActionBars bars = getViewSite().getActionBars();
		cleanLocalPullDown(bars.getMenuManager());

		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void cleanLocalPullDown(IMenuManager manager) {

		manager.removeAll();	

	}

	private void fillLocalPullDown(IMenuManager manager) {
		for (Action action:actions){
			manager.add(action);	
		}
	}

	private void fillContextMenu(IMenuManager manager) {
		for (Action action:actions){
			manager.add(action);	
		}

		manager.add(new Separator());
		//drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		for (Action action:actions){
			manager.add(action);
		}
		manager.add(new Separator());
	}



	/**
	 * @see IActionDelegate#run(IAction)
	 */

	/** Return the selected project or null if no project is selected **/
	public static IProject getSelectedProject(Shell shell) {
		IProject project= null;
		if (PlatformUI.getWorkbench()!=null && PlatformUI.getWorkbench().getActiveWorkbenchWindow()!=null &&
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService()!=null){
			ISelectionService service= PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
			ISelection selection= service.getSelection();
			project= getProjectFromSelection(selection);
		} 
		return project;
	}
	protected static IProject getProjectFromSelection(ISelection selection) {
		if (selection instanceof IStructuredSelection && !selection.isEmpty()) {
			IStructuredSelection ssel= (IStructuredSelection) selection;
			if (ssel.size() > 1)
				return null;
			Object obj= ssel.getFirstElement();
			if (obj.getClass().getName().equals("IJavaElement")) // instanceof IJavaElement
				obj= ((IJavaElement) obj).getResource();
			if (obj instanceof IResource) {
				System.err.println("Fijado un recurso");
				return ((IResource) obj).getProject();
			}
			if (obj instanceof JavaProject) {
				System.err.println("Fijade un proyecto");
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



	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void runTargetTree_mouseClicked(MouseEvent e) {

		if (SwingUtilities.isRightMouseButton(e)) {
			JPopupMenu jpm=new JPopupMenu();
			jpm.add(new javax.swing.AbstractAction("Run"){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					launchSelectedTask();

				}



			});
			jpm.show(runTasks,e.getX(),e.getY());
		}
		else
			if (e.getClickCount() > 1) {				
				launchSelectedTask();
			}
	}


	private void launchSelectedTask() {
		TreePath tp =runTasks.getSelectionPath();
		if (tp!=null){
			DefaultMutableTreeNode
			dmtn = (DefaultMutableTreeNode) tp.getLastPathComponent();
			final Object uo = dmtn.getUserObject();
			System.err.println("Launching "+uo.toString()+ " "+targetTable.containsKey(uo.toString()));
			if (targetTable.containsKey(uo.toString())) {

				AntLaunchDelegate ald=new AntLaunchDelegate();

				ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();
				System.err.println("cuatro");
				TargetInfo ti=targetTable.get(uo.toString());
				ILaunchConfigurationType type =
					manager.getLaunchConfigurationType(IAntLaunchConfigurationConstants.ID_ANT_LAUNCH_CONFIGURATION_TYPE);
				String name= manager.generateUniqueLaunchConfigurationNameFrom(ti.getName());
				try {


					final ILaunchConfigurationWorkingCopy workingCopy = type.newInstance(null, name);	
					// idea extracted from http://lists.mysql.com/commits/35806?f=plain
					workingCopy.setAttribute("org.eclipse.ant.ui.DEFAULT_VM_INSTALL", true);
					workingCopy.setAttribute("process_factory_id", "org.eclipse.ant.ui.remoteAntProcessFactory");
					workingCopy.setAttribute("org.eclipse.jdt.launching.MAIN_TYPE", "org.eclipse.ant.internal.ui.antsupport.InternalAntRunner");
					// These are common configuration options which can be found anywhere in the web
					workingCopy.setAttribute( IDebugUIConstants.ATTR_LAUNCH_IN_BACKGROUND,
							true);
					workingCopy.setAttribute( IDebugUIConstants.ATTR_PRIVATE,
							true); 
					workingCopy.setAttribute( IExternalToolConstants.ATTR_SHOW_CONSOLE,
							true); 
					workingCopy.setAttribute( DebugPlugin.ATTR_CAPTURE_OUTPUT, true); 
					workingCopy.setAttribute(IExternalToolConstants.ATTR_LOCATION,
							pathProject+"/build.xml");
					workingCopy.setAttribute(IAntLaunchConfigurationConstants.ATTR_ANT_TARGETS ,ti.getName());


					String LOGFILE_LOC="-logfile "+pathProject+"/myantlog"+name;
					Set set=new  HashSet();
					set.add(ILaunchManager.RUN_MODE);

					workingCopy.setPreferredLaunchDelegate(set, "org.eclipse.ant.ui.internal.launchConfigurations.AntLaunchDelegate");
					System.err.println("delegates ..."+workingCopy.getPreferredDelegate(set));



					final ILaunch result = workingCopy.launch(
							ILaunchManager.RUN_MODE,
							new NullProgressMonitor(),true,true);

				} catch (CoreException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	private IConsole findConsole(String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (name.equals(existing[i].getName()))
				return (MessageConsole) existing[i];
		//no console found, so create a new one
		MessageConsole myConsole = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[]{myConsole});
		return myConsole;
	}


	private IPath findFileInPlugin(String plugin, String file)
	throws MalformedURLException, IOException {

		IPluginRegistry registry= Platform.getPluginRegistry();
		IPluginDescriptor descriptor=
			registry.getPluginDescriptor(plugin);
		URL pluginURL= descriptor.getInstallURL();
		URL jarURL= new URL(pluginURL, file);
		URL localJarURL= Platform.asLocalURL(jarURL);
		return new Path(localJarURL.getPath());
	}

	private void makeActions() {

	}


	private void showMessage(String message) {
		MessageDialog.openInformation(
				composite.getShell(),
				"IDK View",
				message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		composite.setFocus();
	}

}