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

public class OutputEntity {
	public OutputEntity(MentalEntity entity, TaskOperations op) {
		if (entity==null || op== null)
			throw new RuntimeException("Null values on initializing OutputEntity");
		this.me = entity;
		this.op = op;
	}

	public MentalEntity me;

	public TaskOperations op;
	
	public boolean equals(Object obj){
		if (obj instanceof OutputEntity){
			OutputEntity target=(OutputEntity)obj;
			return target.me.equals(this.me) && target.op.equals(this.op);
		}
		return super.equals(obj);
	}
	
}
