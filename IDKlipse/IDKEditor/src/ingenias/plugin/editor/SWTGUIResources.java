//    IDKLipse is a plugin for the development of Multi-Agent Systems based 
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
package ingenias.plugin.editor;

import ingenias.editor.GUIResources;

public class SWTGUIResources extends GUIResources{
	
	
	@Override
	public void focusPropertiesPane(String name) {
		// TODO Auto-generated method stub
		//super.focusPropertiesPane(name);
	}

	@Override
	public void focusSearchPane() { // To disable IDK specific actions and let SWT handle it
		// TODO Auto-generated method stub
	//	super.focusSearchPane();
	}

	public SWTGUIResources() {
		super();
		// TODO Auto-generated constructor stub
	}

}
