package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_3_01_03 extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_3_01_03(Browser questionBrowser) {
		super(ATQuestion_3_01_03.getInstance(), questionBrowser);
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
		// questionVariables.put("Activity of the Component", ATEntity.Activity);
		// questionVariables.put("Observed Information", ATEntity.Object);
		// questionVariables.put("Surveying Activity", ATEntity.Activity);
		// questionVariables.put("Information about the Activity of the Component", ATEntity.Outcome);
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get the potential entities.
		List<String> potentialObjects = basicView.getEntitiesForATRole(ATEntity.Object);
		List<String> potentialActivities = basicView.getEntitiesForATRole(ATEntity.Activity);
		List<String> potentialOutcomes = basicView.getEntitiesForATRole(ATEntity.Outcome);
		// Get the answers.
		GraphEntity oneComponent = null;
		GraphEntity oneComponentActivity = null;
		GraphEntity oneObservedInformation = null;
		GraphEntity oneSurveyingActivity = null;
		GraphEntity oneComponentInformation = null;
		GraphRelationship oneUseComponent = null;
		GraphRelationship oneTransformObserved1 = null;
		GraphRelationship oneTransformObserved2 = null;
		GraphRelationship oneProduce = null;
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialObjects.size(); i_1++) { // Component
			oneComponent = null;
			for (int j = 0; oneComponent == null && j < entities.length; j++)
				if (entities[j].getID().equalsIgnoreCase( potentialObjects.get(i_1) ) )
					oneComponent = entities[j];
			for (int i_2 = 0; i_2 < potentialActivities.size(); i_2++) { // Activity of the Component
				oneComponentActivity = null;
				for (int j = 0; oneComponentActivity == null && j < entities.length; j++)
					if (entities[j].getID().equalsIgnoreCase( potentialActivities.get(i_2) ) )
						oneComponentActivity = entities[j];
				oneUseComponent = this.isConnected(oneComponentActivity, oneComponent,
						ATRelation.Use);
				if (oneUseComponent != null) {
					for (int i_3 = 0; i_3 < potentialObjects.size(); i_3++) { // Observed Information
						oneObservedInformation = null;
						for (int j = 0; oneObservedInformation == null && j < entities.length; j++)
							if ((i_1 != i_3) && entities[j].getID().equalsIgnoreCase( potentialObjects.get(i_3) ) )
								oneObservedInformation = entities[j];
						if (oneObservedInformation != null)
							oneTransformObserved1 = this.isConnected(
									oneComponentActivity, oneObservedInformation, ATRelation.Transform);
						else
							oneTransformObserved1 = null;
						if (oneTransformObserved1 != null) {
							for (int i_4 = 0; i_4 < potentialActivities.size(); i_4++) { // Surveying Activity
								oneSurveyingActivity = null;
								for (int j = 0; oneSurveyingActivity == null && j < entities.length; j++)
									if ((i_2 != i_4) && entities[j].getID().equalsIgnoreCase( potentialActivities.get(i_4) ) )
										oneSurveyingActivity = entities[j];
								if (oneSurveyingActivity != null)
									oneTransformObserved2 = this.isConnected(
											oneSurveyingActivity, oneObservedInformation,
											ATRelation.Transform);
								else
									oneTransformObserved2 = null;
								if (oneTransformObserved2 != null) {
									for (int i_5 = 0; i_5 < potentialOutcomes.size(); i_5++) { // Information about the Activity of the Component
										oneComponentInformation = null;
										for (int j = 0; oneComponentInformation == null && j < entities.length; j++)
											if ((i_1 != i_5) && (i_3 != i_5) && entities[j].getID().equalsIgnoreCase( potentialOutcomes.get(i_5) ) )
												oneComponentInformation = entities[j];
										if (oneComponentInformation != null)
											oneProduce = this.isConnected(oneSurveyingActivity, oneComponentInformation,
													ATRelation.Produce);
										else
											oneProduce = null;
										if (oneUseComponent != null && oneTransformObserved1 != null &&
												oneTransformObserved2 != null && oneProduce != null) {
											// Store the names and elements in the answer.
											Map<String, String> mapTmp = new HashMap<String, String>();
											Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
											// Component -> ATEntity.Object
											mapTmp.put("Component", oneComponent.getID());
											graphTmp.put("Component", oneComponent);
											// Use: Activity of the Component -> Component -> ATRelation.Use
											mapTmp.put("Use: Activity of the Component -> Component", oneUseComponent.getID());
											graphTmp.put("Use: Activity of the Component -> Component", oneUseComponent);
											// Activity of the Component -> ATEntity.Activity
											mapTmp.put("Activity of the Component", oneComponentActivity.getID());
											graphTmp.put("Activity of the Component", oneComponentActivity);
											// Transform: Activity of the Component -> Observed Information -> ATRelation.Transform
											mapTmp.put("Transform: Activity of the Component -> Observed Information", oneTransformObserved1.getID());
											graphTmp.put("Transform: Activity of the Component -> Observed Information", oneTransformObserved1);
											// Observed Information -> ATEntity.Object
											mapTmp.put("Observed Information", oneObservedInformation.getID());
											graphTmp.put("Observed Information", oneObservedInformation);
											// Transform: Surveying Activity -> Observed Information -> ATRelation.Transform
											mapTmp.put("Transform: Surveying Activity -> Observed Information", oneTransformObserved2.getID());
											graphTmp.put("Transform: Surveying Activity -> Observed Information", oneTransformObserved2);
											// Surveying Activity -> ATEntity.Activity
											mapTmp.put("Surveying Activity", oneSurveyingActivity.getID());
											graphTmp.put("Surveying Activity", oneSurveyingActivity);
											// Produce: Surveying Activity -> Information about the Activity of the Component -> ATRelation.Produce
											mapTmp.put("Produce: Surveying Activity -> Information about the Activity of the Component", oneProduce.getID());
											graphTmp.put("Produce: Surveying Activity -> Information about the Activity of the Component", oneProduce);
											// Information about the Activity of the Component -> ATEntity.Outcome
											mapTmp.put("Information about the Activity of the Component", oneComponentInformation.getID());
											graphTmp.put("Information about the Activity of the Component", oneComponentInformation);
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
