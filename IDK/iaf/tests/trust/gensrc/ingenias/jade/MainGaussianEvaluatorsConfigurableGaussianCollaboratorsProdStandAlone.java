

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
import java.net.Socket;
import java.net.UnknownHostException;

import jade.core.*;
import ingenias.jade.mental.*;

import ingenias.jade.graphics.MainInteractionManager;


public class MainGaussianEvaluatorsConfigurableGaussianCollaboratorsProdStandAlone {


  public static void main(String args[]) throws Exception{
		IAFProperties.setGraphicsOn(false);

		new Thread(){
			public void run(){
				String[] args1=new String[4];
				args1[0]="-port";
				args1[1]="60000";
				args1[2]="-file-dir";
				args1[3]="jade/";								 				
				jade.Boot.main(args1);		
			}
		}.start();

        // Get a hold on JADE runtime
        jade.core.Runtime rt = jade.core.Runtime.instance();

        // Exit the JVM when there are no more containers around
        rt.setCloseVM(true);

        // Create a default profile
        Profile p = new ProfileImpl();
        p.setParameter("preload","a*");
        p.setParameter(Profile.MAIN_PORT, "60000");
        p.setParameter(Profile.FILE_DIR, "jade/");
        
        
        // Waits for JADE to start
        boolean notConnected=true;
		
		while (notConnected){			
				try {
					Socket s=new Socket("localhost",Integer.parseInt("60000"));
					notConnected=false;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					
					System.err.println("Error: "+e.getMessage());
					System.err.println("Reconnecting in one second");
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}

		}

        // Create a new non-main container, connecting to the default
        // main container (i.e. on this host, port 1099)
        final jade.wrapper.AgentContainer ac = rt.createAgentContainer(p);

{
        // Create a new agent
        final jade.wrapper.AgentController agcGaussianSourcesAlfaInspector_0GaussianSourceAlphaInspectorsDU = ac.createNewAgent("GaussianSourcesAlfaInspector_0GaussianSourceAlphaInspectorsDU",
            "ingenias.jade.agents.GaussianSourcesAlfaInspectorJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up GaussianSourcesAlfaInspector_0GaussianSourceAlphaInspectorsDU...");
              agcGaussianSourcesAlfaInspector_0GaussianSourceAlphaInspectorsDU.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcConfigurableAutonomousCollaborator_0ConfigurableCollaboratorAllGoodGaussianDU = ac.createNewAgent("ConfigurableAutonomousCollaborator_0ConfigurableCollaboratorAllGoodGaussianDU",
            "ingenias.jade.agents.ConfigurableAutonomousCollaboratorJADEAgent", new Object[0]);	
	
	{ PreConfiguredInformationSourceList ment=new PreConfiguredInformationSourceList();
	   
	     ment.setdistribution("good");
	   
	     ment.setnumberOfDocuments(20);
	   	   
	   agcConfigurableAutonomousCollaborator_0ConfigurableCollaboratorAllGoodGaussianDU.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up ConfigurableAutonomousCollaborator_0ConfigurableCollaboratorAllGoodGaussianDU...");
              agcConfigurableAutonomousCollaborator_0ConfigurableCollaboratorAllGoodGaussianDU.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcConfigurableAutonomousCollaborator_1ConfigurableCollaboratorAllGoodGaussianDU = ac.createNewAgent("ConfigurableAutonomousCollaborator_1ConfigurableCollaboratorAllGoodGaussianDU",
            "ingenias.jade.agents.ConfigurableAutonomousCollaboratorJADEAgent", new Object[0]);	
	
	{ PreConfiguredInformationSourceList ment=new PreConfiguredInformationSourceList();
	   
	     ment.setdistribution("good");
	   
	     ment.setnumberOfDocuments(20);
	   	   
	   agcConfigurableAutonomousCollaborator_1ConfigurableCollaboratorAllGoodGaussianDU.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up ConfigurableAutonomousCollaborator_1ConfigurableCollaboratorAllGoodGaussianDU...");
              agcConfigurableAutonomousCollaborator_1ConfigurableCollaboratorAllGoodGaussianDU.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPerfectSourceQualityInspector_0PerfectSourceInspectorsDU = ac.createNewAgent("PerfectSourceQualityInspector_0PerfectSourceInspectorsDU",
            "ingenias.jade.agents.PerfectSourceQualityInspectorJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up PerfectSourceQualityInspector_0PerfectSourceInspectorsDU...");
              agcPerfectSourceQualityInspector_0PerfectSourceInspectorsDU.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcPerfectSourceQualityInspector_1PerfectSourceInspectorsDU = ac.createNewAgent("PerfectSourceQualityInspector_1PerfectSourceInspectorsDU",
            "ingenias.jade.agents.PerfectSourceQualityInspectorJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up PerfectSourceQualityInspector_1PerfectSourceInspectorsDU...");
              agcPerfectSourceQualityInspector_1PerfectSourceInspectorsDU.start();
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

}
	      MainInteractionManager.getInstance().setTitle("node GaussianEvaluatorsConfigurableGaussianCollaborators");
     }
}

 