
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

public class UMLComment extends UMLObject {


  public java.lang.String Text;





  public UMLComment(String id) {
    super(id);
    this.setHelpDesc("A text note is simply an graphic object that shows text explaining details of a diagram.");
    this.setHelpRecom("");
  }


      public java.lang.String getText(){
        return Text;
      }
       public void setText(java.lang.String
					Text){
        this.Text=Text;
      }





public void fromMap(Map ht){
super.fromMap(ht);

 if (ht.get("Text")!=null && ht.get("Text").equals(""))
  this.setText(null);
 else
  if (ht.get("Text")!=null)
   this.setText(new java.lang.String(ht.get("Text").toString()));



}
public void toMap(Map ht){
super.toMap(ht);

//if (this.getText() instanceof String)
 if (this.getText()!=null)
 	ht.put("Text",this.getText().toString());
 else	
 	ht.put("Text","");


}

public String toString(){
if (this.getText()==null ||
    this.getText().toString().equals(""))
 return "Please, define the value of field Text";
else
 return this.getText().toString();
}

}