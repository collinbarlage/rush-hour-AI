import java.io.*;
import java.util.Vector;

public class Path implements java.io.Serializable {
    IO io = new IO();

    private Vector<Board> boards = new Vector<Board>();

    public Path() {
        // default does nothing
    }

    public Path(Path parent) { // copy constuctor
        
    }

    public Board last() {
        return boards.get(boards.size() - 1);
    }


    public void add(Board b) {
        boards.add(b);
    }

    public Board get(int i) {
        return boards.get(i);
    }

    public int size() {
        return boards.size();
    }

    public void print() {
        String lines[] = {"", "", "", "", "", "", "", ""};

        for (int b=0; b<boards.size(); b++) {
            for (int l=0; l<8; l++) {
                lines[l] += boards.get(b).getLine(l) + " ";
            }
            if ((b+1)%6 == 0 || b == boards.size()-1) {
                for (int l=0; l<8; l++) { 
                    io.log(lines[l]); 
                    lines[l] = "";
                }
            }
        }
    }



}
