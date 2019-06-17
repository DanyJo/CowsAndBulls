package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Computer {

    private List<String> possibleNumbers;
    private Random rand;
    private Scanner input;

    public Computer() {
        rand = new Random();
        input = new Scanner(System.in);
        possibleNumbers = AllPossibleCombinations();
    }

    public Boolean Play() {

        int initialIndex = rand.nextInt(possibleNumbers.size()) + 0;
        String initialNumber = possibleNumbers.get(initialIndex);

        System.out.print("Computer's guess is ");
        System.out.println(initialNumber);

        System.out.println("Enter the number of bulls and cows:");
        int bullsCount = input.nextInt();
        int cowsCount = input.nextInt();

        if (bullsCount == 4) {
            System.out.println("Computer wins!");
            return true;
        }

        possibleNumbers.remove(initialIndex);
        NumbersPruning(possibleNumbers, initialNumber, bullsCount, cowsCount);

        if (possibleNumbers.size() < 1 && bullsCount != 4) {
            throw new IllegalArgumentException();
        }

        System.out.println("------------------------------------");

        return false;
    }

    private void NumbersPruning(List<String> possibleNumbers, String initialNumber, int bullsCount, int cowsCount) {
        for (int i = 0; i < possibleNumbers.size(); i++) {
            String currentNumber = possibleNumbers.get(i);
            int currentBullsCount = 0;
            int currentCowsCount = 0;

            for (int j = 0; j < initialNumber.length(); j++) {
                if (currentNumber.contains(initialNumber.charAt(j) + "")) {
                    currentCowsCount++;
                    if (currentNumber.charAt(j) == initialNumber.charAt(j)) {
                        currentBullsCount++;
                        currentCowsCount--;
                    }
                }
            }
            if (currentBullsCount != bullsCount || currentCowsCount != cowsCount) {
                possibleNumbers.remove(i);
                i--;
            }
        }
    }

    private List<String> AllPossibleCombinations() {
        List<String> combinations = new ArrayList<String>();

        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (i != j) {
                    for (int k = 0; k <= 9; k++) {
                        if (k != i && k != j) {
                            for (int p = 0; p <= 9; p++) {
                                if (p != i && p != j && p != k) {
                                    combinations.add(i + "" + j + "" + k + "" + p + "");
                                }
                            }
                        }
                    }
                }
            }
        }

        return combinations;
    }
}
