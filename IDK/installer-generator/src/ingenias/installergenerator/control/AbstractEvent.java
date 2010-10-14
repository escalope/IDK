/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ingenias.installergenerator.control;

/**
 *
 * @author Carlos
 */
public abstract class AbstractEvent {

    private EventId id;

    /**
     * @return the id
     */
    public EventId getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(EventId id) {
        this.id = id;
    }

}
