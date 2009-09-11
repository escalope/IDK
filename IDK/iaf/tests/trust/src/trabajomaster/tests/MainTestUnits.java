/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabajomaster.tests;

import ingenias.jade.IAFProperties;
import ingenias.jade.graphics.MainInteractionManager;
import jade.core.Profile;
import jade.core.ProfileImpl;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/**
 *
 * @author carlos
 */
public class MainTestUnits {

    public static void main(String args[]) throws Exception {


        ingenias.jade.MainTestDeploymentProdStandAlone.main(args);
  
        System.out.println("Running tests...");

        JUnitCore uc = new JUnitCore();
        uc.addListener(new RunListener() {

            public void testRunStarted(Description desc) {
            }

            public void testRunFinished(Result desc) {
                String resultado = desc.wasSuccessful() ? "Exito" : "Fracaso";
                System.out.println("Prueba Ejecutada con resultado: " + resultado + "");
                System.out.flush();
            }

            public void testStarted(Description desc) {
                System.out.println("* Test: " + desc.getDisplayName());
                System.out.flush();
            }

            public void testFailure(Failure desc) {
                System.out.println("Fallo:" + desc.getMessage() + "");
                System.out.println("Traza:" + desc.getTrace() + "");
                System.out.flush();
            }
        });



        uc.run(CasosBasicosTest.class);

        System.exit(0);

    }
}
