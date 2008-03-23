package atai.questions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import ingenias.exception.NullEntity;
import ingenias.generator.browser.*;

/**
 * A class implementing a question for the Requirements Elicitation Guide (REG).
 * It includes the question to pose as a graph, the potential answers
 * found to this question in the specifications as graphs, and a list
 * of actual answers to the question as graphs too. Both potential
 * and actual answers may inlude elements that o not belong to the
 * current specifications. These elements are recorded as new elements.
 * The potential answers are extracted from a browser that provides access
 * to the specifications. 
 *  
 * @author ruben
 *
 */

public class ATQuestionGraph extends ATQuestion {

	/**
	 * Constants.
	 */
	static public final String GRAPH_LANGUAGE = "INGENIAS";
	
	/**
	 * AttributedElements of the potential complete answers to the question.
	 * AttributedElement is the superclass of both <b>GraphEntity</b> and
	 * <b>GraphRelationship</b>.
	 * It is composed by pairs <String, AttributedElement> where the first
	 * element is the name of an element extracted from the <b>question</b> of
	 * the superclass <b>ATQuestion</b>.
	 * It is a specialization of the attribute <b>potentialAnswers</b> of the
	 * class <b>ATQuestion</b> for INGENIAS specifications.
	 */
	private List<Map<String, AttributedElement>> potentialAnswersElements;
	/**
	 * AttributedElements of the actual complete answers to the question.
	 * AttributedElement is the superclass of both <b>GraphEntity</b> and
	 * <b>GraphRelationship</b>.
	 * It is composed by pairs <String, AttributedElement> where the first
	 * element is the name of an element extracted from the <b>question</b> of
	 * the superclass <b>ATQuestion</b>.
	 * It is a specialization of the attribute <b>actualAnswers</b> of the
	 * class <b>ATQuestion</b> for INGENIAS specifications.
	 */
	private List<Map<String, AttributedElement>> actualAnswersElements;
	/**
	 * A browser to acces to the specifications of the system.
	 */
	private Browser questionBrowser;

	

	
	
	/**
	 * @param area
	 * @param aspect
	 * @param id
	 * @param description
	 * @param question
	 * @param textualQuestion
	 * @param potentialAnswers
	 * @param newElementsInPotentialAnswers
	 * @param languageOfPotentialAnswers
	 * @param actualAnswers
	 * @param newElementsInActualAnswers
	 * @param languageOfActualAnswers
	 * @param questionBrowser
	 */
	public ATQuestionGraph(String area, String aspect, String id, String description,
			ATGraph question, List<String> textualQuestion,
			List<Map<String, String>> potentialAnswers,
			List<List<String>> newElementsInPotentialAnswers,
			List<String> languageOfPotentialAnswers,
			List<Map<String, String>> actualAnswers,
			List<List<String>> newElementsInActualAnswers,
			List<String> languageOfActualAnswers,
			Browser questionBrowser) {
		super(area, aspect, id, description, question, textualQuestion,
				potentialAnswers, newElementsInPotentialAnswers, languageOfPotentialAnswers,
				actualAnswers, newElementsInActualAnswers, languageOfActualAnswers);
		this.potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		this.actualAnswersElements = new Vector<Map<String, AttributedElement>>();
		this.questionBrowser = questionBrowser;
	}

	
	
	/**
	 * @param oneATQuestion ATQuestion 
	 * @param questionBrowser
	 */
	public ATQuestionGraph(Question oneATQuestion, Browser questionBrowser) {
		super();
		this.initializeFromATQuestion(oneATQuestion);
		this.potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		this.actualAnswersElements = new Vector<Map<String, AttributedElement>>();
		this.questionBrowser = questionBrowser;
	}
	
	
	
	/**
	 * @param questionBrowser
	 */
	public ATQuestionGraph(Browser questionBrowser) {
		super();
		this.potentialAnswersElements = new Vector<Map<String, AttributedElement>>();
		this.actualAnswersElements = new Vector<Map<String, AttributedElement>>();
		this.questionBrowser = questionBrowser;
	}
	
	
	
