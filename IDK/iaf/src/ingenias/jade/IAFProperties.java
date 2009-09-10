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

package ingenias.jade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IAFProperties {


	public static final int TIMEOUT=0;
	public static final int INTERNAL_FAILURE = 1;
	public static final int NO_AGENTS_FOUND = 2;
        public static final int NONE = 2;
        
	static {
		readProperties();	
	}

	public static void readProperties(){
		File input=new File("config/Properties.prop");
		if (input.exists()){
			FileInputStream fis;
			try {
				fis = new FileInputStream(input);
				System.getProperties().load(fis);
				if (!System.getProperties().containsKey("ingenias.jade.LogFileOn"))
					System.getProperties().put("ingenias.jade.LogFileOn", "false");
				if (!System.getProperties().containsKey("ingenias.jade.GUIOn"))
					System.getProperties().put("ingenias.jade.GUIOn", "true");
				if (!System.getProperties().containsKey("ingenias.jade.GarbageCollectionInterval"))
					System.getProperties().put("ingenias.jade.GarbageCollectionInterval", "10");
				if (!System.getProperties().containsKey("ingenias.jade.GarbageCollectionEnabled"))
					System.getProperties().put("ingenias.jade.GarbageCollectionEnabled", "true");
				if (!System.getProperties().containsKey("ingenias.jade.IntrospectionOn"))
					System.getProperties().put("ingenias.jade.IntrospectionOn", "false");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.getProperties().put("ingenias.jade.LogFileOn", "false");
			System.getProperties().put("ingenias.jade.GUIOn", "true");
			System.getProperties().put("ingenias.jade.GarbageCollectionInterval", "10");
			System.getProperties().put("ingenias.jade.GarbageCollectionEnabled", "true");
			System.getProperties().put("ingenias.jade.IntrospectionOn", "false");
		}
		System.err.println("fileon:"+getLogFileOn());
	}

	public static boolean getGraphicsOn() {
		return System.getProperties().get("ingenias.jade.GUIOn").equals("true");
	}

	public static void setGraphicsOn(boolean value) {
		if (value)
			System.getProperties().put("ingenias.jade.GUIOn","true");
		else
			System.getProperties().put("ingenias.jade.GUIOn","false");
	}

	public static boolean getLogFileOn() {
		return System.getProperties().get("ingenias.jade.LogFileOn").equals("true");
	}
	
	public static boolean getIntrospection() {
		return System.getProperties().get("ingenias.jade.IntrospectionOn").equals("true");
	}
	
	public static void setIntrospection(boolean bool) {
		System.getProperties().put("ingenias.jade.IntrospectionOn",""+bool);
	}


	public static int getGarbageCollectionInterval() {
		return Integer.parseInt((String) System.getProperties().get("ingenias.jade.GarbageCollectionInterval"));
	}	

	public static void setGarbageCollectionInterval(int interval) {
		System.getProperties().put("ingenias.jade.GarbageCollectionInterval",""+interval);
	}

	public static void setGarbageCollectionEnabled(boolean value) {
		System.getProperties().put("ingenias.jade.GarbageCollectionEnabled",""+value);
	}

	public static boolean getGarbageCollectionEnabled() {
		return System.getProperties().get("ingenias.jade.GarbageCollectionEnabled").equals("true");
	}


}
