/*
    Copyright (C) 2007 Jorge Gomez Sanz

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
package ingenias.testing;

import ingenias.exception.TimeOut;
import ingenias.jade.MentalStateManager;
import ingenias.jade.MentalStateProcessor;

import java.util.Hashtable;

public class MSMRepository {

	public static Hashtable<String,MentalStateManager> msms=new Hashtable<String,MentalStateManager>();

	private static MSMRepository instance=new MSMRepository();

	private MSMRepository(){};

	public static  MSMRepository getInstance(){
		return instance;
	}

	public synchronized  void register(String agentid,MentalStateManager msm){
		msms.put(agentid,msm);
		notifyAll();
	}

	public synchronized  void remove(String agentid){
		msms.remove(agentid);
	}

	public synchronized  MentalStateManager get(String agentid){	 
		return msms.get(agentid);
	}

	public synchronized MentalStateManager waitFor(String agentID) throws ingenias.exception.TimeOut{
		int timeOut=10;
		while(!msms.containsKey(agentID) && timeOut>0)
			try {
				wait(1000);
				timeOut--;
			} catch (InterruptedException e) {
				e.printStackTrace();
				timeOut=0;
			}
			if (msms.containsKey(agentID))
				return msms.get(agentID);

			throw new ingenias.exception.TimeOut("It was not possible to obtain an instance of the Mental State Manager of agent "+agentID);

	}


}
