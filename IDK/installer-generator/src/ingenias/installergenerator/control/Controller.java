/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ingenias.installergenerator.control;

import ingenias.installergenerator.control.actions.RequestIzPackAntPath;
import ingenias.installergenerator.control.actions.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Carlos
 */
public class Controller {

    class Runner implements Runnable {

        private Action _action;
        private AbstractEvent _event;

        Runner(Action action, AbstractEvent event) {
            _action = action;
            _event = event;
        }

        public void run() {
            _action.execute(_event);
        }
    }
    private static Controller instance = new Controller();

    public static Controller getController() {
        return instance;
    }
    private ExecutorService executor = null;
    private GenerationState currentState = GenerationState.NotStarted;

    private Controller() {
        executor = Executors.newFixedThreadPool(1);
    }

    public void event(AbstractEvent event) {
        Action action = null;
        switch (currentState) {
            case GeneratingInstallers:
                switch (event.getId()) {
                    case ErrorGeneratingInstaller:
                        currentState = GenerationState.ShowingAbortInstallation;
                        action = new ShowAbortReasons();
                        break;
                    case OK:
                        currentState = GenerationState.ShowingResults;
                        action = new ShowInstallerGenerationResults();
                        break;
                }
                break;
            case ReadingSpec:
                switch (event.getId()) {
                    case ErrorReadingSpec:
                        currentState = GenerationState.ShowingAbortInstallation;
                        action = new ShowAbortReasons();
                        break;
                    case OK:
                        currentState = GenerationState.RequestingForSelectDeployments;
                        action = new RequestForSelectDeployments();
                        break;
                }
                break;
            case RequestingForSelectDeployments:
                switch (event.getId()) {
                    case DeploymentsToGenerateInstaller:
                        currentState = GenerationState.GeneratingInstallers;
                        action = new GenerateInstallers();
                        break;
                    case Cancel:
                        action = new CancelExit();
                        break;
                }
                break;
            case RequestingForMissingPrograms:
                switch (event.getId()) {
                    case Next:
                        action = new SetGivenPaths();
                        break;

                    case ReadyPrograms:
                        currentState = GenerationState.ReadingSpec;
                        action = new ProcessSpec();
                        break;
                    case Cancel:
                        action = new CancelExit();
                        break;
                }
                break;
            case ShowingAbortInstallation:
                switch (event.getId()) {
                    case Next:
                        action = new CancelExit();
                        break;
                }
                break;
            case ShowingResults:
                switch (event.getId()) {
                    case Next:
                        action = new CancelExit();
                        break;
                }
                break;
            case VerifyingAntIzPack:
                switch (event.getId()) {
                    case MissingPrograms:
                        currentState = GenerationState.RequestingForMissingPrograms;
                        action = new RequestIzPackAntPath();
                        break;
                    case ReadyPrograms:
                        currentState = GenerationState.ReadingSpec;
                        action = new ProcessSpec();
                        break;
                }
                break;
            case NotStarted:
                action = new VerifyAntIzPack();
                currentState = GenerationState.VerifyingAntIzPack;
                break;
        }

        if (action != null) {
            executor.submit(new Runner(action, event));
            //action.execute(event);
        }
    }
}
