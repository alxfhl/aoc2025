package alxfhl.aoc2025;

import alxfhl.aoc2025.tools.CharMatrix;
import alxfhl.aoc2025.tools.Coord;
import alxfhl.aoc2025.tools.Direction;

import java.util.*;

public class Day07 {

    public static long solve(String s) {
        CharMatrix matrix = CharMatrix.valueOf(Arrays.asList(s.split("\n")));
        long splits = 0;
        Set<Coord> beams = new HashSet<>(matrix.findAll('S'));
        if (beams.size() != 1) {
            throw new IllegalArgumentException("start size != 1");
        }
        while (true) {
            Set<Coord> newBeams = new HashSet<>();
            for (Coord beam : beams) {
                beam = beam.go(Direction.DOWN);
                if (matrix.isOutside(beam)) {
                    continue;
                }
                if (matrix.get(beam) == '.') {
                    newBeams.add(beam);
                    matrix.set(beam, '|');
                } else if (matrix.get(beam) == '^') {
                    splits++;
                    Coord left = beam.go(Direction.LEFT);
                    Coord right = beam.go(Direction.RIGHT);
                    newBeams.add(left);
                    newBeams.add(right);
                    matrix.set(left, '|');
                    matrix.set(right, '|');
                }
            }
            if (newBeams.isEmpty()) {
                break;
            }
            beams = newBeams;
        }
        return splits;
    }

    public static long solve2(String s) {
        CharMatrix matrix = CharMatrix.valueOf(Arrays.asList(s.split("\n")));
        List<Coord> starts = matrix.findAll('S');
        if (starts.size() != 1) {
            throw new IllegalArgumentException("start size != 1");
        }
        Map<Coord, Long> beams = new HashMap<>();
        beams.put(starts.getFirst(), 1L);
        while (true) {
            Map<Coord, Long> newBeams = new HashMap<>();
            for (var entry : beams.entrySet()) {
                Coord beam = entry.getKey().go(Direction.DOWN);
                long count = entry.getValue();
                if (matrix.isOutside(beam)) {
                    continue;
                }
                if (matrix.get(beam) == '.' || matrix.get(beam) == '|') {
                    newBeams.compute(beam, (_, v) -> v == null ? count : v + count);
                    matrix.set(beam, '|');
                } else if (matrix.get(beam) == '^') {
                    Coord left = beam.go(Direction.LEFT);
                    Coord right = beam.go(Direction.RIGHT);
                    newBeams.compute(left, (_, v) -> v == null ? count : v + count);
                    newBeams.compute(right, (_, v) -> v == null ? count : v + count);
                    matrix.set(left, '|');
                    matrix.set(right, '|');
                }
            }
            if (newBeams.isEmpty()) {
                break;
            }
            beams = newBeams;
        }
        return beams.values().stream().mapToLong(v -> v).sum();
    }
}
