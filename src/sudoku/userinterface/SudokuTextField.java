package sudoku.userinterface;

import javafx.scene.control.TextField;

// Custom TextField for Sudoku cells
// Stores its position and restricts user input
public class SudokuTextField extends TextField {

    private final int x;
    private final int y;

    public SudokuTextField(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Controls text replacement (typing)
    @Override
    public void replaceText(int i, int i1, String s){
        if (!s.matches("[0-9]")){  // Allow only digits (change to [1-9] for Sudoku rules)
            super.replaceText(i,i1,s);
        }
    }

    // Controls text replacement (pasting / selection)
    @Override
    public void replaceSelection(String s){
        if (!s.matches("[0-9]")){
            super.replaceSelection(s);
        }
    }
}
