package sudoku.computationlogic;

import sudoku.problemdomain.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

public class GameGenerator {
    public static int[][] getNewGameGrid() {
        return unsolveGame(getSolvedGame());
    }

    // generate a fully solved, valid 9×9 Sudoku grid
    private static int[][] getSolvedGame() {
        Random random = new Random(System.currentTimeMillis());
        int[][] newGrid = new int [GRID_BOUNDARY][GRID_BOUNDARY];

        // Value represents potential values for each square. Each value must be allocated 9 times
        for (int value = 1; value <= GRID_BOUNDARY; value++) {

            int allocations = 0;  // number of times in which a square has been given a value

            // If too many allocation attempts are made which end in an invalid game, we grab the most recent
            // allocations stored in the List below, and reset them all to 0 (empty)
            int interrupt = 0;  // tracks failed attempts for the current digit

            // Keep track of what has been allocated in the current frame of the loop
            // if things go wrong, those specific placements can be undone (reset back to 0) without wiping the whole grid
            List<Coordinates> allocTracker = new ArrayList<>();

            // As a failsafe, if we keep rolling back allocations on the most recent frame, and the game still
            // keeps breaking, after 500 times we reset the board entirely and start again
            int attempts = 0;  //  tracks how many times you've given up on this digit and restarted it

            while (allocations < GRID_BOUNDARY) { // Keep placing current number until it's used 9 times

                //
                if (interrupt > 200) {
                    allocTracker.forEach(coord -> {
                       newGrid[coord.getX()][coord.getY()] = 0;
                    });

                    // Reset counters
                    interrupt = 0;
                    allocations = 0;
                    allocTracker.clear();
                    attempts++;

                    // Start whole Sudoku from scratch from digit 1
                    if (attempts > 500) {
                        clearArray(newGrid);
                        attempts = 0;
                        value = 1;
                    }
                }

                // Pick a random cell
                int xCoordinate = random.nextInt(GRID_BOUNDARY);
                int yCoordinate = random.nextInt(GRID_BOUNDARY);

                if (newGrid[xCoordinate][yCoordinate] == 0) {
                    newGrid[xCoordinate][yCoordinate] = value;

                    // if value results in an invalid game, then re-assign that element to 0 and try again
                    if (GameLogic.sudokuIsInvalid(newGrid)) {
                        newGrid[xCoordinate][yCoordinate] = 0;
                        interrupt++;
                    }
                    // otherwise, indicate that a value has been allocated, and add it to the allocation tracker
                    else {
                        allocTracker.add(new Coordinates(xCoordinate, yCoordinate));
                        allocations++;
                    }
                }
            }
        }

        return newGrid;  // a fully valid solved Sudoku board
    }

    // Resets whole board to 0
    private static void clearArray(int[][] newGrid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++){
                newGrid[xIndex][yIndex] = 0;
            }
        }
    }
}
