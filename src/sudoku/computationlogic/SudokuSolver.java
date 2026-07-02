package sudoku.computationlogic;

import sudoku.problemdomain.Coordinates;

import static sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

public class SudokuSolver {

    public static boolean puzzleIsSolvable (int[][] puzzle){

        // collect a list of all empty positions
        Coordinates[] emptyCells = typeWriterEnumerate(puzzle);

        int index = 0;  // which empty cell we are filling
        int input;  // number we are trying (1–9)

        while (index < 40)  {  // CHANGES: outer loop iterates for 40 cells
            Coordinates current = emptyCells[index];  // Pick current empty cell

            input = 1;

            while (input <= 9) {   // CHANGES: inner loop iterates for trying 9 digits
                puzzle[current.getX()][current.getY()] = input;  // try placing input number

                if(GameLogic.sudokuIsInvalid(puzzle)) {  // is it valid?

                    puzzle[current.getX()][current.getY()] = 0; // CHANGES: reset cell before checking next attempt, undo invalid placement

                    // back to first empty cell and have tried all possible numbers (1–9)
                    if (index == 0 && input == GRID_BOUNDARY) {
                        return false;    // not solvable

                    }

                    // none of 1-9 works for current cell, go back to previous cell
                    else if (input == GRID_BOUNDARY) {
                        index--;  // backtrack
                    }

                    input ++;  // try next digit

                // valid
                } else {
                    index ++;  //

                    if (index ==39) return true;  // filled the last cell, puzzle solved

                    break; // CHANGES: move to next cell
                }

            }
        }

        return false;
    }

    // Collecting all empty cells (0s) in the Sudoku board and storing their (x, y) positions
    private static Coordinates[] typeWriterEnumerate(int[][] puzzle) {
        Coordinates[] emptyCells = new Coordinates[40];
        int iterator = 0;
        for (int y = 0; y < GRID_BOUNDARY; y++) {
            for (int x = 0; x < GRID_BOUNDARY; x++) {

                // Every empty cell (0) found gets recorded into emptyCells
                if (puzzle[x][y] == 0) {
                    emptyCells[iterator] = new Coordinates(x, y);
                    if (iterator == 39) return emptyCells;
                    iterator++;
                }
            }
        }

        return emptyCells;
    }

}
