
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

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
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

public class ProjectTreeRenderer extends javax.swing.tree.DefaultTreeCellRenderer{
// Meta-models icons

 ImageIcon modeloEnvironmentModel=new ImageIcon(ImageLoader.getImage("images/menvdiag.gif"));

 ImageIcon modeloComponentDiagram=new ImageIcon(ImageLoader.getImage("images/musediag.gif"));

 ImageIcon modeloOrganizationModel=new ImageIcon(ImageLoader.getImage("images/morgdiag.gif"));

 ImageIcon modeloTasksAndGoalsModel=new ImageIcon(ImageLoader.getImage("images/mtaskgoal.gif"));

 ImageIcon modeloInteractionModel=new ImageIcon(ImageLoader.getImage("images/minterdiag.gif"));

 ImageIcon modeloActivityDiagram=new ImageIcon(ImageLoader.getImage("images/musediag.gif"));

 ImageIcon modeloAgentModel=new ImageIcon(ImageLoader.getImage("images/magdiag.gif"));

 ImageIcon modeloUseCaseDiagram=new ImageIcon(ImageLoader.getImage("images/musediag.gif"));

 ImageIcon modeloAUMLInteractionDiagram=new ImageIcon(ImageLoader.getImage("images/musediag.gif"));

 ImageIcon modeloDeployDiagram=new ImageIcon(ImageLoader.getImage("images/musediag.gif"));


// Object icons
 
 ImageIcon DecisionNodeIcon=new ImageIcon(ImageLoader.getImage("images/mdecisionnode.gif"));
	
 ImageIcon TestingPackageIcon=new ImageIcon(ImageLoader.getImage("images/mdepl.gif"));
	
 ImageIcon MentalStateIcon=new ImageIcon(ImageLoader.getImage("images/mmstate.gif"));
	
 ImageIcon InternalApplicationIcon=new ImageIcon(ImageLoader.getImage("images/mappi.gif"));
	
 ImageIcon GoalIcon=new ImageIcon(ImageLoader.getImage("images/mgoal.gif"));
	
 ImageIcon AgentWSIcon=new ImageIcon(ImageLoader.getImage("images/magentws.gif"));
	
 ImageIcon FactIcon=new ImageIcon(ImageLoader.getImage("images/mfact.gif"));
	
 ImageIcon ShareToupleIcon=new ImageIcon(ImageLoader.getImage("images/miu.gif"));
	
 ImageIcon DeploymentUnitByTypeWithInitMSIcon=new ImageIcon(ImageLoader.getImage("images/mimtypedepl.gif"));
	
 ImageIcon JoinNodeIcon=new ImageIcon(ImageLoader.getImage("images/mforknode.gif"));
	
 ImageIcon RuntimeEventIcon=new ImageIcon(ImageLoader.getImage("images/mffact.gif"));
	
 ImageIcon InitialNodeIcon=new ImageIcon(ImageLoader.getImage("images/minitialnode.gif"));
	
 ImageIcon TextUseCaseIcon=new ImageIcon(ImageLoader.getImage("images/musecase.gif"));
	
 ImageIcon RemoteProcedureCallIcon=new ImageIcon(ImageLoader.getImage("images/miu.gif"));
	
 ImageIcon ResourceIcon=new ImageIcon(ImageLoader.getImage("images/mresource.gif"));
	
 ImageIcon AgentModelBelieveIcon=new ImageIcon(ImageLoader.getImage("images/mabel.gif"));
	
 ImageIcon ActivityFinalIcon=new ImageIcon(ImageLoader.getImage("images/mfinalnode.gif"));
	
 ImageIcon INGENIASUseCaseIcon=new ImageIcon(ImageLoader.getImage("images/miusecase.gif"));
	
 ImageIcon TextNoteIcon=new ImageIcon(ImageLoader.getImage("images/mtext.gif"));
	
 ImageIcon RuntimeFactIcon=new ImageIcon(ImageLoader.getImage("images/mffact.gif"));
	
 ImageIcon ForkNodeIcon=new ImageIcon(ImageLoader.getImage("images/mforknode.gif"));
	
 ImageIcon OrganizationNetworkIcon=new ImageIcon(ImageLoader.getImage("images/mnetwork.gif"));
	
 ImageIcon MessagePassingIcon=new ImageIcon(ImageLoader.getImage("images/miu.gif"));
	
 ImageIcon SubProtocolIcon=new ImageIcon(ImageLoader.getImage("images/prot.png"));
	
 ImageIcon RuntimeConversationIcon=new ImageIcon(ImageLoader.getImage("images/mconv.png"));
	
