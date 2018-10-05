import java.io.*;
import java.util.Vector;

public class Board implements java.io.Serializable {
    IO io = new IO();

    private String boardArray[][] = new String[6][6];

    private Vector<Car> cars = new Vector<Car>();
    private Vector<Board> childBoards = new Vector<Board>();

    public Board() {
        // default does nothing
    }

    public Board(Board parent) { // copy const
         this.boardArray = parent.boardArray;
         this.cars = parent.cars;
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
                if (!tile(i,j).equals(" ") && !hasCar(tile(i,j))) {
                    // found unique car
                    int iHorizontal = 0; int iVertical = 0;
                    for (int h=0; h<6; h++) { // check lanes
                        if (tile(h,j).equals(tile(i,j))) {
                            iHorizontal++;
                        } else if (tile(i,h).equals(tile(i,j))) {
                            iVertical++;
                        }
                    }
                    if (iHorizontal < iVertical) {
                        addCar(tile(i,j), i, j, iVertical, true);
                    } else {
                        addCar(tile(i,j), i, j, iHorizontal, false);
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

    private String tile(int x, int y) {
        return boardArray[x][y];
    }

    public String getLine(int y) {
        String str = "|"; // left wall
        if (y == 0 || y == 7) { return " ------ "; } // top and bottom
        for (int i=0; i<6; i++) { str += tile(i, y-1); }
        str += (y == 3)? "" : "|"; // right wall
        return str;
    }    

    public void display() {
        for (int y=0; y<8; y++) {
            io.outputln(getLine(y));
        }
    }

    public void next() {
        for (int c=0; c<cars.size(); c++) {
            // for each car
            Board forward = new Board(this)
            while (forward.canMove(cars.get(c), 1)) {
                forward.move(cars.get(c), 1);
                forward.display();
            }      
            Board backward = new Board(this)
            while (backward.canMove(cars.get(c), -1)) {
                backward.move(cars.get(c), -1);
                backward.display();
            }          
        }
    }

    private boolean canMove(Car car, int direction) {
        int [] target = new int[2];
        target = car.move(direction); 
        return isVacant(target[0], [1]); 
    }

    private boolean isVacant(int x, int y) {
        if (x < 0 || y < 0 || x > 5 || y > 5) { return false; }
        if (tile(x,y).equals(" ")) { return true ; }
        return false;
    } 

    private void move(Car car, int direction) {
        
    }


}
