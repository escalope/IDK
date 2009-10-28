package ingenias.editor.rendererxml;

import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.*;
import javax.swing.border.*;
import ingenias.editor.FontConfiguration;

public class HTMLLabel extends JLabel {

  public HTMLLabel() {
//    DashedBorder db=new DashedBorder(Color.black);
//    this.setBorder(db);
    this.setFont(FontConfiguration.getConfiguration().getStandardFont());
  }

  public void setText(String t){
    super.setText("<html>"+t+"</html>");
  }




}
