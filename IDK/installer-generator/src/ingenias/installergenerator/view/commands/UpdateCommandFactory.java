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
public class UpdateCommandFactory {

    private static UpdateCommandFactory instance = new UpdateCommandFactory();

    public static UpdateCommandFactory getInstance() {
        if (instance == null) {
            instance = new UpdateCommandFactory();
        }
        return instance;

    }
    private WizardWindow wizardWindow = null;

    private UpdateCommandFactory() {
        wizardWindow = WizardWindow.getInstance();
    }

    public UpdateCommand createCommand(UpdateCommandId id) {
        UpdateCommand command = null;
        switch (id) {
            case ProcessingView:
                command = new ProcessingStateVC(wizardWindow);
                break;
            case DeploymentsView:
                command = new DeploymentsVC(wizardWindow);
                break;
            case ShowAbortReasonsView:
                command = new ShowAbortReasonsVC(wizardWindow);
                break;
            case ProcessingViewWithProgress:
                command = new ProcessingStateWithBarVC(wizardWindow);
                break;
            case ShowInstallerGenerationResultsView:
                command = new ShowInstallerGenerationResultsVC(wizardWindow);
                break;
            case IzPackAntPathFormView:
                command = new IzPackAntPathFormVC(wizardWindow);
                break;
            case CloseWindows:
                command = new CloseWindowsVC(wizardWindow);
                break;

        }
        return command;
    }
}
