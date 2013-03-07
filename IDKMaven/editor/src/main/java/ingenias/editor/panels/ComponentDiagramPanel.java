

/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz over original code from Ruben Fuentes and Juan Pavon
 * 
 *   Modifications over original code from jgraph.sourceforge.net
 * 
 * This file is part of the INGENME tool. INGENME is an open source meta-editor
 * which produces customized editors for user-defined modeling languages
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3 of the License
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 **/
 
package ingenias.editor.panels;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.Map;
import java.util.Hashtable;

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
import ingenias.editor.events.*;
import ingenias.exception.InvalidEntity;
import ingenias.editor.*;

public class ComponentDiagramPanel extends JGraph {

  public ComponentDiagramPanel(ComponentDiagramDataEntity mde, 
                               String nombre, Model
                               m, BasicMarqueeHandler mh) {
    super(m, mh);
    
    this.getGraphLayoutCache().setFactory(new ingenias.editor.cellfactories.ComponentDiagramCellViewFactory());
  }


  //
  // Adding Tooltips
  //

  // Return Cell Label as a Tooltip
  public String getToolTipText(MouseEvent e) {
    if (e != null) {
      // Fetch Cell under Mousepointer
      Object c = getFirstCellForLocation(e.getX(), e.getY());
      if (c != null) {

        // Convert Cell to String and Return
        return convertValueToString(c);
      }
    }
    return null;
  }

  public static Vector<String> getAllowedEntities(){
   Vector<String> entities=new   Vector<String>();


 entities.add("INGENIASComponent");

 entities.add("INGENIASCodeComponent");

 entities.add("FileSpecPatternMapping");

 entities.add("Application");

 entities.add("EnvironmentApplication");

 entities.add("InternalApplication");

 entities.add("Task");

 entities.add("Test");

 entities.add("WFTest");

 entities.add("WFTestState");

 entities.add("WFTestFinalState");

 entities.add("WFTestInitialState");

 entities.add("UMLComment");

 entities.add("TaskWS");

 entities.add("ContextUseTask");

 entities.add("BoxedTask");

 entities.add("ContextBindingTask");

 entities.add("ContextReleaseTask");

 entities.add("Plan");

   return entities;
  }

 
   
