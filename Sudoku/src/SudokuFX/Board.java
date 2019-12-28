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

    public ArrayList<Integer> getNonetNumbers(int row, int column) {
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

    public void fillRest() {
        Checker checker = new Checker();
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++) {
                if (board[row][column] == 0)
                    board[row][column] = checker.generateAvailableNumber(board[row], getNonetNumbers(row, column));
            }
        }
    }

    /**
     * Randomly generate numbers to put on the board
     */
    public void randomlyGenerate() {
        fillDiagonal();
        fillRest();
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
