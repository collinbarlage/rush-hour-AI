import java.io.*;
import java.util.Vector;

public class Game implements java.io.Serializable {
    IO io = new IO();
    private Vector<Question> questions = new Vector<Question>();

    public Game() {
        io.outputln("making default game");
    }

    public Game(String GameName) {

    }

    public void display() {
        io.outputln("\n\n----------------------------------------------");

    }


}
