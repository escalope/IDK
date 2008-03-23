package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_4_01_04 extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_4_01_04(Browser questionBrowser) {
		super(ATQuestion_4_01_04.getInstance(), questionBrowser);
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
		// questionVariables.put("Component", ATEntity.Object);
		// questionVariables.put("Obsolete Activity", ATEntity.Activity);
		// questionVariables.put("New Activity", ATEntity.Activity);
		// questionVariables.put("Evolve", ATEntity.Activity);
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get the potential entities.
		List<String> potentialObjects = basicView.getEntitiesForATRole(ATEntity.Object);
		List<String> potentialActivities = basicView.getEntitiesForATRole(ATEntity.Activity);
		// Get the answers.
		GraphEntity oneComponent = null;
		GraphEntity oneObsoleteActivity = null;
		GraphEntity oneNewActivity = null;
		GraphEntity oneEvolve = null;
		GraphRelationship oneTransformEvolve = null;
		GraphRelationship oneTransformObsolete = null;
		GraphRelationship oneTransformNew = null;
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialObjects.size(); i_1++) { // Component
			oneComponent = null;
			for (int j = 0; oneComponent == null && j < entities.length; j++)
				if (entities[j].getID().equalsIgnoreCase( potentialObjects.get(i_1) ) )
					oneComponent = entities[j];
			for (int i_2 = 0; i_2 < potentialActivities.size(); i_2++) { // Obsolete Activity
				oneObsoleteActivity = null;
				for (int j = 0; oneObsoleteActivity == null && j < entities.length; j++)
					if (entities[j].getID().equalsIgnoreCase( potentialActivities.get(i_2) ) )
						oneObsoleteActivity = entities[j];
				oneTransformObsolete = this.isConnected(
						oneObsoleteActivity, oneComponent, ATRelation.Transform);
				if (oneTransformObsolete != null) {
					for (int i_3 = 0; i_3 < potentialActivities.size(); i_3++) { // New Activity
						oneNewActivity = null;
						for (int j = 0; oneNewActivity == null && j < entities.length; j++)
							if (entities[j].getID().equalsIgnoreCase( potentialActivities.get(i_3) ) )
								oneNewActivity = entities[j];
						oneTransformNew = this.isConnected(oneNewActivity, oneComponent,
								ATRelation.Transform);
						if (oneTransformNew != null) {
							for (int i_4 = 0; i_4 < potentialActivities.size(); i_4++) { // Evolve
								for (int j = 0; j < entities.length; j++)
									if ((i_2 != i_4) && (i_3 != i_4) && entities[j].getID().equalsIgnoreCase( potentialActivities.get(i_4) ) )
										oneEvolve = entities[j];
								if (oneEvolve != null)
									oneTransformEvolve = this.isConnected(oneEvolve, oneComponent,
											ATRelation.Transform);
								else
									oneTransformEvolve = null;
								if (oneTransformEvolve != null &&
										oneTransformObsolete != null && oneTransformNew != null) {
									// Store the names and elements in the answer.
									Map<String, String> mapTmp = new HashMap<String, String>();
									Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
									// Component -> ATEntity.Object
									mapTmp.put("Component", oneComponent.getID());
									graphTmp.put("Component", oneComponent);
									// Transform: Obsolete Activity -> Component -> ATRelation.Transform
									mapTmp.put("Transform: Obsolete Activity -> Component", oneTransformObsolete.getID());
									graphTmp.put("Transform: Obsolete Activity -> Component", oneTransformObsolete);
									// Obsolete Activity -> ATEntity.Activity
									mapTmp.put("Obsolete Activity", oneObsoleteActivity.getID());
									graphTmp.put("Obsolete Activity", oneObsoleteActivity);
									// Transform: New Activity -> Component -> ATRelation.Transform
									mapTmp.put("Transform: New Activity -> Component", oneTransformNew.getID());
									graphTmp.put("Transform: New Activity -> Component", oneTransformNew);
									// New Activity -> ATEntity.Activity
									mapTmp.put("New Activity", oneNewActivity.getID());
									graphTmp.put("New Activity", oneNewActivity);
									// Transform: Evolve -> Component -> ATRelation.Transform
									mapTmp.put("Transform: Evolve -> Component", oneTransformEvolve.getID());
									graphTmp.put("Transform: Evolve -> Component", oneTransformEvolve);
									// Evolve -> ATEntity.Activity
									mapTmp.put("Evolve", oneEvolve.getID());
									graphTmp.put("Evolve", oneEvolve);
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
