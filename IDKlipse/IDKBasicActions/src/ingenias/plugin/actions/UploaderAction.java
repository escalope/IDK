//    IDKLipse is a plugin for the development of Multi-Agent Systems based 
//    on the INGENIAS Development Kit, which is distributed under the terms of 
//    the GPL.
//
//	  Copyright (C) 2009  Jorge J. Gomez-Sanz & Ivan Garcia-Magarino
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

import java.io.FileNotFoundException;
import java.util.Properties;

import ingenias.codeproc.IAFGenerator;
import ingenias.editor.IDEState;
import ingenias.editor.Log;
import ingenias.editor.ProjectProperty;
import ingenias.generator.browser.BrowserImp;
import ingenias.module.CodeUploader;
import ingenias.plugin.editor.DiagramEditor;

import org.apache.commons.logging.LogSource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;



public class UploaderAction extends INGENIASModuleAction{
	private static final String CONSOLE_NAME = "Code Uploader";

	/**
	 * Constructor for Action1.
	 */
	public UploaderAction() {
		super(CONSOLE_NAME);
	}

	public void launchModuleAction(final IDEState ids, String pathProject)
	throws FileNotFoundException {
		CodeUploader uploader;
		try {
			IAFGenerator jadegen = new IAFGenerator(new BrowserImp(ids));

			Properties props = jadegen.getProperties();
			
			ProjectProperty jadeProjectProperty = (ProjectProperty) props.get(jadegen.getName()+":jadeproject");
			jadeProjectProperty.value= pathProject;
			ids.prop.putAll(props);
			uploader = new CodeUploader(new BrowserImp(ids));
			uploader.run();
			Log.getInstance().log("Code upload complete");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}