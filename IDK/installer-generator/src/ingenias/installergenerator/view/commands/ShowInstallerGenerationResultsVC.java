/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ingenias.installergenerator.view.commands;

import ingenias.installergenerator.view.InstallerGenerationSuccessfulView;
import ingenias.installergenerator.view.UpdateCommand;
import ingenias.installergenerator.view.WizardWindow;
import java.awt.BorderLayout;

/**
 *
 * @author Carlos
 */
public class ShowInstallerGenerationResultsVC extends UpdateCommand {

    WizardWindow wizardWindow;

    ShowInstallerGenerationResultsVC(WizardWindow wizardWindow) {

        this.wizardWindow = wizardWindow;
    }

    @Override
    public void execute() {
        InstallerGenerationSuccessfulView view = new InstallerGenerationSuccessfulView();
        this.wizardWindow.setListener(view);
        this.wizardWindow.getWizardContentPanel().removeAll();
        this.wizardWindow.getWizardContentPanel().add(view, BorderLayout.CENTER);
        this.wizardWindow.getWizardContentPanel().updateUI();
 //       this.wizardWindow.getBackButton().setEnabled(false);
        this.wizardWindow.getNextButton().setEnabled(true);
        this.wizardWindow.getNextButton().setText("Exit");
        this.wizardWindow.getCancelButton().setEnabled(false);
    }
}
