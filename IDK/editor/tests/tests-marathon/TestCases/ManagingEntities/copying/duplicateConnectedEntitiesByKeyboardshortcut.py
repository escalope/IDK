#{{{ Marathon Fixture
from default import *
#}}} Marathon Fixture

def test():
	java_recorded_version = '1.6.0_12'
	if window('INGENIAS Development Kit'):
		click('ProjectsTree', 'Project')
		select('ProjectsTree', '[Project]')
		rightclick('ProjectsTree', 'Project')
		select_menu('Add EnvironmentModel')
		select('ProjectsTree', '[Project]')

		if window('New graph'):
			select('OptionPane.textField', 'e1')
			click('OK')
		close()

		doubleclick('ProjectsTree', 'e1')
		select('ProjectsTree', '[e1]')
		click('Agent')
		click('e1', 35, 12)
		keystroke('e1', 'Ctrl+C')
		keystroke('e1', 'Ctrl+V')
		drag('e1', 36, 23, 203, 115)
		click('Application')
		drag('e1', 49, 15, 73, 180)
		drag('e1', 188, 119, 61, 186)
	close()

	if window('Valid Relationships'):
		click('OK', 23, 5)
	close()

	if window('INGENIAS Development Kit'):
		drag('e1', 20, 76, 229, 227)
		keystroke('e1', 'Ctrl+C')
		keystroke('e1', 'c')
		click('ProjectsTree', 'Project')
		select('ProjectsTree', '[Project]')
		select('ProjectsTree', '[Project]')
		rightclick('ProjectsTree', 'Project')
		select_menu('Add EnvironmentModel')
		select('ProjectsTree', '[Project]')

		if window('New graph'):
			select('OptionPane.textField', 'e2')
			click('OK')
		close()

		doubleclick('ProjectsTree', 'e2')
		select('ProjectsTree', '[e2]')
		click('e2', 181, 94)
		keystroke('e2', 'Ctrl+V')
		drag('e2', 195, 105, 289, 78)
		drag('e2', 77, 192, 151, 252)
	close()


