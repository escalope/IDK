/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ingenias.installergenerator.control;

/**
 *
 * @author Carlos
 */
public enum GenerationState {
GeneratingInstallers,
ReadingSpec,
RequestingForSelectDeployments,
RequestingForMissingPrograms,
ShowingAbortInstallation,
ShowingResults,
VerifyingAntIzPack,
NotStarted
}