 ImageIcon AUMLComponentIcon=new ImageIcon(ImageLoader.getImage("images/magent.gif"));
	
 ImageIcon OrganizationIcon=new ImageIcon(ImageLoader.getImage("images/morg.gif"));
	
 ImageIcon ConditionalMentalStateIcon=new ImageIcon(ImageLoader.getImage("images/mcmstate.gif"));
	
 ImageIcon ApplicationWSIcon=new ImageIcon(ImageLoader.getImage("images/mapp.gif"));
	
 ImageIcon AUMLSpecificationIcon=new ImageIcon(ImageLoader.getImage("images/maumlspec.gif"));
	
 ImageIcon ApplicationIcon=new ImageIcon(ImageLoader.getImage("images/mapp.gif"));
	
 ImageIcon DeploymentPackageIcon=new ImageIcon(ImageLoader.getImage("images/mdepl.gif"));
	
 ImageIcon PlanIcon=new ImageIcon(ImageLoader.getImage("images/mplan.gif"));
	
 ImageIcon AUMLPortIcon=new ImageIcon(ImageLoader.getImage("images/magent.gif"));
	
 ImageIcon INGENIASComponentIcon=new ImageIcon(ImageLoader.getImage("images/micomponent.gif"));
	
 ImageIcon OrganizationGroupIcon=new ImageIcon(ImageLoader.getImage("images/mgroup.gif"));
	
 ImageIcon IUConcurrenceIcon=new ImageIcon(ImageLoader.getImage("images/mconc.gif"));
	
 ImageIcon DeploymentUnitByTypeIcon=new ImageIcon(ImageLoader.getImage("images/mtypedepl.gif"));
	
 ImageIcon IUIterateIcon=new ImageIcon(ImageLoader.getImage("images/mit.gif"));
	
 ImageIcon AUMLAlternativeRowIcon=new ImageIcon(ImageLoader.getImage("images/altr.png"));
	
 ImageIcon ColumnIcon=new ImageIcon(ImageLoader.getImage("images/col.png"));
	
 ImageIcon AutonomousEntityQueryIcon=new ImageIcon(ImageLoader.getImage("images/mquery.gif"));
	
 ImageIcon AUMLContainerIcon=new ImageIcon(ImageLoader.getImage("images/magent.gif"));
	
 ImageIcon ConcreteAgentIcon=new ImageIcon(ImageLoader.getImage("images/mcaquery.gif"));
	
 ImageIcon UMLCommentIcon=new ImageIcon(ImageLoader.getImage("images/mumlcomment.gif"));
	
 ImageIcon AgentRequirementsQueryIcon=new ImageIcon(ImageLoader.getImage("images/mrquery.gif"));
	
 ImageIcon UMLSpecificationIcon=new ImageIcon(ImageLoader.getImage("images/mumlspec.gif"));
	
 ImageIcon BelieveIcon=new ImageIcon(ImageLoader.getImage("images/mbel.gif"));
	
 ImageIcon WorkflowBoxIcon=new ImageIcon(ImageLoader.getImage("images/mworkflow.gif"));
	
 ImageIcon DeploymentUnitByTypeEnumInitMSIcon=new ImageIcon(ImageLoader.getImage("images/mimtypedepl.gif"));
	
 ImageIcon GeneralEventIcon=new ImageIcon(ImageLoader.getImage("images/mevent.gif"));
	
 ImageIcon CompromiseIcon=new ImageIcon(ImageLoader.getImage("images/mcomp.gif"));
	
 ImageIcon RoleIcon=new ImageIcon(ImageLoader.getImage("images/mrole.gif"));
	
 ImageIcon EnvironmentApplicationIcon=new ImageIcon(ImageLoader.getImage("images/mappe.gif"));
	
 ImageIcon ApplicationEventSlotsIcon=new ImageIcon(ImageLoader.getImage("images/meventas.gif"));
	
 ImageIcon ProtocolIcon=new ImageIcon(ImageLoader.getImage("images/prot.png"));
	
 ImageIcon MentalInstanceSpecificationIcon=new ImageIcon(ImageLoader.getImage("images/miusecase.gif"));
	
 ImageIcon ActionUMLIcon=new ImageIcon(ImageLoader.getImage("images/miusecase.gif"));
	
 ImageIcon InteractionUnitIcon=new ImageIcon(ImageLoader.getImage("images/miu.gif"));
	
 ImageIcon GRASIASpecificationIcon=new ImageIcon(ImageLoader.getImage("images/mgspec.gif"));
	
 ImageIcon MentalStateProcessorIcon=new ImageIcon(ImageLoader.getImage("images/mproc.gif"));
	
