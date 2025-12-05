package alxfhl.aoc2025;

import java.util.Arrays;
import java.util.List;

public class Day04 {

    private static final List<Integer> dx = List.of(-1, 0, 1, -1, 1, -1, 0, 1);
    private static final List<Integer> dy = List.of(-1, -1, -1, 0, 0, 1, 1, 1);

    public static int solve(String input) {
        char[][] map = Arrays.stream(input.split("\n"))
                .filter(s -> !s.isBlank())
                .map(String::toCharArray)
                .toArray(char[][]::new);
        int result = 0;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (!isPaper(map, x, y)) {
                    continue;
                }
                int papers = 0;
                for (int i = 0; i < dx.size(); i++) {
                    if (isPaper(map, x + dx.get(i), y + dy.get(i))) {
                        papers++;
                    }
                }
                if (papers < 4) {
                    result++;
                }
            }
        }
        return result;
    }

    private static boolean isPaper(char[][] map, int x, int y) {
        if (x < 0 || y < 0 || x >= map[0].length || y >= map.length) {
            return false;
        }
        return map[y][x] == '@';
    }

    public static long solve2(String input) {
        char[][] map = Arrays.stream(input.split("\n"))
                .filter(s -> !s.isBlank())
                .map(String::toCharArray)
                .toArray(char[][]::new);
        int result = 0;
        boolean repeat = false;
        do {
            repeat = false;
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[0].length; x++) {
                    if (!isPaper(map, x, y)) {
                        continue;
                    }
                    int papers = 0;
                    for (int i = 0; i < dx.size(); i++) {
                        if (isPaper(map, x + dx.get(i), y + dy.get(i))) {
                            papers++;
                        }
                    }
                    if (papers < 4) {
                        result++;
                        map[y][x] = '.';
                        repeat = true;
                    }
                }
            }
        } while (repeat);
        return result;
    }
}
