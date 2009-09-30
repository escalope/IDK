package cinema;

import ingenias.jade.components.SimulationKernelInit;

import java.io.FileNotFoundException;
import java.io.IOException;

import jade.core.AID;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.wrapper.StaleProxyException;

public class GenerateMonthlyActivityFromCurrentAgentDistribution extends jade.core.Agent {
	
	
	@Override
	protected void setup() {		
		super.setup();
		final Agent ja=this;
		this.addBehaviour(new jade.core.behaviours.OneShotBehaviour(){

			@Override
			public void action() {				
				String rolename="User_assistant";
				SearchConstraints searchcons= new SearchConstraints();
				searchcons.setMaxDepth(5l); // To search within federated DFs
				searchcons.setMaxResults(100l); // To return 100 matches as much
				DFAgentDescription[] result=null;		
				int k=0;
				System.err.println("running1");
				while (result==null && k<10){
					System.err.println("running2");
					try {
						AID dfName;
						result= jade.domain.DFService.search(ja, getColDescription(rolename));
						// Lower values than 20 seconds timeout lead to null values returned. See ingenias.testing.TestJadeDFSearch
					}catch (FIPAException fe){

					}
					k=k+1;
				}
				SimulationKernelInit.getInstance().informAgentsFound(result);
				
			}});
	}

	public static DFAgentDescription getColDescription(String rolename) {
		// Tiene sentido en iniciadores
		DFAgentDescription dfd;
		ServiceDescription sd;
		dfd = new DFAgentDescription();
		sd = new ServiceDescription();
		sd.setType(rolename);
		dfd.addServices(sd);
		return dfd;

	}
	
	public static void main(String args[]) throws StaleProxyException{
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


        // Create a new agent
        final jade.wrapper.AgentController agcInterfaceAgent = ac.createNewAgent("ConfigGeneration",
            "cinema.GenerateMonthlyActivityFromCurrentAgentDistribution", new Object[0]);	
	
	
        new Thread(){
          public void run(){
            try {
               System.out.println("Starting up GenerateMonthlyActivityFromCurrentAgentDistribution...");
              agcInterfaceAgent.start();
            } catch (Exception e){
              e.printStackTrace();
            }
          }
        }.start();
	}

}
