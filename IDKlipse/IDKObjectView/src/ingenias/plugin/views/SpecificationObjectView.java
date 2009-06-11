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

import ingenias.editor.IDEState;
import ingenias.editor.ModelJGraph;
import ingenias.editor.ObjectTreeMenuEntries;
import ingenias.editor.ProjectTreeRenderer;
import ingenias.editor.events.DiagramChangeHandler;
import ingenias.plugin.editor.DiagramEditor;
import ingenias.plugin.editor.DiagramInput;
import ingenias.plugin.editor.X11Patch;

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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IPluginRegistry;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;
import org.jgraph.JGraph;

public class SpecificationObjectView extends ViewPart {
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();


	}


	//	private DrillDownAdapter drillDownAdapter;
	private Action doubleClickAction;
	private IPartListener partListener;
	private DiagramEditor currentEditor=null;
	protected DiagramChangeHandler dhc;
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
	/*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */

	/**
	 * The constructor.
	 */
	public SpecificationObjectView() {
		final SpecificationObjectView self=this;

		dhc=new DiagramChangeHandler(){
			@Override
			public void addNewDiagram(ModelJGraph mjg) {
				currentEditor.setDirty();
			}

			@Override
			public void addNewPackage(Object[] arg0, String arg1) {
				currentEditor.setDirty();				
			}

			@Override
			public void diagramDeleted(ModelJGraph arg0) {
				currentEditor.setDirty();

			}

			@Override
			public void diagramPropertiesChanged(ModelJGraph arg0) {
				currentEditor.setDirty();
			
			}

			@Override
			public void diagramRenamed(ModelJGraph arg0) {
				currentEditor.setDirty();			
			}

			@Override
			public void packageRenamed(String arg0) {


				currentEditor.setDirty();

			}

			@Override
			public void otherChange() {
				//vcp.replicate(root,viewer,input.getArbolProyectos());
				//		if (viewer!=null)
				//			viewer.refresh();
				currentEditor.setDirty();

			}

		};


		partListener = new IPartListener() {
			private void trackOpenChatEditors(IWorkbenchPart part) {
				if (! (part instanceof DiagramEditor))
					return;

				if (part!=currentEditor){ // It must be exactly the same object
					DiagramEditor editor = (DiagramEditor) part;
					currentEditor=editor;

					input = (ingenias.plugin.editor.DiagramInput) editor.getModel();
					root=input.getRoot();

					Runnable modifyGUI=new Runnable(){
						public void run(){
							System.err.println("agregando controles");
							initializeWithModel(input.getIDEState());	

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
					mainPanel.removeAll();					
					mainPanel.add(new JLabel("<html>Please, open an idk file from the project</html>"));
					currentEditor=null;
					frame.pack();
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

	void objectTree_mouseClicked(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			JPopupMenu jp = new ObjectTreeMenuEntries(input.getIDEState(),input.getResources()).menuObjectTree(e);		
			TreePath tp = input.getIDEState().om.arbolObjetos.getSelectionPath();
			if (tp != null) {
				jp.show(input.getIDEState().om.arbolObjetos, e.getPoint().x, e.getPoint().y);
				mainPanel.repaint();
			}
		}
		else
			if (e.getClickCount() > 1) {
				TreePath tp = input.getIDEState().om.arbolObjetos.getSelectionPath();
				if (tp != null && tp.getPathCount() > 1) {
					JGraph jg = input.getIDEState().gm.getCurrent();
					javax.swing.tree.DefaultMutableTreeNode dmtn =

						(javax.swing.tree.DefaultMutableTreeNode) tp.getLastPathComponent();
					// jg.setSelectionCell(dmtnetUserObject());
					mainPanel.validate();

				}
			}
	}


	private String getNewName() {
		final InputDialog id = new InputDialog(getSite().getShell(),
				"Enter name",
				"Enter a name for the diagram",  
				null, new IInputValidator() {  
			public String isValid(String newText) {
				if (newText==null)
					return "Write down a name for the diagram";
				TreePath uo = currentEditor.getModel().getIDEState().gm.findModelTreePath(newText);
				if (uo!=null) {  
					return "There is another diagram with that name";  
				}  
				return null;  
			}  
		});  


		id.open();
		int returnCode = id.getReturnCode();
		if (returnCode==InputDialog.OK){
			return id.getValue();
		}
		return null;
	}			

	private void initializeWithModel(IDEState ids){
		mainPanel.removeAll();		
		JScrollPane panel = new JScrollPane(ids.om.arbolObjetos);
		input.getResources().setArbolObjetos(ids.om.arbolObjetos);
		ids.om.setFilteredOff(false);
		ids.om.arbolObjetos.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				objectTree_mouseClicked(e);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		ids.om.arbolObjetos.setCellRenderer(new ProjectTreeRenderer());
		mainPanel.add(panel, BorderLayout.CENTER);		
		frame.pack();
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		System.err.println("creando control tree");
		composite = new Composite(parent, SWT.EMBEDDED| SWT.NO_BACKGROUND| SWT.FILL);
		frame = SWT_AWT.new_Frame(composite);
		X11Patch.getInstance().applyPatch();
		mainPanel=new JPanel(new BorderLayout());		
		frame.add(mainPanel);		
		mainPanel.setBackground(Color.white);


		getSite().getWorkbenchWindow().getPartService().addPartListener(partListener);
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				SpecificationObjectView.this.fillContextMenu(manager);
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