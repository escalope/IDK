/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ingenias.installergenerator.view.commands;

import ingenias.installergenerator.view.LabelledProcessWithBarView;
import ingenias.installergenerator.view.ProgressingUpdateCommand;
import ingenias.installergenerator.view.WizardWindow;
import java.awt.BorderLayout;

/**
 *
 * @author Carlos
 */
class ProcessingStateWithBarVC extends ProgressingUpdateCommand {

    private WizardWindow wizardWindow;
    private LabelledProcessWithBarView view = new LabelledProcessWithBarView();

    public ProcessingStateWithBarVC(WizardWindow wizardWindow) {
        this.wizardWindow = wizardWindow;
    }

    @Override
    public void setAdvance(int percent) {
        view.getProgressBar().setValue(percent);
    }

    @Override
    public void execute() {

        view.getMessageLabel().setText((String) this.getData());
        this.wizardWindow.setListener(view);
        this.wizardWindow.getWizardContentPanel().removeAll();
        this.wizardWindow.getWizardContentPanel().add(view, BorderLayout.CENTER);
        this.wizardWindow.getWizardContentPanel().updateUI();
//        this.wizardWindow.getBackButton().setEnabled(false);
        this.wizardWindow.getNextButton().setEnabled(false);
        this.wizardWindow.getCancelButton().setEnabled(false);
    }
}
