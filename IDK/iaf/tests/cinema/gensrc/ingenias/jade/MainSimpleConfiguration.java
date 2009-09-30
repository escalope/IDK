

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


public class MainSimpleConfiguration {


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
        final jade.wrapper.AgentController agcSellerAgent2_0expSellerWithProfile2 = ac.createNewAgent("SellerAgent2_0expSellerWithProfile2",
            "ingenias.jade.agents.SellerAgent2JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent2_0expSellerWithProfile2.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent2_0expSellerWithProfile2...");
              agcSellerAgent2_0expSellerWithProfile2.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent1_0expSellerWithProfile1 = ac.createNewAgent("SellerAgent1_0expSellerWithProfile1",
            "ingenias.jade.agents.SellerAgent1JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent1_0expSellerWithProfile1.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent1_0expSellerWithProfile1...");
              agcSellerAgent1_0expSellerWithProfile1.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_0expInterfaceAgentwithprofile = ac.createNewAgent("InterfaceAgent_0expInterfaceAgentwithprofile",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_0expInterfaceAgentwithprofile.putO2AObject(ment, false);
	}
	
	{ GetAssignments ment=new GetAssignments();
	   	   
	   agcInterfaceAgent_0expInterfaceAgentwithprofile.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_0expInterfaceAgentwithprofile...");
              agcInterfaceAgent_0expInterfaceAgentwithprofile.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent1_0287 = ac.createNewAgent("SellerAgent1_0287",
            "ingenias.jade.agents.SellerAgent1JADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent1_0287...");
              agcSellerAgent1_0287.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_0expBuyerAgentWithProfile = ac.createNewAgent("BuyerAgent_0expBuyerAgentWithProfile",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CurrentAssistedAgent ment=new CurrentAssistedAgent();
	   
	     ment.setinterfaceAgent("");
	   	   
	   agcBuyerAgent_0expBuyerAgentWithProfile.putO2AObject(ment, false);
	}
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_0expBuyerAgentWithProfile.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_0expBuyerAgentWithProfile...");
              agcBuyerAgent_0expBuyerAgentWithProfile.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_0interfaceinstantiation = ac.createNewAgent("InterfaceAgent_0interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_0interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_0interfaceinstantiation...");
              agcInterfaceAgent_0interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_1interfaceinstantiation = ac.createNewAgent("InterfaceAgent_1interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_1interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_1interfaceinstantiation...");
              agcInterfaceAgent_1interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_2interfaceinstantiation = ac.createNewAgent("InterfaceAgent_2interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_2interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_2interfaceinstantiation...");
              agcInterfaceAgent_2interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_3interfaceinstantiation = ac.createNewAgent("InterfaceAgent_3interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_3interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_3interfaceinstantiation...");
              agcInterfaceAgent_3interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_4interfaceinstantiation = ac.createNewAgent("InterfaceAgent_4interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_4interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_4interfaceinstantiation...");
              agcInterfaceAgent_4interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_5interfaceinstantiation = ac.createNewAgent("InterfaceAgent_5interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_5interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_5interfaceinstantiation...");
              agcInterfaceAgent_5interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_6interfaceinstantiation = ac.createNewAgent("InterfaceAgent_6interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_6interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_6interfaceinstantiation...");
              agcInterfaceAgent_6interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_7interfaceinstantiation = ac.createNewAgent("InterfaceAgent_7interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_7interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_7interfaceinstantiation...");
              agcInterfaceAgent_7interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_8interfaceinstantiation = ac.createNewAgent("InterfaceAgent_8interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_8interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_8interfaceinstantiation...");
              agcInterfaceAgent_8interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_9interfaceinstantiation = ac.createNewAgent("InterfaceAgent_9interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_9interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_9interfaceinstantiation...");
              agcInterfaceAgent_9interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_10interfaceinstantiation = ac.createNewAgent("InterfaceAgent_10interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_10interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_10interfaceinstantiation...");
              agcInterfaceAgent_10interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_11interfaceinstantiation = ac.createNewAgent("InterfaceAgent_11interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_11interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_11interfaceinstantiation...");
              agcInterfaceAgent_11interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_12interfaceinstantiation = ac.createNewAgent("InterfaceAgent_12interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_12interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_12interfaceinstantiation...");
              agcInterfaceAgent_12interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_13interfaceinstantiation = ac.createNewAgent("InterfaceAgent_13interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_13interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_13interfaceinstantiation...");
              agcInterfaceAgent_13interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_14interfaceinstantiation = ac.createNewAgent("InterfaceAgent_14interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_14interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_14interfaceinstantiation...");
              agcInterfaceAgent_14interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_15interfaceinstantiation = ac.createNewAgent("InterfaceAgent_15interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_15interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_15interfaceinstantiation...");
              agcInterfaceAgent_15interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_16interfaceinstantiation = ac.createNewAgent("InterfaceAgent_16interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_16interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_16interfaceinstantiation...");
              agcInterfaceAgent_16interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_17interfaceinstantiation = ac.createNewAgent("InterfaceAgent_17interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_17interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_17interfaceinstantiation...");
              agcInterfaceAgent_17interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_18interfaceinstantiation = ac.createNewAgent("InterfaceAgent_18interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_18interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_18interfaceinstantiation...");
              agcInterfaceAgent_18interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_19interfaceinstantiation = ac.createNewAgent("InterfaceAgent_19interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_19interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_19interfaceinstantiation...");
              agcInterfaceAgent_19interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_20interfaceinstantiation = ac.createNewAgent("InterfaceAgent_20interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_20interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_20interfaceinstantiation...");
              agcInterfaceAgent_20interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_21interfaceinstantiation = ac.createNewAgent("InterfaceAgent_21interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_21interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_21interfaceinstantiation...");
              agcInterfaceAgent_21interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_22interfaceinstantiation = ac.createNewAgent("InterfaceAgent_22interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_22interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_22interfaceinstantiation...");
              agcInterfaceAgent_22interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_23interfaceinstantiation = ac.createNewAgent("InterfaceAgent_23interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_23interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_23interfaceinstantiation...");
              agcInterfaceAgent_23interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_24interfaceinstantiation = ac.createNewAgent("InterfaceAgent_24interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_24interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_24interfaceinstantiation...");
              agcInterfaceAgent_24interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_25interfaceinstantiation = ac.createNewAgent("InterfaceAgent_25interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_25interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_25interfaceinstantiation...");
              agcInterfaceAgent_25interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_26interfaceinstantiation = ac.createNewAgent("InterfaceAgent_26interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_26interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_26interfaceinstantiation...");
              agcInterfaceAgent_26interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_27interfaceinstantiation = ac.createNewAgent("InterfaceAgent_27interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_27interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_27interfaceinstantiation...");
              agcInterfaceAgent_27interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_28interfaceinstantiation = ac.createNewAgent("InterfaceAgent_28interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_28interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_28interfaceinstantiation...");
              agcInterfaceAgent_28interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_29interfaceinstantiation = ac.createNewAgent("InterfaceAgent_29interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_29interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_29interfaceinstantiation...");
              agcInterfaceAgent_29interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_30interfaceinstantiation = ac.createNewAgent("InterfaceAgent_30interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_30interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_30interfaceinstantiation...");
              agcInterfaceAgent_30interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_31interfaceinstantiation = ac.createNewAgent("InterfaceAgent_31interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_31interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_31interfaceinstantiation...");
              agcInterfaceAgent_31interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_32interfaceinstantiation = ac.createNewAgent("InterfaceAgent_32interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_32interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_32interfaceinstantiation...");
              agcInterfaceAgent_32interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_33interfaceinstantiation = ac.createNewAgent("InterfaceAgent_33interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_33interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_33interfaceinstantiation...");
              agcInterfaceAgent_33interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_34interfaceinstantiation = ac.createNewAgent("InterfaceAgent_34interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_34interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_34interfaceinstantiation...");
              agcInterfaceAgent_34interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_35interfaceinstantiation = ac.createNewAgent("InterfaceAgent_35interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_35interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_35interfaceinstantiation...");
              agcInterfaceAgent_35interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_36interfaceinstantiation = ac.createNewAgent("InterfaceAgent_36interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_36interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_36interfaceinstantiation...");
              agcInterfaceAgent_36interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_37interfaceinstantiation = ac.createNewAgent("InterfaceAgent_37interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_37interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_37interfaceinstantiation...");
              agcInterfaceAgent_37interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_38interfaceinstantiation = ac.createNewAgent("InterfaceAgent_38interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_38interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_38interfaceinstantiation...");
              agcInterfaceAgent_38interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_39interfaceinstantiation = ac.createNewAgent("InterfaceAgent_39interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_39interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_39interfaceinstantiation...");
              agcInterfaceAgent_39interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_40interfaceinstantiation = ac.createNewAgent("InterfaceAgent_40interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_40interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_40interfaceinstantiation...");
              agcInterfaceAgent_40interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_41interfaceinstantiation = ac.createNewAgent("InterfaceAgent_41interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_41interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_41interfaceinstantiation...");
              agcInterfaceAgent_41interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_42interfaceinstantiation = ac.createNewAgent("InterfaceAgent_42interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_42interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_42interfaceinstantiation...");
              agcInterfaceAgent_42interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_43interfaceinstantiation = ac.createNewAgent("InterfaceAgent_43interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_43interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_43interfaceinstantiation...");
              agcInterfaceAgent_43interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_44interfaceinstantiation = ac.createNewAgent("InterfaceAgent_44interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_44interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_44interfaceinstantiation...");
              agcInterfaceAgent_44interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_45interfaceinstantiation = ac.createNewAgent("InterfaceAgent_45interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_45interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_45interfaceinstantiation...");
              agcInterfaceAgent_45interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_46interfaceinstantiation = ac.createNewAgent("InterfaceAgent_46interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_46interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_46interfaceinstantiation...");
              agcInterfaceAgent_46interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_47interfaceinstantiation = ac.createNewAgent("InterfaceAgent_47interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_47interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_47interfaceinstantiation...");
              agcInterfaceAgent_47interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_48interfaceinstantiation = ac.createNewAgent("InterfaceAgent_48interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_48interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_48interfaceinstantiation...");
              agcInterfaceAgent_48interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_49interfaceinstantiation = ac.createNewAgent("InterfaceAgent_49interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_49interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_49interfaceinstantiation...");
              agcInterfaceAgent_49interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_50interfaceinstantiation = ac.createNewAgent("InterfaceAgent_50interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_50interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_50interfaceinstantiation...");
              agcInterfaceAgent_50interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_51interfaceinstantiation = ac.createNewAgent("InterfaceAgent_51interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_51interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_51interfaceinstantiation...");
              agcInterfaceAgent_51interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_52interfaceinstantiation = ac.createNewAgent("InterfaceAgent_52interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_52interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_52interfaceinstantiation...");
              agcInterfaceAgent_52interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_53interfaceinstantiation = ac.createNewAgent("InterfaceAgent_53interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_53interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_53interfaceinstantiation...");
              agcInterfaceAgent_53interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_54interfaceinstantiation = ac.createNewAgent("InterfaceAgent_54interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_54interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_54interfaceinstantiation...");
              agcInterfaceAgent_54interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_55interfaceinstantiation = ac.createNewAgent("InterfaceAgent_55interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_55interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_55interfaceinstantiation...");
              agcInterfaceAgent_55interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_56interfaceinstantiation = ac.createNewAgent("InterfaceAgent_56interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_56interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_56interfaceinstantiation...");
              agcInterfaceAgent_56interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_57interfaceinstantiation = ac.createNewAgent("InterfaceAgent_57interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_57interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_57interfaceinstantiation...");
              agcInterfaceAgent_57interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_58interfaceinstantiation = ac.createNewAgent("InterfaceAgent_58interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_58interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_58interfaceinstantiation...");
              agcInterfaceAgent_58interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_59interfaceinstantiation = ac.createNewAgent("InterfaceAgent_59interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_59interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_59interfaceinstantiation...");
              agcInterfaceAgent_59interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_60interfaceinstantiation = ac.createNewAgent("InterfaceAgent_60interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_60interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_60interfaceinstantiation...");
              agcInterfaceAgent_60interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_61interfaceinstantiation = ac.createNewAgent("InterfaceAgent_61interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_61interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_61interfaceinstantiation...");
              agcInterfaceAgent_61interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_62interfaceinstantiation = ac.createNewAgent("InterfaceAgent_62interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_62interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_62interfaceinstantiation...");
              agcInterfaceAgent_62interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_63interfaceinstantiation = ac.createNewAgent("InterfaceAgent_63interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_63interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_63interfaceinstantiation...");
              agcInterfaceAgent_63interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_64interfaceinstantiation = ac.createNewAgent("InterfaceAgent_64interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_64interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_64interfaceinstantiation...");
              agcInterfaceAgent_64interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_65interfaceinstantiation = ac.createNewAgent("InterfaceAgent_65interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_65interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_65interfaceinstantiation...");
              agcInterfaceAgent_65interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_66interfaceinstantiation = ac.createNewAgent("InterfaceAgent_66interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_66interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_66interfaceinstantiation...");
              agcInterfaceAgent_66interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_67interfaceinstantiation = ac.createNewAgent("InterfaceAgent_67interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_67interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_67interfaceinstantiation...");
              agcInterfaceAgent_67interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_68interfaceinstantiation = ac.createNewAgent("InterfaceAgent_68interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_68interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_68interfaceinstantiation...");
              agcInterfaceAgent_68interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_69interfaceinstantiation = ac.createNewAgent("InterfaceAgent_69interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_69interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_69interfaceinstantiation...");
              agcInterfaceAgent_69interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_70interfaceinstantiation = ac.createNewAgent("InterfaceAgent_70interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_70interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_70interfaceinstantiation...");
              agcInterfaceAgent_70interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_71interfaceinstantiation = ac.createNewAgent("InterfaceAgent_71interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_71interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_71interfaceinstantiation...");
              agcInterfaceAgent_71interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_72interfaceinstantiation = ac.createNewAgent("InterfaceAgent_72interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_72interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_72interfaceinstantiation...");
              agcInterfaceAgent_72interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_73interfaceinstantiation = ac.createNewAgent("InterfaceAgent_73interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_73interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_73interfaceinstantiation...");
              agcInterfaceAgent_73interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_74interfaceinstantiation = ac.createNewAgent("InterfaceAgent_74interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_74interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_74interfaceinstantiation...");
              agcInterfaceAgent_74interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_75interfaceinstantiation = ac.createNewAgent("InterfaceAgent_75interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_75interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_75interfaceinstantiation...");
              agcInterfaceAgent_75interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_76interfaceinstantiation = ac.createNewAgent("InterfaceAgent_76interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_76interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_76interfaceinstantiation...");
              agcInterfaceAgent_76interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_77interfaceinstantiation = ac.createNewAgent("InterfaceAgent_77interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_77interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_77interfaceinstantiation...");
              agcInterfaceAgent_77interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_78interfaceinstantiation = ac.createNewAgent("InterfaceAgent_78interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_78interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_78interfaceinstantiation...");
              agcInterfaceAgent_78interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_79interfaceinstantiation = ac.createNewAgent("InterfaceAgent_79interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_79interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_79interfaceinstantiation...");
              agcInterfaceAgent_79interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_80interfaceinstantiation = ac.createNewAgent("InterfaceAgent_80interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_80interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_80interfaceinstantiation...");
              agcInterfaceAgent_80interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_81interfaceinstantiation = ac.createNewAgent("InterfaceAgent_81interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_81interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_81interfaceinstantiation...");
              agcInterfaceAgent_81interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_82interfaceinstantiation = ac.createNewAgent("InterfaceAgent_82interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_82interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_82interfaceinstantiation...");
              agcInterfaceAgent_82interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_83interfaceinstantiation = ac.createNewAgent("InterfaceAgent_83interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_83interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_83interfaceinstantiation...");
              agcInterfaceAgent_83interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_84interfaceinstantiation = ac.createNewAgent("InterfaceAgent_84interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_84interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_84interfaceinstantiation...");
              agcInterfaceAgent_84interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_85interfaceinstantiation = ac.createNewAgent("InterfaceAgent_85interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_85interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_85interfaceinstantiation...");
              agcInterfaceAgent_85interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_86interfaceinstantiation = ac.createNewAgent("InterfaceAgent_86interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_86interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_86interfaceinstantiation...");
              agcInterfaceAgent_86interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_87interfaceinstantiation = ac.createNewAgent("InterfaceAgent_87interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_87interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_87interfaceinstantiation...");
              agcInterfaceAgent_87interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_88interfaceinstantiation = ac.createNewAgent("InterfaceAgent_88interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_88interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_88interfaceinstantiation...");
              agcInterfaceAgent_88interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_89interfaceinstantiation = ac.createNewAgent("InterfaceAgent_89interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_89interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_89interfaceinstantiation...");
              agcInterfaceAgent_89interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_90interfaceinstantiation = ac.createNewAgent("InterfaceAgent_90interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_90interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_90interfaceinstantiation...");
              agcInterfaceAgent_90interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_91interfaceinstantiation = ac.createNewAgent("InterfaceAgent_91interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_91interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_91interfaceinstantiation...");
              agcInterfaceAgent_91interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_92interfaceinstantiation = ac.createNewAgent("InterfaceAgent_92interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_92interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_92interfaceinstantiation...");
              agcInterfaceAgent_92interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_93interfaceinstantiation = ac.createNewAgent("InterfaceAgent_93interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_93interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_93interfaceinstantiation...");
              agcInterfaceAgent_93interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_94interfaceinstantiation = ac.createNewAgent("InterfaceAgent_94interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_94interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_94interfaceinstantiation...");
              agcInterfaceAgent_94interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_95interfaceinstantiation = ac.createNewAgent("InterfaceAgent_95interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_95interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_95interfaceinstantiation...");
              agcInterfaceAgent_95interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_96interfaceinstantiation = ac.createNewAgent("InterfaceAgent_96interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_96interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_96interfaceinstantiation...");
              agcInterfaceAgent_96interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_97interfaceinstantiation = ac.createNewAgent("InterfaceAgent_97interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_97interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_97interfaceinstantiation...");
              agcInterfaceAgent_97interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_98interfaceinstantiation = ac.createNewAgent("InterfaceAgent_98interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_98interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_98interfaceinstantiation...");
              agcInterfaceAgent_98interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_99interfaceinstantiation = ac.createNewAgent("InterfaceAgent_99interfaceinstantiation",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_99interfaceinstantiation.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_99interfaceinstantiation...");
              agcInterfaceAgent_99interfaceinstantiation.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_0283 = ac.createNewAgent("InterfaceAgent_0283",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_0283...");
              agcInterfaceAgent_0283.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

}
	      MainInteractionManager.getInstance().setTitle("node SimpleConfiguration");
     }
}

 