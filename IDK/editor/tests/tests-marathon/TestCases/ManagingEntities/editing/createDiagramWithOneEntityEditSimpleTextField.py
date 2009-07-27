#{{{ Marathon Fixture
from default import *
#}}} Marathon Fixture

def test():
	java_recorded_version = '1.6.0_12'
	from createEnvironmentDiagram import *
	if window('INGENIAS Development Kit'):
		generateEmptyEnvironmentDiagram('e1')
		doubleclick('ProjectsTree', 'e1')
		createAgentAndEdit()
		assert_p('e1', 'Component.Model.Roots[0]', 'Agent0')		
		#Change the id of the entity
		select('id', 'Agent1')
		click('Accept')
		#Check new name on the panel
		assert_p('e1', 'Component.Model.Roots[0]', 'Agent1')
		#Check new name on the objects tree
		doubleclick('ObjectsTree', 'INGENIASObject')
		doubleclick('ObjectsTree', 'Autonomous_entity')
		doubleclick('ObjectsTree', 'Agent')
		assert_p('ObjectsTree', 'Text', 'Agent1', 'Agent1')
	close()
