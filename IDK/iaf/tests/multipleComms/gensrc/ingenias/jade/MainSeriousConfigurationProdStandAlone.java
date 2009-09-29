

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


public class MainSeriousConfigurationProdStandAlone {


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
        final jade.wrapper.AgentController agcBuyerAgent_0multipleBuyers = ac.createNewAgent("BuyerAgent_0multipleBuyers",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CurrentAssistedAgent ment=new CurrentAssistedAgent();
	   
	     ment.setinterfaceAgent("");
	   	   
	   agcBuyerAgent_0multipleBuyers.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_0multipleBuyers...");
              agcBuyerAgent_0multipleBuyers.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_1multipleBuyers = ac.createNewAgent("BuyerAgent_1multipleBuyers",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CurrentAssistedAgent ment=new CurrentAssistedAgent();
	   
	     ment.setinterfaceAgent("");
	   	   
	   agcBuyerAgent_1multipleBuyers.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_1multipleBuyers...");
              agcBuyerAgent_1multipleBuyers.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_2multipleBuyers = ac.createNewAgent("BuyerAgent_2multipleBuyers",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CurrentAssistedAgent ment=new CurrentAssistedAgent();
	   
	     ment.setinterfaceAgent("");
	   	   
	   agcBuyerAgent_2multipleBuyers.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_2multipleBuyers...");
              agcBuyerAgent_2multipleBuyers.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_3multipleBuyers = ac.createNewAgent("BuyerAgent_3multipleBuyers",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CurrentAssistedAgent ment=new CurrentAssistedAgent();
	   
	     ment.setinterfaceAgent("");
	   	   
	   agcBuyerAgent_3multipleBuyers.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_3multipleBuyers...");
              agcBuyerAgent_3multipleBuyers.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_4multipleBuyers = ac.createNewAgent("BuyerAgent_4multipleBuyers",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CurrentAssistedAgent ment=new CurrentAssistedAgent();
	   
	     ment.setinterfaceAgent("");
	   	   
	   agcBuyerAgent_4multipleBuyers.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_4multipleBuyers...");
              agcBuyerAgent_4multipleBuyers.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_5multipleBuyers = ac.createNewAgent("BuyerAgent_5multipleBuyers",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CurrentAssistedAgent ment=new CurrentAssistedAgent();
	   
	     ment.setinterfaceAgent("");
	   	   
	   agcBuyerAgent_5multipleBuyers.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_5multipleBuyers...");
              agcBuyerAgent_5multipleBuyers.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_6multipleBuyers = ac.createNewAgent("BuyerAgent_6multipleBuyers",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CurrentAssistedAgent ment=new CurrentAssistedAgent();
	   
	     ment.setinterfaceAgent("");
	   	   
	   agcBuyerAgent_6multipleBuyers.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_6multipleBuyers...");
              agcBuyerAgent_6multipleBuyers.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_7multipleBuyers = ac.createNewAgent("BuyerAgent_7multipleBuyers",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CurrentAssistedAgent ment=new CurrentAssistedAgent();
	   
	     ment.setinterfaceAgent("");
	   	   
	   agcBuyerAgent_7multipleBuyers.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_7multipleBuyers...");
              agcBuyerAgent_7multipleBuyers.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_8multipleBuyers = ac.createNewAgent("BuyerAgent_8multipleBuyers",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CurrentAssistedAgent ment=new CurrentAssistedAgent();
	   
	     ment.setinterfaceAgent("");
	   	   
	   agcBuyerAgent_8multipleBuyers.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_8multipleBuyers...");
              agcBuyerAgent_8multipleBuyers.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_9multipleBuyers = ac.createNewAgent("BuyerAgent_9multipleBuyers",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CurrentAssistedAgent ment=new CurrentAssistedAgent();
	   
	     ment.setinterfaceAgent("");
	   	   
	   agcBuyerAgent_9multipleBuyers.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_9multipleBuyers...");
              agcBuyerAgent_9multipleBuyers.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_0multipleInterfaceAgents = ac.createNewAgent("InterfaceAgent_0multipleInterfaceAgents",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ GetAssignments ment=new GetAssignments();
	   	   
	   agcInterfaceAgent_0multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_0multipleInterfaceAgents...");
              agcInterfaceAgent_0multipleInterfaceAgents.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_1multipleInterfaceAgents = ac.createNewAgent("InterfaceAgent_1multipleInterfaceAgents",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ GetAssignments ment=new GetAssignments();
	   	   
	   agcInterfaceAgent_1multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_1multipleInterfaceAgents...");
              agcInterfaceAgent_1multipleInterfaceAgents.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_2multipleInterfaceAgents = ac.createNewAgent("InterfaceAgent_2multipleInterfaceAgents",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ GetAssignments ment=new GetAssignments();
	   	   
	   agcInterfaceAgent_2multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_2multipleInterfaceAgents...");
              agcInterfaceAgent_2multipleInterfaceAgents.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_3multipleInterfaceAgents = ac.createNewAgent("InterfaceAgent_3multipleInterfaceAgents",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ GetAssignments ment=new GetAssignments();
	   	   
	   agcInterfaceAgent_3multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_3multipleInterfaceAgents...");
              agcInterfaceAgent_3multipleInterfaceAgents.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_4multipleInterfaceAgents = ac.createNewAgent("InterfaceAgent_4multipleInterfaceAgents",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ GetAssignments ment=new GetAssignments();
	   	   
	   agcInterfaceAgent_4multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_4multipleInterfaceAgents...");
              agcInterfaceAgent_4multipleInterfaceAgents.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_5multipleInterfaceAgents = ac.createNewAgent("InterfaceAgent_5multipleInterfaceAgents",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ GetAssignments ment=new GetAssignments();
	   	   
	   agcInterfaceAgent_5multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_5multipleInterfaceAgents...");
              agcInterfaceAgent_5multipleInterfaceAgents.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_6multipleInterfaceAgents = ac.createNewAgent("InterfaceAgent_6multipleInterfaceAgents",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ GetAssignments ment=new GetAssignments();
	   	   
	   agcInterfaceAgent_6multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_6multipleInterfaceAgents...");
              agcInterfaceAgent_6multipleInterfaceAgents.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_7multipleInterfaceAgents = ac.createNewAgent("InterfaceAgent_7multipleInterfaceAgents",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ GetAssignments ment=new GetAssignments();
	   	   
	   agcInterfaceAgent_7multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_7multipleInterfaceAgents...");
              agcInterfaceAgent_7multipleInterfaceAgents.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_8multipleInterfaceAgents = ac.createNewAgent("InterfaceAgent_8multipleInterfaceAgents",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ GetAssignments ment=new GetAssignments();
	   	   
	   agcInterfaceAgent_8multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_8multipleInterfaceAgents...");
              agcInterfaceAgent_8multipleInterfaceAgents.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_9multipleInterfaceAgents = ac.createNewAgent("InterfaceAgent_9multipleInterfaceAgents",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ GetAssignments ment=new GetAssignments();
	   	   
	   agcInterfaceAgent_9multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_9multipleInterfaceAgents...");
              agcInterfaceAgent_9multipleInterfaceAgents.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

}
	      MainInteractionManager.getInstance().setTitle("node SeriousConfiguration");
     }
}

 