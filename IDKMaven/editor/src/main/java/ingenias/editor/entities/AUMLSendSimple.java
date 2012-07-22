

/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz over original code from Ruben Fuentes
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

public class AUMLSendSimple extends NAryEdgeEntity {


  public java.lang.String SpeechAct;




  public AUMLSendSimple(String id) {
    super(id);
    ModelEntity em=null;
  }






  
      public java.lang.String getSpeechAct(){
        return SpeechAct;
      }
   public void setSpeechAct(java.lang.String
					SpeechAct){
        this.SpeechAct=SpeechAct;
      }



public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("SpeechAct")!=null && ht.get("SpeechAct").equals(""))
  this.setSpeechAct(null);
 else
  if (ht.get("SpeechAct")!=null)
   this.setSpeechAct(new java.lang.String(ht.get("SpeechAct").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getSpeechAct() instanceof String)
 if (this.getSpeechAct()!=null)
 	ht.put("SpeechAct",this.getSpeechAct().toString());
 else	
 	ht.put("SpeechAct","");


}


public String toString(){
 return getId()+":"+getType();
}



}
