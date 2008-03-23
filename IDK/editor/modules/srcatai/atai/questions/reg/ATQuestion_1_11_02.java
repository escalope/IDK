package atai.questions.reg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import atai.questions.*;

public class ATQuestion_1_11_02 extends ATQuestion {
	
	/**
	 * Area of the question.
	 */
	static private String area = "1";
	/**
	 * Aspect of the question.
	 */
	static private String aspect = "11";
	/**
	 * Id of the question.
	 */
	static private String id = "02";
	/**
	 * General textual description of the question.
	 */
	static private String description = "How is it possible to know that the goal failed? " +
		"What are the evidences/data that reflect that failure?";
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
	static ATQuestion_1_11_02 theInstance = null;
	
	
	static {
		// The question.
		Map<String, ATEntity> entityTypes = new HashMap<String, ATEntity>(); 
		entityTypes.put("Goal to Evaluate", ATEntity.Objective);
		entityTypes.put("Evidence of Failure", ATEntity.Artifact);
		Map<String, ATRelation> relationTypes = new HashMap<String, ATRelation>();
		relationTypes.put("Impede: Evidence of Failure -> Goal to Evaluate", ATRelation.Impede);
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
		players.put("Impede: Evidence of Failure -> Goal to Evaluate" + "::" + Graph.ROLE_ORIGIN, "Evidence of Failure");
		players.put("Impede: Evidence of Failure -> Goal to Evaluate" + "::" + Graph.ROLE_TARGET, "Goal to Evaluate");
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
		textualQuestion.add("Evidence of Failure");
		textualQuestion.add("reflects the failure in the achievement of the");
		textualQuestion.add("Goal to Evaluate");
		textualQuestion.add(".");
	}


	
	protected ATQuestion_1_11_02() {
		super();
		this.setArea(ATQuestion_1_11_02.area);
		this.setAspect(ATQuestion_1_11_02.aspect);
		this.setId(ATQuestion_1_11_02.id);
		this.setDescription(ATQuestion_1_11_02.description);
		this.setQuestion(ATQuestion_1_11_02.question);
		this.setTextualQuestion(ATQuestion_1_11_02.textualQuestion);
	}
	
	
	
	static public ATQuestion_1_11_02 getInstance() {
		if (ATQuestion_1_11_02.theInstance == null)
			ATQuestion_1_11_02.theInstance = new ATQuestion_1_11_02();
		return ATQuestion_1_11_02.theInstance;
	}

}
