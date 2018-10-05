PHONY: all clean

all: run

Main.class: Main.java IO.java Board.java Car.java
	javac $^

run: Main.class
	java Main $(ARGS)

clean:
	-rm -f *.class
