package atai.questions.reg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import atai.questions.*;

public class ATQuestion_3_01_03 extends ATQuestion {
	
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
	static ATQuestion_3_01_03 theInstance = null;
	
	
	static {
		// The question.
		Map<String, ATEntity> entityTypes = new HashMap<String, ATEntity>(); 
		entityTypes.put("Component", ATEntity.Object);
		entityTypes.put("Activity of the Component", ATEntity.Activity);
		entityTypes.put("Observed Information", ATEntity.Object);
		entityTypes.put("Surveying Activity", ATEntity.Activity);
		entityTypes.put("Information about the Activity of the Component", ATEntity.Outcome);
		Map<String, ATRelation> relationTypes = new HashMap<String, ATRelation>();
		relationTypes.put("Use: Activity of the Component -> Component", ATRelation.Use);
		relationTypes.put("Transform: Activity of the Component -> Observed Information", ATRelation.Transform);
		relationTypes.put("Transform: Surveying Activity -> Observed Information", ATRelation.Transform);
		relationTypes.put("Produce: Surveying Activity -> Information about the Activity of the Component", ATRelation.Produce);
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
		players.put("Use: Activity of the Component -> Component" + "::" + Graph.ROLE_ORIGIN, "Activity of the Component");
		players.put("Use: Activity of the Component -> Component" + "::" + Graph.ROLE_TARGET, "Component");
		players.put("Transform: Activity of the Component -> Observed Information" + "::" + Graph.ROLE_ORIGIN, "Activity of the Component");
		players.put("Transform: Activity of the Component -> Observed Information" + "::" + Graph.ROLE_TARGET, "Observed Information");		
		players.put("Transform: Surveying Activity -> Observed Information" + "::" + Graph.ROLE_ORIGIN, "Surveying Activity");
		players.put("Transform: Surveying Activity -> Observed Information" + "::" + Graph.ROLE_TARGET, "Observed Information");
		players.put("Produce: Surveying Activity -> Information about the Activity of the Component" + "::" + Graph.ROLE_ORIGIN, "Surveying Activity");
		players.put("Produce: Surveying Activity -> Information about the Activity of the Component" + "::" + Graph.ROLE_TARGET, "Information about the Activity of the Component");
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
		textualQuestion.add("The activity");
		textualQuestion.add("Surveying Activity");
		textualQuestion.add("makes a surveillance of the");
		textualQuestion.add("Component");
		textualQuestion.add("This surveillance produces as result the");
		textualQuestion.add("Information about the Activity of the Component");
		textualQuestion.add("To obtain this information, the");
		textualQuestion.add("Observed Information");
		textualQuestion.add("produced by the");
		textualQuestion.add("Activity of the Component");
		textualQuestion.add("is recollected.");
	}


	
	protected ATQuestion_3_01_03() {
		super();
		this.setArea(ATQuestion_3_01_03.area);
		this.setAspect(ATQuestion_3_01_03.aspect);
		this.setId(ATQuestion_3_01_03.id);
		this.setDescription(ATQuestion_3_01_03.description);
		this.setQuestion(ATQuestion_3_01_03.question);
		this.setTextualQuestion(ATQuestion_3_01_03.textualQuestion);
	}
	
	
	
	static public ATQuestion_3_01_03 getInstance() {
		if (ATQuestion_3_01_03.theInstance == null)
			ATQuestion_3_01_03.theInstance = new ATQuestion_3_01_03();
		return ATQuestion_3_01_03.theInstance;
	}

}
