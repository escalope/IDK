




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


public class GraphLoadImp2 extends GraphLoadImp2Abs
    implements GraphLoad {

  /**
   *  Constructor for the GraphLoad object
   */
  public GraphLoadImp2() {}


  // Convert a vertex represented by a GXL DOM node in a DefaultGraphCell.
  // ids contains the already processed vertex ids.

  protected DefaultGraphCell GXLVertex(String id, String type, ModelJGraph graph,
                                    ObjectManager om, RelationshipManager rm) {

    DefaultGraphCell vertex = null;

    ingenias.editor.entities.Entity en = om.getEntity(id, type);
    // if it is registered in the OM, then it is a diagram object
    if (en != null) {


   	if (en.getClass().equals(ingenias.editor.entities.DecisionNode.class))
	  return  new ingenias.editor.cell.DecisionNodeCell((ingenias.editor.entities.DecisionNode)en);

   	if (en.getClass().equals(ingenias.editor.entities.FileSpecPatternMapping.class))
	  return  new ingenias.editor.cell.FileSpecPatternMappingCell((ingenias.editor.entities.FileSpecPatternMapping)en);

   	if (en.getClass().equals(ingenias.editor.entities.TestingPackage.class))
	  return  new ingenias.editor.cell.TestingPackageCell((ingenias.editor.entities.TestingPackage)en);

   	if (en.getClass().equals(ingenias.editor.entities.MentalState.class))
	  return  new ingenias.editor.cell.MentalStateCell((ingenias.editor.entities.MentalState)en);

   	if (en.getClass().equals(ingenias.editor.entities.FAERIECtxtAttribute.class))
	  return  new ingenias.editor.cell.FAERIECtxtAttributeCell((ingenias.editor.entities.FAERIECtxtAttribute)en);

   	if (en.getClass().equals(ingenias.editor.entities.InternalApplication.class))
	  return  new ingenias.editor.cell.InternalApplicationCell((ingenias.editor.entities.InternalApplication)en);

   	if (en.getClass().equals(ingenias.editor.entities.Goal.class))
	  return  new ingenias.editor.cell.GoalCell((ingenias.editor.entities.Goal)en);

   	if (en.getClass().equals(ingenias.editor.entities.AMICtxtModel.class))
	  return  new ingenias.editor.cell.AMICtxtModelCell((ingenias.editor.entities.AMICtxtModel)en);

   	if (en.getClass().equals(ingenias.editor.entities.AgentWS.class))
	  return  new ingenias.editor.cell.AgentWSCell((ingenias.editor.entities.AgentWS)en);

   	if (en.getClass().equals(ingenias.editor.entities.ContextBindingTask.class))
	  return  new ingenias.editor.cell.ContextBindingTaskCell((ingenias.editor.entities.ContextBindingTask)en);

   	if (en.getClass().equals(ingenias.editor.entities.DeploymentPackageWithContext.class))
	  return  new ingenias.editor.cell.DeploymentPackageWithContextCell((ingenias.editor.entities.DeploymentPackageWithContext)en);

   	if (en.getClass().equals(ingenias.editor.entities.OrgDeploymentUnit.class))
	  return  new ingenias.editor.cell.OrgDeploymentUnitCell((ingenias.editor.entities.OrgDeploymentUnit)en);

   	if (en.getClass().equals(ingenias.editor.entities.WFTest.class))
	  return  new ingenias.editor.cell.WFTestCell((ingenias.editor.entities.WFTest)en);

   	if (en.getClass().equals(ingenias.editor.entities.Fact.class))
	  return  new ingenias.editor.cell.FactCell((ingenias.editor.entities.Fact)en);

   	if (en.getClass().equals(ingenias.editor.entities.AMIContextInstantiation.class))
	  return  new ingenias.editor.cell.AMIContextInstantiationCell((ingenias.editor.entities.AMIContextInstantiation)en);

   	if (en.getClass().equals(ingenias.editor.entities.ShareTouple.class))
	  return  new ingenias.editor.cell.ShareToupleCell((ingenias.editor.entities.ShareTouple)en);

   	if (en.getClass().equals(ingenias.editor.entities.DeploymentUnitByTypeWithInitMS.class))
	  return  new ingenias.editor.cell.DeploymentUnitByTypeWithInitMSCell((ingenias.editor.entities.DeploymentUnitByTypeWithInitMS)en);

   	if (en.getClass().equals(ingenias.editor.entities.JoinNode.class))
	  return  new ingenias.editor.cell.JoinNodeCell((ingenias.editor.entities.JoinNode)en);

   	if (en.getClass().equals(ingenias.editor.entities.BoxedTask.class))
	  return  new ingenias.editor.cell.BoxedTaskCell((ingenias.editor.entities.BoxedTask)en);

   	if (en.getClass().equals(ingenias.editor.entities.RuntimeEvent.class))
	  return  new ingenias.editor.cell.RuntimeEventCell((ingenias.editor.entities.RuntimeEvent)en);

   	if (en.getClass().equals(ingenias.editor.entities.InitialNode.class))
	  return  new ingenias.editor.cell.InitialNodeCell((ingenias.editor.entities.InitialNode)en);

   	if (en.getClass().equals(ingenias.editor.entities.TextUseCase.class))
	  return  new ingenias.editor.cell.TextUseCaseCell((ingenias.editor.entities.TextUseCase)en);

   	if (en.getClass().equals(ingenias.editor.entities.RemoteProcedureCall.class))
	  return  new ingenias.editor.cell.RemoteProcedureCallCell((ingenias.editor.entities.RemoteProcedureCall)en);

   	if (en.getClass().equals(ingenias.editor.entities.Resource.class))
	  return  new ingenias.editor.cell.ResourceCell((ingenias.editor.entities.Resource)en);

   	if (en.getClass().equals(ingenias.editor.entities.AgentModelBelieve.class))
	  return  new ingenias.editor.cell.AgentModelBelieveCell((ingenias.editor.entities.AgentModelBelieve)en);

   	if (en.getClass().equals(ingenias.editor.entities.ActivityFinal.class))
	  return  new ingenias.editor.cell.ActivityFinalCell((ingenias.editor.entities.ActivityFinal)en);

   	if (en.getClass().equals(ingenias.editor.entities.INGENIASUseCase.class))
	  return  new ingenias.editor.cell.INGENIASUseCaseCell((ingenias.editor.entities.INGENIASUseCase)en);

   	if (en.getClass().equals(ingenias.editor.entities.TextNote.class))
	  return  new ingenias.editor.cell.TextNoteCell((ingenias.editor.entities.TextNote)en);

   	if (en.getClass().equals(ingenias.editor.entities.RuntimeFact.class))
	  return  new ingenias.editor.cell.RuntimeFactCell((ingenias.editor.entities.RuntimeFact)en);

   	if (en.getClass().equals(ingenias.editor.entities.ForkNode.class))
	  return  new ingenias.editor.cell.ForkNodeCell((ingenias.editor.entities.ForkNode)en);

   	if (en.getClass().equals(ingenias.editor.entities.OrganizationNetwork.class))
	  return  new ingenias.editor.cell.OrganizationNetworkCell((ingenias.editor.entities.OrganizationNetwork)en);

   	if (en.getClass().equals(ingenias.editor.entities.FAERIECtxtModelInst.class))
	  return  new ingenias.editor.cell.FAERIECtxtModelInstCell((ingenias.editor.entities.FAERIECtxtModelInst)en);

   	if (en.getClass().equals(ingenias.editor.entities.FAERIECtxtModel.class))
	  return  new ingenias.editor.cell.FAERIECtxtModelCell((ingenias.editor.entities.FAERIECtxtModel)en);

   	if (en.getClass().equals(ingenias.editor.entities.MessagePassing.class))
	  return  new ingenias.editor.cell.MessagePassingCell((ingenias.editor.entities.MessagePassing)en);

   	if (en.getClass().equals(ingenias.editor.entities.GroupDeploymentUnit.class))
	  return  new ingenias.editor.cell.GroupDeploymentUnitCell((ingenias.editor.entities.GroupDeploymentUnit)en);

   	if (en.getClass().equals(ingenias.editor.entities.SubProtocol.class))
	  return  new ingenias.editor.cell.SubProtocolCell((ingenias.editor.entities.SubProtocol)en);

   	if (en.getClass().equals(ingenias.editor.entities.RuntimeConversation.class))
	  return  new ingenias.editor.cell.RuntimeConversationCell((ingenias.editor.entities.RuntimeConversation)en);

   	if (en.getClass().equals(ingenias.editor.entities.MentalEntityInstanceCreation.class))
	  return  new ingenias.editor.cell.MentalEntityInstanceCreationCell((ingenias.editor.entities.MentalEntityInstanceCreation)en);

   	if (en.getClass().equals(ingenias.editor.entities.AUMLComponent.class))
	  return  new ingenias.editor.cell.AUMLComponentCell((ingenias.editor.entities.AUMLComponent)en);

   	if (en.getClass().equals(ingenias.editor.entities.FAERIECtxtRelationship.class))
	  return  new ingenias.editor.cell.FAERIECtxtRelationshipCell((ingenias.editor.entities.FAERIECtxtRelationship)en);

   	if (en.getClass().equals(ingenias.editor.entities.Organization.class))
	  return  new ingenias.editor.cell.OrganizationCell((ingenias.editor.entities.Organization)en);

   	if (en.getClass().equals(ingenias.editor.entities.ConditionalMentalState.class))
	  return  new ingenias.editor.cell.ConditionalMentalStateCell((ingenias.editor.entities.ConditionalMentalState)en);

   	if (en.getClass().equals(ingenias.editor.entities.ApplicationWS.class))
	  return  new ingenias.editor.cell.ApplicationWSCell((ingenias.editor.entities.ApplicationWS)en);

   	if (en.getClass().equals(ingenias.editor.entities.Application.class))
	  return  new ingenias.editor.cell.ApplicationCell((ingenias.editor.entities.Application)en);

   	if (en.getClass().equals(ingenias.editor.entities.DeploymentPackage.class))
	  return  new ingenias.editor.cell.DeploymentPackageCell((ingenias.editor.entities.DeploymentPackage)en);

   	if (en.getClass().equals(ingenias.editor.entities.Plan.class))
	  return  new ingenias.editor.cell.PlanCell((ingenias.editor.entities.Plan)en);

   	if (en.getClass().equals(ingenias.editor.entities.FAERIECtxtValue.class))
	  return  new ingenias.editor.cell.FAERIECtxtValueCell((ingenias.editor.entities.FAERIECtxtValue)en);

   	if (en.getClass().equals(ingenias.editor.entities.WFTestState.class))
	  return  new ingenias.editor.cell.WFTestStateCell((ingenias.editor.entities.WFTestState)en);

   	if (en.getClass().equals(ingenias.editor.entities.FAERIECtxtEntity.class))
	  return  new ingenias.editor.cell.FAERIECtxtEntityCell((ingenias.editor.entities.FAERIECtxtEntity)en);

   	if (en.getClass().equals(ingenias.editor.entities.AUMLPort.class))
	  return  new ingenias.editor.cell.AUMLPortCell((ingenias.editor.entities.AUMLPort)en);

   	if (en.getClass().equals(ingenias.editor.entities.ContextReleaseTask.class))
	  return  new ingenias.editor.cell.ContextReleaseTaskCell((ingenias.editor.entities.ContextReleaseTask)en);

   	if (en.getClass().equals(ingenias.editor.entities.INGENIASComponent.class))
	  return  new ingenias.editor.cell.INGENIASComponentCell((ingenias.editor.entities.INGENIASComponent)en);

   	if (en.getClass().equals(ingenias.editor.entities.OrganizationGroup.class))
	  return  new ingenias.editor.cell.OrganizationGroupCell((ingenias.editor.entities.OrganizationGroup)en);

   	if (en.getClass().equals(ingenias.editor.entities.IUConcurrence.class))
	  return  new ingenias.editor.cell.IUConcurrenceCell((ingenias.editor.entities.IUConcurrence)en);

   	if (en.getClass().equals(ingenias.editor.entities.DeploymentUnitByType.class))
	  return  new ingenias.editor.cell.DeploymentUnitByTypeCell((ingenias.editor.entities.DeploymentUnitByType)en);

   	if (en.getClass().equals(ingenias.editor.entities.IUIterate.class))
	  return  new ingenias.editor.cell.IUIterateCell((ingenias.editor.entities.IUIterate)en);

   	if (en.getClass().equals(ingenias.editor.entities.AUMLAlternativeRow.class))
	  return  new ingenias.editor.cell.AUMLAlternativeRowCell((ingenias.editor.entities.AUMLAlternativeRow)en);

   	if (en.getClass().equals(ingenias.editor.entities.Column.class))
	  return  new ingenias.editor.cell.ColumnCell((ingenias.editor.entities.Column)en);

   	if (en.getClass().equals(ingenias.editor.entities.AutonomousEntityQuery.class))
	  return  new ingenias.editor.cell.AutonomousEntityQueryCell((ingenias.editor.entities.AutonomousEntityQuery)en);

   	if (en.getClass().equals(ingenias.editor.entities.AUMLContainer.class))
	  return  new ingenias.editor.cell.AUMLContainerCell((ingenias.editor.entities.AUMLContainer)en);

   	if (en.getClass().equals(ingenias.editor.entities.ConcreteAgent.class))
	  return  new ingenias.editor.cell.ConcreteAgentCell((ingenias.editor.entities.ConcreteAgent)en);

   	if (en.getClass().equals(ingenias.editor.entities.UMLComment.class))
	  return  new ingenias.editor.cell.UMLCommentCell((ingenias.editor.entities.UMLComment)en);

   	if (en.getClass().equals(ingenias.editor.entities.AgentRequirementsQuery.class))
	  return  new ingenias.editor.cell.AgentRequirementsQueryCell((ingenias.editor.entities.AgentRequirementsQuery)en);

   	if (en.getClass().equals(ingenias.editor.entities.UMLSpecification.class))
	  return  new ingenias.editor.cell.UMLSpecificationCell((ingenias.editor.entities.UMLSpecification)en);

   	if (en.getClass().equals(ingenias.editor.entities.Believe.class))
	  return  new ingenias.editor.cell.BelieveCell((ingenias.editor.entities.Believe)en);

   	if (en.getClass().equals(ingenias.editor.entities.WorkflowBox.class))
	  return  new ingenias.editor.cell.WorkflowBoxCell((ingenias.editor.entities.WorkflowBox)en);

   	if (en.getClass().equals(ingenias.editor.entities.FAERIECtxtElement.class))
	  return  new ingenias.editor.cell.FAERIECtxtElementCell((ingenias.editor.entities.FAERIECtxtElement)en);

   	if (en.getClass().equals(ingenias.editor.entities.DeploymentUnitByTypeEnumInitMS.class))
	  return  new ingenias.editor.cell.DeploymentUnitByTypeEnumInitMSCell((ingenias.editor.entities.DeploymentUnitByTypeEnumInitMS)en);

   	if (en.getClass().equals(ingenias.editor.entities.ContextUseTask.class))
	  return  new ingenias.editor.cell.ContextUseTaskCell((ingenias.editor.entities.ContextUseTask)en);

   	if (en.getClass().equals(ingenias.editor.entities.GeneralEvent.class))
	  return  new ingenias.editor.cell.GeneralEventCell((ingenias.editor.entities.GeneralEvent)en);

   	if (en.getClass().equals(ingenias.editor.entities.Compromise.class))
	  return  new ingenias.editor.cell.CompromiseCell((ingenias.editor.entities.Compromise)en);

   	if (en.getClass().equals(ingenias.editor.entities.Role.class))
	  return  new ingenias.editor.cell.RoleCell((ingenias.editor.entities.Role)en);

   	if (en.getClass().equals(ingenias.editor.entities.EnvironmentApplication.class))
	  return  new ingenias.editor.cell.EnvironmentApplicationCell((ingenias.editor.entities.EnvironmentApplication)en);

   	if (en.getClass().equals(ingenias.editor.entities.WFTestFinalState.class))
	  return  new ingenias.editor.cell.WFTestFinalStateCell((ingenias.editor.entities.WFTestFinalState)en);

   	if (en.getClass().equals(ingenias.editor.entities.ApplicationEventSlots.class))
	  return  new ingenias.editor.cell.ApplicationEventSlotsCell((ingenias.editor.entities.ApplicationEventSlots)en);

   	if (en.getClass().equals(ingenias.editor.entities.Protocol.class))
	  return  new ingenias.editor.cell.ProtocolCell((ingenias.editor.entities.Protocol)en);

   	if (en.getClass().equals(ingenias.editor.entities.MentalInstanceSpecification.class))
	  return  new ingenias.editor.cell.MentalInstanceSpecificationCell((ingenias.editor.entities.MentalInstanceSpecification)en);

   	if (en.getClass().equals(ingenias.editor.entities.ActionUML.class))
	  return  new ingenias.editor.cell.ActionUMLCell((ingenias.editor.entities.ActionUML)en);

   	if (en.getClass().equals(ingenias.editor.entities.InteractionUnit.class))
	  return  new ingenias.editor.cell.InteractionUnitCell((ingenias.editor.entities.InteractionUnit)en);

   	if (en.getClass().equals(ingenias.editor.entities.GRASIASpecification.class))
	  return  new ingenias.editor.cell.GRASIASpecificationCell((ingenias.editor.entities.GRASIASpecification)en);

   	if (en.getClass().equals(ingenias.editor.entities.MentalStateProcessor.class))
	  return  new ingenias.editor.cell.MentalStateProcessorCell((ingenias.editor.entities.MentalStateProcessor)en);

   	if (en.getClass().equals(ingenias.editor.entities.MergeNode.class))
	  return  new ingenias.editor.cell.MergeNodeCell((ingenias.editor.entities.MergeNode)en);

   	if (en.getClass().equals(ingenias.editor.entities.EndNode.class))
	  return  new ingenias.editor.cell.EndNodeCell((ingenias.editor.entities.EndNode)en);

   	if (en.getClass().equals(ingenias.editor.entities.FrameFact.class))
	  return  new ingenias.editor.cell.FrameFactCell((ingenias.editor.entities.FrameFact)en);

   	if (en.getClass().equals(ingenias.editor.entities.Test.class))
	  return  new ingenias.editor.cell.TestCell((ingenias.editor.entities.Test)en);

   	if (en.getClass().equals(ingenias.editor.entities.Lifeline.class))
	  return  new ingenias.editor.cell.LifelineCell((ingenias.editor.entities.Lifeline)en);

   	if (en.getClass().equals(ingenias.editor.entities.Interaction.class))
	  return  new ingenias.editor.cell.InteractionCell((ingenias.editor.entities.Interaction)en);

   	if (en.getClass().equals(ingenias.editor.entities.SimExtractedInformation.class))
	  return  new ingenias.editor.cell.SimExtractedInformationCell((ingenias.editor.entities.SimExtractedInformation)en);

   	if (en.getClass().equals(ingenias.editor.entities.Agent.class))
	  return  new ingenias.editor.cell.AgentCell((ingenias.editor.entities.Agent)en);

   	if (en.getClass().equals(ingenias.editor.entities.TaskOutputDefinition.class))
	  return  new ingenias.editor.cell.TaskOutputDefinitionCell((ingenias.editor.entities.TaskOutputDefinition)en);

   	if (en.getClass().equals(ingenias.editor.entities.MentalStateManager.class))
	  return  new ingenias.editor.cell.MentalStateManagerCell((ingenias.editor.entities.MentalStateManager)en);

   	if (en.getClass().equals(ingenias.editor.entities.INGENIASCodeComponent.class))
	  return  new ingenias.editor.cell.INGENIASCodeComponentCell((ingenias.editor.entities.INGENIASCodeComponent)en);

   	if (en.getClass().equals(ingenias.editor.entities.SimulationPackage.class))
	  return  new ingenias.editor.cell.SimulationPackageCell((ingenias.editor.entities.SimulationPackage)en);

   	if (en.getClass().equals(ingenias.editor.entities.CommunicationEvent.class))
	  return  new ingenias.editor.cell.CommunicationEventCell((ingenias.editor.entities.CommunicationEvent)en);

   	if (en.getClass().equals(ingenias.editor.entities.TaskWS.class))
	  return  new ingenias.editor.cell.TaskWSCell((ingenias.editor.entities.TaskWS)en);

   	if (en.getClass().equals(ingenias.editor.entities.GoalStateWS.class))
	  return  new ingenias.editor.cell.GoalStateWSCell((ingenias.editor.entities.GoalStateWS)en);

   	if (en.getClass().equals(ingenias.editor.entities.RuntimeCommFailure.class))
	  return  new ingenias.editor.cell.RuntimeCommFailureCell((ingenias.editor.entities.RuntimeCommFailure)en);

   	if (en.getClass().equals(ingenias.editor.entities.StateGoal.class))
	  return  new ingenias.editor.cell.StateGoalCell((ingenias.editor.entities.StateGoal)en);

   	if (en.getClass().equals(ingenias.editor.entities.Conversation.class))
	  return  new ingenias.editor.cell.ConversationCell((ingenias.editor.entities.Conversation)en);

   	if (en.getClass().equals(ingenias.editor.entities.ApplicationEvent.class))
	  return  new ingenias.editor.cell.ApplicationEventCell((ingenias.editor.entities.ApplicationEvent)en);

   	if (en.getClass().equals(ingenias.editor.entities.WFTestInitialState.class))
	  return  new ingenias.editor.cell.WFTestInitialStateCell((ingenias.editor.entities.WFTestInitialState)en);

   	if (en.getClass().equals(ingenias.editor.entities.Task.class))
	  return  new ingenias.editor.cell.TaskCell((ingenias.editor.entities.Task)en);

   	if (en.getClass().equals(ingenias.editor.entities.DeploymentUnitByTypeMSEntity.class))
	  return  new ingenias.editor.cell.DeploymentUnitByTypeMSEntityCell((ingenias.editor.entities.DeploymentUnitByTypeMSEntity)en);

   	if (en.getClass().equals(ingenias.editor.entities.MentalEntityInstanceAccess.class))
	  return  new ingenias.editor.cell.MentalEntityInstanceAccessCell((ingenias.editor.entities.MentalEntityInstanceAccess)en);

   	if (en.getClass().equals(ingenias.editor.entities.RoleWS.class))
	  return  new ingenias.editor.cell.RoleWSCell((ingenias.editor.entities.RoleWS)en);

   	if (en.getClass().equals(ingenias.editor.entities.TaskInputDefinition.class))
	  return  new ingenias.editor.cell.TaskInputDefinitionCell((ingenias.editor.entities.TaskInputDefinition)en);

   	if (en.getClass().equals(ingenias.editor.entities.AMIContext.class))
	  return  new ingenias.editor.cell.AMIContextCell((ingenias.editor.entities.AMIContext)en);

   	if (en.getClass().equals(ingenias.editor.entities.SimulationEvent.class))
	  return  new ingenias.editor.cell.SimulationEventCell((ingenias.editor.entities.SimulationEvent)en);

   	if (en.getClass().equals(ingenias.editor.entities.FAERIEContext.class))
	  return  new ingenias.editor.cell.FAERIEContextCell((ingenias.editor.entities.FAERIEContext)en);

   	if (en.getClass().equals(ingenias.editor.entities.AUMLAlternativeBox.class))
	  return  new ingenias.editor.cell.AUMLAlternativeBoxCell((ingenias.editor.entities.AUMLAlternativeBox)en);

   	if (en.getClass().equals(ingenias.editor.entities.Workflow.class))
	  return  new ingenias.editor.cell.WorkflowCell((ingenias.editor.entities.Workflow)en);

    } else {
    // If not, it is a relationship
      en = rm.getRelationship(id);
    if (en==null) return null;

    if (en instanceof ingenias.editor.entities.AHasMSManager)
     return  new ingenias.editor.cell.AHasMSManagerEdge((ingenias.editor.entities.AHasMSManager)en);

    if (en instanceof ingenias.editor.entities.AGOInconditionalSubordinationRelationshipOrg)
     return  new ingenias.editor.cell.AGOInconditionalSubordinationRelationshipOrgEdge((ingenias.editor.entities.AGOInconditionalSubordinationRelationshipOrg)en);

    if (en instanceof ingenias.editor.entities.GTDecomposes)
     return  new ingenias.editor.cell.GTDecomposesEdge((ingenias.editor.entities.GTDecomposes)en);

    if (en instanceof ingenias.editor.entities.WFResponsible)
     return  new ingenias.editor.cell.WFResponsibleEdge((ingenias.editor.entities.WFResponsible)en);

    if (en instanceof ingenias.editor.entities.CDUsesCode)
     return  new ingenias.editor.cell.CDUsesCodeEdge((ingenias.editor.entities.CDUsesCode)en);

    if (en instanceof ingenias.editor.entities.AGOCondSubordinationRelationshipGroup)
     return  new ingenias.editor.cell.AGOCondSubordinationRelationshipGroupEdge((ingenias.editor.entities.AGOCondSubordinationRelationshipGroup)en);

    if (en instanceof ingenias.editor.entities.OHasMember)
     return  new ingenias.editor.cell.OHasMemberEdge((ingenias.editor.entities.OHasMember)en);

    if (en instanceof ingenias.editor.entities.EPerceivesPolling)
     return  new ingenias.editor.cell.EPerceivesPollingEdge((ingenias.editor.entities.EPerceivesPolling)en);

    if (en instanceof ingenias.editor.entities.WFParticipates)
     return  new ingenias.editor.cell.WFParticipatesEdge((ingenias.editor.entities.WFParticipates)en);

    if (en instanceof ingenias.editor.entities.WFDecomposesWF)
     return  new ingenias.editor.cell.WFDecomposesWFEdge((ingenias.editor.entities.WFDecomposesWF)en);

    if (en instanceof ingenias.editor.entities.UIInitiates)
     return  new ingenias.editor.cell.UIInitiatesEdge((ingenias.editor.entities.UIInitiates)en);

    if (en instanceof ingenias.editor.entities.ContributeNegatively)
     return  new ingenias.editor.cell.ContributeNegativelyEdge((ingenias.editor.entities.ContributeNegatively)en);

    if (en instanceof ingenias.editor.entities.MemberDplmnt)
     return  new ingenias.editor.cell.MemberDplmntEdge((ingenias.editor.entities.MemberDplmnt)en);

    if (en instanceof ingenias.editor.entities.DefinesDeployment)
     return  new ingenias.editor.cell.DefinesDeploymentEdge((ingenias.editor.entities.DefinesDeployment)en);

    if (en instanceof ingenias.editor.entities.HasCtxModel)
     return  new ingenias.editor.cell.HasCtxModelEdge((ingenias.editor.entities.HasCtxModel)en);

    if (en instanceof ingenias.editor.entities.WFUsesMethod)
     return  new ingenias.editor.cell.WFUsesMethodEdge((ingenias.editor.entities.WFUsesMethod)en);

    if (en instanceof ingenias.editor.entities.UISelection)
     return  new ingenias.editor.cell.UISelectionEdge((ingenias.editor.entities.UISelection)en);

    if (en instanceof ingenias.editor.entities.EPerceivesNotification)
     return  new ingenias.editor.cell.EPerceivesNotificationEdge((ingenias.editor.entities.EPerceivesNotification)en);

    if (en instanceof ingenias.editor.entities.OHasWF)
     return  new ingenias.editor.cell.OHasWFEdge((ingenias.editor.entities.OHasWF)en);

    if (en instanceof ingenias.editor.entities.ParticipatesInUseCase)
     return  new ingenias.editor.cell.ParticipatesInUseCaseEdge((ingenias.editor.entities.ParticipatesInUseCase)en);

    if (en instanceof ingenias.editor.entities.GTCreates)
     return  new ingenias.editor.cell.GTCreatesEdge((ingenias.editor.entities.GTCreates)en);

    if (en instanceof ingenias.editor.entities.WFCancels)
     return  new ingenias.editor.cell.WFCancelsEdge((ingenias.editor.entities.WFCancels)en);

    if (en instanceof ingenias.editor.entities.WFTestAfter)
     return  new ingenias.editor.cell.WFTestAfterEdge((ingenias.editor.entities.WFTestAfter)en);

    if (en instanceof ingenias.editor.entities.AGOInconditionalSubordinationRelationshipGroup)
     return  new ingenias.editor.cell.AGOInconditionalSubordinationRelationshipGroupEdge((ingenias.editor.entities.AGOInconditionalSubordinationRelationshipGroup)en);

    if (en instanceof ingenias.editor.entities.AUMLSendSimple)
     return  new ingenias.editor.cell.AUMLSendSimpleEdge((ingenias.editor.entities.AUMLSendSimple)en);

    if (en instanceof ingenias.editor.entities.GTModifies)
     return  new ingenias.editor.cell.GTModifiesEdge((ingenias.editor.entities.GTModifies)en);

    if (en instanceof ingenias.editor.entities.WFProduces)
     return  new ingenias.editor.cell.WFProducesEdge((ingenias.editor.entities.WFProduces)en);

    if (en instanceof ingenias.editor.entities.GTPursues)
     return  new ingenias.editor.cell.GTPursuesEdge((ingenias.editor.entities.GTPursues)en);

    if (en instanceof ingenias.editor.entities.UseCasePursues)
     return  new ingenias.editor.cell.UseCasePursuesEdge((ingenias.editor.entities.UseCasePursues)en);

    if (en instanceof ingenias.editor.entities.EPerceives)
     return  new ingenias.editor.cell.EPerceivesEdge((ingenias.editor.entities.EPerceives)en);

    if (en instanceof ingenias.editor.entities.ODecomposesWF)
     return  new ingenias.editor.cell.ODecomposesWFEdge((ingenias.editor.entities.ODecomposesWF)en);

    if (en instanceof ingenias.editor.entities.WFFollows)
     return  new ingenias.editor.cell.WFFollowsEdge((ingenias.editor.entities.WFFollows)en);

    if (en instanceof ingenias.editor.entities.WFDecomposes)
     return  new ingenias.editor.cell.WFDecomposesEdge((ingenias.editor.entities.WFDecomposes)en);

    if (en instanceof ingenias.editor.entities.AGOClientServerRelationshipMember)
     return  new ingenias.editor.cell.AGOClientServerRelationshipMemberEdge((ingenias.editor.entities.AGOClientServerRelationshipMember)en);

    if (en instanceof ingenias.editor.entities.WFSpecifiesExecution)
     return  new ingenias.editor.cell.WFSpecifiesExecutionEdge((ingenias.editor.entities.WFSpecifiesExecution)en);

    if (en instanceof ingenias.editor.entities.OrgDplmnt)
     return  new ingenias.editor.cell.OrgDplmntEdge((ingenias.editor.entities.OrgDplmnt)en);

    if (en instanceof ingenias.editor.entities.AInstanceOf)
     return  new ingenias.editor.cell.AInstanceOfEdge((ingenias.editor.entities.AInstanceOf)en);

    if (en instanceof ingenias.editor.entities.AGORelationshipGroup)
     return  new ingenias.editor.cell.AGORelationshipGroupEdge((ingenias.editor.entities.AGORelationshipGroup)en);

    if (en instanceof ingenias.editor.entities.AGORelationshipMember)
     return  new ingenias.editor.cell.AGORelationshipMemberEdge((ingenias.editor.entities.AGORelationshipMember)en);

    if (en instanceof ingenias.editor.entities.AInherits)
     return  new ingenias.editor.cell.AInheritsEdge((ingenias.editor.entities.AInherits)en);

    if (en instanceof ingenias.editor.entities.GTFails)
     return  new ingenias.editor.cell.GTFailsEdge((ingenias.editor.entities.GTFails)en);

    if (en instanceof ingenias.editor.entities.AGOClientServerRelationshipOrg)
     return  new ingenias.editor.cell.AGOClientServerRelationshipOrgEdge((ingenias.editor.entities.AGOClientServerRelationshipOrg)en);

    if (en instanceof ingenias.editor.entities.AGOSubordinationRelationshipOrg)
     return  new ingenias.editor.cell.AGOSubordinationRelationshipOrgEdge((ingenias.editor.entities.AGOSubordinationRelationshipOrg)en);

    if (en instanceof ingenias.editor.entities.GTDepends)
     return  new ingenias.editor.cell.GTDependsEdge((ingenias.editor.entities.GTDepends)en);

    if (en instanceof ingenias.editor.entities.Includes)
     return  new ingenias.editor.cell.IncludesEdge((ingenias.editor.entities.Includes)en);

    if (en instanceof ingenias.editor.entities.IHasSpec)
     return  new ingenias.editor.cell.IHasSpecEdge((ingenias.editor.entities.IHasSpec)en);

    if (en instanceof ingenias.editor.entities.WFPursue)
     return  new ingenias.editor.cell.WFPursueEdge((ingenias.editor.entities.WFPursue)en);

    if (en instanceof ingenias.editor.entities.EResourceBelongsTo)
     return  new ingenias.editor.cell.EResourceBelongsToEdge((ingenias.editor.entities.EResourceBelongsTo)en);

    if (en instanceof ingenias.editor.entities.WFConsumes)
     return  new ingenias.editor.cell.WFConsumesEdge((ingenias.editor.entities.WFConsumes)en);

    if (en instanceof ingenias.editor.entities.FAERIETrgtEntity)
     return  new ingenias.editor.cell.FAERIETrgtEntityEdge((ingenias.editor.entities.FAERIETrgtEntity)en);

    if (en instanceof ingenias.editor.entities.Generalizes)
     return  new ingenias.editor.cell.GeneralizesEdge((ingenias.editor.entities.Generalizes)en);

    if (en instanceof ingenias.editor.entities.FAERIESrcEntity)
     return  new ingenias.editor.cell.FAERIESrcEntityEdge((ingenias.editor.entities.FAERIESrcEntity)en);

    if (en instanceof ingenias.editor.entities.GroupDplmnt)
     return  new ingenias.editor.cell.GroupDplmntEdge((ingenias.editor.entities.GroupDplmnt)en);

    if (en instanceof ingenias.editor.entities.PConnects)
     return  new ingenias.editor.cell.PConnectsEdge((ingenias.editor.entities.PConnects)en);

    if (en instanceof ingenias.editor.entities.AHasMSProcessor)
     return  new ingenias.editor.cell.AHasMSProcessorEdge((ingenias.editor.entities.AHasMSProcessor)en);

    if (en instanceof ingenias.editor.entities.AGOSubordinationRelationshipGroup)
     return  new ingenias.editor.cell.AGOSubordinationRelationshipGroupEdge((ingenias.editor.entities.AGOSubordinationRelationshipGroup)en);

    if (en instanceof ingenias.editor.entities.CtxtUpdates)
     return  new ingenias.editor.cell.CtxtUpdatesEdge((ingenias.editor.entities.CtxtUpdates)en);

    if (en instanceof ingenias.editor.entities.GroupBelongsToOrganization)
     return  new ingenias.editor.cell.GroupBelongsToOrganizationEdge((ingenias.editor.entities.GroupBelongsToOrganization)en);

    if (en instanceof ingenias.editor.entities.AContainsME)
     return  new ingenias.editor.cell.AContainsMEEdge((ingenias.editor.entities.AContainsME)en);

    if (en instanceof ingenias.editor.entities.GTOrDepends)
     return  new ingenias.editor.cell.GTOrDependsEdge((ingenias.editor.entities.GTOrDepends)en);

    if (en instanceof ingenias.editor.entities.AUMLUseProtocol)
     return  new ingenias.editor.cell.AUMLUseProtocolEdge((ingenias.editor.entities.AUMLUseProtocol)en);

    if (en instanceof ingenias.editor.entities.GTAndDepends)
     return  new ingenias.editor.cell.GTAndDependsEdge((ingenias.editor.entities.GTAndDepends)en);

    if (en instanceof ingenias.editor.entities.ODecomposesGroup)
     return  new ingenias.editor.cell.ODecomposesGroupEdge((ingenias.editor.entities.ODecomposesGroup)en);

    if (en instanceof ingenias.editor.entities.IInitiates)
     return  new ingenias.editor.cell.IInitiatesEdge((ingenias.editor.entities.IInitiates)en);

    if (en instanceof ingenias.editor.entities.Contribute)
     return  new ingenias.editor.cell.ContributeEdge((ingenias.editor.entities.Contribute)en);

    if (en instanceof ingenias.editor.entities.AGORelationshipOrg)
     return  new ingenias.editor.cell.AGORelationshipOrgEdge((ingenias.editor.entities.AGORelationshipOrg)en);

    if (en instanceof ingenias.editor.entities.Consumes)
     return  new ingenias.editor.cell.ConsumesEdge((ingenias.editor.entities.Consumes)en);

    if (en instanceof ingenias.editor.entities.TestEventInjection)
     return  new ingenias.editor.cell.TestEventInjectionEdge((ingenias.editor.entities.TestEventInjection)en);

    if (en instanceof ingenias.editor.entities.AHasMS)
     return  new ingenias.editor.cell.AHasMSEdge((ingenias.editor.entities.AHasMS)en);

    if (en instanceof ingenias.editor.entities.UMLDescribesUseCase)
     return  new ingenias.editor.cell.UMLDescribesUseCaseEdge((ingenias.editor.entities.UMLDescribesUseCase)en);

    if (en instanceof ingenias.editor.entities.IColaborates)
     return  new ingenias.editor.cell.IColaboratesEdge((ingenias.editor.entities.IColaborates)en);

    if (en instanceof ingenias.editor.entities.WFFollowsGuarded)
     return  new ingenias.editor.cell.WFFollowsGuardedEdge((ingenias.editor.entities.WFFollowsGuarded)en);

    if (en instanceof ingenias.editor.entities.GTAffects)
     return  new ingenias.editor.cell.GTAffectsEdge((ingenias.editor.entities.GTAffects)en);

    if (en instanceof ingenias.editor.entities.IAccesses)
     return  new ingenias.editor.cell.IAccessesEdge((ingenias.editor.entities.IAccesses)en);

    if (en instanceof ingenias.editor.entities.UIPrecedes)
     return  new ingenias.editor.cell.UIPrecedesEdge((ingenias.editor.entities.UIPrecedes)en);

    if (en instanceof ingenias.editor.entities.ARoleInheritance)
     return  new ingenias.editor.cell.ARoleInheritanceEdge((ingenias.editor.entities.ARoleInheritance)en);

    if (en instanceof ingenias.editor.entities.AGOInconditionalSubordinationRelationshipMember)
     return  new ingenias.editor.cell.AGOInconditionalSubordinationRelationshipMemberEdge((ingenias.editor.entities.AGOInconditionalSubordinationRelationshipMember)en);

    if (en instanceof ingenias.editor.entities.WFStarts)
     return  new ingenias.editor.cell.WFStartsEdge((ingenias.editor.entities.WFStarts)en);

    if (en instanceof ingenias.editor.entities.UMLSendsMessage)
     return  new ingenias.editor.cell.UMLSendsMessageEdge((ingenias.editor.entities.UMLSendsMessage)en);

    if (en instanceof ingenias.editor.entities.UMLAssociation)
     return  new ingenias.editor.cell.UMLAssociationEdge((ingenias.editor.entities.UMLAssociation)en);

    if (en instanceof ingenias.editor.entities.OHasGroup)
     return  new ingenias.editor.cell.OHasGroupEdge((ingenias.editor.entities.OHasGroup)en);

    if (en instanceof ingenias.editor.entities.WFResponsable)
     return  new ingenias.editor.cell.WFResponsableEdge((ingenias.editor.entities.WFResponsable)en);

    if (en instanceof ingenias.editor.entities.WFContainsTask)
     return  new ingenias.editor.cell.WFContainsTaskEdge((ingenias.editor.entities.WFContainsTask)en);

    if (en instanceof ingenias.editor.entities.FAERIEGeneratedBy)
     return  new ingenias.editor.cell.FAERIEGeneratedByEdge((ingenias.editor.entities.FAERIEGeneratedBy)en);

    if (en instanceof ingenias.editor.entities.FAERIEAppliedTo)
     return  new ingenias.editor.cell.FAERIEAppliedToEdge((ingenias.editor.entities.FAERIEAppliedTo)en);

    if (en instanceof ingenias.editor.entities.UMLRealizes)
     return  new ingenias.editor.cell.UMLRealizesEdge((ingenias.editor.entities.UMLRealizes)en);

    if (en instanceof ingenias.editor.entities.WFConnects)
     return  new ingenias.editor.cell.WFConnectsEdge((ingenias.editor.entities.WFConnects)en);

    if (en instanceof ingenias.editor.entities.Extends)
     return  new ingenias.editor.cell.ExtendsEdge((ingenias.editor.entities.Extends)en);

    if (en instanceof ingenias.editor.entities.GTInherits)
     return  new ingenias.editor.cell.GTInheritsEdge((ingenias.editor.entities.GTInherits)en);

    if (en instanceof ingenias.editor.entities.GTDecomposesAND)
     return  new ingenias.editor.cell.GTDecomposesANDEdge((ingenias.editor.entities.GTDecomposesAND)en);

    if (en instanceof ingenias.editor.entities.SimulationPursues)
     return  new ingenias.editor.cell.SimulationPursuesEdge((ingenias.editor.entities.SimulationPursues)en);

    if (en instanceof ingenias.editor.entities.WFEnds)
     return  new ingenias.editor.cell.WFEndsEdge((ingenias.editor.entities.WFEnds)en);

    if (en instanceof ingenias.editor.entities.AUMLSelection)
     return  new ingenias.editor.cell.AUMLSelectionEdge((ingenias.editor.entities.AUMLSelection)en);

    if (en instanceof ingenias.editor.entities.CtxtNotifies)
     return  new ingenias.editor.cell.CtxtNotifiesEdge((ingenias.editor.entities.CtxtNotifies)en);

    if (en instanceof ingenias.editor.entities.UMLAnnotatedElement)
     return  new ingenias.editor.cell.UMLAnnotatedElementEdge((ingenias.editor.entities.UMLAnnotatedElement)en);

    if (en instanceof ingenias.editor.entities.UIColaborates)
     return  new ingenias.editor.cell.UIColaboratesEdge((ingenias.editor.entities.UIColaborates)en);

    if (en instanceof ingenias.editor.entities.TriggersFailure)
     return  new ingenias.editor.cell.TriggersFailureEdge((ingenias.editor.entities.TriggersFailure)en);

    if (en instanceof ingenias.editor.entities.WFPlays)
     return  new ingenias.editor.cell.WFPlaysEdge((ingenias.editor.entities.WFPlays)en);

    if (en instanceof ingenias.editor.entities.AGOClientServerRelationshipGroup)
     return  new ingenias.editor.cell.AGOClientServerRelationshipGroupEdge((ingenias.editor.entities.AGOClientServerRelationshipGroup)en);

    if (en instanceof ingenias.editor.entities.GTDestroys)
     return  new ingenias.editor.cell.GTDestroysEdge((ingenias.editor.entities.GTDestroys)en);

    if (en instanceof ingenias.editor.entities.IPursues)
     return  new ingenias.editor.cell.IPursuesEdge((ingenias.editor.entities.IPursues)en);

    if (en instanceof ingenias.editor.entities.ApplicationBelongsTo)
     return  new ingenias.editor.cell.ApplicationBelongsToEdge((ingenias.editor.entities.ApplicationBelongsTo)en);

    if (en instanceof ingenias.editor.entities.AGOSubordinationRelationshipMember)
     return  new ingenias.editor.cell.AGOSubordinationRelationshipMemberEdge((ingenias.editor.entities.AGOSubordinationRelationshipMember)en);

    if (en instanceof ingenias.editor.entities.WSConnects)
     return  new ingenias.editor.cell.WSConnectsEdge((ingenias.editor.entities.WSConnects)en);

    if (en instanceof ingenias.editor.entities.GTDecomposesOR)
     return  new ingenias.editor.cell.GTDecomposesOREdge((ingenias.editor.entities.GTDecomposesOR)en);

    if (en instanceof ingenias.editor.entities.AGOCondSubordinationRelationshipOrg)
     return  new ingenias.editor.cell.AGOCondSubordinationRelationshipOrgEdge((ingenias.editor.entities.AGOCondSubordinationRelationshipOrg)en);

    if (en instanceof ingenias.editor.entities.FAERIEHasValue)
     return  new ingenias.editor.cell.FAERIEHasValueEdge((ingenias.editor.entities.FAERIEHasValue)en);

    if (en instanceof ingenias.editor.entities.GTSatisfies)
     return  new ingenias.editor.cell.GTSatisfiesEdge((ingenias.editor.entities.GTSatisfies)en);

    if (en instanceof ingenias.editor.entities.WFUses)
     return  new ingenias.editor.cell.WFUsesEdge((ingenias.editor.entities.WFUses)en);

    if (en instanceof ingenias.editor.entities.AGOCondSubordinationRelationshipMember)
     return  new ingenias.editor.cell.AGOCondSubordinationRelationshipMemberEdge((ingenias.editor.entities.AGOCondSubordinationRelationshipMember)en);

    if (en instanceof ingenias.editor.entities.WFDecides)
     return  new ingenias.editor.cell.WFDecidesEdge((ingenias.editor.entities.WFDecides)en);

    if (en instanceof ingenias.editor.entities.ContributePositively)
     return  new ingenias.editor.cell.ContributePositivelyEdge((ingenias.editor.entities.ContributePositively)en);


    }

    return null;
  }


 
}