 ImageIcon MergeNodeIcon=new ImageIcon(ImageLoader.getImage("images/mdecisionnode.gif"));
	
 ImageIcon EndNodeIcon=new ImageIcon(ImageLoader.getImage("images/mendnode.gif"));
	
 ImageIcon FrameFactIcon=new ImageIcon(ImageLoader.getImage("images/mffact.gif"));
	
 ImageIcon TestIcon=new ImageIcon(ImageLoader.getImage("images/mdepl.gif"));
	
 ImageIcon LifelineIcon=new ImageIcon(ImageLoader.getImage("images/col.png"));
	
 ImageIcon InteractionIcon=new ImageIcon(ImageLoader.getImage("images/minter.gif"));
	
 ImageIcon AgentIcon=new ImageIcon(ImageLoader.getImage("images/magent.gif"));
	
 ImageIcon MentalStateManagerIcon=new ImageIcon(ImageLoader.getImage("images/mman.gif"));
	
 ImageIcon INGENIASCodeComponentIcon=new ImageIcon(ImageLoader.getImage("images/miccomponent.gif"));
	
 ImageIcon TaskWSIcon=new ImageIcon(ImageLoader.getImage("images/mtaskws.gif"));
	
 ImageIcon GoalStateWSIcon=new ImageIcon(ImageLoader.getImage("images/mgoalstatews.png"));
	
 ImageIcon StateGoalIcon=new ImageIcon(ImageLoader.getImage("images/msgoal.gif"));
	
 ImageIcon ConversationIcon=new ImageIcon(ImageLoader.getImage("images/mconv.png"));
	
 ImageIcon ApplicationEventIcon=new ImageIcon(ImageLoader.getImage("images/meventa.gif"));
	
 ImageIcon TaskIcon=new ImageIcon(ImageLoader.getImage("images/mtask.gif"));
	
 ImageIcon DeploymentUnitByTypeMSEntityIcon=new ImageIcon(ImageLoader.getImage("images/mimtypedepl.gif"));
	
 ImageIcon RoleWSIcon=new ImageIcon(ImageLoader.getImage("images/mrolews.gif"));
	
 ImageIcon AUMLAlternativeBoxIcon=new ImageIcon(ImageLoader.getImage("images/altb.png"));
	
 ImageIcon WorkflowIcon=new ImageIcon(ImageLoader.getImage("images/mworkflow.gif"));
	

  ImageIcon paquete;

  public ProjectTreeRenderer() {

  super();

  }


