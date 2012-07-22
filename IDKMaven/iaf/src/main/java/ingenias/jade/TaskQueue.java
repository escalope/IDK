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
import ingenias.exception.NotFound;
import ingenias.jade.comm.LocksManager;
import ingenias.jade.components.Task;
import ingenias.jade.graphics.AgentGraphics;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.testing.DebugUtils;
import jade.core.AID;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

/**
 * This class represents a queue of tasks to be executed by the agent. This
 * queue is dynamic, since while queued, tasks can be removed. Internally, the
 * class stores two queues of tasks:
 * <ul>
 * <li> A general queue containing all tasks scheduled for execution
 * <li> A manual execution queue which contains those tasks to be executed only
 * when the user decides so.
 * </ul>
 * A task if first added to the general queue. When it is time to execute it
 * because it is a valid task (see method reviewTask), the task is added to the
 * manual queue if and only if the execution mode is manual. Otherwise, the task
 * is simply executed and removed from the general queue. If added to the manual
 * queue, the task will be removed when the user, in the GUI demands its
 * execution or when the conditions in the mental state altere the inputs
 * required by the tasks, making it no more active.
 * <p>
 * This class is accessed from different execution threads, so it is prepared
 * for concurrent access.
 * 
 * @author jj
 * 
 */
public class TaskQueue {
	private Vector<Task> tqueue = new Vector<Task>(); // General queue
	// containing all queued
	// tasks
	private Vector<Task> manualExecQueue = new Vector<Task>(); // tasks which
	// are queued so
	// that the user
	// can
	// choose which one to launch
	private AgentGraphics graphics = null; // Graphics to show the task
	// execution
	private AID agentID = null; // The id of the agent who will execute the
	// tasks
	private ingenias.jade.comm.LocksManager lm = null; // The responsible of
	// creating/removing
	// locks
	private MentalStateReader msr = null; // An interface to have read-only
	// access to the mental state

	public TaskQueue(AgentGraphics graphics, AID agentID, LocksManager lm,
			MentalStateReader msr) {
		this.graphics = graphics;
		this.agentID = agentID;
		this.lm = lm;
		this.msr = msr;
	}

	public TaskQueue( AID agentID, LocksManager lm,
			MentalStateReader msr) {		
		this.agentID = agentID;
		this.lm = lm;
		this.msr = msr;
	}

	/**
	 * When a task is executed, the task queue removes it from its list, it also
	 * removes its respective entry in the graphic panel for task execution.
	 * 
	 * @param t
	 */
	public synchronized void taskExecuted(Task t) {
		
		tqueue.remove(t);
		if (graphics!=null)
			graphics.removeTaskPanel(t);
		manualExecQueue.remove(t);
		if (t.getConversationContext()!=null)
			this.msr.conversationAlreadyUsed(t.getConversationContext());
		this.reviewScheduledTasks();

	}

	/**
	 * It schedules a task for execution. It is added to the queued.
	 * 
	 * @param t
	 */
	public synchronized void scheduleTask(Task t) {
		if (!tqueue.contains(t) && !manualExecQueue.contains(t)){
			
			DebugUtils.logEvent("TaskScheduled", new String[]{agentID.getLocalName(),t.getType(),t.getID(),t.getInputs().toString()});
			tqueue.add(t);
			EventManager.getInstance().scheduledTask(agentID.getLocalName(), "", t);
			if (t.getConversationContext()!=null){
				msr.conversationIsInUse(t.getConversationContext());	
			}
		} else {
			
			//MainInteractionManager.logMSP("Task "+t.getID()+" of type "+t.getType()+" was already scheduled", agentID.getLocalName());
		}
	}

	/**
	 * The queue where there are tasks whose execution is demanded by the user.
	 * It is related with the manual execution of tasks described in the Mental
	 * State Processor
	 * 
	 * @param t
	 */
	public synchronized void manualQueue(Task t) {
		manualExecQueue.add(t);
	}

	/**
	 * It obtains the first element in the manual queue
	 * 
	 * @return The first task in the queue or null if none
	 */
	public synchronized Task manualQueuefirstElement() {
		return manualExecQueue.firstElement();
	}

	/**
	 * It obtains the first element of the task queue
	 * 
	 * @return
	 */
	public synchronized Task nextTask() {
		Task result = null;
		if (!tqueue.isEmpty()) {
			Task first = tqueue.firstElement();
			// tqueue.remove(0);
			result = first;
		}
		return result;
	}

	/**
	 * It tells whether the manual queue contains or not the task
	 * 
	 * @param t
	 *            The task to be looked for
	 * @return True if it existed, false i.o.c.
	 */
	synchronized boolean manualQueueContains(Task t) {
		return manualExecQueue.contains(t);
	}

