

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

public class ColumnWidgetPreferences extends AUMLContainerWidgetPreferences {

  Hashtable preferredWidget=new Hashtable();
  Hashtable defaultValues=new Hashtable();


  public ColumnWidgetPreferences() {
  super();
  String[] preferredOrder={""};
  this.setPreferredOrder(preferredOrder);
   Vector result=null;
  
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


