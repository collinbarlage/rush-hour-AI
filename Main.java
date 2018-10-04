public class Main {

    public static void main(String[] args) {
        IO io = new IO();
        io.outputln("\n  R U S H     H O U R\n-----------------------");
        io.outputln(" Collin Barlage CS 380\n");

        // check for errors 
        if (args.length < 1) {
            io.outputln("YIKES you didn't give me a command argument");
            io.outputln("Try running something like  > sh run.sh command\n\n");
            return;
        }

        // get args
        String command = args[0];
        String input     = "";
        if (args.length > 1) { input = args[1]; }

        // create game board
        Board newBoard = new Board();  
        newBoard.load(input);
        newBoard.display();




        io.outputln("\n");
    }

}
