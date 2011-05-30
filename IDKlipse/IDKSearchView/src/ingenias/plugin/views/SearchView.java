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


package ingenias.plugin.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import ingenias.editor.GUIResources;
import ingenias.editor.GraphManager;
import ingenias.editor.IDEState;
import ingenias.editor.ModelJGraph;
import ingenias.editor.ObjectTreeMenuEntries;
import ingenias.editor.ProjectTreeRenderer;
import ingenias.editor.actions.HyperlinkAction;
import ingenias.editor.events.DiagramChangeHandler;
import ingenias.editor.events.DiagramCreationAction;
import ingenias.editor.widget.DnDJTree;



import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.part.*;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IPluginRegistry;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.jgraph.JGraph;

import ingenias.plugin.editor.Activator;
import ingenias.plugin.editor.DiagramEditor;
import ingenias.plugin.editor.DiagramInput;
import ingenias.plugin.editor.X11Patch;



public class SearchView extends ViewPart {
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();


	}


	//	private DrillDownAdapter drillDownAdapter;
	private Action doubleClickAction;
	private IPartListener partListener;
	private DiagramEditor currentEditor=null;	
	private DefaultMutableTreeNode root;

	private JPanel mainPanel;

	private Frame frame; 

	private Composite composite;

	private Vector<Action> actions=new Vector<Action>();
	private DiagramInput input;
	public DiagramInput getInput() {
		return input;
	}

	ResourceBundle resourceBundle;
	private JEditorPane searchPane;


	/**
	 * The constructor.
	 */
	public SearchView() {
		final SearchView self=this;

		

		partListener = new IPartListener() {
			private HyperlinkListener listener;

			private void trackOpenChatEditors(IWorkbenchPart part) {
				if (! (part instanceof DiagramEditor))
					return;

				if (part!=currentEditor){ // It must be exactly the same object
					DiagramEditor editor = (DiagramEditor) part;
					currentEditor=editor;
					input = (ingenias.plugin.editor.DiagramInput) editor.getModel();
					root=input.getRoot();
					if (listener!=null)
						searchPane.removeHyperlinkListener(listener);
					updateResources();
					listener=new HyperlinkAction(input.getIDEState(),input.getResources());
					searchPane.addHyperlinkListener(listener);
					Runnable modifyGUI=new Runnable(){
						public void run(){
							System.err.println("agregando controles");								
							/*		if (viewer!=null)
							viewer.refresh();*/				
							makeActions();
							hookContextMenu();
							contributeToActionBars();		
						}
					};


					getSite().getShell().getDisplay().asyncExec(modifyGUI);
				}

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
				if (part==currentEditor){
					searchPane.setText("<html>Please, open an idk file from the project</html>");
					searchPane.removeHyperlinkListener(listener);
					currentEditor=null;	
					
				}

			}

			@Override
			public void partDeactivated(IWorkbenchPart arg0) { // Lost focus

				System.err.println("DEACTIVATED....");
			}

			@Override
			public void partOpened(IWorkbenchPart part) {
				trackOpenChatEditors(part);
			}		  
		};
	}

	private void updateResources(){
		input.getResources().setSearchDiagramPanel(searchPane);	
	}

	
	public void createPartControl(Composite parent) {
		System.err.println("creando control tree");
		composite = new Composite(parent, SWT.EMBEDDED| SWT.NO_BACKGROUND| SWT.FILL);
		frame = SWT_AWT.new_Frame(composite);
		X11Patch.getInstance().applyPatch();
		mainPanel=new JPanel(new BorderLayout());		
		frame.add(mainPanel);		
		mainPanel.setBackground(Color.white);
		 searchPane = new JEditorPane();
		 searchPane.setContentType("text/html");
		 searchPane.setEditable(false);
		mainPanel.add(new JScrollPane(searchPane));				
		getSite().getWorkbenchWindow().getPartService().addPartListener(partListener);
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				SearchView.this.fillContextMenu(manager);
			}
		});
		//Menu menu = menuMgr.createContextMenu(viewer.getControl());
		//	viewer.getControl().setMenu(menu);
		//		getSite().registerContextMenu(menuMgr, viewer);
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
		//	drillDownAdapter.addNavigationActions(manager);
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