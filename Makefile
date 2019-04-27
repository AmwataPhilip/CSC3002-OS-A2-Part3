# Modified by: Philip Amwata
# Date: 27/04/2019

JAVAC = javac
.SUFFIXES: .java .class

SRCDIR=src
BINDIR=bin/dishWashS/
DOCDIR=doc

$(BINDIR)/%.class:%.java
	$(JAVAC) $<
	mv dishwashS/*.class $(BINDIR)/


CLASSES = dishwashS/CleaningDishes.class dishwashS/Dryer.class dishwashS/Washer.class dishwashS/WetDishRack.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm -rf $(BINDIR)/*

run:
	java -cp bin dishWashS.CleaningDishes 6 3 100 100
