/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trabajomaster.demo.colaboradorartificial;

import ingenias.jade.components.TaskOutput;
import ingenias.jade.mental.NuevaPropuestaPorEnviar;
import ingenias.jade.mental.NuevoCicloEvento;

/**
 *
 * @author carlos
 */
public class Tareas {

    public static void buscarFuenteTask(String agentID, 
            NuevoCicloEvento eiNuevoCicloEvento,
            NuevaPropuestaPorEnviar outputsdefaultNuevaPropuestaPorEnviar,
            TaskOutput outputsdefault) {
        outputsdefaultNuevaPropuestaPorEnviar.setdata(eiNuevoCicloEvento.getdata());
    }

}
