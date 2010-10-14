/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ingenias.installergenerator.view.commands;

import ingenias.installergenerator.view.IzPackAntPathFormView;
import ingenias.installergenerator.view.UpdateCommand;
import ingenias.installergenerator.view.WizardWindow;
import java.awt.BorderLayout;

/**
 *
 * @author Carlos
 */
public class IzPackAntPathFormVC extends UpdateCommand {

    private WizardWindow wizardWindow;

    IzPackAntPathFormVC(WizardWindow wizardWindow) {
        this.wizardWindow = wizardWindow;
    }

    @Override
    public void execute() {
        IzPackAntPathFormView view = new IzPackAntPathFormView();
        this.wizardWindow.setListener(view);
        this.wizardWindow.getWizardContentPanel().removeAll();
        this.wizardWindow.getWizardContentPanel().add(view, BorderLayout.CENTER);
        this.wizardWindow.getWizardContentPanel().updateUI();
  //      this.wizardWindow.getBackButton().setEnabled(false);
        this.wizardWindow.getNextButton().setEnabled(true);
        this.wizardWindow.getCancelButton().setEnabled(true);
    }
}
