package ingenias.editor.rendererxml;

import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.*;
import javax.swing.border.*;

public class HTMLLabel extends JLabel {

  public HTMLLabel() {
//    DashedBorder db=new DashedBorder(Color.black);
//    this.setBorder(db);
  }

  public void setText(String t){
    super.setText("<HTML><BODY>"+t+"<BODY></HTML>");
  }




}
