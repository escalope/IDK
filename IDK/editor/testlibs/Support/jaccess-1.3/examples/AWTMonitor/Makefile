# @(#)Makefile	1.9 98/07/24
# NMAKE makefile

!ifndef JDK_HOME
JDK_HOME = c:\java
!endif
!ifndef SWING_HOME
SWING_HOME = c:\swing
!endif
!ifndef JACCESS_HOME
JACCESS_HOME = ..\..\..\build
!endif
CSEP = ;

CLASSPATH = .;..\AccessibilityPanels;..\..\..\build\classes;$(SWING_HOME);$(SWING_HOME)\swing.jar;$(JDK_HOME)\lib\classes.zip

JAVAC = $(JDK_HOME)\bin\javac -deprecation -g -classpath $(CLASSPATH)

FILES = AWTMonitor.class

all: $(FILES)

release: all

clean clobber:
	rm -f *.class *~

AWTMonitor.class: AWTMonitor.java
	$(JAVAC) $?
