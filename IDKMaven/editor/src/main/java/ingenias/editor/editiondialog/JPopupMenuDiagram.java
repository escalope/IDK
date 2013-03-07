package ingenias.editor.editiondialog;

import ingenias.editor.CommonMenuEntriesActionFactory;
import ingenias.editor.GUIResources;
import ingenias.editor.IDE;
import ingenias.editor.ModelJGraph;
import ingenias.editor.actions.diagram.TasksAndGoalsModelActionsFactory;
import ingenias.editor.cell.NAryEdge;
import ingenias.editor.cell.TaskCell;
import ingenias.editor.entities.Entity;
import ingenias.editor.entities.Task;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import org.jgraph.graph.DefaultGraphCell;

public class JPopupMenuDiagram extends JDialog {
	
	public JPopupMenuDiagram() {
		BoxLayout verticalBox = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		this.getContentPane().setLayout(verticalBox);
	}
	
	
	public void addActionsWithScroll(Vector<AbstractAction> actions, String title){
		TitledBorder refineBorder = BorderFactory.createTitledBorder(title);
		JPanel refineanel=new JPanel(new GridLayout(3, actions.size()/3));
		for (AbstractAction aa:actions){
			refineanel.add(new JButton(aa));
		}		
		JScrollPane refineJSP=new JScrollPane(refineanel);
		refineJSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		refineJSP.setBorder(refineBorder);
		for (AbstractAction aa:actions){
			refineanel.add(new JButton(aa));
		}
		this.getContentPane().add(refineJSP);
	}
	
	public void addActionsWithoutScroll(Vector<AbstractAction> actions, String title){
		TitledBorder basicBorder = BorderFactory.createTitledBorder(title);
		JPanel basicPanel=new JPanel();
		for (AbstractAction aa:actions){
			basicPanel.add(new JButton(aa));
		}
		basicPanel.setBorder(basicBorder);
		this.getContentPane().add(basicPanel);
	}
	
	
	public static void main(String args[]){
		IDE ide=new IDE();
		TasksAndGoalsModelActionsFactory daf=new TasksAndGoalsModelActionsFactory(ide.getResources(),ide.getIds());
		Entity ent=new Task("uno");
		CommonMenuEntriesActionFactory af=
				new CommonMenuEntriesActionFactory(ide.getResources(),ide.getIds());
		DefaultGraphCell cell = new TaskCell((Task)ent);
		
		JPopupMenuDiagram menu=new JPopupMenuDiagram();
		
		/*menu.setTitle("Entity menu");	
		menu.addActionsWithoutScroll(
				daf.createChangeViewActions((DefaultGraphCell)cell,(ModelJGraph)null),"Notation Change");
		Vector<AbstractAction> vaction=new Vector<AbstractAction>();
		vaction.addAll(af.createCellActions((DefaultGraphCell)cell,(ModelJGraph)null));
		vaction.addAll(af.createEntityActions(ent));
		menu.addActionsWithoutScroll(
				vaction,"Basic Actions");
		menu.addActionsWithScroll(
				af.createCellRefinementActions(ent),"Refinement");*/
		
		
		menu.pack();
		menu.setVisible(true);
	}

}
