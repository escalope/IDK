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

import ingenias.editor.ButtonToolBar;
import ingenias.editor.DiagramPaneInitialization;
import ingenias.editor.GUIResources;
import ingenias.editor.ModelJGraph;
import ingenias.editor.filters.FilterManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resources;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IPluginRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.jgraph.event.GraphModelEvent;

import com.mtp.gui.widget.Label;

/**
 * This class was created starting from a template of the Eclipse RCP
 * @author jj
 *
 */
public class DiagramEditor extends EditorPart {
	public static String ID = "p.editors.DiagramEditor";
	private Canvas transcript;
	private DiagramInput model=null;
	private Composite composite;
	private JPanel mainPanel;
	private com.languageExplorer.widgets.ScrollableBar buttons;
	private Frame frame; 
	private ButtonToolBar commonButtons;
	private boolean dirty=false;

	DiagramPaneInitialization dpi=null;
	private Composite leftPanel;
	private Composite rightPanel;
	private Frame leftFrame;
	private boolean x11ErrorHandlerFixInstalled;
	@Override
	public void doSave(IProgressMonitor arg0) {
		model.saveFile(model.getSpecFile());
		arg0.done();
		dirty=false;
		firePropertyChange(org.eclipse.ui.ISaveablePart.PROP_DIRTY);
	}

