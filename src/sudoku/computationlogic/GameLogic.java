package sudoku.computationlogic;

import sudoku.constants.GameState;
import sudoku.constants.Rows;
import sudoku.problemdomain.SudokuGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

public class GameLogic {

    // create new game
    public static SudokuGame getNewGame() {
        return new SudokuGame(
                GameState.NEW,
                GameGenerator.getNewGameGrid()
        );
    }

    // check if game is finished
    public static GameState checkForCompletion(int[][]  grid) {
        if (sudokuIsInvalid(grid)) return GameState.ACTIVE;  // check is there still duplicates
        if (tilesAreNotFilled(grid)) return GameState.ACTIVE;  // check are there still empty cells
        return GameState.COMPLETE;
    }

    // check if board is valid (no duplicate)
    public static boolean sudokuIsInvalid(int[][] grid) {

    }


    // checks are there still empty cells (0)
    private static boolean tilesAreNotFilled (int[][] grid){
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                if (grid[xIndex][yIndex] == 0) return true;
            }
        }

        return false;
    }
}
