package ingenias.testing.fest;

import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JTree;

import org.fest.swing.core.Robot;
import org.fest.swing.driver.BasicJTreeCellReader;
import org.fest.swing.fixture.ComponentFixtureExtension;
import org.fest.swing.fixture.ContainerFixture;
import org.jgraph.JGraph;

public class JGraphFixtureExtension extends ComponentFixtureExtension<JGraph, JGraphFixture> {
	
	 private String name;

	public JGraphFixtureExtension(String name) {
		this.name=name;
	}

	public static JGraphFixtureExtension jgraphWithName(String id) {
		    return new JGraphFixtureExtension(id);
	}  
	  
	  public JGraphFixture createFixture(Robot robot, Container root) {
		  JGraph calendar = robot.finder().findByName(root, name, JGraph.class, true);
		    return new JGraphFixture(robot, calendar);
		  }

}
