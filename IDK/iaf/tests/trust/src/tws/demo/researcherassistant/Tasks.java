/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tws.demo.researcherassistant;

import ingenias.jade.components.TaskOutput;
import ingenias.jade.mental.NewProposalToBeSent;
import ingenias.jade.mental.NewIntroducedProposalEvent;

/**
 *
 * @author carlos
 */
public class Tasks {

    public static void processHumanProposalTaskTask(
            NewIntroducedProposalEvent eiNuevaPropuestaIntroducidaEvento,
            NewProposalToBeSent outputsdefaultNuevaPropuestaPorEnviar,
            TaskOutput outputsdefault) {
        outputsdefaultNuevaPropuestaPorEnviar.setdata(eiNuevaPropuestaIntroducidaEvento.getdata());
    }

}
