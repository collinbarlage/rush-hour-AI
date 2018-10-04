import java.io.*;
import java.util.Vector;

public class Board implements java.io.Serializable {
    IO io = new IO();
    private String boardArray[][] = new String[6][6];

    public Board() {
        // default does nothing
    }

    public void load(String init) {
        Vector<char> chars = new Vector<char>();
        for (int i=0; i<init.length(); i++) {
            if (init.charAt(i) != '|') chars.add(init.charAt(i));
        }

        if(chars.size() != 36) {
            io.outputln("Oh geez, something went wrong. Check your argument string");
            return;
        }

        // load  chars onto board
        for (int j=0; j<6; j++) {
            for (int i=0; i<6; i++) { boardArray[i,j] = chars.get(j*6 + i) };
        }

    }

    public String get(int x, int y) {
        return boardArray[x][y];
    }

    public void display() {
        io.outputln(" ------ ");
        for (int j=0; j<6; j++) {
            io.output("|");
            for (int i=0; i<6; i++) { io.output(boardArray[i,j]) };
            io.output("|\n");
        }
        io.outputln(" ------ ");
    }


}