	/**
	 * @return the potentialAnswersElements
	 */
	public List<Map<String, AttributedElement>> getPotentialAnswersElements() {
		return potentialAnswersElements;
	}

	
	
	/* (non-Javadoc)
	 * @see atai.questions.Question#getPotentialAnswers()
	 */
	public Graph getPotentialAnswers(int index) {
		// The language of the answer.
		String language = this.getLanguageOfPotentialAnswers().get(index);
		// The mapping to apply in the translation.
		Map<String, String> correspondence = this.getPotentialAnswers().get(index);
		// New elements in the answer.
		List<String> newElements = this.getNewElementsInPotentialAnswers().get(index);
		// Elements from INGENIAS graphs.
		Map<String, AttributedElement> elements = this.getPotentialAnswersElements().get(index);
		// Translate actual answer.
		return this.getAnswerFromMappingAndElement(language, correspondence, newElements, elements);
	}


	
	/**
	 * @param potentialAnswersElements the potentialAnswersElements to set
	 */
	public void setPotentialAnswersElements(
			List<Map<String, AttributedElement>> potentialAnswersElements) {
		this.potentialAnswersElements = potentialAnswersElements;
		List<Map<String, String>> atQuestionPotentialAnswers =
			new Vector<Map<String, String>>();
		Map<String, AttributedElement> oneAnswer;
		Map<String, String> oneATQuestionAnswer;
		AttributedElement value;
		String name;
		for (int i = 0; i < potentialAnswersElements.size(); i++) {
			oneAnswer = potentialAnswersElements.get(i);
			oneATQuestionAnswer = new HashMap<String, String>();
			for (String key : oneAnswer.keySet()) {
				value = oneAnswer.get(key);
				if (value instanceof GraphEntity) {
					name = ((GraphEntity) value).getID();
					oneATQuestionAnswer.put(key, name);
				} else if (value instanceof GraphRelationship) {
					name = ((GraphRelationship) value).getID();
					oneATQuestionAnswer.put(key, name);
				}
////			// No vale ponerlo porque es un tipo				
//				else if (value instanceof GraphRole)
//					name = ((GraphRole) value).getName();				
			}
			atQuestionPotentialAnswers.add(oneATQuestionAnswer);
		}
		super.setPotentialAnswers(atQuestionPotentialAnswers);
	}
	
	
	
	/**
	 * @param index
	 * @param Graph   the actual answer to set.
	 */
	public void setPotentialAnswers(int index, Graph oneAnswer) {
		super.setPotentialAnswers(index, oneAnswer);
		// Get translating INGENIAS AttributedElement.
		Map<String, String> currentAnswer = super.getPotentialAnswers().get(index);
		// Update translating INGENIAS AttributedElement.		
		Map<String, AttributedElement> elements = this.getElementsFromMapping(currentAnswer);
		List<Map<String, AttributedElement>> potentialElements = this.getPotentialAnswersElements();
		potentialElements.set(index, elements);
		this.potentialAnswersElements = potentialElements;
	}
	
	
	
	/**
	 * @param index int
	 * @param language String the language of the answer 
	 * @param oneAnswer Map<String, String>   the potential answer to set.
	 * @param newElements List<String>   the new elements to set. 
	 */
	public void setPotentialAnswers(int index, String language, Map<String, String> oneAnswer, List<String> newElements) {
		super.setPotentialAnswers(index, language, oneAnswer, newElements);
		// Get translating INGENIAS AttributedElement.
		// Update translating INGENIAS AttributedElement.		
		Map<String, AttributedElement> elements = this.getElementsFromMapping(oneAnswer);
		List<Map<String, AttributedElement>> potentialElements = this.getPotentialAnswersElements();
		potentialElements.set(index, elements);
		this.potentialAnswersElements = potentialElements;		
	}
	
	
	