	@Override
	public void doSaveAs() {


	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
	throws PartInitException {
		setSite(site);
		setInput(input);
		

		if (input instanceof org.eclipse.ui.part.FileEditorInput &&
				(( org.eclipse.ui.part.FileEditorInput)input).getURI().getRawPath()!=null){
			org.eclipse.ui.part.FileEditorInput finput=
				( org.eclipse.ui.part.FileEditorInput)input;
			String file = finput.getURI().getRawPath();
			ingenias.editor.Log.initInstance(new java.io.PrintWriter(System.err));
			System.err.println(file);
			setPartName(file);
			model=new DiagramInput(file);		
			System.err.println("Cargando desde fichero");
			 
			setIAFFilter(site);			

		} else {
			model=new DiagramInput();
			System.err.println("creando vacio");
			setIAFFilter(site);		
		}
		
		if (model.getIDEState().editor.getGraph()!=null){
			setCurrentDiagram(model.getIDEState().editor.getGraph().getName());
		}
		
		model.getIDEState().editor.addGraphModelListener(new org.jgraph.event.GraphModelListener(){

			@Override
			public void graphChanged(GraphModelEvent arg0) {
				setDirty();

			}

		});

	}

	private void setIAFFilter(IEditorSite site) {
		InputStream xmlConfiguration;
		try {
			
			xmlConfiguration = findFileInPlugin(site.getPluginId(),"configs/iafFilter.xml").openStream();
			model.getIDEState().setDiagramFilter(FilterManager.obtainDiagramFilter(xmlConfiguration));	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setDirty(){
		dirty=true;	
		System.err.println("dirty");
		Runnable diagramChange=new Runnable(){
			public void run(){
				firePropertyChange(org.eclipse.ui.ISaveablePart.PROP_DIRTY);
			}};
			getEditorSite().getShell().getDisplay().asyncExec(diagramChange);
	}
	
	



	@Override
	public boolean isDirty() {

		return dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {

		return true;
	}

	@Override
	public void createPartControl(Composite parent) {
				
				

		
		 GridLayout gridLayout = new GridLayout();
	     gridLayout.numColumns = 2;
	     parent.setLayout(gridLayout);
	     
	   /*  Button browse = new Button(parent, SWT.PUSH);
	     browse.setText("File Browse");
	     //browse.addSelectionListener(this);
	     Button render = new Button(parent, SWT.PUSH);
	     render.setText("Render");
	     //render.addSelectionListener(this);
	     
	     GridData gridData = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL);
	     gridData.horizontalSpan = 2;
	     SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
	     sashForm.setLayoutData(gridData);
	     Browser left = new Browser(sashForm, SWT.NONE);
	     Browser right = new Browser(sashForm, SWT.NONE);
	     
	     parent.pack();*/
	
				
		//composite = new Composite(parent, SWT.EMBEDDED| SWT.NO_BACKGROUND| SWT.FILL);
	//	Layout layout=new FillLayout();
//		composite.setLayout(layout);
		GridData gridData;
		
		
		gridData=new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL);	
		gridData.grabExcessHorizontalSpace=false;
		gridData.grabExcessVerticalSpace=true;
		//composite.setLayout(layout);
		
		leftPanel= new Composite(parent, SWT.EMBEDDED| SWT.NO_BACKGROUND| SWT.FILL);
		leftPanel.setLayout(new FillLayout());
		
		gridData=new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL);	
		gridData.grabExcessHorizontalSpace=false;
		gridData.grabExcessVerticalSpace=true;
		gridData.widthHint=35;
		gridData.horizontalAlignment=GridData.HORIZONTAL_ALIGN_CENTER;
		
		leftPanel.setLayoutData(gridData);
		leftFrame = SWT_AWT.new_Frame(leftPanel);
		
		X11Patch.getInstance().applyPatch();

		
		
		JPanel buttonPanel = new JPanel(new BorderLayout());
		
		leftFrame.add(buttonPanel);		
		//leftPanel.setLayoutData(gridData);
		rightPanel= new Composite(parent,SWT.EMBEDDED| SWT.NO_BACKGROUND| SWT.FILL);
		/*rightPanel.setLayout(new FillLayout());
		//l=new org.eclipse.swt.widgets.Label(rightPanel,SWT.CENTER);
		//l.setText("adios");
		gridData=new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL);
		gridData.horizontalAlignment=GridData.FILL;
		gridData.grabExcessHorizontalSpace=true;
		gridData.grabExcessVerticalSpace=true;*/
		//rightPanel.setLayoutData(gridData);
		frame = SWT_AWT.new_Frame(rightPanel);
		gridData=new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);	
		gridData.grabExcessHorizontalSpace=true;
		gridData.grabExcessVerticalSpace=true;
		rightPanel.setLayoutData(gridData);
		parent.pack();
		mainPanel=new JPanel(new BorderLayout());		
		JScrollPane panel = new JScrollPane(model.getIDEState().editor);
						 
		frame.add(mainPanel);	
	//	mainPanel.add(panel,BorderLayout.CENTER);	
		
		model.getResources().setMainFrame(frame);
		model.getResources().setPprin(mainPanel);
		model.getResources().setButtonModelPanel(buttonPanel);
		model.getResources().setEditPopUpProperties(new JCheckBoxMenuItem("popup",true));
		model.getResources().setArbolObjetos(model.getIDEState().om.arbolObjetos);
		model.getResources().setArbolProyectos(model.getIDEState().gm.arbolProyecto);
		dpi=new DiagramPaneInitialization(model.getIDEState(),model.getResources());
		dpi.createEditorAndRelatedComponents(model.getIDEState());		
		//model.getIDEState().editor.setBackground(Color.YELLOW);
		getModel().getIDEState().editor.addTabSelectorChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent ce) {
				// Modifies the button bars when a new panel is selected
				if (  getModel().getIDEState().editor.getGraphPanel() != null &&
						( (JScrollPane)  getModel().getIDEState().editor.getGraphPanel().getSelectedComponent()).getViewport() != null &&
						( (JScrollPane)  getModel().getIDEState().editor.getGraphPanel().getSelectedComponent()).getViewport().
						getComponentCount() > 0) {
					ModelJGraph mjg = (ModelJGraph)
					( ( (JScrollPane)  getModel().getIDEState().editor.getGraphPanel().getSelectedComponent()).getViewport().
							getComponent(0));
					if ( getModel().getIDEState().editor.getGraphPanel().getTabCount() > 0) {

						setCurrentDiagram(mjg.getName());
					}
					setCurrentDiagram(mjg.getName());
					
				}
			}
		});
		/*for (ModelJGraph mjg:model.getIDEState().gm.getUOModels()){
			mjg.setUI(new ingenias.editor.editionmode.PopupCellEditor(model.getIDEState().om,model.getIDEState().gm,frame));
		}*/

		frame.setVisible(true);
		leftFrame.setVisible(true);
		
	}
	
	private URL findFileInPlugin(String plugin, String file)
	throws MalformedURLException, IOException {
		
		IPluginRegistry registry= Platform.getPluginRegistry();
		IPluginDescriptor descriptor=
			registry.getPluginDescriptor(plugin);
		URL pluginURL= descriptor.getInstallURL();
		URL jarURL= new URL(pluginURL, file);
	
		return jarURL;
	}

	@Override
	public void setFocus() {
		rightPanel.setFocus();
	}

	private void sendMessage() {

	}

	private String renderMessage(String from, String body) {
		if (from == null)
			return body;
		int j = from.indexOf('@');
		if (j > 0)
			from = from.substring(0, j);
		return "<" + from + "> " + body;
	}
	private void scrollToEnd() {

	}

	public DiagramInput getModel() {
		return model;
	}

	public void setCurrentDiagram(final String diagramName) {
		Runnable diagramChange=new Runnable(){


			public void run(){
				leftPanel.redraw();
				rightPanel.redraw();
				ModelJGraph mjg=model.getDiagram(diagramName);
				if (mjg!=null){
				
					model.getIDEState().editor.changeGraph(mjg);
					dpi.updateButtonBars();
					model.getIDEState().gm.setCurrent(mjg);
					SwingUtilities.invokeLater(new Runnable(){

						@Override
						public void run() {
							
						
						//	model.getResources().getMainFrame().pack(); // Other combinations, even composite.redraw, update, layout, etc do not seem to work
							leftFrame.pack();
							//resources.getPprin().invalidate();
							//resources.getPprin().validate();
							//resources.getPprin().repaint();
							//resources.getButtonModelPanel().invalidate();
							//resources.getMainFrame().setVisible(false);							
							//resources.getMainFrame().setVisible(true);
							//composite.update();
							//composite.redraw();
							//getEditorSite().getShell().update();							
							//resources.getMainFrame().doLayout();
							//resources.getMainFrame().repaint();
							
						}
						
					});
				
					//composite.layout();
					//
					//composite.redraw();
				
					//ingenias.generator.browser.BrowserImp.initialise(model.getIDEState());
				}
			}
		};

		getEditorSite().getShell().getDisplay().asyncExec(diagramChange);

	}

	@Override
	public boolean isSaveOnCloseNeeded() {
		return super.isSaveOnCloseNeeded();
	}

	public Frame getMainFrame() {
		return this.frame;

	}





}
