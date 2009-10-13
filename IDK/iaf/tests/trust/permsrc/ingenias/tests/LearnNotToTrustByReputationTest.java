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

import org.junit.Test;

import ingenias.jade.MentalStateManager;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.testing.*;
import ingenias.jade.IAFProperties;
import ingenias.jade.MentalStateProcessor;
import ingenias.jade.mental.NewCycleEvent;
import ingenias.jade.mental.TrustInformation;
import tws.demo.supervisor.ReGreTInfo;
import tws.demo.testing.Assertion;
import static tws.demo.testing.TestUtils2.*;
import static org.junit.Assert.*;

public class LearnNotToTrustByReputationTest {

    @Test
    public void testDemo() throws Exception {

        int delay = 4000;
        IAFProperties.setGarbageCollectionInterval(100);

        // Involved agent local ids for this test are:

        // -AutonomousColaborator_0AutonomousColaboratorDU

        // -SourcesSupervisor_0SourcesSupervisorDU

        // -SourcesSupervisor_1SourcesSupervisorDU

        // -SourcesManager_0SourcesManagerDU

        // -ResearcherAssistant_0ResearcherAssistantDU

        // -SourcesInspector_0SourcesInspectorDU

        // -SourcesAlfaInspector_0SourcesAlfaInspectorDU

        // write here the agent id whose
        // mental state manager you want to get access to
        // MentalStateManager msm = MSMRepository.getInstance().get("MY_AGENT_ID"); // provides access to the
        // For current agents, these are the variables containing their mental states

        MentalStateManager msmA = MSMRepository.getInstance().waitFor("AutonomousCollaborator_0AutonomousCollaboratorDU");
        MentalStateProcessor mspA = MSPRepository.getInstance().waitFor("AutonomousCollaborator_0AutonomousCollaboratorDU");
        TestUtils.waitForAgentInitialised(mspA);


        final MentalStateManager msmB = MSMRepository.getInstance().waitFor("SourcesSupervisor_0SourcesSupervisorDU");
        MentalStateProcessor mspB = MSPRepository.getInstance().waitFor("SourcesSupervisor_0SourcesSupervisorDU");
        TestUtils.waitForAgentInitialised(mspB);


        final MentalStateManager msmB1 = MSMRepository.getInstance().waitFor("SourcesSupervisor_1SourcesSupervisorDU");
        MentalStateProcessor mspB1 = MSPRepository.getInstance().waitFor("SourcesSupervisor_1SourcesSupervisorDU");
        TestUtils.waitForAgentInitialised(mspB1);


        MainInteractionManager.goAutomatic();

        NewCycleEvent evento = new NewCycleEvent();
        evento.setdata("urn:fuente:2");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(delay);


        TestUtils.checkExistenceMEWithinMS(msmB, "TrustInformation", "SourcesSupervisor_0SourcesSupervisorDU", 1);

        evento = new NewCycleEvent();
        evento.setdata("urn:fuente:2");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(delay);

        evento = new NewCycleEvent();
        evento.setdata("urn:fuente:2");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(delay);

        evento = new NewCycleEvent();
        evento.setdata("urn:fuente:2");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(delay);

        evento = new NewCycleEvent();
        evento.setdata("_urn:fuente:2");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(delay);

        evento = new NewCycleEvent();
        evento.setdata("_urn:fuente:2");
        msmA.addMentalEntity(evento);

        waitAssertionUntil(new Assertion(){
            public boolean eval() {
                return msmB.getMentalEntityByType("TrustInformation")!=null && 
                        msmB1.getMentalEntityByType("TrustInformation")!=null;
            }
            public String failMessage() {
                return "";
            }
            
        }, 2*delay/1000);


        final TrustInformation vcr1 = (TrustInformation) msmB.getMentalEntityByType("TrustInformation").get(0);
        final TrustInformation vcr2 = (TrustInformation) msmB1.getMentalEntityByType("TrustInformation").get(0);

        waitAssertionUntil(new Assertion(){

            public boolean eval() {
                return vcr1.getdata()!=null && vcr2.getdata()!=null;
            }

            public String failMessage() {
                return "";
            }
        },2*delay/1000);
   
        final ReGreTInfo data1 = (ReGreTInfo) vcr1.getdata();
        final ReGreTInfo data2 = (ReGreTInfo) vcr2.getdata();


        waitAssertionUntil(new Assertion(){

            public boolean eval() {
                
                return data1.getOdb().get("AutonomousCollaborator_0AutonomousCollaboratorDU") != null
                        &&
                        data2.getOdb().get("AutonomousCollaborator_0AutonomousCollaboratorDU") != null;
            }

            public String failMessage() {
                return "";
            }
        },2*delay/1000);


        waitAssertionUntil(new Assertion(){

            public boolean eval() {
                return data1.getOdb().get("AutonomousCollaborator_0AutonomousCollaboratorDU").size()==4
                        &&
                        data2.getOdb().get("AutonomousCollaborator_0AutonomousCollaboratorDU").size()==2;
            }

            public String failMessage() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        },20);

        waitAssertionUntil(new Assertion(){

            public boolean eval() {
                boolean e=true;
                e=e&&data1.getConfianzas().get("AutonomousCollaborator_0AutonomousCollaboratorDU") != null;
                e=e&&data1.getConfianzas().get("AutonomousCollaborator_0AutonomousCollaboratorDU").getSubjectCriteriaGoodQuality().getReliability() >= 0.5;
                e=e&&data1.getConfianzas().get("AutonomousCollaborator_0AutonomousCollaboratorDU").getSubjectCriteriaGoodQuality().getValue() < 0;
                return e;
            }

            public String failMessage() {
                return "";
            }
        },12);

        waitAssertionUntil(new Assertion(){

            public boolean eval() {
                boolean e=true;
                e=e&&data2.getConfianzas().get("AutonomousCollaborator_0AutonomousCollaboratorDU") != null;
                e=e&&data2.getConfianzas().get("AutonomousCollaborator_0AutonomousCollaboratorDU").getSubjectCriteriaGoodQuality().getReliability() >= 0.5;
                e=e&&data2.getConfianzas().get("AutonomousCollaborator_0AutonomousCollaboratorDU").getSubjectCriteriaGoodQuality().getValue() < 0;
                return e;
            }

            public String failMessage() {
                return "";
            }

        },12);

    }
}

 