/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ingenias.installergenerator.control;

import ingenias.installergenerator.control.AbstractEvent;

/**
 *
 * @author Carlos
 */
public interface Action{
    void execute(AbstractEvent event);
}
