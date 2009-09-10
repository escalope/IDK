/**
 *
 *
 *  Description of the task /
 *
 * 
 *@author     Jorge J. Gomez
 *@version    1.0
 */
package ingenias.jade.components;

import ingenias.exception.InvalidEntity;
import ingenias.jade.mental.NuevaPropuestaIntroducidaEvento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import trabajomaster.demo.visualizacion.VentanaPrincipal;

public class VisualizacionAppAppImp extends VisualizacionAppApp {

    private VentanaPrincipal frame;

    public VisualizacionAppAppImp() {
        super();
        frame = new VentanaPrincipal();
    }

    @Override
    public void start() {
        frame.setVisible(true);
    }

    @Override
    public void update(int component, Object data) {
        frame.addMensaje((String)data);
    }

    @Override
    public void enviarEvento(String fuente) {
        NuevaPropuestaIntroducidaEvento evento = new NuevaPropuestaIntroducidaEvento();
        evento.setdata(fuente);
        try {
            this.getMultipleOwners().get(0).getMSM().addMentalEntity(evento);
        } catch (InvalidEntity ex) {
            Logger.getLogger(TimerCiclosAppAppImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

 