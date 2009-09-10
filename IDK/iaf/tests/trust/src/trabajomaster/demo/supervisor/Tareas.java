/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajomaster.demo.supervisor;

import ingenias.editor.entities.RuntimeConversation;
import ingenias.jade.components.TaskOutput;
import ingenias.jade.components.VisualizacionAppInit;
import ingenias.jade.mental.AgenteDesconocido;
import ingenias.jade.mental.ConsultaReputacionAgente;

import ingenias.jade.mental.FuenteParaCuarentena;

import ingenias.jade.mental.GradoCalidadFuente;
import ingenias.jade.mental.GradoCalidadFuenteCuarentena;

import ingenias.jade.mental.InspeccionarCalidadFuenteCuarentena;

import ingenias.jade.mental.InspectQualitySource;
import ingenias.jade.mental.PropuestaFuente;
import ingenias.jade.mental.PropuestaRechazada;
import ingenias.jade.mental.ReputacionAgente;
import ingenias.jade.mental.ReputacionAgenteNecesaria;
import ingenias.jade.mental.SourceToProcess;
import ingenias.jade.mental.ValoresConfianzaReputacion;
import java.util.Vector;
import regretsystem.EvaluationValue;
import regretsystem.ExpectedQuality;
import regretsystem.ITrustCalculation;
import regretsystem.ReGreTServicesFactory;
import regretsystem.Trust;
import regretsystem.WitnessTrust;
import trabajomaster.demo.FuenteInfo;
import trabajomaster.demo.InspeccionAlfa;
import trabajomaster.demo.ReputacionInfo;

/**
 *
 * @author carlos
 */
public class Tareas {

    private static ExpectedQuality expectedQuality = null;


    static {
        expectedQuality = new ExpectedQuality();
        expectedQuality.setObjectGoodQualityValue(0.7);
        expectedQuality.setProcessGoodQualityValue(0.55);
        expectedQuality.setSubjectGoodQualityValue(0.3);

    }

    public static void initDataSimulacion(ValoresConfianzaReputacion vcr) {
        ReGreTInfo regret = new ReGreTInfo();

        vcr.setdata(regret);
    }

    public static void introducirFuenteSistemaTask(String id, SourceToProcess eiFuenteParaProcesado,
    		InspectQualitySource outputsdefaultSolicitudInspeccionCalidad,
            RuntimeConversation outputsdefaultPeticionIntroducirFuente,
            ingenias.jade.mental.NewSource outputsdefaultFuenteNueva,
            ingenias.jade.mental.InspectQualitySource outputsdefaultInspeccionarCalidadFuente,
            ingenias.jade.mental.AcceptedProposal outputsdefaultPropuestaAceptada,
            TaskOutput outputsdefault) {
        outputsdefaultInspeccionarCalidadFuente.setdata(eiFuenteParaProcesado.getdata());
        outputsdefaultPropuestaAceptada.setdata(eiFuenteParaProcesado.getdata());
        outputsdefaultFuenteNueva.setdata(eiFuenteParaProcesado.getdata());
        VisualizacionAppInit.getInstance().update(0, id + ": Introduciendo fuente en el sistema");

    }

    public static void procesarConsultaReputacionTask(String id, ConsultaReputacionAgente eiConsultaReputacionAgente,
            ValoresConfianzaReputacion eiValoresConfianzaReputacion,
            ReputacionAgente outputsdefaultReputacionAgente,
            AgenteDesconocido outputsdefaultAgenteDesconocido,
            TaskOutput outputsdefault) {

        Object data = eiValoresConfianzaReputacion.getdata();
        if (data == null) {
            data = new ReGreTInfo();
            eiValoresConfianzaReputacion.setdata(data);
        }

        ReGreTInfo regret = (ReGreTInfo) data;
        String idAgente = (String) eiConsultaReputacionAgente.getdata();
        Trust trustAgente = regret.getConfianzas().get(idAgente);
        if (trustAgente == null) {
            outputsdefault.removeEntity(outputsdefaultReputacionAgente);
            outputsdefaultAgenteDesconocido.setdata(idAgente);
            VisualizacionAppInit.getInstance().update(0, id + ": No conozco al agente " + idAgente);
        } else {
            outputsdefault.removeEntity(outputsdefaultAgenteDesconocido);
            ReputacionInfo reputacion = new ReputacionInfo();
            WitnessTrust wt = new WitnessTrust();
            wt.setObject(trustAgente.getObjectCriteriaGoodQuality());
            wt.setProcess(trustAgente.getProcessCriteriaGoodQuality());
            wt.setSubject(trustAgente.getSubjectCriteriaGoodQuality());
            wt.setWitnessId(id);
            reputacion.setColaboradorId(idAgente);
            reputacion.setTrust(wt);
            outputsdefaultReputacionAgente.setdata(reputacion);
            VisualizacionAppInit.getInstance().update(0, id + ": Conozco al agente " + idAgente + ", Trust Subject:" + wt.getSubjectCriteriaGoodQuality().getValue() + "%" + wt.getSubjectCriteriaGoodQuality().getReliability());
        }

    }

