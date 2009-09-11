/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajomaster.tests;

import ingenias.jade.MentalStateManager;
import ingenias.jade.MentalStateProcessor;
import ingenias.jade.mental.NuevaPropuestaIntroducidaEvento;
import ingenias.jade.mental.NuevoCicloEvento;
import ingenias.testing.MSMRepository;
import ingenias.testing.MSPRepository;
import ingenias.testing.TestUtils;
import org.junit.Test;

/**
 *
 * @author carlos
 */
public class CasosBasicosTest {

    @Test
    public void aprendeANoFiarse() throws Exception {

        MentalStateManager msmA = MSMRepository.getInstance().waitFor("AutonomousColaborator_0AutonomousColaboratorDU");
        MentalStateProcessor mspA = MSPRepository.getInstance().waitFor("AutonomousColaborator_0AutonomousColaboratorDU");
        TestUtils.waitForAgentInitialised(mspA);

        NuevoCicloEvento evento = new NuevoCicloEvento();
        evento.setdata("urn:fuente:2");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);        

        evento = new NuevoCicloEvento();
        evento.setdata("urn:fuente:2");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);

        evento = new NuevoCicloEvento();
        evento.setdata("urn:fuente:2");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);

        evento = new NuevoCicloEvento();
        evento.setdata("urn:fuente:2");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);





    }

    @Test
    public void aprendeAFiarse() throws Exception {

        MentalStateManager msmA = MSMRepository.getInstance().waitFor("ResearcherAssistant_0ResearcherAssistantDU");
        MentalStateProcessor mspA = MSPRepository.getInstance().waitFor("ResearcherAssistant_0ResearcherAssistantDU");
        TestUtils.waitForAgentInitialised(mspA);

        NuevaPropuestaIntroducidaEvento evento = new NuevaPropuestaIntroducidaEvento();
        evento.setdata("urn:fuente:3");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);


        evento = new NuevaPropuestaIntroducidaEvento();
        evento.setdata("urn:fuente:3");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);


        evento = new NuevaPropuestaIntroducidaEvento();
        evento.setdata("urn:fuente:3");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);


        evento = new NuevaPropuestaIntroducidaEvento();
        evento.setdata("urn:fuente:3");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);


    }

    @Test
    public void aprendeANoFiarsePorReputacion() throws Exception {

        MentalStateManager msmA = MSMRepository.getInstance().waitFor("AutonomousColaborator_0AutonomousColaboratorDU");
        MentalStateProcessor mspA = MSPRepository.getInstance().waitFor("AutonomousColaborator_0AutonomousColaboratorDU");
        TestUtils.waitForAgentInitialised(mspA);

        NuevoCicloEvento evento = new NuevoCicloEvento();
        evento.setdata("urn:fuente:2");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);


        evento = new NuevoCicloEvento();
        evento.setdata("urn:fuente:2");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);

        evento = new NuevoCicloEvento();
        evento.setdata("urn:fuente:2");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);

        evento = new NuevoCicloEvento();
        evento.setdata("urn:fuente:2");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);


        evento = new NuevoCicloEvento();
        evento.setdata("_urn:fuente:1");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);


        evento = new NuevoCicloEvento();
        evento.setdata("urn:fuente:2");
        msmA.addMentalEntity(evento);

        TestUtils.doNothing(4000);


    }
}
