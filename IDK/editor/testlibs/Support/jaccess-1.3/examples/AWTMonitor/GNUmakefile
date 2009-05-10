# @(#)GNUmakefile	1.12 98/07/24
#
# Makefile for use on Solaris using gnumake.  It assumes the environment
# variables JDK_HOME (points to the JDK), SWING_HOME (points to Swing), and
# JACCESS_HOME (Points to JAccess) are defined.

TOPLEVEL = $(shell echo `pwd`)

include ../../../build/config

ifeq ($(JACCESS_HOME),)
JACCESSCLASSES=../../../build/classes
else
JACCESSCLASSES=$(JACCESS_HOME)/jaccess.jar
endif
ifeq ($(CSEP),)
CSEP = :
endif

CLASSPATH = .:$(JACCESSCLASSES):$(CLASSES)

JAVAC = $(JDK_HOME)/bin/javac $(DEPRECATION) -g -classpath $(CLASSPATH)

FILES = AWTMonitor.class

all: $(FILES)

release: all

clean clobber:
	rm -f *.class *~

%.class: %.java
	$(JAVAC) $?
