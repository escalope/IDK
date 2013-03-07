package ingenias.testing.fest;

import ingenias.editor.DraggableTabbedPane;

import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JTree;

import org.fest.swing.core.Robot;
import org.fest.swing.driver.BasicJTreeCellReader;
import org.fest.swing.fixture.ComponentFixtureExtension;
import org.fest.swing.fixture.ContainerFixture;
import org.jgraph.JGraph;

public class DraggableTabbedPaneFixtureExtension extends 
	ComponentFixtureExtension<DraggableTabbedPane, DraggableTabbedPaneFixture> {
	
	 private String name;

	public DraggableTabbedPaneFixtureExtension(String name) {
		this.name=name;
	}

	public static DraggableTabbedPaneFixtureExtension draggableTabbedPaneWithName(String id) {
		    return new DraggableTabbedPaneFixtureExtension(id);
	}  
	  
	  public DraggableTabbedPaneFixture createFixture(Robot robot, Container root) {
		  DraggableTabbedPane calendar = robot.finder().findByName(root, 
				  name, DraggableTabbedPane.class, true);
		    return new DraggableTabbedPaneFixture(robot, calendar);
		  }

}
