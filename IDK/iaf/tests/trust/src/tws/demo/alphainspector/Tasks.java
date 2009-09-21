/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tws.demo.alphainspector;

import ingenias.jade.components.TaskOutput;
import ingenias.jade.mental.QualityDegreeOfSourceInTesting;
import ingenias.jade.mental.InspectQualityOfSourceInTesting;
import regretsystem.EvaluationValue;
import tws.demo.AlphaInspection;

/**
 *
 * @author carlos
 */
public class Tasks {

    public static void processAlphaQualityInspectionRequestTaskTask(
            InspectQualityOfSourceInTesting eiInspeccionarCalidadFuenteCuarentena,
            QualityDegreeOfSourceInTesting outputsdefaultGradoCalidadFuenteCuarentena,
            TaskOutput outputsdefault) {
        
        AlphaInspection inspeccion = (AlphaInspection) eiInspeccionarCalidadFuenteCuarentena.getdata();
        String fuenteURL = (String) inspeccion.getFuenteInfo().getData();
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
