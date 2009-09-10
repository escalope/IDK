/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trabajomaster.demo.inspectoralfa;

import ingenias.jade.components.TaskOutput;
import ingenias.jade.mental.GradoCalidadFuenteCuarentena;
import ingenias.jade.mental.InspeccionarCalidadFuenteCuarentena;
import regretsystem.EvaluationValue;
import trabajomaster.demo.InspeccionAlfa;

/**
 *
 * @author carlos
 */
public class Tareas {

    public static void procesarSolicitudInspeccionAlfaFuenteTask(
            InspeccionarCalidadFuenteCuarentena eiInspeccionarCalidadFuenteCuarentena,
            GradoCalidadFuenteCuarentena outputsdefaultGradoCalidadFuenteCuarentena,
            TaskOutput outputsdefault) {
        
        InspeccionAlfa inspeccion = (InspeccionAlfa) eiInspeccionarCalidadFuenteCuarentena.getdata();
        String fuenteURL = (String) inspeccion.getFuenteInfo().getDatos();
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
        inspeccion.getFuenteInfo().setEval(eval);
        outputsdefaultGradoCalidadFuenteCuarentena.setdata(inspeccion);

    }

}
