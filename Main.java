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
        if(input == "") { input = "  o aa|  o   |xxo   |ppp  q|     q|     q"; }
		newBoard.load(input);

        Path next;

        // execute command
        switch (command) {
            case "print": 
                newBoard.display();
                break;

            case "done": // return True or False if xx car can gtfo
                if(newBoard.isDone()) {
					io.log("True");
				} else {
					io.log("False");
				}
                break;

            case "next": // display all boards of each attempt to move each car +-direction
                next = newBoard.next();
                next.print();
                break;

            case "random": // display random path
                newBoard.random(10);
                break;

            case "bfs": // Breadth first search
                next = newBoard.next();
                next.bfs(next);
                break;

            case "astar": // Breadth first search
                next = newBoard.next();
                next.astar(next);
                break;

            case "test": // display all boards of each attempt to move each car +-direction
                next = newBoard.next();
                next.print();
                io.log("and the parent is:");
                if(next.get(2).hasParent()) {
                    next.get(2).parent.display();
                } else {
                    io.log("no Parent found :(");
                }
                break;


            default:
                io.log("Erk, invalid command argument :(");
                break;
        }

        io.log("\n");
    }

}
