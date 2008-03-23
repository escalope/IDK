package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_1_13_06 extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_1_13_06(Browser questionBrowser) {
		super(ATQuestion_1_13_06.getInstance(), questionBrowser);
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
		// questionVariables.put("Actor", ATEntity.Subject);
		// questionVariables.put("Actor's Goal", ATEntity.Objective);
		// questionVariables.put("Artifact Goal", ATEntity.Objective);
		// questionVariables.put("Artifact", ATEntity.Artifact);
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get the potential entities.
		List<String> potentialActors = basicView.getEntitiesForATRole(ATEntity.Subject);
		List<String> potentialObjectives = basicView.getEntitiesForATRole(ATEntity.Objective);
		List<String> potentialArtifacts = basicView.getEntitiesForATRole(ATEntity.Artifact);
		// Get the answers.
		GraphEntity oneActor = null;
		GraphEntity oneActorsGoal = null;
		GraphEntity oneArtifactGoal = null;
		GraphEntity oneArtifact = null;
		GraphRelationship onePursue = null;
		GraphRelationship oneContributePositively = null;
		GraphRelationship oneContributeNegatively = null;
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialActors.size(); i_1++) {
			oneActor = null;
			for (int j = 0; oneActor == null && j < entities.length; j++)
				if (entities[j].getID().equalsIgnoreCase( potentialActors.get(i_1) ) )
					oneActor = entities[j];
			for (int i_2 = 0; i_2 < potentialObjectives.size(); i_2++) {
				oneActorsGoal = null;
				for (int j = 0; oneActorsGoal == null && j < entities.length; j++)
					if (entities[j].getID().equalsIgnoreCase( potentialObjectives.get(i_2) ) )
						oneActorsGoal = entities[j];
				onePursue = this.isConnected(oneActor, oneActorsGoal, ATRelation.Pursue);
				if (onePursue != null) {
					for (int i_3 = 0; i_3 < potentialObjectives.size(); i_3++) {
						oneArtifactGoal = null;
						for (int j = 0; oneArtifactGoal == null && j < entities.length; j++)
							if (entities[j].getID().equalsIgnoreCase( potentialObjectives.get(i_3) ) )
								oneArtifactGoal = entities[j];
						oneContributeNegatively = this.isConnected(oneArtifactGoal, oneActorsGoal,
								ATRelation.ContributeNegatively);
						if (oneContributeNegatively != null) {
							for (int i_4 = 0; i_4 < potentialArtifacts.size(); i_4++) {
								oneArtifact = null;
								for (int j = 0; oneArtifact == null && j < entities.length; j++)
									if (entities[j].getID().equalsIgnoreCase( potentialArtifacts.get(i_4) ) )
										oneArtifact = entities[j];
								oneContributePositively = this.isConnected(oneArtifact, oneArtifactGoal,
										ATRelation.ContributePositively);
								if (oneContributePositively != null) {
									if (onePursue != null && oneContributePositively != null && oneContributeNegatively != null) {
										// Store the names and elements in the answer.
										Map<String, String> mapTmp = new HashMap<String, String>();
										Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
										// Actor -> ATEntity.Subject
										mapTmp.put("Actor", oneActor.getID());
										graphTmp.put("Actor", oneActor);
										// Pursue: Actor -> Actor's Goal -> ATRelation.Pursue
										mapTmp.put("Pursue: Actor -> Actor's Goal", onePursue.getID());
										graphTmp.put("Pursue: Actor -> Actor's Goal", onePursue);
										// Actor's Goal -> ATEntity.Objective
										mapTmp.put("Actor's Goal", oneActorsGoal.getID());
										graphTmp.put("Actor's Goal", oneActorsGoal);
										// Contribute-: Artifact Goal -> Actor's Goal -> ATRelation.ContributeNegatively
										mapTmp.put("Contribute-: Artifact Goal -> Actor's Goal", oneContributeNegatively.getID());
										graphTmp.put("Contribute-: Artifact Goal -> Actor's Goal", oneContributeNegatively);
										// Artifact Goal -> ATEntity.Objective
										mapTmp.put("Artifact Goal", oneArtifactGoal.getID());
										graphTmp.put("Artifact Goal", oneArtifactGoal);
										// Contribute+: Artifact -> Artifact Goal -> ATRelation.ContributePositively
										mapTmp.put("Contribute+: Artifact -> Artifact Goal", oneContributePositively.getID());
										graphTmp.put("Contribute+: Artifact -> Artifact Goal", oneContributePositively);
										// Artifact -> ATEntity.Artifact
										mapTmp.put("Artifact", oneArtifact.getID());
										graphTmp.put("Artifact", oneArtifact);
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
		this.setPotentialAnswers(potentialAnswersTmp);
		this.setPotentialAnswersElements(potentialAnswersElements);
		
		this.completePotentialAnswers(potentialAnswersTmp.size());
	}

}
