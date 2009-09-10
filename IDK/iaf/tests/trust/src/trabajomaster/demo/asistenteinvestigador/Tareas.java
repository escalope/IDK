/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trabajomaster.demo.asistenteinvestigador;

import ingenias.jade.components.TaskOutput;
import ingenias.jade.mental.NuevaPropuestaIntroducidaEvento;
import ingenias.jade.mental.NuevaPropuestaPorEnviar;

/**
 *
 * @author carlos
 */
public class Tareas {

    public static void procesarPropuestaDeHumanoTask(
            NuevaPropuestaIntroducidaEvento eiNuevaPropuestaIntroducidaEvento,
            NuevaPropuestaPorEnviar outputsdefaultNuevaPropuestaPorEnviar,
            TaskOutput outputsdefault) {
        outputsdefaultNuevaPropuestaPorEnviar.setdata(eiNuevaPropuestaIntroducidaEvento.getdata());
    }

}
