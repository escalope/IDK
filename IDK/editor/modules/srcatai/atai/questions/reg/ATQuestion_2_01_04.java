package atai.questions.reg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import atai.questions.*;

public class ATQuestion_2_01_04 extends ATQuestion {
	// EXISTEN MÁS VERSIONES DE ESTA PREGUNTA.
	/**
	 * Area of the question.
	 */
	static private String area = "2";
	/**
	 * Aspect of the question.
	 */
	static private String aspect = "01";
	/**
	 * Id of the question.
	 */
	static private String id = "04";
	/**
	 * General textual description of the question.
	 */
	static private String description = "What are the advantages and disadvantages of " +
		"the alternative activities in comparisson with those of the component?";
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
	static ATQuestion_2_01_04 theInstance = null;
	
	
	static {
		// The question.
		Map<String, ATEntity> entityTypes = new HashMap<String, ATEntity>(); 
		entityTypes = new HashMap<String, ATEntity>();
		entityTypes.put("Disadvantage of the Alternative", ATEntity.Objective);
		entityTypes.put("Current Activity", ATEntity.Activity);
		entityTypes.put("Alternative Activity", ATEntity.Activity);
		Map<String, ATRelation> relationTypes = new HashMap<String, ATRelation>();
		relationTypes.put("Contribute+: Current Activity -> Disadvantage of the Alternative", ATRelation.ContributePositively);
		relationTypes.put("Contribute-: Alternative Activity -> Disadvantage of the Alternative", ATRelation.ContributeNegatively);
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
		players.put("Contribute+: Current Activity -> Disadvantage of the Alternative" + "::" + Graph.ROLE_ORIGIN, "Current Activity");
		players.put("Contribute+: Current Activity -> Disadvantage of the Alternative" + "::" + Graph.ROLE_TARGET, "Disadvantage of the Alternative");		
		players.put("Contribute-: Alternative Activity -> Disadvantage of the Alternative" + "::" + Graph.ROLE_ORIGIN, "Alternative Activity");
		players.put("Contribute-: Alternative Activity -> Disadvantage of the Alternative" + "::" + Graph.ROLE_TARGET, "Disadvantage of the Alternative");
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
		textualQuestion.add("The execution of the");
		textualQuestion.add("Current Activity");
		textualQuestion.add("is better than the execution of the");
		textualQuestion.add("Alternative Activity");
		textualQuestion.add("in order to achieve the");
		textualQuestion.add("Disadvantage of the Alternative");
		textualQuestion.add(".");
	}


	
	protected ATQuestion_2_01_04() {
		super();
		this.setArea(ATQuestion_2_01_04.area);
		this.setAspect(ATQuestion_2_01_04.aspect);
		this.setId(ATQuestion_2_01_04.id);
		this.setDescription(ATQuestion_2_01_04.description);
		this.setQuestion(ATQuestion_2_01_04.question);
		this.setTextualQuestion(ATQuestion_2_01_04.textualQuestion);
	}
	
	
	
	static public ATQuestion_2_01_04 getInstance() {
		if (ATQuestion_2_01_04.theInstance == null)
			ATQuestion_2_01_04.theInstance = new ATQuestion_2_01_04();
		return ATQuestion_2_01_04.theInstance;
	}

}
