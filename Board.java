import java.io.*;
import java.util.Vector;

public class Board implements java.io.Serializable {
    IO io = new IO();
    private String boardArray[][] = new String[6][6];

    public Board() {
        // default does nothing
    }

    public void load(String init) {
        Vector<String> input = new Vector<String>();
        for (int i=0; i<init.length(); i++) {
            if (init.charAt(i) != '|') input.add("" + init.charAt(i));
        }

        if(input.size() != 36) {
            io.outputln("Oh geez, something went wrong. Check your argument string");
            return;
        }

        // load  input onto board
        for (int j=0; j<6; j++) {
            for (int i=0; i<6; i++) boardArray[i][j] = input.get(j*6 + i);
        }

    }

    public String get(int x, int y) {
        return boardArray[x][y];
    }

    public void display() {
        io.outputln(" ------ ");
        for (int j=0; j<6; j++) {
            io.output("|");
            for (int i=0; i<6; i++) io.output(boardArray[i][j]);
            if (j == 2) { io.output("\n"); }
            else { io.output("|\n"); }
        }
        io.outputln(" ------ ");
    }


}
