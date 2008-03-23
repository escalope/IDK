package atai.questions.reg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import atai.questions.*;

public class ATQuestion_2_04_03 extends ATQuestion {

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
	static private String id = "03";
	/**
	 * General textual description of the question.
	 */
	static private String description = "What are the evidences that point out that the element is not available?";
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
	static ATQuestion_2_04_03 theInstance = null;
	
	
	static {
		// The question.
		Map<String, ATEntity> entityTypes = new HashMap<String, ATEntity>(); 
		entityTypes.put("Component", ATEntity.Artifact);
		entityTypes.put("Evidence of Unavailability", ATEntity.Object);
		Map<String, ATRelation> relationTypes = new HashMap<String, ATRelation>();
		relationTypes.put("Impede: Evidence of Unavailability -> Component", ATRelation.Impede);
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
		players.put("Impede: Evidence of Unavailability -> Component" + "::" + Graph.ROLE_ORIGIN, "Evidence of Unavailability");
		players.put("Impede: Evidence of Unavailability -> Component" + "::" + Graph.ROLE_TARGET, "Component");
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
		textualQuestion.add("Evidence of Unavailability");
		textualQuestion.add("points out that the");
		textualQuestion.add("Component");
		textualQuestion.add("is not available to provide its services.");
	}


	
	protected ATQuestion_2_04_03() {
		super();
		this.setArea(ATQuestion_2_04_03.area);
		this.setAspect(ATQuestion_2_04_03.aspect);
		this.setId(ATQuestion_2_04_03.id);
		this.setDescription(ATQuestion_2_04_03.description);
		this.setQuestion(ATQuestion_2_04_03.question);
		this.setTextualQuestion(ATQuestion_2_04_03.textualQuestion);
	}
	
	
	
	static public ATQuestion_2_04_03 getInstance() {
		if (ATQuestion_2_04_03.theInstance == null)
			ATQuestion_2_04_03.theInstance = new ATQuestion_2_04_03();
		return ATQuestion_2_04_03.theInstance;
	}

}
