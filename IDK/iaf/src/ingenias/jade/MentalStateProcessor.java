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

package ingenias.jade;

import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;
import ingenias.editor.entities.StateGoal;
import ingenias.jade.comm.LocksListener;
import ingenias.jade.components.Task;
import ingenias.jade.exception.TaskException;
import ingenias.jade.graphics.AgentGraphics;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.testing.DebugUtils;
import ingenias.testing.MSPRepository;
import jade.core.behaviours.OneShotBehaviour;

import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Vector;

/**
 * This class implements the decision procedure associated to the agent. Basically, it decides which
 * tasks have to be added to the task queue of the agent. The decision algorithm is as follows:
 * <ul>
 * <li>All tasks satisfying agent pending goals are selected
 * <li>All tasks not finding their inputs either in the mental state (if not attached to a conversation),
 * or in a conversation (if they have been associated to a conversation) are removed from the selected list
 * <li>Remaining tasks are added to the execution queue
 * </ul>
 * Once queued, the behavior of is as follows
 * <ul>
 * <li>The first task is selected. Its inputs are checked to verify that it still can be executed. 
 * If it cannot, it is removed 
 * <li>The task is execued. 
 * <li>The execution of the task may make other tasks not eligible any more. This happens when: 
 * the task has produced an output that is also produced by other task, when the task has consumed an 
 * entity which is used as input of other task.
 * </ul>
 * 
 * The mental state processor has its own execution thread. This thread is responsible of launching the
 * different tasks from those queued. Task execution is synchronized with existing protocols by a wait/notify
 * mechanisms. When one round of evaluation of protocols and messages reception has been completed, the agent
 * proceeds to execute one more task. This way, information produced by tasks can be sent to other agents before
 * other task comes into scene and consumes the information. Also, it gives time for new information to come
 * to the agent, information which could enable the execution of another task.
 * 
 * @author jj
 *
 */
public class MentalStateProcessor implements  LocksListener {



