

/*
    Copyright (C) 2005 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 

    INGENIAS Agent Framework is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS Agent Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS Agent Framework; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 */


package ingenias.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;
import java.util.Vector;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import regretsystem.Trust;
import tws.demo.SourceInfo;
import tws.demo.supervisor.ReGreTInfo;

import jade.core.*;
import jade.domain.FIPAAgentManagement.Search;
import jade.wrapper.StaleProxyException;
import ingenias.editor.entities.MentalEntity;
import ingenias.exception.InvalidEntity;
import ingenias.exception.TimeOut;
import ingenias.jade.EventManager;
import ingenias.jade.MentalStateManager;
import ingenias.jade.MentalStateProcessor;
import ingenias.jade.components.Task;
import ingenias.jade.components.YellowPages;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.mental.*;
import ingenias.testing.*;
import ingenias.jade.IAFProperties;
import tws.demo.AlphaInspection;


public class Experimenting_with_evaluators extends BasicMASTest{

	private Hashtable<String,Double> documentsTable=new Hashtable<String,Double>();
	private SecureRandom random = new SecureRandom();
	private FileOutputStream logFile;
	private Date initiatingExperimentDate=new Date();
	private Random randomQuality=new Random();


	@Test
	public void testDemo(){			
		EventStateMachine esm = createAutomataForRegisteringEvents();		        
		EventSequenceChecker esc=new EventSequenceChecker();
		esc.registerEvaluator(esm);
		MainInteractionManager.goAutomatic();			
		TestUtils.doNothing(120000);
	}



	private void createEntry(FileOutputStream fos,String message){

		long milliseconds=(new Date().getTime()-initiatingExperimentDate.getTime());
		try {
			fos.write((""+milliseconds+","+message+"\n").getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private EventStateMachine createAutomataForRegisteringEvents() {
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("MMddyyHHmm");
		Date d=new Date();
		new File("logs").mkdir();
		try {
			this.logFile=new FileOutputStream("logs/simulationData"+df.format(d)+".log");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		State processingEvents= new State("Processing");
		EventHandler alleventsprocessingHandler=		new EventHandler(new State[]{processingEvents}){
			@Override
			public boolean evaluate(Event event,
					State fromState) {
				if (event.getEventKind().equals(EventKind.AddedNewEntityToConversation)){ 
					// entities produced by a task within a
					// conversation are automatically added to that conversation
					MentalEntity[] entities = event.getInvolvedMentalEntities();
					for (MentalEntity me:entities){
						if (me.getType().equalsIgnoreCase("AcceptedProposal")){
							// A new source has been included
							AcceptedProposal ap=(AcceptedProposal)me;
                                                        SourceInfo si = (SourceInfo) ap.getdata();
							createEntry(logFile, "acceptedsource,"+si.getCollaboradorId()+","+si.getData()
                                                                +","+si.getEval().getSubjectCriteria());
						}
						if (me.getType().equalsIgnoreCase("DeniedProposal")){
							// A new source has been included
							DeniedProposal ap=(DeniedProposal)me;
                                                        SourceInfo si = (SourceInfo) ap.getdata();
							createEntry(logFile, "deniedsource,"+si.getCollaboradorId()+","+si.getData()+","+si.getEval().getSubjectCriteria());
						}

						if (me.getType().equalsIgnoreCase("InspectQualityOfSourceInTesting")){
							// Requested a new evaluation
							InspectQualityOfSourceInTesting iq=(InspectQualityOfSourceInTesting)me;
                                                        AlphaInspection ai = (AlphaInspection) iq.getdata();
							createEntry(logFile, "requestedevaluation,"+ai.getFuenteInfo().getCollaboradorId()+","+ai.getFuenteInfo().getData());
						}
                                                if(me.getType().equalsIgnoreCase("SourceQualityDegree")){
                                                    SourceQualityDegree sqd = (SourceQualityDegree)me;
                                                    SourceInfo si = (SourceInfo)sqd.getdata();
                                                    createEntry(logFile,"evaluation,"+si.getCollaboradorId()+","+si.getData()+","+si.getEval().getSubjectCriteria()+","+sqd.getdeclaredQuality());

                                                }
						if (me.getType().equalsIgnoreCase("QualityDegreeOfSourceInTesting")){
							// A new source has been evaluated 
							QualityDegreeOfSourceInTesting qd=(QualityDegreeOfSourceInTesting)me;
                                                        AlphaInspection ai = (AlphaInspection) qd.getdata();
                                                        SourceInfo si = ai.getFuenteInfo();
                                                        createEntry(logFile, "testevaluation,"+si.getCollaboradorId()+","+si.getData()+","+si.getEval().getSubjectCriteria()+","+qd.getdeclaredQuality());
							//if (qd.getdeclaredQuality()>qd.getsubjectCriteria()){
							//	createEntry(logFile, "badevaluated,"+si.getCollaboradorId()+","+si.getData()+","+si.getEval().getSubjectCriteria()+","+qd.getdeclaredQuality());
							//} else
							//	createEntry(logFile, "wellevaluated,"+si.getCollaboradorId()+","+si.getData()+","+si.getEval().getSubjectCriteria()+","+qd.getdeclaredQuality());
						}						
					}					
				}

				if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
						event.getInvolvedTasks()[0].getType().equalsIgnoreCase("ProcessInspectionResult")){
					// After terminating ProcessInspectionResult, trust values for each collaborator are recomputed
					Task task=event.getInvolvedTasks()[0];
					SourceQualityDegree  eiSourceQualityDegree=(SourceQualityDegree)task.getFirstInputOfType("SourceQualityDegree");             

					TrustInformation  eiTrustInformation=(TrustInformation)task.getFirstInputOfType("TrustInformation");
					ReGreTInfo regret = (ReGreTInfo) eiTrustInformation.getdata();
					SourceInfo fuente = (SourceInfo) eiSourceQualityDegree.getdata();
					Trust currentValue=regret.getConfianzas().get(fuente.getCollaboradorId());
					createEntry(logFile, "trust,"+fuente.getCollaboradorId()+","
                                                +currentValue.getSubjectCriteriaGoodQuality().getValue()+","
                                                +currentValue.getSubjectCriteriaGoodQuality().getReliability()+","
                                                +fuente.getData()+","
                                                +fuente.getEval().getSubjectCriteria());
				}

				if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
						event.getInvolvedTasks()[0].getType().equalsIgnoreCase("GenerateProposalTask")){
					// After terminating ProcessInspectionResult, trust values for each collaborator are recomputed
					Task task=event.getInvolvedTasks()[0];

					NewProposalToBeSent  eiproposal=(NewProposalToBeSent)task.getFirstInputOfType("NewProposalToBeSent");
					createEntry(logFile, "createdproposal,"+task.getAgentID()+","+eiproposal.getdata()+","+eiproposal.getdeclaredQuality());
				}


				return true; // Going into new states is irrelevant
			}
		};

		processingEvents.addTransition(alleventsprocessingHandler);
		EventStateMachine esm=new EventStateMachine(
				"Analysing all events",processingEvents,new State[]{processingEvents});
		return esm;
	}
}

