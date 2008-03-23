package ingenias.editor.rendererxml;

import javax.swing.JLabel;
import javax.swing.Icon;
import java.awt.*;
import java.net.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */


public class JLabelStereotype extends JLabel {



  public  String iconName="";


  public JLabelStereotype() {

  }

  public void setText(String text){
    super.setText("<html><body>&laquo;"+text+"&raquo;</body></html>");
  }


}