	TaskQueue queues=null;
	MentalStateManager msm=null;
	JADEAgent ja=null;
	AgentGraphics graphics=null;
	Thread executionThread=null;
	TaskExecutionModel tem=null;
	private int state=AgentStates.PLANNING_STARTING; 
	private Vector<Boolean> doReplan=new Vector<Boolean>();
	private Integer monitorDoReplanQueue=new Integer(1);
	Thread replanThread=new Thread(){


		public void run(){
			while(true){
				boolean repeat=true;
				while (repeat){
					synchronized(monitorDoReplanQueue){
						repeat=doReplan.isEmpty();
					}
					try {
						Thread.currentThread().sleep(100);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
				synchronized(monitorDoReplanQueue){
					doReplan.remove(0);
				}
				//System.err.println("replanning "+doReplan.size());				
				replan();	

				OneShotBehaviour osb=new OneShotBehaviour(){

					@Override
					public void action() {
						state = AgentStates.DECISION_FINISHED;
						processQueues();
						state=AgentStates.EXECUTION_FINISHED;
					}
					
				};
				ja.addBehaviour(osb);
				
			}

		}
	};


	public MentalStateProcessor(MentalStateManager msm, JADEAgent ja, AgentGraphics graphics){
		this.msm=msm;
		this.ja=ja;
		this.graphics=graphics;
		this.queues=new TaskQueue(graphics,ja.getAID(), ja.getLM(), ja.getMSM());
		this.tem=new TaskExecutionModel(ja.getAID().getLocalName());

		//executionThread=new Thread(this);
		//executionThread.start();
		MSPRepository.getInstance().register(ja.getName().substring(0,ja.getName().indexOf('@')), this);
		replanThread.setName("Replanning "+ja.getAID().getLocalName());
		replanThread.start();
	}		


	public MentalStateProcessor(MentalStateManager msm, JADEAgent ja) {
		this.msm=msm;
		this.ja=ja;
		this.queues=new TaskQueue(ja.getAID(), ja.getLM(), ja.getMSM());
		this.tem=new TaskExecutionModel(ja.getAID().getLocalName());

		//executionThread=new Thread(this);
		//executionThread.start();
		MSPRepository.getInstance().register(ja.getName().substring(0,ja.getName().indexOf('@')), this);
		replanThread.setName("Replanning "+ja.getAID().getLocalName());
		replanThread.start();
	}





	public void lifeCycle() {
		doReplan();

	}

	/**
	 * It proceeds to execute tasks stored in the execution queues. If the mode is automatic and
	 * there were tasks stored previous waiting the user to choose, then it means there is a
	 * transition from manual to automatic. Therefore, all tasks waiting to be chosen are considered
	 * and executed. Before executing the task, it is checked that there exist the inputs it requires.
	 * If they exist, the tasks is executed.  If they did not, the task is considered as executed and 
	 * it is removed from the queue. 
	 * If the execution mode is manual, none of this is applied, since the user, when choosing a task, will
	 * invoke its execution.
	 *  
	 *
	 */

	private boolean hasBreakpoint(String taskType){

		return IAFProperties.getGraphicsOn() &&
		this.ja.getGraphics().getSelectedTaskBreakpoints().contains(taskType);
	}

	private synchronized void processQueues(){
		Task oldtask=null;
		long delay=MainInteractionManager.getInstance().getDelay();	

		if (MainInteractionManager.getInstance().isAutomatic() && queues.manualQueueSize()!=0){
			// Emptying the buffer of tasks shown in the User GUI. It is assumed
			// a manual to automatic transition is in progress
			if (queues.manualQueueSize()!=0){
				Task nextTask=queues.manualQueuefirstElement();
				Vector<MentalEntity> missing=queues.locateMissingItems(nextTask);								
				if (missing.size()!=0){						
					StringBuffer missingitems=new StringBuffer();
					for (int j=0;j<missing.size();j++){
						missingitems.append(missing.elementAt(j).getType()+","); 
					}
					MainInteractionManager.logMSP("Aborting task "+nextTask.getID()+
							" because there are not all required inputs, concretely "+missingitems+"  are missing",
							this.ja.getAID().getLocalName(),nextTask.getID(), nextTask.getType());
					DebugUtils.logEvent("TaskAborted", new String[]{ja.getAID().getLocalName(),nextTask.getType(),nextTask.getID(),missingitems.toString()});
					queues.taskExecuted(nextTask);
					
				} else
				{
					processTaskExecution(nextTask);
					//queues.taskExecuted(nextTask);
				}				
			}
		} else {
			final Task t=queues.nextTask();
			if (t!=null && !queues.manualQueueContains(t)){
				if (MainInteractionManager.getInstance().isAutomatic())
					queues.taskExecuted(t);
				processTaskExecution(t);	
			}
			oldtask=t;
		}
		//if (delay<0)
		//	delay=5000;
		//replan();
		//try {
		//this.wait();
		//	Thread.currentThread().sleep(delay);

		//} catch (InterruptedException e) {
		//	e.printStackTrace();
		//}			
	}


	/**
	 * This method manages the execution of a task either manually or automatically.
	 * In the automatic mode, it executes the task and processes the consecuences. In the 
	 * manual mode, it has to wait for the user to request the concrete task execution. In the
	 * manual mode, the user is shown the task queue (not ordered) so that he can decide which
	 * one to execute.
	 * 
	 * @param t The task whose execution has to be managed
	 */
	private void processTaskExecution(final Task t) {
		/*	new Thread(){					
		 public void run(){*/
		//queues.printQueues();
		if (hasBreakpoint(t.getType())){
			MainInteractionManager.getInstance().goManual();
		}
		if (MainInteractionManager.getInstance().isAutomatic()){
			MainInteractionManager.logMSP("Executing task "+t.getID(),ja.getAID().getLocalName());			
			MainInteractionManager.logMSP("Executing task "+t.getID(),ja.getAID().getLocalName(), t.getID(), t.getType());
			tem.executeTask(ja,queues, msm,t);				
			queues.taskExecuted(t);
		} else {
			queues.manualQueue(t);
			MainInteractionManager.logMSP("Queuing task "+t.getID()+" in the manual queue",ja.getAID().getLocalName(),t.getID(), t.getType());
			String name=t.getID();

			queues.descheduleTask(t);
			if (t.getConversationContext()!=null)
				name=name+" for "+ t.getConversationContext().getConversationID();
			if (graphics!=null)
				graphics.addTask(t,name, new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {	
						new Thread(){
							public void run(){
								MainInteractionManager.logMSP("Executing task "+t.getID(),ja.getAID().getLocalName(),t.getID(),t.getType());
								tem.executeTask(ja,queues, msm,t);
								queues.taskExecuted(t);							
							}
						}.start();
					}});
		}
		//		}}.start();
	}



	/**
	 * Obtains a list of activated tasks in a certain moment. Initially, they are obtained
	 * using the goal satisfaction criterium. Through method  getAllPendingGoals, a list of tasks satisfying
	 * pending goals is obtained. This list is studied in order to determine which tasks are active in the
	 * list. Active tasks are those having all necessary inputs available in the conversation context or in 
	 * the agent mental state. The initial list of tasks is purged, filtering those tasks that cannot be
	 * activated. At the end, active tasks are returned as a vector.
	 * 
	 */	
	public Vector<Task> activeTasks() throws TaskException{
		Vector<Task> pending=msm.getAllPendingGoals();
		Vector<Task> atasks=new Vector<Task>();
		Enumeration enumeration=pending.elements();
		while (enumeration.hasMoreElements()){
			StateGoal sg=(StateGoal)enumeration.nextElement();

			Vector<Task> tasks=this.ja.tasksThatSatisfyGoal(sg.getId());
		/*	// schedule first those tasks affecting conversations not finished/aborted
			Vector<Task> finishedTasks=new Vector<Task>(); 
			for (Task t:tasks){
				if (t.getConversationContext()!=null){
					if	(t.getConversationContext().getState().equalsIgnoreCase("finished") || 
							t.getConversationContext().getState().equalsIgnoreCase("aborted")){
						finishedTasks.add(t);
					}
				}
			}
			// remove those tasks associated to finished/aborted conversations if there is another
			// task of the same type and same conversation context type and with a non finished/aborted
			// conversation
			tasks.removeAll(finishedTasks);
			Vector<Task> removeFromFinished=new Vector<Task>(); 
			for (Task t:finishedTasks){
				boolean found=false;
				for (Task original:tasks){
					found= found || 
					(original.getType().equalsIgnoreCase(t.getType()) 
							&& t.getConversationContext()!=null
							&& original.getConversationContext()!=null
							&& t.getConversationContext().getInteraction().getId().equalsIgnoreCase(
									original.getConversationContext().getInteraction().getId()));

				}
				if (found)
					removeFromFinished.add(t);
			}
			finishedTasks.removeAll(removeFromFinished);
			tasks.addAll(finishedTasks); // So that tasks associated a finished/aborted conversations are
			// scheduled at the end*/
			Vector nonactive=new Vector();
			for (int k=0;k<tasks.size();k++){
				Task t=(Task)tasks.elementAt(k);
				t.setPursuedGoal(sg);
				Vector<MentalEntity> missing=queues.locateMissingItems(t);
				Vector<MentalEntity> lockedInputs=new Vector<MentalEntity>(queues.getLockedConsumedInputs(t));

				if (missing.size()!=0){
					nonactive.add(t);
					StringBuffer missingitems=new StringBuffer();
					for (int j=0;j<missing.size();j++){
						missingitems.append(missing.elementAt(j).getId()+":"+missing.elementAt(j).getType()+","); 
					}
					MainInteractionManager.logMSP("Not considering task "+t.getID()+" because there are not all required inputs, concretely "+missingitems+"  are missing",
							ja.getLocalName(),t.getID(), t.getType());
					MainInteractionManager.logMSP("Not considering task "+t.getID()+" because there are not all required inputs, concretely "+missingitems+"  are missing",
							ja.getLocalName(),t.getID(), t.getType());
				}

				if (lockedInputs.size()!=0){
					nonactive.add(t);
					StringBuffer lockedItems=new StringBuffer();
					for (int j=0;j<lockedInputs.size();j++){
						lockedItems.append(lockedInputs.elementAt(j).getId()+":"+lockedInputs.elementAt(j).getType()+",");
					}
					MainInteractionManager.logMSP("Not considering task "+t.getID()+" because there are locks on required inputs, concretely "+lockedItems+"  are locked. Involved interaction must finish first.",
							ja.getLocalName(),t.getID(),t.getType());
					MainInteractionManager.logMSP("Not considering task "+t.getID()+" because there are locks on required inputs, concretely "+lockedItems+"  are locked. Involved interaction must finish first.",
							ja.getLocalName());
				}

			}
			tasks.removeAll(nonactive);
			atasks.addAll(tasks);
		}
		return atasks;
	}

	/**
	 * It tells if the task should be aborted or not. The task is aborted if the expected inputs
	 * are no more present. 
	 * 
	 * @param t The task whose viability is under study
	 * @return True if it should be aborted, false i.o.c.
	 */
	private boolean isAbortable(Task t){
		return queues.locateMissingItems(t).size()!=0;
	}

	/**
	 * It obtains a mental entity stored inside of a conversation object. Conversation objects
	 * contain the information received along an interaction. 
	 * 
	 * @param conv The conversation representing the interaction
	 * @param id The id of the entity to look for
	 * @return The entity or null if none exist
	 */
	protected MentalEntity obtainConversationalMentalEntity(RuntimeConversation conv, String id){
		Enumeration enumeration=conv.getCurrentContentElements();
		boolean found=false;
		MentalEntity me=null;
		while (enumeration.hasMoreElements() && !found ){
			me=(MentalEntity)enumeration.nextElement();
			found=me.getId().equalsIgnoreCase(id);
		}
		if (found)
			return me;
		else 
			return null;        	
	}

	public void doReplan(){
		synchronized(monitorDoReplanQueue){
			doReplan.add(true);
		}
	}

	/**
	 * It reviews agent mental state to reconsider if there are new active tasks. Active tasks are
	 * compared to those already scheduled. If they were already scheduled, they are removed.
	 * If they were not, then they are scheduled for execution.
	 * <p>The method is invoked from the Mental State Manager when it is known that a change has 
	 * ocurred to the mental state. A change can be editing the values of an entity, creating a new one, or
	 * removing an existing one.
	 */

	private synchronized void replan() {
		if (ja.isAgentInitialised()){
			queues.reviewScheduledTasks();
			//if (this.msm.modifiedSinceLastLecture()){

			try {	    
				Vector tasks = activeTasks();
				HashSet hs=new HashSet(tasks);
				tasks.clear();
				tasks.addAll(hs);
				//System.err.println("Replanning "+tasks.size());
				//queues.descheduleAlreadyActiveTasks(tasks);

				queues.removeAlreadyActive(tasks);


				for (int k = 0; k < tasks.size(); k++) {
					final Task t=(Task)tasks.elementAt(k);

					queues.scheduleTask(t);
					

				}
				MainInteractionManager.logMSP("Current scheduled tasks:"+queues.getScheduledTasks(),this.ja.getLocalName());
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
		//}
	}


	/**
	 * It requests the decision procedure to be started. The execution of tasks is constrained to the
	 * lifecycle of a JADE agent. Some tasks require to create behaviors, and this can be done only
	 * within another behavior. So we schedule task execution according to calls to this method. When
	 * it is called, it means that a task can be executed. 
	 * <p>The reason for this configuration is that protocols and actions go side by side. We require
	 * to establish that the agent executes one task at a time, and that task execution does not prevent
	 * the agent from keep on receiving  or sending messages.
	 *
	 */
	public synchronized void wakeup() {
		this.notifyAll();

	}


	public  void locksChanged() {
		doReplan();
	}


	public Vector<Task> getScheduledTasks() {
		return queues.getScheduledTasks();
	}


	public int getState() {
		return state;
	}






}
