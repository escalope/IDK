/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tws.demo.inspector;

import ingenias.jade.components.TaskOutput;
import ingenias.jade.mental.SourceQualityDegree;

import ingenias.jade.mental.InspectQualitySource;
import regretsystem.EvaluationValue;
import tws.demo.SourceInfo;

/**
 *
 * @author carlos
 */
public class Tasks {

    public static void processQualityInspectionRequestTaskTask(
    		InspectQualitySource eiInspeccionarCalidadFuente,
            SourceQualityDegree outputsdefaultGradoCalidadFuente,
            TaskOutput outputsdefault) {
        SourceInfo fuente = (SourceInfo) eiInspeccionarCalidadFuente.getdata();
        String fuenteURL = (String) fuente.getData();
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
