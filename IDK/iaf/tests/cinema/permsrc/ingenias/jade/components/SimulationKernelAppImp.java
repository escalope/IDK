

/**
 *
 *
 *  Description of the task /
 *
 * 
 *@author     Jorge J. Gomez
 *@version    1.0
 */

package ingenias.jade.components;

import jade.core.AID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.management.AgentManagementService;
import jade.domain.AMSService;
import jade.domain.FIPAException;
import jade.domain.ams;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.wrapper.StaleProxyException;

import java.util.*;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import ingenias.editor.entities.MentalStateProcessor;
import ingenias.jade.JADEAgent;
import ingenias.jade.agents.InterfaceAgentJADEAgent;
import ingenias.jade.exception.*;
import ingenias.jade.mental.User_wants_to_watch_a_movie;



public  class SimulationKernelAppImp extends SimulationKernelApp{

	private java.lang.String synchroString="synchrostring";
	Thread registeredThreads=null;

	Vector<JADEAgent> agentList=new Vector<JADEAgent>();
	int positive=0;
	int negative=0;

	public SimulationKernelAppImp(){
		super();
		
		//new Exception().printStackTrace();
		if (registeredThreads==null){
			registeredThreads=new Thread(){
				public void run(){
					System.out.println("-------------------------------------------------");
					System.out.println("starting the system");
					System.out.println("-------------------------------------------------");
					try {
						Thread.currentThread().sleep(20000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					System.out.println("-------------------------------------------------");
					System.out.println("Injecting requests into the system");
					System.out.println("-------------------------------------------------");
					Vector<JADEAgent> owners = getMultipleOwners();					
					for (JADEAgent ja:owners){
						if (ja instanceof InterfaceAgentJADEAgent){
							agentList.add(ja);
							issueRequest(ja);
						}
					}

					while (!agentList.isEmpty()){						
							waitMessages();
							System.out.println("There are "+agentList.size()+" agents to complete");						
					}
					System.out.println("-------------------------------------------------");
					System.out.println("All agents fullfilled the goal with "+positive+" agents getting the ticket and "+negative+" failing to obtain the ticket");
					System.out.println("-------------------------------------------------");

				}
			};
			registeredThreads.start();
		}

	}
	
	public synchronized void waitMessages(){
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public synchronized void resume(){
		notifyAll();
	}

	@Override
	public void registerMultipleOwners(final JADEAgent owner) {
		super.registerMultipleOwners(owner);
	}

	@Override
	public void informAgentsFound(DFAgentDescription[] results) {
		resume();		
	}

	protected void issueRequest(JADEAgent owner) {
		System.err.println("agent :"+owner.getAID().getLocalName()+" requesting...");

		User_wants_to_watch_a_movie nevent= new User_wants_to_watch_a_movie();	        
		try {
			owner.getMSM().addMentalEntity(nevent);
		} catch (ingenias.exception.InvalidEntity ex){
			ex.printStackTrace();
		}
	}

	@Override
	public synchronized void ticketNotObtained(JADEAgent owner) {
		agentList.remove(owner);
		negative++;
		resume();

	}

	@Override
	public synchronized void ticketObtained(JADEAgent owner) {
		agentList.remove(owner);
		positive++;
		resume();
	}



}

