

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
import jade.wrapper.StaleProxyException;
import ingenias.editor.entities.MentalEntity;
import ingenias.exception.InvalidEntity;
import ingenias.exception.TimeOut;
import ingenias.jade.MentalStateManager;
import ingenias.jade.MentalStateProcessor;
import ingenias.jade.components.Task;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.mental.*;
import ingenias.testing.*;
import ingenias.jade.IAFProperties;


public class Experiment_with_tedious_autonomous_collaborators extends BasicMASTest{


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
						
		try {
			HashSet<String> collaborators = getAgentsForRole("CollaboratorRole");
			Hashtable<String,MentalStateProcessor> msps=new Hashtable<String,MentalStateProcessor>(); 
			Hashtable<String,MentalStateManager> msms=new Hashtable<String,MentalStateManager>();
			for (String collaboratorId:collaborators){
				MentalStateProcessor mspA = MSPRepository.getInstance().waitFor(collaboratorId);
				TestUtils.waitForAgentInitialised(mspA);
				MentalStateManager msmA = MSMRepository.getInstance().waitFor(collaboratorId);
				generateEventSequence(msmA);
			}
									
			MainInteractionManager.goAutomatic();			
			
			
		} catch (TimeOut e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestUtils.doNothing(40000);
	}

	private void generateEventSequence(MentalStateManager msmA) {
		try{

			for (int k=0;k<10;k++){
				NewCycleEvent evento = new NewCycleEvent();
				evento.setdeclaredQuality(Math.abs(randomQuality.nextGaussian()));		
				BigInteger nvalue=new BigInteger(32, random);
				while (documentsTable.containsKey(nvalue.toString())){
					nvalue=new BigInteger(32, random);
				}
				evento.setdata(nvalue.toString()); // Idea from http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string-in-java
				documentsTable.put(nvalue.toString(), evento.getdeclaredQuality());
				msmA.addMentalEntity(evento);
				TestUtils.doNothing(2000);
				System.err.println(".................Requested event "+k+" ...............");
			}			
		} catch (InvalidEntity e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
							createEntry(logFile, "acceptedsource");
						}
						if (me.getType().equalsIgnoreCase("DeniedProposal")){
							// A new source has been included
							DeniedProposal ap=(DeniedProposal)me;
							createEntry(logFile, "deniedsource");
						}

						if (me.getType().equalsIgnoreCase("InspectQualityOfSourceInTesting")){
							// Requested a new evaluation
							InspectQualityOfSourceInTesting iq=(InspectQualityOfSourceInTesting)me;
							createEntry(logFile, "requestedevaluation");
						}
						if (me.getType().equalsIgnoreCase("QualityDegreeOfSourceInTesting")){
							// A new source has been evaluated 
							QualityDegreeOfSourceInTesting qd=(QualityDegreeOfSourceInTesting)me;
							if (qd.getdeclaredQuality()>qd.getsubjectCriteria()){
								createEntry(logFile, "badevaluated");
							} else
								createEntry(logFile, "wellevaluated");						
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
					createEntry(logFile, "trust,"+fuente.getCollaboradorId()+","+currentValue.getSubjectCriteriaGoodQuality().getReliability());
				}
				
				if (event.getEventKind().equals(EventKind.TaskExecutionFinished) &&
						event.getInvolvedTasks()[0].getType().equalsIgnoreCase("GenerateProposalTask")){
					// After terminating ProcessInspectionResult, trust values for each collaborator are recomputed
					Task task=event.getInvolvedTasks()[0];
					NewProposalToBeSent  eiproposal=(NewProposalToBeSent)task.getFirstInputOfType("NewProposalToBeSent");             
					createEntry(logFile, "createdproposal");
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

 