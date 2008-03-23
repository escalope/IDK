package atai.questions;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import atai.questions.reg.ATQuestion_1_01_07;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

public class ATView {

	/**
	 * A browser to acces to the specifications of the system.
	 */
	Browser questionBrowser;
	/**
	 * The instance of this class.
	 */
	static private ATView theInstance;
	
	
	/**
	 */
	private ATView() {
		super();
		this.questionBrowser = null;
	}

	
	
	/**
	 * @return the questionBrowser
	 */
	protected Browser getQuestionBrowser() {
		return questionBrowser;
	}

	
	
	/**
	 * @param questionBrowser the questionBrowser to set
	 */
	protected void setQuestionBrowser(Browser questionBrowser) {
		this.questionBrowser = questionBrowser;
	}
	
	
		
	/**
	 * @return List<String> the List<String> of types that are compatible with the ATEntity.
	 */
	public List<String> getEntitiesForATRole(ATEntity oneRole) {
		// Get translating INGENIAS types.
		List<String> typesToSearch = ATView.getEntityTypesForATRole(oneRole);
		// Get the actual entities from the specification.
		List<String> result = new Vector<String>();
		GraphEntity[] availableEntities = this.getQuestionBrowser().getAllEntities();
		for (int i = 0; i < availableEntities.length; i++) {
			boolean ok = false;
			for (int j = 0; !ok && j < typesToSearch.size(); j++)
				ok = availableEntities[i].getType().equalsIgnoreCase(typesToSearch.get(j));
			if (ok)
				result.add(availableEntities[i].getID());
		}
		return result;
	}
	
	
	
	public Hashtable<String,String> getCompleteEntitiesDescriptionForATRole(ATEntity oneRole) {
		// Get translating INGENIAS types.
		List<String> typesToSearch = ATView.getEntityTypesForATRole(oneRole);
		// Get the actual entities from the specification.
		Hashtable<String,String> result = new Hashtable<String,String>();
		GraphEntity[] availableEntities = this.getQuestionBrowser().getAllEntities();
		for (int i = 0; i < availableEntities.length; i++) {
			boolean ok = false;
			for (int j = 0; !ok && j < typesToSearch.size(); j++)
				ok = availableEntities[i].getType().equalsIgnoreCase(typesToSearch.get(j));
			if (ok){
				result.put(availableEntities[i].getID(), availableEntities[i].getType());
			}
		}
		return result;
	}

	
	
	/**
	 * @return List<String> the List<String> of types that are compatible with the ATEntity.
	 */
	static public List<String> getEntityTypesForATRole(ATEntity oneRole) {
		// Get translating INGENIAS types.
		List<String> typesToSearch = new Vector<String>();
		switch (oneRole) {
			case ActivitySysten:
				typesToSearch.add("Task");
				break;
			case Activity:
				typesToSearch.add("Task");
				typesToSearch.add("Conversation");
				typesToSearch.add("Workflow");
				typesToSearch.add("Plan");
				typesToSearch.add("Interaction");				
				break;				
			case Subject:
				typesToSearch.add("Agent");
				typesToSearch.add("Role");
				typesToSearch.add("Organization");
				break;				
			case Object:
			case Outcome:
			case Tool:
				typesToSearch.add("Task");
				typesToSearch.add("Interaction");
				typesToSearch.add("Agent");
				typesToSearch.add("Role");				
				typesToSearch.add("Fact");
				typesToSearch.add("FrameFact"); // NUEVA
				typesToSearch.add("Resource");
				typesToSearch.add("Application");
				typesToSearch.add("EnvironmentApplication");
				typesToSearch.add("InternalApplication");
				typesToSearch.add("Believe");
				typesToSearch.add("GeneralEvent");
				break;
			case Community:
				typesToSearch.add("Organization");
				typesToSearch.add("OrganizationGroup");
				break;
			case Rules:
				break;
			case DivisionOfLabour:
				break;
			case Objective:
				typesToSearch.add("Goal");
				typesToSearch.add("StateGoal");
				typesToSearch.add("Compromise");
				break;
			case Artifact:
				typesToSearch.add("Task");
				typesToSearch.add("Conversation");
				typesToSearch.add("Interaction");
				typesToSearch.add("Workflow");
				typesToSearch.add("Plan");
				typesToSearch.add("Agent");
				typesToSearch.add("Role");				
				typesToSearch.add("Fact");
				typesToSearch.add("FrameFact"); // NUEVA
				typesToSearch.add("Resource");
				typesToSearch.add("Application");
				typesToSearch.add("EnvironmentApplication");
				typesToSearch.add("InternalApplication");
				typesToSearch.add("Believe");
				typesToSearch.add("GeneralEvent");
				typesToSearch.add("Organization");
				typesToSearch.add("OrganizationGroup");
				typesToSearch.add("Goal");
				typesToSearch.add("StateGoal");
				typesToSearch.add("Compromise");
				break;
			default:
		}
		// Get the actual entities from the specification.
		return typesToSearch;
	}
	
	
	
