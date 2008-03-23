package atai.questions.reg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import atai.questions.*;

public class ATQuestion_1_16_03 extends ATQuestion {
	
	/**
	 * Area of the question.
	 */
	static private String area = "1";
	/**
	 * Aspect of the question.
	 */
	static private String aspect = "16";
	/**
	 * Id of the question.
	 */
	static private String id = "03";
	/**
	 * General textual description of the question.
	 */
	static private String description = "In case of conflict, " +
		"are there evidences/data that allow the actor choosing the goal to satisfy?";
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
	static ATQuestion_1_16_03 theInstance = null;
	
	
	static {
		// The question.
		Map<String, ATEntity> entityTypes = new HashMap<String, ATEntity>(); 
		entityTypes = new HashMap<String, ATEntity>();
		entityTypes.put("Actor", ATEntity.Subject);
		entityTypes.put("Goal 1", ATEntity.Objective);
		entityTypes.put("Goal 2", ATEntity.Objective);
		entityTypes.put("Choose Goal", ATEntity.Activity);
		entityTypes.put("Evidence for Goal 1", ATEntity.Tool);
		Map<String, ATRelation> relationTypes = new HashMap<String, ATRelation>();
		relationTypes.put("Pursue: Actor -> Goal 1", ATRelation.Pursue);
		relationTypes.put("Pursue: Actor -> Goal 2", ATRelation.Pursue);
		relationTypes.put("Transform: Choose Goal -> Goal 1", ATRelation.Transform);
		relationTypes.put("Transform: Choose Goal -> Goal 2", ATRelation.Transform);
		relationTypes.put("Produce: Choose Goal -> Goal 1", ATRelation.Produce);
		relationTypes.put("Use: Choose Goal -> Evidence for Goal 1", ATRelation.Use);
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
		players.put("Pursue: Actor -> Goal 1" + "::" + Graph.ROLE_ORIGIN, "Actor");
		players.put("Pursue: Actor -> Goal 1" + "::" + Graph.ROLE_TARGET, "Goal 1");
		players.put("Pursue: Actor -> Goal 2" + "::" + Graph.ROLE_ORIGIN, "Actor");
		players.put("Pursue: Actor -> Goal 2" + "::" + Graph.ROLE_TARGET, "Goal 2");		
		players.put("Transform: Choose Goal -> Goal 1" + "::" + Graph.ROLE_ORIGIN, "Choose Goal");
		players.put("Transform: Choose Goal -> Goal 1" + "::" + Graph.ROLE_TARGET, "Goal 1");
		players.put("Transform: Choose Goal -> Goal 2" + "::" + Graph.ROLE_ORIGIN, "Choose Goal");
		players.put("Transform: Choose Goal -> Goal 2" + "::" + Graph.ROLE_TARGET, "Goal 2");
		players.put("Produce: Choose Goal -> Goal 1" + "::" + Graph.ROLE_ORIGIN, "Choose Goal");
		players.put("Produce: Choose Goal -> Goal 1" + "::" + Graph.ROLE_TARGET, "Goal 1");
		players.put("Use: Choose Goal -> Evidence for Goal 1" + "::" + Graph.ROLE_ORIGIN, "Choose Goal");
		players.put("Use: Choose Goal -> Evidence for Goal 1" + "::" + Graph.ROLE_TARGET, "Evidence for Goal 1");
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
		textualQuestion.add("The presence of the");
		textualQuestion.add("Evidence for Goal 1");
		textualQuestion.add("takes the");
		textualQuestion.add("Choose Goal");
		textualQuestion.add("activity to consider the");
		textualQuestion.add("Goal 1");
		textualQuestion.add("as more adequate to pursue in the given circumstances than the");
		textualQuestion.add("Goal 2");
		textualQuestion.add("by");
		textualQuestion.add("Actor");
		textualQuestion.add(".");
	}


	
	protected ATQuestion_1_16_03() {
		super();
		this.setArea(ATQuestion_1_16_03.area);
		this.setAspect(ATQuestion_1_16_03.aspect);
		this.setId(ATQuestion_1_16_03.id);
		this.setDescription(ATQuestion_1_16_03.description);
		this.setQuestion(ATQuestion_1_16_03.question);
		this.setTextualQuestion(ATQuestion_1_16_03.textualQuestion);
	}
	
	
	
	static public ATQuestion_1_16_03 getInstance() {
		if (ATQuestion_1_16_03.theInstance == null)
			ATQuestion_1_16_03.theInstance = new ATQuestion_1_16_03();
		return ATQuestion_1_16_03.theInstance;
	}

}
