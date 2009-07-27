#{{{ Marathon Fixture
from default import *
#}}} Marathon Fixture

def test():
	java_recorded_version = '1.6.0_12'
	from createEnvironmentDiagram import generateEmptyEnvironmentDiagram
	generateEmptyEnvironmentDiagram('e1')
	if window('INGENIAS Development Kit'):
		#Project entry created but not opened
		assert_content('ProjectsTree', [ ['Project', 'e1']])		
		assert_p('DiagramsPanel', 'Content[0].size', '0')
		#Project entry opened
		doubleclick('ProjectsTree', 'e1')
		assert_p('DiagramsPanel', 'Content[0].size', '1')
	close()