	/**
	 * @return List<String> the List<String> of types that are compatible with the ATRelation.
	 */
	static public List<String> getRelationTypesForATRelation(ATRelation oneRelation) {
		// Get translating INGENIAS types.
		List<String> typesToSearch = new Vector<String>();
		switch (oneRelation) {
			case Pursue:
				typesToSearch.add("GTPursues");
				typesToSearch.add("GTSatisfies" ); // NUEVA
				break;
			case ContributePositively:
				typesToSearch.add("GTSatisfies" );
				typesToSearch.add("GTAffects");
				break;				
			case ContributeNegatively:
				typesToSearch.add("GTFails" );
				typesToSearch.add("GTAffects");
				break;
			case AccomplishedBy:
				typesToSearch.add("WFResponsable" );
				break;
			case Use:
				typesToSearch.add("WFUses" );
				typesToSearch.add("WFUsesMethod" );
				typesToSearch.add("WFConsumes" );
				typesToSearch.add("Consumes");
				typesToSearch.add("GTCreates");
				typesToSearch.add("GTAffects");
				typesToSearch.add("GTDestroys");
				typesToSearch.add("GTModifies");
				break;
			case Decompose:
				typesToSearch.add("OHasGroup" ); // For Groups
				typesToSearch.add("OHasMember"); // For Agents
				typesToSearch.add("WFDecomposes"); // For Activities
				break;
			case Produce:
				typesToSearch.add("WFProduces" );
				typesToSearch.add("GTCreates" );
				typesToSearch.add("GTAffects");
				typesToSearch.add("GTModifies");
				typesToSearch.add("WFUses" );
				typesToSearch.add("WFUsesMethod" );
				break;
			case Transform:
				typesToSearch.add("WFUses" ); // NUEVA
				typesToSearch.add("WFUsesMethod" ); // NUEVA
				typesToSearch.add("WFProduces" );
				typesToSearch.add("GTCreates" );
				typesToSearch.add("GTAffects");
				typesToSearch.add("GTModifies");
				break;
			case Impede:
				typesToSearch.add("GTFails");
				break;
			case Surmount:
				typesToSearch.add("GTDecomposes");
				typesToSearch.add("GTDecomposesAND");
				typesToSearch.add("GTDecomposesOR");
				break;
			case Essential:
				typesToSearch.add("GTDecomposes");
				typesToSearch.add("GTDecomposesAND");
				break;
			case Include:  // MODIFICAR
				typesToSearch.add("GTDecomposes");
				typesToSearch.add("GTDecomposesAND");
				break;
			default:
		}
		// Get the actual relations from the specification.
		return typesToSearch;
	}
	
	
	
	/**
	 * A method that inituialize the singleton instance with the broser parameter.
	 * @param questionBrowser
	 */
	static public void initializeBrowser(Browser questionBrowser) {
		ATView.getTheInstance().setQuestionBrowser(questionBrowser);
	}



	/**
	 * @return the theInstance
	 */
	public static ATView getTheInstance() {
		if (ATView.theInstance == null)
			ATView.theInstance = new ATView();
		return ATView.theInstance;
	}



	/**
	 * @param theInstance the theInstance to set
	 */
	public static void setTheInstance(ATView theInstance) {
		ATView.theInstance = theInstance;
	}
	
}
