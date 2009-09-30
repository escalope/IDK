

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


public class MainFullSystemProdStandAlone {


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
        final jade.wrapper.AgentController agcSellerAgent2_0146 = ac.createNewAgent("SellerAgent2_0146",
            "ingenias.jade.agents.SellerAgent2JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent2_0146.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent2_0146...");
              agcSellerAgent2_0146.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent2_1146 = ac.createNewAgent("SellerAgent2_1146",
            "ingenias.jade.agents.SellerAgent2JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent2_1146.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent2_1146...");
              agcSellerAgent2_1146.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent2_2146 = ac.createNewAgent("SellerAgent2_2146",
            "ingenias.jade.agents.SellerAgent2JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent2_2146.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent2_2146...");
              agcSellerAgent2_2146.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent2_3146 = ac.createNewAgent("SellerAgent2_3146",
            "ingenias.jade.agents.SellerAgent2JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent2_3146.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent2_3146...");
              agcSellerAgent2_3146.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent2_4146 = ac.createNewAgent("SellerAgent2_4146",
            "ingenias.jade.agents.SellerAgent2JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent2_4146.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent2_4146...");
              agcSellerAgent2_4146.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent1_091 = ac.createNewAgent("SellerAgent1_091",
            "ingenias.jade.agents.SellerAgent1JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setprice(2);
	   	   
	   agcSellerAgent1_091.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent1_091...");
              agcSellerAgent1_091.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent1_191 = ac.createNewAgent("SellerAgent1_191",
            "ingenias.jade.agents.SellerAgent1JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setprice(2);
	   	   
	   agcSellerAgent1_191.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent1_191...");
              agcSellerAgent1_191.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent1_291 = ac.createNewAgent("SellerAgent1_291",
            "ingenias.jade.agents.SellerAgent1JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setprice(2);
	   	   
	   agcSellerAgent1_291.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent1_291...");
              agcSellerAgent1_291.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_090 = ac.createNewAgent("BuyerAgent_090",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_090.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_090...");
              agcBuyerAgent_090.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_190 = ac.createNewAgent("BuyerAgent_190",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_190.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_190...");
              agcBuyerAgent_190.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_290 = ac.createNewAgent("BuyerAgent_290",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_290.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_290...");
              agcBuyerAgent_290.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_390 = ac.createNewAgent("BuyerAgent_390",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_390.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_390...");
              agcBuyerAgent_390.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_490 = ac.createNewAgent("BuyerAgent_490",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_490.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_490...");
              agcBuyerAgent_490.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_590 = ac.createNewAgent("BuyerAgent_590",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_590.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_590...");
              agcBuyerAgent_590.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_690 = ac.createNewAgent("BuyerAgent_690",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_690.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_690...");
              agcBuyerAgent_690.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_790 = ac.createNewAgent("BuyerAgent_790",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_790.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_790...");
              agcBuyerAgent_790.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_890 = ac.createNewAgent("BuyerAgent_890",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_890.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_890...");
              agcBuyerAgent_890.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_990 = ac.createNewAgent("BuyerAgent_990",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_990.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_990...");
              agcBuyerAgent_990.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_1090 = ac.createNewAgent("BuyerAgent_1090",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_1090.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_1090...");
              agcBuyerAgent_1090.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_1190 = ac.createNewAgent("BuyerAgent_1190",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_1190.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_1190...");
              agcBuyerAgent_1190.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_1290 = ac.createNewAgent("BuyerAgent_1290",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_1290.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_1290...");
              agcBuyerAgent_1290.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_1390 = ac.createNewAgent("BuyerAgent_1390",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_1390.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_1390...");
              agcBuyerAgent_1390.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_1490 = ac.createNewAgent("BuyerAgent_1490",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_1490.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_1490...");
              agcBuyerAgent_1490.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_1590 = ac.createNewAgent("BuyerAgent_1590",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_1590.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_1590...");
              agcBuyerAgent_1590.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_1690 = ac.createNewAgent("BuyerAgent_1690",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_1690.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_1690...");
              agcBuyerAgent_1690.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_1790 = ac.createNewAgent("BuyerAgent_1790",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_1790.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_1790...");
              agcBuyerAgent_1790.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_1890 = ac.createNewAgent("BuyerAgent_1890",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_1890.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_1890...");
              agcBuyerAgent_1890.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_1990 = ac.createNewAgent("BuyerAgent_1990",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_1990.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_1990...");
              agcBuyerAgent_1990.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_2090 = ac.createNewAgent("BuyerAgent_2090",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_2090.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_2090...");
              agcBuyerAgent_2090.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_2190 = ac.createNewAgent("BuyerAgent_2190",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_2190.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_2190...");
              agcBuyerAgent_2190.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_2290 = ac.createNewAgent("BuyerAgent_2290",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_2290.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_2290...");
              agcBuyerAgent_2290.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_2390 = ac.createNewAgent("BuyerAgent_2390",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_2390.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_2390...");
              agcBuyerAgent_2390.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_2490 = ac.createNewAgent("BuyerAgent_2490",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_2490.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_2490...");
              agcBuyerAgent_2490.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_2590 = ac.createNewAgent("BuyerAgent_2590",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_2590.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_2590...");
              agcBuyerAgent_2590.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_2690 = ac.createNewAgent("BuyerAgent_2690",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_2690.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_2690...");
              agcBuyerAgent_2690.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_2790 = ac.createNewAgent("BuyerAgent_2790",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_2790.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_2790...");
              agcBuyerAgent_2790.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_2890 = ac.createNewAgent("BuyerAgent_2890",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_2890.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_2890...");
              agcBuyerAgent_2890.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_2990 = ac.createNewAgent("BuyerAgent_2990",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_2990.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_2990...");
              agcBuyerAgent_2990.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_3090 = ac.createNewAgent("BuyerAgent_3090",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_3090.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_3090...");
              agcBuyerAgent_3090.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_3190 = ac.createNewAgent("BuyerAgent_3190",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_3190.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_3190...");
              agcBuyerAgent_3190.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_3290 = ac.createNewAgent("BuyerAgent_3290",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_3290.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_3290...");
              agcBuyerAgent_3290.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_3390 = ac.createNewAgent("BuyerAgent_3390",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_3390.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_3390...");
              agcBuyerAgent_3390.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_3490 = ac.createNewAgent("BuyerAgent_3490",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_3490.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_3490...");
              agcBuyerAgent_3490.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_3590 = ac.createNewAgent("BuyerAgent_3590",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_3590.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_3590...");
              agcBuyerAgent_3590.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_3690 = ac.createNewAgent("BuyerAgent_3690",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_3690.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_3690...");
              agcBuyerAgent_3690.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_3790 = ac.createNewAgent("BuyerAgent_3790",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_3790.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_3790...");
              agcBuyerAgent_3790.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_3890 = ac.createNewAgent("BuyerAgent_3890",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_3890.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_3890...");
              agcBuyerAgent_3890.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_3990 = ac.createNewAgent("BuyerAgent_3990",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_3990.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_3990...");
              agcBuyerAgent_3990.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_4090 = ac.createNewAgent("BuyerAgent_4090",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_4090.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_4090...");
              agcBuyerAgent_4090.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_4190 = ac.createNewAgent("BuyerAgent_4190",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_4190.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_4190...");
              agcBuyerAgent_4190.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_4290 = ac.createNewAgent("BuyerAgent_4290",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_4290.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_4290...");
              agcBuyerAgent_4290.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_4390 = ac.createNewAgent("BuyerAgent_4390",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_4390.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_4390...");
              agcBuyerAgent_4390.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_4490 = ac.createNewAgent("BuyerAgent_4490",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_4490.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_4490...");
              agcBuyerAgent_4490.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_4590 = ac.createNewAgent("BuyerAgent_4590",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_4590.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_4590...");
              agcBuyerAgent_4590.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_4690 = ac.createNewAgent("BuyerAgent_4690",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_4690.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_4690...");
              agcBuyerAgent_4690.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_4790 = ac.createNewAgent("BuyerAgent_4790",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_4790.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_4790...");
              agcBuyerAgent_4790.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_4890 = ac.createNewAgent("BuyerAgent_4890",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_4890.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_4890...");
              agcBuyerAgent_4890.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_4990 = ac.createNewAgent("BuyerAgent_4990",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_4990.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_4990...");
              agcBuyerAgent_4990.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_5090 = ac.createNewAgent("BuyerAgent_5090",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_5090.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_5090...");
              agcBuyerAgent_5090.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_5190 = ac.createNewAgent("BuyerAgent_5190",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_5190.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_5190...");
              agcBuyerAgent_5190.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_5290 = ac.createNewAgent("BuyerAgent_5290",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_5290.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_5290...");
              agcBuyerAgent_5290.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_5390 = ac.createNewAgent("BuyerAgent_5390",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_5390.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_5390...");
              agcBuyerAgent_5390.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_5490 = ac.createNewAgent("BuyerAgent_5490",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_5490.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_5490...");
              agcBuyerAgent_5490.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_5590 = ac.createNewAgent("BuyerAgent_5590",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_5590.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_5590...");
              agcBuyerAgent_5590.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_5690 = ac.createNewAgent("BuyerAgent_5690",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_5690.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_5690...");
              agcBuyerAgent_5690.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_5790 = ac.createNewAgent("BuyerAgent_5790",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_5790.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_5790...");
              agcBuyerAgent_5790.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_5890 = ac.createNewAgent("BuyerAgent_5890",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_5890.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_5890...");
              agcBuyerAgent_5890.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_5990 = ac.createNewAgent("BuyerAgent_5990",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_5990.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_5990...");
              agcBuyerAgent_5990.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_6090 = ac.createNewAgent("BuyerAgent_6090",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_6090.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_6090...");
              agcBuyerAgent_6090.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_6190 = ac.createNewAgent("BuyerAgent_6190",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_6190.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_6190...");
              agcBuyerAgent_6190.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_6290 = ac.createNewAgent("BuyerAgent_6290",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_6290.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_6290...");
              agcBuyerAgent_6290.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_6390 = ac.createNewAgent("BuyerAgent_6390",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_6390.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_6390...");
              agcBuyerAgent_6390.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_6490 = ac.createNewAgent("BuyerAgent_6490",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_6490.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_6490...");
              agcBuyerAgent_6490.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_6590 = ac.createNewAgent("BuyerAgent_6590",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_6590.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_6590...");
              agcBuyerAgent_6590.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_6690 = ac.createNewAgent("BuyerAgent_6690",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_6690.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_6690...");
              agcBuyerAgent_6690.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_6790 = ac.createNewAgent("BuyerAgent_6790",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_6790.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_6790...");
              agcBuyerAgent_6790.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_6890 = ac.createNewAgent("BuyerAgent_6890",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_6890.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_6890...");
              agcBuyerAgent_6890.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_6990 = ac.createNewAgent("BuyerAgent_6990",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_6990.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_6990...");
              agcBuyerAgent_6990.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_7090 = ac.createNewAgent("BuyerAgent_7090",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_7090.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_7090...");
              agcBuyerAgent_7090.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_7190 = ac.createNewAgent("BuyerAgent_7190",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_7190.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_7190...");
              agcBuyerAgent_7190.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_7290 = ac.createNewAgent("BuyerAgent_7290",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_7290.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_7290...");
              agcBuyerAgent_7290.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_7390 = ac.createNewAgent("BuyerAgent_7390",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_7390.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_7390...");
              agcBuyerAgent_7390.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_7490 = ac.createNewAgent("BuyerAgent_7490",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_7490.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_7490...");
              agcBuyerAgent_7490.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_7590 = ac.createNewAgent("BuyerAgent_7590",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_7590.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_7590...");
              agcBuyerAgent_7590.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_7690 = ac.createNewAgent("BuyerAgent_7690",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_7690.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_7690...");
              agcBuyerAgent_7690.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_7790 = ac.createNewAgent("BuyerAgent_7790",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_7790.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_7790...");
              agcBuyerAgent_7790.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_7890 = ac.createNewAgent("BuyerAgent_7890",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_7890.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_7890...");
              agcBuyerAgent_7890.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_7990 = ac.createNewAgent("BuyerAgent_7990",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_7990.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_7990...");
              agcBuyerAgent_7990.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_8090 = ac.createNewAgent("BuyerAgent_8090",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_8090.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_8090...");
              agcBuyerAgent_8090.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_8190 = ac.createNewAgent("BuyerAgent_8190",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_8190.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_8190...");
              agcBuyerAgent_8190.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_8290 = ac.createNewAgent("BuyerAgent_8290",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_8290.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_8290...");
              agcBuyerAgent_8290.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_8390 = ac.createNewAgent("BuyerAgent_8390",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_8390.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_8390...");
              agcBuyerAgent_8390.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_8490 = ac.createNewAgent("BuyerAgent_8490",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_8490.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_8490...");
              agcBuyerAgent_8490.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_8590 = ac.createNewAgent("BuyerAgent_8590",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_8590.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_8590...");
              agcBuyerAgent_8590.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_8690 = ac.createNewAgent("BuyerAgent_8690",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_8690.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_8690...");
              agcBuyerAgent_8690.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_8790 = ac.createNewAgent("BuyerAgent_8790",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_8790.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_8790...");
              agcBuyerAgent_8790.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_8890 = ac.createNewAgent("BuyerAgent_8890",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_8890.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_8890...");
              agcBuyerAgent_8890.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_8990 = ac.createNewAgent("BuyerAgent_8990",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_8990.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_8990...");
              agcBuyerAgent_8990.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_9090 = ac.createNewAgent("BuyerAgent_9090",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_9090.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_9090...");
              agcBuyerAgent_9090.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_9190 = ac.createNewAgent("BuyerAgent_9190",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_9190.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_9190...");
              agcBuyerAgent_9190.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_9290 = ac.createNewAgent("BuyerAgent_9290",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_9290.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_9290...");
              agcBuyerAgent_9290.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_9390 = ac.createNewAgent("BuyerAgent_9390",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_9390.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_9390...");
              agcBuyerAgent_9390.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_9490 = ac.createNewAgent("BuyerAgent_9490",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_9490.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_9490...");
              agcBuyerAgent_9490.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_9590 = ac.createNewAgent("BuyerAgent_9590",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_9590.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_9590...");
              agcBuyerAgent_9590.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_9690 = ac.createNewAgent("BuyerAgent_9690",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_9690.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_9690...");
              agcBuyerAgent_9690.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_9790 = ac.createNewAgent("BuyerAgent_9790",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_9790.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_9790...");
              agcBuyerAgent_9790.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_9890 = ac.createNewAgent("BuyerAgent_9890",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_9890.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_9890...");
              agcBuyerAgent_9890.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_9990 = ac.createNewAgent("BuyerAgent_9990",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_9990.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_9990...");
              agcBuyerAgent_9990.start();
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

}
	      MainInteractionManager.getInstance().setTitle("node FullSystem");
     }
}

 