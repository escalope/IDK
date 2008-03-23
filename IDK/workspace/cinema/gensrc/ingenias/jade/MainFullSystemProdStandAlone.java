

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
        final jade.wrapper.AgentController agcSellerAgent2_0 = ac.createNewAgent("SellerAgent2_0",
            "ingenias.jade.agents.SellerAgent2JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent2_0.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent2_0...");
              agcSellerAgent2_0.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent2_1 = ac.createNewAgent("SellerAgent2_1",
            "ingenias.jade.agents.SellerAgent2JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent2_1.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent2_1...");
              agcSellerAgent2_1.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent2_2 = ac.createNewAgent("SellerAgent2_2",
            "ingenias.jade.agents.SellerAgent2JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent2_2.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent2_2...");
              agcSellerAgent2_2.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent2_3 = ac.createNewAgent("SellerAgent2_3",
            "ingenias.jade.agents.SellerAgent2JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent2_3.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent2_3...");
              agcSellerAgent2_3.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent2_4 = ac.createNewAgent("SellerAgent2_4",
            "ingenias.jade.agents.SellerAgent2JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setprice(6);
	   	   
	   agcSellerAgent2_4.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent2_4...");
              agcSellerAgent2_4.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent1_0 = ac.createNewAgent("SellerAgent1_0",
            "ingenias.jade.agents.SellerAgent1JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setprice(2);
	   	   
	   agcSellerAgent1_0.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent1_0...");
              agcSellerAgent1_0.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent1_1 = ac.createNewAgent("SellerAgent1_1",
            "ingenias.jade.agents.SellerAgent1JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setprice(2);
	   	   
	   agcSellerAgent1_1.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent1_1...");
              agcSellerAgent1_1.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcSellerAgent1_2 = ac.createNewAgent("SellerAgent1_2",
            "ingenias.jade.agents.SellerAgent1JADEAgent", new Object[0]);	
	
	{ CurrentMovies ment=new CurrentMovies();
	   
	     ment.setavailableExtras(cinema.helpers.DSHelper.createVS("(icecream,nachos,coke)"));
	   
	     ment.setavailableSeats(cinema.helpers.DSHelper.createSeats("(Rambo,((1900,{1,2,3,4,5}))),(Rocky,((1900,{1,2,3,4,5}),(2000,{1,2,3,4,5,6})))"));
	   
	     ment.setstartingTimeMovie(cinema.helpers.DSHelper.createSessions("(Rambo,{1900,2000}),(Rocky,{1900,2100})"));
	   
	     ment.setprice(2);
	   	   
	   agcSellerAgent1_2.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up SellerAgent1_2...");
              agcSellerAgent1_2.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_0 = ac.createNewAgent("InterfaceAgent_0",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_0.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_0...");
              agcInterfaceAgent_0.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_1 = ac.createNewAgent("InterfaceAgent_1",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_1.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_1...");
              agcInterfaceAgent_1.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_2 = ac.createNewAgent("InterfaceAgent_2",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_2.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_2...");
              agcInterfaceAgent_2.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_3 = ac.createNewAgent("InterfaceAgent_3",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_3.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_3...");
              agcInterfaceAgent_3.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_4 = ac.createNewAgent("InterfaceAgent_4",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_4.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_4...");
              agcInterfaceAgent_4.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_5 = ac.createNewAgent("InterfaceAgent_5",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_5.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_5...");
              agcInterfaceAgent_5.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_6 = ac.createNewAgent("InterfaceAgent_6",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_6.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_6...");
              agcInterfaceAgent_6.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_7 = ac.createNewAgent("InterfaceAgent_7",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_7.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_7...");
              agcInterfaceAgent_7.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_8 = ac.createNewAgent("InterfaceAgent_8",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_8.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_8...");
              agcInterfaceAgent_8.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_9 = ac.createNewAgent("InterfaceAgent_9",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_9.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_9...");
              agcInterfaceAgent_9.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_10 = ac.createNewAgent("InterfaceAgent_10",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_10.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_10...");
              agcInterfaceAgent_10.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_11 = ac.createNewAgent("InterfaceAgent_11",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_11.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_11...");
              agcInterfaceAgent_11.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_12 = ac.createNewAgent("InterfaceAgent_12",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_12.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_12...");
              agcInterfaceAgent_12.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_13 = ac.createNewAgent("InterfaceAgent_13",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_13.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_13...");
              agcInterfaceAgent_13.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_14 = ac.createNewAgent("InterfaceAgent_14",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_14.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_14...");
              agcInterfaceAgent_14.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_15 = ac.createNewAgent("InterfaceAgent_15",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_15.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_15...");
              agcInterfaceAgent_15.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_16 = ac.createNewAgent("InterfaceAgent_16",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_16.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_16...");
              agcInterfaceAgent_16.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_17 = ac.createNewAgent("InterfaceAgent_17",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_17.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_17...");
              agcInterfaceAgent_17.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_18 = ac.createNewAgent("InterfaceAgent_18",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_18.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_18...");
              agcInterfaceAgent_18.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_19 = ac.createNewAgent("InterfaceAgent_19",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_19.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_19...");
              agcInterfaceAgent_19.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_20 = ac.createNewAgent("InterfaceAgent_20",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_20.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_20...");
              agcInterfaceAgent_20.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_21 = ac.createNewAgent("InterfaceAgent_21",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_21.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_21...");
              agcInterfaceAgent_21.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_22 = ac.createNewAgent("InterfaceAgent_22",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_22.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_22...");
              agcInterfaceAgent_22.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_23 = ac.createNewAgent("InterfaceAgent_23",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_23.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_23...");
              agcInterfaceAgent_23.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_24 = ac.createNewAgent("InterfaceAgent_24",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_24.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_24...");
              agcInterfaceAgent_24.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_25 = ac.createNewAgent("InterfaceAgent_25",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_25.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_25...");
              agcInterfaceAgent_25.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_26 = ac.createNewAgent("InterfaceAgent_26",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_26.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_26...");
              agcInterfaceAgent_26.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_27 = ac.createNewAgent("InterfaceAgent_27",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_27.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_27...");
              agcInterfaceAgent_27.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_28 = ac.createNewAgent("InterfaceAgent_28",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_28.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_28...");
              agcInterfaceAgent_28.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_29 = ac.createNewAgent("InterfaceAgent_29",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_29.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_29...");
              agcInterfaceAgent_29.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_30 = ac.createNewAgent("InterfaceAgent_30",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_30.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_30...");
              agcInterfaceAgent_30.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_31 = ac.createNewAgent("InterfaceAgent_31",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_31.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_31...");
              agcInterfaceAgent_31.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_32 = ac.createNewAgent("InterfaceAgent_32",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_32.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_32...");
              agcInterfaceAgent_32.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_33 = ac.createNewAgent("InterfaceAgent_33",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_33.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_33...");
              agcInterfaceAgent_33.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_34 = ac.createNewAgent("InterfaceAgent_34",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_34.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_34...");
              agcInterfaceAgent_34.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_35 = ac.createNewAgent("InterfaceAgent_35",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_35.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_35...");
              agcInterfaceAgent_35.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_36 = ac.createNewAgent("InterfaceAgent_36",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_36.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_36...");
              agcInterfaceAgent_36.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_37 = ac.createNewAgent("InterfaceAgent_37",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_37.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_37...");
              agcInterfaceAgent_37.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_38 = ac.createNewAgent("InterfaceAgent_38",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_38.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_38...");
              agcInterfaceAgent_38.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_39 = ac.createNewAgent("InterfaceAgent_39",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_39.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_39...");
              agcInterfaceAgent_39.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_40 = ac.createNewAgent("InterfaceAgent_40",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_40.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_40...");
              agcInterfaceAgent_40.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_41 = ac.createNewAgent("InterfaceAgent_41",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_41.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_41...");
              agcInterfaceAgent_41.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_42 = ac.createNewAgent("InterfaceAgent_42",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_42.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_42...");
              agcInterfaceAgent_42.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_43 = ac.createNewAgent("InterfaceAgent_43",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_43.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_43...");
              agcInterfaceAgent_43.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_44 = ac.createNewAgent("InterfaceAgent_44",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_44.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_44...");
              agcInterfaceAgent_44.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_45 = ac.createNewAgent("InterfaceAgent_45",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_45.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_45...");
              agcInterfaceAgent_45.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_46 = ac.createNewAgent("InterfaceAgent_46",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_46.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_46...");
              agcInterfaceAgent_46.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_47 = ac.createNewAgent("InterfaceAgent_47",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_47.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_47...");
              agcInterfaceAgent_47.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_48 = ac.createNewAgent("InterfaceAgent_48",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_48.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_48...");
              agcInterfaceAgent_48.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_49 = ac.createNewAgent("InterfaceAgent_49",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_49.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_49...");
              agcInterfaceAgent_49.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_50 = ac.createNewAgent("InterfaceAgent_50",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_50.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_50...");
              agcInterfaceAgent_50.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_51 = ac.createNewAgent("InterfaceAgent_51",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_51.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_51...");
              agcInterfaceAgent_51.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_52 = ac.createNewAgent("InterfaceAgent_52",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_52.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_52...");
              agcInterfaceAgent_52.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_53 = ac.createNewAgent("InterfaceAgent_53",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_53.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_53...");
              agcInterfaceAgent_53.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_54 = ac.createNewAgent("InterfaceAgent_54",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_54.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_54...");
              agcInterfaceAgent_54.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_55 = ac.createNewAgent("InterfaceAgent_55",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_55.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_55...");
              agcInterfaceAgent_55.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_56 = ac.createNewAgent("InterfaceAgent_56",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_56.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_56...");
              agcInterfaceAgent_56.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_57 = ac.createNewAgent("InterfaceAgent_57",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_57.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_57...");
              agcInterfaceAgent_57.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_58 = ac.createNewAgent("InterfaceAgent_58",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_58.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_58...");
              agcInterfaceAgent_58.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_59 = ac.createNewAgent("InterfaceAgent_59",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_59.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_59...");
              agcInterfaceAgent_59.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_60 = ac.createNewAgent("InterfaceAgent_60",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_60.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_60...");
              agcInterfaceAgent_60.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_61 = ac.createNewAgent("InterfaceAgent_61",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_61.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_61...");
              agcInterfaceAgent_61.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_62 = ac.createNewAgent("InterfaceAgent_62",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_62.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_62...");
              agcInterfaceAgent_62.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_63 = ac.createNewAgent("InterfaceAgent_63",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_63.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_63...");
              agcInterfaceAgent_63.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_64 = ac.createNewAgent("InterfaceAgent_64",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_64.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_64...");
              agcInterfaceAgent_64.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_65 = ac.createNewAgent("InterfaceAgent_65",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_65.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_65...");
              agcInterfaceAgent_65.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_66 = ac.createNewAgent("InterfaceAgent_66",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_66.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_66...");
              agcInterfaceAgent_66.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_67 = ac.createNewAgent("InterfaceAgent_67",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_67.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_67...");
              agcInterfaceAgent_67.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_68 = ac.createNewAgent("InterfaceAgent_68",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_68.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_68...");
              agcInterfaceAgent_68.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_69 = ac.createNewAgent("InterfaceAgent_69",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_69.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_69...");
              agcInterfaceAgent_69.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_70 = ac.createNewAgent("InterfaceAgent_70",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_70.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_70...");
              agcInterfaceAgent_70.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_71 = ac.createNewAgent("InterfaceAgent_71",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_71.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_71...");
              agcInterfaceAgent_71.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_72 = ac.createNewAgent("InterfaceAgent_72",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_72.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_72...");
              agcInterfaceAgent_72.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_73 = ac.createNewAgent("InterfaceAgent_73",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_73.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_73...");
              agcInterfaceAgent_73.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_74 = ac.createNewAgent("InterfaceAgent_74",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_74.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_74...");
              agcInterfaceAgent_74.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_75 = ac.createNewAgent("InterfaceAgent_75",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_75.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_75...");
              agcInterfaceAgent_75.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_76 = ac.createNewAgent("InterfaceAgent_76",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_76.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_76...");
              agcInterfaceAgent_76.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_77 = ac.createNewAgent("InterfaceAgent_77",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_77.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_77...");
              agcInterfaceAgent_77.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_78 = ac.createNewAgent("InterfaceAgent_78",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_78.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_78...");
              agcInterfaceAgent_78.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_79 = ac.createNewAgent("InterfaceAgent_79",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_79.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_79...");
              agcInterfaceAgent_79.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_80 = ac.createNewAgent("InterfaceAgent_80",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_80.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_80...");
              agcInterfaceAgent_80.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_81 = ac.createNewAgent("InterfaceAgent_81",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_81.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_81...");
              agcInterfaceAgent_81.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_82 = ac.createNewAgent("InterfaceAgent_82",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_82.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_82...");
              agcInterfaceAgent_82.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_83 = ac.createNewAgent("InterfaceAgent_83",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_83.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_83...");
              agcInterfaceAgent_83.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_84 = ac.createNewAgent("InterfaceAgent_84",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_84.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_84...");
              agcInterfaceAgent_84.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_85 = ac.createNewAgent("InterfaceAgent_85",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_85.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_85...");
              agcInterfaceAgent_85.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_86 = ac.createNewAgent("InterfaceAgent_86",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_86.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_86...");
              agcInterfaceAgent_86.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_87 = ac.createNewAgent("InterfaceAgent_87",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_87.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_87...");
              agcInterfaceAgent_87.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_88 = ac.createNewAgent("InterfaceAgent_88",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_88.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_88...");
              agcInterfaceAgent_88.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_89 = ac.createNewAgent("InterfaceAgent_89",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_89.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_89...");
              agcInterfaceAgent_89.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_90 = ac.createNewAgent("InterfaceAgent_90",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_90.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_90...");
              agcInterfaceAgent_90.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_91 = ac.createNewAgent("InterfaceAgent_91",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_91.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_91...");
              agcInterfaceAgent_91.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_92 = ac.createNewAgent("InterfaceAgent_92",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_92.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_92...");
              agcInterfaceAgent_92.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_93 = ac.createNewAgent("InterfaceAgent_93",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_93.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_93...");
              agcInterfaceAgent_93.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_94 = ac.createNewAgent("InterfaceAgent_94",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_94.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_94...");
              agcInterfaceAgent_94.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_95 = ac.createNewAgent("InterfaceAgent_95",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_95.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_95...");
              agcInterfaceAgent_95.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_96 = ac.createNewAgent("InterfaceAgent_96",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_96.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_96...");
              agcInterfaceAgent_96.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_97 = ac.createNewAgent("InterfaceAgent_97",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_97.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_97...");
              agcInterfaceAgent_97.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_98 = ac.createNewAgent("InterfaceAgent_98",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_98.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_98...");
              agcInterfaceAgent_98.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent_99 = ac.createNewAgent("InterfaceAgent_99",
            "ingenias.jade.agents.InterfaceAgentJADEAgent", new Object[0]);	
	
	{ MoviesProfile ment=new MoviesProfile();
	   
	     ment.setmoviesILike(cinema.helpers.DSHelper.createVS("{Rambo,Rocky}"));
	   
	     ment.setpreferredSessions(cinema.helpers.DSHelper.createVS("{1900,2000}"));
	   
	     ment.setpreferredSeats(cinema.helpers.DSHelper.createVS("{1,2,3}"));
	   
	     ment.setpreferredPrice(5);
	   
	     ment.setpreferredExtras(cinema.helpers.DSHelper.createVS("(nachos)"));
	   	   
	   agcInterfaceAgent_99.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up InterfaceAgent_99...");
              agcInterfaceAgent_99.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_0 = ac.createNewAgent("BuyerAgent_0",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_0.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_0...");
              agcBuyerAgent_0.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_1 = ac.createNewAgent("BuyerAgent_1",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_1.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_1...");
              agcBuyerAgent_1.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_2 = ac.createNewAgent("BuyerAgent_2",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_2.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_2...");
              agcBuyerAgent_2.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_3 = ac.createNewAgent("BuyerAgent_3",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_3.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_3...");
              agcBuyerAgent_3.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_4 = ac.createNewAgent("BuyerAgent_4",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_4.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_4...");
              agcBuyerAgent_4.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_5 = ac.createNewAgent("BuyerAgent_5",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_5.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_5...");
              agcBuyerAgent_5.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_6 = ac.createNewAgent("BuyerAgent_6",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_6.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_6...");
              agcBuyerAgent_6.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_7 = ac.createNewAgent("BuyerAgent_7",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_7.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_7...");
              agcBuyerAgent_7.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_8 = ac.createNewAgent("BuyerAgent_8",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_8.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_8...");
              agcBuyerAgent_8.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_9 = ac.createNewAgent("BuyerAgent_9",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_9.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_9...");
              agcBuyerAgent_9.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_10 = ac.createNewAgent("BuyerAgent_10",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_10.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_10...");
              agcBuyerAgent_10.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_11 = ac.createNewAgent("BuyerAgent_11",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_11.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_11...");
              agcBuyerAgent_11.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_12 = ac.createNewAgent("BuyerAgent_12",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_12.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_12...");
              agcBuyerAgent_12.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_13 = ac.createNewAgent("BuyerAgent_13",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_13.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_13...");
              agcBuyerAgent_13.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_14 = ac.createNewAgent("BuyerAgent_14",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_14.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_14...");
              agcBuyerAgent_14.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_15 = ac.createNewAgent("BuyerAgent_15",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_15.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_15...");
              agcBuyerAgent_15.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_16 = ac.createNewAgent("BuyerAgent_16",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_16.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_16...");
              agcBuyerAgent_16.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_17 = ac.createNewAgent("BuyerAgent_17",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_17.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_17...");
              agcBuyerAgent_17.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_18 = ac.createNewAgent("BuyerAgent_18",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_18.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_18...");
              agcBuyerAgent_18.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_19 = ac.createNewAgent("BuyerAgent_19",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_19.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_19...");
              agcBuyerAgent_19.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_20 = ac.createNewAgent("BuyerAgent_20",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_20.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_20...");
              agcBuyerAgent_20.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_21 = ac.createNewAgent("BuyerAgent_21",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_21.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_21...");
              agcBuyerAgent_21.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_22 = ac.createNewAgent("BuyerAgent_22",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_22.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_22...");
              agcBuyerAgent_22.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_23 = ac.createNewAgent("BuyerAgent_23",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_23.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_23...");
              agcBuyerAgent_23.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_24 = ac.createNewAgent("BuyerAgent_24",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_24.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_24...");
              agcBuyerAgent_24.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_25 = ac.createNewAgent("BuyerAgent_25",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_25.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_25...");
              agcBuyerAgent_25.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_26 = ac.createNewAgent("BuyerAgent_26",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_26.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_26...");
              agcBuyerAgent_26.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_27 = ac.createNewAgent("BuyerAgent_27",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_27.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_27...");
              agcBuyerAgent_27.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_28 = ac.createNewAgent("BuyerAgent_28",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_28.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_28...");
              agcBuyerAgent_28.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_29 = ac.createNewAgent("BuyerAgent_29",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_29.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_29...");
              agcBuyerAgent_29.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_30 = ac.createNewAgent("BuyerAgent_30",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_30.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_30...");
              agcBuyerAgent_30.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_31 = ac.createNewAgent("BuyerAgent_31",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_31.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_31...");
              agcBuyerAgent_31.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_32 = ac.createNewAgent("BuyerAgent_32",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_32.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_32...");
              agcBuyerAgent_32.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_33 = ac.createNewAgent("BuyerAgent_33",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_33.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_33...");
              agcBuyerAgent_33.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_34 = ac.createNewAgent("BuyerAgent_34",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_34.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_34...");
              agcBuyerAgent_34.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_35 = ac.createNewAgent("BuyerAgent_35",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_35.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_35...");
              agcBuyerAgent_35.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_36 = ac.createNewAgent("BuyerAgent_36",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_36.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_36...");
              agcBuyerAgent_36.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_37 = ac.createNewAgent("BuyerAgent_37",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_37.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_37...");
              agcBuyerAgent_37.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_38 = ac.createNewAgent("BuyerAgent_38",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_38.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_38...");
              agcBuyerAgent_38.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_39 = ac.createNewAgent("BuyerAgent_39",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_39.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_39...");
              agcBuyerAgent_39.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_40 = ac.createNewAgent("BuyerAgent_40",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_40.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_40...");
              agcBuyerAgent_40.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_41 = ac.createNewAgent("BuyerAgent_41",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_41.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_41...");
              agcBuyerAgent_41.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_42 = ac.createNewAgent("BuyerAgent_42",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_42.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_42...");
              agcBuyerAgent_42.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_43 = ac.createNewAgent("BuyerAgent_43",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_43.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_43...");
              agcBuyerAgent_43.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_44 = ac.createNewAgent("BuyerAgent_44",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_44.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_44...");
              agcBuyerAgent_44.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_45 = ac.createNewAgent("BuyerAgent_45",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_45.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_45...");
              agcBuyerAgent_45.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_46 = ac.createNewAgent("BuyerAgent_46",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_46.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_46...");
              agcBuyerAgent_46.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_47 = ac.createNewAgent("BuyerAgent_47",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_47.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_47...");
              agcBuyerAgent_47.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_48 = ac.createNewAgent("BuyerAgent_48",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_48.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_48...");
              agcBuyerAgent_48.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_49 = ac.createNewAgent("BuyerAgent_49",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_49.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_49...");
              agcBuyerAgent_49.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_50 = ac.createNewAgent("BuyerAgent_50",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_50.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_50...");
              agcBuyerAgent_50.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_51 = ac.createNewAgent("BuyerAgent_51",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_51.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_51...");
              agcBuyerAgent_51.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_52 = ac.createNewAgent("BuyerAgent_52",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_52.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_52...");
              agcBuyerAgent_52.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_53 = ac.createNewAgent("BuyerAgent_53",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_53.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_53...");
              agcBuyerAgent_53.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_54 = ac.createNewAgent("BuyerAgent_54",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_54.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_54...");
              agcBuyerAgent_54.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_55 = ac.createNewAgent("BuyerAgent_55",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_55.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_55...");
              agcBuyerAgent_55.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_56 = ac.createNewAgent("BuyerAgent_56",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_56.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_56...");
              agcBuyerAgent_56.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_57 = ac.createNewAgent("BuyerAgent_57",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_57.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_57...");
              agcBuyerAgent_57.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_58 = ac.createNewAgent("BuyerAgent_58",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_58.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_58...");
              agcBuyerAgent_58.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_59 = ac.createNewAgent("BuyerAgent_59",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_59.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_59...");
              agcBuyerAgent_59.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_60 = ac.createNewAgent("BuyerAgent_60",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_60.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_60...");
              agcBuyerAgent_60.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_61 = ac.createNewAgent("BuyerAgent_61",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_61.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_61...");
              agcBuyerAgent_61.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_62 = ac.createNewAgent("BuyerAgent_62",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_62.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_62...");
              agcBuyerAgent_62.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_63 = ac.createNewAgent("BuyerAgent_63",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_63.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_63...");
              agcBuyerAgent_63.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_64 = ac.createNewAgent("BuyerAgent_64",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_64.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_64...");
              agcBuyerAgent_64.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_65 = ac.createNewAgent("BuyerAgent_65",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_65.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_65...");
              agcBuyerAgent_65.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_66 = ac.createNewAgent("BuyerAgent_66",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_66.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_66...");
              agcBuyerAgent_66.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_67 = ac.createNewAgent("BuyerAgent_67",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_67.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_67...");
              agcBuyerAgent_67.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_68 = ac.createNewAgent("BuyerAgent_68",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_68.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_68...");
              agcBuyerAgent_68.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_69 = ac.createNewAgent("BuyerAgent_69",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_69.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_69...");
              agcBuyerAgent_69.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_70 = ac.createNewAgent("BuyerAgent_70",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_70.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_70...");
              agcBuyerAgent_70.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_71 = ac.createNewAgent("BuyerAgent_71",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_71.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_71...");
              agcBuyerAgent_71.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_72 = ac.createNewAgent("BuyerAgent_72",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_72.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_72...");
              agcBuyerAgent_72.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_73 = ac.createNewAgent("BuyerAgent_73",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_73.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_73...");
              agcBuyerAgent_73.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_74 = ac.createNewAgent("BuyerAgent_74",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_74.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_74...");
              agcBuyerAgent_74.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_75 = ac.createNewAgent("BuyerAgent_75",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_75.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_75...");
              agcBuyerAgent_75.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_76 = ac.createNewAgent("BuyerAgent_76",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_76.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_76...");
              agcBuyerAgent_76.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_77 = ac.createNewAgent("BuyerAgent_77",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_77.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_77...");
              agcBuyerAgent_77.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_78 = ac.createNewAgent("BuyerAgent_78",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_78.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_78...");
              agcBuyerAgent_78.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_79 = ac.createNewAgent("BuyerAgent_79",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_79.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_79...");
              agcBuyerAgent_79.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_80 = ac.createNewAgent("BuyerAgent_80",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_80.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_80...");
              agcBuyerAgent_80.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_81 = ac.createNewAgent("BuyerAgent_81",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_81.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_81...");
              agcBuyerAgent_81.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_82 = ac.createNewAgent("BuyerAgent_82",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_82.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_82...");
              agcBuyerAgent_82.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_83 = ac.createNewAgent("BuyerAgent_83",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_83.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_83...");
              agcBuyerAgent_83.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_84 = ac.createNewAgent("BuyerAgent_84",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_84.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_84...");
              agcBuyerAgent_84.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_85 = ac.createNewAgent("BuyerAgent_85",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_85.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_85...");
              agcBuyerAgent_85.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_86 = ac.createNewAgent("BuyerAgent_86",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_86.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_86...");
              agcBuyerAgent_86.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_87 = ac.createNewAgent("BuyerAgent_87",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_87.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_87...");
              agcBuyerAgent_87.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_88 = ac.createNewAgent("BuyerAgent_88",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_88.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_88...");
              agcBuyerAgent_88.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_89 = ac.createNewAgent("BuyerAgent_89",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_89.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_89...");
              agcBuyerAgent_89.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_90 = ac.createNewAgent("BuyerAgent_90",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_90.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_90...");
              agcBuyerAgent_90.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_91 = ac.createNewAgent("BuyerAgent_91",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_91.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_91...");
              agcBuyerAgent_91.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_92 = ac.createNewAgent("BuyerAgent_92",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_92.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_92...");
              agcBuyerAgent_92.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_93 = ac.createNewAgent("BuyerAgent_93",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_93.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_93...");
              agcBuyerAgent_93.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_94 = ac.createNewAgent("BuyerAgent_94",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_94.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_94...");
              agcBuyerAgent_94.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_95 = ac.createNewAgent("BuyerAgent_95",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_95.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_95...");
              agcBuyerAgent_95.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_96 = ac.createNewAgent("BuyerAgent_96",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_96.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_96...");
              agcBuyerAgent_96.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_97 = ac.createNewAgent("BuyerAgent_97",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_97.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_97...");
              agcBuyerAgent_97.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_98 = ac.createNewAgent("BuyerAgent_98",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_98.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_98...");
              agcBuyerAgent_98.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcBuyerAgent_99 = ac.createNewAgent("BuyerAgent_99",
            "ingenias.jade.agents.BuyerAgentJADEAgent", new Object[0]);	
	
	{ CinemaProfile ment=new CinemaProfile();
	   
	     ment.setscoreForLastCinemasVisited(new java.util.Hashtable<String,Integer>());
	   	   
	   agcBuyerAgent_99.putO2AObject(ment, false);
	}
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up BuyerAgent_99...");
              agcBuyerAgent_99.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

}
	      MainInteractionManager.getInstance().setTitle("node FullSystem");
     }
}

 