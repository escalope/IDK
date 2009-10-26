

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


package ingenias.jade;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import jade.core.*;
import ingenias.jade.mental.*;

import ingenias.jade.graphics.MainInteractionManager;


public class MainPerfectInformationConfigurableManualCollaborators {


  public static void main(String args[]) throws Exception{


        // Get a hold on JADE runtime
        jade.core.Runtime rt = jade.core.Runtime.instance();

        // Exit the JVM when there are no more containers around
        rt.setCloseVM(true);

        // Create a default profile
        Profile p = new ProfileImpl();
        p.setParameter("preload","a*");
        p.setParameter(Profile.MAIN_PORT, "60000");
        p.setParameter(Profile.FILE_DIR, "jade/");

        // Create a new non-main container, connecting to the default
        // main container (i.e. on this host, port 1099)
        final jade.wrapper.AgentContainer ac = rt.createAgentContainer(p);

{
        // Create a new agent
        final jade.wrapper.AgentController agcConfigurableAutonomousCollaborator_0ConfigurableCollaboratorsGoodAndBadManualDU = ac.createNewAgent("ConfigurableAutonomousCollaborator_0ConfigurableCollaboratorsGoodAndBadManualDU",
            "ingenias.jade.agents.ConfigurableAutonomousCollaboratorJADEAgent", new Object[0]);	
	
	{ PreConfiguredInformationSourceList ment=new PreConfiguredInformationSourceList();
	   
	     ment.setdeclaredQualityList(new Double[]{0.2,0.1,0.5,0.7,0.9,0.5,0.2,0.4,0.1});
	   	   
	   agcConfigurableAutonomousCollaborator_0ConfigurableCollaboratorsGoodAndBadManualDU.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up ConfigurableAutonomousCollaborator_0ConfigurableCollaboratorsGoodAndBadManualDU...");
              agcConfigurableAutonomousCollaborator_0ConfigurableCollaboratorsGoodAndBadManualDU.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcConfigurableAutonomousCollaborator_1ConfigurableCollaboratorsGoodAndBadManualDU = ac.createNewAgent("ConfigurableAutonomousCollaborator_1ConfigurableCollaboratorsGoodAndBadManualDU",
            "ingenias.jade.agents.ConfigurableAutonomousCollaboratorJADEAgent", new Object[0]);	
	
	{ PreConfiguredInformationSourceList ment=new PreConfiguredInformationSourceList();
	   
	     ment.setdeclaredQualityList(new Double[]{0.2,0.1,0.5,0.7,0.9,0.5,0.2,0.4,0.1});
	   	   
	   agcConfigurableAutonomousCollaborator_1ConfigurableCollaboratorsGoodAndBadManualDU.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up ConfigurableAutonomousCollaborator_1ConfigurableCollaboratorsGoodAndBadManualDU...");
              agcConfigurableAutonomousCollaborator_1ConfigurableCollaboratorsGoodAndBadManualDU.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPerfectSourcesAlfaInspector_0PerefectSourceAlphaInspectorsDU = ac.createNewAgent("PerfectSourcesAlfaInspector_0PerefectSourceAlphaInspectorsDU",
            "ingenias.jade.agents.PerfectSourcesAlfaInspectorJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up PerfectSourcesAlfaInspector_0PerefectSourceAlphaInspectorsDU...");
              agcPerfectSourcesAlfaInspector_0PerefectSourceAlphaInspectorsDU.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSourcesSupervisor_0SourcesSupervisorDU = ac.createNewAgent("SourcesSupervisor_0SourcesSupervisorDU",
            "ingenias.jade.agents.SourcesSupervisorJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SourcesSupervisor_0SourcesSupervisorDU...");
              agcSourcesSupervisor_0SourcesSupervisorDU.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSourcesSupervisor_1SourcesSupervisorDU = ac.createNewAgent("SourcesSupervisor_1SourcesSupervisorDU",
            "ingenias.jade.agents.SourcesSupervisorJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SourcesSupervisor_1SourcesSupervisorDU...");
              agcSourcesSupervisor_1SourcesSupervisorDU.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSourcesManager_0SourcesManagerDU = ac.createNewAgent("SourcesManager_0SourcesManagerDU",
            "ingenias.jade.agents.SourcesManagerJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SourcesManager_0SourcesManagerDU...");
              agcSourcesManager_0SourcesManagerDU.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcResearcherAssistant_0ResearcherAssistantDU = ac.createNewAgent("ResearcherAssistant_0ResearcherAssistantDU",
            "ingenias.jade.agents.ResearcherAssistantJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up ResearcherAssistant_0ResearcherAssistantDU...");
              agcResearcherAssistant_0ResearcherAssistantDU.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSourcesInspector_0SourcesInspectorDU = ac.createNewAgent("SourcesInspector_0SourcesInspectorDU",
            "ingenias.jade.agents.SourcesInspectorJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SourcesInspector_0SourcesInspectorDU...");
              agcSourcesInspector_0SourcesInspectorDU.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

}
	      MainInteractionManager.getInstance().setTitle("node PerfectInformationConfigurableManualCollaborators");
     }
}

 