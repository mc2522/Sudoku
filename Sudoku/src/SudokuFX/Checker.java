package SudokuFX;

import java.util.ArrayList;

public class Checker {
    private final int DIM = 9;

    /**
     * Gets the nonet's numbers
     * @param row - starting row
     * @param column - starting column
     * @return nonet - nonet's numbers
     */
    public ArrayList<Integer> getNonetNumbers(int row, int column, int [][] board) {
        ArrayList<Integer> nonet = new ArrayList<>();
        if (row >= 0 && row <= 2 && column >= 0 && column <= 2) {
            for (int nonetRow = 0; nonetRow <= 2; nonetRow++) {
                for (int nonetColumn = 0; nonetColumn <= 2; nonetColumn++)
                    nonet.add(board[nonetRow][nonetColumn]);
            }
        } else if (row >= 0 && row <= 2 && column >= 3 && column <= 5) {
            for (int nonetRow = 0; nonetRow <= 2; nonetRow++) {
                for (int nonetColumn = 3; nonetColumn <= 5; nonetColumn++)
                    nonet.add(board[nonetRow][nonetColumn]);
            }
        } else if (row >= 0 && row <= 2 && column >= 6 && column <= 8) {
            for (int nonetRow = 0; nonetRow <= 2; nonetRow++) {
                for (int nonetColumn = 6; nonetColumn <= 8; nonetColumn++)
                    nonet.add(board[nonetRow][nonetColumn]);
            }
        } else if (row >= 3 && row <= 5 && column >= 0 && column <= 2) {
            for (int nonetRow = 3; nonetRow <= 5; nonetRow++) {
                for (int nonetColumn = 0; nonetColumn <= 2; nonetColumn++)
                    nonet.add(board[nonetRow][nonetColumn]);
            }
        } else if (row >= 3 && row <= 5 && column >= 3 && column <= 5) {
            for (int nonetRow = 3; nonetRow <= 5; nonetRow++) {
                for (int nonetColumn = 3; nonetColumn <= 5; nonetColumn++)
                    nonet.add(board[nonetRow][nonetColumn]);
            }
        } else if (row >= 3 && row <= 5 && column >= 6 && column <= 8) {
            for (int nonetRow = 3; nonetRow <= 5; nonetRow++) {
                for (int nonetColumn = 6; nonetColumn <= 8; nonetColumn++)
                    nonet.add(board[nonetRow][nonetColumn]);
            }
        } else if (row >= 6 && row <= 8 && column >= 0 && column <= 2) {
            for (int nonetRow = 6; nonetRow <= 8; nonetRow++) {
                for (int nonetColumn = 0; nonetColumn <= 2; nonetColumn++)
                    nonet.add(board[nonetRow][nonetColumn]);
            }
        } else if (row >= 6 && row <= 8 && column >= 3 && column <= 5) {
            for (int nonetRow = 6; nonetRow <= 8; nonetRow++) {
                for (int nonetColumn = 3; nonetColumn <= 5; nonetColumn++)
                    nonet.add(board[nonetRow][nonetColumn]);
            }
        } else if (row >= 6 && row <= 8 && column >= 6 && column <= 8) {
            for (int nonetRow = 6; nonetRow <= 8; nonetRow++) {
                for (int nonetColumn = 6; nonetColumn <= 8; nonetColumn++)
                    nonet.add(board[nonetRow][nonetColumn]);
            }
        }
        return nonet;
    }

    /**
     * Checks if the number can be used.
     * @param number - number to check
     * @param row - row number
     * @param column - column number
     * @return true if usable else false
     */
    public boolean checkIfUsable(int number, int row, int column, int [][] board) {
        // check row
        for (int columnIndex = 0; columnIndex < DIM; columnIndex++) {
            if (board[row][columnIndex] == number)
                return false;
        }
        // check column
        for (int rowIndex = 0; rowIndex < DIM; rowIndex++) {
            if (board[rowIndex][column] == number)
                return false;
        }
        // check nonet
        ArrayList<Integer> nonet = getNonetNumbers(row, column, board);
        for (int num : nonet) {
            if (number == num)
                return false;
        }
        return true;
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
