package sudoku.buildlogic;

import sudoku.computationlogic.GameLogic;
import sudoku.problemdomain.IStorage;
import sudoku.problemdomain.SudokuGame;
import sudoku.userinterface.IUserInterfaceContract;
import sudoku.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {

    // set up the entire app using this UI
    public static void build(IUserInterfaceContract.View userInterface) throws IOException {

        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();  // Create storage layer


        try {
            initialState = storage.getGameData();
        } catch (IOException e) {
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        // Create controller (event handler)
        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);

        userInterface.setListener(uiLogic);  // Link UI to controller
        userInterface.updateBoard(initialState);  // renders the first Sudoku board on screen
    }
}
