

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

public class UMLSendsMessage extends NAryEdgeEntity {


  public java.lang.String Order;

  public java.lang.String Message;




  public UMLSendsMessage(String id) {
    super(id);
    ModelEntity em=null;
  }






  
      public java.lang.String getOrder(){
        return Order;
      }
   public void setOrder(java.lang.String
					Order){
        this.Order=Order;
      }

      public java.lang.String getMessage(){
        return Message;
      }
   public void setMessage(java.lang.String
					Message){
        this.Message=Message;
      }



public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Order")!=null && ht.get("Order").equals(""))
  this.setOrder(null);
 else
  if (ht.get("Order")!=null)
   this.setOrder(new java.lang.String(ht.get("Order").toString()));

 if (ht.get("Message")!=null && ht.get("Message").equals(""))
  this.setMessage(null);
 else
  if (ht.get("Message")!=null)
   this.setMessage(new java.lang.String(ht.get("Message").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getOrder() instanceof String)
 if (this.getOrder()!=null)
 	ht.put("Order",this.getOrder().toString());
 else	
 	ht.put("Order","");

//if (this.getMessage() instanceof String)
 if (this.getMessage()!=null)
 	ht.put("Message",this.getMessage().toString());
 else	
 	ht.put("Message","");


}


public String toString(){
 return getId()+":"+getType();
}



}
