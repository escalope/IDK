package ingenias.jade;

import java.util.Iterator;
import java.util.Vector;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Utils {
 
	 public static DFAgentDescription toDFAgentDescription(AgentExternalDescription aed){		
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(aed.id);
        ServiceDescription sd = new ServiceDescription();
        sd.setName(aed.id.getLocalName() + "-sub-df");
        sd.setType(aed.role);
        sd.setOwnership("JADE");
        dfd.addServices(sd);
        return dfd;
	}
	
	 public static Vector<AgentExternalDescription> toAgentExternalDescription(DFAgentDescription description) {
	       
		 Vector<AgentExternalDescription> participants=new  Vector<AgentExternalDescription>();
	            Iterator it = description.getAllServices();
	            while (it.hasNext()) {
	                ServiceDescription sd = (ServiceDescription) it.next();	               
					participants.add(new AgentExternalDescription(description.getName(),
	                        sd.getType()));
	            }
	        	      

	        return participants;
	    }
}
