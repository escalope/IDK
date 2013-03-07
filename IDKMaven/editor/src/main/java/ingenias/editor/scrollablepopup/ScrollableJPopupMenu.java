package ingenias.editor.scrollablepopup;

/*
 * 09/26/2009
 *
 * ScrollableJPopupMenu.java - A popup menu that will allow the user to scroll
 * through its contents if its contents get too long.
 * Copyright (C) 2009 Robert Futrell
 * http://fifesoft.com/rtext
 * Licensed under a modified BSD license.
 * See the included license file for details.
 */

import ingenias.editor.ImageLoader;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.beans.Transient;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.Timer;


/**
 * A <code>JPopupMenu</code> that allows the user to scroll through its
 * elements if there are more than a certain amount.  This is useful for menus
 * with (possibly) too many menu items to display on the user's screen
 * comfortably.<p>
 *
 * The user can define the maximum number of menu items to display.  If more
 * than this number of components are added to the menu, then "up" and "down"
 * arrows are added to the top and bottom of the menu, which can be used to
 * scroll through the larger list of items.  The mouse wheel can also be used
 * to scroll through the menu items.<p>
 *
 * If the number of menu items added is less than the threshold, this menu
 * acts just like a standard <code>JPopupMenu</code>.<p>
 *
 * This class is based off of code on Sun's Java forums, posted by
 * DarrylBurke
 * <a href="http://forums.sun.com/thread.jspa?forumID=57&threadID=5362822">here</a>.
 *
 * @author Robert Futrell
 * @version 1.0
 */
public class ScrollableJPopupMenu extends JPopupMenu {

	private int visibleRowCount;
	private List children;
	JMenuItem previousItem;
	JMenuItem nextItem;
	private static Icon upIcon;
	private static Icon downIcon;
	private Timer previousTimer = new Timer(DELAY, new MenuScrollAction(-1));
	private Timer nextTimer = new Timer(DELAY, new MenuScrollAction(1));
	private int firstItemIndex;

	private static final int DEFAULT_ROW_COUNT	= 30;
	private static final int DELAY				= 100;
	private static final String PROPERTY_TIMER	= "ScrollableJPopupMenu.timer";


	/**
	 * Constructor.
	 */
	public ScrollableJPopupMenu() {
		this(DEFAULT_ROW_COUNT);
	}


	/**
	 * Constructor.
	 *
	 * @param rowCount The number of rows that will be displayed before
	 *        scrolling arrows are drawn.
	 */
	public ScrollableJPopupMenu(int rowCount) {
		enableEvents(java.awt.AWTEvent.MOUSE_WHEEL_EVENT_MASK);
		visibleRowCount = rowCount;
		children = new ArrayList(rowCount);
		MenuMouseAdapter adapter = new MenuMouseAdapter();
		previousItem = new ArrowMenuItem(upIcon);
		previousItem.putClientProperty(PROPERTY_TIMER, previousTimer);
		previousItem.addMouseListener(adapter);
		nextItem = new ArrowMenuItem(downIcon);
		nextItem.putClientProperty(PROPERTY_TIMER, nextTimer);
		nextItem.addMouseListener(adapter);
		refresh();
	}


	/**
	 * This method should be used to add items to this popup menu, not the
	 * standard <code>add</code> methods.
	 *
	 * @param c The component to add.
	 */
	public void addComponent(Component c) {
		children.add(c);
	}
	
	


	@Override
	public JMenuItem add(JMenuItem menuItem) {
		addComponent(menuItem);
		return menuItem;
	}


	@Override
	public JMenuItem add(String s) {
		addComponent(new JLabel(s));
		return null;
	}


	@Override
	public JMenuItem add(Action a) {
		addComponent(new JMenuItem(a));
		return null;
	}


	/**
	 * Overridden so we can keep track of what is added to the popup menu.
	 */
	public void addSeparator() {
		addComponent(new JPopupMenu.Separator());
	}


	/**
	 * Overridden to apply the new orientation to our (possibly hidden)
	 * child components.
	 *
	 * @param o The new component orientation.
	 */
	public void applyComponentOrientation(ComponentOrientation o) {
		super.applyComponentOrientation(o);
		for (Iterator i=children.iterator(); i.hasNext(); ) {
			Component c = (Component)i.next();
			c.applyComponentOrientation(o);
		}
	}


	/**
	 * {@inheritDoc}
	 */
	public Dimension getPreferredSize() {
		refresh();
		return super.getPreferredSize();
	}


