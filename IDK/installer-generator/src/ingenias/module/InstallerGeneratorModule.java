/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ingenias.module;

import ingenias.editor.ProjectProperty;
import ingenias.generator.browser.Browser;
import ingenias.installergenerator.control.BasicEvent;
import ingenias.installergenerator.control.Controller;
import ingenias.installergenerator.control.EventId;
import ingenias.installergenerator.model.Context;
import ingenias.installergenerator.view.WizardWindow;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Vector;

/**
 *
 * @author Carlos
 */
public class InstallerGeneratorModule extends ingenias.editor.extension.BasicToolImp {

    private File projectDir = null;
    private boolean enable = true;

    public InstallerGeneratorModule(Browser browser) {
        super(browser);

    }

    @Override
    public String getDescription() {
        return "Generator of Installers for IAF projects";
    }

    @Override
    public String getName() {
        return "InstallerGenerator";
    }

    private void init() {
        File projectFile = browser.getProjectFilePath();
        if (projectFile == null) {
            enable = false;
        } else {
            File specDir = projectFile.getParentFile();
            projectDir = specDir.getParentFile();
            if (!projectDir.exists()) {
                enable = false;
            }
            File[] buildXML = projectDir.listFiles(new FilenameFilter() {

                public boolean accept(File dir, String name) {
                    return name.contains("build.xml");
                }
            });
            if (buildXML.length == 0) {
                enable = false;
            }
        }
    }

    @Override
    public void run() {

        init();
        if (enable) {
            System.out.println("Running Assistant");
            Context.getInstance().put("ProjectDir", projectDir.getAbsolutePath());
            WizardWindow.getInstance().setVisible(true);
            Controller.getController().event(new BasicEvent(EventId.Start));
        } else {
            System.err.println("The InstallerGenerator module is disable because the project has not been detected as an IAF project");
        }
    }

    @Override
    protected Vector<ProjectProperty> defaultProperties() {
        Vector<ProjectProperty> result = new Vector<ProjectProperty>();
        return result;
    }

    public String getVersion() {
        return "1.0.0";
    }
}
