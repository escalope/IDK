package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_1_16_02 extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_1_16_02(Browser questionBrowser) {
		super(ATQuestion_1_16_02.getInstance(), questionBrowser);
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
		// questionVariables.put("Goal 1", ATEntity.Objective);
		// questionVariables.put("Goal 2", ATEntity.Objective);
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get the potential entities.
		List<String> potentialObjectives = basicView.getEntitiesForATRole(ATEntity.Objective);
		// Get the answers.
		GraphEntity oneGoal1 = null;
		GraphEntity oneGoal2 = null;
		GraphRelationship oneSurmount = null;
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialObjectives.size(); i_1++) {
			oneGoal1 = null;
			for (int j = 0; oneGoal1 == null && j < entities.length; j++)
				if (entities[j].getID().equalsIgnoreCase( potentialObjectives.get(i_1) ) )
					oneGoal1 = entities[j];
			for (int i_2 = 0; i_2 < potentialObjectives.size(); i_2++) {
				oneGoal2 = null;
				for (int j = 0; oneGoal2 == null && j < entities.length; j++)
					if (entities[j].getID().equalsIgnoreCase( potentialObjectives.get(i_2) ) )
						oneGoal2 = entities[j];
				oneSurmount = this.isConnected(oneGoal1, oneGoal2, ATRelation.Surmount);
				if (oneSurmount != null) {
					// Store the names and elements in the answer.
					Map<String, String> mapTmp = new HashMap<String, String>();
					Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
					// Goal 1 -> ATEntity.Objective
					mapTmp.put("Goal 1", oneGoal1.getID());
					graphTmp.put("Goal 1", oneGoal1);
					// Surmount: Goal 2 -> Goal 1 -> ATRelation.Surmount
					mapTmp.put("Surmount: Goal 2 -> Goal 1", oneSurmount.getID());
					graphTmp.put("Surmount: Goal 2 -> Goal 1", oneSurmount);
					// Goal 2 -> ATEntity.Objective
					mapTmp.put("Goal 2", oneGoal2.getID());
					graphTmp.put("Goal 2", oneGoal2);
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
