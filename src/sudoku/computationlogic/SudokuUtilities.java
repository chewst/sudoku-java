package sudoku.computationlogic;

import sudoku.problemdomain.SudokuGame;

public class SudokuUtilities {

    // Copies values from one existing array into another existing array
    public static void copySudokuArrayValues(int[][] oldArray, int[][] newArray) {
        for (int xIndex = 0; xIndex< SudokuGame.GRID_BOUNDARY; xIndex++){
            for (int yIndex = 0; yIndex < SudokuGame.GRID_BOUNDARY; yIndex++){
                newArray[xIndex][yIndex] = oldArray[xIndex][yIndex];
            }
        }
    }

    // Creates and return a new array copy of the Sudoku grid
    public static int[][] copyToNewArray(int[][] oldArray){
        int[][] newArray = new int[SudokuGame.GRID_BOUNDARY][SudokuGame.GRID_BOUNDARY];

        for (int xIndex = 0; xIndex< SudokuGame.GRID_BOUNDARY; xIndex++){
            for (int yIndex = 0; yIndex < SudokuGame.GRID_BOUNDARY; yIndex++){
                newArray[xIndex][yIndex] = oldArray[xIndex][yIndex];
            }
        }

        return newArray;
    }
}
