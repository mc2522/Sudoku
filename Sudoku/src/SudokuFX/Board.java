package SudokuFX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board {
    // Dimension of the board
    private final int DIM = 9;
    // Maximum limit for region
    private final int LIMIT = 3;
    // 2D array to represent sudoku board
    private int board[][];
    // Checker to check and generate numbers
    private Checker checker;

    /**
     * Constructor for Board
     * Initialize board with a 2D array of 9x9 size
     */
    public Board() {
        // Create a 2D array of all 0s
        board = new int[9][9];
        checker = new Checker();
        // Generates random numbers on the Sudoku board
        randomlyGenerate();
    }


    /**
     * Fills the diagonal of the Sudoku board
     */
    public void fillDiagonal() {
        int counter = 0;
        ArrayList<Integer> marker = new ArrayList<>();
        for (int i = 0; i < DIM; i++) {
            int generatedNumber;
            // keep generating a number if it has been generated before in the same nonet
            do {
                generatedNumber = (int) Math.ceil(Math.random() * DIM);
            } while (marker.contains(generatedNumber));
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
     * Uses backtracking to solve the rest of the board automatically with recursion
     * @return true if solvable for the specific row and column
     */
    public boolean solve() {
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++) {
                if (board[row][column] == 0) {
                    for (int guess = 1; guess <= 9; guess++) {
                        if (checker.checkIfUsable(guess, row, column, board)) {
                            board[row][column] = guess;

                            if (solve()) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Randomly generate numbers to put on the board
     */
    public void randomlyGenerate() {
        fillDiagonal();
        // solve the remaining puzzle
        solve();
    }

    @Override
    public String toString() {
        String ret = "";
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++)
                ret += board[row][column];
        }
        return ret;
    }
}
