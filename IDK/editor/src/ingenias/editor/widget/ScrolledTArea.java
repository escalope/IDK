/*
    Copyright (C) 2002 Jorge Gomez Sanz

    This file is part of INGENIAS IDE, a support tool for the INGENIAS
    methodology, availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net

    INGENIAS IDE is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS IDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS IDE; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/

package ingenias.editor.widget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.io.*;


public class ScrolledTArea extends JPanel implements Editable,java.io.Serializable ,ConfigurableWidget {

  private JTextArea contenido = new JTextArea();
  private CustomJTextArea jTextArea1 = new CustomJTextArea();


  public ScrolledTArea() {
    super();
    contenido=new JTextArea(15,40);
    this.setLayout(new GridLayout());
    JScrollPane jScrollPane1 = new JScrollPane();
    contenido.setWrapStyleWord(true);
    contenido.setLineWrap(true);
    this.add(jScrollPane1, null);

    jScrollPane1.getViewport().add(contenido, null);
    contenido.addInputMethodListener(new java.awt.event.InputMethodListener() {
      public void inputMethodTextChanged(InputMethodEvent e) {
        jTextArea1_inputMethodTextChanged(e);
      }
      public void caretPositionChanged(InputMethodEvent e) {
      }
    });


  }

  public void setText(String text){
    this.contenido.setText(text);
  }

  public String getText(){
    try {
    java.io.ByteArrayOutputStream ba=new java.io.ByteArrayOutputStream();
    OutputStreamWriter osw=new OutputStreamWriter(ba,"UTF8");
    osw.write(contenido.getText() );
//    osw.close();
    //System.err.println(new String(ba.toByteArray()));
    return  new String(ba.toByteArray());
    } catch (Exception uee){
      uee.printStackTrace();
    }
    return "";

  }

  public void addKeyListener(KeyListener ke){
    this.contenido.addKeyListener(ke);
  }

  public void addFocusListener(FocusListener ke){

    if (contenido!=null)
    this.contenido.addFocusListener(ke);
  }

  public void setDefaultValues(Vector v){

  }

  public static void main(String args[]){
    JFrame f=new JFrame();
    f.getContentPane().add(new ScrolledTArea());
    f.pack();
    f.show();
  }


  void jTextArea1_inputMethodTextChanged(InputMethodEvent e) {
    System.err.println("Text changed");
  }


}