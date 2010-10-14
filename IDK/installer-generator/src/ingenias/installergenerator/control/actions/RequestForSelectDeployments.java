/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ingenias.installergenerator.control.actions;

import ingenias.installergenerator.control.Action;
import ingenias.installergenerator.control.AbstractEvent;
import ingenias.installergenerator.control.BasicEvent;
import ingenias.installergenerator.view.UpdateCommand;
import ingenias.installergenerator.view.commands.UpdateCommandFactory;
import ingenias.installergenerator.view.commands.UpdateCommandId;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class RequestForSelectDeployments implements Action {

    public void execute(AbstractEvent event) {
        BasicEvent bevent = (BasicEvent) event;
        List<String> deployments = (List<String>) bevent.getData();
        UpdateCommandFactory factory = UpdateCommandFactory.getInstance();
        UpdateCommand command = factory.createCommand(UpdateCommandId.DeploymentsView);
        command.setData(deployments);
        command.execute();
    }
}
