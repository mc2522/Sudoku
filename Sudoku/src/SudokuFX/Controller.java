package SudokuFX;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

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
        if (selected != null) {
            input = ((Button)e.getSource()).getText();
            if (selected.equals("zero_zero")) {
                zero_zero.setText(input);
                if (!board.checkIfLocked(0, 0))
                    board.makeMove(Integer.parseInt(input), 0, 0);
            }
        }
    }

    /**
     * Sets the board up by injecting board numbers into the GUI
     */
    public void setBoard() {
        // row zero
        zero_zero.setText(Integer.toString(board.getNumber(0, 0)));
        zero_one.setText(Integer.toString(board.getNumber(0, 1)));
        zero_two.setText(Integer.toString(board.getNumber(0, 2)));
        zero_three.setText(Integer.toString(board.getNumber(0, 3)));
        zero_four.setText(Integer.toString(board.getNumber(0, 4)));
        zero_five.setText(Integer.toString(board.getNumber(0, 5)));
        zero_six.setText(Integer.toString(board.getNumber(0, 6)));
        zero_seven.setText(Integer.toString(board.getNumber(0, 7)));
        zero_eight.setText(Integer.toString(board.getNumber(0, 8)));
        // row one
        one_zero.setText(Integer.toString(board.getNumber(1, 0)));
        one_one.setText(Integer.toString(board.getNumber(1, 1)));
        one_two.setText(Integer.toString(board.getNumber(1, 2)));
        one_three.setText(Integer.toString(board.getNumber(1, 3)));
        one_four.setText(Integer.toString(board.getNumber(1, 4)));
        one_five.setText(Integer.toString(board.getNumber(1, 5)));
        one_six.setText(Integer.toString(board.getNumber(1, 6)));
        one_seven.setText(Integer.toString(board.getNumber(1, 7)));
        one_eight.setText(Integer.toString(board.getNumber(1, 8)));
        // row two
        two_zero.setText(Integer.toString(board.getNumber(2, 0)));
        two_one.setText(Integer.toString(board.getNumber(2, 1)));
        two_two.setText(Integer.toString(board.getNumber(2, 2)));
        two_three.setText(Integer.toString(board.getNumber(2, 3)));
        two_four.setText(Integer.toString(board.getNumber(2, 4)));
        two_five.setText(Integer.toString(board.getNumber(2, 5)));
        two_six.setText(Integer.toString(board.getNumber(2, 6)));
        two_seven.setText(Integer.toString(board.getNumber(2, 7)));
        two_eight.setText(Integer.toString(board.getNumber(2, 8)));
        // row three
        three_zero.setText(Integer.toString(board.getNumber(3, 0)));
        three_one.setText(Integer.toString(board.getNumber(3, 1)));
        three_two.setText(Integer.toString(board.getNumber(3, 2)));
        three_three.setText(Integer.toString(board.getNumber(3, 3)));
        three_four.setText(Integer.toString(board.getNumber(3, 4)));
        three_five.setText(Integer.toString(board.getNumber(3, 5)));
        three_six.setText(Integer.toString(board.getNumber(3, 6)));
        three_seven.setText(Integer.toString(board.getNumber(3, 7)));
        three_eight.setText(Integer.toString(board.getNumber(3, 8)));
        // row four
        four_zero.setText(Integer.toString(board.getNumber(4, 0)));
        four_one.setText(Integer.toString(board.getNumber(4, 1)));
        four_two.setText(Integer.toString(board.getNumber(4, 2)));
        four_three.setText(Integer.toString(board.getNumber(4, 3)));
        four_four.setText(Integer.toString(board.getNumber(4, 4)));
        four_five.setText(Integer.toString(board.getNumber(4, 5)));
        four_six.setText(Integer.toString(board.getNumber(4, 6)));
        four_seven.setText(Integer.toString(board.getNumber(4, 7)));
        four_eight.setText(Integer.toString(board.getNumber(4, 8)));
        // row five
        five_zero.setText(Integer.toString(board.getNumber(5, 0)));
        five_one.setText(Integer.toString(board.getNumber(5, 1)));
        five_two.setText(Integer.toString(board.getNumber(5, 2)));
        five_three.setText(Integer.toString(board.getNumber(5, 3)));
        five_four.setText(Integer.toString(board.getNumber(5, 4)));
        five_five.setText(Integer.toString(board.getNumber(5, 5)));
        five_six.setText(Integer.toString(board.getNumber(5, 6)));
        five_seven.setText(Integer.toString(board.getNumber(5, 7)));
        five_eight.setText(Integer.toString(board.getNumber(5, 8)));
        // row six
        six_zero.setText(Integer.toString(board.getNumber(6, 0)));
        six_one.setText(Integer.toString(board.getNumber(6, 1)));
        six_two.setText(Integer.toString(board.getNumber(6, 2)));
        six_three.setText(Integer.toString(board.getNumber(6, 3)));
        six_four.setText(Integer.toString(board.getNumber(6, 4)));
        six_five.setText(Integer.toString(board.getNumber(6, 5)));
        six_six.setText(Integer.toString(board.getNumber(6, 6)));
        six_seven.setText(Integer.toString(board.getNumber(6, 7)));
        six_eight.setText(Integer.toString(board.getNumber(6, 8)));
        // row seven
        seven_zero.setText(Integer.toString(board.getNumber(7, 0)));
        seven_one.setText(Integer.toString(board.getNumber(7, 1)));
        seven_two.setText(Integer.toString(board.getNumber(7, 2)));
        seven_three.setText(Integer.toString(board.getNumber(7, 3)));
        seven_four.setText(Integer.toString(board.getNumber(7, 4)));
        seven_five.setText(Integer.toString(board.getNumber(7, 5)));
        seven_six.setText(Integer.toString(board.getNumber(7, 6)));
        seven_seven.setText(Integer.toString(board.getNumber(7, 7)));
        seven_eight.setText(Integer.toString(board.getNumber(7, 8)));
        // row eight
        eight_zero.setText(Integer.toString(board.getNumber(8, 0)));
        eight_one.setText(Integer.toString(board.getNumber(8, 1)));
        eight_two.setText(Integer.toString(board.getNumber(8, 2)));
        eight_three.setText(Integer.toString(board.getNumber(8, 3)));
        eight_four.setText(Integer.toString(board.getNumber(8, 4)));
        eight_five.setText(Integer.toString(board.getNumber(8, 5)));
        eight_six.setText(Integer.toString(board.getNumber(8, 6)));
        eight_seven.setText(Integer.toString(board.getNumber(8, 7)));
        eight_eight.setText(Integer.toString(board.getNumber(8, 8)));
        // status message
        status.setText("");
    }

    /**
     * Initializer function
     */
    public void initialize() {
        System.out.println("GUI START.");
        checker = new Checker();
        gridButtons = new ArrayList<>();
        status.setText("Please choose a difficulty to begin the game.");
    }
}
