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

        days[0] = (int) (totalMinutes * 0.11);
        days[1] = (int) (totalMinutes * 0.13);
        days[2] = (int) (totalMinutes * 0.2);
        days[3] = (int) (totalMinutes * 0.13);
        days[4] = (int) (totalMinutes * 0.11);
        days[5] = (int) (totalMinutes * 0.2);
        days[6] = (int) (totalMinutes * 0.1);


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

        int weekSum = 0;

        for (int i = 0; i < days.length; i++) {
            weekSum += days[i];
        }

        if (weekSum < totalMinutes) {
            while (weekSum < totalMinutes) {

                if (totalMinutes - weekSum <= 5) {
                    days[2] += totalMinutes - weekSum;
                    weekSum += totalMinutes - weekSum;
                    break;
                }

                for (int i = 0; i < days.length; i++) {
                    if (i == 0 || i == 2 || i == 4 || i == 6) {
                        days[i] += 1;
                        weekSum += 1;
                    }
                }
            }
        }

        for (int i = 0; i < days.length; i++) {
            IO.println("Day " + (i + 1) + " " + days[i] + " minutes");
        }



        IO.println("Week total minutes: " + weekSum);

    }
}
