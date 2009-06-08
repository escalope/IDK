


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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import org.jgraph.JGraph;
import org.jgraph.graph.*;
import javax.swing.tree.*;
import javax.swing.JTree;
import javax.swing.SwingUtilities;

import ingenias.editor.entities.*;



interface Filter { 
	public boolean pass(DefaultMutableTreeNode obj);
	public void setFilteredOff(boolean pass);
	public boolean isFiltered();
}
class MyFilter implements Filter {
	boolean pass=true;
	public boolean pass(DefaultMutableTreeNode obj) { 
		if (pass) return true;
		//System.err.println(obj.getUserObject().getClass().getName());
		///boolean oldPass=pass; // to enable the use of getlastleaf isleaf
		//pass=true;
		Enumeration dfe=obj.depthFirstEnumeration();		
		
		boolean someEntity=false;	
		while (dfe.hasMoreElements() && !someEntity){
			DefaultMutableTreeNode next = (DefaultMutableTreeNode) dfe.nextElement();			
			someEntity=next.isLeaf() && !(next.getUserObject() instanceof java.lang.String);
		}
		boolean shouldPass= someEntity;
		//pass=oldPass;
		return shouldPass;
	}

	public void setFilteredOff(boolean pass) { this.pass=pass; }
	public boolean isFiltered() { return pass; }
}

class FilterTreeModel extends DefaultTreeModel {
	Filter filter;
	
	public FilterTreeModel(TreeNode root, Filter filter) {
		super(root);
		this.filter =filter;
	}
	
	public void setFilteredOff(boolean pass) {
		filter.setFilteredOff(pass);
		Object[] path = {root};
		int[] childIndices  = new int[root.getChildCount()];      
		Object[] children  = new Object[root.getChildCount()];
		for (int i = 0; i < root.getChildCount(); i++) {
			childIndices[i] = i;
			children[i] = root.getChildAt(i);
		}
		fireTreeStructureChanged(this,path,childIndices, children);
	}
	
	public int getChildCount(Object parent) {

		int realCount = super.getChildCount(parent), filterCount=0;;
		for (int i=0; i<realCount; i++) {
			DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)super.getChild(parent,i);
			if (filter.pass(dmtn)) filterCount++;
		}
		return filterCount;
	}
	public Object getChild(Object parent, int index) {

		int cnt=-1;
		for (int i=0; i<super.getChildCount(parent); i++) {
			Object child = super.getChild(parent,i);
			if (filter.pass((DefaultMutableTreeNode) child)) cnt++;
			if (cnt==index) return child;
		}
		return null;
	}
}

public class ObjectManager extends javax.swing.tree.DefaultMutableTreeNode implements java.io.Serializable {



	public  JTree arbolObjetos=null;

	javax.swing.tree.DefaultMutableTreeNode root=new javax.swing.tree.DefaultMutableTreeNode("SystemObjects");




	public javax.swing.tree.DefaultMutableTreeNode DecisionNodeNode=null;

	public javax.swing.tree.DefaultMutableTreeNode FileSpecPatternMappingNode=null;

	public javax.swing.tree.DefaultMutableTreeNode TestingPackageNode=null;

	public javax.swing.tree.DefaultMutableTreeNode MethodParameterNode=null;

	public javax.swing.tree.DefaultMutableTreeNode MentalStateNode=null;

	public javax.swing.tree.DefaultMutableTreeNode InternalApplicationNode=null;

	public javax.swing.tree.DefaultMutableTreeNode FileSpecMappingNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ParameterNode=null;

	public javax.swing.tree.DefaultMutableTreeNode GoalNode=null;

	public javax.swing.tree.DefaultMutableTreeNode AgentWSNode=null;

	public javax.swing.tree.DefaultMutableTreeNode FactNode=null;

	public javax.swing.tree.DefaultMutableTreeNode AOPMentalStatePatternNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ShareToupleNode=null;

	public javax.swing.tree.DefaultMutableTreeNode DeploymentUnitByTypeWithInitMSNode=null;

	public javax.swing.tree.DefaultMutableTreeNode JoinNodeNode=null;

	public javax.swing.tree.DefaultMutableTreeNode SlotNode=null;

	public javax.swing.tree.DefaultMutableTreeNode RuntimeEventNode=null;

	public javax.swing.tree.DefaultMutableTreeNode InitialNodeNode=null;

	public javax.swing.tree.DefaultMutableTreeNode MethodNode=null;

	public javax.swing.tree.DefaultMutableTreeNode TextUseCaseNode=null;

	public javax.swing.tree.DefaultMutableTreeNode RemoteProcedureCallNode=null;

	public javax.swing.tree.DefaultMutableTreeNode MentalEntityNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ResourceNode=null;

	public javax.swing.tree.DefaultMutableTreeNode AgentModelBelieveNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ActivityFinalNode=null;

	public javax.swing.tree.DefaultMutableTreeNode INGENIASUseCaseNode=null;

	public javax.swing.tree.DefaultMutableTreeNode GRASIAAgentDescriptionNode=null;

	public javax.swing.tree.DefaultMutableTreeNode TextNoteNode=null;

	public javax.swing.tree.DefaultMutableTreeNode RuntimeFactNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ForkNodeNode=null;

	public javax.swing.tree.DefaultMutableTreeNode OrganizationNetworkNode=null;

	public javax.swing.tree.DefaultMutableTreeNode UseCaseNode=null;

	public javax.swing.tree.DefaultMutableTreeNode MessagePassingNode=null;

	public javax.swing.tree.DefaultMutableTreeNode SubProtocolNode=null;

	public javax.swing.tree.DefaultMutableTreeNode RuntimeConversationNode=null;

	public javax.swing.tree.DefaultMutableTreeNode SlotValueSpecificationNode=null;

	public javax.swing.tree.DefaultMutableTreeNode INGENIASObjectNode=null;

	public javax.swing.tree.DefaultMutableTreeNode AUMLComponentNode=null;

	public javax.swing.tree.DefaultMutableTreeNode NaturalLanguageAgentDescriptionNode=null;

	public javax.swing.tree.DefaultMutableTreeNode OrganizationNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ConditionalMentalStateNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ApplicationWSNode=null;

	public javax.swing.tree.DefaultMutableTreeNode AUMLSpecificationNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ApplicationNode=null;

	public javax.swing.tree.DefaultMutableTreeNode DeploymentPackageNode=null;

	public javax.swing.tree.DefaultMutableTreeNode PlanNode=null;

	public javax.swing.tree.DefaultMutableTreeNode UMLComponentNode=null;

	public javax.swing.tree.DefaultMutableTreeNode AUMLPortNode=null;

	public javax.swing.tree.DefaultMutableTreeNode INGENIASComponentNode=null;

	public javax.swing.tree.DefaultMutableTreeNode OrganizationGroupNode=null;

	public javax.swing.tree.DefaultMutableTreeNode IUConcurrenceNode=null;

	public javax.swing.tree.DefaultMutableTreeNode DeploymentUnitByTypeNode=null;

	public javax.swing.tree.DefaultMutableTreeNode IUIterateNode=null;

	public javax.swing.tree.DefaultMutableTreeNode InformationMentalEntityNode=null;

	public javax.swing.tree.DefaultMutableTreeNode AUMLAlternativeRowNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ColumnNode=null;

	public javax.swing.tree.DefaultMutableTreeNode AutonomousEntityQueryNode=null;

	public javax.swing.tree.DefaultMutableTreeNode AUMLContainerNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ConcreteAgentNode=null;

	public javax.swing.tree.DefaultMutableTreeNode UMLCommentNode=null;

	public javax.swing.tree.DefaultMutableTreeNode AgentRequirementsQueryNode=null;

	public javax.swing.tree.DefaultMutableTreeNode UMLSpecificationNode=null;

	public javax.swing.tree.DefaultMutableTreeNode BelieveNode=null;

	public javax.swing.tree.DefaultMutableTreeNode WorkflowBoxNode=null;

	public javax.swing.tree.DefaultMutableTreeNode AgentComponentNode=null;

	public javax.swing.tree.DefaultMutableTreeNode GRASIAMentalStatePatternNode=null;

	public javax.swing.tree.DefaultMutableTreeNode DeploymentUnitByTypeEnumInitMSNode=null;

	public javax.swing.tree.DefaultMutableTreeNode Autonomous_entityNode=null;

	public javax.swing.tree.DefaultMutableTreeNode GeneralEventNode=null;

	public javax.swing.tree.DefaultMutableTreeNode CompromiseNode=null;

	public javax.swing.tree.DefaultMutableTreeNode RoleNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ControlMentalEntityNode=null;

	public javax.swing.tree.DefaultMutableTreeNode EnvironmentApplicationNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ApplicationEventSlotsNode=null;

	public javax.swing.tree.DefaultMutableTreeNode AgentDescriptionNode=null;

	public javax.swing.tree.DefaultMutableTreeNode MentalStatePatternNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ProtocolNode=null;

	public javax.swing.tree.DefaultMutableTreeNode MentalInstanceSpecificationNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ActionUMLNode=null;

	public javax.swing.tree.DefaultMutableTreeNode StackEntryNode=null;

	public javax.swing.tree.DefaultMutableTreeNode InteractionUnitNode=null;

	public javax.swing.tree.DefaultMutableTreeNode GRASIASpecificationNode=null;

	public javax.swing.tree.DefaultMutableTreeNode UMLClassNode=null;

	public javax.swing.tree.DefaultMutableTreeNode MentalStateProcessorNode=null;

	public javax.swing.tree.DefaultMutableTreeNode MergeNodeNode=null;

	public javax.swing.tree.DefaultMutableTreeNode EndNodeNode=null;

	public javax.swing.tree.DefaultMutableTreeNode FrameFactNode=null;

	public javax.swing.tree.DefaultMutableTreeNode TestNode=null;

	public javax.swing.tree.DefaultMutableTreeNode LifelineNode=null;

	public javax.swing.tree.DefaultMutableTreeNode SymbolicMentalStatePatternNode=null;

	public javax.swing.tree.DefaultMutableTreeNode PROLOGAgentDescriptionNode=null;

	public javax.swing.tree.DefaultMutableTreeNode InteractionNode=null;

	public javax.swing.tree.DefaultMutableTreeNode SpecificationNode=null;

	public javax.swing.tree.DefaultMutableTreeNode AgentNode=null;

