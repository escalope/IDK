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

import ingenias.jade.MentalStateProcessor;

import java.util.Hashtable;
import java.util.Vector;

public class MSPRepository {

	public static Hashtable<String,MentalStateProcessor> msps=new Hashtable<String,MentalStateProcessor>();

	private static MSPRepository instance=new MSPRepository();

	private MSPRepository(){};

	public static  MSPRepository getInstance(){
		return instance;
	}

	public synchronized  void register(String agentid,MentalStateProcessor processor){
		msps.put(agentid,processor);
		notifyAll();
	}

	public synchronized  void remove(String agentid){
		msps.remove(agentid);
	}

	public synchronized  MentalStateProcessor get(String agentid){	 
		return msps.get(agentid);
	}

	public synchronized MentalStateProcessor waitFor(String agentID) throws ingenias.exception.TimeOut{
			int timeOut=5;
			while(!msps.containsKey(agentID) && timeOut>0)
				try {
				wait(1000);
				timeOut--;
				} catch (InterruptedException e) {
					e.printStackTrace();
					timeOut=0;
				}
			if (msps.containsKey(agentID))
				return msps.get(agentID);
			
			throw new ingenias.exception.TimeOut("It was not possible to obtain an instance of the Mental State Processor of agent "+agentID);
		
	}

	public Vector<MentalStateProcessor> getAllRegisteredMSP() {
		// TODO Auto-generated method stub
		return new Vector<MentalStateProcessor>(msps.values());
	}


}
