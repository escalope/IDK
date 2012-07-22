

/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz over original code from Ruben Fuentes
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
 
package ingenias.editor.persistence;

import java.lang.reflect.*;
import javax.swing.tree.*;
import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.InputSource;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.OutputStreamWriter;
import java.io.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;
import java.util.Map;
import java.util.Hashtable;
import java.util.ArrayList;
import javax.xml.parsers.*;
import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;
import ingenias.editor.entities.*;
import ingenias.exception.*;
import ingenias.editor.cell.*;
import ingenias.editor.*;


public class GraphLoadImp1
    implements GraphLoad {

  /**
   *  Constructor for the GraphLoad object
   */
  public GraphLoadImp1() {}

  
  private ModelJGraph fromGXL(ObjectManager om, RelationshipManager rm,
                              ModelJGraph graph, org.w3c.dom.Node node, org.w3c.dom.Node nodeView) {
    try {

      // Get Graph's Child Nodes (the cells)
      NodeList list = node.getChildNodes();
      // Get Graph's Child Nodes (the views)
      NodeList listView = nodeView.getChildNodes();
      // ConnectionSet for the Insert method
      ConnectionSet cs = new ConnectionSet();
      // Hashtable for the ID lookup (ID to Vertex)
      Hashtable ids = new Hashtable();
      // Hashtable for Attributes (Vertex to Map)
      Hashtable attributes = new Hashtable();
      Vector edges = new Vector();
      Vector edgesAttr = new Vector();
      // Loop Children
      for (int i = 0; i < list.getLength(); i++) {
        // The order is the same in both trees.
        node = list.item(i);
        nodeView = listView.item(i);
        // If Valid Node
        if (node.getAttributes() != null && node.getNodeName() != null) {
          // Fetch Supertype
          String supertype = (String) node.getNodeName();
          // Create Vertex
          if (supertype.equals("node")) {
            String id = node.getAttributes().getNamedItem("id").getNodeValue();
            String type = node.getAttributes().getNamedItem("type").
                getNodeValue();
            DefaultGraphCell vertex = GXLVertex(id, type, graph, om, rm);
            if ( (vertex != null) && ! (vertex instanceof NAryEdge)) {
              // Add ID, Vertex pair to Hashtable
              if (node.getAttributes().getNamedItem("nid") != null) {
                ids.put(node.getAttributes().getNamedItem("nid").getNodeValue(),
                        vertex);
              }
              else {
                ids.put(node.getAttributes().getNamedItem("id").getNodeValue(),
                        vertex);
                // Add Attributes
              }
              Map vertexAttr = GXLCellView(graph, vertex, ids, nodeView);
              attributes.put(vertex, vertexAttr);
              // Add Vertex to new Cells
              graph.getModel().insert(new Object[] {vertex},attributes,
                                       cs, null, null);
//              Log.getInstance().logSYS("loaded " + vertex.getUserObject());
            }
            else {
              if (vertex != null && (vertex instanceof NAryEdge)) {
                edges.add(vertex);
                Vector idscon = getConnectedEntities(node);
                Map vertexAttr = GXLCellView(graph, vertex, ids, nodeView);
                edgesAttr.add(vertexAttr);
                edgesAttr.add(idscon);

              }
            }
            // Create Edge
          }
        }
      }
      Enumeration enumeration = edges.elements();
      Enumeration enumeration1 = edgesAttr.elements();
      while (enumeration.hasMoreElements()) {
        NAryEdge ne = (NAryEdge) enumeration.nextElement();
        NAryEdgeEntity ent = (NAryEdgeEntity) ne.getUserObject();
        Map eas = (Map) enumeration1.nextElement();
        Vector idscon = ( (Vector) enumeration1.nextElement());
        DefaultGraphCell gcs[] = null;
        if (idscon.size() > 0) {
          String[] idEnts = ent.getIds();
          gcs = new DefaultGraphCell[idscon.size()];

          for (int k = 0; k < idscon.size(); k++) {
            String id = idscon.elementAt(k).toString();
            gcs[k] = (DefaultGraphCell) ids.get(id);
            //this.getGraphCell(graph,id);

          }
        }
        else {
          String[] idEnts = ent.getIds();
          gcs = new DefaultGraphCell[idEnts.length];

          for (int k = 0; k < idEnts.length; k++) {
            String id = idEnts[k];
            gcs[k] = this.getGraphCell(graph, id);

          }
        }

        this.connect(graph, gcs, ne, eas);
      }
      return graph;
    }
    catch (Exception e) {
      // Display error message on stderr
      e.printStackTrace();
      return null;
    }
  }

  // Convert an CellView represented by a GXL DOM node in a Map.
  // cell is the GraphCell represented by the returned CellView.
  // ids has a mapping from id to vertex.
  
  private Map GXLCellView(JGraph graph, GraphCell cell, Map ids, org.w3c.dom.Node node) {

    // Fetch Map attributes.
    Hashtable attrMap = getMap(node);
    // The id attribute is not appliable.
    attrMap.remove("id");

    attrMap.remove("icon");

    if (attrMap.containsKey("points")) {
      List points = GraphConstants.getPoints(attrMap);
      ArrayList result = new ArrayList();
      // The ports are removed. They are converted to String's.
      // Ports are added employing cell.
      PortView sourceView = (PortView) graph.getGraphLayoutCache().getMapping( (GraphCell) ( (
          Port) ( (DefaultEdge) cell).getSource()), false);
      PortView targetView = (PortView) graph.getGraphLayoutCache().getMapping( (GraphCell) ( (
          Port) ( (DefaultEdge) cell).getTarget()), false);
      // Add source.
      result.add(sourceView);
      // Other points are represented as Point's.
      Iterator it = points.iterator();
      while (it.hasNext()) {
        Object point = it.next();
        if (point instanceof Point) {
          result.add(point);
        }
      }
      // Add target.
      result.add(targetView);
      GraphConstants.setPoints(attrMap, result);
    }

    if (attrMap.containsKey("lineBegin")) {
      // int values are saved as Strings.
      String lineBegin = (String) attrMap.get("lineBegin");
      GraphConstants.setLineBegin(attrMap, Integer.parseInt(lineBegin));
    }

    if (attrMap.containsKey("lineEnd")) {
      // int values are saved as Strings.
      String lineEnd = (String) attrMap.get("lineEnd");
      GraphConstants.setLineEnd(attrMap, Integer.parseInt(lineEnd));
    }

    return attrMap;
  }

  // Convert a vertex represented by a GXL DOM node in a DefaultGraphCell.
  // ids contains the already processed vertex ids.
  
  private DefaultGraphCell GXLVertex(String id, String type, ModelJGraph graph,
                                     ObjectManager om, RelationshipManager rm) {

    DefaultGraphCell vertex = null;

    ingenias.editor.entities.Entity en = om.getEntity(id, type);
    // if it is registered in the OM, then it is a diagram object
    if (en != null) {



   	if (en instanceof ingenias.editor.entities.DecisionNode)
	  return  new ingenias.editor.cell.DecisionNodeCell((ingenias.editor.entities.DecisionNode)en);

   	if (en instanceof ingenias.editor.entities.FileSpecPatternMapping)
	  return  new ingenias.editor.cell.FileSpecPatternMappingCell((ingenias.editor.entities.FileSpecPatternMapping)en);

   	if (en instanceof ingenias.editor.entities.TestingPackage)
	  return  new ingenias.editor.cell.TestingPackageCell((ingenias.editor.entities.TestingPackage)en);

   	if (en instanceof ingenias.editor.entities.MentalState)
	  return  new ingenias.editor.cell.MentalStateCell((ingenias.editor.entities.MentalState)en);

   	if (en instanceof ingenias.editor.entities.FAERIECtxtAttribute)
	  return  new ingenias.editor.cell.FAERIECtxtAttributeCell((ingenias.editor.entities.FAERIECtxtAttribute)en);

   	if (en instanceof ingenias.editor.entities.InternalApplication)
	  return  new ingenias.editor.cell.InternalApplicationCell((ingenias.editor.entities.InternalApplication)en);

   	if (en instanceof ingenias.editor.entities.Goal)
	  return  new ingenias.editor.cell.GoalCell((ingenias.editor.entities.Goal)en);

   	if (en instanceof ingenias.editor.entities.AMICtxtModel)
	  return  new ingenias.editor.cell.AMICtxtModelCell((ingenias.editor.entities.AMICtxtModel)en);

   	if (en instanceof ingenias.editor.entities.AgentWS)
	  return  new ingenias.editor.cell.AgentWSCell((ingenias.editor.entities.AgentWS)en);

   	if (en instanceof ingenias.editor.entities.ContextBindingTask)
	  return  new ingenias.editor.cell.ContextBindingTaskCell((ingenias.editor.entities.ContextBindingTask)en);

   	if (en instanceof ingenias.editor.entities.DeploymentPackageWithContext)
	  return  new ingenias.editor.cell.DeploymentPackageWithContextCell((ingenias.editor.entities.DeploymentPackageWithContext)en);

   	if (en instanceof ingenias.editor.entities.Fact)
	  return  new ingenias.editor.cell.FactCell((ingenias.editor.entities.Fact)en);

   	if (en instanceof ingenias.editor.entities.ShareTouple)
	  return  new ingenias.editor.cell.ShareToupleCell((ingenias.editor.entities.ShareTouple)en);

   	if (en instanceof ingenias.editor.entities.DeploymentUnitByTypeWithInitMS)
	  return  new ingenias.editor.cell.DeploymentUnitByTypeWithInitMSCell((ingenias.editor.entities.DeploymentUnitByTypeWithInitMS)en);

   	if (en instanceof ingenias.editor.entities.JoinNode)
	  return  new ingenias.editor.cell.JoinNodeCell((ingenias.editor.entities.JoinNode)en);

   	if (en instanceof ingenias.editor.entities.BoxedTask)
	  return  new ingenias.editor.cell.BoxedTaskCell((ingenias.editor.entities.BoxedTask)en);

   	if (en instanceof ingenias.editor.entities.RuntimeEvent)
	  return  new ingenias.editor.cell.RuntimeEventCell((ingenias.editor.entities.RuntimeEvent)en);

   	if (en instanceof ingenias.editor.entities.InitialNode)
	  return  new ingenias.editor.cell.InitialNodeCell((ingenias.editor.entities.InitialNode)en);

   	if (en instanceof ingenias.editor.entities.TextUseCase)
	  return  new ingenias.editor.cell.TextUseCaseCell((ingenias.editor.entities.TextUseCase)en);

   	if (en instanceof ingenias.editor.entities.RemoteProcedureCall)
	  return  new ingenias.editor.cell.RemoteProcedureCallCell((ingenias.editor.entities.RemoteProcedureCall)en);

   	if (en instanceof ingenias.editor.entities.Resource)
	  return  new ingenias.editor.cell.ResourceCell((ingenias.editor.entities.Resource)en);

   	if (en instanceof ingenias.editor.entities.AgentModelBelieve)
	  return  new ingenias.editor.cell.AgentModelBelieveCell((ingenias.editor.entities.AgentModelBelieve)en);

   	if (en instanceof ingenias.editor.entities.ActivityFinal)
	  return  new ingenias.editor.cell.ActivityFinalCell((ingenias.editor.entities.ActivityFinal)en);

   	if (en instanceof ingenias.editor.entities.INGENIASUseCase)
	  return  new ingenias.editor.cell.INGENIASUseCaseCell((ingenias.editor.entities.INGENIASUseCase)en);

   	if (en instanceof ingenias.editor.entities.TextNote)
	  return  new ingenias.editor.cell.TextNoteCell((ingenias.editor.entities.TextNote)en);

   	if (en instanceof ingenias.editor.entities.RuntimeFact)
	  return  new ingenias.editor.cell.RuntimeFactCell((ingenias.editor.entities.RuntimeFact)en);

   	if (en instanceof ingenias.editor.entities.ForkNode)
	  return  new ingenias.editor.cell.ForkNodeCell((ingenias.editor.entities.ForkNode)en);

   	if (en instanceof ingenias.editor.entities.OrganizationNetwork)
	  return  new ingenias.editor.cell.OrganizationNetworkCell((ingenias.editor.entities.OrganizationNetwork)en);

   	if (en instanceof ingenias.editor.entities.FAERIECtxtModel)
	  return  new ingenias.editor.cell.FAERIECtxtModelCell((ingenias.editor.entities.FAERIECtxtModel)en);

   	if (en instanceof ingenias.editor.entities.MessagePassing)
	  return  new ingenias.editor.cell.MessagePassingCell((ingenias.editor.entities.MessagePassing)en);

   	if (en instanceof ingenias.editor.entities.SubProtocol)
	  return  new ingenias.editor.cell.SubProtocolCell((ingenias.editor.entities.SubProtocol)en);

   	if (en instanceof ingenias.editor.entities.RuntimeConversation)
	  return  new ingenias.editor.cell.RuntimeConversationCell((ingenias.editor.entities.RuntimeConversation)en);

   	if (en instanceof ingenias.editor.entities.MentalEntityInstanceCreation)
	  return  new ingenias.editor.cell.MentalEntityInstanceCreationCell((ingenias.editor.entities.MentalEntityInstanceCreation)en);

   	if (en instanceof ingenias.editor.entities.AUMLComponent)
	  return  new ingenias.editor.cell.AUMLComponentCell((ingenias.editor.entities.AUMLComponent)en);

   	if (en instanceof ingenias.editor.entities.FAERIECtxtRelationship)
	  return  new ingenias.editor.cell.FAERIECtxtRelationshipCell((ingenias.editor.entities.FAERIECtxtRelationship)en);

   	if (en instanceof ingenias.editor.entities.Organization)
	  return  new ingenias.editor.cell.OrganizationCell((ingenias.editor.entities.Organization)en);

   	if (en instanceof ingenias.editor.entities.ConditionalMentalState)
	  return  new ingenias.editor.cell.ConditionalMentalStateCell((ingenias.editor.entities.ConditionalMentalState)en);

   	if (en instanceof ingenias.editor.entities.ApplicationWS)
	  return  new ingenias.editor.cell.ApplicationWSCell((ingenias.editor.entities.ApplicationWS)en);

   	if (en instanceof ingenias.editor.entities.Application)
	  return  new ingenias.editor.cell.ApplicationCell((ingenias.editor.entities.Application)en);

   	if (en instanceof ingenias.editor.entities.DeploymentPackage)
	  return  new ingenias.editor.cell.DeploymentPackageCell((ingenias.editor.entities.DeploymentPackage)en);

   	if (en instanceof ingenias.editor.entities.Plan)
	  return  new ingenias.editor.cell.PlanCell((ingenias.editor.entities.Plan)en);

   	if (en instanceof ingenias.editor.entities.FAERIECtxtValue)
	  return  new ingenias.editor.cell.FAERIECtxtValueCell((ingenias.editor.entities.FAERIECtxtValue)en);

   	if (en instanceof ingenias.editor.entities.FAERIECtxtEntity)
	  return  new ingenias.editor.cell.FAERIECtxtEntityCell((ingenias.editor.entities.FAERIECtxtEntity)en);

   	if (en instanceof ingenias.editor.entities.AUMLPort)
	  return  new ingenias.editor.cell.AUMLPortCell((ingenias.editor.entities.AUMLPort)en);

   	if (en instanceof ingenias.editor.entities.ContextReleaseTask)
	  return  new ingenias.editor.cell.ContextReleaseTaskCell((ingenias.editor.entities.ContextReleaseTask)en);

   	if (en instanceof ingenias.editor.entities.INGENIASComponent)
	  return  new ingenias.editor.cell.INGENIASComponentCell((ingenias.editor.entities.INGENIASComponent)en);

   	if (en instanceof ingenias.editor.entities.OrganizationGroup)
	  return  new ingenias.editor.cell.OrganizationGroupCell((ingenias.editor.entities.OrganizationGroup)en);

   	if (en instanceof ingenias.editor.entities.IUConcurrence)
	  return  new ingenias.editor.cell.IUConcurrenceCell((ingenias.editor.entities.IUConcurrence)en);

   	if (en instanceof ingenias.editor.entities.DeploymentUnitByType)
	  return  new ingenias.editor.cell.DeploymentUnitByTypeCell((ingenias.editor.entities.DeploymentUnitByType)en);

   	if (en instanceof ingenias.editor.entities.IUIterate)
	  return  new ingenias.editor.cell.IUIterateCell((ingenias.editor.entities.IUIterate)en);

   	if (en instanceof ingenias.editor.entities.AUMLAlternativeRow)
	  return  new ingenias.editor.cell.AUMLAlternativeRowCell((ingenias.editor.entities.AUMLAlternativeRow)en);

   	if (en instanceof ingenias.editor.entities.Column)
	  return  new ingenias.editor.cell.ColumnCell((ingenias.editor.entities.Column)en);

   	if (en instanceof ingenias.editor.entities.AutonomousEntityQuery)
	  return  new ingenias.editor.cell.AutonomousEntityQueryCell((ingenias.editor.entities.AutonomousEntityQuery)en);

   	if (en instanceof ingenias.editor.entities.AUMLContainer)
	  return  new ingenias.editor.cell.AUMLContainerCell((ingenias.editor.entities.AUMLContainer)en);

   	if (en instanceof ingenias.editor.entities.ConcreteAgent)
	  return  new ingenias.editor.cell.ConcreteAgentCell((ingenias.editor.entities.ConcreteAgent)en);

   	if (en instanceof ingenias.editor.entities.UMLComment)
	  return  new ingenias.editor.cell.UMLCommentCell((ingenias.editor.entities.UMLComment)en);

   	if (en instanceof ingenias.editor.entities.AgentRequirementsQuery)
	  return  new ingenias.editor.cell.AgentRequirementsQueryCell((ingenias.editor.entities.AgentRequirementsQuery)en);

   	if (en instanceof ingenias.editor.entities.UMLSpecification)
	  return  new ingenias.editor.cell.UMLSpecificationCell((ingenias.editor.entities.UMLSpecification)en);

   	if (en instanceof ingenias.editor.entities.Believe)
	  return  new ingenias.editor.cell.BelieveCell((ingenias.editor.entities.Believe)en);

   	if (en instanceof ingenias.editor.entities.WorkflowBox)
	  return  new ingenias.editor.cell.WorkflowBoxCell((ingenias.editor.entities.WorkflowBox)en);

   	if (en instanceof ingenias.editor.entities.FAERIECtxtElement)
	  return  new ingenias.editor.cell.FAERIECtxtElementCell((ingenias.editor.entities.FAERIECtxtElement)en);

   	if (en instanceof ingenias.editor.entities.DeploymentUnitByTypeEnumInitMS)
	  return  new ingenias.editor.cell.DeploymentUnitByTypeEnumInitMSCell((ingenias.editor.entities.DeploymentUnitByTypeEnumInitMS)en);

   	if (en instanceof ingenias.editor.entities.GeneralEvent)
	  return  new ingenias.editor.cell.GeneralEventCell((ingenias.editor.entities.GeneralEvent)en);

   	if (en instanceof ingenias.editor.entities.Compromise)
	  return  new ingenias.editor.cell.CompromiseCell((ingenias.editor.entities.Compromise)en);

   	if (en instanceof ingenias.editor.entities.Role)
	  return  new ingenias.editor.cell.RoleCell((ingenias.editor.entities.Role)en);

   	if (en instanceof ingenias.editor.entities.EnvironmentApplication)
	  return  new ingenias.editor.cell.EnvironmentApplicationCell((ingenias.editor.entities.EnvironmentApplication)en);

   	if (en instanceof ingenias.editor.entities.ApplicationEventSlots)
	  return  new ingenias.editor.cell.ApplicationEventSlotsCell((ingenias.editor.entities.ApplicationEventSlots)en);

   	if (en instanceof ingenias.editor.entities.Protocol)
	  return  new ingenias.editor.cell.ProtocolCell((ingenias.editor.entities.Protocol)en);

   	if (en instanceof ingenias.editor.entities.MentalInstanceSpecification)
	  return  new ingenias.editor.cell.MentalInstanceSpecificationCell((ingenias.editor.entities.MentalInstanceSpecification)en);

   	if (en instanceof ingenias.editor.entities.ActionUML)
	  return  new ingenias.editor.cell.ActionUMLCell((ingenias.editor.entities.ActionUML)en);

   	if (en instanceof ingenias.editor.entities.InteractionUnit)
	  return  new ingenias.editor.cell.InteractionUnitCell((ingenias.editor.entities.InteractionUnit)en);

   	if (en instanceof ingenias.editor.entities.GRASIASpecification)
	  return  new ingenias.editor.cell.GRASIASpecificationCell((ingenias.editor.entities.GRASIASpecification)en);

   	if (en instanceof ingenias.editor.entities.MentalStateProcessor)
	  return  new ingenias.editor.cell.MentalStateProcessorCell((ingenias.editor.entities.MentalStateProcessor)en);

   	if (en instanceof ingenias.editor.entities.MergeNode)
	  return  new ingenias.editor.cell.MergeNodeCell((ingenias.editor.entities.MergeNode)en);

   	if (en instanceof ingenias.editor.entities.EndNode)
	  return  new ingenias.editor.cell.EndNodeCell((ingenias.editor.entities.EndNode)en);

   	if (en instanceof ingenias.editor.entities.FrameFact)
	  return  new ingenias.editor.cell.FrameFactCell((ingenias.editor.entities.FrameFact)en);

   	if (en instanceof ingenias.editor.entities.Test)
	  return  new ingenias.editor.cell.TestCell((ingenias.editor.entities.Test)en);

   	if (en instanceof ingenias.editor.entities.Lifeline)
	  return  new ingenias.editor.cell.LifelineCell((ingenias.editor.entities.Lifeline)en);

   	if (en instanceof ingenias.editor.entities.Interaction)
	  return  new ingenias.editor.cell.InteractionCell((ingenias.editor.entities.Interaction)en);

   	if (en instanceof ingenias.editor.entities.SimExtractedInformation)
	  return  new ingenias.editor.cell.SimExtractedInformationCell((ingenias.editor.entities.SimExtractedInformation)en);

   	if (en instanceof ingenias.editor.entities.Agent)
	  return  new ingenias.editor.cell.AgentCell((ingenias.editor.entities.Agent)en);

   	if (en instanceof ingenias.editor.entities.MentalStateManager)
	  return  new ingenias.editor.cell.MentalStateManagerCell((ingenias.editor.entities.MentalStateManager)en);

   	if (en instanceof ingenias.editor.entities.INGENIASCodeComponent)
	  return  new ingenias.editor.cell.INGENIASCodeComponentCell((ingenias.editor.entities.INGENIASCodeComponent)en);

   	if (en instanceof ingenias.editor.entities.SimulationPackage)
	  return  new ingenias.editor.cell.SimulationPackageCell((ingenias.editor.entities.SimulationPackage)en);

   	if (en instanceof ingenias.editor.entities.AMIContextBindingData)
	  return  new ingenias.editor.cell.AMIContextBindingDataCell((ingenias.editor.entities.AMIContextBindingData)en);

   	if (en instanceof ingenias.editor.entities.CommunicationEvent)
	  return  new ingenias.editor.cell.CommunicationEventCell((ingenias.editor.entities.CommunicationEvent)en);

   	if (en instanceof ingenias.editor.entities.TaskWS)
	  return  new ingenias.editor.cell.TaskWSCell((ingenias.editor.entities.TaskWS)en);

   	if (en instanceof ingenias.editor.entities.GoalStateWS)
	  return  new ingenias.editor.cell.GoalStateWSCell((ingenias.editor.entities.GoalStateWS)en);

   	if (en instanceof ingenias.editor.entities.StateGoal)
	  return  new ingenias.editor.cell.StateGoalCell((ingenias.editor.entities.StateGoal)en);

   	if (en instanceof ingenias.editor.entities.Conversation)
	  return  new ingenias.editor.cell.ConversationCell((ingenias.editor.entities.Conversation)en);

   	if (en instanceof ingenias.editor.entities.ApplicationEvent)
	  return  new ingenias.editor.cell.ApplicationEventCell((ingenias.editor.entities.ApplicationEvent)en);

   	if (en instanceof ingenias.editor.entities.Task)
	  return  new ingenias.editor.cell.TaskCell((ingenias.editor.entities.Task)en);

   	if (en instanceof ingenias.editor.entities.DeploymentUnitByTypeMSEntity)
	  return  new ingenias.editor.cell.DeploymentUnitByTypeMSEntityCell((ingenias.editor.entities.DeploymentUnitByTypeMSEntity)en);

   	if (en instanceof ingenias.editor.entities.MentalEntityInstanceAccess)
	  return  new ingenias.editor.cell.MentalEntityInstanceAccessCell((ingenias.editor.entities.MentalEntityInstanceAccess)en);

   	if (en instanceof ingenias.editor.entities.RoleWS)
	  return  new ingenias.editor.cell.RoleWSCell((ingenias.editor.entities.RoleWS)en);

   	if (en instanceof ingenias.editor.entities.AMIContext)
	  return  new ingenias.editor.cell.AMIContextCell((ingenias.editor.entities.AMIContext)en);

   	if (en instanceof ingenias.editor.entities.SimulationEvent)
	  return  new ingenias.editor.cell.SimulationEventCell((ingenias.editor.entities.SimulationEvent)en);

   	if (en instanceof ingenias.editor.entities.FAERIEContext)
	  return  new ingenias.editor.cell.FAERIEContextCell((ingenias.editor.entities.FAERIEContext)en);

   	if (en instanceof ingenias.editor.entities.AUMLAlternativeBox)
	  return  new ingenias.editor.cell.AUMLAlternativeBoxCell((ingenias.editor.entities.AUMLAlternativeBox)en);

   	if (en instanceof ingenias.editor.entities.Workflow)
	  return  new ingenias.editor.cell.WorkflowCell((ingenias.editor.entities.Workflow)en);

    } else {
    // If not, it is a relationship
      en = rm.getRelationship(id);
    if (en==null) return null;

    if (en instanceof ingenias.editor.entities.AHasMSManager)
     return  new AHasMSManagerEdge((ingenias.editor.entities.AHasMSManager)en);

    if (en instanceof ingenias.editor.entities.AGOInconditionalSubordinationRelationshipOrg)
     return  new AGOInconditionalSubordinationRelationshipOrgEdge((ingenias.editor.entities.AGOInconditionalSubordinationRelationshipOrg)en);

    if (en instanceof ingenias.editor.entities.GTDecomposes)
     return  new GTDecomposesEdge((ingenias.editor.entities.GTDecomposes)en);

    if (en instanceof ingenias.editor.entities.WFResponsible)
     return  new WFResponsibleEdge((ingenias.editor.entities.WFResponsible)en);

    if (en instanceof ingenias.editor.entities.CDUsesCode)
     return  new CDUsesCodeEdge((ingenias.editor.entities.CDUsesCode)en);

    if (en instanceof ingenias.editor.entities.AGOCondSubordinationRelationshipGroup)
     return  new AGOCondSubordinationRelationshipGroupEdge((ingenias.editor.entities.AGOCondSubordinationRelationshipGroup)en);

    if (en instanceof ingenias.editor.entities.OHasMember)
     return  new OHasMemberEdge((ingenias.editor.entities.OHasMember)en);

    if (en instanceof ingenias.editor.entities.EPerceivesPolling)
     return  new EPerceivesPollingEdge((ingenias.editor.entities.EPerceivesPolling)en);

    if (en instanceof ingenias.editor.entities.WFParticipates)
     return  new WFParticipatesEdge((ingenias.editor.entities.WFParticipates)en);

    if (en instanceof ingenias.editor.entities.WFDecomposesWF)
     return  new WFDecomposesWFEdge((ingenias.editor.entities.WFDecomposesWF)en);

    if (en instanceof ingenias.editor.entities.UIInitiates)
     return  new UIInitiatesEdge((ingenias.editor.entities.UIInitiates)en);

    if (en instanceof ingenias.editor.entities.ContributeNegatively)
     return  new ContributeNegativelyEdge((ingenias.editor.entities.ContributeNegatively)en);

    if (en instanceof ingenias.editor.entities.DefinesDeployment)
     return  new DefinesDeploymentEdge((ingenias.editor.entities.DefinesDeployment)en);

    if (en instanceof ingenias.editor.entities.HasCtxModel)
     return  new HasCtxModelEdge((ingenias.editor.entities.HasCtxModel)en);

    if (en instanceof ingenias.editor.entities.WFUsesMethod)
     return  new WFUsesMethodEdge((ingenias.editor.entities.WFUsesMethod)en);

    if (en instanceof ingenias.editor.entities.UISelection)
     return  new UISelectionEdge((ingenias.editor.entities.UISelection)en);

    if (en instanceof ingenias.editor.entities.EPerceivesNotification)
     return  new EPerceivesNotificationEdge((ingenias.editor.entities.EPerceivesNotification)en);

    if (en instanceof ingenias.editor.entities.OHasWF)
     return  new OHasWFEdge((ingenias.editor.entities.OHasWF)en);

    if (en instanceof ingenias.editor.entities.ParticipatesInUseCase)
     return  new ParticipatesInUseCaseEdge((ingenias.editor.entities.ParticipatesInUseCase)en);

    if (en instanceof ingenias.editor.entities.GTCreates)
     return  new GTCreatesEdge((ingenias.editor.entities.GTCreates)en);

    if (en instanceof ingenias.editor.entities.WFCancels)
     return  new WFCancelsEdge((ingenias.editor.entities.WFCancels)en);

    if (en instanceof ingenias.editor.entities.AGOInconditionalSubordinationRelationshipGroup)
     return  new AGOInconditionalSubordinationRelationshipGroupEdge((ingenias.editor.entities.AGOInconditionalSubordinationRelationshipGroup)en);

    if (en instanceof ingenias.editor.entities.AUMLSendSimple)
     return  new AUMLSendSimpleEdge((ingenias.editor.entities.AUMLSendSimple)en);

    if (en instanceof ingenias.editor.entities.GTModifies)
     return  new GTModifiesEdge((ingenias.editor.entities.GTModifies)en);

    if (en instanceof ingenias.editor.entities.WFProduces)
     return  new WFProducesEdge((ingenias.editor.entities.WFProduces)en);

    if (en instanceof ingenias.editor.entities.GTPursues)
     return  new GTPursuesEdge((ingenias.editor.entities.GTPursues)en);

    if (en instanceof ingenias.editor.entities.UseCasePursues)
     return  new UseCasePursuesEdge((ingenias.editor.entities.UseCasePursues)en);

    if (en instanceof ingenias.editor.entities.EPerceives)
     return  new EPerceivesEdge((ingenias.editor.entities.EPerceives)en);

    if (en instanceof ingenias.editor.entities.ODecomposesWF)
     return  new ODecomposesWFEdge((ingenias.editor.entities.ODecomposesWF)en);

    if (en instanceof ingenias.editor.entities.WFFollows)
     return  new WFFollowsEdge((ingenias.editor.entities.WFFollows)en);

    if (en instanceof ingenias.editor.entities.WFDecomposes)
     return  new WFDecomposesEdge((ingenias.editor.entities.WFDecomposes)en);

    if (en instanceof ingenias.editor.entities.AGOClientServerRelationshipMember)
     return  new AGOClientServerRelationshipMemberEdge((ingenias.editor.entities.AGOClientServerRelationshipMember)en);

    if (en instanceof ingenias.editor.entities.WFSpecifiesExecution)
     return  new WFSpecifiesExecutionEdge((ingenias.editor.entities.WFSpecifiesExecution)en);

    if (en instanceof ingenias.editor.entities.AInstanceOf)
     return  new AInstanceOfEdge((ingenias.editor.entities.AInstanceOf)en);

    if (en instanceof ingenias.editor.entities.AGORelationshipGroup)
     return  new AGORelationshipGroupEdge((ingenias.editor.entities.AGORelationshipGroup)en);

    if (en instanceof ingenias.editor.entities.AGORelationshipMember)
     return  new AGORelationshipMemberEdge((ingenias.editor.entities.AGORelationshipMember)en);

    if (en instanceof ingenias.editor.entities.AInherits)
     return  new AInheritsEdge((ingenias.editor.entities.AInherits)en);

    if (en instanceof ingenias.editor.entities.GTFails)
     return  new GTFailsEdge((ingenias.editor.entities.GTFails)en);

    if (en instanceof ingenias.editor.entities.AGOClientServerRelationshipOrg)
     return  new AGOClientServerRelationshipOrgEdge((ingenias.editor.entities.AGOClientServerRelationshipOrg)en);

    if (en instanceof ingenias.editor.entities.AGOSubordinationRelationshipOrg)
     return  new AGOSubordinationRelationshipOrgEdge((ingenias.editor.entities.AGOSubordinationRelationshipOrg)en);

    if (en instanceof ingenias.editor.entities.GTDepends)
     return  new GTDependsEdge((ingenias.editor.entities.GTDepends)en);

    if (en instanceof ingenias.editor.entities.Includes)
     return  new IncludesEdge((ingenias.editor.entities.Includes)en);

    if (en instanceof ingenias.editor.entities.IHasSpec)
     return  new IHasSpecEdge((ingenias.editor.entities.IHasSpec)en);

    if (en instanceof ingenias.editor.entities.WFPursue)
     return  new WFPursueEdge((ingenias.editor.entities.WFPursue)en);

    if (en instanceof ingenias.editor.entities.EResourceBelongsTo)
     return  new EResourceBelongsToEdge((ingenias.editor.entities.EResourceBelongsTo)en);

    if (en instanceof ingenias.editor.entities.WFConsumes)
     return  new WFConsumesEdge((ingenias.editor.entities.WFConsumes)en);

    if (en instanceof ingenias.editor.entities.FAERIETrgtEntity)
     return  new FAERIETrgtEntityEdge((ingenias.editor.entities.FAERIETrgtEntity)en);

    if (en instanceof ingenias.editor.entities.Generalizes)
     return  new GeneralizesEdge((ingenias.editor.entities.Generalizes)en);

    if (en instanceof ingenias.editor.entities.FAERIESrcEntity)
     return  new FAERIESrcEntityEdge((ingenias.editor.entities.FAERIESrcEntity)en);

    if (en instanceof ingenias.editor.entities.PConnects)
     return  new PConnectsEdge((ingenias.editor.entities.PConnects)en);

    if (en instanceof ingenias.editor.entities.AHasMSProcessor)
     return  new AHasMSProcessorEdge((ingenias.editor.entities.AHasMSProcessor)en);

    if (en instanceof ingenias.editor.entities.AGOSubordinationRelationshipGroup)
     return  new AGOSubordinationRelationshipGroupEdge((ingenias.editor.entities.AGOSubordinationRelationshipGroup)en);

    if (en instanceof ingenias.editor.entities.CtxtUpdates)
     return  new CtxtUpdatesEdge((ingenias.editor.entities.CtxtUpdates)en);

    if (en instanceof ingenias.editor.entities.GroupBelongsToOrganization)
     return  new GroupBelongsToOrganizationEdge((ingenias.editor.entities.GroupBelongsToOrganization)en);

    if (en instanceof ingenias.editor.entities.AContainsME)
     return  new AContainsMEEdge((ingenias.editor.entities.AContainsME)en);

    if (en instanceof ingenias.editor.entities.GTOrDepends)
     return  new GTOrDependsEdge((ingenias.editor.entities.GTOrDepends)en);

    if (en instanceof ingenias.editor.entities.AUMLUseProtocol)
     return  new AUMLUseProtocolEdge((ingenias.editor.entities.AUMLUseProtocol)en);

    if (en instanceof ingenias.editor.entities.GTAndDepends)
     return  new GTAndDependsEdge((ingenias.editor.entities.GTAndDepends)en);

    if (en instanceof ingenias.editor.entities.ODecomposesGroup)
     return  new ODecomposesGroupEdge((ingenias.editor.entities.ODecomposesGroup)en);

    if (en instanceof ingenias.editor.entities.IInitiates)
     return  new IInitiatesEdge((ingenias.editor.entities.IInitiates)en);

    if (en instanceof ingenias.editor.entities.Contribute)
     return  new ContributeEdge((ingenias.editor.entities.Contribute)en);

    if (en instanceof ingenias.editor.entities.AGORelationshipOrg)
     return  new AGORelationshipOrgEdge((ingenias.editor.entities.AGORelationshipOrg)en);

    if (en instanceof ingenias.editor.entities.Consumes)
     return  new ConsumesEdge((ingenias.editor.entities.Consumes)en);

    if (en instanceof ingenias.editor.entities.AHasMS)
     return  new AHasMSEdge((ingenias.editor.entities.AHasMS)en);

    if (en instanceof ingenias.editor.entities.UMLDescribesUseCase)
     return  new UMLDescribesUseCaseEdge((ingenias.editor.entities.UMLDescribesUseCase)en);

    if (en instanceof ingenias.editor.entities.IColaborates)
     return  new IColaboratesEdge((ingenias.editor.entities.IColaborates)en);

    if (en instanceof ingenias.editor.entities.WFFollowsGuarded)
     return  new WFFollowsGuardedEdge((ingenias.editor.entities.WFFollowsGuarded)en);

    if (en instanceof ingenias.editor.entities.GTAffects)
     return  new GTAffectsEdge((ingenias.editor.entities.GTAffects)en);

    if (en instanceof ingenias.editor.entities.IAccesses)
     return  new IAccessesEdge((ingenias.editor.entities.IAccesses)en);

    if (en instanceof ingenias.editor.entities.UIPrecedes)
     return  new UIPrecedesEdge((ingenias.editor.entities.UIPrecedes)en);

    if (en instanceof ingenias.editor.entities.ARoleInheritance)
     return  new ARoleInheritanceEdge((ingenias.editor.entities.ARoleInheritance)en);

    if (en instanceof ingenias.editor.entities.AGOInconditionalSubordinationRelationshipMember)
     return  new AGOInconditionalSubordinationRelationshipMemberEdge((ingenias.editor.entities.AGOInconditionalSubordinationRelationshipMember)en);

    if (en instanceof ingenias.editor.entities.WFStarts)
     return  new WFStartsEdge((ingenias.editor.entities.WFStarts)en);

    if (en instanceof ingenias.editor.entities.UMLSendsMessage)
     return  new UMLSendsMessageEdge((ingenias.editor.entities.UMLSendsMessage)en);

    if (en instanceof ingenias.editor.entities.UMLAssociation)
     return  new UMLAssociationEdge((ingenias.editor.entities.UMLAssociation)en);

    if (en instanceof ingenias.editor.entities.OHasGroup)
     return  new OHasGroupEdge((ingenias.editor.entities.OHasGroup)en);

    if (en instanceof ingenias.editor.entities.WFResponsable)
     return  new WFResponsableEdge((ingenias.editor.entities.WFResponsable)en);

    if (en instanceof ingenias.editor.entities.WFContainsTask)
     return  new WFContainsTaskEdge((ingenias.editor.entities.WFContainsTask)en);

    if (en instanceof ingenias.editor.entities.FAERIEGeneratedBy)
     return  new FAERIEGeneratedByEdge((ingenias.editor.entities.FAERIEGeneratedBy)en);

    if (en instanceof ingenias.editor.entities.FAERIEAppliedTo)
     return  new FAERIEAppliedToEdge((ingenias.editor.entities.FAERIEAppliedTo)en);

    if (en instanceof ingenias.editor.entities.UMLRealizes)
     return  new UMLRealizesEdge((ingenias.editor.entities.UMLRealizes)en);

    if (en instanceof ingenias.editor.entities.WFConnects)
     return  new WFConnectsEdge((ingenias.editor.entities.WFConnects)en);

    if (en instanceof ingenias.editor.entities.Extends)
     return  new ExtendsEdge((ingenias.editor.entities.Extends)en);

    if (en instanceof ingenias.editor.entities.GTInherits)
     return  new GTInheritsEdge((ingenias.editor.entities.GTInherits)en);

    if (en instanceof ingenias.editor.entities.GTDecomposesAND)
     return  new GTDecomposesANDEdge((ingenias.editor.entities.GTDecomposesAND)en);

    if (en instanceof ingenias.editor.entities.SimulationPursues)
     return  new SimulationPursuesEdge((ingenias.editor.entities.SimulationPursues)en);

    if (en instanceof ingenias.editor.entities.WFEnds)
     return  new WFEndsEdge((ingenias.editor.entities.WFEnds)en);

    if (en instanceof ingenias.editor.entities.AUMLSelection)
     return  new AUMLSelectionEdge((ingenias.editor.entities.AUMLSelection)en);

    if (en instanceof ingenias.editor.entities.CtxtNotifies)
     return  new CtxtNotifiesEdge((ingenias.editor.entities.CtxtNotifies)en);

    if (en instanceof ingenias.editor.entities.UMLAnnotatedElement)
     return  new UMLAnnotatedElementEdge((ingenias.editor.entities.UMLAnnotatedElement)en);

    if (en instanceof ingenias.editor.entities.UIColaborates)
     return  new UIColaboratesEdge((ingenias.editor.entities.UIColaborates)en);

    if (en instanceof ingenias.editor.entities.WFPlays)
     return  new WFPlaysEdge((ingenias.editor.entities.WFPlays)en);

    if (en instanceof ingenias.editor.entities.AGOClientServerRelationshipGroup)
     return  new AGOClientServerRelationshipGroupEdge((ingenias.editor.entities.AGOClientServerRelationshipGroup)en);

    if (en instanceof ingenias.editor.entities.GTDestroys)
     return  new GTDestroysEdge((ingenias.editor.entities.GTDestroys)en);

    if (en instanceof ingenias.editor.entities.IPursues)
     return  new IPursuesEdge((ingenias.editor.entities.IPursues)en);

    if (en instanceof ingenias.editor.entities.ApplicationBelongsTo)
     return  new ApplicationBelongsToEdge((ingenias.editor.entities.ApplicationBelongsTo)en);

    if (en instanceof ingenias.editor.entities.AGOSubordinationRelationshipMember)
     return  new AGOSubordinationRelationshipMemberEdge((ingenias.editor.entities.AGOSubordinationRelationshipMember)en);

    if (en instanceof ingenias.editor.entities.WSConnects)
     return  new WSConnectsEdge((ingenias.editor.entities.WSConnects)en);

    if (en instanceof ingenias.editor.entities.GTDecomposesOR)
     return  new GTDecomposesOREdge((ingenias.editor.entities.GTDecomposesOR)en);

    if (en instanceof ingenias.editor.entities.AGOCondSubordinationRelationshipOrg)
     return  new AGOCondSubordinationRelationshipOrgEdge((ingenias.editor.entities.AGOCondSubordinationRelationshipOrg)en);

    if (en instanceof ingenias.editor.entities.FAERIEHasValue)
     return  new FAERIEHasValueEdge((ingenias.editor.entities.FAERIEHasValue)en);

    if (en instanceof ingenias.editor.entities.GTSatisfies)
     return  new GTSatisfiesEdge((ingenias.editor.entities.GTSatisfies)en);

    if (en instanceof ingenias.editor.entities.AGOCondSubordinationRelationshipMember)
     return  new AGOCondSubordinationRelationshipMemberEdge((ingenias.editor.entities.AGOCondSubordinationRelationshipMember)en);

    if (en instanceof ingenias.editor.entities.WFUses)
     return  new WFUsesEdge((ingenias.editor.entities.WFUses)en);

    if (en instanceof ingenias.editor.entities.WFDecides)
     return  new WFDecidesEdge((ingenias.editor.entities.WFDecides)en);

    if (en instanceof ingenias.editor.entities.ContributePositively)
     return  new ContributePositivelyEdge((ingenias.editor.entities.ContributePositively)en);


 }

    return null;
  }

  // Convert an edge represented by a GXL DOM node in a DefaultEdge.
  // ids has a mapping from id to vertex.
  
  private DefaultEdge GXLEdge(Map ids, org.w3c.dom.Node node) {

    DefaultEdge edge = new DefaultEdge();

    // Fetch Map attributes.
    Map attrMap = getMap(node);

    // Create Edge with label
    String label = (String) attrMap.get("id");
    // Fetch type
    String type = (String) attrMap.get("type");
    // id and type are not valid JGraph attributes.
    attrMap.remove("id");
    attrMap.remove("type");
    return edge;
  }

  private DefaultGraphCell[]
      getEntitiesAlreadyInsertedInRelationshipAndUpdateDGCIds(Object[] selected,
      ingenias.editor.entities.NAryEdgeEntity nEdgeObject) {
    String[] ids = nEdgeObject.getIds();
    Vector newselectedv=new Vector();


    int i = 0;
//      for (int i = 0; i < ids.length; i++) {
    for (int j = 0; j < selected.length; j++) {
      Object userObject = ( (DefaultGraphCell) selected[j]).getUserObject();
/*      Log.getInstance().logSYS("Processing Relationship" +
                            nEdgeObject.getId() + " of type " +
                            nEdgeObject.getType() +
                            " considering " + userObject);*/

      try {
        if (userObject != null &&
            userObject instanceof ingenias.editor.entities.Entity) {
          nEdgeObject.searchEntityID( ( (ingenias.editor.entities.Entity)
                                       userObject).getId());
          nEdgeObject.updateCell( (DefaultGraphCell) selected[j]);
          newselectedv.add(selected[j]);
          i++;
        }
      }
      catch (NotFound nf) {
/*        Log.getInstance().logSYS("Processing Relationship" +
                              nEdgeObject.getId() + " of type " +
                              nEdgeObject.getType() +
                              " not found " + userObject);*/
      }
    }
    ids = nEdgeObject.getIds();

    // Number of ids in the relationship can be less than initial number
    if (ids.length != i) {
      throw new RuntimeException(
          "INTERNAL ERROR!!! Length of ids connected in " +
          nEdgeObject.getId() + " of type " + nEdgeObject.getType() +
          " a relationship does not match selected default graph cell number. I had " +
          ids.length + " elements to find and I found " + i);
    }
    DefaultGraphCell[] newSelected = new DefaultGraphCell[ids.length];
    for (int k=0;k<newSelected.length;k++){
      newSelected[k]=(DefaultGraphCell)newselectedv.elementAt(k);
    }
/*
    Copyright (C) 2002 Jorge Gomez Sanz, Ruben Fuentes, Juan Pavon

    Modifications over original code from jgraph.sourceforge.net

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
    return newSelected;

  }

  private void connect(ModelJGraph graph, DefaultGraphCell[] selected,
                       NAryEdge nEdge,
                       Map eas) throws NotFound {
    ingenias.editor.entities.NAryEdgeEntity ne = (NAryEdgeEntity)
        nEdge.getUserObject();

    DefaultGraphCell[] newSelected = null;
    // N-ary relationship.
    if (nEdge != null) {
      // Role assignations to classes are obtained from NAryEdgeEntity in NAryEdge.
      // NAryEdgeEntity has a list of object ids that have to be in selected.
      // assignations is a Vector of Vectors of Strings where Strings represents roles.
      NAryEdgeEntity nEdgeObject = (NAryEdgeEntity) nEdge.getUserObject();
      // ids of objects connected with nEdge.
//      String[] ids = nEdgeObject.getIds(); // Not valid yet
  /*    {
        String idsres = "";
        for (int k = 0; k < ids.length; k++) {
          idsres = idsres + ids[k] + ",";
        }
        Log.getInstance().logSYS("Processing Relationship" +
                              ne.getId() + " of type " + ne.getType() +
                              " with elems " +
                              idsres +
                              " in graph " + graph.getID());
      }*/

      // Selected objects are reduced to those connected with nEdge
      newSelected = this.
          getEntitiesAlreadyInsertedInRelationshipAndUpdateDGCIds(selected,
          nEdgeObject);

/*      ids = nEdgeObject.getIds(); // Now valid ones, after the update
      {
        String idsres = "";
        for (int k = 0; k < ids.length; k++) {
          idsres = idsres + ids[k] + ",";
        }
        Log.getInstance().logSYS("Processing Relationship" +
                              ne.getId() + " of type " + ne.getType() +
                              " new ids " +
                              idsres +
                              " in graph " + graph.getID());
      }*/

      // Role assignation to objects is obtained.
      String[] selectedAssignation = new String[newSelected.length];
      for (int i = 0; i < newSelected.length; i++) {
        selectedAssignation[i] = nEdgeObject.getRole(""+newSelected[i].hashCode());
        // Auxiliary edges that will be inserted in the Model.
      }
      try {
        DefaultEdge[] auxiliaryEdges = nEdge.connectionsEdges(newSelected,
            selectedAssignation);
        for (int i = 0; i < newSelected.length; i++) {
          RoleEntity re = nEdgeObject.getRoleEntity(""+newSelected[i].hashCode());
          auxiliaryEdges[i].setUserObject(re);
        }

        // Connections that will be inserted into the Model.
        ConnectionSet cs = nEdge.connections(selectedAssignation,
                                             auxiliaryEdges,
                                             getPorts(graph, newSelected));
        // Construct a Map from cells to Maps (for insert).
        Hashtable attributes = new Hashtable();
        // Associate the NAryEdge Vertex with its Attributes.
        attributes.put(nEdge, eas);
//////////-----------------------------------//////////
        Hashtable edgesAttributes = new Hashtable();
        for (int i = 0; i < selectedAssignation.length; i++) {
          // Create a Map that holds the attributes for the edge
          Map attr = ( (RoleEntity) auxiliaryEdges[i].getUserObject()).
              getAttributes();
          // Source
          /*
           *  if (selectedAssignation[i].indexOf("source") >= 0)
           *  / Add a Line Begin Attribute
           *  GraphConstants.setLineBegin(attr, GraphConstants.SIMPLE);
           */
          // Target
          if (selectedAssignation[i].toUpperCase().indexOf("TARGET")>= 0 ||
              selectedAssignation[i].endsWith("T")) {
            // Add a Line End Attribute
            GraphConstants.setLineEnd(attr, GraphConstants.ARROW_CLASSIC);
            // Associate the Edge with its Attributes
          }
      GraphConstants.setDisconnectable(attr,false);

      GraphConstants.setBendable(attr,false);
          edgesAttributes.put(auxiliaryEdges[i], attr);
        }
//////////-----------------------------------//////////
        // Insert the Edge and its Attributes. The order matters.
        if (auxiliaryEdges.length >= 2) {
//        nEdgeObject.updateCells(newSelected);
          graph.getModel().insert(new Object[] {nEdge},attributes
                                  , null, null, null);
          String inserted = "";
          for (int j = 0; j < auxiliaryEdges.length; j++) {
            inserted = inserted + auxiliaryEdges[j];
          }
//          Log.getInstance().logSYS("Inserting " + inserted);
          graph.getModel().insert( (Object[]) auxiliaryEdges,edgesAttributes, cs, null,
                                  null);

        }
        else {
          // There was an error. A relationship was found with less than 2 extremes
          ne = (NAryEdgeEntity)
              nEdge.getUserObject();
          String[] tids = ne.getIds();
          String result = "";
          for (int k = 0; k < tids.length; k++) {
            result = result + tids[k] + ",";
          }
          Log.getInstance().logSYS("WARNING Relationship removed:Relationship " +
                                ne.getId() + " of type " + ne.getType() +
                                " has not been saved properly among objects " +
                                result + " in graph " + graph.getID());
        }
      }
      catch (WrongParameters wp) {
        Log.getInstance().logSYS(
            "WARNING!!! Cannot produce edges for relationship " +
            ne.getId() + " of type " + ne.getType());
        wp.printStackTrace();
      }
    }
  }

  private Object[] getModelPath(org.w3c.dom.Node n, GraphManager gm) {
    Object[] opath = null;
    Vector path = new Vector();
    NodeList packages = n.getChildNodes();
    for (int k = 0; k < packages.getLength(); k++) {
      org.w3c.dom.Node pack = packages.item(k);
      if (pack.getNodeName().equalsIgnoreCase("path")) {
        NodeList packs = pack.getChildNodes();
        for (int j = 0; j < packs.getLength(); j++) {
          org.w3c.dom.Node npack = packs.item(j);
          if (npack.getNodeName().equalsIgnoreCase("package")) {
            String id = npack.getAttributes().getNamedItem("id").getNodeValue().
                toString();
            path.add(id);
          }
        }
        for (int j = 1; j < path.size(); j++) {
          opath = new Object[j];
          for (int l = 0; l < j; l++) {
            opath[l] = path.elementAt(l);
          }
          gm.addPackage(opath, path.elementAt(j).toString());
        }
      }
    }
    return path.toArray();
  }

  
  private DefaultGraphCell getGraphCell(ModelJGraph mj, String id) {
    for (int k = 0; k < mj.getModel().getRootCount(); k++) {
//      System.err.println("k:"+k+mj.getModel().getRootCount());
      DefaultGraphCell dgc = (DefaultGraphCell) mj.getModel().getRootAt(k);
      ingenias.editor.entities.Entity ent = (ingenias.editor.entities.Entity)
          dgc.getUserObject();
      if (ent.getId().equalsIgnoreCase(id)) {
        return dgc;
      }
//      System.err.println("Identiddad:"+ent.getId());
    }
//    System.err.println("no pude encontrar " + id);
    return null;
  }

  
  private Vector getConnectedEntities(org.w3c.dom.Node n) {
    Vector result = new Vector();
    NodeList nl = n.getChildNodes();
    for (int k = 0; k < nl.getLength(); k++) {
      org.w3c.dom.Node current = nl.item(k);
      if (current.getNodeName().equalsIgnoreCase("connected")) {
        String id = current.getAttributes().getNamedItem("id").getNodeValue();
        result.add(id);
      }
    }
    return result;
  }

  public void restoreModels(IDEState ids,GUIResources resources,
                            Document doc) throws CannotLoadDiagram {
    // For compatibility and in case a future different RM is needed
    RelationshipManager rm = new RelationshipManager();
    NodeList models = doc.getElementsByTagName("models").item(0).getChildNodes();
    boolean allrecovered = true;
    String failureMessage = "";

    for (int k = 0; k < models.getLength(); k++) {
      org.w3c.dom.Node model = models.item(k);
      String id = "";
      String type = "";
      try {
        if (model.getNodeName().equalsIgnoreCase("model")) {
          id = model.getAttributes().getNamedItem("id").getNodeValue().
              toString();
          Log.getInstance().logSYS("Loading model " + id);
          type = model.getAttributes().getNamedItem("type").getNodeValue().
              toString();
          this.restoreModel(ids, rm, model);
        }
      }
      catch (Exception e) {
        allrecovered = false;
        failureMessage = failureMessage + "\n Error loading model " + id +
            " of type " + type + ". Original error message was \n " +
            e.getMessage();
        e.printStackTrace();
      }
    }
    if (!allrecovered) {
      throw new CannotLoadDiagram(failureMessage);
    }
  }

  private void restoreModel(IDEState ids, RelationshipManager rm,
                            org.w3c.dom.Node model) throws ClassNotFoundException,
      IllegalAccessException, InstantiationException, NoSuchMethodException,
      InvocationTargetException {
    String id = model.getAttributes().getNamedItem("id").getNodeValue().
        toString();
    String type = model.getAttributes().getNamedItem("type").getNodeValue().
        toString();

    Object[] path = this.getModelPath(model, ids.gm);

    org.w3c.dom.Node graph = null;
    org.w3c.dom.Node layout = null;
    NodeList children = model.getChildNodes();
    ModelDataEntity mde = null;

    for (int j = 0; j < children.getLength(); j++) {

      org.w3c.dom.Node current = children.item(j);
      if (current.getNodeName().equalsIgnoreCase("object")) {
        mde = (ModelDataEntity) PersistenceManager.getOL().restoreObject(ids.om,
            ids.gm, current);
      }
      if (current.getNodeName().equalsIgnoreCase("gxl")) {
        NodeList gxls = current.getChildNodes();
        for (int l = 0; l < gxls.getLength(); l++) {
          org.w3c.dom.Node currentgxl = gxls.item(l);
          if (currentgxl.getNodeName().equalsIgnoreCase("graph")) {
            graph = currentgxl;
          }
          if (currentgxl.getNodeName().equalsIgnoreCase("layout")) {
            layout = currentgxl;
          }
        }
      }
    }
    ModelJGraph mjg = null;

    if (mde != null) {
        int indmarquee=type.indexOf("ModelJGraph");
        String marqueetype=type.substring(0,indmarquee)+"MarqueeHandler";
        Constructor consmarquee = Class.forName(marqueetype).getConstructor(new Class[]{Editor.class});
        Object marquee=consmarquee.newInstance(new Object[]{ids.editor});
        Class[] conspar = {
                mde.getClass(), ingenias.editor.Editor.class, String.class,ObjectManager.class, Model.class,BasicMarqueeHandler.class};
            Object[] valpar = {
                mde, ids.editor,id,ids.om,new Model(ids),marquee};
              
        Constructor cons= Class.forName(type).getConstructor(conspar);
        mjg = (ModelJGraph) cons.newInstance(valpar);
    }
    else {
      Class[] conspar = {
    		  ids.editor.getClass()};
      Object[] valpar = {
    		  ids.editor};
      Constructor cons = Class.forName(type).getConstructor(conspar);
      mjg = (ModelJGraph) cons.newInstance(valpar);
    }

    //mjg.setEditor(ids.editor);
    //mjg.setOM(ids.om);
    //mjg.setId(id);
    this.fromGXL(ids.om, rm, mjg, graph, layout);
    ids.gm.addModel(path, id, mjg);
    ids.editor.setEnabled(true);

  }

  private Object[] GXL2Array(org.w3c.dom.Node node) throws WrongTypedDOMNode {
    if (node.getNodeName().equals("array")) {
      try {
        Hashtable children = new Hashtable();

        // Obtain children
        NodeList values = node.getChildNodes();
        for (int k = 0; k < values.getLength(); k++) {
          try {
            children.put(new Integer(k), GXL2Object(values.item(k)));
          }
          catch (WrongTypedDOMNode e) {
            // It is not a valid child.
          }
        }

        Object[] array = new Object[children.size()];
        Iterator it = children.keySet().iterator();
        while (it.hasNext()) {
          Integer index = (Integer) it.next();
          array[index.intValue()] = children.get(index);
        }
        // Construct the Array
        return array;
      }
      catch (Exception e) {
        throw new WrongTypedDOMNode(node.toString() +
            "is a malformed representation of an Object[].");
      }
    }
    else {
      throw new WrongTypedDOMNode(node.toString() +
                                  "does not represent an Object[]");
    }
  }

  
  private List GXL2List(org.w3c.dom.Node node) throws WrongTypedDOMNode {
    if (node.getNodeName().equals("list")) {
      try {
        ArrayList children = new ArrayList();

        // Obtain children
        NodeList values = node.getChildNodes();
        int index = 0;
        for (int k = 0; k < values.getLength(); k++) {
          try {
            Object child = GXL2Object(values.item(k));
            children.add(index++, child);
          }
          catch (WrongTypedDOMNode e) {
            // It is not a valid child.
          }
        }
        // Construct the List
        return ( (List) children);
      }
      catch (Exception e) {
        throw new WrongTypedDOMNode(node.toString() +
                                    "is a malformed representation of an List.");
      }
    }
    else {
      throw new WrongTypedDOMNode(node.toString() +
                                  "does not represent an List");
    }
  }

  private Point GXL2Point(org.w3c.dom.Node node) throws WrongTypedDOMNode {
    if (node.getNodeName().equals("point")) {
      try {
        // Obtain attributes
        int x = (new Integer(node.getAttributes().getNamedItem("x").
                             getNodeValue())).intValue();
        int y = (new Integer(node.getAttributes().getNamedItem("y").
                             getNodeValue())).intValue();
        // Construct the Point
        return new Point(x, y);
      }
      catch (Exception e) {
        throw new WrongTypedDOMNode(node.toString() +
            "is a malformed representation of a java.awt.Point.");
      }
    }
    else {
      throw new WrongTypedDOMNode(node.toString() +
                                  "does not represent a java.awt.Point.");
    }
  }

  private Rectangle GXL2Rectangle(org.w3c.dom.Node node) throws WrongTypedDOMNode {
    if (node.getNodeName().equals("rectangle")) {
      try {
        // Obtain attributes
        int x = (new Integer(node.getAttributes().getNamedItem("x").
                             getNodeValue())).intValue();
        int y = (new Integer(node.getAttributes().getNamedItem("y").
                             getNodeValue())).intValue();
        int width = (new Integer(node.getAttributes().getNamedItem("width").
                                 getNodeValue())).intValue();
        int height = (new Integer(node.getAttributes().getNamedItem("height").
                                  getNodeValue())).intValue();
        // Construct the Rectangle
        return new Rectangle(x, y, width, height);
      }
      catch (Exception e) {
        throw new WrongTypedDOMNode(node.toString() +
            "is a malformed representation of a java.awt.Rectangle.");
      }
    }
    else {
      throw new WrongTypedDOMNode(node.toString() +
                                  "does not represent a java.awt.Rectangle.");
    }
  }

  private Object GXL2Object(org.w3c.dom.Node node) throws WrongTypedDOMNode {
    Object object = null;

    if (node.getNodeName().equals("point")) {
      object = GXL2Point(node);
    }
    else if (node.getNodeName().equals("rectangle")) {
      object = GXL2Rectangle(node);
    }
    else if (node.getNodeName().equals("list")) {
      object = GXL2List(node);
    }
    else if (node.getNodeName().equals("array")) {
      object = GXL2Array(node);
    }
    else if (node.getNodeName().equals("string")) {
      org.w3c.dom.Node labelNode = node.getFirstChild();
      if (labelNode != null) {
        object = (String) labelNode.getNodeValue();
      }
    }
    else if (node.getNodeName().equals("attr")) {
      NodeList values = node.getChildNodes();

      for (int k = 0; k < values.getLength(); k++) {
        try {
          Object objectAttr = GXL2Object(values.item(k));
          if (object == null) {
            object = objectAttr;
          }
        }
        catch (WrongTypedDOMNode e) {
        }
      }

      if (object == null) {
        throw new WrongTypedDOMNode(node.toString() +
                                    "does not represent any valid Object.");
      }
//        if (values.item(k).getNodeName().equals("string")) {
//          Node labelNode = values.item(k).getFirstChild();
//          if (labelNode != null)
//            object = (String) labelNode.getNodeValue();
//        }
    }
    else {
      throw new WrongTypedDOMNode(node.toString() +
                                  "does not represent any valid Object.");
    }

    return object;
  }

  // Fetch Cell Map from Node
  protected Hashtable getMap(org.w3c.dom.Node node) {

    Hashtable hashAttr = new Hashtable();

    try {
      // Common attributes
      hashAttr.put(new String("id"),
                   node.getAttributes().getNamedItem("id").getNodeValue());
      hashAttr.put(new String("type"),
                   node.getAttributes().getNamedItem("type").getNodeValue());
      // Edge attributes
      hashAttr.put("from",
                   node.getAttributes().getNamedItem("from").getNodeValue());
      hashAttr.put("to", node.getAttributes().getNamedItem("to").getNodeValue());
    }
    catch (Exception e) {
      // If the node is a vertex there is neither from nor to attributes.
    }
    // Node specific attributes
    NodeList children = node.getChildNodes();
    for (int j = 0; j < children.getLength(); j++) {
      org.w3c.dom.Node attr = children.item(j);
      try {
        Object object = GXL2Object(attr);
        hashAttr.put(attr.getAttributes().getNamedItem("name").getNodeValue(),
                     object);
      }
      catch (Exception e) {
        // The node is not a valid attribute.
      }
    }
//////////    return (lab != null) ? lab : new String("");
    return hashAttr;
  }

  // Gives the ports in the model related with GraphCells in vertexList.
  private Port[] getPorts(ModelJGraph graph, Object[] vertexList) {

    // Ports of argument vertexs.
    Port[] ports = new Port[vertexList.length];
    // Obtain the model.
    GraphModel model = graph.getModel();

    // Iterate over all Objects.
    for (int i = 0; i < vertexList.length; i++) {
      Port objectPort = null;
      // Iterate over all Children
      for (int j = 0; j < model.getChildCount(vertexList[i]); j++) {
        // Fetch the Child of Vertex at Index i
        Object child = model.getChild(vertexList[i], j);
        // Check if Child is a Port
        if (child instanceof Port) {

          // Return the Child as a Port
          objectPort = (Port) child;
        }
      }
      ports[i] = objectPort;
    }

    return ports;
  }

  public static void main(String[] args) {
    GraphLoadImp1 graphLoad1 = new GraphLoadImp1();
  }

}




