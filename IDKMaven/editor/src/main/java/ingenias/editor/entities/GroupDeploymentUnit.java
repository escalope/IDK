
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

public class GroupDeploymentUnit extends INGENIASObject {


  public java.lang.String InstanceName;

  public ingenias.editor.entities.OrganizationGroup InstGroup;





  public GroupDeploymentUnit(String id) {
    super(id);
    this.setHelpDesc("A deploy unit is an entity that defines how many instances of agents will exist in the final system");
    this.setHelpRecom("");
  }


      public java.lang.String getInstanceName(){
        return InstanceName;
      }
       public void setInstanceName(java.lang.String
					InstanceName){
        this.InstanceName=InstanceName;
      }


      public ingenias.editor.entities.OrganizationGroup getInstGroup(){
        return InstGroup;
      }
       public void setInstGroup(ingenias.editor.entities.OrganizationGroup
					InstGroup){
        this.InstGroup=InstGroup;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("InstanceName")!=null && ht.get("InstanceName").equals(""))
  this.setInstanceName(null);
 else
  if (ht.get("InstanceName")!=null)
   this.setInstanceName(new java.lang.String(ht.get("InstanceName").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getInstanceName() instanceof String)
 if (this.getInstanceName()!=null)
 	ht.put("InstanceName",this.getInstanceName().toString());
 else	
 	ht.put("InstanceName","");


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
