package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_2_02_01_B extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_2_02_01_B(Browser questionBrowser) {
		super(ATQuestion_2_02_01_B.getInstance(), questionBrowser);
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
		// questionVariables.put("This Activity", ATEntity.Activity);
		// questionVariables.put("Needed Elements", ATEntity.Tool);
		// questionVariables.put("Needed Elements", ATEntity.Object);
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get the potential entities.
		List<String> potentialActivities = basicView.getEntitiesForATRole(ATEntity.Activity);
		List<String> potentialObjects = basicView.getEntitiesForATRole(ATEntity.Object);
		// Get the answers.		
		GraphEntity oneThisActivity = null;
		GraphEntity oneNeededElements = null;
		GraphRelationship oneTransform = null;
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialActivities.size(); i_1++) {
			oneThisActivity = null;
			for (int j = 0; oneThisActivity == null && j < entities.length; j++)
				if (entities[j].getID().equalsIgnoreCase( potentialActivities.get(i_1) ) )
					oneThisActivity = entities[j];
			for (int i_3 = 0; i_3 < potentialObjects.size(); i_3++) {
				oneNeededElements = null;
				for (int j = 0; oneNeededElements == null && j < entities.length; j++)
					if (entities[j].getID().equalsIgnoreCase( potentialObjects.get(i_3) ) )
						oneNeededElements = entities[j];
				oneTransform = this.isConnected(oneThisActivity, oneNeededElements,
						ATRelation.Transform);
				if (oneTransform != null) {
					// Store the names and elements in the answer.
					Map<String, String> mapTmp = new HashMap<String, String>();
					Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
					// This Activity -> ATEntity.Activity
					mapTmp.put("This Activity", oneThisActivity.getID());
					graphTmp.put("This Activity", oneThisActivity);
					// Tranform: This Activity -> Needed Elements -> ATRelation.Transform
					mapTmp.put("Tranform: This Activity -> Needed Elements", oneTransform.getID());
					graphTmp.put("Tranform: This Activity -> Needed Elements", oneTransform);
					// Needed Elements -> ATEntity.Object
					mapTmp.put("Needed Elements", oneNeededElements.getID());
					graphTmp.put("Needed Elements", oneNeededElements);
					// Roles
					this.completeRoles(mapTmp);
					// Add to potential answers.
					potentialAnswersTmp.add(mapTmp);
					// Add to potential answers.
					potentialAnswersElements.add(graphTmp);
				}
			}
		}
		this.setPotentialAnswers(potentialAnswersTmp);
		this.setPotentialAnswersElements(potentialAnswersElements);
		
		this.completePotentialAnswers(potentialAnswersTmp.size());
	}

}
