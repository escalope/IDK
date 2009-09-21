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
import ingenias.jade.mental.NewCycleEvent;
import java.util.*;
import ingenias.jade.exception.*;

public class TimerAppAppImp extends TimerAppApp {

    public TimerAppAppImp() {
        super();
    }

    public void sendEvent(String source) {
        NewCycleEvent evento = new NewCycleEvent();
        evento.setdata(source);
        try {
            this.getMultipleOwners().get(0).getMSM().addMentalEntity(evento);
        } catch (InvalidEntity ex) {
            ex.printStackTrace();
        }
    }
}

 