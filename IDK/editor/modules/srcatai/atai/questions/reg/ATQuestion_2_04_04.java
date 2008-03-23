package atai.questions.reg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import atai.questions.*;

public class ATQuestion_2_04_04 extends ATQuestion {
	
	/**
	 * Area of the question.
	 */
	static private String area = "2";
	/**
	 * Aspect of the question.
	 */
	static private String aspect = "04";
	/**
	 * Id of the question.
	 */
	static private String id = "04";
	/**
	 * General textual description of the question.
	 */
	static private String description = "Has every user access to the device? If not, who has it? who does not have it?";
	/**
	 * The question of this class.
	 */
	static private ATGraph question;
	/**
	 * The question with the variable elements.
	 * The final statement of the question is:
	 * textualQuestion.get(0) + textualQuestion.get(1) + textualQuestion.get(2)...
	 * where pair indexes are fixed and odd ones are the name of variable elements
	 * of the question from <b>questionVariables</b>.
	 */
	static private List<String> textualQuestion;
	
	/**
	 * The singleton instance of this class.
	 */
	static ATQuestion_2_04_04 theInstance = null;
	
	
	static {
		// The question.
		Map<String, ATEntity> entityTypes = new HashMap<String, ATEntity>(); 
		entityTypes = new HashMap<String, ATEntity>();
		entityTypes.put("User", ATEntity.Subject);
		entityTypes.put("Activity with Access", ATEntity.Activity);
		entityTypes.put("Device", ATEntity.Tool);
		Map<String, ATRelation> relationTypes = new HashMap<String, ATRelation>();
		relationTypes.put("AccomplishedBy: Activity with Access -> User", ATRelation.AccomplishedBy);
		relationTypes.put("Use: Activity with Access -> Device", ATRelation.Use);
		Map<String,	List<String>> roles = new HashMap<String, List<String>>();
		Map<String, String> roleTypes = new HashMap<String, String>();
		for (String relationName : relationTypes.keySet()) {
			List<String> relationRoles = new Vector<String>();
			relationRoles.add(relationName + "::" + Graph.ROLE_ORIGIN);
			relationRoles.add(relationName + "::" + Graph.ROLE_TARGET);
			roles.put(relationName, relationRoles);
			roleTypes.put(relationName + "::" + Graph.ROLE_ORIGIN, Graph.ROLE_ORIGIN);
			roleTypes.put(relationName + "::" + Graph.ROLE_TARGET, Graph.ROLE_TARGET);
		}
		Map<String, String> players = new HashMap<String, String>();
		players.put("AccomplishedBy: Activity with Access -> User" + "::" + Graph.ROLE_ORIGIN, "Activity with Access");
		players.put("AccomplishedBy: Activity with Access -> User" + "::" + Graph.ROLE_TARGET, "User");
		players.put("Use: Activity with Access -> Device" + "::" + Graph.ROLE_ORIGIN, "Activity with Access");
		players.put("Use: Activity with Access -> Device" + "::" + Graph.ROLE_TARGET, "Device");
//////////// ¿SERÁ ESTO NECESARIO?		
		List<String> variables = new Vector<String>();
		for (String entityName : entityTypes.keySet())
			variables.add(entityName);
		/*for (String relationName : relationTypes.keySet()) {
		variables.add(relationName);
		for (int i = 0; i < roles.get(relationName).size(); i++)
			variables.add(roles.get(relationName).get(i));
		}*/
		question = new 	ATGraph(ATGraph.LANGUAGE_ATGRAPH,
				entityTypes, relationTypes, roles, roleTypes,
				players, variables);
		// The textual question.		
		textualQuestion = new Vector<String>();
		textualQuestion.add("The");
		textualQuestion.add("User");
		textualQuestion.add("has access to the");
		textualQuestion.add("Device");
		textualQuestion.add("in the execution of the");
		textualQuestion.add("Activity with Access");
		textualQuestion.add(".");
	}
	

	
	protected ATQuestion_2_04_04() {
		super();
		this.setArea(ATQuestion_2_04_04.area);
		this.setAspect(ATQuestion_2_04_04.aspect);
		this.setId(ATQuestion_2_04_04.id);
		this.setDescription(ATQuestion_2_04_04.description);
		this.setQuestion(ATQuestion_2_04_04.question);
		this.setTextualQuestion(ATQuestion_2_04_04.textualQuestion);
	}
	
	
	
	static public ATQuestion_2_04_04 getInstance() {
		if (ATQuestion_2_04_04.theInstance == null)
			ATQuestion_2_04_04.theInstance = new ATQuestion_2_04_04();
		return ATQuestion_2_04_04.theInstance;
	}

}
