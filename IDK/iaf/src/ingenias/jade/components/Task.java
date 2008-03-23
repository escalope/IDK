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


package ingenias.jade.components;

import java.util.*;

import ingenias.editor.entities.Entity;
import ingenias.editor.entities.FrameFact;
import ingenias.editor.entities.Goal;
import ingenias.editor.entities.MentalEntity;
import ingenias.exception.NotFound;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.jade.exception.*;

/**
 * A task is the encapsulation of an action of an agent. A task has a concrete sets of inputs to start from,
 * a set of outputs to produce, and a set of applications to be used during the process. An input can be
 * a mental entity. Outputs could be any, but at the moment is restricted to FrameFact entities and Interaction entities.
 * Input and outputs are represented internally by means of hashtables. 
 * An entity can produce an conversation by adding a conversation entity to the outputs table. However, it should
 * be done only when at specification level it is indicated that a task produces a conversation. Otherwise, the 
 * final result would not work as expected. When the task produces a conversation, it has the possibility of 
 * determining who will participate in the interaction and what role will play. To help in finding proper agents,
 * tasks associated to interactions have a default application, the Yellow Pages service application. <p>
 * 
 * A task may decide not to produce all the expected outputs. In that case, it is enough with removing the corresponding
 * output from the table. Outputs are preinitialized, this means that they are created but their valiues are not.
 * Therefore, the task should initialize individual fields of the information produced as output.<p>
 * 
 * A task consuming an input will imply that the task will delete that input from the mental state. For read-only access,
 * the specification should link the task with the input with gtmodifies instances. <p>
 * 
 * When a task execution is associated with the evolution of an interaction, the task is provided an additional input,
 * the conversation context. This context, accesible from method getconversationcontext, provides all the information
 * transmitted by other agents to this one in the current interaction.
 * 
 * @author jj
 *
 */

public abstract class Task {
	
	private Vector<MentalEntity> inputs=new Vector<MentalEntity>();        
	
	private Vector<TaskOutput> outputs=new Vector<TaskOutput>();
	
	private Hashtable<String,Application> applications=new Hashtable<String,Application>();
	
	private String agentID="";
	
	private TaskOutput finalOutput=null;
	
	Goal pursuedGoal=null;
	
	String id;
	String type;
	
	private RuntimeConversation conversation;

	public Task(String id, String type){
		this.id=id;	
		this.type=type;
		
	}

	public String getType(){
		return type;
	}

	public String getID(){
		return id;
	}


	public MentalEntity getFirstInputOfType(String type) {
		MentalEntity result=null;
		for (MentalEntity me:inputs){
			if (me.getType().equals(type) && result==null){
				result=me;
			}
		}
		
		return result;
	}
	
	public Vector<MentalEntity> getAllInputsOfType(String type){
		Vector<MentalEntity> result=new Vector<MentalEntity>();
		for (MentalEntity me:inputs){
			if (me.getType().equalsIgnoreCase("runtimeconversation")){
				if (((RuntimeConversation)me).getInteraction().getId().equalsIgnoreCase(type)){
					result.add(me);
				}
			} else 
			if (me.getType().equals(type)){
				result.add(me);
			}
		}		
		return result;
	}
	
	public void addInput(MentalEntity ei){
		inputs.add(ei);
	}

	public void addOutput(TaskOutput to){
		outputs.add(to);
	}

	public void removeOutput(TaskOutput to){
		outputs.remove(to);
	}

	public void addApplication(String name, Application app){
		//System.err.println(name+":"+app);
		this.applications.put(name,app);  
	}

	public Vector<MentalEntity> getInputs(){
		return  new Vector<MentalEntity>(inputs);
	}

	public Vector<TaskOutput> getOutputs(){
		return  new Vector<TaskOutput>(outputs);
	}

	public Vector getApplications(){
		return new Vector(this.applications.values());
	}

	public Goal getPursuedGoal(){
		return pursuedGoal;
	}

	public void setPursuedGoal(Goal g){
		this.pursuedGoal=g;
	}


	public Application getApplication(String id) {
		//System.err.println(id+":"+this.applications.containsKey(id)+":"+this.applications);
		Application app=(this.applications.get(id));
		return app;
	}

	public boolean equals(Object obj){
		if (Task.class.isAssignableFrom(obj.getClass())){
		   Task target=(Task)obj;
		   boolean sameType=((Task)obj).getType().equals(this.getType());
		   boolean sameInputs=target.getInputs().equals(this.getInputs());
		   boolean sameOutputs=target.getOutputs().equals(this.getOutputs());
		   return sameType || (sameInputs && sameOutputs);
		}
		return super.equals(obj);
	}

	public void setConversationContext(RuntimeConversation conversation) {
		this.conversation=conversation;
	}

	public RuntimeConversation getConversationContext() {
		return conversation;
	}

	/**
	 * This is the method to be redefined by inheritors of this class. This method will be the one called by the
	 * Mental State Processor when this task is eligible to be executed. An task will be executed when all its required
	 * inputs are available, they are not locked (if they has to be consumed), the output will not overwrite any existing
	 * one, and the task is satisfying one of the goals of the agent.
	 * 
	 * @throws TaskException
	 */
	abstract public void execute() throws TaskException;
	
	
	public void setFinalOutput(TaskOutput to){
		this.finalOutput=to; 
	}

	public TaskOutput getFinalOutput(){
		return this.finalOutput; 
	}
	
	/**
	 * Creates the set of inputs to be consumed in every possible output. Only a subset of this set will
	 * be really consumed.
	 * @return
	 */
	public HashSet<MentalEntity> getExpectedConsumedInputs() {
		HashSet<MentalEntity> consumedInputs=new HashSet<MentalEntity>();  
		for (TaskOutput to:this.outputs){			
			consumedInputs.addAll(to.getConsumedEntities());
			
		}
		return consumedInputs;
	}
	
	public String toString(){
		return this.getID()+":"+this.getType();
		
	}
	
	public TaskOutput findOutputAlternative(String outputID, Vector<TaskOutput> outputs) {
		TaskOutput result=null;
		for (TaskOutput output:outputs){
			if (output.getID().equals(outputID)){
				result=output;
				
			}
		}
		return result;
	}

	public void setAgentID(String localName) {
		this.agentID=localName;
		
	}
	
	public String getAgentID(){
		return agentID;
	}


}