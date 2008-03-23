
License
-------

The IDK and its different modules (the INGENIAS Agent Framework, the AppLinker, the HTMLDoc, the reflections module, and the ATAI) have been developed and distributed under the GPL license. A copy of this license can be found on the licenses folder.


QUICK GUIDE
-----------

1. Install a J2 Standard Edition 1.4.2 or higher

2. Install ANT utilities from http://ant.apache.org

This will require executing the folowing steps
- Downloading and unzipping the ant binaries. Say the install folder is foo
- Modify the following environment variables
	PATH --> it must include the path to foo/bin
	JAVA_HOME--> it must include the path to the java install folder
	ANT_HOME --> it must be the path to foo folder

3. Once installed, go to the distribution folder and write

        ant runide

For more instructions, please read the last version of ingeniasmanual.pdf and IAFGuide.pdf files which can be downloaded from http://ingenias.sourceforge.net. 

INGENIAS Development Kit (IDK)
------------------------------

Folder structure:

The IDK presents its new folder structure. Under the IDK folder you will find
-editor folder. This contains the visual editor
-iaf folder. It contains a java code generator for JADE agent platform, the INGENIAS Agent Framework
-workspace folder. It contains several examples of demo applications. You are expected to allocate here your developments, though other external folders can be used as well

Running the demos:

Just get into any of the demos and inspect the different build.xml files within. Each demo has a spec folder where the problem specification can be checked out.

The following demos are available:

folder workspace/cinema: a buyer wants to buy a cinema ticket. To do that, several cinemas are contacted. When one of them offers a good price, the ticket is bought and delivered to the user. To run the demo, from the folder, do one of the following:
- in a console, execute "ant runjade" while in other you run "ant runSeriousConfiguration". It will show all GUIs.
- in a console, execute "ant runSeriousConfigurationProdStandAlone". It will show only application specific guis.

folder workspace/perceptionExample: an example of how to connect a GUI with an agent. It shows a Hellow World message when a button is pressed. To run the demo, from the folder, do one of the following:
- in a console, execute "ant runjade" while in other you run "ant run". It will show all GUIs.
- in a console, execute "ant runProdStandAlone". It will show only application specific guis.

folder workspace/requestExample: an example of how to ask several agents at the same time and collecting the results. To run the demo, from the folder, do one of the following:
- in a console, execute "ant runjade" while in other you run "ant runDemo". It will show all GUIs.
- in a console, execute "ant runDemoProdStandAlone". It will show only application specific guis.

folder workspace/testInteractions: an example of interactions one-to-many and many-to-one. It includes as well test definitions so that you can learn how to integrate tests and the different deployment configurations. This demo is not designed to show any GUI interaction. To run the demo, from the folder, do one of the following:
- in a console, execute "ant runjade" while in other you run "ant alljunit". It will run all defined tests stopping only when one of them fails or all finish successfully.


Troubleshooting
---------------

This distribution was compiled with JDK 1.6.0_02. This may cause you some class compatibility problems if the latest J2SE distribution is not installed. To prevent these mistakes, you can run "ant compile" from the install folder. It will recompile everything with your current JDK.

The performance with JDK 1.6.0 is lower in linux due to a series of issues with the X11 libraries. Nevertheless, these problems have been observed with Xorg 7.2.0. Other previous versions of X do not have those performance issues. If you experience these problems, try using another X server. For instance, running the application in a vncserver will show better performance. Versions previous to Xorg where libX11 is used instead of XCB, will not show this problem. It has not been observed in windows either.


