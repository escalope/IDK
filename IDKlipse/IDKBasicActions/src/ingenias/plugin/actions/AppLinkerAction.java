//    IDKLipse is a plugin for the development of Multi-Agent Systems based 
//    on the INGENIAS Development Kit, which is distributed under the terms of 
//    the GPL.
//
//   Copyright (C) 2009  Jorge J. Gomez-Sanz & Ivan Garcia-Magarino
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.

package ingenias.plugin.actions;

import ingenias.codeproc.HTMLDocumentGenerator;
import ingenias.codeproc.IAFGenerator;
import ingenias.editor.IDEState;
import ingenias.editor.ProjectProperty;
import ingenias.generator.browser.BrowserImp;

import java.io.FileNotFoundException;
import java.util.Properties;



public class AppLinkerAction extends INGENIASModuleAction {
	private static final String CONSOLE_NAME = "AppLinker Output";

	/**
	 * Constructor for Action1.
	 */
	public AppLinkerAction() {
		super(CONSOLE_NAME);
	}

	public void launchModuleAction(final IDEState ids, String pathProject)
	throws FileNotFoundException {
		ingenias.utils.applinker.AppLinker applinker;
		IAFGenerator jadegen = new IAFGenerator(new BrowserImp(ids));
		
		Properties props = jadegen.getProperties();
		
		ProjectProperty jadeProjectProperty = (ProjectProperty) props.get(jadegen.getName()+":jadeproject");
		jadeProjectProperty.value= pathProject;
		ids.prop.putAll(props);		

		for (Object key: ids.prop.keySet()){
			System.err.println(key+"--"+ props.get(key));
		};	
		
		applinker = new ingenias.utils.applinker.AppLinker(new BrowserImp(ids));

		applinker.run();


	}

}
