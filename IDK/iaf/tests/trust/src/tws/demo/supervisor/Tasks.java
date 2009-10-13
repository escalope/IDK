/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tws.demo.supervisor;

import ingenias.editor.entities.RuntimeConversation;
import ingenias.jade.components.TaskOutput;
import ingenias.jade.components.VisualizationAppInit;
import ingenias.jade.mental.AgentReputationQuery;

import ingenias.jade.mental.SourceToTesting;

import ingenias.jade.mental.SourceQualityDegree;
import ingenias.jade.mental.QualityDegreeOfSourceInTesting;

import ingenias.jade.mental.InspectQualityOfSourceInTesting;

import ingenias.jade.mental.InspectQualitySource;
import ingenias.jade.mental.SourceInfoProposal;
import ingenias.jade.mental.DeniedProposal;
import ingenias.jade.mental.AgentReputation;
import ingenias.jade.mental.AgentReputationInfoNeeded;
import ingenias.jade.mental.SourceToProcess;
import ingenias.jade.mental.TrustInformation;
import java.util.Vector;
import regretsystem.EvaluationValue;
import regretsystem.ExpectedQuality;
import regretsystem.ITrustCalculation;
import regretsystem.ReGreTServicesFactory;
import regretsystem.Trust;
import regretsystem.WitnessTrust;
import tws.demo.SourceInfo;
import tws.demo.AlphaInspection;
import tws.demo.ReputationInfo;

/**
 *
 * @author carlos
 */
public class Tasks {

    private static ExpectedQuality expectedQuality = null;


    static {
        expectedQuality = new ExpectedQuality();
        expectedQuality.setObjectGoodQualityValue(0.7);
        expectedQuality.setProcessGoodQualityValue(0.55);
        expectedQuality.setSubjectGoodQualityValue(0.3);

    }

    public static void initDataSimulacion(TrustInformation vcr) {
        ReGreTInfo regret = new ReGreTInfo();

        vcr.setdata(regret);
    }

    public static void addSourceIntoSystemTask(String id, SourceToProcess eiFuenteParaProcesado,
    		InspectQualitySource outputsdefaultSolicitudInspeccionCalidad,
            RuntimeConversation outputsdefaultPeticionIntroducirFuente,
            ingenias.jade.mental.NewSource outputsdefaultFuenteNueva,
            ingenias.jade.mental.InspectQualitySource outputsdefaultInspeccionarCalidadFuente,
            ingenias.jade.mental.AcceptedProposal outputsdefaultPropuestaAceptada,
            TaskOutput outputsdefault) {
        outputsdefaultInspeccionarCalidadFuente.setdata(eiFuenteParaProcesado.getdata());
        outputsdefaultPropuestaAceptada.setdata(eiFuenteParaProcesado.getdata());
        outputsdefaultFuenteNueva.setdata(eiFuenteParaProcesado.getdata());
        VisualizationAppInit.getInstance().update(0, id + ": Introduciendo fuente en el sistema");

    }

    public static void processReputationQueryTaskTask(String id, AgentReputationQuery eiAgentReputationQuery,
            TrustInformation eiValoresConfianzaReputacion,
            AgentReputation outputsdefaultReputacionAgente,
            TaskOutput outputsdefault) {

        Object data = eiValoresConfianzaReputacion.getdata();
        if (data == null) {
            data = new ReGreTInfo();
            eiValoresConfianzaReputacion.setdata(data);
        }

        ReGreTInfo regret = (ReGreTInfo) data;
        String idAgente = (String) eiAgentReputationQuery.getdata();
        Trust trustAgente = regret.getConfianzas().get(idAgente);
        if (trustAgente == null) {
            //outputsdefault.removeEntity(outputsdefaultReputacionAgente);
            outputsdefaultReputacionAgente.setdata(null);
            VisualizationAppInit.getInstance().update(0, id + ": No conozco al agente " + idAgente);
        } else {
            ReputationInfo reputacion = new ReputationInfo();
            WitnessTrust wt = new WitnessTrust();
            wt.setObject(trustAgente.getObjectCriteriaGoodQuality());
            wt.setProcess(trustAgente.getProcessCriteriaGoodQuality());
            wt.setSubject(trustAgente.getSubjectCriteriaGoodQuality());
            wt.setWitnessId(id);
            reputacion.setCollaboradorId(idAgente);
            reputacion.setTrust(wt);
            outputsdefaultReputacionAgente.setdata(reputacion);
            VisualizationAppInit.getInstance().update(0, id + ": Conozco al agente " + idAgente + ", Trust Subject:" + wt.getSubjectCriteriaGoodQuality().getValue() + "%" + wt.getSubjectCriteriaGoodQuality().getReliability());
        }

    }

