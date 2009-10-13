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
package ingenias.genproject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProjectGenerator {
	
	public static void main(String args[]) throws IOException{
		FileOutputStream fos=new FileOutputStream(new File(args[0]+"/spec/specification.xml"));
		fos.write(("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+
"<project cid=\"0\" version=\"1.1\">\n"+
"<projectproperties>\n"+
"<projectproperty id=\"jadeout\" module=\"Ingenias Agent Framework generator\"  name=\"JADE generated output folder\" value=\"gensrc\"  tooltip=\"The folder that will hold generated JADE agents\" />\n"+
"<projectproperty id=\"proysrc\" module=\"Ingenias Agent Framework generator\"  name=\"Main source folder for the project\" value=\"src\"  tooltip=\"The folder containing the sources of the project\" />\n"+
"<projectproperty id=\"jadeproject\" module=\"Ingenias Agent Framework generator\"  name=\"JADE main project folder\" value=\""+args[0]+"\"  tooltip=\"The folder that will contain the project for this development\" />\n"+
"<projectproperty id=\"jadeperm\" module=\"Ingenias Agent Framework generator\"  name=\"JADE generate only once folder\" value=\"permsrc\"  tooltip=\"The folder that will hold generated elements that should not be regenerated\" />\n"+
" <projectproperty id=\"htmldoc\" module=\"HTML Document generator\"  name=\"HTML document folder\" value=\""+args[0]+"/html\"  tooltip=\"The document folder that will contain HTML version of this specification\" />\n"+
"</projectproperties>\n"+
"<leafpackages>\n"+
"</leafpackages>\n"+
"<objects>\n"+
"</objects>\n"+
"<relationships>\n"+
"</relationships>\n"+
"<models>\n"+ 
"</models>\n"+
"</project>\n").getBytes());
		
	}

}
