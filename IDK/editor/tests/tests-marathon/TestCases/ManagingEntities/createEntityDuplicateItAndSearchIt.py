#{{{ Marathon Fixture
from default import *
#}}} Marathon Fixture
#create an entity in a diagram and copying it to another in two different ways
def test():
	java_recorded_version = '1.6.0_12'
	from createEnvironmentDiagram import *
	from editingEntities import *
	if window('INGENIAS Development Kit'):
		generateEmptyEnvironmentDiagram('e1')
		#Create two agents in diagram e1
		createAgent('e1')
		#move it so that the new one can be seen
		drag('e1', 17, 11, 151, 155)		
		createAgent('e1')
		#create the target diagram where old entities will be put
		generateEmptyEnvironmentDiagram('e2')		
		#Select created entity
		doubleclick('ObjectsTree', 'INGENIASObject')
		doubleclick('ObjectsTree', 'Autonomous_entity')
		doubleclick('ObjectsTree', 'Agent')		
		click('ObjectsTree', 'Agent0')
		select('ObjectsTree', '[Agent0]')
		#Gain access to operations over the entity
		rightclick('ObjectsTree', 'Agent0')
		#Search occurrences of the entity in different diagrams
		select_menu('Search occurrences')	
		#There should be only one entry
		#The target diagram is opened
		doubleclick('ProjectsTree', 'e2')
		select('ProjectsTree', '[e2]')
		select('ObjectsTree', '[Agent0]')
		rightclick('ObjectsTree', 'Agent0')
		#and a copy is inserted from the objects tree
		select_menu('Add to current graph1')
		#now, occurrences are searched again
		select('ObjectsTree', '[Agent0]')
		rightclick('ObjectsTree', 'Agent0')		
		select_menu('Search occurrences2')	
		#The following will make the focus go from one diagram to the other
		click('EditorPane', 57, 42)
		assert_p('DiagramsPanel', 'Text', 'e1')
		assert_p('e1', 'Component.SelectionCell', 'Agent0')
		#The following should work, but it does not. Instead, we use the raw method
		#click('EditorPane', 'http://app/e1/Agent0,17')		
		click('EditorPane', 63, 56)
		assert_p('DiagramsPanel', 'Text', 'e2')
		assert_p('e2', 'Component.SelectionCell', 'Agent0')
		#The following should work, but it does not. Instead, we use the raw method
 		#doubleclick('EditorPane', 'http://app/e2/Agent0,20')
	close()
		
	