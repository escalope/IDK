package atai.questions.reg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import atai.questions.*;

public class ATQuestion_4_01_04 extends ATQuestion {
	
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
	static private String id = "04";
	/**
	 * General textual description of the question.
	 */
	static private String description = "What activities do you think that the use of "+
		"the component will made obsolete and tend to dissappear from the work practices?";
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
	static ATQuestion_4_01_04 theInstance = null;
	
	
	static {
		// The question.
		Map<String, ATEntity> entityTypes = new HashMap<String, ATEntity>(); 
		entityTypes.put("Component", ATEntity.Object);
		entityTypes.put("Obsolete Activity", ATEntity.Activity);
		entityTypes.put("New Activity", ATEntity.Activity);
		entityTypes.put("Evolve", ATEntity.Activity);
		Map<String, ATRelation> relationTypes = new HashMap<String, ATRelation>();
		relationTypes.put("Transform: Obsolete Activity -> Component", ATRelation.Transform);
		relationTypes.put("Transform: New Activity -> Component", ATRelation.Transform);
		relationTypes.put("Transform: Evolve -> Component", ATRelation.Transform);
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
		players.put("Transform: Obsolete Activity -> Component" + "::" + Graph.ROLE_ORIGIN, "Obsolete Activity");
		players.put("Transform: Obsolete Activity -> Component" + "::" + Graph.ROLE_TARGET, "Component");
		players.put("Transform: New Activity -> Component" + "::" + Graph.ROLE_ORIGIN, "New Activity");
		players.put("Transform: New Activity -> Component" + "::" + Graph.ROLE_TARGET, "Component");
		players.put("Transform: Evolve -> Component" + "::" + Graph.ROLE_ORIGIN, "Evolve");
		players.put("Transform: Evolve -> Component" + "::" + Graph.ROLE_TARGET, "Component");
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
		textualQuestion.add("The current");
		textualQuestion.add("Obsolete Activity");
		textualQuestion.add("made with the");
		textualQuestion.add("Component");
		textualQuestion.add("will be substituted by the");
		textualQuestion.add("New Activity");
		textualQuestion.add("as a consequence of the changes in the element/device "+
				"introduced by the process of");
		textualQuestion.add("Evolve");
		textualQuestion.add(".");
	}


	
	protected ATQuestion_4_01_04() {
		super();
		this.setArea(ATQuestion_4_01_04.area);
		this.setAspect(ATQuestion_4_01_04.aspect);
		this.setId(ATQuestion_4_01_04.id);
		this.setDescription(ATQuestion_4_01_04.description);
		this.setQuestion(ATQuestion_4_01_04.question);
		this.setTextualQuestion(ATQuestion_4_01_04.textualQuestion);
	}
	
	
	
	static public ATQuestion_4_01_04 getInstance() {
		if (ATQuestion_4_01_04.theInstance == null)
			ATQuestion_4_01_04.theInstance = new ATQuestion_4_01_04();
		return ATQuestion_4_01_04.theInstance;
	}

}
