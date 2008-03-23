package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_2_01_04 extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_2_01_04(Browser questionBrowser) {
		super(ATQuestion_2_01_04.getInstance(), questionBrowser);
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
		// questionVariables.put("Disadvantage of the Alternative", ATEntity.Objective);
		// questionVariables.put("Current Activity", ATEntity.Activity);
		// questionVariables.put("Alternative Activity", ATEntity.Activity);
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get the potential entities.
		List<String> potentialObjectives = basicView.getEntitiesForATRole(ATEntity.Objective);
		List<String> potentialActivities = basicView.getEntitiesForATRole(ATEntity.Activity);
		// Get the answers.
		GraphEntity oneDisadvantage = null;
		GraphEntity oneCurrentActivity = null;
		GraphEntity oneAlternativeActivity = null;
		GraphRelationship oneContributePositively = null;
		GraphRelationship oneContributeNegatively = null;
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialObjectives.size(); i_1++) {
			oneDisadvantage = null;
			for (int j = 0; oneDisadvantage == null && j < entities.length; j++)
				if (entities[j].getID().equalsIgnoreCase( potentialObjectives.get(i_1) ) )
					oneDisadvantage = entities[j];
			for (int i_2 = 0; i_2 < potentialActivities.size(); i_2++) {
				oneCurrentActivity = null;
				for (int j = 0; oneCurrentActivity == null && j < entities.length; j++)
					if (entities[j].getID().equalsIgnoreCase( potentialActivities.get(i_2) ) )
						oneCurrentActivity = entities[j];
				oneContributePositively = this.isConnected(oneDisadvantage, oneCurrentActivity, ATRelation.ContributePositively);
				if (oneContributePositively != null) {
					for (int i_3 = 0; i_3 < potentialActivities.size(); i_3++) {
						oneAlternativeActivity = null;
						for (int j = 0; oneAlternativeActivity == null && j < entities.length; j++)
							if (entities[j].getID().equalsIgnoreCase( potentialActivities.get(i_3) ) )
								oneAlternativeActivity = entities[j];
						oneContributeNegatively = this.isConnected(oneDisadvantage, oneAlternativeActivity, ATRelation.ContributeNegatively);
						if (oneContributePositively != null && oneContributeNegatively != null) {
							// Store the names and elements in the answer.
							Map<String, String> mapTmp = new HashMap<String, String>();
							Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
							// Disadvantage of the Alternative -> ATEntity.Objective
							mapTmp.put("Disadvantage of the Alternative", oneDisadvantage.getID());
							graphTmp.put("Disadvantage of the Alternative", oneDisadvantage);
							// Contribute+: Current Activity -> Disadvantage of the Alternative -> ATRelation.ContributePositively
							mapTmp.put("Contribute+: Current Activity -> Disadvantage of the Alternative", oneContributePositively.getID());
							graphTmp.put("Contribute+: Current Activity -> Disadvantage of the Alternative", oneContributePositively);
							// Current Activity -> ATEntity.Activity
							mapTmp.put("Current Activity", oneCurrentActivity.getID());
							graphTmp.put("Current Activity", oneCurrentActivity);
							// Contribute-: Alternative Activity -> Disadvantage of the Alternative -> ATRelation.ContributeNegatively
							mapTmp.put("Contribute-: Alternative Activity -> Disadvantage of the Alternative", oneContributeNegatively.getID());
							graphTmp.put("Contribute-: Alternative Activity -> Disadvantage of the Alternative", oneContributeNegatively);
							// Alternative Activity -> ATEntity.Activity
							mapTmp.put("Alternative Activity", oneAlternativeActivity.getID());
							graphTmp.put("Alternative Activity", oneAlternativeActivity);
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
