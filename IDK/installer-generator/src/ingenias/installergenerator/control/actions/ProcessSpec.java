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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Carlos
 */
public class ProcessSpec implements Action {

    public void execute(AbstractEvent guiEvent) {
        UpdateCommandFactory factory = UpdateCommandFactory.getInstance();
        UpdateCommand command = factory.createCommand(UpdateCommandId.ProcessingView);
        command.setData("Processing INGENIAS Specification...");
        command.execute();

        String projectDir = (String) Context.getInstance().get("ProjectDir");
        if (projectDir == null) {
            projectDir = System.getProperty("user.dir");
            projectDir = (new File(projectDir)).getAbsolutePath();
            Context.getInstance().put("ProjectDir", projectDir);
        }

        XMLInputFactory xmlFactory = XMLInputFactory.newInstance();
        File spec = new File(projectDir+"spec/specification.xml");
        try {
            XMLEventReader xmlReader = xmlFactory.createXMLEventReader(new FileReader(spec));
            ArrayList<String> deployments = new ArrayList<String>();
            while (xmlReader.hasNext()) {
                XMLEvent event = xmlReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement elem = event.asStartElement();
                    if (elem.getName().getLocalPart().equals("object")) {
                        Attribute attr = elem.getAttributeByName(new QName("type"));
                        if ((attr != null) && (attr.getValue().equals("ingenias.editor.entities.DeploymentPackage"))) {
                            Attribute attrId = elem.getAttributeByName(new QName("id"));
                            if (attrId != null) {
                                deployments.add(attrId.getValue());
                            }
                        }
                    }
                }
            }
            xmlReader.close();
            Controller.getController().event(new BasicEvent(EventId.OK, deployments));

        } catch (XMLStreamException ex) {
            BasicEvent event = new BasicEvent(EventId.ErrorReadingSpec, "Error processing Specification!");
            ex.printStackTrace();
            Controller.getController().event(event);
        } catch (FileNotFoundException ex) {
            BasicEvent event = new BasicEvent(EventId.ErrorReadingSpec, projectDir+"spec/specification.xml file was not found!");
            ex.printStackTrace();
            Controller.getController().event(event);
        }

    }
}
