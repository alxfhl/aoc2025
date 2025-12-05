package alxfhl.aoc2025;

import java.util.ArrayList;
import java.util.List;

public class Day02 {

    static long[] factors = {
            0L, 11L, 101L, 1_001L, 10_001L, 100_001L, 1_000_001L, 10_000_001L, 100_000_001L, 1_000_000_001L
    };

    record LongRange(long min, long max) {
    }


    public static long solve(String s) {
        final List<LongRange> ranges = parseRanges(s);
        long result = 0;
        for (LongRange range : ranges) {
            long start = getNext(range.min - 1);
            long end = getPrev(range.max + 1);
            for (long x = start; x <= end; x = getNext(x)) {
                result += x;
            }
        }
        return result;
    }

    public static long getNext(long min) {
        String s = Long.toString(min);
        int len = s.length();
        if (len % 2 == 0) {
            long factor = factors[len / 2];
            long candidate = Long.parseLong(s.substring(0, len / 2));

            if (candidate * factor > min) {
                return candidate * factor;
            } else {
                return (candidate + 1) * factor;
            }
        }
        long factor = factors[len / 2 + 1];
        return factor / 10 * factor;
    }

    public static long getPrev(long max) {
        String s = Long.toString(max);
        int len = s.length();
        if (len % 2 == 0) {
            long factor = factors[len / 2];
            long candidate = Long.parseLong(s.substring(0, len / 2));

            if (candidate * factor < max) {
                return candidate * factor;
            } else {
                return (candidate - 1) * factor;
            }
        }
        long factor = factors[len / 2];
        return (factor - 2) * factor;
    }

    public static long solve2(String s) {
        final List<LongRange> ranges = parseRanges(s);
        long result = 0;
        for (LongRange range : ranges) {
            for (long x = range.min; x <= range.max; x++) {
                if (isInvalid(x)) {
                    result += x;
                }
            }
        }
        return result;
    }

    private static boolean isInvalid(long x) {
        String s = Long.toString(x);
        int length = s.length();
        for (int len = 1; len <= length / 2; len++) {
            if ((length % len) != 0) {
                continue;
            }
            int repeat = length / len;
            if (s.equals(s.substring(0, len).repeat(repeat))) {
                return true;
            }
        }
        return false;
    }

    private static List<LongRange> parseRanges(String s) {
        List<LongRange> result = new ArrayList<>();
        for (String range : s.split("[\n, ]+")) {
            int minus = range.indexOf('-');
            if (minus == -1) {
                continue;
            }
            long min = Long.parseLong(range.substring(0, minus));
            long max = Long.parseLong(range.substring(minus + 1));
            result.add(new LongRange(min, max));
        }
        return result;
    }
}
