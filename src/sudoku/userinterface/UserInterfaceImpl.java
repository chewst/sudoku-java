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
    private final Group root; // a container, like a div in html

    // keep track of 81 different text fields
    private HashMap<Coordinates, SudokuTextField>  textFieldCoordinates;

    private IUserInterfaceContract.EventListener listener;

    private static final double WINDOW_Y = 732;
    private static final double WINDOW_X = 668;
    private static final double BOARD_PADDING = 50;    // margin from the window edge
    private static final double BOARD_X_AND_Y = 576;   // size of the whole Sudoku board

    private static final Color WINDOW_BACKGROUND_COLOUR = Color.rgb(0,150,136);
    private static final Color BOARD_BACKGROUND_COLOUR = Color.rgb(224,242,241);
    private static final String SUDOKU = "Sudoku";

    public UserInterfaceImpl(Stage stage) {
        this.stage = stage;
        this.root = new Group();
        this.textFieldCoordinates = new HashMap<>();
        initialiseUserInterface();
    }

    // orders matter - things added later appear on top
    // for example - grid lines are drawn last, which appear above cells
    private void initialiseUserInterface(){
        drawBackground(root);
        drawTitle(root);
        drawSudokuBoard(root);
        drawTextFields(root);
        drawGridLines(root);
        stage.show();

    }

    // draws the lines separating Sudoku cells (both vertical and horizontal)
    // includes thicker lines to separate 3x3 subgrids
    private void drawGridLines(Group root) {
        int xAndY = 114;    // starting pixel position of the first line (line 0)
        int index = 0;

        while (index < 8){    // 8 lines between 9x9
            int thickness;

            if (index == 2 || index == 5){
                thickness = 3;    // Make thicker lines for 3x3 box boundaries
            } else {
                thickness = 2;
            }

            Rectangle verticalLine = getLine(
                    xAndY + 64 * index,    // Every line is spaced 64 pixels apart
                    BOARD_PADDING,
                    BOARD_X_AND_Y,
                    thickness
            );

            Rectangle horizontalLine = getLine(
                    BOARD_PADDING,
                    xAndY + 64 * index,
                    thickness,
                    BOARD_X_AND_Y
            );

            // Put these UI elements into the scene so they appear on screen
            root.getChildren().addAll(
                    verticalLine,
                    horizontalLine
            );

            index++;
        }
    }

    private Rectangle getLine(double x, double y, double height, double width){
        Rectangle line = new Rectangle();

        line.setX(x);    // horizontal position
        line.setY(y);    // vertical position
        line.setHeight(height);
        line.setWidth(width);

        line.setFill(Color.BLACK);
        return line;
    }

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
        this.listener = listener;

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
