package atai.questions.reg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import atai.questions.*;

public class ATQuestion_1_02_01 extends ATQuestion {
	
	/**
	 * Area of the question.
	 */
	static private String area = "1";
	/**
	 * Aspect of the question.
	 */
	static private String aspect = "02";
	/**
	 * Id of the question.
	 */
	static private String id = "01";
	/**
	 * General textual description of the question.
	 */
	static private String description = "Why does the actor/organization want to provide the service?"+ 
		" Which are their objectives?";
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
	static ATQuestion_1_02_01 theInstance = null;
	
	
	static {
		// The question.
		Map<String, ATEntity> entityTypes = new HashMap<String, ATEntity>(); 
		entityTypes.put("Actor", ATEntity.Subject);
		entityTypes.put("Actor's Goal", ATEntity.Objective);
		//entityTypes.put("Service", ATEntity.Activity);
		Map<String, ATRelation> relationTypes = new HashMap<String, ATRelation>();
		relationTypes.put("Pursue: Actor -> Actor's Goal", ATRelation.Pursue);
		//relationTypes.put("Contribute+: Service -> Actor's Goal", ATRelation.ContributePositively);
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
		players.put("Pursue: Actor -> Actor's Goal" + "::" + Graph.ROLE_ORIGIN, "Actor");
		players.put("Pursue: Actor -> Actor's Goal" + "::" + Graph.ROLE_TARGET, "Actor's Goal");
		//players.put("Contribute+: Service -> Actor's Goal" + "::" + Graph.ROLE_ORIGIN, "Service");
		//players.put("Contribute+: Service -> Actor's Goal" + "::" + Graph.ROLE_TARGET, "Actor's Goal");
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
		textualQuestion.add("Actor");
		//textualQuestion.add("want to provide the");
		//textualQuestion.add("Service");
		//textualQuestion.add("because it helps to achieve its");
		textualQuestion.add("wants the service being provided because it helps to achieve its");
		textualQuestion.add("Actor's Goal");
		textualQuestion.add(".");
	}


	
	protected ATQuestion_1_02_01() {
		super();
		this.setArea(ATQuestion_1_02_01.area);
		this.setAspect(ATQuestion_1_02_01.aspect);
		this.setId(ATQuestion_1_02_01.id);
		this.setDescription(ATQuestion_1_02_01.description);
		this.setQuestion(ATQuestion_1_02_01.question);
		this.setTextualQuestion(ATQuestion_1_02_01.textualQuestion);
	}
	
	
	
	static public ATQuestion_1_02_01 getInstance() {
		if (ATQuestion_1_02_01.theInstance == null)
			ATQuestion_1_02_01.theInstance = new ATQuestion_1_02_01();
		return ATQuestion_1_02_01.theInstance;
	}

}
