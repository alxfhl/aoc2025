package alxfhl.aoc2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day05 {

    record RangeInc(long min, long max) {
        boolean contains(long n) {
            return n >= min && n <= max;
        }

        public boolean overlaps(RangeInc other) {
            return other.min <= max && min <= other.max;
        }

        public RangeInc combineWith(RangeInc otherRange) {
            return new RangeInc(Math.min(min, otherRange.min), Math.max(max, otherRange.max));
        }

        public long size() {
            return max - min + 1;
        }
    }

    public static long solve(String s) {
        List<String> lines = Arrays.stream(s.split("\n")).toList();
        List<RangeInc> ranges = lines.stream().filter(l -> l.contains("-"))
                .map(l -> {
                    String[] parts = l.split("\\-");
                    return new RangeInc(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
                }).toList();
        List<Long> ingredients = lines.stream().filter(l -> l.matches("^\\d+$")).map(Long::parseLong).toList();

        return ingredients.stream().filter(i -> ranges.stream().anyMatch(r -> r.contains(i))).count();
    }

    public static long solve2(String s) {
        List<String> lines = Arrays.stream(s.split("\n")).toList();
        List<RangeInc> ranges = lines.stream().filter(l -> l.contains("-"))
                .map(l -> {
                    String[] parts = l.split("\\-");
                    return new RangeInc(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
                }).collect(Collectors.toCollection(ArrayList::new));
        int countBefore = 0;
        int countAfter = 1;
        while (countAfter != countBefore) {
            countBefore = ranges.size();
            ranges = combineOverlappingRanges(ranges);
            countAfter = ranges.size();
        }
        return ranges.stream().mapToLong(RangeInc::size).sum();
    }

    private static List<RangeInc> combineOverlappingRanges(List<RangeInc> ranges) {
        List<RangeInc> combinedRanges = new ArrayList<>();
        while (!ranges.isEmpty()) {
            RangeInc range = ranges.removeLast();
            List<RangeInc> toRemove = new ArrayList<>();
            for (RangeInc otherRange : ranges) {
                if (range.overlaps(otherRange)) {
                    range = range.combineWith(otherRange);
                    toRemove.add(otherRange);
                }
            }
            ranges.removeAll(toRemove);
            combinedRanges.add(range);
        }
        return combinedRanges;
    }
}
