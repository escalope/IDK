

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


public class MainDemoDeployment {


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
        final jade.wrapper.AgentController agcA_0 = ac.createNewAgent("A_0",
            "ingenias.jade.agents.AJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up A_0...");
              agcA_0.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcB_0 = ac.createNewAgent("B_0",
            "ingenias.jade.agents.BJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up B_0...");
              agcB_0.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcC_0 = ac.createNewAgent("C_0",
            "ingenias.jade.agents.CJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up C_0...");
              agcC_0.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcC_1 = ac.createNewAgent("C_1",
            "ingenias.jade.agents.CJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up C_1...");
              agcC_1.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcC_2 = ac.createNewAgent("C_2",
            "ingenias.jade.agents.CJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up C_2...");
              agcC_2.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcC_3 = ac.createNewAgent("C_3",
            "ingenias.jade.agents.CJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up C_3...");
              agcC_3.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

        // Create a new agent
        final jade.wrapper.AgentController agcC_4 = ac.createNewAgent("C_4",
            "ingenias.jade.agents.CJADEAgent", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up C_4...");
              agcC_4.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();

}
	      MainInteractionManager.getInstance().setTitle("node DemoDeployment");
     }
}

 