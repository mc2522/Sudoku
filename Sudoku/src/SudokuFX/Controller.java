package SudokuFX;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

public class Controller {
    private int DIM = 9;
    private ArrayList<Button> gridButtons;
    private String selected, pastSelected;
    private Board initialBoard, board;
    private boolean finished, started;
    public Text status;
    public GridPane gridPane;
    public Button check, solve, one, two, three, four, five, six, seven, eight, nine,
            beginner, intermediate, expert, pastSelection,
            zero_zero, zero_one, zero_two, zero_three, zero_four, zero_five, zero_six, zero_seven, zero_eight,
            one_zero, one_one, one_two, one_three, one_four, one_five, one_six, one_seven, one_eight,
            two_zero, two_one, two_two, two_three, two_four, two_five, two_six, two_seven, two_eight,
            three_zero, three_one, three_two, three_three, three_four, three_five, three_six, three_seven, three_eight,
            four_zero, four_one, four_two, four_three, four_four, four_five, four_six, four_seven, four_eight,
            five_zero, five_one, five_two, five_three, five_four, five_five, five_six, five_seven, five_eight,
            six_zero, six_one, six_two, six_three, six_four, six_five, six_six, six_seven, six_eight,
            seven_zero, seven_one, seven_two, seven_three, seven_four, seven_five, seven_six, seven_seven, seven_eight,
            eight_zero, eight_one, eight_two, eight_three, eight_four, eight_five, eight_six, eight_seven, eight_eight;

    /**
     * Checks the puzzle from GUI
     */
    public void check() {
        if (!finished && started) {
            if (!board.check()) {
                status.setText("Incomplete. Try again!");
            } else {
                status.setText("Sudoku board is correct. Congratulations! Start a new game or exit.");
                finished = true;
            }
        }
    }

    /**
     * Solves the puzzle from GUI
     */
    public void solve() {
        if (started && !finished) {
            board.copy(initialBoard);
            board.solve();
            finished = true;
            status.setText("Sudoku board is solved. Start a new game to try again.");
            setBoard();
        }
    }

    /**
     * Formats and prints the board in plaintext.
     */
    private void printBoard() {
        // formatting
        System.out.println();
        StringCharacterIterator iterator = new StringCharacterIterator(board.toString());
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

    /**
     * Resets CSS when new game starts
     */
    public void resetGraphics() {
        for (Button button : gridButtons) {
            button.setText("");
            button.setStyle("-fx-text-fill: black; -fx-color: white");
        }
    }

    /**
     * Applies CSS to locked locations on board so user doesn't get confused on where is locked
     */
    public void applyLockGraphics() {
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++) {
                if (board.checkIfLocked(row, column)) {
                    for (Button button : gridButtons) {
                        if (gridPane.getRowIndex(button) == row && gridPane.getColumnIndex(button) == column)
                            button.setStyle("-fx-text-fill: red; -fx-color: lightgrey; -fx-focus-color: transparent");
                    }
                }
            }
        }
    }

    /**
     * Starts the puzzle from GUI
     */
    public void start(ActionEvent e) {
        initialBoard = new Board();
        // clear the board
        resetGraphics();
        // create a new board depending on the difficulty chosen
        if (e.getSource().equals(beginner)) {
            board = new Board(1);
            setBoard();
        } else if (e.getSource().equals(intermediate)) {
            board = new Board(2);
            setBoard();
        } else if (e.getSource().equals(expert)) {
            board = new Board(3);
            setBoard();
        }
        // copy the initial state of the board so in case of solving, solve the initial board
        initialBoard.copy(board);
        finished = false;
        started = true;
        applyLockGraphics();
        status.setText(((Button) e.getSource()).getText() + " Difficulty");
    }

    /**
     * Changes the selected button
     * @param e - Action Event
     */
    public void changeSelected(ActionEvent e) {
        if (started) {
            if (selected != null) {
                pastSelected = selected;
                for (Button button : gridButtons) {
                    if (button.getId().equals(pastSelected)) {
                        if (!board.checkIfLocked(gridPane.getRowIndex(button), gridPane.getColumnIndex(button)))
                            button.setStyle("-fx-text-fill: black; -fx-color: white");
                    }
                }
            }
            selected = ((Button) e.getSource()).getId();
            for (Button button : gridButtons) {
                if (button.getId().equals(selected)) {
                    if (!board.checkIfLocked(gridPane.getRowIndex(button), gridPane.getColumnIndex(button)))
                        button.setStyle("-fx-text-fill: black; -fx-color: white; -fx-border-style: solid; -fx-border-color: black; -fx-border-width: 2");
                }
            }
        }
    }

    /**
     * Checks if the location can be changed and change both board and gui if allowed
     * @param e - ActionEvent
     */
    public void setInput(ActionEvent e) {
        if (!finished && started) {
            String input;
            if (selected != null) {
                input = ((Button) e.getSource()).getText();
                for (Button button : gridButtons) {
                    int row = gridPane.getRowIndex(button);
                    int column = gridPane.getColumnIndex(button);
                    if (selected.equals(button.getId()) && !board.checkIfLocked(row, column)) {
                        button.setText(input);
                        board.makeMove(Integer.parseInt(input), row, column);
                    }
                }
            }
        }
    }

    /**
     * Sets the board up by injecting board numbers into the GUI
     */
    public void setBoard() {
        for (Button button : gridButtons) {
            String number = Integer.toString(board.getNumber(gridPane.getRowIndex(button), gridPane.getColumnIndex(button)));
            if (!number.equals("0"))
                button.setText(number);
        }
        printBoard();
    }

    /**
     * Initializer function
     */
    public void initialize() {
        System.out.println("GUI START.");
        finished = false;
        started = false;
        gridButtons = new ArrayList<>();
        ObservableList<Node> children = gridPane.getChildren();
        for (Node child : children) {
            if (gridPane.getRowIndex(child) != null && gridPane.getColumnIndex(child) != null)
                gridButtons.add((Button) child);
        }
        status.setText("Please choose a difficulty to begin the game.");

    }
}
