
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

public class WFTest extends Test {


  public java.lang.String NeverClaim;

  public java.lang.String Repetition;

  public java.lang.String MaxTimePerCycle;

  public java.lang.String TestDuration;



  public TypedVector TestStates=new TypedVector(ingenias.editor.entities.WFTestState.class);



  public WFTest(String id) {
    super(id);
    this.setHelpDesc("A deploy unit is an entity that defines how many instances of agents will exist in the final system");
    this.setHelpRecom("");
  }


      public java.lang.String getNeverClaim(){
        return NeverClaim;
      }
       public void setNeverClaim(java.lang.String
					NeverClaim){
        this.NeverClaim=NeverClaim;
      }


      public java.lang.String getRepetition(){
        return Repetition;
      }
       public void setRepetition(java.lang.String
					Repetition){
        this.Repetition=Repetition;
      }


      public java.lang.String getMaxTimePerCycle(){
        return MaxTimePerCycle;
      }
       public void setMaxTimePerCycle(java.lang.String
					MaxTimePerCycle){
        this.MaxTimePerCycle=MaxTimePerCycle;
      }


      public java.lang.String getTestDuration(){
        return TestDuration;
      }
       public void setTestDuration(java.lang.String
					TestDuration){
        this.TestDuration=TestDuration;
      }




  public void setTestStates(TypedVector tv){
    this.TestStates=tv;
  }

  public String getTestStates(){
   return TestStates.toString();
  }

  public Class getTestStatesType(){
   return TestStates.getType();
  }
  public void addTestStates(ingenias.editor.entities.WFTestState element){
   this.TestStates.add(element);
  }

  public void insertTestStatesAt(int pos,ingenias.editor.entities.WFTestState element){
   this.TestStates.insert(element,pos);
  }

  public int containsTestStates(ingenias.editor.entities.WFTestState element){
   return this.TestStates.indexOf(element);
  }


  public Enumeration getTestStatesElements(){
   return this.TestStates.elements();
  }

  public void removeTestStatesElement(String id){
    Enumeration enumeration=this.getTestStatesElements();
    ingenias.editor.entities.Entity found=null;
    while (enumeration.hasMoreElements() && found==null){
     ingenias.editor.entities.Entity ent=(ingenias.editor.entities.Entity)enumeration.nextElement();
     if (ent.getId().equalsIgnoreCase(id))
      found=ent;
    }
    if (found!=null)
     this.TestStates.remove(found);
  }



public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("NeverClaim")!=null && ht.get("NeverClaim").equals(""))
  this.setNeverClaim(null);
 else
  if (ht.get("NeverClaim")!=null)
   this.setNeverClaim(new java.lang.String(ht.get("NeverClaim").toString()));

 if (ht.get("Repetition")!=null && ht.get("Repetition").equals(""))
  this.setRepetition(null);
 else
  if (ht.get("Repetition")!=null)
   this.setRepetition(new java.lang.String(ht.get("Repetition").toString()));

 if (ht.get("MaxTimePerCycle")!=null && ht.get("MaxTimePerCycle").equals(""))
  this.setMaxTimePerCycle(null);
 else
  if (ht.get("MaxTimePerCycle")!=null)
   this.setMaxTimePerCycle(new java.lang.String(ht.get("MaxTimePerCycle").toString()));

 if (ht.get("TestDuration")!=null && ht.get("TestDuration").equals(""))
  this.setTestDuration(null);
 else
  if (ht.get("TestDuration")!=null)
   this.setTestDuration(new java.lang.String(ht.get("TestDuration").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getNeverClaim() instanceof String)
 if (this.getNeverClaim()!=null)
 	ht.put("NeverClaim",this.getNeverClaim().toString());
 else	
 	ht.put("NeverClaim","");

//if (this.getRepetition() instanceof String)
 if (this.getRepetition()!=null)
 	ht.put("Repetition",this.getRepetition().toString());
 else	
 	ht.put("Repetition","");

//if (this.getMaxTimePerCycle() instanceof String)
 if (this.getMaxTimePerCycle()!=null)
 	ht.put("MaxTimePerCycle",this.getMaxTimePerCycle().toString());
 else	
 	ht.put("MaxTimePerCycle","");

//if (this.getTestDuration() instanceof String)
 if (this.getTestDuration()!=null)
 	ht.put("TestDuration",this.getTestDuration().toString());
 else	
 	ht.put("TestDuration","");


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
