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
import ingenias.jade.comm.StateBehavior;
import ingenias.jade.components.Task;
import ingenias.jade.exception.TaskException;
import ingenias.jade.graphics.AgentGraphics;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.testing.DebugUtils;
import ingenias.testing.MSPRepository;
//import jade.core.Scheduler;
import jade.core.behaviours.OneShotBehaviour;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Vector;

import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelListener;

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
public class MentalStateProcessor implements LocksListener {

	TaskQueue queues = null;
	MentalStateManager msm = null;
	JADEAgent ja = null;
	AgentGraphics graphics = null;
	Thread executionThread = null;
	TaskExecutionModel tem = null;
	private int MSPState = AgentStates.PLANNING_STARTING;
	private Vector<Boolean> doReplan = new Vector<Boolean>();
	private Integer monitorDoReplanQueue = new Integer(1);
	Thread replanThread = new Thread() {

		public void run() {
			while (true) {
				
				if (Simulation.getInstance().isSimulationModeEnabled())
					Simulation.getInstance().waitNextCycle(ja.getAID().getLocalName());
				
				boolean repeat = true;

				MSPState = AgentStates.WAIT_FOR_CHANGE_IN_MENTAL_STATE;
				while (repeat) { 
					
					msm.processNewEntitiesInConversations();
					repeat = isReplanRequestQueueEmpty(); // a plan request is triggered automatically when modifying the MS
					try {
						Thread.currentThread().sleep((long)(200*Math.random()));
					} catch (InterruptedException e) {
					}
				}
				MSPState = AgentStates.STARTING_DECISION;
				doReplan.remove(0);

				//if (ja.getMSM().modifiedSinceLastLecture())
				 replan();
				 
				 letAllJadeBehaviorsReevaluate();// WARNING:you cannot do this inside the previous loop
				 
				// When simulation mode is on, the next task execution has to
				// wait for the next cycle.
				// isSimulationModelEnabled is invoked beforehand so as not
				// to enter the synchronized method in the waitNextCycle unless
				// it is necessary. The synchronized method of a singleton object
				// induces a bottleneck in the execution since all threads
				// accesing to it would have to wait their turn

				MSPState = AgentStates.DECISION_FINISHED;


				processQueues(); // queue processing implies the execution of the first task. This execution triggers a replan, what enables the execution of
				// the next task. 
				
			//	msm.checkNullFieldsInMentalEntities();

				msm.cleanConversations();

				MSPState = AgentStates.EXECUTION_FINISHED;
				letAllJadeBehaviorsReevaluate();


			}

		}
	};

