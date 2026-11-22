import java.io.*;

public class Main {

    public static void main(String[] args) {

        int hoursInput = 0;
        boolean valid = false;

        while (!valid) {
            try {
                hoursInput = Integer.parseInt(IO.readln("Enter an integer value between 8 and 15"));
            } catch (NumberFormatException e) {
                IO.println("Invalid, try again");
            }
        }
    }
}
