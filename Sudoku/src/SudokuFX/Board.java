package SudokuFX;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    // Dimension of the board
    private final int DIM = 9;
    // Maximum limit for region
    private final int LIMIT = 3;
    // 2D array to represent sudoku board
    private int board[][];
    // 2D array to represent locked pieces
    private boolean lock[][];
    // Checker to check and generate numbers
    private Checker checker;
    // Randomizer
    private Random randomizer;

    /**
     * Constructor for Board
     * Initialize board with a 2D array of 9x9 size
     */
    public Board(int difficulty) {
        // Create a 2D array of all 0s
        board = new int[9][9];
        lock = new boolean[9][9];
        checker = new Checker();
        randomizer = new Random();
        // Generates random numbers on the Sudoku board
        randomlyGenerate(difficulty);
        // lock pieces
        lock();
    }

    /**
     * Locks the nonzero generated numbers
     */
    public void lock() {
        // lock the pieces
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++) {
                if (board[row][column] != 0)
                    lock[row][column] = true;
            }
        }
    }

    /**
     * Check the board
     * @return true if complete else false
     */
    public boolean check() {
        return checker.checkRows(board) && checker.checkColumns(board) && checker.checkNonets(board);
    }

    /**
     * Uses backtracking to solve the rest of the board automatically with recursion
     * @return true if solvable for the specific row and column
     */
    public boolean solve() {
        // iterate through the whole board
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++) {
                // check if empty
                if (board[row][column] == 0) {
                    // make a guess
                    for (int guess = 1; guess <= 9; guess++) {
                        if (checker.checkIfUsable(guess, row, column, board)) {
                            // assign a usable number to that location
                            board[row][column] = guess;
                            // keep going recursively through the whole board
                            if (solve()) {
                                return true;
                            } else {
                                // if no guesses remain, then assign the latest assignment to 0 and go back
                                board[row][column] = 0;
                            }
                        }
                    }
                    // signal to go back and try other numbers through the for loop since no guesses remain
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if location is locked
     * @param row - row number
     * @param column - column number
     * @return true if locked else false
     */
    public boolean checkIfLocked(int row, int column) {
        return lock[row - 1][column - 1];
    }

    /**
     * Make a move by adding a number to the board
     * @param number - number to be added
     * @param row - row number of location
     * @param column - column number of location
     */
    public void makeMove(int number, int row, int column) {
        board[row - 1][column - 1] = number;
    }

    /**
     * Gets the number at the row and column
     * @param row - row number
     * @param column - column number
     * @return number at that row and column
     */
    public int getNumber(int row, int column) {
        return board[row][column];
    }

    /**
     * Removes a certain amount of numbers from the Sudoku board
     * @param difficulty - amount to remove
     */
    private void remove(int difficulty) {
        // array to store amount to remove according to difficulty
        int [] diff = {30, 40, 50, 60};
        // get the amount to remove
        int amount = diff[difficulty - 1];
        int row, column;
        // remove a random number from the board amount times
        for (int removed = 0; removed < amount; removed++) {
            do {
                row = randomizer.nextInt(DIM);
                column = randomizer.nextInt(DIM);
            } while (board[row][column] == 0);
            board[row][column] = 0;
        }
    }

    /**
     * Fills the diagonal of the Sudoku board
     */
    private void fillDiagonal() {
        int counter = 0;
        ArrayList<Integer> marker = new ArrayList<>();
        for (int i = 0; i < DIM; i++) {
            int generatedNumber;
            // keep generating a number if it has been generated before in the same nonet
            do {
                generatedNumber = (int) Math.ceil(Math.random() * DIM);
            } while (marker.contains(generatedNumber));
            // assign generatedNumber and mark it
            board[i][i] = generatedNumber;
            marker.add(generatedNumber);
            counter++;
            if (counter == LIMIT) {
                marker = new ArrayList<>();
                counter = 0;
            }
        }
    }

    /**
     * Randomly generate numbers to put on the board
     */
    private void randomlyGenerate(int difficulty) {
        fillDiagonal();
        // solve the remaining puzzle
        solve();
        // remove amount corresponding to difficulty
        remove(difficulty);
    }

    /**
     * Formats the Sudoku board into a string to be printed
     * @return ret - formatted string
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++)
                ret.append(board[row][column]);
        }
        return ret.toString();
    }
}
