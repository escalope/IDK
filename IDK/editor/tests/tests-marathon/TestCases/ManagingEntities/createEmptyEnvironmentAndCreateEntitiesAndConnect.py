
#{{{ Marathon Fixture
from default import *
#}}} Marathon Fixture

def test():
	java_recorded_version = '1.6.0_12'
	from createEnvironmentDiagram import *	
	if window('INGENIAS Development Kit'):
		generateEmptyEnvironmentDiagram('e1')
		#Creates two entites and connects them
		createEnvironmentEntitiesAndConnectThem()
		#The panel shows two entities
		assert_p('e1', 'Component.Model.Roots', '[Agent0, Application0, , , ]')
		#The entities are present in the object tree
		doubleclick('ObjectsTree', 'INGENIASObject')
		doubleclick('ObjectsTree', 'Autonomous_entity')
		doubleclick('ObjectsTree', 'Agent')
		click('ObjectsTree', 'Agent0')
		assert_p('ObjectsTree', 'Text', 'Agent0', 'Agent0')
		doubleclick('ObjectsTree', 'AgentComponent')
		doubleclick('ObjectsTree', 'Application')
		click('ObjectsTree', 'Application0')		
	close()
	
