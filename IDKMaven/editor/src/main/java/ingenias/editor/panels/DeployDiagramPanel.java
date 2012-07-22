

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

public class DeployDiagramPanel extends JGraph {

  public DeployDiagramPanel(DeployDiagramDataEntity mde, 
                               String nombre, Model
                               m, BasicMarqueeHandler mh) {
    super(m, mh);
    
    this.getGraphLayoutCache().setFactory(new ingenias.editor.cellfactories.DeployDiagramCellViewFactory());
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


 entities.add("TestingPackage");

 entities.add("DeploymentPackage");

 entities.add("DeploymentPackageWithContext");

 entities.add("INGENIASComponent");

 entities.add("Application");

 entities.add("SimulationPackage");

 entities.add("SimulationEvent");

 entities.add("SimExtractedInformation");

 entities.add("INGENIASCodeComponent");

 entities.add("EnvironmentApplication");

 entities.add("InternalApplication");

 entities.add("UMLComment");

 entities.add("Goal");

 entities.add("DeploymentUnitByType");

 entities.add("DeploymentUnitByTypeEnumInitMS");

 entities.add("DeploymentUnitByTypeMSEntity");

 entities.add("AMIContext");

 entities.add("Fact");

 entities.add("ApplicationWS");

 entities.add("DeploymentUnitByTypeWithInitMS");

 entities.add("ApplicationEvent");

 entities.add("MentalState");

 entities.add("GoalStateWS");

 entities.add("FrameFact");

 entities.add("Test");

 entities.add("RuntimeEvent");

 entities.add("FAERIEContext");

 entities.add("AgentWS");

 entities.add("AgentModelBelieve");

 entities.add("AMIContextBindingData");

 entities.add("ApplicationEventSlots");

 entities.add("Compromise");

 entities.add("FileSpecPatternMapping");

 entities.add("Conversation");

 entities.add("FAERIECtxtModel");

 entities.add("Agent");

 entities.add("RuntimeConversation");

 entities.add("CommunicationEvent");

 entities.add("MentalInstanceSpecification");

 entities.add("GeneralEvent");

 entities.add("ConditionalMentalState");

 entities.add("StateGoal");

 entities.add("RuntimeFact");

 entities.add("Believe");

   return entities;
  }

 
   
