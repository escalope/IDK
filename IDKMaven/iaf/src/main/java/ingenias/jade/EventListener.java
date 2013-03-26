/*
Copyright (C) 2012 Jorge Gomez Sanz

This file is part of INGENIAS Agent Framework, an agent infrastructure linked
to the INGENIAS Development Kit, and availabe at http://ingenias.sourceforge.net. 

INGENIAS Agent Framework is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

INGENIAS Agent Framework is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with INGENIAS Agent Framework; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 */
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
