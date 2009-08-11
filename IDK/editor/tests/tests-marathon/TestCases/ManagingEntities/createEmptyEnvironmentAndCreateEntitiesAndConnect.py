
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
		assert_p('e1', 'Component.Roots', '[Agent0, Application0, 0:EPerceives, EPerceivessourceRole0:EPerceivessourceRole, EPerceivestargetRole0:EPerceivestargetRole]')
		#The entities are present in the object tree
		click('ObjectsTree', 'INGENIASObject')
		click('ObjectsTree', 'Autonomous_entity')
		click('ObjectsTree', 'Agent')
		click('ObjectsTree', 'Agent0')
		assert_p('ObjectsTree', 'Text', 'Agent0', 'Agent0')
		click('ObjectsTree', 'AgentComponent')
		click('ObjectsTree', 'Application')
		click('ObjectsTree', 'Application0')		
	close()
	
