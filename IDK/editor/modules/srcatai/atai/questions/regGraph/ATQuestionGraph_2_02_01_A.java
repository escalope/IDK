package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_2_02_01_A extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_2_02_01_A(Browser questionBrowser) {
		super(ATQuestion_2_02_01_A.getInstance(), questionBrowser);
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
		// questionVariables.put("More Needed Elements", ATEntity.Object);
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get the potential entities.
		List<String> potentialActivities = basicView.getEntitiesForATRole(ATEntity.Activity);
		List<String> potentialTools = basicView.getEntitiesForATRole(ATEntity.Tool);
		// Get the answers.		
		GraphEntity oneThisActivity = null;
		GraphEntity oneNeededElements = null;
		GraphRelationship oneUse = null;
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialActivities.size(); i_1++) {
			oneThisActivity = null;
			for (int j = 0; oneThisActivity == null && j < entities.length; j++)
				if (entities[j].getID().equalsIgnoreCase( potentialActivities.get(i_1) ) )
					oneThisActivity = entities[j];
			for (int i_2 = 0; i_2 < potentialTools.size(); i_2++) {
				oneNeededElements = null;
				for (int j = 0; oneNeededElements == null && j < entities.length; j++)
					if (entities[j].getID().equalsIgnoreCase( potentialTools.get(i_2) ) )
						oneNeededElements = entities[j];
				oneUse = this.isConnected(oneThisActivity, oneNeededElements,
						ATRelation.Use);
				if (oneUse != null) {
					// Store the names and elements in the answer.
					Map<String, String> mapTmp = new HashMap<String, String>();
					Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
					// This Activity -> ATEntity.Activity
					mapTmp.put("This Activity", oneThisActivity.getID());
					graphTmp.put("This Activity", oneThisActivity);
					// Use: This Activity -> Needed Elements -> ATRelation.Use
					mapTmp.put("Use: This Activity -> Needed Elements", oneUse.getID());
					graphTmp.put("Use: This Activity -> Needed Elements", oneUse);
					// Needed Elements -> ATEntity.Tool
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
