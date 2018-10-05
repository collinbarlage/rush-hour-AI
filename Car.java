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
        int [] target = new int[2];
        if(vert && direction < 0) {        
            target[0] = xPos;
            target[1] = yPos - 1;
        } else if(vert && direction > 0) {
            target[0] = xPos;
            target[1] = getEnd + 1;
        } else if(!vert && direction < 0) {
            target[0] = xPos - 1;
            target[1] = yPos;
        } else {
            target[0] = getEnd + 1;
            target[1] = yPos;
        }
        return target;
    }


}
