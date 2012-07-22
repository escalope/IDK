

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
 
package ingenias.editor.cellfactories;

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

public class DeployDiagramCellViewFactory implements CellViewFactory {

public DeployDiagramCellViewFactory() {}
 

 	public org.jgraph.graph.CellView createView(GraphModel model, Object cell) {
		
			org.jgraph.graph.CellView view = null;
			if (model.isPort(cell))
				view = new PortView(cell);
			else if (model.isEdge(cell))
				view = createEdgeView(cell);
			else
				view = createVertexView(cell);
			return view;
		}

  // Modificar agregando nuevas clases VIEW segun se vayan a?endo

  protected org.jgraph.graph.VertexView createVertexView(Object v) {
    Object userObject = ( (DefaultGraphCell) v).getUserObject();

   // Diagram Objects start here


   if (userObject.getClass().equals(TestingPackage.class)){
           return new ingenias.editor.cell.TestingPackageView(v);
   }

   if (userObject.getClass().equals(DeploymentPackage.class)){
           return new ingenias.editor.cell.DeploymentPackageView(v);
   }

   if (userObject.getClass().equals(DeploymentPackageWithContext.class)){
           return new ingenias.editor.cell.DeploymentPackageWithContextView(v);
   }

   if (userObject.getClass().equals(INGENIASComponent.class)){
           return new ingenias.editor.cell.INGENIASComponentView(v);
   }

   if (userObject.getClass().equals(Application.class)){
           return new ingenias.editor.cell.ApplicationView(v);
   }

   if (userObject.getClass().equals(SimulationPackage.class)){
           return new ingenias.editor.cell.SimulationPackageView(v);
   }

   if (userObject.getClass().equals(SimulationEvent.class)){
           return new ingenias.editor.cell.SimulationEventView(v);
   }

   if (userObject.getClass().equals(SimExtractedInformation.class)){
           return new ingenias.editor.cell.SimExtractedInformationView(v);
   }

   if (userObject.getClass().equals(INGENIASCodeComponent.class)){
           return new ingenias.editor.cell.INGENIASCodeComponentView(v);
   }

   if (userObject.getClass().equals(EnvironmentApplication.class)){
           return new ingenias.editor.cell.EnvironmentApplicationView(v);
   }

   if (userObject.getClass().equals(InternalApplication.class)){
           return new ingenias.editor.cell.InternalApplicationView(v);
   }

   if (userObject.getClass().equals(UMLComment.class)){
           return new ingenias.editor.cell.UMLCommentView(v);
   }

   if (userObject.getClass().equals(Goal.class)){
           return new ingenias.editor.cell.GoalView(v);
   }

   if (userObject.getClass().equals(DeploymentUnitByType.class)){
           return new ingenias.editor.cell.DeploymentUnitByTypeView(v);
   }

   if (userObject.getClass().equals(DeploymentUnitByTypeEnumInitMS.class)){
           return new ingenias.editor.cell.DeploymentUnitByTypeEnumInitMSView(v);
   }

   if (userObject.getClass().equals(DeploymentUnitByTypeMSEntity.class)){
           return new ingenias.editor.cell.DeploymentUnitByTypeMSEntityView(v);
   }

   if (userObject.getClass().equals(AMIContext.class)){
           return new ingenias.editor.cell.AMIContextView(v);
   }

   if (userObject.getClass().equals(Fact.class)){
           return new ingenias.editor.cell.FactView(v);
   }

   if (userObject.getClass().equals(ApplicationWS.class)){
           return new ingenias.editor.cell.ApplicationWSView(v);
   }

   if (userObject.getClass().equals(DeploymentUnitByTypeWithInitMS.class)){
           return new ingenias.editor.cell.DeploymentUnitByTypeWithInitMSView(v);
   }

   if (userObject.getClass().equals(ApplicationEvent.class)){
           return new ingenias.editor.cell.ApplicationEventView(v);
   }

   if (userObject.getClass().equals(MentalState.class)){
           return new ingenias.editor.cell.MentalStateView(v);
   }

   if (userObject.getClass().equals(GoalStateWS.class)){
           return new ingenias.editor.cell.GoalStateWSView(v);
   }

   if (userObject.getClass().equals(FrameFact.class)){
           return new ingenias.editor.cell.FrameFactView(v);
   }

   if (userObject.getClass().equals(Test.class)){
           return new ingenias.editor.cell.TestView(v);
   }

   if (userObject.getClass().equals(RuntimeEvent.class)){
           return new ingenias.editor.cell.RuntimeEventView(v);
   }

   if (userObject.getClass().equals(FAERIEContext.class)){
           return new ingenias.editor.cell.FAERIEContextView(v);
   }

   if (userObject.getClass().equals(AgentWS.class)){
           return new ingenias.editor.cell.AgentWSView(v);
   }

   if (userObject.getClass().equals(AgentModelBelieve.class)){
           return new ingenias.editor.cell.AgentModelBelieveView(v);
   }

   if (userObject.getClass().equals(AMIContextBindingData.class)){
           return new ingenias.editor.cell.AMIContextBindingDataView(v);
   }

   if (userObject.getClass().equals(ApplicationEventSlots.class)){
           return new ingenias.editor.cell.ApplicationEventSlotsView(v);
   }

   if (userObject.getClass().equals(Compromise.class)){
           return new ingenias.editor.cell.CompromiseView(v);
   }

   if (userObject.getClass().equals(FileSpecPatternMapping.class)){
           return new ingenias.editor.cell.FileSpecPatternMappingView(v);
   }

   if (userObject.getClass().equals(Conversation.class)){
           return new ingenias.editor.cell.ConversationView(v);
   }

   if (userObject.getClass().equals(FAERIECtxtModel.class)){
           return new ingenias.editor.cell.FAERIECtxtModelView(v);
   }

   if (userObject.getClass().equals(Agent.class)){
           return new ingenias.editor.cell.AgentView(v);
   }

   if (userObject.getClass().equals(RuntimeConversation.class)){
           return new ingenias.editor.cell.RuntimeConversationView(v);
   }

   if (userObject.getClass().equals(CommunicationEvent.class)){
           return new ingenias.editor.cell.CommunicationEventView(v);
   }

   if (userObject.getClass().equals(MentalInstanceSpecification.class)){
           return new ingenias.editor.cell.MentalInstanceSpecificationView(v);
   }

   if (userObject.getClass().equals(GeneralEvent.class)){
           return new ingenias.editor.cell.GeneralEventView(v);
   }

   if (userObject.getClass().equals(ConditionalMentalState.class)){
           return new ingenias.editor.cell.ConditionalMentalStateView(v);
   }

   if (userObject.getClass().equals(StateGoal.class)){
           return new ingenias.editor.cell.StateGoalView(v);
   }

   if (userObject.getClass().equals(RuntimeFact.class)){
           return new ingenias.editor.cell.RuntimeFactView(v);
   }

   if (userObject.getClass().equals(Believe.class)){
           return new ingenias.editor.cell.BelieveView(v);
   }


   // Diagram Relationships start here

   if (v.getClass().equals(UMLAnnotatedElementEdge.class)){
           return new ingenias.editor.cell.UMLAnnotatedElementView(v);
   }

   if (v.getClass().equals(DefinesDeploymentEdge.class)){
           return new ingenias.editor.cell.DefinesDeploymentView(v);
   }

   if (v.getClass().equals(CDUsesCodeEdge.class)){
           return new ingenias.editor.cell.CDUsesCodeView(v);
   }

   if (v.getClass().equals(SimulationPursuesEdge.class)){
           return new ingenias.editor.cell.SimulationPursuesView(v);
   }


    return null;
  }

  protected org.jgraph.graph.EdgeView createEdgeView(Object v) {
  return new org.jgraph.graph.EdgeView(v);

//         if (v instanceof ExecuteEdge){
//           return new ExecuteView(v,this,cm);
//         }

  }

 


}
