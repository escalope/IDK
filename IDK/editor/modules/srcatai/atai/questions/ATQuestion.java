package atai.questions;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * A class implementing a question for the Requirements Elicitation Guide (REG).
 * It includes the question to pose as a graph, the potential answers
 * found to this question in the specifications as graphs, and a list
 * of actual answers to the question as graphs too. Both potential
 * and actual answers may inlude elements that o not belong to the
 * current specifications. These elements are recorded as new elements.
 * 
 * NOTE: Does it makes sense that a potential answer extracted from
 * the specifications has new elements?
 *  
 * @author ruben
 *
 */

public class ATQuestion implements Question {
	
	/**
	 * Area of the question.
	 */
	private String area;
	/**
	 * Aspect of the question.
	 */
	private String aspect;
	/**
	 * Id of the question.
	 */
	private String id;
	/**
	 * General textual description of the question.
	 */
	private String description;
	/**
	 * The description of the question with AT concepts. 
	 */
	private ATGraph question;
	/**
	 * The question with the variable elements.
	 * The final statement of the question is:
	 * textualQuestion.get(0) + textualQuestion.get(1) + textualQuestion.get(2)...
	 * where pair indexes are fixed and odd ones are the name of variable elements
	 * of the question from <b>question</b>. Variable elements can be either
	 * entities or relationships.
	 */
	private List<String> textualQuestion;
	/**
	 * Potential complete answers to the question.
	 * A potential answer assigns to every variable element of the
	 * <b>question</b> a possible element by its name. 
	 */
	private List<Map<String, String>> potentialAnswers;
	/**
	 * New elements in potential complete answers to the question.
	 * Every element (i.e. String) in this attribute must be the second
	 * component of a pair in the same answer of the attribute
	 * <b>potentialAnswers</b>.
	 */
	private List<List<String>> newElementsInPotentialAnswers;
	/**
	 * Language of the potential complete answers to the question.
	 */
	private List<String> languageOfPotentialAnswers;
	/**
	 * Actual answers to the question.
	 * The answers selected/build for this question.
	 * There is usually just one actual answer.
	 * An actual answer assigns to every variable element of the
	 * <b>question</b> a possible element by its name. 
	 */
	private List<Map<String, String>> actualAnswers;
	
	/**
	 * Types of variables when they correspond to new entities 
	 * defined by the user. The table contains pairs (new ingenias value,type)
	 */
	private List<Map<String,String>> typesOfNewElementsInActualAnswers=new Vector<Map<String,String>>() ;

	/*
	 * New elements in actual answers to the question.
	 * Every element (i.e. String) in this attribute must be the second
	 * component of a pair in the same answer of the attribute
	 * <b>actualAnswers</b>.
	 */
	private List<List<String>> newElementsInActualAnswers;
	
	
	/**
	 * Language of the actual complete answers to the question.
	 */
	private List<String> languageOfActualAnswers;
	private boolean answerCreatedWithAssistance;
	


	
	
	/**
	 * Constructor.
	 */
	public ATQuestion() {
		super();
		this.area = "";
		this.aspect = "";
		this.id = "";
		this.description = "";
		this.question = new ATGraph();
		this.textualQuestion = new Vector<String>();
		this.potentialAnswers = new Vector<Map<String, String>>();
		this.newElementsInPotentialAnswers = new Vector<List<String>>();
		this.languageOfPotentialAnswers = new Vector<String>();
		this.actualAnswers = new Vector<Map<String, String>>();
		this.newElementsInActualAnswers = new Vector<List<String>>();
		this.languageOfActualAnswers = new Vector<String>();
	}

	
	
