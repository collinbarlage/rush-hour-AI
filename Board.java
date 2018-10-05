import java.io.*;
import java.util.Vector;

public class Board implements java.io.Serializable {
    IO io = new IO();
    private String boardArray[][] = new String[6][6];
    private Vector<Car> cars = new Vector<Car>();

    public Board() {
        // default does nothing
    }

    public void load(String init) {
        Vector<String> input = new Vector<String>();
        for (int i=0; i<init.length(); i++) {
            if (init.charAt(i) != '|') input.add("" + init.charAt(i));
        }
        // check if error
        if(input.size() != 36) {
            io.outputln("Oh geez, something went wrong. Check your argument string"); return;
        }
        // load input onto board
        for (int j=0; j<6; j++) {
            for (int i=0; i<6; i++) { 
                boardArray[i][j] = input.get(j*6 + i);
            }
        }
        // note where cars are
        for (int j=0; j<6; j++) {
            for (int i=0; i<6; i++) { 
                if (!boardArray[i][j].equals(" ") && !hasCar(boardArray[i][j])) {
                    // found unique car
                    int iHorizontal = 0 int iVertical = 0;
                    for (int h=0; h<6; h++) { // check lanes
                        if (boardArray[h][j].equals(boardArray[i][j])) {
                            iHorizontal++;
                        } else if (boardArray[i][h].equals(boardArray[i][j])) {
                            iVertical++;
                        }
                    }
                    if (iHorizontal < iVertical) {
                        addCar(boardArray[i][j], i, j, iVertical, true);
                    } else {
                        addCar(boardArray[i][j], i, j, iHorizontal, false);
                    }
                } 
            }
        }
    }

    private void addCar(String carId, int x, int y, int size, boolean vert) {
        Car newCar = new Car(carId, x, y, size, vert);
        cars.add(newCar);
    }

    private boolean hasCar(String carId) {
        for (int i=0; i<cars.size(); i++) {
            if(carId.equals(cars.get(i).id)) { return true; }
        }
        return false;
    }

    private String get(int x, int y) {
        return boardArray[x][y];
    }

    public String getLine(int y) {
        String str = "|"; // left wall
        if (y == 0 || y == 7) { return " ------ "; } // top and bottom
        for (int i=0; i<6; i++) { str += boardArray[i][y-1]; }
        str += (y == 3)? "" : "|"; // right wall
        return str;
    }    

    public void display() {
        for (int y=0; y<8; y++) {
            io.outputln(getLine(y));
        }
    }

    // public void display() {
    //     io.outputln(" ------ ");
    //     for (int j=0; j<6; j++) {
    //         io.output("|");
    //         for (int i=0; i<6; i++) io.output(boardArray[i][j]);
    //         if (j == 2) { io.output("\n"); }
    //         else { io.output("|\n"); }
    //     }
    //     io.outputln(" ------ ");
    // }


}
