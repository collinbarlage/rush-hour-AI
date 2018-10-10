import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        IO io = new IO();

        io.log("\n  R U S H     H O U R\n-----------------------");
        io.log(" Collin Barlage CS 380\n");

        // check for errors 
        if (args.length < 1) {
            io.log("YIKES you didn't give me a command argument");
            io.log("Try running something like  > sh run.sh command\n\n");
            return;
        }

        // get args
        String command = args[0];
        String input     = "";
        if (args.length > 1) { input = args[1]; }

        // create and load game board
        Board newBoard = new Board();  
        newBoard.load(input);

        // execute command
        switch (command) {
            case "print": 
                newBoard.display();
                break;

            case "done": // return True or False if xx car can gtfo
                //TODO
                io.log(newBoard.isDone())
                break;

            case "next": // display all boards of each attempt to move each car +-direction
                Vector<Board> nextBoards = new Vector<Board>();
                for (int c=0; c<newBoard.cars.size(); c++) {
                    // for each car
                    Board forward = new Board(newBoard);
                    while (forward.canMove(forward.cars.get(c), 1)) {
                        forward.move(forward.cars.get(c), 1);
                        nextBoards.add(new Board(forward));
                    }      
                    Board backward = new Board(newBoard);
                    while (backward.canMove(backward.cars.get(c), -1)) {
                        backward.move(backward.cars.get(c), -1);
                        nextBoards.add(new Board(backward));
                    }       
                }
                newBoard.printBoards(nextBoards);
                break;

            default:
                io.log("Erk, invalid command argument :(");
                break;
        }

        io.log("\n");
    }

}
