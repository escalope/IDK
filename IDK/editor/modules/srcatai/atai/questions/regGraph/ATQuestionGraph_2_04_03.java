package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_2_04_03 extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_2_04_03(Browser questionBrowser) {
		super(ATQuestion_2_04_03.getInstance(), questionBrowser);
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
		// questionVariables.put("Component", ATEntity.Artifact);
		// questionVariables.put("Evidence of Unavailability", ATEntity.Object);
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get the potential entities.
		List<String> potentialArtifacts = basicView.getEntitiesForATRole(ATEntity.Artifact);
		List<String> potentialObjects = basicView.getEntitiesForATRole(ATEntity.Object);
		// Get the answers.
		GraphEntity oneComponent = null;
		GraphEntity oneEvidence = null;
		GraphRelationship oneImpede = null;
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialArtifacts.size(); i_1++) {
			oneComponent = null;
			for (int j = 0; oneComponent == null && j < entities.length; j++)
				if (entities[j].getID().equalsIgnoreCase( potentialArtifacts.get(i_1) ) )
					oneComponent = entities[j];
			for (int i_2 = 0; i_2 < potentialObjects.size(); i_2++) {
				oneEvidence = null;
				for (int j = 0; oneEvidence == null && j < entities.length; j++)
					if (entities[j].getID().equalsIgnoreCase( potentialObjects.get(i_2) ) )
						oneEvidence = entities[j];
				oneImpede = this.isConnected(oneComponent, oneEvidence, ATRelation.Impede);
				if (oneImpede != null) {
					// Store the names and elements in the answer.
					Map<String, String> mapTmp = new HashMap<String, String>();
					Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
					// Component -> ATEntity.Artifact
					mapTmp.put("Component", oneComponent.getID());
					graphTmp.put("Component", oneComponent);
					// Impede: Evidence of Unavailability -> Component -> ATRelation.Impede
					mapTmp.put("Impede: Evidence of Unavailability -> Component", oneImpede.getID());
					graphTmp.put("Impede: Evidence of Unavailability -> Component", oneImpede);
					// Evidence of Unavailability -> ATEntity.Object
					mapTmp.put("Evidence of Unavailability", oneEvidence.getID());
					graphTmp.put("Evidence of Unavailability", oneEvidence);
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
