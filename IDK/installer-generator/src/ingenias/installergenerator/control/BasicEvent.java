/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ingenias.installergenerator.control;

/**
 *
 * @author Carlos
 */
public class BasicEvent extends AbstractEvent {
    private Object data;

    public BasicEvent(EventId id, Object data){
        this.setId(id);
        this.data=data;
    }
    public BasicEvent(EventId id){
        this.setId(id);
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }
}