	/**
	 * @param potentialAnswers the actualAnswers to set
	 */
	public void setPotentialAnswers(List<Map<String, String>> potentialAnswers) {
		super.setPotentialAnswers(potentialAnswers);
		// Update translating INGENIAS AttributedElement.
		List<Map<String, AttributedElement>> potentialElements = new Vector<Map<String, AttributedElement>>();
		for (int i = 0; i < super.getPotentialAnswers().size(); i++) {
			Map<String, String> currentAnswer = super.getPotentialAnswers().get(i);
			Map<String, AttributedElement> elements = this.getElementsFromMapping(currentAnswer);
			potentialElements.add(elements);
		}
		this.potentialAnswersElements = potentialElements;
	}
	
	
	
	/**
	 * @return the actualAnswersElements
	 */
	public List<Map<String, AttributedElement>> getActualAnswersElements() {
		return actualAnswersElements;
	}

	
	
	/* (non-Javadoc)
	 * @see atai.questions.Question#getActualAnswers()
	 */
	public Graph getActualAnswers(int index) {
		// The language of the answer.
		String language = this.getLanguageOfActualAnswers().get(index);
		// The mapping to apply in the translation.
		Map<String, String> correspondence = this.getActualAnswers().get(index);
		// New elements in the answer.
		List<String> newElements = this.getNewElementsInActualAnswers().get(index);
		// Elements from INGENIAS graphs.
		Map<String, AttributedElement> elements = this.getActualAnswersElements().get(index);
		// Translate actual answer.
		return this.getAnswerFromMappingAndElement(language, correspondence, newElements, elements);
	}

	

	/**
	 * @param actualAnswersElements the actualAnswersElements to set
	 */
	public void setActualAnswersElements(
			List<Map<String, AttributedElement>> actualAnswersElements) {
		this.actualAnswersElements = actualAnswersElements;
		/* El name de los roles es su tipo. El id de entidades y relaciones s� es �nico */
		/* this.questionBrowser.getAllEntities()[0].getID();
		this.questionBrowser.getAllEntities()[0].getRelationships()[0].getID();
		this.questionBrowser.getAllEntities()[0].getRelationships()[0].getRoles()[0].getName(); */
		List<Map<String, String>> atQuestionActualAnswers = new Vector<Map<String, String>>();
		Map<String, AttributedElement> oneAnswer;
		Map<String, String> oneATQuestionAnswer;
		AttributedElement value;
		String name;
		for (int i = 0; i < actualAnswersElements.size(); i++) {
			oneAnswer = actualAnswersElements.get(i);
			oneATQuestionAnswer = new HashMap<String, String>();
			for (String key : oneAnswer.keySet()) {
				value = oneAnswer.get(key);
				if (value instanceof GraphEntity) {
					name = ((GraphEntity) value).getID();
					oneATQuestionAnswer.put(key, name);
				} else if (value instanceof GraphRelationship) {
					name = ((GraphRelationship) value).getID();
					oneATQuestionAnswer.put(key, name);
				}
////			// No vale ponerlo porque es un tipo				
//				else if (value instanceof GraphRole)
//					name = ((GraphRole) value).getName();				
			}
			atQuestionActualAnswers.add(oneATQuestionAnswer);
		}
		super.setActualAnswers(atQuestionActualAnswers);
	}
	

	
	/**
	 * @param index
	 * @param Graph   the actual answer to set.
	 */
	public void setActualAnswers(int index, Graph oneAnswer) {
		super.setActualAnswers(index, oneAnswer);
		// Get translating INGENIAS AttributedElement.
		Map<String, String> currentAnswer = super.getActualAnswers().get(index);
		// Update translating INGENIAS AttributedElement.		
		Map<String, AttributedElement> elements = this.getElementsFromMapping(currentAnswer);
		List<Map<String, AttributedElement>> actualElements = this.getActualAnswersElements();
		actualElements.set(index, elements);
		this.actualAnswersElements = actualElements;
	}
	
	
	
