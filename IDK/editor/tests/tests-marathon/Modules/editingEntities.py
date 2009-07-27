def addMethodToMethodList(name):
	from marathon.playback import *
	if window('INGENIAS Development Kit'):
		#add new value to the list
		rightclick('valueListMethods')
		select_menu('Add new element')
		#Choose default type in the combo
		if window('Select one'):
			click('Yes')
		close()
		#Fill in the method definition form with a name and a result type
		if window('Editing'):			
			select('Name', name)
			keystroke('Name','\n')
			keystroke('Name','\n')
			keystroke('Name','\n')
			select('Result', 'java.lang.String\n')			
			click('Accept')
		close()
	close()
	
def addMethodToMethodListMC(name,call):
	from marathon.playback import *
	if window('INGENIAS Development Kit'):
		#add new value to the list
		rightclick('valueListMethods'+call)
		select_menu('Add new element'+call)
		#Choose default type in the combo
		if window('Select one'):
			click('Yes')
		close()
		#Fill in the method definition form with a name and a result type
		if window('Editing'):			
			select('Name', name)
			keystroke('Name','\n')
			keystroke('Name','\n')
			keystroke('Name','\n')
			select('Result', 'java.lang.String')
			#Accept button is selected to force a focus lost
			#from other fields			
			click('Accept')
		close()
	close()
