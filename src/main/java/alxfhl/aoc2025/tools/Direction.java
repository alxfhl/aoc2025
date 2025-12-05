package alxfhl.aoc2025.tools;

import lombok.AllArgsConstructor;

/**
 * Direction in a system with 0/0 being in the top left.
 */
@AllArgsConstructor
public enum Direction {
    UP(0, -1, '^'),
    DOWN(0, 1, 'v'),
    LEFT(-1, 0, '<'),
    RIGHT(1, 0, '>');

    private final int dx;
    private final int dy;
    private final char symbol;

    public static Direction valueOf(Coord from, Coord to) {
        for (Direction dir : values()) {
            if (from.go(dir).equals(to)) {
                return dir;
            }
        }
        throw new IllegalArgumentException("invalid direction");
    }

    public static Direction valueOf(char symbol) {
        for (Direction dir : values()) {
            if (dir.symbol == symbol) {
                return dir;
            }
        }
        throw new IllegalArgumentException("invalid direction");
    }

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }

    public char symbol() {
        return symbol;
    }

    public Direction turnLeft() {
        return switch (this) {
            case UP -> LEFT;
            case LEFT -> DOWN;
            case DOWN -> RIGHT;
            case RIGHT -> UP;
        };
    }

    public Direction turnRight() {
        return switch (this) {
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
        };
    }

    public Direction turnAround() {
        return switch (this) {
            case UP -> DOWN;
            case RIGHT -> LEFT;
            case DOWN -> UP;
            case LEFT -> RIGHT;
        };
    }

    public boolean isHorizontal() {
        return this == LEFT || this == RIGHT;
    }

    public boolean isVertical() {
        return this == UP || this == DOWN;
    }
}
