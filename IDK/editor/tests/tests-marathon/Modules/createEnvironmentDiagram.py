def generateEmptyEnvironmentDiagram(name):
	from marathon.playback import *
	if window('INGENIAS Development Kit'):
		click('ProjectsTree', 'Project')
		select('ProjectsTree', '[Project]')
		rightclick('ProjectsTree', 'Project')
		select_menu('Add EnvironmentModel')
		select('ProjectsTree', '[Project]')
		if window('New graph'):
			select('OptionPane.textField', name)
			click('OK')
		close()		
	close()
	
def createEnvironmentEntitiesAndConnectThem():
	from marathon.playback import *
	if window('INGENIAS Development Kit'):
		doubleclick('ProjectsTree', 'e1')
		select('ProjectsTree', '[e1]')
		click('Agent')
		click('Application')
		drag('e1', 49, 17, 160, 168)
		drag('e1', 21, 28, 149, 175)
		if window('Valid Relationships'):
			click('OK')
		close()
	close()	
	
def createAgent(diagramName):
	from marathon.playback import *
	if window('INGENIAS Development Kit'):
		doubleclick('ProjectsTree', diagramName)
		click('Agent')			
	close()
	
def createAgentAndEdit():
	from marathon.playback import *
	if window('INGENIAS Development Kit'):	
		click('Agent')
		click('e1', 31, 20)
		rightclick('e1', 31, 20)
		select_menu('Edit1')		
	close()
	
def createInternalApplicationAndEdit():
	from marathon.playback import *
	if window('INGENIAS Development Kit'):	
		click('InternalApplication')
		click('e1', 31, 20)
		rightclick('e1', 31, 20)
		select_menu('Edit1')		
	close()
