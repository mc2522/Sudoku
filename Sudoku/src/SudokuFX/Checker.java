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
     * Checks the rows
     * @param board - board to check
     * @return true if board's rows are good else false
     */
    public boolean checkRows(int [][] board) {
        boolean [] marker;
        for (int column = 0; column < DIM; column++) {
            marker = new boolean[DIM];
            for (int row = 0; row < DIM; row++) {
                if (board[row][column] == 0) {
                    return false;
                } else if (marker[board[row][column] - 1]) {
                    return false;
                }
                marker[board[row][column] - 1] = true;
            }
        }
        return true;
    }

    /**
     * Checks the columns
     * @param board - board to check
     * @return true if board's columns are good else false
     */
    public boolean checkColumns(int [][] board) {
        boolean [] marker;
        for (int row = 0; row < DIM; row++) {
            marker = new boolean[DIM];
            for (int column = 0; column < DIM; column++) {
                if (board[row][column] == 0) {
                    return false;
                } else if (marker[board[row][column] - 1]) {
                    return false;
                }
                marker[board[row][column] - 1] = true;
            }
        }
        return true;
    }

    /**
     * Check the nonets
     * @param board - board to check
     * @return true if board's nonets are good else false
     */
    public boolean checkNonets(int [][] board) {
        boolean [] marker;
        int row, column;
        for (row = 0; row < DIM; row += 2) {
            for (column = 0; column < DIM; column += 2) {
                // boolean array to keep track of every number in nonet
                marker = new boolean[DIM];
                ArrayList<Integer> nonet = getNonetNumbers(row, column, board);
                for (int number : nonet) {
                    if (nonet.contains(0)) {
                        return false;
                    } else if (marker[number - 1]) {
                        return false;
                    }
                    marker[number - 1] = true;
                }
                column++;
            }
            row++;
        }
        return true;
    }
}
