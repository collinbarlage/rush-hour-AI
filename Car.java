import java.io.*;
import java.util.Vector;

public class Car implements java.io.Serializable {
    IO io = new IO();
    
    public int size;
    public int xPos; // 0 - 5
    public int yPos; // 0 - 5
    public String id; 
    public boolean vert;


    public Car() {
        // default does nothing
    }

    public Car(String carId, int carX, int carY, int carSize, boolean carVert) {
        id = carId;
        xPos = carX;
        yPos = carY;
        size = carSize;
        vert = carVert;

        io.outputln("constructed car "+ id + " at [" + xPos + ", " + yPos + "] with size " + size + ". VERT = " + vert);
    }

    public int getEnd() {
        int start = (vert)? yPos : xPos;
        return start + size - 1;
    }

    public int[] move(int direction) {
        if(vert && direction < 0) {        
            return { xPos, yPos - 1 };
        } else if(vert && direction > 0) {
            return { xPos, getEnd + 1 };
        } else if(!vert && direction < 0) {
            return { xPos - 1, yPos };
        } else {
            return { getEnd + 1, yPos };
        }
    }

    public void display() {
        for (int y=0; y<8; y++) {
            //io.outputln(getLine(y));
        }
    }



}