    public static void procesarPropuestaRecibidaTask(String id, ValoresConfianzaReputacion eiValoresConfianzaReputacion,
            FuenteParaCuarentena outputsdefaultFuenteParaCuarentena,
            PropuestaRechazada outputsdefaultPropuestaRechazada,
            SourceToProcess outputsdefaultFuenteParaProcesado,
            PropuestaFuente eiPropuestaFuente,
            ReputacionAgenteNecesaria outputsdefaultReputacionAgenteNecesaria,
            TaskOutput outputsdefault) {


        Object data = eiValoresConfianzaReputacion.getdata();
        if (data == null) {
            data = new ReGreTInfo();
            eiValoresConfianzaReputacion.setdata(data);
        }

        ReGreTInfo regret = (ReGreTInfo) data;


        //   RuntimeConversation realizacionPropuesta = (RuntimeConversation) outputsdefault.getEntityByType("RealizacionPropuesta");
        //   String colaborador = realizacionPropuesta.getInitiator();
        FuenteInfo fuente = (FuenteInfo) eiPropuestaFuente.getdata();
        System.out.println("Propuesta recibida de:" + fuente.getColaboradorId());
        VisualizacionAppInit.getInstance().update(0, id + ": Propuesta recibida de:" + fuente.getColaboradorId());
        //  fuente.setColaboradorId(colaborador);
        String colaborador = fuente.getColaboradorId();
        outputsdefaultReputacionAgenteNecesaria.setdata(colaborador);

        InspeccionAlfa inspeccion = new InspeccionAlfa();
        inspeccion.setFuenteInfo(fuente);
        boolean necesitaInspAlfa = false;

        if (!regret.getConfianzas().containsKey(colaborador)) {
            regret.getConfianzas().put(colaborador, null);
            regret.getOdb().put(colaborador, new Vector<EvaluationValue>());
            regret.getIdb().put(colaborador, new Vector<WitnessTrust>());

            //Fuente para cuarentena pues no tengo ni un dato
            inspeccion.setDeObjectCriteria(true);
            inspeccion.setDeProcessCriteria(true);
            inspeccion.setDeSubjectCriteria(true);
            necesitaInspAlfa = true;

            VisualizacionAppInit.getInstance().update(0, id + ": No hay datos de: " + fuente.getColaboradorId());

        } else {

            Trust trust = regret.getConfianzas().get(colaborador);

            assert VisualizacionAppInit.getInstance()!=null;
            assert fuente!=null;
            assert fuente.getColaboradorId()!=null;
            assert trust!=null;

          //  VisualizacionAppInit.getInstance().update(0, id + ": Trust en Subject-criteria de: " + fuente.getColaboradorId() + " " +
          //          trust.getSubjectCriteriaGoodQuality().getValue() + "%" + trust.getSubjectCriteriaGoodQuality().getReliability());

            if (trust.getObjectCriteriaGoodQuality().getReliability() >= 0.5) {
                if (trust.getObjectCriteriaGoodQuality().getValue() < 0) {
                    necesitaInspAlfa = true;
                    inspeccion.setDeObjectCriteria(true);
                }
            }
            if (trust.getSubjectCriteriaGoodQuality().getReliability() >= 0.5) {
                if (trust.getSubjectCriteriaGoodQuality().getValue() < 0) {
                    necesitaInspAlfa = true;
                    inspeccion.setDeSubjectCriteria(true);
                }
            }
            if (trust.getProcessCriteriaGoodQuality().getReliability() >= 0.5) {
                if (trust.getProcessCriteriaGoodQuality().getValue() < 0) {
                    necesitaInspAlfa = true;
                    inspeccion.setDeProcessCriteria(true);
                }
            }
        }

        if (!necesitaInspAlfa) {
            VisualizacionAppInit.getInstance().update(0, id + ": No se le aplica filtro a " + fuente.getDatos());
            VisualizacionAppInit.getInstance().update(0, id + ": fuente " + fuente.getDatos()+" ACEPTADA");
            outputsdefault.removeEntity(outputsdefaultPropuestaRechazada);
            outputsdefault.removeEntity(outputsdefaultFuenteParaCuarentena);
            outputsdefaultFuenteParaProcesado.setdata(fuente);
        } else {
            VisualizacionAppInit.getInstance().update(0, id + ": Inspeccion alfa de: " + fuente.getDatos());
            outputsdefault.removeEntity(outputsdefaultPropuestaRechazada);
            outputsdefault.removeEntity(outputsdefaultFuenteParaProcesado);
            outputsdefaultFuenteParaCuarentena.setdata(inspeccion);
        }
    }