	/**
	 * @param area
	 * @param aspect
	 * @param id 
	 * @param description
	 * @param question
	 * @param textualQuestion
	 * @param potentialATElements 
	 * @param potentialAnswers
	 * @param newElementsInPotentialAnswers
	 * @param languageOfPotentialAnswers
	 * @param actualAnswers
	 * @param newElementsInActualAnswers
	 * @param languageOfActualAnswers 
	 */
	public ATQuestion(String area, String aspect, String id, String description,
			ATGraph question, List<String> textualQuestion,
			List<Map<String, String>> potentialAnswers,
			List<List<String>> newElementsInPotentialAnswers,
			List<String> languageOfPotentialAnswers,
			List<Map<String, String>> actualAnswers,
			List<List<String>> newElementsInActualAnswers,
			List<String> languageOfActualAnswers) {
		super();
		this.area = area;
		this.aspect = aspect;
		this.id = id;
		this.description = description;
		this.question = question;
		this.textualQuestion = textualQuestion;
		this.potentialAnswers = potentialAnswers;
		this.newElementsInPotentialAnswers = newElementsInPotentialAnswers;
		this.languageOfPotentialAnswers = languageOfPotentialAnswers;
		this.languageOfPotentialAnswers = new Vector<String>();
		this.actualAnswers = actualAnswers;
		this.newElementsInActualAnswers = newElementsInActualAnswers;
		this.languageOfActualAnswers = languageOfActualAnswers;
		this.languageOfActualAnswers = new Vector<String>();
	}
	
	
	
	/**
	 * @param area
	 * @param aspect
	 * @param id 
	 * @param description
	 * @param question
	 * @param textualQuestion
	 */
	public ATQuestion(String area, String aspect, String id, String description,
			ATGraph question, List<String> textualQuestion) {
		super();
		this.area = area;
		this.aspect = aspect;
		this.id = id;
		this.description = description;
		this.question = question;
		this.textualQuestion = textualQuestion;
		this.potentialAnswers = new Vector<Map<String, String>>();
		this.newElementsInPotentialAnswers = new Vector<List<String>>();
		this.languageOfPotentialAnswers = new Vector<String>();
		this.actualAnswers = new Vector<Map<String, String>>();
		this.newElementsInActualAnswers = new Vector<List<String>>();
		this.languageOfActualAnswers = new Vector<String>();
	}
	
	
	
