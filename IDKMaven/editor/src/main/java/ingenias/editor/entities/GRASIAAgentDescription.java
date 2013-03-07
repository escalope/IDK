
/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz
 * 
 * 
 * This file is part of the INGENME tool. INGENME is an open source meta-editor
 * which produces customized editors for user-defined modeling languages
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3 of the License
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 **/

package ingenias.editor.entities;

import java.util.*;
import ingenias.editor.TypedVector;

public class GRASIAAgentDescription extends AgentDescription {


  public ingenias.editor.entities.AgentModelModelEntity DescriptionWithAgentModel;





  public GRASIAAgentDescription(String id) {
    super(id);
    this.setHelpDesc("Contains a reference to an agent model. With this model you describe conditions of goal satisfaction or failure, and interaction collaboration. Usually, this is achieved associating mental states to an instance of ConcreteAgent or<br>    AutonomousEntityQuery. This instance should be understood as the executor of the task or the performer or colaborator in an interaction unit. You also can use common associations in these diagrams to represent abilities required or other<br>    qualities.");
    this.setHelpRecom("");
  }


      public AgentModelModelEntity getDescriptionWithAgentModel(){
        return DescriptionWithAgentModel;
      }
       public void setDescriptionWithAgentModel(AgentModelModelEntity
					DescriptionWithAgentModel){
        this.DescriptionWithAgentModel=DescriptionWithAgentModel;
      }





public void fromMap(Map ht){
super.fromMap(ht);



}
public void toMap(Map ht){
super.toMap(ht);


}

public String toString(){
/*if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();*/
return ""+getId();
}

}
