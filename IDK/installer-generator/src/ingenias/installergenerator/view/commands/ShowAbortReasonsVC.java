/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ingenias.installergenerator.view.commands;

import ingenias.installergenerator.view.InstallationAbortedView;
import ingenias.installergenerator.view.UpdateCommand;
import ingenias.installergenerator.view.WizardWindow;
import java.awt.BorderLayout;

/**
 *
 * @author Carlos
 */
class ShowAbortReasonsVC extends UpdateCommand{

    WizardWindow wizardWindow;
    ShowAbortReasonsVC(WizardWindow wizardWindow) {

        this.wizardWindow = wizardWindow;
    }

    @Override
    public void execute() {
        InstallationAbortedView view = new InstallationAbortedView();
        view.getReasonsArea().setText((String)this.getData());
this.wizardWindow.setListener(view);
        this.wizardWindow.getWizardContentPanel().removeAll();
        this.wizardWindow.getWizardContentPanel().add(view,BorderLayout.CENTER);
        this.wizardWindow.getWizardContentPanel().updateUI();
 //       this.wizardWindow.getBackButton().setEnabled(false);
        this.wizardWindow.getNextButton().setEnabled(true);
        this.wizardWindow.getNextButton().setText("Exit");
        this.wizardWindow.getCancelButton().setEnabled(false);
    }

}
