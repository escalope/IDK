/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tws.demo;

import java.io.Serializable;
import regretsystem.EvaluationValue;

/**
 *
 * @author carlos
 */
public class SourceInfo implements Serializable {

    private String collaboradorId;
    private EvaluationValue eval;
    private Object data;

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
     * @return the eval
     */
    public EvaluationValue getEval() {
        return eval;
    }

    /**
     * @param eval the eval to set
     */
    public void setEval(EvaluationValue eval) {
        this.eval = eval;
    }

    /**
     * @return the datos
     */
    public Object getData() {
        return data;
    }

    /**
     * @param datos the datos to set
     */
    public void setData(Object data) {
        this.data = data;
    }

}
