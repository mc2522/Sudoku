package SudokuFX;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class Controller {
    private Checker checker;
    private Board board;
    public Button check, solve, restart, one, two, three, four, five, six, seven, eight, nine, selected;

    /**
     * Checks the puzzle from GUI
     */
    public boolean check() {
        return board.check();
    }

    /**
     * Solves the puzzle from GUI
     */
    public void solve() {
        board.solve();
    }

    /**
     * Starts the puzzle from GUI
     */
    public void start() {
        // TODO
    }

    public void changeSelected(ActionEvent e) {
        switch (e.getSource().toString()) {
            // TODO
        }
    }

    public void setInput(ActionEvent e) {
        switch (e.getSource().toString()) {
            case "one":
                break;
            case "two":
                break;
            case "three":
                break;
            case "four":
                break;
            case "five":
                break;
            case "six":
                break;
            case "seven":
                break;
            case "eight":
                break;
            case "nine":
                break;
        }
    }

    /**
     * Initializer function
     */
    public void initialize() {
        System.out.println("GUI START.");
        checker = new Checker();
        // TODO change by asking for difficulty
        board = new Board(3);
    }
}
