import java.io.*;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Board implements java.io.Serializable {
    IO io = new IO();

    private String boardArray[][] = new String[6][6];

    public Vector<Car> cars = new Vector<Car>();
    public Vector<Board> childBoards = new Vector<Board>();

    public Board parent;

    public Board() {
        // default does nothing
    }

    public Board(Board og) { // copy constuctor
        this.parent = og.parent;

        String newBoardArray  [][] = new String[6][6];
        for (int j=0; j<6; j++) {
            for (int i=0; i<6; i++) { 
                newBoardArray[i][j] = og.tile(i,j);
            }
        }
        this.boardArray = newBoardArray;

        Vector<Car> newCars = new Vector<Car>();
        for (int i=0; i<og.cars.size(); i++) {
            Car newCar = new Car(og.cars.get(i));
            newCars.add(newCar);
        }
        this.cars = newCars;
    }

    public void load(String init) {
        Vector<String> input = new Vector<String>();
        for (int i=0; i<init.length(); i++) {
            if (init.charAt(i) != '|') input.add("" + init.charAt(i));
        }
        // check if error
        if(input.size() != 36) {
            io.log("Oh geez, something went wrong. Check your argument string"); return;
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
                        if (tile(h,j).equals(tile(i,j))) { iHorizontal++; } 
                        if (tile(i,h).equals(tile(i,j))) { iVertical++; }
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

    public boolean hasParent() {
        return (parent != null);
    }

    public String tile(int x, int y) {
        return boardArray[x][y];
    }

    public String getLine(int y) {
        String str = "|"; // left wall
        if (y == 0 || y == 7) { return " ------ "; } // top and bottom
        for (int i=0; i<6; i++) { str += tile(i, y-1); }
        str += (y == 3)? " " : "|"; // right wall
        return str;
    }    

    public void display() {
        for (int y=0; y<8; y++) {
            io.log(getLine(y));
        }
    }

    public boolean canMove(Car car, int direction) {
        int [] target = new int[2];
        target = car.moveTarget(direction); 
        return isVacant(target[0], target[1]); 
    }

    public boolean isVacant(int x, int y) {
        if (isInBounds(x, y) && tile(x,y).equals(" ")) { return true ; }
        return false;
    } 

    public boolean isInBounds(int x, int y) {
        if (x < 0 || y < 0 || x > 5 || y > 5) { return false; }
        return true;
    }

    public void move(Car car, int direction) {
        int antiX = car.antiTarget(direction)[0];
        int antiY = car.antiTarget(direction)[1];
        int moveX = car.moveTarget(direction)[0];
        int moveY = car.moveTarget(direction)[1];
        
        // io.log("moving " + car.id +" "+ direction+ "  target: [" + moveX + ", " + moveY + "] anti: [" +antiX+", " + antiY + "]");

        if (isInBounds(antiX, antiY)) { this.boardArray[antiX][antiY] = " "; }
        this.boardArray[moveX][moveY] = car.id;

        car.move(direction);
    }

    public void printBoards(Vector<Board> boards) {
        String lines[] = {"", "", "", "", "", "", "", ""};

        for (int b=0; b<boards.size(); b++) {
            for (int l=0; l<8; l++) {
                lines[l] += boards.get(b).getLine(l) + " ";
            }
        }
        for (int l=0; l<8; l++) {
            io.log(lines[l]);
        }
    }

    public boolean isDone() {
        String line = getLine(3);
        char[] lineChars = line.toCharArray();
        int x = 0;
        // for (int i=7; i>=0; i--) {
        //     if(x == 2) { return true; }
        //     if(lineChars[i] == 'x') { x++; } 
        //     else if(lineChars[i] != ' ') { return false; }
        // }
        return(lineChars[6] == 'x' && lineChars[5] == 'x');
    }

    public Path next() {
        Path path = new Path();
        for (int c=0; c<this.cars.size(); c++) {
            // for each car
            Board forward = new Board(this);
            while (forward.canMove(forward.cars.get(c), 1)) {
                forward.move(forward.cars.get(c), 1);
                forward.parent = new Board(this);
                path.add(new Board(forward));
            }      
            Board backward = new Board(this);
            while (backward.canMove(backward.cars.get(c), -1)) {
                backward.move(backward.cars.get(c), -1);
                backward.parent = new Board(this);
                path.add(new Board(backward));
            }       
        }
        return path;
    }

    private int randomInt(int max) {
        return ThreadLocalRandom.current().nextInt(0, max);
    }

    public void random(int n) {
        Path path = new Path(); // random path to construct
        Board b = this;
        path.add(b);
        for (int i=0; i<n-1; i++) {
            // check for solution
            if(b.isDone()) {
                break;
            }
            Path next = b.next();
            b = next.get(randomInt(next.size()));
            path.add(new Board(b));
        }
        path.print();
    }

}
