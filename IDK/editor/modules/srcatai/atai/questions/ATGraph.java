package atai.questions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * A graph in the Requirements Elicitation Guide (REG) described in
 * terms of AT concepts.
 * It gives the names and types of entitites and relations in a graph.
 * Besides, it also indicates the entitites that a relation connects.
 * The class considers names and types of elements as AT elements without
 * further restrictions.
 * 
 * @see ATEntity
 * @see ATRelation
 * 
 * @author ruben
 */

public class ATGraph implements Graph {
	
	/**
	 * Constants.
	 */
	static final public String LANGUAGE_ATGRAPH = "AT";
	
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
	private Map<String, ATEntity> entityTypes;
	/**
	 * Ids for the relations in the answer.
	 */
	private List<String> relations;
	/**
	 * Pairs <id, type> for the relations in the answer.
	 */
	private Map<String, ATRelation> relationTypes;
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
	 * Variables in the graph.
	 */
	private List<String> variables;

	

	
	
	/**
	 * Construtor.
	 */
	public ATGraph() {
		super();
		this.language = "";
		this.entities = new Vector<String>();
		this.entityTypes = new HashMap<String, ATEntity>();
		this.relations = new Vector<String>();
		this.relationTypes = new HashMap<String, ATRelation>();
		this.roles = new HashMap<String, List<String>>();
		this.roleTypes = new HashMap<String, String>();
		this.players = new HashMap<String, String>();
		this.variables = new Vector<String>();
	}
	
	
	
	/**
	 * Constructor.
	 * @param language 
	 * @param entityTypes
	 * @param relationTypes
	 * @param roles
	 * @param relationTypes
	 * @param players
	 * @param variables  
	 */
	public ATGraph(String language,
			Map<String, ATEntity> entityTypes,
			Map<String, ATRelation> relationTypes,
			Map<String,	List<String>> roles, Map<String, String> roleTypes,
			Map<String, String> players, List<String> variables) {
		super();
		this.language = language;
		this.entities = new Vector<String>(entityTypes.keySet());
		this.entityTypes = entityTypes;
		this.relations = new Vector<String>(relationTypes.keySet());
		this.relationTypes = relationTypes;
		this.roles = roles;
		this.roleTypes = roleTypes;
		this.players = players;
		this.variables = variables;
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
	public ATEntity putATEntity(String key, ATEntity value) {
		entities.add(key);
		return entityTypes.put(key, value);
	}
	
	
	
	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public String putEntity(String key, String value) {
		ATEntity atValue = ATEntity.valueOf(value);
		return this.putATEntity(key, atValue).toString();
	}
		
	
	
	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public ATEntity removeATEntity(String key) {
		entities.remove(key);
		return entityTypes.remove(key);
	}
	
	
	
	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public String removeEntity(String key) {
		return this.removeEntity(key).toString();
	}
	
	
	
	public Map<String, ATEntity> getATEntityTypes() {
		return this.entityTypes;
	}
	
	
	
	/**
	 * @return the entity types
	 */
	public Map<String, String> getEntityTypes() {
		Map<String, String> result = new HashMap<String, String>();
		for (String key : this.getATEntityTypes().keySet())
			result.put(key, this.getATEntityTypes().get(key).toString());
		return result;
	}
	
	
	
	/**
	 * @param entityTypes the entityTypes to set
	 */
	public void setATEntityTypes(Map<String, ATEntity> entityTypes) {
		this.entities = new Vector<String>(entityTypes.keySet());
		this.entityTypes = entityTypes;
	}
	
	
	
	/**
	 * @param entityTypes the entityTypes to set
	 */
	public void setEntityTypes(Map<String, String> entityTypes) {
		Map<String, ATEntity> types = new HashMap<String, ATEntity>();
		for (String key : entityTypes.keySet())
			types.put(key, ATEntity.valueOf( entityTypes.get(key) ));
		this.setATEntityTypes(types);
	}
		
	
	
	/**
	 * @return the relations
	 */
	public List<String> getRelations() {
		return relations;
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
	public ATRelation putATRelation(String key, ATRelation value) {
		relations.add(key);
		return relationTypes.put(key, value);
	}
	
	
	
	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public String putRelation(String key, String value) {
		ATRelation atValue = ATRelation.valueOf(value);
		return this.putATRelation(key, atValue).toString();
	}
	
	
	
	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public ATRelation removeATRelation(String key) {
		relations.remove(key);
		return relationTypes.remove(key);
	}
	
	
	
	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public String removeRelation(String key) {
		return this.removeATRelation(key).toString();
	}


	
	public Map<String, ATRelation> getATRelationTypes() {
		return this.relationTypes;
	}
	

	
	/**
	 * @return the relation types
	 */
	public Map<String, String> getRelationTypes() {
		Map<String, String> result = new HashMap<String, String>();
		for (String key : this.getATRelationTypes().keySet())
			result.put(key, this.getATRelationTypes().get(key).toString());
		return result;
	}

	
	
	/**
	 * @param relationTypes the relationTypes to set
	 */
	public void setATRelationTypes(Map<String, ATRelation> relationTypes) {
		this.relations = new Vector<String>(relationTypes.keySet());
		this.relationTypes = relationTypes;
	}

	
	
	/**
	 * @param relationTypes the relationTypes to set
	 */
	public void setRelationTypes(Map<String, String> relationTypes) {
		Map<String, ATRelation> types = new HashMap<String, ATRelation>();
		for (String key : relationTypes.keySet())
			types.put(key, ATRelation.valueOf( relationTypes.get(key) ));
		this.setATRelationTypes(types);
	}
	
	
	
	/**
	 * @return the roles of relations.
	 */
	public Map<String, List<String>> getRoles() {
		return roles;
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
	
	
	
	/**
	 * @return the role types.
	 */
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
	 * @return the players as pairs <role id, player id>
	 */
	public void setPlayers(Map<String, String> players) {
		this.players = players;
	}

	
	
	/**
	 * 
	 * @param relation
	 * @param role
	 */
	public String getPlayer(String relation, String role) {
		return players.get(role);
	}

	
	
	/**
	 * 
	 * @param relation
	 * @param role
	 * @param player 
	 */
	public void setPlayer(String relation, String role, String player) {
		this.players.put(role, player);
	}
			
	
	
	/**
	 * @return the elements that are variable in this graph.
	 */
	public List<String> getVariables() {
		return variables;
	}

	
	
	/**
	 * @return the elements that are variable in this graph.
	 */
	public void setVariables(List<String> variables) {
		this.variables = variables;
	}
	
	
	
	/**
	 * @return if the elememt is variable.
	 */
	public boolean isVariable(String element) {
		return this.variables.contains(element);
	}
	
	
	
	/**
	 * 
	 * @param variable   the variable to set
	 */
	public void addVariable(String element) {
		if (!this.isVariable(element))
			this.variables.add(element);
	}
	
	
	
	/**
	 * 
	 * @param variable   the variable to remove
	 */
	public void removeVariable(String variable) {
		this.variables.remove(variable);
	}
	
}

