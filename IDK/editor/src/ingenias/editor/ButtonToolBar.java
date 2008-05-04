/**
 * 
 */
package ingenias.editor;

import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

class ButtonToolBar extends JToolBar {
	/**
	 * 
	 */
	private final Editor editor;
	JComboBox jc = new JComboBox(new Object[] {"automatic", "manual"});
	// Actions which Change State
	/**
	 *  Description of the Field
	 */
	protected Action undo, redo, remove, group, ungroup, tofront, toback,
	cut, copy, paste;

	ButtonToolBar(Editor editor){
		this.editor = editor;
		this.setFloatable(false);
		JButton jb = null;
		// Toggle Connect Mode
		/*		Image img_connecton = ImageLoader.getImage("images/connecton.gif");
		 if (graph!=null)
		 graph.setPortsVisible(true);
		 ImageIcon connectIcon = new ImageIcon(img_connecton);
		 jb=new JButton(
		 new AbstractAction("", connectIcon) {
		 public void actionPerformed(ActionEvent e) {
		 if (graph!=null){
		 graph.setPortsVisible(!graph.isPortsVisible());
		 Image img_connect;
		 if (graph.isPortsVisible()) {
		 img_connect = ImageLoader.getImage("images/connecton.gif");
		 }
		 else {
		 img_connect = ImageLoader.getImage("images/connectoff.gif");
		 }
		 ImageIcon connectIcon = new ImageIcon(img_connect);
		 putValue(SMALL_ICON, connectIcon);
		 }
		 }
		 });
		 jb.setToolTipText("View connection ports");
		 toolbar.add(jb);*/

		// Automatic layout

		if (this.editor.graph != null) {
			this.editor.graph.setPortsVisible(true);

		}
		JPanel jp = new JPanel();
		jp.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		jp.add(new JLabel("Relationship Layout"));
		jp.add(jc);
		jc.setAction(new AbstractAction("") {
			private boolean enabled = true;
			public void actionPerformed(ActionEvent e) {
				if (jc.getSelectedIndex() == 0) {	
					if (IDE.ide!=null)
						IDE.ide.prefs.setRelationshiplayout(Preferences.RelationshipLayout.AUTOMATIC);
					ButtonToolBar.this.editor.enableAutomaticLayout();
				}
				else {
					if (IDE.ide!=null)
						IDE.ide.prefs.setRelationshiplayout(Preferences.RelationshipLayout.MANUAL);
					ButtonToolBar.this.editor.disableAutomaticLayout();
				}
			}
		});
		this.add(jp);

		// Undo
		this.addSeparator();
		Image img_undo = ImageLoader.getImage("images/undo.gif");
		ImageIcon undoIcon = new ImageIcon(img_undo);
		this.undo =
			new AbstractAction("", undoIcon) {

			public void actionPerformed(ActionEvent e) {
				ButtonToolBar.this.editor.undo();
			}
		};
		this.undo.setEnabled(false);
		jb = new JButton(this.undo);
		jb.setToolTipText("Undo an action");
		this.add(jb);
		// Redo
		Image img_redo = ImageLoader.getImage("images/redo.gif");
		ImageIcon redoIcon = new ImageIcon(img_redo);
		redo =
			new AbstractAction("", redoIcon) {

			public void actionPerformed(ActionEvent e) {
				if (ButtonToolBar.this.editor.graph != null) {
					ButtonToolBar.this.editor.redo();
				}
			}
		};
		redo.setEnabled(false);
		jb = new JButton(redo);
		jb.setToolTipText("Redo last action");
		this.add(jb);

		//
		// Edit Block
		//
		this.addSeparator();
		Action action;
		Image img;

		if (this.editor.graph != null) {

			// Copy
			action = this.editor.graph.getTransferHandler().getCopyAction();
			img = ImageLoader.getImage("images/copy.gif");
			action.putValue(Action.SMALL_ICON, new ImageIcon(img));
			copy = new EventRedirector(this.editor, action,new ImageIcon(img));
			this.add(copy);


			// Paste
			action = this.editor.graph.getTransferHandler().getPasteAction();
			img = java.awt.Toolkit.getDefaultToolkit().createImage("images/paste.gif");
			
			action.putValue(Action.SMALL_ICON, new ImageIcon(img));
			paste = new EventRedirectorPaste(this.editor, action,new ImageIcon(img));
			this.add(paste);

			// Cut
			action = this.editor.graph.getTransferHandler().getCopyAction(); // cut is simulated
			// with a copy and a delete when paste was performed.
			// it was required this solution to prevent deleting from
			// the ingenias model elements appearing only once.
			/* img = ImageLoader.getImage("images/cut.gif");
			 action.putValue(Action.SMALL_ICON, new ImageIcon(img));
			 cut = new EventRedirectorCut(Editor.this,action);
			 this.add(cut);*/
		}

		// Remove
		Image img_delete =
			ImageLoader.getImage("images/delete.gif");
		ImageIcon removeIcon = new ImageIcon(img_delete);
		remove =
			new AbstractAction("", removeIcon) {

			public void actionPerformed(ActionEvent e) {
				if (ButtonToolBar.this.editor.graph != null) {
					if (!ButtonToolBar.this.editor.graph.isSelectionEmpty()) {
						Object[] cells =
							ButtonToolBar.this.editor.graph.getSelectionCells();						
						//cells = ButtonToolBar.this.editor.graph.getDescendants(cells);
						ButtonToolBar.this.editor.graph.getGraphLayoutCache().remove(cells,true,true);
					}
				}
			}
		};
		remove.setEnabled(false);
		this.add(remove);

		// Zoom Std
		this.addSeparator();
		Image img_zoom = ImageLoader.getImage("images/zoom.gif");
		ImageIcon zoomIcon = new ImageIcon(img_zoom);
		this.add(
				new AbstractAction("", zoomIcon) {
					/**
					 *  Description of the Method
					 *
					 *@param  e  Description of Parameter
					 */
					public void actionPerformed(ActionEvent e) {
						if (ButtonToolBar.this.editor.graph != null) {
							ButtonToolBar.this.editor.graph.setScale(1.0);
						}
					}
				});
		// Zoom In
		Image img_zoomin =
			ImageLoader.getImage("images/zoomin.gif");
		ImageIcon zoomInIcon = new ImageIcon(img_zoomin);
		this.add(
				new AbstractAction("", zoomInIcon) {

					public void actionPerformed(ActionEvent e) {
						if (ButtonToolBar.this.editor.graph != null) {
							ButtonToolBar.this.editor.graph.setScale(2 * ButtonToolBar.this.editor.graph.getScale());
						}
					}
				});
		// Zoom Out
		Image img_zoomout =
			ImageLoader.getImage("images/zoomout.gif");
		ImageIcon zoomOutIcon = new ImageIcon(img_zoomout);
		this.add(
				new AbstractAction("", zoomOutIcon) {

					public void actionPerformed(ActionEvent e) {
						if (ButtonToolBar.this.editor.graph != null) {
							ButtonToolBar.this.editor.graph.setScale(ButtonToolBar.this.editor.graph.getScale() / 2);
						}
					}
				});

		// Group
		/* toolbar.addSeparator();
		 Image img_group =
		 ImageLoader.getImage("images/group.gif");
		 ImageIcon groupIcon = new ImageIcon(img_group);
		 group =
		 new AbstractAction("", groupIcon) {
		 public void actionPerformed(ActionEvent e) {
		 if (graph != null) {
		 group(graph.getSelectionCells());
		 }
		 }
		 };
		 group.setEnabled(false);
		 toolbar.add(group);
		 // Ungroup
		  Image img_ungroup =
		  ImageLoader.getImage("images/ungroup.gif");
		  ImageIcon ungroupIcon = new ImageIcon(img_ungroup);
		  ungroup =
		  new AbstractAction("", ungroupIcon) {
		  public void actionPerformed(ActionEvent e) {
		  if (graph != null) {
		  ungroup(graph.getSelectionCells());
		  }
		  }
		  };
		  ungroup.setEnabled(false);
		  toolbar.add(ungroup);
		  // To Front
		   toolbar.addSeparator();
		   Image img_tofront =
		   ImageLoader.getImage("images/tofront.gif");
		   ImageIcon toFrontIcon = new ImageIcon(img_tofront);
		   tofront =
		   new AbstractAction("", toFrontIcon) {
		   public void actionPerformed(ActionEvent e) {
		   if (graph != null) {
		   if (!graph.isSelectionEmpty()) {
		   toFront(graph.getSelectionCells());
		   }
		   }
		   }
		   };
		   tofront.setEnabled(false);
		   toolbar.add(tofront);
		   // To Back
		    Image img_toback =
		    ImageLoader.getImage("images/toback.gif");
		    ImageIcon toBackIcon = new ImageIcon(img_toback);
		    toback =
		    new AbstractAction("", toBackIcon) {
		    public void actionPerformed(ActionEvent e) {
		    if (graph != null) {
		    if (!graph.isSelectionEmpty()) {
		    toBack(graph.getSelectionCells());
		    }
		    }
		    }
		    };
		    toback.setEnabled(false);
		    toolbar.add(toback);
		 */
	}

	protected Action getUndo() {
		return undo;
	}

	protected void setUndo(Action undo) {
		this.undo = undo;
	}

	protected Action getCopy() {
		return copy;
	}

	protected Action getCut() {
		return cut;
	}

	protected Action getGroup() {
		return group;
	}

	public JComboBox getJc() {
		return jc;
	}

	protected Action getPaste() {
		return paste;
	}

	protected Action getRedo() {
		return redo;
	}

	protected Action getRemove() {
		return remove;
	}

	protected Action getToback() {
		return toback;
	}

	protected Action getTofront() {
		return tofront;
	}

	protected Action getUngroup() {
		return ungroup;
	}




}