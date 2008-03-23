package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_3_01_02 extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_3_01_02(Browser questionBrowser) {
		super(ATQuestion_3_01_02.getInstance(), questionBrowser);
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
		// questionVariables.put("Distributed Activity", ATEntity.Activity);
		// questionVariables.put("Partial Activity", ATEntity.Activity);
		// questionVariables.put("Component", ATEntity.Artifact);
		// questionVariables.put("Distributed Knowledge", ATEntity.Artifact);
		// questionVariables.put("Partial Knowledge", ATEntity.Artifact);
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get the potential entities.
		List<String> potentialActivities = basicView.getEntitiesForATRole(ATEntity.Activity);
		List<String> potentialArtifacts = basicView.getEntitiesForATRole(ATEntity.Artifact);
		// Get the answers.
		GraphEntity oneDistributedActivity = null;
		GraphEntity onePartialActivity = null;
		GraphEntity oneComponent = null;
		GraphEntity oneDistributedKnowledge = null;
		GraphEntity onePartialKnowledge = null;
		GraphRelationship oneDecomposeActivity = null;
		GraphRelationship oneDecomposeComponent = null;
		GraphRelationship oneDecomposeDistributedKnowledge = null;
		GraphRelationship oneIncludeDistributed = null;
		GraphRelationship oneIncludePartial = null;
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialActivities.size(); i_1++) { // Distributed Activity
			oneDistributedActivity = null;
			for (int j = 0; oneDistributedActivity == null && j < entities.length; j++)
				if (entities[j].getID().equalsIgnoreCase( potentialActivities.get(i_1) ) )
					oneDistributedActivity = entities[j];
			for (int i_2 = 0; i_2 < potentialActivities.size(); i_2++) { // Partial Activity
				onePartialActivity = null;
				for (int j = 0; onePartialActivity == null && j < entities.length; j++)
					if ((i_1 != i_2) && entities[j].getID().equalsIgnoreCase( potentialActivities.get(i_2) ) )
						onePartialActivity = entities[j];
				if (onePartialActivity != null)
					oneDecomposeActivity = this.isConnected(oneDistributedActivity, onePartialActivity,
							ATRelation.Decompose);
				else
					oneDecomposeActivity = null; 
				if (oneDecomposeActivity != null) {
					for (int i_3 = 0; i_3 < potentialArtifacts.size(); i_3++) { // Partial Knowledge
						onePartialKnowledge = null;
						for (int j = 0; onePartialKnowledge == null && j < entities.length; j++)
							if (entities[j].getID().equalsIgnoreCase( potentialArtifacts.get(i_3) ) )
								onePartialKnowledge = entities[j];
						oneIncludePartial = this.isConnected(onePartialActivity, onePartialKnowledge,
								ATRelation.Include);
						if (oneIncludePartial != null) {
							for (int i_4 = 0; i_4 < potentialArtifacts.size(); i_4++) { // Distributed Knowledge
								oneDistributedKnowledge = null;
								for (int j = 0; oneDistributedKnowledge == null && j < entities.length; j++)
									if (entities[j].getID().equalsIgnoreCase( potentialArtifacts.get(i_4) ) )
										oneDistributedKnowledge = entities[j];
								oneIncludeDistributed = this.isConnected(oneDistributedActivity, oneDistributedKnowledge,
										ATRelation.Include);
								if (oneIncludeDistributed != null)
									oneDecomposeDistributedKnowledge = this.isConnected(
											oneDistributedKnowledge, onePartialKnowledge, ATRelation.Decompose);
								if ((oneIncludeDistributed != null) && (oneDecomposeDistributedKnowledge != null)) {
									for (int i_5 = 0; i_5 < potentialArtifacts.size(); i_5++) { // Component
										oneComponent = null;
										for (int j = 0; oneComponent == null && j < entities.length; j++)
											if (entities[j].getID().equalsIgnoreCase( potentialArtifacts.get(i_5) ) )
												oneComponent = entities[j];
										oneDecomposeComponent = this.isConnected(oneComponent, oneDistributedKnowledge,
												ATRelation.Decompose);
										if (oneDecomposeActivity != null &&
												oneDecomposeComponent != null && oneDecomposeDistributedKnowledge != null &&
												oneIncludeDistributed != null && oneIncludePartial != null) {
											// Store the names and elements in the answer.
											Map<String, String> mapTmp = new HashMap<String, String>();
											Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
											// Distributed Activity -> ATEntity.Activity
											mapTmp.put("Distributed Activity", oneDistributedActivity.getID());
											graphTmp.put("Distributed Activity", oneDistributedActivity);
											// Decompose: Distributed Activity -> Partial Activity -> ATRelation.Decompose
											mapTmp.put("Decompose: Distributed Activity -> Partial Activity", oneDecomposeActivity.getID());
											graphTmp.put("Decompose: Distributed Activity -> Partial Activity", oneDecomposeActivity);
											// Partial Activity -> ATEntity.Activity
											mapTmp.put("Partial Activity", onePartialActivity.getID());
											graphTmp.put("Partial Activity", onePartialActivity);
											// Component -> ATEntity.Artifact
											mapTmp.put("Component", oneComponent.getID());
											graphTmp.put("Component", oneComponent);
											// Include: Distributed Activity -> Distributed Knowledge -> ATRelation.Include
											mapTmp.put("Include: Distributed Activity -> Distributed Knowledge", oneIncludeDistributed.getID());
											graphTmp.put("Include: Distributed Activity -> Distributed Knowledge", oneIncludeDistributed);
											// Decompose: Component -> Distributed Knowledge -> ATRelation.Decompose
											mapTmp.put("Decompose: Component -> Distributed Knowledge", oneDecomposeComponent.getID());
											graphTmp.put("Decompose: Component -> Distributed Knowledge", oneDecomposeComponent);
											// Distributed Knowledge -> ATEntity.Artifact
											mapTmp.put("Distributed Knowledge", oneDistributedKnowledge.getID());
											graphTmp.put("Distributed Knowledge", oneDistributedKnowledge);
											// Include: Partial Activity -> Partial Knowledge -> ATRelation.Include
											mapTmp.put("Include: Partial Activity -> Partial Knowledge", oneIncludePartial.getID());
											graphTmp.put("Include: Partial Activity -> Partial Knowledge", oneIncludePartial);
											// Decompose: Distributed Knowledge -> Partial Knowledge -> ATRelation.Decompose
											mapTmp.put("Decompose: Distributed Knowledge -> Partial Knowledge", oneDecomposeDistributedKnowledge.getID());
											graphTmp.put("Decompose: Distributed Knowledge -> Partial Knowledge", oneDecomposeDistributedKnowledge);
											// Partial Knowledge -> ATEntity.Artifact
											mapTmp.put("Partial Knowledge", onePartialKnowledge.getID());
											graphTmp.put("Partial Knowledge", onePartialKnowledge);
											// Add to potential answers.
											potentialAnswersTmp.add(mapTmp);
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
