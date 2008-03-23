package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_1_01_07 extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_1_01_07(Browser questionBrowser) {
		super(ATQuestion_1_01_07.getInstance(), questionBrowser);
	}

	
	
	/**
	 * A method that uses the browser to get the potential answers
	 * to this question.
	 * @todo
	 * FALTAN POSIBILIDADES
	 */
	public void solvePotentialAnswers() {
		ATView.initializeBrowser(this.getQuestionBrowser());
		ATView basicView = ATView.getTheInstance();
		// Potential answers to the question.
		// questionVariables.put("Actor", ATEntity.Subject);
		// questionVariables.put("Actor's Goal", ATEntity.Objective);
		// questionVariables.put("Task", ATEntity.Activity);
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get the potential entities.
		List<String> potentialActors = basicView.getEntitiesForATRole(ATEntity.Subject);
		List<String> potentialActorsGoal = basicView.getEntitiesForATRole(ATEntity.Objective);
		List<String> potentialTasks = basicView.getEntitiesForATRole(ATEntity.Activity);
		// Get the answers.
		GraphEntity oneActor = null;
		GraphEntity oneActorsGoal = null;
		GraphEntity oneTask = null;
		GraphRelationship onePursue = null;
		GraphRelationship oneContribute = null;
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialActors.size(); i_1++) {
			oneActor = null;
			for (int j = 0; oneActor == null && j < entities.length; j++)
				if (entities[j].getID().equalsIgnoreCase( potentialActors.get(i_1) ) )
					oneActor = entities[j];
			for (int i_2 = 0; i_2 < potentialActorsGoal.size(); i_2++) {
				oneActorsGoal = null;
				for (int j = 0; oneActorsGoal == null && j < entities.length; j++)
					if (entities[j].getID().equalsIgnoreCase( potentialActorsGoal.get(i_2) ) )
						oneActorsGoal = entities[j];
				onePursue = this.isConnected(oneActor, oneActorsGoal, ATRelation.Pursue);
				if (onePursue != null) {
					for (int i_3 = 0; i_3 < potentialTasks.size(); i_3++) {
						oneTask = null;
						for (int j = 0; oneTask == null && j < entities.length; j++)
							if (entities[j].getID().equalsIgnoreCase( potentialTasks.get(i_3) ) )
								oneTask = entities[j];
						oneContribute = this.isConnected(oneActorsGoal, oneTask, ATRelation.ContributePositively);
						if (onePursue != null && oneContribute != null) {
							// Store the names and elements in the answer.
							Map<String, String> mapTmp = new HashMap<String, String>();
							Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
							// Actor -> ATEntity.Subject
							mapTmp.put("Actor", oneActor.getID());
							graphTmp.put("Actor", oneActor);
							// onePursue -> ATRelation.Pursue
							mapTmp.put("Pursue: Actor -> Actor's Goal", onePursue.getID());
							graphTmp.put("Pursue: Actor -> Actor's Goal", onePursue);
							// Actor's Goal -> ATEntity.Objective
							mapTmp.put("Actor's Goal", oneActorsGoal.getID());
							graphTmp.put("Actor's Goal", oneActorsGoal);
							// onePursue -> ATRelation.ContributePositively
							mapTmp.put("Contribute+: Task -> Actor's Goal", oneContribute.getID());
							graphTmp.put("Contribute+: Task -> Actor's Goal", oneContribute);
							// Task -> ATEntity.Activity
							mapTmp.put("Task", oneTask.getID());
							graphTmp.put("Task", oneTask);
							// Roles
							this.completeRoles(mapTmp);
							// Add to potential answers.
							potentialAnswersTmp.add(mapTmp);
							// Add to potential answers.
							potentialAnswersElements.add(graphTmp);
						}
					}
				}
			}
		}
		this.setPotentialAnswers(potentialAnswersTmp);
		this.setPotentialAnswersElements(potentialAnswersElements);
		
		this.completePotentialAnswers(potentialAnswersTmp.size());
	}

}
