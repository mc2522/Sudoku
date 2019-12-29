package SudokuFX;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Scanner;

import static java.lang.System.exit;

public class SudokuPTUI {
    // help message
    private static final String HELP =
            "\nHELP: View available commands and tips.\n" +
            "CHECK: Check the current Sudoku board if complete.\n" +
            "SOLVE: Solve the current Sudoku board.\n" +
            "INPUT: Enter the next move\n" +
            "RESTART: Restart by generating a new puzzle.\n" +
            "EXIT: Exit the game.";
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

    public static void main(String [] args) throws Exception {
        String input;
        scan = new Scanner(System.in);
        // Introductory message
        System.out.println("\nWelcome! Let's play SUDOKU!\nType HELP at any time to view available commands and tips.\n");
        System.out.print("Please enter your desired difficulty [1 = BEGINNER | 2 = INTERMEDIATE | 3 = EXPERT | 4 = GRANDMASTER]: ");
        int difficulty = scan.nextInt();
        // skip the new line
        scan.nextLine();
        // check for validity
        while (difficulty != 1 && difficulty != 2 && difficulty != 3 && difficulty != 4) {
            // query for correct input
            System.out.print("Invalid difficulty. Please enter the difficulty again [1 = BEGINNER | 2 = INTERMEDIATE | 3 = EXPERT | 4 = GRANDMASTER]: ");
            difficulty = scan.nextInt();
        }
        // infinite loop until user exits
        while (true) {
            // create a new board and print the board
            Board board = new Board(difficulty);
            printBoard(board.toString());
            // query for input
            System.out.print("\nEnter your command: ");
            input = scan.nextLine().toUpperCase();
            switch (input) {
                case "HELP":
                    System.out.println(HELP);
                    // sleep for 4 seconds so help message can be read
                    Thread.sleep(4000);
                    break;
                case "SOLVE":
                    board.solve();
                    printBoard(board.toString());
                    System.out.print("\nWould you like to restart a new puzzle or exit? Type RESTART to restart else exit: ");
                    if (scan.nextLine().toUpperCase().equals("RESTART")) {
                        // restart
                        System.out.println("\nRestarting...");
                        exit(0);
                    } else {
                        System.out.println("\nExiting...");
                        exit(0);
                    }
                    break;
                case "EXIT":
                    exit(0);
                    break;
                default:
                    System.out.println("\nInput not recognized. Enter another command.");
                    // sleep for 1 second so message can be read
                    Thread.sleep(1000);
            }
        }
    }
}