	/**
	 * @param index int
	 * @param language String the language of the answer 
	 * @param oneAnswer Map<String, String>   the actual answer to set.
	 * @param newElements List<String>   the new elements to set. 
	 */
	public void setActualAnswers(int index, String language, Map<String, String> oneAnswer, List<String> newElements) {
		super.setActualAnswers(index, language, oneAnswer, newElements);
		// Get translating INGENIAS AttributedElement.
		// Update translating INGENIAS AttributedElement.		
		Map<String, AttributedElement> elements = this.getElementsFromMapping(oneAnswer);
		List<Map<String, AttributedElement>> actualElements = this.getActualAnswersElements();
		actualElements.set(index, elements);
		this.actualAnswersElements = actualElements;		
	}
	
	

	/**
	 * @param actualAnswers the actualAnswers to set
	 */
	public void setActualAnswers(List<Map<String, String>> actualAnswers) {
		super.setActualAnswers(actualAnswers);
		// Update translating INGENIAS AttributedElement.
		List<Map<String, AttributedElement>> actualElements = new Vector<Map<String, AttributedElement>>();
		for (int i = 0; i < super.getActualAnswers().size(); i++) {
			Map<String, String> currentAnswer = super.getActualAnswers().get(i);
			Map<String, AttributedElement> elements = this.getElementsFromMapping(currentAnswer);
			actualElements.add(elements);
		}
		this.actualAnswersElements = actualElements;
	}
	
	
	
	/**
	 * @return the questionBrowser
	 */
	public Browser getQuestionBrowser() {
		return questionBrowser;
	}

	
	
	/**
	 * @param questionBrowser the questionBrowser to set
	 */
	public void setQuestionBrowser(Browser questionBrowser) {
		this.questionBrowser = questionBrowser;
	}
	
		
	
	/**
	 * A method that uses the browser to get the potential answers
	 * to this question.
	 * @todo
	 */
	public void solvePotentialAnswers() {
		super.solvePotentialAnswers();
	}
	
	
	
	/**
	 * Initialization of this question from the parameter.
	 * @param oneATQuestion the source ATQuestion
	 */
	protected void initializeFromATQuestion(Question oneATQuestion) {
		this.setArea(oneATQuestion.getArea());
		this.setAspect(oneATQuestion.getAspect());
		this.setId(oneATQuestion.getId());
		this.setDescription(oneATQuestion.getDescription());
		this.setQuestion(oneATQuestion.getQuestion());
		this.setTextualQuestion(oneATQuestion.getTextualQuestion());
	}
	
	
	
	/**
	 * Tries to find a relation of the given kind that connect the source entity
	 * with the target.
	 * @param source
	 * @param target
	 * @param kindOfRelation
	 * @param connectingRelation
	 * @return GraphRelationship   The connectingRelation or null if it does not exist
	 */
	public GraphRelationship isConnected(GraphEntity source, GraphEntity target,
			ATRelation kindOfRelation) {
		GraphRelationship connectingRelation = null;
		// GraphRelationship[] potentialRelations = source.getRelationships();
		Vector potentialRelations = source.getAllRelationships();
		for (int i = 0; connectingRelation == null && i < potentialRelations.size(); i++) {
			GraphRelationship aPotentialRelation = (GraphRelationship) potentialRelations.get(i); 
			if (ATView.getRelationTypesForATRelation(kindOfRelation).contains(
					aPotentialRelation.getType() ) ) {
				int sourceIndex = -1;
				int targetIndex = -1;
				GraphRole[] potentialRoles = aPotentialRelation.getRoles();
				for (int j = 0; j < potentialRoles.length; j++)
					try {
						if ((sourceIndex == -1) && potentialRoles[j].getPlayer().getID().equalsIgnoreCase( source.getID() ) )
							sourceIndex = j;
						if ((targetIndex == -1) && (sourceIndex != j) &&
							potentialRoles[j].getPlayer().getID().equalsIgnoreCase( target.getID() ) )
//							connectingRelation = aPotentialRelation;
							targetIndex = j;
					} catch (NullEntity e) {
						// e.printStackTrace();
					}
				if ((sourceIndex >= 0) && ((targetIndex >= 0)))
					connectingRelation = aPotentialRelation;
			}
		}
		return connectingRelation;
	}
	
	
	
