/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tws.demo.autonomouscollaborator;

import ingenias.jade.components.TaskOutput;
import ingenias.jade.mental.NewProposalToBeSent;
import ingenias.jade.mental.NewCycleEvent;

/**
 *
 * @author carlos
 */
public class Tasks {

    public static void seekSourcesTaskTask(String agentID,
            NewCycleEvent eiNuevoCicloEvento,
            NewProposalToBeSent outputsdefaultNuevaPropuestaPorEnviar,
            TaskOutput outputsdefault) {
        outputsdefaultNuevaPropuestaPorEnviar.setdata(eiNuevoCicloEvento.getdata());
    }

}
