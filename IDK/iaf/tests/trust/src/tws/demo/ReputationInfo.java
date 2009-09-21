/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tws.demo;

import java.io.Serializable;
import regretsystem.WitnessTrust;

/**
 *
 * @author carlos
 */
public class ReputationInfo  implements Serializable {

    private String collaboradorId;
    private WitnessTrust trust;

    /**
     * @return the colaboradorId
     */
    public String getCollaboradorId() {
        return collaboradorId;
    }

    /**
     * @param colaboradorId the colaboradorId to set
     */
    public void setCollaboradorId(String collaboradorId) {
        this.collaboradorId = collaboradorId;
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
