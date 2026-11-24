public class Main {

    public static void main(String[] args) {

        //Input
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

        //Race weekend selection
        valid = false;
        boolean race = false;
        int raceSelection = -1;
        int raceDay = -1;
        int raceLength = 0;

        while (!valid) {
            try {
                raceSelection = Integer.parseInt(IO.readln("Race Weekend?\n 0 - No\n 1 - Yes\n"));

                if (raceSelection == 1) {
                    race = true;
                    valid = true;

                    raceDay = Integer.parseInt(IO.readln("Which day?\n 0 - Saturday\n 1 - Sunday\n"));
                    raceLength = Integer.parseInt(IO.readln("How long?\n"));
                    raceLength += 30;

                }

                if (raceSelection == 0) {
                    race = false;
                    valid = true;
                }

            } catch (NumberFormatException e) {
                IO.println("Invalid, try again");
            }
        }

        //Assign time to days
        int totalMinutes = hoursInput * 60;
        int weekMinutes = totalMinutes;
        int[] days = new int[7];

        if (race) {

            if (raceSelection == 0) {
                days[5] = raceLength;
            } else {
                days[5] = (int) (totalMinutes * 0.2);
            }

            if (raceSelection == 1) {
                days[6] = raceLength;
            } else {
                days[6] = (int) (totalMinutes * 0.1);
            }
        }

        days[0] = (int) (totalMinutes * 0.11);
        days[1] = (int) (totalMinutes * 0.13);
        days[2] = (int) (totalMinutes * 0.2);
        days[3] = (int) (totalMinutes * 0.13);
        days[4] = (int) (totalMinutes * 0.11);

        //Select Workouts
        valid = false;

        int workoutOne = 1;
        int workoutTwo = 4;

        while (!valid) {
            try {
                workoutOne = Integer.parseInt((IO.readln("Select first workout type:\n" +
                        "1 - Tempo\n" + "2 - Intervals (short)\n" + "3 - Intervals (long)\n" + "4 - Short Sprints\n")));

            } catch (NumberFormatException e) {
                IO.println("Invalid, try again");
            }

            try {
                workoutTwo = Integer.parseInt((IO.readln("Select second workout type:\n" +
                        "1 - Tempo\n" + "2 - Intervals (short)\n" + "3 - Intervals (long)\n" + "4 - Short Sprints\n")));
            } catch (NumberFormatException e) {
                IO.println("Invalid, try again");
            }

            if ((workoutOne > 0 && workoutOne < 5) && (workoutTwo > 0 && workoutTwo < 5)) {

                IO.println("Workouts Chosen.");
                valid = true;

            }
        }


        //Meet minimum time
        int weekSum = 0;

        for (int i = 0; i < days.length; i++) {
            weekSum += days[i];
        }

        if (weekSum < totalMinutes + raceLength) {
            while (weekSum < totalMinutes + raceLength) {

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

        //Day 1
        IO.println();
        IO.println("Day 1:\n" + days[0] + " minutes Z2");


        // Workout One
        if (workoutOne == 1) {

            int block = (int) (days[1] * 0.5);
            int warmup = (int) (days[1] * 0.25);
            int cooldown = (int) (days[1] * 0.25);

            if (block % 5 != 0) {
                block += 1;
            }

            while ((block + warmup + cooldown) > days[1]) {
                if ((block + warmup + cooldown) - days[1] > 2) {
                    warmup -= 1;
                    cooldown -= 1;
                } else {
                    warmup -= 2;
                }

            }

            IO.println();
            IO.println("Day 2:\n" + warmup + " minutes Z2 Warmup\n" +
                    block + " minutes tempo effort Z3/Z4\n" + cooldown + " minutes Z2 Cooldown");
        }

        if (workoutOne == 2) {

            int intervalCount = (int) (days[1] * 0.2);
            int blockMinutes = (int) (days[1] * 0.4);
            int warmup = (int) (days[1] * 0.3);
            int cooldown = (int) (days[1] * 0.3);

            while ((blockMinutes + warmup + cooldown) > days[1]) {
                if ((blockMinutes + warmup + cooldown) - days[1] > 2) {
                    warmup -= 1;
                    cooldown -= 1;
                } else {
                    warmup -= 2;
                }
            }

            IO.println();
            IO.println("Day 2:\n" + warmup + " minutes Z2 warmup\n" +
                    intervalCount + " x minute on / minute off intervals\n" + cooldown + " minutes Z2 cooldown");
        }

        if (workoutOne == 3) {

            int blockMinutes = (int) (days[1] * 0.5);
            int warmup = (int) (days[1] * 0.25);
            int cooldown = (int) (days[1] * 0.25);

            if (blockMinutes % 7 != 0) {
                blockMinutes += 1;
                warmup -= 1;
            }

            int intervalCount = blockMinutes / 7;

            while ((blockMinutes + warmup + cooldown) > days[1]) {
                if ((blockMinutes + warmup + cooldown) - days[1] > 2) {
                    warmup -= 1;
                    cooldown -= 1;
                } else {
                    warmup -= 2;
                }
            }

            IO.println();
            IO.println("Day 2:\n" + warmup + " minutes Z2 warmup\n" +
                    intervalCount + " x 5 minutes on / 2 minutes off\n" + cooldown + " minutes Z2 cooldown");
        }

        if (workoutOne == 4) {

            int blockMinutes = (int) (days[1] * 0.4);
            int warmCoolPool = (int) (days[1] * 0.6);

            if ((blockMinutes + 2) % 12 != 0) {
                blockMinutes += 1;
                warmCoolPool -= 1;
            }

            int intervalCount = blockMinutes / 12;
            int warmup = warmCoolPool / 2;
            int cooldown = warmCoolPool / 2;

            while ((blockMinutes + warmup + cooldown) > days[1]) {
                if ((blockMinutes + warmup + cooldown) - days[1] > 2) {
                    warmup -= 1;
                    cooldown -= 1;
                } else {
                    warmup -= 2;
                }
            }

            IO.println();
            IO.println("Day 2:\n" + warmup + " minutes Z2 warmup\n" +
                    intervalCount + " sets 10 x 15 second SS / 105 second rest, 2 minute rest between sets\n" +
                    cooldown + " minutes Z2 cooldown");
        }


        //Day 3, LR
        IO.println();
        IO.println("Day 3:\n" + days[2] + " minutes Z2");


        //Workout Two
        if (workoutTwo == 1) {

            int block = (int) (days[3] * 0.5);
            int warmup = (int) (days[3] * 0.25);
            int cooldown = (int) (days[3] * 0.25);

            if (block % 5 != 0) {
                block += 1;
            }

            while ((block + warmup + cooldown) > days[3]) {
                if ((block + warmup + cooldown) - days[3] > 2) {
                    warmup -= 1;
                    cooldown -= 1;
                } else {
                    warmup -= 2;
                }

            }

            IO.println();
            IO.println("Day 4:\n" + warmup + " minutes Z2 Warmup\n" +
                    block + " minutes tempo effort Z3/Z4\n" + cooldown + " minutes Z2 Cooldown");
        }

        if (workoutTwo == 2) {

            int intervalCount = (int) (days[3] * 0.2);
            int blockMinutes = (int) (days[3] * 0.4);
            int warmup = (int) (days[3] * 0.3);
            int cooldown = (int) (days[3] * 0.3);

            while ((blockMinutes + warmup + cooldown) > days[3]) {
                if ((blockMinutes + warmup + cooldown) - days[3] > 2) {
                    warmup -= 1;
                    cooldown -= 1;
                } else {
                    warmup -= 2;
                }
            }

            IO.println();
            IO.println("Day 4:\n" + warmup + " minutes Z2 warmup\n" +
                    intervalCount + " x minute on / minute off intervals\n" + cooldown + " minutes Z2 cooldown");
        }

        if (workoutTwo == 3) {

            int blockMinutes = (int) (days[3] * 0.5);
            int warmup = (int) (days[3] * 0.25);
            int cooldown = (int) (days[3] * 0.25);

            if (blockMinutes % 7 != 0) {
                blockMinutes += 1;
                warmup -= 1;
            }

            int intervalCount = blockMinutes / 7;

            while ((blockMinutes + warmup + cooldown) > days[3]) {
                if ((blockMinutes + warmup + cooldown) - days[3] > 2) {
                    warmup -= 1;
                    cooldown -= 1;
                } else {
                    warmup -= 2;
                }
            }

            IO.println();
            IO.println("Day 4:\n" + warmup + " minutes Z2 warmup\n" +
                    intervalCount + " x 5 minutes on / 2 minutes off\n" + cooldown + " minutes Z2 cooldown");
        }

        if (workoutTwo == 4) {

            int blockMinutes = (int) (days[3] * 0.4);
            int warmCoolPool = (int) (days[3] * 0.6);

            if ((blockMinutes + 2) % 12 != 0) {
                blockMinutes += 1;
                warmCoolPool -= 1;
            }

            int intervalCount = blockMinutes / 12;
            int warmup = warmCoolPool / 2;
            int cooldown = warmCoolPool / 2;

            while ((blockMinutes + warmup + cooldown) > days[3]) {
                if ((blockMinutes + warmup + cooldown) - days[3] > 2) {
                    warmup -= 1;
                    cooldown -= 1;
                } else {
                    warmup -= 2;
                }
            }

            IO.println();
            IO.println("Day 4:\n" + warmup + " minutes Z2 warmup\n" +
                    intervalCount + " sets 10 x 15 second SS / 105 second rest, 2 minute rest between sets\n" +
                    cooldown + " minutes Z2 cooldown");
        }

        //Day 5
        IO.println();
        IO.println("Day 5:\n" + days[4] + " minutes Z2");

        //Day 6

        if (race && raceSelection == 0) {
            IO.println();
            IO.println("Day 6:\n" + "15 minute warmup\n" + "Race (" + (raceLength - 30) +
                    " minutes)\n" + "15 minute cooldown\n");
        } else {
            IO.println();
            IO.println("Day 6:\n" + days[5] + " minutes Z2");
        }


        //Day 7

        if (race && raceSelection == 1) {
            IO.println();
            IO.println("Day 7:\n" + "15 minute warmup\n" + "Race (" + (raceLength - 30) +
                    " minutes)\n" + "15 minute cooldown\n");
        } else {
            IO.println();
            IO.println("Day 7:\n" + days[6] + " minutes Z2");
        }

        IO.println("Week total minutes: " + weekSum);

    }
}
