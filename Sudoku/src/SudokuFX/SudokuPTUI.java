package SudokuFX;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

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
    // input message
    private static final String INPUT = "\nWhen inputting a move, please enter it in the format: [number to place] [row number (left to right) (1-9)] [column number (up to down) (1-9)]\n";
    // Scanner to scan input from user
    private static Scanner scan;
    // Sudoku board
    private static Board board;

    /**
     * Formats and prints the board.
     * @param board - Sudoku board
     */
    private static void printBoard(String board) {
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

    private static int getDifficulty(boolean retry) {
        if (retry)
            System.out.print("\nInvalid difficulty. Please enter the desired difficulty again [1 = BEGINNER | 2 = INTERMEDIATE | 3 = EXPERT | 4 = GRANDMASTER]: ");
        int difficulty;
        while (true) {
            try {
                difficulty = scan.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.print("\nInvalid difficulty. Please enter the desired difficulty again [1 = BEGINNER | 2 = INTERMEDIATE | 3 = EXPERT | 4 = GRANDMASTER]: ");
                scan.nextLine();
            }
        }
        return difficulty;
    }

    /**
     * Introduction to the game, prints introductory message and asks for difficulty
     */
    private static int introduction() {
        // Introductory message
        System.out.println("\nWelcome! Let's play SUDOKU!\nType HELP at any time to view available commands and tips.\n");
        System.out.print("Please enter your desired difficulty [1 = BEGINNER | 2 = INTERMEDIATE | 3 = EXPERT | 4 = GRANDMASTER]: ");
        int difficulty = getDifficulty(false);
        while (difficulty != 1 && difficulty != 2 && difficulty != 3 && difficulty != 4) {
            difficulty = getDifficulty(true);
        }
        // skip the new line
        scan.nextLine();
        return difficulty;
    }

    /**
     * Restart the game
     */
    private static void restart() {
        int difficulty = introduction();
        board = new Board(difficulty);
    }

    /**
     * Asks the user if they want to restart the game or exit
     */
    private static void askIfRestart() {
        System.out.print("\nWould you like to restart a new puzzle or exit? Type RESTART to restart else exit: ");
        if (scan.nextLine().toUpperCase().equals("RESTART")) {
            System.out.println("\nRestarting...");
            restart();
        } else {
            System.out.println("\nExiting...");
            exit(0);
        }
    }

    /**
     * Takes the scanned input and interprets it
     * @param input - scanned input from user
     * @return true if move can be made else false
     */
    private static boolean interpretMove(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input);
        int number, row, column;
        // get the number, row, and column from the input
        try {
            number = Integer.parseInt(tokenizer.nextToken());
            row = Integer.parseInt(tokenizer.nextToken());
            column = Integer.parseInt(tokenizer.nextToken());
        } catch (NumberFormatException e) {
            return false;
        }
        // if more tokens, then input is in incorrect format
        if (tokenizer.hasMoreTokens()) {
            return false;
        } else if ((number < 1 || number > 9) || (row < 1 || row > 9) || (column < 1 || column > 9)) {
            return false;
        }
        // check if locked
        if (board.checkIfLocked(row - 1, column - 1))
            return false;
        // make a move if valid
        board.makeMove(number, row - 1, column - 1);
        return true;
    }

    /**
     * Read the user's input and query their input
     */
    private static void read(int difficulty) throws Exception {
        String input;
        // create a new board and print the board
        board = new Board(difficulty);
        // infinite loop until user exits
        while (true) {
            printBoard(board.toString());
            // query for input
            System.out.print("\nEnter your command: ");
            input = scan.nextLine().toUpperCase();
            switch (input) {
                case "HELP":
                    System.out.println(HELP);
                    Thread.sleep(3000);
                    break;
                case "SOLVE":
                    board.solve();
                    printBoard(board.toString());
                    Thread.sleep(1000);
                    askIfRestart();
                    break;
                case "RESTART":
                    restart();
                    break;
                case "INPUT":
                    System.out.println(INPUT);
                    System.out.print("Enter your move: ");
                    input = scan.nextLine();
                    while (!interpretMove(input)) {
                        System.out.print("\nMove not valid. Enter your move again: ");
                        input = scan.nextLine();
                    }
                    break;
                case "CHECK":
                    if (board.check()) {
                        System.out.println("\nPuzzle completed!");
                        askIfRestart();
                    } else {
                        System.out.println("\nPuzzle not correct. Keep trying!");
                        Thread.sleep(1000);
                    }
                    break;
                case "EXIT":
                    exit(0);
                    break;
                default:
                    System.out.println("\nInput not recognized. Enter another command.");
                    Thread.sleep(1000);
            }
        }
    }

    /**
     * Main function
     * @param args - command line args
     */
    public static void main(String [] args) {
        scan = new Scanner(System.in);
        // print introduction and ask for difficulty
        int difficulty = introduction();
        // start the game
        try {
            read(difficulty);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
