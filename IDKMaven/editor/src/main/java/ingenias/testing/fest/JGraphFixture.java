package ingenias.testing.fest;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import ingenias.editor.entities.Entity;
import ingenias.exception.NotFound;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.core.KeyPressInfo;
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

	public JGraphFixture withDGC(String name) {
		Object[] roots = target.getRoots();
		boolean found=false;
		StringBuffer currentEntities=new StringBuffer();
		for (int k=0;k<roots.length && !found;k++){
			
			if (roots[k] instanceof DefaultGraphCell){				
				DefaultGraphCell dgc=(DefaultGraphCell)roots[k];
				
				if (dgc.getUserObject()!=null && dgc.getUserObject() instanceof Entity){
					Entity ent=(Entity) dgc.getUserObject();
					found=ent.getId().equals(name);
					lastSelectedCell=dgc;
					currentEntities.append(ent.getId()+":"+ent.getType()+",");
				}
			}
		}
		assertTrue("There is no DGC with containing an entity with id "+name+". Known entities are: "+currentEntities, found);
		return this;
	}
	
	public JGraphFixture withOutDGC(String name) {
		Object[] roots = target.getRoots();
		boolean found=false;

		for (int k=0;k<roots.length && !found;k++){
			if (roots[k] instanceof DefaultGraphCell){
				DefaultGraphCell dgc=(DefaultGraphCell)roots[k];
				if (dgc.getUserObject()!=null && dgc.getUserObject() instanceof Entity){
					Entity ent=(Entity) dgc.getUserObject();
					found=ent.getId().equals(name);					
				}
			}
		}
		assertTrue("There is a DGC containing an entity with id "+name, !found);
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


	private Rectangle2D getBoundsCoordinates(DefaultGraphCell cell) {
		AttributeMap atts = target.getModel().getAttributes(cell);
		if (atts==null)
			return null;
		Rectangle2D bounds = GraphConstants.getBounds(atts);
		if (bounds==null)
			return null;
		return bounds;
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
	
	private Point getFreeCoordinates(DefaultGraphCell cell) {
		AttributeMap atts = target.getModel().getAttributes(cell);
		if (atts==null)
			return null;
		Rectangle2D bounds = GraphConstants.getBounds(atts);
		Point centerPoint = new Point((int)bounds.getMinX(),(int)bounds.getMinY());
		
		Object nextCell=null;
		
		do {
			nextCell=target.getNextCellForLocation(cell, centerPoint.x, centerPoint.y);
			if (nextCell!=null && nextCell!=cell){
				centerPoint.x=centerPoint.x+10;
				if (centerPoint.x>bounds.getMaxX()){
					centerPoint.x=(int) bounds.getMinX();
					centerPoint.y=centerPoint.y+5;
					if (centerPoint.y>bounds.getMaxY())
						nextCell=null;
				}
				
				
			}
		} while(nextCell!=null && nextCell!=cell);
		
	
	  return centerPoint;
	
	}
	
	private Rectangle2D getBounds(DefaultGraphCell cell) {
		AttributeMap atts = target.getModel().getAttributes(cell);
		if (atts==null)
			return null;
		Rectangle2D bounds = GraphConstants.getBounds(atts);
		return bounds;		
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
		Rectangle2D entitySize = getBoundsCoordinates(lastSelectedCell);
		Dimension targetSize= target.getSize();
		boolean possibleToFitIn=
				entitySize.getWidth()<targetSize.getWidth()
				&& entitySize.getHeight()<targetSize.getHeight();
		do{			
			destination=new Point(
					Math.max(
							(int)(java.lang.Math.random()*(target.getSize().width-100f)), 
							20),
							Math.max((int)(java.lang.Math.random()*(target.getSize().height-100f)), 20));

			distance=mindistanceToAll(destination);

		}  while (distance<allowedDistance*allowedDistance && 				
				((possibleToFitIn &&((destination.getX()+entitySize.getWidth()<targetSize.width))
						&& ((destination.getY()+entitySize.getHeight()<targetSize.height)))
						|| !possibleToFitIn));		
		robot.pressMouse(target, startingPoint);
		robot.moveMouse(target, destination);
		robot.releaseMouseButtons();		
		return this;		
	}
	
	
	public JGraphFixture moveToCenter() {
		assertTrue("There must be a selected cell with withDGC", lastSelectedCell!=null);
		Point startingPoint=getCentralCoordinates(lastSelectedCell);
		startingPoint.x=startingPoint.x-20; // to avoid central square
		startingPoint.y=startingPoint.y-20; // to avoid central square
		Rectangle2D entitySize = getBoundsCoordinates(lastSelectedCell);
		Point destination=new Point(
				(int)(target.getSize().width/2-entitySize.getWidth()/2),
				(int)(target.getSize().height/2-entitySize.getHeight()/2));
		
		
		robot.pressMouse(target, startingPoint);
		robot.moveMouse(target, destination);
		robot.releaseMouseButtons();		
		return this;		
	}

	/**
	 * Expand (if variation>0) or reduces (if variation<0) the size of the selected entity
	 * @return
	 */
	public JGraphFixture changeWidthBy(int variation) {
		assertTrue("There must be a selected cell with withDGC", lastSelectedCell!=null);
		Rectangle2D currentBounds = getBoundsCoordinates(lastSelectedCell);			
		float startingPointX=(float) (currentBounds.getMinX()+currentBounds.getWidth());
		float startingPointY=(float) (currentBounds.getMinY()+currentBounds.getHeight()/2);
		float destinationPointX=(float) (currentBounds.getMinX()+currentBounds.getWidth()+variation);
		float destinationPointY=(float) (currentBounds.getMinY()+currentBounds.getHeight()/2);

		Point starting=new Point((int)startingPointX,(int)startingPointY);
		Point destination=new Point((int)destinationPointX,(int)destinationPointX);			
		robot.pressMouse(target, starting);
		robot.moveMouse(target, destination);
		robot.releaseMouseButtons();		
		return this;		
	}

	/**
	 * Expand (if variation>0) or reduces (if variation<0) the size of the selected entity
	 * @return
	 */
	public JGraphFixture changeHeightBy(int variation) {
		assertTrue("There must be a selected cell with withDGC", lastSelectedCell!=null);
		Rectangle2D currentBounds = getBoundsCoordinates(lastSelectedCell);			
		float startingPointX=(float) (currentBounds.getMinX()+currentBounds.getWidth()/2);
		float startingPointY=(float) (currentBounds.getMinY()+currentBounds.getHeight());
		float destinationPointX=(float) (currentBounds.getMinX()+currentBounds.getWidth()/2);
		float destinationPointY=(float) (currentBounds.getMinY()+currentBounds.getHeight()+variation);

		Point starting=new Point((int)startingPointX,(int)startingPointY);
		Point destination=new Point((int)destinationPointX,(int)destinationPointY);			
		robot.pressMouse(target, starting);
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

	public JGraphFixture moveWithin() {
		assertTrue("There must be two previous invocations of selectOrigin and selectTarget", 
				originCell!=null && targetCell!=null);

		Point centerPointSource=getCentralCoordinates(originCell);
		Point centerPointTarget=getCentralCoordinates(targetCell);
		centerPointTarget.x=centerPointTarget.x-20;
		centerPointTarget.y=centerPointTarget.y-20;
		centerPointSource.x=centerPointSource.x-20;
		centerPointSource.y=centerPointSource.y-20;

		robot.pressMouse(target,centerPointSource);
		robot.moveMouse(target,centerPointTarget);
		robot.releaseMouseButtons();

		return this;			
		
	}

	public JPopupMenuFixture rightClick() {
		assertTrue("There must be a selected cell with withDGC", lastSelectedCell!=null);
		Point startingPoint=getFreeCoordinates(lastSelectedCell);	
		JPopupMenuFixture mf=new JPopupMenuFixture(robot, robot.showPopupMenu(target, startingPoint));
		return mf;
		
	}

	public void requireNotVisible() {
		assertTrue("There must be a selected cell with withDGC", lastSelectedCell!=null);		
		assertTrue("It should not be visible", !isVisible(lastSelectedCell));
	}
	
	public void requireVisible() {
		assertTrue("There must be a selected cell with withDGC", lastSelectedCell!=null);		
		assertTrue("It should be visible", isVisible(lastSelectedCell));
	}

	private boolean isVisible(DefaultGraphCell cell) {
		return this.target.getGraphLayoutCache().isVisible(cell);
	}

	public JGraphFixture isWithin() {
		assertTrue("There must be two previous invocations of selectOrigin and selectTarget", 
				originCell!=null && targetCell!=null);

		Rectangle2D boundsTarget = getBounds(targetCell);
		Rectangle2D boundsSource=getBounds(originCell);
		assertTrue("the source cell should be within the target cell",boundsTarget.contains(boundsSource));		
		return this;				
	}	


}
