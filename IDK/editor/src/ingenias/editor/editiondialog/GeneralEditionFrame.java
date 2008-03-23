/*
    Copyright (C) 2002 Jorge Gomez Sanz

    This file is part of INGENIAS IDE, a support tool for the INGENIAS
    methodology, availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net

    INGENIAS IDE is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS IDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS IDE; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/

package ingenias.editor.editiondialog;

import javax.swing.*;
import java.lang.reflect.*;
import ingenias.editor.entities.*;
import ingenias.editor.widget.DnDJTree;

import java.util.*;
import java.awt.*;
import ingenias.editor.GraphManager;
import javax.swing.tree.DefaultMutableTreeNode;

public class GeneralEditionFrame extends javax.swing.JDialog implements java.io.Serializable {
  private JScrollPane mainscroll = new JScrollPane();

  public GeneralEditionFrame(ingenias.editor.Editor editor,ingenias.editor.ObjectManager om,final Frame dialogOwner,String title,Entity ent) {
    super(dialogOwner,title,true);

    final JPanel main=new JPanel();
    BorderLayout bl=new BorderLayout();
    main.setLayout(bl);
    this.getContentPane().add(main);
    main.addContainerListener(new java.awt.event.ContainerAdapter(){
      public void componentAdded(java.awt.event.ContainerEvent ce){
        pack();
      }
    });
    
    final JDialog self=this;
    final GeneralEditionPanel gep=new GeneralEditionPanel(editor,dialogOwner, om,ent);
    main.add(mainscroll,BorderLayout.CENTER);
    mainscroll.getViewport().add(gep,null);    
    JButton cancel=new JButton("Cancel");
    cancel.addActionListener(new java.awt.event.ActionListener(){
      public void actionPerformed(java.awt.event.ActionEvent ae) {
        self.setVisible(false);
        gep.undo();
      }
    });
    
    JButton accept=new JButton("Accept");
    accept.addActionListener(new java.awt.event.ActionListener(){
      public void actionPerformed(java.awt.event.ActionEvent ae) {
    	  self.setVisible(false);
          gep.confirmActions();
      }
    });

    JPanel southButtons=new JPanel();
    southButtons.add(accept);
    southButtons.add(cancel);
    main.add(southButtons,BorderLayout.SOUTH);
    SwingUtilities.invokeLater(new Runnable(){
    	  public void run(){
    		  if (dialogOwner!=null)
    		dialogOwner.requestFocusInWindow();
    	    self.requestFocusInWindow();}});

  }

  public void pack(){
   super.pack();
   final JDialog jd=this;
   this.setSize(this.getSize().width+30,300);
  
  }


  public static void main(String args[]){
	
    System.err.println("hola");
    ingenias.editor.entities.INGENIASCodeComponent a=new ingenias.editor.entities.INGENIASCodeComponent("mio");
    a.setCode("hola que tal");
    GraphManager.initInstance(new DefaultMutableTreeNode(),new DnDJTree());

    GeneralEditionFrame ge=new GeneralEditionFrame(null,ingenias.editor.ObjectManager.initialise(new DefaultMutableTreeNode(),new JTree()),null,"hola", a);

    
    ge.setVisible(true);
    System.out.println(a.getDescription());

    //System.out.println(a.getDescription());

  }
}