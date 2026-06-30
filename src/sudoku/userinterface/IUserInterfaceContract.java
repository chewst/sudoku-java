package sudoku.userinterface;

import sudoku.problemdomain.SudokuGame;

// This interface acts as a contract between the UI layer and the game logic layer.
// It defines how both sides communicate without directly depending on each other.
public interface IUserInterfaceContract {

    // Handles user actions from the UI (UI to Logic)
    interface EventListener {

        // Called when the user inputs a number into a Sudoku cell
        // x, y = position of the cell
        // input = number entered by the user
        void onSudokuInput(int x, int y,int input);

        // Called when the user interacts with a dialog (e.g. presses OK)
        void onDialogClick();
    }

    // Defines what the UI must implement (Logic to UI)
    interface View{

        // Sets the listener so UI can send user actions to the logic
        void setListener(IUserInterfaceContract.EventListener listener);
        void updateSquare(int x, int y, int input);
        void updateBoard(SudokuGame game);
        void showDialog(String message);
        void showError(String message);
    }
}
