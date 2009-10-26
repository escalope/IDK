/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tws.demo.alphainspector;

import java.util.Random;

import ingenias.jade.MyMath;
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
	private static Random randomSubject=new Random();
	private static Random randomObject=new Random();
	private static Random randomProcess=new Random();

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
		outputsdefaultGradoCalidadFuenteCuarentena.setprocessCriteria(eval.getProcessCriteria());
		outputsdefaultGradoCalidadFuenteCuarentena.setsubjectCriteria(eval.getSubjectCriteria());
		outputsdefaultGradoCalidadFuenteCuarentena.setobjectCriteria(eval.getObjectCriteria());
		outputsdefaultGradoCalidadFuenteCuarentena.setdeclaredQuality(eiInspeccionarCalidadFuenteCuarentena.getdeclaredQuality());

		outputsdefaultGradoCalidadFuenteCuarentena.setdata(inspeccion);

		System.out.println("Inspector real: "+eval.getSubjectCriteria()+ " known value :"+eiInspeccionarCalidadFuenteCuarentena.getdeclaredQuality());
	}

	public static void gaussianprocessAlphaQualityInspectionRequestTaskTask(
			InspectQualityOfSourceInTesting eiInspeccionarCalidadFuenteCuarentena,
			QualityDegreeOfSourceInTesting outputsdefaultGradoCalidadFuenteCuarentena,
			TaskOutput outputsdefault) {

		AlphaInspection inspeccion = (AlphaInspection) eiInspeccionarCalidadFuenteCuarentena.getdata();
		String fuenteURL = (String) inspeccion.getFuenteInfo().getData();
		EvaluationValue eval = new EvaluationValue();

		eval.setObjectCriteria(MyMath.gaussian(0.5, 0.5));
		eval.setProcessCriteria(MyMath.gaussian(0.5, 0.5));
		eval.setSubjectCriteria(MyMath.gaussian(0.5, 0.5));

		inspeccion.getFuenteInfo().setEval(eval);
		outputsdefaultGradoCalidadFuenteCuarentena.setprocessCriteria(eval.getProcessCriteria());
		outputsdefaultGradoCalidadFuenteCuarentena.setsubjectCriteria(eval.getSubjectCriteria());
		outputsdefaultGradoCalidadFuenteCuarentena.setobjectCriteria(eval.getObjectCriteria());
		outputsdefaultGradoCalidadFuenteCuarentena.setdeclaredQuality(eiInspeccionarCalidadFuenteCuarentena.getdeclaredQuality());
		outputsdefaultGradoCalidadFuenteCuarentena.setdata(inspeccion);

		System.out.println("Inspector gaussian: "+eval.getSubjectCriteria()+ " known value :"+eiInspeccionarCalidadFuenteCuarentena.getdeclaredQuality());
	}


	public static void perfectprocessAlphaQualityInspectionRequestTaskTask(
			InspectQualityOfSourceInTesting eiInspeccionarCalidadFuenteCuarentena,
			QualityDegreeOfSourceInTesting outputsdefaultGradoCalidadFuenteCuarentena,
			TaskOutput outputsdefault) {

		AlphaInspection inspeccion = (AlphaInspection) eiInspeccionarCalidadFuenteCuarentena.getdata();
		String fuenteURL = (String) inspeccion.getFuenteInfo().getData();
		EvaluationValue eval = new EvaluationValue();

		eval.setObjectCriteria(eiInspeccionarCalidadFuenteCuarentena.getdeclaredQuality());
		eval.setProcessCriteria(eiInspeccionarCalidadFuenteCuarentena.getdeclaredQuality());
		eval.setSubjectCriteria(eiInspeccionarCalidadFuenteCuarentena.getdeclaredQuality());

		inspeccion.getFuenteInfo().setEval(eval);
		outputsdefaultGradoCalidadFuenteCuarentena.setprocessCriteria(eval.getProcessCriteria());
		outputsdefaultGradoCalidadFuenteCuarentena.setsubjectCriteria(eval.getSubjectCriteria());
		outputsdefaultGradoCalidadFuenteCuarentena.setobjectCriteria(eval.getObjectCriteria());
		outputsdefaultGradoCalidadFuenteCuarentena.setdeclaredQuality(eiInspeccionarCalidadFuenteCuarentena.getdeclaredQuality());

		outputsdefaultGradoCalidadFuenteCuarentena.setdata(inspeccion);

		System.out.println("Inspector perfect: "+eval.getSubjectCriteria()+ " known value :"+eiInspeccionarCalidadFuenteCuarentena.getdeclaredQuality());
	}

}
