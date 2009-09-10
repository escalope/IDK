/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trabajomaster.demo;

import java.io.Serializable;
import regretsystem.EvaluationValue;

/**
 *
 * @author carlos
 */
public class FuenteInfo implements Serializable {

    private String colaboradorId;
    private EvaluationValue eval;
    private Object datos;

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
    public Object getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(Object datos) {
        this.datos = datos;
    }

}
