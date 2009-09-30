

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


public class MainDeploymentPackage0 {


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

}
	      MainInteractionManager.getInstance().setTitle("node DeploymentPackage0");
     }
}

 