/*
 *  Copyright (C) 2002 Jorge Gomez Sanz
 *  This file is part of INGENIAS IDE, a support tool for the INGENIAS
 *  methodology, availabe at http://grasia.fdi.ucm.es/ingenias or
 *  http://ingenias.sourceforge.net
 *  INGENIAS IDE is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *  INGENIAS IDE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  You should have received a copy of the GNU General Public License
 *  along with INGENIAS IDE; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package ingenias.editor.cell;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.lang.reflect.*;

import ingenias.editor.entities.*;
import java.util.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

import ingenias.editor.Editor;
import ingenias.editor.IDEState;
import ingenias.editor.widget.*;
import java.io.*;
import java.awt.image.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Map;
import java.util.Hashtable;
import java.util.ArrayList;
import javax.swing.event.UndoableEditEvent;
import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.jgraph.event.*;
import java.util.Vector;
import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.jgraph.event.*;
import org.jgraph.plaf.basic.*;
import ingenias.editor.entities.*;
import ingenias.editor.cell.*;
//import ingenias.editor.auml.*;
import ingenias.exception.*;
import java.io.*;

/**
 *  Description of the Class
 *
 * @author     developer
 * @created    15 de enero de 2004
 */
public class GeneralEditionPanel
extends javax.swing.JPanel
implements java.io.Serializable {
	private Vector<ActionListener> undo=new Vector<ActionListener>();
	Entity entity;
	Hashtable ht;
	Editor editor;
	private Border border1;
	private TitledBorder titledBorder1;
	private JScrollPane jScrollPane1 = new JScrollPane();
	Vector<ActionListener> actionsForUpdatingEntities=new Vector<ActionListener>();
	private Frame parentFrame=null;
	
	private ingenias.editor.ObjectManager om = null;
	public static Image delImage;
	static {
		try {
			delImage =
				Toolkit.getDefaultToolkit().createImage("images/delete.gif");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private class CloseIconTitledBorder
	extends TitledBorder {
		Point location = new Point(0, 0);
		Dimension size = new Dimension(0, 0);
		
		public CloseIconTitledBorder(Border b, String title) {
			super(b, title);
		}
		
		public Point getLocation() {
			return location;
		}
		
		public Dimension getSize() {
			return size;
		}
		
		public void paintBorder(Component comp, Graphics g, int x1, int y1, int x2,
				int y2) {
			super.paintBorder(comp, g, x1, y1, x2, y2);
			int desp = 20;
			int orig = x2 - GeneralEditionPanel.delImage.getWidth(null) - desp;
			int fin = x2 - desp;
			g.setColor(Color.white);
			g.fillRect(orig - 2, 3,
					GeneralEditionPanel.delImage.getWidth(null) + 1,
					GeneralEditionPanel.delImage.getHeight(null) + 1);
			g.setColor(Color.black);
			g.drawRect(orig - 2, 3,
					GeneralEditionPanel.delImage.getWidth(null) + 1,
					GeneralEditionPanel.delImage.getHeight(null) + 1);
			g.drawImage(GeneralEditionPanel.delImage,
					orig - 1, 4, null);
			location = new Point(orig - 2, 3);
			size = new Dimension(GeneralEditionPanel.delImage.getWidth(null) + 1,
					GeneralEditionPanel.delImage.getHeight(null) + 1);
			
		}
		
	}
	
	/**
	 *  Description of the Method
	 *
	 * @param  type  Description of the Parameter
	 * @param  cf    Description of the Parameter
	 * @return       Description of the Return Value
	 */
	private JPanel createSubPanel(Class type, Field cf) {
		Entity ent = this.createEntity(type);
		this.setValue(ent, cf);
		GeneralEditionPanel gep = new GeneralEditionPanel(editor, parentFrame, om, (Entity) ent);
		
		gep.setAlignmentX(Component.LEFT_ALIGNMENT);
		return gep;
	}
	
	/**
	 *  Description of the Method
	 *
	 * @param  entType  Description of the Parameter
	 * @return          Description of the Return Value
	 */
	private Entity createEntity(Class entType) {
		
		try {
			Class[] conscf = {
					String.class};
			int index = entType.getName().lastIndexOf(".");
			String type = entType.getName().substring(index + 1,
					entType.getName().length());
			Object[] paracf = {
					"" + ingenias.editor.Editor.getNewId()};
			
			
			String methodName = "create" +
			type.substring(0, 1).toUpperCase() +
			type.substring(1, type.length());
			java.lang.reflect.Method m = ingenias.editor.ObjectManager.class.
			getMethod(methodName, conscf);			
			final Entity result = (Entity) m.invoke(om, paracf);
			this.undo.add(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					om.removeEntity(result);
					System.err.println("removing "+result);
					
				}
				
			});
			return result;
			
		}
		catch (NoSuchMethodException nsme) {
			nsme.printStackTrace();
		}
		catch (java.lang.reflect.InvocationTargetException ite) {
			ite.printStackTrace();
		}
		catch (IllegalAccessException iae) {
			iae.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 *  Description of the Method
	 *
	 * @param  entType  Description of the Parameter
	 * @return          Description of the Return Value
	 */
	private Entity createModelEntity(Class entType) {
		Entity result = null;
		try {
			Class[] conscf = {
					String.class};
			Object[] paracf = {
					"" + ingenias.editor.Editor.getNewId()};
			return (Entity) entType.getConstructor(conscf).newInstance(paracf);
		}
		catch (InstantiationException ie) {
			ie.printStackTrace();
		}
		catch (NoSuchMethodException nsme) {
			nsme.printStackTrace();
		}
		catch (java.lang.reflect.InvocationTargetException ite) {
			ite.printStackTrace();
		}
		catch (IllegalAccessException iae) {
			iae.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 *  Description of the Method
	 *
	 * @param  cf1                         Description of the Parameter
	 * @param  entc                        Description of the Parameter
	 * @return                             Description of the Return Value
	 * @exception  NoSuchMethodException   Description of the Exception
	 * @exception  ClassNotFoundException  Description of the Exception
	 * @exception  IllegalAccessException  Description of the Exception
	 * @exception  InstantiationException  Description of the Exception
	 */
	private JPanel createSimplePanel(Field cf1, Class entc) throws
	NoSuchMethodException,
	ClassNotFoundException, IllegalAccessException, InstantiationException {
		final Field cf = cf1;
		JPanel np = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
		
		JLabel jl = new JLabel(cf.getName());
		jl.setAlignmentX(Component.LEFT_ALIGNMENT);
		Object value = this.getValue(entc, cf);
		if (value == null) {
			value = "";
		}
		final ingenias.editor.widget.Editable jt = this.getPreferredWidget(this.entity.
				getClass(), cf1.getName());
		if (jt instanceof ingenias.editor.widget.ScrolledTArea) {
			BoxLayout bl = new javax.swing.BoxLayout(np, javax.swing.BoxLayout.Y_AXIS);
			( (Component) jt).setSize(200, 100);
			np.setLayout(bl);
		}
		jt.setText(value.toString());
		jt.setAlignmentX(Component.LEFT_ALIGNMENT);
		np.add(jl);
		np.add( (java.awt.Component) jt);
		this.add(np);
		np.setMinimumSize(new Dimension(cf.getName().length() * 10 + 30 * 10, 20));
		
		jt.addFocusListener(
				new java.awt.event.FocusListener() {					
					public void focusLost(java.awt.event.FocusEvent fe) {
						if (fe.getID() == fe.FOCUS_LOST ) {														          JTextComponent jt = (JTextComponent) fe.getComponent();
							setValue(jt.getText(), cf);
						}
					}
					
					public void focusGained(java.awt.event.FocusEvent fe) {
					}
					
				});
		jt.addKeyListener(
				new java.awt.event.KeyListener() {
					
					public void keyReleased(java.awt.event.KeyEvent ke) {
						
					}
					
					public void keyPressed(java.awt.event.KeyEvent ke) {
						
					}
					
					public void keyTyped(java.awt.event.KeyEvent ke) {
						if (ke.getKeyCode() == ke.VK_ENTER) {
							JTextComponent jt = (JTextComponent) ke.getComponent();
							setValue(jt.getText(), cf);
						}
					}
				});
		
		return np;
	}
	

	/**
	 *  Description of the Method
	 *
	 * @param  text  Description of the Parameter
	 * @return       Description of the Return Value
	 */
	public String getutf16Text(String text) {
		try {
			java.io.ByteArrayOutputStream ba = new java.io.ByteArrayOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(ba, "UTF16");
			osw.write(text);
			osw.close();
//			System.err.println(new String(ba.toByteArray()));
			return new String(ba.toByteArray());
		}
		catch (Exception uee) {
			uee.printStackTrace();
		}
		return "";
	}
	
	/**
	 *Constructor for the GeneralEditionPanel object
	 *
	 * @param  ed   Description of the Parameter
	 * @param  om   Description of the Parameter
	 * @param  ent  Description of the Parameter
	 */
	public GeneralEditionPanel(Editor ed, Frame f, ingenias.editor.ObjectManager om,
			Entity ent) {
		super(new GridLayout(1, 1));
		this.editor = ed;
		this.om = om;
		this.parentFrame=f;
		if (om==null)
			throw new RuntimeException("OM is null");
		Box main = Box.createVerticalBox();
		this.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.add(main);
		
		/*
		 *  BoxLayout bl=new BoxLayout(this,BoxLayout.Y_AXIS);
		 *  this.setLayout(bl);
		 */
		//  this.add(Box.createRigidArea(new Dimension(0,20)));
		
		if (ModelEntity.class.isAssignableFrom(ent.getClass())) {
			JPanel subpanel = null;
			subpanel = this.createModelPanel( (ModelEntity) ent);
			if (subpanel != null) {
				subpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
				main.add(subpanel);
			}
		}
		else {
			
			entity = ent;
			Class entc = entity.getClass();
			Field[] fs = entc.getFields();
			try {
				String[] preferredOrder = this.getPreferredOrder();
				for (int k = 0; k < preferredOrder.length - 1; k++) {
					
					final Field cf = entc.getField(preferredOrder[k]);
					try {
						JPanel subpanel = null;
						if (! (
								(RoleEntity.class.isAssignableFrom(entc) ||
										NAryEdgeEntity.class.isAssignableFrom(entc)) &&
										cf.getName().equalsIgnoreCase("id"))) {
							
							if (cf.getType().isAssignableFrom("".getClass())) {
								subpanel = this.createSimplePanel(cf, entc);
							}
							else {
								Border border1;
								TitledBorder border;
								border1 = BorderFactory.createLineBorder(Color.black, 2);
								border = new TitledBorder(border1, cf.getName());
								if (!this.isCollectionType(cf)) {
									this.getValue(entity.getClass(), cf);
									subpanel = this.createSinglePanel(cf);
								}
								else {
									subpanel = this.createCollectionPanel(cf);
								}
								if (subpanel != null) {
									subpanel.setBorder(border);
								}
							}
							if (subpanel != null) {
								subpanel.setAlignmentX(Component.LEFT_ALIGNMENT);
								main.add(subpanel);
							}
						}
					}
					catch (NoSuchMethodException nsm) {
						nsm.printStackTrace();
					}
					catch (Exception nsme) {
						nsme.printStackTrace();
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 *  Description of the Method
	 *
	 * @param  me  Description of the Parameter
	 * @return     Description of the Return Value
	 */
	private JPanel createModelPanel(ModelEntity me) {
		final ModelEntity cent = me;
		JPanel np = new JPanel(new BorderLayout());
		np.setAlignmentX(Component.LEFT_ALIGNMENT);
		JPanel top = new JPanel();
		JLabel value = null;
		if (cent.getModelID() == null) {
			value = new JLabel("NONE");
		}
		else {
			value = new JLabel(cent.getModelID());
		}
		final JLabel finalValue = value;
		top.add(new JLabel("Current value:"));
		top.add(value);
		JPanel middle = new JPanel(new BorderLayout());
		Vector instancesName = this.getModelInstancesNames("ingenias.editor." +
				cent.getModelType());
		final Vector instances = ingenias.editor.GraphManager.getInstance().
		getInstances("ingenias.editor." + cent.getModelType());
		final javax.swing.JComboBox jcb = new javax.swing.JComboBox(instancesName);
		middle.add(jcb, BorderLayout.CENTER);
		JButton selectValue = new JButton("Select one model");
		JButton selectModel = new JButton("Show selected");
		JPanel middleButtons = new JPanel();
		middleButtons.add(selectValue);
		middleButtons.add(selectModel);
		middle.add(middleButtons, BorderLayout.SOUTH);
		
		selectValue.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						int index = jcb.getSelectedIndex();
						if (index >= 0) {
							String selected = instances.elementAt(index).toString();
							cent.setModelID(selected);
							finalValue.setText(selected);
						}
					}
				});
		selectModel.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						if (cent.getModelID() != null && !cent.getModelID().equalsIgnoreCase("")) {
							ingenias.editor.ModelJGraph mjg =
								ingenias.editor.GraphManager.getInstance().getModel(cent.
										getModelID());
							editor.changeGraph(mjg);
						}
					}
				});
		
		np.add(top, BorderLayout.NORTH);
		np.add(middle, BorderLayout.CENTER);
		return np;
	}
	
	/**
	 *  Description of the Method
	 *
	 * @param  cf                             Description of the Parameter
	 * @return                                Description of the Return Value
	 * @exception  NoSuchMethodException      Description of the Exception
	 * @exception  InvocationTargetException  Description of the Exception
	 * @exception  IllegalAccessException     Description of the Exception
	 * @exception  InstantiationException     Description of the Exception
	 */
	private JPanel createModelPanel(Field cf) throws NoSuchMethodException,
	InvocationTargetException, IllegalAccessException, InstantiationException {
		ModelEntity cent1 = (ModelEntity)this.getValue(this.entity.getClass(), cf);
		if (cent1 == null) {
			Class[] paramt = {
					String.class};
			Object[] objects = {
					"" + ingenias.editor.Editor.getNewId()};
			cent1 = (ModelEntity) cf.getType().getConstructor(paramt).newInstance(
					objects);
			this.setValue(cent1, cf);
		}
		
		final ModelEntity cent = cent1;
		
		JPanel np = new JPanel(new BorderLayout());
		np.setAlignmentX(Component.LEFT_ALIGNMENT);
		JPanel top = new JPanel();
		JLabel value = null;
		if (cent.getModelID() == null) {
			value = new JLabel("NONE");
		}
		else {
			value = new JLabel(cent.getModelID());
		}
		final JLabel finalValue = value;
		top.add(new JLabel("Current value:"));
		top.add(value);
		JPanel middle = new JPanel(new BorderLayout());
		Vector instancesName = this.getModelInstancesNames("ingenias.editor." +
				cent.getModelType());
		final Vector instances = ingenias.editor.GraphManager.getInstance().
		getInstances("ingenias.editor." + cent.getModelType());
		final javax.swing.JComboBox jcb = new javax.swing.JComboBox(instancesName);
		middle.add(jcb, BorderLayout.CENTER);
		JButton selectValue = new JButton("Select one model");
		JButton selectModel = new JButton("Show selected");
		JPanel middleButtons = new JPanel();
		middleButtons.add(selectValue);
		middleButtons.add(selectModel);
		middle.add(middleButtons, BorderLayout.SOUTH);
		
		selectValue.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						int index = jcb.getSelectedIndex();
						if (index >= 0) {
							String selected = instances.elementAt(index).toString();
							cent.setModelID(selected);
							finalValue.setText(selected);
						}
					}
				});
		selectModel.addActionListener(
				new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						if (cent.getModelID() != null && !cent.getModelID().equalsIgnoreCase("")) {
							ingenias.editor.ModelJGraph mjg =
								ingenias.editor.GraphManager.getInstance().getModel(cent.
										getModelID());
							editor.changeGraph(mjg);
						}
					}
				});
		
		np.add(top, BorderLayout.NORTH);
		np.add(middle, BorderLayout.CENTER);
		
		return np;
	}
	
	/**
	 *  Gets the modelInstancesNames attribute of the GeneralEditionPanel object
	 *
	 * @param  type  Description of the Parameter
	 * @return       The modelInstancesNames value
	 */
	private Vector getModelInstancesNames(String type) {
		
		Vector instances = ingenias.editor.GraphManager.getInstance().getInstances(
				type);
		Vector instanceIDS = new Vector();
		Enumeration enumeration = instances.elements();
		
		while (enumeration.hasMoreElements()) {
			String name = enumeration.nextElement().toString();
			instanceIDS.add(type + ":" + name);
		}
		
		return instanceIDS;
	}
	
	private void makePanelRemovable(JPanel jp, JPanel newPanel, Field cf) {
		final Field cf1 = cf;
		final JPanel np = jp;
		final JPanel newPanel1=newPanel;
		Border border1;
		System.err.println("making "+cf.getName());
		
		border1 = BorderFactory.createLineBorder(Color.black, 2);
		final CloseIconTitledBorder border = new CloseIconTitledBorder(
				border1, cf1.getName());
		np.setBorder(border);
		np.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {}
			
			public void mouseReleased(MouseEvent e) {}
			
			public void mouseEntered(MouseEvent e) {}
			
			public void mouseExited(MouseEvent e) {}
			
			public void mouseClicked(MouseEvent e) {
				boolean delete = new java.awt.Rectangle(
						border.getLocation(),
						border.getSize()).contains(e.getPoint());
				if (delete && e.getClickCount() == 2) {
					int result = JOptionPane.showConfirmDialog(null,
							"Are you sure that you want to remove this entity?",
							"Question", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						setValue(null, cf1);
						try {
							Object obj = getValue(entity.getClass(), cf1);
							System.err.println(obj + " " );
						}catch (Exception ex1){
							ex1.printStackTrace();
						}
						np.removeAll();
						np.add(newPanel1,BorderLayout.CENTER);
						np.getRootPane().validate();
						np.removeMouseListener(this);
					}
				}
				
			}
			
		});
		
	}
	
	/**
	 *  Description of the Method
	 *
	 * @param  cf  Description of the Parameter
	 * @return     Description of the Return Value
	 */
	private JPanel createSelectionPanel(Field cf) {
		final Field cf1 = cf;
		final Vector subclasses = this.getSubclasses(cf.getType());
		sortClasses(subclasses);
		final JPanel np = new JPanel(new BorderLayout());
		np.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		final JScrollPane jsp = new JScrollPane();
		JPanel jp1 = new JPanel();
		final JList jl = new JList(subclasses);
		jp1.add(jl);
		jsp.getViewport().add(jl, null);
		JButton cnew = new JButton("Create new");
		JButton sexisting = new JButton("Select existing");
		np.add(jsp, BorderLayout.CENTER);
		final JPanel psouth = new JPanel();
		psouth.add(cnew);
		psouth.add(sexisting);
		JPanel npanel=new JPanel(new BorderLayout());
		npanel.add(jsp,BorderLayout.CENTER);
		npanel.add(psouth,BorderLayout.SOUTH);
		np.add(npanel,BorderLayout.CENTER);
		this.makePanelRemovable(np,npanel,cf);
		
		cnew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if (jl.getSelectedIndex() >= 0) {
					Class type = ( (Class) subclasses.elementAt(jl.getSelectedIndex()));
					JPanel jp = createSubPanel(type, cf1);
					np.removeAll();
					np.add(jp, BorderLayout.CENTER);
					np.getRootPane().validate();
				}
			}
		});
		
		sexisting.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						if (jl.getSelectedIndex() >= 0) {
							String type = ( (Class) subclasses.elementAt(jl.getSelectedIndex())).
							getName();
							Vector instances = om.getInstances(type);
							Vector instanceIDS = new Vector();
							Enumeration enumeration = instances.elements();
							
							while (enumeration.hasMoreElements()) {
								Entity o = (Entity) enumeration.nextElement();
								String etype = o.getClass().getName();
								int index = etype.lastIndexOf(".");
								String className = etype.substring(index + 1, etype.length());
								instanceIDS.add(className + ":" + o.toString());
							}
							
							if (instances.size() > 0) {
								javax.swing.JComboBox options = new javax.swing.JComboBox(
										instanceIDS);
								int result = JOptionPane.showConfirmDialog(null, options,
										"Select one", JOptionPane.NO_OPTION,
										JOptionPane.QUESTION_MESSAGE);
								if (result == JOptionPane.OK_OPTION &&
										options.getSelectedIndex() >= 0) {
									Entity ent = (Entity) instances.elementAt(
											options.getSelectedIndex());
									setValue(ent, cf1);
									JPanel jp = new GeneralEditionPanel(editor, parentFrame, om, ent);
									np.removeAll();
									np.add(jp, BorderLayout.CENTER);
									CloseIconTitledBorder border;
									border1 = BorderFactory.createLineBorder(Color.black, 2);
									border = new CloseIconTitledBorder(border1, cf1.getName());
									np.setBorder(border);
									np.validate();
								}
							}
							else {
								JOptionPane.showMessageDialog(null,
										"There are no instances of " + type,
										"Warning",
										JOptionPane.WARNING_MESSAGE);
							}
							
						}
					}
				});
		
		return np;
	}
	
	/**
	 *  Description of the Method
	 *
	 * @param  jl1  Description of the Parameter
	 * @param  lm1  Description of the Parameter
	 * @param  cf1  Description of the Parameter
	 * @return      Description of the Return Value
	 */
	private JPopupMenu createCollectionPopupmenu(JList jl1,
			javax.swing.DefaultListModel lm1,
			Field cf1) {
		JPopupMenu menu = new JPopupMenu();
		final Field cf = cf1;
		final javax.swing.DefaultListModel lm = lm1;
		final JList jl = jl1;
		
		// Edit
		menu.add(
				new AbstractAction("Add new element") {
					public void actionPerformed(ActionEvent e) {
						try {
							String type = cf.getType().getName();
							Vector instClasses = om.getValidEntitiesClasses();
							sortClasses(instClasses);
							Vector classesIDS = new Vector();
							Vector validClasses = new Vector();
							Enumeration enumeration = instClasses.elements();
							while (enumeration.hasMoreElements()) {
								Class o = (Class) enumeration.nextElement();
								if (getCollectionType(cf).isAssignableFrom(o)) {
									String etype = o.getName();
									int index = etype.lastIndexOf(".");
									String className = etype.substring(index + 1, etype.length());
									classesIDS.add(className);
									validClasses.add(o);
								}
							}
							
							if (classesIDS.size() > 0) {
								javax.swing.JComboBox options = new javax.swing.JComboBox(
										classesIDS);
								int result = JOptionPane.showConfirmDialog(null, options,
										"Select one", JOptionPane.NO_OPTION,
										JOptionPane.QUESTION_MESSAGE);
								if (result == JOptionPane.OK_OPTION &&
										options.getSelectedIndex() >= 0) {
									Class sclass = (Class) validClasses.elementAt(
											options.getSelectedIndex());
									Entity ent = null;
									if (ModelEntity.class.isAssignableFrom(sclass)) {
										ent = createModelEntity(sclass);
									}
									else {
										ent = createEntity(sclass);
									}
									addValue(ent, cf);
									lm.addElement(ent);
									GeneralEditionFrame gef = new GeneralEditionFrame(editor, om, parentFrame,
											"Editing", ent);
									gef.pack();
									gef.show();
								}
							}
							else {
								JOptionPane.showMessageDialog(null,
										"There are no valid classes assignable to " + type, "Warning",
										JOptionPane.WARNING_MESSAGE);
							}
							
						}
						catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
		menu.add(
				new AbstractAction("Add existing element") {
					public void actionPerformed(ActionEvent e) {
						try {
							String type = getCollectionType(cf).getName();
							Vector instances = om.getInstances(type);
							sort(instances);
							Vector instanceIDS = new Vector();
							Enumeration enumeration = instances.elements();
							
							while (enumeration.hasMoreElements()) {
								Entity o = (Entity) enumeration.nextElement();
								String etype = o.getClass().getName();
								int index = etype.lastIndexOf(".");
								String className = etype.substring(index + 1, etype.length());
								instanceIDS.add(className + ":" + o.toString());
							}
							
							if (instances.size() > 0) {
								javax.swing.JComboBox options = new javax.swing.JComboBox(
										instanceIDS);
								int result = JOptionPane.showConfirmDialog(null, options,
										"Select one", JOptionPane.NO_OPTION,
										JOptionPane.QUESTION_MESSAGE);
								if (result == JOptionPane.OK_OPTION &&
										options.getSelectedIndex() >= 0) {
									Entity ent = (Entity) instances.elementAt(
											options.getSelectedIndex());
									addValue(ent, cf);
									lm.addElement(ent);
								}
							}
							else {
								JOptionPane.showMessageDialog(null,
										"There are no instances of " + type,
										"Warning",
										JOptionPane.WARNING_MESSAGE);
							}
							
						}
						catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
		
		menu.add(
				new AbstractAction("Remove selected element") {
					public void actionPerformed(ActionEvent e) {
						int index = jl.getSelectedIndex();
						if (index > -1) {
							try {
								Class type = getCollectionType(cf);
								Enumeration enumeration = getCollection(cf);
								for (int k = 0; k < index; k++) {
									enumeration.nextElement();
								}
								Object o = enumeration.nextElement();
								if (type.equals(java.lang.String.class)) {
									removeValue(o.toString(), cf);
								}
								else {
									ingenias.editor.entities.Entity en = (ingenias.editor.entities.
											Entity) o;
									removeValue(en.getId(), cf);
								}
								lm.remove(index);
							}
							catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
				}
		);
		
		menu.add(
				new AbstractAction("Open selected element") {
					public void actionPerformed(ActionEvent e) {
						int index = jl.getSelectedIndex();
						if (index > -1) {
							Entity ent = (Entity) lm.get(index);
//							System.err.println(ent.getClass());
							JDialog jf = new GeneralEditionFrame(editor, om, parentFrame, "Edition", ent);
							jf.pack();
							jf.show();
						}
					}
				}
		);
		
		return menu;
	}
	
	protected static void sortClasses(Vector instClasses) {
		for (int k=0;k<instClasses.size();k++){
			Class a=(Class)instClasses.elementAt(k);
			for (int j=k;j<instClasses.size();j++){			  
				Class b=(Class)instClasses.elementAt(j);
				if (a.getName().compareTo(b.getName())>0){
					instClasses.setElementAt(b,k);
					instClasses.setElementAt(a,j);
					a=b;
				}
			}
		}
		
	}
	
	protected void sort(Vector instances) {
		
		Collections.sort(instances);	  
		
	}
	
	/**
	 *  Description of the Method
	 *
	 * @param  cf                         Description of the Parameter
	 * @return                            Description of the Return Value
	 * @exception  NoSuchMethodException  Description of the Exception
	 */
	private JPanel createCollectionPanel(Field cf) throws NoSuchMethodException {
		JPanel main = new JPanel(new GridLayout());
		main.setAlignmentX(Component.LEFT_ALIGNMENT);
		JScrollPane collection = new JScrollPane();
		collection.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		collection.setAlignmentX(Component.LEFT_ALIGNMENT);
		main.add(collection, null);
		collection.setAlignmentX(Component.LEFT_ALIGNMENT);
		final javax.swing.DefaultListModel dlm = new javax.swing.DefaultListModel();
		Enumeration enumeration = this.getCollection(cf);
		while (enumeration.hasMoreElements()) {
			dlm.addElement(enumeration.nextElement());
		}
		final JList jl = new JList(dlm);
		jl.setAutoscrolls(true);
		jl.setAlignmentX(Component.LEFT_ALIGNMENT);
//		jl.setPreferredSize(new Dimension(300, 100));
//		main.add(jl,null);
		collection.getViewport().add(jl, null);
		collection.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		final Field cf1 = cf;
		jl.addMouseListener(
				new java.awt.event.MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						if (SwingUtilities.isRightMouseButton(e)) {
							JPopupMenu menu = createCollectionPopupmenu(jl, dlm, cf1);
							menu.show(e.getComponent(), e.getX(), e.getY());
						}
					}
				});
		return main;
	}
	
	/**
	 *  Description of the Method
	 *
	 * @param  cf                             Description of the Parameter
	 * @return                                Description of the Return Value
	 * @exception  NoSuchMethodException      Description of the Exception
	 * @exception  InstantiationException     Description of the Exception
	 * @exception  IllegalAccessException     Description of the Exception
	 * @exception  InvocationTargetException  Description of the Exception
	 */
	private JPanel createSinglePanel(Field cf) throws NoSuchMethodException,
	InstantiationException, IllegalAccessException, InvocationTargetException {
		JPanel subpanel = null;
		if (ingenias.editor.entities.ModelEntity.class.isAssignableFrom(cf.getType())) {
			subpanel = this.createModelPanel(cf);
			this.makePanelRemovable(this,subpanel,cf);
			this.add(subpanel);
		}
		else if (ingenias.editor.entities.Entity.class.isAssignableFrom(cf.getType())) {
			if (this.getValue(this.entity.getClass(), cf) == null) {
				// select an existing one
				subpanel = this.createSelectionPanel(cf);
				// create a new one
			}
			else {
				subpanel = new GeneralEditionPanel(editor, parentFrame, om,
						(Entity)this.getValue(this.entity.
								getClass(), cf));
				JPanel jp=new JPanel(new BorderLayout());
				jp.add(subpanel,BorderLayout.CENTER);
				this.makePanelRemovable(jp,subpanel,cf);
				subpanel=jp;
			}
		}
		return subpanel;
	}
	
	/**
	 *  Description of the Method
	 *
	 * @param  e           Description of the Parameter
	 * @param  subclasses  Description of the Parameter
	 */
	private void mouseClickedOnSelection(java.awt.event.MouseEvent e,
			Vector subclasses) {
		JList jl = (JList) e.getComponent();
		int index = jl.getSelectedIndex();
		if (index >= 0) {
			Class selected = (Class) subclasses.get(index);
		}
	}
	
	/**
	 *  Gets the subclasses attribute of the GeneralEditionPanel object
	 *
	 * @param  c  Description of the Parameter
	 * @return    The subclasses value
	 */
	private Vector getSubclasses(Class c) {
		Vector result = new Vector();
		final Vector validClasses =
			ingenias.editor.ObjectManager.getValidEntitiesClasses();
		Enumeration enumeration = validClasses.elements();
		while (enumeration.hasMoreElements()) {
			Class current = (Class) enumeration.nextElement();
			if (c.isAssignableFrom(current)) {
				result.add(current);
			}
		}
//		Package.getPackage("ingenias.editor.entities").;
		return result;
	}
	
	/**
	 *  Gets the value attribute of the GeneralEditionPanel object
	 *
	 * @param  ent                        Description of the Parameter
	 * @param  cf                         Description of the Parameter
	 * @return                            The value value
	 * @exception  NoSuchMethodException  Description of the Exception
	 */
	private Object getValue(Class ent, Field cf) throws NoSuchMethodException {
		try {
			Class params[] = {};
			Object paramVal[] = {};
			java.lang.reflect.Method m = ent.getMethod("get" +
					cf.getName().substring(0, 1).
					toUpperCase() +
					cf.getName().substring(1,
							cf.getName().length()), params);
			return m.invoke(entity, paramVal);
		}
		catch (NoSuchMethodException nsm) {
			throw nsm;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 *  Sets the value attribute of the GeneralEditionPanel object
	 *
	 * @param  value  The new value value
	 * @param  cf     The new value value
	 */
	private void setValue(final Object value, final Field cf) {
		try {
			
			Class params[] = {
					cf.getType()};
			Object paramVal[] = {
					value};
			String mname = "set" +
			cf.getName().substring(0, 1).toUpperCase() +
			cf.getName().substring(1, cf.getName().length());
			java.lang.reflect.Method[] mms = entity.getClass().getMethods();
			for (int k = 0; k < mms.length; k++) {
				Class[] c = mms[k].getParameterTypes();
			}
			
			java.lang.reflect.Method m = entity.getClass().getMethod(mname, params);
			
			
			final java.lang.reflect.Method undo=m;
			final Object oldvalue=getValue(entity.getClass(),cf);
			this.undo.add(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					try {   if (!value.equals(oldvalue))
						 undo.invoke(entity, new Object[]{oldvalue});
						System.err.println("setting old value "+getValue(entity.getClass(),cf));
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NoSuchMethodException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				
			});
			m.invoke(entity, paramVal);
			
		}
		catch (Exception iae) {
			iae.printStackTrace();
		}
	}
	
	/**
	 *  Adds a feature to the Value attribute of the GeneralEditionPanel object
	 *
	 * @param  value  The feature to be added to the Value attribute
	 * @param  cf     The feature to be added to the Value attribute
	 */
	private void addValue(final Object value, Field cf) {
		try {
			
			
			Object paramVal[] = {
					value};
			String mname = "add" +
			cf.getName().substring(0, 1).toUpperCase() +
			cf.getName().substring(1, cf.getName().length());
			String mnameremove = "remove" +
			cf.getName().substring(0, 1).toUpperCase() +
			cf.getName().substring(1, cf.getName().length())+"Element";
			/*
			 *  java.lang.reflect.Method[] mms = entity.getClass().getMethods();
			 *  for (int k = 0; k < mms.length; k++) {
			 *  Class[] c = mms[k].getParameterTypes();
			 *  }
			 */
			java.lang.reflect.Method m =null;
			java.lang.reflect.Method rev =null;
			Class vclass=value.getClass();
			Class params[] =null;
			while (m==null && !vclass.equals(Object.class)){
				
				try {
					params = new Class[]{vclass};
					m = this.entity.getClass().getMethod(mname,
							params);
					rev=this.entity.getClass().getMethod(mnameremove,
							new Class[]{String.class});
				} catch (NoSuchMethodException nsme){
					vclass=vclass.getSuperclass();
				}
			}
			
			m.invoke(entity, paramVal);
			
			final java.lang.reflect.Method undo=rev;
			this.undo.add(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					try {
						if (value instanceof Entity)
						 undo.invoke(entity, new Object[]{((Entity)value).getId()});
						else 
						 undo.invoke(entity, new Object[]{value.toString()});	
						System.err.println("undoing adding "+value);
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				
			});
			
		}
		catch (Exception iae) {
			iae.printStackTrace();
		}
	}
	
	/**
	 *  Description of the Method
	 *
	 * @param  id  Description of the Parameter
	 * @param  cf  Description of the Parameter
	 */
	private void removeValue(String id, Field cf) {
		try {
			
			Class params[] = {
					"".getClass()};
			Object paramVal[] = {
					id};
			String mname = "remove" +
			cf.getName().substring(0, 1).toUpperCase() +
			cf.getName().substring(1, cf.getName().length()) + "Element";
			/*
			 *  java.lang.reflect.Method[] mms = entity.getClass().getMethods();
			 *  for (int k = 0; k < mms.length; k++) {
			 *  Class[] c = mms[k].getParameterTypes();
			 *  }
			 */
			java.lang.reflect.Method m = this.entity.getClass().getMethod(mname,
					params);
			
			m.invoke(entity, paramVal);
			
		}
		catch (Exception iae) {
			iae.printStackTrace();
		}
	}
	
	/**
	 *  Gets the collection attribute of the GeneralEditionPanel object
	 *
	 * @param  cf  Description of the Parameter
	 * @return     The collection value
	 */
	private Enumeration getCollection(Field cf) {
		try {
			
			Class params[] = {};
			Object paramVal[] = {};
			String mname = "get" +
			cf.getName().substring(0, 1).toUpperCase() +
			cf.getName().substring(1, cf.getName().length()) + "Elements";
			java.lang.reflect.Method[] mms = entity.getClass().getMethods();
			
			java.lang.reflect.Method m = this.entity.getClass().getMethod(mname,
					params);
			
			return (Enumeration) m.invoke(entity, paramVal);
		}
		catch (Exception iae) {
			iae.printStackTrace();
			return null;
		}
	}
	
	/**
	 *  Gets the collectionType attribute of the GeneralEditionPanel object
	 *
	 * @param  cf  Description of the Parameter
	 * @return     The collectionType value
	 */
	private boolean isCollectionType(Field cf) {
		return cf.getType().equals(ingenias.editor.TypedVector.class);
	}
	
	/**
	 *  Gets the collectionType attribute of the GeneralEditionPanel object
	 *
	 * @param  cf             Description of the Parameter
	 * @return                The collectionType value
	 * @exception  Exception  Description of the Exception
	 */
	private Class getCollectionType(Field cf) throws Exception {
		
		Class params[] = {};
		Object paramVal[] = {};
		String mname = "get" +
		cf.getName().substring(0, 1).toUpperCase() +
		cf.getName().substring(1, cf.getName().length()) + "Type";
		java.lang.reflect.Method[] mms = entity.getClass().getMethods();
		
		java.lang.reflect.Method m = this.entity.getClass().getMethod(mname, params);
		
		return (Class) m.invoke(entity, paramVal);
	}
	
	/**
	 *  Description of the Method
	 *
	 * @param  args  Description of the Parameter
	 */
	public static void main(String args[]) {
		
		
		
	}
	
	/**
	 *  Description of the Method
	 *
	 * @param  object  Description of the Parameter
	 * @param  name    Description of the Parameter
	 * @param  params  Description of the Parameter
	 * @return         Description of the Return Value
	 */
	private java.lang.reflect.Method findAppropriateMethod(Class object,
			String name, Class[] params) {
		java.lang.reflect.Method[] ms = object.getDeclaredMethods();
		boolean found = false;
		java.lang.reflect.Method current = null;
		for (int k = 0; k < ms.length && !found; k++) {
			current = ms[k];
			if (current.getName().equals(name) &&
					current.getParameterTypes().length == params.length) {
				Class[] cparams = current.getParameterTypes();
				boolean correct = true;
				for (int j = 0; j < cparams.length && correct; j++) {
					correct = correct && cparams[j].isAssignableFrom(params[j]);
				}
				found = correct;
			}
		}
		if (found) {
			return current;
		}
		else {
			return null;
		}
	}
	
	/**
	 *  Gets the preferredWidget attribute of the GeneralEditionPanel object
	 *
	 * @param  c                           Description of the Parameter
	 * @param  field                       Description of the Parameter
	 * @return                             The preferredWidget value
	 * @exception  IllegalAccessException  Description of the Exception
	 * @exception  InstantiationException  Description of the Exception
	 */
	private ingenias.editor.widget.Editable getPreferredWidget(Class c,
			String field) throws
			IllegalAccessException, InstantiationException {
		try {
			String className = "ingenias.editor.widget." +
			c.getName().substring(c.getName().lastIndexOf(".") + 1,
					c.getName().length()) + "WidgetPreferences";
			Class[] cparams = {};
			Object[] cval = {};
			
			EntityWidgetPreferences ep = (EntityWidgetPreferences) Class.forName(
					className).newInstance();
			Object widget = ep.getWidget(field);
			if (widget == null) {
				return new CustomJTextField();
			}
			ingenias.editor.widget.Editable edit = (ingenias.editor.widget.Editable)
			widget;
			
			//((ConfigurableWidget)edit).setDefaultValues();
			return edit;
			//cparams).newInstance(cval);
		}
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
			
			return new CustomJTextField();
		}
		catch (Exception e) {
			e.printStackTrace();
			
//			e.printStackTrace();
			return new CustomJTextField();
		}
		
	}
	
	public boolean isModified(){
		boolean ismodified=false;
		Component[] comps=this.getComponents();
		for (int k=0;k<comps.length && !ismodified;k++){
			if (comps[k] instanceof GeneralEditionPanel){
				ismodified=ismodified ||((GeneralEditionPanel)comps[k]).isModified();
			}
		}
		return ismodified || undo.size()>0;
	}
	
	public void undo(){
		System.err.println(""+undo.size()+" actions to undo");
		Component[] comps=this.getComponents();
		for (int k=0;k<comps.length;k++){
			if (comps[k] instanceof GeneralEditionPanel){
				((GeneralEditionPanel)comps[k]).undo();
			}
		}
		while (!undo.isEmpty()){			
			ActionListener al=(ActionListener)undo.lastElement();
			al.actionPerformed(null);
			undo.removeElementAt(undo.size()-1);
		}
		undo.clear();
	}
	
	public void confirmActions(){
		undo.clear();
	}
	
	/**
	 *  Gets the preferredOrder attribute of the GeneralEditionPanel object
	 *
	 * @return                             The preferredOrder value
	 * @exception  IllegalAccessException  Description of the Exception
	 * @exception  InstantiationException  Description of the Exception
	 */
	private String[] getPreferredOrder() throws
	IllegalAccessException, InstantiationException {
		String[] defaultResult = {};
		try {
			Class c = this.entity.getClass();
			String className = "ingenias.editor.widget." +
			c.getName().substring(c.getName().lastIndexOf(".") + 1,
					c.getName().length()) + "WidgetPreferences";
			Class[] cparams = {};
			Object[] cval = {};
			
			EntityWidgetPreferences ep = (EntityWidgetPreferences) Class.forName(
					className).newInstance();
			return ep.getPreferredOrder();
		}
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
			
			return defaultResult;
		}
		catch (Exception e) {
			e.printStackTrace();
			
//			e.printStackTrace();
			return defaultResult;
		}
		
	}
	
	public void applyChanges(){
		
	}
	
	
}
