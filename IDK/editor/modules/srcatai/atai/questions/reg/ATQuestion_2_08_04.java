package atai.questions.reg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import atai.questions.*;

public class ATQuestion_2_08_04 extends ATQuestion {
	
	/**
	 * Area of the question.
	 */
	static private String area = "2";
	/**
	 * Aspect of the question.
	 */
	static private String aspect = "08";
	/**
	 * Id of the question.
	 */
	static private String id = "04";
	/**
	 * General textual description of the question.
	 */
	static private String description = "Do decisions in the group depend on a hierarchy?";
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
	static ATQuestion_2_08_04 theInstance = null;
	
	
	static {
		// The question.
		Map<String, ATEntity> entityTypes = new HashMap<String, ATEntity>(); 
		entityTypes.put("Superior", ATEntity.Subject); // Original is Superior Agent.
		entityTypes.put("Subordinated", ATEntity.Subject); // Original is Subordinated Agent.
		entityTypes.put("Group", ATEntity.Community);
		entityTypes.put("Give Command to Subordinates", ATEntity.Activity);
		entityTypes.put("Decision", ATEntity.Objective); // Original is Objective.
		Map<String, ATRelation> relationTypes = new HashMap<String, ATRelation>();
		relationTypes.put("Decompose: Group -> Superior", ATRelation.Decompose);
		relationTypes.put("Decompose: Group -> Subordinated", ATRelation.Decompose);
		relationTypes.put("AccomplishedBy: Give Command to Subordinates -> Superior", ATRelation.AccomplishedBy);
		relationTypes.put("Produce: Give Command to Subordinates -> Decision", ATRelation.Produce);
		relationTypes.put("Pursue: Subordinated -> Decision", ATRelation.Pursue);		
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
		players.put("Decompose: Group -> Superior" + "::" + Graph.ROLE_ORIGIN, "Group");
		players.put("Decompose: Group -> Superior" + "::" + Graph.ROLE_TARGET, "Superior");
		players.put("Decompose: Group -> Subordinated" + "::" + Graph.ROLE_ORIGIN, "Group");
		players.put("Decompose: Group -> Subordinated" + "::" + Graph.ROLE_TARGET, "Subordinated");		
		players.put("AccomplishedBy: Give Command to Subordinates -> Superior" + "::" + Graph.ROLE_ORIGIN, "Give Command to Subordinates");
		players.put("AccomplishedBy: Give Command to Subordinates -> Superior" + "::" + Graph.ROLE_TARGET, "Superior");
		players.put("Produce: Give Command to Subordinates -> Decision" + "::" + Graph.ROLE_ORIGIN, "Give Command to Subordinates");
		players.put("Produce: Give Command to Subordinates -> Decision" + "::" + Graph.ROLE_TARGET, "Decision");		
		players.put("Pursue: Subordinated -> Decision" + "::" + Graph.ROLE_ORIGIN, "Subordinated");
		players.put("Pursue: Subordinated -> Decision" + "::" + Graph.ROLE_TARGET, "Decision");
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
		textualQuestion.add("The decission of accomplishing");
		textualQuestion.add("Decision");
		textualQuestion.add("in the");
		textualQuestion.add("Group");
		textualQuestion.add("depends on a hierarchy. In that hierarchy,");
		textualQuestion.add("Superior");
		textualQuestion.add("commands to");
		textualQuestion.add("Subordinated");
		textualQuestion.add(".");
	}

		
	
	protected ATQuestion_2_08_04() {
		super();
		this.setArea(ATQuestion_2_08_04.area);
		this.setAspect(ATQuestion_2_08_04.aspect);
		this.setId(ATQuestion_2_08_04.id);
		this.setDescription(ATQuestion_2_08_04.description);
		this.setQuestion(ATQuestion_2_08_04.question);
		this.setTextualQuestion(ATQuestion_2_08_04.textualQuestion);
	}
	
	
	
	static public ATQuestion_2_08_04 getInstance() {
		if (ATQuestion_2_08_04.theInstance == null)
			ATQuestion_2_08_04.theInstance = new ATQuestion_2_08_04();
		return ATQuestion_2_08_04.theInstance;
	}

}