	/**
	 * 
	 * @param correspondence
	 * @return
	 */
	protected Map<String, AttributedElement> getElementsFromMapping(
			Map<String, String> correspondence) {
		Map<String, AttributedElement> result = new HashMap<String, AttributedElement>();
		for (String key : correspondence.keySet()) {
			String elementName = correspondence.get(key);
			AttributedElement element = null;
			element = this.getQuestionBrowser().findEntity(elementName);
			if (element == null) {
				GraphEntity[] allEntities = this.getQuestionBrowser().getAllEntities();
				for (int i = 0;	element == null && i < allEntities.length; i++) {
					// GraphRelationship[] allRelations = allEntities[i].getRelationships();
					Vector allRelations =  allEntities[i].getAllRelationships();
					for (int j = 0; element == null && j < allRelations.size();	j++) {
						GraphRelationship aPotentialRelation = (GraphRelationship) allRelations.get(j);
						if (aPotentialRelation.getID().equalsIgnoreCase(elementName))
							element = aPotentialRelation; 
					}
				}
			}
			if (element != null)
				result.put(key, element);	
		}
		// Return the result.
		return result;
	}
	
	
	
	/**
	 * 
	 * @param correspondence
	 * @return
	 */
	protected Graph getAnswerFromMappingAndElement(String language,
			Map<String, String> correspondence,
			List<String> newElements,
			Map<String, AttributedElement> elements) {
		Graph partialAnswer = super.getAnswerFromMapping(language,
				correspondence, newElements);
		// Translate the types according to their true elements.
		// Translate the entities.
		Map<String, String> entities = partialAnswer.getEntityTypes();
		for (String entity : entities.keySet()) {
			String originalType = null;
			for (String original : correspondence.keySet())
				if (correspondence.get(original).equalsIgnoreCase(entity))
					originalType = original;
			String correspondentType = ((GraphEntity) elements.get(originalType)).getType();
			entities.put(entity, correspondentType);
		}
		// Translate the relations. Roles are not incldued in the translation.
		// Roles do not change their type in this process.
		Map<String, String> relations = partialAnswer.getRelationTypes();
		for (String relation : relations.keySet()) {
			String originalType = null;
			for (String original : correspondence.keySet())
				if (correspondence.get(original).equalsIgnoreCase(relation))
					originalType = original;
			String correspondentType = ((GraphRelationship) elements.get(originalType)).getType();   /////ERROR
			relations.put(relation, correspondentType);
		}
		// Return the result.
		return new GenericGraph(language, entities, relations,
				partialAnswer.getRoles(), partialAnswer.getRoleTypes(), partialAnswer.getPlayers(),
				partialAnswer.getVariables());
	}
		
	
	
