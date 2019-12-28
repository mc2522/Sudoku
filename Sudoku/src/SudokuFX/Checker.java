package SudokuFX;

import java.util.ArrayList;

public class Checker {
    /**
     * Get an ArrayList of integers that can be used.
     * @param numbers - row/column to check
     * @return availableNumbers - ArrayList of available numbers
     */
    public ArrayList<Integer> getAvailableNumbers(int [] numbers) {
        ArrayList<Integer> availableNumbers = new ArrayList<>();
        // Add all numbers 1-9 into an ArrayList
        for (int number = 1; number < 10; number++)
            availableNumbers.add(number);
        // Remove all numbers that appeared from the ArrayList
        for (int number : numbers) {
            if (number != 0)
                availableNumbers.remove((Integer)number);
        }
        return availableNumbers;
    }

    /**
     * Get an ArrayList of integers that can be used.
     * @param nonet - nonet to check
     * @return availableNumbers - ArrayList of available numbers
     */
    public ArrayList<Integer> getAvailableNumbers(ArrayList<Integer> nonet) {
        ArrayList<Integer> availableNumbers = new ArrayList<>();
        // Add all numbers 1-9 into an ArrayList
        for (int number = 1; number < 10; number++)
            availableNumbers.add(number);
        // Remove all numbers that appeared from the ArrayList
        for (int number : nonet) {
            if (number != 0)
                availableNumbers.remove((Integer)number);
        }
        return availableNumbers;
    }

    /**
     * generates a new integer that is unused in the row/column and nonet
     * @param numbers - row or column of numbers
     * @param nonet - nonet of numbers
     * @return unused integer in row/column and nonet
     */
    public int generateAvailableNumber(int [] numbers, ArrayList<Integer> nonet) {
        ArrayList<Integer> availableNumbers = getAvailableNumbers(numbers);
        ArrayList<Integer> nonetAvailableNumbers = getAvailableNumbers(nonet);
        ArrayList<Integer> commonNumbers = new ArrayList<>();
        for (int number : availableNumbers) {
            if (nonetAvailableNumbers.contains(number))
                commonNumbers.add(number);
        }
        int size = commonNumbers.size();
        if (size != 0) {
            return commonNumbers.get((int) Math.random() * size);
        } else {
            return 0;
        }
    }

    /**
     * Checks the row, column, or nonet
     * @return true if is valid else false
     */
    public boolean check(int [] numbers) {
        boolean [] marker = new boolean[9];
        // mark each number in the row and return false if there are any duplicates
        for (int number : numbers) {
            if (!marker[number - 1]) {
                marker[number - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }
}
