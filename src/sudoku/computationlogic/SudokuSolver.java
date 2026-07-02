package sudoku.computationlogic;

import sudoku.problemdomain.Coordinates;

import static sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

public class SudokuSolver {

    public static boolean puzzleIsSolvable (int[][] puzzle) {

        // collect a list of all empty positions
        Coordinates[] emptyCells = typeWriterEnumerate(puzzle);
        int[] lastTried = new int[40]; // tracks last value attempted per cell, 0 = none yet

        int index = 0;  // which empty cell we are filling\

        while (index < 40) {  // CHANGES: outer loop iterates for 40 cells
            Coordinates current = emptyCells[index];  // Pick current empty cell
            int input = lastTried[index] + 1; // resume from where we left off

            boolean placed = false;

            while (input <= GRID_BOUNDARY) {
                puzzle[current.getX()][current.getY()] = input;

                if (!GameLogic.sudokuIsInvalid(puzzle)) {
                    lastTried[index] = input;
                    placed = true;
                    break;
                }
                input++;
            }

            if (placed) {
                index++;
                if (index == 40) return true; // filled all 40 cells successfully
            } else {
                // no digit worked here — reset and backtrack
                puzzle[current.getX()][current.getY()] = 0;
                lastTried[index] = 0;
                index--;

                if (index < 0) return false; // exhausted all options at cell 0 — unsolvable
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
