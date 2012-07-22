
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

public class PROLOGAgentDescription extends AgentDescription {


  public java.lang.String PROLOGDescription;





  public PROLOGAgentDescription(String id) {
    super(id);
    this.setHelpDesc("Provides an prolog based description of an agent. There is no syntax check here. This means that we assume that you write down prolog code. This kind of descriptions is used to represent conditions of goal satisfaction or failure, and<br>    interaction collaboration.");
    this.setHelpRecom("");
  }


      public java.lang.String getPROLOGDescription(){
        return PROLOGDescription;
      }
       public void setPROLOGDescription(java.lang.String
					PROLOGDescription){
        this.PROLOGDescription=PROLOGDescription;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("PROLOGDescription")!=null && ht.get("PROLOGDescription").equals(""))
  this.setPROLOGDescription(null);
 else
  if (ht.get("PROLOGDescription")!=null)
   this.setPROLOGDescription(new java.lang.String(ht.get("PROLOGDescription").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getPROLOGDescription() instanceof String)
 if (this.getPROLOGDescription()!=null)
 	ht.put("PROLOGDescription",this.getPROLOGDescription().toString());
 else	
 	ht.put("PROLOGDescription","");


}

public String toString(){
if (this.getId()==null ||
    this.getId().toString().equals(""))
 return "Please, define the value of field Id";
else
 return this.getId().toString();
}

}
