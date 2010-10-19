/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ingenias.installergenerator.view.commands;

import ingenias.installergenerator.view.UpdateCommand;
import ingenias.installergenerator.view.WizardWindow;

/**
 *
 * @author Carlos
 */
public class CloseWindowsVC extends UpdateCommand{

    private WizardWindow wizardWindow;
    public CloseWindowsVC(WizardWindow wizardWindow) {
        this.wizardWindow = wizardWindow;
    }

    @Override
    public void execute() {
        this.wizardWindow.setVisible(false);
    }
}
