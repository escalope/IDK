/*
    Copyright (C) 2013 Jorge Gomez Sanz

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
package ingenias.codeproc.macros;

import java.util.Vector;

import ingenias.exception.TransformationException;
import ingenias.generator.browser.Browser;

public abstract class Macro {
	Browser browser=null;
	public Macro(Browser myBrowser) {
		this.browser=myBrowser;
	}
	public Browser getBrowser() {
		return browser;
	}
	
	
	public abstract Vector<String>  apply() throws TransformationException;
	
	

}