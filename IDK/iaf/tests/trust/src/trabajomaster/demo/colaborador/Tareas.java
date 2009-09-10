/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trabajomaster.demo.colaborador;

import ingenias.editor.entities.RuntimeConversation;
import ingenias.jade.AgentExternalDescription;
import ingenias.jade.components.TaskOutput;
import ingenias.jade.components.YellowPages;
import ingenias.jade.mental.NuevaPropuestaPorEnviar;
import ingenias.jade.mental.PropuestaFuente;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAException;
import java.util.logging.Level;
import java.util.logging.Logger;
import regretsystem.EvaluationValue;
import trabajomaster.demo.FuenteInfo;

/**
 *
 * @author carlos
 */
public class Tareas {

    public static void generarPropuestaTask(String id,NuevaPropuestaPorEnviar eiNuevaPropuestaPorEnviar,
           RuntimeConversation outputsdefaultRealizacionPropuesta,
           PropuestaFuente outputsdefaultPropuestaFuente,
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
                Logger.getLogger(Tareas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        FuenteInfo fuenteInfo = new FuenteInfo();
        fuenteInfo.setEval(new EvaluationValue());
        fuenteInfo.setColaboradorId(id);
        fuenteInfo.setDatos(fuente);
        outputsdefaultPropuestaFuente.setdata(fuenteInfo);
    }

}
