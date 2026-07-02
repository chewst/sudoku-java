package sudoku.userinterface.logic;

import sudoku.computationlogic.GameLogic;
import sudoku.constants.GameState;
import sudoku.constants.Messages;
import sudoku.problemdomain.IStorage;
import sudoku.problemdomain.SudokuGame;
import sudoku.userinterface.IUserInterfaceContract;

import java.io.IOException;

public class ControlLogic implements IUserInterfaceContract.EventListener {

    private IStorage storage;

    private IUserInterfaceContract.View view;

    public ControlLogic(IStorage storage, IUserInterfaceContract.View view) {
        this.storage = storage;
        this.view = view;
    }

    @Override
    public void onSudokuInput(int x, int y, int input) {
        try{
            SudokuGame gameData = storage.getGameData();

            // work on a copy to avoid mutating original directly
            int[][] newGridState = gameData.getCopyOfGridState();

            newGridState[x][y] = input; // apply user input to selected cell

            // create new game instance with updated state
            gameData = new SudokuGame(
                    GameLogic.checkForCompletion(newGridState),
                    newGridState
            );

            storage.updateGameData(gameData);  // persist updated game state

            view.updateSquare(x ,y ,input); // update UI for the changed cell only

            // if game is complete, pop up the CONFIRMATION dialog
            if(gameData.getGameState() == GameState.COMPLETE){
                view.showDialog(Messages.GAME_COMPLETE);
            }

        } catch (IOException e) {
            e.printStackTrace();
            view.showError(Messages.ERROR);
        }
    }

    // when the user clicks OK on the Game Complete dialog
    @Override
    public void onDialogClick() {
        try {
            // generate and store a new Sudoku game
            storage.updateGameData(
                    GameLogic.getNewGame()
            );

            view.updateBoard(storage.getGameData());

        } catch (IOException e) {
            // show error if storage fails
            view.showError(Messages.ERROR);
        }
    }
}
