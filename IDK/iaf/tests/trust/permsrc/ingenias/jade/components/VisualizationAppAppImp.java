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
import ingenias.jade.mental.NewIntroducedProposalEvent;
import tws.demo.visualization.MainWindow;

public class VisualizationAppAppImp extends VisualizationAppApp {

    private MainWindow frame;

    public VisualizationAppAppImp() {
        super();
        frame = new MainWindow();
    }

    @Override
    public void start() {
        frame.setVisible(true);
    }

    @Override
    public void update(int component, Object data) {
        frame.addMensaje((String) data);
    }

    @Override
    public void sendEvent(String fuente) {
        NewIntroducedProposalEvent evento = new NewIntroducedProposalEvent();
        evento.setdata(fuente);
        try {
            this.getMultipleOwners().get(0).getMSM().addMentalEntity(evento);
        } catch (InvalidEntity ex) {
            ex.printStackTrace();
        }
    }
}

 