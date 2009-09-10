/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trabajomaster.demo;

import java.io.Serializable;
import regretsystem.WitnessTrust;

/**
 *
 * @author carlos
 */
public class ReputacionInfo  implements Serializable {

    private String colaboradorId;
    private WitnessTrust trust;

    /**
     * @return the colaboradorId
     */
    public String getColaboradorId() {
        return colaboradorId;
    }

    /**
     * @param colaboradorId the colaboradorId to set
     */
    public void setColaboradorId(String colaboradorId) {
        this.colaboradorId = colaboradorId;
    }

    /**
     * @return the trust
     */
    public WitnessTrust getTrust() {
        return trust;
    }

    /**
     * @param trust the trust to set
     */
    public void setTrust(WitnessTrust trust) {
        this.trust = trust;
    }

}
