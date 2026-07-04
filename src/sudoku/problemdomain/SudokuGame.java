package sudoku.problemdomain;

import sudoku.computationlogic.SudokuUtilities;
import sudoku.constants.GameState;

import java.io.Serializable;

// A single complete Sudoku game state at one moment in time
public class SudokuGame implements Serializable {

    private final GameState gameState;
    private final int[][] gridState;
    private final boolean[][] fixedCells;

    public static final int GRID_BOUNDARY = 9;

    // Used when creating a brand new puzzle — derives fixedCells from the
    // initial grid (any non-zero cell at generation time is a preset clue)
    public SudokuGame(GameState gameState, int[][] gridState) {
        this.gameState = gameState;
        this.gridState = gridState;
        this.fixedCells = deriveFixedCells(gridState);
    }

    // Used when creating an updated game state (e.g. after a player move),
    // so the original fixed cells carry forward unchanged
    public SudokuGame(GameState gameState, int[][] gridState, boolean[][] fixedCells) {
        this.gameState = gameState;
        this.gridState = gridState;
        this.fixedCells = fixedCells;
    }

    private static boolean[][] deriveFixedCells(int[][] gridState) {
        boolean[][] fixed = new boolean[GRID_BOUNDARY][GRID_BOUNDARY];
        for (int x = 0; x < GRID_BOUNDARY; x++) {
            for (int y = 0; y < GRID_BOUNDARY; y++) {
                fixed[x][y] = gridState[x][y] != 0;
            }
        }
        return fixed;
    }

    public int[][] getGridState() {
        return gridState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public boolean[][] getFixedCells() {
        return fixedCells;
    }

    public int[][] getCopyOfGridState() {
        return SudokuUtilities.copyToNewArray(gridState);
    }
}
