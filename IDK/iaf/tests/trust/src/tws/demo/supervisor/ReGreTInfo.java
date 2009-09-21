/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tws.demo.supervisor;

import java.util.HashMap;
import regretsystem.Trust;
import regretsystem.db.IDB;
import regretsystem.db.ODB;

/**
 *
 * @author carlos
 */
public class ReGreTInfo {
    private IDB idb = new IDB();
    private ODB odb = new ODB();
    private HashMap<String, Trust> confianzas = new HashMap<String, Trust>();

    /**
     * @return the idb
     */
    public IDB getIdb() {
        return idb;
    }

    /**
     * @param idb the idb to set
     */
    public void setIdb(IDB idb) {
        this.idb = idb;
    }

    /**
     * @return the odb
     */
    public ODB getOdb() {
        return odb;
    }

    /**
     * @param odb the odb to set
     */
    public void setOdb(ODB odb) {
        this.odb = odb;
    }

    /**
     * @return the confianzas
     */
    public HashMap<String, Trust> getConfianzas() {
        return confianzas;
    }

    /**
     * @param confianzas the confianzas to set
     */
    public void setConfianzas(HashMap<String, Trust> confianzas) {
        this.confianzas = confianzas;
    }



}
