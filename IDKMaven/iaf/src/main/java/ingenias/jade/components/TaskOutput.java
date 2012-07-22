/*
    Copyright (C) 2007 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 

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
package ingenias.jade.components;

import ingenias.editor.entities.MentalEntity;
import ingenias.editor.entities.RuntimeConversation;

import java.util.HashSet;
import java.util.Vector;


public class TaskOutput extends Vector<OutputEntity>{
	private String id=null; 

	public TaskOutput(String id){
		this.id=id;
	}
	
	public void remove(MentalEntity mt){
	 removeEntity(mt);	
	}
	
	public void removeEntity(MentalEntity mt){
		OutputEntity toRemove=null;
		for (OutputEntity ent:this){
			if (ent.me.getId().equals(mt.getId())){
				toRemove=ent;
			}
		}
		if (toRemove!=null)
			super.remove(toRemove);
	}
	
	public HashSet<MentalEntity> getConsumedEntities() {
		HashSet<MentalEntity> consumed=new  HashSet<MentalEntity>();
		for (OutputEntity ent:this){
			if (ent.op.equals(TaskOperations.Consume)){
				consumed.add(ent.me);
			}
		}
		return consumed;
	}

	public HashSet<MentalEntity> getNewEntitiesMS() {
		HashSet<MentalEntity> consumed=new  HashSet<MentalEntity>();
		for (OutputEntity ent:this){
			if (ent.op.equals(TaskOperations.CreateMS)){
				consumed.add(ent.me);
			}
		}
		return consumed;
	}
	
	public HashSet<MentalEntity> getNewEntitiesWF() {
		HashSet<MentalEntity> consumed=new  HashSet<MentalEntity>();
		for (OutputEntity ent:this){
			if (ent.op.equals(TaskOperations.CreateWF)){
				consumed.add(ent.me);
			}
		}
		return consumed;
	}

	public MentalEntity getEntityByType(String type) {
		MentalEntity result=null;
		for (OutputEntity ent:this){
			if (ent.me.getType().equals(type)){
				result=ent.me;
			}
			if ((ent.me instanceof RuntimeConversation) &&
                                (((RuntimeConversation)ent.me).getInteraction().getId().equalsIgnoreCase(type))){
				result=ent.me;
			}
		}
		return result;
	}

	public Object getID() {
		
		return id;
	}
	
	public String toString(){
		StringBuffer sb=new StringBuffer();
		for (OutputEntity oe:this){
			sb.append(oe.me.getId()+":"+oe.me.getType()+",");
		}
		return sb.toString();
	}
	
	
	
	

}
