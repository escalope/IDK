package ingenias.editor;

import ingenias.editor.entities.Entity;
import ingenias.editor.widget.DnDJTree;

import java.util.Vector;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;

/**
 * Created by IntelliJ IDEA.
 * User: idk
 * Date: 01-Mar-2009
 * Time: 12:17:32
 * To change this template use File | Settings | File Templates.
 */
public class GUIResources implements ProgressListener{

	private JTree arbolObjetos;

	private DnDJTree arbolProyectos;

	private JPanel buttonModelPanel;

	private JMenu codeGenerator;

	private ButtonToolBar commonButtons = null;

	private JCheckBoxMenuItem editOnMessages;
	private JCheckBoxMenuItem editPopUpProperties;
	private JRadioButtonMenuItem enableINGENIASView;
	private JRadioButtonMenuItem enableUMLView;
	private JMenu file;
	private JTextPane logs;
	private JFrame mainFrame;
	private JTabbedPaneWithCloseIcons messagespane;
	private JTextPane moduleOutput;
	private JProgressBar pbar;
	private JPanel pprin;
	private Vector<ProgressListener> progressListeners=new Vector<ProgressListener>();
	private JMenuItem save;
	private JMenuItem saveas;
	private JEditorPane searchDiagramPanel; 

	private JTextField searchField;

	private JMenu tools = null;

	public void addPropertiesPanel(String name,JPanel jp, Entity ent){
		if (this.messagespane.indexOfTab(name) < 0) {
			//JScrollPane scrolledit=new JScrollPane();		 
			//scrolledit.getViewport().removeAll();
			//scrolledit.getViewport().add(jp,null);
			messagespane.addTab(name,ProjectTreeRenderer.selectIconByUserObject(ent),jp);
		} else
			this.messagespane.setSelectedIndex(this.messagespane.indexOfTab(name));
	}

	public void focusPropertiesPane(String name){
		if (this.messagespane.indexOfTab(name) >= 0) 			  
			this.messagespane.setSelectedIndex(this.messagespane.indexOfTab(name));
	}

	public void focusSearchPane(){
		this.messagespane.setSelectedIndex(2);
	}

	public JTree getArbolObjetos() {
		return arbolObjetos;
	}

	public DnDJTree getArbolProyectos() {
		return arbolProyectos;
	}

	public JPanel getButtonModelPanel() {
		return buttonModelPanel;
	}

	public JMenu getCodeGenerator() {
		return codeGenerator;
	}

	public ButtonToolBar getCommonButtons() {
		return commonButtons;
	}

	public JCheckBoxMenuItem getEditOnMessages() {
		return editOnMessages;
	}
	public JCheckBoxMenuItem getEditPopUpProperties() {
		return editPopUpProperties;
	}
	public JRadioButtonMenuItem getEnableINGENIASView() {
		return enableINGENIASView;
	}
	public JRadioButtonMenuItem getEnableUMLView() {
		return enableUMLView;
	}
	public JMenu getFile() {
		return file;
	}
	public JTextPane getLogs() {
		return logs;
	}
	public JFrame getMainFrame() {
		return mainFrame;
	}
	public JTabbedPaneWithCloseIcons getMessagespane() {
		return messagespane;
	}
	public JTextPane getModuleOutput() {
		return moduleOutput;
	}

	public JPanel getPprin() {
		return pprin;
	}

	public JProgressBar getProgressBar() {
		return pbar;
	}


	public int getProgressBarValue() {
		if (pbar!=null){
			return pbar.getValue();
		}
		return 0;
	}

	public JMenuItem getSave() {
		return save;
	}

	public JMenuItem getSaveas() {
		return saveas;
	}

	public JEditorPane getSearchDiagramPanel() {
		return searchDiagramPanel;
	}

	public JTextField getSearchField() {
		return searchField;
	}

	public JMenu getTools() {
		return tools;
	}


	public void removePropertiesPane(String name){
		if (this.messagespane.indexOfTab(name) >= 0)
			this.messagespane.removeTabAt(this.messagespane.indexOfTab(name));
	}

	public void setArbolObjetos(JTree arbolObjetos) {
		this.arbolObjetos=arbolObjetos;

	}
	public void setArbolProyectos(DnDJTree arbolProyectos) {
		this.arbolProyectos=arbolProyectos;

	}


	public void setButtonModelPanel(JPanel buttonModelPanel) {
		this.buttonModelPanel=buttonModelPanel;

	}

	/**
	 *  Sets the changed attribute of the IDE class
	 */
	public  void setChanged() {
		save.setEnabled(true);
		saveas.setEnabled(true);

	}

	public void setCodeGenerator(JMenu codeGenerator) {
		this.codeGenerator=codeGenerator;

	}

	public void setCommonButtons(ButtonToolBar commonButtons) {
		this.commonButtons = commonButtons;
	}

	public void setCurrentProgress(int progress) {
		for (ProgressListener pl:progressListeners)
			pl.setCurrentProgress(progress);
		
	}

	public void setEditOnMessages(JCheckBoxMenuItem editOnMessages2) {
		this.editOnMessages=editOnMessages2;

	}

	public void setEditPopUpProperties(JCheckBoxMenuItem editPopUpProperties) {
		this.editPopUpProperties=editPopUpProperties;

	}

	public void setEnableINGENIASView(JRadioButtonMenuItem enableINGENIASView) {
		this.enableINGENIASView=enableINGENIASView;

	}

	public void setEnableUMLView(JRadioButtonMenuItem enableUMLView) {
		this.enableUMLView=enableUMLView;

	}

	public void setFile(JMenu file) {
		this.file = file;
	}


	public void setLogs(JTextPane logs) {
		this.logs=logs;

	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void setMessagespane(JTabbedPaneWithCloseIcons messagespane) {
		this.messagespane = messagespane;
	}

	public void setModuleOutput(JTextPane moduleOutput) {
		this.moduleOutput=moduleOutput;

	}

	public void setPprin(JPanel pprin) {
		this.pprin=pprin;

	}

	public void setProgressBar(JProgressBar pbar) {
		this.pbar=pbar;

	}

	

	public void setSave(JMenuItem save) {
		this.save=save;

	}

	public void setSaveas(JMenuItem saveas) {
		this.saveas = saveas;
	}

	public void setSaveAs(JMenuItem saveas) {
		this.saveas=saveas;

	}

	public void setSearchDiagramPanel(JEditorPane searchDiagramPanel) {
		this.searchDiagramPanel = searchDiagramPanel;
	}

	public void setSearchField(JTextField searchField) {
		this.searchField=searchField;

	}



	public void setTools(JMenu tools) {
		this.tools = tools;
	}

	/**
	 *  Sets the unChanged attribute of the IDE class
	 */
	public void setUnChanged() {
		save.setEnabled(false);
		saveas.setEnabled(true);
	}
	
	public void removeProgressListener(ProgressListener pl){
		this.progressListeners.remove(pl);
	}
	
	public void addProgressListener(ProgressListener pl){
		this.progressListeners.add(pl);
	}
}
