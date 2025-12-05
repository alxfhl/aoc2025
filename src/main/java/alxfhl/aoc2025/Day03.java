package alxfhl.aoc2025;

public class Day03 {

    public static long solve(String s) {
        long result = 0;
        for (String line : s.split("\n")) {
            if (line.isEmpty()) {
                continue;
            }
            int len = line.length();
            int max = 0;
            for (int x = 0; x < len - 1; x++) {
                char first = line.charAt(x);
                for (int y = x + 1; y < len; y++) {
                    char second = line.charAt(y);
                    int value = Integer.valueOf(first + "" + second);
                    if (value > max) {
                        max = value;
                    }
                }
            }
            result += max;
        }
        return result;
    }

    public static long solve2(String s) {
        long result = 0;
        for (String line : s.split("\n")) {
            if (line.isEmpty()) {
                continue;
            }
            String max = getMax(line, 12);
            result += Long.parseLong(max);
        }
        return result;
    }

    private static String getMax(String line, int digits) {
        if (digits == 0) {
            return "";
        }
        int len = line.length();
        char max = line.charAt(0);
        int pos = 0;
        for (int x = 1; x < len - digits + 1; x++) {
            if (line.charAt(x) > max) {
                max = line.charAt(x);
                pos = x;
            }
        }
        return max + getMax(line.substring(pos + 1), digits - 1);
    }
}
