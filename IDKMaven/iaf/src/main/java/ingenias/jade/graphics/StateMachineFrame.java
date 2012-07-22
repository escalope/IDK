/*
    Copyright (C) 2005 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 

    INGENIAS Agent Framework is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS Agent Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS Agent Framework; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 */


package ingenias.jade.graphics;

import ingenias.jade.exception.NodeNotFound;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StateMachineFrame
extends JPanel {

	class EndState
	extends JPanel {
		public EndState(LayoutManager lm) {
			super(lm);
		}

		public void paint(java.awt.Graphics g) {
			super.paint(g);
			Dimension d = this.getSize();
			g.setColor(Color.black);
			int rad = Math.min(20,(Math.min(d.width, d.height)));
			g.fillOval(0, d.height/2-rad/2, rad, rad);
			this.setBackground(Color.WHITE);
		}

		public Dimension getMinimumSize() {
			return new Dimension(20, 20);
		}

		public Dimension getPreferredSize() {
			return new Dimension(20, 20);
		}


	}

	Hashtable tree = new Hashtable();
	Hashtable panels = new Hashtable();
	JLabel last = null;
	String cid = "";
	String role="";
	
	private String protocol;

	public StateMachineFrame(String cid, String protocol, String playedRole) {
		this.cid = cid;
		this.role=playedRole;
		this.protocol=protocol;
//		super(title);
	}

	public void add(String source, String target) throws ingenias.exception.
	CycleInProtocol {
		Vector row = new Vector();
		if (tree.containsKey(source)) {
			row = (Vector) tree.get(source);
			if (!tree.containsKey(target)) {
				tree.put(target, new Vector());
			}
		}
		else {
			tree.put(source, row);
			if (!tree.containsKey(target)) {
				tree.put(target, new Vector());

			}
		}
		row.add(target);
		this.findFirst();
	}

	public boolean isFinal(String source) throws NodeNotFound {
		if (tree.containsKey(source)) {
			return ( (Vector) tree.get(source)).size() == 0;
		}
		throw new ingenias.jade.exception.NodeNotFound();
	}

	private Vector findFirst() throws ingenias.exception.CycleInProtocol {
		Vector keys = new Vector(this.tree.keySet());
		Vector candidates = new Vector(keys);
		for (int k = 0; k < candidates.size(); k++) {
			String current = candidates.elementAt(k).toString();
			Vector connected = (Vector) tree.get(current);
			for (int i = 0; i < connected.size(); i++) {
				keys.remove(connected.elementAt(i));
			}
		}
		if (keys.size() == 0) {
			throw new ingenias.exception.CycleInProtocol();
		}
		return keys;
	}

	public void updateStates(String agent) {
		Vector first = null;
		try {
			first = this.findFirst();
		}
		catch (ingenias.exception.CycleInProtocol ex) {

		}
		//this.getContentPane().removeAll();
		//this.getContentPane().setLayout(new java.awt.BorderLayout());
		this.setLayout(new java.awt.BorderLayout());
		JPanel center = new JPanel(new java.awt.GridBagLayout());
		center.setBackground(Color.WHITE);
//		this.getContentPane().add(center, java.awt.BorderLayout.CENTER);
		this.add(center, java.awt.BorderLayout.CENTER);
		this.add(new JLabel(role+"-"+agent), java.awt.BorderLayout.NORTH);
		this.createNext(center, first);
		MainInteractionManager.addAgentInteraction(this,agent, cid,role, protocol);
	}

	private void createNext(javax.swing.JPanel parentPanel, Vector init) {
		if (init.size() > 0) {
			Enumeration enumeration = init.elements();
			int y = 0;
			while (enumeration.hasMoreElements()) {
				Object next = enumeration.nextElement();       
				Vector following = (Vector)this.tree.get(next);
				JLabel comp = new JLabel(next.toString());        
				java.awt.GridBagLayout gb = new java.awt.GridBagLayout();
				JPanel npanel = new JPanel(gb);
				int narrows=following.size();
				if (following.size()==0)
					narrows=1;
				ArrowedPanel hl = new ArrowedPanel(narrows);
				npanel.setBackground(Color.WHITE);
				comp.setBackground(Color.WHITE);        
				GridBagConstraints c = new GridBagConstraints();
				c.fill = GridBagConstraints.NONE;
				c.weightx = 0;
				c.gridx = 0;
				c.gridy = y;
				parentPanel.add(comp, c);
				this.panels.put(next, comp);

				c.fill = GridBagConstraints.BOTH;
				c.weightx = 2;
				c.weighty = 2;
				c.gridx = 1;
				c.gridy = y;
				parentPanel.add(hl, c);

				c = new GridBagConstraints();
				c.fill = GridBagConstraints.BOTH;
				c.weightx = 0;
				c.gridx = 2;
				c.gridy = y;
				//npanel.setBorder(BorderFactory.createLineBorder(Color.black,2));
				parentPanel.add(npanel, c);

				this.createNext(npanel, following);
				y = y + 1;
			}
		}
		else {
			parentPanel.setLayout(new BorderLayout());
			parentPanel.add(new EndState(new BorderLayout()));
//			parentPanel.setBackground(Color.cyan);
		}
	}

	public void enable1(String state) {
		JLabel l = (JLabel)this.panels.get(state);

		l.setForeground(Color.RED);

	}

	public void clearRed(String state) {
		JLabel l = (JLabel)this.panels.get(state);

		l.setForeground(Color.BLACK);

	}

	public void enable(String state) {
		JLabel l = (JLabel)this.panels.get(state);

		l.setForeground(Color.RED);


	}


	/*public void enable(String state) {
    JLabel l = (JLabel)this.panels.get(state);
    if (l == null) {
      System.err.println("state " + state + " not registered in ");
    }
    else {
      if (last != null) {
        Vector v = (Vector)this.tree.get(last.getText());
        if (v != null) {
          if (!v.contains(state)) {
            JOptionPane.showMessageDialog(this,
                                          new JLabel(
                "Cannot perform transition from " +
                last.getText() + " to " + state + ". It is not allowed"),
                                          "ERROR", JOptionPane.ERROR_MESSAGE);
          }
        }
        else {
          JOptionPane.showMessageDialog(this,
                                        new JLabel(
              "Cannot perform transition from " +
              last.getText() + " to " + state + ". It is not allowed"), "ERROR",
                                        JOptionPane.ERROR_MESSAGE);
        }
      }
      l.setForeground(Color.RED);
      if (this.last != null) {
        last.setForeground(Color.BLACK);
        last = l;
      }
      else {
        this.last = l;
      }
    }
  }*/

	public static void main(String args[]) throws Exception {
		JFrame main = new JFrame();

		/*StateMachineFrame smf = new StateMachineFrame("prueba","role1");
		main.getContentPane().add(smf);
		smf.add("1", "2");
		smf.add("1", "3");
		smf.add("1", "4");
		smf.add("2", "5");
		smf.updateStates("hola");
		main.pack();
		main.show();*/
	}

	public void setAbortColor() {
		this.setBackground(Color.RED);

	}

}