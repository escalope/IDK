package atai.questions;

import java.util.List;
import java.util.Map;

/**
 * A graph in the Requirements Elicitation Guide (REG).
 * This interface is used to represent questions and answers in the REG. 
 * It gives the names and types of entitites and relations in a graph.
 * Besides, it also indicates the entitites that a relation connects.
 * 
 * @author ruben
 */

public interface Graph {

	/**
	 * Role origin in a relationship.
	 */
	static public final String ROLE_ORIGIN = "origin";
	/**
	 * Role target in a relationship.
	 */
	static public final String ROLE_TARGET = "target";
	
	
	/**
	 * @return the language
	 */
	public String getLanguage();	
	
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language);
	
	/**
	 * @return the entities
	 */
	public List<String> getEntities();	

	/**
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clearEntities();
	
	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public String putEntity(String key, String value);
	
	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public String removeEntity(String key);
	
	/**
	 * @return the entity types
	 */
	public Map<String, String> getEntityTypes();
	
	/**
	 * @param entityTypes the entityTypes to set
	 */
	public void setEntityTypes(Map<String, String> entityTypes);
	
	/**
	 * @return the relations
	 */
	public List<String> getRelations();
	
	/**
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clearRelations();
	
	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public String putRelation(String key, String value);
	
	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public String removeRelation(String key);
	
	/**
	 * @return the relation types
	 */	
	public Map<String, String> getRelationTypes();
	
	/**
	 * @param relationTypes the relationTypes to set
	 */
	public void setRelationTypes(Map<String, String> relationTypes);
	
	/**
	 * @return the roles of relations.
	 */
	public Map<String, List<String>> getRoles();
	
	/**
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clearRoles();
	
	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public List<String> putRole(String relation, String key, String value);
	
	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public List<String> removeRole(String relation, String key);
	
	/**
	 * @return the role types.
	 */
	public Map<String, String> getRoleTypes();
		
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Map<String, List<String>> roles);
	
	/**
	 * @param roleTypes the role types to set
	 */
	public void setRoleTypes(Map<String, String> roleTypes);	
	
	/**
	 * @return the players as pairs <role id, player id>
	 */
	public Map<String, String> getPlayers();	
	
	/**
	 * @param players the players to set
	 */
	public void setPlayers(Map<String, String> players);
		
	/**
	 * 
	 * @param relation
	 * @param role
	 */
	public String getPlayer(String relation, String role);
		
	/**
	 * 
	 * @param relation
	 * @param role
	 * @param player 
	 */
	public void setPlayer(String relation, String role, String player);
	
	/**
	 * @return the elements that are variables.
	 */
	public List<String> getVariables();	
	
	/**
	 * param variableElements  the elements that are variables to set.
	 */
	public void setVariables(List<String> variables);
		
	/**
	 * 
	 * @param element
	 * @return if the elememt is a variable.
	 */
	public boolean isVariable(String element);
		
	/**
	 * 
	 * @param variable   the variable to set
	 */	
	public void addVariable(String variable);
	
	/**
	 * 
	 * @param variable   the variable to remove
	 */
	public void removeVariable(String variable);

}