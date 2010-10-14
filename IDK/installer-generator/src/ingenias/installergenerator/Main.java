/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ingenias.installergenerator;

import ingenias.installergenerator.control.BasicEvent;
import ingenias.installergenerator.control.Controller;
import ingenias.installergenerator.control.EventId;
import ingenias.installergenerator.view.WizardWindow;

/**
 *
 * @author Carlos
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WizardWindow.getInstance().setVisible(true);
        Controller.getController().event(new BasicEvent(EventId.Start));
    }

}
