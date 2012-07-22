
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

public class GRASIASpecification extends Specification {


  public ingenias.editor.entities.InteractionModelModelEntity ModelThatContainsSpecification;





  public GRASIASpecification(String id) {
    super(id);
    this.setHelpDesc("A description of an interaction using GRASIA elements. This description allows to talk about the technology used to transfer information from one agent to another, refer to the mental conditions that must meet initator and collaborators<br>    at each step, what tasks will be executed and when, and what is the execution order of the different communication acts.");
    this.setHelpRecom("");
  }


      public InteractionModelModelEntity getModelThatContainsSpecification(){
        return ModelThatContainsSpecification;
      }
       public void setModelThatContainsSpecification(InteractionModelModelEntity
					ModelThatContainsSpecification){
        this.ModelThatContainsSpecification=ModelThatContainsSpecification;
      }





public void fromMap(Map ht){
super.fromMap(ht);



}
public void toMap(Map ht){
super.toMap(ht);


}

public String toString(){
if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();
}

}
