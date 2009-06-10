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

package ingenias.plugin.wizard;

import org.eclipse.jdt.ui.wizards.CustomizedNewJavaProjectWizardPageOne;
import org.eclipse.jdt.ui.wizards.NewJavaProjectWizardPageOne;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

public class IDKProjectPageOne extends CustomizedNewJavaProjectWizardPageOne {

	
	private List availableTemplates;
	private int selectedEntry=-1;
	public IDKProjectPageOne() {
		// TODO Auto-generated constructor stub
	}

	
	public int getAvailableTemplates() {
		return selectedEntry;
	}


	protected Control createExampleSelectionControl(Composite control) {		
		GridData gd=new GridData();
		Group ncontrol=new Group(control,SWT.FILL );
		gd=new GridData();
		gd.grabExcessHorizontalSpace=true;
		gd.grabExcessVerticalSpace=true;
		ncontrol.setLayoutData(gd);
		ncontrol.setText("Available examples");
		GridLayout gl=new GridLayout(1,true);	 
		ncontrol.setLayout(gl);				
		
		gd=new GridData();
		gd.grabExcessHorizontalSpace=true;
		gd.grabExcessVerticalSpace=false;
		Label nlabel=new Label(ncontrol,SWT.LEFT);
		nlabel.setLayoutData(gd);
		nlabel.setText("If you wish to reuse an example, please select one of the following:");
		
		availableTemplates = new List(ncontrol, SWT.SINGLE | SWT.BORDER  |SWT.CENTER);
		gd=new GridData();
		gd.grabExcessHorizontalSpace=true;
		gd.horizontalAlignment=GridData.FILL;
		gd.grabExcessVerticalSpace=true;				
		//gd.horizontalAlignment=SWT.CENTER;
		availableTemplates.setLayoutData(gd);
		//  l.setBounds(50, 50, 75, 75);
		availableTemplates.add("Hello world agent");
		availableTemplates.add("Basic interaction");
		availableTemplates.add("Agent connected to a GUI");
		availableTemplates.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				//selectedEntry=availableTemplates.getSelectionIndex();		
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				selectedEntry=availableTemplates.getSelectionIndex();				
			}
		    });
						
		
		gd=new GridData();
		gd.grabExcessHorizontalSpace=true;
		gd.grabExcessVerticalSpace=false;
		nlabel=new Label(ncontrol,SWT.LEFT);
		nlabel.setLayoutData(gd);
		nlabel.setText("Select none to use the default configuration:");
		
		Button deselectButton= new Button(ncontrol, SWT.PUSH);
		gd=new GridData();
		gd.grabExcessHorizontalSpace=true;
		gd.horizontalAlignment=GridData.FILL;
		gd.grabExcessVerticalSpace=false;
		deselectButton.setLayoutData(gd);
		deselectButton.setText("Deselect all and use default");
		deselectButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				availableTemplates.deselectAll();
				selectedEntry=-1;
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				availableTemplates.deselectAll();
				selectedEntry=-1;
				
			}
		    });
		return ncontrol;
	}
	
	private GridLayout initGridLayout(GridLayout layout, boolean margins) {
		layout.horizontalSpacing= convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		layout.verticalSpacing= convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		if (margins) {
			layout.marginWidth= convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
			layout.marginHeight= convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		} else {
			layout.marginWidth= 0;
			layout.marginHeight= 0;
		}
		return layout;
	}

	
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);

		final Composite composite= new Composite(parent, SWT.NULL);
		composite.setFont(parent.getFont());
		composite.setLayout(initGridLayout(new GridLayout(1, false), true));
		composite.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

		// create UI elements
		Control nameControl= createNameControl(composite);
		nameControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		/*Control locationControl= createLocationControl(composite);
		locationControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Control layoutControl= createProjectLayoutControl(composite);
		layoutControl.setLayoutData(new GridData(GridData.FILL_HORIZO
		
		Control workingSetControl= createWorkingSetControl(composite);
		workingSetControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));*/

		Control jreControl= createJRESelectionControl(composite);
		jreControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Control templatesControl=createExampleSelectionControl(composite);
		templatesControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	

		//Control infoControl= createInfoControl(composite);
		//infoControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		setControl(composite);
	}




}
