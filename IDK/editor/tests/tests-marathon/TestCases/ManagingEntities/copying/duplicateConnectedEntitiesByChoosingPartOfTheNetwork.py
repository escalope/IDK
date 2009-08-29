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
		click('e1', 126, 145)
		assert_p('e1', 'Component.SelectionCells', '[EPerceivestargetRole0:EPerceivestargetRole]')		
		#click('e1', 298, 132)
		#click('e1', 122, 137)		
		keystroke('e1', 'Ctrl+C')
	    #As a result, the whole relationship is selected		
		assert_p('e1', 'Component.SelectionCells', '[EPerceivestargetRole0:EPerceivestargetRole, Application0, 0:EPerceives, EPerceivessourceRole0:EPerceivessourceRole, Agent0]')
		#Now, an edge and one end of the relationship are chosen
		click('e1', 267, 173)
		click('e1', 251, 150)
		click('e1', 118, 140)
		click('e1', 141, 173, 'Ctrl')
		assert_p('e1', 'Component.SelectionCells', '[EPerceivestargetRole0:EPerceivestargetRole, Application0]')
		#After selected, we copy the whole by means of the keyboard shortcut
		keystroke('e1', 'Ctrl+C')
		keystroke('e1', 'Ctrl+V')
		#Pasted elements are selected
		#assert_p('e1', 'Component.SelectionCells', '[Agent0, EPerceivessourceRole0:EPerceivessourceRole, EPerceivestarget, 0:EPerceives, Agent0, EPerceivestargetRole0:EPerceivestargetRole, EPerceivessource,  Application0, Application0]')
		#Every element is duplicated
		assert_p('e1', 'Component.Roots', '[Agent0, Application0, 0:EPerceives, EPerceivessourceRole0:EPerceivessourceRole, EPerceivestargetRole0:EPerceivestargetRole, Agent0, Application0, 0:EPerceives, EPerceivessourceRole0:EPerceivessourceRole, EPerceivestargetRole0:EPerceivestargetRole]')
	close()