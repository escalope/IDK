package ingenias.testing.fest;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import ingenias.editor.DraggableTabbedPane;
import ingenias.editor.entities.Entity;
import ingenias.exception.NotFound;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.core.KeyPressInfo;
import org.fest.swing.core.MouseButton;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.ComponentFixture;
import org.fest.swing.fixture.ContainerFixture;
import org.fest.swing.fixture.JPanelFixture;
import org.fest.swing.fixture.JPopupMenuFixture;
import org.fest.swing.timing.Pause;
import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.swixml.converters.KeyEvent;
import org.testng.Assert;

public class DraggableTabbedPaneFixture extends ComponentFixture<DraggableTabbedPane> {
	


	private Component selected;

	public DraggableTabbedPaneFixture(Robot robot, Class<? extends DraggableTabbedPane> type) {
		super(robot, type);
		// TODO Auto-generated constructor stub
	}

	public DraggableTabbedPaneFixture(Robot robot, DraggableTabbedPane target) {
		super(robot, target);
		// TODO Auto-generated constructor stub
	}

	public DraggableTabbedPaneFixture(Robot robot, String name, Class<? extends DraggableTabbedPane> type) {
		super(robot, name, type);
	}

	public DraggableTabbedPaneFixture withDraggableTabPane(String name) {
		selected=null;		
		for (int k=0;k<target.getTabCount();k++){			
			if (target.getTitleAt(k).equalsIgnoreCase(name)){
				selected = target.getTabComponentAt(k);
			}
		}				
		
		assertTrue("There is no tab with title "+name, selected!=null);
		return this;
	}
	
	public DraggableTabbedPaneFixture moveAway() {
		assertTrue("There is no selected tab", selected!=null);		
		Point startingPoint=selected.getLocation();
		startingPoint.x=startingPoint.x+20; // to avoid central square
		startingPoint.y=(int) (startingPoint.y+20); // to avoid central square
		Point destination=null;
		int allowedDistance=100;
		destination=new Point(target.getLocation().x,
				(int) (startingPoint.y));

		robot.click(selected);		
		robot.pressMouse(target, startingPoint);
		robot.pressMouse(target, startingPoint);
		robot.pressMouse(target, startingPoint);
		robot.moveMouse(target, destination);
		
		robot.releaseMouseButtons();
		
		return this;
	}
	


}