	/**
	 * Find with the help of the browser the <b>AttributedElement</b>s that
	 * correspond to the elements in the mapping of the answer.
	 * @param currentAnswer Map<String, String>  
	 * @result Map<String, AttributedElement>
	 */
/*	protected Map<String, AttributedElement> getAnswerElements(Map<String, String> currentAnswer) {
		// Get translating INGENIAS AttributedElement.
		Map<String, AttributedElement> answerElements = new HashMap<String, AttributedElement>(); 
		// Entities.
		String questionEntity;
		String answerEntity;
		GraphEntity answerGraphEntity = null;
		for (int i = 0; i < this.getQuestion().getEntities().size(); i++) {
			questionEntity = this.getQuestion().getEntities().get(i);
			answerEntity = currentAnswer.get(questionEntity);
			answerGraphEntity = this.getQuestionBrowser().findEntity(answerEntity);
			answerElements.put(questionEntity, answerGraphEntity);
		}
		// Relations.
		String questionRelation;
		String answerRelation;
		GraphRelationship answerGraphRelation;
		for (int j_1 = 0; j_1 < this.getQuestion().getRelations().size(); j_1++) {
			questionRelation = this.getQuestion().getRelations().get(j_1);
			answerRelation = currentAnswer.get(questionRelation);
			answerGraphRelation = null;
			boolean found = false;
			for (int j_2 = 0; !found && j_2 < this.getQuestionBrowser().getAllEntities().length; j_2++) {
				GraphEntity tmpEntity = this.getQuestionBrowser().getAllEntities()[j_2];
				GraphRelationship[] tmpRelations = tmpEntity.getRelationships(); 
				for (int j_3 = 0; !found && j_3 < tmpRelations.length; j_3++)
////// S�LO FUNCIONA SI TODAS LAS RELACIONES TIENEN IDS �NICOS.					
					if (tmpRelations[j_3].getID().equalsIgnoreCase(answerRelation)) {
						found = true;
						answerGraphRelation = tmpRelations[j_3];
					} 
			}
			answerElements.put(questionRelation, answerGraphRelation);
		}
		// Roles.
		String questionRole;
		String answerRole;
		GraphRole answerGraphRole;
		for (int k_1 = 0; k_1 < this.getQuestion().getRelations().size(); k_1++) {
			questionRelation = this.getQuestion().getRelations().get(k_1);
			List<String> roles = this.getQuestion().getRoles().get(questionRelation);
			for (int k_2 = 0; k_2 < roles.size(); k_2++) {
				questionRole = roles.get(k_2);
				answerRole = currentAnswer.get(questionRole);
				answerGraphRole = null;
				boolean found = false;
				for (int j_2 = 0; !found && j_2 < this.getQuestionBrowser().getAllEntities().length; j_2++) {
					GraphEntity tmpEntity = this.getQuestionBrowser().getAllEntities()[j_2];
					GraphRelationship[] tmpRelations = tmpEntity.getRelationships();
//////				 S�LO FUNCIONA SI TODAS LAS RELACIONES TIENEN IDS �NICOS.					
					for (int j_3 = 0; !found && j_3 < tmpRelations.length; j_3++) {
						GraphRole[] tmpRoles = tmpRelations[j_3].getRoles();
						for (int j_4 = 0; !found && j_4 < tmpRoles.length; j_4++) {
//////						 S�LO FUNCIONA SI TODOS LOS ROLES TIENEN IDS �NICOS.
							if (tmpRoles[j_4].getName().equalsIgnoreCase(answerRole)) {
								found = true;
								answerGraphRole = tmpRoles[j_4];
							}
						}
					}
				}
				answerElements.put(questionRole, answerGraphRole);
			}
		}
		
		return answerElements;
	}*/
	
	
	
	protected void completePotentialAnswers(int potentialAnswersSize) {
		List<String> languageOfPotentialAnswers = new Vector<String>();
		List<List<String>> newElementsInPotentialAnswers = new Vector<List<String>>();
		for (int i = 0; i < potentialAnswersSize; i++) {
			languageOfPotentialAnswers.add(ATQuestionGraph.GRAPH_LANGUAGE);
			// Potential answers do not have any new element.
			newElementsInPotentialAnswers.add(new Vector<String>());
		}
		this.setLanguageOfPotentialAnswers(languageOfPotentialAnswers);
		this.setNewElementsInPotentialAnswers(newElementsInPotentialAnswers);
	}
	
	
	
	protected void completeRoles(Map<String, String> correspondences) {
		for (String relation : this.getQuestion().getRelations())
			for (String role : this.getQuestion().getRoles().get(relation)) {
				correspondences.put(role, role);
			}
	}
	
}