    public static void processReceivedProposalTaskTask(String id, TrustInformation eiValoresConfianzaReputacion,
            SourceToTesting outputsdefaultFuenteParaCuarentena,
            DeniedProposal outputsdefaultPropuestaRechazada,
            SourceToProcess outputsdefaultFuenteParaProcesado,
            SourceInfoProposal eiPropuestaFuente,
            AgentReputationInfoNeeded outputsdefaultReputacionAgenteNecesaria,
            TaskOutput outputsdefault) {


        Object data = eiValoresConfianzaReputacion.getdata();
        if (data == null) {
            data = new ReGreTInfo();
            eiValoresConfianzaReputacion.setdata(data);
        }

        ReGreTInfo regret = (ReGreTInfo) data;


        //   RuntimeConversation realizacionPropuesta = (RuntimeConversation) outputsdefault.getEntityByType("RealizacionPropuesta");
        //   String colaborador = realizacionPropuesta.getInitiator();
        SourceInfo fuente = (SourceInfo) eiPropuestaFuente.getdata();
        System.out.println("Propuesta recibida de:" + fuente.getCollaboradorId());
        VisualizationAppInit.getInstance().update(0, id + ": Propuesta recibida de:" + fuente.getCollaboradorId());
        //  fuente.setColaboradorId(colaborador);
        String colaborador = fuente.getCollaboradorId();
        outputsdefaultReputacionAgenteNecesaria.setdata(colaborador);

        AlphaInspection inspeccion = new AlphaInspection();
        inspeccion.setFuenteInfo(fuente);
        boolean necesitaInspAlfa = false;

        if (!regret.getConfianzas().containsKey(colaborador)) {
            regret.getConfianzas().put(colaborador, new Trust());
            regret.getOdb().put(colaborador, new Vector<EvaluationValue>());
            regret.getIdb().put(colaborador, new Vector<WitnessTrust>());

            //Fuente para cuarentena pues no tengo ni un dato
            inspeccion.setDeObjectCriteria(true);
            inspeccion.setDeProcessCriteria(true);
            inspeccion.setDeSubjectCriteria(true);
            necesitaInspAlfa = true;

            VisualizationAppInit.getInstance().update(0, id + ": No hay datos de: " + fuente.getCollaboradorId());

        } else {

            Trust trust = regret.getConfianzas().get(colaborador);

            assert VisualizationAppInit.getInstance()!=null;
            assert fuente!=null;
            assert fuente.getCollaboradorId()!=null;
            assert trust!=null;

          //  VisualizationAppInit.getInstance().update(0, id + ": Trust en Subject-criteria de: " + fuente.getColaboradorId() + " " +
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
            VisualizationAppInit.getInstance().update(0, id + ": No se le aplica filtro a " + fuente.getData());
            VisualizationAppInit.getInstance().update(0, id + ": fuente " + fuente.getData()+" ACEPTADA");
            outputsdefault.removeEntity(outputsdefaultPropuestaRechazada);
            outputsdefault.removeEntity(outputsdefaultFuenteParaCuarentena);
            outputsdefaultFuenteParaProcesado.setdata(fuente);
        } else {
            VisualizationAppInit.getInstance().update(0, id + ": Inspeccion alfa de: " + fuente.getData());
            outputsdefault.removeEntity(outputsdefaultPropuestaRechazada);
            outputsdefault.removeEntity(outputsdefaultFuenteParaProcesado);
            outputsdefaultFuenteParaCuarentena.setdata(inspeccion);
        }
    }

    public static void makeReputationRequestTaskTask(String id, AgentReputationInfoNeeded eiReputacionAgenteNecesaria,
            RuntimeConversation outputsdefaultConsultaReputacion,
            AgentReputationQuery outputsdefaultAgentReputationQuery,
            TaskOutput outputsdefault) {
        VisualizationAppInit.getInstance().update(0, id + ": Realizando pregunta de reputacion para:" + eiReputacionAgenteNecesaria.getdata());
        outputsdefaultAgentReputationQuery.setdata(eiReputacionAgenteNecesaria.getdata());
    }

    public static void updateReputationInfoTaskTask(String id, AgentReputation eiReputacionAgente,
            TrustInformation eiValoresConfianzaReputacion) {

        if(eiReputacionAgente.getdata()==null) return;

        ReputationInfo reputacion = (ReputationInfo) eiReputacionAgente.getdata();
        if(reputacion.getTrust().getWitnessId().equals(id)) return;

        VisualizationAppInit.getInstance().update(0, id + ": Reputacion recibida para:" + reputacion.getCollaboradorId() + " " + reputacion.getTrust().getSubjectCriteriaGoodQuality().getValue() + "%" + reputacion.getTrust().getSubjectCriteriaGoodQuality().getReliability());

        ReGreTInfo regret = (ReGreTInfo) eiValoresConfianzaReputacion.getdata();
        Vector<WitnessTrust> wts = regret.getIdb().get(reputacion.getCollaboradorId());
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
                reputacion.getCollaboradorId());
        regret.getConfianzas().put(reputacion.getCollaboradorId(), tr);

