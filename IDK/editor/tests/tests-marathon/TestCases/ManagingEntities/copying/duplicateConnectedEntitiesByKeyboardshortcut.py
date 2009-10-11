#{{{ Marathon Fixture
from default import *
from createEnvironmentDiagram import *	
#}}} Marathon Fixture
def test():
	java_recorded_version = '1.6.0_13'
	if window('INGENIAS Development Kit'):
		generateEmptyEnvironmentDiagram('e1')
		#Creates two entites and connects them
		createEnvironmentEntitiesAndConnectThem()
		#The panel shows two entities connected with a relationship
		assert_p('e1', 'Component.Roots', '[Agent0, Application0, 0:EPerceives, EPerceivessourceRole0:EPerceivessourceRole, EPerceivestargetRole0:EPerceivestargetRole]')
		#One single edge of the relationship is selected
		click('e1', 110, 80)
		assert_p('e1', 'Component.SelectionCells', '[EPerceivessourceRole0:EPerceivessourceRole]')		
		#click('e1', 298, 132)
		#click('e1', 122, 137)		
		keystroke('e1', 'Ctrl+C')
		#Target diagram is created
		generateEmptyEnvironmentDiagram('e2')
		doubleclick('ProjectsTree', 'e2')
		#The new set of entities is created into the other diagram		
		keystroke('e2', 'Ctrl+V')		
		#Every element is duplicated
		assert_p('e2', 'Component.Roots', '[Agent0, Application0, 0:EPerceives, EPerceivessourceRole0:EPerceivessourceRole, EPerceivestargetRole0:EPerceivestargetRole]')
		doubleclick('ProjectsTree', 'e1')
		assert_p('e1', 'Component.Roots', '[Agent0, Application0, 0:EPerceives, EPerceivessourceRole0:EPerceivessourceRole, EPerceivestargetRole0:EPerceivestargetRole]')
	close()
