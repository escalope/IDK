package ingenias.jade;

public interface EventListener {

	public void executedTask(String agentid, String agentType, String taskid, String tasktype, String[] inputEntityIds, String[] inputEntityType, String[] outputEntityIds, String[] outputEntityType);
	public void abortedTask(String agentid, String agentType, String taskid, String tasktype, String[] missingInputType);
	public void scheduledTask(String agentid, String agentType, String taskid, String tasktype, String[] inputEntityIds, String[] inputEntityType);
	public void addedNewEntityToMS(String agentid, String agentType,String entityId,String entityType );
	public void removeEntityFromMS(String agentid, String agentType,String entityId,String entityType );
	public void addedNewEntityToConversation(String agentid, String agentType,String entityId,String entityType, String convId, String interactionType );
	public void removeEntityFromConversation(String agentid, String agentType,String entityId,String entityType, String convId, String interactionType );
	
}