	/**
	 * It returns the size of the manual queue
	 * 
	 * @return
	 */
	public synchronized int manualQueueSize() {
		return manualExecQueue.size();
	}

	/**
	 * It removes a task from the main queue. The graphics are updated to remove
	 * the task.
	 * 
	 * @param t
	 */
	public synchronized void descheduleTask(Task t) {
	
	/*	MainInteractionManager.logMSP("Aborting task " + t.getID(), agentID.getLocalName());
		MainInteractionManager.logMSP("Aborting task " + t.getID(), agentID.getLocalName(),t.getID(),t.getType());*/
		tqueue.remove(t); // Dequeue the task
		//DebugUtils.logEvent("TaskAborted", new String[]{agentID.getLocalName(),t.getType(),t.getID()});
		MainInteractionManager.refresh();
	}

	/**
	 * It tells if the task queue contains all elements passed as parameter
	 * 
	 * @param tasks
	 * @return
	 */
	public synchronized boolean tQueueContainsAll(Vector tasks) {
		// TODO Auto-generated method stub
		return tasks.containsAll(tqueue);
	}

	/**
	 * It removes from the "tasks" list those which are already scheduled for
	 * execution either in the main queue or in the manual execution queue
	 * 
	 * @param tasks
	 */
	public synchronized void removeAlreadyActive(Vector tasks) {
		// TODO Auto-generated method stub
		tasks.removeAll(this.tqueue);
		tasks.removeAll(this.manualExecQueue);
	}

	/**
	 * 
	 * 
	 */
	synchronized void reviewScheduledTasks() {

		// Emptying the buffer of tasks shown in the User GUI. It is assumed
		// a manual to automatic transition is in progress
		Vector<Task> toReview = new Vector<Task>(manualExecQueue);
		Enumeration enumeration = toReview.elements();
		while (enumeration.hasMoreElements()) {
			Task nextTask = (Task) enumeration.nextElement();
			if (!reviewTaskToBeExecuted(nextTask)) {
				manualExecQueue.remove(nextTask);
				if (graphics!=null)
					graphics.removeTaskPanel(nextTask);
				if (nextTask.getConversationContext()!=null)
					this.msr.conversationAlreadyUsed(nextTask.getConversationContext());
			}
		}
	}

	/**
	 * It tells if a concrete tasks is eligible for execution. A task can be
	 * executed if:
	 * <ul>
	 * <li> It has all of its required inputs available either in the mental
	 * state or in the interaction context. In other words, if the sice of the
	 * list returned by locateMissingItems is zero.
	 * <li> If elements to be consumed can be deleted from the mental state or
	 * from the interaction context. This information is obtained from the Locks
	 * Manager. An information is locked if it has to be sent first to other
	 * agent
	 * </ul>
	 * 
	 * @param nextTask
	 * @return True if it can be executed. False i.o.c.
	 */
	public synchronized boolean reviewTaskToBeExecuted(Task nextTask) {
		Vector<MentalEntity> missing = locateMissingItems(nextTask);
		boolean valid = true;
		// System.err.println("Missing:..."+nextTask+" "+missing.size());
		if (missing.size() != 0) {
			// Determines missing information
			StringBuffer missingitems = new StringBuffer();
			for (int j = 0; j < missing.size(); j++) {
				missingitems.append(missing.elementAt(j).getType() + ",");
			}
			EventManager.getInstance().abortedTaskDueToMissingItems(
					agentID.getLocalName(), "", nextTask, missing.toArray(
							new MentalEntity[missing.size()]));
			// System.err.println("removed "+nextTask);
			valid = false;
		} else {
			// Examines current locks

			Vector<MentalEntity> lockedElements = getLockedConsumedInputs(nextTask);

			if (lockedElements.size()>0){
				EventManager.getInstance().abortedTaskDueToLockedItems(
						agentID.getLocalName(), "", nextTask, lockedElements.toArray(
								new MentalEntity[lockedElements.size()]));
			}
			valid = lockedElements.size() == 0;
			
			/*for (MentalEntity ei : lockedElements) {
				MainInteractionManager.logMSP("Aborting task " + nextTask.getID()
						+ " because it consumes " + ei.getType()
						+ ", an input required by an active interaction",
						agentID.getLocalName(), nextTask.getID(),nextTask.getType());
			}*/

			/*if (lockedElements.size() > 0) {
				// The task can be executed
				MainInteractionManager.logMSP(nextTask.getID()
						+ " did not have any lock or missing element "
						+ this.lm.getLocks(), this.agentID.getLocalName(),
						nextTask.getID(),nextTask.getType());
			} else

				MainInteractionManager.logMSP(nextTask.getID()
						+ " inputs review completed. It is ready for execution" , this.agentID
						.getLocalName(), nextTask.getID(),nextTask.getType());*/
		}
		return valid;
	}

