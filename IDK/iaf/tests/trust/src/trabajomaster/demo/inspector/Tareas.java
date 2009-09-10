/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trabajomaster.demo.inspector;

import ingenias.jade.components.TaskOutput;
import ingenias.jade.mental.GradoCalidadFuente;

import ingenias.jade.mental.InspectQualitySource;
import regretsystem.EvaluationValue;
import trabajomaster.demo.FuenteInfo;

/**
 *
 * @author carlos
 */
public class Tareas {

    public static void procesarSolicitudInspeccionCalidadTask(
    		InspectQualitySource eiInspeccionarCalidadFuente,
            GradoCalidadFuente outputsdefaultGradoCalidadFuente,
            TaskOutput outputsdefault) {
        FuenteInfo fuente = (FuenteInfo) eiInspeccionarCalidadFuente.getdata();
        String fuenteURL = (String) fuente.getDatos();
        EvaluationValue eval = new EvaluationValue();
        if(fuenteURL.equals("urn:fuente:1")){
            eval.setObjectCriteria(0.8);
            eval.setProcessCriteria(0.6);
            eval.setSubjectCriteria(0.3);
        }else if(fuenteURL.equals("urn:fuente:2")){
            eval.setObjectCriteria(0.72);
            eval.setProcessCriteria(0.7);
            eval.setSubjectCriteria(0.18);
        }else{
            eval.setObjectCriteria(0.8);
            eval.setProcessCriteria(0.59);
            eval.setSubjectCriteria(0.6);
        }
        fuente.setEval(eval);
        outputsdefaultGradoCalidadFuente.setdata(fuente);
    }

}