	public javax.swing.tree.DefaultMutableTreeNode MentalStateManagerNode=null;

	public javax.swing.tree.DefaultMutableTreeNode INGENIASCodeComponentNode=null;

	public javax.swing.tree.DefaultMutableTreeNode UMLClassifierNode=null;

	public javax.swing.tree.DefaultMutableTreeNode CommunicationEventNode=null;

	public javax.swing.tree.DefaultMutableTreeNode TaskWSNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ObjectSlotNode=null;

	public javax.swing.tree.DefaultMutableTreeNode GoalStateWSNode=null;

	public javax.swing.tree.DefaultMutableTreeNode StateGoalNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ConversationNode=null;

	public javax.swing.tree.DefaultMutableTreeNode ApplicationEventNode=null;

	public javax.swing.tree.DefaultMutableTreeNode TaskNode=null;

	public javax.swing.tree.DefaultMutableTreeNode DeploymentUnitByTypeMSEntityNode=null;

	public javax.swing.tree.DefaultMutableTreeNode RoleWSNode=null;

	public javax.swing.tree.DefaultMutableTreeNode AUMLAlternativeBoxNode=null;

	public javax.swing.tree.DefaultMutableTreeNode WorkflowNode=null;

	public javax.swing.tree.DefaultMutableTreeNode UMLObjectNode=null;



	public static ObjectManager initialise(javax.swing.tree.DefaultMutableTreeNode root,JTree arbolObjetos){
		ObjectManager om=new ObjectManager(root,arbolObjetos);
		return om;
	}

	public void setFilteredOff(boolean filterOn){
		((FilterTreeModel)this.arbolObjetos.getModel()).setFilteredOff(filterOn);
		reload();
	}

