from ingenias.editor import IDE
from marathon.playback import *

class Fixture:
	def start_application(self):
		args = ["testing"]
		IDE.main(args)

	def teardown(self):
		pass

	def setup(self):
		self.start_application()

	def test_setup(self):
		pass

fixture = Fixture()
