/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tws.demo.inspector;

import ingenias.jade.MyMath;
import ingenias.jade.components.TaskOutput;
import ingenias.jade.mental.InspectQualityOfSourceInTesting;
import ingenias.jade.mental.QualityDegreeOfSourceInTesting;
import ingenias.jade.mental.SourceQualityDegree;

import ingenias.jade.mental.InspectQualitySource;
import regretsystem.EvaluationValue;
import tws.demo.AlphaInspection;
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
        System.out.println("Inspector: "+eval.getSubjectCriteria());
    }
    
    public static void perfectProcessQualityInspectionRequestTaskTask(
    		InspectQualitySource eiInspeccionarCalidadFuente,
            SourceQualityDegree outputsdefaultGradoCalidadFuente,
            TaskOutput outputsdefault) {
        SourceInfo fuente = (SourceInfo) eiInspeccionarCalidadFuente.getdata();
        String fuenteURL = (String) fuente.getData();
        EvaluationValue eval = new EvaluationValue();
        eval.setObjectCriteria(0.6);
	eval.setProcessCriteria(0.8);       
	eval.setSubjectCriteria(filter(MyMath.gaussian(eiInspeccionarCalidadFuente.getdeclaredQuality(), 0.1)));

        fuente.setEval(eval);
        outputsdefaultGradoCalidadFuente.setdata(fuente);
        outputsdefaultGradoCalidadFuente.setdeclaredQuality(eiInspeccionarCalidadFuente.getdeclaredQuality());
        System.out.println("Inspector: "+eval.getSubjectCriteria());
    }
    
    public static void gaussianProcessQualityInspectionRequestTaskTask(
    		InspectQualitySource eiInspeccionarCalidadFuente,
            SourceQualityDegree outputsdefaultGradoCalidadFuente,
            TaskOutput outputsdefault) {
        SourceInfo fuente = (SourceInfo) eiInspeccionarCalidadFuente.getdata();
        String fuenteURL = (String) fuente.getData();
        EvaluationValue eval = new EvaluationValue();
        
        eval.setObjectCriteria(0.6);
	eval.setProcessCriteria(0.7);
	eval.setSubjectCriteria(filter(MyMath.gaussian(eiInspeccionarCalidadFuente.getdeclaredQuality()-0.2, 0.2)));
		
        fuente.setEval(eval);
        outputsdefaultGradoCalidadFuente.setdata(fuente);
        outputsdefaultGradoCalidadFuente.setdeclaredQuality(eiInspeccionarCalidadFuente.getdeclaredQuality());
        System.out.println("Inspector: "+eval.getSubjectCriteria());
    }
    
 private static double filter(double value){
            if(value>1) value=1;
            if(value<-1) value=-1;
            return value;
        }

}