	private ObjectManager(javax.swing.tree.DefaultMutableTreeNode root,JTree arbolObjetos) {
		super("System Objects");
		this.root=root;
		this.arbolObjetos=arbolObjetos;
		MyFilter mf = new MyFilter();

		this.arbolObjetos.setModel(new FilterTreeModel((TreeNode) arbolObjetos.getModel().getRoot(),
				mf));
		System.err.println("filtering ............");

		DecisionNodeNode=new javax.swing.tree.DefaultMutableTreeNode("DecisionNode");

		FileSpecPatternMappingNode=new javax.swing.tree.DefaultMutableTreeNode("FileSpecPatternMapping");

		TestingPackageNode=new javax.swing.tree.DefaultMutableTreeNode("TestingPackage");

		MethodParameterNode=new javax.swing.tree.DefaultMutableTreeNode("MethodParameter");

		MentalStateNode=new javax.swing.tree.DefaultMutableTreeNode("MentalState");

		InternalApplicationNode=new javax.swing.tree.DefaultMutableTreeNode("InternalApplication");

		FileSpecMappingNode=new javax.swing.tree.DefaultMutableTreeNode("FileSpecMapping");

		ParameterNode=new javax.swing.tree.DefaultMutableTreeNode("Parameter");

		GoalNode=new javax.swing.tree.DefaultMutableTreeNode("Goal");

		AgentWSNode=new javax.swing.tree.DefaultMutableTreeNode("AgentWS");

		FactNode=new javax.swing.tree.DefaultMutableTreeNode("Fact");

		AOPMentalStatePatternNode=new javax.swing.tree.DefaultMutableTreeNode("AOPMentalStatePattern");

		ShareToupleNode=new javax.swing.tree.DefaultMutableTreeNode("ShareTouple");

		DeploymentUnitByTypeWithInitMSNode=new javax.swing.tree.DefaultMutableTreeNode("DeploymentUnitByTypeWithInitMS");

		JoinNodeNode=new javax.swing.tree.DefaultMutableTreeNode("JoinNode");

		SlotNode=new javax.swing.tree.DefaultMutableTreeNode("Slot");

		RuntimeEventNode=new javax.swing.tree.DefaultMutableTreeNode("RuntimeEvent");

		InitialNodeNode=new javax.swing.tree.DefaultMutableTreeNode("InitialNode");

		MethodNode=new javax.swing.tree.DefaultMutableTreeNode("Method");

		TextUseCaseNode=new javax.swing.tree.DefaultMutableTreeNode("TextUseCase");

		RemoteProcedureCallNode=new javax.swing.tree.DefaultMutableTreeNode("RemoteProcedureCall");

		MentalEntityNode=new javax.swing.tree.DefaultMutableTreeNode("MentalEntity");

		ResourceNode=new javax.swing.tree.DefaultMutableTreeNode("Resource");

		AgentModelBelieveNode=new javax.swing.tree.DefaultMutableTreeNode("AgentModelBelieve");

		ActivityFinalNode=new javax.swing.tree.DefaultMutableTreeNode("ActivityFinal");

		INGENIASUseCaseNode=new javax.swing.tree.DefaultMutableTreeNode("INGENIASUseCase");

		GRASIAAgentDescriptionNode=new javax.swing.tree.DefaultMutableTreeNode("GRASIAAgentDescription");

		TextNoteNode=new javax.swing.tree.DefaultMutableTreeNode("TextNote");

		RuntimeFactNode=new javax.swing.tree.DefaultMutableTreeNode("RuntimeFact");

		ForkNodeNode=new javax.swing.tree.DefaultMutableTreeNode("ForkNode");

		OrganizationNetworkNode=new javax.swing.tree.DefaultMutableTreeNode("OrganizationNetwork");

		UseCaseNode=new javax.swing.tree.DefaultMutableTreeNode("UseCase");

		MessagePassingNode=new javax.swing.tree.DefaultMutableTreeNode("MessagePassing");

		SubProtocolNode=new javax.swing.tree.DefaultMutableTreeNode("SubProtocol");

		RuntimeConversationNode=new javax.swing.tree.DefaultMutableTreeNode("RuntimeConversation");

		SlotValueSpecificationNode=new javax.swing.tree.DefaultMutableTreeNode("SlotValueSpecification");

		INGENIASObjectNode=new javax.swing.tree.DefaultMutableTreeNode("INGENIASObject");

		AUMLComponentNode=new javax.swing.tree.DefaultMutableTreeNode("AUMLComponent");

		NaturalLanguageAgentDescriptionNode=new javax.swing.tree.DefaultMutableTreeNode("NaturalLanguageAgentDescription");

		OrganizationNode=new javax.swing.tree.DefaultMutableTreeNode("Organization");

		ConditionalMentalStateNode=new javax.swing.tree.DefaultMutableTreeNode("ConditionalMentalState");

		ApplicationWSNode=new javax.swing.tree.DefaultMutableTreeNode("ApplicationWS");

		AUMLSpecificationNode=new javax.swing.tree.DefaultMutableTreeNode("AUMLSpecification");

		ApplicationNode=new javax.swing.tree.DefaultMutableTreeNode("Application");

		DeploymentPackageNode=new javax.swing.tree.DefaultMutableTreeNode("DeploymentPackage");

		PlanNode=new javax.swing.tree.DefaultMutableTreeNode("Plan");

		UMLComponentNode=new javax.swing.tree.DefaultMutableTreeNode("UMLComponent");

		AUMLPortNode=new javax.swing.tree.DefaultMutableTreeNode("AUMLPort");

		INGENIASComponentNode=new javax.swing.tree.DefaultMutableTreeNode("INGENIASComponent");

		OrganizationGroupNode=new javax.swing.tree.DefaultMutableTreeNode("OrganizationGroup");

		IUConcurrenceNode=new javax.swing.tree.DefaultMutableTreeNode("IUConcurrence");

		DeploymentUnitByTypeNode=new javax.swing.tree.DefaultMutableTreeNode("DeploymentUnitByType");

		IUIterateNode=new javax.swing.tree.DefaultMutableTreeNode("IUIterate");

		InformationMentalEntityNode=new javax.swing.tree.DefaultMutableTreeNode("InformationMentalEntity");

		AUMLAlternativeRowNode=new javax.swing.tree.DefaultMutableTreeNode("AUMLAlternativeRow");

		ColumnNode=new javax.swing.tree.DefaultMutableTreeNode("Column");

		AutonomousEntityQueryNode=new javax.swing.tree.DefaultMutableTreeNode("AutonomousEntityQuery");

		AUMLContainerNode=new javax.swing.tree.DefaultMutableTreeNode("AUMLContainer");

		ConcreteAgentNode=new javax.swing.tree.DefaultMutableTreeNode("ConcreteAgent");

		UMLCommentNode=new javax.swing.tree.DefaultMutableTreeNode("UMLComment");

		AgentRequirementsQueryNode=new javax.swing.tree.DefaultMutableTreeNode("AgentRequirementsQuery");

		UMLSpecificationNode=new javax.swing.tree.DefaultMutableTreeNode("UMLSpecification");

		BelieveNode=new javax.swing.tree.DefaultMutableTreeNode("Believe");

		WorkflowBoxNode=new javax.swing.tree.DefaultMutableTreeNode("WorkflowBox");

		AgentComponentNode=new javax.swing.tree.DefaultMutableTreeNode("AgentComponent");

		GRASIAMentalStatePatternNode=new javax.swing.tree.DefaultMutableTreeNode("GRASIAMentalStatePattern");

		DeploymentUnitByTypeEnumInitMSNode=new javax.swing.tree.DefaultMutableTreeNode("DeploymentUnitByTypeEnumInitMS");

		Autonomous_entityNode=new javax.swing.tree.DefaultMutableTreeNode("Autonomous_entity");

		GeneralEventNode=new javax.swing.tree.DefaultMutableTreeNode("GeneralEvent");

		CompromiseNode=new javax.swing.tree.DefaultMutableTreeNode("Compromise");

		RoleNode=new javax.swing.tree.DefaultMutableTreeNode("Role");

		ControlMentalEntityNode=new javax.swing.tree.DefaultMutableTreeNode("ControlMentalEntity");

		EnvironmentApplicationNode=new javax.swing.tree.DefaultMutableTreeNode("EnvironmentApplication");

		ApplicationEventSlotsNode=new javax.swing.tree.DefaultMutableTreeNode("ApplicationEventSlots");

		AgentDescriptionNode=new javax.swing.tree.DefaultMutableTreeNode("AgentDescription");

		MentalStatePatternNode=new javax.swing.tree.DefaultMutableTreeNode("MentalStatePattern");

		ProtocolNode=new javax.swing.tree.DefaultMutableTreeNode("Protocol");

		MentalInstanceSpecificationNode=new javax.swing.tree.DefaultMutableTreeNode("MentalInstanceSpecification");

		ActionUMLNode=new javax.swing.tree.DefaultMutableTreeNode("ActionUML");

		StackEntryNode=new javax.swing.tree.DefaultMutableTreeNode("StackEntry");

		InteractionUnitNode=new javax.swing.tree.DefaultMutableTreeNode("InteractionUnit");

		GRASIASpecificationNode=new javax.swing.tree.DefaultMutableTreeNode("GRASIASpecification");

		UMLClassNode=new javax.swing.tree.DefaultMutableTreeNode("UMLClass");

		MentalStateProcessorNode=new javax.swing.tree.DefaultMutableTreeNode("MentalStateProcessor");

		MergeNodeNode=new javax.swing.tree.DefaultMutableTreeNode("MergeNode");

		EndNodeNode=new javax.swing.tree.DefaultMutableTreeNode("EndNode");

		FrameFactNode=new javax.swing.tree.DefaultMutableTreeNode("FrameFact");

		TestNode=new javax.swing.tree.DefaultMutableTreeNode("Test");

		LifelineNode=new javax.swing.tree.DefaultMutableTreeNode("Lifeline");

		SymbolicMentalStatePatternNode=new javax.swing.tree.DefaultMutableTreeNode("SymbolicMentalStatePattern");

		PROLOGAgentDescriptionNode=new javax.swing.tree.DefaultMutableTreeNode("PROLOGAgentDescription");

		InteractionNode=new javax.swing.tree.DefaultMutableTreeNode("Interaction");

		SpecificationNode=new javax.swing.tree.DefaultMutableTreeNode("Specification");

		AgentNode=new javax.swing.tree.DefaultMutableTreeNode("Agent");

		MentalStateManagerNode=new javax.swing.tree.DefaultMutableTreeNode("MentalStateManager");

		INGENIASCodeComponentNode=new javax.swing.tree.DefaultMutableTreeNode("INGENIASCodeComponent");

		UMLClassifierNode=new javax.swing.tree.DefaultMutableTreeNode("UMLClassifier");

		CommunicationEventNode=new javax.swing.tree.DefaultMutableTreeNode("CommunicationEvent");

		TaskWSNode=new javax.swing.tree.DefaultMutableTreeNode("TaskWS");

		ObjectSlotNode=new javax.swing.tree.DefaultMutableTreeNode("ObjectSlot");

		GoalStateWSNode=new javax.swing.tree.DefaultMutableTreeNode("GoalStateWS");

		StateGoalNode=new javax.swing.tree.DefaultMutableTreeNode("StateGoal");

		ConversationNode=new javax.swing.tree.DefaultMutableTreeNode("Conversation");

		ApplicationEventNode=new javax.swing.tree.DefaultMutableTreeNode("ApplicationEvent");

		TaskNode=new javax.swing.tree.DefaultMutableTreeNode("Task");

		DeploymentUnitByTypeMSEntityNode=new javax.swing.tree.DefaultMutableTreeNode("DeploymentUnitByTypeMSEntity");

		RoleWSNode=new javax.swing.tree.DefaultMutableTreeNode("RoleWS");

		AUMLAlternativeBoxNode=new javax.swing.tree.DefaultMutableTreeNode("AUMLAlternativeBox");

		WorkflowNode=new javax.swing.tree.DefaultMutableTreeNode("Workflow");

		UMLObjectNode=new javax.swing.tree.DefaultMutableTreeNode("UMLObject");






		// 1st level nodes

		addNodeInSortedOrder( root,ParameterNode);

		addNodeInSortedOrder( root,INGENIASObjectNode);

		addNodeInSortedOrder( root,UMLClassifierNode);

		addNodeInSortedOrder( root,UMLObjectNode);


		// 2nd and lower nodes

		addNodeInSortedOrder( UMLObjectNode,DecisionNodeNode);

		addNodeInSortedOrder( FileSpecMappingNode,FileSpecPatternMappingNode);

		addNodeInSortedOrder( INGENIASObjectNode,TestingPackageNode);

		addNodeInSortedOrder( INGENIASObjectNode,MethodParameterNode);

		addNodeInSortedOrder( INGENIASObjectNode,MentalStateNode);

		addNodeInSortedOrder( ApplicationNode,InternalApplicationNode);

		addNodeInSortedOrder( UMLClassifierNode,FileSpecMappingNode);

		addNodeInSortedOrder( ControlMentalEntityNode,GoalNode);

		addNodeInSortedOrder( AgentNode,AgentWSNode);

		addNodeInSortedOrder( InformationMentalEntityNode,FactNode);

		addNodeInSortedOrder( SymbolicMentalStatePatternNode,AOPMentalStatePatternNode);

		addNodeInSortedOrder( InteractionUnitNode,ShareToupleNode);

		addNodeInSortedOrder( DeploymentUnitByTypeNode,DeploymentUnitByTypeWithInitMSNode);

		addNodeInSortedOrder( UMLObjectNode,JoinNodeNode);

		addNodeInSortedOrder( INGENIASObjectNode,SlotNode);

		addNodeInSortedOrder( GeneralEventNode,RuntimeEventNode);

		addNodeInSortedOrder( UMLObjectNode,InitialNodeNode);

		addNodeInSortedOrder( INGENIASObjectNode,MethodNode);

		addNodeInSortedOrder( UseCaseNode,TextUseCaseNode);

		addNodeInSortedOrder( InteractionUnitNode,RemoteProcedureCallNode);

		addNodeInSortedOrder( INGENIASObjectNode,MentalEntityNode);

		addNodeInSortedOrder( AgentComponentNode,ResourceNode);

		addNodeInSortedOrder( BelieveNode,AgentModelBelieveNode);

		addNodeInSortedOrder( UMLObjectNode,ActivityFinalNode);

		addNodeInSortedOrder( UseCaseNode,INGENIASUseCaseNode);

		addNodeInSortedOrder( AgentDescriptionNode,GRASIAAgentDescriptionNode);

		addNodeInSortedOrder( UMLObjectNode,TextNoteNode);

		addNodeInSortedOrder( FactNode,RuntimeFactNode);

		addNodeInSortedOrder( UMLObjectNode,ForkNodeNode);

		addNodeInSortedOrder( OrganizationGroupNode,OrganizationNetworkNode);

		addNodeInSortedOrder( UMLObjectNode,UseCaseNode);

		addNodeInSortedOrder( InteractionUnitNode,MessagePassingNode);

		addNodeInSortedOrder( AUMLContainerNode,SubProtocolNode);

		addNodeInSortedOrder( ConversationNode,RuntimeConversationNode);

		addNodeInSortedOrder( INGENIASObjectNode,SlotValueSpecificationNode);

		addNodeInSortedOrder( INGENIASObjectNode,AUMLComponentNode);

		addNodeInSortedOrder( AgentDescriptionNode,NaturalLanguageAgentDescriptionNode);

		addNodeInSortedOrder( Autonomous_entityNode,OrganizationNode);

		addNodeInSortedOrder( MentalStateNode,ConditionalMentalStateNode);

		addNodeInSortedOrder( ApplicationNode,ApplicationWSNode);

		addNodeInSortedOrder( SpecificationNode,AUMLSpecificationNode);

		addNodeInSortedOrder( AgentComponentNode,ApplicationNode);

		addNodeInSortedOrder( INGENIASObjectNode,DeploymentPackageNode);

		addNodeInSortedOrder( TaskNode,PlanNode);

		addNodeInSortedOrder( UMLClassifierNode,UMLComponentNode);

		addNodeInSortedOrder( AUMLComponentNode,AUMLPortNode);

		addNodeInSortedOrder( UMLComponentNode,INGENIASComponentNode);

		addNodeInSortedOrder( INGENIASObjectNode,OrganizationGroupNode);

		addNodeInSortedOrder( InteractionUnitNode,IUConcurrenceNode);

		addNodeInSortedOrder( INGENIASObjectNode,DeploymentUnitByTypeNode);

		addNodeInSortedOrder( InteractionUnitNode,IUIterateNode);

		addNodeInSortedOrder( MentalEntityNode,InformationMentalEntityNode);

		addNodeInSortedOrder( AUMLContainerNode,AUMLAlternativeRowNode);

		addNodeInSortedOrder( AUMLContainerNode,ColumnNode);

		addNodeInSortedOrder( INGENIASObjectNode,AutonomousEntityQueryNode);

		addNodeInSortedOrder( AUMLComponentNode,AUMLContainerNode);

		addNodeInSortedOrder( AutonomousEntityQueryNode,ConcreteAgentNode);

		addNodeInSortedOrder( UMLObjectNode,UMLCommentNode);

		addNodeInSortedOrder( AutonomousEntityQueryNode,AgentRequirementsQueryNode);

		addNodeInSortedOrder( SpecificationNode,UMLSpecificationNode);

		addNodeInSortedOrder( InformationMentalEntityNode,BelieveNode);

		addNodeInSortedOrder( INGENIASObjectNode,WorkflowBoxNode);

		addNodeInSortedOrder( INGENIASObjectNode,AgentComponentNode);

		addNodeInSortedOrder( SymbolicMentalStatePatternNode,GRASIAMentalStatePatternNode);

		addNodeInSortedOrder( DeploymentUnitByTypeNode,DeploymentUnitByTypeEnumInitMSNode);

		addNodeInSortedOrder( INGENIASObjectNode,Autonomous_entityNode);

		addNodeInSortedOrder( InformationMentalEntityNode,GeneralEventNode);

		addNodeInSortedOrder( ControlMentalEntityNode,CompromiseNode);

		addNodeInSortedOrder( INGENIASObjectNode,RoleNode);

		addNodeInSortedOrder( MentalEntityNode,ControlMentalEntityNode);

		addNodeInSortedOrder( ApplicationNode,EnvironmentApplicationNode);

		addNodeInSortedOrder( ApplicationEventNode,ApplicationEventSlotsNode);

		addNodeInSortedOrder( INGENIASObjectNode,AgentDescriptionNode);

		addNodeInSortedOrder( INGENIASObjectNode,MentalStatePatternNode);

		addNodeInSortedOrder( AUMLContainerNode,ProtocolNode);

		addNodeInSortedOrder( INGENIASObjectNode,MentalInstanceSpecificationNode);

		addNodeInSortedOrder( UMLObjectNode,ActionUMLNode);

		addNodeInSortedOrder( INGENIASObjectNode,StackEntryNode);

		addNodeInSortedOrder( INGENIASObjectNode,InteractionUnitNode);

		addNodeInSortedOrder( SpecificationNode,GRASIASpecificationNode);

		addNodeInSortedOrder( UMLClassifierNode,UMLClassNode);

		addNodeInSortedOrder( AgentComponentNode,MentalStateProcessorNode);

		addNodeInSortedOrder( UMLObjectNode,MergeNodeNode);

		addNodeInSortedOrder( UMLObjectNode,EndNodeNode);

		addNodeInSortedOrder( FactNode,FrameFactNode);

		addNodeInSortedOrder( INGENIASObjectNode,TestNode);

		addNodeInSortedOrder( AUMLContainerNode,LifelineNode);

		addNodeInSortedOrder( MentalStatePatternNode,SymbolicMentalStatePatternNode);

		addNodeInSortedOrder( AgentDescriptionNode,PROLOGAgentDescriptionNode);

		addNodeInSortedOrder( InteractionUnitNode,InteractionNode);

		addNodeInSortedOrder( INGENIASObjectNode,SpecificationNode);

		addNodeInSortedOrder( Autonomous_entityNode,AgentNode);

		addNodeInSortedOrder( AgentComponentNode,MentalStateManagerNode);

		addNodeInSortedOrder( INGENIASObjectNode,INGENIASCodeComponentNode);

		addNodeInSortedOrder( GeneralEventNode,CommunicationEventNode);

		addNodeInSortedOrder( TaskNode,TaskWSNode);

		addNodeInSortedOrder( SlotNode,ObjectSlotNode);

		addNodeInSortedOrder( GoalNode,GoalStateWSNode);

		addNodeInSortedOrder( GoalNode,StateGoalNode);

		addNodeInSortedOrder( InformationMentalEntityNode,ConversationNode);

		addNodeInSortedOrder( GeneralEventNode,ApplicationEventNode);

		addNodeInSortedOrder( AgentComponentNode,TaskNode);

		addNodeInSortedOrder( DeploymentUnitByTypeNode,DeploymentUnitByTypeMSEntityNode);

		addNodeInSortedOrder( RoleNode,RoleWSNode);

		addNodeInSortedOrder( AUMLContainerNode,AUMLAlternativeBoxNode);

		addNodeInSortedOrder( INGENIASObjectNode,WorkflowNode);

		setFilteredOff(false);
		reload();
	}