        VisualizationAppInit.getInstance().update(0, id + ": Nuevo Trust en Subject-criteria para:" + reputacion.getCollaboradorId() + " " + tr.getSubjectCriteriaGoodQuality().getValue() + "%" + tr.getSubjectCriteriaGoodQuality().getReliability());

    }

    public static void processResultAlphaQualityInspectionTask(String id, QualityDegreeOfSourceInTesting eiGradoCalidadFuenteCuarentena,
            TrustInformation eiValoresConfianzaReputacion,
            DeniedProposal outputsdefaultPropuestaRechazada,
            SourceToProcess outputsdefaultFuenteParaProcesado,
            TaskOutput outputsdefault) {

        AlphaInspection inspeccion = (AlphaInspection) eiGradoCalidadFuenteCuarentena.getdata();
        VisualizationAppInit.getInstance().update(0, id + ": Nuevo dato de inspeccion alfa para:" + inspeccion.getFuenteInfo().getCollaboradorId());
        EvaluationValue eval = inspeccion.getFuenteInfo().getEval();
        System.out.println("Supervisor: Evaluacion:"+eval.getSubjectCriteria());
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
            VisualizationAppInit.getInstance().update(0, id + ": Fuente " + inspeccion.getFuenteInfo().getData() + " de " + inspeccion.getFuenteInfo().getCollaboradorId() + " ACEPTADA");
            outputsdefaultFuenteParaProcesado.setdata(inspeccion.getFuenteInfo());
            outputsdefault.removeEntity(outputsdefaultPropuestaRechazada);
        } else {
            VisualizationAppInit.getInstance().update(0, id + ": Fuente " + inspeccion.getFuenteInfo().getData() + " de " + inspeccion.getFuenteInfo().getCollaboradorId() + " RECHAZADA");
            //Actualizar la confianza

            if (!regret.getOdb().containsKey(inspeccion.getFuenteInfo().getCollaboradorId())) {
                regret.getOdb().put(inspeccion.getFuenteInfo().getCollaboradorId(), new Vector<EvaluationValue>());
            }

            regret.getOdb().get(inspeccion.getFuenteInfo().getCollaboradorId()).add(eval);
            ITrustCalculation tc = ReGreTServicesFactory.createTrustCalculationService();
            tc.configure(expectedQuality, 3);
            Trust tr = tc.calculate(regret.getOdb(),
                    regret.getIdb(),
                    regret.getOdb().size(),
                    inspeccion.getFuenteInfo().getCollaboradorId());
            regret.getConfianzas().put(inspeccion.getFuenteInfo().getCollaboradorId(), tr);

            VisualizationAppInit.getInstance().update(0, id + ": Nuevo Trust en Subject-criteria para:" + inspeccion.getFuenteInfo().getCollaboradorId() + " " + tr.getSubjectCriteriaGoodQuality().getValue() + "%" + tr.getSubjectCriteriaGoodQuality().getReliability());
            outputsdefaultPropuestaRechazada.setdata(inspeccion.getFuenteInfo());
            outputsdefault.removeEntity(outputsdefaultFuenteParaProcesado);

        }
    }

    public static void processInspectionResultTask(String id, SourceQualityDegree eiGradoCalidadFuente,
            TrustInformation eiValoresConfianzaReputacion,
            TaskOutput outputsdefault) {
        ReGreTInfo regret = (ReGreTInfo) eiValoresConfianzaReputacion.getdata();
        SourceInfo fuente = (SourceInfo) eiGradoCalidadFuente.getdata();

        VisualizationAppInit.getInstance().update(0, id + ": Nuevo dato de inspeccion para:" + fuente.getCollaboradorId());

        if (!regret.getOdb().containsKey(fuente.getCollaboradorId())) {
            regret.getOdb().put(fuente.getCollaboradorId(), new Vector<EvaluationValue>());
        }

        System.out.println("Supervisor: Evaluacion:"+fuente.getEval().getSubjectCriteria());

        regret.getOdb().get(fuente.getCollaboradorId()).add(fuente.getEval());
        ITrustCalculation tc = ReGreTServicesFactory.createTrustCalculationService();
        tc.configure(expectedQuality, 3);
        Trust tr = tc.calculate(regret.getOdb(),
                regret.getIdb(),
                regret.getOdb().size(),
                fuente.getCollaboradorId());
        regret.getConfianzas().put(fuente.getCollaboradorId(), tr);
        VisualizationAppInit.getInstance().update(0, id + ": Nuevo Trust en Subject-criteria para:" + fuente.getCollaboradorId() + " " + tr.getSubjectCriteriaGoodQuality().getValue() + "%" + tr.getSubjectCriteriaGoodQuality().getReliability());
    }

    public static void requestAlphaQualityInspectionTask(String id,
            SourceToTesting eiFuenteParaCuarentena,
            RuntimeConversation outputsdefaultSolicitudInspeccionAlfaCalidad,
            InspectQualityOfSourceInTesting outputsdefaultInspeccionarCalidadFuenteCuarentena,
            TaskOutput outputsdefault) {
        outputsdefaultInspeccionarCalidadFuenteCuarentena.setdata(eiFuenteParaCuarentena.getdata());
    }
}
