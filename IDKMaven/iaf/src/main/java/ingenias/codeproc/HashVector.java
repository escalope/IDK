/*
    Copyright (C) 2005 Jorge Gomez Sanz

    This file is part of INGENIAS Agent Framework, an agent infrastructure linked
    to the INGENIAS Development Kit, and availabe at http://grasia.fdi.ucm.es/ingenias or
    http://ingenias.sourceforge.net. 

    INGENIAS Agent Framework is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    INGENIAS Agent Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with INGENIAS Agent Framework; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/
package ingenias.codeproc;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 * A Hashtable whose values are vectors. It does not extend a hashtable, but provides similar
 * functions.
 * 
 * @author jj
 *
 */
public class HashVector {
	Hashtable ht = new Hashtable();
	
	/**
	 * Adds a new value to the row identified by the key.  If the key did not exist before,
	 * a new one is created. If it did exist, it is simply added 
	 * 
	 * @param key The key to be used as index
	 * @param value	The value to be associated to the key
	 */
	void put(Object key, Object value) {
		if (ht.containsKey(key)) {
			Vector v = (Vector) ht.get(key);
			if (!v.contains(value)) {
				v.add(value);
			}
		}
		else {
			Vector v = new Vector();
			v.add(value);
			ht.put(key, v);
		}
	}
	
	/**
	 * It obtains the vector of elements associated to the key
	 * @param key An object representing the key
	 * @return The vector of values
	 */
	Vector get(Object key) {
		return (Vector)this.ht.get(key);
	}
	
	/**
	 * It obtains all the keys defined so far
	 * 
	 * @return An iterator of the key objects
	 */
	Iterator keys() {
		return this.ht.keySet().iterator();
	}
}
