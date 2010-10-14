/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ingenias.installergenerator.control.actions;

import ingenias.installergenerator.control.AbstractEvent;
import ingenias.installergenerator.control.Action;
import ingenias.installergenerator.control.BasicEvent;
import ingenias.installergenerator.control.Controller;
import ingenias.installergenerator.control.EventId;
import ingenias.installergenerator.control.PathsEvent;
import ingenias.installergenerator.model.Context;
import ingenias.installergenerator.view.UpdateCommand;
import ingenias.installergenerator.view.commands.UpdateCommandFactory;
import ingenias.installergenerator.view.commands.UpdateCommandId;
import java.io.File;
import java.util.prefs.Preferences;

/**
 *
 * @author Carlos
 */
public class SetGivenPaths implements Action {

    public void execute(AbstractEvent event) {
        UpdateCommandFactory factory = UpdateCommandFactory.getInstance();
        UpdateCommand command = factory.createCommand(UpdateCommandId.ProcessingView);
        command.setData("Setting Programs paths...");
        command.execute();

        PathsEvent pevent = (PathsEvent) event;
        File izPackInstallDir = pevent.getIzPackInstallDir();
        File antInstallDir = pevent.getAntInstallDir();

        Context.getInstance().put("IzPackInstallDir", izPackInstallDir.getAbsolutePath());
        Context.getInstance().put("AntInstallDir", antInstallDir.getAbsolutePath());

        Preferences pref = Preferences.userRoot().node("/grasia/ingenias/project-installer");
        pref.put("IzPackInstallDir", izPackInstallDir.getAbsolutePath());
        pref.put("AntInstallDir", antInstallDir.getAbsolutePath());

        Controller.getController().event(new BasicEvent(EventId.ReadyPrograms));

    }
}
