import java.io.*;
import java.util.Vector;

public class Path implements java.io.Serializable {
    IO io = new IO();
    int bfsCounter = 0;

    private Vector<Board> boards = new Vector<Board>();
    private Path parent;

    public Path() {
        // default does nothing
    }

    public Path(Path og) { // copy constuctor
        
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

    public void reversePrint() {
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

    public void append(Path p) {
        for(int i=0; i<p.size(); i++) {
            boards.add(p.get(i));
        }
    }



    public void bfs(Path path) {

        io.log("starting bfs with path size" + path.size())
        Path nextLevel = new Path();

        for (int i=0; i<path.size(); i++) {
            Board b = path.get(i);
            bfsCounter++;

            //print parents
            Path route = new Path();
            route.add(b);
            while (b.hasParent()) {
                route.add(b);
                b = b.parent;
            }
            io.log("board has "+route.size()-1+" parents")
            route.reversePrint();
            b = path.get(i);


            if(b.isDone()) {
                io.log("WE FOUND IT BOI" + bfsCounter);
                return;
            } else {
                //build next level
                nextLevel.append(b.next());
            }
        }

        bfs(nextLevel);
        io.log("returning....")
        return;
    }



}
