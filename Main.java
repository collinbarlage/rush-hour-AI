public class Main {

    public static void main(String[] args) {

        IO io = new IO();

        boolean error = true;

        io.outputln("\n  R U S H   H O U R\n----------------------");
        io.outputln(" Collin Barlage CS 380\n");
        io.outputln("Args: ");

        String command = args[0];
        String arg     = "";
        if (args.length > 1) arg = args[1]

        io.outputln("command: "+ command + "\nArg: "+ arg);


        io.outputln("");

        Game newGame = new Game(); 
        newGame.display();




        io.outputln("\n");
    }

}
