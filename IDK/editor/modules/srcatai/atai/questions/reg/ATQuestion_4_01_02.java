package atai.questions.reg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import atai.questions.*;

public class ATQuestion_4_01_02 extends ATQuestion {
	
	/**
	 * Area of the question.
	 */
	static private String area = "4";
	/**
	 * Aspect of the question.
	 */
	static private String aspect = "01";
	/**
	 * Id of the question.
	 */
	static private String id = "02";
	/**
	 * General textual description of the question.
	 */
	static private String description = "Do you think that the actual activities will " +
		"be accomplished by the actors in different ways when these gain a greater expertise? How?";
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
	static ATQuestion_4_01_02 theInstance = null;
	
	
	static {
		// The question.
		Map<String, ATEntity> entityTypes = new HashMap<String, ATEntity>(); 
		entityTypes.put("Total Activity", ATEntity.Activity);
		entityTypes.put("Current Activity", ATEntity.Activity);
		entityTypes.put("Foreseen Activity", ATEntity.Activity);
		Map<String, ATRelation> relationTypes = new HashMap<String, ATRelation>();
		relationTypes.put("Decompose: Total Activity -> Current Activity", ATRelation.Decompose);
		relationTypes.put("Decompose: Total Activity -> Foreseen Activity", ATRelation.Decompose);
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
		players.put("Decompose: Total Activity -> Current Activity" + "::" + Graph.ROLE_ORIGIN, "Total Activity");
		players.put("Decompose: Total Activity -> Current Activity" + "::" + Graph.ROLE_TARGET, "Current Activity");		
		players.put("Decompose: Total Activity -> Foreseen Activity" + "::" + Graph.ROLE_ORIGIN, "Total Activity");
		players.put("Decompose: Total Activity -> Foreseen Activity" + "::" + Graph.ROLE_TARGET, "Foreseen Activity");
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
		textualQuestion.add("Total Activity");
		textualQuestion.add("is currently done through the ");
		textualQuestion.add("Current Activity");
		textualQuestion.add("However, this is not the most efficient way possible. " +
				"We expect that in the future we will do it through the");
		textualQuestion.add("Foreseen Activity");
		textualQuestion.add(".");
	}


	
	protected ATQuestion_4_01_02() {
		super();
		this.setArea(ATQuestion_4_01_02.area);
		this.setAspect(ATQuestion_4_01_02.aspect);
		this.setId(ATQuestion_4_01_02.id);
		this.setDescription(ATQuestion_4_01_02.description);
		this.setQuestion(ATQuestion_4_01_02.question);
		this.setTextualQuestion(ATQuestion_4_01_02.textualQuestion);
	}
	
	
	
	static public ATQuestion_4_01_02 getInstance() {
		if (ATQuestion_4_01_02.theInstance == null)
			ATQuestion_4_01_02.theInstance = new ATQuestion_4_01_02();
		return ATQuestion_4_01_02.theInstance;
	}

}
