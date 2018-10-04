import java.util.Scanner;

public class IO implements java.io.Serializable {
    private String response;

    IO() {
        response = "NO_RESPONSE_YET";
    }

    public String getInput() {
        return response;
    }


    public int getNumber() {
        return Integer.parseInt(response);
    }

    public void outputColumns(String x, String y) {
        System.out.printf("%-36.36s  %-36.36s%n", x, y);
    }

    public void outputln(String x) {
        System.out.println(x);
    }

    public void output(String x) {
        System.out.print(x);
    }

    public void prompt(String p) {
        Scanner in = new Scanner(System.in);

        System.out.println(p);
        System.out.print("\t-> ");
        response = in.nextLine();
    }

    public void prompt() {
        Scanner in = new Scanner(System.in);

        System.out.print("\t-> ");
        response = in.nextLine();
    }
}