	/**
	 * Overridden to enable scrolling through the menu via the mouse wheel.
	 *
	 * @param e The event.
	 */
	protected void processMouseWheelEvent(MouseWheelEvent e) {
		if (getComponent(0)==previousItem) { // i.e., scroll arrows are visible
			int amt = e.getUnitsToScroll()>0 ? 1 : -1;
			firstItemIndex += amt;
			refresh();
		}
		super.processMouseWheelEvent(e);
	}


	private void refresh() {

		removeAll();
		int itemCount = children.size();

		if (itemCount>visibleRowCount) {

			// Determine the best width for the popup.
			for (int i=0; i<itemCount; i++) {
				super.add((Component)children.get(i));
			}
			int w = super.getPreferredSize().width;
			removeAll();

			firstItemIndex = Math.min(itemCount-visibleRowCount, firstItemIndex);
			firstItemIndex = Math.max(0, firstItemIndex);

			previousItem.setEnabled(firstItemIndex > 0);
			nextItem.setEnabled(firstItemIndex < itemCount - visibleRowCount);
			super.add(previousItem);
			for (int i=0; i<visibleRowCount && firstItemIndex+i<itemCount; i++) {
				super.add((Component)children.get(firstItemIndex + i));
			}
			super.add(nextItem);
			Dimension size = super.getPreferredSize();
			size.width = Math.max(w, upIcon.getIconWidth());		
			setSize(size);
			revalidate();
			repaint(); // Needed to refresh arrow menu items

		}

		else {
			for (int i=0; i<itemCount; i++) {
				super.add((Component)children.get(i));
			}
		}

	}


	/**
	 * {@inheritDoc}
	 */
	public void setVisible(boolean visible) {
		if (visible) {
			refresh();
		}
		super.setVisible(visible);
	}


	private static class ArrowMenuItem extends JMenuItem {

		private Icon arrowIcon;
		private Icon disabledArrowIcon;

		public ArrowMenuItem(Icon icon) {
			// We only temporarily set the menu item's standard icon, just so
			// we can use Swing to get our "disabled" version.  Then we clear
			// it so we can draw the icon ourselves, in the text area.
			super(icon);
			arrowIcon = icon;
			disabledArrowIcon = getDisabledIcon();
			setIcon(null);
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Icon icon = isEnabled() ? arrowIcon : disabledArrowIcon;
			int x = (getWidth() - icon.getIconWidth()) / 2;
			int y = (getHeight() - icon.getIconHeight()) / 2;
			icon.paintIcon(this, g, x, y);
		}

		@Override
		@Transient
		public Dimension getPreferredSize() {
			return new Dimension(arrowIcon.getIconWidth(),arrowIcon.getIconWidth());
			
		}

		
		

	}


	private static class MenuMouseAdapter extends MouseAdapter {
		   
		public void mouseEntered(MouseEvent e) {
			JMenuItem item = (JMenuItem)e.getSource();
			Timer timer = (Timer)item.getClientProperty(PROPERTY_TIMER);
			timer.start();
		}

		public void mouseExited(MouseEvent e) {
			JMenuItem item = (JMenuItem)e.getSource();
			Timer timer = (Timer)item.getClientProperty(PROPERTY_TIMER);
			timer.stop();
		}
	}


	private class MenuScrollAction extends AbstractAction {

		private int increment;

		public MenuScrollAction(int increment) {
			this.increment = increment;
		}

		public void actionPerformed(ActionEvent e) {
			firstItemIndex += increment;
			refresh();
		}

	}


	/*
	 * Load the icons shared among all scrollable popup menus.
	 */
	static {
		
		upIcon = new ImageIcon(ImageLoader.getImage("images/uparrow.gif"));
		
		downIcon = new ImageIcon(ImageLoader.getImage("images/downarrow.gif"));
	}
	
	public static void main(String args[]){
		JFrame jf=new JFrame();
	
		jf.getContentPane().add(new JLabel(ScrollableJPopupMenu.upIcon));
		jf.pack();
		jf.setLocation(200, 200);
		jf.setVisible(true);
		
		JPopupMenu jp=new ScrollableJPopupMenu(20);
		
		for (int k=0;k<50;k++)
		jp.add(new JMenuItem(new AbstractAction("hola"+k) {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("hllo");
				
			}
		}));
		
		jp.add(new JMenu("adios"));
		jp.setLocation(200, 200);
		jp.setVisible(true);
		
	}


}