package atai.questions.reg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import atai.questions.*;

public class ATQuestion_3_01_02 extends ATQuestion {
	
	/**
	 * Area of the question.
	 */
	static private String area = "3";
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
	static private String description = "How should the distributed knowledge used from " +
		"the component be accessible? What information should be needed to represent it?";
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
	static ATQuestion_3_01_02 theInstance = null;
	
	
	static {
		// The question.
		Map<String, ATEntity> entityTypes = new HashMap<String, ATEntity>(); 
		entityTypes.put("Distributed Activity", ATEntity.Activity);
		entityTypes.put("Partial Activity", ATEntity.Activity);
		entityTypes.put("Component", ATEntity.Artifact);
		entityTypes.put("Distributed Knowledge", ATEntity.Artifact);
		entityTypes.put("Partial Knowledge", ATEntity.Artifact);
		Map<String, ATRelation> relationTypes = new HashMap<String, ATRelation>();
		relationTypes.put("Decompose: Distributed Activity -> Partial Activity", ATRelation.Decompose);
		relationTypes.put("Include: Partial Activity -> Partial Knowledge", ATRelation.Include);
		relationTypes.put("Include: Distributed Activity -> Distributed Knowledge", ATRelation.Include);
		relationTypes.put("Decompose: Distributed Knowledge -> Partial Knowledge", ATRelation.Decompose);
		relationTypes.put("Decompose: Component -> Distributed Knowledge", ATRelation.Decompose);
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
		players.put("Decompose: Distributed Activity -> Partial Activity" + "::" + Graph.ROLE_ORIGIN, "Distributed Activity");
		players.put("Decompose: Distributed Activity -> Partial Activity" + "::" + Graph.ROLE_TARGET, "Partial Activity");
		players.put("Include: Partial Activity -> Partial Knowledge" + "::" + Graph.ROLE_ORIGIN, "Partial Activity");
		players.put("Include: Partial Activity -> Partial Knowledge" + "::" + Graph.ROLE_TARGET, "Partial Knowledge");
		players.put("Include: Distributed Activity -> Distributed Knowledge" + "::" + Graph.ROLE_ORIGIN, "Distributed Activity");
		players.put("Include: Distributed Activity -> Distributed Knowledge" + "::" + Graph.ROLE_TARGET, "Distributed Knowledge");
		players.put("Decompose: Distributed Knowledge -> Partial Knowledge" + "::" + Graph.ROLE_ORIGIN, "Distributed Knowledge");
		players.put("Decompose: Distributed Knowledge -> Partial Knowledge" + "::" + Graph.ROLE_TARGET, "Partial Knowledge");
		players.put("Decompose: Component -> Distributed Knowledge" + "::" + Graph.ROLE_ORIGIN, "Component");
		players.put("Decompose: Component -> Distributed Knowledge" + "::" + Graph.ROLE_TARGET, "Distributed Knowledge");
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
		textualQuestion.add("Partial Activity");
		textualQuestion.add("is one of the sub-tasks required to accomplish the task");
		textualQuestion.add("Distributed Activity");
		textualQuestion.add("This task uses the");
		textualQuestion.add("Distributed Knowledge");
		textualQuestion.add("coming from the");
		textualQuestion.add("Component");
		textualQuestion.add("Part of this knowledge appears in the sub-task as the");
		textualQuestion.add("Partial Knowledge");
		textualQuestion.add(".");
	}


	
	protected ATQuestion_3_01_02() {
		super();
		this.setArea(ATQuestion_3_01_02.area);
		this.setAspect(ATQuestion_3_01_02.aspect);
		this.setId(ATQuestion_3_01_02.id);
		this.setDescription(ATQuestion_3_01_02.description);
		this.setQuestion(ATQuestion_3_01_02.question);
		this.setTextualQuestion(ATQuestion_3_01_02.textualQuestion);
	}
	
	
	
	static public ATQuestion_3_01_02 getInstance() {
		if (ATQuestion_3_01_02.theInstance == null)
			ATQuestion_3_01_02.theInstance = new ATQuestion_3_01_02();
		return ATQuestion_3_01_02.theInstance;
	}

}
