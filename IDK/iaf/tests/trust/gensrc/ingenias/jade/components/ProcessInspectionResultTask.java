

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



public class ProcessInspectionResultTask extends Task{

 public ProcessInspectionResultTask(String id){
  super(id,"ProcessInspectionResult");
 }



 public void execute() throws TaskException{


        GradoCalidadFuente  eiGradoCalidadFuente=(GradoCalidadFuente)this.getFirstInputOfType("GradoCalidadFuente");             

        ValoresConfianzaReputacion  eiValoresConfianzaReputacion=(ValoresConfianzaReputacion)this.getFirstInputOfType("ValoresConfianzaReputacion");             










  		Vector<TaskOutput> outputs = this.getOutputs();
  		TaskOutput defaultOutput= outputs.firstElement();
  		
  		  	
  		TaskOutput	outputsdefault=findOutputAlternative("default",
  																			outputs);
  		
		
		
		
        YellowPages yp=null; // only available for initiators of interactions


//#start_node:ProcesarResultadoInspeccionTaskImpl <--- DO NOT REMOVE THIS	

        System.out.println(this.getClass().getName()+" << ");
        trabajomaster.demo.supervisor.Tareas.procesarResultadoInspeccionTask(this.getAgentID(),eiGradoCalidadFuente,
                eiValoresConfianzaReputacion,
                outputsdefault);
        System.out.println(this.getClass().getName()+" >> ");

//#end_node:ProcesarResultadoInspeccionTaskImpl <--- DO NOT REMOVE THIS

 }
 
}

 