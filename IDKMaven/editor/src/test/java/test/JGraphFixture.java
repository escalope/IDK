package test;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

import ingenias.editor.entities.Entity;

import javax.swing.JPanel;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.core.KeyPressInfo;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.ComponentFixture;
import org.fest.swing.fixture.ContainerFixture;
import org.fest.swing.fixture.JPanelFixture;
import org.fest.swing.timing.Pause;
import org.jgraph.JGraph;
import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.swixml.converters.KeyEvent;
import org.testng.Assert;

public class JGraphFixture extends ComponentFixture<JGraph> {

	private DefaultGraphCell lastSelectedCell=null;
	private DefaultGraphCell originCell=null;
	private DefaultGraphCell targetCell=null;
	
	public JGraphFixture(Robot robot, Class<? extends JGraph> type) {
		super(robot, type);
		// TODO Auto-generated constructor stub
	}

	public JGraphFixture(Robot robot, JGraph target) {
		super(robot, target);
		// TODO Auto-generated constructor stub
	}

	public JGraphFixture(Robot robot, String name, Class<? extends JGraph> type) {
		super(robot, name, type);
	}
	
	public JGraphFixture withDGC(String name){
		Object[] roots = target.getRoots();
		boolean found=false;
		
		for (int k=0;k<roots.length && !found;k++){
			if (roots[k] instanceof DefaultGraphCell){
				DefaultGraphCell dgc=(DefaultGraphCell)roots[k];
				if (dgc.getUserObject()!=null && dgc.getUserObject() instanceof Entity){
					Entity ent=(Entity) dgc.getUserObject();
					found=ent.getId().equals(name);
					lastSelectedCell=dgc;
				}
			}
		}
		assertTrue("There is no DGC with containing an entity with id "+name, found);
		return this;
		
	}

	public JGraphFixture selectDGC() {
		if (lastSelectedCell!=null){
			robot.click(target,getCentralCoordinates(lastSelectedCell) );			
		} else
			Assert.fail("There is no selected DGC. Do select with withDGC method");
		/*Object[] roots = target.getRoots();
		Object selected=null;
		boolean found=false;
		for (int k=0;k<roots.length && !found;k++){
			if (roots[k] instanceof DefaultGraphCell){
				DefaultGraphCell dgc=(DefaultGraphCell)roots[k];
				if (dgc.getUserObject()!=null && dgc.getUserObject() instanceof Entity){
					Entity ent=(Entity) dgc.getUserObject();
					found=ent.getId().equals(name);
					selected=roots[k];
				}
			}
		}*/							
		return this;
	}
	
	public JGraphFixture selectOrigin() {
		assertTrue("There must be a selected cell with withDGC", lastSelectedCell!=null);			
		originCell=lastSelectedCell;
		return this;		
	}
	
	public JGraphFixture selectTarget() {
		assertTrue("There must be a selected cell with withDGC", lastSelectedCell!=null);			
		targetCell=lastSelectedCell;
		return this;		
	}
	
	public JGraphFixture drag() {
		assertTrue("There must be two previous invocations of selectOrigin and selectTarget", 
				originCell!=null && targetCell!=null);
		
		Point centerPointSource=getCentralCoordinates(originCell);
		Point centerPointTarget=getCentralCoordinates(targetCell);
		
		robot.pressMouse(target,centerPointSource);
		robot.moveMouse(target,centerPointTarget);
		robot.releaseMouseButtons();
		
		return this;		
	}
	
	private Point getCentralCoordinates(DefaultGraphCell cell) {
		AttributeMap atts = target.getModel().getAttributes(cell);
		if (atts==null)
			return null;
		Rectangle2D bounds = GraphConstants.getBounds(atts);
		if (bounds==null)
			return null;
		return new Point((int)bounds.getCenterX(),(int)bounds.getCenterY());
	}

	public JGraphFixture deleteSelectedDGC() {
		assertTrue("Before the call, a cell must have been selected first with withDGC and then with selectDGC", 
				lastSelectedCell!=null && target.getSelectionModel().isCellSelected(lastSelectedCell));
		robot.pressAndReleaseKey(KeyEvent.VK_DELETE);
		return this;
	}

	public JGraphFixture moveAway() {
		assertTrue("There must be a selected cell with withDGC", lastSelectedCell!=null);
		Point startingPoint=getCentralCoordinates(lastSelectedCell);
		startingPoint.x=startingPoint.x-20; // to avoid central square
		startingPoint.y=startingPoint.y-20; // to avoid central square
		Point destination=null;
		int allowedDistance=100;
		float distance=0;
		do{			
			destination=new Point(
					Math.max(
							(int)(java.lang.Math.random()*(target.getSize().width-100f)), 
									20),
					Math.max((int)(java.lang.Math.random()*(target.getSize().height-100f)), 20));
			
			distance=mindistanceToAll(destination);
		}  while (distance<allowedDistance*allowedDistance);		
		robot.pressMouse(target, startingPoint);
		robot.moveMouse(target, destination);
		robot.releaseMouseButtons();		
		return this;		
	}

	private float mindistanceToAll(Point destination) {
		Object[] roots = target.getRoots();
		boolean found=false;
		float minDistance=Float.MAX_VALUE;
		for (int k=0;k<roots.length && !found;k++){
			if (roots[k] instanceof DefaultGraphCell){
				DefaultGraphCell dgc=(DefaultGraphCell)roots[k];
				Point centerCoords=getCentralCoordinates(dgc);
				if (centerCoords!=null){
					float distance=(destination.x-centerCoords.x)*(destination.x-centerCoords.x) + (destination.y-centerCoords.y)*(destination.y-centerCoords.y);
					minDistance=Math.min(distance, minDistance);
				}				
			}
		}
		return minDistance;
	}

	public JGraphFixture addToSelectionDGC() {
		assertTrue("There must be a selected cell with withDGC", lastSelectedCell!=null);
		robot.pressKey(KeyEvent.VK_CONTROL);
		selectDGC();
		robot.releaseKey(KeyEvent.VK_CONTROL);
		return this;
	}

	public JGraphFixture copySelectedEntities() {
		robot.focus(target);
		robot.pressKey(KeyEvent.VK_CONTROL);
		robot.pressKey(KeyEvent.VK_C);
		robot.releaseKey(KeyEvent.VK_C);
		robot.releaseKey(KeyEvent.VK_CONTROL);
		return this;
		
	}
	
	public JGraphFixture pasteSelectedEntities() {
		robot.focus(target);
		robot.pressKey(KeyEvent.VK_CONTROL);
		robot.pressKey(KeyEvent.VK_V);
		robot.releaseKey(KeyEvent.VK_V);
		robot.releaseKey(KeyEvent.VK_CONTROL);	
		return this;
	}

	public JGraphFixture doubleClick() {
		assertTrue("There must be a selected cell with withDGC", lastSelectedCell!=null);
		Point startingPoint=getCentralCoordinates(lastSelectedCell);
		startingPoint.x=startingPoint.x-20; // to avoid central square
		startingPoint.y=startingPoint.y-20; // to avoid central square
		robot.click(target, startingPoint, org.fest.swing.core.MouseButton.LEFT_BUTTON, 2);		
		return this;
	}	
	

}
