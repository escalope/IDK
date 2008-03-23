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

import ingenias.editor.Log;
import ingenias.editor.ProjectProperty;
import ingenias.exception.CannotLoad;
import ingenias.exception.DamagedFormat;
import ingenias.exception.UnknowFormat;
import ingenias.generator.datatemplate.Sequences;
import ingenias.generator.datatemplate.Var;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Vector;

/**
 *  This class implements an IDK module for the production of files for integration with the 
 *  ACLAnalyser
 *
 *@author     Jorge Gomez
 *@created    1 de December of 2007
 */

public class IAMGenerator
extends ingenias.editor.extension.BasicCodeGeneratorImp {

	public IAMGenerator(String file, String[] templateFileArray) throws UnknowFormat, DamagedFormat,
	CannotLoad {
		super(file, templateFileArray);

	}

	public IAMGenerator(String[] templateFileArray) throws UnknowFormat, DamagedFormat,
	CannotLoad {
		super(templateFileArray);

	}
	static boolean error = false; // An error has been raised. It indicates the process should stop

	AnaliserProtocolGenerator ig = new AnaliserProtocolGenerator(this);


	/**
	 *  Initialises code generation and prepares the module to receive data from an external file
	 *
	 *@param  file             Path to file containing INGENIAS specification
	 *@exception  Exception    Error accessing any file or malformed XML exception
	 */
	public IAMGenerator(String file) throws ingenias.exception.UnknowFormat,
	ingenias.exception.DamagedFormat,
	ingenias.exception.CannotLoad,
	java.io.FileNotFoundException {

		super(file);

		try {
			this.addTemplate("templates/protocol.xml");
		} catch (java.io.FileNotFoundException fne) {
			Log.getInstance().logERROR(fne.getMessage());
			this.fatalError();
		}
	}

	/**
	 * Raise an error so that at the end of the analysis there is not code generation
	 *
	 */
	static void fatalError() {
		error = true;
		new Exception("Fatal error triggered").printStackTrace();
	}
	
	/**
	 * Initialises code generation and prepares the module to receive data from the IDK
	 *
	 * @throws java.io.FileNotFoundException One of the assigned templates could not be found
	 */
	public IAMGenerator() throws java.io.FileNotFoundException {
		try {
			this.addTemplate("templates/protocol.xml");
		} catch (java.io.FileNotFoundException fne) {
			Log.getInstance().logERROR(fne.getMessage());
			this.fatalError();
		}

	}
	
	/**
	 * Obtains the value of the error flag
	 *
	 * @return true if there was an error previously or false if there was none
	 */
	boolean getError() {
		return this.error;
	}

	/**
	 * There is only one default property of this module. It is called "jadeout" and represents the path
	 * to the folder where the generated code is going to be stored
	 *
	 */
	public Vector<ProjectProperty> defaultProperties() {
		Vector<ProjectProperty> pp = new Vector<ProjectProperty>();

		pp.add(new ingenias.editor.ProjectProperty(this.getName(), "analyserfolder", 
				"Main folder of the ACLAnaliser folder",
				"analyserfolder", "The folder that contains the ACLAnalyser"));

		return pp;
	}

	/**
	 * It obtains the name associated to this module
	 *
	 */
	public String getName() {
		return "Ingenias Analyser Module";
	}



	/**
	 * It obtains a description of this module
	 */
	public String getDescription() {
		return "It generates communication and behavior infrastructure of MAS composed of JADE agents ";
	}



	

	public boolean verify() {
		error = false;
		if (this.getError() != true) {
			this.generate();
		}

		return this.getError() != true;
	}

	/**
	 * It produces the data structure needed to produce the templates. First, it generates the
	 * data structure for the definition of individual protocol per agents, then their actions, following their
	 * knowledge, next their resources (applications used by the agents to perform tasks).
	 *
	 */
	public Sequences generate() {
		this.error = false;
		Sequences p = new Sequences();
		try {
			p.addVar(new Var("analyserfolder",
					this.getProperty("analyserfolder").value));			
			AnaliserProtocolGenerator ig=new AnaliserProtocolGenerator(this);
			
			ig.generateProtocolData(p);
				
			File outputFile=File.createTempFile("ing", "_output");
			FileOutputStream fos = new FileOutputStream(outputFile);
			fos.write(p.toString().getBytes());
			fos.close();

		} catch (Exception exp) {
			exp.printStackTrace();
		}

		return p;
	}


}
