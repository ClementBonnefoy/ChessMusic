JAVA=java
JAVAC=javac
JFLEX= bin/jflex 
CUP=$(JAVA) -jar lib/java-cup-11a.jar <

all: Lexer.java parser.java

%.class: %.java
	$(JAVAC) $^

Lexer.java: sml.flex
	$(JFLEX) -d ../src/sml/compiler sml.flex

parser.java: parser.cup
	$(CUP) parser.cup
	mv sym.java ../src/sml/compiler/sym.java
	mv parser.java ../src/sml/compiler/parser.java

clean:
	rm -f parser.java Lexer.java sym.java *.class *~