    public static void realizarPreguntaReputacionTask(String id, ReputacionAgenteNecesaria eiReputacionAgenteNecesaria,
            RuntimeConversation outputsdefaultConsultaReputacion,
            ConsultaReputacionAgente outputsdefaultConsultaReputacionAgente,
            TaskOutput outputsdefault) {
        VisualizacionAppInit.getInstance().update(0, id + ": Realizando pregunta de reputacion para:" + eiReputacionAgenteNecesaria.getdata());
        outputsdefaultConsultaReputacionAgente.setdata(eiReputacionAgenteNecesaria.getdata());
    }

    public static void actualizarValorReputacionTask(String id, ReputacionAgente eiReputacionAgente,
            ValoresConfianzaReputacion eiValoresConfianzaReputacion) {
        ReputacionInfo reputacion = (ReputacionInfo) eiReputacionAgente.getdata();

        VisualizacionAppInit.getInstance().update(0, id + ": Reputacion recibida para:" + reputacion.getColaboradorId() + " " + reputacion.getTrust().getSubjectCriteriaGoodQuality().getValue() + "%" + reputacion.getTrust().getSubjectCriteriaGoodQuality().getReliability());

        ReGreTInfo regret = (ReGreTInfo) eiValoresConfianzaReputacion.getdata();
        Vector<WitnessTrust> wts = regret.getIdb().get(reputacion.getColaboradorId());
        boolean found = false;
        int i = 0;
        while (!found && i < wts.size()) {
            WitnessTrust otro = wts.get(i);
            if (otro.getWitnessId().equals(reputacion.getTrust().getWitnessId())) {
                wts.remove(i);
                found = true;
            }
            i++;
        }
        wts.add(reputacion.getTrust());

        ITrustCalculation tc = ReGreTServicesFactory.createTrustCalculationService();

        tc.configure(expectedQuality, 3);
        Trust tr = tc.calculate(regret.getOdb(),
                regret.getIdb(),
                regret.getOdb().size(),
                reputacion.getColaboradorId());
        regret.getConfianzas().put(reputacion.getColaboradorId(), tr);

        VisualizacionAppInit.getInstance().update(0, id + ": Nuevo Trust en Subject-criteria para:" + reputacion.getColaboradorId() + " " + tr.getSubjectCriteriaGoodQuality().getValue() + "%" + tr.getSubjectCriteriaGoodQuality().getReliability());

    }

