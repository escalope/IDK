

/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz over original code from Ruben Fuentes
 * 
 *   Modifications over original code from jgraph.sourceforge.net
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

package ingenias.editor.widget;
import org.jgraph.graph.*;
import java.util.*;

public class InteractionUnitWidgetPreferences extends INGENIASObjectWidgetPreferences {

  Hashtable preferredWidget=new Hashtable();
  Hashtable defaultValues=new Hashtable();


  public InteractionUnitWidgetPreferences() {
  super();
  String[] preferredOrder={"id","SpeechAct","Timeout","Description","TransferredInfo",""};
  this.setPreferredOrder(preferredOrder);
   Vector result=null;
  
   preferredWidget.put("SpeechAct",ingenias.editor.widget.CustomJComboBox.class);
   result=new Vector();
   
   result.add("not-understood");
   
   result.add("cancel");
   
   result.add("accept-proposal");
   
   result.add("confirm");
   
   result.add("propose");
   
   result.add("refuse");
   
   result.add("agree");
   
   result.add("disconfirm");
   
   result.add("inform-ref");
   
   result.add("inform");
   
   result.add("inform-if");
   
   result.add("proxy");
   
   result.add("reject-proposal");
   
   result.add("request-whenever");
   
   result.add("request");
   
   result.add("request-when");
   
   result.add("agree");
   
   result.add("failure");
   
   result.add("not-understood");
   
   result.add("subscribe");
   
   result.add("cfp");
   
   result.add("propagate");
   
   result.add("query-ref");
   
   result.add("query-if");
   
   result.add("query");
   
   defaultValues.put("SpeechAct",result);
  
   preferredWidget.put("Timeout",ingenias.editor.widget.CustomJTextField.class);
   result=new Vector();
   
   defaultValues.put("Timeout",result);
  
  };

  public Object getWidget(String attName)  throws IllegalAccessException,InstantiationException{
   Class result=null;
   ConfigurableWidget instance=null;
   if (preferredWidget.get(attName)!=null)
    result= ((Class)preferredWidget.get(attName));
   else
    return (super.getWidget(attName));
   if (result!=null){
    instance=(ConfigurableWidget)result.newInstance();
    Vector values=(Vector)defaultValues.get(attName);
    instance.setDefaultValues(values);
   }
   return instance;
  }

  public void configureWidget(ConfigurableWidget cw){

//   cw.setDefaultValues(result);
  }
}


