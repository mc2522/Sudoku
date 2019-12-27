package SudokuFX;

import java.util.ArrayList;

public class Board {
    // Dimension of the board
    private final int DIM = 9;
    // Maximum limit for region
    private final int LIMIT = 3;
    // 2D array to represent sudoku board
    private int board[][];

    /**
     * Constructor for Board
     * Initialize board with a 2D array of 9x9 size
     */
    public Board() {
        // Create a 2D array of all 0s
        board = new int[9][9];
        // Generates random numbers on the Sudoku board
        randomlyGenerate();
    }

    /**
     *
     */
    public void fillDiagonal() {
        int counter = 0;
        ArrayList<Integer> marker = new ArrayList<>();
        for (int i = 0; i < DIM; i++) {
            int generatedNumber;
            // keep generating a number if it has been generated before in the same region
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
     * Randomly generate numbers to put on the board
     */
    public void randomlyGenerate() {
        fillDiagonal();
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
