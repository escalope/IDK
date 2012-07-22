
/** 
 * Copyright (C) 2010  Jorge J. Gomez-Sanz sobre código original de Rubén Fuentes
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

package ingenias.editor.persistence;

import java.io.*;
import javax.swing.*;
import javax.swing.text.*;

public class TextAreaOutputStream
    extends Writer {
  private JTextPane output;
  
  public TextAreaOutputStream(JTextPane output) {
    this.output = output;
    this.addStylesToDocument(output.getStyledDocument());
  }

  protected void addStylesToDocument(StyledDocument doc) {
    //Initialize some styles.
    Style def = StyleContext.getDefaultStyleContext().
        getStyle(StyleContext.DEFAULT_STYLE);

    Style regular = doc.addStyle("regular", def);
    StyleConstants.setFontFamily(def, "Monospaced");

    Style s = doc.addStyle("error", regular);
    StyleConstants.setForeground(s, java.awt.Color.red);
    StyleConstants.setBold(s, true);

    s = doc.addStyle("warning", regular);
    StyleConstants.setForeground(s, java.awt.Color.green);
    StyleConstants.setBold(s, true);

  }

  /*public TextAreaOutputStream(Object lock) {
    super(lock);
  }*/

  public void write(char[] parm1, int parm2, int parm3) throws java.io.
      IOException {
    
    StyledDocument st = output.getStyledDocument();
    
    try {
    
      String out = new String(parm1).substring(parm2, parm2 + parm3);
      
     if (out.indexOf("ERROR:") > -1) {
    	 out="<span style=\"font-weight: bold; color: rgb(255, 0, 0);\">"+out+"</span>";
        /*st.insertString(st.getLength(),
                        out,
                        st.getStyle("error"));*/
      }
      else
      if (out.indexOf("WARNING:") > -1) {
    	  out="<span style=\"font-weight: bold; color: rgb(0, 153, 0);\">"+out+"</span>";
      /*  st.insertString(st.getLength(),
                        out,
                        st.getStyle("warning"));*/
      }
      else {    	  
       /* st.insertString(st.getLength(),
                        out,
                        st.getStyle("regular"));*/
      }
      output.getEditorKit().read(new StringReader(out),st,st.getLength());
    }
    catch (javax.swing.text.BadLocationException ble) {
      ble.printStackTrace();
    }
//    st..append(new String(parm1).substring(parm2,parm2+parm3));

  }

  public void flush() throws java.io.IOException {

  }

  public void close() throws java.io.IOException {
    /**@todo Implement this java.io.Writer abstract method*/
  }

}