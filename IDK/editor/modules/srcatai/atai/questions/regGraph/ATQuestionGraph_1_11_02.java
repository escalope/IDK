package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_1_11_02 extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_1_11_02(Browser questionBrowser) {
		super(ATQuestion_1_11_02.getInstance(), questionBrowser);
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
		// questionVariables.put("Goal to Evaluate", ATEntity.Objective);
		// questionVariables.put("Evidence of Failure", ATEntity.Artifact);
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get the answers.
		List<String> potentialObjectives = basicView.getEntitiesForATRole(ATEntity.Objective);
		List<String> potentialArtifacts = basicView.getEntitiesForATRole(ATEntity.Artifact);
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialObjectives.size(); i_1++) {
			for (int i_2 = 0; i_2 < potentialArtifacts.size(); i_2++) {
				GraphEntity oneGoalToEvaluate = null;
				GraphEntity oneFailureEvidence = null;
				for (int j = 0; j < entities.length; j++) {
					if (entities[j].getID().equalsIgnoreCase( potentialObjectives.get(i_1) ) )
						oneGoalToEvaluate = entities[j];
					if (entities[j].getID().equalsIgnoreCase( potentialArtifacts.get(i_2) ) )
						oneFailureEvidence = entities[j];
				}
				if (oneGoalToEvaluate != null && oneFailureEvidence != null) {
					GraphRelationship oneImpede = this.isConnected(oneGoalToEvaluate, oneFailureEvidence, ATRelation.Impede);
					if (oneImpede != null) {
						// Store the names and elements in the answer.
						Map<String, String> mapTmp = new HashMap<String, String>();
						Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
						// Goal to Evaluate -> ATEntity.Objective
						mapTmp.put("Goal to Evaluate", oneGoalToEvaluate.getID());
						graphTmp.put("Goal to Evaluate", oneGoalToEvaluate);
						// Impede: Evidence of Failure -> Goal to Evaluate -> ATRelation.Impede
						mapTmp.put("Impede: Evidence of Failure -> Goal to Evaluate", oneImpede.getID());
						graphTmp.put("Impede: Evidence of Failure -> Goal to Evaluate", oneImpede);
						// Evidence of Failure -> ATEntity.Artifact
						mapTmp.put("Evidence of Failure", oneFailureEvidence.getID());
						graphTmp.put("Evidence of Failure", oneFailureEvidence);
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
		this.setPotentialAnswers(potentialAnswersTmp);
		this.setPotentialAnswersElements(potentialAnswersElements);
		
		this.completePotentialAnswers(potentialAnswersTmp.size());
	}

}
