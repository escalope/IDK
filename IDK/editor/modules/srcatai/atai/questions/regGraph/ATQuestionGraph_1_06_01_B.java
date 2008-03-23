package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_1_06_01_B extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_1_06_01_B(Browser questionBrowser) {
		super(ATQuestion_1_06_01_B.getInstance(), questionBrowser);
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
		// questionVariables.put("Key Goal", ATEntity.Objective);
		// questionVariables.put("Component Goal", ATEntity.Objective);
		// questionVariables.put("Component", ATEntity.Artifact);
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get the potential entities.
		List<String> potentialActors = basicView.getEntitiesForATRole(ATEntity.Subject);
		List<String> potentialObjectives = basicView.getEntitiesForATRole(ATEntity.Objective);
		List<String> potentialArtifacts = basicView.getEntitiesForATRole(ATEntity.Artifact);
		// Get the answers.
		GraphEntity oneActor = null;
		GraphEntity oneKeyGoal = null;
		GraphEntity oneComponentGoal = null;
		GraphEntity oneComponent = null;
		GraphRelationship onePursue1 = null;
		GraphRelationship onePursue2 = null;
		GraphRelationship oneEssential = null;
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialActors.size(); i_1++) {
			oneActor = null;
			for (int j = 0; oneActor == null && j < entities.length; j++)
				if (entities[j].getID().equalsIgnoreCase( potentialActors.get(i_1) ) )
					oneActor = entities[j];
			for (int i_2 = 0; i_2 < potentialObjectives.size(); i_2++) {
				oneKeyGoal = null;
				for (int j = 0; oneKeyGoal == null && j < entities.length; j++)
					if (entities[j].getID().equalsIgnoreCase( potentialObjectives.get(i_2) ) )
						oneKeyGoal = entities[j];
				onePursue1 = this.isConnected(oneActor, oneKeyGoal, ATRelation.Pursue);
				if (onePursue1 != null) {
					for (int i_3 = 0; i_3 < potentialObjectives.size(); i_3++) {
						oneComponentGoal = null;
						for (int j = 0; oneComponentGoal == null && j < entities.length; j++)
							if (entities[j].getID().equalsIgnoreCase( potentialObjectives.get(i_3) ) )
								oneComponentGoal = entities[j];
						oneEssential = this.isConnected(oneKeyGoal, oneComponentGoal, ATRelation.Essential);
						if (oneEssential != null ) {
							for (int i_4 = 0; i_4 < potentialArtifacts.size(); i_4++) {
								oneComponent = null;
								for (int j = 0; oneComponent == null && j < entities.length; j++)
									if ((i_1 != i_4) && entities[j].getID().equalsIgnoreCase( potentialArtifacts.get(i_4) ) )
										oneComponent = entities[j];
								if (oneComponent != null)
									onePursue2 = this.isConnected(oneComponent, oneComponentGoal, ATRelation.Pursue);
								else
									onePursue2 = null;
								if (onePursue1 != null && onePursue2 != null && oneEssential != null) {
									// Store the names and elements in the answer.
									Map<String, String> mapTmp = new HashMap<String, String>();
									Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
									// Actor -> ATEntity.Subject
									mapTmp.put("Actor", oneActor.getID());
									graphTmp.put("Actor", oneActor);
									// Pursue: Actor -> Key Goal -> ATRelation.Pursue
									mapTmp.put("Pursue: Actor -> Key Goal", onePursue1.getID());
									graphTmp.put("Pursue: Actor -> Key Goal", onePursue1);
									// Key Goal -> ATEntity.Objective
									mapTmp.put("Key Goal", oneKeyGoal.getID());
									graphTmp.put("Key Goal", oneKeyGoal);
									// Essential: Key Goal -> Component Goal -> ATRelation.Essential
									mapTmp.put("Essential: Key Goal -> Component Goal", oneEssential.getID());
									graphTmp.put("Essential: Key Goal -> Component Goal", oneEssential);
									// Component Goal -> ATEntity.Objective
									mapTmp.put("Component Goal", oneComponentGoal.getID());
									graphTmp.put("Component Goal", oneComponentGoal);
									// Pursue: Component -> Component Goal -> ATRelation.Pursue
									mapTmp.put("Pursue: Component -> Component Goal", onePursue2.getID());
									graphTmp.put("Pursue: Component -> Component Goal", onePursue2);
									// Component -> ATEntity.Artifact
									mapTmp.put("Component", oneComponent.getID());
									graphTmp.put("Component", oneComponent);
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
		this.setPotentialAnswers(potentialAnswersTmp);
		this.setPotentialAnswersElements(potentialAnswersElements);
		
		this.completePotentialAnswers(potentialAnswersTmp.size());
	}

}
