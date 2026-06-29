package sudoku.problemdomain;

import java.io.IOException;

/**
 * Defines a contract for storing and retrieving Sudoku game data.
 *
 * Implementations of this interface are responsible for handling
 * the persistence of a SudokuGame, such as saving to a file,
 * database, or other storage medium.
 *
 * Note:
 * - Methods may throw IOException if a read/write operation fails.
 * - This abstraction allows the storage mechanism to be changed
 *   without affecting the core game logic.
 */
public interface IStorage {

    /**
     * Saves or updates the current Sudoku game data.
     *
     * @param game the SudokuGame instance to be stored
     * @throws IOException if an error occurs during writing
     */
    void updateGameData(SudokuGame game) throws IOException;

    /**
     * Retrieves the stored Sudoku game data.
     *
     * @return the previously saved SudokuGame
     * @throws IOException if an error occurs during reading
     */
    SudokuGame getGameData() throws IOException;
}
