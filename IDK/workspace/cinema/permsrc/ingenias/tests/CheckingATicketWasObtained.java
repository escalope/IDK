

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


package ingenias.tests;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import jade.core.*;
import jade.wrapper.StaleProxyException;
import jade.wrapper.State;
import ingenias.editor.entities.MentalEntity;
import ingenias.exception.InvalidEntity;
import ingenias.exception.TimeOut;
import ingenias.jade.MentalStateManager;
import ingenias.jade.MentalStateProcessor;
import ingenias.jade.graphics.MainInteractionManager;
import ingenias.jade.mental.*;
import ingenias.testing.*;
import ingenias.jade.IAFProperties;


public class CheckingATicketWasObtained {
	@Test
	public void testDemo() throws TimeOut, InvalidEntity{			
		
IAFProperties.setGarbageCollectionEnabled(false);
		
MentalStateManager msmB = 
	 MSMRepository.getInstance().waitFor("BuyerAgent_0");
MentalStateProcessor mspB = 
	MSPRepository.getInstance().waitFor("BuyerAgent_0");
TestUtils.waitForAgentInitialised(mspB);

MentalStateManager msmIA = 
	 MSMRepository.getInstance().waitFor("InterfaceAgent_0");
MentalStateProcessor mspIA = 
	MSPRepository.getInstance().waitFor("InterfaceAgent_0");

TestUtils.waitForAgentInitialised(mspIA);

User_wants_to_watch_a_movie nevent= new User_wants_to_watch_a_movie();
		
msmIA.addMentalEntity(nevent); // to initiate the ticket selling
Vector<MentalEntity> tickets = msmB
.getMentalEntityByType("Ticket_data");
		
assertTrue("There should be no any Ticket_data entities " +
		"and there were "+tickets,
		tickets.size() == 0); // check

MainInteractionManager.goAutomatic(); 
TestUtils.doNothing(2000); // waits for 2 seconds
					
tickets = msmB.getMentalEntityByType("Ticket_data");

assertTrue("There should be exactly one Ticket_data " +
		"and there were "+tickets,
		tickets.size() == 1); 
		
	}
}