  public DefaultGraphCell createCell(String entity) throws InvalidEntity{
  
    if (entity.equalsIgnoreCase("TestingPackage")) {
    TestingPackage nentity=new TestingPackage(((Model)getModel()).getNewId("TestingPackage"));
      DefaultGraphCell vertex = new
          TestingPackageCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("DeploymentPackage")) {
    DeploymentPackage nentity=new DeploymentPackage(((Model)getModel()).getNewId("DeploymentPackage"));
      DefaultGraphCell vertex = new
          DeploymentPackageCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("DeploymentPackageWithContext")) {
    DeploymentPackageWithContext nentity=new DeploymentPackageWithContext(((Model)getModel()).getNewId("DeploymentPackageWithContext"));
      DefaultGraphCell vertex = new
          DeploymentPackageWithContextCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("INGENIASComponent")) {
    INGENIASComponent nentity=new INGENIASComponent(((Model)getModel()).getNewId("INGENIASComponent"));
      DefaultGraphCell vertex = new
          INGENIASComponentCell(nentity);
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

    if (entity.equalsIgnoreCase("SimulationPackage")) {
    SimulationPackage nentity=new SimulationPackage(((Model)getModel()).getNewId("SimulationPackage"));
      DefaultGraphCell vertex = new
          SimulationPackageCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("SimulationEvent")) {
    SimulationEvent nentity=new SimulationEvent(((Model)getModel()).getNewId("SimulationEvent"));
      DefaultGraphCell vertex = new
          SimulationEventCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("SimExtractedInformation")) {
    SimExtractedInformation nentity=new SimExtractedInformation(((Model)getModel()).getNewId("SimExtractedInformation"));
      DefaultGraphCell vertex = new
          SimExtractedInformationCell(nentity);
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

    if (entity.equalsIgnoreCase("UMLComment")) {
    UMLComment nentity=new UMLComment(((Model)getModel()).getNewId("UMLComment"));
      DefaultGraphCell vertex = new
          UMLCommentCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Goal")) {
    Goal nentity=new Goal(((Model)getModel()).getNewId("Goal"));
      DefaultGraphCell vertex = new
          GoalCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("DeploymentUnitByType")) {
    DeploymentUnitByType nentity=new DeploymentUnitByType(((Model)getModel()).getNewId("DeploymentUnitByType"));
      DefaultGraphCell vertex = new
          DeploymentUnitByTypeCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("DeploymentUnitByTypeEnumInitMS")) {
    DeploymentUnitByTypeEnumInitMS nentity=new DeploymentUnitByTypeEnumInitMS(((Model)getModel()).getNewId("DeploymentUnitByTypeEnumInitMS"));
      DefaultGraphCell vertex = new
          DeploymentUnitByTypeEnumInitMSCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("DeploymentUnitByTypeMSEntity")) {
    DeploymentUnitByTypeMSEntity nentity=new DeploymentUnitByTypeMSEntity(((Model)getModel()).getNewId("DeploymentUnitByTypeMSEntity"));
      DefaultGraphCell vertex = new
          DeploymentUnitByTypeMSEntityCell(nentity);
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

    if (entity.equalsIgnoreCase("Fact")) {
    Fact nentity=new Fact(((Model)getModel()).getNewId("Fact"));
      DefaultGraphCell vertex = new
          FactCell(nentity);
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

    if (entity.equalsIgnoreCase("DeploymentUnitByTypeWithInitMS")) {
    DeploymentUnitByTypeWithInitMS nentity=new DeploymentUnitByTypeWithInitMS(((Model)getModel()).getNewId("DeploymentUnitByTypeWithInitMS"));
      DefaultGraphCell vertex = new
          DeploymentUnitByTypeWithInitMSCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("ApplicationEvent")) {
    ApplicationEvent nentity=new ApplicationEvent(((Model)getModel()).getNewId("ApplicationEvent"));
      DefaultGraphCell vertex = new
          ApplicationEventCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("MentalState")) {
    MentalState nentity=new MentalState(((Model)getModel()).getNewId("MentalState"));
      DefaultGraphCell vertex = new
          MentalStateCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("GoalStateWS")) {
    GoalStateWS nentity=new GoalStateWS(((Model)getModel()).getNewId("GoalStateWS"));
      DefaultGraphCell vertex = new
          GoalStateWSCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("FrameFact")) {
    FrameFact nentity=new FrameFact(((Model)getModel()).getNewId("FrameFact"));
      DefaultGraphCell vertex = new
          FrameFactCell(nentity);
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

    if (entity.equalsIgnoreCase("RuntimeEvent")) {
    RuntimeEvent nentity=new RuntimeEvent(((Model)getModel()).getNewId("RuntimeEvent"));
      DefaultGraphCell vertex = new
          RuntimeEventCell(nentity);
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

    if (entity.equalsIgnoreCase("AgentWS")) {
    AgentWS nentity=new AgentWS(((Model)getModel()).getNewId("AgentWS"));
      DefaultGraphCell vertex = new
          AgentWSCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("AgentModelBelieve")) {
    AgentModelBelieve nentity=new AgentModelBelieve(((Model)getModel()).getNewId("AgentModelBelieve"));
      DefaultGraphCell vertex = new
          AgentModelBelieveCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("AMIContextBindingData")) {
    AMIContextBindingData nentity=new AMIContextBindingData(((Model)getModel()).getNewId("AMIContextBindingData"));
      DefaultGraphCell vertex = new
          AMIContextBindingDataCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("ApplicationEventSlots")) {
    ApplicationEventSlots nentity=new ApplicationEventSlots(((Model)getModel()).getNewId("ApplicationEventSlots"));
      DefaultGraphCell vertex = new
          ApplicationEventSlotsCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Compromise")) {
    Compromise nentity=new Compromise(((Model)getModel()).getNewId("Compromise"));
      DefaultGraphCell vertex = new
          CompromiseCell(nentity);
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

    if (entity.equalsIgnoreCase("Conversation")) {
    Conversation nentity=new Conversation(((Model)getModel()).getNewId("Conversation"));
      DefaultGraphCell vertex = new
          ConversationCell(nentity);
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

    if (entity.equalsIgnoreCase("Agent")) {
    Agent nentity=new Agent(((Model)getModel()).getNewId("Agent"));
      DefaultGraphCell vertex = new
          AgentCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("RuntimeConversation")) {
    RuntimeConversation nentity=new RuntimeConversation(((Model)getModel()).getNewId("RuntimeConversation"));
      DefaultGraphCell vertex = new
          RuntimeConversationCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("CommunicationEvent")) {
    CommunicationEvent nentity=new CommunicationEvent(((Model)getModel()).getNewId("CommunicationEvent"));
      DefaultGraphCell vertex = new
          CommunicationEventCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("MentalInstanceSpecification")) {
    MentalInstanceSpecification nentity=new MentalInstanceSpecification(((Model)getModel()).getNewId("MentalInstanceSpecification"));
      DefaultGraphCell vertex = new
          MentalInstanceSpecificationCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("GeneralEvent")) {
    GeneralEvent nentity=new GeneralEvent(((Model)getModel()).getNewId("GeneralEvent"));
      DefaultGraphCell vertex = new
          GeneralEventCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("ConditionalMentalState")) {
    ConditionalMentalState nentity=new ConditionalMentalState(((Model)getModel()).getNewId("ConditionalMentalState"));
      DefaultGraphCell vertex = new
          ConditionalMentalStateCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("StateGoal")) {
    StateGoal nentity=new StateGoal(((Model)getModel()).getNewId("StateGoal"));
      DefaultGraphCell vertex = new
          StateGoalCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("RuntimeFact")) {
    RuntimeFact nentity=new RuntimeFact(((Model)getModel()).getNewId("RuntimeFact"));
      DefaultGraphCell vertex = new
          RuntimeFactCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

    if (entity.equalsIgnoreCase("Believe")) {
    Believe nentity=new Believe(((Model)getModel()).getNewId("Believe"));
      DefaultGraphCell vertex = new
          BelieveCell(nentity);
      // Default Size for the cell with the new entity
     return vertex;
    }
    else

	  throw new ingenias.exception.InvalidEntity("Entity type "+entity+" is not allowed in this diagram"); 
  }
  
  public Dimension getDefaultSize(Entity entity) throws InvalidEntity{
    
    if (entity.getType().equalsIgnoreCase("TestingPackage")) {
      return TestingPackageView.getSize((TestingPackage)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("DeploymentPackage")) {
      return DeploymentPackageView.getSize((DeploymentPackage)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("DeploymentPackageWithContext")) {
      return DeploymentPackageWithContextView.getSize((DeploymentPackageWithContext)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("INGENIASComponent")) {
      return INGENIASComponentView.getSize((INGENIASComponent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Application")) {
      return ApplicationView.getSize((Application)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("SimulationPackage")) {
      return SimulationPackageView.getSize((SimulationPackage)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("SimulationEvent")) {
      return SimulationEventView.getSize((SimulationEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("SimExtractedInformation")) {
      return SimExtractedInformationView.getSize((SimExtractedInformation)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("INGENIASCodeComponent")) {
      return INGENIASCodeComponentView.getSize((INGENIASCodeComponent)entity);      
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

    if (entity.getType().equalsIgnoreCase("UMLComment")) {
      return UMLCommentView.getSize((UMLComment)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Goal")) {
      return GoalView.getSize((Goal)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("DeploymentUnitByType")) {
      return DeploymentUnitByTypeView.getSize((DeploymentUnitByType)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("DeploymentUnitByTypeEnumInitMS")) {
      return DeploymentUnitByTypeEnumInitMSView.getSize((DeploymentUnitByTypeEnumInitMS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("DeploymentUnitByTypeMSEntity")) {
      return DeploymentUnitByTypeMSEntityView.getSize((DeploymentUnitByTypeMSEntity)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AMIContext")) {
      return AMIContextView.getSize((AMIContext)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Fact")) {
      return FactView.getSize((Fact)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ApplicationWS")) {
      return ApplicationWSView.getSize((ApplicationWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("DeploymentUnitByTypeWithInitMS")) {
      return DeploymentUnitByTypeWithInitMSView.getSize((DeploymentUnitByTypeWithInitMS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ApplicationEvent")) {
      return ApplicationEventView.getSize((ApplicationEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("MentalState")) {
      return MentalStateView.getSize((MentalState)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("GoalStateWS")) {
      return GoalStateWSView.getSize((GoalStateWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FrameFact")) {
      return FrameFactView.getSize((FrameFact)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Test")) {
      return TestView.getSize((Test)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RuntimeEvent")) {
      return RuntimeEventView.getSize((RuntimeEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIEContext")) {
      return FAERIEContextView.getSize((FAERIEContext)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AgentWS")) {
      return AgentWSView.getSize((AgentWS)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AgentModelBelieve")) {
      return AgentModelBelieveView.getSize((AgentModelBelieve)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("AMIContextBindingData")) {
      return AMIContextBindingDataView.getSize((AMIContextBindingData)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ApplicationEventSlots")) {
      return ApplicationEventSlotsView.getSize((ApplicationEventSlots)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Compromise")) {
      return CompromiseView.getSize((Compromise)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FileSpecPatternMapping")) {
      return FileSpecPatternMappingView.getSize((FileSpecPatternMapping)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Conversation")) {
      return ConversationView.getSize((Conversation)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("FAERIECtxtModel")) {
      return FAERIECtxtModelView.getSize((FAERIECtxtModel)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Agent")) {
      return AgentView.getSize((Agent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RuntimeConversation")) {
      return RuntimeConversationView.getSize((RuntimeConversation)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("CommunicationEvent")) {
      return CommunicationEventView.getSize((CommunicationEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("MentalInstanceSpecification")) {
      return MentalInstanceSpecificationView.getSize((MentalInstanceSpecification)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("GeneralEvent")) {
      return GeneralEventView.getSize((GeneralEvent)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("ConditionalMentalState")) {
      return ConditionalMentalStateView.getSize((ConditionalMentalState)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("StateGoal")) {
      return StateGoalView.getSize((StateGoal)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("RuntimeFact")) {
      return RuntimeFactView.getSize((RuntimeFact)entity);      
    }
    else

    if (entity.getType().equalsIgnoreCase("Believe")) {
      return BelieveView.getSize((Believe)entity);      
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


    if (entity.getClass().equals(TestingPackage.class)) {
      vertex = new TestingPackageCell( (TestingPackage) entity);
      // Default Size for the new Vertex with the new entity within
      size = TestingPackageView.getSize((TestingPackage) entity);
    }
    else

    if (entity.getClass().equals(DeploymentPackage.class)) {
      vertex = new DeploymentPackageCell( (DeploymentPackage) entity);
      // Default Size for the new Vertex with the new entity within
      size = DeploymentPackageView.getSize((DeploymentPackage) entity);
    }
    else

    if (entity.getClass().equals(DeploymentPackageWithContext.class)) {
      vertex = new DeploymentPackageWithContextCell( (DeploymentPackageWithContext) entity);
      // Default Size for the new Vertex with the new entity within
      size = DeploymentPackageWithContextView.getSize((DeploymentPackageWithContext) entity);
    }
    else

    if (entity.getClass().equals(INGENIASComponent.class)) {
      vertex = new INGENIASComponentCell( (INGENIASComponent) entity);
      // Default Size for the new Vertex with the new entity within
      size = INGENIASComponentView.getSize((INGENIASComponent) entity);
    }
    else

    if (entity.getClass().equals(Application.class)) {
      vertex = new ApplicationCell( (Application) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationView.getSize((Application) entity);
    }
    else

    if (entity.getClass().equals(SimulationPackage.class)) {
      vertex = new SimulationPackageCell( (SimulationPackage) entity);
      // Default Size for the new Vertex with the new entity within
      size = SimulationPackageView.getSize((SimulationPackage) entity);
    }
    else

    if (entity.getClass().equals(SimulationEvent.class)) {
      vertex = new SimulationEventCell( (SimulationEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = SimulationEventView.getSize((SimulationEvent) entity);
    }
    else

    if (entity.getClass().equals(SimExtractedInformation.class)) {
      vertex = new SimExtractedInformationCell( (SimExtractedInformation) entity);
      // Default Size for the new Vertex with the new entity within
      size = SimExtractedInformationView.getSize((SimExtractedInformation) entity);
    }
    else

    if (entity.getClass().equals(INGENIASCodeComponent.class)) {
      vertex = new INGENIASCodeComponentCell( (INGENIASCodeComponent) entity);
      // Default Size for the new Vertex with the new entity within
      size = INGENIASCodeComponentView.getSize((INGENIASCodeComponent) entity);
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

    if (entity.getClass().equals(UMLComment.class)) {
      vertex = new UMLCommentCell( (UMLComment) entity);
      // Default Size for the new Vertex with the new entity within
      size = UMLCommentView.getSize((UMLComment) entity);
    }
    else

    if (entity.getClass().equals(Goal.class)) {
      vertex = new GoalCell( (Goal) entity);
      // Default Size for the new Vertex with the new entity within
      size = GoalView.getSize((Goal) entity);
    }
    else

    if (entity.getClass().equals(DeploymentUnitByType.class)) {
      vertex = new DeploymentUnitByTypeCell( (DeploymentUnitByType) entity);
      // Default Size for the new Vertex with the new entity within
      size = DeploymentUnitByTypeView.getSize((DeploymentUnitByType) entity);
    }
    else

    if (entity.getClass().equals(DeploymentUnitByTypeEnumInitMS.class)) {
      vertex = new DeploymentUnitByTypeEnumInitMSCell( (DeploymentUnitByTypeEnumInitMS) entity);
      // Default Size for the new Vertex with the new entity within
      size = DeploymentUnitByTypeEnumInitMSView.getSize((DeploymentUnitByTypeEnumInitMS) entity);
    }
    else

    if (entity.getClass().equals(DeploymentUnitByTypeMSEntity.class)) {
      vertex = new DeploymentUnitByTypeMSEntityCell( (DeploymentUnitByTypeMSEntity) entity);
      // Default Size for the new Vertex with the new entity within
      size = DeploymentUnitByTypeMSEntityView.getSize((DeploymentUnitByTypeMSEntity) entity);
    }
    else

    if (entity.getClass().equals(AMIContext.class)) {
      vertex = new AMIContextCell( (AMIContext) entity);
      // Default Size for the new Vertex with the new entity within
      size = AMIContextView.getSize((AMIContext) entity);
    }
    else

    if (entity.getClass().equals(Fact.class)) {
      vertex = new FactCell( (Fact) entity);
      // Default Size for the new Vertex with the new entity within
      size = FactView.getSize((Fact) entity);
    }
    else

    if (entity.getClass().equals(ApplicationWS.class)) {
      vertex = new ApplicationWSCell( (ApplicationWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationWSView.getSize((ApplicationWS) entity);
    }
    else

    if (entity.getClass().equals(DeploymentUnitByTypeWithInitMS.class)) {
      vertex = new DeploymentUnitByTypeWithInitMSCell( (DeploymentUnitByTypeWithInitMS) entity);
      // Default Size for the new Vertex with the new entity within
      size = DeploymentUnitByTypeWithInitMSView.getSize((DeploymentUnitByTypeWithInitMS) entity);
    }
    else

    if (entity.getClass().equals(ApplicationEvent.class)) {
      vertex = new ApplicationEventCell( (ApplicationEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationEventView.getSize((ApplicationEvent) entity);
    }
    else

    if (entity.getClass().equals(MentalState.class)) {
      vertex = new MentalStateCell( (MentalState) entity);
      // Default Size for the new Vertex with the new entity within
      size = MentalStateView.getSize((MentalState) entity);
    }
    else

    if (entity.getClass().equals(GoalStateWS.class)) {
      vertex = new GoalStateWSCell( (GoalStateWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = GoalStateWSView.getSize((GoalStateWS) entity);
    }
    else

    if (entity.getClass().equals(FrameFact.class)) {
      vertex = new FrameFactCell( (FrameFact) entity);
      // Default Size for the new Vertex with the new entity within
      size = FrameFactView.getSize((FrameFact) entity);
    }
    else

    if (entity.getClass().equals(Test.class)) {
      vertex = new TestCell( (Test) entity);
      // Default Size for the new Vertex with the new entity within
      size = TestView.getSize((Test) entity);
    }
    else

    if (entity.getClass().equals(RuntimeEvent.class)) {
      vertex = new RuntimeEventCell( (RuntimeEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = RuntimeEventView.getSize((RuntimeEvent) entity);
    }
    else

    if (entity.getClass().equals(FAERIEContext.class)) {
      vertex = new FAERIEContextCell( (FAERIEContext) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIEContextView.getSize((FAERIEContext) entity);
    }
    else

    if (entity.getClass().equals(AgentWS.class)) {
      vertex = new AgentWSCell( (AgentWS) entity);
      // Default Size for the new Vertex with the new entity within
      size = AgentWSView.getSize((AgentWS) entity);
    }
    else

    if (entity.getClass().equals(AgentModelBelieve.class)) {
      vertex = new AgentModelBelieveCell( (AgentModelBelieve) entity);
      // Default Size for the new Vertex with the new entity within
      size = AgentModelBelieveView.getSize((AgentModelBelieve) entity);
    }
    else

    if (entity.getClass().equals(AMIContextBindingData.class)) {
      vertex = new AMIContextBindingDataCell( (AMIContextBindingData) entity);
      // Default Size for the new Vertex with the new entity within
      size = AMIContextBindingDataView.getSize((AMIContextBindingData) entity);
    }
    else

    if (entity.getClass().equals(ApplicationEventSlots.class)) {
      vertex = new ApplicationEventSlotsCell( (ApplicationEventSlots) entity);
      // Default Size for the new Vertex with the new entity within
      size = ApplicationEventSlotsView.getSize((ApplicationEventSlots) entity);
    }
    else

    if (entity.getClass().equals(Compromise.class)) {
      vertex = new CompromiseCell( (Compromise) entity);
      // Default Size for the new Vertex with the new entity within
      size = CompromiseView.getSize((Compromise) entity);
    }
    else

    if (entity.getClass().equals(FileSpecPatternMapping.class)) {
      vertex = new FileSpecPatternMappingCell( (FileSpecPatternMapping) entity);
      // Default Size for the new Vertex with the new entity within
      size = FileSpecPatternMappingView.getSize((FileSpecPatternMapping) entity);
    }
    else

    if (entity.getClass().equals(Conversation.class)) {
      vertex = new ConversationCell( (Conversation) entity);
      // Default Size for the new Vertex with the new entity within
      size = ConversationView.getSize((Conversation) entity);
    }
    else

    if (entity.getClass().equals(FAERIECtxtModel.class)) {
      vertex = new FAERIECtxtModelCell( (FAERIECtxtModel) entity);
      // Default Size for the new Vertex with the new entity within
      size = FAERIECtxtModelView.getSize((FAERIECtxtModel) entity);
    }
    else

    if (entity.getClass().equals(Agent.class)) {
      vertex = new AgentCell( (Agent) entity);
      // Default Size for the new Vertex with the new entity within
      size = AgentView.getSize((Agent) entity);
    }
    else

    if (entity.getClass().equals(RuntimeConversation.class)) {
      vertex = new RuntimeConversationCell( (RuntimeConversation) entity);
      // Default Size for the new Vertex with the new entity within
      size = RuntimeConversationView.getSize((RuntimeConversation) entity);
    }
    else

    if (entity.getClass().equals(CommunicationEvent.class)) {
      vertex = new CommunicationEventCell( (CommunicationEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = CommunicationEventView.getSize((CommunicationEvent) entity);
    }
    else

    if (entity.getClass().equals(MentalInstanceSpecification.class)) {
      vertex = new MentalInstanceSpecificationCell( (MentalInstanceSpecification) entity);
      // Default Size for the new Vertex with the new entity within
      size = MentalInstanceSpecificationView.getSize((MentalInstanceSpecification) entity);
    }
    else

    if (entity.getClass().equals(GeneralEvent.class)) {
      vertex = new GeneralEventCell( (GeneralEvent) entity);
      // Default Size for the new Vertex with the new entity within
      size = GeneralEventView.getSize((GeneralEvent) entity);
    }
    else

    if (entity.getClass().equals(ConditionalMentalState.class)) {
      vertex = new ConditionalMentalStateCell( (ConditionalMentalState) entity);
      // Default Size for the new Vertex with the new entity within
      size = ConditionalMentalStateView.getSize((ConditionalMentalState) entity);
    }
    else

    if (entity.getClass().equals(StateGoal.class)) {
      vertex = new StateGoalCell( (StateGoal) entity);
      // Default Size for the new Vertex with the new entity within
      size = StateGoalView.getSize((StateGoal) entity);
    }
    else

    if (entity.getClass().equals(RuntimeFact.class)) {
      vertex = new RuntimeFactCell( (RuntimeFact) entity);
      // Default Size for the new Vertex with the new entity within
      size = RuntimeFactView.getSize((RuntimeFact) entity);
    }
    else

    if (entity.getClass().equals(Believe.class)) {
      vertex = new BelieveCell( (Believe) entity);
      // Default Size for the new Vertex with the new entity within
      size = BelieveView.getSize((Believe) entity);
    }
    else

   {}; // Just in case there is no allowed entity in the diagram

    if (vertex == null) {
     System.err.println(
		 "Object not allowed in DeployDiagram diagram :"+ 
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
