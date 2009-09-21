/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tws.demo;

import java.io.Serializable;


/**
 *
 * @author carlos
 */
public class AlphaInspection  implements Serializable {

    private SourceInfo fuenteInfo;
    private boolean deObjectCriteria = false;
    private boolean deSubjectCriteria = false;
    private boolean deProcessCriteria = false;

    /**
     * @return the fuenteInfo
     */
    public SourceInfo getFuenteInfo() {
        return fuenteInfo;
    }

    /**
     * @param fuenteInfo the fuenteInfo to set
     */
    public void setFuenteInfo(SourceInfo fuenteInfo) {
        this.fuenteInfo = fuenteInfo;
    }

    /**
     * @return the deObjectCriteria
     */
    public boolean isDeObjectCriteria() {
        return deObjectCriteria;
    }

    /**
     * @param deObjectCriteria the deObjectCriteria to set
     */
    public void setDeObjectCriteria(boolean deObjectCriteria) {
        this.deObjectCriteria = deObjectCriteria;
    }

    /**
     * @return the deSubjectCriteria
     */
    public boolean isDeSubjectCriteria() {
        return deSubjectCriteria;
    }

    /**
     * @param deSubjectCriteria the deSubjectCriteria to set
     */
    public void setDeSubjectCriteria(boolean deSubjectCriteria) {
        this.deSubjectCriteria = deSubjectCriteria;
    }

    /**
     * @return the deProcessCriteria
     */
    public boolean isDeProcessCriteria() {
        return deProcessCriteria;
    }

    /**
     * @param deProcessCriteria the deProcessCriteria to set
     */
    public void setDeProcessCriteria(boolean deProcessCriteria) {
        this.deProcessCriteria = deProcessCriteria;
    }

}
