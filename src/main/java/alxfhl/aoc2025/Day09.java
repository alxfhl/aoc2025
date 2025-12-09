package alxfhl.aoc2025;

import alxfhl.aoc2025.tools.CharMatrix;
import alxfhl.aoc2025.tools.Coord;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class Day09 {

    private static List<Coord> tiles;
    private static List<Long> allXs;
    private static List<Long> allYs;
    private static CharMatrix map;

    public static long solve(String s) {
        List<Coord> tiles = Arrays.stream(s.split("\n")).filter(l -> !l.isBlank()).map(l -> l.split(","))
                .map(p -> new Coord(Integer.parseInt(p[0]), Integer.parseInt(p[1]))).toList();
        long result = 0;
        for (int i = 0; i < tiles.size(); i++) {
            Coord tile1 = tiles.get(i);
            for (int j = i + 1; j < tiles.size(); j++) {
                Coord tile2 = tiles.get(j);
                long dx = Math.abs(tile1.x() - tile2.x()) + 1;
                long dy = Math.abs(tile1.y() - tile2.y()) + 1;
                if (dx * dy > result) {
                    result = dx * dy;
                }
            }
        }
        return result;
    }

    public static long solve2(String s) {
        tiles = Arrays.stream(s.split("\n")).filter(l -> !l.isBlank()).map(l -> l.split(","))
                .map(p -> new Coord(Integer.parseInt(p[0]), Integer.parseInt(p[1]))).toList();
        // this is probably not the most efficient solution, but I think it is fun - we draw a helper image that is not to scale
        createScaledMap();
        drawOutline();
        floodFill();
        System.out.println(map);
        long result = 0;
        for (int i = 0; i < tiles.size(); i++) {
            Coord tile1 = tiles.get(i);
            for (int j = i + 1; j < tiles.size(); j++) {
                Coord tile2 = tiles.get(j);
                long minx = Math.min(tile1.x(), tile2.x());
                long maxx = Math.max(tile1.x(), tile2.x());
                long miny = Math.min(tile1.y(), tile2.y());
                long maxy = Math.max(tile1.y(), tile2.y());
                long area = (maxx - minx + 1) * (maxy - miny + 1);
                if (area > result) {
                    // check that no tile is inside this area
                    if (tiles.stream().filter(t -> t != tile1 && t != tile2)
                            .noneMatch(t -> t.x() > minx && t.x() < maxx && t.y() > miny && t.y() < maxy)) {
                        if (isMapFilled(shrinkX(minx), shrinkY(miny), shrinkX(maxx), shrinkY(maxy))) {
                            result = area;
                        }
                    }
                }
            }
        }
        return result;
    }

    private static boolean isMapFilled(int minX, int minY, int maxX, int maxY) {
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                if (map.get(x, y) == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void createScaledMap() {
        allXs = tiles.stream().map(Coord::x).distinct().sorted().toList();
        allYs = tiles.stream().map(Coord::y).distinct().sorted().toList();
        int mapWidth = allXs.size() * 2 + 1;
        int mapHeight = allYs.size() * 2 + 1;
        map = new CharMatrix(mapWidth, mapHeight, ' ');
    }

    private static void drawOutline() {
        for (int i = 0; i < tiles.size(); i++) {
            Coord from = tiles.get(i);
            Coord to = tiles.get((i + 1) % tiles.size());
            from = shrink(from);
            to = shrink(to);
            if (from.x() == to.x()) {
                int x = (int) from.x();
                int min = (int) Math.min(from.y(), to.y());
                int max = (int) Math.max(from.y(), to.y());
                for (int y = min; y <= max; y++) {
                    map.set(x, y, 'X');
                }
            } else {
                int y = (int) from.y();
                int min = (int) Math.min(from.x(), to.x());
                int max = (int) Math.max(from.x(), to.x());
                for (int x = min; x <= max; x++) {
                    map.set(x, y, 'X');
                }
            }
        }
        for (Coord tile : tiles) {
            map.set(shrink(tile), '#');
        }
    }

    private static void floodFill() {
        LinkedHashSet<Coord> todo = new LinkedHashSet<>();
        // paint outside with '.'
        todo.add(new Coord(0, 0));
        while (!todo.isEmpty()) {
            Coord c = todo.removeLast();
            map.set(c, '.');
            for (Coord neighbor : c.getNeighbors()) {
                if (map.isInside(neighbor) && map.get(neighbor) == ' ') {
                    todo.add(neighbor);
                }
            }
        }
        // paint inside with 'X'
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                if (map.get(x, y) == ' ') {
                    map.set(x, y, 'X');
                }
            }
        }
    }

    private static Coord shrink(Coord coord) {
        return new Coord(shrinkX(coord.x()), shrinkY(coord.y()));
    }

    private static int shrinkX(long x) {
        return allXs.indexOf(x) * 2 + 1;
    }

    private static int shrinkY(long y) {
        return allYs.indexOf(y) * 2 + 1;
    }
}
