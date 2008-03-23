package atai.questions;

import java.util.List;
import java.util.Map;

/**
 * A question for the Requirements Elicitation Guide (REG).
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

public interface Question {

	/**
	 * @return the area
	 */
	public String getArea();
	
	/**
	 * @param area the area to set
	 */
	public void setArea(String area);

	/**
	 * @return the aspect
	 */
	public String getAspect();
	
	/**
	 * @param aspect the aspect to set
	 */
	public void setAspect(String aspect);
	
	/**
	 * @return the id
	 */
	public String getId();
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id);

	/**
	 * @return the name as area + aspect + id
	 */
	public String getName();
	
	/**
	 * @return the description
	 */
	public String getDescription();
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description);
	
	/**
	 * @return the question
	 */
	public ATGraph getQuestion();
	
	/**
	 * @param question the question to set
	 */
	public void setQuestion(ATGraph question);

	/**
	 * @return the textualQuestion
	 */
	public List<String> getTextualQuestion();
	
	/**
	 * @param textualQuestion the textualQuestion to set
	 */
	public void setTextualQuestion(List<String> textualQuestion);
	
	/**
	 * @return the actualAnswers
	 */
	public List<Map<String, String>> getActualAnswers();
		
	/**
	 * @return the newElementsInActualAnswers
	 */
	public List<List<String>> getNewElementsInActualAnswers();
	
	/**
	 * @return the languageOfActualAnswers
	 */
	public List<String> getLanguageOfActualAnswers();
	
	/**
	 * @return int
	 */
	public int sizeActualAnswers();
	
	/**
	 * @return an actualAnswer
	 */
	public Graph getActualAnswers(int index);
	
	/**
	 * @param int
	 * @param Graph   the potential answer to set.
	 */
	public void setActualAnswers(int index, Graph oneAnswer);
	
	/**
	 * @param index int
	 * @param language String the language of the answer 
	 * @param oneAnswer Map<String, String>   the actual answer to set.
	 * @param newElements List<String>   the new elements to set. 
	 */
	public void setActualAnswers(int index, String language, Map<String, String> oneAnswer, List<String> newElements);
	
	/**
	 * @param actualAnswers the actualAnswers to set
	 */
	public void setActualAnswers(List<Map<String, String>> actualAnswers);
	
	/**
	 * @param newElementsInActualAnswers the newElementsInActualAnswers to set
	 */
	public void setNewElementsInActualAnswers(List<List<String>> newElementsInActualAnswers);
	
	/**
	 * @param languageOfActualAnswers the languageOfActualAnswers to set
	 */
	public void setLanguageOfActualAnswers(List<String> languageOfActualAnswers);
	
	/**
	 * @return the potentialAnswers
	 */
	public List<Map<String, String>> getPotentialAnswers();
	
	/**
	 * @return the newElementsInPotentialAnswers
	 */
	public List<List<String>> getNewElementsInPotentialAnswers();
	
	/**
	 * @return the languageOfPotentialalAnswers
	 */
	public List<String> getLanguageOfPotentialAnswers();
	
	/**
	 * @return int
	 */
	public int sizePotentialAnswers();
	
	/**
	 * @return a potentialAnswer
	 */
	public Graph getPotentialAnswers(int index);
	
	/**
	 * @param index
	 * @param Graph   the potential answer to set.
	 */
	public void setPotentialAnswers(int index, Graph oneAnswer);
	
	/**
	 * @param index int
	 * @param language String the language of the answer 
	 * @param oneAnswer Map<String, String>   the actual answer to set.
	 * @param newElements List<String>   the new elements to set. 
	 */
	public void setPotentialAnswers(int index, String language, Map<String, String> oneAnswer, List<String> newElements);
	
	/**
	 * @param potentialAnswers the potentialAnswers to set
	 */
	public void setPotentialAnswers(List<Map<String, String>> potentialAnswers);
	
	/**
	 * @param newElementsInPotentialAnswers the newElementsInPotentialAnswers to set
	 */
	public void setNewElementsInPotentialAnswers(List<List<String>> newElementsInPotentialAnswers);
	
	/**
	 * @param languageOfPotentialAnswers the languageOfPotentialAnswers to set
	 */
	public void setLanguageOfPotentialAnswers(List<String> languageOfPotentialAnswers);
	
	/**
	 * Calculates the potential answers to the question.
	 */
	public void solvePotentialAnswers();

}