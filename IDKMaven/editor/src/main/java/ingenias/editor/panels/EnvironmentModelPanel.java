

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

public class EnvironmentModelPanel extends JGraph {

  public EnvironmentModelPanel(EnvironmentModelDataEntity mde, 
                               String nombre, Model
                               m, BasicMarqueeHandler mh) {
    super(m, mh);
    
    this.getGraphLayoutCache().setFactory(new ingenias.editor.cellfactories.EnvironmentModelCellViewFactory());
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


 entities.add("Agent");

 entities.add("OrganizationGroup");

 entities.add("Resource");

 entities.add("Application");

 entities.add("InternalApplication");

 entities.add("EnvironmentApplication");

 entities.add("ApplicationWS");

 entities.add("TextNote");

 entities.add("UMLComment");

 entities.add("Organization");

 entities.add("AMIContext");

 entities.add("FAERIEContext");

 entities.add("FAERIECtxtModel");

 entities.add("FAERIECtxtAttribute");

 entities.add("FAERIECtxtEntity");

 entities.add("FAERIECtxtRelationship");

 entities.add("FAERIECtxtValue");

 entities.add("RoleWS");

   return entities;
  }

 
   
  public DefaultGraphCell createCell(String entity) throws InvalidEntity{
  
    if (entity.equalsIgnoreCase("Agent")) {
    Agent nentity=new Agent(((Model)getModel()).getNewId("Agent"));
      DefaultGraphCell vertex = new
          AgentCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("OrganizationGroup")) {
    OrganizationGroup nentity=new OrganizationGroup(((Model)getModel()).getNewId("OrganizationGroup"));
      DefaultGraphCell vertex = new
          OrganizationGroupCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Resource")) {
    Resource nentity=new Resource(((Model)getModel()).getNewId("Resource"));
      DefaultGraphCell vertex = new
          ResourceCell(nentity);
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

    if (entity.equalsIgnoreCase("InternalApplication")) {
    InternalApplication nentity=new InternalApplication(((Model)getModel()).getNewId("InternalApplication"));
      DefaultGraphCell vertex = new
          InternalApplicationCell(nentity);
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

    if (entity.equalsIgnoreCase("ApplicationWS")) {
    ApplicationWS nentity=new ApplicationWS(((Model)getModel()).getNewId("ApplicationWS"));
      DefaultGraphCell vertex = new
          ApplicationWSCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("TextNote")) {
    TextNote nentity=new TextNote(((Model)getModel()).getNewId("TextNote"));
      DefaultGraphCell vertex = new
          TextNoteCell(nentity);
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

    if (entity.equalsIgnoreCase("Organization")) {
    Organization nentity=new Organization(((Model)getModel()).getNewId("Organization"));
      DefaultGraphCell vertex = new
          OrganizationCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("AMIContext")) {
    AMIContext nentity=new AMIContext(((Model)getModel()).getNewId("AMIContext"));
      DefaultGraphCell vertex = new
          AMIContextCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("FAERIEContext")) {
    FAERIEContext nentity=new FAERIEContext(((Model)getModel()).getNewId("FAERIEContext"));
      DefaultGraphCell vertex = new
          FAERIEContextCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("FAERIECtxtModel")) {
    FAERIECtxtModel nentity=new FAERIECtxtModel(((Model)getModel()).getNewId("FAERIECtxtModel"));
      DefaultGraphCell vertex = new
          FAERIECtxtModelCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("FAERIECtxtAttribute")) {
    FAERIECtxtAttribute nentity=new FAERIECtxtAttribute(((Model)getModel()).getNewId("FAERIECtxtAttribute"));
      DefaultGraphCell vertex = new
          FAERIECtxtAttributeCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("FAERIECtxtEntity")) {
    FAERIECtxtEntity nentity=new FAERIECtxtEntity(((Model)getModel()).getNewId("FAERIECtxtEntity"));
      DefaultGraphCell vertex = new
          FAERIECtxtEntityCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("FAERIECtxtRelationship")) {
    FAERIECtxtRelationship nentity=new FAERIECtxtRelationship(((Model)getModel()).getNewId("FAERIECtxtRelationship"));
      DefaultGraphCell vertex = new
          FAERIECtxtRelationshipCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("FAERIECtxtValue")) {
    FAERIECtxtValue nentity=new FAERIECtxtValue(((Model)getModel()).getNewId("FAERIECtxtValue"));
      DefaultGraphCell vertex = new
          FAERIECtxtValueCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("RoleWS")) {
    RoleWS nentity=new RoleWS(((Model)getModel()).getNewId("RoleWS"));
      DefaultGraphCell vertex = new
          RoleWSCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

	  throw new ingenias.exception.InvalidEntity("Entity type "+entity+" is not allowed in this diagram"); 
  }
  
  public Dimension getDefaultSize(Entity entity) throws InvalidEntity{
    
    if (entity.getType().equalsIgnoreCase("Agent")) {
      return AgentView.getSize((Agent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("OrganizationGroup")) {
      return OrganizationGroupView.getSize((OrganizationGroup)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Resource")) {
      return ResourceView.getSize((Resource)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Application")) {
      return ApplicationView.getSize((Application)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("InternalApplication")) {
      return InternalApplicationView.getSize((InternalApplication)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("EnvironmentApplication")) {
      return EnvironmentApplicationView.getSize((EnvironmentApplication)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ApplicationWS")) {
      return ApplicationWSView.getSize((ApplicationWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("TextNote")) {
      return TextNoteView.getSize((TextNote)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("UMLComment")) {
      return UMLCommentView.getSize((UMLComment)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Organization")) {
      return OrganizationView.getSize((Organization)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AMIContext")) {
      return AMIContextView.getSize((AMIContext)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIEContext")) {
      return FAERIEContextView.getSize((FAERIEContext)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIECtxtModel")) {
      return FAERIECtxtModelView.getSize((FAERIECtxtModel)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIECtxtAttribute")) {
      return FAERIECtxtAttributeView.getSize((FAERIECtxtAttribute)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIECtxtEntity")) {
      return FAERIECtxtEntityView.getSize((FAERIECtxtEntity)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIECtxtRelationship")) {
      return FAERIECtxtRelationshipView.getSize((FAERIECtxtRelationship)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIECtxtValue")) {
      return FAERIECtxtValueView.getSize((FAERIECtxtValue)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RoleWS")) {
      return RoleWSView.getSize((RoleWS)entity);      
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


    if (entity.getClass().equals(Agent.class)) {
      vertex = new AgentCell( (Agent) entity);
      // Default Size for the new Vertex with the new entity within
      size = AgentView.getSize((Agent) entity);
    }
    else

    if (entity.getClass().equals(OrganizationGroup.class)) {
      vertex = new OrganizationGroupCell( (OrganizationGroup) entity);
      // Default Size for the new Vertex with the new entity within
      size = OrganizationGroupView.getSize((OrganizationGroup) entity);
    }
    else

    if (entity.getClass().equals(Resource.class)) {
      vertex = new ResourceCell( (Resource) entity);
      // Default Size for the new Vertex with the new entity within
      size = ResourceView.getSize((Resource) entity);
    }
    else

    if (entity.getClass().equals(Application.class)) {
      vertex = new ApplicationCell( (Application) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationView.getSize((Application) entity);
    }
    else

    if (entity.getClass().equals(InternalApplication.class)) {
      vertex = new InternalApplicationCell( (InternalApplication) entity);
      // Default Size for the new Vertex with the new entity within
      size = InternalApplicationView.getSize((InternalApplication) entity);
    }
    else

    if (entity.getClass().equals(EnvironmentApplication.class)) {
      vertex = new EnvironmentApplicationCell( (EnvironmentApplication) entity);
      // Default Size for the new Vertex with the new entity within
      size = EnvironmentApplicationView.getSize((EnvironmentApplication) entity);
    }
    else

    if (entity.getClass().equals(ApplicationWS.class)) {
      vertex = new ApplicationWSCell( (ApplicationWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationWSView.getSize((ApplicationWS) entity);
    }
    else

    if (entity.getClass().equals(TextNote.class)) {
      vertex = new TextNoteCell( (TextNote) entity);
      // Default Size for the new Vertex with the new entity within
      size = TextNoteView.getSize((TextNote) entity);
    }
    else

    if (entity.getClass().equals(UMLComment.class)) {
      vertex = new UMLCommentCell( (UMLComment) entity);
      // Default Size for the new Vertex with the new entity within
      size = UMLCommentView.getSize((UMLComment) entity);
    }
    else

    if (entity.getClass().equals(Organization.class)) {
      vertex = new OrganizationCell( (Organization) entity);
      // Default Size for the new Vertex with the new entity within
      size = OrganizationView.getSize((Organization) entity);
    }
    else

    if (entity.getClass().equals(AMIContext.class)) {
      vertex = new AMIContextCell( (AMIContext) entity);
      // Default Size for the new Vertex with the new entity within
      size = AMIContextView.getSize((AMIContext) entity);
    }
    else

    if (entity.getClass().equals(FAERIEContext.class)) {
      vertex = new FAERIEContextCell( (FAERIEContext) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIEContextView.getSize((FAERIEContext) entity);
    }
    else

    if (entity.getClass().equals(FAERIECtxtModel.class)) {
      vertex = new FAERIECtxtModelCell( (FAERIECtxtModel) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIECtxtModelView.getSize((FAERIECtxtModel) entity);
    }
    else

    if (entity.getClass().equals(FAERIECtxtAttribute.class)) {
      vertex = new FAERIECtxtAttributeCell( (FAERIECtxtAttribute) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIECtxtAttributeView.getSize((FAERIECtxtAttribute) entity);
    }
    else

    if (entity.getClass().equals(FAERIECtxtEntity.class)) {
      vertex = new FAERIECtxtEntityCell( (FAERIECtxtEntity) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIECtxtEntityView.getSize((FAERIECtxtEntity) entity);
    }
    else

    if (entity.getClass().equals(FAERIECtxtRelationship.class)) {
      vertex = new FAERIECtxtRelationshipCell( (FAERIECtxtRelationship) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIECtxtRelationshipView.getSize((FAERIECtxtRelationship) entity);
    }
    else

    if (entity.getClass().equals(FAERIECtxtValue.class)) {
      vertex = new FAERIECtxtValueCell( (FAERIECtxtValue) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIECtxtValueView.getSize((FAERIECtxtValue) entity);
    }
    else

    if (entity.getClass().equals(RoleWS.class)) {
      vertex = new RoleWSCell( (RoleWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = RoleWSView.getSize((RoleWS) entity);
    }
    else

   {}; // Just in case there is no allowed entity in the diagram

    if (vertex == null) {
     System.err.println(
		 "Object not allowed in EnvironmentModel diagram :"+ 
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
