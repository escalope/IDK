package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_2_08_04 extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_2_08_04(Browser questionBrowser) {
		super(ATQuestion_2_08_04.getInstance(), questionBrowser);
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
		// questionVariables.put("Superior", ATEntity.Subject); // Original is Superior Agent.
		// questionVariables.put("Subordinated", ATEntity.Subject); // Original is Subordinated Agent.
		// questionVariables.put("Group", ATEntity.Community);
		// questionVariables.put("Give Command to Subordinates", ATEntity.Activity);
		// questionVariables.put("Decision", ATEntity.Objective); // Original is Objective.
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get the potential entities.
		List<String> potentialActors = basicView.getEntitiesForATRole(ATEntity.Subject);
		List<String> potentialObjectives = basicView.getEntitiesForATRole(ATEntity.Objective);
		List<String> potentialActivities = basicView.getEntitiesForATRole(ATEntity.Activity);
		List<String> potentialCommunities = basicView.getEntitiesForATRole(ATEntity.Community);
		// Get the answers.
		GraphEntity oneSuperior = null;
		GraphEntity oneSubordinated = null;
		GraphEntity oneGroup = null;
		GraphEntity oneGiveCommand = null;
		GraphEntity oneDecision = null;
		GraphRelationship oneDecomposeSuperior = null;
		GraphRelationship oneDecomposeSubordinated = null;								
		GraphRelationship oneAccomplishedBy = null;
		GraphRelationship oneProduce = null;
		GraphRelationship onePursue = null;
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialActors.size(); i_1++) {  // Superior
			oneSuperior = null;
			for (int j = 0; oneSuperior == null && j < entities.length; j++)
				if (entities[j].getID().equalsIgnoreCase( potentialActors.get(i_1) ) )
					oneSuperior = entities[j];
			for (int i_2 = 0; i_2 < potentialActors.size(); i_2++) { // Subordinated
				oneSubordinated = null;
				for (int j = 0; oneSubordinated == null && j < entities.length; j++)
					if ((i_1 != i_2) && entities[j].getID().equalsIgnoreCase( potentialActors.get(i_2) ) )
						oneSubordinated = entities[j];
				if (oneSubordinated != null) {
					for (int i_3 = 0; i_3 < potentialCommunities.size(); i_3++) { // Group
						oneGroup = null;
						for (int j = 0; oneGroup == null && j < entities.length; j++)
							if (entities[j].getID().equalsIgnoreCase( potentialCommunities.get(i_3) ) )
								oneGroup = entities[j];
						oneDecomposeSuperior = this.isConnected(oneSuperior, oneGroup, ATRelation.Decompose);
						if (oneDecomposeSuperior != null)
							oneDecomposeSubordinated = this.isConnected(oneSubordinated, oneGroup, ATRelation.Decompose);	
						if (oneDecomposeSuperior != null && oneDecomposeSubordinated != null) {
							for (int i_4 = 0; i_4 < potentialActivities.size(); i_4++) { // Give Command to Subordinates
								oneGiveCommand = null;
								for (int j = 0; oneGiveCommand == null && j < entities.length; j++)
									if (entities[j].getID().equalsIgnoreCase( potentialActivities.get(i_4) ) )
										oneGiveCommand = entities[j];
								oneAccomplishedBy = this.isConnected(oneSuperior, oneGiveCommand, ATRelation.AccomplishedBy);
								if (oneAccomplishedBy != null) {
									for (int i_5 = 0; i_5 < potentialObjectives.size(); i_5++) { // Decision
										oneDecision = null;
										for (int j = 0; oneDecision == null && j < entities.length; j++)
											if (entities[j].getID().equalsIgnoreCase( potentialObjectives.get(i_5) ) )
												oneDecision = entities[j];
										oneProduce = this.isConnected(oneGiveCommand, oneDecision, ATRelation.Produce);
										if (oneProduce != null)
											onePursue = this.isConnected(oneSubordinated, oneDecision, ATRelation.Pursue);
										else
											onePursue = null;
										if (oneDecomposeSuperior != null && oneDecomposeSubordinated != null &&
												oneAccomplishedBy != null && oneProduce != null && onePursue != null) {
											// Store the names and elements in the answer.
											Map<String, String> mapTmp = new HashMap<String, String>();
											Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
											// Superior -> ATEntity.Subject
											mapTmp.put("Superior", oneSuperior.getID());
											graphTmp.put("Superior", oneSuperior);
											// Decompose: Group -> Superior -> ATRelation.Decompose
											mapTmp.put("Decompose: Group -> Superior", oneDecomposeSuperior.getID());
											graphTmp.put("Decompose: Group -> Superior", oneDecomposeSuperior);
											// Subordinated -> ATEntity.Subject
											mapTmp.put("Subordinated", oneSubordinated.getID());
											graphTmp.put("Subordinated", oneSubordinated);
											// Decompose: Group -> Subordinated -> ATRelation.Decompose
											mapTmp.put("Decompose: Group -> Subordinated", oneDecomposeSubordinated.getID());
											graphTmp.put("Decompose: Group -> Subordinated", oneDecomposeSubordinated);
											// Group -> ATEntity.Community
											mapTmp.put("Group", oneGroup.getID());
											graphTmp.put("Group", oneGroup);
											// AccomplishedBy: Give Command to Subordinates -> Superior -> ATRelation.AccomplishedBy
											mapTmp.put("AccomplishedBy: Give Command to Subordinates -> Superior", oneAccomplishedBy.getID());
											graphTmp.put("AccomplishedBy: Give Command to Subordinates -> Superior", oneAccomplishedBy);
											// Give Command to Subordinates -> ATEntity.Activity
											mapTmp.put("Give Command to Subordinates", oneGiveCommand.getID());
											graphTmp.put("Give Command to Subordinates", oneGiveCommand);
											// Produce: Give Command to Subordinates -> Decision -> ATRelation.Produce
											mapTmp.put("Produce: Give Command to Subordinates -> Decision", oneProduce.getID());
											graphTmp.put("Produce: Give Command to Subordinates -> Decision", oneProduce);
											// Pursue: Subordinated -> Decision -> ATRelation.Pursue
											mapTmp.put("Pursue: Subordinated -> Decision", onePursue.getID());
											graphTmp.put("Pursue: Subordinated -> Decision", onePursue);
											// Decision -> ATEntity.Objective
											mapTmp.put("Decision", oneDecision.getID());
											graphTmp.put("Decision", oneDecision);
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
		this.setPotentialAnswers(potentialAnswersTmp);
		this.setPotentialAnswersElements(potentialAnswersElements);
		
		this.completePotentialAnswers(potentialAnswersTmp.size());
	}

}
