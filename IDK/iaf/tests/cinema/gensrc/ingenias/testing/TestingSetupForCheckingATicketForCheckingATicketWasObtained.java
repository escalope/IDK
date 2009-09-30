

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


package ingenias.testing;


import java.util.Vector;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import jade.core.*;
import jade.wrapper.StaleProxyException;
import jade.wrapper.State;
import ingenias.exception.TimeOut;
import ingenias.jade.mental.*;
import ingenias.tests.*;
import ingenias.jade.IAFProperties;
import ingenias.jade.graphics.MainInteractionManager;


public class TestingSetupForCheckingATicketForCheckingATicketWasObtained extends CheckingATicketWasObtained{
jade.wrapper.AgentContainer ac=null;
 
  @Before
  public void agentSetup() throws StaleProxyException, TimeOut{
        IAFProperties.setGraphicsOn(false); // disable graphics
         MainInteractionManager.goManual(); // Stop task execution
        
        // Get a hold on JADE runtime
        jade.core.Runtime rt = jade.core.Runtime.instance();

        // Exit the JVM when there are no more containers around
        rt.setCloseVM(false);

        // Create a default profile
        Profile p = new ProfileImpl();
        p.setParameter("preload","a*");
        p.setParameter(Profile.MAIN_PORT, "60000");
        p.setParameter(Profile.FILE_DIR, "jade/");

        // Create a new non-main container, connecting to the default
        // main container (i.e. on this host, port 1099)
         ac = rt.createAgentContainer(p);
        MainInteractionManager.goManual();


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
        final jade.wrapper.AgentController agcInterfaceAgent_0multipleInterfaceAgents = ac.createNewAgent("InterfaceAgent_0multipleInterfaceAgents",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_0multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
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
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_1multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
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
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_2multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
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
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_3multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
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
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_4multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
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
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_5multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
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
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_6multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
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
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_7multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
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
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_8multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
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
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_9multipleInterfaceAgents.putO2AObject(ment, false);
	}
	
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
	      MainInteractionManager.getInstance().setTitle("node SeriousConfiguration");
	    
     }
     
     
     @After
	public void endTest() throws StaleProxyException{
	 ac.kill();	
	};
	

}

 