	/* (non-Javadoc)
	 * @see atai.questions.Question#getArea()
	 */
	public String getArea() {
		return area;
	}



	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}



	/* (non-Javadoc)
	 * @see atai.questions.Question#getAspect()
	 */
	public String getAspect() {
		return aspect;
	}



	/**
	 * @param aspect the aspect to set
	 */
	public void setAspect(String aspect) {
		this.aspect = aspect;
	}


	
	/* (non-Javadoc)
	 * @see atai.questions.Question#getId()
	 */
	public String getId() {
		return id;
	}
	
	
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	/* (non-Javadoc)
	 * @see atai.questions.Question#getName()
	 */
	public String getName() {
		return "Question_" + this.getArea() + "_" + this.getAspect() + "_" + this.getId();
	}


	
	/* (non-Javadoc)
	 * @see atai.questions.Question#getDescription()
	 */
	public String getDescription() {
		return description;
	}
	
	
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	

	
	/* (non-Javadoc)
	 * @see atai.questions.Question#getQuestion()
	 */
	public ATGraph getQuestion() {
		return this.question;
	}
	
	
	
	/**
	 * @param question the question to set
	 */
	public void setQuestion(ATGraph question) {
		this.question = question;
	}
	
	
	
	/* (non-Javadoc)
	 * @see atai.questions.Question#getTextualQuestion()
	 */
	public List<String> getTextualQuestion() {
		return textualQuestion;
	}
	
	
	
	/**
	 * @param textualQuestion the textualQuestion to set
	 */
	public void setTextualQuestion(List<String> textualQuestion) {
		this.textualQuestion = textualQuestion;
	}
		


	/* (non-Javadoc)
	 * @see atai.questions.Question#getActualAnswers()
	 */
	public List<Map<String, String>> getActualAnswers() {
		return actualAnswers;
	}
	
	
	
	/* (non-Javadoc)
	 * @see atai.questions.Question#getNewElementsInActualAnswers()
	 */
	public List<List<String>> getNewElementsInActualAnswers() {
		return newElementsInActualAnswers;
	}
	
	
	
	/* (non-Javadoc)
	 * @see atai.questions.Question#getLanguageOfActualAnswers()
	 */
	public List<String> getLanguageOfActualAnswers() {
		return languageOfActualAnswers;
	}
	
	
	
	/* (non-Javadoc)
	 * @see atai.questions.Question#sizeActualAnswers()
	 */
	public int sizeActualAnswers() {
		return this.getActualAnswers().size();
	}
	
	
	
	/**
	 * 
	 * @param language
	 * @param correspondence
	 * @param newElements
	 * @return
	 */
	protected Graph getAnswerFromMapping(String language,
			Map<String, String> correspondence,
			List<String> newElements) {
		// Translate the entities.
		Map<String, ATEntity> entities = new HashMap<String, ATEntity>();
		for (int i = 0; i < this.getQuestion().getEntities().size(); i++) {
			String originalName = this.getQuestion().getEntities().get(i);
			String correspondentName = correspondence.get(originalName);
			entities.put(correspondentName,
					this.getQuestion().getATEntityTypes().get(originalName));
		}
		// Translate the relations and their related roles.
		Map<String, ATRelation> relations = new HashMap<String, ATRelation>();
		Map<String,	List<String>> roles = new HashMap<String, List<String>>();
		for (int j = 0; j < this.getQuestion().getRelations().size(); j++) {
			String originalName = this.getQuestion().getRelations().get(j);
			String correspondentName = correspondence.get(originalName);
			relations.put(correspondentName,
					this.getQuestion().getATRelationTypes().get(originalName));
			List<String> originalRelationRoles = this.getQuestion().getRoles().get(originalName);
			List<String> correspondentRelationRoles = new Vector<String>();
			for (int k = 0; k < originalRelationRoles.size(); k++) {
				String originalRoleName = originalRelationRoles.get(k);
				String correspondentRoleName = correspondence.get(originalRoleName);
				correspondentRelationRoles.add(correspondentRoleName);
			}
			roles.put(correspondentName, correspondentRelationRoles);
		}
		// Translate the types of roles.
		Map<String, String> roleTypes = new HashMap<String, String>();			
		for (String originalName : this.getQuestion().getRoleTypes().keySet()) {
			String correspondentName = correspondence.get(originalName);
			roleTypes.put(correspondentName,
					this.getQuestion().getRoleTypes().get(originalName));
		}
		// Translate the players of roles. Players are entitites.
		Map<String, String> players = new HashMap<String, String>();
		for (String originalName : this.getQuestion().getPlayers().keySet()) {
			String correspondentName = correspondence.get(originalName);
			String originalPlayer = this.getQuestion().getPlayers().get(originalName);
			String correspondentPlayer = correspondence.get(originalPlayer);
			players.put(correspondentName, correspondentPlayer);
		}
		// Return the result.
		return new ATGraph(language, entities, relations, roles, roleTypes, players, newElements);
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
		// Translate actual answer.
		return this.getAnswerFromMapping(language, correspondence, newElements);
	}
	
	

	/**
	 * @param index
	 * @param Graph   the actual answer to set.
	 */
	public void setActualAnswers(int index, Graph oneAnswer) {
		// The language of the answer.
		List<String> newLanguages = this.getLanguageOfActualAnswers();
		if (newLanguages.size() == index)
			newLanguages.add( oneAnswer.getLanguage() );
		else
			newLanguages.set(index, oneAnswer.getLanguage());
		this.setLanguageOfActualAnswers(newLanguages);
		// The mapping to apply in the translation.
		List<Map<String, String>> newCorrespondences = this.getActualAnswers();
		Map<String, String> oneCorrespondence = new HashMap<String, String>();
		List<String> questionEntities = this.getQuestion().getEntities();
		List<String> answerEntities = oneAnswer.getEntities();
		for (int i = 0; i < questionEntities.size(); i++)
			oneCorrespondence.put(questionEntities.get(i), answerEntities.get(i));
		List<String> questionRelations = this.getQuestion().getRelations();
		List<String> answerRelations = oneAnswer.getRelations();
		for (int j = 0; j < questionRelations.size(); j++) {
			oneCorrespondence.put(questionRelations.get(j), answerRelations.get(j));
			List<String> questionRoles = this.getQuestion().getRoles().get(questionRelations.get(j));
			List<String> answerRoles = oneAnswer.getRoles().get(answerRelations.get(j));
			for (int k = 0; k < questionRoles.size(); k++)
				oneCorrespondence.put(questionRoles.get(k), answerRoles.get(k));
		}
		if (newCorrespondences.size() == index)
			newCorrespondences.add( oneCorrespondence );
		else
			newCorrespondences.set(index, oneCorrespondence);
		this.setActualAnswers(newCorrespondences);
		// New elements in the answer.
		List<List<String>> newNewElements = this.getNewElementsInActualAnswers();
		if (newNewElements.size() == index)
			newNewElements.add( oneAnswer.getVariables() );
		else
		    newNewElements.set(index, oneAnswer.getVariables());
		this.setNewElementsInActualAnswers(newNewElements);
	}
	
	
	
	/**
	 * @param index int
	 * @param language String the language of the answer 
	 * @param oneAnswer Map<String, String>   the actual answer to set.
	 * @param newElements List<String>   the new elements to set. 
	 * @param typesOfNewValues 
	 */
	public void setActualAnswers(int index, String language, Map<String, String> oneAnswer, 
			List<String> newElements) {
		// The language of the answer.
		List<String> newLanguages = this.getLanguageOfActualAnswers();
		if (newLanguages.size() == index)
			newLanguages.add( language );
		else
			newLanguages.set(index, language);
		this.setLanguageOfActualAnswers(newLanguages);
		// The mapping to apply in the translation.
		List<Map<String, String>> newCorrespondences = this.getActualAnswers();
		if (newCorrespondences.size() == index)
			newCorrespondences.add( oneAnswer );
		else
			newCorrespondences.set(index, oneAnswer);
		this.setActualAnswers(newCorrespondences);
		
				
		// New elements in the answer.
		List<List<String>> newNewElements = this.getNewElementsInActualAnswers();
		if (newNewElements.size() == index)
			newNewElements.add( newElements );
		else
		    newNewElements.set(index, newElements);
		this.setNewElementsInActualAnswers(newNewElements);
	}
	
	public void addTypesOfNewElements(Map<String, String> typesOfNewValues){
		this.typesOfNewElementsInActualAnswers.add(typesOfNewValues);	
	}
	
	/**
	 * @param actualAnswers the actualAnswers to set
	 */
	public void setActualAnswers(List<Map<String, String>> actualAnswers) {
		this.actualAnswers = actualAnswers;
	}
	
	
	
	/**
	 * @param newElementsInActualAnswers the newElementsInActualAnswers to set
	 */
	public void setNewElementsInActualAnswers(List<List<String>> newElementsInActualAnswers) {
		this.newElementsInActualAnswers = newElementsInActualAnswers;
	}
	
	
	
	/**
	 * @param languageOfActualAnswers the languageOfActualAnswers to set
	 */
	public void setLanguageOfActualAnswers(List<String> languageOfActualAnswers) {
		this.languageOfActualAnswers = languageOfActualAnswers;
	}


	
	/* (non-Javadoc)
	 * @see atai.questions.Question#getPotentialAnswers()
	 */
	public List<Map<String, String>> getPotentialAnswers() {
		return potentialAnswers;
	}
	
	
	
	/* (non-Javadoc)
	 * @see atai.questions.Question#getNewElementsInPotentialAnswers()
	 */
	public List<List<String>> getNewElementsInPotentialAnswers() {
		return newElementsInPotentialAnswers;
	}
	
	
	
	/* (non-Javadoc)
	 * @see atai.questions.Question#getLanguageOfPotentialAnswers()
	 */
	public List<String> getLanguageOfPotentialAnswers() {
		return languageOfPotentialAnswers;
	}
	
	
	
	/* (non-Javadoc)
	 * @see atai.questions.Question#sizePotentialAnswers()
	 */
	public int sizePotentialAnswers() {
		return this.getPotentialAnswers().size();
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
		// Translate actual answer.
		return this.getAnswerFromMapping(language, correspondence, newElements);
	}
	
	
	
	/**
	 * @param index
	 * @param Graph   the potential answer to set.
	 */
	public void setPotentialAnswers(int index, Graph oneAnswer) {
		// The language of the answer.
		List<String> newLanguages = this.getLanguageOfPotentialAnswers();
		newLanguages.set(index, oneAnswer.getLanguage());
		this.setLanguageOfPotentialAnswers(newLanguages);
		// The mapping to apply in the translation.
		List<Map<String, String>> newCorrespondences = this.getPotentialAnswers();
		Map<String, String> oneCorrespondence = new HashMap<String, String>();
		List<String> questionEntities = this.getQuestion().getEntities();
		List<String> answerEntities = oneAnswer.getEntities();
		for (int i = 0; i < questionEntities.size(); i++)
			oneCorrespondence.put(questionEntities.get(i), answerEntities.get(i));
		List<String> questionRelations = this.getQuestion().getRelations();
		List<String> answerRelations = oneAnswer.getRelations();
		for (int j = 0; j < questionRelations.size(); j++) {
			oneCorrespondence.put(questionRelations.get(j), answerRelations.get(j));
			List<String> questionRoles = this.getQuestion().getRoles().get(questionRelations.get(j));
			List<String> answerRoles = oneAnswer.getRoles().get(answerRelations.get(j));
			for (int k = 0; k < questionRoles.size(); k++)
				oneCorrespondence.put(questionRoles.get(k), answerRoles.get(k));
		}
		newCorrespondences.set(index, oneCorrespondence);
		this.setPotentialAnswers(newCorrespondences);
		// New elements in the answer.
		List<List<String>> newNewElements = this.getNewElementsInPotentialAnswers();
		newNewElements.set(index, oneAnswer.getVariables());
		this.setNewElementsInPotentialAnswers(newNewElements);
	}
	
	
	
	/**
	 * @param index int
	 * @param language String the language of the answer 
	 * @param oneAnswer Map<String, String>   the actual answer to set.
	 * @param newElements List<String>   the new elements to set. 
	 */
	public void setPotentialAnswers(int index, String language, Map<String, String> oneAnswer, List<String> newElements) {
		// The language of the answer.
		List<String> newLanguages = this.getLanguageOfPotentialAnswers();
		if (newLanguages.size() == index)
			newLanguages.add( language );
		else
			newLanguages.set(index, language);
		this.setLanguageOfPotentialAnswers(newLanguages);
		// The mapping to apply in the translation.
		List<Map<String, String>> newCorrespondences = this.getPotentialAnswers();
		if (newCorrespondences.size() == index)
			newCorrespondences.add( oneAnswer );
		else
			newCorrespondences.set(index, oneAnswer);
		this.setPotentialAnswers(newCorrespondences);
		// New elements in the answer.
		List<List<String>> newNewElements = this.getNewElementsInPotentialAnswers();
		if (newNewElements.size() == index)
			newNewElements.add( newElements );
		else
		    newNewElements.set(index, newElements);
		this.setNewElementsInPotentialAnswers(newNewElements);
	}
	
	
	
	/**
	 * @param potentialAnswers the potentialAnswers to set
	 */
	public void setPotentialAnswers(List<Map<String, String>> potentialAnswers) {
		this.potentialAnswers = potentialAnswers;
	}
	
	
	
	/**
	 * @param potentialAnswers the potentialAnswers to set
	 */
	public void setNewElementsInPotentialAnswers(List<List<String>> newElementsInPotentialAnswers) {
		this.newElementsInPotentialAnswers = newElementsInPotentialAnswers;
	}
	
	
	
	/**
	 * @param potentialAnswers the potentialAnswers to set
	 */
	public void setLanguageOfPotentialAnswers(List<String> languageOfPotentialAnswers) {
		this.languageOfPotentialAnswers = languageOfPotentialAnswers;
	}
	
	
	
	/**
	 * Calculates the potential answers to the question.
	 */
	public void solvePotentialAnswers() {
	}



	public List<Map<String,String>> getTypesOfNewElementsInActualAnswers() {
		return typesOfNewElementsInActualAnswers;
	}



	public void setTypesOfNewElementsInActualAnswers(
			List<Map<String,String>> typesOfNewElementsInActualAnswers) {
		this.typesOfNewElementsInActualAnswers = typesOfNewElementsInActualAnswers;
	}



	public void setAnswerCreatedWithAssistance(boolean value) {
		this.answerCreatedWithAssistance=value;
	}
	
	public boolean getAnswerCreatedWithAssistance() {
		return answerCreatedWithAssistance;
	}
	
}
