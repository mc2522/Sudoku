package SudokuFX;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Scanner;

public class SudokuPTUI {
    // Scanner to scan input from user
    private static Scanner scan;

    /**
     * Formats and prints the board.
     * @param board - Sudoku board
     */
    public static void printBoard(String board) {
        // formatting
        System.out.println();
        StringCharacterIterator iterator = new StringCharacterIterator(board);
        int counter = 1;
        int line = 0;
        while (true) {
            // print each number
            System.out.print(iterator.current() + " ");
            // check if new line needs to be printed
            if (counter % 9 == 0) {
                System.out.println();
                line++;
            // else if a divider needs to be printed for better visibility
            } else if (counter % 3 == 0) {
                System.out.print("| ");
            }
            counter++;
            iterator.next();
            // check if done printing
            if (iterator.current() == CharacterIterator.DONE)
                break;
            // check if divider needs to be printed for better visibility
            if (line % 3 == 0 && line != 0) {
                System.out.println("- - - + - - - + - - -");
                line = 0;
            }
        }
    }

    public static void main(String [] args) {
        scan = new Scanner(System.in);
        // Introductory message
        System.out.println("\nWelcome! Let's play SUDOKU!\nType HELP at any time to view available commands and tips.\n");
        System.out.print("Please enter your desired difficulty [1 = BEGINNER | 2 = INTERMEDIATE | 3 = EXPERT | 4 = GRANDMASTER]: ");
        int difficulty = scan.nextInt();
        // check for validity
        while (difficulty != 1 && difficulty != 2 && difficulty != 3 && difficulty != 4) {
            // query for correct input
            System.out.print("Invalid difficulty. Please enter the difficulty again [1 = BEGINNER | 2 = INTERMEDIATE | 3 = EXPERT | 4 = GRANDMASTER]: ");
            difficulty = scan.nextInt();
        }
        // create a new board
        Board board = new Board(difficulty);
        printBoard(board.toString());
    }
}
