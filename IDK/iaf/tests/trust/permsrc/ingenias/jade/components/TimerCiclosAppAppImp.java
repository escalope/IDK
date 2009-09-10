

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
import java.util.*;
import ingenias.jade.exception.*;
import ingenias.jade.mental.NuevoCicloEvento;
import java.util.logging.Level;
import java.util.logging.Logger;



public  class TimerCiclosAppAppImp extends TimerCiclosAppApp{

 public TimerCiclosAppAppImp(){
  super();
  System.out.println("Timer creado");
 }

    @Override
    public void enviarEvento(String fuente) {
        NuevoCicloEvento evento = new NuevoCicloEvento();
        evento.setdata(fuente);
        try {
            this.getMultipleOwners().get(0).getMSM().addMentalEntity(evento);
        } catch (InvalidEntity ex) {
            Logger.getLogger(TimerCiclosAppAppImp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}

 