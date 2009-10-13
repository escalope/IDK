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
import jade.core.AID;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import regretsystem.EvaluationValue;
import tws.demo.SourceInfo;

/**
 *
 * @author carlos
 */
public class Tasks {

    public static void generateProposalTaskTask(String id, NewProposalToBeSent eiNuevaPropuestaPorEnviar,
            RuntimeConversation outputsdefaultRealizacionPropuesta,
            SourceInfoProposal outputsdefaultPropuestaFuente,
            TaskOutput outputsdefault,
            YellowPages yp) {

        String fuente = (String) eiNuevaPropuestaPorEnviar.getdata();
        try {
            DFAgentDescription[] ad = yp.getAgents("SupervisorRole");
            if (ad.length > 1) {

                AID selected = null;
                if (fuente.contains("_")) {
                    if(ad[0].getName().getLocalName().contains("1")){
                        selected = ad[0].getName();
                    }else{
                        selected = ad[1].getName();
                    }
                    fuente = fuente.substring(1);
                }else{
                    if(ad[0].getName().getLocalName().contains("1")){
                        selected = ad[1].getName();
                    }else{
                        selected = ad[0].getName();
                    }
                }
                System.out.println("Selected: "+selected.toString());
                outputsdefaultRealizacionPropuesta.addCollaborators(new AgentExternalDescription(selected, "SupervisorRole"));
            } else {
                System.out.println("Selected: "+ad[0].getName().toString());
                outputsdefaultRealizacionPropuesta.addCollaborators(new AgentExternalDescription(ad[0].getName(), "SupervisorRole"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        SourceInfo fuenteInfo = new SourceInfo();
        fuenteInfo.setEval(new EvaluationValue());
        fuenteInfo.setCollaboradorId(id);
        fuenteInfo.setData(fuente);
        outputsdefaultPropuestaFuente.setdata(fuenteInfo);
    }
}
