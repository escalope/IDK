package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_1_06_02_A extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_1_06_02_A(Browser questionBrowser) {
		super(ATQuestion_1_06_02_A.getInstance(), questionBrowser);
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
		// questionVariables.put("Group", ATEntity.Community);
		// questionVariables.put("Group Goal", ATEntity.Objective);
		// questionVariables.put("Key Goal", ATEntity.Objective);
		// questionVariables.put("Component Goal", ATEntity.Objective);
		// questionVariables.put("Component", ATEntity.Artifact);
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get the potential entities.
		List<String> potentialGroups = basicView.getEntitiesForATRole(ATEntity.Community);
		List<String> potentialObjectives = basicView.getEntitiesForATRole(ATEntity.Objective);
		List<String> potentialArtifacts = basicView.getEntitiesForATRole(ATEntity.Artifact);
		// Get the answers.
		GraphEntity oneGroup = null;
		GraphEntity oneGroupGoal = null;
		GraphEntity oneKeyGoal = null;
		GraphEntity oneComponentGoal = null;
		GraphEntity oneComponent = null;
		GraphRelationship onePursueGroup = null;
		GraphRelationship onePursueKey = null;
		GraphRelationship onePursueComponent = null;
		GraphRelationship oneEssentialComponent = null;
		GraphRelationship oneEssentialGroup = null;
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialGroups.size(); i_1++) {
			oneGroup = null; 
			for (int j = 0; oneGroup == null && j < entities.length; j++)
				if (entities[j].getID().equalsIgnoreCase( potentialGroups.get(i_1) ) )
					oneGroup = entities[j];
			for (int i_2 = 0; i_2 < potentialObjectives.size(); i_2++) {
				oneGroupGoal = null;
				for (int j = 0; oneGroupGoal == null && j < entities.length; j++)
					if (entities[j].getID().equalsIgnoreCase( potentialObjectives.get(i_2) ) )
						oneGroupGoal = entities[j];
				onePursueGroup = this.isConnected(oneGroup, oneGroupGoal, ATRelation.Pursue);
				if (onePursueGroup != null) {
					for (int i_3 = 0; i_3 < potentialObjectives.size(); i_3++) {
						oneKeyGoal = null;
						for (int j = 0; oneKeyGoal == null && j < entities.length; j++)
							if (entities[j].getID().equalsIgnoreCase( potentialObjectives.get(i_3) ) )
								oneKeyGoal = entities[j];
						onePursueKey = this.isConnected(oneGroup, oneKeyGoal, ATRelation.Pursue);
						if (onePursueKey != null)
							oneEssentialGroup = this.isConnected(oneKeyGoal, oneGroupGoal, ATRelation.Essential);
						if (onePursueKey != null && oneEssentialGroup != null) {
							for (int i_4 = 0; i_4 < potentialObjectives.size(); i_4++) {
								oneComponentGoal = null;
								for (int j = 0; oneComponentGoal == null && j < entities.length; j++)
									if (entities[j].getID().equalsIgnoreCase( potentialObjectives.get(i_4) ) )
										oneComponentGoal = entities[j];
								oneEssentialComponent = this.isConnected(oneKeyGoal, oneComponentGoal, ATRelation.Essential);
								if (oneEssentialComponent != null) {
									for (int i_5 = 0; i_5 < potentialArtifacts.size(); i_5++) {
										oneComponent = null;
										for (int j = 0; oneComponent == null && j < entities.length; j++)
											if ((i_1 != i_5) && entities[j].getID().equalsIgnoreCase( potentialArtifacts.get(i_5) ) )
												oneComponent = entities[j];
										if (oneComponent != null)
											onePursueComponent = this.isConnected(oneComponent, oneComponentGoal, ATRelation.Pursue);
										else
											onePursueComponent = null;
										if (onePursueGroup != null && onePursueKey != null && onePursueComponent != null &&
												oneEssentialComponent != null && oneEssentialGroup != null) {
											// Store the names and elements in the answer.
											Map<String, String> mapTmp = new HashMap<String, String>();
											Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
											// Group -> ATEntity.Community
											mapTmp.put("Group", oneGroup.getID());
											graphTmp.put("Group", oneGroup);
											// Pursue: Group -> Group Goal -> ATRelation.Pursue
											mapTmp.put("Pursue: Group -> Group Goal", onePursueGroup.getID());
											graphTmp.put("Pursue: Group -> Group Goal", onePursueGroup);
											// Group Goal -> ATEntity.Objective
											mapTmp.put("Group Goal", oneGroupGoal.getID());
											graphTmp.put("Group Goal", oneGroupGoal);
											// Pursue: Group -> Key Goal -> ATRelation.Pursue
											mapTmp.put("Pursue: Group -> Key Goal", onePursueKey.getID());
											graphTmp.put("Pursue: Group -> Key Goal", onePursueKey);
											// Key Goal -> ATEntity.Objective
											mapTmp.put("Key Goal", oneKeyGoal.getID());
											graphTmp.put("Key Goal", oneKeyGoal);
											// Component Goal -> ATEntity.Objective
											mapTmp.put("Component Goal", oneComponentGoal.getID());
											graphTmp.put("Component Goal", oneComponentGoal);
											// Pursue: Component -> Component Goal -> ATRelation.Pursue
											mapTmp.put("Pursue: Component -> Component Goal", onePursueComponent.getID());
											graphTmp.put("Pursue: Component -> Component Goal", onePursueComponent);
											// Component -> ATEntity.Artifact
											mapTmp.put("Component", oneComponent.getID());
											graphTmp.put("Component", oneComponent);
											// Essential: Key Goal -> Group Goal -> ATRelation.Essential
											mapTmp.put("Essential: Key Goal -> Group Goal", oneEssentialGroup.getID());
											graphTmp.put("Essential: Key Goal -> Group Goal", oneEssentialGroup);
											// Essential: Key Goal -> Component Goal -> ATRelation.Essential
											mapTmp.put("Essential: Key Goal -> Component Goal", oneEssentialComponent.getID());
											graphTmp.put("Essential: Key Goal -> Component Goal", oneEssentialComponent);
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
