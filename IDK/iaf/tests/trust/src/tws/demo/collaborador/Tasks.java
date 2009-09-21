/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tws.demo.collaborador;

import ingenias.editor.entities.RuntimeConversation;
import ingenias.jade.AgentExternalDescription;
import ingenias.jade.components.TaskOutput;
import ingenias.jade.components.YellowPages;
import ingenias.jade.mental.NewProposalToBeSent;
import ingenias.jade.mental.SourceInfoProposal;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAException;
import java.util.logging.Level;
import java.util.logging.Logger;
import regretsystem.EvaluationValue;
import tws.demo.SourceInfo;

/**
 *
 * @author carlos
 */
public class Tasks {

    public static void generateProposalTaskTask(String id,NewProposalToBeSent eiNuevaPropuestaPorEnviar,
           RuntimeConversation outputsdefaultRealizacionPropuesta,
           SourceInfoProposal outputsdefaultPropuestaFuente,
           TaskOutput outputsdefault,
           YellowPages yp) {

        String fuente  = (String) eiNuevaPropuestaPorEnviar.getdata();
        if(fuente.contains("_")){
            fuente = fuente.substring(1);
            try {
                DFAgentDescription[] ad = yp.getAgents("SupervisorRole");
                DFAgentDescription sup2 = ad[1];
                String supId = sup2.getName().getLocalName();

                outputsdefaultRealizacionPropuesta.addCollaborators(new AgentExternalDescription(sup2.getName(), "SupervisorRole"));
            } catch (FIPAException ex) {
                Logger.getLogger(Tasks.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        SourceInfo fuenteInfo = new SourceInfo();
        fuenteInfo.setEval(new EvaluationValue());
        fuenteInfo.setCollaboradorId(id);
        fuenteInfo.setData(fuente);
        outputsdefaultPropuestaFuente.setdata(fuenteInfo);
    }

}
