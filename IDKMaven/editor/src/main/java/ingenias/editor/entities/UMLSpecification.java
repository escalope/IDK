
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

public class UMLSpecification extends Specification {


  public ingenias.editor.entities.InteractionModelModelEntity ModelThatContainsSpecification;





  public UMLSpecification(String id) {
    super(id);
    this.setHelpDesc("A specification of an interaction that uses UML concepts. In INGENIAS it is assumed that the user will apply collaboration diagrams as specification mechanism");
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
