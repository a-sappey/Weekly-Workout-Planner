import java.io.*;

public class Main {

    public static void main(String[] args) {

        int hoursInput = 0;
        boolean valid = false;

        while (!valid) {
            try {
                hoursInput = Integer.parseInt(IO.readln("Enter an integer value between 8 and 15\n"));

                if (hoursInput >= 8 && hoursInput <= 15) {
                    valid = true;
                    IO.println("Accepted: " + hoursInput + " hours");
                }
            } catch (NumberFormatException e) {
                IO.println("Invalid, try again");
            }
        }

        int totalMinutes = hoursInput * 60;
        int weekMinutes = totalMinutes;
        int[] days = new int[7];
        valid = false;

        while (!valid) {
            try {
                int selection = Integer.parseInt((IO.readln("Select first workout type:\n" +
                        "1 - Tempo\n" + "2 - Intervals (short)\n" + "3 - Intervals (long)" + "4 - Short Sprints\n")));

                if (selection >= 1 && selection < 5) {
                    IO.println("SUCCESS");
                    valid = true;
                }
            } catch (NumberFormatException e) {
                IO.println("Invalid, try again");
            }
        }

    }
}
