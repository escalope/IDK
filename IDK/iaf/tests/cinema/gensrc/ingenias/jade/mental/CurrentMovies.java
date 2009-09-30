

/*
    Copyright (C) 2005 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 

    INGENIAS Agent Framework is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS Agent Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS Agent Framework; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/


package ingenias.jade.mental;

import java.util.*;
import ingenias.jade.components.*;
import ingenias.editor.entities.*;
import ingenias.editor.entities.ViewPreferences.ViewType;

public class CurrentMovies extends ingenias.editor.entities.RuntimeFact{
   
    Hashtable<String,Vector<String>> startingTimeMovie;   
   
    Hashtable<String,Hashtable<String,Vector<String>>> availableSeats;   
   
    Vector<String> availableExtras;   
   
    Integer price;   
    
   
  public CurrentMovies (String id){
   super(id);
   this.getPrefs().setView(ViewType.UML);
   this.type="CurrentMovies";
  }
  

  public CurrentMovies (){
   super(ingenias.jade.MentalStateManager.generateMentalEntityID());
   this.getPrefs().setView(ViewType.UML);
  }
  
  public String toString(){
   return this.getId()+":"+this.getType();
  }
  
  public String getType(){
   return "CurrentMovies";
  }
  
  public String getParentType(){
   return "RuntimeFact";
  }
  
   

   public void setstartingTimeMovie(Hashtable<String,Vector<String>> value){
     startingTimeMovie=value;   
   };
   
   public Hashtable<String,Vector<String>> getstartingTimeMovie(){
     return startingTimeMovie;      
   }
   

   public void setavailableSeats(Hashtable<String,Hashtable<String,Vector<String>>> value){
     availableSeats=value;   
   };
   
   public Hashtable<String,Hashtable<String,Vector<String>>> getavailableSeats(){
     return availableSeats;      
   }
   

   public void setavailableExtras(Vector<String> value){
     availableExtras=value;   
   };
   
   public Vector<String> getavailableExtras(){
     return availableExtras;      
   }
   

   public void setprice(Integer value){
     price=value;   
   };
   
   public Integer getprice(){
     return price;      
   }
    
  
  
}

 