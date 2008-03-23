package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_1_16_03 extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_1_16_03(Browser questionBrowser) {
		super(ATQuestion_1_16_03.getInstance(), questionBrowser);
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
		// questionVariables.put("Goal 1", ATEntity.Objective);
		// questionVariables.put("Goal 2", ATEntity.Objective);
		// questionVariables.put("Choose Goal", ATEntity.Activity);
		// questionVariables.put("Evidence for Goal 1", ATEntity.Tool);
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get Get the potential entities.
		List<String> potentialActors = basicView.getEntitiesForATRole(ATEntity.Subject);
		List<String> potentialObjectives = basicView.getEntitiesForATRole(ATEntity.Objective);
		List<String> potentialActivities = basicView.getEntitiesForATRole(ATEntity.Activity);
		List<String> potentialTools = basicView.getEntitiesForATRole(ATEntity.Tool);
		// Get the answers.
		GraphEntity oneActor = null;
		GraphEntity oneGoal1 = null;
		GraphEntity oneGoal2 = null;
		GraphEntity oneDecissionActivity = null;
		GraphEntity oneEvidence = null;
		GraphRelationship onePursue1 = null;
		GraphRelationship onePursue2 = null;
		GraphRelationship oneTransform1 = null;
		GraphRelationship oneTransform2 = null;
		GraphRelationship oneUse = null;
		GraphRelationship oneProduce = null;
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialActors.size(); i_1++) {
			oneActor = null;
			for (int j = 0; oneActor == null && j < entities.length; j++)
				if (entities[j].getID().equalsIgnoreCase( potentialActors.get(i_1) ) )
					oneActor = entities[j];
			for (int i_2 = 0; i_2 < potentialObjectives.size(); i_2++) {
				oneGoal1 = null;
				for (int j = 0; oneGoal1 == null && j < entities.length; j++)
					if (entities[j].getID().equalsIgnoreCase( potentialObjectives.get(i_2) ) )
						oneGoal1 = entities[j];
				onePursue1 = this.isConnected(oneActor, oneGoal1, ATRelation.Pursue);
				if (onePursue1 != null) {
					for (int i_3 = 0; i_3 < potentialObjectives.size(); i_3++) {
						oneGoal2 = null;
						for (int j = 0; oneGoal2 == null && j < entities.length; j++)
							if (entities[j].getID().equalsIgnoreCase( potentialObjectives.get(i_3) ) )
								oneGoal2 = entities[j];
						onePursue2 = this.isConnected(oneActor, oneGoal2, ATRelation.Pursue);
						if (onePursue2 != null) {
							for (int i_4 = 0; i_4 < potentialActivities.size(); i_4++) {
								oneDecissionActivity = null;
								for (int j = 0; oneDecissionActivity == null && j < entities.length; j++)
									if (entities[j].getID().equalsIgnoreCase( potentialActivities.get(i_4) ) )
										oneDecissionActivity = entities[j];
								oneTransform1 = this.isConnected(oneDecissionActivity, oneGoal1,
										ATRelation.Transform);
								if (oneTransform1 != null)
									oneTransform2 = this.isConnected(oneDecissionActivity, oneGoal2,
											ATRelation.Transform);
								if ((oneTransform1 != null) && (oneTransform2 != null))
									oneProduce = this.isConnected(oneDecissionActivity, oneGoal1,
											ATRelation.Produce);
								if ((oneTransform1 != null) &&
										(oneTransform2 != null) &&
										(oneProduce != null)) {
									for (int i_5 = 0; i_5 < potentialTools.size(); i_5++) {
										for (int j = 0; j < entities.length; j++)
											if (entities[j].getID().equalsIgnoreCase( potentialTools.get(i_5) ) )
												oneEvidence = entities[j];
										oneUse = this.isConnected(oneDecissionActivity, oneEvidence,
												ATRelation.Use);
										if (oneUse != null) {
											if (onePursue1 != null && onePursue2 != null &&
													oneTransform1 != null && oneTransform2 != null &&
													oneUse != null && oneProduce != null) {
												// Store the names and elements in the answer.
												Map<String, String> mapTmp = new HashMap<String, String>();
												Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
												// Actor -> ATEntity.Subject
												mapTmp.put("Actor", oneActor.getID());
												graphTmp.put("Actor", oneActor);
												// Pursue: Actor -> Goal 1 -> ATRelation.Pursue
												mapTmp.put("Pursue: Actor -> Goal 1", onePursue1.getID());
												graphTmp.put("Pursue: Actor -> Goal 1", onePursue1);
												// Goal 1 -> ATEntity.Objective
												mapTmp.put("Goal 1", oneGoal1.getID());
												graphTmp.put("Goal 1", oneGoal1);
												// Pursue: Actor -> Goal 2 -> ATRelation.Pursue
												mapTmp.put("Pursue: Actor -> Goal 2", onePursue2.getID());
												graphTmp.put("Pursue: Actor -> Goal 2", onePursue2);
												// Goal 2 -> ATEntity.Objective
												mapTmp.put("Goal 2", oneGoal2.getID());
												graphTmp.put("Goal 2", oneGoal2);
												// Choose Goal -> ATEntity.Activity
												mapTmp.put("Choose Goal", oneDecissionActivity.getID());
												graphTmp.put("Choose Goal", oneDecissionActivity);
												// Transform: Choose Goal -> Goal 1 -> ATRelation.Transform
												mapTmp.put("Transform: Choose Goal -> Goal 1", oneTransform1.getID());
												graphTmp.put("Transform: Choose Goal -> Goal 1", oneTransform1);
												// Transform: Choose Goal -> Goal 2 -> ATRelation.Transform
												mapTmp.put("Transform: Choose Goal -> Goal 2", oneTransform2.getID());
												graphTmp.put("Transform: Choose Goal -> Goal 2", oneTransform2);
												// Produce: Choose Goal -> Goal 1 -> ATRelation.Produce
												mapTmp.put("Produce: Choose Goal -> Goal 1", oneProduce.getID());
												graphTmp.put("Produce: Choose Goal -> Goal 1", oneProduce);
												// Use: Choose Goal -> Evidence for Goal 1 -> ATRelation.Use
												mapTmp.put("Use: Choose Goal -> Evidence for Goal 1", oneUse.getID());
												graphTmp.put("Use: Choose Goal -> Evidence for Goal 1", oneUse);
												// Evidence for Goal 1 -> ATEntity.Tool
												mapTmp.put("Evidence for Goal 1", oneEvidence.getID());
												graphTmp.put("Evidence for Goal 1", oneEvidence);
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