  public DefaultGraphCell createCell(String entity) throws InvalidEntity{
  
    if (entity.equalsIgnoreCase("INGENIASComponent")) {
    INGENIASComponent nentity=new INGENIASComponent(((Model)getModel()).getNewId("INGENIASComponent"));
      DefaultGraphCell vertex = new
          INGENIASComponentCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("INGENIASCodeComponent")) {
    INGENIASCodeComponent nentity=new INGENIASCodeComponent(((Model)getModel()).getNewId("INGENIASCodeComponent"));
      DefaultGraphCell vertex = new
          INGENIASCodeComponentCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("FileSpecPatternMapping")) {
    FileSpecPatternMapping nentity=new FileSpecPatternMapping(((Model)getModel()).getNewId("FileSpecPatternMapping"));
      DefaultGraphCell vertex = new
          FileSpecPatternMappingCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Application")) {
    Application nentity=new Application(((Model)getModel()).getNewId("Application"));
      DefaultGraphCell vertex = new
          ApplicationCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("EnvironmentApplication")) {
    EnvironmentApplication nentity=new EnvironmentApplication(((Model)getModel()).getNewId("EnvironmentApplication"));
      DefaultGraphCell vertex = new
          EnvironmentApplicationCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("InternalApplication")) {
    InternalApplication nentity=new InternalApplication(((Model)getModel()).getNewId("InternalApplication"));
      DefaultGraphCell vertex = new
          InternalApplicationCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Task")) {
    Task nentity=new Task(((Model)getModel()).getNewId("Task"));
      DefaultGraphCell vertex = new
          TaskCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Test")) {
    Test nentity=new Test(((Model)getModel()).getNewId("Test"));
      DefaultGraphCell vertex = new
          TestCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("WFTest")) {
    WFTest nentity=new WFTest(((Model)getModel()).getNewId("WFTest"));
      DefaultGraphCell vertex = new
          WFTestCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("WFTestState")) {
    WFTestState nentity=new WFTestState(((Model)getModel()).getNewId("WFTestState"));
      DefaultGraphCell vertex = new
          WFTestStateCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("WFTestFinalState")) {
    WFTestFinalState nentity=new WFTestFinalState(((Model)getModel()).getNewId("WFTestFinalState"));
      DefaultGraphCell vertex = new
          WFTestFinalStateCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("WFTestInitialState")) {
    WFTestInitialState nentity=new WFTestInitialState(((Model)getModel()).getNewId("WFTestInitialState"));
      DefaultGraphCell vertex = new
          WFTestInitialStateCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("UMLComment")) {
    UMLComment nentity=new UMLComment(((Model)getModel()).getNewId("UMLComment"));
      DefaultGraphCell vertex = new
          UMLCommentCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("TaskWS")) {
    TaskWS nentity=new TaskWS(((Model)getModel()).getNewId("TaskWS"));
      DefaultGraphCell vertex = new
          TaskWSCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("ContextUseTask")) {
    ContextUseTask nentity=new ContextUseTask(((Model)getModel()).getNewId("ContextUseTask"));
      DefaultGraphCell vertex = new
          ContextUseTaskCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("BoxedTask")) {
    BoxedTask nentity=new BoxedTask(((Model)getModel()).getNewId("BoxedTask"));
      DefaultGraphCell vertex = new
          BoxedTaskCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("ContextBindingTask")) {
    ContextBindingTask nentity=new ContextBindingTask(((Model)getModel()).getNewId("ContextBindingTask"));
      DefaultGraphCell vertex = new
          ContextBindingTaskCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("ContextReleaseTask")) {
    ContextReleaseTask nentity=new ContextReleaseTask(((Model)getModel()).getNewId("ContextReleaseTask"));
      DefaultGraphCell vertex = new
          ContextReleaseTaskCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Plan")) {
    Plan nentity=new Plan(((Model)getModel()).getNewId("Plan"));
      DefaultGraphCell vertex = new
          PlanCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

	  throw new ingenias.exception.InvalidEntity("Entity type "+entity+" is not allowed in this diagram"); 
  }
  
  public Dimension getDefaultSize(Entity entity) throws InvalidEntity{
    
    if (entity.getType().equalsIgnoreCase("INGENIASComponent")) {
      return INGENIASComponentView.getSize((INGENIASComponent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("INGENIASCodeComponent")) {
      return INGENIASCodeComponentView.getSize((INGENIASCodeComponent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FileSpecPatternMapping")) {
      return FileSpecPatternMappingView.getSize((FileSpecPatternMapping)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Application")) {
      return ApplicationView.getSize((Application)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("EnvironmentApplication")) {
      return EnvironmentApplicationView.getSize((EnvironmentApplication)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("InternalApplication")) {
      return InternalApplicationView.getSize((InternalApplication)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Task")) {
      return TaskView.getSize((Task)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Test")) {
      return TestView.getSize((Test)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("WFTest")) {
      return WFTestView.getSize((WFTest)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("WFTestState")) {
      return WFTestStateView.getSize((WFTestState)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("WFTestFinalState")) {
      return WFTestFinalStateView.getSize((WFTestFinalState)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("WFTestInitialState")) {
      return WFTestInitialStateView.getSize((WFTestInitialState)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("UMLComment")) {
      return UMLCommentView.getSize((UMLComment)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("TaskWS")) {
      return TaskWSView.getSize((TaskWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ContextUseTask")) {
      return ContextUseTaskView.getSize((ContextUseTask)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("BoxedTask")) {
      return BoxedTaskView.getSize((BoxedTask)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ContextBindingTask")) {
      return ContextBindingTaskView.getSize((ContextBindingTask)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ContextReleaseTask")) {
      return ContextReleaseTaskView.getSize((ContextReleaseTask)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Plan")) {
      return PlanView.getSize((Plan)entity);      
    }
    else

    throw new ingenias.exception.InvalidEntity("Entity type "+entity+" is not allowed in this diagram"); 
	    
  }

  public DefaultGraphCell insert(Point point, String entity) throws InvalidEntity {
  // CellView information is not available after creating the cell.

    // Create a Map that holds the attributes for the Vertex
    Map map = new Hashtable();
    // Snap the Point to the Grid
    
    // Construct Vertex with no Label
    DefaultGraphCell vertex;
    Dimension size;

    vertex=this.createCell(entity);
    size=this.getDefaultSize((Entity)vertex.getUserObject());



    // Add a Bounds Attribute to the Map
    GraphConstants.setBounds(map, new Rectangle(point, size));

    // Construct a Map from cells to Maps (for insert)
    Hashtable attributes = new Hashtable();
    // Associate the Vertex with its Attributes
    attributes.put(vertex, map);
    // Insert the Vertex and its Attributes
    this.getModel().insert(new Object[] {vertex},attributes
                           , null, null, null);
    return vertex;
  }

  


public DefaultGraphCell insertDuplicated(Point point, ingenias.editor.entities.Entity
                               entity) {
    // CellView information is not available after creating the cell.

    // Create a Map that holds the attributes for the Vertex
    Map map =new Hashtable();
    // Snap the Point to the Grid
      

    // Construct Vertex with no Label
    DefaultGraphCell vertex = null;
    Dimension size = null;


    if (entity.getClass().equals(INGENIASComponent.class)) {
      vertex = new INGENIASComponentCell( (INGENIASComponent) entity);
      // Default Size for the new Vertex with the new entity within
      size = INGENIASComponentView.getSize((INGENIASComponent) entity);
    }
    else

    if (entity.getClass().equals(INGENIASCodeComponent.class)) {
      vertex = new INGENIASCodeComponentCell( (INGENIASCodeComponent) entity);
      // Default Size for the new Vertex with the new entity within
      size = INGENIASCodeComponentView.getSize((INGENIASCodeComponent) entity);
    }
    else

    if (entity.getClass().equals(FileSpecPatternMapping.class)) {
      vertex = new FileSpecPatternMappingCell( (FileSpecPatternMapping) entity);
      // Default Size for the new Vertex with the new entity within
      size = FileSpecPatternMappingView.getSize((FileSpecPatternMapping) entity);
    }
    else

    if (entity.getClass().equals(Application.class)) {
      vertex = new ApplicationCell( (Application) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationView.getSize((Application) entity);
    }
    else

    if (entity.getClass().equals(EnvironmentApplication.class)) {
      vertex = new EnvironmentApplicationCell( (EnvironmentApplication) entity);
      // Default Size for the new Vertex with the new entity within
      size = EnvironmentApplicationView.getSize((EnvironmentApplication) entity);
    }
    else

    if (entity.getClass().equals(InternalApplication.class)) {
      vertex = new InternalApplicationCell( (InternalApplication) entity);
      // Default Size for the new Vertex with the new entity within
      size = InternalApplicationView.getSize((InternalApplication) entity);
    }
    else

    if (entity.getClass().equals(Task.class)) {
      vertex = new TaskCell( (Task) entity);
      // Default Size for the new Vertex with the new entity within
      size = TaskView.getSize((Task) entity);
    }
    else

    if (entity.getClass().equals(Test.class)) {
      vertex = new TestCell( (Test) entity);
      // Default Size for the new Vertex with the new entity within
      size = TestView.getSize((Test) entity);
    }
    else

    if (entity.getClass().equals(WFTest.class)) {
      vertex = new WFTestCell( (WFTest) entity);
      // Default Size for the new Vertex with the new entity within
      size = WFTestView.getSize((WFTest) entity);
    }
    else

    if (entity.getClass().equals(WFTestState.class)) {
      vertex = new WFTestStateCell( (WFTestState) entity);
      // Default Size for the new Vertex with the new entity within
      size = WFTestStateView.getSize((WFTestState) entity);
    }
    else

    if (entity.getClass().equals(WFTestFinalState.class)) {
      vertex = new WFTestFinalStateCell( (WFTestFinalState) entity);
      // Default Size for the new Vertex with the new entity within
      size = WFTestFinalStateView.getSize((WFTestFinalState) entity);
    }
    else

    if (entity.getClass().equals(WFTestInitialState.class)) {
      vertex = new WFTestInitialStateCell( (WFTestInitialState) entity);
      // Default Size for the new Vertex with the new entity within
      size = WFTestInitialStateView.getSize((WFTestInitialState) entity);
    }
    else

    if (entity.getClass().equals(UMLComment.class)) {
      vertex = new UMLCommentCell( (UMLComment) entity);
      // Default Size for the new Vertex with the new entity within
      size = UMLCommentView.getSize((UMLComment) entity);
    }
    else

    if (entity.getClass().equals(TaskWS.class)) {
      vertex = new TaskWSCell( (TaskWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = TaskWSView.getSize((TaskWS) entity);
    }
    else

    if (entity.getClass().equals(ContextUseTask.class)) {
      vertex = new ContextUseTaskCell( (ContextUseTask) entity);
      // Default Size for the new Vertex with the new entity within
      size = ContextUseTaskView.getSize((ContextUseTask) entity);
    }
    else

    if (entity.getClass().equals(BoxedTask.class)) {
      vertex = new BoxedTaskCell( (BoxedTask) entity);
      // Default Size for the new Vertex with the new entity within
      size = BoxedTaskView.getSize((BoxedTask) entity);
    }
    else

    if (entity.getClass().equals(ContextBindingTask.class)) {
      vertex = new ContextBindingTaskCell( (ContextBindingTask) entity);
      // Default Size for the new Vertex with the new entity within
      size = ContextBindingTaskView.getSize((ContextBindingTask) entity);
    }
    else

    if (entity.getClass().equals(ContextReleaseTask.class)) {
      vertex = new ContextReleaseTaskCell( (ContextReleaseTask) entity);
      // Default Size for the new Vertex with the new entity within
      size = ContextReleaseTaskView.getSize((ContextReleaseTask) entity);
    }
    else

    if (entity.getClass().equals(Plan.class)) {
      vertex = new PlanCell( (Plan) entity);
      // Default Size for the new Vertex with the new entity within
      size = PlanView.getSize((Plan) entity);
    }
    else

   {}; // Just in case there is no allowed entity in the diagram

    if (vertex == null) {
     System.err.println(
		 "Object not allowed in ComponentDiagram diagram :"+ 
		 entity.getId()+":"+entity.getClass().getName()+
		 this.getClass().getName());    }
    else {

      // Add a Bounds Attribute to the Map
      GraphConstants.setBounds(map, new Rectangle(point, size));

      // Construct a Map from cells to Maps (for insert)
      Hashtable attributes = new Hashtable();
      // Associate the Vertex with its Attributes
      attributes.put(vertex, map);
      // Insert the Vertex and its Attributes
      this.getModel().insert(new Object[] {vertex},attributes
                             , null, null, null);
    }
   return vertex;

  }


}
