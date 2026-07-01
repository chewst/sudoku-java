package sudoku.userinterface;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sudoku.problemdomain.Coordinates;
import sudoku.problemdomain.SudokuGame;

import java.util.HashMap;

public class UserInterfaceImpl implements IUserInterfaceContract.View,
        EventHandler<KeyEvent> {

    private final Stage stage; // background window for the application
    private final Group root; // div in html

    // keep track of 81 different text fields
    private HashMap<Coordinates, SudokuTextField>  textFieldCoordinates;

    private IUserInterfaceContract.EventListener listener;

    private static final double WINDOW_Y = 732;
    private static final double WINDOW_X = 668;
    private static final double BOARD_PADDING = 50;
    private static final double BOARD_X_AND_Y = 576;

    private static final Color WINDOW_BACKGROUND_COLOUR = Color.rgb(0,150,136);
    private static final Color BOARD_BACKGROUND_COLOUR = Color.rgb(224,242,241);
    private static final String SUDOKU = "Sudoku";


    private void drawTextFields(Group root) {
    }

    private void drawSudokuBoard(Group root) {
    }

    private void drawTitle(Group root) {
    }

    private void drawBackground(Group root) {
        
    }

    @Override
    public void setListener(IUserInterfaceContract.EventListener listener) {
        this listener = listener;

    }

    @Override
    public void handle(KeyEvent keyEvent) {

    }


    @Override
    public void updateSquare(int x, int y, int input) {

    }

    @Override
    public void showDialog(String message) {

    }

    @Override
    public void updateBoard(SudokuGame game) {

    }

    @Override
    public void showError(String message) {

    }
}
