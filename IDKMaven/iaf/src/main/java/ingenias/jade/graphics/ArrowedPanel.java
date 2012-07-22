
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ArrowedPanel extends JPanel {
	private int rows=1;
	
	public ArrowedPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		 this.setBackground(Color.WHITE);
		// TODO Auto-generated constructor stub
	}
	
	public ArrowedPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}
	
	public ArrowedPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	
	public ArrowedPanel(int rows) {
		super();
		this.rows=rows;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void paint(Graphics g) {		
		super.paint(g);
		Dimension d = this.getSize();	     
		if (this.rows!=0){
			g.drawLine(0,this.getSize().height/2,this.getSize().width/2,this.getSize().height/2);
			int separation=this.getSize().height/(rows);
			for (int k=0;k<rows;k++){			
				int cornery=(separation*k)+separation/2;
				g.drawLine(this.getSize().width/2,this.getSize().height/2,this.getSize().width/2,cornery);
				g.drawLine(this.getSize().width/2,cornery,this.getSize().width,cornery);
				g.drawLine(this.getSize().width,cornery,this.getSize().width-5,cornery-5);
				g.drawLine(this.getSize().width,cornery,this.getSize().width-5,cornery+5);
			}
		}
	}
	public static void main(String args[]){
		JFrame jf=new JFrame();
		ArrowedPanel ap=new ArrowedPanel(3);
		ap.setSize(new Dimension(100,100));
		ap.setPreferredSize(new Dimension(100,100));
		jf.getContentPane().add(ap);
		jf.pack();
		jf.show();
		
	}
	
	protected void setRows(int rows) {
		this.rows = rows;
	}
	
	
	
}
