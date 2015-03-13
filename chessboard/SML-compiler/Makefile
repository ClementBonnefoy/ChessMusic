JAVA=java
JAVAC=javac
JFLEX= bin/jflex 
CUP=$(JAVA) -jar lib/java-cup-11a.jar <

all: Lexer.java parser.java

%.class: %.java
	$(JAVAC) $^

Lexer.java: sml.flex
	$(JFLEX) sml.flex
	cp Lexer.java ../src/sml/compiler/

parser.java: parser.cup
	$(CUP) parser.cup
	cp sym.java ../src/sml/compiler/
	cp parser.java ../src/sml/compiler/

clean:
	rm -f parser.java Lexer.java sym.java *.class *~
