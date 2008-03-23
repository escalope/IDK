package atai.questions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * An answer to a question question from the Requirements Elicitation Guide (REG).
 * The answer gives the names and types of entitites and relations in the answer.
 * Besides, it also indicates the entitites that a relation connects.
 * @author ruben
 * 
 */

public class GenericAnswer implements Answer {
	
	/**
	 * Language of the answer.
	 */
	private String language;
	/**
	 * Ids of the entities in the answer.
	 */
	private List<String> entities;
	/**
	 * Pairs <id, type> for the entities in the answer.
	 */
	private Map<String, String> entityTypes;
	/**
	 * Ids for the relations in the answer.
	 */
	private List<String> relations;
	/**
	 * Pairs <id, type> for the relations in the answer.
	 */
	private Map<String, String> relationTypes;
	/**
	 * Pairs <relation id, role id> for the relations in the answer.
	 */
	private Map<String, List<String>> roles;
	/**
	 * Pairs <id, type> for the roles in the answer.
	 */
	private Map<String, String> roleTypes;
	/**
	 * Pairs <role id, entity id> for the players of roles in the answer.
	 */
	private Map<String, String> players;
	/**
	 * New elements in the answer.
	 */
	private List<String> newElements;

	

	
	
	/**
	 * Construtor.
	 */
	public GenericAnswer() {
		super();
		this.language = "";
		this.entities = new Vector<String>();
		this.entityTypes = new HashMap<String, String>();
		this.relations = new Vector<String>();
		this.relationTypes = new HashMap<String, String>();
		this.roles = new HashMap<String, List<String>>();
		this.roleTypes = new HashMap<String, String>();
		this.players = new HashMap<String, String>();
		this.newElements = new Vector<String>();
	}
	
	
	
	/**
	 * Constructor.
	 * @param language
	 * @param entityTypes
	 * @param relationTypes
	 * @param roles
	 * @param relationTypes
	 * @param players
	 * @param newElements  
	 */
	public GenericAnswer(String language,
			Map<String, String> entityTypes,
			Map<String, String> relationTypes,
			Map<String,	List<String>> roles, Map<String, String> roleTypes,
			Map<String, String> players, List<String> newElements) {
		super();
		this.language = language;
		this.entities = new Vector<String>(entityTypes.keySet());
		this.entityTypes = entityTypes;
		this.relations = new Vector<String>(relationTypes.keySet());
		this.relationTypes = relationTypes;
		this.roles = roles;
		this.roleTypes = roleTypes;
		this.players = players;
		this.newElements = newElements;
	}
	
	
	
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	
	
	
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;		
	}
	
	
	
	/**
	 * @return the entities
	 */
	public List<String> getEntities() {
		return entities;
	}

	

	public Map<String, String> getEntityTypes() {
		return this.entityTypes;
	}
	
	
	
	/**
	 * @param entityTypes the entityTypes to set
	 */
	public void setEntityTypes(Map<String, String> entityTypes) {
		this.entities = new Vector<String>(entityTypes.keySet());
		this.entityTypes = entityTypes;
	}
	
	
	
	/**
	 * @return the relations
	 */
	public List<String> getRelations() {
		return relations;
	}
	

	
	public Map<String, String> getRelationTypes() {
		return this.relationTypes;
	}

	
	
	/**
	 * @param relationTypes the relationTypes to set
	 */
	public void setRelationTypes(Map<String, String> relationTypes) {
		this.relations = new Vector<String>(relationTypes.keySet());
		this.relationTypes = relationTypes;
	}

	
	
	/**
	 * @return the roles
	 */
	public Map<String, List<String>> getRoles() {
		return roles;
	}
	
	
	
	public Map<String, String> getRoleTypes() {
		return roleTypes;
	}
	
	
	
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Map<String, List<String>> roles) {
		this.roles = roles;
	}
	
	
	/**
	 * @param roles the roles to set
	 */
	public void setRoleTypes(Map<String, String> roleTypes) {
		this.roleTypes = roleTypes;
	}


	
	/**
	 * @return the players as pairs <role id, player id>
	 */
	public Map<String, String> getPlayers() {
		return players;
	}
	
	
	
	/**
	 * @param players the players to set
	 */
	public void setPlayers(Map<String, String> players) {
		this.players = players;
	}
	
	
	
	public String getPlayer(String relation, String role) {
		return players.get(role);
	}
	
	
	
	public void setPlayer(String relation, String role, String player) {
		this.players.put(role, player);
	}
	
	
	
	/**
	 * @return the elements that are new in this answer.
	 */
	public List<String> getNewElements() {
		return newElements;
	}

	
	
	/**
	 * @return the elements that are new in this answer.
	 */
	public void setNewElements(List<String> newElements) {
		this.newElements = newElements;
	}
	
	
	
	/**
	 * @return if the elememt is new.
	 */
	public boolean isNewElement(String element) {
		return this.newElements.contains(element);
	}
	
	
	
	public void addNewElement(String element) {
		if (!this.isNewElement(element))
			this.newElements.add(element);
	}
	
	
	
	public void removeNewElement(String element) {
		this.newElements.remove(element);
	}
	
	
	
	/**
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clearEntities() {
		entities.clear();
		entityTypes.clear();
	}
	
	
	
	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public String putEntity(String key, String value) {
		entities.add(key);
		return entityTypes.put(key, value);
	}
	
	
	
	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public String removeEntity(String key) {
		entities.remove(key);
		return entityTypes.remove(key);
	}
	
	
	
	/**
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clearRelations() {
		relations.clear();
		relationTypes.clear();
	}
	
	
	
	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public String putRelation(String key, String value) {
		relations.add(key);
		return relationTypes.put(key, value);
	}
	
	
	
	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public String removeRelation(String key) {
		relations.remove(key);
		return relationTypes.remove(key);
	}


	
	/**
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clearRoles() {
		roles.clear();
		roleTypes.clear();
		players.clear();
	}
	
	
	
	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public List<String> putRole(String relation, String key, String value) {
		List<String> relationRoles = roles.get(relation);
		if (relationRoles == null)
			relationRoles = new Vector<String>();
		relationRoles.add(key);
		roles.put(relation, relationRoles);
		roleTypes.put(key, value);
		return relationRoles;
	}
	
	
	
	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public List<String> removeRole(String relation, String key) {
		List<String> relationRoles = roles.get(relation);
		if (relationRoles != null)
			relationRoles.remove(key);
		roles.put(relation, relationRoles);
		roleTypes.remove(key);
		players.remove(key);
		return relationRoles;
	}

}
