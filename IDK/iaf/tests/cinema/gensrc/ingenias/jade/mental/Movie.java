

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

public class Movie extends ingenias.editor.entities.RuntimeFact{
   
    String movieName;   
   
    Integer preferredPrice;   
   
    Vector<String> preferredSeats;   
   
    Vector<String> preferredExtras;   
   
    Vector<String> preferredSessions;   
    
   
  public Movie (String id){
   super(id);
   this.getPrefs().setView(ViewType.UML);
   this.type="Movie";
  }
  

  public Movie (){
   super(ingenias.jade.MentalStateManager.generateMentalEntityID());
   this.getPrefs().setView(ViewType.UML);
  }
  
  public String toString(){
   return this.getId()+":"+this.getType();
  }
  
  public String getType(){
   return "Movie";
  }
  
  public String getParentType(){
   return "RuntimeFact";
  }
  
   

   public void setmovieName(String value){
     movieName=value;   
   };
   
   public String getmovieName(){
     return movieName;      
   }
   

   public void setpreferredPrice(Integer value){
     preferredPrice=value;   
   };
   
   public Integer getpreferredPrice(){
     return preferredPrice;      
   }
   

   public void setpreferredSeats(Vector<String> value){
     preferredSeats=value;   
   };
   
   public Vector<String> getpreferredSeats(){
     return preferredSeats;      
   }
   

   public void setpreferredExtras(Vector<String> value){
     preferredExtras=value;   
   };
   
   public Vector<String> getpreferredExtras(){
     return preferredExtras;      
   }
   

   public void setpreferredSessions(Vector<String> value){
     preferredSessions=value;   
   };
   
   public Vector<String> getpreferredSessions(){
     return preferredSessions;      
   }
     
    
}

 