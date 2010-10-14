/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ingenias.installergenerator.control;

import java.io.File;

/**
 *
 * @author Carlos
 */
public class PathsEvent extends AbstractEvent{
    private File antInstallDir;
    private File izPackInstallDir;

    /**
     * @return the antInstallDir
     */
    public File getAntInstallDir() {
        return antInstallDir;
    }

    /**
     * @param antInstallDir the antInstallDir to set
     */
    public void setAntInstallDir(File antInstallDir) {
        this.antInstallDir = antInstallDir;
    }

    /**
     * @return the izPackInstallDir
     */
    public File getIzPackInstallDir() {
        return izPackInstallDir;
    }

    /**
     * @param izPackInstallDir the izPackInstallDir to set
     */
    public void setIzPackInstallDir(File izPackInstallDir) {
        this.izPackInstallDir = izPackInstallDir;
    }
}
