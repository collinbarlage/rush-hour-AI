public class Main {

    public static void main(String[] args) {

        IO io = new IO();

        boolean error = true;

        io.outputln("\n  R U S H   H O U R\n----------------------");
        io.outputln(" Collin Barlage CS 380\n");
        io.outputln("Args: ");

        for (int i = 0; i < args.length; i++) io.outputln(args[i]);

        io.outputln("");

        Game newGame = new Game(); 
        newGame.display();




        io.outputln("\n");
    }

}
