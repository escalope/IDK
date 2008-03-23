package atai.questions.regGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

import atai.questions.*;
import atai.questions.reg.*;

public class ATQuestionGraph_2_04_04 extends ATQuestionGraph {

	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph_2_04_04(Browser questionBrowser) {
		super(ATQuestion_2_04_04.getInstance(), questionBrowser);
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
		// questionVariables.put("User", ATEntity.Subject);
		// questionVariables.put("Activity with Access", ATEntity.Activity);
		// questionVariables.put("Device", ATEntity.Tool);
		List<Map<String, String>> potentialAnswersTmp = new Vector<Map<String, String>>();
		List<Map<String, AttributedElement>> potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		// Get the potential entities.
		List<String> potentialUsers = basicView.getEntitiesForATRole(ATEntity.Subject);
		List<String> potentialActivitiesWithAccess = basicView.getEntitiesForATRole(ATEntity.Activity);
		List<String> potentialDevices = basicView.getEntitiesForATRole(ATEntity.Tool);
		// Get the answers.
		GraphEntity oneUser = null;
		GraphEntity oneActivityWithAccess = null;
		GraphEntity oneDevice = null;
		GraphRelationship oneAccomplishedBy = null;
		GraphRelationship oneUse = null;
		GraphEntity[] entities = this.getQuestionBrowser().getAllEntities();
		for (int i_1 = 0; i_1 < potentialUsers.size(); i_1++) {
			oneUser = null;
			for (int j = 0; oneUser == null && j < entities.length; j++)
				if (entities[j].getID().equalsIgnoreCase( potentialUsers.get(i_1) ) )
					oneUser = entities[j];
			for (int i_2 = 0; i_2 < potentialActivitiesWithAccess.size(); i_2++) {
				oneActivityWithAccess = null;
				for (int j = 0; oneActivityWithAccess == null && j < entities.length; j++)
					if (entities[j].getID().equalsIgnoreCase( potentialActivitiesWithAccess.get(i_2) ) )
						oneActivityWithAccess = entities[j];
				oneAccomplishedBy = this.isConnected(oneUser, oneActivityWithAccess, ATRelation.AccomplishedBy);
				if (oneAccomplishedBy != null) {
					for (int i_3 = 0; i_3 < potentialDevices.size(); i_3++) {
						oneDevice = null;
						for (int j = 0; oneDevice == null && j < entities.length; j++)
							if (entities[j].getID().equalsIgnoreCase( potentialDevices.get(i_3) ) )
								oneDevice = entities[j];
						oneUse = this.isConnected(oneActivityWithAccess, oneDevice, ATRelation.Use);
						if (oneAccomplishedBy != null && oneUse != null) {
							// Store the names and elements in the answer.
							Map<String, String> mapTmp = new HashMap<String, String>();
							Map<String, AttributedElement> graphTmp = new HashMap<String, AttributedElement>();
							// User -> ATEntity.Subject
							mapTmp.put("User", oneUser.getID());
							graphTmp.put("User", oneUser);
							// AccomplishedBy: Activity with Access -> User -> ATRelation.AccomplishedBy
							mapTmp.put("AccomplishedBy: Activity with Access -> User", oneAccomplishedBy.getID());
							graphTmp.put("AccomplishedBy: Activity with Access -> User", oneAccomplishedBy);
							// Activity with Access -> ATEntity.Activity
							mapTmp.put("Activity with Access", oneActivityWithAccess.getID());
							graphTmp.put("Activity with Access", oneActivityWithAccess);
							// Use: Activity with Access -> Device -> ATRelation.Use
							mapTmp.put("Use: Activity with Access -> Device", oneUse.getID());
							graphTmp.put("Use: Activity with Access -> Device", oneUse);
							// Device -> ATEntity.Tool
							mapTmp.put("Device", oneDevice.getID());
							graphTmp.put("Device", oneDevice);
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
