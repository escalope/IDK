///    IDKLipse is a plugin for the development of Multi-Agent Systems based 
//    on the INGENIAS Development Kit, which is distributed under the terms of 
//    the GPL.
//
//   Copyright (C) 2009  Jorge J. Gomez-Sanz 
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


package ingenias.plugin.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.internal.console.ConsoleView;
import org.eclipse.jdt.ui.JavaUI;


/**
 *  This class is meant to serve as an example for how various contributions 
 *  are made to a perspective. Note that some of the extension point id's are
 *  referred to as API constants while others are hardcoded and may be subject 
 *  to change. 
 */
public class IDKPerspective implements IPerspectiveFactory {

	private IPageLayout factory;

	public IDKPerspective() {
		super();
	}

	
	public void createInitialLayout(IPageLayout factory) {
		this.factory = factory;
		addViews();
		addPerspectiveShortcuts();
		addViewShortcuts();
	}

	private void addViews() {
		
		IFolderLayout right =
			factory.createFolder(
				"right", //NON-NLS-1
				IPageLayout.RIGHT,
				0.80f,
				factory.getEditorArea());
		
		IFolderLayout left =
			factory.createFolder(
				"left", //NON-NLS-1
				IPageLayout.LEFT,
				0.30f,
				factory.getEditorArea());
		left.addView("org.eclipse.jdt.ui.PackageExplorer");

		IFolderLayout bottomRight =
			factory.createFolder(
				"bottomright", //NON-NLS-1
				IPageLayout.BOTTOM,
				0.5f,
			"right");
		
		
		right.addPlaceholder("bottomright");

		right.addView("ingenias.plugin.views.SpecificationView"); //NON-NLS-1
		right.addView("ingenias.plugin.views.IDKAntRunView");
		
		bottomRight.addView("ingenias.plugin.views.SpecificationObjectView"); //NON-NLS-1
		
		
		IFolderLayout bottom =
			factory.createFolder(
				"bottom", //NON-NLS-1				
				IPageLayout.BOTTOM,
				0.8f,
				factory.getEditorArea());
		bottom.addView("ingenias.plugin.views.SearchView");
		
		
		bottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
	}

	private void addPerspectiveShortcuts() {
		factory.addPerspectiveShortcut("ingenias.plugin.views.SpecificationView"); //NON-NLS-1
		
	}

	private void addViewShortcuts() {
		factory.addShowViewShortcut("ingenias.plugin.views.SpecificationView"); //NON-NLS-1
	
	}

}
