--- 
layout: post 
title: Advices to work from eclipse
comments: false
tags:
- development
- advice
--- 

To use INGENIAS from eclipse, version Juno and above that has the Maven plugin (m2e plugin), you just need to follow these steps:

- Go to the menu option **file**, then **New**, then **Project**
- Choose **Maven Project**, and then press **Next**
- Click **Next** in the following dialogue
- In the archetype selection dialog, write **iaf** in the filter, select the latest non SNAPSHOT version. Click on **Next**
- Write your project data, and press **Finish**

Since INGENIAS has been ported to Maven, you should be able to compile the system and generate the code as if you were in command line. You can use the Eclipse's external tools option to launch the ant targets as you would from command line.