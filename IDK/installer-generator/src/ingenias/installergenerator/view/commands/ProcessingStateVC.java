/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ingenias.installergenerator.view.commands;

import ingenias.installergenerator.view.LabelledProcessView;
import ingenias.installergenerator.view.UpdateCommand;
import ingenias.installergenerator.view.WizardWindow;
import java.awt.BorderLayout;

/**
 *
 * @author Carlos
 */
class ProcessingStateVC extends UpdateCommand {

    private WizardWindow wizardWindow;

    ProcessingStateVC(WizardWindow wizardWindow) {
        this.wizardWindow = wizardWindow;
    }

    @Override
    public void execute() {
        LabelledProcessView view = new LabelledProcessView();
        view.getLabel().setText((String) this.getData());
        this.wizardWindow.setListener(view);
        this.wizardWindow.getWizardContentPanel().removeAll();
        this.wizardWindow.getWizardContentPanel().add(view, BorderLayout.CENTER);
        this.wizardWindow.getWizardContentPanel().updateUI();
  //      this.wizardWindow.getBackButton().setEnabled(false);
        this.wizardWindow.getNextButton().setEnabled(false);
        this.wizardWindow.getCancelButton().setEnabled(false);
    }
}
