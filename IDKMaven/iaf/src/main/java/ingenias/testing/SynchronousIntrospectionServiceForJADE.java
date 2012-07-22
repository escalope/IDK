package ingenias.testing;

import jade.core.AID;
import jade.core.BehaviourID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.domain.FIPAAgentManagement.AlreadyRegistered;
import jade.domain.FIPAAgentManagement.NotRegistered;
import jade.gui.AgentTreeModel;
import jade.lang.acl.ACLMessage;

import jade.tools.introspector.Introspector;

import jade.tools.introspector.gui.IntrospectorGUI;
import jade.wrapper.StaleProxyException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Vector;

/**
 * This class reuses the introspection agent code to provide a synchronous interface with the
 * introspection services each JADE agent provides.  
 * @author Jorge J. Gomez-Sanz
 *
 */
public class SynchronousIntrospectionServiceForJADE {

	private static Hashtable<AID, Vector<BehaviourID>> currentBehaviors= new Hashtable<AID, Vector<BehaviourID>>();
	private static Hashtable<AID, Vector<ACLMessage>> currentMessagesSent= new Hashtable<AID, Vector<ACLMessage>>();	
	private static Hashtable<AID, Vector<ACLMessage>> currentMessagesReceived= new Hashtable<AID, Vector<ACLMessage>>();
	private static Vector<String> errorLogs=new Vector<String>();
	private static ingenias.testing.Introspector introspector;
	
	public static synchronized  void initialize() throws StaleProxyException{
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


		// Create a new agent

		final jade.wrapper.AgentController instrospector= ac.createNewAgent("introspector", "ingenias.testing.Introspector", new String[]{});        
		new Thread(){
			public void run(){
				try {					
					System.out.println("Starting up introspector");
					instrospector.start();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}.start();
		
		while (introspector==null){ // introspector is not initialized yet
		 try {
			Thread.currentThread().sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
		
	}
	
	
	public static void logError(String error){
		errorLogs.add(error);
	}
	
	public static synchronized void modifyBehavior(AID agent, BehaviourID behavior){
		if (currentBehaviors.containsKey(agent)){
			currentBehaviors.get(agent).remove(behavior);
			currentBehaviors.get(agent).add(behavior);
		} 
	}
	
	public static synchronized void addBehavior(AID agent, BehaviourID behavior){
		Vector<BehaviourID> behaviors=new Vector<BehaviourID>();  
		if (SynchronousIntrospectionServiceForJADE.currentBehaviors.containsKey(agent)){
			behaviors=SynchronousIntrospectionServiceForJADE.currentBehaviors.get(agent);
		} else {
			SynchronousIntrospectionServiceForJADE.currentBehaviors.put(agent,behaviors);
		}
		behaviors.add(behavior);
	}
	
	public static synchronized void removeBehavior(AID agent, BehaviourID behaviour) {		
		if (SynchronousIntrospectionServiceForJADE.currentBehaviors.containsKey(agent)){
			SynchronousIntrospectionServiceForJADE.currentBehaviors.get(agent).remove(behaviour);
		} 		
	}
	
	public static synchronized Collection<BehaviourID> getBehaviors(AID agentid){		
		return new LinkedList<BehaviourID>(currentBehaviors.get(agentid));
	}

	public static synchronized Collection<BehaviourID> getMessagesSent(AID agentid){
		return null;
	}

	public static synchronized  Collection<BehaviourID> getMessagesReceived(AID agentid){
		return null;
	}
	
	public static synchronized  void watchAgent(AID agentid){
		introspector.addAgent(agentid);		
	}
	
		
	public static void unregister(ingenias.testing.Introspector introspector) throws NotRegistered{
		if (SynchronousIntrospectionServiceForJADE.introspector==introspector)
		 SynchronousIntrospectionServiceForJADE.introspector=null;
		else 
			throw new NotRegistered();
		
	}
	
	public static void register(ingenias.testing.Introspector introspector) throws AlreadyRegistered{
		if (SynchronousIntrospectionServiceForJADE.introspector==null)
		 SynchronousIntrospectionServiceForJADE.introspector=introspector;
		else 
			throw new AlreadyRegistered();
		
	}

	

}
