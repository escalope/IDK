--- 
layout: post 
title: INGENIAS goes to MAVEN 
comments: true
tags:
- news
--- 

INGENIAS has two versions at this moment. The [old
one](http://ingenias.svn.sourceforge.net/viewvc/ingenias/trunk/IDK/) is
discontinued. For a time now, only the
[IDKMaven](http://ingenias.svn.sourceforge.net/viewvc/ingenias/trunk/IDKMaven/)
is being regularly updated

The new one is based in Maven. There are important benefits in using Maven that makes worth the effort:

- Updates are instantly available for you. They can be automatic if you stick to the SNAPSHOT version (unstable) or be manual if you modify a file and change the version number. Maven does the rest, contacting the appropriate repositories and downloading the new versions. INGENIAS artefacts are uploaded to Maven repositories so they can be available worldwide. Besides, stable releases are digitally signed, to ensure you get the right softare from the right person.
- The projects will be smaller. Since Maven now becomes responsible of managing dependencies, you don't need to have several copies of INGENIAS libraries. In fact, your project will be smaller since it will include only the sources.
- Greater integration with main stream software engineering. Maven is used in large projects. By integrating INGENIAS with Maven, it becomes available to any developer in the world, just like J2EE or Ruby on Rails, or any other framework/platform/language. Also, it means you can use anything available in the Maven repositories. 
- Your project becomes a Maven one too. As a result of the migration, your projects becomes Maven ones too. If you are a developer, the transit will be painless. You still can use your favourite development environment. Eclipse, since INDIGO version, supports natively Maven. Netbeans does support Maven for long time now. After importing a Maven project into those environments, the development happens just the same.

Checkout the [quick start guide](/quickstart.html) to get acquainted with this great tool. 
