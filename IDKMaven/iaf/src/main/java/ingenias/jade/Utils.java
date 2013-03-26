/*
Copyright (C) 2012 Jorge Gomez Sanz

This file is part of INGENIAS Agent Framework, an agent infrastructure linked
to the INGENIAS Development Kit, and availabe at http://ingenias.sourceforge.net. 

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
