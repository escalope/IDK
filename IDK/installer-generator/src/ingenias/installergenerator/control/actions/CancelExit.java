/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ingenias.installergenerator.control.actions;

import ingenias.installergenerator.control.AbstractEvent;
import ingenias.installergenerator.control.Action;
import ingenias.installergenerator.view.ProgressingUpdateCommand;
import ingenias.installergenerator.view.UpdateCommand;
import ingenias.installergenerator.view.commands.UpdateCommandFactory;
import ingenias.installergenerator.view.commands.UpdateCommandId;

/**
 *
 * @author Carlos
 */
public class CancelExit implements Action{
    public void execute(AbstractEvent event) {
        UpdateCommandFactory factory = UpdateCommandFactory.getInstance();
        UpdateCommand command = factory.createCommand(UpdateCommandId.CloseWindows);
        command.execute();
    }
}