	/**
	 * @param nextTask
	 * @return
	 */
	public Vector<MentalEntity> getLockedConsumedInputs(Task nextTask) {
		HashSet<MentalEntity> consumed = nextTask.getExpectedConsumedInputs();
		Vector<MentalEntity> lockedElements = new Vector<MentalEntity>();
		Iterator<MentalEntity> iteratorCons = consumed.iterator();
		boolean anyLocked = false;
		while (iteratorCons.hasNext() && !anyLocked) {
			MentalEntity ei = null;
			ei = iteratorCons.next();
			if (nextTask.getConversationContext()==null){
			// it is a non conversational task	
				anyLocked = anyLocked || !this.lm.canBeDeleted(ei);
			} else {
				// it is a conversational task
			anyLocked = anyLocked || !this.lm.canBeDeleted(ei,nextTask.getConversationContext());
			}
			if (anyLocked)
				lockedElements.add(ei);

		}
		return lockedElements;
	}

	/**
	 * It tells if a task has all necessary inputs available at a certain
	 * moment. Necessary inputs are passed to the task when it is scheduled. Due
	 * to the amount of time between the schedule and the actual execution of
	 * the task, some of these inputs may have been deleted or removed by other
	 * tasks (modification of input entities does not affect the execution). If
	 * removed, the already scheduled tasks perhaps has to be cancelled.
	 * 
	 * @param t
	 *            The task to be executed
	 * @return A list of the missing elements.
	 */
	public Vector<MentalEntity> locateMissingItems(Task t) {
		HashSet<MentalEntity> inputs=new HashSet<MentalEntity>(t.getInputs());
		inputs.addAll(t.getExpectedConsumedInputs());
		Vector<MentalEntity> missing=new  Vector<MentalEntity>();

		if (t.getConversationContext()!=null){
			try {
				this.msr.findEntity(t.getConversationContext().getId());
			}catch (NotFound nf){				
				missing.add(t.getConversationContext());				
			}
		}

		for (MentalEntity element:inputs){											
			try {
				MentalEntity requiredEntity = this.msr.obtainConversationalMentalEntity(t.getConversationContext(),element.getId());
			} catch (NotFound nf){
				missing.add(element);
				if (t.getConversationContext()!=null){
				/*	MainInteractionManager.logMSP("Not found entity "+element.getId()+" in conversastion "+t.getConversationContext().getConversationID()+
							" . Scheduled task "+ t.getID()+" is going to be aborted", this.agentID
							.getLocalName(), t.getID(),t.getType());*/
					
				}
			}
		}

		return missing;
	}

	/**
	 * It removes a list of task tasks from the execution queue
	 * 
	 * @param tasks
	 *            The tasks to be removed
	 */
	/*private synchronized void descheduleAlreadyActiveTasks(Vector<Task> tasks) {
		if (!this.tQueueContainsAll(tasks)) {
			Vector abortedTasks = new Vector(this.tqueue);
			abortedTasks.addAll(this.manualExecQueue);
			abortedTasks.removeAll(tasks);
			// System.err.println("Aborting "+abortedTasks.size());
			for (int k = 0; k < abortedTasks.size(); k++) {
				this.descheduleTask((Task) abortedTasks.elementAt(k));
			}
		}
	}*/

	/**
	 * It prints in the standard output the two queues of tasks stored within
	 * this class. For debugging purposes only.
	 * 
	 */
	synchronized void printQueues() {
		
		System.out.println("Manual");
		System.out.println("-------");
		for (int k = 0; k < manualQueueSize(); k++) {
			System.out.println(k + ":"
					+ this.manualExecQueue.elementAt(k).getID() + " ("
					+ this.agentID.getLocalName() + ")");
		}
		System.out.println("Auto");
		System.out.println("----");
		for (int k = 0; k < this.tqueue.size(); k++) {
			System.out.println(k + ":" + this.tqueue.elementAt(k).getID()
					+ " (" + this.agentID.getLocalName() + ")");
		}
		System.out.println("----");
	}

	public Vector<Task> getScheduledTasks() {
		Vector<Task> tasks=new Vector<Task>();
		tasks.addAll(tqueue);
		tasks.addAll(manualExecQueue);
		return tasks;
	}

}
