--- 
layout: post 
title: Experiences after a course on INGENIAS in Soria (Spain)
comments: false
tags:
- training
- feedback
--- 
Yesterday, I finished a course on INGENIAS in Soria for some companies. The experience tells first that Java is not that multi-platform. It is well known, but, from time to time, it is worth reminding it. Concretely, the following issues were detected:

- The paths. Paths in windows, specially in non-english languages, tend to have special characters. To prevent issues with XML, in our tools, those special characters are removed. This leads to undesirable behaviors, like converting an existing path to a string without troublesome characters but corresponding to a non existing path.
- Don't bring your .m2 folder. It turns out that not all programs create zip files the same way. I had my .m2 repository compressed and had the intention of using it during the course instead of downloading them during the class. Nevertheless, the unzipping program used in the laboratory did extract files in a different way, making the maven client unable to detect snapshot versions which were present in the local repository. Doing the same extraction using "jar xvf filename.zip"  did the trick and the extraction was correct

In the good part:

- Maven works. Issues with one editor could be corrected in few minutes, uploaded to the sonatype snapshots repository, and downloaded automatically for all the class.
- The model driven approach did not cause rejection and was accepted. There were concerns about the number of entities in INGENIAS that made difficult to know what to use and when to use it. It was appreciated the large amount of code that seemed to reduce somehow the effort of coding. It introduced other uncertainties, in exchange, like the number of entities that exist in INGENIAS, which are too many. 

There are things to improve too:

- The development cycle needs to be revisited. Sometimes, because the OS or the tools, specifications are not saved. This is a serious issue since not saving a specification means new code is NOT generated. 
- There are some experimental features included in the visual editor that are proving to be troublesome. It is the capability of creating visual containers in the tool, where you can drop elements in a box and keep them together across diagrams. 
- In the editor, the dictionary part needs a drag and drop functionality. Elements should be brought to the diagrams by dragging and dropping. It is the intuitive behavior.

Checkout the [quick start guide](/quickstart.html) to get acquainted with this great tool. 
