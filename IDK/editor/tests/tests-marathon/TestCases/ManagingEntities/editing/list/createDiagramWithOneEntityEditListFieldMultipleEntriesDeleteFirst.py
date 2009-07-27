#{{{ Marathon Fixture
from default import *
#}}} Marathon Fixture

def test():
	java_recorded_version = '1.6.0_12'
	from createEnvironmentDiagram import *
	from editingEntities import *
	if window('INGENIAS Development Kit'):
		generateEmptyEnvironmentDiagram('e1')
		doubleclick('ProjectsTree', 'e1')
		createInternalApplicationAndEdit()
		addMethodToMethodList('n1')
		addMethodToMethodList('n2')
		addMethodToMethodList('n3')
		#Check the list has the new method
		select('valueListMethods', '')
		assert_content('valueListMethods', [ ['n1','n2','n3']])
		#exit the edit dialog
		click('Accept')
		#enter again the edit dialog
		rightclick('e1', 84, 27)
		select_menu('Edit1')
		#Check the list member is still there
		#Obs: due to the naming strategy of marathon
		#references to objects named "valueListMethods" in different windows
		#are modified with a suffix to prevent any confusion (I guess)
		#Hence. The first time "valueListMethods" is referred in a window
		#no suffixes are needed. When the windows is closed a reopened,
		#the element is renamed to "valueListMethods1"
		select('valueListMethods1', '[n1]')		
		assert_content('valueListMethods1', [ ['n1','n2','n3']])
		#Remove the current list member
		select('valueListMethods1', '[n1]')
		rightclick('valueListMethods1', 'n1')
		select_menu('Remove selected element')
		select('valueListMethods1', '')
		#Verify now there is none
		assert_p('valueListMethods1', 'ItemCount', '2')
		assert_content('valueListMethods1', [ ['n2','n3']])
		#exit the edit dialog
		click('Accept1')
		#enter again the edit dialog and check again no list elements are 
		#shown
		rightclick('e1', 84, 27)
		select_menu('Edit2')
		select('valueListMethods2', '')
		assert_p('valueListMethods2', 'ItemCount', '2')
		assert_content('valueListMethods2', [ ['n2','n3']])
		click('Accept2')
	close()
