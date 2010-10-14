/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ingenias.installergenerator.model;

import java.util.HashMap;
import java.util.prefs.Preferences;

/**
 *
 * @author Carlos
 */
public class Context extends HashMap<String, Object> {

    public static Context getInstance() {
        return instance;
    }
    private static Context instance = new Context();

    private Context() {
    }

    @Override
    public Object get(Object key) {
        Object result = null;
        if (!super.containsKey(key)) {
            Preferences pref = Preferences.userRoot().node("/grasia/ingenias/project-installer");
            result = pref.get((String)key, "");
            if(((String)result).equals("")){
                result = null;
            }else{
                this.put((String)key, result);
            }
        }else{
            result = super.get(key);
        }

        return result;
    }
}
