package alxfhl.aoc2025.tools;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public record Coord(long x, long y) {
    /**
     * @return true if this coord is inside an area that is width x height in size and starts at 0/0.
     */
    public boolean isInGrid(long width, long height) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Coord go(Direction direction) {
        return new Coord(x + direction.dx(), y + direction.dy());
    }

    public Coord go(long dx, long dy) {
        return new Coord(x + dx, y + dy);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public List<Coord> getNeighbors() {
        return Arrays.stream(Direction.values())
                .map(dir -> new Coord(x + dir.dx(), y + dir.dy())).toList();
    }

    public Coord minus(Coord other) {
        return new Coord(x - other.x(), y - other.y());
    }

    public Coord plus(Coord other) {
        return new Coord(x + other.x(), y + other.y());
    }

    public Coord multiply(long factor) {
        return new Coord(x * factor, y * factor);
    }
}
