
/*
    Copyright (C) 2002 Jorge Gomez Sanz, Ruben Fuentes

    This file is part of INGENIAS IDE, a support tool for the INGENIAS
    methodology, availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net

    INGENIAS IDE is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS IDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS IDE; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/

package ingenias.editor.entities;

import java.util.*;
import ingenias.editor.TypedVector;

public class DeploymentUnitByTypeWithInitMS extends DeploymentUnitByType {


  public ingenias.editor.entities.AgentModelModelEntity InitialState;





  public DeploymentUnitByTypeWithInitMS(String id) {
    super(id);
    this.setHelpDesc("<br>				A deploy unit is an entity that defines how many instances of agents will exist in the final system<br>			");
    this.setHelpRecom("");
  }


      public AgentModelModelEntity getInitialState(){
        return InitialState;
      }
       public void setInitialState(AgentModelModelEntity
					InitialState){
        this.InitialState=InitialState;
      }





public void fromMap(Map ht){
super.fromMap(ht);



}
public void toMap(Map ht){
super.toMap(ht);

}

public String toString(){
if (this.getAgentTypeDeployed()==null ||
    this.getAgentTypeDeployed().toString().equals(""))
 return "Please, define the value of field AgentTypeDeployed";
else
 return this.getAgentTypeDeployed().toString();
}

}
