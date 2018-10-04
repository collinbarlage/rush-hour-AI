public class Main {

    public static void main(String[] args) {

        IO io = new IO();

        boolean error = true;

        io.outputln("\n   R U S H   H O U R\n---------------------");
        io.outputln("\n Collin Barlage CS 380\n");
        io.outputln("Enter a corresponding number to access menu items:\n");

        Game newGame = new Game(); 

        newGame.display();

    }

}
