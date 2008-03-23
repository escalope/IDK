package ingenias.editor;

import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.JWindow;

public class BusyMessageWindow extends JWindow {
	IDEState ids=null;

	public BusyMessageWindow(IDEState ids) {
		ids.setBusy();
		this.ids=ids;
	}

	public BusyMessageWindow(IDEState ids, GraphicsConfiguration gc) {
		super(gc);
		ids.setBusy();
		this.ids=ids;
		
	}

	public BusyMessageWindow(IDEState ids, Frame owner) {
		super(owner);
		ids.setBusy();
		this.ids=ids;
	}

	public BusyMessageWindow(IDEState ids, Window owner) {
		super(owner);
		ids.setBusy();
		this.ids=ids;
	}

	public BusyMessageWindow(IDEState ids, Window owner, GraphicsConfiguration gc) {
		super(owner, gc);
		ids.setBusy();
		this.ids=ids;
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		super.hide();
		ids.setNotBusy();
	}

	@Override
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		super.setVisible(b);
		if (b)
			ids.setBusy();
		else
			ids.setNotBusy();
	}
	
	

}
