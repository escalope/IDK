

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

public class RequestedMovie extends ingenias.editor.entities.RuntimeFact{
   
    String movieName;   
   
    Vector<String> sessions;   
   
    Vector<String> requestedExtras;   
   
    Vector<String> requestedSeats;   
   
    String cinemaName;   
   
    int numberIntents;   
   
    Vector<String> triedCinemas;   
    
   
  public RequestedMovie (String id){
   super(id);
   this.getPrefs().setView(ViewType.UML);
   this.type="RequestedMovie";
  }
  

  public RequestedMovie (){
   super(ingenias.jade.MentalStateManager.generateMentalEntityID());
   this.getPrefs().setView(ViewType.UML);
  }
  
  public String toString(){
   return this.getId()+":"+this.getType();
  }
  
  public String getType(){
   return "RequestedMovie";
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
   

   public void setsessions(Vector<String> value){
     sessions=value;   
   };
   
   public Vector<String> getsessions(){
     return sessions;      
   }
   

   public void setrequestedExtras(Vector<String> value){
     requestedExtras=value;   
   };
   
   public Vector<String> getrequestedExtras(){
     return requestedExtras;      
   }
   

   public void setrequestedSeats(Vector<String> value){
     requestedSeats=value;   
   };
   
   public Vector<String> getrequestedSeats(){
     return requestedSeats;      
   }
   

   public void setcinemaName(String value){
     cinemaName=value;   
   };
   
   public String getcinemaName(){
     return cinemaName;      
   }
   

   public void setnumberIntents(int value){
     numberIntents=value;   
   };
   
   public int getnumberIntents(){
     return numberIntents;      
   }
   

   public void settriedCinemas(Vector<String> value){
     triedCinemas=value;   
   };
   
   public Vector<String> gettriedCinemas(){
     return triedCinemas;      
   }
    
  
  
}

 