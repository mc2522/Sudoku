package SudokuFX;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class SudokuPTUI {

    /**
     * Formats and prints the board.
     * @param board - Sudoku board
     */
    public static void printBoard(String board) {
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
        // Introductory message
        System.out.println("\nWelcome! Let's play SUDOKU!\nType HELP at any time to view available commands and tips.\n");
        // create a new board
        Board board = new Board();
        printBoard(board.toString());
    }
}
