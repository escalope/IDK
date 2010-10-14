/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ingenias.installergenerator.control.actions;

import ingenias.installergenerator.control.AbstractEvent;
import ingenias.installergenerator.control.Action;

/**
 *
 * @author Carlos
 */
public class CancelExit implements Action{
    public void execute(AbstractEvent event) {
        System.exit(0);
    }
}