    public static void procesarResultadoInspeccionAlfaTask(String id, GradoCalidadFuenteCuarentena eiGradoCalidadFuenteCuarentena,
            ValoresConfianzaReputacion eiValoresConfianzaReputacion,
            PropuestaRechazada outputsdefaultPropuestaRechazada,
            SourceToProcess outputsdefaultFuenteParaProcesado,
            TaskOutput outputsdefault) {

        InspeccionAlfa inspeccion = (InspeccionAlfa) eiGradoCalidadFuenteCuarentena.getdata();
        VisualizacionAppInit.getInstance().update(0, id + ": Nuevo dato de inspeccion alfa para:" + inspeccion.getFuenteInfo().getColaboradorId());
        EvaluationValue eval = inspeccion.getFuenteInfo().getEval();
        ReGreTInfo regret = (ReGreTInfo) eiValoresConfianzaReputacion.getdata();
        boolean esAceptada = true;

        if (inspeccion.isDeObjectCriteria()) {
            if (eval.getObjectCriteria() < expectedQuality.getObjectGoodQualityValue()) {
                esAceptada = esAceptada && false;
            }
        } else {
            eval.setObjectCriteria(expectedQuality.getObjectGoodQualityValue());
        }

        if (inspeccion.isDeProcessCriteria()) {
            if (eval.getProcessCriteria() < expectedQuality.getProcessGoodQualityValue()) {
                esAceptada = esAceptada && false;
            }
        } else {
            eval.setProcessCriteria(expectedQuality.getProcessGoodQualityValue());
        }

        if (inspeccion.isDeSubjectCriteria()) {
            if (eval.getSubjectCriteria() < expectedQuality.getSubjectGoodQualityValue()) {
                esAceptada = esAceptada && false;
            }
        } else {
            eval.setSubjectCriteria(expectedQuality.getSubjectGoodQualityValue());
        }


        //Respuesta
        if (esAceptada) {
            VisualizacionAppInit.getInstance().update(0, id + ": Fuente " + inspeccion.getFuenteInfo().getDatos() + " de " + inspeccion.getFuenteInfo().getColaboradorId() + " ACEPTADA");
            outputsdefaultFuenteParaProcesado.setdata(inspeccion.getFuenteInfo());
            outputsdefault.removeEntity(outputsdefaultPropuestaRechazada);
        } else {
            VisualizacionAppInit.getInstance().update(0, id + ": Fuente " + inspeccion.getFuenteInfo().getDatos() + " de " + inspeccion.getFuenteInfo().getColaboradorId() + " RECHAZADA");
            //Actualizar la confianza

            if (!regret.getOdb().containsKey(inspeccion.getFuenteInfo().getColaboradorId())) {
                regret.getOdb().put(inspeccion.getFuenteInfo().getColaboradorId(), new Vector<EvaluationValue>());
            }

            regret.getOdb().get(inspeccion.getFuenteInfo().getColaboradorId()).add(eval);
            ITrustCalculation tc = ReGreTServicesFactory.createTrustCalculationService();
            tc.configure(expectedQuality, 3);
            Trust tr = tc.calculate(regret.getOdb(),
                    regret.getIdb(),
                    regret.getOdb().size(),
                    inspeccion.getFuenteInfo().getColaboradorId());
            regret.getConfianzas().put(inspeccion.getFuenteInfo().getColaboradorId(), tr);

            VisualizacionAppInit.getInstance().update(0, id + ": Nuevo Trust en Subject-criteria para:" + inspeccion.getFuenteInfo().getColaboradorId() + " " + tr.getSubjectCriteriaGoodQuality().getValue() + "%" + tr.getSubjectCriteriaGoodQuality().getReliability());
            outputsdefaultPropuestaRechazada.setdata(inspeccion.getFuenteInfo());
            outputsdefault.removeEntity(outputsdefaultFuenteParaProcesado);

        }
    }

    public static void procesarResultadoInspeccionTask(String id, GradoCalidadFuente eiGradoCalidadFuente,
            ValoresConfianzaReputacion eiValoresConfianzaReputacion,
            TaskOutput outputsdefault) {
        ReGreTInfo regret = (ReGreTInfo) eiValoresConfianzaReputacion.getdata();
        FuenteInfo fuente = (FuenteInfo) eiGradoCalidadFuente.getdata();

        VisualizacionAppInit.getInstance().update(0, id + ": Nuevo dato de inspeccion para:" + fuente.getColaboradorId());

        if (!regret.getOdb().containsKey(fuente.getColaboradorId())) {
            regret.getOdb().put(fuente.getColaboradorId(), new Vector<EvaluationValue>());
        }

        regret.getOdb().get(fuente.getColaboradorId()).add(fuente.getEval());
        ITrustCalculation tc = ReGreTServicesFactory.createTrustCalculationService();
        tc.configure(expectedQuality, 3);
        Trust tr = tc.calculate(regret.getOdb(),
                regret.getIdb(),
                regret.getOdb().size(),
                fuente.getColaboradorId());
        regret.getConfianzas().put(fuente.getColaboradorId(), tr);
        VisualizacionAppInit.getInstance().update(0, id + ": Nuevo Trust en Subject-criteria para:" + fuente.getColaboradorId() + " " + tr.getSubjectCriteriaGoodQuality().getValue() + "%" + tr.getSubjectCriteriaGoodQuality().getReliability());
    }

    public static void solicitarInspeccionAlfaCalidadTask(String id,
            FuenteParaCuarentena eiFuenteParaCuarentena,
            RuntimeConversation outputsdefaultSolicitudInspeccionAlfaCalidad,
            InspeccionarCalidadFuenteCuarentena outputsdefaultInspeccionarCalidadFuenteCuarentena,
            TaskOutput outputsdefault) {
        outputsdefaultInspeccionarCalidadFuenteCuarentena.setdata(eiFuenteParaCuarentena.getdata());
    }
}
