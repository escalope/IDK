package ingenias.tests;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;

import ingenias.editor.rendererxml.HTMLLabel;
import ingenias.editor.rendererxml.JLabelStereotype;
import ingenias.editor.rendererxml.JMultilineLabel;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.batik.ext.swing.GridBagConstants;
import org.junit.Test;

public class SwingLayoutExperiments {
 
	@Test	
	public void createBoxes(){					  
			  JFrame jf=new JFrame("box");
			  
			  Box vbox = Box.createVerticalBox();			  
			  vbox.add(new HTMLLabel(""));
			  vbox.add(new JLabelStereotype("hola"));
			  jf.getContentPane().add(vbox);
			  jf.pack();
			  jf.setLocation(new Point(0,100));
			  jf.setVisible(true);
			  
			  
			  jf=new JFrame("boxwithmultiline");
			  vbox = Box.createVerticalBox();
			  JTextArea ta=new JTextArea("Estoesunapruebademultiline");
			  ta.setWrapStyleWord(true);
			  ta.setLineWrap(true);
			  ta.setBackground(Color.white);
			  vbox.add(ta);
			  vbox.add(new JLabelStereotype("hola"));
			  jf.getContentPane().add(vbox);			  
			  jf.pack();			 
			  jf.invalidate();
			  jf.doLayout();
			  jf.setLocation(new Point(100,100));
			  jf.setVisible(true);
			  
			  jf=new JFrame("gridbag");
			  GridBagLayout gbl=new GridBagLayout();
			  JPanel jp=new JPanel(gbl);
			  GridBagConstraints gbc=new GridBagConstraints();
			  gbc.fill=GridBagConstraints.NONE;
			  gbc.weightx=0;
			  gbc.weighty=0;
			  gbc.gridx=0;
			  gbc.gridy=0;
			  jp.add(new HTMLLabel(""),gbc);
			  gbc=new GridBagConstraints();
			  gbc.fill=GridBagConstraints.NONE;
			  gbc.gridx=0;
			  gbc.gridy=1;
			  jp.add(new JLabelStereotype("hola"),gbc);
			  jf.getContentPane().add(jp);
			  jf.pack();
			  jf.setLocation(new Point(200,100));
			  jf.setVisible(true);
			  
			  try {
				Thread.currentThread().sleep(50000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}
