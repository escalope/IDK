package atai.gui;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class REGInitializing extends JFrame {
	
	REGWizardInterface wizard=null;
	
	public REGInitializing(REGWizardInterface wizard) throws HeadlessException {
		this.wizard=wizard;
		this.getContentPane().add(new JLabel("Initializing the assistant"));
	}



}
