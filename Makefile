PHONY: all clean

all: run

Main.class: Main.java IO.java Game.java Board.java
	javac $^

run: Main.class
	java Main $(ARGS)

clean:
	-rm -f *.class
