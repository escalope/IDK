package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_4_01_02 extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_4_01_02(Browser questionBrowser) {
		super(ATQuestion_4_01_02.getInstance(), questionBrowser);
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
		// questionVariables.put("Total Activity", ATEntity.Activity);
		// questionVariables.put("Current Activity", ATEntity.Activity);
		// questionVariables.put("Foreseen Activity", ATEntity.Activity);
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get the potential entities.
		List<String> potentialActivities = basicView.getEntitiesForATRole(ATEntity.Activity);
		// Get the answers.
		GraphEntity oneTotalActivity = null;
		GraphEntity oneCurrentActivity = null;
		GraphEntity oneForeseenActivity = null;
		GraphRelationship oneDecomposeCurrent = null;
		GraphRelationship oneDecomposeForeseen = null;
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialActivities.size(); i_1++) { // Total Activity
			oneTotalActivity = null;
			for (int j = 0; oneTotalActivity == null && j < entities.length; j++)
				if (entities[j].getID().equalsIgnoreCase( potentialActivities.get(i_1) ) )
					oneTotalActivity = entities[j];
			for (int i_2 = 0; i_2 < potentialActivities.size(); i_2++) { // Current Activity
				oneCurrentActivity = null;
				for (int j = 0; oneCurrentActivity == null && j < entities.length; j++)
					if ((i_1 != i_2) && entities[j].getID().equalsIgnoreCase( potentialActivities.get(i_2) ) )
						oneCurrentActivity = entities[j];
				if (oneCurrentActivity != null)
					oneDecomposeCurrent = this.isConnected(oneTotalActivity, oneCurrentActivity, ATRelation.Decompose);
				else
					oneDecomposeCurrent = null;
				if (oneDecomposeCurrent != null) {
					for (int i_3 = 0; i_3 < potentialActivities.size(); i_3++) { // Foreseen Activity
						oneForeseenActivity = null;
						for (int j = 0; oneForeseenActivity == null && j < entities.length; j++)
							if ((i_1 != i_3) && entities[j].getID().equalsIgnoreCase( potentialActivities.get(i_3) ) )
								oneForeseenActivity = entities[j];
						if (oneForeseenActivity != null)
							oneDecomposeForeseen = this.isConnected(oneTotalActivity, oneForeseenActivity, ATRelation.Decompose);
						else
							oneDecomposeForeseen = null;
						if (oneDecomposeCurrent != null && oneDecomposeForeseen != null) {
							// Store the names and elements in the answer.
							Map<String, String> mapTmp = new HashMap<String, String>();
							Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
							// Total Activity -> ATEntity.Activity
							mapTmp.put("Total Activity", oneTotalActivity.getID());
							graphTmp.put("Total Activity", oneTotalActivity);
							// Decompose: Total Activity -> Current Activity -> ATRelation.Decompose
							mapTmp.put("Decompose: Total Activity -> Current Activity", oneDecomposeCurrent.getID());
							graphTmp.put("Decompose: Total Activity -> Current Activity", oneDecomposeCurrent);
							// Current Activity -> ATEntity.Activity
							mapTmp.put("Current Activity", oneCurrentActivity.getID());
							graphTmp.put("Current Activity", oneCurrentActivity);
							// Decompose: Total Activity -> Foreseen Activity -> ATRelation.Decompose
							mapTmp.put("Decompose: Total Activity -> Foreseen Activity", oneDecomposeForeseen.getID());
							graphTmp.put("Decompose: Total Activity -> Foreseen Activity", oneDecomposeForeseen);
							// Foreseen Activity -> ATEntity.Activity
							mapTmp.put("Foreseen Activity", oneForeseenActivity.getID());
							graphTmp.put("Foreseen Activity", oneForeseenActivity);
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
