

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


package ingenias.jade.components;

import java.util.*;
import ingenias.jade.exception.*;
import ingenias.jade.comm.*;
import ingenias.jade.mental.*;
import ingenias.editor.entities.*;



public class PayTicketTask extends Task{

 public PayTicketTask(String id){
  super(id,"PayTicket");
 }



 public void execute() throws TaskException{


        Pay_the_money  eiPay_the_money=(Pay_the_money)this.getFirstInputOfType("Pay_the_money");             





			
        VirtualTerminalApp eaVirtualTerminal=(VirtualTerminalApp)this.getApplication("VirtualTerminal");





  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		Transaction_payment outputsdefaultTransaction_payment=
			(Transaction_payment)
				outputsdefault.getEntityByType("Transaction_payment");
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:PayTicket code <--- DO NOT REMOVE THIS	
//REPLACE THIS COMMENT WITH YOUR CODE
//#end_node:PayTicket code <--- DO NOT REMOVE THIS

 }
 
}

 