    public Component getTreeCellRendererComponent(
                        JTree tree,
                        Object value,
                        boolean sel,
                        boolean expanded,
                        boolean leaf,
                        int row,
                        boolean hasFocus) {

        super.getTreeCellRendererComponent(
                        tree, value, sel,
                        expanded, leaf, row,
                        hasFocus);
        if (leaf){
            ImageIcon img=this.selectIcon(value);
            if (img!=null) {
             setIcon(img);
             return this;
            } else
             return super.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);
        } else {
            return super.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);
        }
    }

    protected ImageIcon selectIcon(Object value) {
        DefaultMutableTreeNode node =
            (DefaultMutableTreeNode)value;


	
        if (node.getUserObject().getClass().equals(DecisionNode.class))
          return DecisionNodeIcon;
	
        if (node.getUserObject().getClass().equals(TestingPackage.class))
          return TestingPackageIcon;
	
        if (node.getUserObject().getClass().equals(MentalState.class))
          return MentalStateIcon;
	
        if (node.getUserObject().getClass().equals(InternalApplication.class))
          return InternalApplicationIcon;
	
        if (node.getUserObject().getClass().equals(Goal.class))
          return GoalIcon;
	
        if (node.getUserObject().getClass().equals(AgentWS.class))
          return AgentWSIcon;
	
        if (node.getUserObject().getClass().equals(Fact.class))
          return FactIcon;
	
        if (node.getUserObject().getClass().equals(ShareTouple.class))
          return ShareToupleIcon;
	
        if (node.getUserObject().getClass().equals(DeploymentUnitByTypeWithInitMS.class))
          return DeploymentUnitByTypeWithInitMSIcon;
	
        if (node.getUserObject().getClass().equals(JoinNode.class))
          return JoinNodeIcon;
	
        if (node.getUserObject().getClass().equals(RuntimeEvent.class))
          return RuntimeEventIcon;
	
        if (node.getUserObject().getClass().equals(InitialNode.class))
          return InitialNodeIcon;
	
        if (node.getUserObject().getClass().equals(TextUseCase.class))
          return TextUseCaseIcon;
	
        if (node.getUserObject().getClass().equals(RemoteProcedureCall.class))
          return RemoteProcedureCallIcon;
	
        if (node.getUserObject().getClass().equals(Resource.class))
          return ResourceIcon;
	
        if (node.getUserObject().getClass().equals(AgentModelBelieve.class))
          return AgentModelBelieveIcon;
	
        if (node.getUserObject().getClass().equals(ActivityFinal.class))
          return ActivityFinalIcon;
	
        if (node.getUserObject().getClass().equals(INGENIASUseCase.class))
          return INGENIASUseCaseIcon;
	
        if (node.getUserObject().getClass().equals(TextNote.class))
          return TextNoteIcon;
	
        if (node.getUserObject().getClass().equals(RuntimeFact.class))
          return RuntimeFactIcon;
	
        if (node.getUserObject().getClass().equals(ForkNode.class))
          return ForkNodeIcon;
	
        if (node.getUserObject().getClass().equals(OrganizationNetwork.class))
          return OrganizationNetworkIcon;
	
        if (node.getUserObject().getClass().equals(MessagePassing.class))
          return MessagePassingIcon;
	
        if (node.getUserObject().getClass().equals(SubProtocol.class))
          return SubProtocolIcon;
	
        if (node.getUserObject().getClass().equals(RuntimeConversation.class))
          return RuntimeConversationIcon;
	
        if (node.getUserObject().getClass().equals(AUMLComponent.class))
          return AUMLComponentIcon;
	
        if (node.getUserObject().getClass().equals(Organization.class))
          return OrganizationIcon;
	
        if (node.getUserObject().getClass().equals(ConditionalMentalState.class))
          return ConditionalMentalStateIcon;
	
        if (node.getUserObject().getClass().equals(ApplicationWS.class))
          return ApplicationWSIcon;
	
        if (node.getUserObject().getClass().equals(AUMLSpecification.class))
          return AUMLSpecificationIcon;
	
        if (node.getUserObject().getClass().equals(Application.class))
          return ApplicationIcon;
	
        if (node.getUserObject().getClass().equals(DeploymentPackage.class))
          return DeploymentPackageIcon;
	
        if (node.getUserObject().getClass().equals(Plan.class))
          return PlanIcon;
	
        if (node.getUserObject().getClass().equals(AUMLPort.class))
          return AUMLPortIcon;
	
        if (node.getUserObject().getClass().equals(INGENIASComponent.class))
          return INGENIASComponentIcon;
	
        if (node.getUserObject().getClass().equals(OrganizationGroup.class))
          return OrganizationGroupIcon;
	
        if (node.getUserObject().getClass().equals(IUConcurrence.class))
          return IUConcurrenceIcon;
	
        if (node.getUserObject().getClass().equals(DeploymentUnitByType.class))
          return DeploymentUnitByTypeIcon;
	
        if (node.getUserObject().getClass().equals(IUIterate.class))
          return IUIterateIcon;
	
        if (node.getUserObject().getClass().equals(AUMLAlternativeRow.class))
          return AUMLAlternativeRowIcon;
	
        if (node.getUserObject().getClass().equals(Column.class))
          return ColumnIcon;
	
        if (node.getUserObject().getClass().equals(AutonomousEntityQuery.class))
          return AutonomousEntityQueryIcon;
	
        if (node.getUserObject().getClass().equals(AUMLContainer.class))
          return AUMLContainerIcon;
	
        if (node.getUserObject().getClass().equals(ConcreteAgent.class))
          return ConcreteAgentIcon;
	
        if (node.getUserObject().getClass().equals(UMLComment.class))
          return UMLCommentIcon;
	
        if (node.getUserObject().getClass().equals(AgentRequirementsQuery.class))
          return AgentRequirementsQueryIcon;
	
        if (node.getUserObject().getClass().equals(UMLSpecification.class))
          return UMLSpecificationIcon;
	
        if (node.getUserObject().getClass().equals(Believe.class))
          return BelieveIcon;
	
        if (node.getUserObject().getClass().equals(WorkflowBox.class))
          return WorkflowBoxIcon;
	
        if (node.getUserObject().getClass().equals(DeploymentUnitByTypeEnumInitMS.class))
          return DeploymentUnitByTypeEnumInitMSIcon;
	
        if (node.getUserObject().getClass().equals(GeneralEvent.class))
          return GeneralEventIcon;
	
        if (node.getUserObject().getClass().equals(Compromise.class))
          return CompromiseIcon;
	
        if (node.getUserObject().getClass().equals(Role.class))
          return RoleIcon;
	
        if (node.getUserObject().getClass().equals(EnvironmentApplication.class))
          return EnvironmentApplicationIcon;
	
        if (node.getUserObject().getClass().equals(ApplicationEventSlots.class))
          return ApplicationEventSlotsIcon;
	
        if (node.getUserObject().getClass().equals(Protocol.class))
          return ProtocolIcon;
	
        if (node.getUserObject().getClass().equals(MentalInstanceSpecification.class))
          return MentalInstanceSpecificationIcon;
	
        if (node.getUserObject().getClass().equals(ActionUML.class))
          return ActionUMLIcon;
	
        if (node.getUserObject().getClass().equals(InteractionUnit.class))
          return InteractionUnitIcon;
	
        if (node.getUserObject().getClass().equals(GRASIASpecification.class))
          return GRASIASpecificationIcon;
	
        if (node.getUserObject().getClass().equals(MentalStateProcessor.class))
          return MentalStateProcessorIcon;
	
        if (node.getUserObject().getClass().equals(MergeNode.class))
          return MergeNodeIcon;
	
        if (node.getUserObject().getClass().equals(EndNode.class))
          return EndNodeIcon;
	
        if (node.getUserObject().getClass().equals(FrameFact.class))
          return FrameFactIcon;
	
        if (node.getUserObject().getClass().equals(Test.class))
          return TestIcon;
	
        if (node.getUserObject().getClass().equals(Lifeline.class))
          return LifelineIcon;
	
        if (node.getUserObject().getClass().equals(Interaction.class))
          return InteractionIcon;
	
        if (node.getUserObject().getClass().equals(Agent.class))
          return AgentIcon;
	
        if (node.getUserObject().getClass().equals(MentalStateManager.class))
          return MentalStateManagerIcon;
	
        if (node.getUserObject().getClass().equals(INGENIASCodeComponent.class))
          return INGENIASCodeComponentIcon;
	
        if (node.getUserObject().getClass().equals(TaskWS.class))
          return TaskWSIcon;
	
        if (node.getUserObject().getClass().equals(GoalStateWS.class))
          return GoalStateWSIcon;
	
        if (node.getUserObject().getClass().equals(StateGoal.class))
          return StateGoalIcon;
	
        if (node.getUserObject().getClass().equals(Conversation.class))
          return ConversationIcon;
	
        if (node.getUserObject().getClass().equals(ApplicationEvent.class))
          return ApplicationEventIcon;
	
        if (node.getUserObject().getClass().equals(Task.class))
          return TaskIcon;
	
        if (node.getUserObject().getClass().equals(DeploymentUnitByTypeMSEntity.class))
          return DeploymentUnitByTypeMSEntityIcon;
	
        if (node.getUserObject().getClass().equals(RoleWS.class))
          return RoleWSIcon;
	
        if (node.getUserObject().getClass().equals(AUMLAlternativeBox.class))
          return AUMLAlternativeBoxIcon;
	
        if (node.getUserObject().getClass().equals(Workflow.class))
          return WorkflowIcon;
	


        if (node.getUserObject().getClass().equals(ingenias.editor.EnvironmentModelModelJGraph.class))
            return this.modeloEnvironmentModel;

        if (node.getUserObject().getClass().equals(ingenias.editor.ComponentDiagramModelJGraph.class))
            return this.modeloComponentDiagram;

        if (node.getUserObject().getClass().equals(ingenias.editor.OrganizationModelModelJGraph.class))
            return this.modeloOrganizationModel;

        if (node.getUserObject().getClass().equals(ingenias.editor.TasksAndGoalsModelModelJGraph.class))
            return this.modeloTasksAndGoalsModel;

        if (node.getUserObject().getClass().equals(ingenias.editor.InteractionModelModelJGraph.class))
            return this.modeloInteractionModel;

        if (node.getUserObject().getClass().equals(ingenias.editor.ActivityDiagramModelJGraph.class))
            return this.modeloActivityDiagram;

        if (node.getUserObject().getClass().equals(ingenias.editor.AgentModelModelJGraph.class))
            return this.modeloAgentModel;

        if (node.getUserObject().getClass().equals(ingenias.editor.UseCaseDiagramModelJGraph.class))
            return this.modeloUseCaseDiagram;

        if (node.getUserObject().getClass().equals(ingenias.editor.AUMLInteractionDiagramModelJGraph.class))
            return this.modeloAUMLInteractionDiagram;

        if (node.getUserObject().getClass().equals(ingenias.editor.DeployDiagramModelJGraph.class))
            return this.modeloDeployDiagram;





        return null;
      }

}


