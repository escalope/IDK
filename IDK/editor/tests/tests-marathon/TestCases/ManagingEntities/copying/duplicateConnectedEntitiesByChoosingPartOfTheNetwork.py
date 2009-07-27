#{{{ Marathon Fixture
from default import *
#}}} Marathon Fixture

def test():
	java_recorded_version = '1.6.0_12'
	#Create an environment diagram
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
		click('Agent')
		drag('e1', 19, 10, 137, 95)
		click('InternalApplication')
		click('e1', 65, 24)
	close()
	if window('Valid Relationships'):
		click('Cancel')
	close()
	if window('INGENIAS Development Kit'):
		drag('e1', 63, 23, 141, 113)
	close()
	if window('Valid Relationships'):
		click('OK')
	close()
	if window('INGENIAS Development Kit'):
		click('e1', 110, 77)
		click('e1', 132, 125, 'Ctrl')
		keystroke('e1', 'Ctrl+C')
		click('e1', 215, 258)
		keystroke('e1', 'Ctrl+V')
		click('e1', 103, 19)
	close()
