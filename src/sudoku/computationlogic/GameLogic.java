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
        if (rowsAreInvalid(grid)) return true;      // row check
        if (columnsAreInvalid(grid)) return true;   // columns check
        if (squaresAreInvalid(grid)) return true;   // box checks

        else return false;
    }

    // check each rows for duplicates
    private static boolean rowsAreInvalid(int[][] grid) {
        for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
            List<Integer> row = new ArrayList<>();
            for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
                row.add(grid[xIndex][yIndex]);
            }

            if (collectionHasRepeats(row)) return true;
        }

        return false;
    }

    // check each column for duplicates
    private static boolean columnsAreInvalid(int[][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            List<Integer> row = new ArrayList<>();
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                row.add(grid[xIndex][yIndex]);
            }

            if (collectionHasRepeats(row)) return true;
        }

        return false;
    }

    // 3x3 box check - splits the board into 3 horizontal layers of boxes:
    // top, middle, bottom row of boxes
    private static boolean squaresAreInvalid(int[][] grid) {
        if (rowOfSqauresIsInvalid(Rows.TOP, grid)) return true;
        if (rowOfSqauresIsInvalid(Rows.MIDDLE, grid)) return true;
        if (rowOfSqauresIsInvalid(Rows.BOTTOM, grid)) return true;

        return false;
    }

    // extracts 3x3 block, here are the top left coordinates for each 3x3 box
    private static boolean rowOfSqauresIsInvalid(Rows value, int[][] grid) {
        switch (value) {
            case TOP:
                if (squareIsInvalid (0,0,grid)) return true;
                if (squareIsInvalid (0,3,grid)) return true;
                if (squareIsInvalid (0,6,grid)) return true;
                return false;


            case MIDDLE:
                if (squareIsInvalid (3,0,grid)) return true;
                if (squareIsInvalid (3,3,grid)) return true;
                if (squareIsInvalid (3,6,grid)) return true;
                return false;


            case BOTTOM:
                if (squareIsInvalid (6,0,grid)) return true;
                if (squareIsInvalid (6,3,grid)) return true;
                if (squareIsInvalid (6,6,grid)) return true;
                return false;

            default:
                return false;
        }
    }

    // checks one 3×3 box
    private static boolean squareIsInvalid(int xIndex, int yIndex, int[][] grid) {

        // define box boundaries
        int yIndexEnd = yIndex + 3;
        int xIndexEnd = xIndex + 3;

        List<Integer> square = new ArrayList<>();

        // extract the 3×3 values
        while (yIndex < yIndexEnd) {
            while (xIndex < xIndexEnd) {
                square.add(
                        grid[xIndex][yIndex]
                );

                xIndex++;
            }

            xIndex -= 3;   // reset x

            yIndex ++;
        }

        // check for duplicates
        if (collectionHasRepeats(square)) return true;
        return false;
    }

    // check is there any repeatition
    private static boolean collectionHasRepeats(List<Integer> collection) {
        for (int index = 1; index <= GRID_BOUNDARY; index++) {

            if(Collections.frequency(collection, index) > 1) return true;

        }

        return false;
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
