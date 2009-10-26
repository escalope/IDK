

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

public class QualityDegreeOfSourceInTesting extends ingenias.editor.entities.RuntimeFact{
   
    java.lang.Object data;   
   
    java.lang.Double declaredQuality;   
   
    java.lang.Double objectCriteria;   
   
    java.lang.Double processCriteria;   
   
    java.lang.Double subjectCriteria;   
    
   
  public QualityDegreeOfSourceInTesting (String id){
   super(id);
   this.getPrefs().setView(ViewType.UML);
   this.type="QualityDegreeOfSourceInTesting";
  }
  

  public QualityDegreeOfSourceInTesting (){
   super(ingenias.jade.MentalStateManager.generateMentalEntityID());
   this.getPrefs().setView(ViewType.UML);
  }
  
  public String toString(){
   return this.getId()+":"+this.getType();
  }
  
  public String getType(){
   return "QualityDegreeOfSourceInTesting";
  }
  
  public String getParentType(){
   return "RuntimeFact";
  }
  
   

   public void setdata(java.lang.Object value){
     data=value;   
   };
   
   public java.lang.Object getdata(){
     return data;      
   }
   

   public void setdeclaredQuality(java.lang.Double value){
     declaredQuality=value;   
   };
   
   public java.lang.Double getdeclaredQuality(){
     return declaredQuality;      
   }
   

   public void setobjectCriteria(java.lang.Double value){
     objectCriteria=value;   
   };
   
   public java.lang.Double getobjectCriteria(){
     return objectCriteria;      
   }
   

   public void setprocessCriteria(java.lang.Double value){
     processCriteria=value;   
   };
   
   public java.lang.Double getprocessCriteria(){
     return processCriteria;      
   }
   

   public void setsubjectCriteria(java.lang.Double value){
     subjectCriteria=value;   
   };
   
   public java.lang.Double getsubjectCriteria(){
     return subjectCriteria;      
   }
     
    
}

 