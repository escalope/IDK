/*
    Copyright (C) 2007 Jorge Gomez Sanz

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
package ingenias.jade.components;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

public class HastableMultipleEntries<K,V> extends Hashtable<K,Collection<V>> {

	public HastableMultipleEntries() {}

	public HastableMultipleEntries(int initialCapacity) {
		super(initialCapacity);
	}

	public HastableMultipleEntries(Map t) {
		super(t);
	}

	public HastableMultipleEntries(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public synchronized Collection<V> put(K key, V value) {
		Collection<V> insertion=null;
		if (containsKey(key)){
			insertion=get(key);
		} else {
			insertion=new Vector<V>();
			this.put(key,insertion);
		}		
		insertion.add(value);
		// TODO Auto-generated method stub
		return null;
	}


	public synchronized Collection<V> remove(Object key) {		
		return super.remove(key);
	}
	
	
	
	

}
