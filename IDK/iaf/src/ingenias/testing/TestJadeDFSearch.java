package ingenias.testing;
import jade.core.AID;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.wrapper.StaleProxyException;

/**
 * This tests serves to verify how the DF works. In some cases, the search function
 * was returning null values. To prevent this, I have experimented with different timeouts.
 * For large amounts of agents, timeout values below 20 seconds seem to trigger null values
 * returned by searchUntilFound method. 
 * 
 * @author escalope
 *
 */
public class TestJadeDFSearch extends Agent {

	@Override
	protected void setup() {

		super.setup();
		DFAgentDescription desc=new DFAgentDescription();
		desc.setName(getAID());
		try {
			jade.domain.DFService.register(this, desc);
		} catch (FIPAException e) {
			
			e.printStackTrace();
		}
		
		jade.core.behaviours.CyclicBehaviour cycle= new jade.core.behaviours.CyclicBehaviour(){
			@Override
			public void action() {
				SearchConstraints searchcons= new SearchConstraints();
				searchcons.setMaxDepth(5l); // To search within federated DFs
				searchcons.setMaxResults(100l); // To return 100 matches as much
				DFAgentDescription[] result=null;	
				DFAgentDescription desc=new DFAgentDescription();
				desc.setName(getAID());
				try {
					AID dfName;
					result= jade.domain.DFService.searchUntilFound(myAgent, myAgent.getDefaultDF(),desc,
							searchcons, 20000);					
					if (result==null){
						System.err.println("A null was returned");
						System.exit(0);						
					} else {
						System.out.println("It worked");
					}
				}catch (FIPAException fe){}				
			}
		};
		this.addBehaviour(cycle);
	}

	public static void main(String args[]){
		// Get a hold on JADE runtime
		jade.core.Runtime rt = jade.core.Runtime.instance();

		// Exit the JVM when there are no more containers around
		rt.setCloseVM(true);

		// Create a default profile
		Profile p = new ProfileImpl();
		p.setParameter("preload","a*");
		p.setParameter(Profile.MAIN_PORT, "60000");
		

		// Create a new non-main container, connecting to the default
		// main container (i.e. on this host, port 1099)
		final jade.wrapper.AgentContainer ac = rt.createAgentContainer(p);

		for (int k=0;k<230;k++)
		{
			// Create a new agent

			try {
				final jade.wrapper.AgentController agent = ac.createNewAgent("agent"+k,
						"ingenias.testing.TestJadeDFSearch", new Object[0]);
				final String agentName="agent"+k;

				new Thread(){
					public void run(){
						try {
							System.out.println("Starting up "+agentName);
							agent.start();
						} catch (Exception e){
							e.printStackTrace();
						}
					}
				}.start();
			} catch (StaleProxyException e1) {

				e1.printStackTrace();
			}	

		}
	}
}
