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
    private ArrayList<Button> gridButtons;
    private Checker checker;
    private String selected;
    private Board board;
    public Text status;
    public GridPane gridPane;
    public Button check, solve, one, two, three, four, five, six, seven, eight, nine,
            beginner, intermediate, expert, grandmaster,
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
        if (!board.check()) {
            // TODO change status message, first add one
        } else {
            // TODO
        }
    }

    /**
     * Solves the puzzle from GUI
     */
    public void solve() {
        board.solve();
        setBoard();
    }

    /**
     * Formats and prints the board.
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

    public void printLock() {
        for (int row = 1; row <= 9; row++) {
            for (int column = 1; column <= 9; column++) {
                if (board.checkIfLocked(row, column)) {
                    System.out.println(Integer.toString(row) + " " + Integer.toString(column));
                }
            }
        }
    }

    /**
     * Starts the puzzle from GUI
     */
    public void start(ActionEvent e) {
        if (e.getSource().equals(beginner)) {
            board = new Board(1);
            setBoard();
        } else if (e.getSource().equals(intermediate)) {
            board = new Board(2);
            setBoard();
        } else if (e.getSource().equals(expert)) {
            board = new Board(3);
            setBoard();
        } else if (e.getSource().equals(grandmaster)) {
            board = new Board(4);
            setBoard();
        }
    }

    /**
     * Changes the selected button
     * @param e - Action Event
     */
    public void changeSelected(ActionEvent e) {
        selected = ((Button)e.getSource()).getId();
        status.setText(selected + " has been selected.");
    }

    /**
     * Checks if the location can be changed and change both board and gui if allowed
     * @param e - ActionEvent
     */
    public void setInput(ActionEvent e) {
        String input;
        /*if (selected != null) {
            input = ((Button) e.getSource()).getText();
            if (selected.equals("zero_zero")) {
                zero_zero.setText(input);
                if (!board.checkIfLocked(0, 0))
                    board.makeMove(Integer.parseInt(input), 0, 0);
            }
        }*/
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

    /**
     * Sets the board up by injecting board numbers into the GUI
     */
    public void setBoard() {
        for (Button button : gridButtons) {
            //System.out.println("row: " + Integer.toString(gridPane.getRowIndex(button)) + " column: " + gridPane.getColumnIndex(button));
            button.setText(Integer.toString(board.getNumber(gridPane.getRowIndex(button), gridPane.getColumnIndex(button))));
        }
        // status message
        status.setText("");
        printBoard();
    }

    /**
     * Initializer function
     */
    public void initialize() {
        System.out.println("GUI START.");
        checker = new Checker();
        gridButtons = new ArrayList<>();
        ObservableList<Node> children = gridPane.getChildren();
        for (Node child : children) {
            if (gridPane.getRowIndex(child) != null && gridPane.getColumnIndex(child) != null) {
                gridButtons.add((Button) child);
                System.out.println("row: " + Integer.toString(gridPane.getRowIndex(child)) + " column: " + gridPane.getColumnIndex(child));
            }
        }
        status.setText("Please choose a difficulty to begin the game.");
    }
}
