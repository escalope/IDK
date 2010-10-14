/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ingenias.installergenerator.control.actions;

import ingenias.installergenerator.control.Action;
import ingenias.installergenerator.control.AbstractEvent;
import ingenias.installergenerator.control.BasicEvent;
import ingenias.installergenerator.control.Controller;
import ingenias.installergenerator.control.EventId;
import ingenias.installergenerator.model.Context;
import ingenias.installergenerator.view.UpdateCommand;
import ingenias.installergenerator.view.commands.UpdateCommandFactory;
import ingenias.installergenerator.view.commands.UpdateCommandId;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.prefs.Preferences;

/**
 *
 * @author Carlos
 */
public class VerifyAntIzPack implements Action {

    public void execute(AbstractEvent event) {
        UpdateCommandFactory factory = UpdateCommandFactory.getInstance();
        UpdateCommand command = factory.createCommand(UpdateCommandId.ProcessingView);
        command.setData("Verifying Required Programs...");
        command.execute();

        if (Context.getInstance().get("IzPackInstallDir") != null
                && Context.getInstance().get("AntInstallDir") != null) {
            String izPackInstallDirPath = (String) Context.getInstance().get("IzPackInstallDir");
            String antInstallDirPath = (String) Context.getInstance().get("AntInstallDir");
            File izPackInstallDir = new File(izPackInstallDirPath);
            File antInstallDir = new File(antInstallDirPath);

            if (!izPackInstallDir.exists()
                    || !antInstallDir.exists()) {
                Controller.getController().event(new BasicEvent(EventId.MissingPrograms));
            } else {
                Controller.getController().event(new BasicEvent(EventId.ReadyPrograms));
            }

        } else {

            File izPackInstallDir = null;
            File antInstallDir = null;
            File programFilesDir = null;


            String programFilesDirPath = System.getenv("PROGRAMFILES");
            if (programFilesDirPath != null) {
                programFilesDir = new File(programFilesDirPath);
                File[] appDirs = programFilesDir.listFiles(new FileFilter() {

                    public boolean accept(File dir) {
                        boolean accepted = false;
                        if (dir.isDirectory()) {
                            String nameUpper = dir.getName().toUpperCase();
                            if (nameUpper.contains("IZPACK")
                                    || nameUpper.contains("ANT")) {
                                accepted = true;
                            } else {
                                accepted = false;
                            }
                        }
                        return accepted;
                    }
                });

                if (appDirs.length >= 2) {

                    boolean foundIzPack = false;
                    for (int i = 0; i < appDirs.length && !foundIzPack; ++i) {
                        File appDir = appDirs[i];
                        if (appDir.getName().toUpperCase().contains("IZPACK")) {
                            izPackInstallDir = appDir;
                            foundIzPack = true;
                        }
                    }

                    boolean foundAnt = false;
                    for (int i = 0; i < appDirs.length && !foundAnt; ++i) {
                        File appDir = appDirs[i];
                        if (appDir.getName().toUpperCase().contains("ANT")) {
                            antInstallDir = appDir;
                            foundAnt = true;
                        }
                    }

                    if (foundAnt && foundIzPack) {

                        Context.getInstance().put("IzPackInstallDir", izPackInstallDir.getAbsolutePath());
                        Context.getInstance().put("AntInstallDir", antInstallDir.getAbsolutePath());

                        Preferences pref = Preferences.userRoot().node("/grasia/ingenias/project-installer");
                        pref.put("IzPackInstallDir", izPackInstallDir.getAbsolutePath());
                        pref.put("AntInstallDir", antInstallDir.getAbsolutePath());

                        Controller.getController().event(new BasicEvent(EventId.ReadyPrograms));
                    } else {
                        Controller.getController().event(new BasicEvent(EventId.MissingPrograms));
                    }

                } else {
                    Controller.getController().event(new BasicEvent(EventId.MissingPrograms));
                }

            } else {
                Controller.getController().event(new BasicEvent(EventId.MissingPrograms));
            }

        }

    }
}
