package Game;

import java.util.Random;
import java.util.Scanner;

public class Player {
    private Random rand;
    private Scanner input;
    private StringBuilder sb;
    private boolean firstRound;
    private String number;

    public Player() {
        rand = new Random();
        input = new Scanner(System.in);
        sb = new StringBuilder();
        sb.append("Previous guesses: Number Bulls Cows\n");
        firstRound = true;
        number = GenerateNumber();
    };

    public Boolean Play() {
        if (!firstRound) {
            System.out.println(sb.toString());
        } else {
            firstRound = false;
        }

        int bullsCount = 0;
        int cowsCount = 0;

        String playersGuess = ValidInput();

        for (int i = 0; i < number.length(); i++) {
            if (number.contains(playersGuess.charAt(i) + "")) {
                cowsCount++;
                if (number.charAt(i) == playersGuess.charAt(i)) {
                    bullsCount++;
                    cowsCount--;
                }
            }
        }
        System.out.printf("Bulls: %d Cows: %d \n", bullsCount, cowsCount);
        sb.append("                  " + playersGuess + "   " + (bullsCount + "") + "     " + (cowsCount + "") + "\n");

        if (bullsCount == 4) {
            System.out.println("Player wins!");
            return true;
        }

        System.out.println("------------------------------------");

        return false;
    }

    private int GenerateUniqueDigit(int firstDigit, int secondDigit, int thirdDigit) {
        int number = -1;
        do {
            number = rand.nextInt(9) + 0;
        } while (number == firstDigit || number == secondDigit || number == thirdDigit);

        return number;
    }

    public String GenerateNumber() {
        int[] digits = new int[4];
        String number = "";

        number += Integer.toString(digits[0] = rand.nextInt(9) + 1);
        number += Integer.toString(digits[1] = GenerateUniqueDigit(digits[0], -1, -1));
        number += Integer.toString(digits[2] = GenerateUniqueDigit(digits[0], digits[1], -1));
        number += Integer.toString(digits[3] = GenerateUniqueDigit(digits[0], digits[1], digits[2]));

        return number;
    }

    private String ValidInput() {
        String result = "";
        boolean flag = false;

        do {
            result = input.nextLine();
            if (result.length() != 4 || result.charAt(0) == result.charAt(1) || result.charAt(0) == result.charAt(2)
                    || result.charAt(0) == result.charAt(3) || result.charAt(1) == result.charAt(2)
                    || result.charAt(1) == result.charAt(3) || result.charAt(2) == result.charAt(3)) {
                System.out.println("Invalid input!");
            } else {
                flag = true;
            }
        } while (!flag);

        return result;
    }
}
