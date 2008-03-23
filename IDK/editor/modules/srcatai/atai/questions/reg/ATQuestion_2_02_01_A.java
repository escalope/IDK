package atai.questions.reg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import atai.questions.*;

public class ATQuestion_2_02_01_A extends ATQuestion {
	// HAY MÁS VERSIONES DE ESTA PREGUNTA
	/**
	 * Area of the question.
	 */
	static private String area = "2";
	/**
	 * Aspect of the question.
	 */
	static private String aspect = "02";
	/**
	 * Id of the question.
	 */
	static private String id = "01_A";
	/**
	 * General textual description of the question.
	 */
	static private String description = "What elements are needed to carry out the activity?";
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
	static ATQuestion_2_02_01_A theInstance = null;
	
	
	static {
		// The question.
		Map<String, ATEntity> entityTypes = new HashMap<String, ATEntity>(); 
		entityTypes = new HashMap<String, ATEntity>();
		entityTypes.put("This Activity", ATEntity.Activity);
		entityTypes.put("Needed Elements", ATEntity.Tool);
		Map<String, ATRelation> relationTypes = new HashMap<String, ATRelation>();
		relationTypes.put("Use: This Activity -> Needed Elements", ATRelation.Use);
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
		players.put("Use: This Activity -> Needed Elements" + "::" + Graph.ROLE_ORIGIN, "This Activity");
		players.put("Use: This Activity -> Needed Elements" + "::" + Graph.ROLE_TARGET, "Needed Elements");		
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
		textualQuestion.add("Needed Elements");
		textualQuestion.add("is requiered to perform the");
		textualQuestion.add("This Activity");
		textualQuestion.add(".");
	}


	
	protected ATQuestion_2_02_01_A() {
		super();
		this.setArea(ATQuestion_2_02_01_A.area);
		this.setAspect(ATQuestion_2_02_01_A.aspect);
		this.setId(ATQuestion_2_02_01_A.id);
		this.setDescription(ATQuestion_2_02_01_A.description);
		this.setQuestion(ATQuestion_2_02_01_A.question);
		this.setTextualQuestion(ATQuestion_2_02_01_A.textualQuestion);
	}
	
	
	
	static public ATQuestion_2_02_01_A getInstance() {
		if (ATQuestion_2_02_01_A.theInstance == null)
			ATQuestion_2_02_01_A.theInstance = new ATQuestion_2_02_01_A();
		return ATQuestion_2_02_01_A.theInstance;
	}

}
