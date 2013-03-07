
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

public class AOPMentalStatePattern extends SymbolicMentalStatePattern {


  public java.lang.String AOPExpression;





  public AOPMentalStatePattern(String id) {
    super(id);
    this.setHelpDesc("The mental state of the agent is expressed the Agent0 language. This language in described in the Agent Oriented Programming paper from Shoham. No syntax is verified.");
    this.setHelpRecom("");
  }


      public java.lang.String getAOPExpression(){
        return AOPExpression;
      }
       public void setAOPExpression(java.lang.String
					AOPExpression){
        this.AOPExpression=AOPExpression;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("AOPExpression")!=null && ht.get("AOPExpression").equals(""))
  this.setAOPExpression(null);
 else
  if (ht.get("AOPExpression")!=null)
   this.setAOPExpression(new java.lang.String(ht.get("AOPExpression").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getAOPExpression() instanceof String)
 if (this.getAOPExpression()!=null)
 	ht.put("AOPExpression",this.getAOPExpression().toString());
 else	
 	ht.put("AOPExpression","");


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
