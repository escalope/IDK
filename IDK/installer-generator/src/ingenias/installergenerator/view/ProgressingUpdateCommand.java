/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ingenias.installergenerator.view;

/**
 *
 * @author Carlos
 */
public abstract class ProgressingUpdateCommand extends UpdateCommand{
    public abstract void setAdvance(int percent);
}
