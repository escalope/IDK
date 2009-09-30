

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


public class MainDeploymentPackage1ProdStandAlone {


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
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
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
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
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
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
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
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
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
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
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
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
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
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
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
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
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
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
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
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
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
        final jade.wrapper.AgentController agcSellerAgent1_0multipleSellers = ac.createNewAgent("SellerAgent1_0multipleSellers",
            "ingenias.jade.agents.SellerAgent1JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent1_0multipleSellers.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent1_0multipleSellers...");
              agcSellerAgent1_0multipleSellers.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent1_1multipleSellers = ac.createNewAgent("SellerAgent1_1multipleSellers",
            "ingenias.jade.agents.SellerAgent1JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent1_1multipleSellers.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent1_1multipleSellers...");
              agcSellerAgent1_1multipleSellers.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent1_2multipleSellers = ac.createNewAgent("SellerAgent1_2multipleSellers",
            "ingenias.jade.agents.SellerAgent1JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent1_2multipleSellers.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent1_2multipleSellers...");
              agcSellerAgent1_2multipleSellers.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent1_3multipleSellers = ac.createNewAgent("SellerAgent1_3multipleSellers",
            "ingenias.jade.agents.SellerAgent1JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent1_3multipleSellers.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent1_3multipleSellers...");
              agcSellerAgent1_3multipleSellers.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent1_4multipleSellers = ac.createNewAgent("SellerAgent1_4multipleSellers",
            "ingenias.jade.agents.SellerAgent1JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent1_4multipleSellers.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent1_4multipleSellers...");
              agcSellerAgent1_4multipleSellers.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

}
	      MainInteractionManager.getInstance().setTitle("node DeploymentPackage1");
     }
}

 