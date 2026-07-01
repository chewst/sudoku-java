package sudoku.userinterface;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sudoku.constants.GameState;
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
        final int xOrigin = 50;
        final int yOrigin = 50;

        final int xAndYDelta = 64;

        // O(n^2) Runtime Complexity
        // Create 9x9 = 81 Sudoku cells
        for (int xIndex = 0; xIndex < 9; xIndex++){
            for (int yIndex = 0; yIndex < 9; yIndex++){

                int x = xOrigin + xIndex * xAndYDelta;
                int y = yOrigin + yIndex * xAndYDelta;

                // Create a Sudoku cell with its grid coordinates (not pixel position)
                SudokuTextField tile = new SudokuTextField(xIndex, yIndex);

                styleSudokuTile(tile,x,y);  // // Apply styling and position to the UI element

                tile.setOnKeyPressed(this); // listen from input from the user on this cell

                // Store this cell in its coordinate
                textFieldCoordinates.put(new Coordinates(xIndex,yIndex), tile);

                root.getChildren().add(tile);
            }
        }
    }

    private void styleSudokuTile(SudokuTextField tile, int x, int y) {
        Font numberFont = new Font(32);

        tile.setFont(numberFont);
        tile.setAlignment(Pos.CENTER);

        tile.setLayoutX(x);
        tile.setLayoutY(y);

        // set default size
        tile.setPrefHeight(64);
        tile.setPrefWidth(64);

        tile.setBackground(Background.EMPTY); // set background transparent
    }

    private void drawSudokuBoard(Group root) {
        Rectangle boardBackground = new Rectangle();
        boardBackground.setX(BOARD_PADDING);
        boardBackground.setY(BOARD_PADDING);

        boardBackground.setWidth(BOARD_X_AND_Y);
        boardBackground.setHeight(BOARD_X_AND_Y);

        boardBackground.setFill(BOARD_BACKGROUND_COLOUR);

        root.getChildren().addAll(boardBackground);

    }

    private void drawTitle(Group root) {
        Text title = new Text(235,690, SUDOKU);
        title.setFill(Color.WHITE);
        Font titleFont = new Font(43);
        title.setFont(titleFont);

        root.getChildren().add(title);
    }

    private void drawBackground(Group root) {
        Scene scene = new Scene(root, WINDOW_X, WINDOW_Y);
        scene.setFill(WINDOW_BACKGROUND_COLOUR);
        stage.setScene(scene);
        
    }

    @Override
    public void setListener(IUserInterfaceContract.EventListener listener) {
        this.listener = listener;

    }

    @Override
    public void handle(KeyEvent keyEvent) {

    }

    // Update one cell in the Sudoku grid
    @Override
    public void updateSquare(int x, int y, int input) {
        // Find the correct TextField
        SudokuTextField tile = textFieldCoordinates.get(new Coordinates(x,y));

        // Convert number to string, UI only accepts text, not int
        String value = Integer.toString(
                input
        );

        if (value.equals("0")) value = "";  // Handle empty cell

        tile.textProperty().setValue(value);  // Update UI

    }

    // Update the entire 9×9 board at once
    // starts a new game when game ends
    @Override
    public void updateBoard(SudokuGame game) {
        for (int xIndex = 0; xIndex < 9; xIndex++ ) {
            for (int yIndex = 0; yIndex < 9; yIndex++ ){
                TextField tile = textFieldCoordinates.get(new Coordinates(xIndex, yIndex));

                String value = Integer.toString(
                        game.getCopyOfGridState()[xIndex][yIndex]
                );

                if (value.equals("0")) value = "";

                tile.setText(
                        value
                );  // Update UI

                // JavaFX CSS styling
                if (game.getGameState() == GameState.NEW) {

                    // Empty cells, player can type them
                    if (value.equals("")) {
                        tile.setStyle("-fx-opacity: 1;");  // fully visible
                        tile.setDisable(false);
                    } else {
                        tile.setStyle("-fx-opacity: 0.8;");  // slightly faded
                        tile.setDisable(true);
                    }
                }
            }
        }
    }

    @Override
    public void showDialog(String message) {

    }

    @Override
    public void showError(String message) {

    }
}
