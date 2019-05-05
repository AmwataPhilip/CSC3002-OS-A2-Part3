# Modified by: Philip Amwata
# Date: 27/04/2019

JAVAC = javac
.SUFFIXES: .java .class

SRCDIR=src/molecule/
BINDIR=bin/molecule/
DOCDIR=doc

$(BINDIR)/%.class:$(SRCDIR)%.java
	$(JAVAC) -cp src $<
	mv $(SRCDIR)*.class $(BINDIR)/


CLASSES = BarrierReusable.class Carbon.class Hydrogen.class Methane.class RunSimulation.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm -rf $(BINDIR)/*

run:
	java -cp bin molecule.RunSimulation 12 3
