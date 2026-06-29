package sudoku.problemdomain;

import java.util.Objects;

/**
 * Represents a coordinate (x, y) position in the Sudoku grid.
 * This class is immutable and used as a value object.
 */
public class Coordinates {

    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Checks if this Coordinate is equal to another object.
     * Two Coordinates are considered equal if they have the same x and y values.
     *
     * Includes safety checks:
     * - Same object reference check (this == o)
     * - Null check
     * - Type check (getClass comparison)
     *
     * @param o   the reference object with which to compare.
     * @return true if both coordinates are equal, false otherwise
     */
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;  // cast to Coordinates
        return x == that.x &&
                y == that.y;
    }

    /**
     * Returns a hash code for this Coordinate.
     *
     * The hash code is generated using x and y values.
     * This ensures that equal objects have the same hash code,
     * which is required for correct behavior in HashMap and HashSet.
     *
     * @return hash code value
     */
    @Override
    public int hashCode(){
        return Objects.hash(x,y);  // return x == that.x && y == that.y;
    }
}