	public void letAllJadeBehaviorsReevaluate() {
		Collection<StateBehavior> stateMachines = ja.getCM().getActiveMachines().values();
		for (StateBehavior sb : stateMachines) {
			if (!sb.isState("FINISHED")) {
				//System.err.println("waking up behavior "+sb.getBehaviourName());
				int k=0;
				while (!sb.getExecutionState().equals(StateBehavior.STATE_BLOCKED) 
						&& !sb.getFinished()){
	
					try {
						Thread.currentThread().sleep((long)(100*Math.random()));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				sb.restart();
				//System.err.println("behavior "+sb.getBehaviourName()+" resumed");
			}
		};
		wakeUpCommManagerBehavior();
	}

	public void wakeUpCommManagerBehavior() {
		if (ja.getMainBehavior()!=null){
			while (!ja.getMainBehavior().
					getExecutionState().equals(
							ja.getMainBehavior().STATE_BLOCKED)){
				try {
					Thread.currentThread().sleep((long)(100*Math.random()));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				}
		/*		if (ja.getLocalName().equalsIgnoreCase("InterfaceAgent_5multipleInterfaceAgents"))
				System.err.println("esperando ");*/
			}
			ja.getMainBehavior().restart();
		}
	};

	private synchronized boolean isReplanRequestQueueEmpty() {
		return doReplan.isEmpty();
	}

	protected synchronized void removeFirstReplanRequest() {

		doReplan.remove(0);


	}


	public MentalStateProcessor(MentalStateManager msm, JADEAgent ja, AgentGraphics graphics) {
		this.msm = msm;
		this.ja = ja;
		this.graphics = graphics;
		this.queues = new TaskQueue(graphics, ja.getAID(), ja.getLM(), ja.getMSM());
		this.tem = new TaskExecutionModel(ja.getAID().getLocalName(), ja.getMSM());

		//executionThread=new Thread(this);
		//executionThread.start();
		MSPRepository.getInstance().register(ja.getName().substring(0, ja.getName().indexOf('@')), this);
		replanThread.setName("Replanning " + ja.getAID().getLocalName());
		replanThread.start();
		msm.registerChangeListener(new GraphModelListener() {
			
			@Override
			public void graphChanged(GraphModelEvent e) {
				lifeCycle();
				
			}
		});
				
	}

	public MentalStateProcessor(MentalStateManager msm, JADEAgent ja) {
		this.msm = msm;
		this.ja = ja;
		this.queues = new TaskQueue(ja.getAID(), ja.getLM(), ja.getMSM());
		this.tem = new TaskExecutionModel(ja.getAID().getLocalName(), ja.getMSM());

		//executionThread=new Thread(this);
		//executionThread.start();
		MSPRepository.getInstance().register(ja.getName().substring(0, ja.getName().indexOf('@')), this);
		replanThread.setName("Replanning " + ja.getAID().getLocalName());
		replanThread.start();
		msm.registerChangeListener(new GraphModelListener() {
			
			@Override
			public void graphChanged(GraphModelEvent e) {
				lifeCycle();
				
			}
		});
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
	private boolean hasBreakpoint(String taskType) {

		return IAFProperties.getGraphicsOn() &&
		this.ja.getGraphics().getSelectedTaskBreakpoints().contains(taskType);
	}

	private synchronized void processQueues() {
		Task oldtask = null;
		long delay = MainInteractionManager.getInstance().getDelay();

		if (MainInteractionManager.getInstance().isAutomatic() 
				&& queues.manualQueueSize() != 0) {


			// Emptying the buffer of tasks shown in the User GUI. It is assumed
			// a manual to automatic transition is in progress
			if (queues.manualQueueSize() != 0) {
				Task nextTask = queues.manualQueuefirstElement();
				Vector<MentalEntity> missing = queues.locateMissingItems(nextTask);
				if (missing.size() != 0) {
					StringBuffer missingitems = new StringBuffer();
					for (int j = 0; j < missing.size(); j++) {
						missingitems.append(missing.elementAt(j).getType() + ",");
					}
					EventManager.getInstance().abortedTaskDueToMissingItems(
							this.ja.getAID().getLocalName(),
							ja.getClass().getName().substring(0,
									ja.getClass().getName().indexOf("JADE")),
									nextTask,
									missing.toArray(new MentalEntity[missing.size()]));
					/*
                    DebugUtils.logEvent("TaskAborted", new String[]{ja.getAID().getLocalName(),nextTask.getType(),nextTask.getID(),missingitems.toString()});*/
					queues.taskExecuted(nextTask);

				} else {
					processTaskExecution(nextTask);
					//queues.taskExecuted(nextTask);
				}
			}
		} else {

			//queues.printQueues();
			final Task t = queues.nextTask();
			if (t != null && !queues.manualQueueContains(t)) {
				if (MainInteractionManager.getInstance().isAutomatic()) {
					queues.taskExecuted(t);
				}
				processTaskExecution(t);
			}
			oldtask = t;
		}

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
		if (hasBreakpoint(t.getType())) {
			MainInteractionManager.getInstance().goManual();
		}
		Vector<MentalEntity> currentInputs;
		currentInputs = new Vector(t.getInputs());
		currentInputs.addAll(t.getExpectedConsumedInputs());
		String[] inputids = new String[currentInputs.size()];
		String[] inputtypes = new String[currentInputs.size()];


		for (int j = 0; j < inputids.length; j++) {
			inputids[j] = currentInputs.elementAt(j).getId();
			inputtypes[j] = currentInputs.elementAt(j).getType();
		}
		if (MainInteractionManager.getInstance().isAutomatic()) {



			tem.executeTask(ja, queues, msm, t);

			queues.taskExecuted(t);
		} else {
			queues.manualQueue(t);
			EventManager.getInstance().manuallyScheduledTask(ja.getLocalName(),
					ja.getClass().getName().substring(0, ja.getClass().getName().indexOf("JADE")),
					t);
			String name = t.getID();

			queues.descheduleTask(t);
			if (t.getConversationContext() != null) {
				name = name + " for " + t.getConversationContext().getConversationID();
			}
			if (graphics != null) {
				graphics.addTask(t, name, new java.awt.event.ActionListener() {

					public void actionPerformed(ActionEvent e) {
						new Thread() {

							public void run() {
								Vector<MentalEntity> currentInputs;

								currentInputs = new Vector(t.getInputs());
								currentInputs.addAll(t.getExpectedConsumedInputs());
								String[] inputids = new String[currentInputs.size()];
								String[] inputtypes = new String[currentInputs.size()];
								for (int j = 0; j < inputids.length; j++) {
									inputids[j] = currentInputs.elementAt(j).getId();
									inputtypes[j] = currentInputs.elementAt(j).getType();
								}
								EventManager.getInstance().startingTaskExecution(ja.getLocalName(),
										ja.getClass().getName().substring(0, ja.getClass().getName().indexOf("JADE")),
										t);
								tem.executeTask(ja, queues, msm, t);
								EventManager.getInstance().taskExecutionFinished(
										ja.getLocalName(),
										ja.getClass().getName().substring(0, ja.getClass().getName().indexOf("JADE")),
										t);
								queues.taskExecuted(t);
							}
						}.start();
					}
				});
			}
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
	public Vector<Task> activeTasks() throws TaskException {
		Vector<Task> pending = msm.getAllPendingGoals();
		Vector<Task> atasks = new Vector<Task>();
		Enumeration enumeration = pending.elements();
		while (enumeration.hasMoreElements()) {
			StateGoal sg = (StateGoal) enumeration.nextElement();

			Vector<Task> tasks = this.ja.tasksThatSatisfyGoal(sg.getId());
	
			Vector nonactive = new Vector();
			for (int k = 0; k < tasks.size(); k++) {
				Task t = (Task) tasks.elementAt(k);
				t.setPursuedGoal(sg);
				Vector<MentalEntity> missing = queues.locateMissingItems(t);
				Vector<MentalEntity> lockedInputs = new Vector<MentalEntity>(queues.getLockedConsumedInputs(t));

				if (missing.size() != 0) {
					nonactive.add(t);
					MentalEntity[] missingitems = new MentalEntity[missing.size()];
					for (int j = 0; j < missing.size(); j++) {
						missingitems[j] = missing.elementAt(j);
					}
					EventManager.getInstance().notScheduledTaskDueToMissingItems(
							ja.getLocalName(), ja.getClass().getName().substring(0, ja.getClass().getName().indexOf("JADE")),
							t,
							missingitems);
				}

				if (lockedInputs.size() != 0) {
					nonactive.add(t);
					MentalEntity[] lockedItems = new MentalEntity[lockedInputs.size()];
					for (int j = 0; j < lockedInputs.size(); j++) {
						lockedItems[j] = lockedInputs.elementAt(j);
					}
					EventManager.getInstance().notScheduledTaskDueToLockedItems(
							ja.getLocalName(), ja.getClass().getName(),
							t,
							lockedItems);
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
	private boolean isAbortable(Task t) {
		return queues.locateMissingItems(t).size() != 0;
	}

	/**
	 * It obtains a mental entity stored inside of a conversation object. Conversation objects
	 * contain the information received along an interaction.
	 *
	 * @param conv The conversation representing the interaction
	 * @param id The id of the entity to look for
	 * @return The entity or null if none exist
	 */
	protected MentalEntity obtainConversationalMentalEntity(RuntimeConversation conv, String id) {
		Enumeration enumeration = conv.getCurrentContentElements();
		boolean found = false;
		MentalEntity me = null;
		while (enumeration.hasMoreElements() && !found) {
			me = (MentalEntity) enumeration.nextElement();
			found = me.getId().equalsIgnoreCase(id);
		}
		if (found) {
			return me;
		} else {
			return null;
		}
	}

	public  void doReplan() {
		doReplan.add(true);

	}
	
	public synchronized boolean agentInitialised(){
		return ja.isAgentInitialised();
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
		if (ja.isAgentInitialised()) {
			msm.lockChanges(); // Changes in the mental state are locked during the replan cycle
			queues.reviewScheduledTasks();
			//if (this.msm.modifiedSinceLastLecture()){

			try {
				Vector tasks = activeTasks();
				//System.err.println("active tasks "+ja.getLocalName()+" :"+tasks);
				//HashSet hs=new HashSet(tasks);
				//tasks.clear();
				//tasks.addAll(hs);
				//queues.descheduleAlreadyActiveTasks(tasks);
				queues.removeAlreadyActive(tasks);


				for (int k = 0; k < tasks.size(); k++) {
					final Task t = (Task) tasks.elementAt(k);

					queues.scheduleTask(t);


				}
				//System.err.println("scheduled tasks "+ja.getLocalName()+" :"+queues.getScheduledTasks());
				EventManager.getInstance().currentScheduledTasks(this.ja.getLocalName(),
						ja.getClass().getName().substring(
								0, ja.getClass().getName().indexOf("JADE")),
								queues.getScheduledTasks().toArray(new Task[queues.getScheduledTasks().size()]));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			msm.unlockChanges();
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

	public void locksChanged() {
		doReplan();
	}

	public Vector<Task> getScheduledTasks() {
		return queues.getScheduledTasks();
	}

	public int getState() {
		return MSPState;
	}
}
