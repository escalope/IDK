--- 
layout: post 
title: Changes in the editor and archetypes
comments: true
tags:
- video
- training
video_url: /assets/videos/demotestingfirmada.webm
video_track_es: /assets/videos/demotestingfirmada-es.srt
video_track_en: /assets/videos/demotestingfirmada-en.srt
--- 
I have finished some videos on INGENIAS. It is the hello world example combined with the latest features:

- a new script structure which is more reliable than the maven-ant-plugin. This plugin has as a defect that it takes literal references to current jar files stored in .m2 folder. Usually, nothing happens, but, when you are working with snapshots, it is a mess. Also, it tries to download jar files into .m2 without using at all other maven files which tell maven which version to use, for example. As a result, the progran you run with ant (i.e., using the maven-ant-plugin) is not using the same libraries than a "mvn test" command. 
- a new monitor for changes in files. This monitor looks at the current file being modified through the editor and warns if another actor is changing it. This is useful to implement the development lifecycle in INGENIAS.
- bug fixes for the editor. Now it correctly modifies some menu options to enable/disable when modifying an entity, or moving another.
- An improved version of the update specification function. This new version modifies your current specification file if: a code generation using the modified specification compiles, and if the new version is different from the old one, you get a warning in the editor and an offer to upload the new spec. If you get no warning, that means the specification may seem to have changed, but the IDE fount the new version to be the same as the one being stored in the IDE. 
- Build number included in the Help-> About menu. This will help to be sure which version of the editor it is being run when snapshot versions are being handled.

These features are present in the SNAPSHOT version of the editor (1.2-SNAPSHOT) and archetypes (1.2-SNAPSHOT). Code uploader was modified as well to (1.1-SNAPSHOT).

To work with eclipse, I strongly disencourage the use of mvn eclipse:eclipse if you are using an Eclipse Juno version, or higher, of eclipse. Maven is integrated by default in Eclipse in the latest versions, so you only have to import the project, as if it was a regular project. 

Also, you will not probably notice, but there is also a new working framework to test the editor and prevent regression failures. This new testing framework is based on FEST Swing. I used it in the past, but could not use it to evaluate JGraph diagrams. Now, I have written a fixture that permits me to create automatically tests that can work moving entities in the diagrams. I have tried Marathon and Abbot, and FEST gave me the best. Marathon is very good for rapid testing, and the IDE it provides for creating GUI tests is really original. The mechanism is similar to FEST, based on detection of SWING GUI elements through their name (yes, SWING elements CAN have a name according to SWING API, i.e., methods setName and getName). Unlike FEST, Marathon provides a GUI to record your actions in the tested application and generate a test in Jithon (an implementation of Python in Java). Nevertheless, it failed to handle properly my diagrams. I had to use raw point-and-click and point-and-drag events which is inherently unstable because of attributes of your X. Different fonts or different sizes of the screen makes the test fail when raw events are used (even using Xvfb which is a virtual X server). It required to develop additional software, just as FEST, but I felt more confident to do it with the later. Also, FEST generates snapshots of failed GUI tests. This helps identifying them quicker in the surefire reports maven generates.

For your entertainment, I have recorded a video of one GUI testing session. It is started from console by invoking mvn test. The session is held within a Xvfb and observed externally through vncviewer. The reason for this arrangement is that FEST acquires control of your mouse and does not let you do anything while the test progresses. Besides, an Xvfb should give better opportunities to have headless testing for GUIs and provide the same testing setup across different machines. 
