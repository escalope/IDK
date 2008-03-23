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

package ingenias.editor;

import javax.swing.*;
import javax.swing.tree.*;
import java.util.*;
import ingenias.editor.widget.DnDJTree;


public class IDEState  implements java.io.Serializable {
  public Editor editor;
  public ObjectManager om;
  public GraphManager gm;
  public Properties prop=null;
  
  private boolean busy=false;
  
  

  public IDEState(Editor editor,DefaultMutableTreeNode rootObjetos,
                  JTree arbolObjectos,DefaultMutableTreeNode rootProyectos,
    DnDJTree arbolProyectos){
   this.editor=editor;
   this.gm=GraphManager.initInstance(rootProyectos,arbolProyectos);
   this.om= ObjectManager.initialise(rootObjetos,arbolObjectos);
   this.prop=new Properties();
   ingenias.editor.persistence.PersistenceManager.defaultProperties(prop);
  }
  
  public void putProperty(ProjectProperty pp){
	  this.prop.put(pp.module+":"+pp.key, pp);	  
  }
  
  public ProjectProperty getProperty(String module, String key){
	  return (ProjectProperty) this.prop.get(module+":"+key);	  
  }

  public IDEState(Editor editor,GraphManager gm,ObjectManager om){
    this.om=om;
    this.gm=gm;
    this.editor=editor;
    this.prop=new Properties();
    ingenias.editor.persistence.PersistenceManager.defaultProperties(prop);
  }

  public static IDEState emptyIDEState(){
        DefaultMutableTreeNode rootObjects = new DefaultMutableTreeNode();
        JTree treeObjects = new javax.swing.JTree(rootObjects);
        DnDJTree treeProjects = new DnDJTree();
        DefaultMutableTreeNode rootProjects =(DefaultMutableTreeNode)treeProjects.getModel().getRoot();
        GraphManager gm = GraphManager.createIndependentCopy(rootProjects, treeProjects);
        ObjectManager om = ObjectManager.createIndependentCopy(gm, rootObjects, treeObjects);
        Editor ed=new Editor(om);
        return new IDEState(ed, gm, om);

  }

  public IDEState createEmpty(){

	  return new IDEState(this.editor,new DefaultMutableTreeNode(),new JTree(),
			  new DefaultMutableTreeNode(),new DnDJTree());

  }

  public void setBusy() {
	  busy=true;
  }
  public void setNotBusy() {
	  busy=false;
  }

  public boolean isBusy(){
	  return busy;

  }
}