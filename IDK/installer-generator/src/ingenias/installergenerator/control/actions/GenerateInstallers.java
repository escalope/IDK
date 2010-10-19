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
import ingenias.installergenerator.view.*;
import ingenias.installergenerator.view.commands.UpdateCommandFactory;
import ingenias.installergenerator.view.commands.UpdateCommandId;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.List;
import static ingenias.installergenerator.utils.ResourcesUtil.*;

/**
 *
 * @author Carlos
 */
public class GenerateInstallers implements Action {

    private List<String> selectedDeployments;

    public void execute(AbstractEvent event) {
        UpdateCommandFactory factory = UpdateCommandFactory.getInstance();
        ProgressingUpdateCommand command = (ProgressingUpdateCommand) factory.createCommand(UpdateCommandId.ProcessingViewWithProgress);
        command.setData("Generating Installers...");
        command.setAdvance(0);
        command.execute();

        String projectDir = (String) Context.getInstance().get("ProjectDir");

        selectedDeployments = (List<String>) ((BasicEvent) event).getData();
        String targetTemplate = "";
        try {
            targetTemplate = getTargetTemplate();
        } catch (Exception ex) {
            ex.printStackTrace();
            Controller.getController().event(new BasicEvent(EventId.ErrorGeneratingInstaller, "Internal error getting the target-template file"));
            return;
        }
        command.setAdvance(5);
        String shortcutsTemplate = "";
        try {
            shortcutsTemplate = getShortcutsTemplate();
        } catch (Exception ex) {
            ex.printStackTrace();
            Controller.getController().event(new BasicEvent(EventId.ErrorGeneratingInstaller, "Internal error getting the shortcut-template file"));
        }
        command.setAdvance(10);
        String packFileTemplate = "";
        try {
            packFileTemplate = getPackFileTemplate();
        } catch (Exception ex) {
            ex.printStackTrace();
            Controller.getController().event(new BasicEvent(EventId.ErrorGeneratingInstaller, "Internal error getting the pack-file-template file"));
        }
        command.setAdvance(15);
        StringBuffer targets = new StringBuffer("");
        StringBuffer shortcuts = new StringBuffer("");
        StringBuffer deploymentsFile = new StringBuffer("");
        StringBuffer deploymentList = new StringBuffer("");

        command.setAdvance(20);
        int tempsize = selectedDeployments.size();
        int incr = 50 / tempsize;
        int processState = 20;
        for (String selectedDeployment : selectedDeployments) {
            String target = targetTemplate.replace("@{deployment}", selectedDeployment);
            targets.append(target);
            targets.append("\n");

            String shortcut = shortcutsTemplate.replace("@{deployment}", selectedDeployment);
            shortcuts.append(shortcut);
            shortcuts.append("\n");

            String packFile = packFileTemplate.replace("@{deployment}", selectedDeployment);
            deploymentsFile.append(packFile);
            deploymentsFile.append("\n");

            deploymentList.append("generate-launcher-" + selectedDeployment);
            deploymentList.append(",");
            processState += incr;
            command.setAdvance(processState);
        }

        command.setAdvance(70);

        String deploymentsString = deploymentList.substring(0, deploymentList.length() - 1);

        String buildXML = "";
        try {
            buildXML = getBuildXML();
        } catch (Exception ex) {
            ex.printStackTrace();
            Controller.getController().event(new BasicEvent(EventId.ErrorGeneratingInstaller, "Internal error getting the private build.xml file"));
        }
        String winShortcutsXML = "";
        try {
            winShortcutsXML = getWinShortcutsXML();
        } catch (Exception ex) {
            ex.printStackTrace();
            Controller.getController().event(new BasicEvent(EventId.ErrorGeneratingInstaller, "Internal error getting the win-shortcuts.xml file"));
        }
        String installXML = "";
        try {
            installXML = getInstallXML();
        } catch (Exception ex) {
            ex.printStackTrace();
            Controller.getController().event(new BasicEvent(EventId.ErrorGeneratingInstaller, "Internal error getting the install.xml file"));
        }

        buildXML = buildXML.replace("@{targets}", targets.toString());
        buildXML = buildXML.replace("@{target-list}", deploymentsString);
        winShortcutsXML = winShortcutsXML.replace("@{shortcuts}", shortcuts.toString());
        installXML = installXML.replace("@{deployments-file}", deploymentsFile.toString());
        try {
            writeInstallerConfigurations(projectDir,buildXML, winShortcutsXML, installXML);
        } catch (Exception ex) {
            Controller.getController().event(new BasicEvent(EventId.ErrorGeneratingInstaller, "Error writing the installation files"));
            ex.printStackTrace();
        }
        try {
            String antInstallDir = (String) Context.getInstance().get("AntInstallDir");
            ProcessBuilder pb = new ProcessBuilder();
            pb.directory(new File(projectDir));
            pb.command(antInstallDir + "/bin/ant.bat", "generate-launchers");

            Process antProcess = pb.start();



            command.setAdvance(75);

            InputStream is = antProcess.getInputStream();
            InputStream es = antProcess.getErrorStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            InputStreamReader esr = new InputStreamReader(es);
            BufferedReader ebr = new BufferedReader(esr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            while ((line = ebr.readLine()) != null) {
                System.err.println(line);
            }
            br.close();
            ebr.close();

            command.setAdvance(85);
            if (antProcess.exitValue() == 0) {
                String izPackInstallDir = (String) Context.getInstance().get("IzPackInstallDir");

                if (izPackInstallDir != null) {
                    pb = new ProcessBuilder();
                    pb.command(izPackInstallDir + "/bin/compile.bat", "install.xml");
                    pb.directory(new File(projectDir));
                    Process izPackProcess = pb.start();
                    command.setAdvance(90);
                    is = izPackProcess.getInputStream();
                    es = izPackProcess.getErrorStream();
                    isr = new InputStreamReader(is);
                    br = new BufferedReader(isr);
                    esr = new InputStreamReader(es);
                    ebr = new BufferedReader(esr);

                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                    while ((line = ebr.readLine()) != null) {
                        System.err.println(line);
                    }
                    br.close();
                    ebr.close();
                    if (izPackProcess.exitValue() == 0) {
                        command.setAdvance(100);
                        Controller.getController().event(new BasicEvent(EventId.OK));
                    } else {
                        Controller.getController().event(new BasicEvent(EventId.ErrorGeneratingInstaller, "IzPack has terminated with errors"));
                    }
                } else {
                    Controller.getController().event(new BasicEvent(EventId.ErrorGeneratingInstaller, "IzPack installation has not been detected"));
                }
            } else {
                Controller.getController().event(new BasicEvent(EventId.ErrorGeneratingInstaller, "Ant has terminated with errors"));
            }

        } catch (Exception ex) {
            Controller.getController().event(new BasicEvent(EventId.ErrorGeneratingInstaller, "Error executing processes"));
            ex.printStackTrace();
        }

    }

    private String getTargetTemplate() throws Exception {
        return reader("/ingenias/installergenerator/res/private/target-template.xml");
    }

    private String getShortcutsTemplate() throws Exception {
        return reader("/ingenias/installergenerator/res/shortcuts/shortcut-template.xml");
    }

    private String getPackFileTemplate() throws Exception {
        return reader("/ingenias/installergenerator/res/pack-file-dep-template.xml");
    }

    private String getBuildXML() throws Exception {
        return reader("/ingenias/installergenerator/res/private/build.xml");
    }

    private String getWinShortcutsXML() throws Exception {
        return reader("/ingenias/installergenerator/res/shortcuts/win-shortcuts.xml");
    }

    private String getInstallXML() throws Exception {
        return reader("/ingenias/installergenerator/res/install.xml");
    }

    private void writeInstallerConfigurations(String projectDir, String buildXML, String winShortcutsXML, String installXML) throws FileNotFoundException, IOException, URISyntaxException {
        File installerResourcesPrivateDir = new File(projectDir+"/installer-resources/private");
        installerResourcesPrivateDir.mkdirs();

        File installerResourcesShortcutsDir = new File(projectDir+"/installer-resources/shortcuts");
        installerResourcesShortcutsDir.mkdirs();
        copy("/ingenias/installergenerator/res/Readme.txt", new File(projectDir+"/installer-resources/Readme.txt"));
        copy("/ingenias/installergenerator/res/Licence.txt", new File(projectDir+"/installer-resources/Licence.txt"));
        copy("/ingenias/installergenerator/res/private/DefaultInstallDir.txt", new File(projectDir+"/installer-resources/private/DefaultInstallDir.txt"));
        for (String selectedDeployment : selectedDeployments) {
            copy("/ingenias/installergenerator/res/shortcuts/default.ico",
                    new File(projectDir+"/installer-resources/shortcuts/default-" + selectedDeployment + ".ico"));
        }

        File installXMLFile = new File(projectDir+"/install.xml");
        if (!installXMLFile.exists()) {
            installXMLFile.createNewFile();
        }

        writeToFile(installXML, installXMLFile);

        File buildXMLFile = new File(projectDir+"/installer-resources/private/build.xml");
        if (!buildXMLFile.exists()) {
            buildXMLFile.createNewFile();
        }

        writeToFile(buildXML, buildXMLFile);

        File winShortcutsXMLFile = new File(projectDir+"/installer-resources/shortcuts/win-shortcuts.xml");
        if (!winShortcutsXMLFile.exists()) {
            winShortcutsXMLFile.createNewFile();
        }

        writeToFile(winShortcutsXML, winShortcutsXMLFile);


    }
}
