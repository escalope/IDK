#{{{ Marathon Fixture
from default import *
#}}} Marathon Fixture

def test():
	java_recorded_version = '1.6.0_12'
	from createEnvironmentDiagram import generateEmptyEnvironmentDiagram
	

	if window('INGENIAS Development Kit'):
		#Create project entry
		generateEmptyEnvironmentDiagram('e1')
		#Open project entry
		click('ProjectsTree', 'e1')
		select('ProjectsTree', '[e1]')
		#Remove project entry
		rightclick('ProjectsTree', 'e1')
		select_menu('remove package/model')		
		if window('removing package'):
			click('Yes')
		close()
		#No project entries and no opened entries
		assert_content('ProjectsTree', [ ['Project']])
		assert_p('DiagramsPanel', 'Content[0].size', '0')		
	close()
		
	


