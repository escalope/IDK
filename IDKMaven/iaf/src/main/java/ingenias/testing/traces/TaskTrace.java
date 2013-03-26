/*
    Copyright (C) 2013 Jorge Gomez Sanz

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
package ingenias.testing.traces;

import java.util.Date;

import ingenias.jade.components.Task;

public class TaskTrace {
	private String aid;
	private Task task;
	private Date timestamp;

	public TaskTrace(String aid, Task task) {
		super();
		this.aid = aid;
		this.task = task;
		this.timestamp=new Date();
	}

	public String getAid() {
		return aid;
	}

	public Task getTask() {
		return task;
	}

	public String toString(){
		return aid+":"+task.getID()+":"+task.getType()+":"+task.getInputs();
	}

	public long getTimeStamp() {
		return timestamp.getTime();
	}
}