	// Function is a contribution from Ike http://www.groupsrv.com/computers/about116987.html
	private void addNodeInSortedOrder(DefaultMutableTreeNode parent,
			DefaultMutableTreeNode child){
		int n = parent.getChildCount();
		if(n==0){
			parent.add(child);

		} else {
			DefaultMutableTreeNode node=null;
			for(int i=0;i<n;i++){
				node = (DefaultMutableTreeNode)parent.getChildAt(i);
				if(node.toString().compareTo(child.toString())>0){
					parent.insert(child, i);
					return;
				}
			}
			parent.add(child);
		}
	}



	public DecisionNode createDecisionNode(String id){
		DecisionNode object=new     DecisionNode(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		DecisionNodeNode.insert(nn, DecisionNodeNode.getChildCount());
		nn.setParent(DecisionNodeNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getDecisionNode(String object){
		Object o=findUserObject(DecisionNodeNode,object);
		return o;
	}

	public FileSpecPatternMapping createFileSpecPatternMapping(String id){
		FileSpecPatternMapping object=new     FileSpecPatternMapping(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		FileSpecPatternMappingNode.insert(nn, FileSpecPatternMappingNode.getChildCount());
		nn.setParent(FileSpecPatternMappingNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getFileSpecPatternMapping(String object){
		Object o=findUserObject(FileSpecPatternMappingNode,object);
		return o;
	}

	public TestingPackage createTestingPackage(String id){
		TestingPackage object=new     TestingPackage(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		TestingPackageNode.insert(nn, TestingPackageNode.getChildCount());
		nn.setParent(TestingPackageNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getTestingPackage(String object){
		Object o=findUserObject(TestingPackageNode,object);
		return o;
	}

	public MethodParameter createMethodParameter(String id){
		MethodParameter object=new     MethodParameter(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		MethodParameterNode.insert(nn, MethodParameterNode.getChildCount());
		nn.setParent(MethodParameterNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getMethodParameter(String object){
		Object o=findUserObject(MethodParameterNode,object);
		return o;
	}

	public MentalState createMentalState(String id){
		MentalState object=new     MentalState(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		MentalStateNode.insert(nn, MentalStateNode.getChildCount());
		nn.setParent(MentalStateNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getMentalState(String object){
		Object o=findUserObject(MentalStateNode,object);
		return o;
	}

	public InternalApplication createInternalApplication(String id){
		InternalApplication object=new     InternalApplication(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		InternalApplicationNode.insert(nn, InternalApplicationNode.getChildCount());
		nn.setParent(InternalApplicationNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getInternalApplication(String object){
		Object o=findUserObject(InternalApplicationNode,object);
		return o;
	}

	public FileSpecMapping createFileSpecMapping(String id){
		FileSpecMapping object=new     FileSpecMapping(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		FileSpecMappingNode.insert(nn, FileSpecMappingNode.getChildCount());
		nn.setParent(FileSpecMappingNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getFileSpecMapping(String object){
		Object o=findUserObject(FileSpecMappingNode,object);
		return o;
	}

	public Parameter createParameter(String id){
		Parameter object=new     Parameter(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		ParameterNode.insert(nn, ParameterNode.getChildCount());
		nn.setParent(ParameterNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getParameter(String object){
		Object o=findUserObject(ParameterNode,object);
		return o;
	}

	public Goal createGoal(String id){
		Goal object=new     Goal(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		GoalNode.insert(nn, GoalNode.getChildCount());
		nn.setParent(GoalNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getGoal(String object){
		Object o=findUserObject(GoalNode,object);
		return o;
	}

	public AgentWS createAgentWS(String id){
		AgentWS object=new     AgentWS(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		AgentWSNode.insert(nn, AgentWSNode.getChildCount());
		nn.setParent(AgentWSNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getAgentWS(String object){
		Object o=findUserObject(AgentWSNode,object);
		return o;
	}

	public Fact createFact(String id){
		Fact object=new     Fact(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		FactNode.insert(nn, FactNode.getChildCount());
		nn.setParent(FactNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getFact(String object){
		Object o=findUserObject(FactNode,object);
		return o;
	}

	public AOPMentalStatePattern createAOPMentalStatePattern(String id){
		AOPMentalStatePattern object=new     AOPMentalStatePattern(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		AOPMentalStatePatternNode.insert(nn, AOPMentalStatePatternNode.getChildCount());
		nn.setParent(AOPMentalStatePatternNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getAOPMentalStatePattern(String object){
		Object o=findUserObject(AOPMentalStatePatternNode,object);
		return o;
	}

	public ShareTouple createShareTouple(String id){
		ShareTouple object=new     ShareTouple(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		ShareToupleNode.insert(nn, ShareToupleNode.getChildCount());
		nn.setParent(ShareToupleNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getShareTouple(String object){
		Object o=findUserObject(ShareToupleNode,object);
		return o;
	}

	public DeploymentUnitByTypeWithInitMS createDeploymentUnitByTypeWithInitMS(String id){
		DeploymentUnitByTypeWithInitMS object=new     DeploymentUnitByTypeWithInitMS(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		DeploymentUnitByTypeWithInitMSNode.insert(nn, DeploymentUnitByTypeWithInitMSNode.getChildCount());
		nn.setParent(DeploymentUnitByTypeWithInitMSNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getDeploymentUnitByTypeWithInitMS(String object){
		Object o=findUserObject(DeploymentUnitByTypeWithInitMSNode,object);
		return o;
	}

	public JoinNode createJoinNode(String id){
		JoinNode object=new     JoinNode(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		JoinNodeNode.insert(nn, JoinNodeNode.getChildCount());
		nn.setParent(JoinNodeNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getJoinNode(String object){
		Object o=findUserObject(JoinNodeNode,object);
		return o;
	}

	public Slot createSlot(String id){
		Slot object=new     Slot(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		SlotNode.insert(nn, SlotNode.getChildCount());
		nn.setParent(SlotNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getSlot(String object){
		Object o=findUserObject(SlotNode,object);
		return o;
	}

	public RuntimeEvent createRuntimeEvent(String id){
		RuntimeEvent object=new     RuntimeEvent(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		RuntimeEventNode.insert(nn, RuntimeEventNode.getChildCount());
		nn.setParent(RuntimeEventNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getRuntimeEvent(String object){
		Object o=findUserObject(RuntimeEventNode,object);
		return o;
	}

	public InitialNode createInitialNode(String id){
		InitialNode object=new     InitialNode(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		InitialNodeNode.insert(nn, InitialNodeNode.getChildCount());
		nn.setParent(InitialNodeNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getInitialNode(String object){
		Object o=findUserObject(InitialNodeNode,object);
		return o;
	}

	public Method createMethod(String id){
		Method object=new     Method(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		MethodNode.insert(nn, MethodNode.getChildCount());
		nn.setParent(MethodNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getMethod(String object){
		Object o=findUserObject(MethodNode,object);
		return o;
	}

	public TextUseCase createTextUseCase(String id){
		TextUseCase object=new     TextUseCase(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		TextUseCaseNode.insert(nn, TextUseCaseNode.getChildCount());
		nn.setParent(TextUseCaseNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getTextUseCase(String object){
		Object o=findUserObject(TextUseCaseNode,object);
		return o;
	}

	public RemoteProcedureCall createRemoteProcedureCall(String id){
		RemoteProcedureCall object=new     RemoteProcedureCall(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		RemoteProcedureCallNode.insert(nn, RemoteProcedureCallNode.getChildCount());
		nn.setParent(RemoteProcedureCallNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getRemoteProcedureCall(String object){
		Object o=findUserObject(RemoteProcedureCallNode,object);
		return o;
	}

	public Resource createResource(String id){
		Resource object=new     Resource(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		ResourceNode.insert(nn, ResourceNode.getChildCount());
		nn.setParent(ResourceNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getResource(String object){
		Object o=findUserObject(ResourceNode,object);
		return o;
	}

	public AgentModelBelieve createAgentModelBelieve(String id){
		AgentModelBelieve object=new     AgentModelBelieve(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		AgentModelBelieveNode.insert(nn, AgentModelBelieveNode.getChildCount());
		nn.setParent(AgentModelBelieveNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getAgentModelBelieve(String object){
		Object o=findUserObject(AgentModelBelieveNode,object);
		return o;
	}

	public ActivityFinal createActivityFinal(String id){
		ActivityFinal object=new     ActivityFinal(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		ActivityFinalNode.insert(nn, ActivityFinalNode.getChildCount());
		nn.setParent(ActivityFinalNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getActivityFinal(String object){
		Object o=findUserObject(ActivityFinalNode,object);
		return o;
	}

	public INGENIASUseCase createINGENIASUseCase(String id){
		INGENIASUseCase object=new     INGENIASUseCase(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		INGENIASUseCaseNode.insert(nn, INGENIASUseCaseNode.getChildCount());
		nn.setParent(INGENIASUseCaseNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getINGENIASUseCase(String object){
		Object o=findUserObject(INGENIASUseCaseNode,object);
		return o;
	}

	public GRASIAAgentDescription createGRASIAAgentDescription(String id){
		GRASIAAgentDescription object=new     GRASIAAgentDescription(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		GRASIAAgentDescriptionNode.insert(nn, GRASIAAgentDescriptionNode.getChildCount());
		nn.setParent(GRASIAAgentDescriptionNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getGRASIAAgentDescription(String object){
		Object o=findUserObject(GRASIAAgentDescriptionNode,object);
		return o;
	}

	public TextNote createTextNote(String id){
		TextNote object=new     TextNote(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		TextNoteNode.insert(nn, TextNoteNode.getChildCount());
		nn.setParent(TextNoteNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getTextNote(String object){
		Object o=findUserObject(TextNoteNode,object);
		return o;
	}

	public RuntimeFact createRuntimeFact(String id){
		RuntimeFact object=new     RuntimeFact(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		RuntimeFactNode.insert(nn, RuntimeFactNode.getChildCount());
		nn.setParent(RuntimeFactNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getRuntimeFact(String object){
		Object o=findUserObject(RuntimeFactNode,object);
		return o;
	}

	public ForkNode createForkNode(String id){
		ForkNode object=new     ForkNode(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		ForkNodeNode.insert(nn, ForkNodeNode.getChildCount());
		nn.setParent(ForkNodeNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getForkNode(String object){
		Object o=findUserObject(ForkNodeNode,object);
		return o;
	}

	public OrganizationNetwork createOrganizationNetwork(String id){
		OrganizationNetwork object=new     OrganizationNetwork(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		OrganizationNetworkNode.insert(nn, OrganizationNetworkNode.getChildCount());
		nn.setParent(OrganizationNetworkNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getOrganizationNetwork(String object){
		Object o=findUserObject(OrganizationNetworkNode,object);
		return o;
	}

	public MessagePassing createMessagePassing(String id){
		MessagePassing object=new     MessagePassing(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		MessagePassingNode.insert(nn, MessagePassingNode.getChildCount());
		nn.setParent(MessagePassingNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getMessagePassing(String object){
		Object o=findUserObject(MessagePassingNode,object);
		return o;
	}

	public SubProtocol createSubProtocol(String id){
		SubProtocol object=new     SubProtocol(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		SubProtocolNode.insert(nn, SubProtocolNode.getChildCount());
		nn.setParent(SubProtocolNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getSubProtocol(String object){
		Object o=findUserObject(SubProtocolNode,object);
		return o;
	}

	public RuntimeConversation createRuntimeConversation(String id){
		RuntimeConversation object=new     RuntimeConversation(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		RuntimeConversationNode.insert(nn, RuntimeConversationNode.getChildCount());
		nn.setParent(RuntimeConversationNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getRuntimeConversation(String object){
		Object o=findUserObject(RuntimeConversationNode,object);
		return o;
	}

	public SlotValueSpecification createSlotValueSpecification(String id){
		SlotValueSpecification object=new     SlotValueSpecification(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		SlotValueSpecificationNode.insert(nn, SlotValueSpecificationNode.getChildCount());
		nn.setParent(SlotValueSpecificationNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getSlotValueSpecification(String object){
		Object o=findUserObject(SlotValueSpecificationNode,object);
		return o;
	}

	public NaturalLanguageAgentDescription createNaturalLanguageAgentDescription(String id){
		NaturalLanguageAgentDescription object=new     NaturalLanguageAgentDescription(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		NaturalLanguageAgentDescriptionNode.insert(nn, NaturalLanguageAgentDescriptionNode.getChildCount());
		nn.setParent(NaturalLanguageAgentDescriptionNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getNaturalLanguageAgentDescription(String object){
		Object o=findUserObject(NaturalLanguageAgentDescriptionNode,object);
		return o;
	}

	public Organization createOrganization(String id){
		Organization object=new     Organization(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		OrganizationNode.insert(nn, OrganizationNode.getChildCount());
		nn.setParent(OrganizationNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getOrganization(String object){
		Object o=findUserObject(OrganizationNode,object);
		return o;
	}

	public ConditionalMentalState createConditionalMentalState(String id){
		ConditionalMentalState object=new     ConditionalMentalState(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		ConditionalMentalStateNode.insert(nn, ConditionalMentalStateNode.getChildCount());
		nn.setParent(ConditionalMentalStateNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getConditionalMentalState(String object){
		Object o=findUserObject(ConditionalMentalStateNode,object);
		return o;
	}

	public ApplicationWS createApplicationWS(String id){
		ApplicationWS object=new     ApplicationWS(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		ApplicationWSNode.insert(nn, ApplicationWSNode.getChildCount());
		nn.setParent(ApplicationWSNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getApplicationWS(String object){
		Object o=findUserObject(ApplicationWSNode,object);
		return o;
	}

	public AUMLSpecification createAUMLSpecification(String id){
		AUMLSpecification object=new     AUMLSpecification(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		AUMLSpecificationNode.insert(nn, AUMLSpecificationNode.getChildCount());
		nn.setParent(AUMLSpecificationNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getAUMLSpecification(String object){
		Object o=findUserObject(AUMLSpecificationNode,object);
		return o;
	}

	public Application createApplication(String id){
		Application object=new     Application(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		ApplicationNode.insert(nn, ApplicationNode.getChildCount());
		nn.setParent(ApplicationNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getApplication(String object){
		Object o=findUserObject(ApplicationNode,object);
		return o;
	}

	public DeploymentPackage createDeploymentPackage(String id){
		DeploymentPackage object=new     DeploymentPackage(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		DeploymentPackageNode.insert(nn, DeploymentPackageNode.getChildCount());
		nn.setParent(DeploymentPackageNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getDeploymentPackage(String object){
		Object o=findUserObject(DeploymentPackageNode,object);
		return o;
	}

	public Plan createPlan(String id){
		Plan object=new     Plan(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		PlanNode.insert(nn, PlanNode.getChildCount());
		nn.setParent(PlanNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getPlan(String object){
		Object o=findUserObject(PlanNode,object);
		return o;
	}

	public AUMLPort createAUMLPort(String id){
		AUMLPort object=new     AUMLPort(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		AUMLPortNode.insert(nn, AUMLPortNode.getChildCount());
		nn.setParent(AUMLPortNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getAUMLPort(String object){
		Object o=findUserObject(AUMLPortNode,object);
		return o;
	}

	public INGENIASComponent createINGENIASComponent(String id){
		INGENIASComponent object=new     INGENIASComponent(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		INGENIASComponentNode.insert(nn, INGENIASComponentNode.getChildCount());
		nn.setParent(INGENIASComponentNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getINGENIASComponent(String object){
		Object o=findUserObject(INGENIASComponentNode,object);
		return o;
	}

	public OrganizationGroup createOrganizationGroup(String id){
		OrganizationGroup object=new     OrganizationGroup(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		OrganizationGroupNode.insert(nn, OrganizationGroupNode.getChildCount());
		nn.setParent(OrganizationGroupNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getOrganizationGroup(String object){
		Object o=findUserObject(OrganizationGroupNode,object);
		return o;
	}

	public IUConcurrence createIUConcurrence(String id){
		IUConcurrence object=new     IUConcurrence(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		IUConcurrenceNode.insert(nn, IUConcurrenceNode.getChildCount());
		nn.setParent(IUConcurrenceNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getIUConcurrence(String object){
		Object o=findUserObject(IUConcurrenceNode,object);
		return o;
	}

	public DeploymentUnitByType createDeploymentUnitByType(String id){
		DeploymentUnitByType object=new     DeploymentUnitByType(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		DeploymentUnitByTypeNode.insert(nn, DeploymentUnitByTypeNode.getChildCount());
		nn.setParent(DeploymentUnitByTypeNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getDeploymentUnitByType(String object){
		Object o=findUserObject(DeploymentUnitByTypeNode,object);
		return o;
	}

	public IUIterate createIUIterate(String id){
		IUIterate object=new     IUIterate(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		IUIterateNode.insert(nn, IUIterateNode.getChildCount());
		nn.setParent(IUIterateNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getIUIterate(String object){
		Object o=findUserObject(IUIterateNode,object);
		return o;
	}

	public InformationMentalEntity createInformationMentalEntity(String id){
		InformationMentalEntity object=new     InformationMentalEntity(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		InformationMentalEntityNode.insert(nn, InformationMentalEntityNode.getChildCount());
		nn.setParent(InformationMentalEntityNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getInformationMentalEntity(String object){
		Object o=findUserObject(InformationMentalEntityNode,object);
		return o;
	}

	public AUMLAlternativeRow createAUMLAlternativeRow(String id){
		AUMLAlternativeRow object=new     AUMLAlternativeRow(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		AUMLAlternativeRowNode.insert(nn, AUMLAlternativeRowNode.getChildCount());
		nn.setParent(AUMLAlternativeRowNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getAUMLAlternativeRow(String object){
		Object o=findUserObject(AUMLAlternativeRowNode,object);
		return o;
	}

	public Column createColumn(String id){
		Column object=new     Column(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		ColumnNode.insert(nn, ColumnNode.getChildCount());
		nn.setParent(ColumnNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getColumn(String object){
		Object o=findUserObject(ColumnNode,object);
		return o;
	}

	public AutonomousEntityQuery createAutonomousEntityQuery(String id){
		AutonomousEntityQuery object=new     AutonomousEntityQuery(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		AutonomousEntityQueryNode.insert(nn, AutonomousEntityQueryNode.getChildCount());
		nn.setParent(AutonomousEntityQueryNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getAutonomousEntityQuery(String object){
		Object o=findUserObject(AutonomousEntityQueryNode,object);
		return o;
	}

	public ConcreteAgent createConcreteAgent(String id){
		ConcreteAgent object=new     ConcreteAgent(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		ConcreteAgentNode.insert(nn, ConcreteAgentNode.getChildCount());
		nn.setParent(ConcreteAgentNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getConcreteAgent(String object){
		Object o=findUserObject(ConcreteAgentNode,object);
		return o;
	}

	public UMLComment createUMLComment(String id){
		UMLComment object=new     UMLComment(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		UMLCommentNode.insert(nn, UMLCommentNode.getChildCount());
		nn.setParent(UMLCommentNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getUMLComment(String object){
		Object o=findUserObject(UMLCommentNode,object);
		return o;
	}

	public AgentRequirementsQuery createAgentRequirementsQuery(String id){
		AgentRequirementsQuery object=new     AgentRequirementsQuery(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		AgentRequirementsQueryNode.insert(nn, AgentRequirementsQueryNode.getChildCount());
		nn.setParent(AgentRequirementsQueryNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getAgentRequirementsQuery(String object){
		Object o=findUserObject(AgentRequirementsQueryNode,object);
		return o;
	}

	public UMLSpecification createUMLSpecification(String id){
		UMLSpecification object=new     UMLSpecification(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		UMLSpecificationNode.insert(nn, UMLSpecificationNode.getChildCount());
		nn.setParent(UMLSpecificationNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getUMLSpecification(String object){
		Object o=findUserObject(UMLSpecificationNode,object);
		return o;
	}

	public Believe createBelieve(String id){
		Believe object=new     Believe(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		BelieveNode.insert(nn, BelieveNode.getChildCount());
		nn.setParent(BelieveNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getBelieve(String object){
		Object o=findUserObject(BelieveNode,object);
		return o;
	}

	public WorkflowBox createWorkflowBox(String id){
		WorkflowBox object=new     WorkflowBox(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		WorkflowBoxNode.insert(nn, WorkflowBoxNode.getChildCount());
		nn.setParent(WorkflowBoxNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getWorkflowBox(String object){
		Object o=findUserObject(WorkflowBoxNode,object);
		return o;
	}

	public AgentComponent createAgentComponent(String id){
		AgentComponent object=new     AgentComponent(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		AgentComponentNode.insert(nn, AgentComponentNode.getChildCount());
		nn.setParent(AgentComponentNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getAgentComponent(String object){
		Object o=findUserObject(AgentComponentNode,object);
		return o;
	}

	public GRASIAMentalStatePattern createGRASIAMentalStatePattern(String id){
		GRASIAMentalStatePattern object=new     GRASIAMentalStatePattern(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		GRASIAMentalStatePatternNode.insert(nn, GRASIAMentalStatePatternNode.getChildCount());
		nn.setParent(GRASIAMentalStatePatternNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getGRASIAMentalStatePattern(String object){
		Object o=findUserObject(GRASIAMentalStatePatternNode,object);
		return o;
	}

	public DeploymentUnitByTypeEnumInitMS createDeploymentUnitByTypeEnumInitMS(String id){
		DeploymentUnitByTypeEnumInitMS object=new     DeploymentUnitByTypeEnumInitMS(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		DeploymentUnitByTypeEnumInitMSNode.insert(nn, DeploymentUnitByTypeEnumInitMSNode.getChildCount());
		nn.setParent(DeploymentUnitByTypeEnumInitMSNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getDeploymentUnitByTypeEnumInitMS(String object){
		Object o=findUserObject(DeploymentUnitByTypeEnumInitMSNode,object);
		return o;
	}

	public GeneralEvent createGeneralEvent(String id){
		GeneralEvent object=new     GeneralEvent(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		GeneralEventNode.insert(nn, GeneralEventNode.getChildCount());
		nn.setParent(GeneralEventNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getGeneralEvent(String object){
		Object o=findUserObject(GeneralEventNode,object);
		return o;
	}

	public Compromise createCompromise(String id){
		Compromise object=new     Compromise(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		CompromiseNode.insert(nn, CompromiseNode.getChildCount());
		nn.setParent(CompromiseNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getCompromise(String object){
		Object o=findUserObject(CompromiseNode,object);
		return o;
	}

	public Role createRole(String id){
		Role object=new     Role(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		RoleNode.insert(nn, RoleNode.getChildCount());
		nn.setParent(RoleNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getRole(String object){
		Object o=findUserObject(RoleNode,object);
		return o;
	}

	public EnvironmentApplication createEnvironmentApplication(String id){
		EnvironmentApplication object=new     EnvironmentApplication(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		EnvironmentApplicationNode.insert(nn, EnvironmentApplicationNode.getChildCount());
		nn.setParent(EnvironmentApplicationNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getEnvironmentApplication(String object){
		Object o=findUserObject(EnvironmentApplicationNode,object);
		return o;
	}

	public ApplicationEventSlots createApplicationEventSlots(String id){
		ApplicationEventSlots object=new     ApplicationEventSlots(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		ApplicationEventSlotsNode.insert(nn, ApplicationEventSlotsNode.getChildCount());
		nn.setParent(ApplicationEventSlotsNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getApplicationEventSlots(String object){
		Object o=findUserObject(ApplicationEventSlotsNode,object);
		return o;
	}

	public AgentDescription createAgentDescription(String id){
		AgentDescription object=new     AgentDescription(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		AgentDescriptionNode.insert(nn, AgentDescriptionNode.getChildCount());
		nn.setParent(AgentDescriptionNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getAgentDescription(String object){
		Object o=findUserObject(AgentDescriptionNode,object);
		return o;
	}

	public MentalStatePattern createMentalStatePattern(String id){
		MentalStatePattern object=new     MentalStatePattern(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		MentalStatePatternNode.insert(nn, MentalStatePatternNode.getChildCount());
		nn.setParent(MentalStatePatternNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getMentalStatePattern(String object){
		Object o=findUserObject(MentalStatePatternNode,object);
		return o;
	}

	public Protocol createProtocol(String id){
		Protocol object=new     Protocol(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		ProtocolNode.insert(nn, ProtocolNode.getChildCount());
		nn.setParent(ProtocolNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getProtocol(String object){
		Object o=findUserObject(ProtocolNode,object);
		return o;
	}

	public MentalInstanceSpecification createMentalInstanceSpecification(String id){
		MentalInstanceSpecification object=new     MentalInstanceSpecification(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		MentalInstanceSpecificationNode.insert(nn, MentalInstanceSpecificationNode.getChildCount());
		nn.setParent(MentalInstanceSpecificationNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getMentalInstanceSpecification(String object){
		Object o=findUserObject(MentalInstanceSpecificationNode,object);
		return o;
	}

	public ActionUML createActionUML(String id){
		ActionUML object=new     ActionUML(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		ActionUMLNode.insert(nn, ActionUMLNode.getChildCount());
		nn.setParent(ActionUMLNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getActionUML(String object){
		Object o=findUserObject(ActionUMLNode,object);
		return o;
	}

	public InteractionUnit createInteractionUnit(String id){
		InteractionUnit object=new     InteractionUnit(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		InteractionUnitNode.insert(nn, InteractionUnitNode.getChildCount());
		nn.setParent(InteractionUnitNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getInteractionUnit(String object){
		Object o=findUserObject(InteractionUnitNode,object);
		return o;
	}

	public GRASIASpecification createGRASIASpecification(String id){
		GRASIASpecification object=new     GRASIASpecification(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		GRASIASpecificationNode.insert(nn, GRASIASpecificationNode.getChildCount());
		nn.setParent(GRASIASpecificationNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getGRASIASpecification(String object){
		Object o=findUserObject(GRASIASpecificationNode,object);
		return o;
	}

	public MentalStateProcessor createMentalStateProcessor(String id){
		MentalStateProcessor object=new     MentalStateProcessor(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		MentalStateProcessorNode.insert(nn, MentalStateProcessorNode.getChildCount());
		nn.setParent(MentalStateProcessorNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getMentalStateProcessor(String object){
		Object o=findUserObject(MentalStateProcessorNode,object);
		return o;
	}

	public MergeNode createMergeNode(String id){
		MergeNode object=new     MergeNode(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		MergeNodeNode.insert(nn, MergeNodeNode.getChildCount());
		nn.setParent(MergeNodeNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getMergeNode(String object){
		Object o=findUserObject(MergeNodeNode,object);
		return o;
	}

	public EndNode createEndNode(String id){
		EndNode object=new     EndNode(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		EndNodeNode.insert(nn, EndNodeNode.getChildCount());
		nn.setParent(EndNodeNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getEndNode(String object){
		Object o=findUserObject(EndNodeNode,object);
		return o;
	}

	public FrameFact createFrameFact(String id){
		FrameFact object=new     FrameFact(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		FrameFactNode.insert(nn, FrameFactNode.getChildCount());
		nn.setParent(FrameFactNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getFrameFact(String object){
		Object o=findUserObject(FrameFactNode,object);
		return o;
	}

	public Test createTest(String id){
		Test object=new     Test(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		TestNode.insert(nn, TestNode.getChildCount());
		nn.setParent(TestNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getTest(String object){
		Object o=findUserObject(TestNode,object);
		return o;
	}

	public Lifeline createLifeline(String id){
		Lifeline object=new     Lifeline(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		LifelineNode.insert(nn, LifelineNode.getChildCount());
		nn.setParent(LifelineNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getLifeline(String object){
		Object o=findUserObject(LifelineNode,object);
		return o;
	}

	public SymbolicMentalStatePattern createSymbolicMentalStatePattern(String id){
		SymbolicMentalStatePattern object=new     SymbolicMentalStatePattern(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		SymbolicMentalStatePatternNode.insert(nn, SymbolicMentalStatePatternNode.getChildCount());
		nn.setParent(SymbolicMentalStatePatternNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getSymbolicMentalStatePattern(String object){
		Object o=findUserObject(SymbolicMentalStatePatternNode,object);
		return o;
	}

	public PROLOGAgentDescription createPROLOGAgentDescription(String id){
		PROLOGAgentDescription object=new     PROLOGAgentDescription(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		PROLOGAgentDescriptionNode.insert(nn, PROLOGAgentDescriptionNode.getChildCount());
		nn.setParent(PROLOGAgentDescriptionNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getPROLOGAgentDescription(String object){
		Object o=findUserObject(PROLOGAgentDescriptionNode,object);
		return o;
	}

	public Interaction createInteraction(String id){
		Interaction object=new     Interaction(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		InteractionNode.insert(nn, InteractionNode.getChildCount());
		nn.setParent(InteractionNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getInteraction(String object){
		Object o=findUserObject(InteractionNode,object);
		return o;
	}

	public Specification createSpecification(String id){
		Specification object=new     Specification(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		SpecificationNode.insert(nn, SpecificationNode.getChildCount());
		nn.setParent(SpecificationNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getSpecification(String object){
		Object o=findUserObject(SpecificationNode,object);
		return o;
	}

	public Agent createAgent(String id){
		Agent object=new     Agent(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		AgentNode.insert(nn, AgentNode.getChildCount());
		nn.setParent(AgentNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getAgent(String object){
		Object o=findUserObject(AgentNode,object);
		return o;
	}

	public MentalStateManager createMentalStateManager(String id){
		MentalStateManager object=new     MentalStateManager(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		MentalStateManagerNode.insert(nn, MentalStateManagerNode.getChildCount());
		nn.setParent(MentalStateManagerNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getMentalStateManager(String object){
		Object o=findUserObject(MentalStateManagerNode,object);
		return o;
	}

	public INGENIASCodeComponent createINGENIASCodeComponent(String id){
		INGENIASCodeComponent object=new     INGENIASCodeComponent(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		INGENIASCodeComponentNode.insert(nn, INGENIASCodeComponentNode.getChildCount());
		nn.setParent(INGENIASCodeComponentNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getINGENIASCodeComponent(String object){
		Object o=findUserObject(INGENIASCodeComponentNode,object);
		return o;
	}

	public CommunicationEvent createCommunicationEvent(String id){
		CommunicationEvent object=new     CommunicationEvent(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		CommunicationEventNode.insert(nn, CommunicationEventNode.getChildCount());
		nn.setParent(CommunicationEventNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getCommunicationEvent(String object){
		Object o=findUserObject(CommunicationEventNode,object);
		return o;
	}

	public TaskWS createTaskWS(String id){
		TaskWS object=new     TaskWS(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		TaskWSNode.insert(nn, TaskWSNode.getChildCount());
		nn.setParent(TaskWSNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getTaskWS(String object){
		Object o=findUserObject(TaskWSNode,object);
		return o;
	}

	public ObjectSlot createObjectSlot(String id){
		ObjectSlot object=new     ObjectSlot(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		ObjectSlotNode.insert(nn, ObjectSlotNode.getChildCount());
		nn.setParent(ObjectSlotNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getObjectSlot(String object){
		Object o=findUserObject(ObjectSlotNode,object);
		return o;
	}

	public GoalStateWS createGoalStateWS(String id){
		GoalStateWS object=new     GoalStateWS(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		GoalStateWSNode.insert(nn, GoalStateWSNode.getChildCount());
		nn.setParent(GoalStateWSNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getGoalStateWS(String object){
		Object o=findUserObject(GoalStateWSNode,object);
		return o;
	}

	public StateGoal createStateGoal(String id){
		StateGoal object=new     StateGoal(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		StateGoalNode.insert(nn, StateGoalNode.getChildCount());
		nn.setParent(StateGoalNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getStateGoal(String object){
		Object o=findUserObject(StateGoalNode,object);
		return o;
	}

	public Conversation createConversation(String id){
		Conversation object=new     Conversation(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		ConversationNode.insert(nn, ConversationNode.getChildCount());
		nn.setParent(ConversationNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getConversation(String object){
		Object o=findUserObject(ConversationNode,object);
		return o;
	}

	public ApplicationEvent createApplicationEvent(String id){
		ApplicationEvent object=new     ApplicationEvent(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		ApplicationEventNode.insert(nn, ApplicationEventNode.getChildCount());
		nn.setParent(ApplicationEventNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getApplicationEvent(String object){
		Object o=findUserObject(ApplicationEventNode,object);
		return o;
	}

	public Task createTask(String id){
		Task object=new     Task(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		TaskNode.insert(nn, TaskNode.getChildCount());
		nn.setParent(TaskNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getTask(String object){
		Object o=findUserObject(TaskNode,object);
		return o;
	}

	public DeploymentUnitByTypeMSEntity createDeploymentUnitByTypeMSEntity(String id){
		DeploymentUnitByTypeMSEntity object=new     DeploymentUnitByTypeMSEntity(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		DeploymentUnitByTypeMSEntityNode.insert(nn, DeploymentUnitByTypeMSEntityNode.getChildCount());
		nn.setParent(DeploymentUnitByTypeMSEntityNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getDeploymentUnitByTypeMSEntity(String object){
		Object o=findUserObject(DeploymentUnitByTypeMSEntityNode,object);
		return o;
	}

	public RoleWS createRoleWS(String id){
		RoleWS object=new     RoleWS(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		RoleWSNode.insert(nn, RoleWSNode.getChildCount());
		nn.setParent(RoleWSNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getRoleWS(String object){
		Object o=findUserObject(RoleWSNode,object);
		return o;
	}

	public AUMLAlternativeBox createAUMLAlternativeBox(String id){
		AUMLAlternativeBox object=new     AUMLAlternativeBox(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		AUMLAlternativeBoxNode.insert(nn, AUMLAlternativeBoxNode.getChildCount());
		nn.setParent(AUMLAlternativeBoxNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getAUMLAlternativeBox(String object){
		Object o=findUserObject(AUMLAlternativeBoxNode,object);
		return o;
	}

	public Workflow createWorkflow(String id){
		Workflow object=new     Workflow(id);
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(object);
		WorkflowNode.insert(nn, WorkflowNode.getChildCount());
		nn.setParent(WorkflowNode);
		this.reload();
		arbolObjetos.repaint();
		return object;
	}

	public Object getWorkflow(String object){
		Object o=findUserObject(WorkflowNode,object);
		return o;
	}


	public static Vector getValidEntitiesClasses(){
		Vector result=new Vector();

		result.add(DecisionNode.class);

		result.add(FileSpecPatternMapping.class);

		result.add(TestingPackage.class);

		result.add(MethodParameter.class);

		result.add(MentalState.class);

		result.add(InternalApplication.class);

		result.add(FileSpecMapping.class);

		result.add(Parameter.class);

		result.add(Goal.class);

		result.add(AgentWS.class);

		result.add(Fact.class);

		result.add(AOPMentalStatePattern.class);

		result.add(ShareTouple.class);

		result.add(DeploymentUnitByTypeWithInitMS.class);

		result.add(JoinNode.class);

		result.add(Slot.class);

		result.add(RuntimeEvent.class);

		result.add(InitialNode.class);

		result.add(Method.class);

		result.add(TextUseCase.class);

		result.add(RemoteProcedureCall.class);

		result.add(MentalEntity.class);

		result.add(Resource.class);

		result.add(AgentModelBelieve.class);

		result.add(ActivityFinal.class);

		result.add(INGENIASUseCase.class);

		result.add(GRASIAAgentDescription.class);

		result.add(TextNote.class);

		result.add(RuntimeFact.class);

		result.add(ForkNode.class);

		result.add(OrganizationNetwork.class);

		result.add(UseCase.class);

		result.add(MessagePassing.class);

		result.add(SubProtocol.class);

		result.add(RuntimeConversation.class);

		result.add(SlotValueSpecification.class);

		result.add(INGENIASObject.class);

		result.add(AUMLComponent.class);

		result.add(NaturalLanguageAgentDescription.class);

		result.add(Organization.class);

		result.add(ConditionalMentalState.class);

		result.add(ApplicationWS.class);

		result.add(AUMLSpecification.class);

		result.add(Application.class);

		result.add(DeploymentPackage.class);

		result.add(Plan.class);

		result.add(UMLComponent.class);

		result.add(AUMLPort.class);

		result.add(INGENIASComponent.class);

		result.add(OrganizationGroup.class);

		result.add(IUConcurrence.class);

		result.add(DeploymentUnitByType.class);

		result.add(IUIterate.class);

		result.add(InformationMentalEntity.class);

		result.add(AUMLAlternativeRow.class);

		result.add(Column.class);

		result.add(AutonomousEntityQuery.class);

		result.add(AUMLContainer.class);

		result.add(ConcreteAgent.class);

		result.add(UMLComment.class);

		result.add(AgentRequirementsQuery.class);

		result.add(UMLSpecification.class);

		result.add(Believe.class);

		result.add(WorkflowBox.class);

		result.add(AgentComponent.class);

		result.add(GRASIAMentalStatePattern.class);

		result.add(DeploymentUnitByTypeEnumInitMS.class);

		result.add(Autonomous_entity.class);

		result.add(GeneralEvent.class);

		result.add(Compromise.class);

		result.add(Role.class);

		result.add(ControlMentalEntity.class);

		result.add(EnvironmentApplication.class);

		result.add(ApplicationEventSlots.class);

		result.add(AgentDescription.class);

		result.add(MentalStatePattern.class);

		result.add(Protocol.class);

		result.add(MentalInstanceSpecification.class);

		result.add(ActionUML.class);

		result.add(StackEntry.class);

		result.add(InteractionUnit.class);

		result.add(GRASIASpecification.class);

		result.add(UMLClass.class);

		result.add(MentalStateProcessor.class);

		result.add(MergeNode.class);

		result.add(EndNode.class);

		result.add(FrameFact.class);

		result.add(Test.class);

		result.add(Lifeline.class);

		result.add(SymbolicMentalStatePattern.class);

		result.add(PROLOGAgentDescription.class);

		result.add(Interaction.class);

		result.add(Specification.class);

		result.add(Agent.class);

		result.add(MentalStateManager.class);

		result.add(INGENIASCodeComponent.class);

		result.add(UMLClassifier.class);

		result.add(CommunicationEvent.class);

		result.add(TaskWS.class);

		result.add(ObjectSlot.class);

		result.add(GoalStateWS.class);

		result.add(StateGoal.class);

		result.add(Conversation.class);

		result.add(ApplicationEvent.class);

		result.add(Task.class);

		result.add(DeploymentUnitByTypeMSEntity.class);

		result.add(RoleWS.class);

		result.add(AUMLAlternativeBox.class);

		result.add(Workflow.class);

		result.add(UMLObject.class);


		result.add(EnvironmentModelModelEntity.class);

		result.add(ComponentDiagramModelEntity.class);

		result.add(OrganizationModelModelEntity.class);

		result.add(TasksAndGoalsModelModelEntity.class);

		result.add(InteractionModelModelEntity.class);

		result.add(ActivityDiagramModelEntity.class);

		result.add(AgentModelModelEntity.class);

		result.add(UseCaseDiagramModelEntity.class);

		result.add(AUMLInteractionDiagramModelEntity.class);

		result.add(DeployDiagramModelEntity.class);

		return result;
	}


	public void reload(){
///		((DefaultTreeModel) this.arbolObjetos.getModel()).reload();
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				Enumeration expanded=arbolObjetos.getExpandedDescendants(new TreePath(root.getPath()));
				((DefaultTreeModel)arbolObjetos.getModel()).reload();
				while (expanded!=null && expanded.hasMoreElements()){
					TreePath tp=(TreePath)expanded.nextElement();

					arbolObjetos.expandPath(tp);	
				}
			}});

	}

	public Entity getEntity(String id,String type){
		Object o=this.findUserObjectInTree(root,id,type);
		return (Entity)o;
	}

	public Vector getAllObjects(){
		Vector result=new Vector();
		javax.swing.tree.DefaultMutableTreeNode dfn=this.root.getFirstLeaf();
		while (dfn!=null){
			TreeNode[] path=dfn.getPath();
			Object uo=((DefaultMutableTreeNode)(path[path.length-1])).getUserObject();
			if (uo instanceof Entity)
				result.add(uo);
			dfn=dfn.getNextLeaf();
		}
		return result;
	}




	private Object findUserObject(DefaultMutableTreeNode dtn,String name){
		if (dtn.getChildCount()==0)
			return null;
		DefaultMutableTreeNode node=(DefaultMutableTreeNode)dtn.getFirstChild();
		while (node!=null){
			if (node.getUserObject() instanceof    ingenias.editor.entities.Entity){
				ingenias.editor.entities.Entity uo=(ingenias.editor.entities.Entity)node.getUserObject();
				if (uo.getId().equalsIgnoreCase(name))
					return uo;
			}
			node=node.getNextNode();
		}
		return null;
	}

	public Vector findUserObject(String name){
		Vector found=new Vector();
		DefaultMutableTreeNode node= root.getFirstLeaf();
		while (node!=null){
			if (ingenias.editor.entities.Entity.class.isAssignableFrom(node.getUserObject().getClass())){
				ingenias.editor.entities.Entity uo=(ingenias.editor.entities.Entity)node.getUserObject();
				if (uo.getId().equalsIgnoreCase(name))              	
					found.add(uo);        
			}
			node=node.getNextLeaf();

		}
		return found;
	}

	private Object findUserObjectInTree(DefaultMutableTreeNode dtn,String name,String type){
		DefaultMutableTreeNode node= root.getFirstLeaf();
		while (node!=null){
			if (ingenias.editor.entities.Entity.class.isAssignableFrom(node.getUserObject().getClass())){
				ingenias.editor.entities.Entity uo=(ingenias.editor.entities.Entity)node.getUserObject();
				if (uo.getId().equalsIgnoreCase(name) &&
						uo.getClass().getName().indexOf(type)!=-1)
					return uo;
			}
			node=node.getNextLeaf();
		}
		return null;
	}

	public DefaultMutableTreeNode findNodeInTree(DefaultMutableTreeNode dtn,String name,String type){
		DefaultMutableTreeNode node= root.getFirstLeaf();
		while (node!=null){
			if (ingenias.editor.entities.Entity.class.isAssignableFrom(node.getUserObject().getClass())){
				ingenias.editor.entities.Entity uo=(ingenias.editor.entities.Entity)node.getUserObject();
				if (uo.getId().equalsIgnoreCase(name) )//     uo.getClass().getName().indexOf(type)!=-1)
					return node;
			}
			node=node.getNextLeaf();
		}
		return null;
	}

	private void findInstancesInTree(DefaultMutableTreeNode dtn,Class type, Vector result){
		DefaultMutableTreeNode node= root.getFirstLeaf();
		while (node!=null){
			String tcand=node.getUserObject().getClass().getName();
			if (type.isInstance(node.getUserObject()))
				result.add(node.getUserObject());
			node=node.getNextLeaf();

		}
	}

	public Vector findUserObjectPathRegexp(String nameregexp){
		Vector found=new Vector();
		DefaultMutableTreeNode node= root.getFirstLeaf();
		while (node!=null){
			if (ingenias.editor.entities.Entity.class.isAssignableFrom(node.getUserObject().getClass())){
				ingenias.editor.entities.Entity uo=(ingenias.editor.entities.Entity)node.getUserObject();
				if (java.util.regex.Pattern.matches(nameregexp.toLowerCase(),uo.getId().toLowerCase()))              	
					found.add(new TreePath(node.getPath()));        
			}
			node=node.getNextLeaf();       
		}
		return found;
	}

	public Vector getInstances(String type){
		int index=type.lastIndexOf(".");
		String className=type.substring(index+1,type.length());
		Vector result=new Vector();

		try {
			this.findInstancesInTree(root,Class.forName(type),result);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static Vector getInheritors(Class type){
		Vector validClasses=getValidEntitiesClasses();
		Vector result=new Vector();
		Enumeration enumeration=validClasses.elements();
		while (enumeration.hasMoreElements()){
			Class current=(Class)enumeration.nextElement();
			if (type.isAssignableFrom(current) && !type.equals(current))
				result.add(current);
		}
		return result;
	}




	public void replaceReferencesOM(Vector entities,Entity oldent, Entity newent)
	throws java.lang.IllegalAccessException{
		Enumeration enumeration=entities.elements();
		while (enumeration.hasMoreElements()){
			Entity current=(Entity)enumeration.nextElement();
			this.replaceReferencesOM(current,oldent,newent);
		}
	}

	private void replaceReferencesOM(Entity current,Entity oldent, Entity newent)
	throws java.lang.IllegalAccessException{
		java.lang.reflect.Field[] fs=current.getClass().getDeclaredFields();
		for (int k=0;k<fs.length;k++){
			java.lang.reflect.Field f=fs[k];
			if (f.get(current)!=null && f.get(current).equals(oldent)){
				f.set(current,newent);
			} else {
				if (f.get(current)!=null && f.get(current) instanceof ingenias.editor.TypedVector){
					TypedVector tv=(TypedVector)f.get(current);
					for (int j=0;j<tv.size();j++){
						if (tv.elementAt(j).equals(oldent)){
							tv.remove(j);
							tv.add(newent);
						}
					}
				}
			}
		}
	}

	public void transferFields(Entity source, Entity target)
	throws java.lang.NoSuchMethodException,
	java.lang.reflect.InvocationTargetException,
	java.lang.IllegalAccessException{
		Class sourcec=source.getClass();
		Class targetc=target.getClass();
		java.lang.reflect.Field[] ms=sourcec.getFields();
		for (int k=0;k<ms.length;k++){
			ms[k].set(target,ms[k].get(source));
		}
	}


	private void removeEntityFromAtts(Entity ent) throws IllegalAccessException{
		Vector v=this.getAllObjects();
		Enumeration enumeration=v.elements();
		while (enumeration.hasMoreElements()){
			Object obj=enumeration.nextElement();
			if (obj!=ent){

				java.lang.reflect.Field [] fs=obj.getClass().getFields();
				for (int k=0;k<fs.length;k++){
					Object att=fs[k].get(obj);
					if (att==ent){
						fs[k].set(obj,null);
					} else
						if (att!=null && att.getClass().equals(ingenias.editor.TypedVector.class)){
							((ingenias.editor.TypedVector)att).remove(ent);
						}
				}

			}
		}
	}

	public void removeEntity(Entity ent){
		try {
			this.removeEntityFromTree(root,ent);
			this.removeEntityFromAtts(ent);
		} catch (IllegalAccessException iae){
			iae.printStackTrace();
		}
	}

	private void removeEntityFromTree(DefaultMutableTreeNode dtn,Object entity){
		DefaultMutableTreeNode root=(DefaultMutableTreeNode)this.arbolObjetos.getModel().getRoot();

		DefaultMutableTreeNode node= root.getFirstLeaf();
		while (node!=null){
			if (node.getUserObject()!=null && node.getUserObject() instanceof Entity &&
					((Entity)node.getUserObject()).getId().equals(((Entity)entity).getId())) {
				DefaultTreeModel dtm=(DefaultTreeModel)arbolObjetos.getModel();
				((DefaultMutableTreeNode) node.getParent()).remove(node);
				node.removeFromParent();
				node=null;
			} else
				node=node.getNextLeaf();
		}
		this.reload();
		arbolObjetos.validate();
	}

	public void insert(Entity ent) throws SecurityException, NoSuchFieldException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{

		String className = ent.getType();               
		DefaultMutableTreeNode nn=new DefaultMutableTreeNode(ent);
		Field f=this.getClass().getField(className+"Node");
		java.lang.reflect.Method insertMethod=DefaultMutableTreeNode.class.getMethod("insert",new Class[]{MutableTreeNode.class,int.class});
		java.lang.reflect.Method childCount=DefaultMutableTreeNode.class.getMethod("getChildCount",new Class[]{});
		Integer result=(Integer)childCount.invoke(f.get(this),new Object[]{});
		insertMethod.invoke(f.get(this),new Object[]{nn,result});
		nn.getClass().getMethod("setParent",new Class[]{MutableTreeNode.class}).invoke(nn,new Object[]{f.get(this)});            
		this.reload();
		arbolObjetos.repaint();

	}

	public static void main(String args[]){
		Vector v=ObjectManager.getInheritors(ingenias.editor.entities.Autonomous_entity.class);
		Enumeration enumeration=v.elements();
		while (enumeration.hasMoreElements()){
			System.err.println(enumeration.nextElement());
		}
	}



	public javax.swing.tree.DefaultMutableTreeNode getRoot() {
		return (DefaultMutableTreeNode) arbolObjetos.getModel().getRoot();
	}
}

