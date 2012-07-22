/*
    Copyright (C) 2005 Jorge Gomez Sanz

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

package ingenias.jade.comm;
import ingenias.editor.entities.RuntimeConversation;

/**
 * It represents an ongoing conversation between two or more agents. It encapsulates all the data
 * needed to manage a conversation
 * <ul>
 * <li> A StateBehavior containing the state machine of the protocol
 * <li> The name of the role which the agent is playing in the conversation
 * <li> The conversation id
 * <li> The control class to determine when to send information and how to receive it
 * <li> The mental entity representing the conversation
 * </ul>
 * 
 * @author jj
 *
 */
public class ActiveConversation {

 private StateBehavior sb;
 private String role;
 private String cid;
 private DefaultCommControl dcc;
 private RuntimeConversation conv;
 
 public ActiveConversation(StateBehavior sb, DefaultCommControl dcc, RuntimeConversation conv) {
	super();
	if (sb==null)
		throw new RuntimeException("The statebehavior supplied is null");
	this.sb = sb;
	this.role = conv.getPlayedRole();
	this.cid = conv.getConversationID();
	this.dcc=dcc;
	this.conv=conv;
}

public String getCid() {
	return cid;
}

public String getRole() {
	return role;
}

public StateBehavior getSb() {
	return sb;
}

public DefaultCommControl getDcc() {
	return dcc;
}

public RuntimeConversation getConv() {
	return conv;
}


}
