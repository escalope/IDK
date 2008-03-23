

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

import java.util.*;
import ingenias.jade.exception.*;



public  class TimeOut_generatorAppImp extends TimeOut_generatorApp{

	Hashtable<String,Vector<String>> failuresRequested=new Hashtable<String,Vector<String>>();

	public TimeOut_generatorAppImp(){
  super();
 }

@Override
public void requestTaskFailure(String taskID, String agentID) {
	if (!failuresRequested.containsKey(agentID)){
		failuresRequested.put(agentID, new Vector<String>());
	}
	failuresRequested.get(agentID).add(taskID);
}

@Override
public boolean mustFail(String taskID, String agentID) {
	// TODO Auto-generated method stub
	return failuresRequested.containsKey(agentID) && failuresRequested.get(agentID).contains(taskID);
}


}

 